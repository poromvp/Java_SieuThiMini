package BLL;

import DAL.NhanVienDAL;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;

import javax.swing.JOptionPane;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class NhanVienBLL {
    private NhanVienDAL dal;

    public NhanVienBLL() {
        dal = new NhanVienDAL();
    }

    public List<NhanVienDTO> getAllNhanVien() {
        return dal.getAllNhanVien(); 
    }
    public List<NhanVienDTO> getAllNhanVien0() {
        return dal.getAllNhanVien0(); 
    }

    public boolean addNhanVien(NhanVienDTO nv) {

        return dal.addNhanVien(nv);
    }
    public boolean checkSDT(String sdt){
        return dal.checkSDT(sdt);
    }
    public boolean checkCCCD(String cccd){
        return dal.checkCCCD(cccd);
    }
    public NhanVienDTO getNhanVienByMa(String maNV) {
        return dal.getNhanVienByMa(maNV);
    }
    

    public boolean updateNhanVien(NhanVienDTO nv) {
        return dal.updateNhanVien(nv);
    }
    

    public boolean deleteNhanVien(int maNV) {

        return dal.deleteNhanVien(maNV);
    }
    public List<NhanVienDTO> searchNhanVien(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllNhanVien();
        }
        return dal.searchNhanVien(keyword);
    }
    public String getNameNV (String maNV){
        return dal.getNameNhanVien(maNV);
    }

    
}
