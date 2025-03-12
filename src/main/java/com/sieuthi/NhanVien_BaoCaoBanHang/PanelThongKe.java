package com.sieuthi.NhanVien_BaoCaoBanHang;
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelThongKe extends JPanel{
    public PanelThongKe(JLabel lbTongDon, JLabel lbTongDoanhThu){
        setBorder(new CompoundBorder(new TitledBorder("Thống Kê"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbcPnlThongKe = new GridBagConstraints();
        gbcPnlThongKe.fill = GridBagConstraints.BOTH;
        gbcPnlThongKe.weightx = 1.0;
        gbcPnlThongKe.weighty = 1.0;
        setBackground(new Color(155, 182, 193));

        gbcPnlThongKe.gridx = 0;
        gbcPnlThongKe.gridy = 0;
        JLabel lbtongdon = new JLabel("Tổng Đơn Hàng Trong Ngày: ");
        lbtongdon.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        lbtongdon.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbtongdon, gbcPnlThongKe);
        gbcPnlThongKe.gridx = 1;
        gbcPnlThongKe.gridy = 0;
        lbTongDon.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        lbTongDon.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbTongDon, gbcPnlThongKe);

        gbcPnlThongKe.gridx = 0;
        gbcPnlThongKe.gridy = 1;
        JLabel lbtongdoanhthu = new JLabel("Tổng Doanh Thu Trong Ngày: ");
        lbtongdoanhthu.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        lbtongdoanhthu.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbtongdoanhthu, gbcPnlThongKe);
        gbcPnlThongKe.gridx = 1;
        gbcPnlThongKe.gridy = 1;
        lbTongDoanhThu.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        lbTongDoanhThu.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbTongDoanhThu, gbcPnlThongKe);

        gbcPnlThongKe.gridx = 3;
        gbcPnlThongKe.gridy = 1;
        JLabel vnd = new JLabel("VND");
        vnd.setHorizontalAlignment(SwingConstants.LEFT);
        vnd.setFont(new Font("Arial", Font.BOLD, 20));
        add(vnd, gbcPnlThongKe);
    }
}
