package BLL;

import java.sql.Connection;
import java.util.List;

import DAL.NhanVienDAO;
import DTO.NhanVienDTO;

public class NhanVienBLL {
    private NhanVienDAO nvDAO;

    public NhanVienBLL(Connection conn) {
        this.nvDAO = new NhanVienDAO(conn);
    }

    public List<NhanVienDTO> getAllNhanVien() {
        return nvDAO.getAllNhanVien();
    }

    public boolean insertNhanVien(NhanVienDTO nv) {
        if (!isValidNhanVien(nv, false)) {
            return false;
        }
        return nvDAO.insertNhanVien(nv);
    }

    public boolean updateNhanVien(NhanVienDTO nv) {
        if (!isValidNhanVien(nv, true)) {
            return false;
        }
        return nvDAO.updateNhanVien(nv);
    }

    public boolean deleteNhanVien(int maNV) {
        if (maNV <= 0) {
            return false;
        }
        return nvDAO.deleteNhanVien(maNV);
    }

    private boolean isValidNhanVien(NhanVienDTO nv, boolean isUpdate) {
        if (nv == null) return false;

        if (isUpdate && nv.getMaNV() <= 0) return false;

        if (nv.getTenNV() == null || nv.getTenNV().trim().isEmpty()) return false;
        if (nv.getGioiTinh() == null || nv.getGioiTinh().isEmpty()) return false;
        if (nv.getNgaySinh() == null) return false;
        if (nv.getSdt() == null || nv.getSdt().isEmpty()) return false;
        if (nv.getCccd() == null || nv.getCccd().isEmpty()) return false;
        return true;
    }
}
