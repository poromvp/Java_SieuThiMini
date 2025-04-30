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
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        PanelTomTat panelTomTat = new PanelTomTat();
        panelTomTat.setPreferredSize(new Dimension(1000, 150)); // Tăng chiều rộng
        pn1.add(panelTomTat, gbc);

        gbc.gridy = 1;
        PanelChart panelChart = new PanelChart(panelTomTat);
        panelChart.setPreferredSize(new Dimension(1000, 550));
        panelChart.setMinimumSize(new Dimension(600, 550));
        pn1.add(panelChart, gbc);

        JScrollPane scr = new JScrollPane(pn1);
        scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scr.getVerticalScrollBar().setUnitIncrement(20);
        add(scr, BorderLayout.CENTER);
    }
}