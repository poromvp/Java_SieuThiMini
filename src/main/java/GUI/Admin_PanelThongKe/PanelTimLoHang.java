package GUI.Admin_PanelThongKe;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import BLL.BaoCaoKhoTongHopBLL;
import DTO.SearchLoHangDTO;
import DTO.PhieuNhapHangDTO;

import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.TienIch;
import java.awt.*;
import java.util.ArrayList;
import java.sql.Date;

public class PanelTimLoHang extends JPanel {

        private StyledTextField txtMaDonNhap, txtTenDonNhap, txtMaNV, txtTenNV, txtMaNCC, txtTenNCC;
        private StyledTextField txtMaLoHang, txtMaSP, txtTenSP;
        private JSpinner minTongGia, maxTongGia;
        private JDateChooser dateFrom, dateTo;
        private JComboBox<String> cbSapXep, cbTheoCot;

        public PanelTimLoHang() {
                setBackground(new Color(33, 58, 89));
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 10, 5, 10);
                gbc.fill = GridBagConstraints.BOTH;

                Date today = new Date(System.currentTimeMillis());

                // Mã đơn nhập
                JLabel lbMaDonNhap = new JLabel("Mã đơn nhập hàng");
                TienIch.timStyle(lbMaDonNhap);
                gbc.gridx = 0;
                gbc.gridy = 0;
                add(lbMaDonNhap, gbc);

                txtMaDonNhap = new StyledTextField(1, 10);
                txtMaDonNhap.setPlaceholder("Nhập mã đơn nhập hàng");
                TienIch.timStyle(txtMaDonNhap);
                gbc.gridx = 1;
                gbc.gridy = 0;
                add(txtMaDonNhap, gbc);

                // Tên đơn
                JLabel lbTenDon = new JLabel("Tên đơn");
                TienIch.timStyle(lbTenDon);
                gbc.gridx = 2;
                gbc.gridy = 0;
                add(lbTenDon, gbc);

                txtTenDonNhap = new StyledTextField(1, 10);
                txtTenDonNhap.setPlaceholder("Nhập tên đơn");
                TienIch.timStyle(txtTenDonNhap);
                gbc.gridx = 3;
                gbc.gridy = 0;
                add(txtTenDonNhap, gbc);

                // GIF - chiếm 2 cột, 8 dòng
                JLabel gifIMG = new JLabel();
                TienIch.anhGif(gifIMG, "searching.gif", 320, 320);
                gbc.gridx = 4;
                gbc.gridy = 0;
                gbc.gridheight = 8;
                gbc.gridwidth = 2;
                add(gifIMG, gbc);
                gbc.gridheight = 1;
                gbc.gridwidth = 1;

                // Ngày nhập từ
                JLabel lbTu = new JLabel("Ngày nhập từ");
                TienIch.timStyle(lbTu);
                gbc.gridx = 0;
                gbc.gridy = 1;
                add(lbTu, gbc);

                dateFrom = new JDateChooser();
                dateFrom.setMaxSelectableDate(today);
                dateFrom.setDateFormatString("dd/MM/yyyy");
                TienIch.checkngaynhaptutay(dateFrom, today);
                TienIch.timStyle(dateFrom);
                gbc.gridx = 1;
                gbc.gridy = 1;
                add(dateFrom, gbc);

                // Ngày nhập đến
                JLabel lbDen = new JLabel("Đến ngày");
                TienIch.timStyle(lbDen);
                gbc.gridx = 2;
                gbc.gridy = 1;
                add(lbDen, gbc);

                dateTo = new JDateChooser();
                dateTo.setMaxSelectableDate(today);
                dateTo.setDateFormatString("dd/MM/yyyy");
                TienIch.checkngaynhaptutay(dateTo, today);
                TienIch.timStyle(dateTo);
                gbc.gridx = 3;
                gbc.gridy = 1;
                add(dateTo, gbc);

                // Mã NV
                JLabel lbMaNV = new JLabel("Mã nhân viên");
                TienIch.timStyle(lbMaNV);
                gbc.gridx = 0;
                gbc.gridy = 2;
                add(lbMaNV, gbc);

                txtMaNV = new StyledTextField(1, 10);
                txtMaNV.setPlaceholder("Nhập mã nhân viên");
                TienIch.timStyle(txtMaNV);
                gbc.gridx = 1;
                gbc.gridy = 2;
                add(txtMaNV, gbc);

                // Tên NV
                JLabel lbTenNV = new JLabel("Tên nhân viên");
                TienIch.timStyle(lbTenNV);
                gbc.gridx = 2;
                gbc.gridy = 2;
                add(lbTenNV, gbc);

                txtTenNV = new StyledTextField(1, 10);
                txtTenNV.setPlaceholder("Nhập tên nhân viên");
                TienIch.timStyle(txtTenNV);
                gbc.gridx = 3;
                gbc.gridy = 2;
                add(txtTenNV, gbc);

                // Mã NCC
                JLabel lbMaNCC = new JLabel("Mã NCC");
                TienIch.timStyle(lbMaNCC);
                gbc.gridx = 0;
                gbc.gridy = 3;
                add(lbMaNCC, gbc);

