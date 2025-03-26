package DTO;

import java.util.Date;

public class TheThanhVienDTO {
    private int maTV;        // Mã thẻ thành viên (PK)
    private String tenTV;    // Tên thành viên
    private Date ngaySinh;   // Ngày sinh
    private String diaChi;   // Địa chỉ
    private int diemTL;      // Điểm tích lũy
    private String sdt;      // Số điện thoại
    private Date ngayBD;     // Ngày bắt đầu sử dụng thẻ
    private Date ngayKT;     // Ngày kết thúc hiệu lực của thẻ
    private String tenAnh;   // Tên ảnh đại diện của thành viên
    private String trangThai;// Trạng thái thẻ (Còn hiệu lực/Hết hạn)

    // Constructor không tham số
    public TheThanhVienDTO() {}

    // Constructor đầy đủ tham số
    public TheThanhVienDTO(int maTV, String tenTV, Date ngaySinh, String diaChi, int diemTL, String sdt, 
                        Date ngayBD, Date ngayKT, String tenAnh, String trangThai) {
        this.maTV = maTV;
        this.tenTV = tenTV;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.diemTL = diemTL;
        this.sdt = sdt;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.tenAnh = tenAnh;
        this.trangThai = trangThai;
    }

    // Getter và Setter
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

    public int getDiemTL() {
        return diemTL;
    }

    public void setDiemTL(int diemTL) {
        this.diemTL = diemTL;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public String getTenAnh() {
        return tenAnh;
    }

    public void setTenAnh(String tenAnh) {
        this.tenAnh = tenAnh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức hiển thị thông tin thẻ thành viên
    @Override
    public String toString() {
        return "TheThanhVien{" +
                "maTV=" + maTV +
                ", tenTV='" + tenTV + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", diaChi='" + diaChi + '\'' +
                ", diemTL=" + diemTL +
                ", sdt='" + sdt + '\'' +
                ", ngayBD=" + ngayBD +
                ", ngayKT=" + ngayKT +
                ", tenAnh='" + tenAnh + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
