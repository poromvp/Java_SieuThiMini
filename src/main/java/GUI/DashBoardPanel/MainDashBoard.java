package GUI.DashBoardPanel;

import javax.imageio.ImageIO;
import javax.swing.*;

import GUI.ComponentCommon.TienIch;

import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;

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
        panelTomTat.setPreferredSize(new Dimension(600, 150));
        pn1.add(panelTomTat, gbc);

        gbc.gridy = 1;
        PanelChart panelChart = new PanelChart(panelTomTat); // Truyền panelTomTat
        panelChart.setPreferredSize(new Dimension(600, 550));
        panelChart.setMinimumSize(new Dimension(600, 550));
        pn1.add(panelChart, gbc);

        /*
         * gbc.gridy = 2;
         * PanelMuaNhieu muaNhieu = new PanelMuaNhieu();
         * muaNhieu.setPreferredSize(new Dimension(600, 550));
         * pn1.add(muaNhieu, gbc);
         */

        JButton btnCapture = new JButton("Chụp");
        btnCapture.addActionListener(e -> {
            BufferedImage image = TienIch.captureComponent(pn1);
            try {
                System.out.println("Đã chụp");
                ImageIO.write(image, "pdf", new File("screenshot.pdf"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        gbc.gridy = 2;
        pn1.add(btnCapture,gbc);

        JScrollPane scr = new JScrollPane(pn1);
        scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scr.getVerticalScrollBar().setUnitIncrement(20);
        add(scr, BorderLayout.CENTER);

    }
}