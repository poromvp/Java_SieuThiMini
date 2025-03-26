package DTO;

public class ChiTietKM {
    private int maKM;  // Mã khuyến mãi (Khóa ngoại)
    private int maSP;  // Mã sản phẩm (Khóa ngoại)
    private double tiLeGiam; // Tỉ lệ giảm giá
    private boolean trangThai; // Trạng thái khuyến mãi

    // Constructor không tham số
    public ChiTietKM() {
    }

    // Constructor có tham số
    public ChiTietKM(int maKM, int maSP, double tiLeGiam, boolean trangThai) {
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

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
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
