package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import BLL.LoaiSanPhamBLL;
import BLL.SanPhamBLL;
import DTO.SanPhamDTO;
import GUI.ComponentCommon.*;

public class PanelKhoTongHop extends JPanel implements ChangeListener, ActionListener {
    JPanel pn1, pn2, pn3;
    JScrollPane scr;
    JLabel tongdonhang;
    JButton btnMore;
    JTabbedPane tab;
    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel model;
    JPopupMenu popupMenu;
    JMenuItem searchItem, exportItem;
    public ArrayList<SanPhamDTO> DsSP = (ArrayList<SanPhamDTO>) SanPhamBLL.getAllProducts();

    public PanelKhoTongHop() {
        setBorder(new CompoundBorder(new TitledBorder("Báo cáo kho tổng hợp"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel tongdh = new JLabel("<html><center>Tổng số hàng<br>nhập trong tháng:<center></html>");
        TienIch.labelStyle(tongdh, 1, 20, null);
        add(tongdh, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        tongdonhang = new JLabel("555");
        TienIch.labelStyle(tongdonhang, 2, 20, null);
        add(tongdonhang, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        btnMore = new JButton("<html><center>Xem danh sách<br>lô hàng đã nhập<center></html>");
        TienIch.nutStyle(btnMore, "list.png", 20, 40, 20);
        add(btnMore, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        String[] tencot = { "Mã sản phẩm", "Tên loại", "Tên sản phẩm" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        StyledTable.hoverTable(tb, model);
        loadSanPham(DsSP);
        scr = new JScrollPane(tb);

        pn1 = new JPanel();
        pn1.setLayout(new BorderLayout());
        pn1.add(scr, BorderLayout.CENTER);

        pn2 = new JPanel();
        pn2.setLayout(new BorderLayout());

        pn3 = new JPanel();
        pn3.setLayout(new BorderLayout());

        tab = new JTabbedPane();
        tab.addTab("Danh sách bán chạy", pn1);
        tab.addTab("Danh sách tồn nhiều", pn2);
        tab.addTab("Danh sách hàng sắp hết", pn3);
        add(tab, gbc);

        // Thêm popup menu
        popupMenu = new JPopupMenu();
        searchItem = new JMenuItem("Tìm Kiếm");
        exportItem = new JMenuItem("In Báo Cáo");
        searchItem.addActionListener(this);
        exportItem.addActionListener(this);
        popupMenu.add(searchItem);
        popupMenu.add(exportItem);

        // Thêm sự kiện chuột phải cho bảng
        showpupop(tb);
        showpupop(scr);
        tab.addChangeListener(this);
        btnMore.addActionListener(this);
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

    private void loadSanPham(ArrayList<SanPhamDTO> DsSP) {
        model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (SanPhamDTO sp : DsSP) {
            model.addRow(new Object[] {
                    sp.getMaSP(),
                    new LoaiSanPhamBLL().getLoaiSanPham(sp.getMaLSP()).getTenLoaiSP(),
                    sp.getTenSP()
            });
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int tabSelected = tab.getSelectedIndex();
        if (tabSelected == 0) {
            pn1.add(scr, BorderLayout.CENTER);
        } else if (tabSelected == 1) {
            pn2.add(scr, BorderLayout.CENTER);
        } else if (tabSelected == 2) {
            pn3.add(scr, BorderLayout.CENTER);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMore) {
            PanelDSLoHang panel = new PanelDSLoHang();
            JOptionPane.showMessageDialog(null, panel, "Xem Danh Sách", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == searchItem) {
            PanelTimKho panel = new PanelTimKho();
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
                System.out.println("Bạn vừa nhập: ");
            }
        } else if (e.getSource() == exportItem) {
            PanelExport panel = new PanelExport();
            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if(panel.getSelectedFormat().equals("excel")){
                    panel.XuatExccel(model);
                }
                else{
                    panel.XuatPDF(model);
                }
            } else if (result == JOptionPane.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "Đã hủy xuất file");
            } else {
                JOptionPane.showMessageDialog(null, "Đã hủy xuất file");
            }        }
    }
}