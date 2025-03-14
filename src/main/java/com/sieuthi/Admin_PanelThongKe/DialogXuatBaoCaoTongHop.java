package com.sieuthi.Admin_PanelThongKe;

import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

public class DialogXuatBaoCaoTongHop extends JDialog implements ActionListener{
    JRadioButton rdNgay, rdThang, rdNam, rdTonKho, rdNhap, rdNhanvien, rdPDF, rdExcel, rdTxt;
    ButtonGroup group1, group2;
    JButton btnXuatBaoCao, btnHuy;

    public DialogXuatBaoCaoTongHop(){
        setTitle("Tùy chọn");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(300, 400);
        setLocationRelativeTo(null);
        setModal(true);  // Chặn thao tác bên ngoài khi dialog mở

        add(new JLabel("XUẤT BÁO CÁO TỔNG HỢP"));

        add(new JLabel("Ngày: "));

        add(new JLabel(new Date().toString()));

        add(new JLabel("Loại báo cáo:"));

        rdNgay = new JRadioButton("Báo cáo doanh thu theo ngày");
        rdThang = new JRadioButton("Báo cáo doanh thu theo tháng");
        rdNam = new JRadioButton("Báo cáo doanh thu theo năm");
        rdTonKho = new JRadioButton("Báo cáo hàng tồn kho");
        rdNhap = new JRadioButton("Báo cáo nhập hàng");
        rdNhanvien = new JRadioButton("Báo cáo nhân viên");

        group1 = new ButtonGroup();
        group1.add(rdNgay);
        group1.add(rdThang);
        group1.add(rdNam);
        group1.add(rdTonKho);
        group1.add(rdNhap);
        group1.add(rdNhanvien);

        add(rdNgay);
        add(rdThang);
        add(rdNam);
        add(rdTonKho);
        add(rdNhap);
        add(rdNhanvien);

        add(new JLabel("Định dạng tệp:"));

        rdPDF = new JRadioButton("PDF");
        rdExcel = new JRadioButton("Excel");
        rdTxt = new JRadioButton("Text");

        group2 = new ButtonGroup();
        group2.add(rdPDF);
        group2.add(rdExcel);
        group2.add(rdTxt);

        add(rdPDF);
        add(rdExcel);
        add(rdTxt);

        btnXuatBaoCao = new JButton("Xuất báo cáo");
        btnHuy = new JButton("Hủy");

        add(btnXuatBaoCao);
        add(btnHuy);

        btnXuatBaoCao.addActionListener((ActionListener)this);
        btnHuy.addActionListener((ActionListener)this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() ==  btnXuatBaoCao){
            JOptionPane.showMessageDialog(null, "helloo");
            dispose();
        }
        
        if(e.getSource() ==  btnHuy){
            dispose();
            JOptionPane.showMessageDialog(null, "Bạn đã hủy");
        }
    }
}
