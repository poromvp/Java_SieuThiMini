package com.FormNhanVien;
import BLL.NhanVienBLL;
import DTO.NhanVienDTO;

import javax.swing.*;

import com.ComponentCommon.StyledTable;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

public class EmployeeTablePanel extends JPanel {
    private DetailPanelNV detailPanel;
    private StyledTable tablePanel;
    private NhanVienBLL nhanVienBLL;
    private Object[][] data;
    private Connection conn;

    public EmployeeTablePanel(Connection conn, DetailPanelNV detailPanel) {
        this.conn = conn;
        this.detailPanel = detailPanel;
        setLayout(new BorderLayout());
        
        nhanVienBLL = new NhanVienBLL(conn);
        loadNhanVienTable();
    }

    public void loadNhanVienTable() {
        String[] columnNames = {"Mã NV", "Họ Tên", "Ngày Sinh", "Giới tính", "Địa Chỉ", "Chức Vụ", "Số ĐT", "CCCD", "Trạng thái"};
        
        try {
            List<NhanVienDTO> ds = nhanVienBLL.getAllNhanVien();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            data = new Object[ds.size()][columnNames.length];

            for (int i = 0; i < ds.size(); i++) {
                NhanVienDTO nv = ds.get(i);
                data[i][0] = nv.getMaNV();
                data[i][1] = nv.getTenNV();
                data[i][2] = nv.getNgaySinh() != null ? sdf.format(nv.getNgaySinh()) : "";
                data[i][3] = nv.getGioiTinh();
                data[i][4] = nv.getDiaChi();
                data[i][5] = nv.getChucVu();
                data[i][6] = nv.getSdt();
                data[i][7] = nv.getCccd();
                data[i][8] = nv.getTrangThai(); 
            }

            tablePanel = new StyledTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(tablePanel);
            scrollPane.setBackground(Color.WHITE);
            add(scrollPane, BorderLayout.CENTER);

            addTableClickEvent(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reloadData() {
        removeAll();  // Xóa tất cả component cũ
    
        String[] columnNames = {"Mã NV", "Họ Tên", "Ngày Sinh", "Giới tính", "Địa Chỉ", "Chức Vụ", "Số ĐT", "CCCD", "Trạng thái"};
    
        try {
            List<NhanVienDTO> ds = nhanVienBLL.getAllNhanVien();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
            Object[][] newData = new Object[ds.size()][columnNames.length];
    
            for (int i = 0; i < ds.size(); i++) {
                NhanVienDTO nv = ds.get(i);
                newData[i][0] = nv.getMaNV();
                newData[i][1] = nv.getTenNV();
                newData[i][2] = nv.getNgaySinh() != null ? sdf.format(nv.getNgaySinh()) : "";
                newData[i][3] = nv.getGioiTinh();
                newData[i][4] = nv.getDiaChi();
                newData[i][5] = nv.getChucVu();
                newData[i][6] = nv.getSdt();
                newData[i][7] = nv.getCccd();
                newData[i][8] = nv.getTrangThai();
            }
    
            this.tablePanel = new StyledTable(newData, columnNames);
            JScrollPane scrollPane = new JScrollPane(tablePanel);
            scrollPane.setBackground(Color.WHITE);
    
            // 👉 Đặt lại layout và component
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
    
            // 👉 Thêm lại mouse listener
            addTableClickEvent();
    
            revalidate();
            repaint();
    
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load lại dữ liệu bảng!");
        }
    }
    private void addTableClickEvent() {
        tablePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 ) {
                    int selectedRow = tablePanel.getSelectedRow();
                    if (selectedRow != -1 && detailPanel != null) {
                        String maNV = String.valueOf(tablePanel.getValueAt(selectedRow, 0));
                        String hoTen = String.valueOf(tablePanel.getValueAt(selectedRow, 1));
                        String ngaySinh = String.valueOf(tablePanel.getValueAt(selectedRow, 2));
                        String gioiTinh = String.valueOf(tablePanel.getValueAt(selectedRow, 3));
                        String diaChi = String.valueOf(tablePanel.getValueAt(selectedRow, 4));
                        String chucVu = String.valueOf(tablePanel.getValueAt(selectedRow, 5));
                        String soDT = String.valueOf(tablePanel.getValueAt(selectedRow, 6));
                        String cccd = String.valueOf(tablePanel.getValueAt(selectedRow, 7));
                        String trangThai = String.valueOf(tablePanel.getValueAt(selectedRow, 8));
    
                        detailPanel.setEmployeeData(maNV, hoTen, ngaySinh, gioiTinh, diaChi, chucVu, soDT, cccd, trangThai);
                    }
                }
            }
        });
        
    }
        
    
    
    
    public static void main(String[] args) {
        try {
            Connection conn = JDBC.DBConnection.getConnection(); // giả sử bạn có DBConnection class
    
            JFrame frame = new JFrame("Employee Table Panel Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
    
            EmployeeTablePanel employeeTablePanel = new EmployeeTablePanel(conn, new DetailPanelNV());
            frame.add(employeeTablePanel);
    
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
