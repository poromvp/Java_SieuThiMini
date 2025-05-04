package DAL;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.PhieuNhapHangDTO;
import DTO.SanPhamDTO;
import DTO.SearchBanChayDTO;
import DTO.SearchLoHangDTO;
import DTO.SearchTonKhoDTO;
import JDBC.DBConnection;

public class BaoCaoKhoTongHopDAL {
    public static ArrayList<SearchBanChayDTO> getAllSPBanChay(Date from, Date to) {
        ArrayList<SearchBanChayDTO> DsSP = new ArrayList<>();
        String sql = "SELECT \r\n" + //
                "    sp.MaSP,\r\n" + //
                "    sp.TenSP,\r\n" + //
                "    lsp.TenLoaiSP,\r\n" + //
                "    SUM(ctdh.SoLuong) AS TongSoLuongBan,\r\n" + //
                "    GROUP_CONCAT(dh.MaDH) AS DanhSachDonHang\r\n" + //
                "FROM \r\n" + //
                "    donhang dh\r\n" + //
                "    INNER JOIN chitietdh ctdh ON dh.MaDH = ctdh.MaDH\r\n" + //
                "    INNER JOIN sanpham sp ON ctdh.MaSP = sp.MaSP\r\n" + //
                "    INNER JOIN loaisp lsp ON sp.MaLSP = lsp.MaLSP\r\n" + //
                "WHERE \r\n" + //
                "    dh.NgayTT BETWEEN ? AND ?\r\n" + //
                "GROUP BY \r\n" + //
                "    sp.MaSP, sp.TenSP, lsp.TenLoaiSP\r\n" + //
                "ORDER BY \r\n" + //
                "    TongSoLuongBan DESC;";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, from);
            stmt.setDate(2, to);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SearchBanChayDTO spbanchay = new SearchBanChayDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5));
                DsSP.add(spbanchay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DsSP;
    }

    public static ArrayList<SearchBanChayDTO> TimBanChay(SearchBanChayDTO search, Date from, Date to) {
        ArrayList<SearchBanChayDTO> DsSP = new ArrayList<>();
        ArrayList<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT \r\n" +
                "    sp.MaSP,\r\n" +
                "    sp.TenSP,\r\n" +
                "    lsp.TenLoaiSP,\r\n" +
                "    SUM(ctdh.SoLuong) AS TongSoLuongBan,\r\n" +
                "    GROUP_CONCAT(dh.MaDH) AS DanhSachDonHang\r\n" +
                "FROM \r\n" +
                "    donhang dh\r\n" +
                "    INNER JOIN chitietdh ctdh ON dh.MaDH = ctdh.MaDH\r\n" +
                "    INNER JOIN sanpham sp ON ctdh.MaSP = sp.MaSP\r\n" +
                "    INNER JOIN loaisp lsp ON sp.MaLSP = lsp.MaLSP\r\n");
        StringBuilder where = new StringBuilder("WHERE \r\n" +
                "    dh.NgayTT BETWEEN ? AND ?\r\n");
        param.add(from);
        param.add(to);
        if (search.getMaSP() != 0) {
            where.append("AND sp.MaSP = ?\n");
            param.add(search.getMaSP());
        }
        if (!search.getTenSP().trim().isEmpty()) {
            where.append("AND sp.TenSP = ?\n");
            param.add(search.getTenSP().trim());
        }
        if (search.getMaLSP() != 0) {
            where.append("AND sp.MaLSP = ?\n");
            param.add(search.getMaLSP());
        }
        if (!search.getTenLSP().trim().isEmpty()) {
            where.append("AND lsp.TenLoaiSP = ?\n");
            param.add(search.getTenLSP().trim());
        }
        StringBuilder having = new StringBuilder();
        if (search.getSLmin() != 0) {
            having.append("HAVING TongSoLuongBan >= ?\n");
            param.add(search.getSLmin());
        }
        if (search.getSLmax() != 0) {
            if (having.length() == 0) {
                having.append("HAVING TongSoLuongBan <= ?\n");
            } else {
                having.append("AND TongSoLuongBan <= ?\n");
            }
            param.add(search.getSLmax());
        }
        StringBuilder orderby = new StringBuilder("ORDER BY\n");
        String column;
        switch (search.getBy()) {
            case "Mã sản phẩm":
                column = "sp.MaSP";
                break;
            case "Tên sản phẩm":
                column = "sp.TenSP";
                break;
            case "Số lượng":
                column = "TongSoLuongBan";
                break;
            case "Mã loại sản phẩm":
                column = "sp.MaLSP";
                break;
            default:
                column = "TongSoLuongBan";
        }
        orderby.append(column).append(" ").append(search.getSort().equals("Giảm dần") ? "DESC" : "ASC").append(";");
        sql.append(where.toString())
                .append("GROUP BY sp.MaSP, sp.TenSP, lsp.TenLoaiSP\n")
                .append(having.toString())
                .append(orderby.toString());
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < param.size(); i++) {
                stmt.setObject(i + 1, param.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SearchBanChayDTO spbanchay = new SearchBanChayDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5));
                DsSP.add(spbanchay);
            }
        } catch (SQLException e) {
            // Ghi log hoặc thông báo lỗi thân thiện
            System.err.println("Lỗi SQL: " + e.getMessage());
        }
        return DsSP;
    }

    public static ArrayList<SanPhamDTO> TimTonKho(SearchTonKhoDTO search) {
        ArrayList<SanPhamDTO> DsSP = new ArrayList<>();
        ArrayList<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT \n" +
                "    sp.MaSP,\n" +
                "    sp.TenSP,\n" +
                "    sp.MaLSP,\n" +
                "    sp.SoLuongTon,\n" +
                "    sp.Gia,\n" +
                "    sp.MaNCC\n" +
                "FROM \n" +
                "    sanpham sp\n" +
                "    INNER JOIN loaisp lsp ON sp.MaLSP = lsp.MaLSP\n" +
                "    INNER JOIN nhancc ncc ON sp.MaNCC = ncc.MaNCC\n");
        StringBuilder where = new StringBuilder("WHERE 1=1\n"); // Điều kiện mặc định để dễ thêm AND

        // Thêm các điều kiện lọc
        if (search.getMaSP() != 0) {
            where.append("AND sp.MaSP = ?\n");
            param.add(search.getMaSP());
        }
        if (!search.getTenSP().trim().isEmpty()) {
            where.append("AND sp.TenSP LIKE ?\n");
            param.add("%" + search.getTenSP().trim() + "%"); // Sử dụng LIKE để tìm kiếm gần đúng
        }
        if (search.getMaLSP() != 0) {
            where.append("AND sp.MaLSP = ?\n");
            param.add(search.getMaLSP());
        }
        if (!search.getTenLSP().trim().isEmpty()) {
            where.append("AND lsp.TenLoaiSP LIKE ?\n");
            param.add("%" + search.getTenLSP().trim() + "%");
        }
        if (search.getSLmin() != 0) {
            where.append("AND sp.SoLuongTon >= ?\n");
            param.add(search.getSLmin());
        }
        if (search.getSLmax() != 0) {
            where.append("AND sp.SoLuongTon <= ?\n");
            param.add(search.getSLmax());
        }
        if (search.getGiamin() != 0) {
            where.append("AND sp.Gia >= ?\n");
            param.add(search.getGiamin());
        }
        if (search.getGiamax() != 0) {
            where.append("AND sp.Gia <= ?\n");
            param.add(search.getGiamax());
        }
        if (search.getMaNCC() != 0) {
            where.append("AND sp.MaNCC = ?\n");
            param.add(search.getMaNCC());
        }
        if (!search.getTenNCC().trim().isEmpty()) {
            where.append("AND ncc.TenNCC LIKE ?\n");
            param.add("%" + search.getTenNCC().trim() + "%");
        }

        // Thêm sắp xếp
        StringBuilder orderby = new StringBuilder("ORDER BY\n");
        String column;
        switch (search.getBy()) {
            case "Mã sản phẩm":
                column = "sp.MaSP";
                break;
            case "Số lượng tồn":
                column = "sp.SoLuongTon";
                break;
            case "Giá":
                column = "sp.Gia";
                break;
            case "Mã loại sản phẩm":
                column = "sp.MaLSP";
                break;
            case "Mã nhà cung cấp":
                column = "sp.MaNCC";
                break;
            default:
                column = "sp.MaSP"; // Mặc định sắp xếp theo MaSP
        }
        orderby.append(column).append(" ").append(search.getSort().equals("Giảm dần") ? "DESC" : "ASC").append(";");

        // Kết hợp câu truy vấn
        sql.append(where.toString()).append(orderby.toString());

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            // Gán tham số
            for (int i = 0; i < param.size(); i++) {
                stmt.setObject(i + 1, param.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setSoLuongTon(rs.getInt("SoLuongTon"));
                sp.setGia(rs.getInt("Gia"));
                sp.setMaNCC(rs.getInt("MaNCC"));
                sp.setMaLSP(rs.getInt("MaLSP"));
                DsSP.add(sp);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        }
        return DsSP;
    }

    public static ArrayList<PhieuNhapHangDTO> TimLoHang(SearchLoHangDTO search) {
        ArrayList<PhieuNhapHangDTO> DsPhieuNH = new ArrayList<>();
        ArrayList<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT \n" +
                "    pnh.MaPNH,\n" +
                "    pnh.TenPNH,\n" +
                "    pnh.NgayNhap,\n" +
                "    ct.GiaNhap,\n" +
                "    pnh.MaNV,\n" +
                "    nv.TenNV,\n" +
                "    pnh.MaNCC,\n" +
                "    ncc.TenNCC\n" +
                "FROM \n" +
                "    phieunhaphang pnh\n" +
                "    INNER JOIN nhanvien nv ON pnh.MaNV = nv.MaNV\n" +
                "    INNER JOIN nhancc ncc ON pnh.MaNCC = ncc.MaNCC\n" +
                "    LEFT JOIN chitietpnh ct ON pnh.MaPNH = ct.MaPNH\n" +
                "    LEFT JOIN sanpham sp ON ct.MaSP = sp.MaSP\n");
        StringBuilder where = new StringBuilder("WHERE 1=1\n");

        // Thêm các điều kiện lọc
        if (search.getMaDonNH() != 0) {
            where.append("AND pnh.MaPNH = ?\n");
            param.add(search.getMaDonNH());
        }
        if (!search.getTenDonNH().trim().isEmpty()) {
            where.append("AND pnh.TenPNH LIKE ?\n");
            param.add("%" + search.getTenDonNH().trim() + "%");
        }
        if (search.getNgayTu() != null) {
            where.append("AND pnh.NgayNhap >= ?\n");
            param.add(search.getNgayTu());
        }
        if (search.getNgayDen() != null) {
            where.append("AND pnh.NgayNhap <= ?\n");
            param.add(search.getNgayDen());
        }
        if (search.getMaNV() != 0) {
            where.append("AND pnh.MaNV = ?\n");
            param.add(search.getMaNV());
        }
        if (!search.getTenNV().trim().isEmpty()) {
            where.append("AND nv.TenNV LIKE ?\n");
            param.add("%" + search.getTenNV().trim() + "%");
        }
        if (search.getMaNCC() != 0) {
            where.append("AND pnh.MaNCC = ?\n");
            param.add(search.getMaNCC());
        }
        if (!search.getTenNCC().trim().isEmpty()) {
            where.append("AND ncc.TenNCC LIKE ?\n");
            param.add("%" + search.getTenNCC().trim() + "%");
        }
        if (search.getGiaMin() != 0) {
            where.append("AND ct.GiaNhap >= ?\n");
            param.add(search.getGiaMin());
        }
        if (search.getGiaMax() != 0) {
            where.append("AND ct.GiaNhap <= ?\n");
            param.add(search.getGiaMax());
        }
        if (search.getMaSP() != 0) {
            where.append("AND ct.MaSP = ?\n");
            param.add(search.getMaSP());
        }
        if (!search.getTenSP().trim().isEmpty()) {
            where.append("AND sp.TenSP LIKE ?\n");
            param.add("%" + search.getTenSP().trim() + "%");
        }
        if (search.getMaLH() != 0) {
            where.append("AND ct.MaLH = ?\n");
            param.add(search.getMaLH());
        }

        // Thêm sắp xếp
        StringBuilder orderby = new StringBuilder("ORDER BY\n");
        String column;
        switch (search.getBy()) {
            case "Mã đơn nhập":
                column = "pnh.MaPNH";
                break;
            case "Ngày nhập":
                column = "pnh.NgayNhap";
                break;
            case "Tổng giá":
                column = "ct.GiaNhap";
                break;
            case "Mã nhân viên":
                column = "pnh.MaNV";
                break;
            case "Mã nhà cung cấp":
                column = "pnh.MaNCC";
                break;
            default:
                column = "pnh.MaPNH"; // Mặc định sắp xếp theo MaPhieuNH
        }
        orderby.append(column).append(" ").append(search.getOrder().equals("Giảm dần") ? "DESC" : "ASC").append(";");

        // Kết hợp câu truy vấn
        sql.append(where.toString()).append("GROUP BY pnh.MaPNH\n").append(orderby.toString());

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            // Gán tham số
            for (int i = 0; i < param.size(); i++) {
                stmt.setObject(i + 1, param.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PhieuNhapHangDTO pnh = new PhieuNhapHangDTO();
                pnh.setMaPNH(rs.getInt("MaPNH"));
                pnh.setTenPNH(rs.getString("TenPNH"));
                pnh.setNgayNhap(rs.getDate("NgayNhap"));
                pnh.setMaNV(rs.getInt("MaNV"));
                pnh.setMaNCC(rs.getInt("MaNCC"));
                DsPhieuNH.add(pnh);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        }
        return DsPhieuNH;
    }

    public static String tongSoHangNhapTrongThang() {
        String sum = new String();
        String sql = "SELECT SUM(ctpn.SoLuong) AS tong\n"
                + "FROM chitietpnh ctpn\n"
                + "INNER JOIN phieunhaphang pnh ON pnh.MaPNH = ctpn.MaPNH\n"
                + "WHERE pnh.NgayNhap BETWEEN DATE_FORMAT(CURDATE(), '%Y-%m-01') AND CURDATE()\n";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
                    ResultSet rs = stmt.executeQuery();
                    if(rs.next()){
                        sum = rs.getInt(1)+"";
                    }
        } catch (SQLException e) {
            System.err.println("Lỗi sql: " + e.getMessage());
        }
        return sum;
    }
}
