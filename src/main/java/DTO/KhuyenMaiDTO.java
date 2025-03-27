package DTO;

import java.sql.Date;

public class KhuyenMaiDTO {
    private int maKM;
    private String tenKM;
    private Date ngayKT;
    private Date ngayBD;
    private double tileGiam;
    private String trangThai;

    // Constructor không tham số
    public KhuyenMaiDTO() {
    }

    // Constructor có tham số
    public KhuyenMaiDTO(int maKM, String tenKM, Date ngayKT, Date ngayBD, double tileGiam, String trangThai) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.ngayKT = ngayKT;
        this.ngayBD = ngayBD;
        this.tileGiam = tileGiam;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaKM() {
        return maKM;
    }

    public void setMaKM(int maKM) {
        this.maKM = maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public double getTileGiam() {
        return tileGiam;
    }

    public void setTileGiam(double tileGiam) {
        this.tileGiam = tileGiam;
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
        return "KhuyenMai{" +
                "maKM='" + maKM + '\'' +
                ", tenKM='" + tenKM + '\'' +
                ", ngayKT=" + ngayKT +
                ", ngayBD=" + ngayBD +
                ", tileGiam=" + tileGiam +
                ", trangThai=" + trangThai +
                '}';
    }
}
