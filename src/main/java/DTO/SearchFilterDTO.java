package DTO;

import java.sql.Date;

public class SearchFilterDTO {
    public Integer maDH;
    public Date ngayBD;
    public Date ngayKT;
    public double tongTienMin;
    public double tongTienMax;
    public String phuongThucTT;
    public Integer maKH;
    public String tenKH;
    public Integer maKM;
    public String tenKM;
    public Integer maSP;
    public String tenSP;
    public Integer maLSP;
    public String tenLSP;
    public Integer maNV;
    public String tenNV;
    public String sort;
    public String tenCot;

    public SearchFilterDTO() {
    }

    public SearchFilterDTO(Integer maDH, Date ngayBD, Date ngayKT, double tongTienMin, double tongTienMax,
            String phuongThucTT, Integer maKH, String tenKH, Integer maKM, String tenKM, Integer maSP, String tenSP, Integer maLSP,
            String tenLSP, Integer maNV, String tenNV, String sort, String tenCot) {
        this.maDH = maDH;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.tongTienMin = tongTienMin;
        this.tongTienMax = tongTienMax;
        this.phuongThucTT = phuongThucTT;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLSP = maLSP;
        this.tenLSP = tenLSP;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.sort = sort;
        this.tenCot = tenCot;
    }

    public Integer getMaDH() {
        return maDH;
    }

    public void setMaDH(Integer maDH) {
        this.maDH = maDH;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public double getTongTienMin() {
        return tongTienMin;
    }

    public void setTongTienMin(double tongTienMin) {
        this.tongTienMin = tongTienMin;
    }

    public double getTongTienMax() {
        return tongTienMax;
    }

    public void setTongTienMax(double tongTienMax) {
        this.tongTienMax = tongTienMax;
    }

    public String getPhuongThucTT() {
        return phuongThucTT;
    }

    public void setPhuongThucTT(String phuongThucTT) {
        this.phuongThucTT = phuongThucTT;
    }

    public Integer getMaKH() {
        return maKH;
    }

    public void setMaKH(Integer maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Integer getMaKM() {
        return maKM;
    }

    public void setMaKM(Integer maKM) {
        this.maKM = maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public Integer getMaSP() {
        return maSP;
    }

    public void setMaSP(Integer maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Integer getMaLSP() {
        return maLSP;
    }

    public void setMaLSP(Integer maLSP) {
        this.maLSP = maLSP;
    }

    public String getTenLSP() {
        return tenLSP;
    }

    public void setTenLSP(String tenLSP) {
        this.tenLSP = tenLSP;
    }

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTenCot() {
        return tenCot;
    }

    public void setTenCot(String tenCot) {
        this.tenCot = tenCot;
    }

   
}
