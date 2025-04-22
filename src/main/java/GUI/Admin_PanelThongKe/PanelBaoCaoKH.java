package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import BLL.TheThanhVienBLL;
import DTO.TheThanhVienDTO;
import GUI.TienIch;
import GUI.ComponentCommon.StyledTable;

public class PanelBaoCaoKH extends JPanel implements ChangeListener, ActionListener {
    JButton btnTim;
    JLabel lbMota;
    JTabbedPane tab;
    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel model;
    JScrollPane scr;
    private ArrayList<TheThanhVienDTO> TTV = TheThanhVienBLL.getAllMembers();
    JPanel pn1, pn2, pn3, pn4;
    JPopupMenu popupMenu;
    JMenuItem exportItem;

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
        } else if (obj instanceof JScrollPane scr) {
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

    public PanelBaoCaoKH() {
        setBorder(new CompoundBorder(new TitledBorder("Báo cáo khách hàng"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        btnTim = new JButton("Tìm");
        TienIch.nutStyle(btnTim, "search.png", 30, 150, 60);
        add(btnTim, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        lbMota = new JLabel("*Standard: dành cho thành viên chi tiêu dưới 1,000,000 VND.");
        TienIch.labelStyle(lbMota, 2, 15, null);
        add(lbMota, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;

        String[] tencot = { "Mã thành viên", "Họ tên", "Số điện thoại", "Điểm tích lũy" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        loadThanhVien(TTV);
        TableControl.TableEvent(tb, model, "KH"); // Giữ sự kiện double-click
        scr = new JScrollPane(tb);

        pn1 = new JPanel();
        pn1.setLayout(new BorderLayout());
        pn1.add(scr, BorderLayout.CENTER);

        pn2 = new JPanel();
        pn2.setLayout(new BorderLayout());

        pn3 = new JPanel();
        pn3.setLayout(new BorderLayout());

        pn4 = new JPanel();
        pn4.setLayout(new BorderLayout());

        tab = new JTabbedPane();
        tab.addTab("Standard", pn1);
        tab.addTab("Tiềm năng", pn2);
        tab.addTab("VIP", pn3);
        tab.addTab("Diamond", pn4);
        add(tab, gbc);

        tab.addChangeListener(this);
        btnTim.addActionListener(this);

        // Thêm popup menu
        popupMenu = new JPopupMenu();
        exportItem = new JMenuItem("In Báo Cáo");
        exportItem.addActionListener(this);
        popupMenu.add(exportItem);
        // Thêm sự kiện chuột phải cho bảng
        showpupop(tb);
        showpupop(scr);
    }

    private void loadThanhVien(ArrayList<TheThanhVienDTO> ttv) {
        model.setRowCount(0);
        for (TheThanhVienDTO tv : ttv) {
            model.addRow(new Object[] {
                    tv.getMaTV(),
                    tv.getTenTV(),
                    tv.getSdt(),
                    tv.getDiemTL()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTim) {
            PanelTimKH panel = new PanelTimKH();
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == 0) {
                System.out.println("Bạn vừa nhập: " + panel.getTxtName());
            }
        } else if (e.getSource() == exportItem) {
            JOptionPane.showMessageDialog(null, "In báo cáo khách hàng...");
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int tabSelected = tab.getSelectedIndex();
        if (tabSelected == 0) {
            lbMota.setText("*Standard: dành cho thành viên chi tiêu dưới 1,000,000 VND.");
            pn1.add(scr, BorderLayout.CENTER);
        } else if (tabSelected == 1) {
            lbMota.setText("*Tiềm năng: dành cho thành viên chi tiêu từ 1,000,000 - 3,000,000 VND.");
            pn2.add(scr, BorderLayout.CENTER);
        } else if (tabSelected == 2) {
            lbMota.setText("*VIP: dành cho thành viên chi tiêu trên 3,000,000 VND.");
            pn3.add(scr, BorderLayout.CENTER);
        } else if (tabSelected == 3) {
            lbMota.setText("*Kim cương: dành cho thành viên chi tiêu trên 10,000,000 VND.");
            pn4.add(scr, BorderLayout.CENTER);
        }
    }
}