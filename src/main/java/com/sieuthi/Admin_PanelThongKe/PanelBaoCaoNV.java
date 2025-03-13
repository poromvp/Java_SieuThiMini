package com.sieuthi.Admin_PanelThongKe;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import com.sieuthi.TienIch;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelBaoCaoNV extends JPanel implements ActionListener{
    JButton btnTim, btnXem, btnDS;
    JTable tb;
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<hoadontemp> HoaDon = new ArrayList<>();

    public PanelBaoCaoNV() {
        setBorder(new CompoundBorder(new TitledBorder("Báo cáo nhân viên"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        btnTim = new JButton("Tìm");
        TienIch.nutStyle(btnTim);
        add(btnTim, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        btnXem = new JButton("Xem");
        TienIch.nutStyle(btnXem);
        add(btnXem, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        btnDS = new JButton("Danh sách nhân viên có doanh số tốt nhất");
        TienIch.nutStyle(btnDS);
        add(btnDS, gbc);

        String[] tencot = { "ID", "Name", "Price", "Date" };
        hoadontemp a = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        hoadontemp b = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        hoadontemp c = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        hoadontemp d = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        HoaDon.add(a);
        HoaDon.add(b);
        HoaDon.add(c);
        HoaDon.add(d);
        model = new DefaultTableModel(tencot, 0);
        refreshTable();
        tb = new JTable(model);
        scr = new JScrollPane(tb);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add(scr, gbc);

        btnTim.addActionListener((ActionListener)this);
        btnXem.addActionListener((ActionListener)this);
        btnDS.addActionListener((ActionListener)this);
    }

    private void refreshTable() {
        model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (hoadontemp s : HoaDon) {
            model.addRow(new Object[] { s.getId(), s.getName(), s.getPrice(), s.getDate() });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnTim){
            PanelTim panel = new PanelTim();
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

        if(e.getSource() == btnXem){
            int dong = tb.getSelectedRow();
            if (dong == -1) { // Nếu không có dòng nào được chọn
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xem chi tiết!", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
                return; // Thoát khỏi phương thức
                /*
                 * Dòng return; có tác dụng kết thúc phương thức ngay lập tức, tránh việc thực
                 * hiện tiếp các dòng code bên dưới nếu người dùng chưa chọn dòng nào.
                 */
            }

            PanelXem panel = new PanelXem(model, dong);

            // Hiển thị JOptionPane với panel tự thiết kế
            JOptionPane.showMessageDialog(null, panel, "Xem Chi Tiết", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource() == btnDS){
            PanelTotNhat panel = new PanelTotNhat(scr);
            JOptionPane.showMessageDialog(null, panel, "Xem Danh Sách", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
