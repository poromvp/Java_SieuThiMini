package GUI;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoanDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.TienIch;

public class FrameLogin extends JFrame {
    private Color bgColor = new Color(17, 32, 51);
    private TaiKhoanBLL bll;
    private TaiKhoanDTO tk;
    public FrameLogin() {
        this.bll = new TaiKhoanBLL(); 
        setTitle("Đăng nhập");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event.getID() == WindowEvent.WINDOW_OPENED) {
                Window window = (Window) event.getSource();
                ImageIcon icon = new ImageIcon(TienIch.class.getResource("/images/icon/supermarket.png"));
                Image img = icon.getImage();
                Image resizedImg = img.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
                window.setIconImage(new ImageIcon(resizedImg).getImage());
            }
        }, AWTEvent.WINDOW_EVENT_MASK);

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
        rightPanel.add(new JLabel("Tên tài khoản:"), gbc);

        gbc.gridx = 1;
        StyledTextField tenTKField = new StyledTextField();
        rightPanel.add(tenTKField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        rightPanel.add(new JLabel("Mật khẩu:"), gbc);

        gbc.gridx = 1;
        JPasswordField passWord = new JPasswordField();
        passWord.setEchoChar('*');
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
        ButtonCustom loginBtn = new ButtonCustom("Đăng nhập", 16, "black");
        loginBtn.addActionListener(e -> {
            String tenTK = tenTKField.getText();
            char[] passChars = passWord.getPassword();
            String password = new String(passChars);
            if (bll.loginCheck(tenTK, password)) {
                tk = bll.getTaiKhoanDTO(tenTK);
                String quyen = tk.getQuyen();
                if (tk.getTrangThai().equalsIgnoreCase("ACTIVE")){
                    if (quyen != null) {
                        JOptionPane.showMessageDialog(this, "ĐĂNG NHẬP THÀNH CÔNG", "LOGIN", JOptionPane.INFORMATION_MESSAGE);
                        if (quyen.equalsIgnoreCase("ADMIN")) {
                            new FrameAdmin(Integer.toString(tk.getMaNV()));  
                        } else if (quyen.equalsIgnoreCase("Nhân Viên")) {
                            new FrameEmployee(Integer.toString(tk.getMaNV()));  
                        } else if (quyen.equalsIgnoreCase("Quản Lý Kho")) {
                            new FrameQuanLyKho(Integer.toString(tk.getMaNV()));  
                        } else {
                            JOptionPane.showMessageDialog(this, "Quyền không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        System.out.println("không có quyền");
                    }
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(this,"TÀI KHOẢN ĐÃ BỊ KHÓA","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        rightPanel.add(loginBtn, gbc);

        mainPanel.add(rightPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        // 	try {
        //     // Đổi Look & Feel sang hệ điều hành
        //     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        new FrameLogin();
    }
}
