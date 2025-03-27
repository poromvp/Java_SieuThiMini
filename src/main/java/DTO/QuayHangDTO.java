package DTO;


public class QuayHangDTO {
    private int maQH;       // Mã quầy hàng (PK)
    private int maLSP;      // Mã loại sản phẩm (FK)
    private String tenQH;   // Tên quầy hàng
    private String trangThai; // Trạng thái (VD: "Hoạt động", "Không hoạt động")

    // Constructor không tham số
    public QuayHangDTO() {}

    // Constructor đầy đủ tham số
    public QuayHangDTO(int maQH, int maLSP, String tenQH, String trangThai) {
        this.maQH = maQH;
        this.maLSP = maLSP;
        this.tenQH = tenQH;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaQH() {
        return maQH;
    }

    public void setMaQH(int maQH) {
        this.maQH = maQH;
    }

    public int getMaLSP() {
        return maLSP;
    }

    public void setMaLSP(int maLSP) {
        this.maLSP = maLSP;
    }

    public String getTenQH() {
        return tenQH;
    }

    public void setTenQH(String tenQH) {
        this.tenQH = tenQH;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức hiển thị thông tin quầy hàng
    @Override
    public String toString() {
        return "QuayHang{" +
                "maQH=" + maQH +
                ", maLSP=" + maLSP +
                ", tenQH='" + tenQH + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
