package GUI.DashBoardPanel;

public class Database {
    // Lấy doanh thu cho các ngày trong tháng
    public static double[] getRevenueForMonth(int month, int year, int numDays) {
        double[] revenue = new double[numDays];
        // Logic truy vấn cơ sở dữ liệu (giả sử dùng JDBC hoặc ORM)
        // Ví dụ: SELECT SUM(total_amount) FROM orders WHERE YEAR(order_date) = year AND MONTH(order_date) = month AND DAY(order_date) = i+1
        // Giả lập dữ liệu
        for (int i = 0; i < numDays; i++) {
            revenue[i] = 123456.78 + i * 10000.50; // Giả lập giá trị double
        }
        return revenue;
    }

    // Lấy doanh thu cho các tháng trong năm
    public static double[] getRevenueForYearByMonth(int year) {
        double[] revenue = new double[12];
        // Logic truy vấn: SELECT MONTH(order_date), SUM(total_amount) FROM orders WHERE YEAR(order_date) = year GROUP BY MONTH(order_date)
        for (int i = 0; i < 12; i++) {
            revenue[i] = 500000.25 + i * 20000.75; // Giả lập
        }
        return revenue;
    }

    // Lấy doanh thu cho các năm
    public static double[] getRevenueForYears(int startYear, int numYears) {
        double[] revenue = new double[numYears];
        // Logic truy vấn: nextIntSELECT YEAR(order_date), SUM(total_amount) FROM orders WHERE YEAR(order_date) BETWEEN startYear AND startYear+numYears-1 GROUP BY YEAR(order_date)
        for (int i = 0; i < numYears; i++) {
            revenue[i] = 1000000.50 + i * 500000.25; // Giả lập
        }
        return revenue;
    }
}