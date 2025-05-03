package BLL;

import java.sql.Date;
import java.util.ArrayList;

import DAL.BaoCaoKhoTongHopDAL;
import DTO.PhieuNhapHangDTO;
import DTO.SanPhamDTO;
import DTO.SearchBanChayDTO;
import DTO.SearchLoHangDTO;
import DTO.SearchTonKhoDTO;

public class BaoCaoKhoTongHopBLL {
    public static ArrayList<SearchBanChayDTO> getAllSPBanChay(Date from, Date to) {
        return BaoCaoKhoTongHopDAL.getAllSPBanChay(from, to);
    }

    public static ArrayList<SearchBanChayDTO> TimBanChay(SearchBanChayDTO search, Date from, Date to) {
        return BaoCaoKhoTongHopDAL.TimBanChay(search, from, to);
    }

    public static ArrayList<SanPhamDTO> TimTonKho(SearchTonKhoDTO search){
        return BaoCaoKhoTongHopDAL.TimTonKho(search);
    }

    public static ArrayList<PhieuNhapHangDTO> TimLoHang(SearchLoHangDTO search) {
        return BaoCaoKhoTongHopDAL.TimLoHang(search);
    }
    public static String tongSoHangNhapTrongThang() {
        return BaoCaoKhoTongHopDAL.tongSoHangNhapTrongThang();
    }
}
