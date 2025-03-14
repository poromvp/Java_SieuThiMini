package com.sieuthi.Admin_PanelThongKe;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelTotNhat extends JPanel {
    public PanelTotNhat(JScrollPane scr) {
        setBorder(new CompoundBorder(new TitledBorder("Danh sách nhân viên có doanh số tốt nhất"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new BorderLayout());
        add(scr, BorderLayout.CENTER);
    }
}
