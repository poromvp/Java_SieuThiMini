package DTO;

public class DonHangDTO {
    private int maDH;       // Mã đơn hàng (Khóa chính)
    private Integer maKH;       // Mã khách hàng (Khóa ngoại)
    private Integer maKM;       // Mã khuyến mãi (Khóa ngoại)
    private int maNV;       // Mã nhân viên (Khóa ngoại)
    private String ptThanhToan; // Phương thức thanh toán
    private Integer maDTL;
    private int tienKD;
    private String ngayTT;       // Ngày thanh toán
    private int tongTien;       // Ngày thanh toán
    private String trangThai; // Trạng thái đơn hàng

    // Constructor không tham số
    public DonHangDTO() {
    }

    // Constructor có tham số
    public DonHangDTO(int maDH, Integer maKH, Integer maKM, int maNV, String ptThanhToan,  String ngayTT, Integer dtl, int tienkd, int tongTien,  String trangThai) {
        this.maDH = maDH;
        this.maKH = maKH;
        this.maKM = maKM;
        this.maNV = maNV;
        this.ptThanhToan = ptThanhToan;
        this.ngayTT = ngayTT;
        this.maDTL = dtl;
        this.tienKD = tienkd;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }



    public Integer getMaDTL() {
        return maDTL;
    }
    

    public int getTongTien() {
        return tongTien;
    }

    public void setMaDTL(Integer maDTL) {
        this.maDTL = maDTL;
    }
    
    public int getTienKD() {
        return tienKD;
    }
    
    public void setTienKD(int tienKD) {
        this.tienKD = tienKD;
    }
    

    // Getter và Setter
    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public Integer getMaKH() {
        return maKH;
    }

    public void setMaKH(Integer maHK) {
        this.maKH = maHK;
    }

    public Integer getMaKM() {
        return maKM;
    }

    public void setMaKM(Integer maKM) {
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

 
    public String getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(String ngayTT) {
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
                ", maDTL=" + maDTL +
                ", trangThai=" + trangThai +
                '}';
    }
}
