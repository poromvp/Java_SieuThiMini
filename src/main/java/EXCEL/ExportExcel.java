package EXCEL;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BLL.DonHangBLL;

import javax.swing.*;
import javax.swing.table.TableModel;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import DTO.DonHangDTO;
import DTO.NhanVienDTO;

public class ExportExcel  {
    // private JTable table;

    public ExportExcel() {
    //     setTitle("Demo Export Excel");
    //     setSize(600, 400);
    //     setDefaultCloseOperation(EXIT_ON_CLOSE);
    //     setLocationRelativeTo(null);

    //     // Dữ liệu mẫu
    //     String[] columns = {"Mã", "Tên", "Tổng Tiền"};
    //     Object[][] data = {
    //             {1, "Nguyễn Văn A", 120000},
    //             {2, "Trần Thị B", 540000},
    //             {3, "Lê Văn C", 300000}
    //     };

    //     table = new JTable(data, columns);

    //     JButton btnExport = new JButton("Xuất Excel");
    //     btnExport.addActionListener(e -> exportTableToExcel(table, "donhang.xlsx"));

    //     add(new JScrollPane(table), BorderLayout.CENTER);
    //     add(btnExport, BorderLayout.SOUTH);
    // }

    // private void exportTableToExcel(JTable table, String filePath) {
    //     File file = new File(filePath);
    //     if (file.exists() && !file.renameTo(file)) {
    //         JOptionPane.showMessageDialog(this, "File đang được sử dụng! Hãy đóng nó trước khi ghi đè.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    //         return;
    //     }
    //     try (Workbook workbook = new XSSFWorkbook()) {
    //         Sheet sheet = workbook.createSheet("Đơn hàng");

    //         TableModel model = table.getModel();

    //         // Ghi header
    //         Row headerRow = sheet.createRow(0);
    //         for (int col = 0; col < model.getColumnCount(); col++) {
    //             Cell cell = headerRow.createCell(col);
    //             cell.setCellValue(model.getColumnName(col));
    //         }

    //         // Ghi dữ liệu
    //         for (int row = 0; row < model.getRowCount(); row++) {
    //             Row excelRow = sheet.createRow(row + 1);
    //             for (int col = 0; col < model.getColumnCount(); col++) {
    //                 Cell cell = excelRow.createCell(col);
    //                 Object value = model.getValueAt(row, col);
    //                 if (value != null) {
    //                     cell.setCellValue(value.toString());
    //                 }
    //             }
    //         }

    //         try (FileOutputStream out = new FileOutputStream(filePath)) {
    //             workbook.write(out);
    //             JOptionPane.showMessageDialog(this, "Xuất Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    //         }

    //     } catch (IOException ex) {
    //         ex.printStackTrace();
    //         JOptionPane.showMessageDialog(this, "Lỗi khi ghi file Excel", "Lỗi", JOptionPane.ERROR_MESSAGE);
    //     }
    }

    // public static boolean exportExcelOrder(int orderId, String fileName, String filePath){
    //     File file = new File(filePath);
    //     if (file.exists() && !file.renameTo(file)) {
    //         JOptionPane.showMessageDialog(null, "File đang được sử dụng! Hãy đóng nó trước khi ghi đè.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }
    //     DonHangDTO order = DonHangBLL.getOrderById(orderId);
    //     if(order == null){
    //         System.out.println("Mã đơn hàng không hợp lệ");
    //         return false;
    //     }
    //     JTable table = new JTable();
    
    //     try (Workbook workbook = new XSSFWorkbook()) {
    //     Sheet sheet = workbook.createSheet("Đơn hàng");

    //     TableModel model = table.getModel();

    //     // Ghi header

    //     Row rowInfor = sheet.createRow(0);

    //     for (int col = 0; col < model.getColumnCount(); col++) {
    //         // Cell cell = headerRow.createCell(col);
    //         cell.setCellValue(model.getColumnName(col));
    //     }

    //     // Ghi dữ liệu
    //     for (int row = 0; row < model.getRowCount(); row++) {
    //         Row excelRow = sheet.createRow(row + 1);
    //         for (int col = 0; col < model.getColumnCount(); col++) {
    //             Cell cell = excelRow.createCell(col);
    //             Object value = model.getValueAt(row, col);
    //             if (value != null) {
    //                 cell.setCellValue(value.toString());
    //             }
    //         }
    //     }

    //     try (FileOutputStream out = new FileOutputStream(filePath)) {
    //         workbook.write(out);
    //         // JOptionPane.showMessageDialog(this, "Xuất Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    //     }

    // } catch (IOException ex) {
    //     ex.printStackTrace();
    //     // JOptionPane.showMessageDialog(this, "Lỗi khi ghi file Excel", "Lỗi", JOptionPane.ERROR_MESSAGE);
    // }
    // return true;
    // }

    public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> {
        //     ExportExcelDemo frame = new ExportExcelDemo();
        //     frame.setVisible(true);
        // });
    }
}
