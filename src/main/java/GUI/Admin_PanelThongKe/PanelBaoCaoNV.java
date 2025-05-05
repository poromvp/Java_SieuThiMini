package GUI.Admin_PanelThongKe;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import DTO.SearchNhanVienDTO;
import GUI.ComponentCommon.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PanelBaoCaoNV extends JPanel implements ActionListener {
    JButton btnTim, btnDS;
    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<NhanVienDTO> DsNV = (ArrayList<NhanVienDTO>) new NhanVienBLL().getAllNhanVien();
    JPanel pn1, pn2;
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

    public void initPanel1() {
        pn1.setBorder(new CompoundBorder(new TitledBorder("Chức năng"), new EmptyBorder(4, 4, 4, 4)));
        pn1.setLayout(new GridLayout(1, 2, 2, 5));

        btnTim = new JButton("Tìm");
        TienIch.nutStyle(btnTim, "search.png", 20, 50, 25);
        pn1.add(btnTim);

        btnDS = new JButton("Danh sách nhân viên có doanh số tốt nhất");
        TienIch.nutStyle(btnDS, "list.png", 20, 50, 25);
        pn1.add(btnDS);

        btnTim.addActionListener(this);
        btnDS.addActionListener(this);
    }

    public void initPanel2() {
        pn2.setBorder(new CompoundBorder(new TitledBorder("Danh sách"), new EmptyBorder(4, 4, 4, 4)));
        pn2.setLayout(new BorderLayout());
        String[] tencot = { "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        StyledTable.hoverTable(tb, model);
        loadNhanVien(DsNV);
        StyledTable.TableEvent(tb, model, "NV", MANV); // Giữ sự kiện double-click
        scr = new JScrollPane(tb);
        pn2.add(scr, BorderLayout.CENTER);

        // Thêm sự kiện chuột phải cho bảng
        showpupop(tb);
        showpupop(scr);
    }
    public String MANV;
    public PanelBaoCaoNV(String MANV) {
        this.MANV = MANV;
        setBorder(new CompoundBorder(new TitledBorder("Báo cáo nhân viên"), new EmptyBorder(4, 4, 4, 4)));
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

        gbc.gridx = 0;
        gbc.gridy = 1;
        pn2 = new JPanel();
        initPanel2();
        add(pn2, gbc);

        // Thêm popup menu
        popupMenu = new JPopupMenu();
        exportItem = new JMenuItem("Xuất file");
        exportItem.addActionListener(this);
        popupMenu.add(exportItem);
    }

    private void loadNhanVien(ArrayList<NhanVienDTO> dsnv) {
        model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (NhanVienDTO nv : dsnv) {
            model.addRow(new Object[] {
                    nv.getMaNV(),
                    nv.getTenNV(),
                    TienIch.ddmmyyyy(nv.getNgaySinh()),
                    nv.getSDT()
            });
        }
    }
    public SearchNhanVienDTO SEARCH = new SearchNhanVienDTO();
    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == btnTim) {
            PanelTimVN panel = new PanelTimVN();
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == 0) {
                DsNV = panel.ketqua();
                if(DsNV.size()!=0){
                    SEARCH = panel.trasearch();
                    loadNhanVien(DsNV);
                } else{
                    loadNhanVien(DsNV);
                    TienIch.CustomMessage("Không tìm thấy");
                }
            }
        } else if (e.getSource() == btnDS) {
            PanelTotNhat panel = new PanelTotNhat(MANV);
            JOptionPane.showMessageDialog(null, panel, "Xem Danh Sách", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == exportItem) {
            PanelExport panel = new PanelExport();
            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (panel.getSelectedFormat().equals("excel")) {
                    panel.XuatExccel(model);
                } else {
                    if(DsNV.size()!=0){
                        PanelExport.InPDFNhanVienTheoSearch(DsNV, SEARCH, MANV);
                        panel.XuatPDF(model);
                    } else{
                        TienIch.CustomMessage("Không có gì để in");
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