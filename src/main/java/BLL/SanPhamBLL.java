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
        if (sanPham.getTenSP().isEmpty() || sanPham.getGia() <= 0) {
            System.out.println("Lỗi: Tên sản phẩm không được để trống và giá phải lớn hơn 0.");
            return false;
        }
        return SanPhamDAL.insertProduct(sanPham) >0;
    }

    public static boolean updateProduct(SanPhamDTO sanPham) {
        if (sanPham.getMaSP() <= 0) {
            System.out.println("Lỗi: Mã sản phẩm không hợp lệ.");
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

    public static List<SanPhamDTO> searchProducts(String keyword, String searchType) {
        return SanPhamDAL.searchProducts(keyword, searchType);
    }

    public static void main(String[] args) {
        SanPhamDTO sp = getProductById(1);
        System.out.println(sp.toString());
    }
}
