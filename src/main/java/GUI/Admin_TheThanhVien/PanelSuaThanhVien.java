
package GUI.Admin_TheThanhVien;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import BLL.TheThanhVienBLL;
import DTO.TheThanhVienDTO;
import GUI.ComponentCommon.TienIch;

public class PanelSuaThanhVien extends JPanel {
    private JTextField txtTenTV;
    private JDateChooser dateNgaySinh;
    private JTextField txtDiaChi;
    private JTextField txtSDT;
    private JLabel lblImagePreview;
    private String tenAnh;
    private int maTV;

    public PanelSuaThanhVien(int maTV) {
        this.maTV = maTV;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Sửa Thông Tin Thẻ Thành Viên"));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Các thành phần nhập liệu
        txtTenTV = new JTextField();
        dateNgaySinh = new JDateChooser();
        txtDiaChi = new JTextField();
        txtSDT = new JTextField();
        JButton btnChonAnh = new JButton("Chọn ảnh");

        // Label hiển thị ảnh
        lblImagePreview = new JLabel("Chưa chọn ảnh", JLabel.CENTER);
        lblImagePreview.setPreferredSize(new Dimension(150, 150));
        lblImagePreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Load thông tin thành viên
        TheThanhVienDTO member = TheThanhVienBLL.getMemberById(maTV);
        if (member != null) {
            txtTenTV.setText(member.getTenTV());
            dateNgaySinh.setDate(member.getNgaySinh());
            txtDiaChi.setText(member.getDiaChi());
            txtSDT.setText(member.getSdt());
            tenAnh = member.getTenAnh();
            if (tenAnh != null && !tenAnh.isEmpty()) {
                try {
                    TienIch.anhAVT(lblImagePreview, tenAnh,150, 250);
                } catch (Exception e) {
                    lblImagePreview.setText("Lỗi tải ảnh");
                }
            }
        }

        // Sự kiện chọn ảnh
        btnChonAnh.addActionListener(_ -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                tenAnh = file.getAbsolutePath();
                ImageIcon icon = new ImageIcon(tenAnh);
                Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                lblImagePreview.setIcon(new ImageIcon(img));
                lblImagePreview.setText("");
            }
        });

        // Thêm vào panel
        formPanel.add(new JLabel("Tên thành viên:"));
        formPanel.add(txtTenTV);
        formPanel.add(new JLabel("Ngày sinh:"));
        formPanel.add(dateNgaySinh);
        formPanel.add(new JLabel("Địa chỉ:"));
        formPanel.add(txtDiaChi);
        formPanel.add(new JLabel("SĐT:"));
        formPanel.add(txtSDT);
        formPanel.add(new JLabel("Ảnh đại diện:"));
        formPanel.add(btnChonAnh);

        add(formPanel, BorderLayout.CENTER);
        add(lblImagePreview, BorderLayout.EAST);
    }

    // Getter để lấy DTO từ form
    public TheThanhVienDTO getDTOTheThanhVien() {
        TheThanhVienDTO dto = new TheThanhVienDTO();
        dto.setMaTV(maTV);
        dto.setTenTV(txtTenTV.getText());
        dto.setNgaySinh(dateNgaySinh.getDate());
        dto.setDiaChi(txtDiaChi.getText());
        dto.setSdt(txtSDT.getText());
        dto.setTenAnh(tenAnh);
        // Giữ nguyên các giá trị khác nếu có
        TheThanhVienDTO existing = TheThanhVienBLL.getMemberById(maTV);
        if (existing != null) {
            dto.setDiemTL(existing.getDiemTL());
            // Các trường khác nếu cần
        }
        return dto;
    }
}
