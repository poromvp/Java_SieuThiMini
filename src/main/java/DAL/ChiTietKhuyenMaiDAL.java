package DAL;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import com.google.zxing.Result;

import DTO.ChiTietKhuyenMaiDTO;
import JDBC.DBConnection;



public  class ChiTietKhuyenMaiDAL{


    public static final String tableName = "ChiTietKM";

    public static ArrayList<ChiTietKhuyenMaiDTO> getAllDiscountDetailByDiscountId(int discountId){
        ArrayList<ChiTietKhuyenMaiDTO> detailList = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE maKM = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, discountId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) { 
                ChiTietKhuyenMaiDTO ctkm = new ChiTietKhuyenMaiDTO();
                ctkm.setMaKM(rs.getInt("maKM"));
                ctkm.setMaSP(rs.getInt("maSP"));
                ctkm.setTiLeGiam(rs.getDouble("tiLeGiam"));
                ctkm.setTrangThai(rs.getString("trangThai"));
                
                detailList.add(ctkm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return detailList;
    }


    public static double  getProductOnSaleToday(int productId) {
        String sql = "SELECT ctkm.tiLeGiam FROM ChiTietKM ctkm " +
                     "JOIN KhuyenMai km ON ctkm.maKM = km.maKM " +
                     "WHERE ctkm.maSP = ? " +
                     "AND km.ngayBD <= ? AND km.ngayKT >= ? " +
                     "AND km.trangThai = 'Hoạt động'";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            // Lấy ngày hiện tại
            Date today = Date.valueOf(LocalDate.now());
            pstmt.setInt(1, productId);
            pstmt.setDate(2, today);
            pstmt.setDate(3, today);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("tiLeGiam") ; // Nếu COUNT > 0 nghĩa là có khuyến mãi
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(getProductOnSaleToday(1));
    }



}