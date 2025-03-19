package com.sieuthi;

import com.FormWareHouse.FormImport;
import com.FormWareHouse.FormProduct;
import com.FormWareHouse.LeftMenuWareHouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameQuanLyKho extends JFrame  implements ActionListener{
    private JPanel rightPn;
    private LeftMenuWareHouse pn_leftMenu;

    public FrameQuanLyKho(){
        setTitle("Frame Quản Lý Kho");
        setSize(1300,750);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pn_leftMenu = new LeftMenuWareHouse();
        pn_leftMenu.setActionListener(this);

        add(pn_leftMenu,BorderLayout.WEST);
        rightPn = new JPanel();
        panelImport();
        add(rightPn,BorderLayout.CENTER);
        setVisible(true);
    }

    public void panelImport(){
        rightPn.removeAll();
        rightPn.setBackground(new Color(55, 11, 77));
        rightPn.setLayout(new BorderLayout());

        FormImport importPanel = new FormImport();
        rightPn.add(importPanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelProduct(){
        rightPn.removeAll();
        rightPn.setBackground(new Color(55, 11, 77));
        rightPn.setLayout(new BorderLayout());

        FormProduct productPanel= new FormProduct();
        rightPn.add(productPanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public static void main(String[] args) {
        new FrameQuanLyKho();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== pn_leftMenu.getBtnImport()){
            panelImport();
        } else if (e.getSource()== pn_leftMenu.getBtnProduct()) {
            panelProduct();
        }
        else if(e.getSource()==pn_leftMenu.getBtnLogout()){
            JOptionPane.showMessageDialog(this, "Bạn đã đăng xuất.");
        }
    }
}

