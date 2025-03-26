package DTO;



public class LoaiSanPhamDTO {
    private int maLSP;
    private String tenLoaiSP;
    private String trangThai;

    // Constructor không tham số
    public LoaiSanPhamDTO() {}

    // Constructor đầy đủ tham số
    public LoaiSanPhamDTO(int maLSP, String tenLoaiSP, String trangThai) {
        this.maLSP = maLSP;
        this.tenLoaiSP = tenLoaiSP;
        this.trangThai = trangThai;
    }

    // Getter và Setter
    public int getMaLSP() {
        return maLSP;
    }

    public void setMaLSP(int maLSP) {
        this.maLSP = maLSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public String isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức hiển thị thông tin loại sản phẩm
    @Override
    public String toString() {
        return "LoaiSP{" +
                "maLSP='" + maLSP + '\'' +
                ", tenLoaiSP='" + tenLoaiSP + '\'' +
                ", trangThai=" + (trangThai.equals("") ? "Hoạt động" : "Ngừng hoạt động") +
                '}';
    }
}
