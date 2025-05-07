package GUI.Admin_TheThanhVien;

import com.toedter.calendar.JDateChooser;

import DTO.TheThanhVienDTO;
import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.TienIch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
            JFileChooser chooser = new JFileChooser("src/main/resources/images/avtMember");
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                tenAnh = file.getName();

                // Hiển thị ảnh preview
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
                lblImagePreview.setIcon(new ImageIcon(img));
                lblImagePreview.setText(""); // Xóa chữ "Chưa chọn ảnh"
            }
        });

        // Thêm vào panel
        formPanel.add(new JLabel("Tên thành viên:"));
        txtTenTV.setPlaceholder("Nhập họ và tên");
        formPanel.add(txtTenTV);

        formPanel.add(new JLabel("Ngày sinh:"));
        dateNgaySinh.setDateFormatString("dd/MM/yyyy");
        dateNgaySinh.setMaxSelectableDate(new java.util.Date());
        TienIch.checkngaynhaptutay(dateNgaySinh,null);
        TienIch.checkngaynhapdutuoi(dateNgaySinh,null);
        formPanel.add(dateNgaySinh);

        formPanel.add(new JLabel("Địa chỉ:"));
        txtDiaChi.setPlaceholder("Nhập địa chỉ");
        formPanel.add(txtDiaChi);

        formPanel.add(new JLabel("SĐT:"));
        txtSDT.setPlaceholder("Số điện thoại (10 chữ số)");
        formPanel.add(txtSDT);
        TienIch.chiduocnhapso(txtSDT);

        formPanel.add(new JLabel("Ảnh đại diện:"));
        formPanel.add(btnChonAnh);

        add(formPanel, BorderLayout.CENTER);
        add(lblImagePreview, BorderLayout.EAST);

    }

    public boolean ktraBieuThucChinhQuy() {
        if (getTenTV() == null || getTenTV().trim().isEmpty()) {
            return false;
        }
        if (getNgaySinh() == null) {
            return false;
        }
        if (getDiaChi() == null || getDiaChi().trim().isEmpty()) {
            return false;
        }
        if (getSDT() == null || getSDT().trim().isEmpty() || getSDT().length()!=10) {
            return false;
        }
        if (getTenAnh() == null || getTenAnh().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public TheThanhVienDTO create1TV(){
        return new TheThanhVienDTO(getTenTV(), getNgaySinh(), getDiaChi(), getSDT(), getTenAnh());
    }

    // Các getter để lấy dữ liệu từ form
    public String getTenTV() {
        return txtTenTV.getText();
    }

    public java.sql.Date getNgaySinh() {
        return new java.sql.Date(dateNgaySinh.getDate().getTime());
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
