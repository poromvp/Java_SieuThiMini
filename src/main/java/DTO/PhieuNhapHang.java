package DTO;

import java.util.Date;

public class PhieuNhapHang {
    private int maPNH;       // Mã phiếu nhập hàng (PK)
    private String tenPNH;   // Tên phiếu nhập hàng
    private int maNCC;       // Mã nhà cung cấp (FK)
    private int maNV;        // Mã nhân viên (FK)
    private Date ngayNhap;   // Ngày nhập hàng
    private String trangThai; // Trạng thái

    // Constructor không tham số
    public PhieuNhapHang() {}

    // Constructor đầy đủ tham số
    public PhieuNhapHang(int maPNH, String tenPNH, int maNCC, int maNV, Date ngayNhap, String trangThai) {
        this.maPNH = maPNH;
        this.tenPNH = tenPNH;
        this.maNCC = maNCC;
        this.maNV = maNV;
        this.ngayNhap = ngayNhap;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaPNH() {
        return maPNH;
    }

    public void setMaPNH(int maPNH) {
        this.maPNH = maPNH;
    }

    public String getTenPNH() {
        return tenPNH;
    }

    public void setTenPNH(String tenPNH) {
        this.tenPNH = tenPNH;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức hiển thị thông tin phiếu nhập hàng
    @Override
    public String toString() {
        return "PhieuNhapHang{" +
                "maPNH=" + maPNH +
                ", tenPNH='" + tenPNH + '\'' +
                ", maNCC=" + maNCC +
                ", maNV=" + maNV +
                ", ngayNhap=" + ngayNhap +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
