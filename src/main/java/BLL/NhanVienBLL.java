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
        if (!validateNhanVien(nv)) {
            return false;
        }
        if(dal.checkSDT(nv.getSDT())){
            JOptionPane.showMessageDialog(null, "SDT này đã tồn tại");
            return false;
        }
        return dal.addNhanVien(nv);
    }
    
    public NhanVienDTO getNhanVienByMa(String maNV) {
        return dal.getNhanVienByMa(maNV);
    }
    

    public boolean updateNhanVien(NhanVienDTO nv) {
        if (!validateNhanVien(nv)) {
            return false;
        }
    
        return dal.updateNhanVien(nv);
    }
    

    public boolean deleteNhanVien(int maNV) {
        if (maNV <= 0) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ");
            return false;
        }

        return dal.deleteNhanVien(maNV);
    }
    public List<NhanVienDTO> searchNhanVien(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllNhanVien();
        }
        return dal.searchNhanVien(keyword);
    }
    
    public boolean isOver18(Date ngaySinh) {
        if (ngaySinh == null) {
            return false;
        }

        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(ngaySinh);

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH) ||
            (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
            age--; 
        }

        return age >= 18; 
    }
    public boolean validateNhanVien(NhanVienDTO nv) {
        if (nv.getTenNV() == null || nv.getTenNV().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Họ tên không được để trống");
            return false;
        }
        if (nv.getTenNV().matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Họ tên không được chứa số");
            return false;
        }
        if (nv.getCCCD() == null || !nv.getCCCD().matches("\\d{12}")) {
            JOptionPane.showMessageDialog(null, "CCCD phải là 12 chữ số");
            return false;
        }
        Date ngaySinh = (Date) nv.getNgaySinh();
        if (ngaySinh == null) {
            JOptionPane.showMessageDialog(null, "Ngày sinh không được để trống hoặc không hợp lệ");
            return false;
        }
        
    
        if (!isOver18(ngaySinh)) {
            JOptionPane.showMessageDialog(null, "Nhân viên phải đủ 18 tuổi");
            return false;
        }
            
        if (nv.getSDT() == null || !nv.getSDT().matches("0\\d{9}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu bằng 0 và có 10 chữ số");
            return false;
        }
    
        try {
            String luongString = String.valueOf(nv.getLuong());
            double luong = Double.parseDouble(luongString);
            if (luong < 0) {
                JOptionPane.showMessageDialog(null, "Lương không được âm");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Lương phải là một số hợp lệ");
            return false;
        }
        return true; 
    }
}
