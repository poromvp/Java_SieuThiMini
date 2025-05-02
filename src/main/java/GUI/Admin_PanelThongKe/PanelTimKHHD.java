package GUI.Admin_PanelThongKe;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.TienIch;
import java.awt.*;
import java.util.Date;

public class PanelTimKHHD extends JPanel {

    private StyledTextField txtMaDonHang, txtMaNhanVien, txtTenNhanVien, txtMaKhuyenMai, txtTenKhuyenMai;
    private StyledTextField txtMaSanPham, txtTenSanPham, txtMaLoaiSanPham, txtTenLoaiSanPham;
    private JDateChooser dateTTFrom, dateTTTo;
    private JComboBox<String> cbPhuongThucThanhToan, cbSapXep, cbTheoCot;
    private JSpinner minThanhTien, maxThanhTien;

    public PanelTimKHHD() {
        setBackground(new Color(33, 58, 89));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH;

        Date today = new Date(System.currentTimeMillis());

        // Dòng 0: Mã đơn hàng, Ngày TT từ, Ngày TT đến
        JLabel lbMaDonHang = new JLabel("Mã đơn hàng");
        TienIch.timStyle(lbMaDonHang);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lbMaDonHang, gbc);

        txtMaDonHang = new StyledTextField(1, 10);
        txtMaDonHang.setPlaceholder("Nhập mã đơn hàng");
        TienIch.timStyle(txtMaDonHang);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtMaDonHang, gbc);

        JLabel lbNgayTTTu = new JLabel("Ngày TT từ");
        TienIch.timStyle(lbNgayTTTu);
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(lbNgayTTTu, gbc);

        dateTTFrom = new JDateChooser();
        dateTTFrom.setMaxSelectableDate(today);
        dateTTFrom.setDateFormatString("dd/MM/yyyy");
        TienIch.checkngaynhaptutay(dateTTFrom, today);
        TienIch.timStyle(dateTTFrom);
        gbc.gridx = 3;
        gbc.gridy = 0;
        add(dateTTFrom, gbc);

        JLabel lbNgayTTDen = new JLabel("Ngày TT đến");
        TienIch.timStyle(lbNgayTTDen);
        gbc.gridx = 4;
        gbc.gridy = 0;
        add(lbNgayTTDen, gbc);

        dateTTTo = new JDateChooser();
        dateTTTo.setMaxSelectableDate(today);
        dateTTTo.setDateFormatString("dd/MM/yyyy");
        TienIch.checkngaynhaptutay(dateTTTo, today);
        TienIch.timStyle(dateTTTo);
        gbc.gridx = 5;
        gbc.gridy = 0;
        add(dateTTTo, gbc);

        // Dòng 1: Thành tiền từ, Thành tiền đến, Phương thức thanh toán
        JLabel lbThanhTienTu = new JLabel("Thành tiền từ");
        TienIch.timStyle(lbThanhTienTu);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lbThanhTienTu, gbc);

        minThanhTien = new JSpinner(new SpinnerNumberModel(0, 0, 1_000_000_000, 1000));
        TienIch.timStyle(minThanhTien);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(minThanhTien, gbc);

        JLabel lbThanhTienDen = new JLabel("Thành tiền đến");
        TienIch.timStyle(lbThanhTienDen);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(lbThanhTienDen, gbc);

        maxThanhTien = new JSpinner(new SpinnerNumberModel(1_000_000_000, 0, 1_000_000_000, 1000));
        TienIch.timStyle(maxThanhTien);
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(maxThanhTien, gbc);

        JLabel lbPhuongThucThanhToan = new JLabel("Phương thức thanh toán");
        TienIch.timStyle(lbPhuongThucThanhToan);
        gbc.gridx = 4;
        gbc.gridy = 1;
        add(lbPhuongThucThanhToan, gbc);

        cbPhuongThucThanhToan = new JComboBox<>(new String[]{"TẤT CẢ", "CASH", "BANK"});
        TienIch.timStyle(cbPhuongThucThanhToan);
        gbc.gridx = 5;
        gbc.gridy = 1;
        add(cbPhuongThucThanhToan, gbc);

        // Dòng 2: Mã nhân viên, Tên nhân viên, ảnh gif
        JLabel lbMaNhanVien = new JLabel("Mã nhân viên");
        TienIch.timStyle(lbMaNhanVien);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lbMaNhanVien, gbc);

        txtMaNhanVien = new StyledTextField(1, 10);
        txtMaNhanVien.setPlaceholder("Nhập mã nhân viên");
        TienIch.timStyle(txtMaNhanVien);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtMaNhanVien, gbc);

        JLabel lbTenNhanVien = new JLabel("Tên nhân viên");
        TienIch.timStyle(lbTenNhanVien);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(lbTenNhanVien, gbc);

        txtTenNhanVien = new StyledTextField(1, 10);
        txtTenNhanVien.setPlaceholder("Nhập tên nhân viên");
        TienIch.timStyle(txtTenNhanVien);
        gbc.gridx = 3;
        gbc.gridy = 2;
        add(txtTenNhanVien, gbc);

        // GIF - chiếm 2 cột, 5 dòng
        JLabel gifIMG = new JLabel();
        TienIch.anhGif(gifIMG, "searching.gif", 390, 190);
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridheight = 5;
        gbc.gridwidth = 2;
        add(gifIMG, gbc);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        // Dòng 3: Mã khuyến mãi, Tên khuyến mãi
        JLabel lbMaKhuyenMai = new JLabel("Mã khuyến mãi");
        TienIch.timStyle(lbMaKhuyenMai);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lbMaKhuyenMai, gbc);

        txtMaKhuyenMai = new StyledTextField(1, 10);
        txtMaKhuyenMai.setPlaceholder("Nhập mã khuyến mãi");
        TienIch.timStyle(txtMaKhuyenMai);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtMaKhuyenMai, gbc);

        JLabel lbTenKhuyenMai = new JLabel("Tên khuyến mãi");
        TienIch.timStyle(lbTenKhuyenMai);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(lbTenKhuyenMai, gbc);

        txtTenKhuyenMai = new StyledTextField(1, 10);
        txtTenKhuyenMai.setPlaceholder("Nhập tên khuyến mãi");
        TienIch.timStyle(txtTenKhuyenMai);
        gbc.gridx = 3;
        gbc.gridy = 3;
        add(txtTenKhuyenMai, gbc);

        // Dòng 4: Mã sản phẩm, Tên sản phẩm
        JLabel lbMaSanPham = new JLabel("Mã sản phẩm");
        TienIch.timStyle(lbMaSanPham);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lbMaSanPham, gbc);

        txtMaSanPham = new StyledTextField(1, 10);
        txtMaSanPham.setPlaceholder("Nhập mã sản phẩm");
        TienIch.timStyle(txtMaSanPham);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtMaSanPham, gbc);

        JLabel lbTenSanPham = new JLabel("Tên sản phẩm");
        TienIch.timStyle(lbTenSanPham);
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(lbTenSanPham, gbc);

        txtTenSanPham = new StyledTextField(1, 10);
        txtTenSanPham.setPlaceholder("Nhập tên sản phẩm");
        TienIch.timStyle(txtTenSanPham);
        gbc.gridx = 3;
        gbc.gridy = 4;
        add(txtTenSanPham, gbc);

        // Dòng 5: Mã loại sản phẩm, Tên loại sản phẩm
        JLabel lbMaLoaiSanPham = new JLabel("Mã loại sản phẩm");
        TienIch.timStyle(lbMaLoaiSanPham);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lbMaLoaiSanPham, gbc);

        txtMaLoaiSanPham = new StyledTextField(1, 10);
        txtMaLoaiSanPham.setPlaceholder("Nhập mã loại sản phẩm");
        TienIch.timStyle(txtMaLoaiSanPham);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(txtMaLoaiSanPham, gbc);

        JLabel lbTenLoaiSanPham = new JLabel("Tên loại sản phẩm");
        TienIch.timStyle(lbTenLoaiSanPham);
        gbc.gridx = 2;
        gbc.gridy = 5;
        add(lbTenLoaiSanPham, gbc);

        txtTenLoaiSanPham = new StyledTextField(1, 10);
        txtTenLoaiSanPham.setPlaceholder("Nhập tên loại sản phẩm");
        TienIch.timStyle(txtTenLoaiSanPham);
        gbc.gridx = 3;
        gbc.gridy = 5;
        add(txtTenLoaiSanPham, gbc);

        // Dòng 6: Sắp xếp, Theo cột
        JLabel lbSapXep = new JLabel("Sắp xếp");
        TienIch.timStyle(lbSapXep);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lbSapXep, gbc);

        cbSapXep = new JComboBox<>(new String[]{"Tăng dần", "Giảm dần"});
        TienIch.timStyle(cbSapXep);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(cbSapXep, gbc);

        JLabel lbTheoCot = new JLabel("Theo cột");
        TienIch.timStyle(lbTheoCot);
        gbc.gridx = 2;
        gbc.gridy = 6;
        add(lbTheoCot, gbc);

        cbTheoCot = new JComboBox<>(new String[]{"Mã đơn hàng", "Ngày TT", "Thành tiền", "Mã nhân viên", "Mã khuyến mãi"});
        TienIch.timStyle(cbTheoCot);
        gbc.gridx = 3;
        gbc.gridy = 6;
        add(cbTheoCot, gbc);
    }
}