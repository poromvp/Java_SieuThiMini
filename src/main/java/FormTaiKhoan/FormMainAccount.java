package FormTaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.ComponentCommon.ButtonCustom;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormMainAccount extends JPanel {
    private JButton addButton, editButton, delButton;
    private FormTableAccount tablePanel;

    public FormMainAccount() {
        setLayout(new BorderLayout());

        FormSearchAccount searchPanel = new FormSearchAccount();
        tablePanel = new FormTableAccount();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton = new ButtonCustom("Thêm","add",12,50,50);
        editButton = new ButtonCustom("Sửa","edit",12,50,50);
        delButton = new ButtonCustom("Xóa","del",12,50,50);

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(delButton);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormAddAccount(tablePanel.getTableModel());
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablePanel.getAccountTable().getSelectedRow();
                if (selectedRow != -1) {
                    new FormEditAccount(tablePanel.getTableModel(), selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test FormAccount");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.add(new FormMainAccount());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
