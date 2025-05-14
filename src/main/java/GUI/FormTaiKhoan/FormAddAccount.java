package GUI.FormTaiKhoan;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoanDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTextField;
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
                    if (!bll.kiemTraMaNV(tk.getMaNV())) {
                        JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
            
                    if (bll.kiemTraExist(tk.getMaNV())) {
                        JOptionPane.showMessageDialog(null, "Nhân viên đã có tài khoản!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
            
                    if (bll.kiemTraName(tk.getTenTK(),tk.getMaNV())) {
                        JOptionPane.showMessageDialog(null, "Tên tài khoản đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (bll.kiemTraGmail(tk.getGmail(),tk.getMaNV())) {
                        JOptionPane.showMessageDialog(null, "Gmail đã được sử dụng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return ;
                    }
                    
                    if (!isValidData(tk)) {
                        return ;
                    }
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
    public static boolean isValidData(TaiKhoanDTO tk) {
        if (tk.getMaNV() <= 0) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (tk.getTenTK() == null || tk.getTenTK().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên tài khoản không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (tk.getMatKhau() == null || tk.getMatKhau().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (tk.getMatKhau().matches(".*[àáảãạâầấẩẫậăằắẳẵặèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵ].*")) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không được chứa dấu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (tk.getMatKhau().length() < 6) {
            JOptionPane.showMessageDialog(null, "Mật khẩu phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return isValidGmail(tk.getGmail());
    }

    public static boolean isValidGmail(String gmail) {
        if (gmail == null || gmail.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Gmail không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (gmail.matches(".*[àáảãạâầấẩẫậăằắẳẵặèéẻẽẹêềếểễệìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵ].*")) {
            JOptionPane.showMessageDialog(null, "Gmail không được chứa dấu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!gmail.matches(emailRegex)) {
            JOptionPane.showMessageDialog(null, "Gmail không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        

        return true;
    }
    public static void main(String[] args) {
        JFrame parent = new JFrame();
        FormTableAccount tablePanel = new FormTableAccount();
        new FormAddAccount(parent, tablePanel);
    }
}