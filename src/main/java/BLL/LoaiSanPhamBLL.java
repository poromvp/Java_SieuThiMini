package BLL;

import DAL.LoaiSanPhamDAL;
import DTO.LoaiSanPhamDTO;
import java.util.ArrayList;

public class LoaiSanPhamBLL {
    private LoaiSanPhamDAL loaiSanPhamDAL = new LoaiSanPhamDAL();

    public ArrayList<LoaiSanPhamDTO> getList() {
        return loaiSanPhamDAL.getList();
    }

    public boolean add(LoaiSanPhamDTO lsp) {
        if (lsp.getTenLoaiSP() == null || lsp.getTenLoaiSP().trim().isEmpty()) {
            return false;
        }
        return loaiSanPhamDAL.add(lsp);
    }

    public boolean update(LoaiSanPhamDTO lsp) {
        if (lsp.getTenLoaiSP() == null || lsp.getTenLoaiSP().trim().isEmpty()) {
            return false;
        }
        return loaiSanPhamDAL.update(lsp);
    }

    public boolean delete(int maLSP) {
        return loaiSanPhamDAL.delete(maLSP);
    }

    public LoaiSanPhamDTO getLoaiSanPham(int maLSP) {
        for (LoaiSanPhamDTO lsp : getList()) {
            if (lsp.getMaLSP() == maLSP) {
                return lsp;
            }
        }
        return null;
    }

    public ArrayList<LoaiSanPhamDTO> search(String keyword) {
        return loaiSanPhamDAL.search(keyword);
    }

    public String[] getArrTenLoaiSP() {
        ArrayList<LoaiSanPhamDTO> list = getList();
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).getTenLoaiSP();
        }
        return result;
    }
}
