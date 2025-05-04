package GUI.Admin_PanelThongKe;

import javax.swing.*;

import BLL.BaoCaoKhoTongHopBLL;
import DTO.SanPhamDTO;
import DTO.SearchTonKhoDTO;

import java.awt.*;
import java.util.ArrayList;

import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.TienIch;

public class PanelTimKho extends JPanel {

    // Khai báo các biến instance để truy cập từ các phương thức khác
    private SpinnerNumberModel min, max;
    private JSpinner Max, Min;
    private StyledTextField txtMaSanPham;
    private StyledTextField txtTenSanPham;
    private StyledTextField txtMaLoaiSanPham;
    private StyledTextField txtLoaiSanPham;
    private static JComboBox<String> cboSapXep;
    private static JComboBox<String> cboTheoCot;
    private StyledTextField txtMaNCC;
    private StyledTextField txtTenNCC;
    private SpinnerNumberModel min2, max2;
    private JSpinner Max2, Min2;

    public PanelTimKho() {
        setBackground(new Color(33, 58, 89));
        // Thiết lập layout chính cho panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dòng 1: Mã sản phẩm, tên sản phẩm
        JLabel lbMaSP = new JLabel("Mã sản phẩm");
        TienIch.timStyle(lbMaSP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lbMaSP, gbc);

        txtMaSanPham = new StyledTextField(1, 10);
        txtMaSanPham.setPlaceholder("1,2,3,....");
        TienIch.timStyle(txtMaSanPham);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtMaSanPham, gbc);

        JLabel lbTenSanPham = new JLabel("Tên sản phẩm");
        TienIch.timStyle(lbTenSanPham);
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(lbTenSanPham, gbc);

        txtTenSanPham = new StyledTextField(1, 10);
        txtTenSanPham.setPlaceholder("Nhập tên sản phẩm");
        TienIch.timStyle(txtTenSanPham);
        gbc.gridx = 3;
        gbc.gridy = 0;
        add(txtTenSanPham, gbc);

