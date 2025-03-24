package com.FormWareHouse;

import com.FormNhanVien.LeftSidebarMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

import static javax.swing.text.StyleConstants.setBackground;

public class LeftMenuWareHouse extends JPanel implements  ActionListener{
    private JButton btnImport;
    private JButton btnProduct;
    private JButton btnLogout;

    private ActionListener listener;

    public JButton getBtnImport() {
        return btnImport;
    }

    public JButton getBtnProduct() {
        return btnProduct;
    }

    public ActionListener getListener() {
        return listener;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public LeftMenuWareHouse(){
        setBackground(Color.white);
        setMaximumSize(new Dimension(230, getHeight()));
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 1, 2, 2));
        topPanel.setBackground(Color.WHITE);
        JPanel botPanel = new JPanel();
        botPanel.setBackground(Color.WHITE);


//        //Thay doi kich thuoc anh
//        ImageIcon originalIcon = new ImageIcon("src/main/resources/images/icon/Logo_1.png");
//        Image originalImage = originalIcon.getImage();
//        Image resizedImage = originalImage.getScaledInstance(200,200,Image.SCALE_SMOOTH);
//        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        //Logo
        JLabel logoLabel = new JLabel( new ImageIcon("src/main/resources/images/icon/Logo_2.png"));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(logoLabel,BorderLayout.NORTH);

        btnImport = new JButton("Nhập hàng", new ImageIcon("src/main/resources/images/ImportLogo.png"));
        btnProduct = new JButton("Sản phẩm", new ImageIcon("src/main/resources/images/product.png"));
        btnLogout = new JButton("Đăng Xuất", new ImageIcon("src/main/resources/images/exit.png"));

        setButtonStyle(btnImport);
        setButtonStyle(btnProduct);
        setButtonStyle(btnLogout);

        btnImport.addActionListener(this);
        btnProduct.addActionListener(this);
        btnLogout.addActionListener(this);

        topPanel.add(btnImport);
        topPanel.add(btnProduct);
        botPanel.add(btnLogout);
        add(topPanel,BorderLayout.CENTER);
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

        LeftMenuWareHouse TEST = new LeftMenuWareHouse();

        frame.add(TEST);
        frame.setVisible(true);
    }
}