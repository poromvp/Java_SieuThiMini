package com.sieuthi.Admin_PanelThongKe;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


public class PanelSapHet extends JPanel{
    public PanelSapHet(JScrollPane scr){
        setBorder(new CompoundBorder(new TitledBorder("Danh Sách Nhập Hàng"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new BorderLayout());
        add(scr,BorderLayout.CENTER);
    }
}
