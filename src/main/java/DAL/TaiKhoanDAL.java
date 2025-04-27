package DAL;

import JDBC.DBConnection;
import DTO.TaiKhoanDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hpsf.Array;

public class TaiKhoanDAL {
    public List<TaiKhoanDTO> getAllTaiKhoan() {
        List<TaiKhoanDTO> taiKhoanList = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";
        ResultSet rs = DBConnection.executeQuery(sql);
        
        try {
            while (rs != null && rs.next()) {
                TaiKhoanDTO tk = new TaiKhoanDTO();
                tk.setMaNV(rs.getInt("MaNV"));
                tk.setTenTK(rs.getString("TenTK"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setQuyen(rs.getString("Quyen"));
                tk.setGmail(rs.getString("Gmail"));
                tk.setTrangThai(rs.getString("TrangThai"));
                taiKhoanList.add(tk);
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
        return taiKhoanList;
    }

    public boolean addTaiKhoan(TaiKhoanDTO tk) {
        String sql = "INSERT INTO TaiKhoan (MaNV, TenTK, MatKhau, Quyen, Gmail, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = DBConnection.executeUpdate(sql, 
            tk.getMaNV(), 
            tk.getTenTK(), 
            tk.getMatKhau(), 
            tk.getQuyen(), 
            tk.getGmail(), 
            tk.getTrangThai()
        );
        return rowsAffected > 0;
    }
    public boolean kiemTraMaNV (int maNV){
        String sql = "SELECT * FROM NhanVien WHERE MANV = ?";
        try (ResultSet rs = DBConnection.executeQuery(sql,maNV)){
            return rs.next();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean kiemTraExist (int maNV){
        String sql = "Select * from TaiKhoan WHERE MANV = ?";
        try(ResultSet rs = DBConnection.executeQuery(sql, maNV)){
            return rs.next();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean kiemTraName(String name){
        String sql = "SELECT * From TaiKhoan where TenTK = ?";
        try (ResultSet rs = DBConnection.executeQuery(sql, name)){
            return rs.next();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }
    public boolean kiemTraGmail(String gmail) {
        String sql = "SELECT * FROM TaiKhoan WHERE Gmail = ?";
        try (ResultSet rs = DBConnection.executeQuery(sql, gmail)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateTaiKhoan(TaiKhoanDTO tk) {
        String sql = "UPDATE TaiKhoan SET TenTK = ?, MatKhau = ?, Quyen = ?, Gmail = ?, TrangThai = ? WHERE MaNV = ?";
        int rowsAffected = DBConnection.executeUpdate(sql, 
            tk.getTenTK(), 
            tk.getMatKhau(), 
            tk.getQuyen(), 
            tk.getGmail(), 
            tk.getTrangThai(), 
            tk.getMaNV()
        );
        return rowsAffected > 0;
    }

    public boolean deleteTaiKhoan(int maNV) {
        String sql = "DELETE FROM TaiKhoan WHERE MaNV = ?";
        int rowsAffected = DBConnection.executeUpdate(sql, maNV);
        return rowsAffected > 0;
    }
    public List<TaiKhoanDTO> searchTaiKhoan(String keyword) {
        List<TaiKhoanDTO> taiKhoanList = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan WHERE TenTK LIKE ? OR Gmail LIKE ?";
        ResultSet rs = DBConnection.executeQuery(sql, "%" + keyword + "%", "%" + keyword + "%");
        
        try {
            while (rs != null && rs.next()) {
                TaiKhoanDTO tk = new TaiKhoanDTO();
                tk.setMaNV(rs.getInt("MaNV"));
                tk.setTenTK(rs.getString("TenTK"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setQuyen(rs.getString("Quyen"));
                tk.setGmail(rs.getString("Gmail"));
                tk.setTrangThai(rs.getString("TrangThai"));
                taiKhoanList.add(tk);
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
        return taiKhoanList;
    }
    public boolean loginCheck (String tenTK, String mk){
        String sql = "SELECT * FROM TAIKHOAN WHERE TenTK = ? AND MATKHAU = ?";
        try {
            ResultSet rs = DBConnection.executeQuery(sql, tenTK,mk);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getQuyenByMaNV(String maNV) {
        String sql = "SELECT Quyen FROM TAIKHOAN WHERE MANV = ?";
        try {
            ResultSet rs = DBConnection.executeQuery(sql, maNV);
            if (rs.next()) {
                return rs.getString("Quyen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public TaiKhoanDTO getTaiKhoanDTO (String tenTK){
        try {
            String sql ="SELECT * FROM TAIKHOAN WHERE tenTK = ?";
            ResultSet rs = DBConnection.executeQuery(sql, tenTK);
            
            if (rs.next()){
                TaiKhoanDTO tk = new TaiKhoanDTO();
                tk.setMaNV(rs.getInt("maNV"));
                tk.setTenTK(rs.getString("TenTK"));
                tk.setMatKhau(rs.getString("MATKHAU"));
                tk.setQuyen(rs.getString("Quyen"));
                tk.setGmail(rs.getString("Gmail"));
                tk.setTrangThai(rs.getString("TrangThai"));
                
                return tk;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}