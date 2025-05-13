package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import DTO.DonHangDTO;
import DTO.NhanVienDTO;
import DTO.SearchNVDHDTO;
import DTO.SearchNhanVienDTO;
import JDBC.DBConnection;

public class BaoCaoNhanVienDAL {
    public static ArrayList<NhanVienDTO> getTopNhanVienByDoanhSo(Date from, Date to) {
        ArrayList<NhanVienDTO> nhanVienList = new ArrayList<>();
        String sql = "SELECT nv.MaNV, nv.TenNV, nv.GioiTinh, nv.NgaySinh, nv.CCCD, nv.DiaChi, nv.SDT, nv.Luong, nv.TrangThai, nv.image, "
                +
                "COALESCE(SUM(dh.TongTien), 0) AS DoanhSo " +
                "FROM nhanvien nv " +
                "LEFT JOIN donhang dh ON nv.MaNV = dh.MaNV " +
                "WHERE dh.NgayTT BETWEEN ? AND ? " +
                "GROUP BY nv.MaNV, nv.TenNV, nv.GioiTinh, nv.NgaySinh, nv.CCCD, nv.DiaChi, nv.SDT, nv.Luong, nv.TrangThai, nv.image "
                +
                "ORDER BY DoanhSo DESC";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, from);
            stmt.setDate(2, to);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                NhanVienDTO nhanVien = new NhanVienDTO();
                nhanVien.setMaNV(rs.getInt("MaNV"));
                nhanVien.setTenNV(rs.getString("TenNV"));
                nhanVien.setGioiTinh(rs.getString("GioiTinh"));
                nhanVien.setNgaySinh(rs.getDate("NgaySinh"));
                nhanVien.setCCCD(rs.getString("CCCD"));
                nhanVien.setDiaChi(rs.getString("DiaChi"));
                nhanVien.setSDT(rs.getString("SDT"));
                nhanVien.setLuong(rs.getDouble("Luong"));
                nhanVien.setTrangThai(rs.getInt("TrangThai"));
                nhanVien.setImage(rs.getString("image"));
                nhanVienList.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nhanVienList;
    }

    public static ArrayList<NhanVienDTO> TimTotNhat(SearchNhanVienDTO search, Date from, Date to) {
        ArrayList<NhanVienDTO> nhanVienList = new ArrayList<>();
        ArrayList<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT nv.MaNV, nv.TenNV, nv.GioiTinh, nv.NgaySinh, nv.CCCD, nv.DiaChi, nv.SDT, nv.Luong, nv.TrangThai, nv.image, "
                        +
                        "COALESCE(SUM(dh.TongTien), 0) AS DoanhSo, COUNT(dh.MaDH) AS TongDonHang " +
                        "FROM nhanvien nv " +
                        "LEFT JOIN donhang dh ON nv.MaNV = dh.MaNV ");
        StringBuilder where = new StringBuilder("WHERE dh.NgayTT BETWEEN ? AND ?");
        param.add(from);
        param.add(to);

        // Thêm điều kiện lọc
        if (search.getMaNV() != 0) {
            where.append("AND nv.MaNV = ? ");
            param.add(search.getMaNV());
        }
        if (!search.getTenNV().trim().isEmpty()) {
            where.append("AND nv.TenNV LIKE ? ");
            param.add("%" + search.getTenNV().trim() + "%");
        }
        if (!search.getDiaChi().trim().isEmpty()) {
            where.append("AND nv.DiaChi LIKE ? ");
            param.add("%" + search.getDiaChi().trim() + "%");
        }
        if (search.getNgaySinhFrom() != null) {
            where.append("AND nv.NgaySinh >= ? ");
            param.add(search.getNgaySinhFrom());
        }
        if (search.getNgaySinhTo() != null) {
            where.append("AND nv.NgaySinh <= ? ");
            param.add(search.getNgaySinhTo());
        }
        if (!search.getSDT().trim().isEmpty()) {
            where.append("AND nv.SDT = ? ");
            param.add(search.getSDT().trim());
        }
        if (search.getMaDH() != 0) {
            where.append("AND dh.MaDH = ? ");
            param.add(search.getMaDH());
        }
        if (search.getLuongMin() != 0) {
            where.append("AND nv.Luong >= ? ");
            param.add(search.getLuongMin());
        }
        if (search.getLuongMax() != 0) {
            where.append("AND nv.Luong <= ? ");
            param.add(search.getLuongMax());
        }

        // Thêm điều kiện HAVING
        StringBuilder having = new StringBuilder();
        if (search.getTongDHMin() != 0) {
            having.append("HAVING TongDonHang >= ? ");
            param.add(search.getTongDHMin());
        }
        if (search.getTongDHMax() != 0) {
            if (having.length() == 0) {
                having.append("HAVING TongDonHang <= ? ");
            } else {
                having.append("AND TongDonHang <= ? ");
            }
            param.add(search.getTongDHMax());
        }
        if (search.getDoanhSoMin() != 0) {
            if (having.length() == 0) {
                having.append("HAVING DoanhSo >= ? ");
            } else {
                having.append("AND DoanhSo >= ? ");
            }
            param.add(search.getDoanhSoMin());
        }
        if (search.getDoanhSoMax() != 0) {
            if (having.length() == 0) {
                having.append("HAVING DoanhSo <= ? ");
            } else {
                having.append("AND DoanhSo <= ? ");
            }
            param.add(search.getDoanhSoMax());
        }

        // Thêm sắp xếp
        StringBuilder orderby = new StringBuilder("ORDER BY ");
        String column;
        switch (search.getBy()) {
            case "Mã nhân viên":
                column = "nv.MaNV";
                break;
            case "Tên nhân viên":
                column = "nv.TenNV";
                break;
            case "Ngày sinh":
                column = "nv.NgaySinh";
                break;
            case "Tổng đơn hàng":
                column = "TongDonHang";
                break;
            case "Doanh số":
                column = "DoanhSo";
                break;
            case "Lương":
                column = "nv.Luong";
                break;
            default:
                column = "DoanhSo";
        }
        orderby.append(column).append(" ").append(search.getOrder().equals("Giảm dần") ? "DESC" : "ASC");

        // Kết hợp câu truy vấn
        sql.append(where).append(
                "GROUP BY nv.MaNV, nv.TenNV, nv.GioiTinh, nv.NgaySinh, nv.CCCD, nv.DiaChi, nv.SDT, nv.Luong, nv.TrangThai, nv.image ")
                .append(having).append(orderby);

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            // Gán tham số
            for (int i = 0; i < param.size(); i++) {
                stmt.setObject(i + 1, param.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhanVienDTO nhanVien = new NhanVienDTO();
                nhanVien.setMaNV(rs.getInt("MaNV"));
                nhanVien.setTenNV(rs.getString("TenNV"));
                nhanVien.setGioiTinh(rs.getString("GioiTinh"));
                nhanVien.setNgaySinh(rs.getDate("NgaySinh"));
                nhanVien.setCCCD(rs.getString("CCCD"));
                nhanVien.setDiaChi(rs.getString("DiaChi"));
                nhanVien.setSDT(rs.getString("SDT"));
                nhanVien.setLuong(rs.getDouble("Luong"));
                nhanVien.setTrangThai(rs.getInt("TrangThai"));
                nhanVien.setImage(rs.getString("image"));
                nhanVienList.add(nhanVien);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        }
        return nhanVienList;
    }

    public static ArrayList<NhanVienDTO> TimNhanVien(SearchNhanVienDTO search) {
        ArrayList<NhanVienDTO> nhanVienList = new ArrayList<>();
        ArrayList<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT nv.MaNV, nv.TenNV, nv.GioiTinh, nv.NgaySinh, nv.CCCD, nv.DiaChi, nv.SDT, nv.Luong, nv.TrangThai, nv.image, "
                        +
                        "COALESCE(COUNT(dh.MaDH), 0) AS TongDonHang, COALESCE(SUM(dh.TongTien), 0) AS DoanhSo " +
                        "FROM nhanvien nv " +
                        "LEFT JOIN donhang dh ON nv.MaNV = dh.MaNV ");
        StringBuilder where = new StringBuilder("WHERE nv.TrangThai = 1 ");

        // Thêm điều kiện lọc
        if (search.getMaNV() != 0) {
            where.append("AND nv.MaNV = ? ");
            param.add(search.getMaNV());
        }
        if (!search.getTenNV().trim().isEmpty()) {
            where.append("AND nv.TenNV LIKE ? ");
            param.add("%" + search.getTenNV().trim() + "%");
        }
        if (!search.getDiaChi().trim().isEmpty()) {
            where.append("AND nv.DiaChi LIKE ? ");
            param.add("%" + search.getDiaChi().trim() + "%");
        }
        if (search.getNgaySinhFrom() != null) {
            where.append("AND nv.NgaySinh >= ? ");
            param.add(search.getNgaySinhFrom());
        }
        if (search.getNgaySinhTo() != null) {
            where.append("AND nv.NgaySinh <= ? ");
            param.add(search.getNgaySinhTo());
        }
        if (!search.getSDT().trim().isEmpty()) {
            where.append("AND nv.SDT = ? ");
            param.add(search.getSDT().trim());
        }
        if (search.getMaDH() != 0) {
            where.append("AND dh.MaDH = ? ");
            param.add(search.getMaDH());
        }
        if (search.getLuongMin() != 0) {
            where.append("AND nv.Luong >= ? ");
            param.add(search.getLuongMin());
        }
        if (search.getLuongMax() != 0) {
            where.append("AND nv.Luong <= ? ");
            param.add(search.getLuongMax());
        }

        // Thêm điều kiện HAVING
        StringBuilder having = new StringBuilder();
        if (search.getTongDHMin() != 0) {
            having.append("HAVING TongDonHang >= ? ");
            param.add(search.getTongDHMin());
        }
        if (search.getTongDHMax() != 0) {
            if (having.length() == 0) {
                having.append("HAVING TongDonHang <= ? ");
            } else {
                having.append("AND TongDonHang <= ? ");
            }
            param.add(search.getTongDHMax());
        }
        if (search.getDoanhSoMin() != 0) {
            if (having.length() == 0) {
                having.append("HAVING DoanhSo >= ? ");
            } else {
                having.append("AND DoanhSo >= ? ");
            }
            param.add(search.getDoanhSoMin());
        }
        if (search.getDoanhSoMax() != 0) {
            if (having.length() == 0) {
                having.append("HAVING DoanhSo <= ? ");
            } else {
                having.append("AND DoanhSo <= ? ");
            }
            param.add(search.getDoanhSoMax());
        }

        // Thêm sắp xếp
        StringBuilder orderby = new StringBuilder("ORDER BY ");
        String column;
        switch (search.getBy()) {
            case "Mã nhân viên":
                column = "nv.MaNV";
                break;
            case "Tên nhân viên":
                column = "nv.TenNV";
                break;
            case "Ngày sinh":
                column = "nv.NgaySinh";
                break;
            case "Tổng đơn hàng":
                column = "TongDonHang";
                break;
            case "Doanh số":
                column = "DoanhSo";
                break;
            case "Lương":
                column = "nv.Luong";
                break;
            default:
                column = "nv.MaNV";
        }
        orderby.append(column).append(" ").append(search.getOrder().equals("Giảm dần") ? "DESC" : "ASC");

        // Kết hợp câu truy vấn
        sql.append(where)
                .append("GROUP BY nv.MaNV, nv.TenNV, nv.GioiTinh, nv.NgaySinh, nv.CCCD, nv.DiaChi, nv.SDT, nv.Luong, nv.TrangThai, nv.image ")
                .append(having).append(orderby);

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            // Gán tham số
            for (int i = 0; i < param.size(); i++) {
                stmt.setObject(i + 1, param.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhanVienDTO nhanVien = new NhanVienDTO();
                nhanVien.setMaNV(rs.getInt("MaNV"));
                nhanVien.setTenNV(rs.getString("TenNV"));
                nhanVien.setGioiTinh(rs.getString("GioiTinh"));
                nhanVien.setNgaySinh(rs.getDate("NgaySinh"));
                nhanVien.setCCCD(rs.getString("CCCD"));
                nhanVien.setDiaChi(rs.getString("DiaChi"));
                nhanVien.setSDT(rs.getString("SDT"));
                nhanVien.setLuong(rs.getDouble("Luong"));
                nhanVien.setTrangThai(rs.getInt("TrangThai"));
                nhanVien.setImage(rs.getString("image"));
                nhanVienList.add(nhanVien);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        }
        return nhanVienList;
    }

    public static ArrayList<DonHangDTO> TimNVDH(SearchNVDHDTO search, int MaNV) {
        ArrayList<DonHangDTO> DsDH = new ArrayList<>();
        ArrayList<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT dh.MaDH, " +
                        "dh.MaKH, " +
                        "dh.MaKM, " +
                        "dh.MaNV, " +
                        "dh.PTTToan, " +
                        "dh.NgayTT, " +
                        "dh.maDTL, " +
                        "dh.tienKD, " +
                        "dh.TrangThai, " +
                        "dh.TongTien \n" +
                        "FROM donhang dh\n" +
                        "LEFT JOIN thethanhvien kh ON kh.MaTV = dh.MaKH\n" +
                        "LEFT JOIN khuyenmai km ON km.MaKM = dh.MaKM\n" +
                        "LEFT JOIN chitietdh ctdh ON ctdh.MaDH = dh.MaDH\n" +
                        "LEFT JOIN sanpham sp ON sp.MaSP = ctdh.MaSP\n" +
                        "LEFT JOIN loaisp lsp ON lsp.MaLSP = sp.MaLSP\n");
        StringBuilder where = new StringBuilder(
                "WHERE dh.MaNV = ?\n");
        param.add(MaNV);

        if (search.getMaDH() != 0) {
            where.append("AND dh.MaDH = ?\n");
            param.add(search.getMaDH());
        }
        if (search.getNgayTTfrom() != null) {
            where.append("AND dh.NgayTT >= ?\n");
            param.add(search.getNgayTTfrom());
        }
        if (search.getNgayTTto() != null) {
            where.append("AND dh.NgayTT <= ?\n");
            param.add(search.getNgayTTto());
        }
        if (search.getTienMin() != 0) {
            where.append("AND dh.TongTien >= ?\n");
            param.add(search.getTienMin());
        }
        if (search.getTienMax() != 0) {
            where.append("AND dh.TongTien <= ?\n");
            param.add(search.getTienMax());
        }
        switch (search.getPTTT()) {
            case "CASH":
                where.append("AND dh.PTTToan = ?\n");
                param.add(search.getPTTT());
                break;
            case "BANK":
                where.append("AND dh.PTTToan = ?\n");
                param.add(search.getPTTT());
                break;
            default:
                break;
        }
        if (search.getMaKH() != 0) {
            where.append("AND dh.MaKH = ?\n");
            param.add(search.getMaKH());
        }
        if (!search.getTenKH().trim().isEmpty()) {
            where.append("AND kh.TenTV LIKE ?\n");
            param.add("%" + search.getTenKH().trim() + "%");
        }
        if (search.getMaKM() != 0) {
            where.append("AND dh.MaKM = ?\n");
            param.add(search.getMaKM());
        }
        if (!search.getTenKM().trim().isEmpty()) {
            where.append("AND km.TenKM LIKE ?\n");
            param.add("%" + search.getTenKM().trim() + "%");
        }
        if (search.getMaSP() != 0) {
            where.append("AND ctdh.MaSP = ?\n");
            param.add(search.getMaSP());
        }
        if (!search.getTenSP().trim().isEmpty()) {
            where.append("AND sp.TenSP LIKE ?\n");
            param.add("%" + search.getTenSP().trim() + "%");
        }
        if (search.getMaLSP() != 0) {
            where.append("AND sp.MaLSP = ?\n");
            param.add(search.getMaLSP());
        }
        if (!search.getTenLSP().trim().isEmpty()) {
            where.append("AND lsp.TenLoaiSP LIKE ?\n");
            param.add("%" + search.getTenLSP().trim() + "%");
        }
        String groupby = "GROUP BY \n" + 
                        "   dh.MaDH, dh.MaKH, dh.MaKM, dh.MaNV, dh.PTTToan, dh.NgayTT, dh.maDTL, dh.tienKD, dh.TrangThai, dh.TongTien\n";
        StringBuilder orderby = new StringBuilder("ORDER BY\n");
        switch (search.getBy()) {
            case "Ngày TT":
                orderby.append("dh.NgayTT ");
                break;
            case "Thành tiền":
                orderby.append("dh.TongTien ");
                break;
            case "Mã khách hàng":
                orderby.append("dh.MaKH ");
                break;
            case "Mã khuyến mãi":
                orderby.append("dh.MaKM ");
                break;
            default:
                orderby.append("dh.MaDH ");
                break;
        }
        orderby.append(search.getOrder().equals("Giảm dần") ? "DESC" : "ASC");
        sql.append(where.toString()).append(groupby).append(orderby.toString());
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < param.size(); i++) {
                stmt.setObject(i + 1, param.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DonHangDTO dh = new DonHangDTO(
                        rs.getInt("MaDH"),
                        rs.getInt("MaKH"),
                        rs.getInt("MaKM"),
                        MaNV,
                        rs.getString("PTTToan"),
                        rs.getString("NgayTT"),
                        rs.getInt("maDTL"),
                        rs.getInt("tienKD"),
                        rs.getInt("TongTien"),
                        rs.getString("trangThai"));
                DsDH.add(dh);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi sql: " + e.getMessage());
        }
        return DsDH;
    }
}
