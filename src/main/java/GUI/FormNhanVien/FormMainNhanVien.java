package GUI.FormNhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.FileOutputStream;
import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import GUI.Export;
import GUI.ComponentCommon.ButtonCustom;


import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class FormMainNhanVien extends JPanel {
    private NhanVienBLL bll;
    private FormTableNhanVien employeeTablePanel;
    private InfoPanelNV infoPanel;
    public FormMainNhanVien(Connection conn) {
        setSize(1000, 600);
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.WHITE);
        this.bll = new NhanVienBLL();
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        // -------------Top panel: Tìm kiếm + Bộ lọc
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        infoPanel = new InfoPanelNV();
        employeeTablePanel = new FormTableNhanVien(conn, infoPanel);
        FormSearchNhanVien searchPanel = new FormSearchNhanVien(employeeTablePanel);
        FormFilterNV filterPanel = new FormFilterNV(employeeTablePanel);
        topPanel.add(searchPanel);
        topPanel.add(filterPanel);

        // -------------Info và Table
        infoPanel.setTablePanel(employeeTablePanel);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(employeeTablePanel, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);

        // -------------Panel chứa các nút ở dưới cùng
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        ButtonCustom btnThem = new ButtonCustom("Thêm", "add", 12, 40, 40);
        btnThem.addActionListener(e -> openAddDialog());
        buttonPanel.add(btnThem);

        ButtonCustom btnSua = new ButtonCustom("Sửa", "edit", 12, 40, 40);
        btnSua.addActionListener(e -> openEditDialog());
        buttonPanel.add(btnSua);

        ButtonCustom btnXoa = new ButtonCustom("Xóa", "del", 12, 40, 40);
        btnXoa.addActionListener(e -> deleteNhanVien());
        buttonPanel.add(btnXoa);

        ButtonCustom btnXemNghiViec = new ButtonCustom("Lịch sử", "his", 12, 50, 40);
        btnXemNghiViec.addActionListener(e -> xemLichSu());
        buttonPanel.add(btnXemNghiViec);
        ButtonCustom btnXuatPDF = new ButtonCustom("Xuất", "printer",12,50,40);
        buttonPanel.add(btnXuatPDF);
        btnXuatPDF.addActionListener(e -> FormExport("DANH SÁCH NHÂN VIÊN"));
        add(buttonPanel, BorderLayout.SOUTH);
    }
   
    private void openAddDialog() {
        AddNhanVienDialog dialog = new AddNhanVienDialog(new Frame(), employeeTablePanel);
        dialog.setVisible(true);
    }

    private void openEditDialog() {
        int selectedRow = employeeTablePanel.getNhanVienTable().getSelectedRow();
        if (selectedRow != -1) {
            try {
                String maNV = String.valueOf(employeeTablePanel.getNhanVienTable().getValueAt(selectedRow, 0));
                String hoTen = String.valueOf(employeeTablePanel.getNhanVienTable().getValueAt(selectedRow, 1));
                String gioiTinh = String.valueOf(employeeTablePanel.getNhanVienTable().getValueAt(selectedRow, 2));
                String ngaySinh = String.valueOf(employeeTablePanel.getNhanVienTable().getValueAt(selectedRow, 3));
                String cccd = String.valueOf(employeeTablePanel.getNhanVienTable().getValueAt(selectedRow, 4));
                String diaChi = String.valueOf(employeeTablePanel.getNhanVienTable().getValueAt(selectedRow, 5));
                String soDT = String.valueOf(employeeTablePanel.getNhanVienTable().getValueAt(selectedRow, 6));
                String luongStr = String.valueOf(employeeTablePanel.getNhanVienTable().getValueAt(selectedRow, 7));
                luongStr = luongStr.replaceAll("[^\\d]", "");
                String trangThai = String.valueOf(employeeTablePanel.getNhanVienTable().getValueAt(selectedRow, 8));
    
                NhanVienDTO nv = bll.getNhanVienByMa(maNV);
                String anhNV = (nv != null && nv.getImage() != null) ? nv.getImage() : "default.png";
    
                EditNhanVienDialog dialog = new EditNhanVienDialog(
                    SwingUtilities.getWindowAncestor(this),
                    employeeTablePanel,
                    maNV, hoTen, gioiTinh, ngaySinh, cccd, diaChi, soDT, luongStr, trangThai, anhNV
                );
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, 
                    "Lỗi khi lấy dữ liệu nhân viên: " + e.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn nhân viên để sửa!", 
                "Thông báo", 
                JOptionPane.WARNING_MESSAGE
            );
        }
    }
private void deleteNhanVien() {
    int selectedRow = employeeTablePanel.getNhanVienTable().getSelectedRow();
    
    if (selectedRow != -1) {
       int maNV = (int) employeeTablePanel.getNhanVienTable().getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa nhân viên này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (maNV <= 0) {
                    JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ");
                    return ;
                }
                boolean isDeleted = bll.deleteNhanVien(maNV);
                if (isDeleted) {
                    JOptionPane.showMessageDialog(this, 
                        "Nhân viên đã được xóa.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    employeeTablePanel.refreshTable();

                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Không thể xóa nhân viên này.", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, 
                    "Lỗi khi xóa nhân viên: " + e.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, 
            "Vui lòng chọn nhân viên để xóa!", 
            "Thông báo", 
            JOptionPane.WARNING_MESSAGE
        );
    }
}

    private void xemLichSu() {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Lịch Sử Nghỉ Việc", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(800, 400);
        dialog.setLocationRelativeTo(this);
        FormLichSuNghiViec formLichSu = new FormLichSuNghiViec(employeeTablePanel);
        dialog.add(formLichSu);
        dialog.setVisible(true);
    }
    private void FormExport(String title) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Export", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        FormExport formEx = new FormExport(title);
        dialog.add(formEx);
        dialog.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Connection conn = JDBC.DBConnection.getConnection();
                if (conn == null) {
                    JOptionPane.showMessageDialog(null, 
                        "Không thể kết nối cơ sở dữ liệu!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                JFrame frame = new JFrame("Quản Lý Nhân Viên");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1000, 600);
                frame.setLocationRelativeTo(null);
                frame.setLayout(new BorderLayout());

                FormMainNhanVien mainPanel = new FormMainNhanVien(conn);
                frame.add(mainPanel, BorderLayout.CENTER);

                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Lỗi khởi tạo: " + e.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}