package BLL;


import DAL.DiemTichLuyDAL;
import DTO.DiemTichLuyDTO;

import java.util.ArrayList;

public class DiemTichLuyBLL {

    // Lấy tất cả các điểm tích lũy
    public static ArrayList<DiemTichLuyDTO> getAllDiemTichLuy() {
        return DiemTichLuyDAL.getAllDiemTichLuy();
    }

    // Lấy điểm tích lũy theo mã
    public static DiemTichLuyDTO getDiemTichLuyById(int maDTL) {
        return DiemTichLuyDAL.getDiemTichLuyById(maDTL);
    }

    public static DiemTichLuyDTO getDiemTichLuyByDiem(int diemTL) {
        return DiemTichLuyDAL.getDiemTichLuyByDiem(diemTL);
    }


    // Thêm điểm tích lũy mới
    public static String addDiemTichLuy(DiemTichLuyDTO diem) {
        // Kiểm tra xem mã điểm tích lũy đã tồn tại chưa
        if (DiemTichLuyDAL.exists(diem.getMaDTL())) {
            return "Mã điểm tích lũy đã tồn tại!";
        }

        int result = DiemTichLuyDAL.insertDiemTichLuy(diem);
        return result > 0 ? "Thêm mới thành công!" : "Thêm mới thất bại!";
    }

    // Cập nhật điểm tích lũy
    public static String updateDiemTichLuy(DiemTichLuyDTO diem) {
        // Kiểm tra xem mã điểm tích lũy có tồn tại không
        if (!DiemTichLuyDAL.exists(diem.getMaDTL())) {
            return "Mã điểm tích lũy không tồn tại!";
        }

        int result = DiemTichLuyDAL.updateDiemTichLuy(diem);
        return result > 0 ? "Cập nhật thành công!" : "Cập nhật thất bại!";
    }

    // Xóa điểm tích lũy
    public static String deleteDiemTichLuy(int maDTL) {
        // Kiểm tra xem mã điểm tích lũy có tồn tại không
        if (!DiemTichLuyDAL.exists(maDTL)) {
            return "Mã điểm tích lũy không tồn tại!";
        }

        int result = DiemTichLuyDAL.deleteDiemTichLuy(maDTL);
        return result > 0 ? "Xóa thành công!" : "Xóa thất bại!";
    }

    // In danh sách điểm tích lũy
    public static void printAllDiemTichLuy() {
        ArrayList<DiemTichLuyDTO> list = getAllDiemTichLuy();
        for (DiemTichLuyDTO diem : list) {
            System.out.println(diem);
        }
    }

    // Test
    public static void main(String[] args) {
        System.out.println("Danh sách điểm tích lũy:");
        printAllDiemTichLuy();

        System.out.println("\nTìm điểm tích lũy có mã 1:");
        System.out.println(getDiemTichLuyById(1));

        // Thêm mới một bản ghi
        DiemTichLuyDTO newDiem = new DiemTichLuyDTO(5, 100, 0.1, 50000, "Hoạt động");
        System.out.println("\nThêm mới: " + addDiemTichLuy(newDiem));

        // Cập nhật một bản ghi
        newDiem.setDiemTL(120);
        newDiem.setTrangThai("Tạm ngưng");
        System.out.println("\nCập nhật: " + updateDiemTichLuy(newDiem));

        // Xóa một bản ghi
        System.out.println("\nXóa: " + deleteDiemTichLuy(5));
    }
}
