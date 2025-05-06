package GUI.DashBoardPanel;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import BLL.NhanVienBLL;
import GUI.ComponentCommon.TienIch;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PDFExporter {

    public static void exportChartToPDF(PanelChart panelChart, String timeFilter, int selectedMonth, int selectedYear, String outputPath) {
        try {
            // Khởi tạo PDF
            File file = new File(outputPath);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            // Font hỗ trợ tiếng Việt
            String fontPath = "src/main/resources/fonts/arial.ttf";
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontNormal = new Font(baseFont, 12);
            Font fontBold = new Font(baseFont, 12, Font.BOLD);
            Font fontTitle = new Font(baseFont, 16, Font.BOLD);

            // Thêm tiêu đề và ngày in
            LocalDate today = LocalDate.now();
            String formattedDate = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Paragraph title = new Paragraph("BÁO CÁO DOANH THU", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph date = new Paragraph("Ngày in: " + formattedDate, fontNormal);
            date.setAlignment(Element.ALIGN_RIGHT);
            document.add(date);

            // Chụp ảnh biểu đồ từ PanelChart
            vechart chart = (vechart) ((JScrollPane) panelChart.getComponent(0)).getViewport().getView();
            BufferedImage chartImage = new BufferedImage(chart.getWidth(), chart.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = chartImage.createGraphics();
            chart.paint(g2d);
            g2d.dispose();

            // Thêm ảnh biểu đồ vào PDF
            Image pdfImage = Image.getInstance(chartImage, null);
            pdfImage.scaleToFit(500, 300);
            pdfImage.setAlignment(Image.ALIGN_CENTER);
            document.add(pdfImage);

            // Thêm tiêu đề biểu đồ
            String chartTitle = "Thống Kê Doanh Thu Theo " + timeFilter;
            String[] monthNames = {"", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12"};
            if ("Ngày".equals(timeFilter)) {
                chartTitle += " (" + monthNames[selectedMonth] + " " + selectedYear + ")";
            } else if ("Tháng".equals(timeFilter)) {
                chartTitle += " (" + selectedYear + ")";
            } else {
                chartTitle += " (5 Năm Gần Nhất)";
            }
            Paragraph chartTitlePara = new Paragraph(chartTitle, fontBold);
            chartTitlePara.setAlignment(Element.ALIGN_CENTER);
            chartTitlePara.setSpacingBefore(10);
            document.add(chartTitlePara);

            // Thêm bảng thông tin từ PanelTomTat
            Paragraph summaryTitle = new Paragraph("Thông Tin Tổng Quan", fontBold);
            summaryTitle.setSpacingBefore(20);
            document.add(summaryTitle);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{50, 50});
            table.setSpacingBefore(10f);

            // Header bảng
            PdfPCell header1 = new PdfPCell(new Paragraph("Thông Tin", fontBold));
            header1.setHorizontalAlignment(Element.ALIGN_CENTER);
            header1.setBackgroundColor(new BaseColor(33, 58, 89));
            header1.setBorderColor(BaseColor.BLACK);

            PdfPCell header2 = new PdfPCell(new Paragraph("Giá Trị", fontBold));
            header2.setHorizontalAlignment(Element.ALIGN_CENTER);
            header2.setBackgroundColor(new BaseColor(33, 58, 89));
            header2.setBorderColor(BaseColor.BLACK);

            table.addCell(header1);
            table.addCell(header2);

            // Lấy dữ liệu từ PanelTomTat
            PanelTomTat panelTomTat = new PanelTomTat();
            panelTomTat.updateData(timeFilter, selectedMonth, selectedYear);

            // Dữ liệu bảng
            PdfPCell cellLuotMuaLabel = new PdfPCell(new Paragraph("Lượt Mua", fontNormal));
            cellLuotMuaLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellLuotMuaLabel);

            PdfPCell cellLuotMuaValue = new PdfPCell(new Paragraph(panelTomTat.luotmua.getText(), fontNormal));
            cellLuotMuaValue.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellLuotMuaValue);

            PdfPCell cellDoanhThuLabel = new PdfPCell(new Paragraph("Doanh Thu", fontNormal));
            cellDoanhThuLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellDoanhThuLabel);

            PdfPCell cellDoanhThuValue = new PdfPCell(new Paragraph(panelTomTat.doanhthu.getText(), fontNormal));
            cellDoanhThuValue.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellDoanhThuValue);

            PdfPCell cellChiPhiLabel = new PdfPCell(new Paragraph("Chi Phí", fontNormal));
            cellChiPhiLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellChiPhiLabel);

            PdfPCell cellChiPhiValue = new PdfPCell(new Paragraph(panelTomTat.chiphi.getText(), fontNormal));
            cellChiPhiValue.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellChiPhiValue);

            PdfPCell cellLoiNhuanLabel = new PdfPCell(new Paragraph("Lợi Nhuận", fontNormal));
            cellLoiNhuanLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellLoiNhuanLabel);

            PdfPCell cellLoiNhuanValue = new PdfPCell(new Paragraph(panelTomTat.loinhuan.getText(), fontNormal));
            cellLoiNhuanValue.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellLoiNhuanValue);

            // Phân tích xu hướng doanh thu
            double[] values = chart.values;
            String trend = analyzeTrend(values);

            PdfPCell cellTrendLabel = new PdfPCell(new Paragraph("Xu Hướng Doanh Thu", fontNormal));
            cellTrendLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellTrendLabel);

            PdfPCell cellTrendValue = new PdfPCell(new Paragraph(trend, fontNormal));
            cellTrendValue.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellTrendValue);

            document.add(table);

            // Thêm chữ ký
            Paragraph nguoilambaocao = new Paragraph("Người lập báo cáo\nKý và ghi rõ họ tên", fontBold);
            Paragraph banquanli = new Paragraph("Ban quản lý\nKý và ghi rõ họ tên", fontBold);

            PdfPTable signatureTable = new PdfPTable(2);
            signatureTable.setWidthPercentage(100);
            signatureTable.setWidths(new float[]{50, 50});
            signatureTable.setSpacingBefore(30);

            PdfPCell nguoilam = new PdfPCell(nguoilambaocao);
            nguoilam.setBorder(PdfPCell.NO_BORDER);
            nguoilam.setHorizontalAlignment(Element.ALIGN_CENTER);
            nguoilam.setVerticalAlignment(Element.ALIGN_TOP);

            PdfPCell nguoiduyet = new PdfPCell(banquanli);
            nguoiduyet.setBorder(PdfPCell.NO_BORDER);
            nguoiduyet.setHorizontalAlignment(Element.ALIGN_CENTER);
            nguoiduyet.setVerticalAlignment(Element.ALIGN_TOP);

            signatureTable.addCell(nguoilam);
            signatureTable.addCell(nguoiduyet);
            document.add(signatureTable);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xuất file PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void exportChartToPDFWithDialog(PanelChart panelChart, String timeFilter, int selectedMonth, int selectedYear, String MANV) {
        JFileChooser chooser = new JFileChooser("src/main/resources/file/export/");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setDialogTitle("Chọn nơi lưu báo cáo doanh thu");
        chooser.setSelectedFile(new File("BaoCaoDoanhThu_" + System.currentTimeMillis() + ".pdf"));

        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }

            try {
                // Tạo document mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Font hỗ trợ tiếng Việt
                String fontPath = "src/main/resources/fonts/arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontNormal = new Font(baseFont, 12);
                Font fontBold = new Font(baseFont, 12, Font.BOLD);
                Font fontTitle = new Font(baseFont, 20, Font.BOLD);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD, BaseColor.WHITE);

                // Ngày tháng năm và tên người in
                LocalDate today = LocalDate.now();
                String formattedDate = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String tenNhanVien = new NhanVienBLL().getNameNV(MANV);
                Paragraph dateAndUser = new Paragraph("Ngày in: " + formattedDate + " | Người in: " + tenNhanVien, fontNormal);
                dateAndUser.setAlignment(Element.ALIGN_RIGHT);
                document.add(dateAndUser);

                // Logo
                String imagePath = "src/main/resources/images/icon/Logo_Main.png";
                try {
                    Image logo = Image.getInstance(imagePath);
                    logo.scaleToFit(100, 100);
                    logo.setAlignment(Image.ALIGN_CENTER);
                    document.add(logo);
                } catch (Exception e) {
                    System.err.println("Không thể tải logo: " + e.getMessage());
                }

                // Tiêu đề
                String titleText = "BÁO CÁO DOANH THU";
                String[] monthNames = {"", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12"};
                if ("Ngày".equals(timeFilter)) {
                    titleText += " (" + monthNames[selectedMonth] + " " + selectedYear + ")";
                } else if ("Tháng".equals(timeFilter)) {
                    titleText += " (" + selectedYear + ")";
                } else {
                    titleText += " (5 Năm Gần Nhất)";
                }
                Paragraph title = new Paragraph(titleText + "\n\n", fontTitle);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                // Chụp ảnh biểu đồ từ PanelChart
                vechart chart = (vechart) ((JScrollPane) panelChart.getComponent(0)).getViewport().getView();
                BufferedImage chartImage = new BufferedImage(chart.getWidth(), chart.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = chartImage.createGraphics();
                chart.paint(g2d);
                g2d.dispose();

                // Thêm ảnh biểu đồ vào PDF
                Image pdfImage = Image.getInstance(chartImage, null);
                pdfImage.scaleToFit(500, 300);
                pdfImage.setAlignment(Image.ALIGN_CENTER);
                document.add(pdfImage);

                // Thêm bảng thông tin từ PanelTomTat
                Paragraph summaryTitle = new Paragraph("Thông Tin Tổng Quan", fontBold);
                summaryTitle.setSpacingBefore(20);
                document.add(summaryTitle);

                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{50, 50});
                table.setSpacingBefore(10f);

                // Header bảng
                PdfPCell header1 = new PdfPCell(new Paragraph("Thông Tin", fontHeader));
                header1.setHorizontalAlignment(Element.ALIGN_CENTER);
                header1.setBackgroundColor(new BaseColor(33, 58, 89));

                PdfPCell header2 = new PdfPCell(new Paragraph("Giá Trị", fontHeader));
                header2.setHorizontalAlignment(Element.ALIGN_CENTER);
                header2.setBackgroundColor(new BaseColor(33, 58, 89));

                table.addCell(header1);
                table.addCell(header2);

                // Lấy dữ liệu từ PanelTomTat
                PanelTomTat panelTomTat = new PanelTomTat();
                panelTomTat.updateData(timeFilter, selectedMonth, selectedYear);

                // Dữ liệu bảng
                PdfPCell cellLuotMuaLabel = new PdfPCell(new Paragraph("Lượt Mua", fontNormal));
                cellLuotMuaLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cellLuotMuaLabel);

                PdfPCell cellLuotMuaValue = new PdfPCell(new Paragraph(panelTomTat.luotmua.getText(), fontNormal));
                cellLuotMuaValue.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cellLuotMuaValue);

                PdfPCell cellDoanhThuLabel = new PdfPCell(new Paragraph("Doanh Thu", fontNormal));
                cellDoanhThuLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cellDoanhThuLabel);

                PdfPCell cellDoanhThuValue = new PdfPCell(new Paragraph(panelTomTat.doanhthu.getText(), fontNormal));
                cellDoanhThuValue.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cellDoanhThuValue);

                PdfPCell cellChiPhiLabel = new PdfPCell(new Paragraph("Chi Phí", fontNormal));
                cellChiPhiLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cellChiPhiLabel);

                PdfPCell cellChiPhiValue = new PdfPCell(new Paragraph(panelTomTat.chiphi.getText(), fontNormal));
                cellChiPhiValue.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cellChiPhiValue);

                PdfPCell cellLoiNhuanLabel = new PdfPCell(new Paragraph("Lợi Nhuận", fontNormal));
                cellLoiNhuanLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cellLoiNhuanLabel);

                PdfPCell cellLoiNhuanValue = new PdfPCell(new Paragraph(panelTomTat.loinhuan.getText(), fontNormal));
                cellLoiNhuanValue.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cellLoiNhuanValue);

                // Phân tích xu hướng doanh thu
                double[] values = chart.values;
                String trend = analyzeTrend(values);

                PdfPCell cellTrendLabel = new PdfPCell(new Paragraph("Xu Hướng Doanh Thu", fontNormal));
                cellTrendLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cellTrendLabel);

                PdfPCell cellTrendValue = new PdfPCell(new Paragraph(trend, fontNormal));
                cellTrendValue.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cellTrendValue);

                document.add(table);

                // Thêm chữ ký
                Paragraph nguoilambaocao = new Paragraph("Người lập báo cáo\nKý và ghi rõ họ tên", fontBold);
                Paragraph banquanli = new Paragraph("Ban quản lý\nKý và ghi rõ họ tên", fontBold);

                PdfPTable signatureTable = new PdfPTable(2);
                signatureTable.setWidthPercentage(100);
                signatureTable.setWidths(new float[]{50, 50});
                signatureTable.setSpacingBefore(30);

                PdfPCell nguoilam = new PdfPCell(nguoilambaocao);
                nguoilam.setBorder(PdfPCell.NO_BORDER);
                nguoilam.setHorizontalAlignment(Element.ALIGN_CENTER);
                nguoilam.setVerticalAlignment(Element.ALIGN_TOP);

                PdfPCell nguoiduyet = new PdfPCell(banquanli);
                nguoiduyet.setBorder(PdfPCell.NO_BORDER);
                nguoiduyet.setHorizontalAlignment(Element.ALIGN_CENTER);
                nguoiduyet.setVerticalAlignment(Element.ALIGN_TOP);

                signatureTable.addCell(nguoilam);
                signatureTable.addCell(nguoiduyet);
                document.add(signatureTable);

                document.close();

                TienIch.CustomMessageNormal("Xuất báo cáo doanh thu thành công");

            } catch (java.io.FileNotFoundException e) {
                TienIch.CustomMessageNormal("Không thể xuất file vì file đang được mở");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xuất file PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static String analyzeTrend(double[] values) {
        // Hồi quy tuyến tính: y = ax + b
        int n = values.length;
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for (int i = 0; i < n; i++) {
            sumX += i;
            sumY += values[i];
            sumXY += i * values[i];
            sumX2 += i * i;
        }

        double a = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX); // Độ dốc
        double threshold = 10000; // Ngưỡng để xác định xu hướng

        if (a > threshold) {
            return "Tăng";
        } else if (a < -threshold) {
            return "Giảm";
        } else {
            return "Bình thường";
        }
    }
}