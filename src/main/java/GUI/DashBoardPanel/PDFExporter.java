package GUI.DashBoardPanel;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;


import javax.swing.JScrollPane;
import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PDFExporter {

    public static void exportToPDF(PanelChart panelChart, String timeFilter, int selectedMonth, int selectedYear, String outputPath) {
        try {
            // Khởi tạo PDF
            File file = new File(outputPath);
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Thêm tiêu đề và ngày in
            LocalDate today = LocalDate.now();
            String formattedDate = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            document.add(new Paragraph("BÁO CÁO DOANH THU")
                    .setFontSize(16)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Ngày in: " + formattedDate)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.RIGHT));

            // Lấy dữ liệu từ PanelChart
            vechart chart = (vechart) ((JScrollPane) panelChart.getComponent(0)).getViewport().getView();
            double[] values = chart.values;
            String[] labels = chart.labels;

            // Thêm tiêu đề biểu đồ
            String title = "Thống Kê Doanh Thu Theo " + timeFilter;
            String[] monthNames = {"", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12"};
            if ("Ngày".equals(timeFilter)) {
                title += " (" + monthNames[selectedMonth] + " " + selectedYear + ")";
            } else if ("Tháng".equals(timeFilter)) {
                title += " (" + selectedYear + ")";
            } else {
                title += " (5 Năm Gần Nhất)";
            }
            document.add(new Paragraph(title)
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(10));

            // Vẽ biểu đồ cột
            float pageWidth = pdf.getDefaultPageSize().getWidth() - 40;
            float chartHeight = 300;
            PdfCanvas canvas = new PdfCanvas(pdf.getFirstPage());
            canvas.setFillColor(ColorConstants.LIGHT_GRAY);
            canvas.rectangle(40, pdf.getDefaultPageSize().getHeight() - 80 - chartHeight, pageWidth, chartHeight);
            canvas.fill();

            // Tính toán thông số biểu đồ
            float maxValue = 0;
            for (double value : values) {
                if (value > maxValue) maxValue = (float) value;
            }
            float barWidth = (pageWidth - (labels.length - 1) * 10) / labels.length;
            DecimalFormat df = new DecimalFormat("#,###.##");

            // Vẽ các cột và nhãn
            for (int i = 0; i < values.length; i++) {
                float barHeight = (float) ((values[i] / maxValue) * chartHeight);
                float x = 40 + i * (barWidth + 10);
                float y = pdf.getDefaultPageSize().getHeight() - 80 - barHeight;

                // Sử dụng DeviceRgb thay vì Color
                canvas.setFillColor(new DeviceRgb(100, 100 + i * 30 % 155, 200 - i * 30 % 155));
                canvas.rectangle(x, y, barWidth, barHeight);
                canvas.fill();

                // Nhãn trục X
                document.add(new Paragraph(labels[i])
                        .setFontSize(10)
                        .setFixedPosition(x + barWidth / 4, pdf.getDefaultPageSize().getHeight() - 90, 50));

                // Giá trị trên cột
                document.add(new Paragraph(df.format(values[i] / 1000000.0) + "M")
                        .setFontSize(10)
                        .setFixedPosition(x + barWidth / 4, y - 15, 50));
            }

            // Vẽ trục
            canvas.setStrokeColor(ColorConstants.BLACK);
            canvas.moveTo(40, pdf.getDefaultPageSize().getHeight() - 80);
            canvas.lineTo(pageWidth + 40, pdf.getDefaultPageSize().getHeight() - 80); // Trục X
            canvas.moveTo(40, pdf.getDefaultPageSize().getHeight() - 80);
            canvas.lineTo(40, pdf.getDefaultPageSize().getHeight() - 80 - chartHeight); // Trục Y
            canvas.stroke();

            // Nhãn trục Y
            document.add(new Paragraph("Triệu VND")
                    .setFontSize(10)
                    .setFixedPosition(10, pdf.getDefaultPageSize().getHeight() - 80 - chartHeight / 2, 50));
            float yStep = maxValue / 5;
            for (int i = 0; i <= 5; i++) {
                float y = pdf.getDefaultPageSize().getHeight() - 80 - i * chartHeight / 5;
                document.add(new Paragraph(df.format(i * yStep / 1000000.0) + "M")
                        .setFontSize(10)
                        .setFixedPosition(10, y - 5, 50));
            }

            // Phân tích hồi quy tuyến tính
            String trend = analyzeTrend(values);

            // Thêm bảng thông tin
            document.add(new Paragraph("Thông Tin Tổng Quan")
                    .setFontSize(14)
                    .setBold()
                    .setMarginTop(20));

            Table table = new Table(UnitValue.createPercentArray(new float[]{50, 50}));
            table.addHeaderCell("Thông Tin").addHeaderCell("Giá Trị");

            // Lấy dữ liệu từ PanelTomTat
            PanelTomTat panelTomTat = new PanelTomTat();
            panelTomTat.updateData(timeFilter, selectedMonth, selectedYear);

            table.addCell("Lượt Mua").addCell(panelTomTat.luotmua.getText());
            table.addCell("Doanh Thu").addCell(panelTomTat.doanhthu.getText());
            table.addCell("Chi Phí").addCell(panelTomTat.chiphi.getText());
            table.addCell("Lợi Nhuận").addCell(panelTomTat.loinhuan.getText());
            table.addCell("Xu Hướng Doanh Thu").addCell(trend);

            document.add(table);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
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
        double threshold = 10000; // Ngưỡng để xác định xu hướng (có thể điều chỉnh)

        if (a > threshold) {
            return "Tăng";
        } else if (a < -threshold) {
            return "Giảm";
        } else {
            return "Bình thường";
        }
    }
}