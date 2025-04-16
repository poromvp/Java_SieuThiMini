package GUI.NhanVien_BaoCaoBanHang;
/*package com.sieuthi.NhanVien_BaoCaoBanHang;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;

import javax.swing.JTable;

public class ExportPDF {

    public static void exportToPDF(JTable table, String filePath) {
        try {
            // Tạo tài liệu PDF
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Đường dẫn đến font hỗ trợ tiếng Việt (thay đường dẫn phù hợp với máy bạn)
            String fontPath = "C:\\Windows\\Fonts\\Arial.ttf";  // Windows
            // String fontPath = "/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf";  // Ubuntu

            // Tạo font hỗ trợ tiếng Việt
            // Tạo font hỗ trợ tiếng Việt
            PdfFont vietnameseFont = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);


            // Thêm tiêu đề
            document.add(new Paragraph("Báo cáo doanh thu")
                    .setFont(vietnameseFont)
                    .setBold()
                    .setFontSize(18));

            // Tạo bảng PDF với số cột tương ứng với JTable
            float[] columnWidths = new float[table.getColumnCount()];
            for (int i = 0; i < table.getColumnCount(); i++) {
                columnWidths[i] = 100f;
            }
            Table pdfTable = new Table(columnWidths);

            // Thêm tiêu đề cột
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(new Cell().add(new Paragraph(table.getColumnName(i))
                        .setFont(vietnameseFont)
                        .setBold()));
            }

            // Thêm dữ liệu
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    pdfTable.addCell(new Cell().add(new Paragraph(table.getValueAt(i, j).toString())
                            .setFont(vietnameseFont)));
                }
            }

            // Thêm bảng vào tài liệu
            document.add(pdfTable);
            document.close();
            System.out.println("PDF xuất thành công: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
