package DTO;

public class ChiTietDonHangDTO {
    private int maDH;    // Mã đơn hàng (Khóa ngoại)
    private int maSP;    // Mã sản phẩm (Khóa ngoại)
    private int soLuong;    // Số lượng sản phẩm
    private String trangThai; // Trạng thái của chi tiết đơn hàng

    // Constructor không tham số
    public ChiTietDonHangDTO() {
    }

    // Constructor có tham số
    public ChiTietDonHangDTO(int maDH, int maSP, int soLuong, String trangThai) {
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

  
}
