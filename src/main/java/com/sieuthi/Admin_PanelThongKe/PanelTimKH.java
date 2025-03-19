package com.sieuthi.Admin_PanelThongKe;

import javax.swing.*;

public class PanelTimKH extends JPanel{
    JTextField txtName, txtAge, txtEmail;

    public PanelTimKH() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Sắp xếp dọc
        txtName = new JTextField(10);
        txtAge = new JTextField(10);
        txtEmail = new JTextField(10);

        add(new JLabel("Họ và tên:"));
        add(txtName);
        add(new JLabel("Tuổi:"));
        add(txtAge);
        add(new JLabel("Email:"));
        add(txtEmail);
    }

    public String getTxtName() {
        return txtName.getText();
    }
}
