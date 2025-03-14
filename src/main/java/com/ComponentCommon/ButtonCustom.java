package com.ComponentCommon;

import javax.swing.*;
import java.awt.*;

public class ButtonCustom extends JButton {

    public ButtonCustom(String text, String type, int fontsize, int w, int h) {
        initComponent(text, type, fontsize, w, h);
    }
    public ButtonCustom(String text, int fontsize) {
        this.setBackground(new Color(30,144,255));  // Thiết lập màu nền
        this.setBorderPainted(false);    // Ẩn viền nút
        this.setContentAreaFilled(true); // Cho phép vẽ màu nền
        this.setOpaque(true);            // Đảm bảo nền không trong suốt
        this.setFont(new Font("Arial", Font.PLAIN, fontsize));
        this.setText(text);
        this.setForeground(Color.WHITE);
    }
    

    private void initComponent(String text, String type, int fontsize, int w, int h) {
        ImageIcon addURL = new ImageIcon("src/main/resources/images/icon/addIcon.png");
        ImageIcon delURL = new ImageIcon("src/main/resources/images/icon/delIcon.png");
        ImageIcon editURL = new ImageIcon("src/main/resources/images/icon/editIcon.png");

        switch (type) {
            case "del":
                this.setIcon(resizeIcon(delURL, w, h));
                break;
            case "add":
                this.setIcon(resizeIcon(addURL, w, h));
                break;
            case "edit":
                this.setIcon(resizeIcon(editURL, w, h));
                break;
            default:
                break;
        }

        this.setText(text);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
        this.setFont(new Font("Arial", Font.PLAIN, fontsize));

        // Xóa viền và background
        this.setBorderPainted(false);  
        this.setContentAreaFilled(false); 
        this.setFocusPainted(false);
         
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        if (img != null) {
            Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImg);
        }
        return icon;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test ButtonCustom");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new FlowLayout());

        ButtonCustom button = new ButtonCustom("Click Me", "del", 16, 80, 80);
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Button Clicked!"));

        frame.add(button);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
