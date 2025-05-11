package GUI.FormWareHouse;

import BLL.NhaCungCapBLL;
import BLL.NhanVienBLL;
import DTO.NhaCungCapDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.StyledTable;
import BLL.NhapHangBLL;
import DTO.PhieuNhapHangDTO;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FormImport extends JPanel {
    private NhapHangBLL nhapHangBLL = new NhapHangBLL();
    private NhaCungCapBLL nhaCungCapBLL = new NhaCungCapBLL();
    private NhanVienBLL nhanVienBLL = new NhanVienBLL();
    private StyledTable table;
    private String maNV;

    public FormImport(String maNV) {
        this.maNV = maNV;
        setLayout(new BorderLayout());
        setBackground(Color.white);
        //Panel phia tren
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.white);
        northPanel.setLayout(new BorderLayout());
//        northPanel.setPreferredSize(new Dimension(5000,250));

        // Panel nhập thông tin
        JPanel nhapPnl = new JPanel();
        nhapPnl.setBackground(Color.white);
        nhapPnl.setLayout(new BorderLayout());
        nhapPnl.setBorder(BorderFactory.createTitledBorder("Thông tin đơn hàng nhập vào"));

        JLabel lb1 = new JLabel("Tên đơn hàng");
        StyledTextField t1 = new StyledTextField();
        JLabel lb3 = new JLabel("Nhà cung cấp");
        JComboBox<String> cbNhaCC = new JComboBox<>();
        cbNhaCC.setBackground(Color.white);
        ArrayList<NhaCungCapDTO> nhaCCList = nhaCungCapBLL.getList();
        for (NhaCungCapDTO nhaCC : nhaCCList) {
            if (nhaCC.getTrangThai().equals("ACTIVE")) {
                cbNhaCC.addItem(nhaCC.getMaNCC() + " - " + nhaCC.getTenNCC());
            }
        }
        cbNhaCC.setSelectedIndex(-1);

        ButtonCustom btnThem = new ButtonCustom("Thêm","add",16,20,20);
        JPanel panelNhap = new JPanel();
        panelNhap.setBackground(Color.white);
        panelNhap.setLayout(new GridLayout(2,2,5,5));


        JPanel btnAddPanel = new JPanel();
        btnAddPanel.setBackground(Color.white);
        btnAddPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnAddPanel.add(btnThem);

        panelNhap.add(lb1);
        panelNhap.add(t1);
        panelNhap.add(lb3);
        panelNhap.add(cbNhaCC);
        nhapPnl.add(panelNhap,BorderLayout.NORTH);
        nhapPnl.add(btnAddPanel,BorderLayout.SOUTH);

        //Panel tim kiem
        JPanel panelSearch = new JPanel();
        panelSearch.setLayout(new BorderLayout());
        panelSearch.setBorder(BorderFactory.createTitledBorder("Tìm kiếm: "));
        panelSearch.setBackground(Color.white);

        JPanel panelSearchInput = new JPanel();
        panelSearchInput.setLayout(new GridLayout(4,2,5,5));

        panelSearchInput.setBackground(Color.white);
        panelSearchInput.add(new JLabel("Mã đơn nhập hàng:"));
        StyledTextField maPNHSearch = new StyledTextField();
        panelSearchInput.add(maPNHSearch);
        panelSearchInput.add(new JLabel("Tên đơn nhập hàng:"));
        StyledTextField tenPNHSearch = new StyledTextField();
        panelSearchInput.add(tenPNHSearch);
        panelSearchInput.add(new JLabel("Từ:"));
        JDateChooser startDate = new JDateChooser();
        startDate.setDateFormatString("dd/MM/yyyy");
        panelSearchInput.add(startDate);

        panelSearchInput.add(new JLabel("Đến:"));
        JDateChooser endDate = new JDateChooser();
        endDate.setDateFormatString("dd/MM/yyyy");
        panelSearchInput.add(endDate);

        JPanel buttonPanelSearch = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanelSearch.setBackground(Color.white);
        ButtonCustom searchBtn = new ButtonCustom("Tìm kiếm", "search", 16, 20, 20);
        buttonPanelSearch.add(searchBtn);

        panelSearch.add(panelSearchInput,BorderLayout.NORTH);
        panelSearch.add(buttonPanelSearch,BorderLayout.SOUTH); // Thêm panel chứa nút vào GridLayout

        searchBtn.addActionListener(e -> {
            try {
                String maPNHText = maPNHSearch.getText().trim();
                String tenPNHText = tenPNHSearch.getText().trim();
                Date startDateValue = startDate.getDate();
                Date endDateValue = endDate.getDate();

                ArrayList<PhieuNhapHangDTO> list = nhapHangBLL.getAllPhieuNhapHang();
                ArrayList<PhieuNhapHangDTO> filteredList = new ArrayList<>();

                for (PhieuNhapHangDTO phieu : list) {
                    boolean match = true;

                    if (!maPNHText.isEmpty()) {
                        if (phieu.getMaPNH() != Integer.parseInt(maPNHText)) {
                            match = false;
                        }
                    }

                    if (!tenPNHText.isEmpty()) {
                        if (!phieu.getTenPNH().toLowerCase().contains(tenPNHText.toLowerCase())) {
                            match = false;
                        }
                    }


                    if (startDateValue != null && phieu.getNgayNhap().before(startDateValue)) {
                        match = false;
                    }
                    if (endDateValue != null && phieu.getNgayNhap().after(endDateValue)) {
                        match = false;
                    }

                    if (match) {
                        filteredList.add(phieu);
                    }
                }

                // Cập nhật bảng với danh sách đã lọc
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Xóa các hàng hiện tại
                Object[][] data = convertDTOToArray(filteredList);
                for (Object[] row : data) {
                    model.addRow(row); // Thêm từng hàng mới
                }

                if (filteredList.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu nhập hàng nào!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Mã phiếu nhập hàng phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });


        add(northPanel,BorderLayout.NORTH);
        northPanel.add(nhapPnl, BorderLayout.CENTER);
        northPanel.add(panelSearch,BorderLayout.EAST);

        // Su kien cho nut Them
        btnThem.addActionListener(e -> {
            try {
                String tenPNH = t1.getText().trim();
                int maNVValue = Integer.parseInt(maNV);
                String nhaCCSelected = (String) cbNhaCC.getSelectedItem();

                if (tenPNH.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Tên đơn hàng không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!nhanVienBLL.isNhanVienExists(maNVValue)) {
                    JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (nhaCCSelected == null) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int maNCC = Integer.parseInt(nhaCCSelected.split(" - ")[0]);

                // Tạo đối tượng PhieuNhapHangDTO
                PhieuNhapHangDTO phieuNhap = new PhieuNhapHangDTO();
                phieuNhap.setTenPNH(tenPNH);
                phieuNhap.setMaNV(maNVValue);
                phieuNhap.setMaNCC(maNCC);
                phieuNhap.setNgayNhap(new java.util.Date());
                phieuNhap.setTrangThai("FINISHED");

                // Mở FormAddImport
                JDialog addImportDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm chi tiết đơn nhập hàng", true);
                addImportDialog.setSize(1200, 800);
                addImportDialog.setLayout(new BorderLayout());
                addImportDialog.setLocationRelativeTo(null);

                FormAddImport addImportPanel = new FormAddImport(phieuNhap, this::refreshTable);
                addImportDialog.add(addImportPanel, BorderLayout.CENTER);
                addImportDialog.setVisible(true);
                addImportDialog.setBackground(Color.WHITE);

                // Xóa nội dung các trường nhập sau khi mở FormAddImport
                t1.setText("");
                cbNhaCC.setSelectedIndex(-1);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);
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

//    private void deleteSelectedImport() {
//        int row = table.getSelectedRow();
//        if (row >= 0) {
//            int confirm = JOptionPane.showConfirmDialog(
//                    this,
//                    "Bạn có chắc chắn muốn xóa đơn nhập hàng này?",
//                    "Xác nhận xóa",
//                    JOptionPane.YES_NO_OPTION
//            );
//
//            if (confirm == JOptionPane.YES_OPTION) {
//                int maPNH = (int) table.getValueAt(row, 0);
//                if (nhapHangBLL.deletePhieuNhapHang(maPNH)) {
//                    refreshTable();
//                    JOptionPane.showMessageDialog(this, "Xóa đơn nhập hàng thành công!");
//                } else {
//                    JOptionPane.showMessageDialog(this, "Xóa đơn nhập hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Vui lòng chọn một đơn nhập hàng để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
//        }
//    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Quản lý nhập hàng");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 600);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);

        FormImport test = new FormImport("1");
        f.add(test, BorderLayout.CENTER);
        f.setVisible(true);
    }
}