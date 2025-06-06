package DTO;
import java.util.Date;

public class DonHang {
    private String maDH;       // Mã đơn hàng (Khóa chính)
    private String maHK;       // Mã khách hàng (Khóa ngoại)
    private String maKM;       // Mã khuyến mãi (Khóa ngoại)
    private String maNV;       // Mã nhân viên (Khóa ngoại)
    private String ptThanhToan; // Phương thức thanh toán
    private double tongTien;   // Tổng tiền
    private Date ngayTT;       // Ngày thanh toán
    private boolean trangThai; // Trạng thái đơn hàng

    // Constructor không tham số
    public DonHang() {
    }

    // Constructor có tham số
    public DonHang(String maDH, String maHK, String maKM, String maNV, String ptThanhToan, double tongTien, Date ngayTT, boolean trangThai) {
        this.maDH = maDH;
        this.maHK = maHK;
        this.maKM = maKM;
        this.maNV = maNV;
        this.ptThanhToan = ptThanhToan;
        this.tongTien = tongTien;
        this.ngayTT = ngayTT;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getMaHK() {
        return maHK;
    }

    public void setMaHK(String maHK) {
        this.maHK = maHK;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getPtThanhToan() {
        return ptThanhToan;
    }

    public void setPtThanhToan(String ptThanhToan) {
        this.ptThanhToan = ptThanhToan;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(Date ngayTT) {
        this.ngayTT = ngayTT;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức hiển thị thông tin
    @Override
    public String toString() {
        return "DonHang{" +
                "maDH='" + maDH + '\'' +
                ", maHK='" + maHK + '\'' +
                ", maKM='" + maKM + '\'' +
                ", maNV='" + maNV + '\'' +
                ", ptThanhToan='" + ptThanhToan + '\'' +
                ", tongTien=" + tongTien +
                ", ngayTT=" + ngayTT +
                ", trangThai=" + trangThai +
                '}';
    }
}
