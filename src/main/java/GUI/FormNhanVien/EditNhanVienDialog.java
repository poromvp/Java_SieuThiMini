package GUI.FormNhanVien;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTextField;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EditNhanVienDialog extends JDialog {
    private StyledTextField hoTenField = new StyledTextField();
    private JComboBox<String> cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
    private JDateChooser ngaySinhChooser = new JDateChooser();
    private StyledTextField cccdField = new StyledTextField();
    private StyledTextField diaChiField = new StyledTextField();
    private StyledTextField soDTField = new StyledTextField();
    private StyledTextField luongField = new StyledTextField();
    private JCheckBox cbTinhTrang = new JCheckBox("Đang làm việc");
    private JLabel imageLabel = new JLabel();
    private JButton btnChonAnh = new JButton("Chọn ảnh");
    private String anhNV = null;
    private FormTableNhanVien tablePanel;
    private String maNV;
    NhanVienDTO nv = new NhanVienDTO();
    NhanVienBLL bll = new NhanVienBLL();
    private final String IMAGE_FOLDER = "src/main/resources/images/avtMember/";

    public EditNhanVienDialog(Window parent, FormTableNhanVien tablePanel, String maNV, String hoTen, String gioiTinh,
            String ngaySinh, String cccd, String diaChi, String soDT, String luong, String trangThai, String anhNV) {
        super(parent, "Sửa Nhân Viên", ModalityType.APPLICATION_MODAL);
        this.tablePanel = tablePanel;
        this.maNV = maNV;
        this.anhNV = anhNV;
        setSize(350, 550);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        ngaySinhChooser.setDateFormatString("dd-MM-yyyy");
        cbTinhTrang.setBackground(Color.WHITE);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setBorder(BorderFactory.createTitledBorder("Ảnh nhân viên"));
        
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(150, 150));
        
        loadImage(anhNV);
        
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        
        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chonAnh();
            }
        });
        imagePanel.add(btnChonAnh, BorderLayout.SOUTH);

        // Điền dữ liệu vào các trường
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

        nv.setMaNV(Integer.parseInt(maNV));

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

        // Panel chính chứa tất cả các thành phần
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(imagePanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

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

    private void loadImage(String imageName) {
        String imagePath = IMAGE_FOLDER + (imageName != null && !imageName.isEmpty() ? imageName : "default.png");
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            try {
                ImageIcon icon = new ImageIcon(imageFile.getPath());
                Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                System.out.println("Lỗi khi tải ảnh: " + e.getMessage());
                imageLabel.setIcon(null);
            }
        } else {
            File defaultImage = new File(IMAGE_FOLDER + "default.png");
            if (defaultImage.exists()) {
                try {
                    ImageIcon icon = new ImageIcon(defaultImage.getPath());
                    Image img = icon.getImage().getScaledInstance(
                            imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    System.out.println("Lỗi khi tải ảnh mặc định: " + e.getMessage());
                    imageLabel.setIcon(null);
                }
            } else {
                imageLabel.setIcon(null);
            }
        }
    }

    private void chonAnh() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                ImageIcon icon = new ImageIcon(selectedFile.getPath());
                Image img = icon.getImage().getScaledInstance(
                        imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
                
                anhNV = selectedFile.getName();
                
                File dest = new File(IMAGE_FOLDER + anhNV);
                if (!dest.exists()) {
                    java.nio.file.Files.copy(
                        selectedFile.toPath(), 
                        dest.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                    );
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi chọn ảnh: " + ex.getMessage(),
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveNhanVien() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn sửa thông tin này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Kiểm tra dữ liệu đầu vào
                String hoTen = hoTenField.getText().trim();
                if (hoTen.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Họ tên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String cccd = cccdField.getText().trim();
                if (cccd.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "CCCD không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String soDT = soDTField.getText().trim();
                if (soDT.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String luongText = luongField.getText().trim();
                if (luongText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Lương không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                nv.setTenNV(hoTen);
                nv.setGioiTinh((String) cbGioiTinh.getSelectedItem());
                Date ngaySinh = ngaySinhChooser.getDate();
                nv.setNgaySinh(ngaySinh != null ? new java.sql.Date(ngaySinh.getTime()) : null);
                nv.setCCCD(cccd);
                nv.setDiaChi(diaChiField.getText().trim());
                nv.setSDT(soDT);
                
                if (anhNV != null && !anhNV.isEmpty()) {
                    nv.setImage(anhNV); 
                } else {
                    NhanVienDTO existingNV = bll.getNhanVienByMa(maNV);
                    nv.setImage(existingNV != null && existingNV.getImage() != null ? existingNV.getImage() : "default.png");
                }

                try {
                    double luong = Double.parseDouble(luongText);
                    if (luong < 0) {
                        JOptionPane.showMessageDialog(this, "Lương phải là số không âm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    nv.setLuong(luong);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Lương phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                nv.setTrangThai(cbTinhTrang.isSelected() ? 1 : 0);

                if (bll.updateNhanVien(nv)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công!");
                    if (tablePanel != null) {
                        tablePanel.refreshTable();
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại. Vui lòng thử lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}