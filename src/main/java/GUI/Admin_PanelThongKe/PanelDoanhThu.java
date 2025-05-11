package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import BLL.DonHangBLL;
import BLL.KhuyenMaiBLL;
import BLL.NhanVienBLL;
import BLL.SearchFilterBLL;
import BLL.TheThanhVienBLL;
import DTO.DonHangDTO;
import DTO.SearchFilterDTO;
import GUI.ComponentCommon.*;

public class PanelDoanhThu extends JPanel implements ActionListener {
    JLabel tongdoanhthu, tongdonhang;
    JComboBox<String> mocthoigian;
    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel model;
    public ArrayList<DonHangDTO> HoaDon = DonHangBLL.getAllOrders();
    JButton btnTim;
    JPanel pn1, pn2, pn3;
    JPopupMenu popupMenu;
    JMenuItem exportItem;

    public void initPanel1() {
        loadThongKe(HoaDon);
        pn1.setBorder(new CompoundBorder(new TitledBorder("Thống kê"), new EmptyBorder(4, 4, 4, 4)));
        pn1.setLayout(new GridLayout(2, 2));

        JLabel tong = new JLabel(("Tổng Doanh Thu:"));
        TienIch.labelStyle(tong, 5, 20, null);
        pn1.add(tong);

        TienIch.labelStyle(tongdoanhthu, 2, 20, null);
        pn1.add(tongdoanhthu);

        JLabel tonghd = new JLabel("Tổng đơn hàng (hóa đơn):");
        TienIch.labelStyle(tonghd, 5, 20, null);
        pn1.add(tonghd);

        TienIch.labelStyle(tongdonhang, 2, 20, null);
        pn1.add(tongdonhang);
    }

    public void initPanel2() {
        pn2.setBorder(new CompoundBorder(new TitledBorder("Hành động"), new EmptyBorder(4, 4, 4, 4)));
        pn2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        btnTim = new JButton("Tìm");
        TienIch.nutStyle(btnTim, "search.png", 17, 0, 0);
        pn2.add(btnTim, gbc);

        btnTim.addActionListener(this);
    }

    public void initPanel3() {
        pn3.setBorder(new CompoundBorder(new TitledBorder("Danh sách"), new EmptyBorder(4, 4, 4, 4)));
        pn3.setLayout(new BorderLayout());
        String[] tencot = { "Mã đơn hàng", "Mã nhân viên", "PTTT", "Thành tiền", "Ngày" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        loadDonHang(HoaDon);
        StyledTable.TableEvent(tb, model, "HD", MANV); // Giữ sự kiện double-click
        StyledTable.hoverTable(tb, model);
        JScrollPane scr = new JScrollPane(tb);
        pn3.add(scr, BorderLayout.CENTER);
        // Thêm sự kiện chuột phải cho bảng
        showpupop(tb);
        showpupop(scr);
    }
    public String MANV;
    public PanelDoanhThu(String MANV) {
        this.MANV = MANV;
        setBorder(new CompoundBorder(new TitledBorder("Báo cáo doanh thu tổng hợp"), new EmptyBorder(4, 4, 4, 4)));
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

        gbc.gridx = 1;
        gbc.gridy = 0;
        pn2 = new JPanel();
        initPanel2();
        add(pn2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        pn3 = new JPanel();
        initPanel3();
        add(pn3, gbc);

        // Thêm popup menu
        popupMenu = new JPopupMenu();
        exportItem = new JMenuItem("Xuất file");
        exportItem.addActionListener(this);
        popupMenu.add(exportItem);
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

    private void loadDonHang(ArrayList<DonHangDTO> danhsach) {
        model.setRowCount(0);
        for (DonHangDTO hd : danhsach) {
            model.addRow(new Object[] {
                    hd.getMaDH(),
                    hd.getMaNV(),
                    hd.getPtThanhToan(),
                    TienIch.formatVND(hd.getTongTien()),
                    TienIch.ddmmyyyy(hd.getNgayTT()) });
        }
    }

    private void loadThongKe(ArrayList<DonHangDTO> danhsach) {
        double sumDoanhThu = 0;
        int sumDonHang = danhsach.size();
        for (DonHangDTO hd : danhsach) {
            sumDoanhThu += hd.getTongTien();
        }
        tongdoanhthu = new JLabel(TienIch.formatVND(sumDoanhThu));
        tongdonhang = new JLabel(sumDonHang + " Đơn");
    }

    public SearchFilterDTO SEARCH = new SearchFilterDTO();
    public ArrayList<String> SEARCH2 = new ArrayList<>();
    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == btnTim) {
            PanelTimThK panel = new PanelTimThK();
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == 0) {
                HoaDon = SearchFilterBLL.timKiem_SapXepDonHang(panel.filter());
                SEARCH = panel.filter();
                SEARCH2 = panel.stringsearch();
                if(HoaDon.size()==0){
                    TienIch.CustomMessage("Không tìm thấy");
                }
                System.out.println(HoaDon.size());
                loadDonHang(HoaDon);
            }
        } else if (e.getSource() == exportItem) {
            PanelExport panel = new PanelExport();
            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (panel.getSelectedFormat().equals("excel")) {
                    if(HoaDon.size()==0){
                        TienIch.CustomMessage("Không có gì để in");
                    } else {
                        try {
                            // Chuẩn bị dữ liệu cho exportToExcel
                            ArrayList<List<Object>> data = new ArrayList<>();
                            for (DonHangDTO dh : HoaDon) {
                                List<Object> row = new ArrayList<>();
                                row.add(dh.getMaDH());
                                row.add(dh.getMaKM()!=0?KhuyenMaiBLL.getDiscountById(dh.getMaKM()).getTenKM():"Không có");
                                row.add(dh.getMaKH()!=0?TheThanhVienBLL.getMemberById(dh.getMaKH()).getTenTV():"Không có");
                                row.add(new NhanVienBLL().getNameNV(dh.getMaNV()+""));
                                row.add(dh.getPtThanhToan());
                                row.add(dh.getNgayTT());
                                row.add(dh.getMaDTL()!=0?dh.getMaDTL():"Không có");
                                row.add(TienIch.formatVND(dh.getTienKD()));
                                row.add(TienIch.formatVND(dh.getTongTien()));
                                data.add(row);
                            }
                            String[] columnNames = { "Mã đơn hàng", "Khuyến mãi", "Khách hàng", "Nhân viên", "Phương thức thanh toán", "Ngày thanh toán", "Mã điểm tích lũy", "Tiền khách đưa", "Thành tiền"};
                            String title = "DANH SÁCH ĐƠN HÀNG";
                            String manv = this.MANV;
                            // Gọi hàm exportToExcel
                            XuatFileExccel.exportToExcel(data, columnNames, title, manv, SEARCH2);
                        } catch (Exception ex) {
                            TienIch.CustomMessage("Lỗi khi xuất file Excel: " + ex.getMessage());
                        }
                    }
                } else {
                    if(HoaDon.size()==0){
                        TienIch.CustomMessage("Không có gì để in");
                    } else {
                        PanelExport.InPDFDonHang(HoaDon, SEARCH, MANV);
                    }
                }
            } else if (result == JOptionPane.CANCEL_OPTION) {
                TienIch.CustomMessage("Đã hủy xuất file");
            } else {
                TienIch.CustomMessage("Đã hủy xuất file");
            }
            //ExportToPDF.exportJTableToPDF(tb);
        }
        TienIch.resetUI();
    }
}