package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import BLL.LoaiSanPhamBLL;
import BLL.SanPhamBLL;
import DTO.SanPhamDTO;
import GUI.ComponentCommon.StyledTable;

public class PanelDSLoHang extends JPanel implements ActionListener {
    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<SanPhamDTO> DsSP = (ArrayList<SanPhamDTO>) SanPhamBLL.getAllProducts();
    JPopupMenu popupMenu;
    JMenuItem searchItem, exportItem;

    public PanelDSLoHang() {
        setBorder(new CompoundBorder(new TitledBorder("Danh Sách Nhập Hàng"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new BorderLayout());

        String[] tencot = { "Mã sản phẩm", "Tên loại", "Tên sản phẩm" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        loadSanPham(DsSP);
        scr = new JScrollPane(tb);

        add(scr, BorderLayout.CENTER);

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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchItem) {
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