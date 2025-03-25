package com.FormTaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.ComponentCommon.StyledTextField;
import com.ComponentCommon.ButtonCustom;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormAddAccount extends JFrame {
    private StyledTextField maTKField;
    private StyledTextField tenTKField;
    private StyledTextField matKhauTKField;
    private ButtonCustom saveButton;

    public FormAddAccount(DefaultTableModel tableModel) {
        setTitle("Thêm Tài Khoản");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        maTKField = new StyledTextField();
        tenTKField = new StyledTextField();
        matKhauTKField = new StyledTextField();

        String TrangThai[] = {"Đang hoạt động","Ngưng hoạt động"};
        JComboBox cbTrangThai = new JComboBox<>(TrangThai);
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.add(new JLabel("Mã nhân viên:"));
        inputPanel.add(maTKField);
        inputPanel.add(new JLabel("Tài khoản:"));
        inputPanel.add(tenTKField);
        inputPanel.add(new JLabel("Mật khẩu:"));
        inputPanel.add(matKhauTKField);
        inputPanel.add(new JLabel("Trạng thái:"));
        inputPanel.add(cbTrangThai);

        saveButton = new ButtonCustom("Thêm tài khoản",20,"blue");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maNV = maTKField.getText();
                String user = tenTKField.getText();
                String psw = matKhauTKField.getText();

                if (maNV.isEmpty() || user.isEmpty() || psw.isEmpty()) {
                    JOptionPane.showMessageDialog(FormAddAccount.this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                tableModel.addRow(new Object[]{maNV, user, psw});
                dispose(); // Đóng cửa sổ sau khi thêm
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FormAddAccount(new DefaultTableModel(new Object[]{"Mã NV", "Tài khoản", "Mật khẩu"}, 0));
    }
}
