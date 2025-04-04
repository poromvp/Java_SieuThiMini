package DTO;

import java.sql.Date;
import java.time.LocalDate;

public class Discount {
    private int discountId;
    private String discountName;
    private Date startDate;
    private Date endDate;
    private double discount;
    private String status;

    // Constructor
    public Discount(int discountId, String discountName, Date startDate, Date endDate, double discount, String status) {
        this.discountId = discountId;
        this.discountName = discountName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
        this.status = status;
    }

    // Getters và Setters
    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isValid() {
        LocalDate currentDate = LocalDate.now();
        LocalDate start = startDate.toLocalDate(); // Chuyển Date thành LocalDate
        LocalDate end = endDate.toLocalDate();     // Chuyển Date thành LocalDate
    
        return currentDate.isAfter(start) && currentDate.isBefore(end);
    }
    

    // ToString method để debug
    @Override
    public String toString() {
        return "Discount{" +
                "discountId=" + discountId +
                ", discountName='" + discountName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", discount=" + discount +
                ", status='" + status + '\'' +
                '}';
    }
}
