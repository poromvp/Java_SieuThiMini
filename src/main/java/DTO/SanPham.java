package DTO;

public class SanPham {
    private int maSP;     // Mã sản phẩm (Khóa chính)
    private int  maLH;     // Mã loại hàng (Khóa ngoại)
    private int maNCC;    // Mã nhà cung cấp (Khóa ngoại)
    private int maLSP;    // Mã loại sản phẩm (Khóa ngoại)
    private String tenAnh;   // Tên ảnh sản phẩm
    private double gia;      // Giá sản phẩm
    private String tenSP;    // Tên sản phẩm
    private String moTa;     // Mô tả sản phẩm
    private String trangThai; // Trạng thái sản phẩm (còn bán hay ngừng bán)

    // Constructor không tham số
    public SanPham() {
    }

    // Constructor có tham số
    public SanPham(int maSP, int maLH, int maNCC, int maLSP, String tenAnh, double gia, String tenSP, String moTa, String trangThai) {
        this.maSP = maSP;
        this.maLH = maLH;
        this.maNCC = maNCC;
        this.maLSP = maLSP;
        this.tenAnh = tenAnh;
        this.gia = gia;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaLH() {
        return maLH;
    }

    public void setMaLH(int maLH) {
        this.maLH = maLH;
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

    public String getTenAnh() {
        return tenAnh;
    }

    public void setTenAnh(String tenAnh) {
        this.tenAnh = tenAnh;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức hiển thị thông tin
    @Override
    public String toString() {
        return "SanPham{" +
                "maSP='" + maSP + '\'' +
                ", maLH='" + maLH + '\'' +
                ", maNCC='" + maNCC + '\'' +
                ", maLSP='" + maLSP + '\'' +
                ", tenAnh='" + tenAnh + '\'' +
                ", gia=" + gia +
                ", tenSP='" + tenSP + '\'' +
                ", moTa='" + moTa + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }
}
