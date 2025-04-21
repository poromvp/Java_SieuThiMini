package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;


import BLL.DonHangBLL;
import DTO.DonHangDTO;
import GUI.TienIch;

public class PanelDoanhThu extends JPanel implements ActionListener {
    JLabel tongdoanhthu, tongdonhang;
    JComboBox<String> mocthoigian;
    JTable tb;
    DefaultTableModel model;
    public ArrayList<DonHangDTO> HoaDon = DonHangBLL.getAllOrders();
    JButton btnTim;
    JPanel pn1, pn2, pn3;
    JPopupMenu popupMenu;
    JMenuItem exportItem;

    public void initPanel1() {
        loadThongKe(HoaDon);
        pn1.setBorder(new CompoundBorder(new TitledBorder("Thống kê"), new EmptyBorder(4, 4, 4, 4)));
        pn1.setLayout(new GridLayout(2, 2));

        JLabel tong = new JLabel(("Tổng Doanh Thu:"));
        TienIch.labelStyle(tong, 4, 20, null);
        pn1.add(tong);

        TienIch.labelStyle(tongdoanhthu, 2, 20, null);
        pn1.add(tongdoanhthu);

        JLabel tonghd = new JLabel("Tổng đơn hàng (hóa đơn):");
        TienIch.labelStyle(tonghd, 4, 20, null);
        pn1.add(tonghd);

        TienIch.labelStyle(tongdonhang, 2, 20, null);
        pn1.add(tongdonhang);
    }

    public void initPanel2() {
        pn2.setBorder(new CompoundBorder(new TitledBorder("Hành động"), new EmptyBorder(4, 4, 4, 4)));
        pn2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        btnTim = new JButton("Tìm");
        TienIch.nutStyle(btnTim, "search.png", 17, 0, 0);
        pn2.add(btnTim, gbc);

        btnTim.addActionListener((ActionListener) this);
    }

    public void initPanel3() {
        pn3.setBorder(new CompoundBorder(new TitledBorder("Danh sách"), new EmptyBorder(4, 4, 4, 4)));
        pn3.setLayout(new BorderLayout());
        String[] tencot = { "Mã đơn hàng", "Mã nhân viên", "PTTT", "Thành tiền", "Ngày" };
        for (DonHangDTO hd : HoaDon) {
            System.out.println(hd.getMaDH() + " " + hd.getMaNV() + " " + hd.getPtThanhToan() + " " + hd.getNgayTT());
        }
        model = new DefaultTableModel(tencot, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        loadDonHang(HoaDon);
        tb = new JTable(model);
        TableControl.TableStyle(tb, model);
        TableControl.TableEvent(tb, model, "HD");
        JScrollPane scr = new JScrollPane(tb);
        pn3.add(scr, BorderLayout.CENTER);
        // Thêm sự kiện chuột phải cho bảng
        showpupop(tb);
        showpupop(scr);
    }

    public PanelDoanhThu() {
        setBorder(new CompoundBorder(new TitledBorder("Báo cáo doanh thu tổng hợp"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pn1 = new JPanel();
        initPanel1();
        add(pn1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pn2 = new JPanel();
        initPanel2();
        add(pn2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        pn3 = new JPanel();
        initPanel3();
        add(pn3, gbc);

        // Thêm popup menu
        popupMenu = new JPopupMenu();
        exportItem = new JMenuItem("In Báo Cáo");
        exportItem.addActionListener(this);
        popupMenu.add(exportItem);

    }

    public void showpupop(Object obj) {
        if (obj instanceof JTable tb) {
            tb.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                private void showPopup(MouseEvent e) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });
        }
        else if(obj instanceof JScrollPane scr){
            scr.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                private void showPopup(MouseEvent e) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });
        }
    }

    /*
     * private void refreshTable() {
     * model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
     * for (hoadontemp s : HoaDon) {
     * model.addRow(new Object[] { s.getId(), s.getName(), s.getPrice(), s.getDate()
     * });
     * }
     * }
     */

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

    private void loadThongKe(ArrayList<DonHangDTO> danhsach) {
        double sumDoanhThu = 0;
        int sumDonHang = danhsach.size();
        for (DonHangDTO hd : danhsach) {
            sumDoanhThu += DonHangBLL.tinhTongTienByMaDonHang(hd.getMaDH());
        }
        tongdoanhthu = new JLabel(TienIch.formatVND(sumDoanhThu));
        tongdonhang = new JLabel(sumDonHang + " Đơn");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTim) {
            PanelTimThK panel = new PanelTimThK();
            // Đổi màu nền và chữ
            UIManager.put("OptionPane.background", Color.BLACK); // Nền
            UIManager.put("Panel.background", Color.ORANGE); // Nền bên trong
            UIManager.put("Button.background", Color.BLACK); // Nền nút OK, Cancel
            UIManager.put("Button.foreground", Color.WHITE); // Màu chữ nút
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 13));
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
            UIManager.put("OptionPane.background", null); // Nền
            UIManager.put("Panel.background", null); // Nền bên trong
            UIManager.put("Button.background", null); // Nền nút OK, Cancel
            UIManager.put("Button.foreground", null); // Màu chữ nút
            UIManager.put("Button.font", null);
            if (result == 0) {
                panel.testt();
            }
        }
    }
}
