package GUI.Admin_PanelThongKe;

import javax.swing.*;
import javax.swing.table.*;

import BLL.ChiTietDonHangBLL;
import BLL.DonHangBLL;
import BLL.SanPhamBLL;
import BLL.TheThanhVienBLL;
import DTO.ChiTietDonHangDTO;
import DTO.DonHangDTO;
import DTO.NhanVienDTO;
import DTO.TheThanhVienDTO;
import GUI.ComponentCommon.*;
import GUI.FormWareHouse.FormProductDetail;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PanelXemThK extends JPanel {
    JTextArea txtMaHD, txtMANV;
    JPanel pn1, pn2, pn3, pn4;
    JLabel lbIdHD, lbDay, lbGuest, lbNV;

    public void initPanel1() {
        TienIch.setDarkUI();
        pn1.setLayout(new GridLayout(4, 2));
        JLabel mahd = new JLabel("Mã hóa đơn: ");
        TienIch.labelStyle(mahd, 4, 20, null);
        pn1.add(mahd);
        pn1.add(lbIdHD);

        JLabel ngay = new JLabel("Ngày: ");
        TienIch.labelStyle(ngay, 4, 20, null);
        pn1.add(ngay);
        pn1.add(lbDay);

        JLabel khachhang = new JLabel("Khách hàng: ");
        TienIch.labelStyle(khachhang, 4, 20, null);
        pn1.add(khachhang);
        pn1.add(lbGuest);

        JLabel nhanvien = new JLabel("Nhân viên: ");
        TienIch.labelStyle(nhanvien, 4, 20, null);
        pn1.add(nhanvien);
        pn1.add(lbNV);
    }

    JLabel lbTitle;

    public void initPanel2() {
        pn2.setLayout(new BorderLayout());
        lbTitle = new JLabel("HÓA ĐƠN BÁN HÀNG");
        TienIch.labelStyle(lbTitle, 4, 30, null);
        pn2.add(lbTitle, BorderLayout.CENTER);
    }

    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel modelMini;
    public ArrayList<ChiTietDonHangDTO> ChiTietDH;

    public void initPanel3() {
        pn3.setLayout(new FlowLayout());
        String[] tencot = { "ID", "Mặt hàng", "Số lượng", "Đơn giá", "Thành tiền" };
        ChiTietDH = ChiTietDonHangBLL.getChiTietByMaDH(Integer.parseInt(getid));
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        modelMini = (DefaultTableModel) tb.getModel();
        StyledTable.hoverTable(tb, modelMini);
        loadChiTiet();
        JScrollPane scr = new JScrollPane(tb);

        TienIch.setPreferredSizeTuDong(scr, tb);

        pn3.add(scr);
    }

    JLabel lbTotal, lbDiscount, lbThu;

    public void initPanel4() {
        pn4.setLayout(new GridLayout(2, 2));

        JLabel tongtien = new JLabel("Tổng tiền: ");
        TienIch.labelStyle(tongtien, 4, 15, null);
        pn4.add(tongtien);

        TienIch.labelStyle(lbTotal, 4, 15, null);
        lbTotal.setForeground(Color.GREEN);
        pn4.add(lbTotal);

        JLabel thu = new JLabel("Thu (Sau khi giảm giá): ");
        TienIch.labelStyle(thu, 4, 15, null);
        pn4.add(thu);

        lbThu = new JLabel(TienIch.formatVND(donhang.getTongTien()));
        TienIch.labelStyle(lbThu, 4, 15, null);
        lbThu.setForeground(Color.GREEN);
        pn4.add(lbThu);
    }
    public String MANV;
    public PanelXemThK(DefaultTableModel model, int dong, String MANV) {
        this.MANV = MANV;
        setBackground(new Color(203, 238, 233));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pn1 = new JPanel();
        initLabel(model, dong);
        initPanel1();
        add(pn1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pn2 = new JPanel();
        initPanel2();
        add(pn2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pn3 = new JPanel();
        initPanel3();
        add(pn3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pn4 = new JPanel();
        initPanel4();
        add(pn4, gbc);

        tb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TienIch.resetUI();
                if (e.getClickCount() == 2) { // Kiểm tra double click
                    FormProductDetail detailDialog = new FormProductDetail(null, SanPhamBLL.getProductById((Integer)tb.getValueAt(tb.getSelectedRow(), 0)));
                        detailDialog.setVisible(true);
                }
            }
        });
    }

    public String LayChuoiTuBang(DefaultTableModel model, int dong, int cot) {
        Object txt = model.getValueAt(dong, cot);
        return txt.toString();
    }

    DonHangDTO donhang;
    TheThanhVienDTO khach;
    NhanVienDTO nhanvien;
    String getid;

    public void initLabel(DefaultTableModel model, int dong) {
        getid = model.getValueAt(dong, 0).toString();
        donhang = DonHangBLL.getOrderById(Integer.parseInt(getid));

        lbIdHD = new JLabel(getid);
        TienIch.labelStyle(lbIdHD, 4, 20, null);
        lbDay = new JLabel(LayChuoiTuBang(model, dong, 4));
        TienIch.labelStyle(lbDay, 4, 20, null);

        khach = TheThanhVienBLL.getMemberById(donhang.getMaKH());
        lbGuest = new JLabel(khach!=null ? khach.getTenTV() : "Không có");
        TienIch.labelStyle(lbGuest, 4, 20, null);

        lbNV = new JLabel(LayChuoiTuBang(model, dong, 1));
        TienIch.labelStyle(lbNV, 4, 20, null);
    }

    private void loadChiTiet() {
        modelMini.setRowCount(0);
        int sum = 0;
        for (ChiTietDonHangDTO ct : ChiTietDH) {
            modelMini.addRow(
                    new Object[] {
                            ct.getMaSP(),
                            SanPhamBLL.getProductById(ct.getMaSP()).getTenSP(),
                            ct.getSoLuong(),
                            TienIch.formatVND(SanPhamBLL.getProductById(ct.getMaSP()).getGia()),
                            TienIch.formatVND(SanPhamBLL.getProductById(ct.getMaSP()).getGia() * ct.getSoLuong()) });
            sum += SanPhamBLL.getProductById(ct.getMaSP()).getGia() * ct.getSoLuong();
        }
        lbTotal = new JLabel(TienIch.formatVND(sum));
    }
}