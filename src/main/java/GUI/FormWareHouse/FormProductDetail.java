package GUI.FormWareHouse;

import DTO.SanPhamDTO;
import BLL.SanPhamBLL;

import javax.swing.*;
import java.awt.*;

public class FormProductDetail extends JDialog {
    public FormProductDetail(Frame parent, SanPhamDTO product) {
        super(parent, "Chi tiết sản phẩm", true);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new GridLayout(8, 2, 5, 5));
        detailPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        detailPanel.add(new JLabel("Mã sản phẩm:"));
        detailPanel.add(new JLabel(String.valueOf(product.getMaSP())));

        detailPanel.add(new JLabel("Tên sản phẩm:"));
        detailPanel.add(new JLabel(product.getTenSP()));

        detailPanel.add(new JLabel("Giá sản phẩm:"));
        detailPanel.add(new JLabel(String.valueOf(product.getGia())));

        detailPanel.add(new JLabel("Loại sản phẩm:"));
        detailPanel.add(new JLabel(String.valueOf(product.getMaLSP())));

        detailPanel.add(new JLabel("Nhà cung cấp:"));
        detailPanel.add(new JLabel(String.valueOf(product.getMaNCC())));

        detailPanel.add(new JLabel("Tên ảnh:"));
        detailPanel.add(new JLabel(product.getTenAnh()));

        detailPanel.add(new JLabel("Mô tả:"));
        detailPanel.add(new JLabel(product.getMoTa()));

        detailPanel.add(new JLabel("Trạng thái:"));
        detailPanel.add(new JLabel(product.getTrangThai()));

        JButton closeBtn = new JButton("Đóng");
        closeBtn.addActionListener(e -> dispose());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(closeBtn);

        add(detailPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
}
