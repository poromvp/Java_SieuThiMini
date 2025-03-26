package com.FormNhanVien;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.ComponentCommon.StyledTable;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeTablePanel extends JPanel {
    private DetailPanelNV detailPanel;

    public EmployeeTablePanel(DetailPanelNV detailPanel) {
        this.detailPanel = detailPanel; 
        setLayout(new BorderLayout());

        String[] columnNames = {"Mã NV", "Họ Tên", "Ngày Sinh", "Giới tính", "Địa Chỉ", "Chức Vụ", "Số ĐT", "CCCD", "Trạng thái"};
        Object[][] data = {
            {"1", "Nguyễn Văn A", "2000-01-01", "Nam", "Hà Nội", "Nhân viên", "0123456789", "123456789", "true"},
            {"2", "Trần Thị B", "2002-05-15", "Nữ", "Hồ Chí Minh", "Nhân viên", "0987654321", "987654321", "true"},
            {"3", "Lê Văn C", "1998-10-20", "Nam", "Đà Nẵng", "Quản lý", "0912345678", "456789123", "false"}
        };

        StyledTable tablePanel = new StyledTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(tablePanel);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        tablePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tablePanel.getSelectedRow();
                if (selectedRow != -1 && detailPanel != null) {
                    String maNV = tablePanel.getValueAt(selectedRow, 0).toString();
                    String hoTen = tablePanel.getValueAt(selectedRow, 1).toString();
                    String ngaySinh = tablePanel.getValueAt(selectedRow, 2).toString();
                    String gioiTinh = tablePanel.getValueAt(selectedRow, 3).toString();
                    String diaChi = tablePanel.getValueAt(selectedRow, 4).toString();
                    String chucVu = tablePanel.getValueAt(selectedRow, 5).toString();
                    String soDT = tablePanel.getValueAt(selectedRow, 6).toString();
                    String cccd = tablePanel.getValueAt(selectedRow, 7).toString();
                    boolean tinhTrang = Boolean.parseBoolean(tablePanel.getValueAt(selectedRow, 8).toString());

                    detailPanel.setEmployeeData(maNV, hoTen, ngaySinh, gioiTinh, diaChi, chucVu, soDT, cccd, tinhTrang);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Table Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        EmployeeTablePanel employeeTablePanel = new EmployeeTablePanel(new DetailPanelNV());

        frame.add(employeeTablePanel);
        frame.setVisible(true);
    }
}
