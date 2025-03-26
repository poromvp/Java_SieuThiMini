package DTO;

public class ChiTietDH {
    private String maDH;    // Mã đơn hàng (Khóa ngoại)
    private String maSP;    // Mã sản phẩm (Khóa ngoại)
    private int soLuong;    // Số lượng sản phẩm
    private boolean trangThai; // Trạng thái của chi tiết đơn hàng

    // Constructor không tham số
    public ChiTietDH() {
    }

    // Constructor có tham số
    public ChiTietDH(String maDH, String maSP, int soLuong, boolean trangThai) {
        this.maDH = maDH;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
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

    // Phương thức hiển thị thông tin
    @Override
    public String toString() {
        return "ChiTietDH{" +
                "maDH='" + maDH + '\'' +
                ", maSP='" + maSP + '\'' +
                ", soLuong=" + soLuong +
                ", trangThai=" + trangThai +
                '}';
    }
}
