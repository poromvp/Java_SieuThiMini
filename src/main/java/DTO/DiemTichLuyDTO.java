package DTO;



public class DiemTichLuyDTO {
    private int maDTL;
    private int diemTL;
    private int tiLeGiam;
    private int giamMax;
    private String trangThai;

    // Constructor mặc định
    public DiemTichLuyDTO() {}

    // Constructor có tham số
    public DiemTichLuyDTO(int maDTL, int diemTL, int tiLeGiam, int giamMax, String trangThai) {
        this.maDTL = maDTL;
        this.diemTL = diemTL;
        this.tiLeGiam = tiLeGiam;
        this.giamMax = giamMax;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaDTL() {
        return maDTL;
    }

    public void setMaDTL(int maDTL) {
        this.maDTL = maDTL;
    }

    public int getDiemTL() {
        return diemTL;
    }

    public void setDiemTL(int diemTL) {
        this.diemTL = diemTL;
    }

    public double getTiLeGiam() {
        return tiLeGiam;
    }

    public void setTiLeGiam(int tiLeGiam) {
        this.tiLeGiam = tiLeGiam;
    }

    public double getGiamMax() {
        return giamMax;
    }

    public void setGiamMax(int giamMax) {
        this.giamMax = giamMax;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức toString() để dễ debug
    @Override
    public String toString() {
        return "DiemTichLuyDTO{" +
                "maDTL=" + maDTL +
                ", diemTL=" + diemTL +
                ", tiLeGiam=" + tiLeGiam +
                ", giamMax=" + giamMax +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
