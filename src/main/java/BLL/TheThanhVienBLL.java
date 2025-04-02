package BLL;

import DTO.TheThanhVienDTO;

import java.util.ArrayList;

import DAL.TheThanhVienDAL;

public class TheThanhVienBLL {
    
    // Lấy tất cả thẻ thành viên
    public static ArrayList<TheThanhVienDTO> getAllMembers() {
        return TheThanhVienDAL.getAllMembers();
    }
    
    // Lấy thẻ thành viên theo mã
    public static TheThanhVienDTO getMemberById(int maTV) {
        return TheThanhVienDAL.getMemberById(maTV);
    }
    
    // Lấy thẻ thành viên theo số điện thoại
    public static TheThanhVienDTO getMemberByPhone(String phone) {
        return TheThanhVienDAL.getMemberByPhone(phone);
    }
    
    // Thêm thẻ thành viên mới
    public static boolean addMember(TheThanhVienDTO member) {
        int result = TheThanhVienDAL.insertMember(member);
        return result > 0;
    }
    
    // Cập nhật thông tin thẻ thành viên
    public static boolean updateMember(TheThanhVienDTO member) {
        int result = TheThanhVienDAL.updateMember(member);
        return result > 0;
    }
    
    // Xóa thẻ thành viên theo mã
    public static boolean deleteMember(int maTV) {
        int result = TheThanhVienDAL.deleteMember(maTV);
        return result > 0;
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