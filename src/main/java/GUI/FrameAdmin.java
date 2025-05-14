package GUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

import javax.swing.*;

import DTO.TaiKhoanDTO;
import GUI.Admin_PanelThongKe.PanelMainThongKe;
import GUI.Admin_QuanLyKM.DiscountManagementPanel;
import GUI.Admin_TheThanhVien.PanelMainThanhVien;
import GUI.ComponentCommon.TienIch;
import GUI.DashBoardPanel.MainDashBoard;
import GUI.FormNhanVien.FormMainNhanVien;
import GUI.FormNhanVien.LeftSidebarMenu;
import GUI.FormTaiKhoan.FormMainAccount;
import GUI.FormWareHouse.FormProduct;
import GUI.KhuyenMai.DiscountPanel;
import JDBC.DBConnection;
import javax.swing.border.EmptyBorder;

public class FrameAdmin extends JFrame implements ActionListener {

    private JPanel rightPn;
    private JPanel Center = new JPanel(new BorderLayout());
    private JPanel header = new JPanel(new BorderLayout());
    private JLabel lbl_header = new JLabel("Thống kê");
    private LeftSidebarMenu leftMenu;
    public TaiKhoanDTO tk;
    public String maNV = "1";
    public FrameAdmin(String maNV) {
        header.setBackground(new Color(33, 58, 89));
        header.setPreferredSize(new Dimension(0, 50));
        lbl_header.setForeground(new Color(255, 255, 255));
        lbl_header.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_header.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(lbl_header);
        Center.add(header, BorderLayout.NORTH);
        this.maNV = maNV;
        setTitle("Frame Quản Lý");
        setSize(1300, 750);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        leftMenu = new LeftSidebarMenu(maNV);
        leftMenu.setActionListener(this);
        leftMenu.setPreferredSize(new Dimension(250, 0));

        getContentPane().add(leftMenu, BorderLayout.WEST);

        rightPn = new JPanel();
        rightPn.setBackground(new Color(255, 255, 255));
        rightPn.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelDashBoard(maNV);

        Center.add(rightPn, BorderLayout.CENTER);
        getContentPane().add(Center, BorderLayout.CENTER);

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
        String maNV ="12312";
        SwingUtilities.invokeLater(() -> new FrameAdmin(maNV));
    }

    public void panelNhanVien() {
        Connection conn = DBConnection.getConnection();
        rightPn.removeAll();
        lbl_header.setText("Nhân Viên");
        // rightPn.setBackground(new Color(95, 200, 150));
        rightPn.setLayout(new BorderLayout());

        FormMainNhanVien formNhanVien = new FormMainNhanVien(conn);
        rightPn.add(formNhanVien, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelSanPham() {
        rightPn.removeAll();
        lbl_header.setText("Sản phẩm");

        // rightPn.setBackground(new Color(55, 11, 77));
        rightPn.setLayout(new BorderLayout());

        FormProduct productPanel = new FormProduct();
        rightPn.add(productPanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelBaoCao(String MANV) {
        lbl_header.setText("Báo cáo");

        rightPn.removeAll();
        // rightPn.setBackground(new Color(176, 90, 20));
        rightPn.setLayout(new BorderLayout());
        PanelMainThongKe mainPanel = new PanelMainThongKe(MANV);
        rightPn.add(mainPanel, BorderLayout.CENTER);
        rightPn.revalidate();
        rightPn.repaint();
        // getContentPane().add(rightPn);
    }

    public void panelTaiKhoan() {
        lbl_header.setText("Tài khoản");

        rightPn.removeAll();
        // rightPn.setBackground(new Color(55, 11, 77));

        FormMainAccount accountPanel = new FormMainAccount();
        rightPn.add(accountPanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelDashBoard(String MANV) {
        lbl_header.setText("Thống kê");

        rightPn.removeAll();
        rightPn.setLayout(new BorderLayout());

        MainDashBoard homePanel = new MainDashBoard(MANV);
        rightPn.add(homePanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
        // getContentPane().add(rightPn);
    }

    public void panelTheThanhVien(String MANV) {
        lbl_header.setText("Thẻ thành viên");

        rightPn.removeAll();
        // rightPn.setBackground(new Color(176, 90, 20));
        rightPn.setLayout(new BorderLayout());
        PanelMainThanhVien mainPanel = new PanelMainThanhVien(MANV);
        rightPn.add(mainPanel, BorderLayout.CENTER);
        rightPn.revalidate();
        rightPn.repaint();
        // getContentPane().add(rightPn);
    }

    public void panelKhuyenMai() {
        lbl_header.setText("Khuyến mãi");

        rightPn.removeAll();
        // rightPn.setBackground(new Color(176, 90, 20));
        rightPn.setLayout(new BorderLayout());
        DiscountPanel mainPanel = new DiscountPanel();
        rightPn.add(mainPanel,BorderLayout.CENTER);
        rightPn.revalidate();
        rightPn.repaint();
        // getContentPane().add(rightPn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == leftMenu.getBtnHome()) {
            panelDashBoard(maNV);
        } else if (e.getSource() == leftMenu.getBtnEmployee()) {
            panelNhanVien();
        } else if (e.getSource() == leftMenu.getBtnProduct()) {
            panelSanPham();
        } else if (e.getSource() == leftMenu.getBtnReport()) {
            panelBaoCao(maNV);
        } else if (e.getSource() == leftMenu.getBtnAccount()) {
            panelTaiKhoan();
        } else if (e.getSource() == leftMenu.getBtnLogout()) {
            JOptionPane.showMessageDialog(this, "Bạn đã đăng xuất.");
            this.dispose();
            new FrameLogin();
            
        } else if (e.getSource() == leftMenu.getBtnTheTV()) {
            panelTheThanhVien(maNV);
        }else if (e.getSource() == leftMenu.getBtnKM()){
            panelKhuyenMai();
        }
    }
}
