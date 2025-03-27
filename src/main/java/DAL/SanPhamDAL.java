package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DTO.SanPhamDTO;
import JDBC.DBConnection;

public class SanPhamDAL {




    public static SanPhamDTO getProductById(int id) {
        String sql = "SELECT * FROM SanPham WHERE maSP = ?";
        SanPhamDTO sanPham = null;
        
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                sanPham = new SanPhamDTO();
                sanPham.setMaSP(rs.getInt("maSP"));
                sanPham.setMaLH(rs.getInt("maLH"));
                sanPham.setMaNCC(rs.getInt("maNCC"));
                sanPham.setMaLSP(rs.getInt("maLSP"));
                sanPham.setTenSP(rs.getString("tenSP"));
                sanPham.setGia(rs.getDouble("gia"));
                sanPham.setTenAnh(rs.getString("tenAnh"));
                sanPham.setMoTa(rs.getString("moTa"));
                sanPham.setTrangThai(rs.getString("trangThai"));
                // Thêm các thuộc tính khác nếu cần
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return sanPham;
    }


    public static void main(String[] args) {
        SanPhamDTO sp = getProductById(1);
        System.out.println(sp.toString());
    }
}
