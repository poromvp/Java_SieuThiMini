package GUI.FormWareHouse;

import javax.swing.*;

import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTable;

import java.awt.*;

public class FormProduct extends JPanel {
    private FormAddProduct addProduct = new FormAddProduct();
    public FormProduct(){
        setLayout(new BorderLayout());
        setBackground(Color.white);
        // Tạo panel Tìm Kiếm
        JPanel timKiempnl = new JPanel();
        timKiempnl.setBorder(BorderFactory.createTitledBorder("Tìm Kiếm"));
        timKiempnl.setLayout(new GridLayout(1, 3, 5, 5));
        String timKiemCombo[]={"Tìm theo mã",
                "Tìm theo tên sản phẩm",
                "Tìm theo loại sản phẩm "};
        JComboBox timKiemCb= new JComboBox(timKiemCombo);
        JTextField timKiemtxt = new JTextField();// Nhap thong tin tim kiem
        JButton timBtn = new JButton("Tìm");
        timKiempnl.add(timKiemCb);
        timKiempnl.add(timKiemtxt);
        timKiempnl.add(timBtn);
        add(timKiempnl,BorderLayout.NORTH);

        // Tạo bảng
        ButtonCustom themBtn = new ButtonCustom("Thêm","add",16,20,20);
        themBtn.addActionListener(e -> {
            JDialog info_product = new JDialog((Frame) null,true);
            info_product.setSize(400,300);
            info_product.setLocationRelativeTo(null);
            info_product.setLayout(new BorderLayout());
            info_product.add(addProduct,BorderLayout.CENTER);
            info_product.setVisible(true);
        });

        ButtonCustom suaBtn = new ButtonCustom("Sửa","edit",16,20,20);

        ButtonCustom xoaBtn = new ButtonCustom("Xóa","del",16,20,20);


        String[] headerCol = {"Mã SP", "Tên SP", "Giá", "Loai","Nhà cung cấp"};
        Object[][] data = {
                {1, "Sản phẩm A", 100000, "Bánh","Công ty A"},
                {2, "Sản phẩm B", 200000, "Nước","Công ty B"},
                {3, "Sản phẩm C", 150000, "Bánh","Công ty C"},
                {4, "Sản phẩm D", 180000, "Nước","Công ty A"},
                {5, "Sản phẩm E", 210000, "Bánh","Công ty A"}
        };



        JTable table = new StyledTable(data, headerCol);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBtn.add(themBtn);
        panelBtn.add(suaBtn);
        panelBtn.add(xoaBtn);

        JPanel infoProduct = new JPanel();
        infoProduct.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        infoProduct.setLayout(new BorderLayout());
        infoProduct.add(scrollPane,BorderLayout.CENTER);
        infoProduct.add(panelBtn,BorderLayout.SOUTH);
        infoProduct.setVisible(true);

        add(infoProduct,BorderLayout.CENTER);
    }

    public static void main (String[] args) {
        FormProduct test = new FormProduct();
        JFrame frame = new JFrame("Thông tin sản phẩm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.add(test,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
