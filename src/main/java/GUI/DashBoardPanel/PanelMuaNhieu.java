package GUI.DashBoardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import GUI.ComponentCommon.*;

public class PanelMuaNhieu extends JPanel {
    JPanel pn1, pn2, pn3, pn4, pn5;
    String[] lb1 = new String[4];
    String[] lb2 = new String[4];
    String[] lb3 = new String[4];
    String[] lb4 = new String[4];
    String[] lb5 = new String[4];
    String[] lb = { "ID: ", "SĐT: ", "Tổng Đơn Hàng: ", "Tổng Tiền: " };

    public JPanel initPanel(String id, String sdt, String totalOrders, String totalMoney) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new CompoundBorder(new TitledBorder(" "), new EmptyBorder(4, 4, 4, 4)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        JLabel lbHinh = new JLabel();
        TienIch.labelStyle(lbHinh, 0, 15, "boy.png");
        
        panel.add(lbHinh, gbc);
        gbc.gridheight = 1;

        for (int i = 0; i < lb.length; i++) {
            gbc.gridx = i + 1;
            gbc.gridy = 0;
            panel.add(new JLabel(lb[i]), gbc);
        }

        gbc.gridy = 1;
        panel.add(new JLabel(id), setGbc(gbc, 1));
        panel.add(new JLabel(sdt), setGbc(gbc, 2));
        panel.add(new JLabel(totalOrders), setGbc(gbc, 3));
        panel.add(new JLabel(totalMoney + " VND"), setGbc(gbc, 4));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Đã click chuột");
            }
        });

        return panel;
    }

    private GridBagConstraints setGbc(GridBagConstraints gbc, int gridx) {
        gbc.gridx = gridx;
        return gbc;
    }

    public PanelMuaNhieu() {
        setDoubleBuffered(true); // Bật double buffering
        setBorder(new CompoundBorder(new TitledBorder("Top 5 khách hàng mua nhiều nhất"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(30, 144, 255));

        pn1 = new JPanel();
        pn1 = initPanel("1", "0977723621", "23", "1,000,000");
        add(pn1);
        
        pn2 = new JPanel();
        pn2 = initPanel("2", "0971234567", "45", "2,500,000");
        add(pn2);
        
        pn3 = new JPanel();
        pn3 = initPanel("3", "0987654321", "12", "750,000");
        add(pn3);
        
        pn4 = new JPanel();
        pn4 = initPanel("4", "0961122334", "34", "3,000,000");
        add(pn4);
        
        pn5 = new JPanel();
        pn5 = initPanel("5", "0955566778", "56", "5,000,000");
        add(pn5);
    }
}
