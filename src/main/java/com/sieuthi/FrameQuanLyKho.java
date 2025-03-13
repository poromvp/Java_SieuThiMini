package com.sieuthi;

import com.ComponentCommon.Header;
import com.FormEmployee.LeftMenu;
import com.FormWareHouse.FormImport;
import com.FormWareHouse.FormProduct;
import com.FormWareHouse.LeftMenuWareHouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameQuanLyKho extends JFrame  {
    private LeftMenuWareHouse pn_leftMenu;
    private Header pn_header;
    private FormImport pn_import;
    private FormProduct pn_product;

    private static  JLabel lbl_title = new JLabel("Quản lý sản phẩm");
    private static JPanel pn_body;
    private  static  JPanel pn_cardLayout;
    private static CardLayout cardLayout;

    public FrameQuanLyKho(){
        setLayout(new BorderLayout());
        setSize(1300, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pn_header =new Header();
        pn_leftMenu=new LeftMenuWareHouse();
        pn_import= new FormImport();
        pn_product= new FormProduct();

        cardLayout = new CardLayout();
        pn_cardLayout = new JPanel(cardLayout);

        pn_cardLayout.add(pn_import,"formImport");
        pn_cardLayout.add(pn_product,"formProduct");

        pn_body = new JPanel(new BorderLayout());
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        pn_body.add(lbl_title, BorderLayout.NORTH);
        pn_body.add(pn_cardLayout, BorderLayout.CENTER);


        add(pn_header, BorderLayout.NORTH);
      add(pn_body,BorderLayout.CENTER);
        add(pn_leftMenu, BorderLayout.WEST);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FrameQuanLyKho();
    }
    public static void setPage(String pagename,String title){
        lbl_title.setText(title);
        cardLayout.show(pn_cardLayout,pagename);
    }
}

