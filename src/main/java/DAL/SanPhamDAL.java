package DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DTO.SanPhamDTO;
import JDBC.DBConnection;

public class SanPhamDAL {

    public static SanPhamDTO getProductById(int id) {
        String sql = "SELECT * FROM SanPham WHERE maSP = ?";
        try (ResultSet rs = DBConnection.executeQuery(sql, id)) {
            if (rs.next()) {
                return mapResultSetToSanPham(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<SanPhamDTO> getAllProducts() {
        List<SanPhamDTO> productList = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";

        try (ResultSet rs = DBConnection.executeQuery(sql)) {
            while (rs.next()) {
                productList.add(mapResultSetToSanPham(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public static boolean insertProduct(SanPhamDTO sanPham) {
        String sql = "INSERT INTO SanPham (maLH, maNCC, maLSP, tenSP, gia, tenAnh, moTa, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return DBConnection.executeUpdate(sql, 
                sanPham.getMaLH(), sanPham.getMaNCC(), sanPham.getMaLSP(), 
                sanPham.getTenSP(), sanPham.getGia(), sanPham.getTenAnh(), 
                sanPham.getMoTa(), sanPham.getTrangThai()) > 0;
    }

    public static boolean updateProduct(SanPhamDTO sanPham) {
        String sql = "UPDATE SanPham SET maLH=?, maNCC=?, maLSP=?, tenSP=?, gia=?, tenAnh=?, moTa=?, trangThai=? WHERE maSP=?";
        return DBConnection.executeUpdate(sql, 
                sanPham.getMaLH(), sanPham.getMaNCC(), sanPham.getMaLSP(), 
                sanPham.getTenSP(), sanPham.getGia(), sanPham.getTenAnh(), 
                sanPham.getMoTa(), sanPham.getTrangThai(), sanPham.getMaSP()) > 0;
    }

    public static boolean deleteProduct(int id) {
        String sql = "DELETE FROM SanPham WHERE maSP = ?";
        return DBConnection.executeUpdate(sql, id) > 0;
    }

    private static SanPhamDTO mapResultSetToSanPham(ResultSet rs) throws SQLException {
        return new SanPhamDTO(
            rs.getInt("maSP"),
            rs.getInt("maLH"),
            rs.getInt("maNCC"),
            rs.getInt("maLSP"),
            rs.getString("tenSP"),
            rs.getDouble("gia"),
            rs.getString("tenAnh"),
            rs.getString("moTa"),
            rs.getString("trangThai")
        );
    }
}
