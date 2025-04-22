package GUI.FormNhanVien;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.CustomComboBox;
import GUI.ComponentCommon.StyledTextField;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddNhanVienDialog extends JDialog {
    private StyledTextField hoTenField = new StyledTextField();
    String[] listGioiTinh = {"Nam", "Nữ"};
    private CustomComboBox cbGioiTinh = new CustomComboBox (listGioiTinh);
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
    NhanVienDTO nv = new NhanVienDTO();
    NhanVienBLL bll = new NhanVienBLL();
    private final String IMAGE_FOLDER = "src/main/resources/images/avtMember/"; 

    public AddNhanVienDialog(Window parent, FormTableNhanVien tablePanel) {
        super(parent, "Thêm Nhân Viên", ModalityType.APPLICATION_MODAL);
        this.tablePanel = tablePanel;
        setSize(350, 650); 
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        ngaySinhChooser.setDateFormatString("dd-MM-yyyy");
        cbTinhTrang.setBackground(Color.WHITE);
        cbTinhTrang.setSelected(true);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setBorder(BorderFactory.createTitledBorder("Ảnh nhân viên"));
        
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(100, 100));
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        
        btnChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chonAnh();
            }
        });
        imagePanel.add(btnChonAnh, BorderLayout.SOUTH);

        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 5, 5));
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

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(imagePanel, BorderLayout.NORTH);

        add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);
        ButtonCustom btnSave = new ButtonCustom("Thêm", 12,"black");
        btnSave.addActionListener(e -> saveNhanVien());
        buttonPanel.add(btnSave);

        ButtonCustom btnCancel = new ButtonCustom("Hủy",12, "red");
        btnCancel.addActionListener(e -> dispose());
        buttonPanel.add(btnCancel);

        add(buttonPanel, BorderLayout.SOUTH);
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
                Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
                
                anhNV = selectedFile.getName();
                nv.setImage(anhNV);
    
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
        try {
            String hoTen = hoTenField.getText().trim();
    
            String cccd = cccdField.getText().trim();
    
            String soDT = soDTField.getText().trim();
    
            String luongText = luongField.getText().trim();
    
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
                nv.setImage("default.png"); 
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
    
            if (bll.addNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
                if (tablePanel != null) {
                    tablePanel.refreshTable();
                }
                dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        }
    }
}