package com.FormWareHouse;

import com.ComponentCommon.StyledTextField;

import javax.swing.*;
import java.awt.*;

public class FormAddProduct extends JPanel {
    public FormAddProduct(){
        setLayout(new BorderLayout());

        JPanel nhapPanel = new JPanel();
        nhapPanel.setLayout(new GridLayout(5,2,5,5));
        nhapPanel.add(new JLabel("Mã sản phẩm:"));
        StyledTextField maSP = new StyledTextField();
        nhapPanel.add(maSP);

        nhapPanel.add(new JLabel("Tên sản phẩm:"));
        StyledTextField tenSP = new StyledTextField();
        nhapPanel.add(tenSP);

        nhapPanel.add(new JLabel("Giá sản phẩm:"));
        StyledTextField giaSP = new StyledTextField();
        nhapPanel.add(giaSP);

        nhapPanel.add(new JLabel("Loại sản phẩm:"));
        StyledTextField loaiSP = new StyledTextField();
        nhapPanel.add(loaiSP);

        nhapPanel.add(new JLabel("Nhà cung cấp:"));
        StyledTextField ncc = new StyledTextField();
        nhapPanel.add(ncc);

        JButton btn1 = new JButton("Thêm sản phẩm");
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(btn1);

        add(nhapPanel,BorderLayout.CENTER);
        add(btnPanel,BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Thêm sản phẩm");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,500);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);


        FormAddProduct test = new FormAddProduct();
        f.add(test, BorderLayout.CENTER);
        f.setVisible(true);
    }
}
