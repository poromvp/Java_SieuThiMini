package DAL;

import DTO.PhieuNhapHangDTO;
import JDBC.DBConnection;
import org.bytedeco.opencv.presets.opencv_core;

import java.sql.*;
import java.util.ArrayList;

public class NhapHangDAL {

    // Lấy tất cả phiếu nhập hàng
    public ArrayList<PhieuNhapHangDTO> getAllPhieuNhapHang(){
        ArrayList<PhieuNhapHangDTO> phieuNhapList = new ArrayList<>();
        String query = "SELECT * FROM PhieuNhapHang WHERE TrangThai != 'DELETE'";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)){
            while(rs.next()){
                PhieuNhapHangDTO phieuNhap = new PhieuNhapHangDTO(
                        rs.getInt("MaPNH"),
                        rs.getString("TenPNH"),
                        rs.getInt("MaNCC"),
                        rs.getInt("MaNV"),
                        rs.getDate("NgayNhap"),
                        rs.getString("TrangThai")
                );
                phieuNhapList.add(phieuNhap);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return phieuNhapList;
    }

    public boolean insertPhieuNhapHang(PhieuNhapHangDTO phieuNhap){
        String query ="INSERT INTO PhieuNhapHang (TenPNH,MaNCC,MaNV,NgayNhap,TrangThai) VALUES (?,?,?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, phieuNhap.getTenPNH());
            pstmt.setInt(2, phieuNhap.getMaNCC());
            pstmt.setInt(3, phieuNhap.getMaNV());
            pstmt.setDate(4, new java.sql.Date(phieuNhap.getNgayNhap().getTime()));
            pstmt.setString(5, phieuNhap.getTrangThai());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        phieuNhap.setMaPNH(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePhieuNhapHang(PhieuNhapHangDTO phieuNhap){
        String query = "UPDATE PhieuNhapHang SET TenPNH = ?, MaNCC = ?, MaNV = ?, NgayNhap = ?, TrangThai = ? WHERE MaPNH = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, phieuNhap.getTenPNH());
            pstmt.setInt(2, phieuNhap.getMaNCC());
            pstmt.setInt(3, phieuNhap.getMaNV());
            pstmt.setDate(4, new java.sql.Date(phieuNhap.getNgayNhap().getTime()));
            pstmt.setString(5, phieuNhap.getTrangThai());
            pstmt.setInt(6, phieuNhap.getMaPNH());

            return pstmt.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePhieuNhapHang(int maPNH){
        String query = "UPDATE PhieuNhapHang SET TrangThai = 'DELETED' WHERE MaPNH = ?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, maPNH);
            return pstmt.executeUpdate() > 0;
        } catch ( SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public PhieuNhapHangDTO getPhieuNhapHangById(int maPNH) {
        String query = "SELECT * FROM PhieuNhapHang WHERE MaPNH = ? AND TrangThai != 'DELETED'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, maPNH);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new PhieuNhapHangDTO(
                            rs.getInt("MaPNH"),
                            rs.getString("TenPNH"),
                            rs.getInt("MaNCC"),
                            rs.getInt("MaNV"),
                            rs.getDate("NgayNhap"),
                            rs.getString("TrangThai")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
