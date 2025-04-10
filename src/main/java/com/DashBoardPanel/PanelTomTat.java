package com.DashBoardPanel;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import com.TienIch;

public class PanelTomTat extends JPanel{
    JPanel pn1, pn2, pn3;
    JLabel sp, luotmua, doanhthu;
    TitledBorder title;

    public void initPanel1(){
        title = new TitledBorder("Sản Phẩm");
        title.setTitleColor(Color.WHITE);
        pn1.setBorder(new CompoundBorder(title, new EmptyBorder(4, 4, 4, 4)));
        pn1.setLayout(new GridLayout(1,2,5,5));
        pn1.setBackground(new Color(17, 32, 51));

        JLabel lbName = new JLabel("Sản Phẩm:");
        TienIch.labelStyle(lbName, 1, 18, null);
        pn1.add(lbName);

        sp = new JLabel("128");
        TienIch.labelStyle(sp, 2, 20, null);
        pn1.add(sp);
    }

    public void initPanel2(){
        title = new TitledBorder("Lượt Mua");
        title.setTitleColor(Color.WHITE);
        pn2.setBorder(new CompoundBorder(title, new EmptyBorder(4, 4, 4, 4)));
        pn2.setLayout(new GridLayout(1,2,5,5));
        pn2.setBackground(new Color(17, 32, 51));

        JLabel lbName = new JLabel("Lượt Mua:");
        TienIch.labelStyle(lbName, 1, 18, null);
        pn2.add(lbName);

        luotmua = new JLabel("1200");
        TienIch.labelStyle(luotmua, 2, 20, null);
        pn2.add(luotmua);
    }

    public void initPanel3(){
        title = new TitledBorder("Doanh Thu");
        title.setTitleColor(Color.WHITE);
        pn3.setBorder(new CompoundBorder(title, new EmptyBorder(4, 4, 4, 4)));
        pn3.setLayout(new GridLayout(1,2,5,5));
        pn3.setBackground(new Color(17, 32, 51));

        JLabel lbName = new JLabel("Doanh Thu:");
        TienIch.labelStyle(lbName, 1, 18, null);
        pn3.add(lbName);

        doanhthu = new JLabel("128,546,000");
        TienIch.labelStyle(doanhthu, 2, 20, null);
        pn3.add(doanhthu);
    }

    public PanelTomTat(){
        setLayout(new GridLayout(1, 3, 10, 10));

        pn1 = new JPanel();
        initPanel1();
        add(pn1);

        pn2 = new JPanel();
        initPanel2();
        add(pn2);

        pn3 = new JPanel();
        initPanel3();
        add(pn3);
    }
}
