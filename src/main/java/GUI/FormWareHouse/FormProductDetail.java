package GUI.FormWareHouse;

import BLL.LoaiSanPhamBLL;
import BLL.NhaCungCapBLL;
import DTO.SanPhamDTO;
import BLL.SanPhamBLL;
import GUI.ComponentCommon.ButtonCustom;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FormProductDetail extends JDialog {
    private LoaiSanPhamBLL lsp = new LoaiSanPhamBLL();
    private NhaCungCapBLL ncc = new NhaCungCapBLL();
    public FormProductDetail(Frame parent, SanPhamDTO product) {
        super(parent, "Chi tiết sản phẩm", true);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel detailPanel = new JPanel(new GridBagLayout());
        detailPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        detailPanel.setBackground(Color.WHITE);

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font labelTitleFont = new Font("Segoe UI", Font.BOLD, 14);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // khoảng cách giữa các dòng
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

// Dòng 1: Mã sản phẩm
        detailPanel.add(createLabel("Mã sản phẩm:", labelTitleFont), gbc);
        gbc.gridx = 1;
        detailPanel.add(createLabel(String.valueOf(product.getMaSP()), labelFont), gbc);

// Dòng 2: Tên sản phẩm
        gbc.gridx = 0; gbc.gridy++;
        detailPanel.add(createLabel("Tên sản phẩm:", labelTitleFont), gbc);
        gbc.gridx = 1;
        detailPanel.add(createLabel(product.getTenSP(), labelFont), gbc);

// Dòng 3: Giá
        gbc.gridx = 0; gbc.gridy++;
        detailPanel.add(createLabel("Giá sản phẩm:", labelTitleFont), gbc);
        gbc.gridx = 1;
        detailPanel.add(createLabel(String.valueOf(product.getGia()), labelFont), gbc);

// Dòng 4: Số lượng tồn
        gbc.gridx = 0; gbc.gridy++;
        detailPanel.add(createLabel("Số lượng tồn:", labelTitleFont), gbc);
        gbc.gridx = 1;
        detailPanel.add(createLabel(String.valueOf(product.getSoLuongTon()), labelFont), gbc);

// Dòng 5: Loại sản phẩm
        gbc.gridx = 0; gbc.gridy++;
        detailPanel.add(createLabel("Loại sản phẩm:", labelTitleFont), gbc);
        gbc.gridx = 1;
        detailPanel.add(createLabel(lsp.getLoaiSanPham(product.getMaLSP()).getTenLoaiSP(), labelFont), gbc);

// Dòng 6: Nhà cung cấp
        gbc.gridx = 0; gbc.gridy++;
        detailPanel.add(createLabel("Nhà cung cấp:", labelTitleFont), gbc);
        gbc.gridx = 1;
        detailPanel.add(createLabel(ncc.getNhaCungCap(product.getMaNCC()).getTenNCC(), labelFont), gbc);

// Dòng 7: Mô tả
        gbc.gridx = 0; gbc.gridy++;
        detailPanel.add(createLabel("Mô tả:", labelTitleFont), gbc);
        gbc.gridx = 1;
        JTextArea moTaArea = new JTextArea(product.getMoTa());
        moTaArea.setFont(labelFont);
        moTaArea.setWrapStyleWord(true);
        moTaArea.setLineWrap(true);
        moTaArea.setEditable(false);
        moTaArea.setBackground(detailPanel.getBackground());
        moTaArea.setBorder(BorderFactory.createEmptyBorder());
        detailPanel.add(moTaArea, gbc);

// Dòng 8: Trạng thái
        gbc.gridx = 0; gbc.gridy++;
        detailPanel.add(createLabel("Trạng thái:", labelTitleFont), gbc);
        gbc.gridx = 1;
        detailPanel.add(createLabel(product.getTrangThai(), labelFont), gbc);


        //Hien thi anh san pham
        JPanel imagePanel = new JPanel();
        JLabel imageLabel = new JLabel();
        String imagePath = "src/main/resources/images/ImageProduct/"+product.getTenAnh();
        File imageFile = new File(imagePath);
        if(imageFile.exists()){
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image scaledImage = imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        }else{
             imagePath = "src/main/resources/images/ImageProduct/product-default.png";
             File imageFileDefault = new File(imagePath);
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image scaledImage = imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));

        }
        imagePanel.add(imageLabel);

        ButtonCustom closeBtn = new ButtonCustom("Đóng",12,"red");
        closeBtn.addActionListener(e -> dispose());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(closeBtn);

        add(detailPanel, BorderLayout.CENTER);
        add(imagePanel,BorderLayout.WEST);
        add(btnPanel, BorderLayout.SOUTH);
    }
    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

}
