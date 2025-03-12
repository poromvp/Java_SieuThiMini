package com.sieuthi.NhanVien_BaoCaoBanHang;

import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class PanelTable extends JPanel {
    public PanelTable(JTable tableHoaDon) {
        setBackground(new Color(54, 60, 61));

        tableHoaDon.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16)); // Đổi font thành Arial, cỡ 14, không
                                                                                // đậm
        tableHoaDon.setFont(new Font("Arial", Font.PLAIN, 13)); // Đổi font thành Arial, cỡ 14, không đậm

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); // căn giữa
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tableHoaDon.getColumnCount(); i++) {
            tableHoaDon.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tableHoaDon);
        scrollPane.setPreferredSize(new Dimension(1200, 500));
        add(scrollPane);
    }
}
