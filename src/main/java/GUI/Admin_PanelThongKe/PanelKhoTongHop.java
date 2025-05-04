package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BLL.BaoCaoKhoTongHopBLL;
import BLL.LoaiSanPhamBLL;
import BLL.NhaCungCapBLL;
import BLL.SanPhamBLL;
import DTO.SanPhamDTO;
import DTO.SearchBanChayDTO;
import DTO.SearchTonKhoDTO;
import GUI.ComponentCommon.*;
import GUI.FormWareHouse.FormProductDetail;

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
    public ArrayList<SearchBanChayDTO> DsSP;

    JScrollPane scr2;
    StyledTable tb2;
    DefaultTableModel model2;
    public ArrayList<SanPhamDTO> DsSP2 = (ArrayList<SanPhamDTO>) SanPhamBLL.getAllProducts();

    JDateChooser from, to;
    JPanel pnTool = new JPanel();

    public void initPanelTool() {
        pnTool.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        // Ngày hôm nay
        Date today = new Date(System.currentTimeMillis());

        // Ngày đầu tiên của tháng
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = new Date(cal.getTimeInMillis());

        from = new JDateChooser();
        from.setMaxSelectableDate(today);
        from.setDateFormatString("dd/MM/yyyy");
        TienIch.checkngaynhaptutayy(from, today);
        TienIch.timStyle(from);
        from.setDate(firstDayOfMonth);

        to = new JDateChooser();
        to.setMaxSelectableDate(today);
        to.setDateFormatString("dd/MM/yyyy");
        TienIch.checkngaynhaptutayy(to, today);
        TienIch.timStyle(to);
        to.setDate(today);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel tu = new JLabel("Từ");
        tu.setHorizontalAlignment(SwingConstants.RIGHT);
        pnTool.add(tu, gbc);

        gbc.gridx = 1;
        pnTool.add(from);

        gbc.gridx = 2;
        JLabel toi = new JLabel("Tới");
        toi.setHorizontalAlignment(SwingConstants.RIGHT);
        pnTool.add(toi, gbc);

        gbc.gridx = 3;
        pnTool.add(to);
        sukien();
    }

    public void sukien() {
        from.addPropertyChangeListener("date", _ -> {
            if (from.getDate() != null && to.getDate() != null) {
                Date select1 = new java.sql.Date(from.getDate().getTime());
                Date select2 = new java.sql.Date(to.getDate().getTime());
                if (select1.after(select2)) {
                    TienIch.CustomMessageNormal("Ngày bắt đầu không thể lớn hơn ngày kết thúc");
                    return;
                }
                System.out.println("Ngày được chọn (SQL) từ: " + select1);
                System.out.println("Ngày được chọn (SQL) tới: " + select2);
                DsSP = BaoCaoKhoTongHopBLL.getAllSPBanChay(select1, select2);
                loadSanPham(DsSP);
            } else {
                TienIch.CustomMessage("Không thể để trống");
                from.setDate(new Date(System.currentTimeMillis()));
            }
        });

        to.addPropertyChangeListener("date", _ -> {
            if (from.getDate() != null && to.getDate() != null) {
                Date select1 = new java.sql.Date(from.getDate().getTime());
                Date select2 = new java.sql.Date(to.getDate().getTime());
                if (select1.after(select2)) {
                    TienIch.CustomMessageNormal("Ngày bắt đầu không thể lớn hơn ngày kết thúc");
                    return;
                }
                System.out.println("Ngày được chọn (SQL) từ: " + select1);
                System.out.println("Ngày được chọn (SQL) tới: " + select2);
                DsSP = BaoCaoKhoTongHopBLL.getAllSPBanChay(select1, select2);
                loadSanPham(DsSP);
            } else {
                TienIch.CustomMessage("Không thể để trống");
                to.setDate(new Date(System.currentTimeMillis()));
            }
        });
    }
    public String MANV;
    public PanelKhoTongHop(String MANV) {
        this.MANV = MANV;
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
        tongdonhang = new JLabel(BaoCaoKhoTongHopBLL.tongSoHangNhapTrongThang());
        TienIch.labelStyle(tongdonhang, 2, 20, null);
        add(tongdonhang, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        btnMore = new JButton("<html><center>Xem DS lô hàng<center></html>");
        TienIch.nutStyle(btnMore, "list.png", 20, 40, 20);
        add(btnMore, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        String[] tencot = { "Mã sản phẩm", "Tên loại", "Tên sản phẩm", "Số lượng bán ra" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        StyledTable.hoverTable(tb, model);
        initPanelTool();
        DsSP = BaoCaoKhoTongHopBLL.getAllSPBanChay(new java.sql.Date(from.getDate().getTime()),
                new java.sql.Date(to.getDate().getTime()));
        loadSanPham(DsSP);
        scr = new JScrollPane(tb);

        pn1 = new JPanel();
        pn1.setLayout(new BorderLayout());
        pn1.add(scr, BorderLayout.CENTER);
        pn1.add(pnTool, BorderLayout.NORTH);

        pn2 = new JPanel();
        pn2.setLayout(new BorderLayout());

        String[] tencot2 = { "Mã sản phẩm", "Tên sản phẩm", "Giá", "Số lượng tồn", "Loại", "Nhà cung cấp" };
        Object[][] data2 = new Object[0][tencot2.length]; // Dữ liệu rỗng
        tb2 = new StyledTable(data2, tencot2); // Khởi tạo StyledTable
        model2 = (DefaultTableModel) tb2.getModel();
        StyledTable.hoverTable(tb2, model2);
        loadSanPham2(DsSP2);
        scr2 = new JScrollPane(tb2);

        pn3 = new JPanel();
        pn3.setLayout(new BorderLayout());

        tab = new JTabbedPane();
        tab.addTab("Danh sách bán chạy", pn1);
        tab.addTab("Danh sách tồn kho", pn2);
        // tab.addTab("Danh sách hàng sắp hết", pn3);
        add(tab, gbc);

        // Thêm popup menu
        popupMenu = new JPopupMenu();
        searchItem = new JMenuItem("Tìm Kiếm");
        exportItem = new JMenuItem("Xuất file");
        searchItem.addActionListener(this);
        exportItem.addActionListener(this);
        popupMenu.add(searchItem);
        popupMenu.add(exportItem);

        // Thêm sự kiện chuột phải cho bảng
        showpupop(tb);
        showpupop(scr);
        tab.addChangeListener(this);
        btnMore.addActionListener(this);
        tb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TienIch.setDarkUI();
                if (e.getClickCount() == 2) { // Kiểm tra double click
                    PanelXemDsHDBanChay panel = new PanelXemDsHDBanChay(DsSP.get(tb.getSelectedRow()), MANV);
                    JOptionPane.showMessageDialog(null, panel, "Xem Chi Tiết", JOptionPane.PLAIN_MESSAGE);
                }
                TienIch.resetUI();
            }
        });

        showpupop(tb2);
        showpupop(scr2);
        tb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Kiểm tra double click
                    FormProductDetail detailDialog = new FormProductDetail(null,
                            SanPhamBLL.getProductById((Integer) tb2.getValueAt(tb2.getSelectedRow(), 0)));
                    detailDialog.setVisible(true);
                }
            }
        });
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

    private void loadSanPham(ArrayList<SearchBanChayDTO> DsSP) {
        model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (SearchBanChayDTO sp : DsSP) {
            model.addRow(new Object[] {
                    sp.getMaSP(),
                    sp.getTenLSP(),
                    sp.getTenSP(),
                    sp.getSLbanra()
            });
        }
    }

    private void loadSanPham2(ArrayList<SanPhamDTO> DsSP2) {
        model2.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (SanPhamDTO sp : DsSP2) {
            model2.addRow(new Object[] {
                    sp.getMaSP(),
                    sp.getTenSP(),
                    sp.getGia(),
                    sp.getSoLuongTon(),
                    new LoaiSanPhamBLL().getLoaiSanPham(sp.getMaLSP()).getTenLoaiSP(),
                    new NhaCungCapBLL().getNhaCungCap(sp.getMaNCC()).getTenNCC()
            });
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int tabSelected = tab.getSelectedIndex();
        if (tabSelected == 0) {
            pn1.add(scr, BorderLayout.CENTER);
            pn1.add(pnTool, BorderLayout.NORTH);
        } else if (tabSelected == 1) {
            pn2.add(scr2, BorderLayout.CENTER);
        } else if (tabSelected == 2) {
            pn3.add(scr, BorderLayout.CENTER);
        }
    }
    public SearchBanChayDTO SEARCH = new SearchBanChayDTO();
    public SearchTonKhoDTO SEARCH2 = new SearchTonKhoDTO();
    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == btnMore) {
            PanelDSLoHang panel = new PanelDSLoHang(MANV);
            JOptionPane.showMessageDialog(null, panel, "Xem Danh Sách", JOptionPane.PLAIN_MESSAGE);

        } else if (e.getSource() == searchItem) {
            JPanel panel;
            if (tab.getSelectedIndex() == 0) {
                panel = new PanelTimBanChay();
            } else {
                panel = new PanelTimKho();
            }

            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == 0) {
                // new java.sql.Date(from.getDate().getTime());
                if (panel instanceof PanelTimBanChay pnbanchay) {
                    DsSP = pnbanchay.ketqua(new java.sql.Date(from.getDate().getTime()),
                            new java.sql.Date(to.getDate().getTime()));
                    if(DsSP.size()!=0){
                        SEARCH = ((PanelTimBanChay)panel).trasearch();
                    } else {
                        TienIch.CustomMessage("Không tìm thấy");
                    }
                    loadSanPham(DsSP);
                } else if(panel instanceof PanelTimKho pntimkho){
                    DsSP2 = pntimkho.ketqua();
                    if(DsSP2.size()!=0){
                        SEARCH2 = ((PanelTimKho)panel).trasearch();
                    } else {
                        TienIch.CustomMessage("Không tìm thấy");
                    }
                    loadSanPham2(DsSP2);
                }
            }
        } else if (e.getSource() == exportItem) {
            PanelExport panel = new PanelExport();

            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                if (panel.getSelectedFormat().equals("excel")) {
                    panel.XuatExccel(model);
                } else {
                    if(tab.getSelectedIndex()==0){
                        if(DsSP.size()==0){
                            TienIch.CustomMessage("Không có gì để in");
                        } else{
                            PanelExport.InPDFSanPhamBanChaySearch(DsSP, SEARCH, new java.sql.Date(from.getDate().getTime()),
                            new java.sql.Date(to.getDate().getTime()), MANV);
                        }
                    } else{
                        if(DsSP2.size()==0){
                            TienIch.CustomMessage("Không có gì để in");
                        } else {
                            PanelExport.InPDFSanPhamTonKhoSearch(DsSP2, SEARCH2, MANV);
                        }
                    }
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