package com.FormWareHouse;

import com.ComponentCommon.StyledTextField;
import com.ComponentCommon.StyledTable;

import javax.swing.*;
import java.awt.*;
import java.text.Normalizer;

public class FormImport  extends JPanel {
    FormImportDetail detail = new FormImportDetail();
    public FormImport(){
        setLayout(new BorderLayout());
        setBackground(Color.white);

//        // Title
//        JLabel title = new JLabel("Quản lý kho");
//        title.setFont(new Font("Arial", Font.BOLD, 24));
//        add(title,BorderLayout.NORTH);

        // Nhập thông tin của mã đơn hàng nhập vào
        JPanel nhapPnl = new JPanel();
        nhapPnl.setLayout(new GridLayout(4, 2, 10, 10));
        nhapPnl.setBorder(BorderFactory.createTitledBorder("Thông tin đơn hàng nhập vào"));

        JLabel lb1 = new JLabel("Mã đơn hàng");
        StyledTextField t1 = new StyledTextField();
        JLabel lb2 = new JLabel("Mã nhân viên");
        StyledTextField t2 = new StyledTextField();
        JLabel lb3 = new JLabel("Ngày nhập");
        StyledTextField t3 = new StyledTextField();
        JButton btn1 = new JButton("Thêm");
        JPanel panelNhap = new JPanel();
        panelNhap.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelNhap.add(btn1);

        nhapPnl.add(lb1);
        nhapPnl.add(t1);
        nhapPnl.add(lb2);
        nhapPnl.add(t2);
        nhapPnl.add(lb3);
        nhapPnl.add(t3);
        nhapPnl.add(panelNhap,BorderLayout.NORTH);

        add(nhapPnl,BorderLayout.NORTH);

        // Tạo bảng
        String[] headerCol = {"Mã đơn nhập hàng", "Thời gian", "Nhân viên thực hiện"};
        Object[][] data = {
                {1, "13/03/2025", "Nguyễn Văn A"},
                {2, "13/03/2025",  "Nguyễn Văn A"},
                {3, "13/03/2025",  "Nguyễn Văn A"},
                {4, "13/03/2025",  "Nguyễn Văn A"},
                {5, "13/03/2025",  "Nguyễn Văn A"}
        };

        JTable table = new StyledTable(data, headerCol);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane,BorderLayout.CENTER);

        // Xử lý sự kiện nút thêm
        btn1.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có muốn thêm đơn hàng không?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                //Tao dialog
                JDialog info_import = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Nhập thông tin", true);
                info_import.setLayout(new BorderLayout());
                info_import.setSize(400,300);
                info_import.setLocationRelativeTo(null);

                // Panel thông tin
                JPanel thongTinPnl = new JPanel();
                thongTinPnl.setBorder(BorderFactory.createTitledBorder("Thông Tin"));
                thongTinPnl.setLayout(new GridLayout(6, 2, 5, 5));

                thongTinPnl.add(new JLabel("Mã sản phẩm: "));
                StyledTextField maSP = new StyledTextField();
                thongTinPnl.add(maSP);

                thongTinPnl.add(new JLabel("Số lượng:"));
                StyledTextField soLuong = new StyledTextField();
                thongTinPnl.add(soLuong);

                thongTinPnl.add(new JLabel("Giá:"));
                StyledTextField gia = new StyledTextField();
                thongTinPnl.add(gia);

                thongTinPnl.add(new JLabel("Nhà cung cấp:"));
                StyledTextField ngaySX = new StyledTextField();
                thongTinPnl.add(ngaySX);

                thongTinPnl.add(new JLabel("Lô hàng:"));
                StyledTextField ngayHH = new StyledTextField();
                thongTinPnl.add(ngayHH);

                JButton btnXacNhan = new JButton("Xác nhận");
                JButton btnHuy = new JButton("Hủy");

                JPanel panelBtn = new JPanel();
                panelBtn.setLayout(new FlowLayout(FlowLayout.CENTER));
                panelBtn.add(btnXacNhan);
                panelBtn.add(btnHuy);

                info_import.add(thongTinPnl,BorderLayout.CENTER);
                info_import.add(panelBtn, BorderLayout.SOUTH);
                info_import.setVisible(true);

            }
        });
        JButton chiTietBtn = new JButton("Xem chi tiết");
        JPanel chiTietBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        chiTietBtn.setSize(100,50);
        chiTietBtnPanel.add(chiTietBtn);
        add(chiTietBtnPanel,BorderLayout.SOUTH);
        chiTietBtn.addActionListener(e -> {
            JDialog import_detail = new JDialog();
            import_detail.setSize(700,400);
            import_detail.setLayout(new BorderLayout());
            import_detail.setLocationRelativeTo(null);
            import_detail.add(detail,BorderLayout.CENTER);
            import_detail.setVisible(true);
        });
    }
}

