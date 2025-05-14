package GUI.FormTaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.TaiKhoanBLL;
import GUI.ComponentCommon.ButtonCustom;
import GUI.FormNhanVien.FormExport;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormMainAccount extends JPanel {
    private JButton addButton, editButton, delButton, expButton;
    private FormTableAccount tablePanel;

    public FormMainAccount() {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        tablePanel = new FormTableAccount();
        FormSearchAccount searchPanel = new FormSearchAccount(tablePanel);
        JPanel filterPanel = createFilterPanel();

        searchPanel.add(filterPanel,BorderLayout.EAST);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        addButton = new ButtonCustom("Thêm","add",12,50,50);
        editButton = new ButtonCustom("Sửa","edit",12,50,50);
        delButton = new ButtonCustom("Xóa","del",12,50,50);
        expButton = new ButtonCustom("Xuất", "printer", 12,50,50);

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(delButton);
        buttonPanel.add(expButton);

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
        expButton.addActionListener(e -> FormExport("DANH SÁCH TÀI KHOẢN"));
        
    }
     private void FormExport(String title) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Export", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        FormExport formEx = new FormExport(title);
        dialog.add(formEx);
        dialog.setVisible(true);
    }
    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout());

        JLabel filterLabel = new JLabel("Lọc theo:");
        JComboBox<String> filterOptions = new JComboBox<>(new String[]{"Tất cả", "ACTIVE", "INACTIVE"});
        JComboBox<String> roleOptions = new JComboBox<>(new String[]{"Tất cả", "ADMIN", "NHÂN VIÊN","QUẢN LÝ KHO"});
        ButtonCustom btnCancel = new ButtonCustom("Hủy", 12, "red");
        ButtonCustom filterButton = new ButtonCustom("Lọc", 12, "green");
        filterPanel.add(filterLabel);
        filterPanel.add(filterOptions);
        filterPanel.add(roleOptions);
        filterPanel.add(filterButton);
        filterPanel.add(btnCancel);

        filterButton.addActionListener(e -> {
            String selectedFilter = (String) filterOptions.getSelectedItem();
            String selectedRole = (String) roleOptions.getSelectedItem();
            
            tablePanel.filterData(selectedFilter, selectedRole);
        });
        btnCancel.addActionListener(e -> {
            filterOptions.setSelectedIndex(0); 
            roleOptions.setSelectedIndex(0);
            tablePanel.refreshTable();
        });
        return filterPanel;
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
