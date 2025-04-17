package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.DiemTichLuyDTO;
import JDBC.DBConnection;

public class DiemTichLuyDAL {
    
    // Lấy tất cả dữ liệu từ bảng DiemTichLuy
    public static ArrayList<DiemTichLuyDTO> getAllDiemTichLuy() {
        ArrayList<DiemTichLuyDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM DiemTichLuy";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                DiemTichLuyDTO diem = new DiemTichLuyDTO(
                    rs.getInt("maDTL"),
                    rs.getInt("DiemTL"),
                    rs.getInt("TiLeGiam"),
                    rs.getInt("giamMax"),
                    rs.getString("TrangThai")
                );
                list.add(diem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy thông tin điểm tích lũy theo mã
    public static DiemTichLuyDTO getDiemTichLuyById(int maDTL) {
        String sql = "SELECT * FROM DiemTichLuy WHERE maDTL = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maDTL);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new DiemTichLuyDTO(
                    rs.getInt("maDTL"),
                    rs.getInt("DiemTL"),
                    rs.getInt("TiLeGiam"),
                    rs.getInt("giamMax"),
                    rs.getString("TrangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DiemTichLuyDTO getDiemTichLuyByDiem(int diemTL) {
        String sql = "SELECT * FROM DiemTichLuy WHERE diemTL <= ? order by diemTL desc limit 1";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, diemTL);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new DiemTichLuyDTO(
                    rs.getInt("maDTL"),
                    rs.getInt("DiemTL"),
                    rs.getInt("TiLeGiam"),
                    rs.getInt("giamMax"),
                    rs.getString("TrangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm mới điểm tích lũy
    public static int insertDiemTichLuy(DiemTichLuyDTO diem) {
        String sql = "INSERT INTO DiemTichLuy(maDTL, DiemTL, TiLeGiam, giamMax, TrangThai) VALUES (?, ?, ?, ?, ?)";
        return DBConnection.executeUpdate(sql, 
            diem.getMaDTL(), diem.getDiemTL(), diem.getTiLeGiam(), diem.getGiamMax(), diem.getTrangThai());
    }

    // Cập nhật điểm tích lũy
    public static int updateDiemTichLuy(DiemTichLuyDTO diem) {
        String sql = "UPDATE DiemTichLuy SET DiemTL = ?, TiLeGiam = ?, giamMax = ?, TrangThai = ? WHERE maDTL = ?";
        return DBConnection.executeUpdate(sql, 
            diem.getDiemTL(), diem.getTiLeGiam(), diem.getGiamMax(), diem.getTrangThai(), diem.getMaDTL());
    }

    // Xóa điểm tích lũy theo mã
    public static int deleteDiemTichLuy(int maDTL) {
        String sql = "DELETE FROM DiemTichLuy WHERE maDTL = ?";
        return DBConnection.executeUpdate(sql, maDTL);
    }

    // Kiểm tra xem mã điểm tích lũy có tồn tại không
    public static boolean exists(int maDTL) {
        String sql = "SELECT 1 FROM DiemTichLuy WHERE maDTL = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maDTL);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Nếu có dữ liệu trả về thì mã tồn tại
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // In danh sách điểm tích lũy
    public static void printAllDiemTichLuy() {
        ArrayList<DiemTichLuyDTO> list = getAllDiemTichLuy();
        for (DiemTichLuyDTO diem : list) {
            System.out.println(diem);
        }
    }

    // Test
    public static void main(String[] args) {
        System.out.println(getDiemTichLuyByDiem(1300).toString());
        // printAllDiemTichLuy();
        
       
    }
}

