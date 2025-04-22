package GUI;

import javax.swing.*;

import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTextField;

import java.awt.*;

public class FrameLogin extends JFrame {
    private Color bgColor = new Color(17, 32, 51);
    public FrameLogin() {
        setTitle("Đăng nhập");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/images/icon/Logo_1.png");
        this.setIconImage(icon);

        // Chia đôi frame: trái là ảnh, phải là form
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        getContentPane().add(mainPanel);

        // Panel logo hoặc slideshow
        String[] filepath = {
                "/images/icon/Logo.png",
                // "/images/anhcho1.png",
                // "/images/anhcho2.png"
        };
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);
        SlideshowPanel logo = new SlideshowPanel(filepath, 2000);
        leftPanel.add(logo, BorderLayout.CENTER);
        mainPanel.add(leftPanel);

        // Panel nhập liệu
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel loginLabel = new JLabel("Đăng Nhập Vào Hệ Thống");
        loginLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setForeground(new Color(45, 62, 80));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        rightPanel.add(loginLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        rightPanel.add(new JLabel("Mã nhân viên:"), gbc);

        gbc.gridx = 1;
        StyledTextField maNV = new StyledTextField();
        rightPanel.add(maNV, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        rightPanel.add(new JLabel("Mật khẩu:"), gbc);

        gbc.gridx = 1;
        JPasswordField passWord = new JPasswordField();
        passWord.setEchoChar('*'); // Nếu StyledTextField không hỗ trợ, hãy đổi sang JPasswordField
        passWord.setFont(new Font("SansSerif", Font.BOLD, 14));
        passWord.setForeground(Color.BLACK);
        passWord.setBackground(Color.WHITE);
        passWord.setCaretColor(Color.RED);
        passWord.setOpaque(true);
        passWord.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor, 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        setPreferredSize(new Dimension(200, 30));
        rightPanel.add(passWord, gbc);

        // Nút đăng nhập
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        ButtonCustom loginBtn = new ButtonCustom("Đăng nhập", 16, "#3498db");
        rightPanel.add(loginBtn, gbc);

        mainPanel.add(rightPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FrameLogin();
    }
}
