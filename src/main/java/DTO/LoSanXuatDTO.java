package DTO;

import java.util.Date;

public class LoSanXuatDTO {
    private int maLH;        // Mã lô hàng (PK)
    private String diaChi;   // Địa chỉ sản xuất
    private Date ngaySX;     // Ngày sản xuất
    private Date ngayHH;     // Ngày hết hạn

    // Constructor không tham số
    public LoSanXuatDTO() {}

    // Constructor đầy đủ tham số
    public LoSanXuatDTO(int maLH, String diaChi, Date ngaySX, Date ngayHH) {
        this.maLH = maLH;
        this.diaChi = diaChi;
        this.ngaySX = ngaySX;
        this.ngayHH = ngayHH;
    }

    // Getter và Setter
    public int getMaLH() {
        return maLH;
    }

    public void setMaLH(int maLH) {
        this.maLH = maLH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgaySX() {
        return ngaySX;
    }

    public void setNgaySX(Date ngaySX) {
        this.ngaySX = ngaySX;
    }

    public Date getNgayHH() {
        return ngayHH;
    }

    public void setNgayHH(Date ngayHH) {
        this.ngayHH = ngayHH;
    }

    // Phương thức hiển thị thông tin lô sản xuất
    @Override
    public String toString() {
        return "LoSX{" +
                "maLH=" + maLH +
                ", diaChi='" + diaChi + '\'' +
                ", ngaySX=" + ngaySX +
                ", ngayHH=" + ngayHH +
                '}';
    }
}
