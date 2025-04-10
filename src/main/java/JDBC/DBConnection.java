package JDBC;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/sieu_thi_mini?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&autoReconnect=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Mở kết nối đến CSDL
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {  // Kiểm tra nếu connection bị đóng
                System.out.println("Tạo lại kết nối MySQL...");
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Kết nối thành công!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi: Không tìm thấy Driver MySQL!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Lỗi: Không thể kết nối đến MySQL!");
            e.printStackTrace();
        }
        return connection;
    }
    

    // Đóng kết nối
    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
                System.out.println("Ket noi da dong!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Thực thi INSERT, UPDATE, DELETE
    public static int executeUpdate(String sql, Object... params) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            buildParams(stmt, params);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Thực thi SELECT (trả về ResultSet)
    public static ResultSet executeQuery(String sql, Object... params) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            buildParams(stmt, params);
            return stmt.executeQuery(); //  Cần đóng ResultSet và Statement ở DAL sau khi dùng xong!
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Gán tham số vào PreparedStatement
    private static void buildParams(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

    // In thông tin CSDL
    public static void printInfo() {
        try {
            if (connection != null) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(" Database Name: " + metaData.getDatabaseProductName());
                System.out.println(" Database Version: " + metaData.getDatabaseProductVersion());
                System.out.println(" JDBC Driver Name: " + metaData.getDriverName());
                System.out.println(" JDBC Driver Version: " + metaData.getDriverVersion());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Test kết nối
    public static void main(String[] args) {
        Connection cnt = DBConnection.getConnection();
        System.out.println(cnt);
        DBConnection.printInfo();
    }
}
