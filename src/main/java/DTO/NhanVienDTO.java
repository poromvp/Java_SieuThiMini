package DTO;

import java.sql.Date;

public class NhanVienDTO {
    private int maNV;       
    private String tenNV;   
    private Date ngaySinh;  
    private String gioiTinh; 
    private String diaChi;  
    private String cccd;    
    private String chucVu;  
    private String sdt;     
    private double luong;   
    private String trangThai;

    public NhanVienDTO() {}

    public NhanVienDTO(int maNV, String tenNV, Date ngaySinh, String gioiTinh, String diaChi, 
                       String chucVu, String sdt, String cccd, double luong, String trangThai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
        this.sdt = sdt;
        this.cccd = cccd;
        this.luong = luong;
        this.trangThai = trangThai;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "NhanVienDTO{" +
                "maNV=" + maNV +
                ", tenNV='" + tenNV + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", cccd='" + cccd + '\'' +
                ", chucVu='" + chucVu + '\'' +
                ", sdt='" + sdt + '\'' +
                ", luong=" + luong +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
