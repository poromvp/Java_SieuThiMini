package com.FormEmployee;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class InterfaceOrder extends JPanel{

    private FormQRscan formQRscan ;
    private FormOrderDetailList formOrderDetailList;
    private FormFindProduct formFindProduct;
    private FormOrderInformation formOrderInformation;


    public InterfaceOrder(){


        formFindProduct = new FormFindProduct();
        formOrderDetailList = new FormOrderDetailList();
        formOrderInformation = new FormOrderInformation();
        formQRscan = new FormQRscan();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tìm thông tin sản phẩm"));
        tabbedPane.add("Quét QR", formQRscan);
        tabbedPane.add("Tìm kiếm", formFindProduct);


        this.setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Mở rộng cả chiều ngang và dọc
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các ô

        //  Cột 0 chiếm 2 phần (weightx = 2.0)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 3.0; // Cột 0 rộng hơn
        gbc.weighty = 0.2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(tabbedPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.8;
        add(formOrderDetailList, gbc);
        
        //  Cột 1 chiếm 1 phần (weightx = 1.0)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0.5; // Cột 1 hẹp hơn
        gbc.weighty = 1.0;
        add(formOrderInformation, gbc);

    }

     public static void main(String[] args) {
        JFrame frame = new JFrame("Đơn hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500); // Mở rộng chiều ngang để thấy rõ tỷ lệ
        frame.setLocationRelativeTo(null);
        JPanel panel = new InterfaceOrder( );
        // panel.setBackground(Color.CYAN);
        frame.add(panel);
        frame.setVisible(true);
    }


    
}
