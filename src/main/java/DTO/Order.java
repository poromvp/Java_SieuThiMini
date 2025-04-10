package DTO;

import java.sql.Date;

public class Order {
    private int orderId;
    private int memberId;
    private int discountId;
    private int employeeId;
    private String payMethod;
    private Date payDate;
    private String status;

    // Constructor
    public Order(int orderId, int memberId, int discountId, int employeeId, String payMethod, Date payDate, String status) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.discountId = discountId;
        this.employeeId = employeeId;
        this.payMethod = payMethod;
        this.payDate = payDate;
        this.status = status;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ToString method for debugging
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", memberId=" + memberId +
                ", discountId=" + discountId +
                ", employeeId=" + employeeId +
                ", payMethod='" + payMethod + '\'' +
                ", payDate=" + payDate +
                ", status='" + status + '\'' +
                '}';
    }
}
