package BLL;


import java.util.ArrayList;

import DAL.KhuyenMaiDAL;
import DTO.KhuyenMaiDTO;

public class KhuyenMaiBLL {

    public static ArrayList<KhuyenMaiDTO> getAllDiscounts() {
        return KhuyenMaiDAL.getAllDiscount();
    }

    public static KhuyenMaiDTO getDiscountById(int id) {
        return KhuyenMaiDAL.getDiscountById(id);
    }

    public static boolean addDiscount(KhuyenMaiDTO khuyenMai) {
        if (khuyenMai == null) {
            System.out.println("Dữ liệu khuyến mãi không hợp lệ!");
            return false;
        }

        if (khuyenMai.getTenKM().trim().isEmpty()) {
            System.out.println("Tên khuyến mãi không được để trống!");
            return false;
        }

        

        int result = KhuyenMaiDAL.insertDiscount(khuyenMai);
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
        printAllDiscounts();

        KhuyenMaiDTO newDiscount = new KhuyenMaiDTO(10, "Khuyến mãi Tết", null, null, "Đang diễn ra");
        boolean added = addDiscount(newDiscount);
        if (added) {
            System.out.println("Thêm khuyến mãi thành công!");
        } else {
            System.out.println("Thêm khuyến mãi thất bại!");
        }
    }
}
