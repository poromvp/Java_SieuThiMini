package DAL;

import DTO.NhanVienDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    private Connection conn;

    public NhanVienDAO(Connection conn) {
        this.conn = conn;
    }

    public List<NhanVienDTO> getAllNhanVien() {
        List<NhanVienDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int maNV = rs.getInt("maNV");
                String tenNV = rs.getString("tenNV");
                String gioiTinh = rs.getString("gioiTinh");
                Date ngaySinh = rs.getDate("ngaySinh");
                String cccd = rs.getString("CCCD");
                String diaChi = rs.getString("diaChi");
                String sdt = rs.getString("sdt");
                double luong = rs.getDouble("luong");
                String trangThai = rs.getString("trangThai");

                list.add(new NhanVienDTO(maNV, tenNV, ngaySinh, gioiTinh, diaChi,
                        sdt, cccd, luong, trangThai));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insertNhanVien(NhanVienDTO nv) {
        String sql = "INSERT INTO NhanVien (maNV, tenNV, gioiTinh, ngaySinh, CCCD, diaChi, sdt, luong, trangThai) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, nv.getMaNV());
            ps.setString(2, nv.getTenNV());
            ps.setString(3, nv.getGioiTinh());
            ps.setDate(4, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setString(5, nv.getCccd());
            ps.setString(6, nv.getDiaChi());
            ps.setString(7, nv.getSdt());
            ps.setDouble(8, nv.getLuong());
            ps.setString(9, nv.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateNhanVien(NhanVienDTO nv) {
        String sql = "UPDATE NhanVien SET tenNV = ?, gioiTinh = ?, ngaySinh = ?, CCCD = ?, diaChi = ?, sdt = ?, luong = ?, trangThai = ? " +
                     "WHERE maNV = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getGioiTinh());
            ps.setDate(3, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setString(4, nv.getCccd());
            ps.setString(5, nv.getDiaChi());
            ps.setString(6, nv.getSdt());
            ps.setDouble(7, nv.getLuong());
            ps.setString(8, nv.getTrangThai());
            ps.setInt(9, nv.getMaNV());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteNhanVien(int maNV) {
        String sql = "DELETE FROM NhanVien WHERE maNV = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maNV);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
