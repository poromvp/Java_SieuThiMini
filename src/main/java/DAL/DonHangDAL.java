package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import DTO.DonHangDTO;
import DTO.SearchFilterDTO;
import JDBC.DBConnection;

public class DonHangDAL {

    // Lấy tất cả đơn hàng từ CSDL
    public static ArrayList<DonHangDTO> getAllOrder() {
        ArrayList<DonHangDTO> dsDonHang = new ArrayList<>();
        String sql = "SELECT * FROM donhang";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DonHangDTO dh = new DonHangDTO(
                        rs.getInt("maDH"),
                        rs.getInt("maKH"),
                        rs.getInt("maKM"),
                        rs.getInt("maNV"),
                        rs.getString("PTTToan"),
                        rs.getString("NgayTT"),
                        rs.getInt("maDTL"),
                        rs.getInt("tienKD"),
                        rs.getInt("TongTien"),
                        rs.getString("trangThai"));
                dsDonHang.add(dh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDonHang;
    }

    public static ArrayList<ArrayList<Object>> getFindSortOrder(
            ArrayList<String> whereConditions,
            ArrayList<String> having,
            ArrayList<Object> param,
            String orderBy,
            String orderType,
            Integer limit) {
        ArrayList<ArrayList<Object>> result = new ArrayList<>();

        String sql = "SELECT donhang.MaDH, donhang.MaNV, nhanvien.TenNV, \n" +
                "       donhang.NgayTT, donhang.TrangThai, thethanhvien.TenTV, \n" +
                "       SUM(chitietdh.SoLuong * (sanpham.Gia * (1 - IFNULL(chitietkm.TileGiam, 0) / 100))) \n" +
                "       - \n" +
                "       CASE \n" +
                "           WHEN diemtichluy.TiLeGiam IS NOT NULL THEN \n" +
                "               LEAST(\n" +
                "                   SUM(chitietdh.SoLuong * (sanpham.Gia * (1 - IFNULL(chitietkm.TileGiam, 0) / 100))) * diemtichluy.TiLeGiam / 100,\n"
                +
                "                   diemtichluy.GiamMax\n" +
                "               )\n" +
                "           ELSE 0\n" +
                "       END AS tongTien\n" +

                "FROM sieuthimini.donhang\n" +
                "JOIN nhanvien ON nhanvien.MaNV = donhang.MaNV\n" +
                "LEFT JOIN thethanhvien ON thethanhvien.MaTV = donhang.MaKH\n" +
                "LEFT JOIN chitietdh ON donhang.MaDH = chitietdh.MaDH\n" +
                "LEFT JOIN sanpham ON sanpham.MaSP = chitietdh.MaSP\n" +
                "LEFT JOIN chitietkm ON chitietkm.MaSP = sanpham.MaSP AND chitietkm.MaKM = donhang.MaKM\n" +
                "LEFT JOIN khuyenmai ON donhang.MaKM = khuyenmai.MaKM\n" +
                "LEFT JOIN diemtichluy ON donhang.MaDTL = diemtichluy.MaDTL";

        // WHERE
        if (!whereConditions.isEmpty()) {
            sql += " WHERE ";
            for (int i = 0; i < whereConditions.size(); i++) {
                sql += whereConditions.get(i);
                if (i != whereConditions.size() - 1) {
                    sql += " AND ";
                }
            }
        }

        // GROUP BY

        sql += " GROUP BY donhang.MaDH ";

        // HAVING
        if (!having.isEmpty()) {
            sql += " HAVING ";
            for (int i = 0; i < having.size(); i++) {
                sql += having.get(i);
                if (i != having.size() - 1) {
                    sql += " AND ";
                }
            }
        }

        // ORDER BY
        if (orderBy != null && !orderBy.isEmpty()) {
            sql += " ORDER BY " + orderBy + " " + (orderType != null ? orderType : "ASC");
        }

        if (limit != null) {
            sql += " LIMIT ?";
        }

        try {
            PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);

            for (int i = 0; i < param.size(); i++) {
                stmt.setObject(i + 1, param.get(i));
            }

            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ArrayList<Object> row = new ArrayList<>();
                row.add(rs.getInt("MaDH"));
                row.add(rs.getString("MaNV"));
                row.add(rs.getString("TenNV"));
                row.add(rs.getDate("NgayTT"));
                row.add(rs.getString("TrangThai"));
                row.add(rs.getString("TenTV"));
                row.add(rs.getDouble("tongTien"));

                result.add(row);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // In kết quả để debug (tuỳ bạn có giữ không)
        for (ArrayList<Object> row : result) {
            for (Object val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }

        return result;
    }

    public static int tinhTongTienByMaDonHang(int maDH) {
        String sql = "SELECT " +
                "SUM(ChiTietDH.SoLuong * (SanPham.Gia * (1 - IFNULL(ChiTietKM.TileGiam, 0) / 100))) AS tongTien " +
                "FROM DonHang " +
                "JOIN TheThanhVien ON DonHang.MaKH = TheThanhVien.MaTV " +
                "LEFT JOIN ChiTietDH ON ChiTietDH.MaDH = DonHang.MaDH " +
                "LEFT JOIN SanPham ON SanPham.MaSP = ChiTietDH.MaSP " +
                "LEFT JOIN ChiTietKM ON ChiTietKM.MaSP = SanPham.MaSP AND ChiTietKM.MaKM = DonHang.MaKM " +
                "LEFT JOIN KhuyenMai ON KhuyenMai.MaKM = DonHang.MaKM " +
                "LEFT JOIN DiemTichLuy ON DonHang.MaDTL = DiemTichLuy.MaDTL " +
                "WHERE DonHang.MaDH = ?;";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maDH);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int tongTien = rs.getInt("tongTien");
                return tongTien;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Lấy ds đơn hàng theo ID Thành Viên
    public static ArrayList<DonHangDTO> getOrderByKH(int maKH) {
        ArrayList<DonHangDTO> dsDonHang = new ArrayList<>();
        String sql = "SELECT * FROM DonHang WHERE maKH = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maKH);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DonHangDTO dh = new DonHangDTO(
                        rs.getInt("maDH"),
                        rs.getInt("maKH"),
                        rs.getInt("maKM"),
                        rs.getInt("maNV"),
                        rs.getString("PTTToan"),
                        rs.getString("NgayTT"),
                        rs.getInt("maDTL"),
                        rs.getInt("tienKD"),
                        rs.getInt("TongTien"),
                        rs.getString("trangThai"));
                dsDonHang.add(dh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDonHang;
    }

    // Lấy ds đơn hàng theo ID Nhân Viên
    public static ArrayList<DonHangDTO> getOrderByNV(int maNV) {
        ArrayList<DonHangDTO> dsDonHang = new ArrayList<>();
        String sql = "SELECT * FROM DonHang WHERE maNV = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maNV);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DonHangDTO dh = new DonHangDTO(
                        rs.getInt("maDH"),
                        rs.getInt("maKH"),
                        rs.getInt("maKM"),
                        rs.getInt("maNV"),
                        rs.getString("PTTToan"),
                        rs.getString("NgayTT"),
                        rs.getInt("maDTL"),
                        rs.getInt("tienKD"),
                        rs.getInt("TongTien"),
                        rs.getString("trangThai"));
                dsDonHang.add(dh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDonHang;
    }

    // Lấy đơn hàng theo ID
    public static DonHangDTO getOrderById(int maDH) {
        String sql = "SELECT * FROM DonHang WHERE maDH = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maDH);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new DonHangDTO(
                        rs.getInt("maDH"),
                        rs.getInt("maKH"),
                        rs.getInt("maKM"),
                        rs.getInt("maNV"),
                        rs.getString("PTTToan"),
                        rs.getString("NgayTT"),
                        rs.getInt("maDTL"),
                        rs.getInt("tienKD"),
                        rs.getInt("TongTien"),
                        rs.getString("trangThai"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm đơn hàng mới
    public static int insertOrder(DonHangDTO dh) {
        int generatedId = -1;
        String sql = "INSERT INTO DonHang(maKH, maKM, maNV, PTTToan, NgayTT, tienKD, TongTien, trangThai, maDTL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (dh.getMaKH() != null) {
                stmt.setInt(1, dh.getMaKH());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }

            if (dh.getMaKM() != null) {
                stmt.setInt(2, dh.getMaKM());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }

            stmt.setInt(3, dh.getMaNV());
            stmt.setString(4, dh.getPtThanhToan());

            stmt.setString(5, dh.getNgayTT());

            stmt.setDouble(6, dh.getTienKD());
            stmt.setDouble(7, dh.getTongTien());
            stmt.setString(8, dh.getTrangThai());

            if (dh.getMaDTL() != null) {
                stmt.setInt(9, dh.getMaDTL());
            } else {
                stmt.setNull(9, Types.INTEGER);
            }

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    // Cập nhật đơn hàng
    public static int updateOrder(DonHangDTO dh) {
        String sql = "UPDATE DonHang SET maKH=?, maKM=?, maNV=?, NgayTT=?, trangThai=? WHERE maDH=?";
        return DBConnection.executeUpdate(sql, dh.getMaKH(), dh.getMaKM(), dh.getMaNV(), dh.getNgayTT(),
                dh.getTrangThai(), dh.getMaDH());
    }

    // Xóa đơn hàng
    public static int deleteOrder(int maDH) {
        String sql = "DELETE FROM DonHang WHERE maDH=?";
        return DBConnection.executeUpdate(sql, maDH);
    }

    public static int countOrder() {
        String sql = "SELECT COUNT(maDH) FROM DonHang";
        int count = 0;

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1); // Lấy giá trị COUNT từ cột đầu tiên
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void printAllOrder() {
        ArrayList<DonHangDTO> orderList = getAllOrder();
        for (DonHangDTO donHang : orderList) {
            System.out.println(donHang.toString());
        }
    }

    public static void main(String[] args) {
        // ArrayList<String> where = new ArrayList<>();
        // ArrayList<String> having = new ArrayList<>();
        // ArrayList<Object> params = new ArrayList<>();

        // String orderBy = null;
        // String orderType = null;
        // Integer limit = null;

        // getFindSortOrder(where, having, params, orderBy, orderType, limit);
        System.out.println(tinhTongTienByMaDonHang(1));
    }

    // TimThK
    public static ArrayList<DonHangDTO> getOrderByThK(SearchFilterDTO search) {
        ArrayList<DonHangDTO> dsDonHang = new ArrayList<>();
        ArrayList<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT * FROM donhang"
                        + " JOIN chitietdh ON donhang.maDH = chitietdh.maDH"
                        + " WHERE 1 = 1");

        if (search.getMaDH() != 0) {
            sql.append(" AND donhang.maDH = ?");
            params.add(search.getMaDH());
        }

        if (search.getMaSP() != 0) {
            sql.append(" AND maSP = ?");
            params.add(search.maSP);
        }

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DonHangDTO dh = new DonHangDTO(
                        rs.getInt("maDH"),
                        rs.getInt("maKH"),
                        rs.getInt("maKM"),
                        rs.getInt("maNV"),
                        rs.getString("PTTToan"),
                        rs.getString("NgayTT"),
                        rs.getInt("maDTL"),
                        rs.getInt("tienKD"),
                        rs.getInt("TongTien"),
                        rs.getString("trangThai"));
                dsDonHang.add(dh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsDonHang;
    }
}
