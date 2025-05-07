package GUI.Admin_PanelThongKe;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

import com.toedter.calendar.JDateChooser;

import DTO.SearchFilterDTO;
import GUI.ComponentCommon.*;

public class PanelTimThK extends JPanel {

    // Khai báo các biến instance để truy cập từ các phương thức khác
    private static StyledTextField txtMaDonHang;
    private static JDateChooser dateChooserBatDau = new JDateChooser();
    private static StyledTextField txtLoaiSanPham;
    private static SpinnerNumberModel min, max;
    private static JSpinner Max, Min;
    private static JDateChooser dateChooserKetThuc = new JDateChooser();
    private static StyledTextField txtMaNhanVien;
    private static StyledTextField txtTenNhanVien;
    private static JComboBox<String> cboSapXep;
    private static JComboBox<String> cboTheoCot;
    private static StyledTextField txtMaKhachHang;
    private static StyledTextField txtTenKhachHang;
    private static StyledTextField txtMaKhuyenMai;
    private static StyledTextField txtTenKhuyenMai;
    private static JComboBox<String> cboPhuongThucThanhToan;
    private static StyledTextField txtMaSanPham;
    private static StyledTextField txtTenSanPham;
    private static StyledTextField txtMaLoaiSanPham;

    JLabel lblMaNhanVien = new JLabel("Mã nhân viên");
    JLabel lblTenNhanVien = new JLabel("Tên nhân viên");

    public java.sql.Date getNgayBatDau() {
        java.util.Date utilDate = dateChooserBatDau.getDate();
        if (utilDate != null) {
            return new java.sql.Date(utilDate.getTime());
        }
        return null;
    }

    public java.sql.Date getNgayKetThuc() {
        java.util.Date utilDate = dateChooserKetThuc.getDate();
        if (utilDate != null) {
            return new java.sql.Date(utilDate.getTime());
        }
        return null;
    }

    public PanelTimThK() {
        setBackground(new Color(33, 58, 89));
        // Thiết lập layout chính cho panel
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dòng 1: Mã đơn hàng, Ngày bắt đầu, Ngày kết thúc
        JLabel lblMaDonHang = new JLabel("Mã đơn hàng");
        TienIch.timStyle(lblMaDonHang);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblMaDonHang, gbc);

