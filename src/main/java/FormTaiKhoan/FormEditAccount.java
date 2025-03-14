package FormTaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.ComponentCommon.ButtonCustom;
import com.ComponentCommon.StyledTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormEditAccount extends JFrame {
    private StyledTextField maTKField;
    private StyledTextField tenTKField;
    private StyledTextField matKhauTKField;
    private ButtonCustom saveButton;
    private int selectedRow;
    private DefaultTableModel tableModel;

    public FormEditAccount(DefaultTableModel tableModel, int row) {
        setTitle("Sửa Tài Khoản");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.tableModel = tableModel;
        this.selectedRow = row;

        maTKField = new StyledTextField();
        tenTKField = new StyledTextField();
        matKhauTKField = new StyledTextField();
        
        if (selectedRow != -1) {
            maTKField.setText(tableModel.getValueAt(row, 0).toString());
            tenTKField.setText(tableModel.getValueAt(row, 1).toString());
            matKhauTKField.setText(tableModel.getValueAt(row, 2).toString());
        }
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

        saveButton = new ButtonCustom("Lưu thay đổi",20);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    tableModel.setValueAt(maTKField.getText(), selectedRow, 0);
                    tableModel.setValueAt(tenTKField.getText(), selectedRow, 1);
                    tableModel.setValueAt(matKhauTKField.getText(), selectedRow, 2);
                    dispose();
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
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Mã NV", "Tài khoản", "Mật khẩu"}, 0);
        model.addRow(new Object[]{"1", "userA", "1234"});
        new FormEditAccount(model, 0);
    }
}
