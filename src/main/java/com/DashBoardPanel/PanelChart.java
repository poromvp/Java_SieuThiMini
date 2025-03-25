package com.DashBoardPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PanelChart extends JPanel {

    public PanelChart() {
        // Dữ liệu doanh thu ban đầu (dựa trên ngày hiện tại)
        LocalDate today = LocalDate.now(); // Ví dụ: 2025-03-20
        int year = today.getYear(); // 2025
        int month = today.getMonthValue(); // 3
        int currentDay = today.getDayOfMonth(); // 20
        int[] revenue = Database.getRevenueForMonth(month, year, currentDay); // Dữ liệu thực tế cho ngày
        String[] days = new String[currentDay];
        for (int i = 0; i < currentDay; i++) {
            days[i] = String.valueOf(i + 1);
        }
        vechart chart = new vechart(revenue, days, "Ngày");

        // Tạo bộ lọc thời gian
        String[] filters = { "Ngày", "Tháng", "Năm" };
        JComboBox<String> filterBox = new JComboBox<>(filters);
        filterBox.setSelectedItem("Ngày");

        // Tạo bộ chọn tháng (chỉ dùng cho "Ngày")
        String[] monthNames = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        JComboBox<String> monthBox = new JComboBox<>(monthNames);
        monthBox.setSelectedIndex(month - 1); // Mặc định tháng hiện tại
        monthBox.setVisible(true);
        JLabel thang = new JLabel("Tháng:");
        thang.setVisible(true);

        // Tạo bộ chọn năm
        JLabel yearLabel = new JLabel("Năm:");
        JTextField yearField = new JTextField(String.valueOf(year), 5);
        yearField.setVisible(true);
        yearLabel.setVisible(true);

        // Logic cho filterBox
        filterBox.addActionListener(_ -> {
            String selectedFilter = (String) filterBox.getSelectedItem();
            int numBars;
            int currentYear = today.getYear();
            int currentMonth = today.getMonthValue();

            if ("Ngày".equals(selectedFilter)) {
                monthBox.setVisible(true);
                thang.setVisible(true);
                yearField.setVisible(true);
                yearLabel.setVisible(true);

                chart.selectedMonth = monthBox.getSelectedIndex() + 1;
                try {
                    int inputYear = Integer.parseInt(yearField.getText());
                    chart.selectedYear = (inputYear > currentYear) ? currentYear : inputYear;
                    yearField.setText(String.valueOf(chart.selectedYear));
                } catch (NumberFormatException e) {
                    chart.selectedYear = currentYear;
                    yearField.setText(String.valueOf(currentYear));
                }

                // Không cho chọn tháng tương lai trong năm hiện tại
                if (chart.selectedYear == currentYear && chart.selectedMonth > currentMonth) {
                    chart.selectedMonth = currentMonth;
                    monthBox.setSelectedIndex(currentMonth - 1);
                }

                numBars = chart.getDaysInMonth(chart.selectedMonth, chart.selectedYear);
                if (chart.selectedYear == currentYear && chart.selectedMonth == currentMonth) {
                    numBars = currentDay; // Giới hạn đến ngày hiện tại
                }
                chart.labels = new String[numBars];
                chart.values = new int[numBars];
                chart.animatedValues = new int[numBars];
                chart.values = Database.getRevenueForMonth(chart.selectedMonth, chart.selectedYear, numBars);
                for (int i = 0; i < numBars; i++) {
                    chart.labels[i] = String.valueOf(i + 1); // Nhãn là ngày
                }
            } else if ("Tháng".equals(selectedFilter)) {
                monthBox.setVisible(false); // Không cần chọn tháng khi hiển thị 12 tháng
                thang.setVisible(false);
                yearField.setVisible(true);
                yearLabel.setVisible(true);

                try {
                    int inputYear = Integer.parseInt(yearField.getText());
                    chart.selectedYear = (inputYear > currentYear) ? currentYear : inputYear;
                    yearField.setText(String.valueOf(chart.selectedYear));
                } catch (NumberFormatException e) {
                    chart.selectedYear = currentYear;
                    yearField.setText(String.valueOf(currentYear));
                }

                numBars = 12; // 12 tháng
                chart.labels = monthNames.clone();
                chart.values = new int[numBars];
                chart.animatedValues = new int[numBars];
                chart.values = Database.getRevenueForYearByMonth(chart.selectedYear);
                if (chart.selectedYear == currentYear) {
                    for (int i = currentMonth; i < 12; i++) {
                        chart.values[i] = 0; // Tháng tương lai = 0
                    }
                }
            } else { // Năm
                monthBox.setVisible(false);
                thang.setVisible(false);
                yearField.setVisible(false);
                yearLabel.setVisible(false);
                numBars = 5; // Số năm cố định
                chart.labels = new String[numBars];
                chart.values = new int[numBars];
                chart.animatedValues = new int[numBars];
                chart.values = Database.getRevenueForYears(currentYear - 4, numBars);
                for (int i = 0; i < numBars; i++) {
                    chart.labels[i] = String.valueOf(currentYear - 4 + i);
                }
            }
            chart.timeFilter = selectedFilter;
            chart.startAnimation();
        });

        // Logic cho monthBox (chỉ dùng cho "Ngày")
        monthBox.addActionListener(_ -> {
            if ("Ngày".equals(chart.timeFilter)) {
                int currentYear = today.getYear();
                int currentMonth = today.getMonthValue();

                chart.selectedMonth = monthBox.getSelectedIndex() + 1;
                try {
                    int inputYear = Integer.parseInt(yearField.getText());
                    chart.selectedYear = (inputYear > currentYear) ? currentYear : inputYear;
                    yearField.setText(String.valueOf(chart.selectedYear));
                } catch (NumberFormatException e) {
                    chart.selectedYear = currentYear;
                    yearField.setText(String.valueOf(currentYear));
                }

                if (chart.selectedYear == currentYear && chart.selectedMonth > currentMonth) {
                    chart.selectedMonth = currentMonth;
                    monthBox.setSelectedIndex(currentMonth - 1);
                    JOptionPane.showMessageDialog(null, "Không thể chọn tháng tương lai!");
                }

                int numBars = chart.getDaysInMonth(chart.selectedMonth, chart.selectedYear);
                if (chart.selectedYear == currentYear && chart.selectedMonth == currentMonth) {
                    numBars = currentDay;
                }
                chart.labels = new String[numBars];
                chart.values = new int[numBars];
                chart.animatedValues = new int[numBars];
                chart.values = Database.getRevenueForMonth(chart.selectedMonth, chart.selectedYear, numBars);
                for (int i = 0; i < numBars; i++) {
                    chart.labels[i] = String.valueOf(i + 1);
                }
                chart.startAnimation();
            }
        });

        // Logic cho yearField
        yearField.addActionListener(_ -> {
            if ("Ngày".equals(chart.timeFilter) || "Tháng".equals(chart.timeFilter)) {
                int currentYear = today.getYear();
                int currentMonth = today.getMonthValue();

                try {
                    int inputYear = Integer.parseInt(yearField.getText());
                    chart.selectedYear = (inputYear > currentYear) ? currentYear : inputYear;
                    yearField.setText(String.valueOf(chart.selectedYear));
                    if (inputYear > currentYear) {
                        JOptionPane.showMessageDialog(null, "Không thể chọn năm tương lai!");
                    }
                } catch (NumberFormatException e) {
                    chart.selectedYear = currentYear;
                    yearField.setText(String.valueOf(currentYear));
                }

                int numBars;
                if ("Ngày".equals(chart.timeFilter)) {
                    if (chart.selectedYear == currentYear && chart.selectedMonth > currentMonth) {
                        chart.selectedMonth = currentMonth;
                        monthBox.setSelectedIndex(currentMonth - 1);
                    }
                    numBars = chart.getDaysInMonth(chart.selectedMonth, chart.selectedYear);
                    if (chart.selectedYear == currentYear && chart.selectedMonth == currentMonth) {
                        numBars = currentDay;
                    }
                    chart.labels = new String[numBars];
                    chart.values = new int[numBars];
                    chart.animatedValues = new int[numBars];
                    chart.values = Database.getRevenueForMonth(chart.selectedMonth, chart.selectedYear, numBars);
                    for (int i = 0; i < numBars; i++) {
                        chart.labels[i] = String.valueOf(i + 1);
                    }
                } else { // Tháng
                    numBars = 12;
                    chart.labels = monthNames.clone();
                    chart.values = new int[numBars];
                    chart.animatedValues = new int[numBars];
                    chart.values = Database.getRevenueForYearByMonth(chart.selectedYear);
                    if (chart.selectedYear == currentYear) {
                        for (int i = currentMonth; i < 12; i++) {
                            chart.values[i] = 0; // Tháng tương lai = 0
                        }
                    }
                }
                chart.startAnimation();
            }
        });

        JButton animateButton = new JButton("Load lại");
        animateButton.addActionListener(_ -> {
            int numBars;
            int currentYear = today.getYear();
            int currentMonth = today.getMonthValue();

            // Đồng bộ selectedMonth và selectedYear từ giao diện
            if ("Ngày".equals(chart.timeFilter)) {
                chart.selectedMonth = monthBox.getSelectedIndex() + 1;
            }
            try {
                int inputYear = Integer.parseInt(yearField.getText());
                chart.selectedYear = (inputYear > currentYear) ? currentYear : inputYear;
                yearField.setText(String.valueOf(chart.selectedYear));
            } catch (NumberFormatException e) {
                chart.selectedYear = currentYear;
                yearField.setText(String.valueOf(currentYear));
            }

            // Kiểm tra và giới hạn tháng tương lai
            if ("Ngày".equals(chart.timeFilter) && chart.selectedYear == currentYear
                    && chart.selectedMonth > currentMonth) {
                chart.selectedMonth = currentMonth;
                monthBox.setSelectedIndex(currentMonth - 1);
            }

            // Tải dữ liệu theo timeFilter
            if ("Ngày".equals(chart.timeFilter)) {
                numBars = chart.getDaysInMonth(chart.selectedMonth, chart.selectedYear);
                if (chart.selectedYear == currentYear && chart.selectedMonth == currentMonth) {
                    numBars = currentDay;
                }
                chart.labels = new String[numBars];
                chart.values = new int[numBars];
                chart.animatedValues = new int[numBars];
                chart.values = Database.getRevenueForMonth(chart.selectedMonth, chart.selectedYear, numBars);
                for (int i = 0; i < numBars; i++) {
                    chart.labels[i] = String.valueOf(i + 1);
                }
            } else if ("Tháng".equals(chart.timeFilter)) {
                numBars = 12;
                chart.labels = monthNames.clone();
                chart.values = new int[numBars];
                chart.animatedValues = new int[numBars];
                chart.values = Database.getRevenueForYearByMonth(chart.selectedYear);
                if (chart.selectedYear == currentYear) {
                    for (int i = currentMonth; i < 12; i++) {
                        chart.values[i] = 0; // Tháng tương lai = 0
                    }
                }
            } else { // Năm
                numBars = 5;
                chart.labels = new String[numBars];
                chart.values = new int[numBars];
                chart.animatedValues = new int[numBars];
                chart.values = Database.getRevenueForYears(currentYear - 4, numBars);
                for (int i = 0; i < numBars; i++) {
                    chart.labels[i] = String.valueOf(currentYear - 4 + i);
                }
            }
            chart.startAnimation();
        });

        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        JPanel pn1 = new JPanel();
        pn1.setLayout(new FlowLayout());

        pn1.add(new JLabel("Lọc:"));

        pn1.add(filterBox);

        pn1.add(thang);

        pn1.add(monthBox);

        pn1.add(yearLabel);

        pn1.add(yearField);

        pn1.add(animateButton);

        add(chart, BorderLayout.CENTER);
        add(pn1, BorderLayout.SOUTH);
    }
}