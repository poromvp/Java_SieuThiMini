package DAL;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DTO.Order;
import JDBC.DBConnection1;


public class OrderDAL extends DBConnection1 {

    // Lấy danh sách tất cả đơn hàng
    public List<Order> getAllOrders() throws SQLException {
        this.buildQueryParams(Map.of("select", "*", "where", "", "params", new ArrayList<>()));
        List<Map<String, Object>> results = this.select();

        List<Order> orders = new ArrayList<>();
        for (Map<String, Object> row : results) {
            orders.add(mapRowToOrder(row));
        }
        return orders;
    }

    // Lấy thông tin đơn hàng theo ID
    public Order getOrderById(int orderId) throws SQLException {
        this.buildQueryParams(Map.of("select", "*", "where", "orderId = ?", "params", List.of(orderId)));
        Map<String, Object> row = this.selectOne();
        return row.isEmpty() ? null : mapRowToOrder(row);
    }

    // Thêm một đơn hàng mới
    public int insertOrder(Order order) throws SQLException {
        this.buildQueryParams(Map.of("field", Map.of(
            "memberId", order.getMemberId(),
            "discountId", order.getDiscountId(),
            "employeeId", order.getEmployeeId(),
            "payMethod", order.getPayMethod(),
            "payDate", order.getPayDate(),
            "status", order.getStatus()
        )));
        return this.insert();
    }

    // Cập nhật thông tin đơn hàng
    public int updateOrder(Order order) throws SQLException {
        this.buildQueryParams(Map.of(
            "value", Map.of(
                "memberId", order.getMemberId(),
                "discountId", order.getDiscountId(),
                "employeeId", order.getEmployeeId(),
                "payMethod", order.getPayMethod(),
                "payDate", order.getPayDate(),
                "status", order.getStatus()
            ),
            "where", "orderId = ?",
            "params", List.of(order.getOrderId())
        ));
        return this.update();
    }

    // Xóa đơn hàng theo ID
    public int deleteOrder(int orderId) throws SQLException {
        this.buildQueryParams(Map.of("where", "orderId = ?", "params", List.of(orderId)));
        return this.delete();
    }

    // Chuyển đổi Map sang đối tượng Order
    private Order mapRowToOrder(Map<String, Object> row) {
        return new Order(
            (int) row.get("orderId"),
            (int) row.get("memberId"),
            (int) row.get("discountId"),
            (int) row.get("employeeId"),
            (String) row.get("payMethod"),
            (Date) row.get("payDate"),
            (String) row.get("status")
        );
    }
}
