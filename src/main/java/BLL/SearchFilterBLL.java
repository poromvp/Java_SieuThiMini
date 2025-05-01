package BLL;

import java.util.ArrayList;

import DAL.SearchFilterDAL;
import DTO.DonHangDTO;
import DTO.SearchFilterDTO;

public class SearchFilterBLL {
    public static ArrayList<DonHangDTO> timKiem_SapXepDonHang(SearchFilterDTO filter) {
        if(filter != null){
            return SearchFilterDAL.timKiem_SapXepDonHang(filter);
        }
        return  new ArrayList<DonHangDTO>();
    }
}
