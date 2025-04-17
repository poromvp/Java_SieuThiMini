package GUI.DashBoardPanel;

import javax.swing.*;
import java.awt.*;

public class MainDashBoard extends JPanel {
    public MainDashBoard() {
        setLayout(new BorderLayout());
        JPanel pn1 = new JPanel();
        pn1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Chỉ kéo giãn theo chiều ngang
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        PanelTomTat panelTomTat = new PanelTomTat();
        panelTomTat.setPreferredSize(new Dimension(600, 150));
        pn1.add(panelTomTat, gbc);
        
        gbc.gridy = 1;
        PanelChart panelChart = new PanelChart();
        panelChart.setPreferredSize(new Dimension(600, 550));
        pn1.add(panelChart, gbc);
        
        /* 
        gbc.gridy = 2;
        PanelMuaNhieu panelMuaNhieu = new PanelMuaNhieu();
        panelMuaNhieu.setPreferredSize(new Dimension(600, 500));
        pn1.add(panelMuaNhieu, gbc);*/
        
        JScrollPane scr = new JScrollPane(pn1);
        scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scr.getVerticalScrollBar().setUnitIncrement(20);
        add(scr,BorderLayout.CENTER);
    }
}
