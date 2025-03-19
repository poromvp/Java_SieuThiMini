package DTO;


public class DiscountDetail {
    private int discountId;
    private int productId;
    private double discount;
    private String status;

    // Constructor
    public DiscountDetail(int discountId, int productId, double discount, String status) {
        this.discountId = discountId;
        this.productId = productId;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    // ToString để debug
    @Override
    public String toString() {
        return "DiscountDetail{" +
                "discountId=" + discountId +
                ", productId=" + productId +
                ", discount=" + discount +
                ", status='" + status + '\'' +
                '}';
    }
}
