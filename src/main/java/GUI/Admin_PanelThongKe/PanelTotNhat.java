package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import GUI.ComponentCommon.*;
import GUI.ComponentCommon.StyledTable;

public class PanelTotNhat extends JPanel {
    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<NhanVienDTO> DsNV = (ArrayList<NhanVienDTO>) new NhanVienBLL().getAllNhanVien();

    public PanelTotNhat() {
        setBorder(new CompoundBorder(new TitledBorder("Danh sách nhân viên có doanh số tốt nhất"),
                new EmptyBorder(4, 4, 4, 4)));
        setLayout(new BorderLayout());

        String[] tencot = { "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        loadNhanVien(DsNV);
        TableControl.TableEvent(tb, model, "NV"); // Giữ sự kiện double-click
        scr = new JScrollPane(tb);
        add(scr, BorderLayout.CENTER);
    }

    private void loadNhanVien(ArrayList<NhanVienDTO> dsnv) {
        model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (NhanVienDTO nv : dsnv) {
            model.addRow(new Object[] {
                    nv.getMaNV(),
                    nv.getTenNV(),
                    TienIch.ddmmyyyy(nv.getNgaySinh()),
                    nv.getSDT()
            });
        }
    }
}