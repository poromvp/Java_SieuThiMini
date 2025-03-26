package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DTO.SanPhamDTO;
import JDBC.DBConnection;

public class SanPhamDAL {




    public static SanPhamDTO getSanPhamByMaSanPham(int id) {
        String sql = "SELECT * FROM SanPham WHERE id = ?";
        SanPhamDTO sanPham = null;
        
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                sanPham = new SanPhamDTO();
                sanPham.setMaSP(rs.getInt("maSP"));
                sanPham.setTenSP(rs.getString("tenSP"));
                sanPham.setGia(rs.getDouble("gia"));
                // Thêm các thuộc tính khác nếu cần
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return sanPham;
    }
}
