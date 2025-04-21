package GUI.Admin_PanelThongKe;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class TableControl {
    public static void TableStyle(JTable tb, DefaultTableModel model) {
        // Thêm hiệu ứng di chuột vào dòng
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

        JTableHeader header = tb.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setForeground(Color.WHITE);
        header.setBackground(new Color(30, 144, 255));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        // tb.setDefaultRenderer(Object.class, centerRenderer);
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tb.setShowGrid(false);
        tb.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2, true));
        tb.setSelectionBackground(new Color(173, 216, 230));
        tb.setRowHeight(30);
    }

    public static void TableEvent(JTable tb, DefaultTableModel model, String loaiXem) {
        // Thêm hiệu ứng double-click
        tb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Kiểm tra double click
                    int selectedRow = tb.getSelectedRow();
                    if (selectedRow != -1) {
                        if (loaiXem.equals("NV")) {
                            PanelXemNV panelXemNV = new PanelXemNV(model, selectedRow);
                            JOptionPane.showMessageDialog(null, panelXemNV, "Xem Chi Tiết Nhân Viên",
                                    JOptionPane.PLAIN_MESSAGE);
                        } else if (loaiXem.equals("HD")) {
                            PanelXemThK panel = new PanelXemThK(model, selectedRow);
                            JOptionPane.showMessageDialog(null, panel, "Xem Chi Tiết Hóa Đơn",
                                    JOptionPane.PLAIN_MESSAGE);
                        } else if (loaiXem.equals("KH")) {
                            PanelXemKH panel = new PanelXemKH(model, selectedRow);
                            JOptionPane.showMessageDialog(null, panel, "Xem Chi Tiết", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
            }
        });
    }
}
