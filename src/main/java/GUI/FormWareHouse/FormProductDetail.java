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

        JPanel detailPanel = new JPanel();
        JPanel imagePanel = new JPanel();
        detailPanel.setLayout(new GridLayout(10, 2, 5, 5));
        detailPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));


        detailPanel.add(new JLabel("Mã sản phẩm:"));
        detailPanel.add(new JLabel(String.valueOf(product.getMaSP())));

        detailPanel.add(new JLabel("Tên sản phẩm:"));
        detailPanel.add(new JLabel(product.getTenSP()));

        detailPanel.add(new JLabel("Giá sản phẩm:"));
        detailPanel.add(new JLabel(String.valueOf(product.getGia())));

        detailPanel.add(new JLabel("Số lượng tồn:"));
        detailPanel.add(new JLabel(String.valueOf(product.getSoLuongTon())));

        detailPanel.add(new JLabel("Loại sản phẩm:"));
        detailPanel.add(new JLabel(String.valueOf(
                lsp.getLoaiSanPham(product.getMaLSP()).getTenLoaiSP()
        )));

        detailPanel.add(new JLabel("Nhà cung cấp:"));
        detailPanel.add(new JLabel(String.valueOf(
                ncc.getNhaCungCap(product.getMaNCC()).getTenNCC()
        )));

        detailPanel.add(new JLabel("Mô tả:"));
        detailPanel.add(new JLabel(product.getMoTa()));

        detailPanel.add(new JLabel("Trạng thái:"));
        detailPanel.add(new JLabel(product.getTrangThai()));

        //Hien thi anh san pham
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
}
