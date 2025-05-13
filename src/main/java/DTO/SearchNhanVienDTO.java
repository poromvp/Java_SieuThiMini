package DTO;
import java.sql.Date;
public class SearchNhanVienDTO {
    public int maNV;
    public String tenNV;
    public String diaChi;
    public Date ngaySinhFrom, ngaySinhTo;
    public String SDT;
    public int maDH;
    public int tongDHMin, tongDHMax;
    public int doanhSoMin, doanhSoMax;
    public int luongMin, luongMax;
    public String order, by;
    public SearchNhanVienDTO() {
    }
    public SearchNhanVienDTO(int maNV, String tenNV, String diaChi, Date ngaySinhFrom, Date ngaySinhTo,
            String sDT, int maDH, int tongDHMin, int tongDHMax, int doanhSoMin, int doanhSoMax, int luongMin,
            int luongMax, String order, String by) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.diaChi = diaChi;
        this.ngaySinhFrom = ngaySinhFrom;
        this.ngaySinhTo = ngaySinhTo;
        SDT = sDT;
        this.maDH = maDH;
        this.tongDHMin = tongDHMin;
        this.tongDHMax = tongDHMax;
        this.doanhSoMin = doanhSoMin;
        this.doanhSoMax = doanhSoMax;
        this.luongMin = luongMin;
        this.luongMax = luongMax;
        this.order = order;
        this.by = by;
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
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public Date getNgaySinhFrom() {
        return ngaySinhFrom;
    }
    public void setNgaySinhFrom(Date ngaySinhFrom) {
        this.ngaySinhFrom = ngaySinhFrom;
    }
    public Date getNgaySinhTo() {
        return ngaySinhTo;
    }
    public void setNgaySinhTo(Date ngaySinhTo) {
        this.ngaySinhTo = ngaySinhTo;
    }
    public String getSDT() {
        return SDT;
    }
    public void setSDT(String sDT) {
        SDT = sDT;
    }
    public int getMaDH() {
        return maDH;
    }
    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }
    public int getTongDHMin() {
        return tongDHMin;
    }
    public void setTongDHMin(int tongDHMin) {
        this.tongDHMin = tongDHMin;
    }
    public int getTongDHMax() {
        return tongDHMax;
    }
    public void setTongDHMax(int tongDHMax) {
        this.tongDHMax = tongDHMax;
    }
    public int getDoanhSoMin() {
        return doanhSoMin;
    }
    public void setDoanhSoMin(int doanhSoMin) {
        this.doanhSoMin = doanhSoMin;
    }
    public int getDoanhSoMax() {
        return doanhSoMax;
    }
    public void setDoanhSoMax(int doanhSoMax) {
        this.doanhSoMax = doanhSoMax;
    }
    public int getLuongMin() {
        return luongMin;
    }
    public void setLuongMin(int luongMin) {
        this.luongMin = luongMin;
    }
    public int getLuongMax() {
        return luongMax;
    }
    public void setLuongMax(int luongMax) {
        this.luongMax = luongMax;
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
