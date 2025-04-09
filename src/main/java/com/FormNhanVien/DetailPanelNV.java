package com.FormNhanVien;
import javax.swing.*;

import com.ComponentCommon.ButtonCustom;
import com.ComponentCommon.StyledTextField;
import com.toedter.calendar.JDateChooser;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import JDBC.DBConnection;

import java.awt.*;
import java.text.SimpleDateFormat;

public class DetailPanelNV extends JPanel {
    StyledTextField maNVField = new StyledTextField();
    StyledTextField hoTenField = new StyledTextField();
    JDateChooser ngaySinhChooser = new JDateChooser();
    String combo[]={"Nam","Nữ"};
    JComboBox<String> cbGioiTinh = new JComboBox<>(combo);
    StyledTextField diaChiField = new StyledTextField();
    String chucVu[]={"Nhân Viên","Quản lý"};
    JComboBox<String> cbChucVu = new JComboBox<>(chucVu);
    StyledTextField soDTField = new StyledTextField();
    StyledTextField CCCD = new StyledTextField();
    JCheckBox cbTinhTrang = new JCheckBox("Đang làm việc");
    private EmployeeTablePanel tablePanel;
    Window window = SwingUtilities.getWindowAncestor(this);

    public DetailPanelNV() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Thông Tin Nhân Viên"));
        setPreferredSize(new Dimension(240, 0));

      
        ngaySinhChooser.setDateFormatString("dd-MM-yyyy"); 
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
        ButtonCustom btnThem = new ButtonCustom("Thêm", "add", 12, 40, 40);
        btnThem.addActionListener(e -> insertNhanVien());
        buttonPanel.add(btnThem);

        ButtonCustom btnSua = new ButtonCustom("Sửa","edit",12,40,40);
        btnSua.addActionListener(e -> updateNhanVien());
        buttonPanel.add(btnSua);
        
        ButtonCustom btnXoa = new ButtonCustom("Xóa","del",12,40,40);
        btnXoa.addActionListener(e -> deleteNhanVien());
        buttonPanel.add(btnXoa);

