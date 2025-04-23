package GUI;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class ExportToPDF {
    public static void exportJTableToPDF(JTable table) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files", "pdf");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int userChoice = fileChooser.showSaveDialog(null);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }

            try {
                // Tạo đối tượng Document
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Tạo đối tượng BaseFont từ font hỗ trợ Unicode (ví dụ: Arial Unicode MS)
                BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(baseFont, 12);

                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

                // Thêm tiêu đề cột vào bảng PDF
                for (int i = 0; i < table.getColumnCount(); i++) {
                    pdfTable.addCell(new PdfPCell(new Phrase(table.getColumnName(i), font)));
                }

                // Thêm dữ liệu vào bảng PDF
                for (int rows = 0; rows < table.getRowCount(); rows++) {
                    for (int cols = 0; cols < table.getColumnCount(); cols++) {
                        Object value = table.getValueAt(rows, cols);
                        pdfTable.addCell(new PdfPCell(new Phrase(value != null ? value.toString() : "", font)));
                    }
                }

                document.add(pdfTable);
                document.close();

                JOptionPane.showMessageDialog(null, "Xuất PDF thành công!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi xuất PDF: " + ex.getMessage());
            }
        }
    }
}
