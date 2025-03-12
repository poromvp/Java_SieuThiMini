package com.sieuthi.Admin_PanelThongKe;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class PanelChiPhi extends JPanel{
    public PanelChiPhi(){
        setBorder(new CompoundBorder(new TitledBorder("Thu Chi"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Tổng Thu: "),gbc);

    }
}
