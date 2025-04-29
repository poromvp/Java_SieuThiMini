package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.DBConnection;

public class ChartDAL {
    public static double[] getRevenueForMonth(int month, int year, int numDays) {
        double[] revenue = new double[numDays];
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT DAY(NgayTT), SUM(TongTien) " +
                        "FROM donhang " +
                        "WHERE YEAR(NgayTT) = ? AND MONTH(NgayTT) = ? AND DAY(NgayTT) <= ? " +
                        "GROUP BY DAY(NgayTT)")) {
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            stmt.setInt(3, numDays);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int day = rs.getInt(1);
                if (day >= 1 && day <= numDays) {
                    revenue[day - 1] = rs.getDouble(2);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage());
            return new double[numDays];
        }
        return revenue;
    }

    public static double[] getRevenueForYearByMonth(int year) {
        double[] revenue = new double[12];
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT MONTH(NgayTT), SUM(TongTien) " +
                        "FROM donhang " +
                        "WHERE YEAR(NgayTT) = ? " +
                        "GROUP BY MONTH(NgayTT)")) {
            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int month = rs.getInt(1);
                if (month >= 1 && month <= 12) {
                    revenue[month - 1] = rs.getDouble(2);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage());
            return new double[12];
        }
        return revenue;
    }

    public static double[] getRevenueForYears(int startYear, int numYears) {
        double[] revenue = new double[numYears];
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT YEAR(NgayTT), SUM(TongTien) " +
                                "FROM donhang " +
                                "WHERE YEAR(NgayTT) BETWEEN ? AND ? " +
                                "GROUP BY YEAR(NgayTT)")) {
            stmt.setInt(1, startYear);
            stmt.setInt(2, startYear + numYears - 1);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int year = rs.getInt(1);
                if (year >= startYear && year < startYear + numYears) {
                    revenue[year - startYear] = rs.getDouble(2);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage());
            return new double[numYears];
        }
        return revenue;
    }

    public static int[] countOrdersForMonth(int month, int year, int numDays) {
        int[] orderCounts = new int[numDays];
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT DAY(NgayTT), COUNT(*) " +
                        "FROM donhang " +
                        "WHERE YEAR(NgayTT) = ? AND MONTH(NgayTT) = ? AND DAY(NgayTT) <= ? " +
                        "GROUP BY DAY(NgayTT)")) {
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            stmt.setInt(3, numDays);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int day = rs.getInt(1);
                if (day >= 1 && day <= numDays) {
                    orderCounts[day - 1] = rs.getInt(2);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage());
            return new int[numDays];
        }
        return orderCounts;
    }

    public static int[] countOrdersForYearByMonth(int year) {
        int[] orderCounts = new int[12];
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT MONTH(NgayTT), COUNT(*) " +
                        "FROM donhang " +
                        "WHERE YEAR(NgayTT) = ? " +
                        "GROUP BY MONTH(NgayTT)")) {
            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int month = rs.getInt(1);
                if (month >= 1 && month <= 12) {
                    orderCounts[month - 1] = rs.getInt(2);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage());
            return new int[12];
        }
        return orderCounts;
    }

    public static int[] countOrdersForYears(int startYear, int numYears) {
        int[] orderCounts = new int[numYears];
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT YEAR(NgayTT), COUNT(*) " +
                                "FROM donhang " +
                                "WHERE YEAR(NgayTT) BETWEEN ? AND ? " +
                                "GROUP BY YEAR(NgayTT)")) {
            stmt.setInt(1, startYear);
            stmt.setInt(2, startYear + numYears - 1);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int year = rs.getInt(1);
                if (year >= startYear && year < startYear + numYears) {
                    orderCounts[year - startYear] = rs.getInt(2);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage());
            return new int[numYears];
        }
        return orderCounts;
    }
}