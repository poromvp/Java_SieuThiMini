package DTO;

public class ChiTietDH {
    private int maDH;    // Mã đơn hàng (Khóa ngoại)
    private int maSP;    // Mã sản phẩm (Khóa ngoại)
    private int soLuong;    // Số lượng sản phẩm
    private boolean trangThai; // Trạng thái của chi tiết đơn hàng

    // Constructor không tham số
    public ChiTietDH() {
    }

    // Constructor có tham số
    public ChiTietDH(int maDH, int maSP, int soLuong, boolean trangThai) {
        this.maDH = maDH;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

  
}
