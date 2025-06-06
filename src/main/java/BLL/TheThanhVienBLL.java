package BLL;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import DAL.TheThanhVienDAL;
import DTO.TheThanhVienDTO;

public class TheThanhVienBLL {

    // Lấy tất cả thẻ thành viên
    public static ArrayList<TheThanhVienDTO> getAllMembers() {
        return TheThanhVienDAL.getAllMembers();
    }

    // Lấy tất cả thẻ thành viên ACTIVE
    public static ArrayList<TheThanhVienDTO> getAllMembersACTIVE() {
        return TheThanhVienDAL.getAllMembersACTIVE();
    }

    // Lấy tất cả thẻ thành viên INACTIVE
    public static ArrayList<TheThanhVienDTO> getAllMembersINACTIVE() {
        return TheThanhVienDAL.getAllMembersINACTIVE();
    }

    // Lấy thẻ thành viên theo mã
    public static TheThanhVienDTO getMemberById(int maTV) {
        if (maTV != 0) {
            return TheThanhVienDAL.getMemberById(maTV);
        }
        return null;
    }

    public static TheThanhVienDTO getMemberById(Integer maTV) {
        if (maTV != null) {
            return TheThanhVienDAL.getMemberById(maTV);
        }
        return null;
    }

    // Lấy thẻ thành viên theo số điện thoại
    public static TheThanhVienDTO getMemberByPhone(String phone) {
        return TheThanhVienDAL.getMemberByPhone(phone);
    }

    // Thêm thẻ thành viên mới
    public static String addMember(TheThanhVienDTO member) {
        if (getMemberByPhone(member.getSdt()) != null) {
            return "Số điện thoại này đã có rồi!";
        } else {
            int result = TheThanhVienDAL.insertMember(member);
            if (result >= 1) {
                return "Thêm thành viên thành công!";
            } else {
                return "Thêm thành viên thất bại!";
            }
        }
    }

    // Cập nhật thông tin thẻ thành viên
    public static boolean updateMember(TheThanhVienDTO member) {
        TheThanhVienDTO existing = getMemberByPhone(member.getSdt());

        // Nếu có người dùng số điện thoại này, và người đó KHÔNG PHẢI là chính mình → sai
        if (existing != null && existing.getMaTV() != member.getMaTV()) {
            return false;
        }

        int result = TheThanhVienDAL.updateMember(member);
        return result > 0;
    }
    

    // Khóa thẻ thành viên theo mã
    public static boolean deleteMember(int maTV) {
        int result = TheThanhVienDAL.deleteMember(maTV);
        return result > 0;
    }

    // Mở khóa thẻ thành viên theo mã và có tăng thêm hạn thẻ nếu ngàyKT sau ngày hiện tại
    public static boolean UndeleteMember(int maTV, Date ngayKT) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ngayketthuc = sdf.format(ngayKT), today = LocalDate.now().toString();
        if (ngayketthuc.compareTo(today) < 0) {
            return TheThanhVienDAL.UndeleteMemberandPlus(maTV) > 0;
        } else {
            return TheThanhVienDAL.UndeleteMember(maTV) > 0;
        }
    }

    // Hiển thị danh sách tất cả thành viên
    public static void printAllMembers() {
        ArrayList<TheThanhVienDTO> memberList = getAllMembers();
        for (TheThanhVienDTO member : memberList) {
            System.out.println(member.toString());
        }
    }

    public static void main(String[] args) {
        System.out.println(getMemberByPhone("0987456124").toString());
        System.out.println(getMemberByPhone("0987456124").toString());
        System.out.println(getMemberByPhone("0987456124").toString());
    }
}