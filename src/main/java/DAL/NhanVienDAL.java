package DAL;

import DTO.NhanVienDTO;
import DTO.NhanVienDTO;
import JDBC.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAL {

    public List<NhanVienDTO> getAllNhanVien() {
        List<NhanVienDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien where TrangThai = 1";
        ResultSet rs = DBConnection.executeQuery(sql);
    
        try {
            while (rs != null && rs.next()) {
                NhanVienDTO nv = new NhanVienDTO();
                nv.setMaNV(rs.getInt("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSDT(rs.getString("SDT"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setLuong(rs.getDouble("Luong"));
                nv.setTrangThai(rs.getInt("TrangThai"));
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        return list;
    }
    public List<NhanVienDTO> getAllNhanVien0() {
        List<NhanVienDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien where trangThai = 0";
        ResultSet rs = DBConnection.executeQuery(sql);
    
        try {
            while (rs != null && rs.next()) {
                NhanVienDTO nv = new NhanVienDTO();
                nv.setMaNV(rs.getInt("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSDT(rs.getString("SDT"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setLuong(rs.getDouble("Luong"));
                nv.setTrangThai(rs.getInt("TrangThai"));
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        return list;
    }
    

    public boolean addNhanVien(NhanVienDTO nv) {
        String sql = "INSERT INTO NhanVien (TenNV, GioiTinh, NgaySinh, CCCD, DiaChi, SDT, Luong, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
        int rowsAffected = DBConnection.executeUpdate(sql,
                nv.getTenNV(),
                nv.getGioiTinh(),
                nv.getNgaySinh() != null ? new java.sql.Date(nv.getNgaySinh().getTime()) : null,
                nv.getCCCD(),
                nv.getDiaChi(),
                nv.getSDT(),
                nv.getLuong()
        );
        return rowsAffected > 0;
    }

    public boolean updateNhanVien(NhanVienDTO nv) {

        String sql = "UPDATE NhanVien SET TenNV = ?, GioiTinh = ?, NgaySinh = ?, CCCD = ?, DiaChi = ?, SDT = ?, Luong = ?, TrangThai = ? WHERE MaNV = ?";
        int rowsAffected = DBConnection.executeUpdate(sql,
                nv.getTenNV(),
                nv.getGioiTinh(),
                nv.getNgaySinh() != null ? new java.sql.Date(nv.getNgaySinh().getTime()) : null,
                nv.getCCCD(),
                nv.getDiaChi(),
                nv.getSDT(),
                nv.getLuong(),
                nv.getTrangThai(),
                nv.getMaNV()
        );
        return rowsAffected > 0;
    }

    public boolean deleteNhanVien(int maNV) {
        String sql = "UPDATE NHANVIEN SET TrangThai = 0 where maNV = ?";
        int rowsAffected = DBConnection.executeUpdate(sql, maNV);
        return rowsAffected > 0;
    }
    public boolean checkSDT(String SDT){
        String sql = "Select * from nhanvien where SDT=?";
        try {
            ResultSet rs = DBConnection.executeQuery(sql, SDT);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public NhanVienDTO getNhanVienByID(int maNV) {
        NhanVienDTO nhanVien = null;
        String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";
        ResultSet rs = DBConnection.executeQuery(sql, maNV);

        try {
            if (rs != null && rs.next()) {
                nhanVien = new NhanVienDTO();
                nhanVien.setMaNV(rs.getInt("MaNV"));
                nhanVien.setTenNV(rs.getString("TenNV"));
                nhanVien.setGioiTinh(rs.getString("GioiTinh"));
                nhanVien.setNgaySinh(rs.getDate("NgaySinh"));
                nhanVien.setDiaChi(rs.getString("DiaChi"));
                nhanVien.setSDT(rs.getString("SDT"));
                nhanVien.setCCCD(rs.getString("CCCD"));
                nhanVien.setLuong(rs.getDouble("Luong"));
                nhanVien.setTrangThai(rs.getInt("TrangThai"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return nhanVien;
    }

    public List<NhanVienDTO> searchNhanVien(String keyword) {
        List<NhanVienDTO> list = new ArrayList<>();
        // Thêm dấu ngoặc đơn để nhóm các điều kiện với OR
        String sql = "SELECT * FROM NhanVien WHERE (TenNV LIKE ? OR SDT LIKE ?) AND TrangThai = 1";
        ResultSet rs = DBConnection.executeQuery(sql, "%" + keyword + "%", "%" + keyword + "%");
    
        try {
            while (rs != null && rs.next()) {
                NhanVienDTO nv = new NhanVienDTO();
                nv.setMaNV(rs.getInt("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSDT(rs.getString("SDT"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setLuong(rs.getDouble("Luong"));
                nv.setTrangThai(rs.getInt("TrangThai"));
                list.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    
}
