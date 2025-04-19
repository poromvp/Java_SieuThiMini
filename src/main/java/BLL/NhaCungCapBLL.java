package BLL;

import DAL.NhaCungCapDAL;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

public class NhaCungCapBLL {
    private NhaCungCapDAL nhaCungCapDAL = new NhaCungCapDAL();

    public ArrayList<NhaCungCapDTO> getList() {
        return nhaCungCapDAL.getList();
    }

    public boolean add(NhaCungCapDTO ncc) {
        if (ncc.getTenNCC() == null || ncc.getTenNCC().trim().isEmpty() ||
                ncc.getSdt() == null || ncc.getSdt().trim().isEmpty() ||
                ncc.getDiaChi() == null || ncc.getDiaChi().trim().isEmpty()) {
            return false;
        }
        return nhaCungCapDAL.add(ncc);
    }

    public boolean update(NhaCungCapDTO ncc) {
        if (ncc.getTenNCC() == null || ncc.getTenNCC().trim().isEmpty() ||
                ncc.getSdt() == null || ncc.getSdt().trim().isEmpty() ||
                ncc.getDiaChi() == null || ncc.getDiaChi().trim().isEmpty()) {
            return false;
        }
        return nhaCungCapDAL.update(ncc);
    }

    public boolean delete(int maNCC) {
        return nhaCungCapDAL.delete(maNCC);
    }

    public NhaCungCapDTO getNhaCungCap(int maNCC) {
        for (NhaCungCapDTO ncc : getList()) {
            if (ncc.getMaNCC() == maNCC) {
                return ncc;
            }
        }
        return null;
    }

    public ArrayList<NhaCungCapDTO> search(String keyword) {
        return nhaCungCapDAL.search(keyword);
    }

    public String[] getArrTenNCC() {
        ArrayList<NhaCungCapDTO> list = getList();
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).getTenNCC();
        }
        return result;
    }
}
