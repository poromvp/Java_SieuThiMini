package DTO;

public class Product {

    private int maSP;
    private int maNCC;
    private int maLSP;
    private String tenSP;
    private String ngaySX;
    private String ngayHH;
    private float gia;
    private int soLuong;
    private int trangThai;

    
    public Product(int maSP, int maNCC, int maLSP, String tenSP, String ngaySX, String ngayHH, float gia, int soLuong, int trangThai) {
        this.maSP = maSP;
        this.maNCC = maNCC;
        this.maLSP = maLSP;
        this.tenSP = tenSP;
        this.ngaySX = ngaySX;
        this.ngayHH = ngayHH;
        this.gia = gia;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }
    
    public Product() {
    	
    }
    public int getMaSP() {
        return maSP;
    }
    
    

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public int getMaLSP() {
        return maLSP;
    }

    public void setMaLSP(int maLSP) {
        this.maLSP = maLSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getNgaySX() {
        return ngaySX;
    }

    public void setNgaySX(String ngaySX) {
        this.ngaySX = ngaySX;
    }

    public String getNgayHH() {
        return ngayHH;
    }

    public void setNgayHH(String ngayHH) {
        this.ngayHH = ngayHH;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
