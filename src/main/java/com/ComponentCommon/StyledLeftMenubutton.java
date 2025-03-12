package com.ComponentCommon;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StyledLeftMenubutton extends JButton {
    private String buttonName;
    private int radius = 50;
    private boolean isClicked = false; // Ban đầu không có viền bo góc

    public StyledLeftMenubutton(String pathImg, String buttonName) {
        setPreferredSize(new Dimension(230, 45));
        setBackground(new Color(28, 173, 193)); // Màu mặc định chưa nhấn
        this.buttonName = buttonName;
        setLayout(new BorderLayout());

        // Tắt nền và viền mặc định
        setContentAreaFilled(false);
        setOpaque(false);
        setBorderPainted(false);
        

        // Cài đặt icon và text
        ImageIcon icon = new ImageIcon(pathImg);
        Image img = icon.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        JLabel pn_img = new JLabel(scaledIcon);

        add(pn_img, BorderLayout.WEST);

        JLabel lbl_btnName = new JLabel(this.buttonName, JLabel.CENTER);
        lbl_btnName.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbl_btnName.setForeground(Color.WHITE);
        add(lbl_btnName, BorderLayout.CENTER);
    }

    // Hàm bật/tắt viền bo góc
    public void setClick(boolean bool) {
        isClicked = bool;
        
        revalidate();
        repaint();
    }

    // Vẽ nền bo tròn hoặc hình chữ nhật tùy trạng thái
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isClicked) {
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        } else {
            g2.setColor(getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        super.paintComponent(g); // Đảm bảo JLabel được hiển thị đúng
    }

    // Vẽ viền bo tròn khi nút được nhấn
    @Override
    protected void paintBorder(Graphics g) {
        if (isClicked) { // Chỉ vẽ viền khi nút được nhấn
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(5));
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, radius, radius);
        }
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("Rounded Button Example");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        StyledLeftMenubutton menuItem = new StyledLeftMenubutton("Java_SieuThiMini/src/main/resources/images/home.png", "Click Me");
        frame.add(menuItem);

        // Thêm nút test chuyển trạng thái
        JButton toggleButton = new JButton("Toggle Border");
        toggleButton.addActionListener(e -> {
            menuItem.setClick(!menuItem.isClicked);
        });

        frame.add(toggleButton);
        frame.setVisible(true);
    }
}
