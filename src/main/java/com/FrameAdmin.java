package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.FormNhanVien.FormMainNhanVien;
import com.FormNhanVien.LeftSidebarMenu;
import com.FormTaiKhoan.FormMainAccount;
import com.Admin_PanelThongKe.PanelMainThongKe;
import com.DashBoardPanel.MainDashBoard;
import com.FormWareHouse.FormProduct;

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
        panelDashBoard();  

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
        rightPn.setBackground(new Color(55, 11, 77));
        rightPn.setLayout(new BorderLayout());

        FormProduct productPanel= new FormProduct();
        rightPn.add(productPanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelBaoCao() {
        rightPn.removeAll();
        rightPn.setBackground(new Color(176, 90, 20));
        rightPn.setLayout(new BorderLayout());
        PanelMainThongKe mainPanel=new PanelMainThongKe();
        rightPn.add(mainPanel,BorderLayout.CENTER);
        rightPn.revalidate();
        rightPn.repaint();
        add(rightPn);
    }

    public void panelTaiKhoan() {
        rightPn.removeAll();
        rightPn.setBackground(new Color(55, 11, 77));

        FormMainAccount accountPanel = new FormMainAccount();
        rightPn.add(accountPanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelDashBoard(){
        rightPn.removeAll();
        rightPn.setLayout(new BorderLayout());

        MainDashBoard homePanel = new MainDashBoard();
        rightPn.add(homePanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
        add(rightPn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == leftMenu.getBtnHome()) {
            panelDashBoard();
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
