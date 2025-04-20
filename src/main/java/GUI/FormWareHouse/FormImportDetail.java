package GUI.FormWareHouse;

import BLL.ChiTietNhapHangBLL;
import BLL.NhapHangBLL;
import DTO.ChiTietPNHangDTO;
import DTO.PhieuNhapHangDTO;
import GUI.ComponentCommon.StyledTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FormImportDetail extends JPanel {
    private ChiTietNhapHangBLL chiTietBLL = new ChiTietNhapHangBLL();
    private NhapHangBLL nhapHangBLL = new NhapHangBLL();
    private int maPNH;
    private StyledTable table;

    public FormImportDetail(int maPNH) {
        this.maPNH = maPNH;
        setLayout(new BorderLayout());

        // Lấy thông tin phiếu nhập
        PhieuNhapHangDTO phieuNhap = nhapHangBLL.getPhieuNhapHangById(maPNH);

        // Panel thông tin chung
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin đơn nhập hàng"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        infoPanel.add(new JLabel("Mã đơn nhập hàng:"));
        infoPanel.add(new JLabel(String.valueOf(phieuNhap.getMaPNH())));
        infoPanel.add(new JLabel("Ngày nhập:"));
        infoPanel.add(new JLabel(dateFormat.format(phieuNhap.getNgayNhap())));
        infoPanel.add(new JLabel("Tổng tiền:"));
        double tongTien = chiTietBLL.calculateTongTien(maPNH);
        infoPanel.add(new JLabel(currencyFormat.format(tongTien)));

        add(infoPanel, BorderLayout.NORTH);

        // Tạo bảng chi tiết
        String[] headerCol = {"STT", "Mã sản phẩm", "Mã lô hàng", "Số lượng", "Giá nhập", "Thành tiền"};
        ArrayList<ChiTietPNHangDTO> chiTietList = chiTietBLL.getChiTietByMaPNH(maPNH);

        table = new StyledTable(convertDTOToArray(chiTietList), headerCol);
        table.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Panel nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton addButton = new JButton("Thêm sản phẩm");
        JButton deleteButton = new JButton("Xóa sản phẩm");

        addButton.addActionListener(e -> showAddProductDialog());
        deleteButton.addActionListener(e -> deleteSelectedProduct());

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private Object[][] convertDTOToArray(ArrayList<ChiTietPNHangDTO> list) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        Object[][] data = new Object[list.size()][6];

        for (int i = 0; i < list.size(); i++) {
            ChiTietPNHangDTO ct = list.get(i);
            double thanhTien = ct.getSoLuong() * ct.getGiaNhap();

            data[i][0] = i + 1;
            data[i][1] = ct.getMaSP();
            data[i][2] = ct.getMaLH();
            data[i][3] = ct.getSoLuong();
            data[i][4] = currencyFormat.format(ct.getGiaNhap());
            data[i][5] = currencyFormat.format(thanhTien);
        }
        return data;
    }

    private void showAddProductDialog() {
        JDialog addDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm sản phẩm", true);
        addDialog.setSize(400, 300);
        addDialog.setLayout(new GridLayout(5, 2, 10, 10));
        addDialog.setLocationRelativeTo(null);

        JLabel maSPLabel = new JLabel("Mã sản phẩm:");
        JTextField maSPField = new JTextField();
        JLabel maLHLabel = new JLabel("Mã lô hàng:");
        JTextField maLHField = new JTextField();
        JLabel soLuongLabel = new JLabel("Số lượng:");
        JTextField soLuongField = new JTextField();
        JLabel giaNhapLabel = new JLabel("Giá nhập:");
        JTextField giaNhapField = new JTextField();

        JButton addButton = new JButton("Thêm");
        JButton cancelButton = new JButton("Hủy");

        addButton.addActionListener(e -> {
            try {
                int maSP = Integer.parseInt(maSPField.getText().trim());
                int maLH = Integer.parseInt(maLHField.getText().trim());
                int soLuong = Integer.parseInt(soLuongField.getText().trim());
                double giaNhap = Double.parseDouble(giaNhapField.getText().trim());

                if (soLuong <= 0 || giaNhap <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng và giá nhập phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ChiTietPNHangDTO chiTiet = new ChiTietPNHangDTO();
                chiTiet.setMaPNH(maPNH);
                chiTiet.setMaSP(maSP);
                chiTiet.setMaLH(maLH);
                chiTiet.setSoLuong(soLuong);
                chiTiet.setGiaNhap(giaNhap);
                chiTiet.setTrangThai("ACTIVE");

                if (chiTietBLL.insertChiTietNhapHang(chiTiet)) {
                    refreshTable();
                    addDialog.dispose();
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> addDialog.dispose());

        addDialog.add(maSPLabel);
        addDialog.add(maSPField);
        addDialog.add(maLHLabel);
        addDialog.add(maLHField);
        addDialog.add(soLuongLabel);
        addDialog.add(soLuongField);
        addDialog.add(giaNhapLabel);
        addDialog.add(giaNhapField);
        addDialog.add(addButton);
        addDialog.add(cancelButton);

        addDialog.setVisible(true);
    }

    private void deleteSelectedProduct() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn xóa sản phẩm này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                ArrayList<ChiTietPNHangDTO> chiTietList = chiTietBLL.getChiTietByMaPNH(maPNH);
                if (row < chiTietList.size()) {
                    int maCTPNH = chiTietList.get(row).getMaCTPNH();
                    if (chiTietBLL.deleteChiTietNhapHang(maCTPNH)) {
                        refreshTable();
                        JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void refreshTable() {
        ArrayList<ChiTietPNHangDTO> chiTietList = chiTietBLL.getChiTietByMaPNH(maPNH);
        table.setModel(new DefaultTableModel(convertDTOToArray(chiTietList),
                new String[]{"STT", "Mã sản phẩm", "Mã lô hàng", "Số lượng", "Giá nhập", "Thành tiền"}));
    }

}