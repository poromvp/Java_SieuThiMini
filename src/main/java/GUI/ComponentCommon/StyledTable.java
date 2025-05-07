package GUI.ComponentCommon;
import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import GUI.Admin_PanelThongKe.PanelXemKH;
import GUI.Admin_PanelThongKe.PanelXemNV;
import GUI.Admin_PanelThongKe.PanelXemThK;
import PDF.ChiTietDH_Dialog;


public class StyledTable extends JTable {

    private boolean isEditable = false; // Mặc định không cho chỉnh sửa
    private Color bgColor = new Color(17, 32, 51);
    public StyledTable(Object[][] data, Object[] columnNames) {
        super(new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mặc định chặn chỉnh sửa
            }
        });

        setDefaultStyles();
    }

    public static void TableEvent(JTable tb, DefaultTableModel model, String loaiXem, String MANV) {
        // Thêm hiệu ứng double-click
        tb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Kiểm tra double click
                    int selectedRow = tb.getSelectedRow();
                    if (selectedRow != -1) {
                        if (loaiXem.equals("NV")) {
                            PanelXemNV panelXemNV = new PanelXemNV(model, selectedRow, MANV);
                            JOptionPane.showMessageDialog(null, panelXemNV, "Xem Chi Tiết Nhân Viên",
                                    JOptionPane.PLAIN_MESSAGE);
                        } else if (loaiXem.equals("HD")) {
                            PanelXemThK panel = new PanelXemThK(model, selectedRow, MANV);
                            // JOptionPane.showMessageDialog(null, panel, "Xem Chi Tiết Hóa Đơn",
                            TienIch.resetUI();
                            new ChiTietDH_Dialog(null, Integer.parseInt(tb.getValueAt(selectedRow, 0).toString())).setVisible(true);;
                            
                                    // JOptionPane.PLAIN_MESSAGE);
                        } else if (loaiXem.equals("KH")) {
                            PanelXemKH panel = new PanelXemKH(model, selectedRow, MANV);
                            JOptionPane.showMessageDialog(null, panel, "Xem Chi Tiết", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
                TienIch.resetUI();
            }
        });
    }

    public static void hoverTable(JTable tb, DefaultTableModel model){
        tb.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tb.rowAtPoint(e.getPoint());
                tb.putClientProperty("hoveredRow", row);
                tb.repaint(); // Vẽ lại bảng để áp dụng màu
            }
        });

        // Sự kiện khi chuột rời khỏi bảng
        tb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                tb.putClientProperty("hoveredRow", -1);
                tb.repaint();
            }
        });

        // Đặt renderer để thay đổi màu dòng khi di chuột
        tb.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Integer hoveredRow = (Integer) table.getClientProperty("hoveredRow");

                // Đổi màu khi di chuột vào
                if (hoveredRow != null && hoveredRow == row) {
                    c.setBackground(new Color(173, 216, 230)); // Màu xanh nhạt
                } else {
                    c.setBackground(Color.WHITE);
                }
                // Căn giữa nội dung
                ((JLabel) c).setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        });
    }

    //  Thiết lập mặc định
    private void setDefaultStyles() {
        JTableHeader header = getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setForeground(Color.WHITE);
        header.setBackground(bgColor);
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        setDefaultRenderer(Object.class, centerRenderer);
        setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        setShowGrid(false);
        setBorder(BorderFactory.createLineBorder(bgColor, 2, true));
        setSelectionBackground(new Color(173, 216, 230));
        setRowHeight(30);
    }

   
    public void setEditable(boolean editable) {
        this.isEditable = editable;
        DefaultTableModel newModel = new DefaultTableModel(getData(), getColumnIdentifiers()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEditable;
            }
        };
        setModel(newModel);
    }

    //  Lấy dữ liệu hiện tại của bảng
    private Object[][] getData() {
        DefaultTableModel model = (DefaultTableModel) getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();
        Object[][] data = new Object[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i][j] = model.getValueAt(i, j);
            }
        }
        return data;
    }

    //  Lấy tiêu đề cột
    private Object[] getColumnIdentifiers() {
        DefaultTableModel model = (DefaultTableModel) getModel();
        int colCount = model.getColumnCount();
        Object[] columns = new Object[colCount];
        for (int i = 0; i < colCount; i++) {
            columns[i] = model.getColumnName(i);
        }
        return columns;
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Styled Table");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.setLocationRelativeTo(null);

            // Dữ liệu mẫu
            Object[][] data = {
                {"1", "Nguyễn Văn A", "20", "Hà Nội"},
                {"2", "Trần Thị B", "22", "Hồ Chí Minh"},
                {"3", "Lê Văn C", "25", "Đà Nẵng"}
            };

            //  Tiêu đề cột
            Object[] columns = {"ID", "Họ và Tên", "Tuổi", "Thành phố"};

            //  Tạo bảng
            StyledTable table = new StyledTable(data, columns);
            table.setEditable(false); // Bật chỉnh sửa

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane);
            frame.setVisible(true);
        });
    }
}


