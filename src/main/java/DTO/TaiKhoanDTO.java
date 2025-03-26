package DTO;

public class TaiKhoanDTO {
    private int maNV;         // Mã nhân viên (FK)
    private String tenTK;     // Tên tài khoản
    private String matKhau;   // Mật khẩu
    private String sdt;       // Số điện thoại
    private String quyen;     // Quyền (Admin, Nhân viên, ...)
    private String gmail;     // Gmail
    private String tenAnh;    // Tên ảnh đại diện
    private String trangThai; // Trạng thái tài khoản (Hoạt động, Khóa, ...)

    // Constructor không tham số
    public TaiKhoanDTO() {}

    // Constructor đầy đủ tham số
    public TaiKhoanDTO(int maNV, String tenTK, String matKhau, String sdt, String quyen, String gmail, String tenAnh, String trangThai) {
        this.maNV = maNV;
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.sdt = sdt;
        this.quyen = quyen;
        this.gmail = gmail;
        this.tenAnh = tenAnh;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
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

    // Phương thức hiển thị thông tin tài khoản
    @Override
    public String toString() {
        return "TaiKhoan{" +
                "maNV=" + maNV +
                ", tenTK='" + tenTK + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", sdt='" + sdt + '\'' +
                ", quyen='" + quyen + '\'' +
                ", gmail='" + gmail + '\'' +
                ", tenAnh='" + tenAnh + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
