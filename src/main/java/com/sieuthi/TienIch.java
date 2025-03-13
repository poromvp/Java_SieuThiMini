package com.sieuthi;

import javax.swing.*;
import java.awt.*;

public class TienIch {
    public static void quanlynutsidebar(JButton nut) {
        nut.setPreferredSize(new Dimension(150, 150));
        nut.setMaximumSize(new Dimension(278, 200));
        nut.setFont(new Font("Arial", Font.BOLD, 18));
        nut.setOpaque(true); // Đặt nền đục hay trong suốt
        nut.setFocusPainted(false); // Bỏ viền xanh khi nhấn vào nút
        nut.setContentAreaFilled(false); // Bỏ hiệu ứng nền mặc định của JButton
        nut.setForeground(new Color(226, 224, 221)); // Đặt màu chữ trên nút
        nut.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    public static void nutStyle(JButton nut) {
        nut.setPreferredSize(new Dimension(60, 50));
        nut.setMaximumSize(new Dimension(60, 60));
        nut.setFont(new Font("Arial", Font.BOLD, 18));
        nut.setOpaque(true); // Đặt nền đục hay trong suốt
        //nut.setFocusPainted(true); // Bỏ viền xanh khi nhấn vào nút
        //nut.setContentAreaFilled(false); // Bỏ hiệu ứng nền mặc định của JButton
        nut.setForeground(new Color(15, 16, 16)); // Đặt màu chữ trên nút
        nut.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
    }
}
