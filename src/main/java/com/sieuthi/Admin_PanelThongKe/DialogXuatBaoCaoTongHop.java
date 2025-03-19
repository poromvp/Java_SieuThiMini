package com.sieuthi.Admin_PanelThongKe;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sieuthi.TienIch;
import com.toedter.calendar.JDateChooser;

public class DialogXuatBaoCaoTongHop extends JDialog implements ActionListener, ItemListener {
    JRadioButton rdNgay, rdThang, rdNam, rdTuyChinh, rdTonKho, rdNhap, rdNhanvien, rdPDF, rdExcel, rdTxt;
    JLabel lb1, lb2, lbNgay;
    JDateChooser from, to;
    ButtonGroup group1, group2;
    JButton btnXuatBaoCao, btnHuy;
    JPanel pnZ, pn1, pn2;

    public void initPanel1() {
        pn1.setBorder(new CompoundBorder(new TitledBorder("Loại báo cáo:"), new EmptyBorder(4, 4, 4, 4)));
        pn1.setLayout(new BoxLayout(pn1, BoxLayout.Y_AXIS));

        rdNgay = new JRadioButton("Báo cáo doanh thu theo ngày");
        rdNgay.setSelected(true);
        rdThang = new JRadioButton("Báo cáo doanh thu theo tháng");
        rdNam = new JRadioButton("Báo cáo doanh thu theo năm");
        rdTuyChinh = new JRadioButton("Báo cáo doanh thu theo ngày tùy chỉnh");
        rdTonKho = new JRadioButton("Báo cáo hàng tồn kho");
        rdNhap = new JRadioButton("Báo cáo nhập hàng");
        rdNhanvien = new JRadioButton("Báo cáo nhân viên");
        TienIch.radioStyle(rdNgay);
        TienIch.radioStyle(rdThang);
        TienIch.radioStyle(rdNam);
        TienIch.radioStyle(rdTuyChinh);
        TienIch.radioStyle(rdTonKho);
        TienIch.radioStyle(rdNhap);
        TienIch.radioStyle(rdNhanvien);

        group1 = new ButtonGroup();
        group1.add(rdNgay);
        group1.add(rdThang);
        group1.add(rdNam);
        group1.add(rdTuyChinh);
        group1.add(rdTonKho);
        group1.add(rdNhap);
        group1.add(rdNhanvien);

        pn1.add(rdNgay);
        pn1.add(rdThang);
        pn1.add(rdNam);
        pn1.add(rdTuyChinh);
        pnZ = new JPanel();
        pnZ.setLayout(new BoxLayout(pnZ, BoxLayout.Y_AXIS));
        pn1.add(pnZ);
        pn1.add(rdTonKho);
        pn1.add(rdNhap);
        pn1.add(rdNhanvien);
    }

    public void initPanel2(){
        pn2.setBorder(new CompoundBorder(new TitledBorder("Định dạng tệp:"), new EmptyBorder(4, 4, 4, 4)));
        pn2.setLayout(new BoxLayout(pn2, BoxLayout.Y_AXIS));

        rdPDF = new JRadioButton("PDF");
        rdPDF.setSelected(true);
        rdExcel = new JRadioButton("Excel");
        rdTxt = new JRadioButton("Text");
        TienIch.radioStyle(rdPDF);
        TienIch.radioStyle(rdExcel);
        TienIch.radioStyle(rdTxt);

        group2 = new ButtonGroup();
        group2.add(rdPDF);
        group2.add(rdExcel);
        group2.add(rdTxt);

        pn2.add(rdPDF);
        pn2.add(rdExcel);
        pn2.add(rdTxt);
    }

    public DialogXuatBaoCaoTongHop() {
        setTitle("XUẤT BÁO CÁO TỔNG HỢP");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setModal(true); // Chặn thao tác bên ngoài khi dialog mở
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel ngay = new JLabel("Ngày:");
        TienIch.labelStyle(ngay, 1, 18, null);
        add(ngay,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(new Date());
        lbNgay = new JLabel(formattedDate);
        TienIch.labelStyle(lbNgay, 2, 18, null);
        add(lbNgay,gbc);
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        pn1 = new JPanel();
        initPanel1();
        add(pn1,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        pn2 = new JPanel();
        initPanel2();
        add(pn2,gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 2;
        btnXuatBaoCao = new JButton("Xuất");
        TienIch.nutStyle(btnXuatBaoCao, "check.png", 18, 150, 50);
        add(btnXuatBaoCao,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        btnHuy = new JButton("Hủy");
        TienIch.nutStyle(btnHuy, "cancel.png", 18, 150, 50);
        add(btnHuy,gbc);

        btnXuatBaoCao.addActionListener((ActionListener) this);
        btnHuy.addActionListener((ActionListener) this);

        rdTuyChinh.addItemListener((ItemListener) this);
        lb1 = new JLabel("từ: ");
        lb2 = new JLabel("đến: ");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnXuatBaoCao) {
            JOptionPane.showMessageDialog(null, "helloo");
            dispose();
        }

        if (e.getSource() == btnHuy) {
            dispose();
            JOptionPane.showMessageDialog(null, "Bạn đã hủy");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == rdTuyChinh) {
            if (e.getStateChange() == 1) {
                pnZ.add(lb1);
                from = new JDateChooser();
                pnZ.add(from);
                pnZ.add(lb2);
                to = new JDateChooser();
                pnZ.add(to);
            } else {
                pnZ.remove(lb1);
                pnZ.remove(lb2);
                pnZ.remove(from);
                pnZ.remove(to);
            }
            pnZ.revalidate();
            pnZ.repaint();
        }
    }

}
