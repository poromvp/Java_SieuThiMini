package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static int insertProduct(SanPhamDTO sanPham) {
        String sql = "INSERT INTO SanPham ( maNCC, maLSP, tenSP, gia, tenAnh, moTa, trangThai) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        return DBConnection.executeUpdate(sql, 
                 sanPham.getMaNCC(), sanPham.getMaLSP(), 
                sanPham.getTenSP(), sanPham.getGia(), sanPham.getTenAnh(), 
                sanPham.getMoTa(), sanPham.getTrangThai()) ;
    }

    public static boolean updateProduct(SanPhamDTO sanPham) {
        String sql = "UPDATE SanPham SET maNCC=?, maLSP=?, tenSP=?, gia=?, tenAnh=?, moTa=?, trangThai=? WHERE maSP=?";
        return DBConnection.executeUpdate(sql, 
                sanPham.getMaNCC(), sanPham.getMaLSP(), 
                sanPham.getTenSP(), sanPham.getGia(), sanPham.getTenAnh(), 
                sanPham.getMoTa(), sanPham.getTrangThai(), sanPham.getMaSP()) > 0;
    }

    public static boolean deleteProduct(int id) {
        String sql = "DELETE FROM SanPham WHERE maSP = ?";
        return DBConnection.executeUpdate(sql, id) > 0;
    }

    private static SanPhamDTO mapResultSetToSanPham(ResultSet rs) throws SQLException {
        SanPhamDTO sp = new SanPhamDTO();
            sp.setMaSP(rs.getInt("maSP"));
            sp.setMaNCC( rs.getInt("maNCC"));
            sp.setMaLSP( rs.getInt("maLSP"));
            sp.setTenSP(rs.getString("tenSP"));
            sp.setGia( rs.getDouble("gia"));
            sp.setMoTa( rs.getString("moTa"));
            sp.setTrangThai( rs.getString("trangThai"));
        
        return sp;
    }

    public static void main(String[] args) {
        SanPhamDTO sanPham = new SanPhamDTO(0,  1, 1, "1.png", 99999, "kkkk", "kkkkkkkk", "hoatdonj");
        System.out.println(insertProduct(sanPham));
    }
}
