package com;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class SlideshowPanel extends JPanel {
    private Image[] images;
    private int currentIndex = 0;
    private Timer timer;

    public SlideshowPanel(String[] imagePaths, int delay) {
        // Load ảnh từ đường dẫn
        images = new Image[imagePaths.length];
        for (int i = 0; i < imagePaths.length; i++) {
            java.net.URL imgURL = getClass().getResource(imagePaths[i]);
            if (imgURL != null) {
                images[i] = new ImageIcon(imgURL).getImage();
            } else {
                System.err.println("Không tìm thấy ảnh: " + imagePaths[i]);
            }
        }
        

        // Tạo Timer để đổi ảnh
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % images.length; // Chuyển ảnh tiếp theo
                repaint(); // Vẽ lại JPanel
            }
        });
        timer.start(); // Bắt đầu tự động chuyển ảnh
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (images[currentIndex] != null) {
            // Vẽ ảnh
            g.drawImage(images[currentIndex], 0, 0, getWidth(), getHeight(), this);
        }
    }

}