package BLL;

import DAL.NhapHangDAL;
import DTO.PhieuNhapHangDTO;
import java.util.ArrayList;
import java.util.Date;

public class NhapHangBLL {
    private NhapHangDAL nhapHangDAL;

    public NhapHangBLL() {
        nhapHangDAL = new NhapHangDAL();
    }

    // Lấy tất cả phiếu nhập hàng
    public ArrayList<PhieuNhapHangDTO> getAllPhieuNhapHang() {
        return nhapHangDAL.getAllPhieuNhapHang();
    }

    // Thêm phiếu nhập hàng
    public boolean insertPhieuNhapHang(PhieuNhapHangDTO phieuNhap) {
        // Validate dữ liệu trước khi thêm
            if (validatePhieuNhap(phieuNhap)) {
            return nhapHangDAL.insertPhieuNhapHang(phieuNhap);
        }
        return false;
    }

    // Cập nhật phiếu nhập hàng
    public boolean updatePhieuNhapHang(PhieuNhapHangDTO phieuNhap) {
        // Validate dữ liệu trước khi cập nhật
        if (validatePhieuNhap(phieuNhap)) {
            return nhapHangDAL.updatePhieuNhapHang(phieuNhap);
        }
        return false;
    }

    // Xóa phiếu nhập hàng
    public boolean deletePhieuNhapHang(int maPNH) {
        return nhapHangDAL.deletePhieuNhapHang(maPNH);
    }

    // Tìm kiếm phiếu nhập hàng theo mã
    public PhieuNhapHangDTO getPhieuNhapHangById(int maPNH) {
        return nhapHangDAL.getPhieuNhapHangById(maPNH);
    }

//    // Lấy phiếu nhập hàng theo nhà cung cấp
//    public ArrayList<PhieuNhapHangDTO> getPhieuNhapByNCC(int maNCC) {
//        return nhapHangDAL.getPhieuNhapByNCC(maNCC);
//    }

    // Validate dữ liệu phiếu nhập hàng
    private boolean validatePhieuNhap(PhieuNhapHangDTO phieuNhap) {
        if (phieuNhap.getTenPNH() == null || phieuNhap.getTenPNH().trim().isEmpty()) {
            System.out.println("Lỗi validate: Tên phiếu nhập hàng rỗng");
            return false;
        }
        if (phieuNhap.getNgayNhap() == null) {
            System.out.println("Lỗi validate: Ngày nhập null");
            return false;
        }
        if (phieuNhap.getNgayNhap().after(new Date())) {
            System.out.println("Lỗi validate: Ngày nhập trong tương lai");
            return false;
        }
        if (phieuNhap.getMaNCC() <= 0 || phieuNhap.getMaNV() <= 0) {
            System.out.println("Lỗi validate: Mã NCC hoặc Mã NV không hợp lệ");
            return false;
        }
        return true;
    }

    // Lọc phiếu nhập hàng theo khoảng thời gian
    public ArrayList<PhieuNhapHangDTO> filterPhieuNhapByDate(Date fromDate, Date toDate) {
        ArrayList<PhieuNhapHangDTO> allPhieuNhap = getAllPhieuNhapHang();
        ArrayList<PhieuNhapHangDTO> filteredList = new ArrayList<>();

        for (PhieuNhapHangDTO phieu : allPhieuNhap) {
            if (!phieu.getNgayNhap().before(fromDate) && !phieu.getNgayNhap().after(toDate)) {
                filteredList.add(phieu);
            }
        }

        return filteredList;
    }

    // Kiểm tra phiếu nhập đã hoàn thành chưa
    public boolean isPhieuNhapFinished(int maPNH) {
        PhieuNhapHangDTO phieu = getPhieuNhapHangById(maPNH);
        return phieu != null && phieu.getTrangThai().equals("FINISHED");
    }
}
