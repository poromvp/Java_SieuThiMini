package DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import DTO.ChiTietKhuyenMaiDTO;
import DTO.KhuyenMaiDTO;
import JDBC.DBConnection;

public class ChiTietKhuyenMaiDAL {

    public static final String tableName = "ChiTietKM";

    public static ArrayList<ChiTietKhuyenMaiDTO> getAllDiscountDetailByDiscountId(int discountId) {
        ArrayList<ChiTietKhuyenMaiDTO> detailList = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE maKM = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, discountId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ChiTietKhuyenMaiDTO ctkm = new ChiTietKhuyenMaiDTO();
                ctkm.setMaKM(rs.getInt("maKM"));
                ctkm.setMaSP(rs.getInt("maSP"));
                ctkm.setTiLeGiam(rs.getDouble("tiLeGiam"));
                ctkm.setTrangThai(rs.getString("trangThai"));
                detailList.add(ctkm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detailList;
    }

    public static double getProductOnSaleToday(int productId) {
        String sql = "SELECT ctkm.tiLeGiam FROM ChiTietKM ctkm " +
                     "JOIN KhuyenMai km ON ctkm.maKM = km.maKM " +
                     "WHERE ctkm.maSP = ? " +
                     "AND km.ngayBD <= CURDATE() AND km.ngayKT >= CURDATE() " +
                     "AND km.trangThai = 'ACTIVE'";
    
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
    
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                return rs.getDouble("tiLeGiam");
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return 0;
    }
    
    public static int insertDiscountDetail(ChiTietKhuyenMaiDTO detail) {
        String sql = "INSERT INTO " + tableName + " (maKM, maSP, tiLeGiam, trangThai) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, detail.getMaKM());
            pstmt.setInt(2, detail.getMaSP());
            pstmt.setDouble(3, detail.getTiLeGiam());
            pstmt.setString(4, detail.getTrangThai());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int updateDiscountDetail(ChiTietKhuyenMaiDTO detail) {
        String sql = "UPDATE " + tableName + " SET tiLeGiam = ?, trangThai = ? WHERE maKM = ? AND maSP = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setDouble(1, detail.getTiLeGiam());
            pstmt.setString(2, detail.getTrangThai());
            pstmt.setInt(3, detail.getMaKM());
            pstmt.setInt(4, detail.getMaSP());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int deleteDiscountDetail(int maKM, int maSP) {
        String sql = "DELETE FROM " + tableName + " WHERE maKM = ? AND maSP = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, maKM);
            pstmt.setInt(2, maSP);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    
    public static void main(String[] args) {
        System.out.println(getProductOnSaleToday(1));
    }
}