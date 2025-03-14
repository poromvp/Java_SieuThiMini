package com.sieuthi.Admin_PanelThongKe;

import javax.swing.*;

import com.sieuthi.TienIch;

import java.awt.*;
import java.awt.event.*;

public class PanelMainThongKe extends JPanel implements ActionListener{
    JButton btn1;
    public PanelMainThongKe(){
        setBackground(new Color(30, 144, 255));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(1200, 1800));
        
        PanelDoanhThu pn1 = new PanelDoanhThu();
        add(pn1);
        
        PanelKhoTongHop pn2 = new PanelKhoTongHop();
        add(pn2);

        PanelBaoCaoNV pn3 = new PanelBaoCaoNV();
        add(pn3);

        btn1 = new JButton("In Báo Cáo");
        TienIch.nutStyle(btn1);
        btn1.setPreferredSize(new Dimension(120, 50));
        btn1.setMaximumSize(new Dimension(120, 50));
        add(btn1);

        btn1.addActionListener((ActionListener)this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btn1){
            DialogXuatBaoCaoTongHop dialog = new DialogXuatBaoCaoTongHop();
        }
    }
}
