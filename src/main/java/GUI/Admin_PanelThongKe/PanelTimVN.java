package GUI.Admin_PanelThongKe;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import BLL.BaoCaoNhanVienBLL;
import DTO.NhanVienDTO;
import DTO.SearchNhanVienDTO;
import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.TienIch;
import java.awt.*;
import java.util.ArrayList;
import java.sql.Date;

public class PanelTimVN extends JPanel {

        private StyledTextField txtMaNV, txtTenNV, txtDiaChi, txtSoDienThoai, txtMaDonHang;
        private JDateChooser dateSinhFrom, dateSinhTo;
        private JComboBox<String> cbChucVu, cbSapXep, cbTheoCot;
        private JSpinner minTongDonHang, maxTongDonHang, minDoanhSo, maxDoanhSo, minLuong, maxLuong;

        public PanelTimVN() {
                setBackground(new Color(33, 58, 89));
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 10, 5, 10);
                gbc.fill = GridBagConstraints.BOTH;

                Date today = new Date(System.currentTimeMillis());

                // Dòng 0: Mã nhân viên, Tên nhân viên, Địa chỉ, ảnh gif
                JLabel lbMaNV = new JLabel("Mã nhân viên");
                TienIch.timStyle(lbMaNV);
                gbc.gridx = 0;
                gbc.gridy = 0;
                add(lbMaNV, gbc);

                txtMaNV = new StyledTextField(1, 10);
                txtMaNV.setPlaceholder("Nhập mã nhân viên");
                TienIch.timStyle(txtMaNV);
                gbc.gridx = 1;
                gbc.gridy = 0;
                add(txtMaNV, gbc);

                JLabel lbTenNV = new JLabel("Tên nhân viên");
                TienIch.timStyle(lbTenNV);
                gbc.gridx = 2;
                gbc.gridy = 0;
                add(lbTenNV, gbc);

                txtTenNV = new StyledTextField(1, 10);
                txtTenNV.setPlaceholder("Nhập tên nhân viên");
                TienIch.timStyle(txtTenNV);
                gbc.gridx = 3;
                gbc.gridy = 0;
                add(txtTenNV, gbc);

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

                // GIF - chiếm 2 cột, 5 dòng
                JLabel gifIMG = new JLabel();
                TienIch.anhGif(gifIMG, "searching.gif", 320, 180);
                gbc.gridx = 4;
                gbc.gridy = 2;
                gbc.gridheight = 5;
                gbc.gridwidth = 2;
                add(gifIMG, gbc);
                gbc.gridheight = 1;
                gbc.gridwidth = 1;

                // Dòng 1: Ngày sinh từ, Ngày sinh đến, Chức vụ
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

                JLabel lbChucVu = new JLabel("Chức vụ");
                TienIch.timStyle(lbChucVu);
                gbc.gridx = 4;
                gbc.gridy = 1;
                add(lbChucVu, gbc);

                cbChucVu = new JComboBox<>(new String[] { "TẤT CẢ", "ADMIN", "QUẢN LÝ KHO", "NHÂN VIÊN" });
                TienIch.timStyle(cbChucVu);
                gbc.gridx = 5;
                gbc.gridy = 1;
                add(cbChucVu, gbc);

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

                maxTongDonHang = new JSpinner(new SpinnerNumberModel(0, 0, 1_000_000, 1));
                TienIch.timStyle(maxTongDonHang);
                gbc.gridx = 3;
                gbc.gridy = 3;
                add(maxTongDonHang, gbc);

                // Dòng 4: Doanh số bán hàng từ, Doanh số bán hàng đến
                JLabel lbDoanhSoTu = new JLabel("Doanh số từ");
                TienIch.timStyle(lbDoanhSoTu);
                gbc.gridx = 0;
                gbc.gridy = 4;
                add(lbDoanhSoTu, gbc);

                minDoanhSo = new JSpinner(new SpinnerNumberModel(0, 0, 1_000_000_000, 1000));
                TienIch.timStyle(minDoanhSo);
                gbc.gridx = 1;
                gbc.gridy = 4;
                add(minDoanhSo, gbc);

                JLabel lbDoanhSoDen = new JLabel("Doanh số đến");
                TienIch.timStyle(lbDoanhSoDen);
                gbc.gridx = 2;
                gbc.gridy = 4;
                add(lbDoanhSoDen, gbc);

