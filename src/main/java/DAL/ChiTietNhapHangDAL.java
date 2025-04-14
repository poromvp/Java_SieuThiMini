package DAL;

import DTO.ChiTietPNHangDTO;
import JDBC.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class ChiTietNhapHangDAL {
    public ArrayList<ChiTietPNHangDTO> getAllChiTietNhapHang() {
        ArrayList<ChiTietPNHangDTO> chiTietPNHList = new ArrayList<>();
        String query = "SELECT * FROM ChiTietPNH WHERE TrangThai = 'ACTIVE'";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ChiTietPNHangDTO chiTiet = new ChiTietPNHangDTO(
                        rs.getInt("MaCTPNH"),
                        rs.getInt("MaPNH"),
                        rs.getInt("MaSP"),
                        rs.getInt("MaLH"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("GiaNhap"),
                        rs.getString("TrangThai")
                );
                chiTietPNHList.add(chiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chiTietPNHList;
    }
    public boolean insertChiTietNhapHang(ChiTietPNHangDTO chiTiet) {
        String query = "INSERT INTO ChiTietPNH (MaPNH, MaSP, MaLH, SoLuong, GiaNhap, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, chiTiet.getMaPNH());
            pstmt.setInt(2, chiTiet.getMaSP());
            pstmt.setInt(3, chiTiet.getMaLH());
            pstmt.setInt(4, chiTiet.getSoLuong());
            pstmt.setDouble(5, chiTiet.getGiaNhap());
            pstmt.setString(6, chiTiet.getTrangThai());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateChiTietNhapHang(ChiTietPNHangDTO chiTiet) {
        String query = "UPDATE ChiTietPNH SET MaPNH = ?, MaSP = ?, MaLH = ?, SoLuong = ?, GiaNhap = ?, TrangThai = ? WHERE MaCTPNH = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, chiTiet.getMaPNH());
            pstmt.setInt(2, chiTiet.getMaSP());
            pstmt.setInt(3, chiTiet.getMaLH());
            pstmt.setInt(4, chiTiet.getSoLuong());
            pstmt.setDouble(5, chiTiet.getGiaNhap());
            pstmt.setString(6, chiTiet.getTrangThai());
            pstmt.setInt(7, chiTiet.getMaCTPNH());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteChiTietNhapHang(int maCTPNH) {
        String query = "UPDATE ChiTietPNH SET TrangThai = 'INACTIVE' WHERE MaCTPNH = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, maCTPNH);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<ChiTietPNHangDTO> getChiTietByMaPNH(int maPNH) {
        ArrayList<ChiTietPNHangDTO> chiTietList = new ArrayList<>();
        String query = "SELECT * FROM ChiTietPNH WHERE MaPNH = ? AND TrangThai = 'ACTIVE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, maPNH);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ChiTietPNHangDTO chiTiet = new ChiTietPNHangDTO(
                            rs.getInt("MaCTPNH"),
                            rs.getInt("MaPNH"),
                            rs.getInt("MaSP"),
                            rs.getInt("MaLH"),
                            rs.getInt("SoLuong"),
                            rs.getDouble("GiaNhap"),
                            rs.getString("TrangThai")
                    );
                    chiTietList.add(chiTiet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chiTietList;
    }
}
