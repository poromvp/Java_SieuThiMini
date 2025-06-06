package BLL;

import DAL.TaiKhoanDAL;
import DTO.TaiKhoanDTO;
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
    public boolean kiemTraName(String tenTK, int maNV) {
        for (TaiKhoanDTO tk : taiKhoanDAL.getAllTaiKhoan()) {
            if (tk.getTenTK().equals(tenTK) && tk.getMaNV() != maNV) {
                return true;
            }
        }
        return false;
    }

    public boolean kiemTraGmail(String gmail, int maNV) {
        List<TaiKhoanDTO> danhSach = taiKhoanDAL.getAllTaiKhoan();
        for (TaiKhoanDTO tk : danhSach) {
            if (tk.getGmail().equals(gmail) && tk.getMaNV() != maNV) {
                return true; 
            }
        }
        return false; 
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
    public String getQuyenNV(String maNV){
        return taiKhoanDAL.getQuyenByMaNV(maNV);
    }
    public TaiKhoanDTO getTaiKhoanDTO (String tenTK){
        return taiKhoanDAL.getTaiKhoanDTO(tenTK);
    }
    public TaiKhoanDTO getTaiKhoanById (int id){
        return taiKhoanDAL.getTaiKhoanById(id);
    }
    public boolean taiKhoanExist(String tentk){
        return taiKhoanDAL.taiKhoanExist(tentk);
    }
    
}
 