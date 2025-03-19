package com.sieuthi.NhanVien_BaoCaoBanHang;

import java.awt.*;
import java.util.ArrayList;

//import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sieuthi.Admin_PanelThongKe.TableControl;
import com.sieuthi.Admin_PanelThongKe.hoadontemp;

public class PanelTable extends JPanel {
    JTable tb;
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<hoadontemp> dsHoaDon= new ArrayList<>();
    public PanelTable(){
        setLayout(new BorderLayout());
        setBorder(new CompoundBorder(new TitledBorder("Danh sách các đơn hàng đã thanh toán"), new EmptyBorder(4, 4, 4, 4)));
        String[] tencot = { "ID", "Mặt hàng", "Số lượng", "Đơn giá", "Thành tiền" };
        hoadontemp a = new hoadontemp("1", "Cam", "1", "10,000");
        hoadontemp b = new hoadontemp("2", "Kem đánh răng", "1", "100,000");
        hoadontemp c = new hoadontemp("3", "Nước lọc", "1", "10,000");
        hoadontemp d = new hoadontemp("4", "Snack khoai tây", "1", "100,000");
        dsHoaDon.add(a);
        dsHoaDon.add(b);
        dsHoaDon.add(c);
        dsHoaDon.add(d);
        model = new DefaultTableModel(tencot, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        refreshTable();
        tb = new JTable(model);
        TableControl.TableStyle(tb, model);
        TableControl.TableEvent(tb, model, "HD");
        scr = new JScrollPane(tb);
        //scr.setPreferredSize(new Dimension(400, 120)); // Điều chỉnh chiều rộng và chiều cao của bảng
        add(scr,BorderLayout.CENTER);
    }

    private void refreshTable() {
        model.setRowCount(0);  // Xóa toàn bộ dữ liệu cũ
        for (hoadontemp s : dsHoaDon) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getPrice(), s.getDate()});
        }
    }
}
