package DTO;

public class SearchBanChayDTO {
    public int maSP;
    public String tenSP;
    public int maLSP;
    public String tenLSP;
    public int SLmin;
    public int SLmax;
    public String sort;
    public String by;
    public int SLbanra;
    public String chuoiMaDH;

    public SearchBanChayDTO() {
    }

    public SearchBanChayDTO(int maSP, String tenSP, String tenLSP, int sLbanra, String chuoiMaDH) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.tenLSP = tenLSP;
        SLbanra = sLbanra;
        this.chuoiMaDH = chuoiMaDH;
    }

    public String getChuoiMaDH() {
        return chuoiMaDH;
    }

    public void setChuoiMaDH(String chuoiMaDH) {
        this.chuoiMaDH = chuoiMaDH;
    }

    public SearchBanChayDTO(int maSP, String tenSP, int maLSP, String tenLSP, int sLmin, int sLmax, String sort,
            String by) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLSP = maLSP;
        this.tenLSP = tenLSP;
        SLmin = sLmin;
        SLmax = sLmax;
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

    public int getSLbanra() {
        return SLbanra;
    }

    public void setSLbanra(int sLbanra) {
        SLbanra = sLbanra;
    }

}
