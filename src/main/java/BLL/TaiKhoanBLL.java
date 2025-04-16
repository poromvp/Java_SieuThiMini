package BLL;

import DAL.TaiKhoanDAL;
import DTO.TaiKhoanDTO;

import javax.swing.JOptionPane;
import java.util.List;

public class TaiKhoanBLL {
    private TaiKhoanDAL taiKhoanDAL;

    public TaiKhoanBLL() {
        taiKhoanDAL = new TaiKhoanDAL();
    }

    public List<TaiKhoanDTO> getAllTaiKhoan() {
        return taiKhoanDAL.getAllTaiKhoan();
    }
    public boolean addTaiKhoan(TaiKhoanDTO tk) {
        if (tk.getMaNV() <= 0) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tk.getTenTK() == null || tk.getTenTK().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên tài khoản không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tk.getMatKhau() == null || tk.getMatKhau().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isValidGmail(tk.getGmail())) {
            JOptionPane.showMessageDialog(null, "Gmail không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return taiKhoanDAL.addTaiKhoan(tk);
    }

    public boolean updateTaiKhoan(TaiKhoanDTO tk) {
        if (tk.getTenTK() == null || tk.getTenTK().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên tài khoản không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (tk.getMatKhau() == null || tk.getMatKhau().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isValidGmail(tk.getGmail())) {
            JOptionPane.showMessageDialog(null, "Gmail không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return taiKhoanDAL.updateTaiKhoan(tk);
    }

    public boolean deleteTaiKhoan(int maNV) {
        if (maNV <= 0) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return taiKhoanDAL.deleteTaiKhoan(maNV);
    }

    public List<TaiKhoanDTO> searchTaiKhoan(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllTaiKhoan();
        }
        return taiKhoanDAL.searchTaiKhoan(keyword);
    }

    private boolean isValidGmail(String gmail) {
        if (gmail == null || gmail.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return gmail.matches(emailRegex);
    }
}