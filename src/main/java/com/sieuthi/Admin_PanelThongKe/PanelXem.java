package com.sieuthi.Admin_PanelThongKe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelXem extends JPanel {
    JTextArea txtMaHD, txtMANV;

    public PanelXem(DefaultTableModel model, int dong) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(226, 224, 221));

        txtMaHD = new JTextArea(LayChuoiTuBang(model, dong, 0));
        txtMaHD.setEditable(false);
        txtMANV = new JTextArea(LayChuoiTuBang(model, dong, 1));
        txtMANV.setEditable(false);

        add(new JLabel("Mã Hóa Đơn:"));
        add(txtMaHD);
        add(new JLabel("Mã Nhân Viên:"));
        add(txtMANV);
    }

    public String LayChuoiTuBang(DefaultTableModel model, int dong, int cot) {
        Object txt = model.getValueAt(dong, cot);
        return txt.toString();
    }
}