                maxDoanhSo = new JSpinner(new SpinnerNumberModel(0, 0, 1_000_000_000, 1000));
                TienIch.timStyle(maxDoanhSo);
                gbc.gridx = 3;
                gbc.gridy = 4;
                add(maxDoanhSo, gbc);

                // Dòng 5: Lương từ, Lương đến
                JLabel lbLuongTu = new JLabel("Lương từ");
                TienIch.timStyle(lbLuongTu);
                gbc.gridx = 0;
                gbc.gridy = 5;
                add(lbLuongTu, gbc);

                minLuong = new JSpinner(new SpinnerNumberModel(0, 0, 100_000_000, 1000));
                TienIch.timStyle(minLuong);
                gbc.gridx = 1;
                gbc.gridy = 5;
                add(minLuong, gbc);

                JLabel lbLuongDen = new JLabel("Lương đến");
                TienIch.timStyle(lbLuongDen);
                gbc.gridx = 2;
                gbc.gridy = 5;
                add(lbLuongDen, gbc);

                maxLuong = new JSpinner(new SpinnerNumberModel(0, 0, 100_000_000, 1000));
                TienIch.timStyle(maxLuong);
                gbc.gridx = 3;
                gbc.gridy = 5;
                add(maxLuong, gbc);

                // Dòng 6: Sắp xếp, Theo cột
                JLabel lbSapXep = new JLabel("Sắp xếp");
                TienIch.timStyle(lbSapXep);
                gbc.gridx = 0;
                gbc.gridy = 6;
                add(lbSapXep, gbc);

                cbSapXep = new JComboBox<>(new String[] { "Tăng dần", "Giảm dần" });
                TienIch.timStyle(cbSapXep);
                gbc.gridx = 1;
                gbc.gridy = 6;
                add(cbSapXep, gbc);

                JLabel lbTheoCot = new JLabel("Theo cột");
                TienIch.timStyle(lbTheoCot);
                gbc.gridx = 2;
                gbc.gridy = 6;
                add(lbTheoCot, gbc);

