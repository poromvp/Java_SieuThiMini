package DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DTO.KhuyenMaiDTO;
import JDBC.DBConnection;

public class KhuyenMaiDAL {
    public static ArrayList<KhuyenMaiDTO> getAllDiscount(){
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM KhuyenMai ";
        ArrayList<KhuyenMaiDTO> discountList = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) { 
                KhuyenMaiDTO discount = new KhuyenMaiDTO(
                    rs.getInt("maKM"), 
                    rs.getString("tenKM"),
                    rs.getDate("ngayKT"), 
                    rs.getDate("ngayBD"),
                    rs.getString("trangThai")
                );
                discountList.add(discount);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    
        return discountList;
    }

    
public static KhuyenMaiDTO getDiscountToday() {
    String sql = "SELECT * FROM KhuyenMai WHERE ngayBD <= ? AND ngayKT >= ?";
    KhuyenMaiDTO discount = null;

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(sql)) {
        
        LocalDate today = LocalDate.now(); // Lấy ngày hôm nay
        pstmt.setDate(1, Date.valueOf(today)); // Gán ngày bắt đầu <= hôm nay
        pstmt.setDate(2, Date.valueOf(today)); // Gán ngày kết thúc >= hôm nay
        
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            discount = new KhuyenMaiDTO(
                rs.getInt("maKM"),
                rs.getString("tenKM"),
                rs.getDate("ngayKT"),
                rs.getDate("ngayBD"),
                rs.getString("trangThai")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return discount;
}

    public static KhuyenMaiDTO getDiscountById(int id){
        String sql = "SELECT * FROM KhuyenMai WHERE maKM = ?";
        KhuyenMaiDTO khuyenMai = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                khuyenMai = new KhuyenMaiDTO(
                    rs.getInt("maKM"), 
                    rs.getString("tenKM"), 
                    rs.getDate("ngayKT"), 
                    rs.getDate("ngayBD"), 
                    rs.getString("trangThai")
                );
            }
        } catch (SQLException e) {
            
        }
        return khuyenMai;
    }

    public static int insertDiscount( KhuyenMaiDTO khuyenMai){
        String sql = "INSERT INTO  INTO KhuyenMai(maKM, tenKM, NgayKT, NgayBD, tiLeGiam, trangThai) VALUES (?,?,?,?,?,?,?)";
        return  DBConnection.executeUpdate(sql, khuyenMai.getMaKM(), khuyenMai.getTenKM(), khuyenMai.getNgayKT(), khuyenMai.getNgayBD(),  khuyenMai.getTrangThai());
    }



    public static void printALLDiscount(){
        ArrayList<KhuyenMaiDTO> discountList = KhuyenMaiDAL.getAllDiscount();
        for (KhuyenMaiDTO khuyenMai : discountList) {
            System.out.println(khuyenMai.toString());
        }

    }

    public static void main(String[] args) {
        // KhuyenMaiDAL.printALLDiscount();
        KhuyenMaiDTO km = getDiscountById(1);
        System.out.print(km.toString());
    }
    
}
