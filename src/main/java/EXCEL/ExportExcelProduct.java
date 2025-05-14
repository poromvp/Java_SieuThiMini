package EXCEL;

import BLL.LoaiSanPhamBLL;
import BLL.NhaCungCapBLL;
import BLL.SanPhamBLL;
import DTO.LoaiSanPhamDTO;
import DTO.NhaCungCapDTO;
import DTO.SanPhamDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ExportExcelProduct {
    public static boolean exportProduct(JFrame parent) {
        try {
            // Đổi Look & Feel sang hệ điều hành
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("DanhSachSanPham.xlsx"));
        int result = fileChooser.showSaveDialog(parent);
        if (result != JFileChooser.APPROVE_OPTION) {
            return false;
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Danh sách sản phẩm");

            // Tạo dòng tiêu đề
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Mã SP", "Tên SP", "Giá", "Số lượng tồn", "Loại", "Nhà cung cấp", "Mô tả", "Trạng thái"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Lấy dữ liệu từ BLL
            List<SanPhamDTO> products = SanPhamBLL.getAllProducts();
            LoaiSanPhamBLL lspBLL = new LoaiSanPhamBLL();
            NhaCungCapBLL nccBLL = new NhaCungCapBLL();
            int rowNum = 1;

            for (SanPhamDTO product : products) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(product.getMaSP());
                row.createCell(1).setCellValue(product.getTenSP());
                row.createCell(2).setCellValue(product.getGia());
                row.createCell(3).setCellValue(product.getSoLuongTon());
                LoaiSanPhamDTO lsp = lspBLL.getLoaiSanPham(product.getMaLSP());
                row.createCell(4).setCellValue(lsp != null ? lsp.getTenLoaiSP() : "Chưa cập nhật");
                NhaCungCapDTO ncc = nccBLL.getNhaCungCap(product.getMaNCC());
                row.createCell(5).setCellValue(ncc != null ? ncc.getTenNCC() : "Chưa cập nhật");
                row.createCell(6).setCellValue(product.getMoTa());
                row.createCell(7).setCellValue(product.getTrangThai());
            }

            // Tự động điều chỉnh kích thước cột
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi file
            try (FileOutputStream fos = new FileOutputStream(fileChooser.getSelectedFile())) {
                workbook.write(fos);
                JOptionPane.showMessageDialog(parent, "Xuất dữ liệu ra Excel thành công!");
                return true;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parent, "Lỗi khi xuất Excel: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
