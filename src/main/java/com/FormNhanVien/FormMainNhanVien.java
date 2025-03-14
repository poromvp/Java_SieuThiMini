package com.FormNhanVien;

import javax.swing.*;
import java.awt.*;

public class FormMainNhanVien extends JPanel {
    public FormMainNhanVien() {
        setSize(800, 600);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        // Tạo các panel
        FormSearchNV searchPanel = new FormSearchNV();
        DetailPanelNV detailPanel = new DetailPanelNV();
        EmployeeTablePanel employeeTablePanel = new EmployeeTablePanel();

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(employeeTablePanel, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
        add(detailPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new FormMainNhanVien());
        frame.setSize(800, 600); 
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}