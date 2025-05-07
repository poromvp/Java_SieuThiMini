package DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import DTO.KhuyenMaiDTO;
import JDBC.DBConnection;

public class KhuyenMaiDAL {
    public static ArrayList<KhuyenMaiDTO> getAllDiscount() {
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

    public static boolean kiemTraHopLeNgay(Date ngayBD, Date ngayKT) {
        String sql = "SELECT * FROM khuyenmai WHERE " +
                     "(NgayBD <= ? AND NgayKT >= ?) OR (NgayBD <= ? AND NgayKT >= ?)";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setDate(1, ngayKT); // kết thúc mới >= bắt đầu cũ
            stmt.setDate(2, ngayBD); // bắt đầu mới <= kết thúc cũ
            stmt.setDate(3, ngayBD);
            stmt.setDate(4, ngayKT);
    
            ResultSet rs = stmt.executeQuery();
            boolean coTrung = rs.next(); // Nếu có ít nhất 1 dòng trùng
            rs.close();
            return !coTrung; // Không trùng => hợp lệ => true
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return false; 
    }
    
    public static boolean kiemTraHopLeNgayCuaKM(int maKM ,Date ngayBD, Date ngayKT) {
        String sql = "SELECT * FROM khuyenmai WHERE  MaKM != ? and " +
                     "((NgayBD <= ? AND NgayKT >= ?) OR (NgayBD <= ? AND NgayKT >= ?))";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, maKM); // kết thúc mới >= bắt đầu cũ
            stmt.setDate(2, ngayKT); // kết thúc mới >= bắt đầu cũ
            stmt.setDate(3, ngayBD); // bắt đầu mới <= kết thúc cũ
            stmt.setDate(4, ngayBD);
            stmt.setDate(5, ngayKT);
    
            ResultSet rs = stmt.executeQuery();
            boolean coTrung = rs.next(); // Nếu có ít nhất 1 dòng trùng
            rs.close();
            return !coTrung; // Không trùng => hợp lệ => true
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return false; 
    }
    


    public static ArrayList<KhuyenMaiDTO> getFilterDiscount(String idOrTenKM, String trangThai, String idOrTenSP, Date ngayBD, Date ngayKT, String sapXep, String cot) {
        ArrayList<KhuyenMaiDTO> dsKM = new ArrayList<>();
        String sql = "SELECT khuyenmai.MaKM, khuyenmai.TenKM, khuyenmai.NgayBD, khuyenmai.NgayKT, khuyenmai.TrangThai FROM khuyenmai";
        ArrayList<String> conditions = new ArrayList<>();
        ArrayList<Object> params = new ArrayList<>();
    
        if (idOrTenSP != null && !idOrTenSP.isEmpty()) {
            sql += " JOIN chitietkm ON chitietkm.MaKM = khuyenmai.MaKM  JOIN sanpham ON sanpham.MaSP = chitietkm.MaSP ";
            conditions.add("(sanpham.MaSP = ? OR sanpham.TenSP LIKE ?)");
            params.add(idOrTenSP);
            params.add("%" + idOrTenSP + "%");
        }
    
        if ( idOrTenKM != null && !idOrTenKM.isEmpty()) {
            conditions.add("(khuyenmai.MaKM = ? OR khuyenmai.TenKM LIKE ?)");
            params.add(idOrTenKM);
            params.add("%" + idOrTenKM + "%");
        }
    
        if ( trangThai != null && !trangThai.isEmpty()) {
            conditions.add("khuyenmai.TrangThai = ?");
            params.add(trangThai);
        }
    
        if (ngayBD != null) {
            conditions.add("khuyenmai.NgayBD >= ?");
            params.add(new java.sql.Date(ngayBD.getTime()));
        }
    
        if (ngayKT != null) {
            conditions.add("khuyenmai.NgayKT <= ?");
            params.add(new java.sql.Date(ngayKT.getTime()));
        }
    
        if (!conditions.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", conditions);
        }
        if(sapXep != null && !sapXep.isEmpty()){
            sql += " ORDER BY "  + cot + " " + sapXep;
        }
    
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
    
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhuyenMaiDTO km = new KhuyenMaiDTO();
                km.setMaKM(rs.getInt("MaKM"));
                km.setTenKM(rs.getString("TenKM"));
                km.setNgayBD(rs.getDate("NgayBD"));
                km.setNgayKT(rs.getDate("NgayKT"));
                km.setTrangThai(rs.getString("TrangThai"));
                dsKM.add(km);
            }
    
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return dsKM;
    }
    

    public static KhuyenMaiDTO getDiscountToday() {
        String sql = "SELECT * FROM KhuyenMai WHERE ngayBD <= ? AND ngayKT >= ?";
        KhuyenMaiDTO discount = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            LocalDate today = LocalDate.now();
            pstmt.setDate(1, Date.valueOf(today));
            pstmt.setDate(2, Date.valueOf(today));
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

    public static KhuyenMaiDTO getDiscountById(int id) {
        String sql = "SELECT * FROM KhuyenMai WHERE maKM = ?";
        KhuyenMaiDTO khuyenMai = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                khuyenMai = new KhuyenMaiDTO(
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
        return khuyenMai;
    }
    public static int insertDiscount(KhuyenMaiDTO khuyenMai) {
        String sql = "INSERT INTO KhuyenMai (tenKM, ngayKT, ngayBD, trangThai) VALUES (?, ?, ?, ?)";
    
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    
            pstmt.setString(1, khuyenMai.getTenKM());
            pstmt.setDate(2, khuyenMai.getNgayKT());
            pstmt.setDate(3, khuyenMai.getNgayBD());
            pstmt.setString(4, khuyenMai.getTrangThai());
    
            int affectedRows = pstmt.executeUpdate();
    
            if (affectedRows == 0) {
                throw new SQLException("Thêm khuyến mãi thất bại, không có dòng nào được chèn.");
            }
    
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Trả về ID vừa tạo
                } else {
                    throw new SQLException("Thêm khuyến mãi thành công nhưng không lấy được ID.");
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    

    public static int updateDiscount(KhuyenMaiDTO khuyenMai) {
        String sql = "UPDATE KhuyenMai SET tenKM = ?, ngayKT = ?, ngayBD = ?, trangThai = ? WHERE maKM = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, khuyenMai.getTenKM());
            pstmt.setDate(2, khuyenMai.getNgayKT());
            pstmt.setDate(3, khuyenMai.getNgayBD());
            pstmt.setString(4, khuyenMai.getTrangThai());
            pstmt.setInt(5, khuyenMai.getMaKM());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int deleteDiscount(int maKM) {
        String sql = "DELETE FROM KhuyenMai WHERE maKM = ?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, maKM);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void printALLDiscount() {
        ArrayList<KhuyenMaiDTO> discountList = getAllDiscount();
        for (KhuyenMaiDTO khuyenMai : discountList) {
            System.out.println(khuyenMai.toString());
        }
    }

    public static void main(String[] args) {
        KhuyenMaiDTO km = getDiscountById(1);
        // System.out.print(getFilterDiscount("1", null, null, null, null).size());
    }
}