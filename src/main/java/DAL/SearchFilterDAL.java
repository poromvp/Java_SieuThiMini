package DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.DonHangDTO;
import DTO.SearchFilterDTO;
import JDBC.DBConnection;

public class SearchFilterDAL {
    public static ArrayList<DonHangDTO> timKiem_SapXepDonHang(SearchFilterDTO filter) {
        ArrayList<DonHangDTO> dsDH = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT donhang.MaDH, donhang.MaKH, donhang.maKM, donhang.MaNV, donhang.PTTToan, " +
            "donhang.NgayTT, donhang.maDTL, tienKD, donhang.TrangThai, donhang.TongTien " +
            "FROM donhang "
        );
    
        List<String> whereClauses = new ArrayList<>();
        ArrayList<Object> param = new ArrayList<>();
    
        int checkNhanVien = 0;
        int checkTheThanhVien = 0;
        int checkChiTietDH = 0;
        int checkSanPham = 0;
        int checkKhuyenMai = 0;
        int checkLoaiSP = 0;
    
        try {
            if (filter.getMaDH() != null) {
                whereClauses.add("donhang.maDH = ?");
                param.add(filter.getMaDH());
            }
    
            if (filter.getTongTienMin() > 0) {
                whereClauses.add("donhang.tongTien >= ?");
                param.add(filter.getTongTienMin());
            }
    
            if (filter.getTongTienMax() > 0) {
                whereClauses.add("donhang.tongTien <= ?");
                param.add(filter.getTongTienMax());
            }
    
            if (filter.getNgayBD() != null) {
                whereClauses.add("donhang.ngayTT >= ?");
                param.add(filter.getNgayBD());
            }
    
            if (filter.getNgayKT() != null) {
                whereClauses.add("donhang.ngayTT <= ?");
                param.add(filter.getNgayKT());
            }
    
            if (filter.getPhuongThucTT() != null && !filter.getPhuongThucTT().equalsIgnoreCase("tất cả")) {
                whereClauses.add("donhang.PTTToan = ?");
                param.add(filter.getPhuongThucTT());
            }
    
            if (filter.getMaKH() != null) {
                whereClauses.add("donhang.maKH = ?");
                param.add(filter.getMaKH());
            }
    
            if (filter.getMaKM() != null) {
                if (checkKhuyenMai == 0) {
                    sql.append(" JOIN khuyenmai ON khuyenmai.maKM = donhang.maKM ");
                    checkKhuyenMai = 1;
                }
                whereClauses.add("donhang.maKM = ?");
                param.add(filter.getMaKM());
            }
    
            if (filter.getTenKM() != null && !filter.getTenKM().isEmpty()) {
                if (checkKhuyenMai == 0) {
                    sql.append(" JOIN khuyenmai ON khuyenmai.maKM = donhang.maKM ");
                    checkKhuyenMai = 1;
                }
                whereClauses.add("khuyenmai.TenKM LIKE ?");
                param.add("%" + filter.getTenKM() + "%");
            }
    
            if (filter.getTenKH() != null && !filter.getTenKH().isEmpty()) {
                if (checkTheThanhVien == 0) {
                    sql.append(" JOIN thethanhvien ON thethanhvien.maTV = donhang.maKH ");
                    checkTheThanhVien = 1;
                }
                whereClauses.add("thethanhvien.TenTV LIKE ?");
                param.add("%" + filter.getTenKH() + "%");
            }
    
            if (filter.getMaSP() != null) {
                if (checkChiTietDH == 0) {
                    sql.append(" JOIN chitietdh ON chitietdh.maDH = donhang.maDH ");
                    checkChiTietDH = 1;
                }
                whereClauses.add("chitietdh.MaSP = ?");
                param.add(filter.getMaSP());
            }
    
            if (filter.getTenSP() != null && !filter.getTenSP().isEmpty()) {
                if (checkChiTietDH == 0) {
                    sql.append(" JOIN chitietdh ON chitietdh.maDH = donhang.maDH ");
                    checkChiTietDH = 1;
                }
                if (checkSanPham == 0) {
                    sql.append(" JOIN sanpham ON sanpham.maSP = chitietdh.maSP ");
                    checkSanPham = 1;
                }
                whereClauses.add("sanpham.TenSP LIKE ?");
                param.add("%" + filter.getTenSP() + "%");
            }
    
            if (filter.getMaLSP() != null) {
                if (checkChiTietDH == 0) {
                    sql.append(" JOIN chitietdh ON chitietdh.maDH = donhang.maDH ");
                    checkChiTietDH = 1;
                }
                if (checkSanPham == 0) {
                    sql.append(" JOIN sanpham ON sanpham.maSP = chitietdh.maSP ");
                    checkSanPham = 1;
                }
                if (checkLoaiSP == 0) {
                    sql.append(" JOIN loaiSP ON loaiSP.maLSP = sanpham.maLSP ");
                    checkLoaiSP = 1;
                }
                whereClauses.add("loaiSP.MaLSP = ?");
                param.add(filter.getMaLSP());
            }
    
            if (filter.getTenLSP() != null && !filter.getTenLSP().isEmpty()) {
                if (checkChiTietDH == 0) {
                    sql.append(" JOIN chitietdh ON chitietdh.maDH = donhang.maDH ");
                    checkChiTietDH = 1;
                }
                if (checkSanPham == 0) {
                    sql.append(" JOIN sanpham ON sanpham.maSP = chitietdh.maSP ");
                    checkSanPham = 1;
                }
                if (checkLoaiSP == 0) {
                    sql.append(" JOIN loaiSP ON loaiSP.maLSP = sanpham.maLSP ");
                    checkLoaiSP = 1;
                }
                whereClauses.add("loaiSP.TenLoaiSP LIKE ?");
                param.add("%" + filter.getTenLSP() + "%");
            }
    
            if (filter.getMaNV() != null) {
                if (checkNhanVien == 0) {
                    sql.append(" JOIN nhanvien ON nhanvien.maNV = donhang.maNV ");
                    checkNhanVien = 1;
                }
                whereClauses.add("donhang.maNV = ?");
                param.add(filter.getMaNV());
            }
    
            if (filter.getTenNV() != null && !filter.getTenNV().isEmpty()) {
                if (checkNhanVien == 0) {
                    sql.append(" JOIN nhanvien ON nhanvien.maNV = donhang.maNV ");
                    checkNhanVien = 1;
                }
                whereClauses.add("nhanvien.tenNV LIKE ?");
                param.add("%" + filter.getTenNV() + "%");
            }
    
            // WHERE clause
            if (!whereClauses.isEmpty()) {
                sql.append(" WHERE ").append(String.join(" AND ", whereClauses));
            }
    
            // ORDER BY
            String orderBy = " ORDER BY ";
            switch (filter.getTenCot()) {
                case "Mã đơn hàng":
                    orderBy += "donhang.maDH";
                    break;
                case "Mã Nhân Viên":
                    orderBy += "donhang.maNV";
                    break;
                case "Ngày":
                    orderBy += "donhang.NgayTT";
                    break;
                case "Thành Tiền":
                    orderBy += "donhang.TongTien";
                    break;
                default:
                    orderBy += "donhang.maDH";
            }
            orderBy += filter.getSort().equalsIgnoreCase("tăng dần") ? " ASC" : " DESC";
            sql.append(orderBy);
    
            // Execute
            try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())
            ) {
                for (int i = 0; i < param.size(); i++) {
                    stmt.setObject(i + 1, param.get(i));
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
                        rs.getString("trangThai")
                    );
                    dsDH.add(dh);
                }
                rs.close(); // Close ResultSet manually (try-with-resources preferred if ResultSet declared inside try)
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return dsDH;
    }
    



    public static void main(String[] args) {
        SearchFilterDTO filter = new SearchFilterDTO(
            null,                             // maDH
            null,      // ngayBD
            null,      // ngayKT
            1000.0,                       // tongTienMin
            500000000.0,                       // tongTienMax
            "BANK",                 // phuongThucTT
            null,                               // maKH
            null,                  // tenKH
            null,                               // maKM
            null,                // tenKM
            null,                              // maSP
            null,                    // tenSP
            null,                               // maLSP
            null,           // tenLSP
            1,                               // maNV
            null,                    // tenNV
            "ASC",                           // sort
            "Mã đơn hàng"                    // tenCot
        );

        ArrayList<DonHangDTO> DS = timKiem_SapXepDonHang(filter);
        System.out.println(DS.size());
    }
}
