package GUI.FormWareHouse;

import BLL.NhaCungCapBLL;
import DTO.NhaCungCapDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTable;
import GUI.ComponentCommon.StyledTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormSupplier extends JPanel implements ActionListener {
    private NhaCungCapBLL nhaCungCapBLL = new NhaCungCapBLL();
    private StyledTable table;
    private StyledTextField txtMaNCC, txtTenNCC, txtSdt, txtDiaChi;
    private JComboBox<String> cbTrangThai;
    private ButtonCustom btnAdd, btnEdit, btnDelete,btnReset;

    public FormSupplier() {
        setLayout(new BorderLayout());
        setBackground(Color.white);

        // Panel chứa bảng
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.white);
        tablePanel.setBorder(BorderFactory.createTitledBorder("Nhà cung cấp"));

        // Tạo bảng
        String[] columns = {"Mã NCC", "Tên NCC", "SĐT", "Địa chỉ", "Trạng thái"};
        table = new StyledTable(new Object[][]{}, columns);
        refreshTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        txtMaNCC.setForeground(Color.BLACK);
                        txtMaNCC.setText(table.getValueAt(selectedRow, 0).toString());
                        txtMaNCC.setEnabled(false);
                        txtTenNCC.setText(table.getValueAt(selectedRow, 1).toString());
                        txtSdt.setText(table.getValueAt(selectedRow, 2).toString());
                        txtDiaChi.setText(table.getValueAt(selectedRow, 3).toString());
                        cbTrangThai.setSelectedItem(table.getValueAt(selectedRow, 4).toString());
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.white);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        // Panel chứa form nhập liệu và nút
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(Color.white);
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form nhập liệu
        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.setBackground(Color.white);
        JPanel labelsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        labelsPanel.setBackground(Color.white);
        JPanel fieldsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        fieldsPanel.setBackground(Color.white);

        txtMaNCC = new StyledTextField(150, 30);
        txtMaNCC.setText("Mã nhà cung cấp sẽ được tạo tự động");
        txtMaNCC.setForeground(Color.GRAY);
        txtMaNCC.setEnabled(false); // Vô hiệu hóa trường MaNCC khi thêm mới
        txtTenNCC = new StyledTextField(150, 30);
        txtSdt = new StyledTextField(150, 30);
        txtDiaChi = new StyledTextField(150, 30);
        cbTrangThai = new JComboBox<>(new String[]{"Hoạt động", "Ngừng hoạt động"});
        cbTrangThai.setBackground(Color.white);

        labelsPanel.add(new JLabel("Mã NCC:"));
        labelsPanel.add(new JLabel("Tên NCC:"));
        labelsPanel.add(new JLabel("SĐT:"));
        labelsPanel.add(new JLabel("Địa chỉ:"));
        labelsPanel.add(new JLabel("Trạng thái:"));
        fieldsPanel.add(txtMaNCC);
        fieldsPanel.add(txtTenNCC);
        fieldsPanel.add(txtSdt);
        fieldsPanel.add(txtDiaChi);
        fieldsPanel.add(cbTrangThai);

        formPanel.add(labelsPanel, BorderLayout.WEST);
        formPanel.add(fieldsPanel, BorderLayout.CENTER);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.white);
        btnAdd = new ButtonCustom("Thêm", "add", 14, 20, 20);
        btnEdit = new ButtonCustom("Sửa", "edit", 14, 20, 20);
        btnDelete = new ButtonCustom("Xóa", "del", 14, 20, 20);
        btnReset = new ButtonCustom("Làm mới","reset",14,20,20);
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnReset.addActionListener(this);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnReset);

        northPanel.add(formPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);
    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (NhaCungCapDTO ncc : nhaCungCapBLL.getList()) {
            model.addRow(new Object[]{
                    ncc.getMaNCC(),
                    ncc.getTenNCC(),
                    ncc.getSdt(),
                    ncc.getDiaChi(),
                    ncc.getTrangThai().equals("ACTIVE") ? "Hoạt động" : "Ngừng hoạt động"
            });
        }
    }

    private void clearForm() {
        txtMaNCC.setText("Mã nhà cung cấp sẽ được tạo tự động");
        txtMaNCC.setForeground(Color.GRAY);
        txtMaNCC.setEnabled(false); // Vô hiệu hóa sau khi xóa form
        txtTenNCC.setText("");
        txtSdt.setText("");
        txtDiaChi.setText("");
        cbTrangThai.setSelectedIndex(0);
        table.clearSelection();
    }

    private boolean validateInput() {
        if (txtTenNCC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtSdt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!txtSdt.getText().trim().matches("0\\d{9}") ) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là 10 chữ số và bắt đầu bằng số 0 !", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            if (!validateInput()) {
                return;
            }
            String tenNCC = txtTenNCC.getText().trim();
            String sdt = txtSdt.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String trangThai = cbTrangThai.getSelectedItem().toString().equals("Hoạt động") ? "ACTIVE" : "INACTIVE";

            // MaNCC sẽ được tạo tự động bởi cơ sở dữ liệu
            NhaCungCapDTO ncc = new NhaCungCapDTO(0, tenNCC, sdt, diaChi, trangThai);
            if (nhaCungCapBLL.add(ncc)) {
                refreshTable();
                clearForm();
                JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thất bại! Vui lòng kiểm tra lại dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnEdit) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                if (!validateInput()) {
                    return;
                }
                try {
                    int maNCC = Integer.parseInt(txtMaNCC.getText().trim());
                    String tenNCC = txtTenNCC.getText().trim();
                    String sdt = txtSdt.getText().trim();
                    String diaChi = txtDiaChi.getText().trim();
                    String trangThai = cbTrangThai.getSelectedItem().toString().equals("Hoạt động") ? "ACTIVE" : "INACTIVE";

                    NhaCungCapDTO ncc = new NhaCungCapDTO(maNCC, tenNCC, sdt, diaChi, trangThai);
                    if (nhaCungCapBLL.update(ncc)) {
                        refreshTable();
                        clearForm();
                        JOptionPane.showMessageDialog(this, "Sửa nhà cung cấp thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Sửa nhà cung cấp thất bại! Vui lòng kiểm tra lại dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == btnDelete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhà cung cấp này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    int maNCC = (int) table.getValueAt(selectedRow, 0);
                    if (nhaCungCapBLL.delete(maNCC)) {
                        refreshTable();
                        clearForm();
                        JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa nhà cung cấp thất bại! Nhà cung cấp có thể đang được sử dụng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()== btnReset){
            clearForm();
            JOptionPane.showMessageDialog(this,"Form đã được làm mới","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Quản lý nhà cung cấp");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 500);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);

        FormSupplier test = new FormSupplier();
        f.add(test, BorderLayout.CENTER);
        f.setVisible(true);
    }
}