                cbTheoCot = new JComboBox<>(
                                new String[] { "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Tổng đơn hàng",
                                                "Doanh số", "Lương" });
                TienIch.timStyle(cbTheoCot);
                gbc.gridx = 3;
                gbc.gridy = 6;
                add(cbTheoCot, gbc);
        }

        public ArrayList<NhanVienDTO> ketqua(Date from, Date to) {
                int maNV = txtMaNV.getText().isEmpty() ? 0 : Integer.parseInt(txtMaNV.getText()),
                                maDH = txtMaDonHang.getText().isEmpty() ? 0 : Integer.parseInt(txtMaDonHang.getText());
                Date ngayTu = dateSinhFrom.getDate() != null ? new Date(dateSinhFrom.getDate().getTime()) : null,
                                ngayDen = dateSinhTo.getDate() != null ? new Date(dateSinhTo.getDate().getTime())
                                                : null;
                SearchNhanVienDTO search = new SearchNhanVienDTO(
                                maNV,
                                txtTenNV.getText(),
                                txtDiaChi.getText(),
                                ngayTu,
                                ngayDen,
                                (String) cbChucVu.getSelectedItem(),
                                txtSoDienThoai.getText(),
                                maDH,
                                (int) minTongDonHang.getValue(),
                                (int) maxTongDonHang.getValue(),
                                (int) minDoanhSo.getValue(),
                                (int) maxDoanhSo.getValue(),
                                (int) minLuong.getValue(),
                                (int) maxLuong.getValue(),
                                (String) cbSapXep.getSelectedItem(),
                                (String) cbTheoCot.getSelectedItem());
                return BaoCaoNhanVienBLL.TimTotNhat(search, from, to);
        }

        public ArrayList<NhanVienDTO> ketqua() {
                int maNV = txtMaNV.getText().isEmpty() ? 0 : Integer.parseInt(txtMaNV.getText()),
                                maDH = txtMaDonHang.getText().isEmpty() ? 0 : Integer.parseInt(txtMaDonHang.getText());
                Date ngayTu = dateSinhFrom.getDate() != null ? new Date(dateSinhFrom.getDate().getTime()) : null,
                                ngayDen = dateSinhTo.getDate() != null ? new Date(dateSinhTo.getDate().getTime())
                                                : null;
                SearchNhanVienDTO search = new SearchNhanVienDTO(
                                maNV,
                                txtTenNV.getText(),
                                txtDiaChi.getText(),
                                ngayTu,
                                ngayDen,
                                (String) cbChucVu.getSelectedItem(),
                                txtSoDienThoai.getText(),
                                maDH,
                                (int) minTongDonHang.getValue(),
                                (int) maxTongDonHang.getValue(),
                                (int) minDoanhSo.getValue(),
                                (int) maxDoanhSo.getValue(),
                                (int) minLuong.getValue(),
                                (int) maxLuong.getValue(),
                                (String) cbSapXep.getSelectedItem(),
                                (String) cbTheoCot.getSelectedItem());
                return BaoCaoNhanVienBLL.TimNhanVien(search);
        }

        public SearchNhanVienDTO trasearch() {
                int maNV = txtMaNV.getText().isEmpty() ? 0 : Integer.parseInt(txtMaNV.getText()),
                                maDH = txtMaDonHang.getText().isEmpty() ? 0 : Integer.parseInt(txtMaDonHang.getText());
                Date ngayTu = dateSinhFrom.getDate() != null ? new Date(dateSinhFrom.getDate().getTime()) : null,
                                ngayDen = dateSinhTo.getDate() != null ? new Date(dateSinhTo.getDate().getTime())
                                                : null;
                SearchNhanVienDTO search = new SearchNhanVienDTO(
                                maNV,
                                txtTenNV.getText().trim(),
                                txtDiaChi.getText().trim(),
                                ngayTu,
                                ngayDen,
                                (String) cbChucVu.getSelectedItem(),
                                txtSoDienThoai.getText().trim(),
                                maDH,
                                (int) minTongDonHang.getValue(),
                                (int) maxTongDonHang.getValue(),
                                (int) minDoanhSo.getValue(),
                                (int) maxDoanhSo.getValue(),
                                (int) minLuong.getValue(),
                                (int) maxLuong.getValue(),
                                (String) cbSapXep.getSelectedItem(),
                                (String) cbTheoCot.getSelectedItem());
                return search;
        }

        public ArrayList<String> stringsearch() {
                ArrayList<String> search = new ArrayList<>();
                int maNV = txtMaNV.getText().isEmpty() ? 0 : Integer.parseInt(txtMaNV.getText()),
                                maDH = txtMaDonHang.getText().isEmpty() ? 0 : Integer.parseInt(txtMaDonHang.getText());
                Date ngayTu = dateSinhFrom.getDate() != null ? new Date(dateSinhFrom.getDate().getTime()) : null,
                                ngayDen = dateSinhTo.getDate() != null ? new Date(dateSinhTo.getDate().getTime())
                                                : null;

                if (maNV != 0) {
                        search.add("Mã nhân viên: " + maNV);
                }
                if (!txtTenNV.getText().trim().isEmpty()) {
                        search.add("Tên nhân viên: " + txtTenNV.getText().trim());
                }
                if (!txtDiaChi.getText().trim().isEmpty()) {
                        search.add("Địa chỉ: " + txtDiaChi.getText().trim());
                }
                if (ngayTu != null && ngayDen != null) {
                        search.add("Ngày sinh từ: " + ngayTu + " đến: " + ngayDen);
                }
                search.add("Chức vụ: " + (String) cbChucVu.getSelectedItem());
                if (!txtSoDienThoai.getText().trim().isEmpty()) {
                        search.add("Số điện thoại: " + txtSoDienThoai.getText().trim());
                }
                if (maDH != 0) {
                        search.add("Mã đơn hàng: " + maDH);
                }
                if ((int) minTongDonHang.getValue() != 0 && (int) maxTongDonHang.getValue() != 0) {
                        search.add("Tổng đơn hàng thực hiện từ: " + (int) minTongDonHang.getValue() + " đến: "
                                        + (int) maxTongDonHang.getValue());
                }
                if ((int) minDoanhSo.getValue() != 0 && (int) maxDoanhSo.getValue() != 0) {
                        search.add("Doanh số bán hàng từ: " + (int) minDoanhSo.getValue() + " đến: "
                                        + (int) maxDoanhSo.getValue());
                }
                if ((int) minLuong.getValue() != 0 && (int) maxLuong.getValue() != 0) {
                        search.add("Lương từ: " + (int) minLuong.getValue() + " đến: " + (int) maxLuong.getValue());
                }
                search.add("Sắp xếp: " + (String) cbSapXep.getSelectedItem());
                search.add("Theo cột: " + (String) cbTheoCot.getSelectedItem());
                return search;
        }
}