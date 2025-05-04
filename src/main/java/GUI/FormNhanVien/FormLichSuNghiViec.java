package GUI.FormNhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTable;
import GUI.ComponentCommon.TienIch;
import BLL.NhanVienBLL;
import DTO.NhanVienDTO;

public class FormLichSuNghiViec extends JPanel {
    private StyledTable table;
    private DefaultTableModel model;
    private NhanVienBLL nhanVienBLL;
    private FormTableNhanVien mainTablePanel;

    public FormLichSuNghiViec(FormTableNhanVien mainTablePanel) {
        setLayout(new BorderLayout());
        this.nhanVienBLL = new NhanVienBLL();
        this.mainTablePanel = mainTablePanel;

        initComponents();
        loadNghiViecTable();
    }

    private void initComponents() {
        String[] columnNames = {
            "Mã NV", "Họ Tên", "Giới Tính", "Ngày Sinh",
            "CCCD", "Địa Chỉ", "Số ĐT", "Lương", "Trạng Thái"
        };

        model = new DefaultTableModel(new Object[0][columnNames.length], columnNames);
        table = new StyledTable(new Object[0][columnNames.length], columnNames);
        table.setModel(model);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        // ButtonCustom btnKhoiPhuc = new ButtonCustom("Khôi phục", "restore", 12, 50, 40);
        // btnKhoiPhuc.addActionListener(e -> khoiPhucNhanVien());
        // buttonPanel.add(btnKhoiPhuc);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadNghiViecTable() {
        model.setRowCount(0);
        try {
            List<NhanVienDTO> list = nhanVienBLL.getAllNhanVien0();
            System.out.println("FormLichSuNghiViec: Lấy được " + list.size() + " nhân viên nghỉ việc");
    
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (NhanVienDTO nv : list) {
                model.addRow(new Object[]{
                    nv.getMaNV(),
                    nv.getTenNV(),
                    nv.getGioiTinh(),
                    nv.getNgaySinh() != null ? sdf.format(nv.getNgaySinh()) : "",
                    nv.getCCCD(),
                    nv.getDiaChi(),
                    nv.getSDT(),
                    TienIch.formatVND(nv.getLuong()),
                    "Nghỉ việc"
                });
            }
    
            table.revalidate();
            table.repaint();
            System.out.println("FormLichSuNghiViec: Đã thêm " + model.getRowCount() + " hàng vào bảng");
    
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Không có nhân viên nào trong trạng thái nghỉ việc!", 
                    "Thông báo", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Lỗi tải lịch sử nghỉ việc: " + e.getMessage(), 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    

    // private void khoiPhucNhanVien() {
    //     int selectedRow = table.getSelectedRow();
    //     if (selectedRow == -1) {
    //         JOptionPane.showMessageDialog(this, 
    //             "Vui lòng chọn nhân viên để khôi phục!", 
    //             "Thông báo", 
    //             JOptionPane.WARNING_MESSAGE
    //         );
    //         return;
    //     }

    //     try {
    //         int maNV = Integer.parseInt(String.valueOf(table.getValueAt(selectedRow, 0)));
    //         NhanVienDTO nv = new NhanVienDTO();
    //         nv.setMaNV(maNV);
    //         nv.setTenNV(String.valueOf(table.getValueAt(selectedRow, 1)));
    //         nv.setGioiTinh(String.valueOf(table.getValueAt(selectedRow, 2)));
    //         nv.setCCCD(String.valueOf(table.getValueAt(selectedRow, 4)));
    //         nv.setDiaChi(String.valueOf(table.getValueAt(selectedRow, 5)));
    //         nv.setSDT(String.valueOf(table.getValueAt(selectedRow, 6)));
    //         nv.setLuong(Double.parseDouble(String.valueOf(table.getValueAt(selectedRow, 7))));
    //         nv.setTrangThai(1); 

    //         if (nhanVienBLL.updateNhanVien(nv)) {
    //             JOptionPane.showMessageDialog(this, 
    //                 "Khôi phục nhân viên thành công!", 
    //                 "Thành công", 
    //                 JOptionPane.INFORMATION_MESSAGE
    //             );
    //             loadNghiViecTable();
    //             mainTablePanel.refreshTable(); 
    //         } else {
    //             JOptionPane.showMessageDialog(this, 
    //                 "Không thể khôi phục nhân viên!", 
    //                 "Lỗi", 
    //                 JOptionPane.ERROR_MESSAGE
    //             );
    //         }
    //     } catch (NumberFormatException e) {
    //         e.printStackTrace();
    //         JOptionPane.showMessageDialog(this, 
    //             "Mã nhân viên hoặc lương không hợp lệ!", 
    //             "Lỗi", 
    //             JOptionPane.ERROR_MESSAGE
    //         );
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         JOptionPane.showMessageDialog(this, 
    //             "Lỗi: " + e.getMessage(), 
    //             "Lỗi", 
    //             JOptionPane.ERROR_MESSAGE
    //         );
    //     }
    // }
}