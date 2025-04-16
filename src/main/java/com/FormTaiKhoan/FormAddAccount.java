package com.FormTaiKhoan;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoanDTO;
import com.ComponentCommon.StyledTextField;
import com.ComponentCommon.ButtonCustom;
import JDBC.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormAddAccount extends JDialog {
    private StyledTextField maNVField;
    private StyledTextField tenTKField;
    private StyledTextField matKhauField;
    private StyledTextField gmailField;
    private JComboBox<String> cbQuyen;
    private JComboBox<String> cbTrangThai;
    private ButtonCustom saveButton;
    private FormTableAccount tablePanel;

    public FormAddAccount(Frame parent, FormTableAccount tablePanel) {
        super(parent, "Thêm Tài Khoản", true); // Modal dialog
        this.tablePanel = tablePanel;
        setSize(350, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        maNVField = new StyledTextField();
        tenTKField = new StyledTextField();
        matKhauField = new StyledTextField();
        gmailField = new StyledTextField();

        String[] quyenList = {"ADMIN", "QUẢN LÝ KHO", "NHÂN VIÊN"};
        cbQuyen = new JComboBox<>(quyenList);

        String[] trangThaiOptions = {"Đang hoạt động", "Ngưng hoạt động"};
        cbTrangThai = new JComboBox<>(trangThaiOptions);

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("Mã nhân viên:"));
        inputPanel.add(maNVField);
        inputPanel.add(new JLabel("Tên tài khoản:"));
        inputPanel.add(tenTKField);
        inputPanel.add(new JLabel("Mật khẩu:"));
        inputPanel.add(matKhauField);
        inputPanel.add(new JLabel("Quyền:"));
        inputPanel.add(cbQuyen);
        inputPanel.add(new JLabel("Gmail:"));
        inputPanel.add(gmailField);
        inputPanel.add(new JLabel("Trạng thái:"));
        inputPanel.add(cbTrangThai);

        saveButton = new ButtonCustom("Thêm tài khoản", 20, "blue");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TaiKhoanDTO tk = new TaiKhoanDTO();
                    tk.setMaNV(Integer.parseInt(maNVField.getText().trim()));
                    tk.setTenTK(tenTKField.getText().trim());
                    tk.setMatKhau(matKhauField.getText().trim());
                    tk.setQuyen(cbQuyen.getSelectedItem().toString());
                    tk.setGmail(gmailField.getText().trim());
                    tk.setTrangThai(cbTrangThai.getSelectedItem().equals("Đang hoạt động") ? "ACTIVE" : "INACTIVE");

                    TaiKhoanBLL bll = new TaiKhoanBLL();
                    if (bll.addTaiKhoan(tk)) {
                        tablePanel.refreshTable();
                        JOptionPane.showMessageDialog(FormAddAccount.this, "Thêm tài khoản thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FormAddAccount.this, "Mã nhân viên phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame parent = new JFrame();
        FormTableAccount tablePanel = new FormTableAccount();
        new FormAddAccount(parent, tablePanel);
    }
}