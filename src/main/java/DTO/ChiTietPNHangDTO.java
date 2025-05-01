package DTO;


import java.util.Date;

public class ChiTietPNHangDTO {
    private int maCTPNH;   // Mã chi tiết phiếu nhập hàng (PK)
    private int maPNH;     // Mã phiếu nhập hàng (FK)
    private int maSP;      // Mã sản phẩm (FK)
    private int maLH;      // Mã lô hàng (FK)
    private int soLuong;   // Số lượng sản phẩm nhập
    private double giaNhap; // Giá nhập của sản phẩm
    private Date nsx;
    private Date hsd;
    private String trangThai; // Trạng thái của chi tiết nhập hàng

    // Constructor không tham số
    public ChiTietPNHangDTO() {}

    // Constructor đầy đủ tham số
    public ChiTietPNHangDTO(int maCTPNH, int maPNH, int maSP, int maLH, int soLuong, double giaNhap,Date nsx,Date hsd, String trangThai) {
        this.maCTPNH = maCTPNH;
        this.maPNH = maPNH;
        this.maSP = maSP;
        this.maLH = maLH;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.nsx = nsx;
        this.hsd=hsd;
        this.trangThai = trangThai;
    }


    public int getMaCTPNH() {
        return maCTPNH;
    }

    public void setMaCTPNH(int maCTPNH) {
        this.maCTPNH = maCTPNH;
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

    public Date getNsx() {
        return nsx;
    }

    public void setNsx(Date nsx) {
        this.nsx = nsx;
    }

    public Date getHsd() {
        return hsd;
    }

    public void setHsd(Date hsd) {
        this.hsd = hsd;
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
                "maCTPNH=" + maCTPNH +
                ", maPNH=" + maPNH +
                ", maSP=" + maSP +
                ", maLH=" + maLH +
                ", soLuong=" + soLuong +
                ", giaNhap=" + giaNhap +
                ", nsx=" + nsx +
                ", hsd=" + hsd +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
