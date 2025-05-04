package EXCEL;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BLL.DonHangBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.color.ProfileDataException;
import java.io.File;
import java.io.IOException;

import BLL.NhanVienBLL;
import DTO.DonHangDTO;
import DTO.NhanVienDTO;
import GUI.FormEmployee.BaoCaoPanel;
import GUI.FormEmployee.ProfilePanel;



public class ExportExcelReport extends JFrame {
    

       private String[] HEADER = {"Mã DH", "Mã KM", "Mã KH", "PT thanh toán", "Ngày mua", "thành tiền"};
	private DefaultTableModel tableModel_SP = new DefaultTableModel(HEADER, 0);
	private JTable tableProduct = new JTable(tableModel_SP);
    // private JScrollPane scrollPane_SP = new JScrollPane(tableProduct);
  	JTableHeader tableHeader = tableProduct.getTableHeader();
	private ArrayList<DonHangDTO> DSHoaDon = DonHangBLL.getAllOrders();
	JTextArea txtrNhapNoiDung = new JTextArea();

	private NhanVienDTO  NHANVIEN = new NhanVienDTO(
            1,
            "Nguyễn Văn A",
            null,
            "Nam",
            "123 Đường ABC, Quận 1",
            "0123456789",
            "123456789012",
            8000000.0,
            1
        ); 

    public void exportExcel() {
        NhanVienBLL nvBLL = new NhanVienBLL();
        NHANVIEN = nvBLL.getNhanVienByMa(ProfilePanel.getMaNhanVien() +"");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn nơi lưu file Excel");
        chooser.setSelectedFile(new File("BaoCao.xlsx"));

        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            if (!selectedFile.getName().toLowerCase().endsWith(".xlsx")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".xlsx");
            }

            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                XSSFSheet sheet = workbook.createSheet("Bao cao ban hang");

                // Tạo font và style cho tiêu đề
                XSSFFont titleFont = workbook.createFont();
                titleFont.setFontHeightInPoints((short) 16);
                titleFont.setBold(true);

                CellStyle titleStyle = workbook.createCellStyle();
                titleStyle.setFont(titleFont);
                titleStyle.setAlignment(HorizontalAlignment.CENTER);

                // Dòng tiêu đề
                Row titleRow = sheet.createRow(0);
                Cell titleCell = titleRow.createCell(0);
                titleCell.setCellValue("BÁO CÁO BÁN HÀNG");
                titleCell.setCellStyle(titleStyle);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5)); // merge từ cột 0 tới 5

                // Dòng thông tin chung
                LocalDate today = LocalDate.now();
                Row row2 = sheet.createRow(2);
                row2.createCell(0).setCellValue("Ngày báo cáo: " );
                row2.createCell(1).setCellValue( today.toString());

                Row row3 = sheet.createRow(3);
                row3.createCell(0).setCellValue("Tên nhân viên: " );
                row3.createCell(1).setCellValue( NHANVIEN.getTenNV());
                row3.createCell(2).setCellValue("Mã nhân viên: " );
                row3.createCell(3).setCellValue(NHANVIEN.getMaNV());

                Row row4 = sheet.createRow(4);
                row4.createCell(0).setCellValue("Thống kê từ ngày: " );
                row4.createCell(1).setCellValue((BaoCaoPanel.getPanelTimkiem().getNgayBatDau() != null? BaoCaoPanel.getPanelTimkiem().getNgayBatDau().toString() : "Ngày thành lập"));
                row4.createCell(2).setCellValue("Thống kê đến ngày: ");
                row4.createCell(3).setCellValue( (BaoCaoPanel.getPanelTimkiem().getNgayKetThuc() != null ? BaoCaoPanel.getPanelTimkiem().getNgayKetThuc().toString() : today.toString()));

                Row row5 = sheet.createRow(5);
                row5.createCell(0).setCellValue("Tổng số đơn hàng: " );
                row5.createCell(1).setCellValue( BaoCaoPanel.getSoDonhang());
                row5.createCell(2).setCellValue("Tổng doanh thu: ");
                row5.createCell(3).setCellValue(BaoCaoPanel.getDanhThu());

                sheet.createRow(6).createCell(0).setCellValue("Nội dung báo cáo: " );
                sheet.createRow(7).createCell(0).setCellValue( txtrNhapNoiDung.getText());


                // Dòng header bảng
                Row headerRow = sheet.createRow(8);
                String[] headers = {"Mã DH", "Mã KM", "Mã KH", "PT thanh toán", "Ngày mua", "Thành tiền"};
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                    CellStyle headerStyle = workbook.createCellStyle();
                    XSSFFont headerFont = workbook.createFont();
                    headerFont.setBold(true);
                    headerStyle.setFont(headerFont);
                    headerStyle.setAlignment(HorizontalAlignment.CENTER);
                    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
                    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    cell.setCellStyle(headerStyle);
                }
                int rowIdx = 9;

                // Dữ liệu đơn hàng
                for (DonHangDTO dh : BaoCaoPanel.getDanhSachDonHang()) {
                    Row row = sheet.createRow(rowIdx++);
                    row.createCell(0).setCellValue(dh.getMaDH());
                    row.createCell(1).setCellValue(dh.getMaKM() == null ? "null" : dh.getMaKM() + "" ) ;
                    row.createCell(2).setCellValue(dh.getMaKH() == null ? "null" : dh.getMaKH() + "" );
                    row.createCell(3).setCellValue(dh.getPtThanhToan());
                    row.createCell(4).setCellValue(String.valueOf(dh.getNgayTT()));
                    row.createCell(5).setCellValue(dh.getTongTien());
                }

                // Auto size column
                for (int i = 0; i < headers.length; i++) {
                    sheet.autoSizeColumn(i);
                }

                // Ghi file ra ổ đĩa
                try (FileOutputStream fos = new FileOutputStream(selectedFile)) {
                    workbook.write(fos);
                }

                JOptionPane.showMessageDialog(null, "Xuất báo cáo Excel thành công!", "THÀNH CÔNG", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi xuất file Excel: " + e.getMessage(), "LỖI", JOptionPane.ERROR_MESSAGE);
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
            ExportExcelReport frame = new ExportExcelReport();
            frame.exportExcel();
            // frame.setVisible(true);
        });
    }

}
