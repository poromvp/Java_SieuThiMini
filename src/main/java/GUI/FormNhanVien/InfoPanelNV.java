package GUI.FormNhanVien;

import javax.swing.*;

import GUI.ComponentCommon.ButtonCustom;

import java.awt.*;
import java.io.File;

public class InfoPanelNV extends JPanel {
    private JLabel hoTenLabel = new JLabel();
    private JLabel gioiTinhLabel = new JLabel();
    private JLabel ngaySinhLabel = new JLabel();
    private JLabel cccdLabel = new JLabel();
    private JLabel diaChiLabel = new JLabel();
    private JLabel soDTLabel = new JLabel();
    private JLabel luongLabel = new JLabel();
    private JLabel tinhTrangLabel = new JLabel();
    private JLabel avatarLabel = new JLabel();
    private FormTableNhanVien tablePanel;
    private String currentMaNV;

    public InfoPanelNV() {
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Thông Tin Chi Tiết Nhân Viên"));
        setPreferredSize(new Dimension(240, 0));

        // Avatar panel (trên cùng)
        JPanel avatarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        avatarPanel.setBackground(Color.WHITE);
        avatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avatarLabel.setText("No Image");
        avatarLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        avatarLabel.setPreferredSize(new Dimension(100, 100));
        avatarPanel.add(avatarLabel);
        add(avatarPanel, BorderLayout.NORTH);

        // Info panel (ở giữa)
        JPanel infoPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.add(new JLabel("Họ tên:"));
        infoPanel.add(hoTenLabel);
        infoPanel.add(new JLabel("Giới tính:"));
        infoPanel.add(gioiTinhLabel);
        infoPanel.add(new JLabel("Ngày sinh:"));
        infoPanel.add(ngaySinhLabel);
        infoPanel.add(new JLabel("CCCD:"));
        infoPanel.add(cccdLabel);
        infoPanel.add(new JLabel("Địa chỉ:"));
        infoPanel.add(diaChiLabel);
        infoPanel.add(new JLabel("Số điện thoại:"));
        infoPanel.add(soDTLabel);
        infoPanel.add(new JLabel("Lương:"));
        infoPanel.add(luongLabel);
        infoPanel.add(new JLabel("Tình trạng:"));
        infoPanel.add(tinhTrangLabel);
        add(infoPanel, BorderLayout.CENTER);
    }
    public void setEmployeeData(String maNV, String hoTen, String gioiTinh, String ngaySinh, String cccd,
    String diaChi, String soDT, String luong, String trangThai, String anhNV) {

        this.currentMaNV = maNV;
        hoTenLabel.setText(hoTen != null ? hoTen : "");
        gioiTinhLabel.setText(gioiTinh != null ? gioiTinh : "");
        ngaySinhLabel.setText(ngaySinh != null ? ngaySinh : "");
        cccdLabel.setText(cccd != null ? cccd : "");
        diaChiLabel.setText(diaChi != null ? diaChi : "");
        soDTLabel.setText(soDT != null ? soDT : "");
        luongLabel.setText(luong != null ? luong : "");
        tinhTrangLabel.setText(trangThai != null ? trangThai : "");

        // Xử lý hiển thị ảnh
        if (anhNV != null && !anhNV.isEmpty()) {
            try {
                String imageFolderPath = "src/main/resources/images/avtMember/";
                String fullImagePath = imageFolderPath + anhNV;
                System.out.println("Đang cố gắng tải ảnh từ: " + fullImagePath);

                File imageFile = new File(fullImagePath);
                if (!imageFile.exists()) {
                    System.out.println("File không tồn tại: " + fullImagePath);
                    avatarLabel.setText("No Image");
                    avatarLabel.setIcon(null);
                    return;
            }

                ImageIcon imageIcon = new ImageIcon(fullImagePath);
                Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                avatarLabel.setIcon(new ImageIcon(image));
                avatarLabel.setText(""); 

                System.out.println("Tải ảnh thành công!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Lỗi khi tải ảnh: " + e.getMessage());
                avatarLabel.setText("No Image");
                avatarLabel.setIcon(null);
            }
            } else {
                System.out.println("Không có tên tệp ảnh hoặc tên rỗng");
                avatarLabel.setText("No Image");
                avatarLabel.setIcon(null);
            }

            revalidate();
            repaint();
        }

    public void setTablePanel(FormTableNhanVien tablePanel) {
        this.tablePanel = tablePanel;
    }
}