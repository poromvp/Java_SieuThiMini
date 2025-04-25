package BLL;

import java.util.ArrayList;

import DAL.ChiTietKhuyenMaiDAL;
import DTO.ChiTietKhuyenMaiDTO;

public class ChiTietKhuyenMaiBLL {

    public static ArrayList<ChiTietKhuyenMaiDTO> getDiscountDetailsByDiscountId(int discountId) {
        if (discountId <= 0) {
            System.out.println("Mã khuyến mãi không hợp lệ!");
            return new ArrayList<>();
        }
        return ChiTietKhuyenMaiDAL.getAllDiscountDetailByDiscountId(discountId);
    }

    public static double getProductOnSaleToday(int productId) {
        double discountRate = ChiTietKhuyenMaiDAL.getProductOnSaleToday(productId);
        if (discountRate > 0) {
            return discountRate;
        }
        return 0;
    }

    public static boolean addDiscountDetail(ChiTietKhuyenMaiDTO detail) {
        if (detail == null || detail.getMaKM() <= 0 || detail.getMaSP() <= 0 || detail.getTiLeGiam() < 0) {
            System.out.println("Dữ liệu chi tiết khuyến mãi không hợp lệ!");
            return false;
        }
        int result = ChiTietKhuyenMaiDAL.insertDiscountDetail(detail);
        return result > 0;
    }

    public static boolean updateDiscountDetail(ChiTietKhuyenMaiDTO detail) {
        if (detail == null || detail.getMaKM() <= 0 || detail.getMaSP() <= 0 || detail.getTiLeGiam() < 0) {
            System.out.println("Dữ liệu chi tiết khuyến mãi không hợp lệ!");
            return false;
        }
        int result = ChiTietKhuyenMaiDAL.updateDiscountDetail(detail);
        return result > 0;
    }

    public static boolean deleteDiscountDetail(int maKM, int maSP) {
        if (maKM <= 0 || maSP <= 0) {
            System.out.println("Mã khuyến mãi hoặc mã sản phẩm không hợp lệ!");
            return false;
        }
        int result = ChiTietKhuyenMaiDAL.deleteDiscountDetail(maKM, maSP);
        return result > 0;
    }
    public static void main(String[] args) {
        System.out.println(getProductOnSaleToday(1));
    }
}