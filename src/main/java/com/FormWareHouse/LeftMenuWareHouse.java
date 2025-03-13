package com.FormWareHouse;

import com.ComponentCommon.StyledLeftMenubutton;
import com.sieuthi.FrameEmployee;
import com.sieuthi.FrameQuanLyKho;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftMenuWareHouse extends JPanel implements ActionListener {
    private StyledLeftMenubutton btn_import;
    private StyledLeftMenubutton btn_product;
    private StyledLeftMenubutton btn_import_detail;
    // private FrameNhanVien frame; // Tham chiếu đến FrameNhanVien

    public LeftMenuWareHouse() {


        setBackground(new Color(28, 173, 193));
        setPreferredSize(new Dimension(230, getHeight()));
        setLayout(new FlowLayout());
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        setBorder(emptyBorder);

        btn_import = new StyledLeftMenubutton("src/main/resources/images/import.png", "Nhap hang");
        btn_product = new StyledLeftMenubutton("src/main/resources/images/selling.png", "Quan ly san pham");

        btn_import.addActionListener(this);
        btn_product.addActionListener(this);

        add(btn_import);
        add(btn_product);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_import) {
            System.out.println("click btn_import");
            FrameQuanLyKho.setPage("formImport", "Nhập hàng");
        }
        if (e.getSource() == btn_product) {
            System.out.println("click btn_product");
            FrameQuanLyKho.setPage("formProduct", "Quản lý sản phẩm");
        }
    }
}
