package com.ComponentCommon;

import javax.swing.*;
import java.awt.*;

public class ButtonCustom extends JButton {
    private boolean isRounded = false; //có cho phép bo góc ko

    public ButtonCustom(String text, int fontsize) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, fontsize));
        setForeground(Color.WHITE);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        this.isRounded = true; //có bo góc
    }

    public ButtonCustom(String text, String type, int fontsize, int w, int h) {
        initComponent(text, type, fontsize, w, h);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isRounded) { // Chỉ bo góc nếu isRounded = true
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(52, 171, 235)); 
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2.dispose();
        }
        super.paintComponent(g);
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

        ButtonCustom button = new ButtonCustom("lưu",16);
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Button Clicked!"));

        frame.add(button);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
