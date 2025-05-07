package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.DonHangDTO;
import DTO.SearchKHDHDTO;
import DTO.SearchTheThanhVienDTO;
import DTO.TheThanhVienDTO;
import JDBC.DBConnection;

public class BaoCaoKhachHangDAL {
    public static ArrayList<TheThanhVienDTO> Standard() {
        ArrayList<TheThanhVienDTO> DsKH = new ArrayList<>();
        String sql = "SELECT kh.MaTV, kh.TenTV, kh.NgaySinh, kh.DiaChi, kh.DiemTL, kh.SDT, kh.NgayBD, kh.NgayKT, kh.TenAnh, kh.TrangThai, SUM(dh.TongTien) AS tong\n"
                + //
                "FROM donhang dh\n" + //
                "INNER JOIN thethanhvien kh ON kh.MaTV = dh.MaKH\n" + //
                "WHERE kh.TrangThai = 'ACTIVE'\n" + //
                "GROUP BY kh.MaTV, kh.TenTV\n" + //
                "HAVING SUM(dh.TongTien) < 1000000";
        try (Connection connect = DBConnection.getConnection();
                PreparedStatement stmt = connect.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TheThanhVienDTO kh = new TheThanhVienDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getString(10));
                DsKH.add(kh);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        }
        return DsKH;
    }

    public static ArrayList<TheThanhVienDTO> TiemNang() {
        ArrayList<TheThanhVienDTO> DsKH = new ArrayList<>();
        String sql = "SELECT kh.MaTV, kh.TenTV, kh.NgaySinh, kh.DiaChi, kh.DiemTL, kh.SDT, kh.NgayBD, kh.NgayKT, kh.TenAnh, kh.TrangThai, SUM(dh.TongTien) AS tong\r\n"
                + //
                "FROM donhang dh\r\n" + //
                "INNER JOIN thethanhvien kh ON kh.MaTV = dh.MaKH\r\n" + //
                "WHERE kh.TrangThai = 'ACTIVE'\r\n" + //
                "GROUP BY kh.MaTV, kh.TenTV\r\n" + //
                "HAVING SUM(dh.TongTien) BETWEEN 1000000 AND 3000000";
        try (Connection connect = DBConnection.getConnection();
                PreparedStatement stmt = connect.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TheThanhVienDTO kh = new TheThanhVienDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getString(10));
                DsKH.add(kh);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        }
        return DsKH;
    }

    public static ArrayList<TheThanhVienDTO> VIP() {
        ArrayList<TheThanhVienDTO> DsKH = new ArrayList<>();
        String sql = "SELECT kh.MaTV, kh.TenTV, kh.NgaySinh, kh.DiaChi, kh.DiemTL, kh.SDT, kh.NgayBD, kh.NgayKT, kh.TenAnh, kh.TrangThai, SUM(dh.TongTien) AS tong\r\n"
                + //
                "FROM donhang dh\r\n" + //
                "INNER JOIN thethanhvien kh ON kh.MaTV = dh.MaKH\r\n" + //
                "WHERE kh.TrangThai = 'ACTIVE'\r\n" + //
                "GROUP BY kh.MaTV, kh.TenTV\r\n" + //
                "HAVING SUM(dh.TongTien) BETWEEN 3000000 AND 10000000";
        try (Connection connect = DBConnection.getConnection();
                PreparedStatement stmt = connect.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TheThanhVienDTO kh = new TheThanhVienDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getString(10));
                DsKH.add(kh);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        }
        return DsKH;
    }

    public static ArrayList<TheThanhVienDTO> Diamond() {
        ArrayList<TheThanhVienDTO> DsKH = new ArrayList<>();
        String sql = "SELECT kh.MaTV, kh.TenTV, kh.NgaySinh, kh.DiaChi, kh.DiemTL, kh.SDT, kh.NgayBD, kh.NgayKT, kh.TenAnh, kh.TrangThai, SUM(dh.TongTien) AS tong\r\n"
                + //
                "FROM donhang dh\r\n" + //
                "INNER JOIN thethanhvien kh ON kh.MaTV = dh.MaKH\r\n" + //
                "WHERE kh.TrangThai = 'ACTIVE'\r\n" + //
                "GROUP BY kh.MaTV, kh.TenTV\r\n" + //
                "HAVING SUM(dh.TongTien) > 1000000";
        try (Connection connect = DBConnection.getConnection();
                PreparedStatement stmt = connect.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TheThanhVienDTO kh = new TheThanhVienDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getString(10));
                DsKH.add(kh);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        }
        return DsKH;
    }

    public static ArrayList<TheThanhVienDTO> TimTTV(SearchTheThanhVienDTO search) {
        ArrayList<TheThanhVienDTO> khachHangList = new ArrayList<>();
        ArrayList<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT kh.MaTV, kh.TenTV, kh.NgaySinh, kh.DiaChi, kh.DiemTL, kh.SDT, " +
                        "kh.NgayBD, kh.NgayKT, kh.TenAnh, kh.TrangThai, " +
                        "COALESCE(SUM(COALESCE(dh.TongTien, 0)), 0) AS tong, " +
                        "COALESCE(COUNT(dh.MaDH), 0) AS tongdonhang " +
                        "FROM thethanhvien kh " +
                        "LEFT JOIN donhang dh ON dh.MaKH = kh.MaTV ");
        StringBuilder where = new StringBuilder("WHERE kh.TrangThai = 'ACTIVE' ");

        // Thêm điều kiện lọc
        if(search.getMaTV()!=0){
            where.append("AND kh.MaTV = ? ");
            param.add(search.getMaTV());
        }
        if(!search.getTenTV().trim().isEmpty()){
            where.append("AND kh.TenTV LIKE ? ");
            param.add("%"+search.getTenTV().trim()+"%");
        }
        if(!search.getDiaChi().trim().isEmpty()){
            where.append("AND kh.DiaChi LIKE ? ");
            param.add("%"+search.getDiaChi().trim()+"%");
        }
        if (search.getSinhFrom() != null && search.getSinhTo() != null) {
            where.append("AND kh.NgaySinh BETWEEN ? AND ? ");
            param.add(search.getSinhFrom());
            param.add(search.getSinhTo());
        }
        if (search.getDtlMin() != 0 || search.getDtlMax() != 0) {
            where.append("AND kh.DiemTL BETWEEN ? AND ? ");
            param.add(search.getDtlMin());
            param.add(search.getDtlMax());
        }
        if (search.getNgayMin() != null && search.getNgayMax() != null) {
            where.append("AND kh.NgayBD BETWEEN ? AND ? ");
            param.add(search.getNgayMin());
            param.add(search.getNgayMax());
            where.append("AND kh.NgayKT BETWEEN ? AND ? ");
            param.add(search.getNgayMin());
            param.add(search.getNgayMax());
        }
        if(!search.getSDT().trim().isEmpty()){
            where.append("AND kh.SDT LIKE ? ");
            param.add("%"+search.getSDT()+"%");
        }
        if(search.getMaDH()!=0){
            where.append("AND dh.MaDH = ? ");
            param.add(search.getMaDH());
        }

        // Thêm điều kiện HAVING
        StringBuilder having = new StringBuilder();
        if (search.getTongMin() != 0) {
            having.append("HAVING SUM(COALESCE(dh.TongTien, 0)) >= ? ");
            param.add(search.getTongMin());
        }
        if (search.getTongMax() != 0) {
            if (having.length() == 0) {
                having.append("HAVING SUM(COALESCE(dh.TongTien, 0)) <= ? ");
            } else {
                having.append("AND SUM(COALESCE(dh.TongTien, 0)) <= ? ");
            }
            param.add(search.getTongMax());
        }
        if (search.getDhMin() != 0) {
            if (having.length() == 0) {
                having.append("HAVING COUNT(dh.MaDH) >= ? ");
            } else {
                having.append("AND COUNT(dh.MaDH) >= ? ");
            }
            param.add(search.getDhMin());
        }
        if (search.getDhMax() != 0) {
            if (having.length() == 0) {
                having.append("HAVING COUNT(dh.MaDH) <= ? ");
            } else {
                having.append("AND COUNT(dh.MaDH) <= ? ");
            }
            param.add(search.getDhMax());
        }

        // Thêm sắp xếp
        StringBuilder orderby = new StringBuilder("ORDER BY ");
        String column;
        switch (search.getBy()) {
            case "Mã khách hàng":
                column = "kh.MaTV";
                break;
            case "Tên khách hàng":
                column = "kh.TenTV";
                break;
            case "Tổng chi tiêu":
                column = "tong";
                break;
            case "Tổng đơn hàng":
                column = "tongdonhang";
                break;
            case "Ngày sinh":
                column = "kh.NgaySinh";
                break;
                case "Điểm tích lũy":
                column = "kh.DiemTL";
                break;
            case "Hạn thẻ":
                column = "kh.NgayKT";
                break;
            default:
                column = "kh.MaTV";
        }
        orderby.append(column).append(" ").append(search.getOrder().equals("Giảm dần") ? "DESC" : "ASC");

        // Kết hợp câu truy vấn
        sql.append(where)
                .append("GROUP BY kh.MaTV, kh.TenTV, kh.NgaySinh, kh.DiaChi, kh.DiemTL, kh.SDT, kh.NgayBD, kh.NgayKT, kh.TenAnh, kh.TrangThai ")
                .append(having).append(orderby);

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            // Gán tham số
            for (int i = 0; i < param.size(); i++) {
                stmt.setObject(i + 1, param.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TheThanhVienDTO kh = new TheThanhVienDTO();
                kh.setMaTV(rs.getInt("MaTV"));
                kh.setTenTV(rs.getString("TenTV"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setDiemTL(rs.getInt("DiemTL"));
                kh.setSdt(rs.getString("SDT"));
                kh.setNgayBD(rs.getDate("NgayBD"));
                kh.setNgayKT(rs.getDate("NgayKT"));
                kh.setTenAnh(rs.getString("TenAnh"));
                kh.setTrangThai(rs.getString("TrangThai"));
                khachHangList.add(kh);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        }
        return khachHangList;
    }

    public static ArrayList<TheThanhVienDTO> TimTTVLock(SearchTheThanhVienDTO search) {
        ArrayList<TheThanhVienDTO> khachHangList = new ArrayList<>();
        ArrayList<Object> param = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT kh.MaTV, kh.TenTV, kh.NgaySinh, kh.DiaChi, kh.DiemTL, kh.SDT, " +
                        "kh.NgayBD, kh.NgayKT, kh.TenAnh, kh.TrangThai, " +
                        "COALESCE(SUM(COALESCE(dh.TongTien, 0)), 0) AS tong, " +
                        "COALESCE(COUNT(dh.MaDH), 0) AS tongdonhang " +
                        "FROM thethanhvien kh " +
                        "LEFT JOIN donhang dh ON dh.MaKH = kh.MaTV ");
        StringBuilder where = new StringBuilder("WHERE kh.TrangThai = 'INACTIVE' ");

        // Thêm điều kiện lọc
        if(search.getMaTV()!=0){
            where.append("AND kh.MaTV = ? ");
            param.add(search.getMaTV());
        }
        if(!search.getTenTV().trim().isEmpty()){
            where.append("AND kh.TenTV LIKE ? ");
            param.add("%"+search.getTenTV().trim()+"%");
        }
        if(!search.getDiaChi().trim().isEmpty()){
            where.append("AND kh.DiaChi LIKE ? ");
            param.add("%"+search.getDiaChi().trim()+"%");
        }
        if (search.getSinhFrom() != null && search.getSinhTo() != null) {
            where.append("AND kh.NgaySinh BETWEEN ? AND ? ");
            param.add(search.getSinhFrom());
            param.add(search.getSinhTo());
        }
        if (search.getDtlMin() != 0 || search.getDtlMax() != 0) {
            where.append("AND kh.DiemTL BETWEEN ? AND ? ");
            param.add(search.getDtlMin());
            param.add(search.getDtlMax());
        }
        if (search.getNgayMin() != null && search.getNgayMax() != null) {
            where.append("AND kh.NgayBD BETWEEN ? AND ? ");
            param.add(search.getNgayMin());
            param.add(search.getNgayMax());
            where.append("AND kh.NgayKT BETWEEN ? AND ? ");
            param.add(search.getNgayMin());
            param.add(search.getNgayMax());
        }
        if(!search.getSDT().trim().isEmpty()){
            where.append("AND kh.SDT LIKE ? ");
            param.add("%"+search.getSDT()+"%");
        }
        if(search.getMaDH()!=0){
            where.append("AND dh.MaDH = ? ");
            param.add(search.getMaDH());
        }

        // Thêm điều kiện HAVING
        StringBuilder having = new StringBuilder();
        if (search.getTongMin() != 0) {
            having.append("HAVING SUM(COALESCE(dh.TongTien, 0)) >= ? ");
            param.add(search.getTongMin());
        }
        if (search.getTongMax() != 0) {
            if (having.length() == 0) {
                having.append("HAVING SUM(COALESCE(dh.TongTien, 0)) <= ? ");
            } else {
                having.append("AND SUM(COALESCE(dh.TongTien, 0)) <= ? ");
            }
            param.add(search.getTongMax());
        }
        if (search.getDhMin() != 0) {
            if (having.length() == 0) {
                having.append("HAVING COUNT(dh.MaDH) >= ? ");
            } else {
                having.append("AND COUNT(dh.MaDH) >= ? ");
            }
            param.add(search.getDhMin());
        }
        if (search.getDhMax() != 0) {
            if (having.length() == 0) {
                having.append("HAVING COUNT(dh.MaDH) <= ? ");
            } else {
                having.append("AND COUNT(dh.MaDH) <= ? ");
            }
            param.add(search.getDhMax());
        }

        // Thêm sắp xếp
        StringBuilder orderby = new StringBuilder("ORDER BY ");
        String column;
        switch (search.getBy()) {
            case "Mã khách hàng":
                column = "kh.MaTV";
                break;
            case "Tên khách hàng":
                column = "kh.TenTV";
                break;
            case "Tổng chi tiêu":
                column = "tong";
                break;
            case "Tổng đơn hàng":
                column = "tongdonhang";
                break;
            case "Ngày sinh":
                column = "kh.NgaySinh";
                break;
                case "Điểm tích lũy":
                column = "kh.DiemTL";
                break;
            case "Hạn thẻ":
                column = "kh.NgayKT";
                break;
            default:
                column = "kh.MaTV";
        }
        orderby.append(column).append(" ").append(search.getOrder().equals("Giảm dần") ? "DESC" : "ASC");

        // Kết hợp câu truy vấn
        sql.append(where)
                .append("GROUP BY kh.MaTV, kh.TenTV, kh.NgaySinh, kh.DiaChi, kh.DiemTL, kh.SDT, kh.NgayBD, kh.NgayKT, kh.TenAnh, kh.TrangThai ")
                .append(having).append(orderby);

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            // Gán tham số
            for (int i = 0; i < param.size(); i++) {
                stmt.setObject(i + 1, param.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TheThanhVienDTO kh = new TheThanhVienDTO();
                kh.setMaTV(rs.getInt("MaTV"));
                kh.setTenTV(rs.getString("TenTV"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setDiemTL(rs.getInt("DiemTL"));
                kh.setSdt(rs.getString("SDT"));
                kh.setNgayBD(rs.getDate("NgayBD"));
                kh.setNgayKT(rs.getDate("NgayKT"));
                kh.setTenAnh(rs.getString("TenAnh"));
                kh.setTrangThai(rs.getString("TrangThai"));
                khachHangList.add(kh);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        }
        return khachHangList;
    }

    
    public static ArrayList<DonHangDTO> TimKHDH(SearchKHDHDTO search, int MaKH) {
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
                        "LEFT JOIN nhanvien nv ON nv.MaNV = dh.MaNV\n" +
                        "LEFT JOIN khuyenmai km ON km.MaKM = dh.MaKM\n" +
                        "LEFT JOIN chitietdh ctdh ON ctdh.MaDH = dh.MaDH\n" +
                        "LEFT JOIN sanpham sp ON sp.MaSP = ctdh.MaSP\n" +
                        "LEFT JOIN loaisp lsp ON lsp.MaLSP = sp.MaLSP\n");
        StringBuilder where = new StringBuilder(
                "WHERE dh.MaKH = ?\n");
        param.add(MaKH);

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
        if (search.getMaNV() != 0) {
            where.append("AND dh.MaNV = ?\n");
            param.add(search.getMaNV());
        }
        if (!search.getTenNV().trim().isEmpty()) {
            where.append("AND nv.TenNV LIKE ?\n");
            param.add("%" + search.getTenNV().trim() + "%");
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
                        MaKH,
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
