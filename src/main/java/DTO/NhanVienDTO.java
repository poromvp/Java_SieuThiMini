package DTO;



import java.util.Date;

public class NhanVienDTO {
    private int maNV;       // Mã nhân viên (PK)
    private String tenNV;   // Tên nhân viên
    private String gioiThieu; // Giới thiệu nhân viên
    private Date ngaySinh;  // Ngày sinh
    private String diaChi;  // Địa chỉ
    private String cccd;    // Căn cước công dân
    private String chucVu;  // Chức vụ
    private String sdt;     // Số điện thoại
    private double luong;   // Lương
    private String trangThai; // Trạng thái nhân viên

    // Constructor không tham số
    public NhanVienDTO() {}

    // Constructor đầy đủ tham số
    public NhanVienDTO(int maNV, String tenNV, String gioiThieu, Date ngaySinh, String diaChi, 
                    String cccd, String chucVu, String sdt, double luong, String trangThai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiThieu = gioiThieu;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.cccd = cccd;
        this.chucVu = chucVu;
        this.sdt = sdt;
        this.luong = luong;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức hiển thị thông tin nhân viên
    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV=" + maNV +
                ", tenNV='" + tenNV + '\'' +
                ", gioiThieu='" + gioiThieu + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", diaChi='" + diaChi + '\'' +
                ", cccd='" + cccd + '\'' +
                ", chucVu='" + chucVu + '\'' +
                ", sdt='" + sdt + '\'' +
                ", luong=" + luong +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
