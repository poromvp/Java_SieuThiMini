package GUI.FormWareHouse;

import BLL.SanPhamBLL;
import DTO.SanPhamDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTextField;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FormEditProduct extends JDialog {
    private JComboBox<String> trangThai;
    private StyledTextField tenSP, giaSP, loaiSP, ncc, tenAnh, moTa;
    private ButtonCustom uploadImageBtn;
    private String selectedImageName;
    private SanPhamDTO product;

    public FormEditProduct(Frame parent, SanPhamDTO product) {
        super(parent, "Sửa sản phẩm", true);
        this.product = product;
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel nhapPanel = new JPanel();
        nhapPanel.setLayout(new GridLayout(7, 2, 5, 5));

        nhapPanel.add(new JLabel("Tên sản phẩm:"));
        tenSP = new StyledTextField();
        tenSP.setText(product.getTenSP());
        nhapPanel.add(tenSP);

        nhapPanel.add(new JLabel("Giá sản phẩm:"));
        giaSP = new StyledTextField();
        giaSP.setText(String.valueOf(product.getGia()));
        nhapPanel.add(giaSP);

        nhapPanel.add(new JLabel("Loại sản phẩm:"));
        loaiSP = new StyledTextField();
        loaiSP.setText(String.valueOf(product.getMaLSP()));
        nhapPanel.add(loaiSP);

        nhapPanel.add(new JLabel("Nhà cung cấp:"));
        ncc = new StyledTextField();
        ncc.setText(String.valueOf(product.getMaNCC()));
        nhapPanel.add(ncc);

//        nhapPanel.add(new JLabel("Tên ảnh:"));
//        tenAnh = new StyledTextField();
//        tenAnh.setText(product.getTenAnh());
//        tenAnh.setEditable(false);
//        nhapPanel.add(tenAnh);

        nhapPanel.add(new JLabel("Tải ảnh:"));
        uploadImageBtn = new ButtonCustom("Chọn ảnh",16,"blue");
        uploadImageBtn.addActionListener(e -> uploadImage());
        nhapPanel.add(uploadImageBtn);

        nhapPanel.add(new JLabel("Mô tả:"));
        moTa = new StyledTextField();
        moTa.setText(product.getMoTa());
        nhapPanel.add(moTa);

        nhapPanel.add(new JLabel("Trạng thái:"));
        String[] trangThaiOptions = {"ACTIVE", "INACTIVE"};
        trangThai = new JComboBox<>(trangThaiOptions);
        trangThai.setSelectedItem(product.getTrangThai());
        nhapPanel.add(trangThai);

        ButtonCustom saveBtn = new ButtonCustom("Lưu",16,"green");
        saveBtn.addActionListener(e -> {
            try {
                SanPhamDTO updatedProduct = new SanPhamDTO(
                        product.getMaSP(),
                        Integer.parseInt(ncc.getText()),
                        Integer.parseInt(loaiSP.getText()),
                        selectedImageName != null ? selectedImageName : product.getTenAnh(),
                        Double.parseDouble(giaSP.getText()),
                        tenSP.getText(),
                        moTa.getText(),
                        (String) trangThai.getSelectedItem(),
                        product.getSoLuongTon()
                );
                if (SanPhamBLL.updateProduct(updatedProduct)) {
                    JOptionPane.showMessageDialog(null, "Sửa sản phẩm thành công!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Sửa sản phẩm thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số!");
            }
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(saveBtn);

        add(nhapPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
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
}