        txtMaDonHang = new StyledTextField(1, 10);
        txtMaDonHang.setPlaceholder("1, 2, 3, 4,...");
        TienIch.timStyle(txtMaDonHang);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtMaDonHang, gbc);

        JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu");
        TienIch.timStyle(lblNgayBatDau);
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(lblNgayBatDau, gbc);

        dateChooserBatDau.setDateFormatString("dd/MM/yyyy");
        dateChooserBatDau.setMaxSelectableDate(new java.util.Date());
        TienIch.checkngaynhaptutay(dateChooserBatDau, new java.sql.Date(System.currentTimeMillis()));
        TienIch.timStyle(dateChooserBatDau);
        gbc.gridx = 3;
        gbc.gridy = 0;
        add(dateChooserBatDau, gbc);

        JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc");
        TienIch.timStyle(lblNgayKetThuc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        add(lblNgayKetThuc, gbc);

        dateChooserKetThuc.setDateFormatString("dd/MM/yyyy");
        dateChooserKetThuc.setMaxSelectableDate(new java.util.Date());
        TienIch.checkngaynhaptutay(dateChooserKetThuc, new java.sql.Date(System.currentTimeMillis()));
        TienIch.timStyle(dateChooserKetThuc);
        gbc.gridx = 5;
        gbc.gridy = 0;
        add(dateChooserKetThuc, gbc);

        // Dòng 2: Tổng tiền min, Tổng tiền max, Phương thức thanh toán
        JLabel lblTongTienMin = new JLabel("Tổng tiền min");
        TienIch.timStyle(lblTongTienMin);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblTongTienMin, gbc);

        min = new SpinnerNumberModel(5000, 5000, 9000000, 5000);
        Min = new JSpinner(min);
        TienIch.timStyle(Min);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(Min, gbc);

        JLabel lblTongTienMax = new JLabel("Tổng tiền max");
        TienIch.timStyle(lblTongTienMax);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(lblTongTienMax, gbc);

        max = new SpinnerNumberModel(9000000, 5000, 9000000, 5000);
        Max = new JSpinner(max);
        TienIch.timStyle(Max);
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(Max, gbc);

        JLabel lblPhuongThucThanhToan = new JLabel("Phương thức thanh toán");
        TienIch.timStyle(lblPhuongThucThanhToan);
        gbc.gridx = 4;
        gbc.gridy = 1;
        add(lblPhuongThucThanhToan, gbc);

        cboPhuongThucThanhToan = new JComboBox<>(new String[]{"tất cả", "BANK", "CASH"});
        TienIch.timStyle(cboPhuongThucThanhToan);
        gbc.gridx = 5;
        gbc.gridy = 1;
        add(cboPhuongThucThanhToan, gbc);

        // Dòng 3: Mã khách hàng, Tên khách hàng
        JLabel lblMaKhachHang = new JLabel("Mã khách hàng");
        TienIch.timStyle(lblMaKhachHang);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblMaKhachHang, gbc);

        txtMaKhachHang = new StyledTextField(1, 10);
        txtMaKhachHang.setPlaceholder("1, 2, 3, 4,...");
        TienIch.timStyle(txtMaKhachHang);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtMaKhachHang, gbc);

        JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
        TienIch.timStyle(lblTenKhachHang);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(lblTenKhachHang, gbc);

        txtTenKhachHang = new StyledTextField(1, 10);
        txtTenKhachHang.setPlaceholder("Nhập tên khách hàng");
        TienIch.timStyle(txtTenKhachHang);
        gbc.gridx = 3;
        gbc.gridy = 2;
        add(txtTenKhachHang, gbc);

        JLabel gifIMG = new JLabel();
        TienIch.anhGif(gifIMG, "searching.gif", 380, 250);
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridheight = 6;
        gbc.gridwidth = 2;
        add(gifIMG, gbc);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        // Dòng 4: Mã khuyến mãi, Tên khuyến mãi
        JLabel lblMaKhuyenMai = new JLabel("Mã khuyến mãi");
        TienIch.timStyle(lblMaKhuyenMai);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblMaKhuyenMai, gbc);

        txtMaKhuyenMai = new StyledTextField(1, 10);
        txtMaKhuyenMai.setPlaceholder("1, 2, 3, 4,...");
        TienIch.timStyle(txtMaKhuyenMai);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtMaKhuyenMai, gbc);

        JLabel lblTenKhuyenMai = new JLabel("Tên khuyến mãi");
        TienIch.timStyle(lblTenKhuyenMai);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(lblTenKhuyenMai, gbc);

        txtTenKhuyenMai = new StyledTextField(1, 10);
        txtTenKhuyenMai.setPlaceholder("Nhập tên khuyến mãi");
        TienIch.timStyle(txtTenKhuyenMai);
        gbc.gridx = 3;
        gbc.gridy = 3;
        add(txtTenKhuyenMai, gbc);

        // Dòng 5: Mã sản phẩm, Tên sản phẩm
        JLabel lblMaSanPham = new JLabel("Mã sản phẩm");
        TienIch.timStyle(lblMaSanPham);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblMaSanPham, gbc);

        txtMaSanPham = new StyledTextField(1, 10);
        txtMaSanPham.setPlaceholder("1, 2, 3, 4,...");
        TienIch.timStyle(txtMaSanPham);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtMaSanPham, gbc);

        JLabel lblTenSanPham = new JLabel("Tên sản phẩm");
        TienIch.timStyle(lblTenSanPham);
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(lblTenSanPham, gbc);

        txtTenSanPham = new StyledTextField(1, 10);
        txtTenSanPham.setPlaceholder("Nhập tên sản phẩm");
        TienIch.timStyle(txtTenSanPham);
        gbc.gridx = 3;
        gbc.gridy = 4;
        add(txtTenSanPham, gbc);

        // Dòng 6: Mã loại sản phẩm, Loại sản phẩm
        JLabel lblMaLoaiSanPham = new JLabel("Mã loại sản phẩm");
        TienIch.timStyle(lblMaLoaiSanPham);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblMaLoaiSanPham, gbc);

        txtMaLoaiSanPham = new StyledTextField(1, 10);
        txtMaLoaiSanPham.setPlaceholder("1, 2, 3, 4,...");
        TienIch.timStyle(txtMaLoaiSanPham);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(txtMaLoaiSanPham, gbc);

        JLabel lblLoaiSanPham = new JLabel("Loại sản phẩm");
        TienIch.timStyle(lblLoaiSanPham);
        gbc.gridx = 2;
        gbc.gridy = 5;
        add(lblLoaiSanPham, gbc);

        txtLoaiSanPham = new StyledTextField(1, 10);
        txtLoaiSanPham.setPlaceholder("Nhập loại sản phẩm");
        TienIch.timStyle(txtLoaiSanPham);
        gbc.gridx = 3;
        gbc.gridy = 5;
        add(txtLoaiSanPham, gbc);

        // Dòng 7: Mã nhân viên, Tên nhân viên
        TienIch.timStyle(lblMaNhanVien);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lblMaNhanVien, gbc);

        txtMaNhanVien = new StyledTextField(1, 10);
        txtMaNhanVien.setPlaceholder("1, 2, 3, 4,...");
        TienIch.timStyle(txtMaNhanVien);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(txtMaNhanVien, gbc);

        TienIch.timStyle(lblTenNhanVien);
        gbc.gridx = 2;
        gbc.gridy = 6;
        add(lblTenNhanVien, gbc);

        txtTenNhanVien = new StyledTextField(1, 10);
        txtTenNhanVien.setPlaceholder("Nhập tên nhân viên");
        TienIch.timStyle(txtTenNhanVien);
        gbc.gridx = 3;
        gbc.gridy = 6;
        add(txtTenNhanVien, gbc);

        // Dòng 8: Sắp xếp, Theo cột
        JLabel lblSapXep = new JLabel("Sắp xếp");
        TienIch.timStyle(lblSapXep);
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(lblSapXep, gbc);

        cboSapXep = new JComboBox<>(new String[]{"Tăng dần", "Giảm dần"});
        TienIch.timStyle(cboSapXep);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(cboSapXep, gbc);

        JLabel lblTheoCot = new JLabel("Theo cột");
        TienIch.timStyle(lblTheoCot);
        gbc.gridx = 2;
        gbc.gridy = 7;
        add(lblTheoCot, gbc);

        cboTheoCot = new JComboBox<>(new String[]{"Mã đơn hàng", "Mã Nhân Viên", "Ngày", "Thành Tiền"});
        TienIch.timStyle(cboTheoCot);
        gbc.gridx = 3;
        gbc.gridy = 7;
        add(cboTheoCot, gbc);

        TienIch.checkFromTo(dateChooserBatDau, dateChooserKetThuc);
        TienIch.sukienSoSanh(Min, Max);

        // Chỉ cho phép nhập số đối với các trường mã
        TienIch.chiduocnhapso(txtMaDonHang);
        TienIch.chiduocnhapso(txtMaKhachHang);
        TienIch.chiduocnhapso(txtMaKhuyenMai);
        TienIch.chiduocnhapso(txtMaSanPham);
        TienIch.chiduocnhapso(txtMaLoaiSanPham);
        TienIch.chiduocnhapso(txtMaNhanVien);
    }

    public void testt() {
        SearchFilterDTO search = new SearchFilterDTO();
        if (!txtMaDonHang.getText().isEmpty()) {
            search.setMaDH(Integer.parseInt(txtMaDonHang.getText()));
        }
        System.out.println("Bạn đã nhập " + search.getMaDH());
    }

    public void setVisibleNhanVien(boolean flag) {
        lblMaNhanVien.setVisible(flag);
        lblTenNhanVien.setVisible(flag);
        txtTenNhanVien.setVisible(flag);
        txtMaNhanVien.setVisible(flag);
    }

    public void setMaNhanVien(int maNV) {
        txtMaNhanVien.setText(maNV + "");
    }

    public SearchFilterDTO filter() {
        SearchFilterDTO search = new SearchFilterDTO();
        search.setMaDH(txtMaDonHang.getText().isEmpty() ? null : Integer.parseInt(txtMaDonHang.getText()));
        search.setMaKH(txtMaKhachHang.getText().isEmpty() ? null : Integer.parseInt(txtMaKhachHang.getText()));
        search.setMaKM(txtMaKhuyenMai.getText().isEmpty() ? null : Integer.parseInt(txtMaKhuyenMai.getText()));
        search.setMaLSP(txtMaLoaiSanPham.getText().isEmpty() ? null : Integer.parseInt(txtMaLoaiSanPham.getText()));
        search.setMaNV(txtMaNhanVien.getText().isEmpty() ? null : Integer.parseInt(txtMaNhanVien.getText()));
        search.setMaSP(txtMaSanPham.getText().isEmpty() ? null : Integer.parseInt(txtMaSanPham.getText()));
        java.util.Date utilStart = dateChooserBatDau.getDate();
        java.util.Date utilEnd = dateChooserKetThuc.getDate();

        search.setNgayBD(utilStart == null ? null : new java.sql.Date(utilStart.getTime()));
        search.setNgayKT(utilEnd == null ? null : new java.sql.Date(utilEnd.getTime()));
                
        search.setPhuongThucTT((String) cboPhuongThucThanhToan.getSelectedItem());
        search.setSort((String) cboSapXep.getSelectedItem());
        search.setTenCot((String) cboTheoCot.getSelectedItem());
        search.setTenKH(txtTenKhachHang.getText().isEmpty() ? null : txtTenKhachHang.getText());
        search.setTenKM(txtTenKhuyenMai.getText().isEmpty() ? null : txtTenKhuyenMai.getText());
        search.setTenLSP(txtLoaiSanPham.getText().isEmpty() ? null : txtLoaiSanPham.getText());
        search.setTenNV(txtTenNhanVien.getText().isEmpty() ? null : txtTenNhanVien.getText());
        search.setTenSP(txtTenSanPham.getText().isEmpty() ? null : txtTenSanPham.getText());
        search.setTongTienMax((Integer) max.getValue());
        search.setTongTienMin((Integer) min.getValue());
        return search;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel Tìm Thống Kê");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelTimThK a = new PanelTimThK();
        a.setVisibleNhanVien(false);
        frame.add(a);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}