package DAL;

import DTO.NhaCungCapDTO;
import JDBC.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhaCungCapDAL {
    public ArrayList<NhaCungCapDTO> getList() {
        ArrayList<NhaCungCapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM NhaNCC";
        try (ResultSet rs = DBConnection.executeQuery(sql)) {
            while (rs.next()) {
                NhaCungCapDTO ncc = new NhaCungCapDTO(
                        rs.getInt("MaNCC"),
                        rs.getString("TenNCC"),
                        rs.getString("SDT"),
                        rs.getString("DiaChi"),
                        rs.getString("TrangThai")
                );
                list.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(NhaCungCapDTO ncc) {
        String sql = "INSERT INTO NhaNCC(TenNCC, SDT, DiaChi, TrangThai) VALUES(?,?,?,?)";
        return DBConnection.executeUpdate(sql,
                ncc.getTenNCC(),
                ncc.getSdt(),
                ncc.getDiaChi(),
                ncc.getTrangThai()) > 0;
    }

    public boolean update(NhaCungCapDTO ncc) {
        String sql = "UPDATE NhaNCC SET TenNCC=?, SDT=?, DiaChi=?, TrangThai=? WHERE MaNCC=?";
        return DBConnection.executeUpdate(sql,
                ncc.getTenNCC(),
                ncc.getSdt(),
                ncc.getDiaChi(),
                ncc.getTrangThai(),
                ncc.getMaNCC()) > 0;
    }

    public boolean delete(int maNCC) {
        String sql = "UPDATE NhaNCC SET TrangThai='INACTIVE' WHERE MaNCC=?";
        return DBConnection.executeUpdate(sql, maNCC) > 0;
    }

    public ArrayList<NhaCungCapDTO> search(String keyword) {
        ArrayList<NhaCungCapDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM NhaNCC WHERE TenNCC LIKE ? OR SDT LIKE ? OR DiaChi LIKE ?";
        try (ResultSet rs = DBConnection.executeQuery(sql,
                "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%")) {
            while (rs.next()) {
                NhaCungCapDTO ncc = new NhaCungCapDTO(
                        rs.getInt("MaNCC"),
                        rs.getString("TenNCC"),
                        rs.getString("SDT"),
                        rs.getString("DiaChi"),
                        rs.getString("TrangThai")
                );
                list.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
