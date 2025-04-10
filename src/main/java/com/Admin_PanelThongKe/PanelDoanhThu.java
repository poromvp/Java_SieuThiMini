package com.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import com.TienIch;

public class PanelDoanhThu extends JPanel implements ActionListener {
    JLabel tongdoanhthu, tongdonhang;
    JComboBox<String> mocthoigian;
    JTable tb;
    DefaultTableModel model;
    public ArrayList<hoadontemp> HoaDon = new ArrayList<>();
    JButton btnTim;
    JPanel pn1, pn2, pn3;

    public void initPanel1() {
        pn1.setBorder(new CompoundBorder(new TitledBorder("Thống kê"), new EmptyBorder(4, 4, 4, 4)));
        pn1.setLayout(new GridLayout(2, 2));

        JLabel tong = new JLabel(("Tổng Doanh Thu:"));
        TienIch.labelStyle(tong, 4, 20, null);
        pn1.add(tong);

        tongdoanhthu = new JLabel("1,000,000" + " VND");
        TienIch.labelStyle(tongdoanhthu, 2, 20, null);
        pn1.add(tongdoanhthu);

        JLabel tonghd = new JLabel("Tổng đơn hàng (hóa đơn):");
        TienIch.labelStyle(tonghd, 4, 20, null);
        pn1.add(tonghd);

        tongdonhang = new JLabel("96" + " Đơn");
        TienIch.labelStyle(tongdonhang, 2, 20, null);
        pn1.add(tongdonhang);
    }

    public void initPanel2() {
        pn2.setBorder(new CompoundBorder(new TitledBorder("Hành động"), new EmptyBorder(4, 4, 4, 4)));
        pn2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel doanhthutheo = new JLabel("Doanh thu theo:");
        TienIch.labelStyle(doanhthutheo, 4, 17, null);
        pn2.add(doanhthutheo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        mocthoigian = new JComboBox<>();
        mocthoigian.addItem("Ngày");
        mocthoigian.addItem("Tháng");
        mocthoigian.addItem("Năm");
        mocthoigian.setEditable(false);
        TienIch.comboBoxStyle(mocthoigian);
        pn2.add(mocthoigian, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        btnTim = new JButton("Tìm");
        TienIch.nutStyle(btnTim, null, 17, 0, 0);
        pn2.add(btnTim, gbc);

        btnTim.addActionListener((ActionListener) this);
    }

    public void initPanel3() {
        pn3.setBorder(new CompoundBorder(new TitledBorder("Danh sách"), new EmptyBorder(4, 4, 4, 4)));
        pn3.setLayout(new BorderLayout());
        String[] tencot = { "ID", "Name", "Price", "Date" };
        hoadontemp a = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        hoadontemp b = new hoadontemp("2", "Cam", "10,000", "10/10/2025");
        hoadontemp c = new hoadontemp("3", "Cam", "10,000", "10/10/2025");
        hoadontemp d = new hoadontemp("4", "Cam", "10,000", "10/10/2025");
        HoaDon.add(a);
        HoaDon.add(b);
        HoaDon.add(c);
        HoaDon.add(d);
        model = new DefaultTableModel(tencot, 0){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        refreshTable();
        tb = new JTable(model);
        TableControl.TableStyle(tb, model);
        TableControl.TableEvent(tb, model, "HD");
        JScrollPane scr = new JScrollPane(tb);
        pn3.add(scr,BorderLayout.CENTER);
    }

    public PanelDoanhThu() {
        setBorder(new CompoundBorder(new TitledBorder("Báo cáo doanh thu tổng hợp"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pn1 = new JPanel();
        initPanel1();
        add(pn1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pn2 = new JPanel();
        initPanel2();
        add(pn2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        pn3 = new JPanel();
        initPanel3();
        add(pn3, gbc);
    }

    private void refreshTable() {
        model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (hoadontemp s : HoaDon) {
            model.addRow(new Object[] { s.getId(), s.getName(), s.getPrice(), s.getDate() });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTim) {
            PanelTimThK panel = new PanelTimThK();
            // Hiển thị JOptionPane
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            /*
             * giá trị của biến result sẽ là:
             * JOptionPane.OK_OPTION (0) nếu bạn nhấn OK.
             * JOptionPane.CANCEL_OPTION (2) nếu bạn nhấn Cancel.
             * JOptionPane.CLOSED_OPTION (-1) nếu bạn đóng hộp thoại bằng dấu X (góc trên
             * phải).
             */
            if (result == 0) {
                System.out.println("Bạn vừa nhập: " + panel.getTxtName());
            }
        }
    }
}
