package GUI.FormNhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.FileOutputStream;
import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import GUI.ExportToPDF;
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
        ButtonCustom btnXuatPDF = new ButtonCustom("Xuất PDF", "printer",12,50,40);
        buttonPanel.add(btnXuatPDF);
        btnXuatPDF.addActionListener(e -> ExportToPDF.exportJTableToPDF(employeeTablePanel.getNhanVienTable()));
        add(buttonPanel, BorderLayout.SOUTH);
    }
    public void printPDF() {
        try {
            // Tạo file PDF
            com.lowagie.text.Document document = new com.lowagie.text.Document();
            String filePath = "bang_du_lieu.pdf";
            com.lowagie.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
    
            // Đăng ký font hỗ trợ tiếng Việt
            com.lowagie.text.pdf.BaseFont baseFont = com.lowagie.text.pdf.BaseFont.createFont("c:/windows/fonts/arial.ttf", com.lowagie.text.pdf.BaseFont.IDENTITY_H, com.lowagie.text.pdf.BaseFont.EMBEDDED);
            
            // Font cho tiêu đề
            com.lowagie.text.Font fontTitle = new com.lowagie.text.Font(baseFont, 16, com.lowagie.text.Font.BOLD);
            
            // Font cho nội dung
            com.lowagie.text.Font fontContent = new com.lowagie.text.Font(baseFont, 12);
    
            com.lowagie.text.Paragraph title = new com.lowagie.text.Paragraph("DANH SÁCH DỮ LIỆU", fontTitle);
            title.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
            document.add(title);
            document.add(new com.lowagie.text.Paragraph("\n"));
    
            // Lấy dữ liệu từ JTable
            JTable table = employeeTablePanel.getNhanVienTable();
            com.lowagie.text.pdf.PdfPTable pdfTable = new com.lowagie.text.pdf.PdfPTable(table.getColumnCount());
    
            // Header
            for (int i = 0; i < table.getColumnCount(); i++) {
                com.lowagie.text.pdf.PdfPCell cell = new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(table.getColumnName(i), fontContent));
                cell.setHorizontalAlignment(com.lowagie.text.Element.ALIGN_CENTER);
                cell.setBackgroundColor(new java.awt.Color(240, 240, 240));
                pdfTable.addCell(cell);
            }
    
            // Dữ liệu
            for (int rows = 0; rows < table.getRowCount(); rows++) {
                for (int cols = 0; cols < table.getColumnCount(); cols++) {
                    Object value = table.getValueAt(rows, cols);
                    com.lowagie.text.pdf.PdfPCell cell = new com.lowagie.text.pdf.PdfPCell(new com.lowagie.text.Phrase(value != null ? value.toString() : "", fontContent));
                    pdfTable.addCell(cell);
                }
            }
    
            document.add(pdfTable);
            document.close();
    
            JOptionPane.showMessageDialog(this, "Đã xuất file PDF thành công: " + filePath, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
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
                String luong = String.valueOf(employeeTablePanel.getNhanVienTable().getValueAt(selectedRow, 7));
                String trangThai = String.valueOf(employeeTablePanel.getNhanVienTable().getValueAt(selectedRow, 8));
    
                NhanVienDTO nv = bll.getNhanVienByMa(maNV);
                String anhNV = (nv != null && nv.getImage() != null) ? nv.getImage() : "default.png";
    
                EditNhanVienDialog dialog = new EditNhanVienDialog(
                    SwingUtilities.getWindowAncestor(this),
                    employeeTablePanel,
                    maNV, hoTen, gioiTinh, ngaySinh, cccd, diaChi, soDT, luong, trangThai, anhNV
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
                boolean isDeleted = bll.deleteNhanVien(maNV);
                if (isDeleted) {
                    JOptionPane.showMessageDialog(this, 
                        "Nhân viên đã được xóa (trạng thái đổi thành 0).", 
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