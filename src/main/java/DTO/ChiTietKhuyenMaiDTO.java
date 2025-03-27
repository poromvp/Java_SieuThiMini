package DTO;

public class ChiTietKhuyenMaiDTO {
    private int maKM;  // Mã khuyến mãi (Khóa ngoại)
    private int maSP;  // Mã sản phẩm (Khóa ngoại)
    private double tiLeGiam; // Tỉ lệ giảm giá
    private String trangThai; // Trạng thái khuyến mãi

    // Constructor không tham số
    public ChiTietKhuyenMaiDTO() {
    }

    // Constructor có tham số
    public ChiTietKhuyenMaiDTO(int maKM, int maSP, double tiLeGiam, String trangThai) {
        this.maKM = maKM;
        this.maSP = maSP;
        this.tiLeGiam = tiLeGiam;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaKM() {
        return maKM;
    }

    public void setMaKM(int maKM) {
        this.maKM = maKM;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public double getTiLeGiam() {
        return tiLeGiam;
    }

    public void setTiLeGiam(double tiLeGiam) {
        this.tiLeGiam = tiLeGiam;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức hiển thị thông tin
    @Override
    public String toString() {
        return "ChiTietKM{" +
                "maKM='" + maKM + '\'' +
                ", maSP='" + maSP + '\'' +
                ", tiLeGiam=" + tiLeGiam +
                ", trangThai=" + trangThai +
                '}';
    }
}
