package DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DTO.ChiTietDonHangDTO;
import JDBC.DBConnection;

public class ChiTietDonHangDAL {
    public static final String tableName = "ChiTietDH";

    // Thêm chi tiết đơn hàng
    public static boolean insert(ChiTietDonHangDTO chiTiet) {
        String sql = "INSERT INTO " + tableName + " (maDH, maSP, soLuong, trangThai) VALUES (?, ?, ?, ?)";
        return DBConnection.executeUpdate(sql, chiTiet.getMaDH(), chiTiet.getMaSP(), chiTiet.getSoLuong(), chiTiet.getTrangThai()) > 0;
    }

    // Cập nhật chi tiết đơn hàng
    public static boolean update(ChiTietDonHangDTO chiTiet) {
        String sql = "UPDATE " + tableName + " SET soLuong = ?, trangThai = ? WHERE maDH = ? AND maSP = ?";
        return DBConnection.executeUpdate(sql, chiTiet.getSoLuong(), chiTiet.getTrangThai(), chiTiet.getMaDH(), chiTiet.getMaSP()) > 0;
    }

    // Xóa chi tiết đơn hàng
    public static boolean delete(int maDH, int maSP) {
        String sql = "DELETE FROM " + tableName + " WHERE maDH = ? AND maSP = ?";
        return DBConnection.executeUpdate(sql, maDH, maSP) > 0;
    }

    // // Lấy chi tiết đơn hàng theo mã đơn hàng
    // public static List<ChiTietDonHangDTO> getByOrderId(int maDH) {
    //     List<ChiTietDonHangDTO> list = new ArrayList<>();
    //     String sql = "SELECT * FROM " + tableName + " WHERE maDH = ?";
    //     try (ResultSet rs = DBConnection.executeQuery(sql, maDH)) {
    //         while (rs.next()) {
    //             ChiTietDonHangDTO chiTiet = new ChiTietDonHangDTO(
    //                     rs.getInt("maDH"),
    //                     rs.getInt("maSP"),
    //                     rs.getInt("soLuong"),
    //                     rs.getString("trangThai")
    //             );
    //             list.add(chiTiet);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return list;
    // }

    public static List<ChiTietDonHangDTO> getByMaDH(int maDH) {
        List<ChiTietDonHangDTO> list = new ArrayList<>();
            String sql = "SELECT * FROM " + tableName + " WHERE maDH = ?";
            try (ResultSet rs = DBConnection.executeQuery(sql, maDH)) {
                while (rs.next()) {
                    ChiTietDonHangDTO chiTiet = new ChiTietDonHangDTO(
                            rs.getInt("maDH"),
                            rs.getInt("maSP"),
                            rs.getInt("soLuong"),
                            rs.getString("trangThai")
                    );
                    list.add(chiTiet);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
            }


}
