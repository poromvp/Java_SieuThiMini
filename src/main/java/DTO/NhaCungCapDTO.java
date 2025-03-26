package DTO;

public class NhaCungCapDTO {
    private int maNCC;
    private String tenNCC;
    private String sdt;
    private String diaChi;
    private String trangThai;

    // Constructor không tham số
    public NhaCungCapDTO() {}

    // Constructor đầy đủ tham số
    public NhaCungCapDTO(int maNCC, String tenNCC, String sdt, String diaChi, String trangThai) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức hiển thị thông tin nhà cung cấp
    @Override
    public String toString() {
        return "NhaCCC{" +
                "maNCC='" + maNCC + '\'' +
                ", tenNCC='" + tenNCC + '\'' +
                ", sdt='" + sdt + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", trangThai=" + (trangThai.equals("") ? "Hoạt động" : "Ngừng hoạt động") +
                '}';
    }
}
