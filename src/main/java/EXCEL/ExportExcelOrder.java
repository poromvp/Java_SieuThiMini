package EXCEL;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BLL.ChiTietDonHangBLL;
import BLL.ChiTietKhuyenMaiBLL;
import BLL.DiemTichLuyBLL;
import BLL.DonHangBLL;
import BLL.KhuyenMaiBLL;
import BLL.NhanVienBLL;
import BLL.SanPhamBLL;
import BLL.TheThanhVienBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import DTO.ChiTietDonHangDTO;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.DiemTichLuyDTO;
import DTO.DonHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import DTO.TheThanhVienDTO;

public class ExportExcelOrder extends JFrame  {
    
	private static final long serialVersionUID = 1L;
	private DonHangDTO DONHANG = new DonHangDTO(2, null, 1, 1, "BANK", "2005-04-27", 1,1, 1, "FINISHED");
    private String[] HEADER = {"Mã SP", "Tên SP", "Giá", "Giảm giá", "Số lượng", "thành tiền"};
	private DefaultTableModel tableModel_SP = new DefaultTableModel(HEADER, 0);
	private JTable tableProduct = new JTable(tableModel_SP);
    // private JScrollPane scrollPane_SP = new JScrollPane(tableProduct);
  	JTableHeader tableHeader = tableProduct.getTableHeader();


    


    private void exportExcel(int maDH) {
        DONHANG = DonHangBLL.getOrderById(maDH);
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
        e.printStackTrace();
    }

    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Chọn nơi lưu báo cáo và đặt tên file");
    chooser.setSelectedFile(new File("DonHang.xlsx"));
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    int result = chooser.showSaveDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = chooser.getSelectedFile();

        if (!selectedFile.getName().toLowerCase().endsWith(".xlsx")) {
            selectedFile = new File(selectedFile.getAbsolutePath() + ".xlsx");
        }

        System.out.println("File sẽ lưu: " + selectedFile.getAbsolutePath());

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("ChiTietDonHang");

            // Tạo kiểu font cho tiêu đề
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            // Tiêu đề
            int rowIndex = 0;
            Row titleRow = sheet.createRow(rowIndex++);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("SIÊU THỊ MINI SGU - CHI TIẾT ĐƠN HÀNG");
            titleCell.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

            rowIndex++;

            // Thông tin đơn hàng
            Row infoRow1 = sheet.createRow(rowIndex++);
            infoRow1.createCell(0).setCellValue("Mã đơn hàng:");
            infoRow1.createCell(1).setCellValue(DONHANG.getMaDH());
            infoRow1.createCell(2).setCellValue("Ngày thanh toán:");
            infoRow1.createCell(3).setCellValue(DONHANG.getNgayTT().toString());

            NhanVienDTO nv = new NhanVienBLL().getNhanVienByMa(String.valueOf(DONHANG.getMaNV()));
            Row infoRow2 = sheet.createRow(rowIndex++);
            infoRow2.createCell(0).setCellValue("Tên nhân viên:");
            infoRow2.createCell(1).setCellValue(nv.getTenNV());
            infoRow2.createCell(2).setCellValue("Mã nhân viên:");
            infoRow2.createCell(3).setCellValue(DONHANG.getMaNV());

            TheThanhVienDTO tv = TheThanhVienBLL.getMemberById(DONHANG.getMaKH());
            Row infoRow3 = sheet.createRow(rowIndex++);
            infoRow3.createCell(0).setCellValue("Tên khách hàng:");
            infoRow3.createCell(1).setCellValue(tv == null ? "null" : tv.getTenTV());
            infoRow3.createCell(2).setCellValue("Mã khách hàng:");
            infoRow3.createCell(3).setCellValue(tv == null ? "null" :tv.getMaTV() + "");

