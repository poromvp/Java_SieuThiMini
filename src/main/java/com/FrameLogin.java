package com;

import com.ComponentCommon.StyledTextField;

import javax.swing.*;
import java.awt.*;

public class FrameLogin extends JFrame {
    public FrameLogin() {
        setTitle("Login");
        setSize(600, 500);
        setBackground(new Color(255,255,255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(true); // Khóa kích thước của frame

        String[] filepath = {
            "/images/icon/Logo.png",
            "/images/anhcho1.png",
            "/images/anhcho2.png",
            "/images/anhcho3.png"
        };
        // Logo
        //ImageIcon icon = new ImageIcon(TienIch.class.getResource("/images/icon/" + fileIcon));
        /*JLabel logoLabel = new JLabel(new ImageIcon(FrameLogin.class.getResource("/images/icon/" + filepath[0])));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(logoLabel, BorderLayout.NORTH);*/
        SlideshowPanel logo = new SlideshowPanel(filepath, 2000);
        add(logo, BorderLayout.CENTER);

        // Panel nhập liệu
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        infoPanel.add(new JLabel("Mã nhân viên:"), gbc);

        gbc.gridx = 1;
        StyledTextField maNV = new StyledTextField();
        infoPanel.add(maNV, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(new JLabel("Mật khẩu:"), gbc);

        gbc.gridx = 1;
        StyledTextField passWord = new StyledTextField();
        infoPanel.add(passWord, gbc);

        // Nút đăng nhập căn giữa
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginBtn = new JButton("Đăng nhập");
        infoPanel.add(loginBtn, gbc);

        add(infoPanel, BorderLayout.EAST);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FrameLogin();
    }
}

