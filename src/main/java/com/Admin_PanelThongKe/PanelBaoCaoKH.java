package com.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.TienIch;

public class PanelBaoCaoKH extends JPanel implements ChangeListener, ActionListener{
    JButton btnTim;
    JLabel lbMota;
    JTabbedPane tab;
    JTable tb;
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<hoadontemp> HoaDon = new ArrayList<>();
    JPanel pn1, pn2, pn3, pn4;

    public PanelBaoCaoKH(){
        setBorder(new CompoundBorder(new TitledBorder("Báo cáo khách hàng"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        btnTim = new JButton("Tìm");
        TienIch.nutStyle(btnTim, "search.png", 30, 150, 60);
        add(btnTim, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        lbMota = new JLabel("*Standard: dành cho thành viên chi tiêu dưới 1,000,000 VND.");
        TienIch.labelStyle(lbMota, 2, 15, null);
        add(lbMota, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;

        String[] tencot = { "ID", "Name", "Price", "Date" };
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
        TableControl.TableEvent(tb, model, "KH");
        scr = new JScrollPane(tb);
        
        pn1 = new JPanel();
        pn1.setLayout(new BorderLayout());
        pn1.add(scr,BorderLayout.CENTER);

        pn2 = new JPanel();
        pn2.setLayout(new BorderLayout());

        pn3 = new JPanel();
        pn3.setLayout(new BorderLayout());

        pn4 = new JPanel();
        pn4.setLayout(new BorderLayout());


        tab = new JTabbedPane();
        tab.addTab("Standard",pn1);
        tab.addTab("Tiềm năng",pn2);
        tab.addTab("VIP",pn3);
        tab.addTab("Diamond",pn4);
        add(tab,gbc);

        tab.addChangeListener((ChangeListener)this);
        btnTim.addActionListener((ActionListener)this);
    }

    private void refreshTable() {
        model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (hoadontemp s : HoaDon) {
            model.addRow(new Object[] { s.getId(), s.getName(), s.getPrice(), s.getDate() });
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() ==  btnTim){
            PanelTimKH panel = new PanelTimKH();
            // Hiển thị JOptionPane
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            /*
             * giá trị của biến result sẽ là:
             * JOptionPane.OK_OPTION (0) nếu bạn nhấn OK.
             * JOptionPane.CANCEL_OPTION (2) nếu bạn nhấn Cancel.
             * JOptionPane.CLOSED_OPTION (-1) nếu bạn đóng hộp thoại bằng dấu X (góc trên
             * phải).
             */
            if (result == 0) {
                System.out.println("Bạn vừa nhập: " + panel.getTxtName());
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e){
        int tabSelected = tab.getSelectedIndex();
        if(tabSelected == 0){
            lbMota.setText("*Standard: dành cho thành viên chi tiêu dưới 1,000,000 VND.");
            pn1.add(scr,BorderLayout.CENTER);
        }
        
        if(tabSelected == 1){
            lbMota.setText("*Tiềm năng: dành cho thành viên chi tiêu từ 1,000,000 - 3,000,000 VND.");
            pn2.add(scr,BorderLayout.CENTER);
        }

        if(tabSelected == 2){
            lbMota.setText("*VIP: dành cho thành viên chi tiêu trên 3,000,000 VND.");
            pn3.add(scr,BorderLayout.CENTER);
        }

        if(tabSelected == 3){
            lbMota.setText("*Kim cương: dành cho thành viên chi tiêu trên 10,000,000 VND.");
            pn4.add(scr,BorderLayout.CENTER);
        }
    }
}
