package com.NhanVien_BaoCaoBanHang;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*import com.sieuthi.NhanVien_BaoCaoBanHang.ExportPDF;
import com.sieuthi.NhanVien_BaoCaoBanHang.PDFViewer;*/

import com.TienIch;

public class PanelChucNang extends JPanel implements ActionListener{

    JButton btnTimKiem, btnInBaoCao;

    public PanelChucNang() {
        setBorder(new CompoundBorder(new TitledBorder("Chức Năng"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridLayout(1, 2, 10, 50));

        btnTimKiem = new JButton("Tìm Kiếm");
        TienIch.nutStyle(btnTimKiem, "search.png", 24, 90,30);
        add(btnTimKiem);
        btnInBaoCao = new JButton("In Báo Cáo");
        TienIch.nutStyle(btnInBaoCao, "printer.png", 24, 90, 30);
        add(btnInBaoCao);

        btnTimKiem.addActionListener((ActionListener)this);
        btnInBaoCao.addActionListener((ActionListener)this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
        if (e.getSource() ==  btnInBaoCao){
            /*ExportPDF.exportToPDF(tableHoaDon, "doanh_thu.pdf");
            SwingUtilities.invokeLater(() -> new PDFViewer("doanh_thu.pdf").setVisible(true));*/
        }
    }

    public PanelChucNang(JButton btnTimKiem, JButton btnXemChiTiet, JButton btnInBaoCao) {
        setBorder(new CompoundBorder(new TitledBorder("Chức Năng"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbcPnlChucNang = new GridBagConstraints();
        gbcPnlChucNang.fill = GridBagConstraints.BOTH;
        gbcPnlChucNang.weightx = 1.0;
        gbcPnlChucNang.weighty = 1.0;
        setBackground(new Color(155, 182, 193));

        gbcPnlChucNang.gridx = 0;
        gbcPnlChucNang.gridy = 0;
        btnTimKiem.setMnemonic('T');
        btnTimKiem.setFont(new Font("Arial", Font.BOLD, 16));
        btnTimKiem.setContentAreaFilled(false);
        btnTimKiem.setOpaque(true);
        btnTimKiem.setBackground(new Color(37, 150, 190));
        ImageIcon in = new ImageIcon(getClass().getResource("/images/icon/searchIcon.png").getPath());
        btnTimKiem.setIcon(in);
        btnTimKiem.setHorizontalTextPosition(SwingConstants.RIGHT);
        add(btnTimKiem, gbcPnlChucNang);

        gbcPnlChucNang.gridx = 0;
        gbcPnlChucNang.gridy = 1;
        btnXemChiTiet.setMnemonic('X');
        btnXemChiTiet.setFont(new Font("Arial", Font.BOLD, 16));
        btnXemChiTiet.setContentAreaFilled(false);
        btnXemChiTiet.setOpaque(true);
        btnXemChiTiet.setBackground(new Color(136, 236, 123));
        in = new ImageIcon(getClass().getResource("/images/icon/detailIcon.png").getPath());
        btnXemChiTiet.setIcon(in);
        btnXemChiTiet.setHorizontalTextPosition(SwingConstants.RIGHT);
        add(btnXemChiTiet, gbcPnlChucNang);

        gbcPnlChucNang.gridx = 1;
        gbcPnlChucNang.gridy = 0;
        btnInBaoCao.setMnemonic('I');
        btnInBaoCao.setFont(new Font("Arial", Font.BOLD, 16));
        btnInBaoCao.setContentAreaFilled(false);
        btnInBaoCao.setOpaque(true);
        btnInBaoCao.setBackground(new Color(243, 191, 86));
        in = new ImageIcon(getClass().getResource("/images/icon/inIcon.png").getPath());
        btnInBaoCao.setIcon(in);
        btnInBaoCao.setHorizontalTextPosition(SwingConstants.RIGHT);
        add(btnInBaoCao, gbcPnlChucNang);

    }
}
