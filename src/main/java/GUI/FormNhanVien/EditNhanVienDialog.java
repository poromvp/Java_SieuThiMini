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

public class EditNhanVienDialog extends JDialog {
    private StyledTextField hoTenField = new StyledTextField();
    private JComboBox<String> cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
    private JDateChooser ngaySinhChooser = new JDateChooser();
    private StyledTextField cccdField = new StyledTextField();
    private StyledTextField diaChiField = new StyledTextField();
    private StyledTextField soDTField = new StyledTextField();
    private StyledTextField luongField = new StyledTextField();
    private JCheckBox cbTinhTrang = new JCheckBox("Đang làm việc");
    private FormTableNhanVien tablePanel;
    private String maNV;
    NhanVienDTO nv = new NhanVienDTO();
    NhanVienBLL bll = new NhanVienBLL();
    public EditNhanVienDialog(Window parent, FormTableNhanVien tablePanel, String maNV, String hoTen, String gioiTinh,
            String ngaySinh, String cccd, String diaChi, String soDT, String luong, String trangThai) {
        super(parent, "Sửa Nhân Viên", ModalityType.APPLICATION_MODAL);
        this.tablePanel = tablePanel;
        this.maNV = maNV;
        setSize(300, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        ngaySinhChooser.setDateFormatString("dd-MM-yyyy");
        cbTinhTrang.setBackground(Color.WHITE);

        hoTenField.setText(hoTen);
        cbGioiTinh.setSelectedItem(gioiTinh != null && gioiTinh.equals("Nữ") ? "Nữ" : "Nam");
        try {
        if (ngaySinh != null && !ngaySinh.trim().isEmpty()) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date utilDate = sdf.parse(ngaySinh);
        ngaySinhChooser.setDate(utilDate);
        }
        } catch (Exception e) {
        ngaySinhChooser.setDate(null);
        }
        cccdField.setText(cccd);
        diaChiField.setText(diaChi);
        soDTField.setText(soDT);
        luongField.setText(luong);
        cbTinhTrang.setSelected("Đang làm việc".equalsIgnoreCase(trangThai));

        // 👉 THÊM DÒNG NÀY
        nv.setMaNV(Integer.parseInt(maNV)); // Gán mã nhân viên vào DTO

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
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn sửa thông tin này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Lấy dữ liệu từ các trường nhập liệu
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
                    nv.setLuong(-1);  // Gán giá trị -1 nếu lương không hợp lệ
                }
    
                nv.setTrangThai(cbTinhTrang.isSelected() ? 1 : 0);
    
                // Kiểm tra trước khi gọi phương thức cập nhật
                System.out.println("Thông tin nhân viên: " + nv.getTenNV() + ", " + nv.getMaNV());
    
                // Cập nhật thông tin nhân viên
                if (bll.updateNhanVien(nv)) {
                    // Thông báo cập nhật thành công
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
    
                    // Làm mới bảng (nếu có)
                    if (tablePanel != null) {
                        tablePanel.refreshTable();
                    }
    
                    // Đóng cửa sổ dialog
                    dispose(); // Đóng cửa sổ dialog
                } else {
                    // Nếu update không thành công
                    JOptionPane.showMessageDialog(this, "Cập nhật không thành công. Vui lòng thử lại.");
                }
            } catch (Exception e) {
                // Hiển thị thông báo lỗi nếu có exception
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}