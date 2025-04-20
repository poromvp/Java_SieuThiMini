package GUI.FormNhanVien;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTextField;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNhanVienDialog extends JDialog {
    private StyledTextField hoTenField = new StyledTextField();
    private JComboBox<String> cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
    private JDateChooser ngaySinhChooser = new JDateChooser();
    private StyledTextField cccdField = new StyledTextField();
    private StyledTextField diaChiField = new StyledTextField();
    private StyledTextField soDTField = new StyledTextField();
    private StyledTextField luongField = new StyledTextField();
    private JCheckBox cbTinhTrang = new JCheckBox("Đang làm việc");
    private FormTableNhanVien tablePanel;
    NhanVienDTO nv = new NhanVienDTO();
    NhanVienBLL bll = new NhanVienBLL();
    public AddNhanVienDialog(Window parent, FormTableNhanVien tablePanel) {
        super(parent, "Thêm Nhân Viên", ModalityType.APPLICATION_MODAL);
        this.tablePanel = tablePanel;
        setSize(300, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        ngaySinhChooser.setDateFormatString("dd-MM-yyyy");
        cbTinhTrang.setBackground(Color.WHITE);
        cbTinhTrang.setSelected(true);

        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JLabel("Họ tên:"));
        inputPanel.add(hoTenField);
        inputPanel.add(new JLabel("Giới tính:"));
        inputPanel.add(cbGioiTinh);
        inputPanel.add(new JLabel("Ngày sinh:"));
        inputPanel.add(ngaySinhChooser);
        inputPanel.add(new JLabel("CCCD:"));
        inputPanel.add(cccdField);
        inputPanel.add(new JLabel("Địa chỉ:"));
        inputPanel.add(diaChiField);
        inputPanel.add(new JLabel("Số điện thoại:"));
        inputPanel.add(soDTField);
        inputPanel.add(new JLabel("Lương:"));
        inputPanel.add(luongField);
        inputPanel.add(new JLabel("Tình trạng:"));
        inputPanel.add(cbTinhTrang);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        ButtonCustom btnSave = new ButtonCustom("Lưu", "save", 12, 40, 40);
        btnSave.addActionListener(e -> saveNhanVien());
        buttonPanel.add(btnSave);

        ButtonCustom btnCancel = new ButtonCustom("Hủy", "cancel", 12, 40, 40);
        btnCancel.addActionListener(e -> dispose());
        buttonPanel.add(btnCancel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveNhanVien() {
        try {
            nv.setTenNV(hoTenField.getText().trim());
            nv.setGioiTinh((String) cbGioiTinh.getSelectedItem());
            Date ngaySinh = ngaySinhChooser.getDate();
            nv.setNgaySinh(ngaySinh != null ? new java.sql.Date(ngaySinh.getTime()) : null);
            nv.setCCCD(cccdField.getText().trim());
            nv.setDiaChi(diaChiField.getText().trim());
            nv.setSDT(soDTField.getText().trim());

            try {
                nv.setLuong(Double.parseDouble(luongField.getText().trim()));
            } catch (NumberFormatException e) {
                nv.setLuong(-1); 
            }

            nv.setTrangThai(cbTinhTrang.isSelected() ? 1 : 0);

            if (bll.addNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
                if (tablePanel != null) {
                    tablePanel.refreshTable();
                }
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        }
    }
}