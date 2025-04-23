package GUI.NhanVien_BaoCaoBanHang;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import BLL.DonHangBLL;
import DTO.DonHangDTO;
import GUI.Admin_PanelThongKe.PanelExport;
import GUI.Admin_PanelThongKe.PanelTimThK;
import GUI.ComponentCommon.*;

public class PanelMainBaoCao extends JPanel implements ActionListener {
    private JPanel pn1, pn2, pn3;
    private JLabel lbTongDon, lbTongDoanhThu;
    private JButton btnTimKiem, btnInBaoCao;
    private StyledTable tb;
    private DefaultTableModel model;
    private JScrollPane scr;
    private ArrayList<DonHangDTO> HoaDon = DonHangBLL.getAllOrders();

    public void initPanel1() {
        pn1.setBorder(new CompoundBorder(new TitledBorder("Thống Kê"), new EmptyBorder(4, 4, 4, 4)));
        pn1.setLayout(new GridLayout(2, 3, 10, 10));

        JLabel lb1 = new JLabel("<html><center>Tổng đơn hàng<br>trong ngày:</html></center>");
        TienIch.labelStyle(lb1, 1, 20, null);
        pn1.add(lb1);

        JLabel lbIcon1 = new JLabel();
        TienIch.labelStyle(lbIcon1, 0, 20, "shopping-bag.png");
        pn1.add(lbIcon1);

        lbTongDon = new JLabel("56" + " Đơn");
        TienIch.labelStyle(lbTongDon, 2, 24, null);
        pn1.add(lbTongDon);

        JLabel lb2 = new JLabel("<html><center>Tổng doanh thu<br>trong ngày:</html></center>");
        TienIch.labelStyle(lb2, 1, 20, null);
        pn1.add(lb2);

        JLabel lbIcon2 = new JLabel();
        TienIch.labelStyle(lbIcon2, 0, 20, "dollar.png");
        pn1.add(lbIcon2);

        lbTongDoanhThu = new JLabel("12,000" + " VND");
        TienIch.labelStyle(lbTongDoanhThu, 2, 24, null);
        pn1.add(lbTongDoanhThu);
    }

    public void initPanel2() {
        pn2.setBorder(new CompoundBorder(new TitledBorder("Chức Năng"), new EmptyBorder(4, 4, 4, 4)));
        pn2.setLayout(new GridLayout(1, 2, 10, 50));

        btnTimKiem = new JButton("Tìm Kiếm");
        TienIch.nutStyle(btnTimKiem, "search.png", 24, 90, 30);
        pn2.add(btnTimKiem);

        btnInBaoCao = new JButton("In Báo Cáo");
        TienIch.nutStyle(btnInBaoCao, "printer.png", 24, 90, 30);
        pn2.add(btnInBaoCao);

        btnTimKiem.setVerticalTextPosition(SwingConstants.BOTTOM); // Chữ ở dưới
        btnTimKiem.setHorizontalTextPosition(SwingConstants.CENTER); // Chữ căn giữa theo chiều ngang
        btnInBaoCao.setVerticalTextPosition(SwingConstants.BOTTOM); // Chữ ở dưới
        btnInBaoCao.setHorizontalTextPosition(SwingConstants.CENTER); // Chữ căn giữa theo chiều ngang

        btnTimKiem.addActionListener(this);
        btnInBaoCao.addActionListener(this);
    }

    public void initPanel3() {
        pn3.setBorder(new CompoundBorder(new TitledBorder("Danh sách các đơn hàng đã thanh toán"),
                new EmptyBorder(4, 4, 4, 4)));
        pn3.setLayout(new BorderLayout());

        String[] tencot = { "Mã đơn hàng", "Mã nhân viên", "PTTT", "Thành tiền", "Ngày" };
        for (DonHangDTO hd : HoaDon) {
            System.out.println(hd.getMaDH() + " " + hd.getMaNV() + " " + hd.getPtThanhToan() + " " + hd.getNgayTT());
        }
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        loadDonHang(HoaDon);
        StyledTable.TableEvent(tb, model, "HD"); // Giữ sự kiện double-click
        StyledTable.hoverTable(tb, model);
        scr = new JScrollPane(tb);
        pn3.add(scr, BorderLayout.CENTER);
    }

    private void loadDonHang(ArrayList<DonHangDTO> danhsach) {
        model.setRowCount(0);
        for (DonHangDTO hd : danhsach) {
            model.addRow(new Object[] {
                    hd.getMaDH(),
                    hd.getMaNV(),
                    hd.getPtThanhToan(),
                    TienIch.formatVND(DonHangBLL.tinhTongTienByMaDonHang(hd.getMaDH())),
                    TienIch.ddmmyyyy(hd.getNgayTT()) });
        }
    }

    public PanelMainBaoCao() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pn2 = new JPanel();
        initPanel2();
        add(pn2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pn1 = new JPanel();
        initPanel1();
        add(pn1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        pn3 = new JPanel();
        initPanel3();
        add(pn3, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTimKiem) {
            PanelTimThK panel = new PanelTimThK();
            UIManager.put("OptionPane.background", new Color(33, 58, 89));
            UIManager.put("Panel.background", new Color(33, 58, 89));
            UIManager.put("Button.background", Color.GRAY);
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 13));
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            UIManager.put("OptionPane.background", null);
            UIManager.put("Panel.background", null);
            UIManager.put("Button.background", null);
            UIManager.put("Button.foreground", null);
            UIManager.put("Button.font", null);
            if (result == 0) {
                panel.testt();
            }
        } else if (e.getSource() == btnInBaoCao) {
            PanelExport panel = new PanelExport();
            UIManager.put("OptionPane.background", new Color(33, 58, 89));
            UIManager.put("Panel.background", new Color(33, 58, 89));
            UIManager.put("Button.background", Color.GRAY);
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 13));
            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            UIManager.put("OptionPane.background", null);
            UIManager.put("Panel.background", null);
            UIManager.put("Button.background", null);
            UIManager.put("Button.foreground", null);
            UIManager.put("Button.font", null);
            if (result == JOptionPane.OK_OPTION) {
                if (panel.getSelectedFormat().equals("excel")) {
                    panel.XuatExccel(model);
                } else {
                    panel.XuatPDF(model);
                }
            } else if (result == JOptionPane.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "Đã hủy xuất file");
            } else {
                JOptionPane.showMessageDialog(null, "Đã hủy xuất file");
            }
        }
    }
}