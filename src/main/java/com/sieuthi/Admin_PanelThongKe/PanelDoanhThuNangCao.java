package com.sieuthi.Admin_PanelThongKe;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;

public class PanelDoanhThuNangCao extends JPanel{
    public PanelDoanhThuNangCao(JComboBox<String> characts, JTable tb){
        setBorder(new CompoundBorder(new TitledBorder("Doanh Thu Theo"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx=0;
        gbc.gridy=0;
        JPanel pn1 = new JPanel();
        add(pn1,gbc);

        pn1.add(characts);

        gbc.gridx=0;
        gbc.gridy=1;
        JPanel pn2 = new JPanel();
        add(pn2,gbc);

        tb.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16)); // Đổi font thành Arial, cỡ 14, không
                                                                                // đậm
        tb.setFont(new Font("Arial", Font.PLAIN, 13)); // Đổi font thành Arial, cỡ 14, không đậm

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); // căn giữa
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tb.getColumnCount(); i++) {
            tb.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(tb);
        pn2.add(scrollPane);
    }
}
