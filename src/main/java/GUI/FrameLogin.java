package GUI;

import javax.swing.*;

import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.TienIch;

import java.awt.*;
import java.awt.event.*;

public class FrameLogin extends JFrame {
    public FrameLogin() {
        setTitle("Login");
        setSize(500, 300);
        setBackground(new Color(255,255,255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(true); // Khóa kích thước của frame

        String[] filepath = {
            "/images/icon/Logo.png",
//            "/images/anhcho1.png",
//            "/images/anhcho2.png",
//            "/images/anhcho3.png"
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
        ButtonCustom loginBtn = new ButtonCustom("Đăng nhập",16,"black");
        infoPanel.add(loginBtn, gbc);

        add(infoPanel, BorderLayout.EAST);

        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event.getID() == WindowEvent.WINDOW_OPENED) {
                Window window = (Window) event.getSource();
                ImageIcon icon = new ImageIcon(TienIch.class.getResource("/images/icon/supermarket.png"));
                Image img = icon.getImage();
                Image resizedImg = img.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
                window.setIconImage(new ImageIcon(resizedImg).getImage());
            }
        }, AWTEvent.WINDOW_EVENT_MASK);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FrameLogin();
    }
}

