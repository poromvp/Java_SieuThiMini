package com.sieuthi.Admin_PanelThongKe;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelDoanhThu extends JPanel {
    public PanelDoanhThu(JLabel lbTongDoanhThu, JLabel lbChuyenKhoan, JLabel lbTienMat, JLabel lbGiaVon,
            JLabel lbLoiNhuan, JLabel lbChiPhi) {
        setBorder(new CompoundBorder(new TitledBorder("Thống Kê"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = -50; // Giảm chiều rộng nội bộ
        gbc.ipady = -50; // Giảm chiều cao nội bộ

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Chuyển Khoản: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        lbChuyenKhoan.setForeground(new Color(18, 146, 81));
        add(lbChuyenKhoan, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(new JLabel("VND"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Tiền Mặt: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        lbTienMat.setForeground(new Color(18, 146, 81));
        add(lbTienMat, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(new JLabel("VND"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Tổng Doanh Thu: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        lbTongDoanhThu.setForeground(new Color(18, 146, 81));
        add(lbTongDoanhThu, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        add(new JLabel("VND"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        add(new JLabel("Giá Vốn: "), gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        lbGiaVon.setForeground(new Color(239, 38, 38));
        add(lbGiaVon, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        add(new JLabel("VND"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        add(new JLabel("Chi Phí: "), gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        lbChiPhi.setForeground(new Color(239, 38, 38));
        add(lbChiPhi, gbc);

        gbc.gridx = 5;
        gbc.gridy = 1;
        add(new JLabel("VND"), gbc);

        gbc.gridx = 6;
        gbc.gridy = 0;
        add(new JLabel("Lợi Nhuận: "), gbc);

        gbc.gridx = 7;
        gbc.gridy = 0;
        lbLoiNhuan.setForeground(new Color(18, 146, 81));
        add(lbLoiNhuan, gbc);

        gbc.gridx = 8;
        gbc.gridy = 0;
        add(new JLabel("VND"), gbc);
    }
}
