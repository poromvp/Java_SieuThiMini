package com.FormEmployee;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.FrameEmployee;
import com.ComponentCommon.StyledLeftMenubutton;

public class LeftMenu extends JPanel implements ActionListener {

    private StyledLeftMenubutton btn_home;
    private StyledLeftMenubutton btn_sell;
    private StyledLeftMenubutton btn_orderManagement;
    private StyledLeftMenubutton btn_report;
    // private FrameNhanVien frame; // Tham chiếu đến FrameNhanVien

    public LeftMenu() { 
        
        
        setBackground(new Color(28, 173, 193));
        setPreferredSize(new Dimension(230, getHeight()));
        setLayout(new FlowLayout());
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        setBorder(emptyBorder);

        btn_home = new StyledLeftMenubutton("src/main/resources/images/home.png", "Trang Chủ");
        btn_sell = new StyledLeftMenubutton("src/main/resources/images/selling.png", "Bán Hàng");
        btn_orderManagement = new StyledLeftMenubutton("src/main/resources/images/selling.png", "Quản Lí Order");
        btn_report = new StyledLeftMenubutton("src/main/resources/images/selling.png", "báo cáo");

        btn_home.addActionListener(this);
        btn_sell.addActionListener(this);
        btn_orderManagement.addActionListener(this);
        btn_report.addActionListener(this);

        add(btn_home);
        add(btn_sell);
        add(btn_orderManagement);
        add(btn_report);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_sell) {
            System.out.println("click btn_sell");
            FrameEmployee.setPage("formOrder", "Bán Hàng");
        }
        if (e.getSource() == btn_home) {
            System.out.println("click btn_home");
            FrameEmployee.setPage("formHome", "Trang Chủ");
        } 
        if (e.getSource() == btn_orderManagement) {
            System.out.println("click btn_orderManagement");
            FrameEmployee.setPage("formOrderManagement", "Quản Lí Đơn Hàng");
        }

        if (e.getSource() == btn_report) {
            System.out.println("click btn_report");
            FrameEmployee.setPage("formReport", "Báo cáo đơn hàng");
        }
    }
}
