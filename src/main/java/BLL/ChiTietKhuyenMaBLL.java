package BLL;

import java.util.ArrayList;

import DAL.ChiTietKhuyenMaiDAL;
import DTO.ChiTietKhuyenMaiDTO;

public class ChiTietKhuyenMaBLL {
    
      // Lấy danh sách chi tiết khuyến mãi theo mã khuyến mãi
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




}


