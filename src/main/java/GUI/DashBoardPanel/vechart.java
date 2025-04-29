package GUI.DashBoardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.text.DecimalFormat;

public class vechart extends JPanel implements ActionListener {
    protected String[] labels;
    protected double[] values;
    protected double[] animatedValues;
    protected Timer timer;
    protected String timeFilter;
    protected int selectedMonth;
    protected int selectedYear;

    public vechart(double[] values, String[] labels, String timeFilter) {
        this.labels = labels;
        this.values = values;
        this.timeFilter = timeFilter;
        LocalDate ngayhientai = LocalDate.now();
        this.selectedMonth = ngayhientai.getMonthValue();
        this.selectedYear = ngayhientai.getYear();
        this.animatedValues = new double[values.length];
        timer = new Timer(20, this);
        startAnimation();
        setPreferredSize(new Dimension(calculatePreferredWidth(), 400)); // Đặt kích thước ưu tiên
    }

    public vechart(int numBars, String timeFilter) {
        DataPair data = generateRandomData(numBars);
        this.labels = data.labels;
        this.values = data.values;
        this.timeFilter = timeFilter;
        LocalDate ngayhientai = LocalDate.now();
        this.selectedMonth = ngayhientai.getMonthValue();
        this.selectedYear = ngayhientai.getYear();
        this.animatedValues = new double[values.length];
        timer = new Timer(20, this);
        startAnimation();
        setPreferredSize(new Dimension(calculatePreferredWidth(), 400));
    }

    // Tính chiều rộng ưu tiên dựa trên số cột
    private int calculatePreferredWidth() {
        int minBarWidth = 20; // Chiều rộng tối thiểu cho mỗi cột
        int spacing = 10; // Khoảng cách giữa các cột
        int padding = 50; // Khoảng cách hai bên
        return values.length * (minBarWidth + spacing) + 2 * padding;
    }

    protected static DataPair generateRandomData(int numBars) {
        Random rand = new Random();
        double[] values = new double[numBars];
        String[] labels = new String[numBars];
        for (int i = 0; i < numBars; i++) {
            values[i] = rand.nextDouble() * 1000000.0 + 100000.0;
            labels[i] = String.valueOf(i + 1);
        }
        return new DataPair(values, labels);
    }

    protected int getDaysInMonth(int month, int year) {
        return switch (month) {
            case 2 -> {
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) yield 29;
                else yield 28;
            }
            case 4, 6, 9, 11 -> 30;
            default -> 31;
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int padding = 50;
        int maxBarHeight = height - 2 * padding;
        double maxValue = 0.0;

        // Tìm giá trị lớn nhất
        for (double value : values) {
            if (value > maxValue) maxValue = value;
        }

        // Tiêu đề
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        String[] monthNames = {"", "T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12"};
        String title = "Thống Kê Theo " + timeFilter;
        if ("Ngày".equals(timeFilter)) {
            title += " (" + monthNames[selectedMonth] + " " + selectedYear + ")";
        } else if ("Tháng".equals(timeFilter)) {
            title += " (" + selectedYear + ")";
        } else {
            title += " (5 Năm Gần Nhất)";
        }
        g2d.drawString(title, width / 2 - 50, padding / 2);

        // Vẽ trục
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawLine(padding, height - padding, width - padding, height - padding); // Trục X
        g2d.drawLine(padding, height - padding, padding, padding); // Trục Y

        // Nhãn trục Y (định dạng số tiền)
        DecimalFormat df = new DecimalFormat("#,###.##");
        g2d.drawString("Triệu VND", padding - 40, padding - 20);
        double yStep = maxValue / 5;
        for (int i = 0; i <= 5; i++) {
            int y = height - padding - i * maxBarHeight / 5;
            g2d.drawString(df.format(i * yStep / 1000000.0) + "M", padding - 40, y + 5);
            if (i == 0) continue;
            g2d.drawLine(padding - 5, y, padding + 5, y);
        }

        // Tính chiều rộng cột
        int minBarWidth = 20; // Chiều rộng tối thiểu
        int spacing = 10; // Khoảng cách giữa các cột
        int totalBarWidth = values.length * (minBarWidth + spacing); // Tổng chiều rộng cần thiết
        int barWidth = Math.max(minBarWidth, (width - 2 * padding - (values.length - 1) * spacing) / values.length);

        // Vẽ các cột
        for (int i = 0; i < values.length; i++) {
            int barHeight = (int) ((animatedValues[i] / maxValue) * maxBarHeight);
            int x = padding + i * (barWidth + spacing);
            int y = height - padding - barHeight;

            g2d.setColor(new Color(0, 0, 0, 50)); // Bóng
            g2d.fillRect(x + 5, y + 5, barWidth, barHeight);

            g2d.setColor(new Color(100, 100 + i * 30 % 155, 200 - i * 30 % 155));
            g2d.fillRect(x, y, barWidth, barHeight);

            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, barWidth, barHeight);

            // Nhãn dưới cột
            g2d.drawString(labels[i], x + barWidth / 4, height - padding + 20);
            // Giá trị trên cột (định dạng số tiền)
            g2d.drawString(df.format(animatedValues[i] / 1000000.0) + "M", x + barWidth / 4, y - 5);
        }

        // Vẽ các đường kẻ đứt nét ngang
        g2d.setColor(new Color(150, 150, 150));
        float[] dashPattern = {5, 5};
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));

        HashSet<Integer> drawnYLevels = new HashSet<>();
        for (int i = 0; i < values.length; i++) {
            int y = height - padding - (int) ((animatedValues[i] / maxValue) * maxBarHeight);
            if (!drawnYLevels.contains(y)) {
                g2d.drawLine(padding, y, width - padding, y);
                drawnYLevels.add(y);
            }
        }

        g2d.setStroke(new BasicStroke());
    }

    public void startAnimation() {
        for (int i = 0; i < animatedValues.length; i++) {
            animatedValues[i] = 0.0;
        }
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean done = true;
        for (int i = 0; i < values.length; i++) {
            if (animatedValues[i] < values[i]) {
                animatedValues[i] += values[i] / 50.0;
                if (animatedValues[i] > values[i]) {
                    animatedValues[i] = values[i];
                }
                done = false;
            }
        }
        repaint();
        if (done) timer.stop();
    }

    protected static class DataPair {
        double[] values;
        String[] labels;

        DataPair(double[] values, String[] labels) {
            this.values = values;
            this.labels = labels;
        }
    }
}