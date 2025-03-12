package com.sieuthi.Admin_PanelThongKe;

import java.awt.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import com.toedter.calendar.JDateChooser;

public class PanelChonNgay extends JPanel{
    public PanelChonNgay(JDateChooser to, JDateChooser from, JComboBox<String> chonThoiGian){
        setBorder(new CompoundBorder(new TitledBorder("Mốc Thời Gian"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Từ: "),gbc);
        from.setDate(new Date());
        from.setDateFormatString("dd/MM/yyyy");
        from.getDateEditor().setEnabled(false);
        from.setMaxSelectableDate(new Date());

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(from,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Đến: "),gbc);
        to.setDate(new Date());
        to.setDateFormatString("dd/MM/yyyy");
        to.getDateEditor().setEnabled(false);
        to.setMaxSelectableDate(new Date());

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(to,gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        add(new JLabel("       Hoặc"),gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        add(chonThoiGian,gbc);
    }
}
