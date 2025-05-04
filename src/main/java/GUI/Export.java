package GUI;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Export {

    public static void exportToPDF(JTable table, String titleTable) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF files", "pdf"));
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) filePath += ".pdf";

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontTitle = new Font(baseFont, 16, Font.BOLD);
                Font fontDate = new Font(baseFont, 12, Font.ITALIC);
                Font font = new Font(baseFont, 12);

                Paragraph title = new Paragraph(titleTable, fontTitle);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(10);
                document.add(title);

                String dateStr = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                Paragraph date = new Paragraph("Ngày xuất: " + dateStr, fontDate);
                date.setAlignment(Element.ALIGN_RIGHT);
                date.setSpacingAfter(10);
                document.add(date);

                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
                pdfTable.setWidthPercentage(100);

                for (int i = 0; i < table.getColumnCount(); i++) {
                    PdfPCell cell = new PdfPCell(new Phrase(table.getColumnName(i), font));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    pdfTable.addCell(cell);
                }

                for (int r = 0; r < table.getRowCount(); r++) {
                    for (int c = 0; c < table.getColumnCount(); c++) {
                        Object val = table.getValueAt(r, c);
                        pdfTable.addCell(new PdfPCell(new Phrase(val != null ? val.toString() : "", font)));
                    }
                }

                document.add(pdfTable);
                document.close();
                JOptionPane.showMessageDialog(null, "Xuất PDF thành công!");

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi xuất PDF: " + e.getMessage());
            }
        }
    }

    public static void exportToExcel(JTable table, String titleTable) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel files", "xlsx"));
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) filePath += ".xlsx";

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Dữ liệu");

                Row titleRow = sheet.createRow(0);
                Cell titleCell = titleRow.createCell(0);
                titleCell.setCellValue(titleTable);
                sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, table.getColumnCount() - 1));

                String dateStr = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                Row dateRow = sheet.createRow(1);
                Cell dateCell = dateRow.createCell(table.getColumnCount() - 1);
                dateCell.setCellValue("Ngày xuất: " + dateStr);

                Row headerRow = sheet.createRow(3);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    headerRow.createCell(i).setCellValue(table.getColumnName(i));
                }

                for (int r = 0; r < table.getRowCount(); r++) {
                    Row dataRow = sheet.createRow(r + 4);
                    for (int c = 0; c < table.getColumnCount(); c++) {
                        Object value = table.getValueAt(r, c);
                        dataRow.createCell(c).setCellValue(value != null ? value.toString() : "");
                    }
                }

                for (int i = 0; i < table.getColumnCount(); i++) {
                    sheet.autoSizeColumn(i);
                }

                try (FileOutputStream out = new FileOutputStream(filePath)) {
                    workbook.write(out);
                }

                JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi xuất Excel: " + e.getMessage());
            }
        }
    }
}