            KhuyenMaiDTO km = KhuyenMaiBLL.getDiscountById(DONHANG.getMaKM());
            Row infoRow4 = sheet.createRow(rowIndex++);
            infoRow4.createCell(0).setCellValue("Tên khuyến mãi:");
            infoRow4.createCell(1).setCellValue(km == null ? "null" : km.getTenKM());
            infoRow4.createCell(2).setCellValue("Mã khuyến mãi:");
            infoRow4.createCell(3).setCellValue(km == null ? "null" :String.valueOf( km.getMaKM()));

            DiemTichLuyDTO dtl = DiemTichLuyBLL.getDiemTichLuyById(DONHANG.getMaDTL());
            Row infoRow5 = sheet.createRow(rowIndex++);
            infoRow5.createCell(0).setCellValue("Điểm tích lũy:");
            infoRow5.createCell(1).setCellValue(dtl == null ? "null" : String.valueOf(dtl.getDiemTL()));
            infoRow5.createCell(2).setCellValue("Tỉ lệ giảm (ĐTL):");
            infoRow5.createCell(3).setCellValue(dtl == null ? "null" : dtl.getTiLeGiam() + " %");

            Row infoRow6 = sheet.createRow(rowIndex++);
            infoRow6.createCell(0).setCellValue("Phương thức thanh toán:");
            infoRow6.createCell(1).setCellValue(DONHANG.getPtThanhToan());
            infoRow6.createCell(2).setCellValue("Tiền khách đưa/chuyển:");
            infoRow6.createCell(3).setCellValue(DONHANG.getTienKD() == -1 ? 0 : DONHANG.getTienKD());

            Row infoRow7 = sheet.createRow(rowIndex++);
            infoRow7.createCell(0).setCellValue("Tổng tiền:");
            infoRow7.createCell(1).setCellValue(DONHANG.getTongTien());
            infoRow7.createCell(2).setCellValue("Trạng thái:");
            infoRow7.createCell(3).setCellValue("Đã thanh toán");

            rowIndex++;

            // Header bảng chi tiết sản phẩm
            String[] headers = {"Mã SP", "Tên SP", "Giá", "Giảm giá", "Số lượng", "Thành tiền"};
            Row headerRow = sheet.createRow(rowIndex++);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Dữ liệu chi tiết sản phẩm
            java.util.ArrayList<ChiTietDonHangDTO> dsCTDH = ChiTietDonHangBLL.getChiTietByMaDH(DONHANG.getMaDH());
            for (ChiTietDonHangDTO ctdh : dsCTDH) {
                Row row = sheet.createRow(rowIndex++);

                SanPhamDTO sp = SanPhamBLL.getProductById(ctdh.getMaSP());
                ChiTietKhuyenMaiDTO ctkm = ChiTietKhuyenMaiBLL.getDiscountDetail(ctdh.getMaDH(), ctdh.getMaSP());

                double tiLe = (ctkm != null) ? ctkm.getTiLeGiam() : 0.0;
                double thanhTien = sp.getGia() * (1 - tiLe / 100.0) * ctdh.getSoLuong();

                row.createCell(0).setCellValue(ctdh.getMaSP());
                row.createCell(1).setCellValue(sp.getTenSP());
                row.createCell(2).setCellValue(sp.getGia());
                row.createCell(3).setCellValue(tiLe + " %");
                row.createCell(4).setCellValue(ctdh.getSoLuong());
                row.createCell(5).setCellValue(thanhTien);
            }

            // Tự động điều chỉnh cột
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi file
            FileOutputStream out = new FileOutputStream(selectedFile);
            workbook.write(out);
            out.close();

            JOptionPane.showMessageDialog(null, "XUẤT EXCEL thành công !!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Không thể ghi file Excel. Vui lòng thử lại!", "Lỗi File", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        SwingUtilities.updateComponentTreeUI(this);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ExportExcelOrder frame = new ExportExcelOrder();
            frame.exportExcel(5);
            // frame.setVisible(true);
        });
    }
}
