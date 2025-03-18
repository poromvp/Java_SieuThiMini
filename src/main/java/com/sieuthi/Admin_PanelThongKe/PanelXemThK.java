package com.sieuthi.Admin_PanelThongKe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.sieuthi.TienIch;

import java.awt.*;
import java.util.ArrayList;

public class PanelXemThK extends JPanel {
    JTextArea txtMaHD, txtMANV;
    JPanel pn1, pn2, pn3, pn4;
    JLabel lbIdHD, lbDay, lbGuest, lbNV;

    public void initPanel1() {
        pn1.setLayout(new GridLayout(4, 2));
        JLabel mahd = new JLabel("Mã hóa đơn: ");
        TienIch.labelStyle(mahd, 1, 20, null);
        pn1.add(mahd);
        pn1.add(lbIdHD);

        JLabel ngay = new JLabel("Ngày: ");
        TienIch.labelStyle(ngay, 1, 20, null);
        pn1.add(ngay);
        pn1.add(lbDay);

        JLabel khachhang = new JLabel("Khách hàng: ");
        TienIch.labelStyle(khachhang, 1, 20, null);
        pn1.add(khachhang);
        pn1.add(lbGuest);

        JLabel nhanvien = new JLabel("Nhân viên: ");
        TienIch.labelStyle(nhanvien, 1, 20, null);
        pn1.add(nhanvien);
        pn1.add(lbNV);
    }

    JLabel lbTitle;

    public void initPanel2() {
        pn2.setLayout(new BorderLayout());
        lbTitle = new JLabel("HÓA ĐƠN BÁN HÀNG");
        TienIch.labelStyle(lbTitle, 1, 30, null);
        pn2.add(lbTitle,BorderLayout.CENTER);
    }

    JTable tb;
    DefaultTableModel modelMini;
    public ArrayList<hoadontemp> HoaDon = new ArrayList<>();

    public void initPanel3() {
        pn3.setLayout(new FlowLayout());
        pn3.setLayout(new FlowLayout());
        String[] tencot = { "ID", "Mặt hàng", "Số lượng", "Đơn giá", "Thành tiền" };
        hoadontemp a = new hoadontemp("1", "Cam", "1", "10,000");
        hoadontemp b = new hoadontemp("2", "Kem đánh răng", "1", "100,000");
        hoadontemp c = new hoadontemp("3", "Nước lọc", "1", "10,000");
        hoadontemp d = new hoadontemp("4", "Snack khoai tây", "1", "100,000");
        HoaDon.add(a);
        HoaDon.add(b);
        HoaDon.add(c);
        HoaDon.add(d);
        modelMini = new DefaultTableModel(tencot, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        refreshTable();
        tb = new JTable(modelMini);
        TableControl.TableStyle(tb, modelMini);
        JScrollPane scr = new JScrollPane(tb);
        scr.setPreferredSize(new Dimension(800, 120));
        pn3.add(scr);
    }

    JLabel lbTotal, lbDiscount, lbThu;
    public void initPanel4(){
        pn4.setLayout(new GridLayout(3,2));
        
        JLabel tongtien = new JLabel("Tổng tiền: ");
        TienIch.labelStyle(tongtien, 1, 15, null);
        pn4.add(tongtien);

        lbTotal = new JLabel("120,000" + " VND");
        TienIch.labelStyle(lbTotal, 1, 15, null);
        lbTotal.setForeground(new Color(44, 99, 44));
        pn4.add(lbTotal);

        JLabel giamgia = new JLabel("Giảm giá: ");
        TienIch.labelStyle(giamgia, 1, 15, null);
        pn4.add(giamgia);

        lbDiscount = new JLabel("-" + "20,000" + " VND");
        TienIch.labelStyle(lbDiscount, 1, 15, null);
        lbDiscount.setForeground(Color.RED);
        pn4.add(lbDiscount);

        JLabel thu = new JLabel("Thu: ");
        TienIch.labelStyle(thu, 1, 15, null);
        pn4.add(thu);

        lbThu = new JLabel("100,000" + " VND");
        TienIch.labelStyle(lbThu, 1, 15, null);
        lbThu.setForeground(new Color(44, 99, 44));
        pn4.add(lbThu);
    }

    public PanelXemThK(DefaultTableModel model, int dong) {
        setBackground(new Color(226, 224, 221));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pn1 = new JPanel();
        lbIdHD = new JLabel(LayChuoiTuBang(model, dong, 0));
        TienIch.labelStyle(lbIdHD, 1, 20, null);
        lbDay = new JLabel(LayChuoiTuBang(model, dong, 3));
        TienIch.labelStyle(lbDay, 1, 20, null);
        lbGuest = new JLabel(LayChuoiTuBang(model, dong, 1));
        TienIch.labelStyle(lbGuest, 1, 20, null);
        lbNV = new JLabel(LayChuoiTuBang(model, dong, 1));
        TienIch.labelStyle(lbNV, 1, 20, null);
        initPanel1();
        add(pn1,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pn2 = new JPanel();
        initPanel2();
        add(pn2,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pn3 = new JPanel();
        initPanel3();
        add(pn3,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pn4 = new JPanel();
        initPanel4();
        add(pn4,gbc);
    }

    public String LayChuoiTuBang(DefaultTableModel model, int dong, int cot) {
        Object txt = model.getValueAt(dong, cot);
        return txt.toString();
    }

    private void refreshTable() {
        modelMini.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (hoadontemp s : HoaDon) {
            modelMini.addRow(new Object[] { s.getId(), s.getName(), s.getPrice(), s.getDate() });
        }
    }
}
