package GUI;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Export {

   public static void exportToPDF(JTable table, String titleTable) {
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
        e.printStackTrace();
    }

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
    fileChooser.setSelectedFile(new File(titleTable.replaceAll("\\s+", "") + ".pdf"));
    fileChooser.setFileFilter(new FileNameExtensionFilter("PDF files", "pdf"));
    fileChooser.setAcceptAllFileFilterUsed(false);

    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.toLowerCase().endsWith(".pdf")) {
            filePath += ".pdf";
        }

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Font tiếng Việt
            String fontPath = "src/main/resources/fonts/arial.ttf";
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontNormal = new Font(baseFont, 12);
            Font fontBold = new Font(baseFont, 12, Font.BOLD);
            Font fontTitle = new Font(baseFont, 22, Font.BOLD);
            Font fontSubtitle = new Font(baseFont, 14, Font.ITALIC);
            Font fontHeader = new Font(baseFont, 12, Font.BOLD, BaseColor.WHITE);

            // Logo
            String imagePath = "src/main/resources/images/icon/Logo_Main.png";
            Image logo = Image.getInstance(imagePath);
            logo.scaleToFit(100, 100);
            logo.setAlignment(Image.ALIGN_CENTER);
            document.add(logo);

            // Tiêu đề chính
            Paragraph title = new Paragraph("SIÊU THỊ MINI SGU\n", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Phụ đề
            Paragraph subtitle = new Paragraph("Chất lượng trong từng lựa chọn!\n\n", fontSubtitle);
            subtitle.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitle);

            // Tiêu đề bảng
            Paragraph tableTitle = new Paragraph(titleTable + "\n\n", fontBold);
            tableTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(tableTitle);

            // Ngày xuất
            String dateStr = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            Paragraph date = new Paragraph("Ngày xuất: " + dateStr + "\n\n", fontNormal);
            date.setAlignment(Element.ALIGN_RIGHT);
            document.add(date);

            // Tạo bảng PDF từ JTable
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
            pdfTable.setWidthPercentage(100);
            pdfTable.setSpacingBefore(10f);

            // Header
            for (int i = 0; i < table.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(table.getColumnName(i), fontHeader));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(33, 58, 89));
                pdfTable.addCell(cell);
            }

            // Dữ liệu
            for (int row = 0; row < table.getRowCount(); row++) {
                for (int col = 0; col < table.getColumnCount(); col++) {
                    Object val = table.getValueAt(row, col);
                    PdfPCell cell = new PdfPCell(new Phrase(val != null ? val.toString() : "", fontNormal));
                    pdfTable.addCell(cell);
                }
            }

            document.add(pdfTable);
            document.close();
            JOptionPane.showMessageDialog(null, "Xuất PDF thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (Exception e) {
        e.printStackTrace();
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
