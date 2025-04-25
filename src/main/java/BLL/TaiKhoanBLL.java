package BLL;

import DAL.TaiKhoanDAL;
import DTO.TaiKhoanDTO;
import JDBC.DBConnection;

import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaiKhoanBLL {
    private TaiKhoanDAL taiKhoanDAL;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
    
    public TaiKhoanBLL() {
        taiKhoanDAL = new TaiKhoanDAL();
    }

    public List<TaiKhoanDTO> getAllTaiKhoan() {
        return taiKhoanDAL.getAllTaiKhoan();
    }

    public boolean addTaiKhoan(TaiKhoanDTO tk) {
        return taiKhoanDAL.addTaiKhoan(tk);
    }
    public boolean kiemTraMaNV(int maNV){
        return taiKhoanDAL.kiemTraMaNV(maNV) ;
    }
    public boolean kiemTraExist(int maNV){
        return taiKhoanDAL.kiemTraExist(maNV);
    }
    public boolean kiemTraName(String ten){
        return taiKhoanDAL.kiemTraName(ten);
    }
    public boolean kiemTraGmail(String gmail){
        return taiKhoanDAL.kiemTraGmail(gmail);
    }
    public boolean updateTaiKhoan(TaiKhoanDTO tk) {

        return taiKhoanDAL.updateTaiKhoan(tk);
    }

    public boolean deleteTaiKhoan(int maNV) {

        return taiKhoanDAL.deleteTaiKhoan(maNV);
    }

    public List<TaiKhoanDTO> searchTaiKhoan(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllTaiKhoan();
        }
        return taiKhoanDAL.searchTaiKhoan(keyword);
    }

    public boolean loginCheck (String maNV, String mk){
        return taiKhoanDAL.loginCheck(maNV,mk);
    }
    public String quyenCheck(String maNV) {
        return taiKhoanDAL.getQuyenByMaNV(maNV);  
    }
    public String getQuyenNV(String maNV){
        return taiKhoanDAL.getQuyenByMaNV(maNV);
    }
   
    
}
