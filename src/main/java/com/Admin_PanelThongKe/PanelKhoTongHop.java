package com.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import com.TienIch;

public class PanelKhoTongHop extends JPanel implements ChangeListener, ActionListener{
    JPanel pn1, pn2, pn3;
    JScrollPane scr;
    JLabel tongdonhang;
    JButton btnMore;
    JTabbedPane tab;
    JTable tb;
    DefaultTableModel model;
    public ArrayList<hoadontemp> HoaDon = new ArrayList<>();

    public PanelKhoTongHop() {
        setBorder(new CompoundBorder(new TitledBorder("Báo cáo kho tổng hợp"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel tongdh = new JLabel("<html><center>Tổng số hàng<br>nhập trong tháng:<center></html>");
        TienIch.labelStyle(tongdh, 1, 20, null);
        add(tongdh, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        tongdonhang = new JLabel("555");
        TienIch.labelStyle(tongdonhang, 2, 20, null);
        add(tongdonhang, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        btnMore = new JButton("<html><center>Xem danh sách<br>lô hàng đã nhập<center></html>");
        TienIch.nutStyle(btnMore, "list.png", 20, 40, 20);
        add(btnMore, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        String[] tencot = {"ID", "Tên Sản Phẩm", "Đơn Giá", "Số Lượng"};
        hoadontemp a = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        hoadontemp b = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        hoadontemp c = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        hoadontemp d = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        HoaDon.add(a);
        HoaDon.add(b);
        HoaDon.add(c);
        HoaDon.add(d);
        model = new DefaultTableModel(tencot, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        refreshTable();
        tb = new JTable(model);
        TableControl.TableStyle(tb, model);
        scr = new JScrollPane(tb);

        pn1 = new JPanel();
        pn1.setLayout(new BorderLayout());
        pn1.add(scr,BorderLayout.CENTER);

        pn2 = new JPanel();
        pn2.setLayout(new BorderLayout());

        pn3 = new JPanel();
        pn3.setLayout(new BorderLayout());


        tab = new JTabbedPane();
        tab.addTab("Danh sách bán chạy",pn1);
        tab.addTab("Danh sách tồn nhiều",pn2);
        tab.addTab("Danh sách hàng sắp hết",pn3);
        add(tab,gbc);

        tab.addChangeListener((ChangeListener)this);
        btnMore.addActionListener((ActionListener)this);
    }

    private void refreshTable() {
        model.setRowCount(0);  // Xóa toàn bộ dữ liệu cũ
        for (hoadontemp s : HoaDon) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getPrice(), s.getDate()});
        }
    }

    @Override
    public void stateChanged(ChangeEvent e){
        int tabSelected = tab.getSelectedIndex();
        if(tabSelected == 0){
            pn1.add(scr,BorderLayout.CENTER);
        }
        
        if(tabSelected == 1){
            pn2.add(scr,BorderLayout.CENTER);
        }

        if(tabSelected == 2){
            pn3.add(scr,BorderLayout.CENTER);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnMore){
            PanelSapHet panel = new PanelSapHet();
            JOptionPane.showMessageDialog(null, panel, "Xem Danh Sách", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
