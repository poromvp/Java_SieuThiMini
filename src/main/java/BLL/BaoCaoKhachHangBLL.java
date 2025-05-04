package BLL;

import java.util.ArrayList;

import DAL.BaoCaoKhachHangDAL;
import DTO.DonHangDTO;
import DTO.SearchKHDHDTO;
import DTO.SearchTheThanhVienDTO;
import DTO.TheThanhVienDTO;

public class BaoCaoKhachHangBLL {
    public static ArrayList<TheThanhVienDTO> TimTTV(SearchTheThanhVienDTO search) {
        return BaoCaoKhachHangDAL.TimTTV(search);
    }

    public static ArrayList<TheThanhVienDTO> TimTTVLock(SearchTheThanhVienDTO search) {
        return BaoCaoKhachHangDAL.TimTTVLock(search);
    }

    public static ArrayList<DonHangDTO> TimKHDH(SearchKHDHDTO search, int MaKH) {
        return BaoCaoKhachHangDAL.TimKHDH(search, MaKH);
    }
}