        add(buttonPanel, BorderLayout.SOUTH);

    }
    public void setEmployeeData(String maNV, String hoTen, String ngaySinh, String gioiTinh,
    String diaChi, String chucVu, String soDT, String cccd, String trangThai) {

        maNVField.setText(maNV);
        hoTenField.setText(hoTen);

        try {
            if (ngaySinh != null && !ngaySinh.trim().isEmpty()) {
                java.sql.Date sqlDate;

                if (ngaySinh.contains("/")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    sdf.setLenient(false);
                    java.util.Date utilDate = sdf.parse(ngaySinh);
                    sqlDate = new java.sql.Date(utilDate.getTime());
                } else {
                    sqlDate = java.sql.Date.valueOf(ngaySinh);
                }

                ngaySinhChooser.setDate(sqlDate);
            } else {
                ngaySinhChooser.setDate(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi ngày sinh: " + ngaySinh);
            ngaySinhChooser.setDate(null);
        }

        cbGioiTinh.setSelectedItem(gioiTinh);
        diaChiField.setText(diaChi);
        cbChucVu.setSelectedItem(chucVu);
        soDTField.setText(soDT);
        CCCD.setText(cccd);
        cbTinhTrang.setSelected("ACTIVE".equalsIgnoreCase(trangThai));

    }
    private void insertNhanVien() {
        try {
            if (maNVField.getText().trim().isEmpty()) {
                throw new Exception("Mã nhân viên không được để trống");
            }
            int maNV;
            try {
                maNV = Integer.parseInt(maNVField.getText().trim());
                if (maNV <= 0) {
                    throw new Exception("Mã nhân viên phải là số dương");
                }
            } catch (NumberFormatException e) {
                throw new Exception("Mã nhân viên phải là số nguyên hợp lệ");
            }
    
            String tenNV = hoTenField.getText().trim();
            if (tenNV.isEmpty()) {
                throw new Exception("Họ tên không được để trống");
            }
            if (!tenNV.matches("^[\\p{L} ]+$")) {
                throw new Exception("Họ tên chỉ được chứa chữ cái và khoảng trắng");
            }
    
            java.util.Date utilDate = ngaySinhChooser.getDate();
            if (utilDate == null) {
                throw new Exception("Vui lòng chọn ngày sinh");
            }
            java.sql.Date ngaySinh = new java.sql.Date(utilDate.getTime());
            
            java.util.Date today = new java.util.Date();
            long diffInMillies = Math.abs(today.getTime() - utilDate.getTime());
            long diff = diffInMillies / (1000L * 60 * 60 * 24 * 365);
            if (diff < 18) {
                throw new Exception("Nhân viên phải đủ 18 tuổi");
            }
    
            String soDT = soDTField.getText().trim();
            if (soDT.isEmpty()) {
                throw new Exception("Số điện thoại không được để trống");
            }
            if (!soDT.matches("^[0-9]{10}$")) {
                throw new Exception("Số điện thoại phải có 10 chữ số");
            }
    
            String cccd = CCCD.getText().trim();
            if (cccd.isEmpty()) {
                throw new Exception("CCCD không được để trống");
            }
            if (!cccd.matches("^[0-9]{12}$")) {
                throw new Exception("CCCD phải có đúng 12 chữ số");
            }
    
            String gioiTinh = cbGioiTinh.getSelectedItem().toString();
            String diaChi = diaChiField.getText().trim();
            if (diaChi.isEmpty()) {
                throw new Exception("Địa chỉ không được để trống");
            }
            String chucVu = cbChucVu.getSelectedItem().toString();
            String trangThai = cbTinhTrang.isSelected() ? "ACTIVE" : "INACTIVE";
            double luong = 0; 
    
            NhanVienDTO nv = new NhanVienDTO(maNV, tenNV, ngaySinh, gioiTinh, diaChi,
                                           chucVu, soDT, cccd, luong, trangThai);
    
            NhanVienBLL bll = new NhanVienBLL(DBConnection.getConnection());
            boolean success = bll.insertNhanVien(nv);
    
            if (success) {
                JOptionPane.showMessageDialog(window, "Thêm nhân viên thành công!");
                clearForm();
                tablePanel.reloadData();
                
            } else {
                JOptionPane.showMessageDialog(window, "Thêm nhân viên thất bại! Có thể mã nhân viên đã tồn tại");
            }
    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(window, "Lỗi: " + e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        maNVField.setText("");
        hoTenField.setText("");
        ngaySinhChooser.setDate(null);
        cbGioiTinh.setSelectedIndex(0);
        diaChiField.setText("");
        cbChucVu.setSelectedIndex(0);
        soDTField.setText("");
        CCCD.setText("");
        cbTinhTrang.setSelected(true);
    }
    
    private void updateNhanVien() {
        int confirm = JOptionPane.showConfirmDialog(
            window, 
            "Bạn có chắc chắn muốn cập nhật thông tin nhân viên này?",
            "Xác nhận cập nhật",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
    
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }else{
               
        try {
            if (maNVField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(window, "Mã nhân viên không được để trống!");
                return;
            }
            
            int maNV = Integer.parseInt(maNVField.getText().trim());
            String tenNV = hoTenField.getText().trim();
            
            if (tenNV.isEmpty()) {
                JOptionPane.showMessageDialog(window, "Họ tên không được để trống!");
                return;
            }
            
            java.util.Date utilDate = ngaySinhChooser.getDate();
            if (utilDate == null) {
                JOptionPane.showMessageDialog(window, "Ngày sinh không hợp lệ!");
                return;
            }
            java.sql.Date ngaySinh = new java.sql.Date(utilDate.getTime());
            String gioiTinh = cbGioiTinh.getSelectedItem().toString();
            String diaChi = diaChiField.getText();
            String chucVu = cbChucVu.getSelectedItem().toString();
            String soDT = soDTField.getText();
            String cccd = CCCD.getText();
            boolean tinhTrangCheck = cbTinhTrang.isSelected();
            String trangThai = tinhTrangCheck ? "ACTIVE" : "INACTIVE";
            double luong = 0.0; 

            NhanVienDTO nv = new NhanVienDTO(maNV, tenNV, ngaySinh, gioiTinh, diaChi,
                                            chucVu, soDT, cccd, luong, trangThai);

            NhanVienBLL bll = new NhanVienBLL(DBConnection.getConnection());
            boolean success = bll.updateNhanVien(nv);

            if (success) {
                JOptionPane.showMessageDialog(window, "Cập nhật thành công!"); 
                if (tablePanel != null) {
                    tablePanel.reloadData();
                }
            } else {
                JOptionPane.showMessageDialog(window, "Cập nhật thất bại!");
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(window, "Mã nhân viên phải là số!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(window, "Lỗi khi cập nhật: " + e.getMessage());
        }
    }
    }
   private void deleteNhanVien() {
    try {
        int maNV = Integer.parseInt(maNVField.getText());
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            NhanVienBLL bll = new NhanVienBLL(DBConnection.getConnection());
            boolean success = bll.deleteNhanVien(maNV);

            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");

                if (tablePanel != null) {
                    tablePanel.reloadData();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + e.getMessage());
    }
    
}



    public void setTablePanel(EmployeeTablePanel tablePanel) {
        this.tablePanel = tablePanel;
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