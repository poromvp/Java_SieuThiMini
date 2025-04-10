package BLL;



import java.lang.reflect.Array;
import java.util.ArrayList;

import DAL.DonHangDAL;
import DTO.DonHangDTO;

public class DonHangBLL {
    
    // Lấy danh sách tất cả đơn hàng từ DAL
    public static ArrayList<DonHangDTO> getAllOrders() {
        return DonHangDAL.getAllOrder();
    }

    public static int countOrder() {
        return DonHangDAL.countOrder();
    }

    // Lấy đơn hàng theo ID (có thể kiểm tra ID hợp lệ)
    public static DonHangDTO getOrderById(int maDH) {
        if (maDH <= 0) {
            System.out.println("ID đơn hàng không hợp lệ!");
            return null;
        }
        return DonHangDAL.getOrderById(maDH);
    }

    // Thêm đơn hàng mới (có kiểm tra dữ liệu)
    public static boolean insertOrder(DonHangDTO dh) {
        if (dh == null) {
            System.out.println("Dữ liệu đơn hàng không hợp lệ!");
            return false;
        }

        if (dh.getNgayTT() == null || dh.getTrangThai().isEmpty()) {
            System.out.println("Thông tin đơn hàng không đầy đủ!");
            return false;
        }

        int result = DonHangDAL.insertOrder(dh);
        return result > 0;
    }

    // Cập nhật đơn hàng (có kiểm tra dữ liệu)
    public static boolean updateOrder(DonHangDTO dh) {
        if (dh == null || dh.getMaDH() <= 0) {
            System.out.println("Dữ liệu đơn hàng không hợp lệ!");
            return false;
        }

        int result = DonHangDAL.updateOrder(dh);
        return result > 0;
    }

    // Xóa đơn hàng theo ID (có kiểm tra ID hợp lệ)
    public static boolean deleteOrder(int maDH) {
        if (maDH <= 0) {
            System.out.println("ID đơn hàng không hợp lệ!");
            return false;
        }

        int result = DonHangDAL.deleteOrder(maDH);
        return result > 0;
    }

    // In tất cả đơn hàng (gọi từ DAL)
    public static void printAllOrders() {
        ArrayList<DonHangDTO> orders = getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("Không có đơn hàng nào!");
        } else {
            for (DonHangDTO dh : orders) {
                System.out.println(dh.toString());
            }
        }
    }

    public static ArrayList<ArrayList<Object>> getFindSortOrder(
        ArrayList<String> whereConditions,
        ArrayList<String> having,
        ArrayList<Object> param,
        String orderBy,
        String orderType,
        Integer limit
    ) {
        // Bảo vệ null
        if (whereConditions == null) whereConditions = new ArrayList<>();
        if (having == null) having = new ArrayList<>();
        if (param == null) param = new ArrayList<>();
    
        // Kiểm tra sắp xếp
        if (orderBy == null ) {
            throw new IllegalArgumentException("Trường orderBy không hợp lệ");
        }
    
        if (orderType != null && !orderType.equalsIgnoreCase("ASC") && !orderType.equalsIgnoreCase("DESC")) {
            throw new IllegalArgumentException("Thứ tự sắp xếp chỉ được là ASC hoặc DESC");
        }
    
        // Kiểm tra param đủ số lượng cho WHERE và HAVING
        int expectedParams = whereConditions.size() + having.size();
        if (param.size() < expectedParams) {
            throw new IllegalArgumentException("Số lượng tham số không khớp với điều kiện truy vấn");
        }
    
        return DonHangDAL.getFindSortOrder(
            whereConditions,
            having,
            param,
            orderBy,
            orderType,
            limit
        );
    }
    

    public static void main(String[] args) {
        printAllOrders();
    }
}
