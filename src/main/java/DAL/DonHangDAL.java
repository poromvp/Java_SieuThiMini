package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.DonHangDTO;
import JDBC.DBConnection;

public class DonHangDAL {

    // Lấy tất cả đơn hàng từ CSDL
    public static ArrayList<DonHangDTO> getAllOrder() {
        ArrayList<DonHangDTO> dsDonHang = new ArrayList<>();
        String sql = "SELECT * FROM DonHang";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DonHangDTO dh = new DonHangDTO(
                    rs.getInt("maDH"),
                    rs.getInt("maKH"),
                    rs.getInt("maKM"),
                    rs.getInt("maNV"),
                    rs.getString("PTTToan"),
                    rs.getDate("NgayTT"),
                    rs.getString("trangThai")
                );
                dsDonHang.add(dh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDonHang;
    }

    // Lấy đơn hàng theo ID
    public static DonHangDTO getOrderById(int maDH) {
        String sql = "SELECT * FROM DonHang WHERE maDH = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maDH);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new DonHangDTO(
                    rs.getInt("maDH"),
                    rs.getInt("maKH"),
                    rs.getInt("maKM"),
                    rs.getInt("maNV"),
                    rs.getString("PTTToan"),
                    rs.getDate("NgayTT"),
                    rs.getString("trangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm đơn hàng mới
    public static int insertOrder(DonHangDTO dh) {
        String sql = "INSERT INTO DonHang(maKH, maKM, maNV, NgayTT, trangThai) VALUES (?, ?, ?, ?, ?)";
        return DBConnection.executeUpdate(sql, dh.getMaKH(), dh.getMaKM(), dh.getMaNV(), dh.getNgayTT(), dh.getTrangThai());
    }

    // Cập nhật đơn hàng
    public static int updateOrder(DonHangDTO dh) {
        String sql = "UPDATE DonHang SET maKH=?, maKM=?, maNV=?, NgayTT=?, trangThai=? WHERE maDH=?";
        return DBConnection.executeUpdate(sql, dh.getMaKH(), dh.getMaKM(), dh.getMaNV(), dh.getNgayTT(), dh.getTrangThai(), dh.getMaDH());
    }

    // Xóa đơn hàng
    public static int deleteOrder(int maDH) {
        String sql = "DELETE FROM DonHang WHERE maDH=?";
        return DBConnection.executeUpdate(sql, maDH);
    }

    public static int countOrder() {
        String sql = "SELECT COUNT(maDH) FROM DonHang";
        int count = 0;
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            if (rs.next()) {
                count = rs.getInt(1); // Lấy giá trị COUNT từ cột đầu tiên
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    


    public static void printAllOrder() {
        ArrayList<DonHangDTO> orderList = getAllOrder(); 
        for (DonHangDTO donHang : orderList) { 
            System.out.println( donHang.toString());
        }
    }
    

    public static void main(String[] args) {
        
        System.out.println(countOrder());
    }
}
