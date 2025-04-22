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
        printAllDiscounts();
    }
}