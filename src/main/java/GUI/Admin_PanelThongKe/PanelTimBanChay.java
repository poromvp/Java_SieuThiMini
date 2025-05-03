package GUI.Admin_PanelThongKe;

import javax.swing.*;

import BLL.BaoCaoKhoTongHopBLL;
import DTO.SearchBanChayDTO;

import java.awt.*;
import java.util.ArrayList;

import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.TienIch;
import java.sql.Date;

public class PanelTimBanChay extends JPanel {

    private SpinnerNumberModel min, max;
    private JSpinner Max, Min;
    private StyledTextField txtMaSanPham;
    private StyledTextField txtTenSanPham;
    private StyledTextField txtMaLoaiSanPham;
    private StyledTextField txtLoaiSanPham;
    private static JComboBox<String> cboSapXep;
    private static JComboBox<String> cboTheoCot;

    public PanelTimBanChay() {
        setBackground(new Color(33, 58, 89));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dòng 1: Mã sản phẩm, Tên sản phẩm
        JLabel lbMaSP = new JLabel("Mã sản phẩm");
        TienIch.timStyle(lbMaSP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lbMaSP, gbc);

        txtMaSanPham = new StyledTextField(1, 10);
        txtMaSanPham.setPlaceholder("1,2,3,4,....");
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
        TienIch.anhGif(gifIMG, "searching.gif", 150, 150);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 2;
        add(gifIMG, gbc);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        // Dòng 2: Mã loại sản phẩm, Tên loại sản phẩm
        JLabel lbMaLoaiSP = new JLabel("Mã loại sản phẩm");
        TienIch.timStyle(lbMaLoaiSP);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lbMaLoaiSP, gbc);

        txtMaLoaiSanPham = new StyledTextField(1, 10);
        txtMaLoaiSanPham.setPlaceholder("1,2,3,4,....");
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

        // Dòng 3: Số lượng bán min, max
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

        // Dòng 4: Sắp xếp, theo cột
        JLabel lblSapXep = new JLabel("Sắp xếp");
        TienIch.timStyle(lblSapXep);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblSapXep, gbc);

        cboSapXep = new JComboBox<>(new String[] { "Tăng dần", "Giảm dần" });
        TienIch.timStyle(cboSapXep);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(cboSapXep, gbc);

        JLabel lblTheoCot = new JLabel("Theo cột");
        TienIch.timStyle(lblTheoCot);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(lblTheoCot, gbc);

        cboTheoCot = new JComboBox<>(new String[] { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Mã loại sản phẩm" });
        TienIch.timStyle(cboTheoCot);
        gbc.gridx = 3;
        gbc.gridy = 3;
        add(cboTheoCot, gbc);
    }

    public ArrayList<SearchBanChayDTO> ketqua(Date from, Date to) {
        int maSP = txtMaSanPham.getText().isEmpty() ? 0 : Integer.parseInt(txtMaSanPham.getText());
        int maLSP = txtMaLoaiSanPham.getText().isEmpty() ? 0 : Integer.parseInt(txtMaLoaiSanPham.getText());
        SearchBanChayDTO search = new SearchBanChayDTO(maSP,
                txtTenSanPham.getText(), maLSP, txtLoaiSanPham.getText(),
                (int) Min.getValue(), (int) Max.getValue(), (String) cboSapXep.getSelectedItem(),
                (String) cboTheoCot.getSelectedItem());
        return BaoCaoKhoTongHopBLL.TimBanChay(search, from, to);
    }
}
