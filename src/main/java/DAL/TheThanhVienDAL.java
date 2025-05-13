package DAL;


import DTO.TheThanhVienDTO;
import JDBC.DBConnection;

import java.sql.*;
import java.util.ArrayList;


public class TheThanhVienDAL {
    // Lấy danh sách tất cả thẻ thành viên
    public static ArrayList<TheThanhVienDTO> getAllMembers() {
        ArrayList<TheThanhVienDTO> dsTheThanhVien = new ArrayList<>();
        String sql = "SELECT * FROM TheThanhVien";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                TheThanhVienDTO member = new TheThanhVienDTO(
                    rs.getInt("maTV"),
                    rs.getString("tenTV"),
                    rs.getDate("ngaySinh"),
                    rs.getString("diaChi"),
                    rs.getInt("diemTL"),
                    rs.getString("sdt"),
                    rs.getDate("ngayBD"),
                    rs.getDate("ngayKT"),
                    rs.getString("tenAnh"),
                    rs.getString("trangThai")
                );
                dsTheThanhVien.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsTheThanhVien;
    }

    // Lấy danh sách tất cả thẻ thành viên ACTIVE
    public static ArrayList<TheThanhVienDTO> getAllMembersACTIVE() {
        ArrayList<TheThanhVienDTO> dsTheThanhVien = new ArrayList<>();
        String sql = "SELECT * FROM TheThanhVien WHERE trangthai = 'ACTIVE'";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                TheThanhVienDTO member = new TheThanhVienDTO(
                    rs.getInt("maTV"),
                    rs.getString("tenTV"),
                    rs.getDate("ngaySinh"),
                    rs.getString("diaChi"),
                    rs.getInt("diemTL"),
                    rs.getString("sdt"),
                    rs.getDate("ngayBD"),
                    rs.getDate("ngayKT"),
                    rs.getString("tenAnh"),
                    rs.getString("trangThai")
                );
                dsTheThanhVien.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsTheThanhVien;
    }

    // Lấy danh sách tất cả thẻ thành viên INACTIVE
    public static ArrayList<TheThanhVienDTO> getAllMembersINACTIVE() {
        ArrayList<TheThanhVienDTO> dsTheThanhVien = new ArrayList<>();
        String sql = "SELECT * FROM TheThanhVien WHERE trangthai = 'INACTIVE'";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                TheThanhVienDTO member = new TheThanhVienDTO(
                    rs.getInt("maTV"),
                    rs.getString("tenTV"),
                    rs.getDate("ngaySinh"),
                    rs.getString("diaChi"),
                    rs.getInt("diemTL"),
                    rs.getString("sdt"),
                    rs.getDate("ngayBD"),
                    rs.getDate("ngayKT"),
                    rs.getString("tenAnh"),
                    rs.getString("trangThai")
                );
                dsTheThanhVien.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsTheThanhVien;
    }

    // Lấy thông tin thẻ thành viên theo mã
    public static TheThanhVienDTO getMemberById(int maTV) {
        String sql = "SELECT * FROM TheThanhVien WHERE maTV = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, maTV);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new TheThanhVienDTO(
                    rs.getInt("maTV"),
                    rs.getString("tenTV"),
                    rs.getDate("ngaySinh"),
                    rs.getString("diaChi"),
                    rs.getInt("diemTL"),
                    rs.getString("sdt"),
                    rs.getDate("ngayBD"),
                    rs.getDate("ngayKT"),
                    rs.getString("tenAnh"),
                    rs.getString("trangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TheThanhVienDTO getMemberByPhone(String phone) {
        String sql = "SELECT * FROM TheThanhVien WHERE SDT = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, phone);
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                return new TheThanhVienDTO(
                    rs.getInt("maTV"),
                    rs.getString("tenTV"),
                    rs.getDate("ngaySinh"),
                    rs.getString("diaChi"),
                    rs.getInt("diemTL"),
                    rs.getString("sdt"),
                    rs.getDate("ngayBD"),
                    rs.getDate("ngayKT"),
                    rs.getString("tenAnh"),
                    rs.getString("trangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    


    // Thêm thẻ thành viên mới
    public static int insertMember(TheThanhVienDTO member) {
        String sql = "INSERT INTO TheThanhVien(tenTV, ngaySinh, diaChi, diemTL, sdt, ngayBD, ngayKT, tenAnh, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return DBConnection.executeUpdate(sql, 
                member.getTenTV(),
                member.getNgaySinh(),
                member.getDiaChi(),
                member.getDiemTL(),
                member.getSdt(),
                member.getNgayBD(),
                member.getNgayKT(),
                member.getTenAnh(),
                member.getTrangThai()
        );
    }

    // Cập nhật thông tin thẻ thành viên
    public static int updateMember(TheThanhVienDTO member) {
        String sql = "UPDATE TheThanhVien SET tenTV=?, ngaySinh=?, diaChi=?, diemTL=?, sdt=?, ngayBD=?, ngayKT=?, tenAnh=?, trangThai=? WHERE maTV=?";
        return DBConnection.executeUpdate(sql, 
                member.getTenTV(),
                member.getNgaySinh(),
                member.getDiaChi(),
                member.getDiemTL(),
                member.getSdt(),
                member.getNgayBD(),
                member.getNgayKT(),
                member.getTenAnh(),
                member.getTrangThai(),
                member.getMaTV()
        );
    }

    // Khóa thẻ thành viên theo mã
    public static int deleteMember(int maTV) {
        String sql = "UPDATE TheThanhVien SET trangthai = 'INACTIVE' WHERE maTV=?";
        return DBConnection.executeUpdate(sql, maTV);
    }

    // Mở khóa thẻ thành viên theo mã
    public static int UndeleteMember(int maTV) {
        String sql = "UPDATE TheThanhVien SET trangthai = 'ACTIVE' WHERE maTV=?";
        return DBConnection.executeUpdate(sql, maTV);
    }

    // Mở khóa thẻ thành viên theo mã và tăng hạn thẻ thêm nếu thẻ đã hết hạn
    public static int UndeleteMemberandPlus(int maTV) {
        String sql = "UPDATE TheThanhVien SET trangthai = 'ACTIVE', NgayKT = DATE_ADD(NgayKT, INTERVAL 2 YEAR) WHERE maTV=?";
        return DBConnection.executeUpdate(sql, maTV);
    }

    // Hiển thị danh sách tất cả thành viên
    public static void printAllMembers() {
        ArrayList<TheThanhVienDTO> memberList = getAllMembers();
        for (TheThanhVienDTO member : memberList) {
            System.out.println(member.toString());
        }
    }

    public static void main(String[] args) {
       System.out.println( getMemberByPhone("0987456123").toString());
       System.out.println( getMemberByPhone("0987456123").toString());
       System.out.println( getMemberByPhone("0987456123").toString());
    }
}