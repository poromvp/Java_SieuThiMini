package BLL;

import DTO.ChiTietDonHangDTO;
import DAL.*;

import java.util.*;

public class ChiTietDonHangBLL {

    // Thêm chi tiết đơn hàng
    public static int insertOrderDetail(ChiTietDonHangDTO chiTiet) {

        return ChiTietDonHangDAL.insert(chiTiet);
    }

    // Cập nhật chi tiết đơn hàng (số lượng, trạng thái)
    public static boolean updateOrderDetail(ChiTietDonHangDTO chiTiet) {
        return ChiTietDonHangDAL.update(chiTiet);
    }

    // Xóa chi tiết đơn hàng dựa vào mã đơn hàng và mã sản phẩm
    public static boolean deleteOrderDetail(int maDH, int maSP) {
        return ChiTietDonHangDAL.delete(maDH, maSP);
    }

    // Lấy danh sách chi tiết đơn hàng theo mã đơn hàng
    public static ArrayList<ChiTietDonHangDTO> getChiTietByMaDH(int maDH) {
        if (maDH <= 0) {
            System.err.println("Mã đơn hàng không hợp lệ: " + maDH);
            return new ArrayList<>(); // Trả về danh sách rỗng nếu mã không hợp lệ
        }
    
        return ChiTietDonHangDAL.getByMaDH(maDH);
    }

}
