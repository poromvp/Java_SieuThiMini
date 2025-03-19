package com.sieuthi.NhanVien_BaoCaoBanHang;


import java.awt.*;
import javax.swing.*;

public class PanelMainBaoCao extends JPanel {
    public PanelMainBaoCao() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        PanelChucNang pnlChucNang = new PanelChucNang();
        add(pnlChucNang, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        PanelThongKe pnlThongKe = new PanelThongKe();
        add(pnlThongKe, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        PanelTable pnltb = new PanelTable();
        add(pnltb, gbc);
    }
}
