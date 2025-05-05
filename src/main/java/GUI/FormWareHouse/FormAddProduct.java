package GUI.FormWareHouse;

import BLL.LoaiSanPhamBLL;
import BLL.NhaCungCapBLL;
import BLL.SanPhamBLL;
import DTO.LoaiSanPhamDTO;
import DTO.NhaCungCapDTO;
import DTO.SanPhamDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTextField;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FormAddProduct extends JPanel {
    private JComboBox trangThai;
    private StyledTextField tenSP,giaSP,moTa;
    private String selectedImageName;
    private JComboBox<String> loaiSPCombo;
    private JComboBox<String> nccCombo;

    public FormAddProduct(){
        setLayout(new BorderLayout());

        JPanel nhapPanel = new JPanel();
        nhapPanel.setLayout(new GridLayout(7,2,5,5));

        nhapPanel.add(new JLabel("Tên sản phẩm:"));
        tenSP = new StyledTextField();
        nhapPanel.add(tenSP);

        nhapPanel.add(new JLabel("Giá sản phẩm:"));
        giaSP = new StyledTextField();
        nhapPanel.add(giaSP);

        nhapPanel.add(new JLabel("Loại sản phẩm:"));
        loaiSPCombo = new JComboBox<>();
        List<LoaiSanPhamDTO> loaiSPList = new LoaiSanPhamBLL().getList();
        for (LoaiSanPhamDTO tmp : loaiSPList) {
            loaiSPCombo.addItem(tmp.getTenLoaiSP() + " (" + tmp.getMaLSP() + ")");
        }
        nhapPanel.add(loaiSPCombo);

        nhapPanel.add(new JLabel("Nhà cung cấp:"));
        nccCombo = new JComboBox<>();
        List<NhaCungCapDTO> nccList = new NhaCungCapBLL().getList();
        for (NhaCungCapDTO tmp : nccList) {
            nccCombo.addItem(tmp.getTenNCC() + " (" + tmp.getMaNCC() + ")");
        }
        nhapPanel.add(nccCombo);



        nhapPanel.add(new JLabel("Tải ảnh:"));
        ButtonCustom uploadImageBtn = new ButtonCustom("Chọn ảnh",16,"blue");
        uploadImageBtn.addActionListener(e -> uploadImage());
        nhapPanel.add(uploadImageBtn);

        nhapPanel.add(new JLabel("Mô tả:"));
        moTa = new StyledTextField();
        nhapPanel.add(moTa);

        nhapPanel.add(new JLabel("Trạng thái:"));
        String[] trangThaiOptions = {"ACTIVE", "INACTIVE"};
        trangThai = new JComboBox<>(trangThaiOptions);
        nhapPanel.add(trangThai);

        ButtonCustom btn1 = new ButtonCustom("Thêm sản phẩm",16,"green");
        btn1.addActionListener(e -> {
            try {
                // Kiểm tra tên sản phẩm
                String tenSanPham = tenSP.getText().trim();
                if (tenSanPham.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống!");
                    return;
                }
                if (tenSanPham.length() > 255) {
                    JOptionPane.showMessageDialog(null, "Tên sản phẩm không được vượt quá 255 ký tự!");
                    return;
                }

                // Kiểm tra giá sản phẩm
                String giaText = giaSP.getText().trim();
                if (giaText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Giá sản phẩm không được để trống!");
                    return;
                }
                double gia;
                try {
                    gia = Double.parseDouble(giaText);
                    if (gia <= 0) {
                        JOptionPane.showMessageDialog(null, "Giá sản phẩm phải lớn hơn 0!");
                        return;
                    }
                    if (gia > 99999999.99) {
                        JOptionPane.showMessageDialog(null, "Giá sản phẩm không được vượt quá 99999999.99!");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Giá sản phẩm phải là số hợp lệ!");
                    return;
                }

                // Kiểm tra loại sản phẩm
                String selectedLoaiSP = (String) loaiSPCombo.getSelectedItem();
                if (selectedLoaiSP == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn loại sản phẩm!");
                    return;
                }
                int maLSP = Integer.parseInt(selectedLoaiSP.substring(selectedLoaiSP.indexOf("(") + 1, selectedLoaiSP.indexOf(")")));

                // Kiểm tra nhà cung cấp
                String selectedNCC = (String) nccCombo.getSelectedItem();
                if (selectedNCC == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp!");
                    return;
                }
                int maNCC = Integer.parseInt(selectedNCC.substring(selectedNCC.indexOf("(") + 1, selectedNCC.indexOf(")")));

                // Kiểm tra mô tả
                String moTaText = moTa.getText().trim();
                if (moTaText.length() > 555) {
                    JOptionPane.showMessageDialog(null, "Mô tả không được vượt quá 555 ký tự!");
                    return;
                }

                // Kiểm tra tên ảnh
                if (selectedImageName != null && selectedImageName.length() > 255) {
                    JOptionPane.showMessageDialog(null, "Tên ảnh không được vượt quá 255 ký tự!");
                    return;
                }

                // Tạo đối tượng sản phẩm
                SanPhamDTO product = new SanPhamDTO(
                        0,
                        maNCC,
                        maLSP,
                        selectedImageName != null ? selectedImageName : "product-default.png",
                        gia,
                        tenSanPham,
                        moTaText,
                        (String) trangThai.getSelectedItem(),
                        0
                );

                // Thêm sản phẩm
                if (SanPhamBLL.addProduct(product)) {
                    JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
            }
        });
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(btn1);

        add(nhapPanel,BorderLayout.CENTER);
        add(btnPanel,BorderLayout.SOUTH);
    }

    private void clearFields() {
        tenSP.setText("");
        giaSP.setText("");
        loaiSPCombo.setSelectedIndex(0);
        nccCombo.setSelectedIndex(0);
        moTa.setText("");
    }

    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "jpeg"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                File destDir = new File("src/main/resources/images/ImageProduct");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
                String fileName = selectedFile.getName();
                File destFile = new File(destDir, fileName);
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                selectedImageName = fileName;
                JOptionPane.showMessageDialog(null, "Tải ảnh thành công!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi tải ảnh: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Thêm sản phẩm");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,500);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);


        FormAddProduct test = new FormAddProduct();
        f.add(test, BorderLayout.CENTER);
        f.setVisible(true);
    }
}
