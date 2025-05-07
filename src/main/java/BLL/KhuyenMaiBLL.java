package BLL;

import java.util.ArrayList;

import java.sql.Date;

import DAL.KhuyenMaiDAL;
import DTO.KhuyenMaiDTO;

public class KhuyenMaiBLL {


    public static ArrayList<KhuyenMaiDTO> getFilteredDiscounts(
        String idOrTenKM, String trangThai, String idOrTenSP,
        Date ngayBD, Date ngayKT, String sapXep, String cot) {

        if(trangThai.equalsIgnoreCase("tất cả")){
            trangThai = null;
        }
        
        String sort = sapXep.equalsIgnoreCase("tăng dần") ? "ASC" : "DESC";
        String column = "khuyenmai.MaKM"; 

        switch (cot) {
            case "Tên khuyến mãi":
                column = "khuyenmai.TenKM";
                break;
            case "Ngày bắt đầu":
                column = "khuyenmai.NgayBD";
                break;
            case "Ngày kết thúc":
                column = "khuyenmai.NgayKT";
                break;
        }

        return KhuyenMaiDAL.getFilterDiscount(
            idOrTenKM, trangThai, idOrTenSP, ngayBD, ngayKT, sort, column);
    }

    public static boolean kiemTraHopLeNgay(Date ngayBD, Date ngayKT){
        return KhuyenMaiDAL.kiemTraHopLeNgay(ngayBD, ngayKT);
    }

    public static boolean kiemTraHopLeNgayCuaKM(int maKM,Date ngayBD, Date ngayKT){
        return KhuyenMaiDAL.kiemTraHopLeNgayCuaKM(maKM, ngayBD, ngayKT);
    }
    
    

    public static ArrayList<KhuyenMaiDTO> getAllDiscounts() {
        return KhuyenMaiDAL.getAllDiscount();
    }

    public static KhuyenMaiDTO getDiscountById(int id) {
        return KhuyenMaiDAL.getDiscountById(id);
    }

    public static int addDiscount(KhuyenMaiDTO khuyenMai) {
        if (khuyenMai == null) {
            System.out.println("Dữ liệu khuyến mãi không hợp lệ!");
            return 0;
        }

        if (khuyenMai.getTenKM().trim().isEmpty()) {
            System.out.println("Tên khuyến mãi không được để trống!");
            return 0;
        }

        int result = KhuyenMaiDAL.insertDiscount(khuyenMai);
        return result ;
    }

    public static boolean updateDiscount(KhuyenMaiDTO khuyenMai) {
        if (khuyenMai == null || khuyenMai.getMaKM() <= 0 || khuyenMai.getTenKM().trim().isEmpty()) {
            System.out.println("Dữ liệu khuyến mãi không hợp lệ!");
            return false;
        }
        int result = KhuyenMaiDAL.updateDiscount(khuyenMai);
        return result > 0;
    }

    public static boolean deleteDiscount(int maKM) {
        if (maKM <= 0) {
            System.out.println("Mã khuyến mãi không hợp lệ!");
            return false;
        }
        int result = KhuyenMaiDAL.deleteDiscount(maKM);
        return result > 0;
    }

    public static KhuyenMaiDTO getDiscountToday() {
        return KhuyenMaiDAL.getDiscountToday();
    }

    public static void printAllDiscounts() {
        ArrayList<KhuyenMaiDTO> discounts = getAllDiscounts();
        for (KhuyenMaiDTO km : discounts) {
            System.out.println(km.toString());
        }
    }

    public static void main(String[] args) {
        // printAllDiscounts();
        System.out.println(getDiscountToday().toString());
    }
}