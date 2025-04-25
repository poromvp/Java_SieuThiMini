package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;


import BLL.ChiTietNhapHangBLL;
import BLL.NhapHangBLL;
import BLL.SanPhamBLL;
import DTO.ChiTietPNHangDTO;
import DTO.PhieuNhapHangDTO;
import GUI.ComponentCommon.*;
import GUI.FormWareHouse.FormProductDetail;

public class PanelXemChiTietPhieuNhap extends JPanel {
    private JLabel lbMaPhieuNhap, lbNgayNhap, lbTongTien;
    private StyledTable tb;
    private DefaultTableModel model;
    private JPanel pn1, pn2;
    private PhieuNhapHangDTO phieunhap;
    private ArrayList<ChiTietPNHangDTO> chiTietList;

    public void initPanel1() {
        TienIch.taoTitleBorder(pn1, "Thông tin chung");
        pn1.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lb1 = new JLabel("Mã đơn nhập hàng:");
        TienIch.labelStyle(lb1, 4, 20, null);
        pn1.add(lb1);

        lbMaPhieuNhap = new JLabel(phieunhap.getMaPNH()+"");
        TienIch.labelStyle(lbMaPhieuNhap, 2, 20, null);
        pn1.add(lbMaPhieuNhap);

        JLabel lb2 = new JLabel("Ngày nhập:");
        TienIch.labelStyle(lb2, 4, 20, null);
        pn1.add(lb2);

        lbNgayNhap = new JLabel(TienIch.ddmmyyyy(phieunhap.getNgayNhap()));
        TienIch.labelStyle(lbNgayNhap, 2, 20, null);
        pn1.add(lbNgayNhap);

        JLabel lb3 = new JLabel("Tổng tiền:");
        TienIch.labelStyle(lb3, 4, 20, null);
        pn1.add(lb3);

        double sum = 0;
        for(ChiTietPNHangDTO ctpn : chiTietList){
            sum+=ctpn.getGiaNhap()*ctpn.getSoLuong();
        }
        lbTongTien = new JLabel(TienIch.formatVND(sum));
        TienIch.labelStyle(lbTongTien, 2, 20, null);
        pn1.add(lbTongTien);
    }

    public void initPanel2() {
        TienIch.taoTitleBorder(pn2, "Chi tiết phiếu nhập");
        pn2.setLayout(new BorderLayout());

        String[] tencot = { "STT", "Mã sản phẩm", "Mã lô hàng", "Số lượng", "Giá nhập", "Thành tiền" };
        Object[][] data = new Object[0][tencot.length];
        tb = new StyledTable(data, tencot);
        model = (DefaultTableModel) tb.getModel();
        loadChiTietPhieuNhap(chiTietList);
        StyledTable.hoverTable(tb, model);
        JScrollPane scr = new JScrollPane(tb);
        pn2.add(scr, BorderLayout.CENTER);
        tb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Kiểm tra double click
                    FormProductDetail detailDialog = new FormProductDetail(null, SanPhamBLL.getProductById((Integer)tb.getValueAt(tb.getSelectedRow(), 1)));
                        detailDialog.setVisible(true);
                }
            }
        });
    }

    private void loadChiTietPhieuNhap(ArrayList<ChiTietPNHangDTO> danhsach) {
        model.setRowCount(0);
        int stt = 1;
        for (ChiTietPNHangDTO ct : danhsach) {
            model.addRow(new Object[] {
                    stt++,
                    ct.getMaSP(),
                    ct.getMaLH(),
                    ct.getSoLuong(),
                    TienIch.formatVND(ct.getGiaNhap()),
                    TienIch.formatVND(ct.getSoLuong() * ct.getGiaNhap())
            });
        }
    }

    public PanelXemChiTietPhieuNhap(int maPhieuNhap) {
        TienIch.setDarkUI();
        this.phieunhap = new NhapHangBLL().getPhieuNhapHangById(maPhieuNhap);
        this.chiTietList = new ChiTietNhapHangBLL().getChiTietByMaPNH(maPhieuNhap);
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
        setPreferredSize(new Dimension(700, 400));
    }
}