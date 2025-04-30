package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import BLL.ChiTietNhapHangBLL;
import BLL.LoaiSanPhamBLL;
import BLL.SanPhamBLL;
import DTO.ChiTietPNHangDTO;
import DTO.SanPhamDTO;
import GUI.ComponentCommon.*;
import GUI.FormWareHouse.FormProductDetail;

public class PanelKhoTongHop extends JPanel implements ChangeListener, ActionListener {
    JPanel pn1, pn2, pn3;
    JScrollPane scr, scr2;
    JLabel tongdonhang;
    JButton btnMore;
    JTabbedPane tab;
    StyledTable tbSP, tbKho; // Thay JTable bằng StyledTable
    DefaultTableModel modelSP, modelKho;
    JPopupMenu popupMenu;
    JMenuItem searchItem, exportItem;
    public ArrayList<SanPhamDTO> DsSP = (ArrayList<SanPhamDTO>) SanPhamBLL.getAllProducts();
    public ArrayList<ChiTietPNHangDTO> DsKho = new ChiTietNhapHangBLL().getAllCTNhHang();

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
        tbSP = new StyledTable(data, tencot); // Khởi tạo StyledTable
        modelSP = (DefaultTableModel) tbSP.getModel();
        StyledTable.hoverTable(tbSP, modelSP);
        loadSanPham(DsSP);
        scr = new JScrollPane(tbSP);

        String[] tencott = { "Mã Lô Hàng", "Tên sản phẩm" , "Số lượng", "Tình trạng"};
        Object[][] dataa = new Object[0][tencott.length]; // Dữ liệu rỗng
        tbKho = new StyledTable(dataa, tencott); // Khởi tạo StyledTable
        modelKho = (DefaultTableModel) tbKho.getModel();
        StyledTable.hoverTable(tbKho, modelKho);
        loadChiTietPhieuNhap(DsKho);
        scr2 = new JScrollPane(tbKho);


        pn1 = new JPanel();
        pn1.setLayout(new BorderLayout());
        pn1.add(scr, BorderLayout.CENTER);

        pn2 = new JPanel();
        pn2.setLayout(new BorderLayout());

        pn3 = new JPanel();
        pn3.setLayout(new BorderLayout());

        tab = new JTabbedPane();
        tab.addTab("Danh sách sản phẩm bán chạy", pn1);
        tab.addTab("Danh sách hàng trong kho", pn2);
        //tab.addTab("Danh sách hàng sắp hết ", pn3);
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
        showpupop(tbSP);
        showpupop(scr);
        showpupop(tbKho);
        showpupop(scr2);
        tab.addChangeListener(this);
        btnMore.addActionListener(this);
        tbSP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Kiểm tra double click
                    FormProductDetail detailDialog = new FormProductDetail(null,
                            SanPhamBLL.getProductById((Integer) tbSP.getValueAt(tbSP.getSelectedRow(), 0)));
                    detailDialog.setVisible(true);
                }
            }
        });
    }

    public void showpupop(Object obj) {
        if (obj instanceof JTable tbSP) {
            tbSP.addMouseListener(new MouseAdapter() {
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
        modelSP.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (SanPhamDTO sp : DsSP) {
            modelSP.addRow(new Object[] {
                    sp.getMaSP(),
                    new LoaiSanPhamBLL().getLoaiSanPham(sp.getMaLSP()).getTenLoaiSP(),
                    sp.getTenSP()
            });
        }
    }

    private void loadChiTietPhieuNhap(ArrayList<ChiTietPNHangDTO> DsKho){
        modelKho.setRowCount(0);
        for (ChiTietPNHangDTO ct : DsKho){
            modelKho.addRow(new Object[]{
                ct.getMaLH(),
                SanPhamBLL.getProductById(ct.getMaSP()).getTenSP(),
                ct.getSoLuong(),
                ct.check(ct.getNgaySX(), ct.getNgayHH())
            });
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int tabSelected = tab.getSelectedIndex();
        if (tabSelected == 0) {
            pn1.add(scr, BorderLayout.CENTER);
        } else if (tabSelected == 1) {
            pn2.add(scr2, BorderLayout.CENTER);
        } else if (tabSelected == 2) {
            pn3.add(scr, BorderLayout.CENTER);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == btnMore) {
            PanelDSLoHang panel = new PanelDSLoHang();
            JOptionPane.showMessageDialog(null, panel, "Xem Danh Sách", JOptionPane.PLAIN_MESSAGE);

        } else if (e.getSource() == searchItem) {
            PanelTimKho panel = new PanelTimKho();

            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == 0) {
                System.out.println("Bạn vừa nhập: ");
            }
        } else if (e.getSource() == exportItem) {
            PanelExport panel = new PanelExport();

            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                if (panel.getSelectedFormat().equals("excel")) {
                    panel.XuatExccel(modelSP);
                } else {
                    panel.XuatPDF(modelSP);
                }
            } else if (result == JOptionPane.CANCEL_OPTION) {
                TienIch.CustomMessage("Đã hủy xuất file");
            } else {
                TienIch.CustomMessage("Đã hủy xuất file");
            }
        }
        TienIch.resetUI();
    }
}