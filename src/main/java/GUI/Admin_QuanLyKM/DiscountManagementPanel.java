package GUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.ChiTietKhuyenMaiBLL;
import BLL.KhuyenMaiBLL;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.KhuyenMaiDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTable;
import GUI.ComponentCommon.StyledTextField;

public class DiscountManagementPanel extends JPanel {
    private StyledTable discountTable;
    private DefaultTableModel tableModel;
    private StyledTextField txtMaKM, txtTenKM, txtNgayBD, txtNgayKT, txtTrangThai;
    private ButtonCustom btnAdd, btnEdit, btnDelete, btnClear;
    private JDialog detailDialog;

    public DiscountManagementPanel() {
        setLayout(new BorderLayout());
        initComponents();
        loadDiscounts();
    }

    private void initComponents() {
        // Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and TextFields
        String[] labels = {"Mã KM:", "Tên KM:", "Ngày Bắt Đầu:", "Ngày Kết Thúc:", "Trạng Thái:"};
        txtMaKM = new StyledTextField(200, 30);
        txtTenKM = new StyledTextField(200, 30);
        txtNgayBD = new StyledTextField(200, 30);
        txtNgayKT = new StyledTextField(200, 30);
        txtTrangThai = new StyledTextField(200, 30);

        StyledTextField[] fields = {txtMaKM, txtTenKM, txtNgayBD, txtNgayKT, txtTrangThai};
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            inputPanel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            inputPanel.add(fields[i], gbc);
        }

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnAdd = new ButtonCustom("Thêm","add",14,20,20);
        btnEdit = new ButtonCustom("Chỉnh sửa","edit",14,20,20);
        btnDelete = new ButtonCustom("Xóa","del",14,20,20);
        btnClear = new ButtonCustom("Làm mới","reset",14,20,20);


        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        // Table
        String[] columns = {"Mã KM", "Tên KM", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Trạng Thái"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        discountTable = new StyledTable(new Object[][] {}, columns);
        discountTable.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(discountTable);

        // Add components to panel
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Event Listeners
        btnAdd.addActionListener(e -> addDiscount());
        btnEdit.addActionListener(e -> editDiscount());
        btnDelete.addActionListener(e -> deleteDiscount());
        btnClear.addActionListener(e -> clearForm());


        // Double-click to view details
        discountTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && discountTable.getSelectedRow() >= 0) {
                    viewDetails();
                }
            }
        });

        // Select row to populate form
        discountTable.getSelectionModel().addListSelectionListener(e -> {
            if (discountTable.getSelectedRow() >= 0) {
                int row = discountTable.getSelectedRow();
                txtMaKM.setText(tableModel.getValueAt(row, 0).toString());
                txtTenKM.setText(tableModel.getValueAt(row, 1).toString());
                txtNgayBD.setText(tableModel.getValueAt(row, 2).toString());
                txtNgayKT.setText(tableModel.getValueAt(row, 3).toString());
                txtTrangThai.setText(tableModel.getValueAt(row, 4).toString());
            }
        });
    }

    private void loadDiscounts() {
        tableModel.setRowCount(0);
        ArrayList<KhuyenMaiDTO> discounts = KhuyenMaiBLL.getAllDiscounts();
        for (KhuyenMaiDTO km : discounts) {
            tableModel.addRow(new Object[]{
                    km.getMaKM(),
                    km.getTenKM(),
                    km.getNgayBD(),
                    km.getNgayKT(),
                    km.getTrangThai()
            });
        }
    }

    private void addDiscount() {
        try {
            KhuyenMaiDTO km = createDiscountFromInput();
            if (KhuyenMaiBLL.addDiscount(km)) {
                JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thành công!");
                loadDiscounts();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm khuyến mãi thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ!");
        }
    }

    private void editDiscount() {
        try {
            KhuyenMaiDTO km = createDiscountFromInput();
            if (KhuyenMaiBLL.updateDiscount(km)) {
                JOptionPane.showMessageDialog(this, "Sửa khuyến mãi thành công!");
                loadDiscounts();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa khuyến mãi thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ!");
        }
    }

    private void deleteDiscount() {
        int selectedRow = discountTable.getSelectedRow();
        if (selectedRow >= 0) {
            int maKM = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa khuyến mãi này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (KhuyenMaiBLL.deleteDiscount(maKM)) {
                    JOptionPane.showMessageDialog(this, "Xóa khuyến mãi thành công!");
                    loadDiscounts();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa khuyến mãi thất bại!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khuyến mãi để xóa!");
        }
    }

    private void clearForm() {
        txtMaKM.setText("");
        txtTenKM.setText("");
        txtNgayBD.setText("");
        txtNgayKT.setText("");
        txtTrangThai.setText("");
        discountTable.clearSelection();
    }

    private void viewDetails() {
        int selectedRow = discountTable.getSelectedRow();
        if (selectedRow >= 0) {
            int maKM = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            ArrayList<ChiTietKhuyenMaiDTO> details = ChiTietKhuyenMaiBLL.getDiscountDetailsByDiscountId(maKM);

            // Create detail dialog
            detailDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Chi Tiết Khuyến Mãi", true);
            detailDialog.setSize(600, 400);
            detailDialog.setLocationRelativeTo(this);
            detailDialog.setLayout(new BorderLayout());

            // Detail table
            String[] columns = {"Mã KM", "Mã SP", "Tỉ Lệ Giảm", "Trạng Thái"};
            DefaultTableModel detailModel = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            StyledTable detailTable = new StyledTable(new Object[][] {}, columns);
            detailTable.setModel(detailModel);

            for (ChiTietKhuyenMaiDTO detail : details) {
                detailModel.addRow(new Object[]{
                        detail.getMaKM(),
                        detail.getMaSP(),
                        detail.getTiLeGiam(),
                        detail.getTrangThai()
                });
            }

            JScrollPane scrollPane = new JScrollPane(detailTable);
            detailDialog.add(scrollPane, BorderLayout.CENTER);

            // Close button
            ButtonCustom btnClose = new ButtonCustom("Đóng", 16, "red");
            btnClose.addActionListener(e -> detailDialog.dispose());
            JPanel btnPanel = new JPanel(new FlowLayout());
            btnPanel.add(btnClose);
            detailDialog.add(btnPanel, BorderLayout.SOUTH);

            detailDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khuyến mãi để xem chi tiết!");
        }
    }

    private KhuyenMaiDTO createDiscountFromInput() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return new KhuyenMaiDTO(
                Integer.parseInt(txtMaKM.getText().trim()),
                txtTenKM.getText().trim(),
                new Date(sdf.parse(txtNgayKT.getText().trim()).getTime()),
                new Date(sdf.parse(txtNgayBD.getText().trim()).getTime()),
                txtTrangThai.getText().trim()
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản Lý Khuyến Mãi");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.add(new DiscountManagementPanel());
            frame.setVisible(true);
        });
    }
}