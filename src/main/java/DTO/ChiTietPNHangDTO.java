package DTO;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class ChiTietPNHangDTO {
    private int maPNH;     // Mã phiếu nhập hàng (PK)
    private int maSP;      // Mã sản phẩm (PK)
    private int maLH;      // Mã lô hàng (PK)
    private int soLuong;   // Số lượng sản phẩm nhập
    private double giaNhap; // Giá nhập của sản phẩm
    private String trangThai; // Trạng thái của chi tiết nhập hàng
    private Date ngaySX, ngayHH;

    // Constructor không tham số
    public ChiTietPNHangDTO() {}

    // Constructor đầy đủ tham số
    public ChiTietPNHangDTO( int maPNH, int maSP, int maLH, int soLuong, double giaNhap, String trangThai) {
        this.maPNH = maPNH;
        this.maSP = maSP;
        this.maLH = maLH;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.trangThai = trangThai;
    }

    public String check(Date ngaySX, Date ngayHH) {
        Date today = new Date(System.currentTimeMillis());

        if (today.after(ngayHH)) {
            return "Hết hạn sử dụng";
        }

        long diffInMillis = ngayHH.getTime() - today.getTime();
        long daysDiff = TimeUnit.MILLISECONDS.toDays(diffInMillis);

        if (daysDiff <= 30) {
            return "Sắp hết hạn";
        }

        return "Còn hạn";
    }

    public ChiTietPNHangDTO(int maPNH, int maSP, int maLH, int soLuong, double giaNhap, String trangThai,
            Date ngaySX, Date ngayHH) {
        this.maPNH = maPNH;
        this.maSP = maSP;
        this.maLH = maLH;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.trangThai = trangThai;
        this.ngaySX = ngaySX;
        this.ngayHH = ngayHH;
    }

    // Getter và Setter

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

    public int getMaPNH() {
        return maPNH;
    }

    public void setMaPNH(int maPNH) {
        this.maPNH = maPNH;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaLH() {
        return maLH;
    }

    public void setMaLH(int maLH) {
        this.maLH = maLH;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức hiển thị thông tin chi tiết nhập hàng
    @Override
    public String toString() {
        return "ChiTietPNH{" +
                ", maPNH=" + maPNH +
                ", maSP=" + maSP +
                ", maLH=" + maLH +
                ", soLuong=" + soLuong +
                ", giaNhap=" + giaNhap +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
