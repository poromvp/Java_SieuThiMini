package com.sieuthi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import com.sieuthi.Admin_PanelThongKe.PanelChiPhi;
import com.sieuthi.Admin_PanelThongKe.PanelChonNgay;
import com.sieuthi.Admin_PanelThongKe.PanelDoanhThuNangCao;
import com.sieuthi.Admin_PanelThongKe.PanelDoanhThu;
import com.sieuthi.Admin_PanelThongKe.PanelBieuDo;

public class FrameAdmin extends JFrame implements ActionListener {
    JButton btn1, btn2, btn3, btn4, btn5, btn7;
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

    JLabel lbTongDoanhThu, lbChuyenKhoan, lbTienMat, lbGiaVon, lbLoiNhuan, lbChiPhi;
    JDateChooser to, from;
    JComboBox<String> chonThoiGian, chonDoiTuong;
    JTable tb;
    JButton btnIn, btnGui;

    public void panelBaoCao() {
        /*
         * Xem báo cáo doanh thu theo ngày/tháng.
         * Xem số lượng đơn hàng đã bán.
         * Thống kê mặt hàng bán chạy.
         */
        pn2.removeAll();
        pn2.setBackground(new Color(176, 90, 20));

        pn2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Bảng mở rộng theo chiều ngang và dọc
        gbc.weightx = 1.0; // Giãn ngang
        gbc.weighty = 0.001; // Giãn dọc

        gbc.gridx = 0;
        gbc.gridy = 0;
        to = new JDateChooser();
        from = new JDateChooser();
        String[] items = { "Hôm Nay", "Hôm Qua", "Tháng Này", "Tháng Trước", "Năm Nay", "Năm Trước" };
        chonThoiGian = new JComboBox<>(items);
        PanelChonNgay pnlchonngay = new PanelChonNgay(to, from, chonThoiGian);
        pn2.add(pnlchonngay, gbc);

        gbc.weightx = 1.0; // Giãn ngang
        gbc.weighty = 1.0; // Giãn dọc
        gbc.gridx = 0;
        gbc.gridy = 1;
        PanelBieuDo pnlbieudo = new PanelBieuDo();
        pn2.add(pnlbieudo, gbc);

        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 2;
        lbTongDoanhThu = new JLabel("122,000,000");
        lbChuyenKhoan = new JLabel("12,000,000");
        lbTienMat = new JLabel("110,000,000");
        lbGiaVon = new JLabel("85,000,000");
        lbChiPhi = new JLabel("5,000,000");
        lbLoiNhuan = new JLabel("32,000,000");
        PanelDoanhThu pnldoanhthu = new PanelDoanhThu(lbTongDoanhThu, lbChuyenKhoan, lbTienMat, lbGiaVon, lbLoiNhuan,
                lbChiPhi);
        pn2.add(pnldoanhthu, gbc);

        /*
         * gbc.weightx = 0.01; // Giãn ngang
         * gbc.weighty = 0.5; // Giãn dọc
         * gbc.gridx = 0;
         * gbc.gridy = 3;
         * PanelChiPhi pnlthuchi = new PanelChiPhi();
         * JScrollPane scroll1 = new JScrollPane(pnlthuchi,
         * JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, // Thanh cuộn dọc tự động
         * JScrollPane.HORIZONTAL_SCROLLBAR_NEVER // Không có thanh cuộn ngang
         * );
         * pn2.add(scroll1, gbc);
         */

        gbc.weightx = 1.0; // Giãn ngang
        gbc.weighty = 1.0; // Giãn dọc
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 4;

        String[] characts = { "Sản Phẩm", "Đơn Hàng", "Ngày", "Khách Hàng", "Nhân Viên" };
        chonDoiTuong = new JComboBox<>(characts);

        // Dữ liệu của bảng
        String[][] data = {
                { "1", "Táo", "10", "KH120", "1,100", "NV001", "20/02/2025" },
                { "2", "Cam", "20", "KH126", "9,800", "NV002", "20/02/2025" },
                { "3", "Chuối", "15", "KH122", "10,200", "NV003", "20/02/2025" }
        };
        // Tên cột
        String[] columnNames = { "ID", "Tên sản phẩm", "Số lượng", "Mã Khách Hàng", "Giá Tiền", "Mã Nhân Viên",
                "Ngày Tạo" };

        tb = new JTable(data, columnNames);
        btnIn = new JButton("In Báo Cáo");
        btnGui = new JButton("Gửi Báo Cáo");
        PanelDoanhThuNangCao pnldoanhthunangcao = new PanelDoanhThuNangCao(chonDoiTuong,tb,btnIn,btnGui);
        JScrollPane scroll2 = new JScrollPane(pnldoanhthunangcao,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, // Thanh cuộn dọc tự động
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER // Không có thanh cuộn ngang
        );

        pn2.add(scroll2, gbc);

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
