package GUI.Admin_PanelThongKe;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.TienIch;
import java.awt.*;
import java.util.Date;

public class PanelTimKH extends JPanel {

    private StyledTextField txtMaThanhVien, txtTenThanhVien, txtDiaChi, txtSoDienThoai, txtMaDonHang;
    private JDateChooser dateSinhFrom, dateSinhTo, dateHanTheFrom, dateHanTheTo;
    private JSpinner minTongDonHang, maxTongDonHang, minTongChiTieu, maxTongChiTieu, minDiemTichLuy, maxDiemTichLuy;
    private JComboBox<String> cbSapXep, cbTheoCot;

    public PanelTimKH() {
        setBackground(new Color(33, 58, 89));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH;

        Date today = new Date(System.currentTimeMillis());

        // Dòng 0: Mã thành viên, Tên thành viên, Địa chỉ
        JLabel lbMaThanhVien = new JLabel("Mã thành viên");
        TienIch.timStyle(lbMaThanhVien);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lbMaThanhVien, gbc);

        txtMaThanhVien = new StyledTextField(1, 10);
        txtMaThanhVien.setPlaceholder("Nhập mã thành viên");
        TienIch.timStyle(txtMaThanhVien);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtMaThanhVien, gbc);

        JLabel lbTenThanhVien = new JLabel("Tên thành viên");
        TienIch.timStyle(lbTenThanhVien);
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(lbTenThanhVien, gbc);

        txtTenThanhVien = new StyledTextField(1, 10);
        txtTenThanhVien.setPlaceholder("Nhập tên thành viên");
        TienIch.timStyle(txtTenThanhVien);
        gbc.gridx = 3;
        gbc.gridy = 0;
        add(txtTenThanhVien, gbc);

        JLabel lbDiaChi = new JLabel("Địa chỉ");
        TienIch.timStyle(lbDiaChi);
        gbc.gridx = 4;
        gbc.gridy = 0;
        add(lbDiaChi, gbc);

        txtDiaChi = new StyledTextField(1, 10);
        txtDiaChi.setPlaceholder("Nhập địa chỉ");
        TienIch.timStyle(txtDiaChi);
        gbc.gridx = 5;
        gbc.gridy = 0;
        add(txtDiaChi, gbc);

        // Dòng 1: Ngày sinh từ, Ngày sinh đến, ảnh gif
        JLabel lbNgaySinhTu = new JLabel("Ngày sinh từ");
        TienIch.timStyle(lbNgaySinhTu);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lbNgaySinhTu, gbc);

        dateSinhFrom = new JDateChooser();
        dateSinhFrom.setMaxSelectableDate(today);
        dateSinhFrom.setDateFormatString("dd/MM/yyyy");
        TienIch.checkngaynhaptutay(dateSinhFrom, today);
        TienIch.timStyle(dateSinhFrom);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(dateSinhFrom, gbc);

        JLabel lbNgaySinhDen = new JLabel("Ngày sinh đến");
        TienIch.timStyle(lbNgaySinhDen);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(lbNgaySinhDen, gbc);

        dateSinhTo = new JDateChooser();
        dateSinhTo.setMaxSelectableDate(today);
        dateSinhTo.setDateFormatString("dd/MM/yyyy");
        TienIch.checkngaynhaptutay(dateSinhTo, today);
        TienIch.timStyle(dateSinhTo);
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(dateSinhTo, gbc);

        // GIF - chiếm 2 cột, 7 dòng
        JLabel gifIMG = new JLabel();
        TienIch.anhGif(gifIMG, "searching.gif", 390, 290);
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridheight = 7;
        gbc.gridwidth = 2;
        add(gifIMG, gbc);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        // Dòng 2: Số điện thoại, Mã đơn hàng
        JLabel lbSoDienThoai = new JLabel("Số điện thoại");
        TienIch.timStyle(lbSoDienThoai);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lbSoDienThoai, gbc);

        txtSoDienThoai = new StyledTextField(1, 10);
        txtSoDienThoai.setPlaceholder("Nhập số điện thoại");
        TienIch.timStyle(txtSoDienThoai);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtSoDienThoai, gbc);

        JLabel lbMaDonHang = new JLabel("Mã đơn hàng");
        TienIch.timStyle(lbMaDonHang);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(lbMaDonHang, gbc);

        txtMaDonHang = new StyledTextField(1, 10);
        txtMaDonHang.setPlaceholder("Nhập mã đơn hàng");
        TienIch.timStyle(txtMaDonHang);
        gbc.gridx = 3;
        gbc.gridy = 2;
        add(txtMaDonHang, gbc);

        // Dòng 3: Tổng đơn hàng từ, Tổng đơn hàng đến
        JLabel lbTongDonHangTu = new JLabel("Tổng đơn hàng từ");
        TienIch.timStyle(lbTongDonHangTu);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lbTongDonHangTu, gbc);

        minTongDonHang = new JSpinner(new SpinnerNumberModel(0, 0, 1_000_000, 1));
        TienIch.timStyle(minTongDonHang);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(minTongDonHang, gbc);

        JLabel lbTongDonHangDen = new JLabel("Tổng đơn hàng đến");
        TienIch.timStyle(lbTongDonHangDen);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(lbTongDonHangDen, gbc);

        maxTongDonHang = new JSpinner(new SpinnerNumberModel(1_000_000, 0, 1_000_000, 1));
        TienIch.timStyle(maxTongDonHang);
        gbc.gridx = 3;
        gbc.gridy = 3;
        add(maxTongDonHang, gbc);

        // Dòng 4: Tổng chi tiêu từ, Tổng chi tiêu đến
        JLabel lbTongChiTieuTu = new JLabel("Tổng chi tiêu từ");
        TienIch.timStyle(lbTongChiTieuTu);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lbTongChiTieuTu, gbc);

        minTongChiTieu = new JSpinner(new SpinnerNumberModel(0, 0, 1_000_000_000, 1000));
        TienIch.timStyle(minTongChiTieu);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(minTongChiTieu, gbc);

        JLabel lbTongChiTieuDen = new JLabel("Tổng chi tiêu đến");
        TienIch.timStyle(lbTongChiTieuDen);
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(lbTongChiTieuDen, gbc);

        maxTongChiTieu = new JSpinner(new SpinnerNumberModel(1_000_000_000, 0, 1_000_000_000, 1000));
        TienIch.timStyle(maxTongChiTieu);
        gbc.gridx = 3;
        gbc.gridy = 4;
        add(maxTongChiTieu, gbc);

        // Dòng 5: Điểm tích lũy từ, Điểm tích lũy đến
        JLabel lbDiemTichLuyTu = new JLabel("Điểm tích lũy từ");
        TienIch.timStyle(lbDiemTichLuyTu);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lbDiemTichLuyTu, gbc);

        minDiemTichLuy = new JSpinner(new SpinnerNumberModel(0, 0, 1_000_000, 1));
        TienIch.timStyle(minDiemTichLuy);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(minDiemTichLuy, gbc);

        JLabel lbDiemTichLuyDen = new JLabel("Điểm tích lũy đến");
        TienIch.timStyle(lbDiemTichLuyDen);
        gbc.gridx = 2;
        gbc.gridy = 5;
        add(lbDiemTichLuyDen, gbc);

        maxDiemTichLuy = new JSpinner(new SpinnerNumberModel(1_000_000, 0, 1_000_000, 1));
        TienIch.timStyle(maxDiemTichLuy);
        gbc.gridx = 3;
        gbc.gridy = 5;
        add(maxDiemTichLuy, gbc);

        // Dòng 6: Hạn thẻ từ, Hạn thẻ đến
        JLabel lbHanTheTu = new JLabel("Hạn thẻ từ");
        TienIch.timStyle(lbHanTheTu);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lbHanTheTu, gbc);

        dateHanTheFrom = new JDateChooser();
        dateHanTheFrom.setDateFormatString("dd/MM/yyyy");
        TienIch.timStyle(dateHanTheFrom);
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(dateHanTheFrom, gbc);

        JLabel lbHanTheDen = new JLabel("Hạn thẻ đến");
        TienIch.timStyle(lbHanTheDen);
        gbc.gridx = 2;
        gbc.gridy = 6;
        add(lbHanTheDen, gbc);

        dateHanTheTo = new JDateChooser();
        dateHanTheTo.setDateFormatString("dd/MM/yyyy");
        TienIch.timStyle(dateHanTheTo);
        gbc.gridx = 3;
        gbc.gridy = 6;
        add(dateHanTheTo, gbc);

        // Dòng 7: Sắp xếp, Theo cột
        JLabel lbSapXep = new JLabel("Sắp xếp");
        TienIch.timStyle(lbSapXep);
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(lbSapXep, gbc);

        cbSapXep = new JComboBox<>(new String[]{"Tăng dần", "Giảm dần"});
        TienIch.timStyle(cbSapXep);
        gbc.gridx = 1;
        gbc.gridy = 7;
        add(cbSapXep, gbc);

        JLabel lbTheoCot = new JLabel("Theo cột");
        TienIch.timStyle(lbTheoCot);
        gbc.gridx = 2;
        gbc.gridy = 7;
        add(lbTheoCot, gbc);

        cbTheoCot = new JComboBox<>(new String[]{"Mã khách hàng", "Tên khách hàng", "Ngày sinh", "Tổng đơn hàng", "Tổng chi tiêu", "Điểm tích lũy", "Hạn thẻ"});
        TienIch.timStyle(cbTheoCot);
        gbc.gridx = 3;
        gbc.gridy = 7;
        add(cbTheoCot, gbc);
    }
}