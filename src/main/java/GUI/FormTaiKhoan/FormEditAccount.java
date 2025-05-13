package GUI.FormTaiKhoan;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoanDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTextField;
import JDBC.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FormEditAccount extends JDialog {
    private StyledTextField maNVField;
    private StyledTextField tenTKField;
    private StyledTextField matKhauField;
    private StyledTextField gmailField;
    private JComboBox<String> cbQuyen;
    private JComboBox<String> cbTrangThai;
    private ButtonCustom saveButton;
    private DefaultTableModel tableModel;
    private int selectedRow;

    public FormEditAccount(Frame parent, DefaultTableModel tableModel, int row) {
        super(parent, "Sửa Tài Khoản", true);
        this.tableModel = tableModel;
        this.selectedRow = row;

        setSize(350, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        maNVField = new StyledTextField();
        maNVField.setEnabled(false);
        tenTKField = new StyledTextField();
        matKhauField = new StyledTextField();
        gmailField = new StyledTextField();

        String[] quyenList = {"ADMIN", "QUẢN LÝ KHO", "NHÂN VIÊN"};
        cbQuyen = new JComboBox<>(quyenList);

        String[] trangThaiOptions = {"Đang hoạt động", "Ngưng hoạt động"};
        cbTrangThai = new JComboBox<>(trangThaiOptions);

        if (selectedRow != -1) {
            maNVField.setText(tableModel.getValueAt(row, 0).toString());
            tenTKField.setText(tableModel.getValueAt(row, 1).toString());
            matKhauField.setText(tableModel.getValueAt(row, 2).toString());
            cbQuyen.setSelectedItem(tableModel.getValueAt(row, 3).toString());
            gmailField.setText(tableModel.getValueAt(row, 4).toString());
            String trangThai = tableModel.getValueAt(row, 5).toString();
            cbTrangThai.setSelectedItem(trangThai);
        }

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

        saveButton = new ButtonCustom("Lưu thay đổi", 20, "blue");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (selectedRow != -1) {
                        TaiKhoanDTO tk = new TaiKhoanDTO();
                        tk.setMaNV(Integer.parseInt(maNVField.getText().trim()));
                        tk.setTenTK(tenTKField.getText().trim());
                        tk.setMatKhau(matKhauField.getText().trim());
                        tk.setQuyen(cbQuyen.getSelectedItem().toString());
                        tk.setGmail(gmailField.getText().trim());
                        tk.setTrangThai(cbTrangThai.getSelectedItem().equals("Đang hoạt động") ? "ACTIVE" : "INACTIVE");

                        TaiKhoanBLL bll = new TaiKhoanBLL();
                                      
                        if (!FormAddAccount.isValidData(tk)) {
                            return ;
                        }
                        if (bll.updateTaiKhoan(tk)) {
                            // Cập nhật bảng
                            tableModel.setValueAt(tk.getMaNV(), selectedRow, 0);
                            tableModel.setValueAt(tk.getTenTK(), selectedRow, 1);
                            tableModel.setValueAt(tk.getMatKhau(), selectedRow, 2);
                            tableModel.setValueAt(tk.getQuyen(), selectedRow, 3);
                            tableModel.setValueAt(tk.getGmail(), selectedRow, 4);
                            tableModel.setValueAt(tk.getTrangThai().equals("ACTIVE") ? "Đang hoạt động" : "Ngưng hoạt động", selectedRow, 5);
                            JOptionPane.showMessageDialog(FormEditAccount.this, "Cập nhật tài khoản thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FormEditAccount.this, "Mã nhân viên phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Mã NV", "Tài khoản", "Mật khẩu", "Quyền", "Gmail", "Trạng thái"}, 0);
        model.addRow(new Object[]{"1", "userA", "1234", "ADMIN", "userA@example.com", "Đang hoạt động"});
        new FormEditAccount(parent, model, 0);
    }
}