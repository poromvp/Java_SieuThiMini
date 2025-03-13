package com.sieuthi.Admin_PanelThongKe;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class PanelDoanhThu extends JPanel {
    JLabel tongdoanhthu, tongdonhang;
    JComboBox<String> mocthoigian;
    JTable tb;
    DefaultTableModel model;
    public ArrayList<hoadontemp> HoaDon= new ArrayList<>();
    
    public PanelDoanhThu() {
        setBorder(new CompoundBorder(new TitledBorder("Báo cáo doanh thu tổng hợp"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = -5; // Giảm chiều rộng nội bộ
        gbc.ipady = -5; // Giảm chiều cao nội bộ

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Tổng Doanh Thu:"),gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Tổng đơn hàng (hóa đơn):"),gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        tongdoanhthu = new JLabel("1,000,000");
        add(tongdoanhthu,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        tongdonhang= new JLabel("96");
        add(tongdonhang,gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(new JLabel("Doanh thu theo:"),gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        mocthoigian = new JComboBox<>();
        mocthoigian.addItem("Ngày");
        mocthoigian.addItem("Tháng");
        mocthoigian.addItem("Năm");
        mocthoigian.addItem("Tùy chỉnh");
        mocthoigian.setEditable(false);
        add(mocthoigian,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight=1;
        gbc.gridwidth=4;
        String[] tencot = {"ID", "Name", "Price", "Date"};
        hoadontemp a = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        hoadontemp b = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        hoadontemp c = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        hoadontemp d = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        HoaDon.add(a);
        HoaDon.add(b);
        HoaDon.add(c);
        HoaDon.add(d);
        model = new DefaultTableModel(tencot, 0);
        refreshTable();
        tb = new JTable(model);
        JScrollPane scr = new JScrollPane(tb);
        add(scr,gbc);
        
    }

    private void refreshTable() {
        model.setRowCount(0);  // Xóa toàn bộ dữ liệu cũ
        for (hoadontemp s : HoaDon) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getPrice(), s.getDate()});
        }
    }
}
