package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class PanelTotNhat extends JPanel{
    JTable tb;
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<hoadontemp> HoaDon = new ArrayList<>();
    public PanelTotNhat() {
        setBorder(new CompoundBorder(new TitledBorder("Danh sách nhân viên có doanh số tốt nhất"),
                new EmptyBorder(4, 4, 4, 4)));
        setLayout(new BorderLayout());

        String[] tencot = { "ID", "Name", "Price", "Date" };
        hoadontemp a = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        hoadontemp b = new hoadontemp("2", "Cam", "10,000", "10/10/2025");
        hoadontemp c = new hoadontemp("3", "Cam", "10,000", "10/10/2025");
        hoadontemp d = new hoadontemp("4", "Cam", "10,000", "10/10/2025");
        HoaDon.add(a);
        HoaDon.add(b);
        HoaDon.add(c);
        HoaDon.add(d);
        model = new DefaultTableModel(tencot, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa tất cả các ô
            }
        };

        refreshTable();
        tb = new JTable(model);
        TableControl.TableStyle(tb,model);
        TableControl.TableEvent(tb, model, "NV");
        scr = new JScrollPane(tb);
        add(scr, BorderLayout.CENTER);
    }

    private void refreshTable() {
        model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (hoadontemp s : HoaDon) {
            model.addRow(new Object[] { s.getId(), s.getName(), s.getPrice(), s.getDate() });
        }
    }
}
