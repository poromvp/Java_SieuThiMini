package GUI.FormNhanVien;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import GUI.ComponentCommon.StyledTable;
import GUI.ComponentCommon.TienIch;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FormTableNhanVien extends JPanel {
    private InfoPanelNV infoPanel;
    private StyledTable tablePanel;
    private NhanVienBLL nhanVienBLL;
    private Object[][] data;
    private Connection conn;
    String[] columnNames = {"Mã NV", "Họ Tên", "Giới Tính", "Ngày Sinh", "CCCD", "Địa Chỉ", "Số ĐT", "Lương", "Trạng Thái"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public FormTableNhanVien(Connection conn, InfoPanelNV infoPanel) {
        this.conn = conn;
        this.infoPanel = infoPanel;
        setLayout(new BorderLayout());
        
        nhanVienBLL = new NhanVienBLL();
        loadNhanVienTable();
    }
    public FormTableNhanVien(){
        this.nhanVienBLL = new NhanVienBLL();
        setLayout(new BorderLayout());
        loadNhanVienTable();
    }
    public void loadNhanVienTable() {
    
        try {
            List<NhanVienDTO> ds = nhanVienBLL.getAllNhanVien();
    
            data = new Object[ds.size()][columnNames.length];
    
            for (int i = 0; i < ds.size(); i++) {
                NhanVienDTO nv = ds.get(i);
                data[i][0] = nv.getMaNV();
                data[i][1] = nv.getTenNV();
                data[i][2] = nv.getGioiTinh();
                data[i][3] = nv.getNgaySinh() != null ? sdf.format(nv.getNgaySinh()) : "";
                data[i][4] = nv.getCCCD();
                data[i][5] = nv.getDiaChi();
                data[i][6] = nv.getSDT();
                data[i][7] = TienIch.formatVND(nv.getLuong());
                data[i][8] = nv.getTrangThai()==1 ? "Đang làm việc" : "Nghỉ việc";
            }
    
            tablePanel = new StyledTable(data, columnNames);
            tablePanel.setRowSelectionAllowed(true);
            tablePanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(tablePanel);
            scrollPane.setBackground(Color.WHITE);
            add(scrollPane, BorderLayout.CENTER);
    
            addTableClickEvent();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu bảng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void searchAndUpdateTable(String keyword) {
    
        try {
            List<NhanVienDTO> ds = nhanVienBLL.searchNhanVien(keyword);
    
            Object[][] newData = new Object[ds.size()][columnNames.length];
            for (int i = 0; i < ds.size(); i++) {
                NhanVienDTO nv = ds.get(i);
                newData[i][0] = nv.getMaNV();
                newData[i][1] = nv.getTenNV();
                newData[i][2] = nv.getGioiTinh();
                newData[i][3] = nv.getNgaySinh() != null ? sdf.format(nv.getNgaySinh()) : "";
                newData[i][4] = nv.getCCCD();
                newData[i][5] = nv.getDiaChi();
                newData[i][6] = nv.getSDT();
                newData[i][7] = TienIch.formatVND(nv.getLuong());
                newData[i][8] = nv.getTrangThai() == 1 ? "Đang làm việc" : "Nghỉ việc";
            }
    
            removeAll();
            tablePanel = new StyledTable(newData, columnNames);
            tablePanel.setRowSelectionAllowed(true);
            tablePanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(tablePanel);
            scrollPane.setBackground(Color.WHITE);
            add(scrollPane, BorderLayout.CENTER);
            addTableClickEvent();
            revalidate();
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void reloadData() {
        removeAll();
        setLayout(new BorderLayout()); 
    
        
    
        try {
            List<NhanVienDTO> ds = nhanVienBLL.getAllNhanVien();
            
    
            Object[][] newData = new Object[ds.size()][columnNames.length];
    
            for (int i = 0; i < ds.size(); i++) {
                NhanVienDTO nv = ds.get(i);
                newData[i][0] = nv.getMaNV();
                newData[i][1] = nv.getTenNV();
                newData[i][2] = nv.getGioiTinh();
                newData[i][3] = nv.getNgaySinh() != null ? sdf.format(nv.getNgaySinh()) : "";
                newData[i][4] = nv.getCCCD();
                newData[i][5] = nv.getDiaChi();
                newData[i][6] = nv.getSDT();
                newData[i][7] = TienIch.formatVND(nv.getLuong());
                newData[i][8] = nv.getTrangThai() == 1 ? "Đang làm việc" : "Nghỉ việc";
            }
    
            tablePanel = new StyledTable(newData, columnNames);
            tablePanel.setRowSelectionAllowed(true);
            tablePanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(tablePanel);
            scrollPane.setBackground(Color.WHITE);
    
            add(scrollPane, BorderLayout.CENTER);
            addTableClickEvent();
    
            revalidate();
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load lại dữ liệu bảng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void addTableClickEvent() {        
        tablePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowAtPoint = tablePanel.rowAtPoint(e.getPoint());
                if (rowAtPoint != -1) {
                    tablePanel.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                } else {
                    tablePanel.clearSelection();
                }
        
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    int selectedRow = tablePanel.getSelectedRow();
                    if (selectedRow != -1) {
                        if (infoPanel == null) {
                            JOptionPane.showMessageDialog(FormTableNhanVien.this, "Lỗi: Không thể truy cập panel thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        try {
                            String maNV = tablePanel.getValueAt(selectedRow, 0) != null ? String.valueOf(tablePanel.getValueAt(selectedRow, 0)) : "";
                            String hoTen = tablePanel.getValueAt(selectedRow, 1) != null ? String.valueOf(tablePanel.getValueAt(selectedRow, 1)) : "";
                            String gioiTinh = tablePanel.getValueAt(selectedRow, 2) != null ? String.valueOf(tablePanel.getValueAt(selectedRow, 2)) : "";
                            String ngaySinh = tablePanel.getValueAt(selectedRow, 3) != null ? String.valueOf(tablePanel.getValueAt(selectedRow, 3)) : "";
                            String cccd = tablePanel.getValueAt(selectedRow, 4) != null ? String.valueOf(tablePanel.getValueAt(selectedRow, 4)) : "";
                            String diaChi = tablePanel.getValueAt(selectedRow, 5) != null ? String.valueOf(tablePanel.getValueAt(selectedRow, 5)) : "";
                            String soDT = tablePanel.getValueAt(selectedRow, 6) != null ? String.valueOf(tablePanel.getValueAt(selectedRow, 6)) : "";
                            String luong = tablePanel.getValueAt(selectedRow, 7) != null ? String.valueOf(tablePanel.getValueAt(selectedRow, 7)) : "";
                            String trangThai = tablePanel.getValueAt(selectedRow, 8) != null ? String.valueOf(tablePanel.getValueAt(selectedRow, 8)) : "";
                            
                            NhanVienDTO nv = nhanVienBLL.getNhanVienByMa(maNV);
                            String anhNV = nv != null && nv.getImage() != null ? nv.getImage() : "default.png";
                            
                            infoPanel.setEmployeeData(maNV, hoTen, gioiTinh, ngaySinh, cccd, diaChi, soDT, luong, trangThai, anhNV);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(FormTableNhanVien.this, "Lỗi khi truyền dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(FormTableNhanVien.this, "Vui lòng chọn một hàng trong bảng!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }
    public void locNhanVien(String doTuoi, String gioiTinh, String khuVuc) {
        int currentYear = java.time.Year.now().getValue();
        
        try {
            List<NhanVienDTO> ds = nhanVienBLL.getAllNhanVien();
            List<NhanVienDTO> filteredList = new java.util.ArrayList<>();
        
            int minAge = -1;
            int maxAge = -1;
        
            if (doTuoi != null && doTuoi.matches("\\d{2}-\\d{2,3}")) {
                String[] parts = doTuoi.split("-");
                minAge = Integer.parseInt(parts[0]);
                maxAge = Integer.parseInt(parts[1]);
            }
        
            for (NhanVienDTO nv : ds) {
                boolean match = true;
        
                if (gioiTinh != null && !nv.getGioiTinh().equalsIgnoreCase(gioiTinh)) {
                    match = false;
                }
        
                if (khuVuc != null && (nv.getDiaChi() == null || !nv.getDiaChi().toLowerCase().contains(khuVuc.toLowerCase()))) {
                    match = false;
                }
        
                if (minAge != -1 && nv.getNgaySinh() != null) {
                    java.util.Date utilDate = nv.getNgaySinh();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 
                    LocalDate birthDate = sqlDate.toLocalDate(); 
                    
                    int birthYear = birthDate.getYear();
                    int age = currentYear - birthYear;
                    if (age < minAge || age > maxAge) {
                        match = false;
                    }
                }
                
        
                if (match) {
                    filteredList.add(nv);
                }
            }
        
            Object[][] newData = new Object[filteredList.size()][columnNames.length];
        
            for (int i = 0; i < filteredList.size(); i++) {
                NhanVienDTO nv = filteredList.get(i);
                newData[i][0] = nv.getMaNV();
                newData[i][1] = nv.getTenNV();
                newData[i][2] = nv.getGioiTinh();
                newData[i][3] = nv.getNgaySinh() != null ? sdf.format(nv.getNgaySinh()) : "";
                newData[i][4] = nv.getCCCD();
                newData[i][5] = nv.getDiaChi();
                newData[i][6] = nv.getSDT();
                newData[i][7] = TienIch.formatVND(nv.getLuong());
                newData[i][8] = nv.getTrangThai() == 1 ? "Đang làm việc" : "Nghỉ việc";
            }
        
            removeAll();
            tablePanel = new StyledTable(newData, columnNames);
            tablePanel.setRowSelectionAllowed(true);
            tablePanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(tablePanel);
            scrollPane.setBackground(Color.WHITE);
            add(scrollPane, BorderLayout.CENTER);
            addTableClickEvent();
            revalidate();
            repaint();
        
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lọc dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

        
    
    public void refreshTable() {
        reloadData();
    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) tablePanel.getModel();
    }

    public JTable getNhanVienTable() {
        return tablePanel;
    }
    
    public static void main(String[] args) {
        try {
            Connection conn = JDBC.DBConnection.getConnection();
            JFrame frame = new JFrame("Form Table NhanVien Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            InfoPanelNV infoPanel = new InfoPanelNV();
            FormTableNhanVien tablePanel = new FormTableNhanVien(conn, infoPanel);
            infoPanel.setTablePanel(tablePanel);
            frame.add(tablePanel);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối CSDL: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}