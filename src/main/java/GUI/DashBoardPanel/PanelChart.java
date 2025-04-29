package GUI.DashBoardPanel;

import javax.swing.*;

import BLL.ChartBLL;

import java.awt.*;
import java.time.LocalDate;

public class PanelChart extends JPanel {
    private PanelTomTat panelTomTat;

    public PanelChart(PanelTomTat panelTomTat) {
        this.panelTomTat = panelTomTat;

        // Dữ liệu doanh thu ban đầu (dựa trên ngày hiện tại)
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int currentDay = today.getDayOfMonth();
        double[] revenue = ChartBLL.getRevenueForMonth(month, year, currentDay);
        String[] days = new String[currentDay];
        for (int i = 0; i < currentDay; i++) {
            days[i] = String.valueOf(i + 1);
        }
        vechart chart = new vechart(revenue, days, "Ngày");

        // Cập nhật PanelTomTat với bộ lọc ban đầu
        panelTomTat.updateData("Ngày", month, year);

        // Tạo bộ lọc thời gian
        String[] filters = {"Ngày", "Tháng", "Năm"};
        JComboBox<String> filterBox = new JComboBox<>(filters);
        filterBox.setSelectedItem("Ngày");

        // Tạo bộ chọn tháng
        String[] monthNames = {"T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12"};
        JComboBox<String> monthBox = new JComboBox<>(monthNames);
        monthBox.setSelectedIndex(month - 1);
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

                if (chart.selectedYear == currentYear && chart.selectedMonth > currentMonth) {
                    chart.selectedMonth = currentMonth;
                    monthBox.setSelectedIndex(currentMonth - 1);
                }

                numBars = chart.getDaysInMonth(chart.selectedMonth, chart.selectedYear);
                if (chart.selectedYear == currentYear && chart.selectedMonth == currentMonth) {
                    numBars = currentDay;
                }
                chart.labels = new String[numBars];
                chart.values = new double[numBars];
                chart.animatedValues = new double[numBars];
                chart.values = ChartBLL.getRevenueForMonth(chart.selectedMonth, chart.selectedYear, numBars);
                for (int i = 0; i < numBars; i++) {
                    chart.labels[i] = String.valueOf(i + 1);
                }
            } else if ("Tháng".equals(selectedFilter)) {
                monthBox.setVisible(false);
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

                numBars = 12;
                chart.labels = monthNames.clone();
                chart.values = new double[numBars];
                chart.animatedValues = new double[numBars];
                chart.values = ChartBLL.getRevenueForYearByMonth(chart.selectedYear);
                if (chart.selectedYear == currentYear) {
                    for (int i = currentMonth; i < 12; i++) {
                        chart.values[i] = 0.0;
                    }
                }
            } else { // Năm
                monthBox.setVisible(false);
                thang.setVisible(false);
                yearField.setVisible(false);
                yearLabel.setVisible(false);
                numBars = 5;
                chart.labels = new String[numBars];
                chart.values = new double[numBars];
                chart.animatedValues = new double[numBars];
                chart.values = ChartBLL.getRevenueForYears(currentYear - 4, numBars);
                for (int i = 0; i < numBars; i++) {
                    chart.labels[i] = String.valueOf(currentYear - 4 + i);
                }
            }
            chart.timeFilter = selectedFilter;
            chart.startAnimation();
            // Cập nhật dữ liệu trong PanelTomTat
            panelTomTat.updateData(selectedFilter, chart.selectedMonth, chart.selectedYear);
        });

        // Logic cho monthBox
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
                chart.values = new double[numBars];
                chart.animatedValues = new double[numBars];
                chart.values = ChartBLL.getRevenueForMonth(chart.selectedMonth, chart.selectedYear, numBars);
                for (int i = 0; i < numBars; i++) {
                    chart.labels[i] = String.valueOf(i + 1);
                }
                chart.startAnimation();
                // Cập nhật dữ liệu trong PanelTomTat
                panelTomTat.updateData(chart.timeFilter, chart.selectedMonth, chart.selectedYear);
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
                    chart.values = new double[numBars];
                    chart.animatedValues = new double[numBars];
                    chart.values = ChartBLL.getRevenueForMonth(chart.selectedMonth, chart.selectedYear, numBars);
                    for (int i = 0; i < numBars; i++) {
                        chart.labels[i] = String.valueOf(i + 1);
                    }
                } else { // Tháng
                    numBars = 12;
                    chart.labels = monthNames.clone();
                    chart.values = new double[numBars];
                    chart.animatedValues = new double[numBars];
                    chart.values = ChartBLL.getRevenueForYearByMonth(chart.selectedYear);
                    if (chart.selectedYear == currentYear) {
                        for (int i = currentMonth; i < 12; i++) {
                            chart.values[i] = 0;
                        }
                    }
                }
                chart.startAnimation();
                // Cập nhật dữ liệu trong PanelTomTat
                panelTomTat.updateData(chart.timeFilter, chart.selectedMonth, chart.selectedYear);
            }
        });

        JButton animateButton = new JButton("Load lại");
        animateButton.addActionListener(_ -> {
            int numBars;
            int currentYear = today.getYear();
            int currentMonth = today.getMonthValue();

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

            if ("Ngày".equals(chart.timeFilter) && chart.selectedYear == currentYear
                    && chart.selectedMonth > currentMonth) {
                chart.selectedMonth = currentMonth;
                monthBox.setSelectedIndex(currentMonth - 1);
            }

            if ("Ngày".equals(chart.timeFilter)) {
                numBars = chart.getDaysInMonth(chart.selectedMonth, chart.selectedYear);
                if (chart.selectedYear == currentYear && chart.selectedMonth == currentMonth) {
                    numBars = currentDay;
                }
                chart.labels = new String[numBars];
                chart.values = new double[numBars];
                chart.animatedValues = new double[numBars];
                chart.values = ChartBLL.getRevenueForMonth(chart.selectedMonth, chart.selectedYear, numBars);
                for (int i = 0; i < numBars; i++) {
                    chart.labels[i] = String.valueOf(i + 1);
                }
            } else if ("Tháng".equals(chart.timeFilter)) {
                numBars = 12;
                chart.labels = monthNames.clone();
                chart.values = new double[numBars];
                chart.animatedValues = new double[numBars];
                chart.values = ChartBLL.getRevenueForYearByMonth(chart.selectedYear);
                if (chart.selectedYear == currentYear) {
                    for (int i = currentMonth; i < 12; i++) {
                        chart.values[i] = 0;
                    }
                }
            } else { // Năm
                numBars = 5;
                chart.labels = new String[numBars];
                chart.values = new double[numBars];
                chart.animatedValues = new double[numBars];
                chart.values = ChartBLL.getRevenueForYears(currentYear - 4, numBars);
                for (int i = 0; i < numBars; i++) {
                    chart.labels[i] = String.valueOf(currentYear - 4 + i);
                }
            }
            chart.startAnimation();
            // Cập nhật dữ liệu trong PanelTomTat
            panelTomTat.updateData(chart.timeFilter, chart.selectedMonth, chart.selectedYear);
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

        JScrollPane scr = new JScrollPane(chart);
        scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scr, BorderLayout.CENTER);
        add(pn1, BorderLayout.SOUTH);
    }
}