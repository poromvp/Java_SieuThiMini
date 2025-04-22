package DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DTO.KhuyenMaiDTO;
import JDBC.DBConnection;

public class KhuyenMaiDAL {
    public static ArrayList<KhuyenMaiDTO> getAllDiscount() {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM KhuyenMai ";
        ArrayList<KhuyenMaiDTO> discountList = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                KhuyenMaiDTO discount = new KhuyenMaiDTO(
                        rs.getInt("maKM"),
                        rs.getString("tenKM"),
                        rs.getDate("ngayKT"),
                        rs.getDate("ngayBD"),
                        rs.getString("trangThai")
                );
                discountList.add(discount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discountList;
    }

    public static KhuyenMaiDTO getDiscountToday() {
        String sql = "SELECT * FROM KhuyenMai WHERE ngayBD <= ? AND ngayKT >= ?";
        KhuyenMaiDTO discount = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            LocalDate today = LocalDate.now();
            pstmt.setDate(1, Date.valueOf(today));
            pstmt.setDate(2, Date.valueOf(today));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                discount = new KhuyenMaiDTO(
                        rs.getInt("maKM"),
                        rs.getString("tenKM"),
                        rs.getDate("ngayKT"),
                        rs.getDate("ngayBD"),
                        rs.getString("trangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount;
    }

    public static KhuyenMaiDTO getDiscountById(int id) {
        String sql = "SELECT * FROM KhuyenMai WHERE maKM = ?";
        KhuyenMaiDTO khuyenMai = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                khuyenMai = new KhuyenMaiDTO(
                        rs.getInt("maKM"),
                        rs.getString("tenKM"),
                        rs.getDate("ngayKT"),
                        rs.getDate("ngayBD"),
                        rs.getString("trangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khuyenMai;
    }

    public static int insertDiscount(KhuyenMaiDTO khuyenMai) {
        String sql = "INSERT INTO KhuyenMai (maKM, tenKM, ngayKT, ngayBD, trangThai) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, khuyenMai.getMaKM());
            pstmt.setString(2, khuyenMai.getTenKM());
            pstmt.setDate(3, khuyenMai.getNgayKT());
            pstmt.setDate(4, khuyenMai.getNgayBD());
            pstmt.setString(5, khuyenMai.getTrangThai());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int updateDiscount(KhuyenMaiDTO khuyenMai) {
        String sql = "UPDATE KhuyenMai SET tenKM = ?, ngayKT = ?, ngayBD = ?, trangThai = ? WHERE maKM = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, khuyenMai.getTenKM());
            pstmt.setDate(2, khuyenMai.getNgayKT());
            pstmt.setDate(3, khuyenMai.getNgayBD());
            pstmt.setString(4, khuyenMai.getTrangThai());
            pstmt.setInt(5, khuyenMai.getMaKM());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int deleteDiscount(int maKM) {
        String sql = "DELETE FROM KhuyenMai WHERE maKM = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, maKM);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void printALLDiscount() {
        ArrayList<KhuyenMaiDTO> discountList = getAllDiscount();
        for (KhuyenMaiDTO khuyenMai : discountList) {
            System.out.println(khuyenMai.toString());
        }
    }

    public static void main(String[] args) {
        KhuyenMaiDTO km = getDiscountById(1);
        System.out.print(km.toString());
    }
}