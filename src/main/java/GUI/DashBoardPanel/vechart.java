package GUI.DashBoardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.time.LocalDate;

public class vechart extends JPanel implements ActionListener {
    protected String[] labels;         // Nhãn của các cột
    protected int[] values;           // Giá trị của các cột
    protected int[] animatedValues;   // Giá trị động cho animation
    protected Timer timer;
    protected String timeFilter;      // Bộ lọc thời gian: "Ngày", "Tháng", "Năm"
    protected int selectedMonth;      // Tháng được chọn (1-12, dùng khi timeFilter là "Ngày")
    protected int selectedYear;       // Năm được chọn

    // Getter và Setter giữ nguyên, không liệt kê lại để tiết kiệm không gian

    public vechart() {
        this(12, "Tháng"); // Mặc định là 12 tháng
    }

    public vechart(int[] values, String[] labels, String timeFilter) {
        this.labels = labels;
        this.values = values;
        this.timeFilter = timeFilter;
        LocalDate ngayhientai = LocalDate.now();
        this.selectedMonth = ngayhientai.getMonthValue();
        this.selectedYear = ngayhientai.getYear();
        this.animatedValues = new int[values.length];
        timer = new Timer(20, this);
        startAnimation();
    }

    public vechart(int numBars, String timeFilter) {
        DataPair data = generateRandomData(numBars);
        this.labels = data.labels;
        this.values = data.values;
        this.timeFilter = timeFilter;
        LocalDate ngayhientai = LocalDate.now();
        this.selectedMonth = ngayhientai.getMonthValue();
        this.selectedYear = ngayhientai.getYear();
        this.animatedValues = new int[values.length];
        timer = new Timer(20, this);
        startAnimation();
    }

    protected static DataPair generateRandomData(int numBars) {
        Random rand = new Random();
        int[] values = new int[numBars];
        String[] labels = new String[numBars];
        for (int i = 0; i < numBars; i++) {
            values[i] = rand.nextInt(1000) + 100;
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
        int maxValue = 0;

        for (int value : values) {
            if (value > maxValue) maxValue = value;
        }

        // Tiêu đề
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        String[] monthNames = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String title = "Thống Kê Theo " + timeFilter;
        if ("Ngày".equals(timeFilter)) {
            title += " (" + monthNames[selectedMonth] + " " + selectedYear + ")";
        } else if ("Tháng".equals(timeFilter)) {
            title += " (" + selectedYear + ")";
        } else { // Năm
            title += " (5 Năm Gần Nhất)";
        }
        g2d.drawString(title, width / 2 - 50, padding / 2);

        // Vẽ trục
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawLine(padding, height - padding, width - padding, height - padding); // Trục X
        g2d.drawLine(padding, height - padding, padding, padding); // Trục Y

        // Nhãn trục Y
        g2d.drawString("Triệu VND", padding - 20, padding - 20);
        int yStep = maxValue / 5;
        for (int i = 0; i <= 5; i++) {
            int y = height - padding - i * maxBarHeight / 5;
            g2d.drawString(String.valueOf(i * yStep), padding - 30, y + 5);
            g2d.drawLine(padding - 5, y, padding + 5, y);
        }

        // Vẽ các cột
        int barWidth = (width - 2 * padding) / values.length - 20;
        for (int i = 0; i < values.length; i++) {
            int barHeight = (int) ((double) animatedValues[i] / maxValue * maxBarHeight);
            int x = padding + i * (barWidth + 20) + 10;
            int y = height - padding - barHeight;

            g2d.setColor(new Color(0, 0, 0, 50)); // Bóng
            g2d.fillRect(x + 5, y + 5, barWidth, barHeight);

            g2d.setColor(new Color(100, 100 + i * 30 % 155, 200 - i * 30 % 155)); // Màu cột
            g2d.fillRect(x, y, barWidth, barHeight);

            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, barWidth, barHeight);

            // Nhãn dưới cột
            g2d.drawString(labels[i], x + barWidth / 4, height - padding + 20);
            // Giá trị trên cột
            g2d.drawString(String.valueOf(animatedValues[i]), x + barWidth / 4, y - 5);
        }
    }

    public void startAnimation() {
        for (int i = 0; i < animatedValues.length; i++) {
            animatedValues[i] = 0;
        }
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean done = true;
        for (int i = 0; i < values.length; i++) {
            if (animatedValues[i] < values[i]) {
                animatedValues[i] += 100;
                done = false;
            }
        }
        repaint();
        if (done) timer.stop();
    }

    protected static class DataPair {
        int[] values;
        String[] labels;

        DataPair(int[] values, String[] labels) {
            this.values = values;
            this.labels = labels;
        }
    }
}