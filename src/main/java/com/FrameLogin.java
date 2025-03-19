package com;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class FrameLogin extends JFrame implements ActionListener {
    public JPanel pn1, pn2;
    public JTextArea txtUserName;
    public JPasswordField txtPass;
    public JCheckBox cbShowPass;
    public JButton btnSubmit;
    public char kytumacdinhcuaPass;

    public FrameLogin() {
        setTitle("Frame Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        pn1 = new JPanel();
        pn1.setBackground(new Color(201, 214, 226));
        pn1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        gbc.gridx = 0; // cột
        gbc.gridy = 0; // hàng
        gbc.weightx = 0.65; // Chiếm 70% chiều rộng
        gbc.weighty = 1.0; // Chiếm toàn bộ chiều cao
        gbc.fill = GridBagConstraints.BOTH; // Kéo giãn cả hai chiều
        add(pn1, gbc);

        pn2 = new JPanel();
        pn2.setBackground(new Color(183, 201, 54));
        pn2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        gbc.gridx = 1; // cột
        gbc.gridy = 0; // hàng
        gbc.weightx = 0.35; // Chiếm 30% chiều rộng
        gbc.weighty = 1.0; // Chiếm toàn bộ chiều cao
        gbc.fill = GridBagConstraints.BOTH; // Kéo giãn cả hai chiều
        add(pn2, gbc);

        String[] images = {
                getClass().getClassLoader().getResource("images/anhcho1.png").getPath(),
                getClass().getClassLoader().getResource("images/anhcho2.png").getPath(),
                getClass().getClassLoader().getResource("images/anhcho3.png").getPath()
        };

        SlideshowPanel slideshow = new SlideshowPanel(images, 2000);
        pn1.setLayout(new BorderLayout());
        pn1.add(slideshow, BorderLayout.CENTER);

        ImageIcon avtLogin = new ImageIcon(
                getClass().getClassLoader().getResource("images/avtloginandanh.jpg").getPath());
        // Thay đổi kích thước hình ảnh (50x50)
        Image scaledImage = avtLogin.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        JLabel lbAvatar = new JLabel(resizedIcon);
        lbAvatar.setSize(5, 5);
        pn2.setLayout(new GridBagLayout());
        GridBagConstraints gbcpn2 = new GridBagConstraints();

        gbcpn2.gridx = 0; // cột
        gbcpn2.gridy = 0;
        gbcpn2.gridwidth = 2;
        // Đảm bảo pn2 chiếm đúng 30% chiều rộng
        gbcpn2.weightx = 0.1;
        gbcpn2.weighty = 0.1;
        gbcpn2.anchor = GridBagConstraints.NORTH; // Căn các thành phần lên trên cùng
        gbcpn2.insets = new Insets(1, 1, 2, 1); // Khoảng cách giữa các thành phần
        gbcpn2.ipadx = 2; // Giảm padding ngang
        gbcpn2.ipady = 2; // Giảm padding dọc

        gbcpn2.fill = GridBagConstraints.HORIZONTAL;
        pn2.add(lbAvatar, gbcpn2);
        gbcpn2.fill = GridBagConstraints.NONE;

        JLabel lb1 = new JLabel("ĐĂNG NHẬP");
        lb1.setFont(new Font("Arial", Font.BOLD, 20));
        gbcpn2.gridx = 0;
        gbcpn2.gridy = 1;

        pn2.add(lb1, gbcpn2);

        gbcpn2.gridwidth = 1;

        JLabel lb2 = new JLabel("Tài Khoản: ");
        lb2.setFont(new Font("Arial", Font.BOLD, 14));
        gbcpn2.gridx = 0;
        gbcpn2.gridy = 2;

        pn2.add(lb2, gbcpn2);

        txtUserName = new JTextArea(1, 8);
        gbcpn2.gridx = 1;
        gbcpn2.gridy = 2;

        pn2.add(txtUserName, gbcpn2);

        JLabel lb3 = new JLabel("Mật Khẩu: ");
        lb3.setFont(new Font("Arial", Font.BOLD, 14));
        gbcpn2.gridx = 0;
        gbcpn2.gridy = 3;

        pn2.add(lb3, gbcpn2);

        txtPass = new JPasswordField(8);
        gbcpn2.gridx = 1;
        gbcpn2.gridy = 3;

        pn2.add(txtPass, gbcpn2);

        cbShowPass = new JCheckBox("Hiển thị mật khẩu");
        kytumacdinhcuaPass = txtPass.getEchoChar();
        gbcpn2.gridx = 0;
        gbcpn2.gridy = 4;

        pn2.add(cbShowPass, gbcpn2);

        gbcpn2.gridwidth = 2;
        btnSubmit = new JButton(new ImageIcon(
                getClass().getClassLoader().getResource("images/right-arrow.png").getPath()));
        btnSubmit.setFocusPainted(false); // Bỏ viền xanh khi nhấn vào nút
        btnSubmit.setContentAreaFilled(false); // Bỏ hiệu ứng nền mặc định của JButton
        gbcpn2.gridx = 0;
        gbcpn2.gridy = 5;

        pn2.add(btnSubmit, gbcpn2);

        btnSubmit.addActionListener((ActionListener) this);
        cbShowPass.addActionListener((ActionListener) this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            JOptionPane.showMessageDialog(null, "Bạn đã nhấn nút đăng nhập");
        }
        if (e.getSource() == cbShowPass) {
            if (cbShowPass.isSelected()) {
                txtPass.setEchoChar((char) 0);
            } else {
                txtPass.setEchoChar(kytumacdinhcuaPass);
            }
        }
    }

    public static void main(String[] args) {
        new FrameLogin();
    }
}
