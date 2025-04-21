package BLL;

import java.util.List;
import DAL.SanPhamDAL;
import DTO.SanPhamDTO;

public class SanPhamBLL {

    public static SanPhamDTO getProductById(int id) {
        return SanPhamDAL.getProductById(id);
    }

    public static List<SanPhamDTO> getAllProducts() {
        return SanPhamDAL.getAllProducts();
    }

    public static boolean addProduct(SanPhamDTO sanPham) {
        if (sanPham.getTenSP().isEmpty() || sanPham.getGia() <= 0 || sanPham.getSoLuongTon() < 0) {
            System.out.println("Lỗi: Tên sản phẩm không được để trống, giá phải lớn hơn 0 và số lượng tồn không được âm.");
            return false;
        }
        return SanPhamDAL.insertProduct(sanPham) > 0;
    }

    public static boolean updateProduct(SanPhamDTO sanPham) {
        if (sanPham.getMaSP() <= 0 || sanPham.getSoLuongTon() < 0) {
            System.out.println("Lỗi: Mã sản phẩm không hợp lệ hoặc số lượng tồn không được âm.");
            return false;
        }
        return SanPhamDAL.updateProduct(sanPham);
    }

    public static boolean deleteProduct(int id) {
        if (id <= 0) {
            System.out.println("Lỗi: ID sản phẩm không hợp lệ.");
            return false;
        }
        return SanPhamDAL.deleteProduct(id);
    }

    public static List<SanPhamDTO> searchProducts(String maSP, String tenSP, int maLSP) {
        return SanPhamDAL.searchProducts(maSP, tenSP, maLSP);
    }

    public static void main(String[] args) {
        SanPhamDTO sp = getProductById(1);
        if (sp != null) {
            System.out.println(sp.toString());
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID = 1");
        }
    }
}