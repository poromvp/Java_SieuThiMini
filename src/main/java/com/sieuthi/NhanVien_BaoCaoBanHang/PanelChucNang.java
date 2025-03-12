package com.sieuthi.NhanVien_BaoCaoBanHang;

import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelChucNang extends JPanel{
    public PanelChucNang(JButton btnTimKiem, JButton btnXemChiTiet, JButton btnInBaoCao){
        setBorder(new CompoundBorder(new TitledBorder("Chức Năng"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbcPnlChucNang = new GridBagConstraints();
        gbcPnlChucNang.fill = GridBagConstraints.BOTH;
        gbcPnlChucNang.weightx = 1.0;
        gbcPnlChucNang.weighty = 1.0;
        setBackground(new Color(155, 182, 193));

        gbcPnlChucNang.gridx = 0;
        gbcPnlChucNang.gridy = 0;
        btnTimKiem.setMnemonic('T');
        btnTimKiem.setFont(new Font("Arial", Font.BOLD, 16));
        btnTimKiem.setContentAreaFilled(false);
        btnTimKiem.setOpaque(true);
        btnTimKiem.setBackground(new Color(37, 150, 190));
        ImageIcon in = new ImageIcon(getClass().getResource("/images/icon/searchIcon.png").getPath());
        btnTimKiem.setIcon(in);
        btnTimKiem.setHorizontalTextPosition(SwingConstants.RIGHT);
        add(btnTimKiem, gbcPnlChucNang);

        gbcPnlChucNang.gridx = 0;
        gbcPnlChucNang.gridy = 1;
        btnXemChiTiet.setMnemonic('X');
        btnXemChiTiet.setFont(new Font("Arial", Font.BOLD, 16));
        btnXemChiTiet.setContentAreaFilled(false);
        btnXemChiTiet.setOpaque(true);
        btnXemChiTiet.setBackground(new Color(136, 236, 123));
        in = new ImageIcon(getClass().getResource("/images/icon/detailIcon.png").getPath());
        btnXemChiTiet.setIcon(in);
        btnXemChiTiet.setHorizontalTextPosition(SwingConstants.RIGHT);
        add(btnXemChiTiet, gbcPnlChucNang);

        gbcPnlChucNang.gridx = 1;
        gbcPnlChucNang.gridy = 0;
        btnInBaoCao.setMnemonic('I');
        btnInBaoCao.setFont(new Font("Arial", Font.BOLD, 16));
        btnInBaoCao.setContentAreaFilled(false);
        btnInBaoCao.setOpaque(true);
        btnInBaoCao.setBackground(new Color(243, 191, 86));
        in = new ImageIcon(getClass().getResource("/images/icon/inIcon.png").getPath());
        btnInBaoCao.setIcon(in);
        btnInBaoCao.setHorizontalTextPosition(SwingConstants.RIGHT);
        add(btnInBaoCao, gbcPnlChucNang);
        
    }
}
