package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.SanPhamDTO;
import JDBC.DBConnection;

public class SanPhamDAL {

    public static SanPhamDTO getProductById(int id) {
        String sql = "SELECT * FROM SanPham WHERE maSP = ?";
        try (ResultSet rs = DBConnection.executeQuery(sql, id)) {
            if (rs.next()) {
                return mapResultSetToSanPham(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<SanPhamDTO> timKiemSanPham(String idOrTenSP, String IdOrTenLSP, int giaMin, int giaMax) {
        ArrayList<SanPhamDTO> ketQua = new ArrayList<>();
    
        String sql = "SELECT * FROM SanPham " +
                     "LEFT JOIN LoaiSP ON SanPham.maLSP = LoaiSP.maLSP " +
                     "WHERE 1=1";
        
        ArrayList<Object> params = new ArrayList<>();
    
        // Tìm theo mã SP hoặc tên SP (không phân biệt hoa thường)
        if (!idOrTenSP.isEmpty()) {
            sql += " AND (LOWER(SanPham.maSP) = LOWER(?) OR LOWER(SanPham.tenSP) LIKE LOWER(?))";
            params.add(idOrTenSP);
            params.add("%" + idOrTenSP + "%");
        }
    
        // Tìm theo mã loại SP hoặc tên loại SP (không phân biệt hoa thường)
        if (!IdOrTenLSP.equalsIgnoreCase("tất cả")) {
            sql += " AND (LOWER(LoaiSP.maLSP) = LOWER(?) OR LOWER(LoaiSP.tenLoaiSP) LIKE LOWER(?))";
            params.add(IdOrTenLSP);
            params.add("%" + IdOrTenLSP + "%");
        }
    
        // Điều kiện khoảng giá
        sql += " AND SanPham.gia BETWEEN ? AND ?";
        params.add(giaMin);
        params.add(giaMax);
    
        try (PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof String) {
                    stmt.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    stmt.setInt(i + 1, (Integer) param);
                }
            }
    
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ketQua.add(mapResultSetToSanPham(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return ketQua;
    }
    
    

    public static List<SanPhamDTO> getAllProducts() {
        List<SanPhamDTO> productList = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";

        try (ResultSet rs = DBConnection.executeQuery(sql)) {
            while (rs.next()) {
                productList.add(mapResultSetToSanPham(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public static int insertProduct(SanPhamDTO sanPham) {
        String sql = "INSERT INTO SanPham (maNCC, maLSP, tenSP, gia, tenAnh, moTa, trangThai, SoLuongTon) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return DBConnection.executeUpdate(sql,
                sanPham.getMaNCC(), sanPham.getMaLSP(),
                sanPham.getTenSP(), sanPham.getGia(), sanPham.getTenAnh(),
                sanPham.getMoTa(), sanPham.getTrangThai(), sanPham.getSoLuongTon());
    }

    public static boolean updateProduct(SanPhamDTO sanPham) {
        String sql = "UPDATE SanPham SET maNCC=?, maLSP=?, tenSP=?, gia=?, tenAnh=?, moTa=?, trangThai=?, SoLuongTon=? WHERE maSP=?";
        return DBConnection.executeUpdate(sql,
                sanPham.getMaNCC(), sanPham.getMaLSP(),
                sanPham.getTenSP(), sanPham.getGia(), sanPham.getTenAnh(),
                sanPham.getMoTa(), sanPham.getTrangThai(), sanPham.getSoLuongTon(), sanPham.getMaSP()) > 0;
    }

    public static boolean deleteProduct(int id) {
        String sql = "DELETE FROM SanPham WHERE maSP = ?";
        return DBConnection.executeUpdate(sql, id) > 0;
    }

    private static SanPhamDTO mapResultSetToSanPham(ResultSet rs) throws SQLException {
        SanPhamDTO sp = new SanPhamDTO();
        sp.setMaSP(rs.getInt("maSP"));
        sp.setMaNCC(rs.getInt("maNCC"));
        sp.setMaLSP(rs.getInt("maLSP"));
        sp.setTenSP(rs.getString("tenSP"));
        sp.setGia(rs.getDouble("gia"));
        sp.setMoTa(rs.getString("moTa"));
        sp.setTenAnh(rs.getString("tenAnh"));
        sp.setTrangThai(rs.getString("trangThai"));
        sp.setSoLuongTon(rs.getInt("SoLuongTon")); // Ánh xạ cột mới
        return sp;
    }

    public static ArrayList<SanPhamDTO> searchProducts(String maSP, String tenSP, int maLSP) {
        ArrayList<SanPhamDTO> productList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM SanPham WHERE 1=1");
        List<Object> params = new ArrayList<>();


        if (maSP != null && !maSP.isEmpty()) {
            sql.append(" AND maSP = ?");
            params.add(Integer.parseInt(maSP));
        }


        if (tenSP != null && !tenSP.isEmpty()) {
            sql.append(" AND tenSP LIKE ?");
            params.add("%" + tenSP + "%");
        }


        if (maLSP != -1) {
            sql.append(" AND maLSP = ?");
            params.add(maLSP);
        }

        try (ResultSet rs = DBConnection.executeQuery(sql.toString(), params.toArray())) {
            while (rs.next()) {
                productList.add(mapResultSetToSanPham(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public static ArrayList<SanPhamDTO> getProductByNcc(int maNCC){
        ArrayList<SanPhamDTO> productList = new ArrayList<>();
        String sql = "SELECT * FROM SanPham WHERE MaNCC = ? AND TrangThai = 'ACTIVE'";
        try (ResultSet rs = DBConnection.executeQuery(sql, maNCC)) {
            while (rs.next()) {
                productList.add(mapResultSetToSanPham(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public static void main(String[] args) {
        // SanPhamDTO sanPham = new SanPhamDTO(0, 1, 1, "1.png", 99999, "kkkk", "kkkkkkkk", "hoatdong", 100); // Thêm SoLuongTon
        // System.out.println(insertProduct(sanPham));
        ArrayList a = timKiemSanPham("1", "1", 1, 1000000);
        System.out.print(a.get(0).toString());
    }
}