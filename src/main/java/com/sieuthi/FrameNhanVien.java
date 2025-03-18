package com.sieuthi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*import com.sieuthi.NhanVien_BaoCaoBanHang.ExportPDF;
import com.sieuthi.NhanVien_BaoCaoBanHang.PDFViewer;*/
import com.sieuthi.NhanVien_BaoCaoBanHang.PanelMainBaoCao;

public class FrameNhanVien extends JFrame implements ActionListener {
    JButton btn0, btn1, btn2, btn3, btn4, btn5, btn7;
    JPanel pn2;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrameNhanVien());
    }

    public FrameNhanVien() {
        setTitle("Frame Nhân Viên");
        setBounds(100, 100, 1390, 700);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        btn0 = new JButton();
        btn1 = new JButton("BÁN HÀNG");
        btn2 = new JButton("<html><center>QUẢN LÝ<br>KHÁCH HÀNG</center></html>");
        btn3 = new JButton("<html><center>BÁO CÁO<br>BÁN HÀNG</center></html>");
        btn4 = new JButton("ĐĂNG XUẤT");

        JPanel pn1 = new JPanel();
        pn1.setBackground(new Color(56, 57, 60));
        pn1.setBounds(0, 0, 278, 700);
        pn1.setLayout(new BoxLayout(pn1, BoxLayout.Y_AXIS));
        pn1.add(btn0);
        pn1.add(btn1);
        pn1.add(btn2);
        pn1.add(btn3);
        pn1.add(btn4);

        pn2 = new JPanel();
        panelBanHang();

        TienIch.quanlynutsidebar(btn0);
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/P-Mart-logo.png").getPath());
        btn0.setIcon(icon);
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

    public void panelBanHang() {
        pn2.removeAll();
        pn2.setBackground(new Color(95, 200, 150));
        btn7 = new JButton("Bán Hàng");
        btn7.setMnemonic('B');
        TienIch.quanlynutsidebar(btn7);
        pn2.add(btn7);
        pn2.revalidate();
        pn2.repaint();
        add(pn2);
    }

    public void panelKhachHang() {
        pn2.removeAll();
        pn2.setBackground(new Color(53, 11, 77));
        btn5 = new JButton("Thêm Khách Hàng");
        btn5.setMnemonic('T');
        TienIch.quanlynutsidebar(btn5);
        pn2.add(btn5);
        pn2.revalidate();
        pn2.repaint();
        add(pn2);
    }
    
    public void panelBaoCao() {
        pn2.removeAll();
        pn2.setBackground(new Color(195, 196, 198));
        pn2.setLayout(new BorderLayout());
        PanelMainBaoCao mainPanel=new PanelMainBaoCao();
        pn2.add(mainPanel,BorderLayout.CENTER);
        pn2.revalidate();
        pn2.repaint();
        add(pn2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            panelBanHang();
        }
        if (e.getSource() == btn3) {
            panelBaoCao();
        }
        if (e.getSource() == btn2) {
            panelKhachHang();
        }
        if (e.getSource() == btn4) {
            JOptionPane.showMessageDialog(null, "Bạn Đã Nhấn Nút Đăng Xuất");
        }
    }

}
