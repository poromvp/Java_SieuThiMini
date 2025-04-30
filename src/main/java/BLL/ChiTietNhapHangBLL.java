package BLL;

import DAL.ChiTietNhapHangDAL;
import DTO.ChiTietPNHangDTO;
import java.util.ArrayList;

public class ChiTietNhapHangBLL {
    private ChiTietNhapHangDAL chiTietNhapHangDAL;

    public ChiTietNhapHangBLL() {
        chiTietNhapHangDAL = new ChiTietNhapHangDAL();
    }

    // Lấy tất cả chi tiết nhập hàng
    public ArrayList<ChiTietPNHangDTO> getAllChiTietNhapHang() {
        return chiTietNhapHangDAL.getAllChiTietNhapHang();
    }

    public ArrayList<ChiTietPNHangDTO> getAllCTNhHang() {
        return chiTietNhapHangDAL.getAllCTNhHang();
    }

    // Thêm chi tiết nhập hàng
    public boolean insertChiTietNhapHang(ChiTietPNHangDTO chiTiet) {
        return chiTietNhapHangDAL.insertChiTietNhapHang(chiTiet);
    }

    // Cập nhật chi tiết nhập hàng
    public boolean updateChiTietNhapHang(ChiTietPNHangDTO chiTiet) {
        return chiTietNhapHangDAL.updateChiTietNhapHang(chiTiet);
    }

    // Xóa chi tiết nhập hàng
    public boolean deleteChiTietNhapHang(int maCTPNH) {
        return chiTietNhapHangDAL.deleteChiTietNhapHang(maCTPNH);
    }

    // Lấy chi tiết nhập hàng theo mã phiếu nhập
    public ArrayList<ChiTietPNHangDTO> getChiTietByMaPNH(int maPNH) {
        return chiTietNhapHangDAL.getChiTietByMaPNH(maPNH);
    }

    // Tính tổng tiền của một phiếu nhập hàng
    public double calculateTongTien(int maPNH) {
        ArrayList<ChiTietPNHangDTO> chiTietList = getChiTietByMaPNH(maPNH);
        double tongTien = 0;

        for (ChiTietPNHangDTO chiTiet : chiTietList) {
            tongTien += chiTiet.getSoLuong() * chiTiet.getGiaNhap();
        }

        return tongTien;
    }

    // Kiểm tra số lượng nhập hợp lệ
    public boolean validateSoLuong(int soLuong) {
        return soLuong > 0;
    }

    // Kiểm tra giá nhập hợp lệ
    public boolean validateGiaNhap(double giaNhap) {
        return giaNhap > 0;
    }
}
