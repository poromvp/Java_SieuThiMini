package com;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TienIch {
    public static void quanlynutsidebar(JButton nut) {
        nut.setPreferredSize(new Dimension(150, 150));
        nut.setMaximumSize(new Dimension(278, 200));
        nut.setFont(new Font("Arial", Font.BOLD, 18));
        nut.setOpaque(true); // Đặt nền đục hay trong suốt
        nut.setFocusPainted(false); // Bỏ viền xanh khi nhấn vào nút
        nut.setContentAreaFilled(false); // Bỏ hiệu ứng nền mặc định của JButton
        nut.setForeground(new Color(226, 224, 221)); // Đặt màu chữ trên nút
        nut.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    public static void nutStyle(JButton nut) {
        nut.setPreferredSize(new Dimension(60, 50));
        nut.setMaximumSize(new Dimension(60, 60));
        nut.setFont(new Font("Arial", Font.BOLD, 18));
        nut.setOpaque(true); // Đặt nền đục hay trong suốt
        // nut.setFocusPainted(true); // Bỏ viền xanh khi nhấn vào nút
        // nut.setContentAreaFilled(false); // Bỏ hiệu ứng nền mặc định của JButton
        nut.setForeground(new Color(15, 16, 16)); // Đặt màu chữ trên nút
        nut.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
    }

    public static void nutStyle(JButton nut, String fileIcon, int size, int dai, int rong) {
        // Kích thước và font
        nut.setPreferredSize(new Dimension(dai,rong));
        nut.setFont(new Font("Arial", Font.BOLD, size));
        nut.setFocusPainted(false); // Bỏ viền khi focus
        nut.setContentAreaFilled(false); // Tắt nền mặc định
        nut.setOpaque(true); // Cho phép vẽ màu nền

        // Màu sắc
        Color normalColor = new Color(30, 144, 255);
        Color hoverColor = new Color(0, 102, 204);
        Color clickColor = new Color(0, 51, 153);

        // Đặt màu ban đầu
        nut.setBackground(normalColor);
        nut.setForeground(Color.WHITE);

        // Thêm icon nếu có
        if (fileIcon != null && !fileIcon.isEmpty()) {
            ImageIcon icon = new ImageIcon(TienIch.class.getResource("/images/icon/" + fileIcon));
            Image img = icon.getImage();
            int iconSize = size + 30; // Tùy chỉnh kích thước icon so với chữ
            Image resizedImg = img.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
            nut.setIcon(new ImageIcon(resizedImg));
        }

        // Viền bo góc
        nut.setBorder(BorderFactory.createLineBorder(normalColor, 2, true));

        // Sự kiện hover và click
        nut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                nut.setBackground(hoverColor);
                nut.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                nut.setBackground(normalColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                nut.setBackground(clickColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                nut.setBackground(hoverColor);
            }
        });
    }

    public static void labelStyle(JLabel label, int rank, int size, String fileIcon) {
        // Đặt font chữ
        label.setFont(new Font("Arial", Font.BOLD, size));

        // Căn chỉnh văn bản giữa theo cả hai chiều
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        // Đặt màu chữ
        if (rank == 1) { //title
            label.setForeground(Color.BLACK); // Màu đen
        } else if (rank == 2) { //số thống kê
            label.setForeground(new Color(30, 144, 255)); // Màu xanh dương

            // Đặt viền cho label (tùy chọn)
            label.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 1, true));
            label.setBackground(Color.WHITE);
            // Màu sắc
            Color normalColor = new Color(30, 144, 255);
            Color hoverColor = new Color(210, 53, 29);
            // Sự kiện hover và click
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    label.setForeground(hoverColor);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    label.setForeground(normalColor);
                }
            });
        } else if (rank == 0) { //icon
            ImageIcon icon = new ImageIcon(TienIch.class.getResource("/images/icon/" + fileIcon));
            Image img = icon.getImage();
            int iconSize = size + 10; // Tùy chỉnh kích thước icon so với chữ
            Image resizedImg = img.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(resizedImg));

        } else if (rank == 3) { //nhãn bên trái trong hóa đơn
            label.setForeground(Color.BLACK);
            label.setFont(new Font("Arial", Font.PLAIN, size));
            label.setHorizontalAlignment(SwingConstants.LEFT);
            label.setVerticalAlignment(SwingConstants.CENTER);
        } else if (rank == 4){ 
            label.setForeground(Color.BLACK);
            label.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (rank == 5){ //bên phải 
            label.setForeground(Color.BLACK);
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            label.setVerticalAlignment(SwingConstants.CENTER);
        }

        // Đặt khoảng cách đệm xung quanh nội dung
        label.setOpaque(true);
        label.setPreferredSize(new Dimension(150, 40));
    }

    public static void radioStyle(JRadioButton rd) {
        // Thiết lập font và kích thước
        rd.setFont(new Font("Arial", Font.PLAIN, 14));
        rd.setFocusPainted(false);  // Loại bỏ viền focus khi nhấn vào
        rd.setOpaque(false);        // Làm trong suốt nền để thấy màu background
        
        // Màu sắc cho các trạng thái khác nhau
        Color normalColor = new Color(30, 144, 255);
        Color hoverColor = new Color(0, 102, 204);
        Color clickColor = new Color(0, 51, 153);

        // Màu chữ ban đầu
        rd.setForeground(normalColor);

        // Hiệu ứng khi di chuột và click
        rd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rd.setForeground(hoverColor);
                rd.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!rd.isSelected()) {
                    rd.setForeground(normalColor);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                rd.setForeground(clickColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                rd.setForeground(hoverColor);
            }
        });

        // Thêm khoảng cách giữa nút và text
        rd.setIconTextGap(10);

        // Bo góc nếu muốn
        rd.setBorder(BorderFactory.createLineBorder(normalColor, 1, true));
    }

    public static void comboBoxStyle(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Arial", Font.BOLD, 14));
        comboBox.setForeground(Color.WHITE);
        comboBox.setBackground(new Color(30, 144, 255)); // Màu xanh dương đẹp
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2, true)); // Viền bo góc
        comboBox.setFocusable(false); // Bỏ viền focus
        comboBox.setOpaque(true);

        // Tùy chỉnh dropdown list
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setFont(new Font("Arial", Font.BOLD, 14));
                label.setForeground(isSelected ? Color.WHITE : Color.BLACK);
                label.setBackground(isSelected ? new Color(0, 102, 204) : Color.WHITE);
                return label;
            }
        });
    }

    public static void textFieldStyle(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.BLACK); // Màu chữ
        textField.setBackground(Color.WHITE); // Nền trắng
        textField.setCaretColor(new Color(30, 144, 255)); // Màu con trỏ nhập liệu
        textField.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2, true)); // Viền bo góc
        textField.setOpaque(true);
        textField.setMargin(new Insets(5, 10, 5, 10)); // Thêm padding

        // Placeholder khi chưa nhập dữ liệu
        textField.setForeground(Color.GRAY);
    }
}
