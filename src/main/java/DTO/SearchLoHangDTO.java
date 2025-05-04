package DTO;
import java.sql.Date;
public class SearchLoHangDTO {
    public int maDonNH;
    public String tenDonNH;
    public Date ngayTu, ngayDen;
    public int maNV;
    public String tenNV;
    public int maNCC;
    public String tenNCC;
    public int giaMin, giaMax;
    public int maSP;
    public String tenSP;
    public int maLH;
    public String order, by;
    public SearchLoHangDTO() {
    }
    public SearchLoHangDTO(int maDonNH, String tenDonNH, Date ngayTu, Date ngayDen, int maNV, String tenNV, int maNCC,
            String tenNCC, int giaMin, int giaMax, int maSP, String tenSP, int maLH, String order, String by) {
        this.maDonNH = maDonNH;
        this.tenDonNH = tenDonNH;
        this.ngayTu = ngayTu;
        this.ngayDen = ngayDen;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.giaMin = giaMin;
        this.giaMax = giaMax;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLH = maLH;
        this.order = order;
        this.by = by;
    }
    public int getMaDonNH() {
        return maDonNH;
    }
    public void setMaDonNH(int maDonNH) {
        this.maDonNH = maDonNH;
    }
    public String getTenDonNH() {
        return tenDonNH;
    }
    public void setTenDonNH(String tenDonNH) {
        this.tenDonNH = tenDonNH;
    }
    public Date getNgayTu() {
        return ngayTu;
    }
    public void setNgayTu(Date ngayTu) {
        this.ngayTu = ngayTu;
    }
    public Date getNgayDen() {
        return ngayDen;
    }
    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
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
    public int getMaNCC() {
        return maNCC;
    }
    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }
    public String getTenNCC() {
        return tenNCC;
    }
    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }
    public int getGiaMin() {
        return giaMin;
    }
    public void setGiaMin(int giaMin) {
        this.giaMin = giaMin;
    }
    public int getGiaMax() {
        return giaMax;
    }
    public void setGiaMax(int giaMax) {
        this.giaMax = giaMax;
    }
    public int getMaSP() {
        return maSP;
    }
    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }
    public String getTenSP() {
        return tenSP;
    }
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    public int getMaLH() {
        return maLH;
    }
    public void setMaLH(int maLH) {
        this.maLH = maLH;
    }
    public String getOrder() {
        return order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    public String getBy() {
        return by;
    }
    public void setBy(String by) {
        this.by = by;
    }
    
}
