package com.sieuthi.NhanVien_BaoCaoBanHang;
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import com.TienIch;

public class PanelThongKe extends JPanel{

    JLabel lbTongDon, lbTongDoanhThu;
    public PanelThongKe(){
        setBorder(new CompoundBorder(new TitledBorder("Thống Kê"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridLayout(2,3,10,10));

        JLabel lb1 = new JLabel("<html><center>Tổng đơn hàng<br>trong ngày:</html></center>");
        TienIch.labelStyle(lb1, 1,20, null);
        add(lb1);

        JLabel lbIcon1 = new JLabel();
        TienIch.labelStyle(lbIcon1, 0, 20, "shopping-bag.png");
        add(lbIcon1);

        lbTongDon = new JLabel("56" + " Đơn");
        TienIch.labelStyle(lbTongDon,2,24, null);
        add(lbTongDon);

        JLabel lb2 = new JLabel("<html><center>Tổng doanh thu<br>trong ngày:</html></center>");
        TienIch.labelStyle(lb2, 1, 20, null);
        add(lb2);

        JLabel lbIcon2 = new JLabel();
        TienIch.labelStyle(lbIcon2, 0, 20, "dollar.png");
        add(lbIcon2);

        lbTongDoanhThu = new JLabel("12,000" + " VND");
        TienIch.labelStyle(lbTongDoanhThu, 2, 24, null);
        add(lbTongDoanhThu);
    }

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