        JLabel gifIMG = new JLabel();
        TienIch.anhGif(gifIMG, "searching.gif", 300, 230);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 6;
        gbc.gridwidth = 2;
        add(gifIMG, gbc);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        // Dòng 2: Mã loại sản phẩm, tên loại sản phẩm
        JLabel lbMaLoaiSP = new JLabel("Mã loại sản phẩm");
        TienIch.timStyle(lbMaLoaiSP);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lbMaLoaiSP, gbc);

        txtMaLoaiSanPham = new StyledTextField(1, 10);
        txtMaLoaiSanPham.setPlaceholder("1,2,3,....");
        TienIch.timStyle(txtMaLoaiSanPham);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtMaLoaiSanPham, gbc);

        JLabel lbTenLoaiSP = new JLabel("Tên loại sản phẩm");
        TienIch.timStyle(lbTenLoaiSP);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(lbTenLoaiSP, gbc);

        txtLoaiSanPham = new StyledTextField(1, 10);
        txtLoaiSanPham.setPlaceholder("Nhập tên loại sản phẩm");
        TienIch.timStyle(txtLoaiSanPham);
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(txtLoaiSanPham, gbc);

        // Dòng 3: Số lượng max, số lượng min
        JLabel lbmin = new JLabel("Số lượng từ");
        TienIch.timStyle(lbmin);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lbmin, gbc);

        min = new SpinnerNumberModel(0, 0, 9000000, 15);
        Min = new JSpinner(min);
        TienIch.timStyle(Min);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(Min, gbc);

        JLabel lbmax = new JLabel("Số lượng đến");
        TienIch.timStyle(lbmax);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(lbmax, gbc);

        max = new SpinnerNumberModel(0, 0, 9000000, 15);
        Max = new JSpinner(max);
        TienIch.timStyle(Max);
        gbc.gridx = 3;
        gbc.gridy = 2;
        add(Max, gbc);

        // Dòng 4: Giá tiền max, min
        JLabel lbMinGia = new JLabel("Giá từ");
        TienIch.timStyle(lbMinGia);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lbMinGia, gbc);

        min2 = new SpinnerNumberModel(0, 0, 900000000, 1000);
        Min2 = new JSpinner(min2);
        TienIch.timStyle(Min2);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(Min2, gbc);

        JLabel lbMaxGia = new JLabel("Giá đến");
        TienIch.timStyle(lbMaxGia);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(lbMaxGia, gbc);

        max2 = new SpinnerNumberModel(0, 0, 900000000, 1000);
        Max2 = new JSpinner(max2);
        TienIch.timStyle(Max2);
        gbc.gridx = 3;
        gbc.gridy = 3;
        add(Max2, gbc);

        // Dòng 5: Mã nhà cung cấp, Tên nhà cung cấp
        JLabel lbMaNCC = new JLabel("Mã NCC");
        TienIch.timStyle(lbMaNCC);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lbMaNCC, gbc);

        txtMaNCC = new StyledTextField(1, 10);
        txtMaNCC.setPlaceholder("1,2,3,....");
        TienIch.timStyle(txtMaNCC);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtMaNCC, gbc);

        JLabel lbTenNCC = new JLabel("Tên NCC");
        TienIch.timStyle(lbTenNCC);
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(lbTenNCC, gbc);

        txtTenNCC = new StyledTextField(1, 10);
        txtTenNCC.setPlaceholder("Nhập tên nhà cung cấp");
        TienIch.timStyle(txtTenNCC);
        gbc.gridx = 3;
        gbc.gridy = 4;
        add(txtTenNCC, gbc);

        // Dòng 6: Sắp xếp, Theo cột
        JLabel lblSapXep = new JLabel("Sắp xếp");
        TienIch.timStyle(lblSapXep);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblSapXep, gbc);

        cboSapXep = new JComboBox<>(new String[] { "Tăng dần", "Giảm dần" });
        TienIch.timStyle(cboSapXep);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(cboSapXep, gbc);

        JLabel lblTheoCot = new JLabel("Theo cột");
        TienIch.timStyle(lblTheoCot);
        gbc.gridx = 2;
        gbc.gridy = 5;
        add(lblTheoCot, gbc);

        cboTheoCot = new JComboBox<>(
                new String[] { "Mã sản phẩm", "Giá", "Số lượng", "Mã loại sản phẩm", "Mã nhà cung cấp" });
        TienIch.timStyle(cboTheoCot);
        gbc.gridx = 3;
        gbc.gridy = 5;
        add(cboTheoCot, gbc);
    }

    public ArrayList<SanPhamDTO> ketqua() {
        int maSP = txtMaSanPham.getText().isEmpty() ? 0 : Integer.parseInt(txtMaSanPham.getText());
        int maLSP = txtMaLoaiSanPham.getText().isEmpty() ? 0 : Integer.parseInt(txtMaLoaiSanPham.getText());
        int maNCC = txtMaNCC.getText().isEmpty() ? 0 : Integer.parseInt(txtMaNCC.getText());
        SearchTonKhoDTO search = new SearchTonKhoDTO(maSP, txtTenSanPham.getText(), maLSP, txtLoaiSanPham.getText(),
                (int) Min.getValue(), (int) Max.getValue(), (int) Min2.getValue(), (int) Max2.getValue(), maNCC,
                txtTenNCC.getText(), (String) cboSapXep.getSelectedItem(),
                (String) cboTheoCot.getSelectedItem());
        return BaoCaoKhoTongHopBLL.TimTonKho(search);
    }

    public SearchTonKhoDTO trasearch() {
        int maSP = txtMaSanPham.getText().isEmpty() ? 0 : Integer.parseInt(txtMaSanPham.getText());
        int maLSP = txtMaLoaiSanPham.getText().isEmpty() ? 0 : Integer.parseInt(txtMaLoaiSanPham.getText());
        int maNCC = txtMaNCC.getText().isEmpty() ? 0 : Integer.parseInt(txtMaNCC.getText());
        SearchTonKhoDTO search = new SearchTonKhoDTO(maSP, txtTenSanPham.getText(), maLSP, txtLoaiSanPham.getText(),
                (int) Min.getValue(), (int) Max.getValue(), (int) Min2.getValue(), (int) Max2.getValue(), maNCC,
                txtTenNCC.getText(), (String) cboSapXep.getSelectedItem(),
                (String) cboTheoCot.getSelectedItem());
        return search;
    }
}
