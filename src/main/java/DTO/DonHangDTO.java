package DTO;
import java.util.Date;

public class DonHangDTO {
    private int maDH;       // Mã đơn hàng (Khóa chính)
    private int maKH;       // Mã khách hàng (Khóa ngoại)
    private int maKM;       // Mã khuyến mãi (Khóa ngoại)
    private int maNV;       // Mã nhân viên (Khóa ngoại)
    private String ptThanhToan; // Phương thức thanh toán
    private Date ngayTT;       // Ngày thanh toán
    private String trangThai; // Trạng thái đơn hàng

    // Constructor không tham số
    public DonHangDTO() {
    }

    // Constructor có tham số
    public DonHangDTO(int maDH, int maKH, int maKM, int maNV, String ptThanhToan,  Date ngayTT, String trangThai) {
        this.maDH = maDH;
        this.maKH = maKH;
        this.maKM = maKM;
        this.maNV = maNV;
        this.ptThanhToan = ptThanhToan;
        this.ngayTT = ngayTT;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maHK) {
        this.maKH = maHK;
    }

    public int getMaKM() {
        return maKM;
    }

    public void setMaKM(int maKM) {
        this.maKM = maKM;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getPtThanhToan() {
        return ptThanhToan;
    }

    public void setPtThanhToan(String ptThanhToan) {
        this.ptThanhToan = ptThanhToan;
    }

 
    public Date getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(Date ngayTT) {
        this.ngayTT = ngayTT;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức hiển thị thông tin
    @Override
    public String toString() {
        return "DonHang{" +
                "maDH='" + maDH + '\'' +
                ", maHK='" + maKH + '\'' +
                ", maKM='" + maKM + '\'' +
                ", maNV='" + maNV + '\'' +
                ", ptThanhToan='" + ptThanhToan + '\'' +
                ", ngayTT=" + ngayTT +
                ", trangThai=" + trangThai +
                '}';
    }
}
