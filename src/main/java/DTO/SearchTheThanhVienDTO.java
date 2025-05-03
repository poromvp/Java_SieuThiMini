package DTO;
import java.sql.Date;
public class SearchTheThanhVienDTO {
    public int maTV;
    public String tenTV;
    public String diaChi;
    public Date sinhFrom, sinhTo;
    public String SDT;
    public int maDH;
    public int dhMin, dhMax;
    public int tongMin, tongMax;
    public int dtlMin, dtlMax;
    public Date ngayMin, ngayMax;
    public String order, by;
    public SearchTheThanhVienDTO() {
    }
    public SearchTheThanhVienDTO(int maTV, String tenTV, String diaChi, Date sinhFrom, Date sinhTo, String sDT,
            int maDH, int dhMin, int dhMax, int tongMin, int tongMax, int dtlMin, int dtlMax, Date ngayMin,
            Date ngayMax, String order, String by) {
        this.maTV = maTV;
        this.tenTV = tenTV;
        this.diaChi = diaChi;
        this.sinhFrom = sinhFrom;
        this.sinhTo = sinhTo;
        SDT = sDT;
        this.maDH = maDH;
        this.dhMin = dhMin;
        this.dhMax = dhMax;
        this.tongMin = tongMin;
        this.tongMax = tongMax;
        this.dtlMin = dtlMin;
        this.dtlMax = dtlMax;
        this.ngayMin = ngayMin;
        this.ngayMax = ngayMax;
        this.order = order;
        this.by = by;
    }
    public int getMaTV() {
        return maTV;
    }
    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }
    public String getTenTV() {
        return tenTV;
    }
    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public Date getSinhFrom() {
        return sinhFrom;
    }
    public void setSinhFrom(Date sinhFrom) {
        this.sinhFrom = sinhFrom;
    }
    public Date getSinhTo() {
        return sinhTo;
    }
    public void setSinhTo(Date sinhTo) {
        this.sinhTo = sinhTo;
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
    public int getDhMin() {
        return dhMin;
    }
    public void setDhMin(int dhMin) {
        this.dhMin = dhMin;
    }
    public int getDhMax() {
        return dhMax;
    }
    public void setDhMax(int dhMax) {
        this.dhMax = dhMax;
    }
    public int getTongMin() {
        return tongMin;
    }
    public void setTongMin(int tongMin) {
        this.tongMin = tongMin;
    }
    public int getTongMax() {
        return tongMax;
    }
    public void setTongMax(int tongMax) {
        this.tongMax = tongMax;
    }
    public int getDtlMin() {
        return dtlMin;
    }
    public void setDtlMin(int dtlMin) {
        this.dtlMin = dtlMin;
    }
    public int getDtlMax() {
        return dtlMax;
    }
    public void setDtlMax(int dtlMax) {
        this.dtlMax = dtlMax;
    }
    public Date getNgayMin() {
        return ngayMin;
    }
    public void setNgayMin(Date ngayMin) {
        this.ngayMin = ngayMin;
    }
    public Date getNgayMax() {
        return ngayMax;
    }
    public void setNgayMax(Date ngayMax) {
        this.ngayMax = ngayMax;
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
