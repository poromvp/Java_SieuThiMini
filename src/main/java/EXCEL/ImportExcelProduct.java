package EXCEL;


import BLL.LoaiSanPhamBLL;
import BLL.NhaCungCapBLL;
import BLL.SanPhamBLL;
import DTO.LoaiSanPhamDTO;
import DTO.NhaCungCapDTO;
import DTO.SanPhamDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ImportExcelProduct {
    private static NhaCungCapBLL nhaCungCapBLL = new NhaCungCapBLL();
    private static LoaiSanPhamBLL loaiSanPhamBLL = new LoaiSanPhamBLL();

    public static boolean importProduct(JFrame parent) {
        try {
            // Đổi Look & Feel sang hệ điều hành
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel files", "xlsx", "xls"));
        int result = fileChooser.showOpenDialog(parent);
        if (result != JFileChooser.APPROVE_OPTION) {
            return false;
        }

        List<String> errors = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileChooser.getSelectedFile());
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Bỏ qua dòng tiêu đề

                try {
                    SanPhamDTO product = new SanPhamDTO();
                    product.setTenSP(getCellStringValue(row.getCell(0), row.getRowNum()));
                    product.setGia(getCellNumericValue(row.getCell(1), row.getRowNum()));
                    product.setSoLuongTon((int) getCellNumericValue(row.getCell(2), row.getRowNum()));
                    product.setMaLSP((int) getCellNumericValue(row.getCell(3), row.getRowNum()));
                    product.setMaNCC((int) getCellNumericValue(row.getCell(4), row.getRowNum()));
                    product.setMoTa(getCellStringValue(row.getCell(5), row.getRowNum()));
                    product.setTrangThai(getCellStringValue(row.getCell(6), row.getRowNum()));
                    product.setTenAnh("product-default.png"); // Giá trị mặc định cho TenAnh

                    // Kiểm tra dữ liệu
                    if (product.getTenSP().isEmpty() || product.getGia() <= 0 || product.getSoLuongTon() < 0) {
                        errors.add("Dòng " + (row.getRowNum() + 1) + ": Dữ liệu không hợp lệ (tên, giá, hoặc số lượng).");
                        continue;
                    }
                    if (!product.getTrangThai().equals("ACTIVE") && !product.getTrangThai().equals("INACTIVE")) {
                        errors.add("Dòng " + (row.getRowNum() + 1) + ": Trạng thái phải là ACTIVE hoặc INACTIVE.");
                        continue;
                    }

                    // Kiểm tra MaNCC và MaLSP
                    NhaCungCapDTO ncc = nhaCungCapBLL.getNhaCungCap(product.getMaNCC());
                    if (ncc == null) {
                        errors.add("Dòng " + (row.getRowNum() + 1) + ": Mã nhà cung cấp " + product.getMaNCC() + " không tồn tại.");
                        continue;
                    }
                    LoaiSanPhamDTO lsp = loaiSanPhamBLL.getLoaiSanPham(product.getMaLSP());
                    if (lsp == null) {
                        errors.add("Dòng " + (row.getRowNum() + 1) + ": Mã loại sản phẩm " + product.getMaLSP() + " không tồn tại.");
                        continue;
                    }

                    if (!SanPhamBLL.addProduct(product)) {
                        errors.add("Dòng " + (row.getRowNum() + 1) + ": Thêm sản phẩm " + product.getTenSP() + " thất bại.");
                    }
                } catch (Exception ex) {
                    errors.add("Dòng " + (row.getRowNum() + 1) + ": Lỗi - " + ex.getMessage());
                }
            }

            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(parent, "Nhập dữ liệu hoàn thành, nhưng có lỗi:\n" + String.join("\n", errors));
                return false;
            } else {
                JOptionPane.showMessageDialog(parent, "Nhập dữ liệu từ Excel thành công!");
                return true;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parent, "Lỗi khi nhập Excel: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    private static double getCellNumericValue(Cell cell, int rowNum) {
        if (cell == null || cell.getCellType() != CellType.NUMERIC) {
            throw new IllegalArgumentException("Ô số tại dòng " + (rowNum + 1) + " không hợp lệ.");
        }
        return cell.getNumericCellValue();
    }

    private static String getCellStringValue(Cell cell, int rowNum) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return "";
        }
    }
}
