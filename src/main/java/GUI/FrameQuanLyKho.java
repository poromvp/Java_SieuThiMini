package GUI;

import javax.swing.*;

import GUI.FormWareHouse.*;

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
        pn_leftMenu.setPreferredSize(new Dimension(250, 0));

        add(pn_leftMenu,BorderLayout.WEST);
        rightPn = new JPanel();
        panelImport();
        add(rightPn,BorderLayout.CENTER);
        setVisible(true);
    }

    public void panelImport(){
        rightPn.removeAll();
        rightPn.setLayout(new BorderLayout());

        FormImport importPanel = new FormImport();
        rightPn.add(importPanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelProduct(){
        rightPn.removeAll();
        rightPn.setLayout(new BorderLayout());

        FormProduct productPanel= new FormProduct();
        rightPn.add(productPanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelCategory(){
        rightPn.removeAll();
        rightPn.setLayout(new BorderLayout());

        FormProductType productTypePanel= new FormProductType();
        rightPn.add(productTypePanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelSupplier(){
        rightPn.removeAll();
        rightPn.setLayout(new BorderLayout());

        FormSupplier supplierPanel= new FormSupplier();
        rightPn.add(supplierPanel, BorderLayout.CENTER);

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
        } else if (e.getSource()==pn_leftMenu.getBtnProductType()) {
            panelCategory();
        } else if (e.getSource()==pn_leftMenu.getBtnSupplier()) {
            panelSupplier();
        } else if(e.getSource()==pn_leftMenu.getBtnLogout()){
            JOptionPane.showMessageDialog(this, "Bạn đã đăng xuất.");
        }
    }
}

