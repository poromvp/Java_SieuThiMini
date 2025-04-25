package GUI.Admin_TheThanhVien;
import com.toedter.calendar.JDateChooser;

import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.TienIch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class PanelThemThanhVien extends JPanel {

    private StyledTextField txtTenTV;
    private StyledTextField txtDiaChi;
    private StyledTextField txtSDT;
    private JLabel lblImagePreview;
    private JDateChooser dateNgaySinh;
    private String tenAnh;

    public PanelThemThanhVien() {
        setLayout(new BorderLayout(10, 10));
        TienIch.taoTitleBorder(this, "Thông tin thẻ thành viên");

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Các thành phần nhập liệu
        txtTenTV = new StyledTextField();
        dateNgaySinh = new JDateChooser();
        txtDiaChi = new StyledTextField();
        txtSDT = new StyledTextField();
        JButton btnChonAnh = new JButton("Chọn ảnh");

        // Label hiển thị ảnh
        lblImagePreview = new JLabel("Chưa chọn ảnh", JLabel.CENTER);
        lblImagePreview.setPreferredSize(new Dimension(150, 150));
        lblImagePreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Sự kiện chọn ảnh
        btnChonAnh.addActionListener((ActionEvent _) -> {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                tenAnh = file.getName();

                // Hiển thị ảnh preview
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                lblImagePreview.setIcon(new ImageIcon(img));
                lblImagePreview.setText(""); // Xóa chữ "Chưa chọn ảnh"
            }
        });

        // Thêm vào panel
        formPanel.add(new JLabel("Tên thành viên:"));
        txtTenTV.setPlaceholder("Nhập họ và tên");
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

    // Các getter để lấy dữ liệu từ form
    public String getTenTV() {
        return txtTenTV.getText();
    }

    public java.util.Date getNgaySinh() {
        return dateNgaySinh.getDate();
    }

    public String getDiaChi() {
        return txtDiaChi.getText();
    }

    public String getSDT() {
        return txtSDT.getText();
    }

    public String getTenAnh() {
        return tenAnh;
    }
}

