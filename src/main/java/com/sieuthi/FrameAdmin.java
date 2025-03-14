package com.sieuthi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.FormNhanVien.FormMainNhanVien;
import com.FormNhanVien.LeftSidebarMenu;
import com.sieuthi.Admin_PanelThongKe.PanelMainThongKe;

import FormTaiKhoan.FormMainAccount;

public class FrameAdmin extends JFrame implements ActionListener {

    private JPanel rightPn;
    private LeftSidebarMenu leftMenu;  

    public FrameAdmin() {
        setTitle("Frame Quản Lý");
        setSize(1300, 750);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        leftMenu = new LeftSidebarMenu();
        leftMenu.setActionListener(this);  

        add(leftMenu, BorderLayout.WEST);

        rightPn = new JPanel();
        panelNhanVien();  

        add(rightPn, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrameAdmin());
    }

    public void panelNhanVien() {
        rightPn.removeAll();
        rightPn.setBackground(new Color(95, 200, 150));
        rightPn.setLayout(new BorderLayout());

        FormMainNhanVien formNhanVien = new FormMainNhanVien();
        rightPn.add(formNhanVien, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelSanPham() {
        rightPn.removeAll();
        rightPn.setBackground(new Color(53, 11, 77));
        rightPn.revalidate();
        rightPn.repaint();
        add(rightPn);
    }

    public void panelBaoCao() {
        PanelMainThongKe mainPanel=new PanelMainThongKe();

        JScrollPane src = new JScrollPane(mainPanel);
        src.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        src.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
        src.getVerticalScrollBar().setUnitIncrement(16);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelTaiKhoan() {
        rightPn.removeAll();
        rightPn.setBackground(new Color(55, 11, 77));

        FormMainAccount accountPanel = new FormMainAccount();
        rightPn.add(accountPanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == leftMenu.getBtnHome()) {
            panelNhanVien();
        } else if (e.getSource() == leftMenu.getBtnEmployee()) {
            panelNhanVien();
        } else if (e.getSource() == leftMenu.getBtnProduct()) {
            panelSanPham();
        } else if (e.getSource() == leftMenu.getBtnReport()) {
            panelBaoCao();
        } else if (e.getSource() == leftMenu.getBtnAccount()) {
            panelTaiKhoan();
        } else if (e.getSource() == leftMenu.getBtnLogout()) {
            JOptionPane.showMessageDialog(this, "Bạn đã đăng xuất.");
        }
    }
}
