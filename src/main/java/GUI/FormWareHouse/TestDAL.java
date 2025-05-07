package GUI.FormWareHouse;

import DTO.PhieuNhapHangDTO;
import DTO.SanPhamDTO;
import JDBC.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDAL {

    public static SanPhamDTO getProductById(int id){
        String sql ="SELECT FROM SanPham WHERE maSP=?";
        try(ResultSet rs = DBConnection.executeQuery(sql,id)){
            if(rs.next()){
                return mapResultSetToSanPham(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private static SanPhamDTO mapResultSetToSanPham(ResultSet rs) throws SQLException{
        SanPhamDTO sp = new SanPhamDTO();
        sp.setMaSP(rs.getInt("maSP"));
        sp.setMaNCC(rs.getInt("maNCC"));
        sp.setMaLSP(rs.getInt("maLSP"));
        sp.setTenSP(rs.getString("tenSP"));
        sp.setGia(rs.getDouble("gia"));
        sp.setMoTa(rs.getString("moTa"));
        sp.setTenAnh(rs.getString("tenAnh"));
        sp.setTrangThai(rs.getString("trangThai"));
        sp.setSoLuongTon(rs.getInt("SoLuongTon"));
        return sp;
    }

    public static ArrayList<SanPhamDTO> searchProduct(String maSP,String tenSP,int maLSP){
        ArrayList<SanPhamDTO> productList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM SanPham WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if(maSP != null && !maSP.isEmpty()){
            sql.append(" AND maSP = ?");
            params.add(Integer.parseInt(maSP));
        }

        if(tenSP != null && !tenSP.isEmpty()){
            sql.append(" AND tenSP LIKE ?");
            params.add("%"+ tenSP+"%");
        }

        if(maLSP!=-1){
            sql.append(" AND maLSP = ?");
            params.add(maLSP);
        }

        try(ResultSet rs = DBConnection.executeQuery(sql.toString(),params.toArray())){
            while(rs.next()){
                productList.add(mapResultSetToSanPham(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

}