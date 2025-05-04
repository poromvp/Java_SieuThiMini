package GUI.DashBoardPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MainDashBoard extends JPanel implements ActionListener {
    JPopupMenu popupMenu;
    JMenuItem exportItem;

    public void showpupop(Object obj) {
        if (obj instanceof JPanel pn) {
            pn.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                private void showPopup(MouseEvent e) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });
        } else if (obj instanceof JScrollPane scr) {
            scr.addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                private void showPopup(MouseEvent e) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }

            });
        }
    }

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

        // Thêm popup menu
        popupMenu = new JPopupMenu();
        exportItem = new JMenuItem("Xuất file");
        exportItem.addActionListener(this);
        popupMenu.add(exportItem);
        // Thêm sự kiện chuột phải
        showpupop(panelTomTat);
        showpupop(scr);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exportItem) {
            JFileChooser fileChooser = new JFileChooser("src/main/resources/file/export");
            fileChooser.setDialogTitle("Lưu Báo Cáo");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setSelectedFile(new File("BaoCaoDoanhThu.pdf"));
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                JScrollPane scrollPane = (JScrollPane) this.getComponent(0);
                JPanel pn1 = (JPanel) scrollPane.getViewport().getView();
                PanelChart panelChart = (PanelChart) pn1.getComponent(1);
                vechart chart = (vechart) ((JScrollPane) panelChart.getComponent(0)).getViewport().getView();
                PDFExporter.exportChartToPDFWithDialog(panelChart, chart.timeFilter, chart.selectedMonth,
                        chart.selectedYear, "MANV_DEFAULT"); // Thay MANV_DEFAULT bằng mã nhân viên thực tế
            }
        }
    }
}