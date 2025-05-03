package DTO;

public class SearchTonKhoDTO {
    public int maSP;
    public String tenSP;
    public int maLSP;
    public String tenLSP;
    public int SLmin;
    public int SLmax;
    public int Giamin;
    public int Giamax;
    public int maNCC;
    public String tenNCC;
    public String sort;
    public String by;
    public SearchTonKhoDTO(){};
    public SearchTonKhoDTO(int maSP, String tenSP, int maLSP, String tenLSP, int sLmin, int sLmax, int giamin, int giamax,
            int maNCC, String tenNCC, String sort, String by) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLSP = maLSP;
        this.tenLSP = tenLSP;
        SLmin = sLmin;
        SLmax = sLmax;
        Giamin = giamin;
        Giamax = giamax;
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.sort = sort;
        this.by = by;
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
    public int getMaLSP() {
        return maLSP;
    }
    public void setMaLSP(int maLSP) {
        this.maLSP = maLSP;
    }
    public String getTenLSP() {
        return tenLSP;
    }
    public void setTenLSP(String tenLSP) {
        this.tenLSP = tenLSP;
    }
    public int getSLmin() {
        return SLmin;
    }
    public void setSLmin(int sLmin) {
        SLmin = sLmin;
    }
    public int getSLmax() {
        return SLmax;
    }
    public void setSLmax(int sLmax) {
        SLmax = sLmax;
    }
    public int getGiamin() {
        return Giamin;
    }
    public void setGiamin(int giamin) {
        Giamin = giamin;
    }
    public int getGiamax() {
        return Giamax;
    }
    public void setGiamax(int giamax) {
        Giamax = giamax;
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
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getBy() {
        return by;
    }
    public void setBy(String by) {
        this.by = by;
    }
    
}