                txtMaNCC = new StyledTextField(1, 10);
                txtMaNCC.setPlaceholder("Nhập mã NCC");
                TienIch.timStyle(txtMaNCC);
                gbc.gridx = 1;
                gbc.gridy = 3;
                add(txtMaNCC, gbc);

                // Tên NCC
                JLabel lbTenNCC = new JLabel("Tên NCC");
                TienIch.timStyle(lbTenNCC);
                gbc.gridx = 2;
                gbc.gridy = 3;
                add(lbTenNCC, gbc);

                txtTenNCC = new StyledTextField(1, 10);
                txtTenNCC.setPlaceholder("Nhập tên NCC");
                TienIch.timStyle(txtTenNCC);
                gbc.gridx = 3;
                gbc.gridy = 3;
                add(txtTenNCC, gbc);

                // Tổng giá từ
                JLabel lbGiaMin = new JLabel("Giá nhập từ");
                TienIch.timStyle(lbGiaMin);
                gbc.gridx = 0;
                gbc.gridy = 4;
                add(lbGiaMin, gbc);

                minTongGia = new JSpinner(new SpinnerNumberModel(0, 0, 1_000_000_000, 1000));
                TienIch.timStyle(minTongGia);
                gbc.gridx = 1;
                gbc.gridy = 4;
                add(minTongGia, gbc);

                // Tổng giá đến
                JLabel lbGiaMax = new JLabel("Giá nhập đến");
                TienIch.timStyle(lbGiaMax);
                gbc.gridx = 2;
                gbc.gridy = 4;
                add(lbGiaMax, gbc);

                maxTongGia = new JSpinner(new SpinnerNumberModel(0, 0, 1_000_000_000, 1000));
                TienIch.timStyle(maxTongGia);
                gbc.gridx = 3;
                gbc.gridy = 4;
                add(maxTongGia, gbc);

                // Mã SP
                JLabel lbMaSP = new JLabel("Mã sản phẩm");
                TienIch.timStyle(lbMaSP);
                gbc.gridx = 0;
                gbc.gridy = 5;
                add(lbMaSP, gbc);

                txtMaSP = new StyledTextField(1, 10);
                txtMaSP.setPlaceholder("Nhập mã SP");
                TienIch.timStyle(txtMaSP);
                gbc.gridx = 1;
                gbc.gridy = 5;
                add(txtMaSP, gbc);

                // Tên SP
                JLabel lbTenSP = new JLabel("Tên sản phẩm");
                TienIch.timStyle(lbTenSP);
                gbc.gridx = 2;
                gbc.gridy = 5;
                add(lbTenSP, gbc);

                txtTenSP = new StyledTextField(1, 10);
                txtTenSP.setPlaceholder("Nhập tên SP");
                TienIch.timStyle(txtTenSP);
                gbc.gridx = 3;
                gbc.gridy = 5;
                add(txtTenSP, gbc);

                // Mã lô hàng
                JLabel lbMaLo = new JLabel("Mã lô hàng");
                TienIch.timStyle(lbMaLo);
                gbc.gridx = 0;
                gbc.gridy = 6;
                add(lbMaLo, gbc);

                txtMaLoHang = new StyledTextField(1, 10);
                txtMaLoHang.setPlaceholder("Nhập mã lô hàng");
                TienIch.timStyle(txtMaLoHang);
                gbc.gridx = 1;
                gbc.gridy = 6;
                add(txtMaLoHang, gbc);

                // Sắp xếp + Theo cột
                JLabel lbSapXep = new JLabel("Sắp xếp");
                TienIch.timStyle(lbSapXep);
                gbc.gridx = 0;
                gbc.gridy = 7;
                add(lbSapXep, gbc);

                cbSapXep = new JComboBox<>(new String[] { "Tăng dần", "Giảm dần" });
                gbc.gridx = 1;
                gbc.gridy = 7;
                add(cbSapXep, gbc);

                JLabel lbTheoCot = new JLabel("Theo cột");
                TienIch.timStyle(lbTheoCot);
                gbc.gridx = 2;
                gbc.gridy = 7;
                add(lbTheoCot, gbc);

