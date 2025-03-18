package com.FormNhanVien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class LeftSidebarMenu extends JPanel implements ActionListener {

    private JButton btnHome;
    private JButton btnEmployee;
    private JButton btnProduct;
    private JButton btnReport;
    private JButton btnAccount;
    private JButton btnLogout;

    private ActionListener listener;  

    public JButton getBtnHome() {
        return btnHome;
    }

    public JButton getBtnEmployee() {
        return btnEmployee;
    }

    public JButton getBtnProduct() {
        return btnProduct;
    }

    public JButton getBtnReport() {
        return btnReport;
    }

    public JButton getBtnAccount() {
        return btnAccount;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public LeftSidebarMenu() {
        
        setBackground(Color.white);
        setMaximumSize(new Dimension(230, getHeight()));
        setLayout(new BorderLayout());  
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 1, 2, 2));  
        topPanel.setBackground(Color.WHITE);
        JPanel botPanel = new JPanel();
        botPanel.setBackground(Color.WHITE);
        
        btnHome = new JButton("Trang Chủ", new ImageIcon("src/main/resources/images/home.png"));
        btnEmployee = new JButton("Nhân Viên", new ImageIcon("src/main/resources/images/employee.png"));
        btnProduct = new JButton("Sản Phẩm", new ImageIcon("src/main/resources/images/product.png"));
        btnReport = new JButton("Báo Cáo", new ImageIcon("src/main/resources/images/report.png"));
        btnAccount = new JButton("Tài Khoản", new ImageIcon("src/main/resources/images/account.png"));
        btnLogout = new JButton("Đăng Xuất", new ImageIcon("src/main/resources/images/exit.png"));

        setButtonStyle(btnHome);
        setButtonStyle(btnEmployee);
        setButtonStyle(btnProduct);
        setButtonStyle(btnReport);
        setButtonStyle(btnAccount);
        setButtonStyle(btnLogout);

        btnHome.addActionListener(this);
        btnEmployee.addActionListener(this);
        btnProduct.addActionListener(this);
        btnReport.addActionListener(this);
        btnAccount.addActionListener(this);
        btnLogout.addActionListener(this);

        
        
        topPanel.add(btnHome);
        topPanel.add(btnEmployee);
        topPanel.add(btnProduct);
        topPanel.add(btnReport);
        topPanel.add(btnAccount);
        botPanel.add(btnLogout);
        add(topPanel,BorderLayout.NORTH);
        add(botPanel,BorderLayout.SOUTH);
    }

    public void setButtonStyle(JButton button) {
        button.setBackground(Color.WHITE); 
        button.setForeground(Color.BLACK); 
        button.setFocusPainted(false);  
        button.setBorderPainted(false);  
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Kiểu chữ
        button.setPreferredSize(new Dimension(200, 60)); // Kích thước nút
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Con trỏ khi hover
        Border emptyBorder = BorderFactory.createEmptyBorder(20,10,20,10);
        setBorder(emptyBorder);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(200, 200, 200)); // Màu nền khi hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(240, 240, 240)); // Màu nền khi không hover
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (listener != null) {
            listener.actionPerformed(e); 
        }
    }

    public void setActionListener(ActionListener listener) {
        this.listener = listener;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Detail Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        
        LeftSidebarMenu TEST = new LeftSidebarMenu();
        
        frame.add(TEST);
        frame.setVisible(true);
    }
}