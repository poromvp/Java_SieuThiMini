package DAL;

import DTO.NhanVienDTO;
import DTO.NhanVienDTO;
import JDBC.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.zxing.Result;

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
    public static List<String> getAllKhuVuc() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT DiaChi FROM NhanVien WHERE TrangThai = 1";
        ResultSet rs = DBConnection.executeQuery(sql);
        
        try {
            while (rs != null && rs.next()) {
                String khuVuc = rs.getString("DiaChi");
                list.add(khuVuc);
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
        String sql = "INSERT INTO NhanVien (TenNV, GioiTinh, NgaySinh, CCCD, DiaChi, SDT, Luong, Image, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1)";
        int rowsAffected = DBConnection.executeUpdate(sql,
                nv.getTenNV(),
                nv.getGioiTinh(),
                nv.getNgaySinh() != null ? new java.sql.Date(nv.getNgaySinh().getTime()) : null,
                nv.getCCCD(),
                nv.getDiaChi(),
                nv.getSDT(),
                nv.getLuong(),
                nv.getImage()
        );
        return rowsAffected > 0;
    }

    public boolean updateNhanVien(NhanVienDTO nv) {

        String sql = "UPDATE NhanVien SET TenNV = ?, GioiTinh = ?, NgaySinh = ?, CCCD = ?, DiaChi = ?, SDT = ?, Luong = ?,Image = ?, TrangThai = ? WHERE MaNV = ?";
        int rowsAffected = DBConnection.executeUpdate(sql,
                nv.getTenNV(),
                nv.getGioiTinh(),
                nv.getNgaySinh() != null ? new java.sql.Date(nv.getNgaySinh().getTime()) : null,
                nv.getCCCD(),
                nv.getDiaChi(),
                nv.getSDT(),
                nv.getLuong(),
                nv.getImage(),
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
    public boolean checkCCCD(String CCCD){
        String sql = "Select * from nhanvien where CCCD=?";
        try {
            ResultSet rs = DBConnection.executeQuery(sql, CCCD);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public NhanVienDTO getNhanVienByMa(String maNV) {
        try {
            String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";
            ResultSet rs = DBConnection.executeQuery(sql,maNV);
            
            if (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO();
                nv.setMaNV(rs.getInt("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSDT(rs.getString("SDT"));
                nv.setLuong(rs.getDouble("Luong"));
                nv.setTrangThai(rs.getInt("TrangThai"));
                
                nv.setImage(rs.getString("Image"));
                
                return nv;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public List<NhanVienDTO> searchNhanVien(String keyword) {
        List<NhanVienDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE (TenNV LIKE ? OR SDT LIKE ? ) AND TrangThai = 1";
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
    public String getNameNhanVien(String maNV){
        String sql = "SELECT  NHANVIEN.TENNV " + 
                        "FROM TAIKHOAN " + 
                        "JOIN NHANVIEN ON TAIKHOAN.MANV = NHANVIEN.MANV " + 
                        "WHERE TAIKHOAN.MANV = ?";
        try {
            ResultSet rs = DBConnection.executeQuery(sql, maNV);
            if(rs.next()){
                return rs.getString("TenNV");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public NhanVienDTO getNhanVienById(int maNV) {
        try {
            String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";
            ResultSet rs = DBConnection.executeQuery(sql,maNV);

            if (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO();
                nv.setMaNV(rs.getInt("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSDT(rs.getString("SDT"));
                nv.setLuong(rs.getDouble("Luong"));
                nv.setTrangThai(rs.getInt("TrangThai"));

                nv.setImage(rs.getString("Image"));

                return nv;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
