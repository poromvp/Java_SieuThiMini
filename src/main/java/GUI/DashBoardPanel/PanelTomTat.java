package GUI.DashBoardPanel;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import BLL.ChartBLL;
import BLL.DonHangBLL;
import BLL.SanPhamBLL;
import DTO.DonHangDTO;
import GUI.ComponentCommon.*;

public class PanelTomTat extends JPanel {
    JPanel pn1, pn2, pn3;
    JLabel sp, luotmua, doanhthu;
    public ArrayList<DonHangDTO> HoaDon = DonHangBLL.getAllOrders();
    private String timeFilter;
    private int selectedMonth;
    private int selectedYear;

    public PanelTomTat() {
        // Khởi tạo mặc định
        LocalDate today = LocalDate.now();
        timeFilter = "Ngày";
        selectedMonth = today.getMonthValue();
        selectedYear = today.getYear();

        setLayout(new GridLayout(1, 2, 10, 10));

        /*pn1 = new JPanel();
        initPanel1();
        add(pn1);*/

        pn2 = new JPanel();
        initPanel2();
        add(pn2);

        pn3 = new JPanel();
        initPanel3();
        add(pn3);
    }

    public void initPanel1() {
        TitledBorder border = new TitledBorder("Sản Phẩm");
        border.setTitleColor(Color.WHITE);
        pn1.setBorder(new CompoundBorder(border, new EmptyBorder(4, 4, 4, 4)));
        pn1.setLayout(new GridLayout(1, 2, 5, 5));
        pn1.setBackground(new Color(33, 58, 89));

        JLabel lbName = new JLabel("Sản Phẩm:");
        TienIch.labelStyle(lbName, 1, 18, null);
        pn1.add(lbName);

        sp = new JLabel(SanPhamBLL.getAllProducts().size() + "");
        TienIch.labelStyle(sp, 2, 20, null);
        pn1.add(sp);
    }

    public void initPanel2() {
        TienIch.taoTitleBorder(pn2, "Lượt mua");
        pn2.setLayout(new GridLayout(1, 2, 5, 5));
        pn2.setBackground(new Color(33, 58, 89));

        JLabel lbName = new JLabel("Lượt Mua:");
        TienIch.labelStyle(lbName, 1, 18, null);
        pn2.add(lbName);

        // Tính lượt mua dựa trên timeFilter
        int totalOrders = calculateOrderCount();
        luotmua = new JLabel(totalOrders + "");
        TienIch.labelStyle(luotmua, 2, 20, null);
        pn2.add(luotmua);
    }

    public void initPanel3() {
        TienIch.taoTitleBorder(pn3, "Doanh thu");
        pn3.setLayout(new GridLayout(1, 2, 5, 5));
        pn3.setBackground(new Color(33, 58, 89));

        JLabel lbName = new JLabel("Doanh Thu:");
        TienIch.labelStyle(lbName, 1, 18, null);
        pn3.add(lbName);

        // Tính doanh thu dựa trên timeFilter
        double sumDoanhThu = calculateRevenue();
        doanhthu = new JLabel(TienIch.formatVND(sumDoanhThu));
        TienIch.labelStyle(doanhthu, 2, 20, null);
        pn3.add(doanhthu);
    }

    private int calculateOrderCount() {
        int totalOrders = 0;
        if ("Ngày".equals(timeFilter)) {
            int numDays = getDaysInMonth(selectedMonth, selectedYear);
            if (selectedYear == LocalDate.now().getYear() && selectedMonth == LocalDate.now().getMonthValue()) {
                numDays = LocalDate.now().getDayOfMonth();
            }
            int[] orderCounts = ChartBLL.countOrdersForMonth(selectedMonth, selectedYear, numDays);
            for (int count : orderCounts) {
                totalOrders += count;
            }
        } else if ("Tháng".equals(timeFilter)) {
            int[] orderCounts = ChartBLL.countOrdersForYearByMonth(selectedYear);
            for (int count : orderCounts) {
                totalOrders += count;
            }
        } else { // Năm
            int[] orderCounts = ChartBLL.countOrdersForYears(selectedYear - 4, 5);
            for (int count : orderCounts) {
                totalOrders += count;
            }
        }
        return totalOrders;
    }

    private double calculateRevenue() {
        double sumDoanhThu = 0.0;
        if ("Ngày".equals(timeFilter)) {
            int numDays = getDaysInMonth(selectedMonth, selectedYear);
            if (selectedYear == LocalDate.now().getYear() && selectedMonth == LocalDate.now().getMonthValue()) {
                numDays = LocalDate.now().getDayOfMonth();
            }
            double[] revenue = ChartBLL.getRevenueForMonth(selectedMonth, selectedYear, numDays);
            for (double value : revenue) {
                sumDoanhThu += value;
            }
        } else if ("Tháng".equals(timeFilter)) {
            double[] revenue = ChartBLL.getRevenueForYearByMonth(selectedYear);
            for (double value : revenue) {
                sumDoanhThu += value;
            }
        } else { // Năm
            double[] revenue = ChartBLL.getRevenueForYears(selectedYear - 4, 5);
            for (double value : revenue) {
                sumDoanhThu += value;
            }
        }
        return sumDoanhThu;
    }

    private int getDaysInMonth(int month, int year) {
        return switch (month) {
            case 2 -> {
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) yield 29;
                else yield 28;
            }
            case 4, 6, 9, 11 -> 30;
            default -> 31;
        };
    }

    // Phương thức để cập nhật dữ liệu (doanh thu và lượt mua)
    public void updateData(String timeFilter, int selectedMonth, int selectedYear) {
        this.timeFilter = timeFilter;
        this.selectedMonth = selectedMonth;
        this.selectedYear = selectedYear;
        pn2.removeAll(); // Xóa panel Lượt Mua
        initPanel2(); // Tạo lại panel Lượt Mua
        pn3.removeAll(); // Xóa panel Doanh Thu
        initPanel3(); // Tạo lại panel Doanh Thu
        pn2.revalidate();
        pn2.repaint();
        pn3.revalidate();
        pn3.repaint();
    }
}