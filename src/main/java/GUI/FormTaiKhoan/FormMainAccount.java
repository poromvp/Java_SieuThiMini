package GUI.FormTaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.TaiKhoanBLL;
import GUI.ComponentCommon.ButtonCustom;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormMainAccount extends JPanel {
    private JButton addButton, editButton, delButton;
    private FormTableAccount tablePanel;

    public FormMainAccount() {
        setLayout(new BorderLayout());

        tablePanel = new FormTableAccount();
        FormSearchAccount searchPanel = new FormSearchAccount(tablePanel);

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
                new FormAddAccount(new JFrame(), tablePanel); 

            }
        });
        
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablePanel.getAccountTable().getSelectedRow();
                if (selectedRow != -1) {
                    new FormEditAccount(new JFrame(), tablePanel.getTableModel(), selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablePanel.getAccountTable().getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = tablePanel.getTableModel();
                    int maNV = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        
                    int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Bạn có chắc muốn xóa tài khoản của Mã NV: " + maNV + "?",
                        "Xác nhận xóa",
                        JOptionPane.YES_NO_OPTION
                    );
        
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (maNV <= 0) {
                            JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return ;
                        }
                        TaiKhoanBLL bll = new TaiKhoanBLL();
                        if (bll.deleteTaiKhoan(maNV)) {
                            model.removeRow(selectedRow);
                            JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Xóa tài khoản thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
