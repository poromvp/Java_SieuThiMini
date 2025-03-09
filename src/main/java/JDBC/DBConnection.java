/*package JDBC;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    public static Connection getConnection() {
        Connection c = null;
        try {
            // Không cần gọi registerDriver nữa, chỉ cần Class.forName()
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Cập nhật URL kết nối cho đúng chuẩn MySQL 8+
            String url = "jdbc:mysql://localhost:3306/sieu_thi_mini?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
            String userName = "root";
            String password = "";

            // Kết nối đến MySQL
            c = DriverManager.getConnection(url, userName, password);
            System.out.println("Kết nối thành công!");

        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi: Không tìm thấy Driver MySQL!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Lỗi: Không thể kết nối đến MySQL!");
            e.printStackTrace();
        }

        return c;
    }
    
    public static void closeConnection(Connection c) {
        if (c != null) {
            try {
                c.close();
                System.out.println("Đóng kết nối thành công!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void printInfo(Connection c) {
        if (c != null) {
            try {
                DatabaseMetaData mtdt = c.getMetaData();
                System.out.println("Database Name: " + mtdt.getDatabaseProductName());
                System.out.println("Database Version: " + mtdt.getDatabaseProductVersion());
                System.out.println("JDBC Driver Name: " + mtdt.getDriverName());
                System.out.println("JDBC Driver Version: " + mtdt.getDriverVersion());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    public static void query(Connection conn) {
        
        if (conn != null) {
            try {
                String sql = "SELECT * FROM DonHang";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int maDH = rs.getInt("maDH");
                    int maKH = rs.getInt("maKH");
                    int maKM = rs.getInt("maKM");
                    int maNV = rs.getInt("maNV");
                    float tongTien = rs.getFloat("tongTien");
                    String ngayTT = rs.getString("NgayTT");
                    int trangThai = rs.getInt("trangThai");

                    System.out.println("Đơn hàng: " + maDH + ", Khách hàng: " + maKH + 
                                       ", Nhân viên: " + maNV + ", Tổng tiền: " + tongTien);
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection cnt = DBConnection.getConnection();
        DBConnection.printInfo(cnt);
        DBConnection.query(cnt);
        DBConnection.closeConnection(cnt);
    }
}*/
