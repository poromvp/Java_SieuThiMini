package GUI.ComponentCommon;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.*;

public class TienIch {
    public static void nutStyle(JButton nut, String fileIcon, int size, int rong, int cao, Color normalColor,
            Color hoverColor, Color clickColor) {
        // Kích thước và font
        nut.setPreferredSize(new Dimension(rong, cao));
        nut.setFont(new Font("Arial", Font.BOLD, size));
        nut.setFocusPainted(false); // Bỏ viền khi focus
        nut.setContentAreaFilled(false); // Tắt nền mặc định
        nut.setOpaque(true); // Cho phép vẽ màu nền
        nut.setVerticalTextPosition(SwingConstants.BOTTOM); // Chữ nằm dưới
        nut.setHorizontalTextPosition(SwingConstants.CENTER); // Chữ căn giữa theo chiều ngang

        // Đặt màu ban đầu
        nut.setBackground(normalColor);
        nut.setForeground(Color.BLACK); // màu chữ

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
        nut.setPreferredSize(new Dimension(dai, rong));
        nut.setFont(new Font("Arial", Font.BOLD, size));
        nut.setFocusPainted(false); // Bỏ viền khi focus
        nut.setContentAreaFilled(false); // Tắt nền mặc định
        nut.setOpaque(true); // Cho phép vẽ màu nền
        nut.setVerticalTextPosition(SwingConstants.BOTTOM); // Chữ ở dưới
        nut.setHorizontalTextPosition(SwingConstants.CENTER); // Chữ căn giữa theo chiều ngang

        // Màu sắc
        Color normalColor = new Color(33, 58, 89);
        Color hoverColor = Color.GRAY;
        Color clickColor = new Color(18, 31, 47);

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
        if (rank == 1) { // title
            label.setForeground(Color.BLACK); // Màu đen
        } else if (rank == 2) { // số thống kê
            label.setForeground(new Color(33, 58, 89)); // Màu xanh dương

            // Đặt viền cho label (tùy chọn)
            label.setBorder(BorderFactory.createLineBorder(new Color(33, 58, 89), 1, true));
            label.setBackground(Color.WHITE);
            // Màu sắc
            Color normalColor = new Color(33, 58, 89);
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
        } else if (rank == 0) { // icon
            ImageIcon icon = new ImageIcon(TienIch.class.getResource("/images/icon/" + fileIcon));
            Image img = icon.getImage();
            int iconSize = size + 10; // Tùy chỉnh kích thước icon so với chữ
            Image resizedImg = img.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(resizedImg));

        } else if (rank == 3) { // nhãn bên trái trong hóa đơn
            label.setForeground(Color.BLACK);
            label.setFont(new Font("Arial", Font.PLAIN, size));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
        } else if (rank == 4) { // XemThK và XemNV
            label.setForeground(Color.WHITE);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBackground(new Color(33, 58, 89));
        } else if (rank == 5) { // TongDoanhThu TongDonHang
            label.setForeground(Color.BLACK);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBackground(null);
        }

