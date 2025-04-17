package GUI.FormNhanVien;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

import JDBC.DBConnection;

public class FormMainNhanVien extends JPanel {
    
    public FormMainNhanVien(Connection conn) {
        setSize(1000, 600);
        setLayout(new BorderLayout(5,5));
        setBackground(Color.WHITE);

        JPanel panel = new JPanel(new BorderLayout(5,5));

        // -------------top panel: Tìm kiếm + Bộ lọc
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        FormSearchNV searchPanel = new FormSearchNV();
        FormFilterNV filterPanel = new FormFilterNV();
        topPanel.add(searchPanel);
        topPanel.add(filterPanel);

        // -------------Detail và Table
        DetailPanelNV detailPanel = new DetailPanelNV();
        EmployeeTablePanel employeeTablePanel = new EmployeeTablePanel(conn, detailPanel);
        
        detailPanel.setTablePanel(employeeTablePanel);
        
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(employeeTablePanel, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
        add(detailPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection(); // đảm bảo bạn đã có class này

            JFrame frame = new JFrame("Quản lý Nhân viên");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            frame.add(new FormMainNhanVien(conn));
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối CSDL!");
        }
    }
}
