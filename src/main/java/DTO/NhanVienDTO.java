package DTO;

import java.util.Date;

public class NhanVienDTO {
    private int maNV;
    private String tenNV;
    private String gioiTinh;
    private Date ngaySinh;
    private String cccd;
    private String diaChi;
    private String sdt;
    private String chucVu;
    private double luong;
    private int trangThai;

    public NhanVienDTO() {}

    public NhanVienDTO(int maNV, String tenNV, Date ngaySinh, String gioiTinh, String diaChi,
                       String sdt, String cccd, double luong, int trangThai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.cccd = cccd;
        this.luong = luong;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public int getMaNV() { return maNV; }
    public void setMaNV(int maNV) { this.maNV = maNV; }

    public String getTenNV() { return tenNV; }
    public void setTenNV(String tenNV) { this.tenNV = tenNV; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getCCCD() { return cccd; }
    public void setCCCD(String cccd) { this.cccd = cccd; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getSDT() { return sdt; }
    public void setSDT(String sdt) { this.sdt = sdt; }

    public String getChucVu() { return chucVu; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }

    public double getLuong() { return luong; }
    public void setLuong(double luong) { this.luong = luong; }

    public int getTrangThai() { return trangThai; }
    public void setTrangThai(int trangThai) { this.trangThai = trangThai; }
}