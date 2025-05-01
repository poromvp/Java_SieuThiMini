package GUI.FormWareHouse;

import BLL.LoaiSanPhamBLL;
import BLL.NhaCungCapBLL;
import BLL.SanPhamBLL;
import DTO.LoaiSanPhamDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTextField;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FormEditProduct extends JDialog {
    private JComboBox<String> trangThai;
    private StyledTextField tenSP, giaSP, tenAnh, moTa;
    private JComboBox<String> loaiSPCombo;
    private JComboBox<String> nccCombo;
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

        nhapPanel.add(new JLabel("Loại sản phẩm"));
        loaiSPCombo = new JComboBox<>();
        List<LoaiSanPhamDTO> loaiSPList = new LoaiSanPhamBLL().getList();
        for(LoaiSanPhamDTO loai: loaiSPList){
            loaiSPCombo.addItem(loai.getTenLoaiSP() + " (" + loai.getMaLSP() + ")");
            if(loai.getMaLSP()==product.getMaLSP()){
                loaiSPCombo.setSelectedItem(loai.getTenLoaiSP() + " (" + loai.getMaLSP() + ")");
            }
        }
        nhapPanel.add(loaiSPCombo);

        nhapPanel.add(new JLabel("Nhà cung cấp:"));
        nccCombo= new JComboBox<>();
        List<NhaCungCapDTO> nccList = new NhaCungCapBLL().getList();
        for(NhaCungCapDTO ncc:nccList){
            nccCombo.addItem(ncc.getTenNCC()+" ("+ncc.getMaNCC()+") ");
            if(ncc.getMaNCC()== product.getMaNCC()){
                nccCombo.setSelectedItem(ncc.getTenNCC()+" ("+ncc.getMaNCC()+") ");
            }
        }
        nhapPanel.add(nccCombo);

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
                String selectedLoaiSP = (String) loaiSPCombo.getSelectedItem();
                int maLSP = Integer.parseInt(selectedLoaiSP.substring(selectedLoaiSP.indexOf("(") + 1, selectedLoaiSP.indexOf(")")));

                String selectedNCC = (String) nccCombo.getSelectedItem();
                int maNCC = Integer.parseInt(selectedNCC.substring(selectedNCC.indexOf("(") + 1, selectedNCC.indexOf(")")));
                SanPhamDTO updatedProduct = new SanPhamDTO(
                        product.getMaSP(),
                        maNCC,
                        maLSP,
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
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
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