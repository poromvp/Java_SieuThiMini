package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import BLL.TheThanhVienBLL;
import DTO.SearchTheThanhVienDTO;
import DTO.TheThanhVienDTO;
import GUI.Admin_TheThanhVien.PanelXemDSLock;
import GUI.ComponentCommon.*;

public class PanelBaoCaoKH extends JPanel implements ActionListener {
    JButton btnTim;
    JLabel lbMota;
    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel model;
    JScrollPane scr;
    private ArrayList<TheThanhVienDTO> TTV;
    JPanel pn1;
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
    public String MANV;
    public PanelBaoCaoKH(String MANV) {
        this.MANV=MANV;
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
        lbMota = new JLabel("Danh sách Thẻ Thành Viên Hết Hạn");
        lbMota.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TienIch.setDarkUI();
                PanelXemDSLock panel = new PanelXemDSLock(MANV);
                int result = JOptionPane.showConfirmDialog(null, panel, "Danh sách TTV đã khóa",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    TienIch.CustomMessage("Đã hủy xem danh sách thẻ thành viên bị khóa");
                }
                TienIch.resetUI();
            }
        });
        TienIch.labelStyle(lbMota, 2, 15, null);
        add(lbMota, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;

        String[] tencot = { "Mã thành viên", "Họ tên", "Số điện thoại", "Điểm tích lũy" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        TTV = TheThanhVienBLL.getAllMembersACTIVE();
        loadThanhVien(TTV);
        StyledTable.hoverTable(tb, model);
        StyledTable.TableEvent(tb, model, "KH", MANV); // Giữ sự kiện double-click
        scr = new JScrollPane(tb);

        pn1 = new JPanel();
        pn1.setLayout(new BorderLayout());
        pn1.add(scr, BorderLayout.CENTER);
        add(pn1, gbc);

        btnTim.addActionListener(this);

        // Thêm popup menu
        popupMenu = new JPopupMenu();
        exportItem = new JMenuItem("Xuất file");
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
    public SearchTheThanhVienDTO SEARCH = new SearchTheThanhVienDTO();
    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == btnTim) {
            PanelTimKH panel = new PanelTimKH();
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == 0) {
                TTV = panel.ketqua();
                if(TTV.size()!=0){
                    SEARCH = panel.traSearch();
                    loadThanhVien(TTV);
                } else{
                    TienIch.CustomMessage("Không tìm thấy");
                    loadThanhVien(TTV);
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
                    if(TTV.size()!=0){
                        PanelExport.InPDFTheThanhVienTheoSearch(TTV, SEARCH, MANV, "");
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