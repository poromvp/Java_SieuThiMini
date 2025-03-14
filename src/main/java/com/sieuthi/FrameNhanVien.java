package com.sieuthi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*import com.sieuthi.NhanVien_BaoCaoBanHang.ExportPDF;
import com.sieuthi.NhanVien_BaoCaoBanHang.PDFViewer;*/
import com.sieuthi.NhanVien_BaoCaoBanHang.PanelChucNang;
import com.sieuthi.NhanVien_BaoCaoBanHang.PanelTable;
import com.sieuthi.NhanVien_BaoCaoBanHang.PanelThongKe;
import com.sieuthi.NhanVien_BaoCaoBanHang.PanelTimKiem;
import com.sieuthi.NhanVien_BaoCaoBanHang.PanelXemChiTiet;

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

        btnTimKiem = new JButton("Tìm Kiếm");
        btnXemChiTiet = new JButton("Xem Chi Tiết");
        btnInBaoCao = new JButton("In Báo Cáo");
        PanelChucNang pnlChucNang = new PanelChucNang(btnTimKiem, btnXemChiTiet, btnInBaoCao);

        lbTongDon = new JLabel("56");
        lbTongDoanhThu = new JLabel("12,000");
        PanelThongKe pnlThongKe = new PanelThongKe(lbTongDon, lbTongDoanhThu);

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
        PanelTable pnlTable = new PanelTable(tableHoaDon);

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
        btnInBaoCao.addActionListener((ActionListener) this);

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
            PanelTimKiem panel = new PanelTimKiem();
            // Hiển thị JOptionPane
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            /*
             * giá trị của biến result sẽ là:
             * JOptionPane.OK_OPTION (0) nếu bạn nhấn OK.
             * JOptionPane.CANCEL_OPTION (2) nếu bạn nhấn Cancel.
             * JOptionPane.CLOSED_OPTION (-1) nếu bạn đóng hộp thoại bằng dấu X (góc trên
             * phải).
             */
            if (result == 0) {
                System.out.println("Bạn vừa nhập: " + panel.getTxtName());
            }
        }
        if (e.getSource() == btnXemChiTiet) {
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

            PanelXemChiTiet panel = new PanelXemChiTiet(tableHoaDon, dong);

            // Hiển thị JOptionPane với panel tự thiết kế
            JOptionPane.showMessageDialog(null, panel, "Xem Chi Tiết", JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() ==  btnInBaoCao){
            /*ExportPDF.exportToPDF(tableHoaDon, "doanh_thu.pdf");
            SwingUtilities.invokeLater(() -> new PDFViewer("doanh_thu.pdf").setVisible(true));*/
        }
    }

}
