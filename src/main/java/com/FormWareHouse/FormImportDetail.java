package com.FormWareHouse;

import com.ComponentCommon.StyledTable;

import javax.swing.*;
import java.awt.*;

public class FormImportDetail extends JPanel {
    public FormImportDetail(){
        setLayout(new BorderLayout());
        String[] headerCol = {"Mã sản phẩm","Tên sản phẩm","Giá nhập","Số lượng", "Nhà cung cấp"};
        Object[][] data ={
                {"SP001","Sting",8000,20,"Công ty A"},
                {"SP001","Sting",8000,20,"Công ty A"},
                {"SP001","Sting",8000,20,"Công ty A"},
                {"SP001","Sting",8000,20,"Công ty A"},
                {"SP001","Sting",8000,20,"Công ty A"},
                {"SP001","Sting",8000,20,"Công ty A"},
                {"SP001","Sting",8000,20,"Công ty A"},
        };
        JTable table = new StyledTable(data,headerCol);
        JScrollPane import_detail = new JScrollPane(table);
        import_detail.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(import_detail,BorderLayout.CENTER);
     }
    public static void main(String[] args) {
        JFrame f = new JFrame("Chi tiết đơn nhập hàng");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,500);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);


        FormImportDetail test = new FormImportDetail();
        f.add(test, BorderLayout.CENTER);
        f.setVisible(true);
    }
}
