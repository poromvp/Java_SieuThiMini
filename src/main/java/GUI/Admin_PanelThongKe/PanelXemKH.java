package GUI.Admin_PanelThongKe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.DonHangBLL;
import BLL.KhuyenMaiBLL;
import BLL.NhanVienBLL;
import BLL.TheThanhVienBLL;
import DTO.DonHangDTO;
import DTO.SearchKHDHDTO;
import DTO.TheThanhVienDTO;
import GUI.ComponentCommon.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class PanelXemKH extends JPanel implements ActionListener {
    JPanel pn1, pn2, pn3;
    TheThanhVienDTO kh;
    JPopupMenu popupMenu;
    JMenuItem searchItem, exportItem;
    public String MANV;

    public PanelXemKH(DefaultTableModel model, int dong, String MANV) {
        this.MANV = MANV;
        TienIch.setDarkUI();
        kh = TheThanhVienBLL.getMemberById(Integer.parseInt(model.getValueAt(dong, 0).toString()));
        HoaDon = DonHangBLL.getOrderByKH(Integer.parseInt(model.getValueAt(dong, 0).toString()));
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

    JLabel lbTenKH, lbNgSInh, lbDchi, lbSDT, lbDiemTL, lbID, lbStart, lbEnd;

    public void initPanel1(DefaultTableModel model, int dong) {
        TienIch.taoTitleBorder(pn1, "Thông tin chi tiết");
        pn1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 6;
        gbc.ipadx = 10;
        JLabel avt = new JLabel();
        TienIch.anhAVT(avt, kh.getTenAnh(), 150, 250, "KH");
        pn1.add(avt, gbc);
        gbc.gridheight = 1;

        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel ten = new JLabel("Tên: ");
        TienIch.labelStyle(ten, 4, 15, null);
        pn1.add(ten, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        lbTenKH = new JLabel(kh.getTenTV());
        TienIch.labelStyle(lbTenKH, 2, 15, null);
        pn1.add(lbTenKH, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel namsinh = new JLabel("Năm sinh: ");
        TienIch.labelStyle(namsinh, 4, 15, null);
        pn1.add(namsinh, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        lbNgSInh = new JLabel(TienIch.ddmmyyyy(kh.getNgaySinh()));
        TienIch.labelStyle(lbNgSInh, 2, 15, null);
        pn1.add(lbNgSInh, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JLabel dchi = new JLabel("Địa chỉ: ");
        TienIch.labelStyle(dchi, 4, 15, null);
        pn1.add(dchi, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        lbDchi = new JLabel(kh.getDiaChi());
        TienIch.labelStyle(lbDchi, 2, 15, null);
        pn1.add(lbDchi, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JLabel sdt = new JLabel("SĐT: ");
        TienIch.labelStyle(sdt, 4, 15, null);
        pn1.add(sdt, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        lbSDT = new JLabel(kh.getSdt());
        TienIch.labelStyle(lbSDT, 2, 15, null);
        pn1.add(lbSDT, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        JLabel diem = new JLabel("Điểm: ");
        TienIch.labelStyle(diem, 4, 15, null);
        pn1.add(diem, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        lbDiemTL = new JLabel(kh.getDiemTL() + "");
        TienIch.labelStyle(lbDiemTL, 2, 15, null);
        pn1.add(lbDiemTL, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        JLabel id = new JLabel("ID:");
        TienIch.labelStyle(id, 4, 15, null);
        pn1.add(id, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        lbID = new JLabel(kh.getMaTV() + "");
        TienIch.labelStyle(lbID, 2, 15, null);
        pn1.add(lbID, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        JLabel from = new JLabel("Ngày bắt đầu:");
        TienIch.labelStyle(from, 4, 15, null);
        pn1.add(from, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        JLabel to = new JLabel("Ngày kết thúc:");
        TienIch.labelStyle(to, 4, 15, null);
        pn1.add(to, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        lbStart = new JLabel(TienIch.ddmmyyyy(kh.getNgayBD()));
        TienIch.labelStyle(lbStart, 2, 15, null);
        pn1.add(lbStart, gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        lbEnd = new JLabel(TienIch.ddmmyyyy(kh.getNgayKT()));
        TienIch.labelStyle(lbEnd, 2, 15, null);
        pn1.add(lbEnd, gbc);
    }

    JLabel lbTongDonHang, lbChiTieu;

    public void initPanel2() {
        TienIch.taoTitleBorder(pn2, "Tóm tắt");
        pn2.setLayout(new GridLayout(2, 2));

        JLabel tongdh = new JLabel("<html><center>Tổng đơn hàng <br>đã mua: </center></html>");
        TienIch.labelStyle(tongdh, 4, 15, null);
        pn2.add(tongdh);
        lbTongDonHang = new JLabel(HoaDon.size() + "");
        TienIch.labelStyle(lbTongDonHang, 2, 15, null);
        pn2.add(lbTongDonHang);

        JLabel tongchitieu = new JLabel("Tổng chi tiêu: ");
        TienIch.labelStyle(tongchitieu, 4, 15, null);
        pn2.add(tongchitieu);

        double sum = 0;
        for (DonHangDTO hd : HoaDon) {
            sum += hd.getTongTien();
        }
        lbChiTieu = new JLabel(TienIch.formatVND(sum));
        TienIch.labelStyle(lbChiTieu, 2, 15, null);
        pn2.add(lbChiTieu);
    }

    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel modelMini;
    JScrollPane scr;
    public ArrayList<DonHangDTO> HoaDon;

    public void initPanel3() {
        TienIch.taoTitleBorder(pn3, "Danh sách các hóa đơn đã mua");
        pn3.setLayout(new BorderLayout());
        String[] tencot = { "Mã đơn hàng", "Mã nhân viên", "PTTT", "Thành tiền", "Ngày" };
        /*
         * for (DonHangDTO hd : HoaDon) {
         * System.out.println(hd.getMaDH() + " " + hd.getMaNV() + " " +
         * hd.getPtThanhToan() + " " + hd.getNgayTT());
         * }
         */
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        modelMini = (DefaultTableModel) tb.getModel();
        loadDonHang(HoaDon);
        StyledTable.hoverTable(tb, modelMini);
        StyledTable.TableEvent(tb, modelMini, "HD", MANV); // Giữ sự kiện double-click
        scr = new JScrollPane(tb);
        scr.setPreferredSize(new Dimension(600, 120)); // Giữ kích thước
        pn3.add(scr, BorderLayout.CENTER);
    }

    private void loadDonHang(ArrayList<DonHangDTO> danhsach) {
        modelMini.setRowCount(0);
        for (DonHangDTO hd : danhsach) {
            modelMini.addRow(new Object[] {
                    hd.getMaDH(),
                    hd.getMaNV(),
                    hd.getPtThanhToan(),
                    TienIch.formatVND(hd.getTongTien()),
                    TienIch.ddmmyyyy(hd.getNgayTT()) });
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

    public SearchKHDHDTO SEARCH = new SearchKHDHDTO();
    public ArrayList<String> SEARCH2 = new ArrayList<>();
    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == searchItem) {
            PanelTimKHHD panel = new PanelTimKHHD();
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == 0) {
                HoaDon = panel.ketqua(kh.getMaTV());
                if (HoaDon.size() == 0) {
                    TienIch.CustomMessage("Không tìm thấy");
                }
                SEARCH = panel.traSearch();
                SEARCH2 = panel.stringsearch();
                lbTongDonHang.setText(HoaDon.size() + "");
                double sum = 0;
                for (DonHangDTO hd : HoaDon) {
                    sum += hd.getTongTien();
                }
                lbChiTieu.setText(TienIch.formatVND(sum) + "");
                loadDonHang(HoaDon);
            }
        } else if (e.getSource() == exportItem) {
            PanelExport panel = new PanelExport();
            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (panel.getSelectedFormat().equals("excel")) {
                    if(HoaDon.size() == 0){
                        TienIch.CustomMessage("Không có gì để xuất");
                    } else {
                        try {
                            // Chuẩn bị dữ liệu cho exportToExcel
                            ArrayList<List<Object>> data = new ArrayList<>();
                            for (DonHangDTO dh : HoaDon) {
                                List<Object> row = new ArrayList<>();
                                row.add(dh.getMaDH());
                                row.add(dh.getMaKM()!=0?KhuyenMaiBLL.getDiscountById(dh.getMaKM()).getTenKM():"Không có");
                                row.add(new NhanVienBLL().getNhanVienByMa(dh.getMaNV()+"").getTenNV());
                                row.add(dh.getPtThanhToan());
                                row.add(dh.getNgayTT());
                                row.add(dh.getMaDTL()!=0?dh.getMaDTL():"Không có");
                                row.add(TienIch.formatVND(dh.getTienKD()));
                                row.add(TienIch.formatVND(dh.getTongTien()));
                                data.add(row);
                            }
                            String[] columnNames = { "Mã đơn hàng", "Khuyến mãi", "Nhân viên", "Phương thức thanh toán", "Ngày thanh toán", "Mã điểm tích lũy", "Tiền khách đưa", "Thành tiền"};
                            String title = "DANH SÁCH CÁC ĐƠN HÀNG CỦA THÀNH VIÊN";
                            String manv = this.MANV;
                            // Gọi hàm exportToExcel
                            XuatFileExccel.exportToExcel(data, columnNames, title, manv, SEARCH2);
                        } catch (Exception ex) {
                            TienIch.CustomMessage("Lỗi khi xuất file Excel: " + ex.getMessage());
                        }
                    }
                } else {
                    if (HoaDon.size() != 0) {
                        double sum = 0;
                        for (DonHangDTO hd : HoaDon) {
                            sum += hd.getTongTien();
                        }
                        PanelExport.InPDFDonHangCuaTTVTheoSearch(HoaDon, SEARCH, kh, TienIch.formatVND(sum),
                                HoaDon.size(), MANV);
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