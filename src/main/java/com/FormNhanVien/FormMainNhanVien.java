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

        // -------------top panel chung
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        FormSearchNV searchPanel = new FormSearchNV();
        FormFilterNV filterPanel = new FormFilterNV();
        topPanel.add(searchPanel);
        topPanel.add(filterPanel);

        DetailPanelNV detailPanel = new DetailPanelNV();
        EmployeeTablePanel employeeTablePanel = new EmployeeTablePanel();

        panel.add(topPanel, BorderLayout.NORTH);
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