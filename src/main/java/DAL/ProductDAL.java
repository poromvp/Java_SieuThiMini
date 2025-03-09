package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.Product;
import JDBC.DBConnection;

public class ProductDAL {

    public static Product getSanPhamByMaSanPham(int id) {
        Connection conn = DBConnection.getConnection();
        Product sp = null;
        String sql = "SELECT * FROM SanPham WHERE maSP = ?";

        if (conn != null) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {  // Kiểm tra nếu có dữ liệu
                    int maSP = rs.getInt("maSP");
                    int maNCC = rs.getInt("maNCC");
                    int maLSP = rs.getInt("maLSP");
                    String tenSP = rs.getString("tenSP"); // Sửa từ "maNV" thành "tenSP"
                    String ngaySX = rs.getString("ngaySX");
                    String ngayHH = rs.getString("ngayHH");
                    float gia = rs.getFloat("gia");
                    int soLuong = rs.getInt("soLuong");
                    int trangThai = rs.getInt("trangThai");

                    sp = new Product(maSP, maNCC, maLSP, tenSP, ngaySX, ngayHH, gia, soLuong, trangThai);
                }
                rs.close(); // Đóng ResultSet
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close(); // Đóng Connection
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return sp;
    }
}
