package com.FormEmployee;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterfaceOrderManagement extends JPanel{

    public InterfaceOrderManagement(){
        setBackground(Color.pink);
    }

     public static void main(String[] args) {
        JFrame frame = new JFrame("Đơn hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500); // Mở rộng chiều ngang để thấy rõ tỷ lệ
        frame.setLocationRelativeTo(null);
        JPanel panel = new  InterfaceOrderManagement();
        // panel.setBackground(Color.CYAN);
        frame.add(panel);
        frame.setVisible(true);
    }
    
}
