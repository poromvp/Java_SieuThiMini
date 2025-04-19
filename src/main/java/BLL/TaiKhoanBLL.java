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
        if (!taiKhoanDAL.kiemTraMaNV(tk.getMaNV())) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (taiKhoanDAL.kiemTraExist(tk.getMaNV())) {
            JOptionPane.showMessageDialog(null, "Nhân viên đã có tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (taiKhoanDAL.kiemTraName(tk.getTenTK())) {
            JOptionPane.showMessageDialog(null, "Tên tài khoản đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (taiKhoanDAL.kiemTraGmail(tk.getGmail())) {
            JOptionPane.showMessageDialog(null, "Gmail đã được sử dụng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!isValidData(tk)) {
            return false;
        }

        return taiKhoanDAL.addTaiKhoan(tk);
    }

    public boolean updateTaiKhoan(TaiKhoanDTO tk) {
        if (!isValidData(tk)) {
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

    // -------------------- VALIDATION --------------------
    private boolean isValidData(TaiKhoanDTO tk) {
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

        if (tk.getMatKhau().matches(".*[àáảãạâầấẩẫậăằắẳẵặèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵ].*")) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không được chứa dấu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (tk.getMatKhau().length() < 6) {
            JOptionPane.showMessageDialog(null, "Mật khẩu phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return isValidGmail(tk.getGmail());
    }

    private boolean isValidGmail(String gmail) {
        if (gmail == null || gmail.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Gmail không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (gmail.matches(".*[àáảãạâầấẩẫậăằắẳẵặèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵ].*")) {
            JOptionPane.showMessageDialog(null, "Gmail không được chứa dấu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!gmail.matches(emailRegex)) {
            JOptionPane.showMessageDialog(null, "Gmail không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        

        return true;
    }
}
