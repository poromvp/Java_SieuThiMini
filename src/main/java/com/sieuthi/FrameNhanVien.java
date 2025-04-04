package com.sieuthi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class FrameNhanVien extends JFrame implements ActionListener {
    JButton btn1, btn2, btn3, btn4, btn5, btn7;
    JPanel pn2;

    public FrameNhanVien() {
        setTitle("Frame Nhân Viên");
        setBounds(100, 100, 1390, 700);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        btn1 = new JButton("BÁN HÀNG");
        btn2 = new JButton("<html><center>QUẢN LÝ<br>KHÁCH HÀNG</center></html>");
        btn3 = new JButton("<html><center>BÁO CÁO<br>BÁN HÀNG</center></html>");
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
        panelBanHang();

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

    JButton btnTimKiem, btnXemChiTiet, btnInBaoCao;
    JLabel lbTongDon, lbTongDoanhThu;
    JTable tableHoaDon;

    public void panelBaoCao() {
        /*
         * Xem danh sách đơn hàng đã bán.
         * Xem tổng doanh thu trong ngày.
         */
        pn2.removeAll();
        pn2.setBackground(new Color(195, 196, 198));
        pn2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lbBaoCao = new JLabel("BÁO CÁO BÁN HÀNG");
        lbBaoCao.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        // Đặt font chữ: Arial, đậm, cỡ 20
        lbBaoCao.setFont(new Font("Arial", Font.BOLD, 20));
        // Đặt màu chữ: Xanh dương
        lbBaoCao.setForeground(Color.WHITE);

        JPanel pnlChucNang = new JPanel();
        pnlChucNang.setBorder(new CompoundBorder(new TitledBorder("Chức Năng"), new EmptyBorder(4, 4, 4, 4)));
        pnlChucNang.setLayout(new GridBagLayout());
        GridBagConstraints gbcPnlChucNang = new GridBagConstraints();
        gbcPnlChucNang.fill = GridBagConstraints.BOTH;
        gbcPnlChucNang.weightx = 1.0;
        gbcPnlChucNang.weighty = 1.0;

        gbcPnlChucNang.gridx = 0;
        gbcPnlChucNang.gridy = 0;
        btnTimKiem = new JButton("Tìm Kiếm");
        btnTimKiem.setMnemonic('T');
        btnTimKiem.setFont(new Font("Arial", Font.BOLD, 16));
        btnTimKiem.setContentAreaFilled(false);
        btnTimKiem.setOpaque(true);
        btnTimKiem.setBackground(new Color(37, 150, 190));
        ImageIcon in = new ImageIcon(getClass().getResource("/images/icon/searchIcon.png").getPath());
        btnTimKiem.setIcon(in);
        btnTimKiem.setHorizontalTextPosition(SwingConstants.RIGHT);
        pnlChucNang.add(btnTimKiem, gbcPnlChucNang);

        gbcPnlChucNang.gridx = 0;
        gbcPnlChucNang.gridy = 1;
        btnXemChiTiet = new JButton("Xem Chi Tiết");
        btnXemChiTiet.setMnemonic('X');
        btnXemChiTiet.setFont(new Font("Arial", Font.BOLD, 16));
        btnXemChiTiet.setContentAreaFilled(false);
        btnXemChiTiet.setOpaque(true);
        btnXemChiTiet.setBackground(new Color(136, 236, 123));
        in = new ImageIcon(getClass().getResource("/images/icon/detailIcon.png").getPath());
        btnXemChiTiet.setIcon(in);
        btnXemChiTiet.setHorizontalTextPosition(SwingConstants.RIGHT);
        pnlChucNang.add(btnXemChiTiet, gbcPnlChucNang);

        gbcPnlChucNang.gridx = 1;
        gbcPnlChucNang.gridy = 0;
        btnInBaoCao = new JButton("In Báo Cáo");
        btnInBaoCao.setMnemonic('I');
        btnInBaoCao.setFont(new Font("Arial", Font.BOLD, 16));
        btnInBaoCao.setContentAreaFilled(false);
        btnInBaoCao.setOpaque(true);
        btnInBaoCao.setBackground(new Color(243, 191, 86));
        in = new ImageIcon(getClass().getResource("/images/icon/inIcon.png").getPath());
        btnInBaoCao.setIcon(in);
        btnInBaoCao.setHorizontalTextPosition(SwingConstants.RIGHT);
        pnlChucNang.add(btnInBaoCao, gbcPnlChucNang);

        JPanel pnlThongKe = new JPanel();
        pnlThongKe.setBorder(new CompoundBorder(new TitledBorder("Thống Kê"), new EmptyBorder(4, 4, 4, 4)));
        pnlThongKe.setLayout(new GridBagLayout());
        GridBagConstraints gbcPnlThongKe = new GridBagConstraints();
        gbcPnlThongKe.fill = GridBagConstraints.BOTH;
        gbcPnlThongKe.weightx = 1.0;
        gbcPnlThongKe.weighty = 1.0;

        gbcPnlThongKe.gridx = 0;
        gbcPnlThongKe.gridy = 0;
        JLabel lbtongdon = new JLabel("Tổng Đơn Hàng Trong Ngày: ");
        lbtongdon.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        lbtongdon.setFont(new Font("Arial", Font.BOLD, 20));
        pnlThongKe.add(lbtongdon, gbcPnlThongKe);
        gbcPnlThongKe.gridx = 1;
        gbcPnlThongKe.gridy = 0;
        lbTongDon = new JLabel("56");
        lbTongDon.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        lbTongDon.setFont(new Font("Arial", Font.BOLD, 20));
        pnlThongKe.add(lbTongDon, gbcPnlThongKe);

        gbcPnlThongKe.gridx = 0;
        gbcPnlThongKe.gridy = 1;
        JLabel lbtongdoanhthu = new JLabel("Tổng Doanh Thu Trong Ngày: ");
        lbtongdoanhthu.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        lbtongdoanhthu.setFont(new Font("Arial", Font.BOLD, 20));
        pnlThongKe.add(lbtongdoanhthu, gbcPnlThongKe);
        gbcPnlThongKe.gridx = 1;
        gbcPnlThongKe.gridy = 1;
        lbTongDoanhThu = new JLabel("12,000");
        lbTongDoanhThu.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa
        lbTongDoanhThu.setFont(new Font("Arial", Font.BOLD, 20));
        pnlThongKe.add(lbTongDoanhThu, gbcPnlThongKe);

        gbcPnlThongKe.gridx = 3;
        gbcPnlThongKe.gridy = 1;
        JLabel vnd = new JLabel("VND");
        vnd.setHorizontalAlignment(SwingConstants.LEFT);
        vnd.setFont(new Font("Arial", Font.BOLD, 20));
        pnlThongKe.add(vnd, gbcPnlThongKe);

        JPanel pnlTable = new JPanel();

        // Dữ liệu của bảng
        String[][] data = {
                { "1", "Táo", "10", "KH120", "1,100", "NV001", "20/02/2025" },
                { "2", "Cam", "20", "KH126", "9,800", "NV002", "20/02/2025" },
                { "3", "Chuối", "15", "KH122", "10,200", "NV003", "20/02/2025" }
        };

        // Tên cột
        String[] columnNames = { "ID", "Tên sản phẩm", "Số lượng", "Mã Khách Hàng", "Giá Tiền", "Mã Nhân Viên",
                "Ngày Tạo" };
        tableHoaDon = new JTable(data, columnNames);
        tableHoaDon.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16)); // Đổi font thành Arial, cỡ 14, không
                                                                                // đậm
        tableHoaDon.setFont(new Font("Arial", Font.PLAIN, 13)); // Đổi font thành Arial, cỡ 14, không đậm

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); // căn giữa
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tableHoaDon.getColumnCount(); i++) {
            tableHoaDon.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tableHoaDon);
        scrollPane.setPreferredSize(new Dimension(1200, 500));
        pnlTable.add(scrollPane);

        gbc.fill = GridBagConstraints.BOTH; // Bảng mở rộng theo chiều ngang và dọc
        gbc.weightx = 1.0; // Giãn ngang
        gbc.weighty = 1.0; // Giãn dọc

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        pn2.add(lbBaoCao, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        pn2.add(pnlChucNang, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pn2.add(pnlThongKe, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        pn2.add(pnlTable, gbc);

        pn2.revalidate();
        pn2.repaint();
        add(pn2);

        btnTimKiem.addActionListener((ActionListener) this);
        btnXemChiTiet.addActionListener((ActionListener) this);

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
        if (e.getSource() == btnTimKiem) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Sắp xếp dọc

            JTextField txtName = new JTextField(10);
            JTextField txtAge = new JTextField(10);
            JTextField txtEmail = new JTextField(10);

            panel.add(new JLabel("Họ và tên:"));
            panel.add(txtName);
            panel.add(new JLabel("Tuổi:"));
            panel.add(txtAge);
            panel.add(new JLabel("Email:"));
            panel.add(txtEmail);

            // Hiển thị JOptionPane
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == btnXemChiTiet) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JTextField txtUsername = new JTextField(10);
            txtUsername.setEnabled(false);
            int dong = tableHoaDon.getSelectedRow();

            if (dong == -1) { // Nếu không có dòng nào được chọn
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xem chi tiết!", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
                return; // Thoát khỏi phương thức
                /*
                 * Dòng return; có tác dụng kết thúc phương thức ngay lập tức, tránh việc thực
                 * hiện tiếp các dòng code bên dưới nếu người dùng chưa chọn dòng nào.
                 */
            }

            Object txt = tableHoaDon.getValueAt(dong, 1);
            txtUsername.setText(txt.toString());

            panel.add(new JLabel("Tên đăng nhập:"));
            panel.add(txtUsername);

            // Hiển thị JOptionPane với panel tự thiết kế
            int result = JOptionPane.showConfirmDialog(null, panel, "Xem Chi Tiết",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrameNhanVien());
    }
}
