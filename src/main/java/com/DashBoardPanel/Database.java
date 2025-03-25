package com.DashBoardPanel;

public class Database {
    // Trả về doanh thu theo ngày của một tháng/năm cụ thể
    public static int[] getRevenueForMonth(int month, int year, int numDays) {
        // Đây là giả lập, bạn thay bằng truy vấn thực tế (DB, file, API)
        int[] revenue = new int[numDays];
        for (int i = 0; i < numDays; i++) {
            revenue[i] = (i + 1) * 100; // Ví dụ: 100, 200, 300, ...
        }
        return revenue;
    }

    public static int[] getRevenueForYears(int startYear, int numYears) {
        int[] revenue = new int[numYears];
        for (int i = 0; i < numYears; i++) {
            revenue[i] = (i + 1) * 1000; // Giả lập dữ liệu theo năm
        }
        return revenue;
    }

    public static int[] getRevenueForYearByMonth(int year) {
        int[] revenue = new int[12];
        for (int i = 0; i < 12; i++) {
            revenue[i] = (i + 1) * 1000; // Giả lập doanh thu tổng theo tháng
        }
        return revenue;
    }
}