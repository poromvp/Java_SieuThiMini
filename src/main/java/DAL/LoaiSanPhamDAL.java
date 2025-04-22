package DAL;

import DTO.LoaiSanPhamDTO;
import JDBC.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoaiSanPhamDAL {
    public ArrayList<LoaiSanPhamDTO> getList() {
        ArrayList<LoaiSanPhamDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM LoaiSP";
        try (ResultSet rs = DBConnection.executeQuery(sql)) {
            while (rs.next()) {
                LoaiSanPhamDTO lsp = new LoaiSanPhamDTO(
                        rs.getInt("MaLSP"),
                        rs.getString("TenLoaiSP"),
                        rs.getString("TrangThai")
                );
                list.add(lsp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(LoaiSanPhamDTO lsp) {
        String sql = "INSERT INTO LoaiSP(TenLoaiSP, TrangThai) VALUES(?,?)";
        return DBConnection.executeUpdate(sql,
                lsp.getTenLoaiSP(),
                lsp.getTrangThai()) > 0;
    }

    public boolean update(LoaiSanPhamDTO lsp) {
        String sql = "UPDATE LoaiSP SET TenLoaiSP=?, TrangThai=? WHERE MaLSP=?";
        return DBConnection.executeUpdate(sql,
                lsp.getTenLoaiSP(),
                lsp.getTrangThai(),
                lsp.getMaLSP()) > 0;
    }

    public boolean delete(int maLSP) {
        String sql = "UPDATE LoaiSP SET TrangThai='INACTIVE' WHERE MaLSP=?";
        return DBConnection.executeUpdate(sql, maLSP) > 0;
    }

    public ArrayList<LoaiSanPhamDTO> search(String keyword) {
        ArrayList<LoaiSanPhamDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM LoaiSP WHERE TenLoaiSP LIKE ? OR MaLSP LIKE ?";
        try (ResultSet rs = DBConnection.executeQuery(sql, "%" + keyword + "%", "%" + keyword + "%")) {
            while (rs.next()) {
                LoaiSanPhamDTO lsp = new LoaiSanPhamDTO(
                        rs.getInt("MaLSP"),
                        rs.getString("TenLoaiSP"),
                        rs.getString("TrangThai")
                );
                list.add(lsp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}