package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import BLL.NhaCungCapBLL;
import BLL.NhanVienBLL;
import BLL.NhapHangBLL;
import DTO.PhieuNhapHangDTO;
import DTO.SearchLoHangDTO;
import GUI.ComponentCommon.StyledTable;
import GUI.ComponentCommon.TienIch;

public class PanelDSLoHang extends JPanel implements ActionListener {
    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<PhieuNhapHangDTO> DsNHang = new NhapHangBLL().getAllPhieuNhapHang();
    JPopupMenu popupMenu;
    JMenuItem searchItem, exportItem;

    public PanelDSLoHang(String MANV) {
        this.MANV=MANV;
        TienIch.taoTitleBorder(this, "Danh sách nhập hàng");
        setLayout(new BorderLayout());

        String[] tencot = { "Mã đơn nhập hàng", "Tên đơn", "Ngày nhập", "Nhân viên", "Nhà Cung Cấp" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        loadNhapHang(DsNHang);
        StyledTable.hoverTable(tb, model);
        scr = new JScrollPane(tb);
        //scr.setPreferredSize(new Dimension(800, 300));
        TienIch.setPreferredSizeTuDong(scr, tb);

        add(scr, BorderLayout.CENTER);

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

        tb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    PanelXemChiTietPhieuNhap panel = new PanelXemChiTietPhieuNhap((Integer)tb.getValueAt(tb.getSelectedRow(), 0));
                            JOptionPane.showMessageDialog(null, panel, "Xem Chi Tiết Phiếu Nhập", JOptionPane.PLAIN_MESSAGE);
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

    private void loadNhapHang(ArrayList<PhieuNhapHangDTO> DsNHang) {
        model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (PhieuNhapHangDTO nh : DsNHang) {
            model.addRow(new Object[] {
                    nh.getMaPNH(),
                    nh.getTenPNH(),
                    nh.getNgayNhap(),
                    new NhanVienBLL().getNhanVienByMa(nh.getMaNV() + "").getTenNV(),
                    new NhaCungCapBLL().getNhaCungCap(nh.getMaNCC()).getTenNCC()
            });
        }
    }
    public String MANV;
    SearchLoHangDTO SEARCH = new SearchLoHangDTO();
    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == searchItem) {
            PanelTimLoHang panel = new PanelTimLoHang();
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == 0) {
                DsNHang = panel.ketqua();
                if(DsNHang.size()!=0){
                    SEARCH = panel.trasearch();
                } else {
                    TienIch.CustomMessage("Không tìm thấy");
                }
                loadNhapHang(DsNHang);
            }
        } else if (e.getSource() == exportItem) {
            PanelExport panel = new PanelExport();
            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (panel.getSelectedFormat().equals("excel")) {
                    panel.XuatExccel(model);
                } else {
                    if(DsNHang.size()==0){
                        TienIch.CustomMessage("Không có gì để in");
                    } else{
                        PanelExport.InPDFLoHangSearch(DsNHang, SEARCH, MANV);
                    }
                    panel.XuatPDF(model);
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