package com.sieuthi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameAdmin extends JFrame implements ActionListener {
    JButton btn1, btn2, btn3, btn4, btn5;
    JButton btn6, btn7;
    JPanel pn2;

    public FrameAdmin() {
        setTitle("Frame Quản Lý");
        setBounds(100, 100, 1390, 700);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        btn1 = new JButton("<html><center>QUẢN LÝ<br>NHÂN VIÊN</center></html>");
        btn2 = new JButton("<html><center>QUẢN LÝ<br>SẢN PHẨM</center></html>");
        btn3 = new JButton("<html><center>QUẢN LÝ<br>DOANH THU</center></html>");
        btn4 = new JButton("ĐĂNG XUẤT");

        JPanel pn1 = new JPanel();
        pn1.setBackground(new Color(56, 57, 60));
        pn1.setBounds(0, 0, 278, 700);
        pn1.setLayout(new BoxLayout(pn1, BoxLayout.Y_AXIS));
        pn1.add(btn1);
        pn1.add(btn2);
        pn1.add(btn3);
        pn1.add(btn4);

        pn2 = new JPanel();
        panelNhanVien();

        TienIch.quanlynutsidebar(btn1);
        TienIch.quanlynutsidebar(btn2);
        TienIch.quanlynutsidebar(btn3);
        TienIch.quanlynutsidebar(btn4);

        btn1.addActionListener((ActionListener) this);
        btn2.addActionListener((ActionListener) this);
        btn3.addActionListener((ActionListener) this);
        btn4.addActionListener((ActionListener) this);

        add(pn1, BorderLayout.WEST);
        add(pn2, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrameAdmin());
    }

    public void panelNhanVien() {
        pn2.removeAll();
        pn2.setBackground(new Color(95, 200, 150));
        btn7 = new JButton("Thêm Nhân Viên");
        btn7.setMnemonic('T');
        TienIch.quanlynutsidebar(btn7);
        pn2.add(btn7);
        pn2.revalidate();
        pn2.repaint();
        add(pn2);
    }

    public void panelSanPham() {
        pn2.removeAll();
        pn2.setBackground(new Color(53, 11, 77));
        btn5 = new JButton("Cập Nhật Thông Tin Sản Phẩm");
        btn5.setMnemonic('C');
        TienIch.quanlynutsidebar(btn5);
        pn2.add(btn5);
        pn2.revalidate();
        pn2.repaint();
        add(pn2);
    }

    public void panelBaoCao() {
        /*
         * Xem báo cáo doanh thu theo ngày/tháng.
         * Xem số lượng đơn hàng đã bán.
         * Thống kê mặt hàng bán chạy.
         */
        pn2.removeAll();
        pn2.setBackground(new Color(176, 90, 20));
        btn6 = new JButton("Tìm Kiếm");
        btn6.setMnemonic('T');
        TienIch.quanlynutsidebar(btn6);
        pn2.add(btn6);
        pn2.revalidate();
        pn2.repaint();
        add(pn2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            panelNhanVien();
        }
        if (e.getSource() == btn3) {
            panelBaoCao();
        }
        if (e.getSource() == btn2) {
            panelSanPham();
        }
        if (e.getSource() == btn4) {
            JOptionPane.showMessageDialog(null, "Bạn Đã Nhấn Nút Đăng Xuất");
        }
    }
}
