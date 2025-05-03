package BLL;

import java.sql.Date;
import java.util.ArrayList;

import DAL.BaoCaoNhanVienDAL;
import DTO.DonHangDTO;
import DTO.NhanVienDTO;
import DTO.SearchNVDHDTO;
import DTO.SearchNhanVienDTO;

public class BaoCaoNhanVienBLL {
    public static ArrayList<NhanVienDTO> getTopNhanVienByDoanhSo(Date from, Date to) {
        return BaoCaoNhanVienDAL.getTopNhanVienByDoanhSo(from, to);
    }

    public static ArrayList<NhanVienDTO> TimTotNhat(SearchNhanVienDTO search, Date from, Date to) {
        return BaoCaoNhanVienDAL.TimTotNhat(search, from, to);
    }

    public static ArrayList<NhanVienDTO> TimNhanVien(SearchNhanVienDTO search) {
        return BaoCaoNhanVienDAL.TimNhanVien(search);
    }

    public static ArrayList<DonHangDTO> TimNVDH(SearchNVDHDTO search, int MaNV) {
        return BaoCaoNhanVienDAL.TimNVDH(search, MaNV);
    }
}