                cbTheoCot = new JComboBox<>(
                                new String[] { "Mã đơn nhập", "Ngày nhập", "Tổng giá", "Mã nhân viên",
                                                "Mã nhà cung cấp" });
                gbc.gridx = 3;
                gbc.gridy = 7;
                add(cbTheoCot, gbc);
        }

        public ArrayList<PhieuNhapHangDTO> ketqua() {
                int maDonNH = txtMaDonNhap.getText().isEmpty() ? 0 : Integer.parseInt(txtMaDonNhap.getText()),
                                maNV = txtMaNV.getText().isEmpty() ? 0 : Integer.parseInt(txtMaNV.getText()),
                                maNCC = txtMaNCC.getText().isEmpty() ? 0 : Integer.parseInt(txtMaNCC.getText()),
                                maSP = txtMaSP.getText().isEmpty() ? 0 : Integer.parseInt(txtMaSP.getText()),
                                maLH = txtMaLoHang.getText().isEmpty() ? 0 : Integer.parseInt(txtMaLoHang.getText());
                Date ngayTu = dateFrom.getDate() != null ? new Date(dateFrom.getDate().getTime()) : null,
                                ngayDen = dateTo.getDate() != null ? new Date(dateTo.getDate().getTime()) : null;
                SearchLoHangDTO search = new SearchLoHangDTO(
                                maDonNH,
                                txtTenDonNhap.getText(),
                                ngayTu,
                                ngayDen,
                                maNV,
                                txtTenNV.getText(),
                                maNCC,
                                txtTenNCC.getText(),
                                (int) minTongGia.getValue(),
                                (int) maxTongGia.getValue(),
                                maSP,
                                txtTenSP.getText(),
                                maLH,
                                (String) cbSapXep.getSelectedItem(),
                                (String) cbTheoCot.getSelectedItem());
                return BaoCaoKhoTongHopBLL.TimLoHang(search);
        }

        public SearchLoHangDTO trasearch() {
                int maDonNH = txtMaDonNhap.getText().isEmpty() ? 0 : Integer.parseInt(txtMaDonNhap.getText()),
                                maNV = txtMaNV.getText().isEmpty() ? 0 : Integer.parseInt(txtMaNV.getText()),
                                maNCC = txtMaNCC.getText().isEmpty() ? 0 : Integer.parseInt(txtMaNCC.getText()),
                                maSP = txtMaSP.getText().isEmpty() ? 0 : Integer.parseInt(txtMaSP.getText()),
                                maLH = txtMaLoHang.getText().isEmpty() ? 0 : Integer.parseInt(txtMaLoHang.getText());
                Date ngayTu = dateFrom.getDate() != null ? new Date(dateFrom.getDate().getTime()) : null,
                                ngayDen = dateTo.getDate() != null ? new Date(dateTo.getDate().getTime()) : null;
                SearchLoHangDTO search = new SearchLoHangDTO(
                                maDonNH,
                                txtTenDonNhap.getText(),
                                ngayTu,
                                ngayDen,
                                maNV,
                                txtTenNV.getText(),
                                maNCC,
                                txtTenNCC.getText(),
                                (int) minTongGia.getValue(),
                                (int) maxTongGia.getValue(),
                                maSP,
                                txtTenSP.getText(),
                                maLH,
                                (String) cbSapXep.getSelectedItem(),
                                (String) cbTheoCot.getSelectedItem());
                return search;
        }

        public ArrayList<String> stringsearch() {
                ArrayList<String> search = new ArrayList<>();
                int maDonNH = txtMaDonNhap.getText().isEmpty() ? 0 : Integer.parseInt(txtMaDonNhap.getText()),
                                maNV = txtMaNV.getText().isEmpty() ? 0 : Integer.parseInt(txtMaNV.getText()),
                                maNCC = txtMaNCC.getText().isEmpty() ? 0 : Integer.parseInt(txtMaNCC.getText()),
                                maSP = txtMaSP.getText().isEmpty() ? 0 : Integer.parseInt(txtMaSP.getText()),
                                maLH = txtMaLoHang.getText().isEmpty() ? 0 : Integer.parseInt(txtMaLoHang.getText());
                Date ngayTu = dateFrom.getDate() != null ? new Date(dateFrom.getDate().getTime()) : null,
                                ngayDen = dateTo.getDate() != null ? new Date(dateTo.getDate().getTime()) : null;
                if (maDonNH != 0) {
                        search.add("Mã đơn nhập hàng: " + maDonNH);
                }
                if (!txtTenDonNhap.getText().trim().isEmpty()) {
                        search.add("Tên đơn nhập hàng: " + txtTenDonNhap.getText().trim());
                }
                if (ngayTu != null && ngayDen != null) {
                        search.add("Ngày nhập từ: " + ngayTu + " đến: " + ngayDen);
                }
                if (maNV != 0) {
                        search.add("Mã nhân viên: " + maNV);
                }
                if (!txtTenNV.getText().trim().isEmpty()) {
                        search.add("Tên nhân viên: " + txtTenNV.getText().trim());
                }
                if (maNCC != 0) {
                        search.add("Mã nhà cung cấp: " + maNCC);
                }
                if (!txtTenNCC.getText().trim().isEmpty()) {
                        search.add("Tên nhà cung cấp: " + txtTenNCC.getText().trim());
                }
                if ((int) minTongGia.getValue() != 0 && (int) maxTongGia.getValue() != 0) {
                        search.add("Giá nhập từ: " + (int) minTongGia.getValue() + " đến: "
                                        + (int) maxTongGia.getValue());
                }
                if (maSP != 0) {
                        search.add("Mã sản phẩm: " + maSP);
                }
                if (!txtTenSP.getText().trim().isEmpty()) {
                        search.add("Tên sản phẩm: " + txtTenSP.getText().trim());
                }
                if (maLH != 0) {
                        search.add("Mã lô hàng: " + maLH);
                }
                search.add("Sắp xếp: " + (String) cbSapXep.getSelectedItem());
                search.add("Theo cột: " + (String) cbTheoCot.getSelectedItem());
                return search;
        }
}
