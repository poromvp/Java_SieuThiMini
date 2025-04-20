package GUI.FormWareHouse;

import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.StyledTable;
import BLL.NhapHangBLL;
import DTO.PhieuNhapHangDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FormImport extends JPanel {
    private NhapHangBLL nhapHangBLL = new NhapHangBLL();
    private StyledTable table;

    public FormImport() {
        setLayout(new BorderLayout());
        setBackground(Color.white);

        // Panel nhập thông tin
        JPanel nhapPnl = new JPanel();
        nhapPnl.setLayout(new GridLayout(4, 2, 10, 10));
        nhapPnl.setBorder(BorderFactory.createTitledBorder("Thông tin đơn hàng nhập vào"));

        JLabel lb1 = new JLabel("Tên đơn hàng");
        StyledTextField t1 = new StyledTextField();
        JLabel lb2 = new JLabel("Mã nhân viên");
        StyledTextField t2 = new StyledTextField();
        JLabel lb3 = new JLabel("Mã nhà cung cấp");
        StyledTextField t3 = new StyledTextField();
        JButton btn1 = new JButton("Thêm");
        JPanel panelNhap = new JPanel();
        panelNhap.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelNhap.add(btn1);

        nhapPnl.add(lb1);
        nhapPnl.add(t1);
        nhapPnl.add(lb2);
        nhapPnl.add(t2);
        nhapPnl.add(lb3);
        nhapPnl.add(t3);
        nhapPnl.add(panelNhap);

        add(nhapPnl, BorderLayout.NORTH);

        // Thêm sự kiện cho nút Thêm
        btn1.addActionListener(e -> {
            try {
                // Lấy dữ liệu từ các trường nhập
                String tenPNH = t1.getText().trim();
                int maNV = Integer.parseInt(t2.getText().trim());
                int maNCC = Integer.parseInt(t3.getText().trim());

                // Tạo đối tượng PhieuNhapHangDTO
                PhieuNhapHangDTO phieuNhap = new PhieuNhapHangDTO();
                phieuNhap.setTenPNH(tenPNH);
                phieuNhap.setMaNV(maNV);
                phieuNhap.setMaNCC(maNCC);
                phieuNhap.setNgayNhap(new java.util.Date());
                phieuNhap.setTrangThai("FINISHED");

                // Mở FormAddImport
                JDialog addImportDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm chi tiết đơn nhập hàng", true);
                addImportDialog.setSize(800, 600);
                addImportDialog.setLayout(new BorderLayout());
                addImportDialog.setLocationRelativeTo(null);

                FormAddImport addImportPanel = new FormAddImport(phieuNhap, this::refreshTable);
                addImportDialog.add(addImportPanel, BorderLayout.CENTER);
                addImportDialog.setVisible(true);

                // Xóa nội dung các trường nhập sau khi mở FormAddImport
                t1.setText("");
                t2.setText("");
                t3.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho mã nhân viên và mã nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // Tạo bảng
        String[] headerCol = {"Mã đơn nhập hàng", "Tên đơn", "Ngày nhập", "Nhân viên", "Nhà cung cấp", "Trạng thái"};
        table = new StyledTable(convertDTOToArray(nhapHangBLL.getAllPhieuNhapHang()), headerCol);
        table.setEditable(false);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        int maPNH = (int) table.getValueAt(row, 0);
                        showImportDetail(maPNH);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Panel nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton deleteButton = new JButton("Xóa đơn hàng");
        JButton refreshButton = new JButton("Làm mới");

        deleteButton.addActionListener(e -> deleteSelectedImport());
        refreshButton.addActionListener(e -> refreshTable());

        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private Object[][] convertDTOToArray(ArrayList<PhieuNhapHangDTO> list) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<PhieuNhapHangDTO> filteredList = new ArrayList<>();
        for (PhieuNhapHangDTO p : list) {
            if (!p.getTrangThai().equals("DELETED")) {
                filteredList.add(p);
            }
        }
        Object[][] data = new Object[filteredList.size()][6];
        for (int i = 0; i < filteredList.size(); i++) {
            PhieuNhapHangDTO p = filteredList.get(i);
            data[i][0] = p.getMaPNH();
            data[i][1] = p.getTenPNH();
            data[i][2] = dateFormat.format(p.getNgayNhap());
            data[i][3] = p.getMaNV();
            data[i][4] = p.getMaNCC();
            data[i][5] = p.getTrangThai();
        }
        return data;
    }
    // Cần học lại
    private void refreshTable() {
        ArrayList<PhieuNhapHangDTO> list = nhapHangBLL.getAllPhieuNhapHang();
        if (list == null || list.isEmpty()) {
            System.out.println("Không có dữ liệu phiếu nhập hàng để hiển thị!");
        } else {
            System.out.println("Số lượng phiếu nhập hàng: " + list.size());
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa các hàng hiện tại
        Object[][] data = convertDTOToArray(list);
        for (Object[] row : data) {
            model.addRow(row); // Thêm từng hàng mới
        }
    }

    private void showImportDetail(int maPNH) {
        JDialog detailDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Chi tiết đơn nhập hàng", true);
        detailDialog.setSize(800, 500);
        detailDialog.setLayout(new BorderLayout());
        detailDialog.setLocationRelativeTo(null);

        FormImportDetail detailPanel = new FormImportDetail(maPNH);
        detailDialog.add(detailPanel, BorderLayout.CENTER);
        detailDialog.setVisible(true);
    }

    private void deleteSelectedImport() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn xóa đơn nhập hàng này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                int maPNH = (int) table.getValueAt(row, 0);
                if (nhapHangBLL.deletePhieuNhapHang(maPNH)) {
                    refreshTable();
                    JOptionPane.showMessageDialog(this, "Xóa đơn nhập hàng thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa đơn nhập hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đơn nhập hàng để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Quản lý nhập hàng");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 600);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);

        FormImport test = new FormImport();
        f.add(test, BorderLayout.CENTER);
        f.setVisible(true);
    }
}