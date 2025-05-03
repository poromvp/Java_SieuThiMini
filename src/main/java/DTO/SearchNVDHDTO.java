package DTO;
import java.sql.Date;
public class SearchNVDHDTO {
    public int maDH;
    public Date ngayTTfrom, ngayTTto;
    public int tienMin, tienMax;
    public String PTTT;
    public int maKH;
    public String tenKH;
    public int maKM;
    public String tenKM;
    public int maSP;
    public String tenSP;
    public int maLSP;
    public String tenLSP;
    public String order, by;
    public SearchNVDHDTO() {
    }
    public SearchNVDHDTO(int maDH, Date ngayTTfrom, Date ngayTTto, int tienMin, int tienMax, String pTTT, int maKH,
            String tenKH, int maKM, String tenKM, int maSP, String tenSP, int maLSP, String tenLSP, String order,
            String by) {
        this.maDH = maDH;
        this.ngayTTfrom = ngayTTfrom;
        this.ngayTTto = ngayTTto;
        this.tienMin = tienMin;
        this.tienMax = tienMax;
        PTTT = pTTT;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLSP = maLSP;
        this.tenLSP = tenLSP;
        this.order = order;
        this.by = by;
    }
    public int getMaDH() {
        return maDH;
    }
    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }
    public Date getNgayTTfrom() {
        return ngayTTfrom;
    }
    public void setNgayTTfrom(Date ngayTTfrom) {
        this.ngayTTfrom = ngayTTfrom;
    }
    public Date getNgayTTto() {
        return ngayTTto;
    }
    public void setNgayTTto(Date ngayTTto) {
        this.ngayTTto = ngayTTto;
    }
    public int getTienMin() {
        return tienMin;
    }
    public void setTienMin(int tienMin) {
        this.tienMin = tienMin;
    }
    public int getTienMax() {
        return tienMax;
    }
    public void setTienMax(int tienMax) {
        this.tienMax = tienMax;
    }
    public String getPTTT() {
        return PTTT;
    }
    public void setPTTT(String pTTT) {
        PTTT = pTTT;
    }
    public int getMaKH() {
        return maKH;
    }
    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }
    public String getTenKH() {
        return tenKH;
    }
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }
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
