package DTO;

import java.sql.Date;

public class KhuyenMai {
    private String maKM;
    private String tenKM;
    private Date ngayKT;
    private Date ngayHH;
    private boolean trangThai;

    // Constructor không tham số
    public KhuyenMai() {
    }

    // Constructor có tham số
    public KhuyenMai(String maKM, String tenKM, Date ngayKT, Date ngayHH, boolean trangThai) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.ngayKT = ngayKT;
        this.ngayHH = ngayHH;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
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

    public Date getNgayHH() {
        return ngayHH;
    }

    public void setNgayHH(Date ngayHH) {
        this.ngayHH = ngayHH;
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
        return "KhuyenMai{" +
                "maKM='" + maKM + '\'' +
                ", tenKM='" + tenKM + '\'' +
                ", ngayKT=" + ngayKT +
                ", ngayHH=" + ngayHH +
                ", trangThai=" + trangThai +
                '}';
    }
}
