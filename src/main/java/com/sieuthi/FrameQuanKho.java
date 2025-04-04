package com.sieuthi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameQuanKho extends JFrame implements ActionListener {
    JButton btn1, btn2, btn3, btn4, btn5;
    JButton btn6, btn7;
    JPanel pn2;

    public FrameQuanKho() {
        setTitle("Frame Quản Kho");
        setBounds(100, 100, 1390, 700);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        btn1 = new JButton("NHẬP KHO");
        btn2 = new JButton("<html><center>KIỂM TRA<br>HÀNG TỒN</center></html>");
        btn3 = new JButton("<html><center>BÁO CÁO<br>KHO</center></html>");
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
        panelNhapKho();

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
        SwingUtilities.invokeLater(() -> new FrameQuanKho());
    }

    public void panelNhapKho() {
        pn2.removeAll();
        pn2.setBackground(new Color(95, 200, 150));
        btn7 = new JButton("Nhập");
        btn7.setMnemonic('N');
        TienIch.quanlynutsidebar(btn7);
        pn2.add(btn7);
        pn2.revalidate();
        pn2.repaint();
        add(pn2);
    }

    public void panelHangTon() {
        pn2.removeAll();
        pn2.setBackground(new Color(53, 11, 77));
        btn5 = new JButton("Quầy Kho Số 1");
        btn5.setMnemonic('T');
        TienIch.quanlynutsidebar(btn5);
        pn2.add(btn5);
        pn2.revalidate();
        pn2.repaint();
        add(pn2);
    }

    public void panelBaoCao() {
        /*
         * Xem báo cáo hàng nhập/xuất theo ngày/tháng.
         * Kiểm tra hàng sắp hết.
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
            panelNhapKho();
        }
        if (e.getSource() == btn3) {
            panelBaoCao();
        }
        if (e.getSource() == btn2) {
            panelHangTon();
        }
        if (e.getSource() == btn4) {
            JOptionPane.showMessageDialog(null, "Bạn Đã Nhấn Nút Đăng Xuất");
        }
    }
}