        // Đặt khoảng cách đệm xung quanh nội dung
        label.setOpaque(true);
        label.setPreferredSize(new Dimension(150, 40));
    }

    public static void radioStyle(JRadioButton rd) {
        // Thiết lập font và kích thước
        rd.setFont(new Font("Arial", Font.PLAIN, 14));
        rd.setFocusPainted(false); // Loại bỏ viền focus khi nhấn vào
        rd.setOpaque(false); // Làm trong suốt nền để thấy màu background

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

    public static String ddmmyyyy(String input) {
        // Định dạng ban đầu của chuỗi
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Định dạng mong muốn
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Chuyển từ chuỗi sang đối tượng Date
            Date date = inputFormat.parse(input);

            // Chuyển từ Date sang chuỗi theo định dạng mới
            String formattedDate = outputFormat.format(date);

            return formattedDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String ddmmyyyy(Date date) {
        // Định dạng mong muốn
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Chuyển từ Date sang chuỗi theo định dạng mới
        return outputFormat.format(date);
    }

    public static String formatVND(double amount) {
        Locale localeVN = new Locale("vi", "VN"); // định dạng tiếng Việt, Việt Nam
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);
        return currencyFormatter.format(amount);
    }

    public static LocalDate convertDateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of("Asia/Ho_Chi_Minh"))
                .toLocalDate();
    }

    public static void checkngaynhaptutayy(JDateChooser day, Date ngay) {
        // Kiểm tra khi người dùng tự gõ tay
        day.getDateEditor().addPropertyChangeListener("date", _ -> {
            Date selectedDate = day.getDate();
            Date today = new Date();
            if (selectedDate != null && selectedDate.after(today)) {
                CustomMessageNormal("Không thể chọn ngày trong tương lai!");
                day.setDate(ngay);
            }
        }); // có thể thay today bằng một giá trị Date cụ thể nếu muốn kiểm tra theo một mốc
            // nào đó.
    }

    public static void checkngaynhaptutay(JDateChooser day, Date ngay) {
        // Kiểm tra khi người dùng tự gõ tay
        day.getDateEditor().addPropertyChangeListener("date", _ -> {
            Date selectedDate = day.getDate();
            Date today = new Date();
            if (selectedDate != null && selectedDate.after(today)) {
                CustomMessage("Không thể chọn ngày trong tương lai!");
                day.setDate(ngay);
            }
        }); // có thể thay today bằng một giá trị Date cụ thể nếu muốn kiểm tra theo một mốc
            // nào đó.
    }

    public static void checkngayKT(JDateChooser day, Date ngayBD, Date ngayKT) {
        // Kiểm tra khi người dùng tự gõ tay
        day.getDateEditor().addPropertyChangeListener("date", _ -> {
            Date selectedDate = day.getDate();
            if (selectedDate != null && selectedDate.before(ngayBD)) {
                CustomMessage("Không thể chọn ngày trước ngày bắt đầu được!");
                day.setDate(ngayKT);
            } else if (selectedDate != null && selectedDate.after(ngayBD) && selectedDate.before(ngayKT)) {
                CustomMessage("Ngày kết thúc phải cách ngày bắt đầu ít nhất 2 năm!");
                day.setDate(ngayKT);
            }
        });
    }

    public static void checkngaynhapdutuoi(JDateChooser day, Date ngay) {
        day.getDateEditor().addPropertyChangeListener("date", _ -> {
            if (day != null && day.getDate() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(day.getDate());
                int year = cal.get(Calendar.YEAR);

                Date currentDay = new Date();
                cal.setTime(currentDay);
                int currentYear = cal.get(Calendar.YEAR);

                if (currentYear - year < 18) {
                    CustomMessage("Chưa đủ 18 tuổi");
                    day.setDate(ngay);
                    return;
                }
            }
        });
    }

    public static void timStyle(Object obj) {
        if (obj instanceof JLabel label) {
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
            label.setForeground(Color.WHITE);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    label.setForeground(Color.GREEN);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    label.setForeground(Color.WHITE);
                }
            });

        } else if (obj instanceof JTextField textField) {
            textField.setFont(new Font("Segoe UI", Font.BOLD, 14));
            textField.setPreferredSize(new Dimension(200, 30));
            textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            textField.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    textField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
            });

        } else if (obj instanceof JDateChooser day) {
            day.setFont(new Font("Segoe UI", Font.BOLD, 14));
            day.setPreferredSize(new Dimension(200, 30));
            day.setDateFormatString("dd/MM/yyyy");
            day.getDateEditor().getUiComponent().setFont(new Font("Segoe UI", Font.BOLD, 14));
            day.getDateEditor().getUiComponent().setBackground(Color.WHITE);
            day.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            day.setCursor(new Cursor(Cursor.HAND_CURSOR));
            ImageIcon icon = new ImageIcon(TienIch.class.getResource("/images/icon/" + "calendar.png"));
            Image img = icon.getImage();
            int iconSize = 14 + 5; // Tùy chỉnh kích thước icon so với chữ
            Image resizedImg = img.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
            day.setIcon(new ImageIcon(resizedImg));

        } else if (obj instanceof JComboBox<?> cmb) {
            cmb.setFont(new Font("Segoe UI", Font.BOLD, 14));
            cmb.setPreferredSize(new Dimension(200, 30));
            cmb.setBackground(Color.WHITE);
            cmb.setForeground(Color.BLACK);
            cmb.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            cmb.setCursor(new Cursor(Cursor.HAND_CURSOR));
            cmb.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    cmb.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    cmb.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                }
            });

        } else if (obj instanceof JSpinner spn) {
            spn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            spn.setPreferredSize(new Dimension(200, 30));
            spn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            spn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            spn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    spn.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    spn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                }
            });
        }
    }

    public static void anhGif(JLabel lb, String file, int width, int height) {
        ImageIcon icon = new ImageIcon(TienIch.class.getResource("/images/icon/" + file));
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        lb.setIcon(new ImageIcon(resizedImg));
    }

    public static void anhAVT(JLabel lb, String file, int width, int height, String loai) {
        ImageIcon icon;
        if (loai.equals("KH")) {
            icon = new ImageIcon(TienIch.class.getResource("/images/avtMember/" + file));
        } else {
            icon = new ImageIcon(TienIch.class.getResource("/images/avtEmployee/" + file));
        }
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        lb.setIcon(new ImageIcon(resizedImg));
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb.setVerticalAlignment(SwingConstants.CENTER);
    }

    public static void setPreferredSizeTuDong(JScrollPane scr, JTable tb) {
        // Tính chiều cao theo số dòng của bảng
        int rowCount = tb.getRowCount();
        int rowHeight = tb.getRowHeight();
        int tableHeight = rowCount * rowHeight;

        if (tableHeight > 150) {
            scr.setPreferredSize(new Dimension(800, 150)); // Giữ kích cố định
        } else {
            // Đặt kích thước động (có padding)
            scr.setPreferredSize(new Dimension(800, tableHeight + 24)); // Giữ kích thước động
        }

    }

    public static void setDarkUI() {
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("OptionPane.background", new Color(33, 58, 89));
        UIManager.put("Panel.background", new Color(33, 58, 89));
        UIManager.put("Button.background", Color.GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 13));
    }

    public static void resetUI() {
        UIManager.put("Label.foreground", null);
        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("Button.background", null);
        UIManager.put("Button.foreground", null);
        UIManager.put("Button.font", null);
    }

    public static void CustomMessage(String message) {
        JLabel label = new JLabel(message);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        JOptionPane.showMessageDialog(null, label, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void CustomMessageNormal(String message) {
        JLabel label = new JLabel(message);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.BLACK);
        JOptionPane.showMessageDialog(null, label, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void taoTitleBorder(JPanel panel, String title) {
        TitledBorder border = new TitledBorder(title);
        EmptyBorder emptyBorder = new EmptyBorder(4, 4, 4, 4);
        border.setTitleColor(Color.WHITE);
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.setBorder(new CompoundBorder(border, emptyBorder));
    }

    public static BufferedImage captureComponent(Component comp) {
        comp.doLayout();  // bắt buộc
        comp.setSize(comp.getPreferredSize());  // bắt buộc
    
        int width = comp.getWidth();
        int height = comp.getHeight();
    
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Component has invalid size.");
        }
    
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        comp.paint(g2d);
        g2d.dispose();
        return image;
    }
    
}
