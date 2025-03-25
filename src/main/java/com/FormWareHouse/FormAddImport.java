package com.FormWareHouse;

import com.ComponentCommon.StyledTable;
import com.ComponentCommon.StyledTextField;

import javax.swing.*;
import java.awt.*;

public class FormAddImport extends JPanel {
    public FormAddImport(){
        setLayout(new BorderLayout());
        setBackground(Color.white);

        //Tao panel de nhap chi tiet san pham
        JPanel import_info = new JPanel();
        import_info.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm "));
        import_info.setLayout(new GridLayout(6, 2, 5, 5));
        import_info.add(new JLabel("Mã sản phẩm: "));
        StyledTextField maSP = new StyledTextField();
        import_info.add(maSP);

        import_info.add(new JLabel("Số lượng:"));
        StyledTextField soLuong = new StyledTextField();
        import_info.add(soLuong);

        import_info.add(new JLabel("Giá:"));
        StyledTextField gia = new StyledTextField();
        import_info.add(gia);

        import_info.add(new JLabel("Nhà cung cấp:"));
        StyledTextField ngaySX = new StyledTextField();
        import_info.add(ngaySX);

        import_info.add(new JLabel("Lô hàng:"));
        StyledTextField ngayHH = new StyledTextField();
        import_info.add(ngayHH);

        JButton btnThemSanPham = new JButton("Thêm sản phẩm ");
        JButton btnHoanTat = new JButton("Hoàn tất đơn nhập hàng");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnThemSanPham);
        buttonPanel.add(btnHoanTat);
        import_info.add(buttonPanel);


        add(import_info,BorderLayout.NORTH);

        //Tao panel hien thi bang thong tin cac san pham hien tai
        JPanel product_info = new JPanel();
        product_info.setLayout(new BorderLayout());
        product_info.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        String[] headerCol = {"Mã SP", "Tên SP", "Tồn kho", "Loai","Nhà cung cấp"};
        Object[][] data = {
                {1, "Sản phẩm A", 10, "Bánh","Công ty A"},
                {2, "Sản phẩm B", 20, "Nước","Công ty B"},
                {3, "Sản phẩm C", 15, "Bánh","Công ty C"},
                {4, "Sản phẩm D", 18, "Nước","Công ty A"},
                {5, "Sản phẩm E", 21, "Bánh","Công ty A"}
        };

        JTable table_product = new StyledTable(data,headerCol);
        JScrollPane scrollPane = new JScrollPane(table_product);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        product_info.add(scrollPane,BorderLayout.CENTER);
        add(product_info,BorderLayout.CENTER);

        //Chi tiet don hang
        FormImportDetail importDetail= new FormImportDetail();
        add(importDetail,BorderLayout.WEST);

    }

    public static void main (String[] args) {
        FormAddImport test = new FormAddImport();
        JFrame frame = new JFrame("Thông tin sản phẩm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.add(test,BorderLayout.CENTER);
        frame.setVisible(true);
    }

}
