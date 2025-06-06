package GUI.Admin_PanelThongKe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.DonHangBLL;
import BLL.KhuyenMaiBLL;
import BLL.NhanVienBLL;
import BLL.TheThanhVienBLL;
import DTO.DonHangDTO;
import DTO.NhanVienDTO;
import DTO.SearchNVDHDTO;
import GUI.ComponentCommon.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PanelXemNV extends JPanel implements ActionListener {
    JPanel pn1, pn2, pn3;
    JPopupMenu popupMenu;
    JMenuItem searchItem, exportItem;
    NhanVienDTO nv;
    public String MANV;

    public PanelXemNV(DefaultTableModel model, int dong, String MANV) {
        this.MANV = MANV;
        TienIch.setDarkUI();
        nv = new NhanVienBLL().getNhanVienByMa(model.getValueAt(dong, 0).toString());
        dsHoaDon = DonHangBLL.getOrderByNV(nv.getMaNV());
        setBackground(new Color(226, 224, 221));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        pn1 = new JPanel();
        pn2 = new JPanel();
        pn3 = new JPanel();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        initPanel1(model, dong);
        add(pn1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        initPanel2();
        add(pn2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        initPanel3();
        add(pn3, gbc);

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
    }

    public String LayChuoiTuBang(DefaultTableModel model, int dong, int cot) {
        Object txt = model.getValueAt(dong, cot);
        return txt.toString();
    }

    JTextArea GioiThieu;
    JLabel lbTenNV, lbNgSInh, lbDchi, lbSDT, lbChucVu, lbID;

    public void initPanel1(DefaultTableModel model, int dong) {
        TienIch.taoTitleBorder(pn1, "Thông tin chi tiết");
        pn1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = 10;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        JLabel avt = new JLabel();
        TienIch.anhAVT(avt, nv.getImage(), 200, 250, "NV");
        pn1.add(avt, gbc);
        gbc.gridheight = 1;

        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel ten = new JLabel("Tên NV: ");
        TienIch.labelStyle(ten, 4, 15, null);
        pn1.add(ten, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        lbTenNV = new JLabel(nv.getTenNV());
        TienIch.labelStyle(lbTenNV, 2, 15, null);
        pn1.add(lbTenNV, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel namsinh = new JLabel("Năm sinh: ");
        TienIch.labelStyle(namsinh, 4, 15, null);
        pn1.add(namsinh, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        lbNgSInh = new JLabel(TienIch.ddmmyyyy(nv.getNgaySinh()));
        TienIch.labelStyle(lbNgSInh, 2, 15, null);
        pn1.add(lbNgSInh, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JLabel dchi = new JLabel("Địa chỉ: ");
        TienIch.labelStyle(dchi, 4, 15, null);
        pn1.add(dchi, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        lbDchi = new JLabel(nv.getDiaChi());
        TienIch.labelStyle(lbDchi, 2, 15, null);
        pn1.add(lbDchi, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JLabel sdt = new JLabel("SĐT: ");
        TienIch.labelStyle(sdt, 4, 15, null);
        pn1.add(sdt, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        lbSDT = new JLabel(nv.getSDT());
        TienIch.labelStyle(lbSDT, 2, 15, null);
        pn1.add(lbSDT, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        JLabel id = new JLabel("ID:");
        TienIch.labelStyle(id, 4, 15, null);
        pn1.add(id, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        lbID = new JLabel(nv.getMaNV() + "");
        TienIch.labelStyle(lbID, 2, 15, null);
        pn1.add(lbID, gbc);
    }

    JLabel lbTongDonHang, lbDoanhSo, lbLuong;

    public void initPanel2() {
        TienIch.taoTitleBorder(pn2, "Tóm tắt");
        pn2.setLayout(new GridLayout(3, 2));

        JLabel tongdh = new JLabel("<html>Tổng đơn hàng <br>đã thực hiện: </html>");
        TienIch.labelStyle(tongdh, 4, 15, null);
        pn2.add(tongdh);
        lbTongDonHang = new JLabel(dsHoaDon.size() + "");
        TienIch.labelStyle(lbTongDonHang, 2, 15, null);
        pn2.add(lbTongDonHang);

        JLabel doanhs = new JLabel("<html>Doanh số <br>bán hàng: </html>");
        TienIch.labelStyle(doanhs, 4, 15, null);
        pn2.add(doanhs);
        double sum = 0;
        for (DonHangDTO hd : dsHoaDon) {
            sum += hd.getTongTien();
        }
        lbDoanhSo = new JLabel(TienIch.formatVND(sum));
        TienIch.labelStyle(lbDoanhSo, 2, 15, null);
        pn2.add(lbDoanhSo);

        JLabel luong = new JLabel("Lương: ");
        TienIch.labelStyle(luong, 4, 15, null);
        pn2.add(luong);
        lbLuong = new JLabel(TienIch.formatVND(nv.getLuong()));
        TienIch.labelStyle(lbLuong, 2, 15, null);
        pn2.add(lbLuong);
    }

    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel modelMini;
    JScrollPane scr;
    public ArrayList<DonHangDTO> dsHoaDon;

    public void initPanel3() {
        TienIch.taoTitleBorder(pn3, "Danh sách các đơn hàng đã thanh toán");
        pn3.setLayout(new BorderLayout());
        String[] tencot = { "Mã đơn hàng", "Mã khách hàng", "PTTT", "Thành tiền", "Ngày" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        modelMini = (DefaultTableModel) tb.getModel();
        loadDonHang(dsHoaDon);
        StyledTable.hoverTable(tb, modelMini);
        StyledTable.TableEvent(tb, modelMini, "HD", MANV); // Giữ sự kiện double-click
        scr = new JScrollPane(tb);
        scr.setPreferredSize(new Dimension(600, 120)); // Giữ kích thước
        pn3.add(scr, BorderLayout.CENTER);
    }

    private void loadDonHang(ArrayList<DonHangDTO> danhsach) {
        modelMini.setRowCount(0);
        for (DonHangDTO hd : danhsach) {
            if (hd.getMaKH() != 0) {
                modelMini.addRow(new Object[] {
                        hd.getMaDH(),
                        hd.getMaKH(),
                        hd.getPtThanhToan(),
                        TienIch.formatVND(hd.getTongTien()),
                        TienIch.ddmmyyyy(hd.getNgayTT()) });
            } else {
                modelMini.addRow(new Object[] {
                        hd.getMaDH(),
                        "Không có",
                        hd.getPtThanhToan(),
                        TienIch.formatVND(hd.getTongTien()),
                        TienIch.ddmmyyyy(hd.getNgayTT()) });
            }
        }
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

    public SearchNVDHDTO SEARCH = new SearchNVDHDTO();
    public ArrayList<String> SEARCH2 = new ArrayList<>();
    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == searchItem) {
            PanelTimNVHD panel = new PanelTimNVHD();
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == 0) {
                dsHoaDon = panel.ketqua(nv.getMaNV());
                SEARCH = panel.trasearch();
                SEARCH2 = panel.stringsearch();
                if(dsHoaDon.size() ==0){
                    TienIch.CustomMessage("Không tìm thấy");
                }
                lbTongDonHang.setText(dsHoaDon.size() + "");
                double sum = 0;
                for (DonHangDTO hd : dsHoaDon) {
                    sum += hd.getTongTien();
                }
                lbDoanhSo.setText(TienIch.formatVND(sum) + "");
                loadDonHang(dsHoaDon);
            }
        } else if (e.getSource() == exportItem) {
            PanelExport panel = new PanelExport();
            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (panel.getSelectedFormat().equals("excel")) {
                    if(dsHoaDon.size() == 0){
                        TienIch.CustomMessage("Không có gì để xuất");
                    } else {
                        try {
                            // Chuẩn bị dữ liệu cho exportToExcel
                            ArrayList<List<Object>> data = new ArrayList<>();
                            for (DonHangDTO dh : dsHoaDon) {
                                List<Object> row = new ArrayList<>();
                                row.add(dh.getMaDH());
                                row.add(dh.getMaKM()!=0?KhuyenMaiBLL.getDiscountById(dh.getMaKM()).getTenKM():"Không có");
                                row.add(dh.getMaKH()!=0?TheThanhVienBLL.getMemberById(dh.getMaKH()).getTenTV():"Không có");
                                row.add(dh.getPtThanhToan());
                                row.add(dh.getNgayTT());
                                row.add(dh.getMaDTL()!=0?dh.getMaDTL():"Không có");
                                row.add(TienIch.formatVND(dh.getTienKD()));
                                row.add(TienIch.formatVND(dh.getTongTien()));
                                data.add(row);
                            }
                            String[] columnNames = { "Mã đơn hàng", "Khuyến mãi", "Khách hàng", "Phương thức thanh toán", "Ngày thanh toán", "Mã điểm tích lũy", "Tiền khách đưa", "Thành tiền"};
                            String title = "DANH SÁCH CÁC ĐƠN HÀNG CỦA NHÂN VIÊN";
                            String manv = this.MANV;
                            // Gọi hàm exportToExcel
                            XuatFileExccel.exportToExcel(data, columnNames, title, manv, SEARCH2);
                        } catch (Exception ex) {
                            TienIch.CustomMessage("Lỗi khi xuất file Excel: " + ex.getMessage());
                        }
                    }
                } else {
                    if(dsHoaDon.size()!=0){
                        PanelExport.InPDFDonHangCuaNVTheoSearch(dsHoaDon, SEARCH, nv, MANV);
                    } else {
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