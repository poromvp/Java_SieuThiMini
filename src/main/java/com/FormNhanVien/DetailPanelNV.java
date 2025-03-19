package com.FormNhanVien;
import javax.swing.*;

import com.ComponentCommon.ButtonCustom;
import com.ComponentCommon.StyledTextField;
import com.toedter.calendar.JDateChooser;

import java.awt.*;

public class DetailPanelNV extends JPanel {
    public DetailPanelNV() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Thông Tin Nhân Viên"));
        setPreferredSize(new Dimension(240, 0));

        StyledTextField maNVField = new StyledTextField();
        StyledTextField hoTenField = new StyledTextField();
        JDateChooser ngaySinhChooser = new JDateChooser();
        ngaySinhChooser.setDateFormatString("dd-MM-yyyy"); 
        String combo[]={"Nam","Nữ"};
        JComboBox<String> cbGioiTinh = new JComboBox<>(combo);
        StyledTextField diaChiField = new StyledTextField();
        String chucVu[]={"Nhân Viên","Quản lý"};
        JComboBox<String> cbChucVu = new JComboBox<>(chucVu);
        StyledTextField soDTField = new StyledTextField();
        StyledTextField CCCD = new StyledTextField();
        JCheckBox cbTinhTrang = new JCheckBox("Đang làm việc");
        cbTinhTrang.setBackground(Color.WHITE);
        cbTinhTrang.setSelected(true);

        JPanel topDetailPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        topDetailPanel.setBackground(Color.WHITE);
        topDetailPanel.add(new JLabel("Mã nhân viên:"));
        topDetailPanel.add(maNVField);
        topDetailPanel.add(new JLabel("Họ tên:"));
        topDetailPanel.add(hoTenField);
        topDetailPanel.add(new JLabel("Ngày sinh:"));
        topDetailPanel.add(ngaySinhChooser);
        topDetailPanel.add(new JLabel("Giới tính:"));
        topDetailPanel.add(cbGioiTinh);
        topDetailPanel.add(new JLabel("Địa chỉ:"));
        topDetailPanel.add(diaChiField);
        topDetailPanel.add(new JLabel("Chức vụ:"));
        topDetailPanel.add(cbChucVu);
        topDetailPanel.add(new JLabel("Số điện thoại:"));
        topDetailPanel.add(soDTField);
        topDetailPanel.add(new JLabel("CCCD:"));
        topDetailPanel.add(CCCD);
        topDetailPanel.add(new JLabel("Tình trạng:")); 
        topDetailPanel.add(cbTinhTrang);

        add(topDetailPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(new ButtonCustom("Thêm","add",12,40,40));
        buttonPanel.add(new ButtonCustom("Sửa","edit",12,40,40));
        buttonPanel.add(new ButtonCustom("Xóa","del",12,40,40));
        add(buttonPanel, BorderLayout.SOUTH);
        // setPreferredSize(new Dimension(350, 600)); // Tăng chiều rộng panel

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Detail Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        
        DetailPanelNV detailPanel = new DetailPanelNV();
        
        frame.add(detailPanel);
        frame.setVisible(true);
    }
}