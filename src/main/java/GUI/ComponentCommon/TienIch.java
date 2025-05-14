package GUI.ComponentCommon;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import com.toedter.calendar.JDateChooser;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.util.*;
import java.util.regex.Pattern;
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
        label.setToolTipText(label.getText());
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

    public static String formatVND(int amount) {
        Locale localeVN = new Locale("vi", "VN");
        Number intress = amount;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);
        return currencyFormatter.format(intress);
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

    public static Date addTwoYearsToDate(Date ngayBD) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayBD); // Đặt thời gian cho calendar bằng ngày gốc
        calendar.add(Calendar.YEAR, 2); // Thêm 2 năm
        return new Date(calendar.getTimeInMillis()); // Trả về ngày mới sau khi cộng thêm 2 năm
    }

    public static void checkngayKT(JDateChooser day, Date ngayBD, Date ngayKT) {
        // Kiểm tra khi người dùng tự gõ tay
        day.getDateEditor().addPropertyChangeListener("date", _ -> {
            Date selectedDate = day.getDate();
            if (selectedDate != null && selectedDate.before(ngayBD)) {
                CustomMessage("Không thể chọn ngày trước ngày bắt đầu được!");
                day.setDate(ngayKT);
            } else {// Tính ngày cách 2 năm sau ngày bắt đầu
                if (selectedDate.before(addTwoYearsToDate(ngayBD))) {
                    CustomMessage("Ngày kết thúc phải cách ngày bắt đầu ít nhất 2 năm!");
                    day.setDate(ngayKT);
                }
            }
        });
    }

    private static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
        // Lý do là do cách bạn so sánh hai ngày bằng Date.after() không xét đến
        // giờ/phút/giây, và bản chất là ngày bạn chọn tuy "giống nhau", nhưng có thể có
        // sự khác biệt ở phần thời gian (time) dẫn đến việc select1.after(select2) trả
        // về true.
    }

    public static void checkFromTo(JDateChooser from, JDateChooser to) {
        // Dùng một cờ (isAdjusting) để tạm vô hiệu hóa tạm thời listener khi đang chỉnh
        // sửa bằng code, tránh kích hoạt chồng lấn.
        final boolean[] isAdjusting = { false }; // Dùng mảng để thay đổi được trong lambda

        from.addPropertyChangeListener("date", _ -> {
            if (isAdjusting[0])
                return;
            if (from.getDate() != null && to.getDate() != null) {
                Date select1 = removeTime(from.getDate());
                Date select2 = removeTime(to.getDate());
                if (select1.after(select2)) {
                    isAdjusting[0] = true;
                    TienIch.CustomMessage("Ngày bắt đầu không thể lớn hơn ngày kết thúc");
                    from.setDate(null);
                    to.setDate(null);
                    isAdjusting[0] = false;
                }
            }
        });

        to.addPropertyChangeListener("date", _ -> {
            if (isAdjusting[0])
                return;
            if (from.getDate() != null && to.getDate() != null) {
                Date select1 = removeTime(from.getDate());
                Date select2 = removeTime(to.getDate());
                if (select1.after(select2)) {
                    isAdjusting[0] = true;
                    TienIch.CustomMessage("Ngày bắt đầu không thể lớn hơn ngày kết thúc");
                    from.setDate(null);
                    to.setDate(null);
                    isAdjusting[0] = false;
                }
            }
        });
        // vì setDate() là thay đổi thuộc tính "date" → Java gọi tiếp
        // PropertyChangeListener của đối tượng đó.
    }

    public static void checkFromToBanChay(JDateChooser from, JDateChooser to) {
        // Ngày hôm nay
        Date today = new Date(System.currentTimeMillis());

        // Ngày đầu tiên của tháng
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = new Date(cal.getTimeInMillis());

        final boolean[] isAdjusting = { false }; // Dùng mảng để thay đổi được trong lambda

        from.addPropertyChangeListener("date", _ -> {
            if (isAdjusting[0])
                return;
            if (from.getDate() != null && to.getDate() != null) {
                Date select1 = new java.sql.Date(from.getDate().getTime());
                Date select2 = new java.sql.Date(to.getDate().getTime());
                if (select1.after(select2)) {
                    isAdjusting[0] = true;
                    TienIch.CustomMessageNormal("Ngày bắt đầu không thể lớn hơn ngày kết thúc 1");
                    from.setDate(firstDayOfMonth);
                    to.setDate(today);
                    isAdjusting[0] = false;
                }
            }
        });

        to.addPropertyChangeListener("date", _ -> {
            if (isAdjusting[0])
                return;
            if (from.getDate() != null && to.getDate() != null) {
                Date select1 = new java.sql.Date(from.getDate().getTime());
                Date select2 = new java.sql.Date(to.getDate().getTime());
                if (select1.after(select2)) {
                    isAdjusting[0] = true;
                    TienIch.CustomMessageNormal("Ngày bắt đầu không thể lớn hơn ngày kết thúc 2");
                    from.setDate(firstDayOfMonth);
                    to.setDate(today);
                    isAdjusting[0] = false;
                }
            }
        });
        // Dùng một cờ (isAdjusting) để tạm vô hiệu hóa tạm thời listener khi đang chỉnh
        // sửa bằng code, tránh kích hoạt chồng lấn.
        // vì setDate() là thay đổi thuộc tính "date" → Java gọi tiếp
        // PropertyChangeListener của đối tượng đó.
    }

    public static void checkFromToTotNhat(JDateChooser from, JDateChooser to) {
        // Ngày hôm nay
        Date today = new Date(System.currentTimeMillis());

        // Ngày đầu tiên của tháng
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = new Date(cal.getTimeInMillis());

        final boolean[] isAdjusting = { false }; // Dùng mảng để thay đổi được trong lambda

        from.addPropertyChangeListener("date", _ -> {
            if (isAdjusting[0])
                return;
            if (from.getDate() != null && to.getDate() != null) {
                Date select1 = new java.sql.Date(from.getDate().getTime());
                Date select2 = new java.sql.Date(to.getDate().getTime());
                if (select1.after(select2)) {
                    isAdjusting[0] = true;
                    TienIch.CustomMessage("Ngày bắt đầu không thể lớn hơn ngày kết thúc 1");
                    from.setDate(firstDayOfMonth);
                    to.setDate(today);
                    isAdjusting[0] = false;
                }
            }
        });

        to.addPropertyChangeListener("date", _ -> {
            if (isAdjusting[0])
                return;
            if (from.getDate() != null && to.getDate() != null) {
                Date select1 = new java.sql.Date(from.getDate().getTime());
                Date select2 = new java.sql.Date(to.getDate().getTime());
                if (select1.after(select2)) {
                    isAdjusting[0] = true;
                    TienIch.CustomMessage("Ngày bắt đầu không thể lớn hơn ngày kết thúc 2");
                    from.setDate(firstDayOfMonth);
                    to.setDate(today);
                    isAdjusting[0] = false;
                }
            }
        });
        // Dùng một cờ (isAdjusting) để tạm vô hiệu hóa tạm thời listener khi đang chỉnh
        // sửa bằng code, tránh kích hoạt chồng lấn.
        // vì setDate() là thay đổi thuộc tính "date" → Java gọi tiếp
        // PropertyChangeListener của đối tượng đó.
    }

    public static void checkngaynhapdutuoi(JDateChooser day, Date ngay) {
        final boolean[] isAdjusting = { false }; // cờ tránh lặp listener

        day.getDateEditor().addPropertyChangeListener("date", _ -> {
            if (isAdjusting[0])
                return;

            if (day != null && day.getDate() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(day.getDate());
                int year = cal.get(Calendar.YEAR);

                Calendar now = Calendar.getInstance();
                int currentYear = now.get(Calendar.YEAR);

                if (currentYear - year < 18) {
                    isAdjusting[0] = true; // bắt đầu điều chỉnh
                    CustomMessage("Chưa đủ 18 tuổi");
                    day.setDate(ngay); // setDate sẽ gọi lại listener
                    isAdjusting[0] = false; // kết thúc điều chỉnh
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

    /*
     * public static void anhAVT(JLabel lb, String file, int width, int height,
     * String loai) {
     * ImageIcon icon;
     * if (loai.equals("KH")) {
     * icon = new ImageIcon(TienIch.class.getResource("/images/avtMember/" + file));
     * } else {
     * icon = new ImageIcon(TienIch.class.getResource("/images/avtMember/" + file));
     * }
     * Image img = icon.getImage();
     * Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
     * lb.setIcon(new ImageIcon(resizedImg));
     * lb.setHorizontalAlignment(SwingConstants.CENTER);
     * lb.setVerticalAlignment(SwingConstants.CENTER);
     * }
     */

    public static void anhAVT(JLabel lb, String file, int width, int height, String loai) {
        ImageIcon icon = null;
        String defaultImage = "error.jpg"; // Đường dẫn đến hình ảnh mặc định

        try {
            // Kiểm tra loại và tải hình ảnh
            if (loai.equals("KH")) {
                icon = new ImageIcon(TienIch.class.getResource("/images/avtMember/" + file));
            } else {
                icon = new ImageIcon(TienIch.class.getResource("/images/avtMember/" + file));
            }

            // Kiểm tra xem icon có hợp lệ không (nếu file không tồn tại, getResource trả về
            // null)
            if (icon.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
                throw new Exception("Hình ảnh không hợp lệ hoặc không tồn tại");
            }
        } catch (Exception e) {
            // Nếu có lỗi (file không tồn tại, tên file không hợp lệ, hoặc ngoại lệ khác),
            // sử dụng hình ảnh mặc định
            try {
                icon = new ImageIcon(TienIch.class.getResource("/images/icon/" + defaultImage));
                if (icon.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
                    // Nếu cả error.jpg cũng không tồn tại, hiển thị thông báo trên JLabel
                    lb.setText("Không tìm thấy error.jpg");
                    lb.setIcon(null);
                    return;
                }
            } catch (Exception ex) {
                // Nếu không tải được error.jpg, hiển thị thông báo trên JLabel
                lb.setText("Lỗi tải hình ảnh mặc định");
                lb.setIcon(null);
                return;
            }
        }

        // Thay đổi kích thước hình ảnh và đặt vào JLabel
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

    public static void taoTitleBorder(JRadioButton rd, String title) {
        TitledBorder border = new TitledBorder(title);
        EmptyBorder emptyBorder = new EmptyBorder(4, 4, 4, 4);
        border.setTitleColor(Color.WHITE);
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 13));
        rd.setBorder(new CompoundBorder(border, emptyBorder));
    }

    public static void eventRadio(JRadioButton rd) {
        rd.addChangeListener(_ -> {
            if (rd.isSelected()) {
                // Khi được chọn: đổi màu nền và chữ
                rd.setBackground(Color.PINK);
                rd.setOpaque(true); // Bật opaque để màu nền hiển thị
            } else {
                // Khi không được chọn: khôi phục màu mặc định
                rd.setBackground(new Color(33, 58, 89));
                rd.setOpaque(true);
            }
        });
    }

    public static BufferedImage captureComponent(Component comp) {
        comp.doLayout(); // bắt buộc
        comp.setSize(comp.getPreferredSize()); // bắt buộc

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

    public static void sosanhSpinner(JSpinner a, JSpinner b) {
        int min = (Integer) a.getValue();
        int max = (Integer) b.getValue();

        if (min > max) {
            CustomMessage(" 'Giá trị từ' không được lớn hơn 'Giá trị đến'.");
            // Gán lại max = min
            b.setValue(min);
        }
    }

    public static void sukienSoSanh(JSpinner a, JSpinner b) {
        a.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                TienIch.sosanhSpinner(a, b);
            }
        });

        b.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                TienIch.sosanhSpinner(a, b);
            }
        });
    }

    private static final int MAX_LENGTH = 10;

    public static void chiduocnhapso(JTextField txt) {
        Document doc = txt.getDocument();
        if (doc instanceof AbstractDocument) {
            ((AbstractDocument) doc).setDocumentFilter(new DocumentFilter() {
                @Override
                public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                        throws BadLocationException {
                    if (isValidInput(fb, offset, 0, string)) {
                        super.insertString(fb, offset, string, attr);
                    }
                }

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                        throws BadLocationException {
                    if (isValidInput(fb, offset, length, text)) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }

                private boolean isValidInput(FilterBypass fb, int offset, int length, String text)
                        throws BadLocationException {
                    if (!text.matches("\\d*")) {
                        showWarning("Chỉ được nhập số");
                        return false;
                    }

                    String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
                    String newText = oldText.substring(0, offset) + text + oldText.substring(offset + length);

                    if (newText.length() > MAX_LENGTH) {
                        showWarning("Tối đa " + MAX_LENGTH + " chữ số");
                        return false;
                    }

                    return true;
                }

                private void showWarning(String msg) {
                    CustomMessage(msg);
                }
            });
        }
    }

    // Phương thức static để nhập mã thành viên chỉ chứa số
    public static Integer getValidMemberId(String title, String prompt) {
        String input;
        boolean validInput = false;

        while (!validInput) {
            input = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.PLAIN_MESSAGE);

            // Kiểm tra nếu người dùng nhấn Cancel hoặc đóng dialog
            if (input == null) {
                CustomMessage("Đã hủy");
                return null; // Trả về null nếu hủy
            }

            // Kiểm tra nếu chuỗi rỗng
            if (input.trim().isEmpty()) {
                CustomMessage("Mã thành viên không được rỗng!");
                continue;
            }

            // Kiểm tra nếu chuỗi chỉ chứa số
            if (input.matches("\\d+")) {
                validInput = true;
                return Integer.parseInt(input); // Trả về số nguyên hợp lệ
            } else {
                CustomMessage("Mã thành viên chỉ được chứa số!");
            }
        }
        return null; // Trường hợp này khó xảy ra, nhưng thêm để hoàn chỉnh
    }

    public static void chiDuocNhapChu(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {

            private boolean showedWarning = false; // Biến cờ

            private void showMessageOnce(String message) {
                if (!showedWarning) {
                    showedWarning = true;
                    TienIch.CustomMessage(message);

                    // Đặt lại cờ sau 500ms để tránh hiện nhiều lần
                    new javax.swing.Timer(500, _ -> showedWarning = false).start();
                }
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string != null && string.matches("[\\p{L}\\s]+")) {
                    super.insertString(fb, offset, string, attr);
                } else {
                    showMessageOnce("Chỉ được nhập chữ và khoảng trắng");
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text != null && text.matches("[\\p{L}\\s]+")) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    showMessageOnce("Chỉ được nhập chữ và khoảng trắng");
                }
            }
        });
    }

    public static Icon seticon(ImageIcon img) {
        Image scaledImage = img.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        Icon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }

    // Kiểm tra tên chỉ chứa chữ cái (bao gồm dấu tiếng Việt) và khoảng trắng
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        // Biểu thức chính quy: chỉ cho phép chữ cái (bao gồm tiếng Việt) và khoảng
        // trắng
        String regex = "^[\\p{L}\\s]+$";
        return Pattern.matches(regex, name);
    }

    private static LookAndFeel previousLookAndFeel = null;

    public static void setlookandfeel(boolean enable, Component parentComponent) {
        try {
            if (enable) {
                // Lưu LookAndFeel hiện tại trước khi thay đổi
                previousLookAndFeel = UIManager.getLookAndFeel();
                // Bật giao diện hệ thống
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } else {
                // Khôi phục LookAndFeel trước đó (hoặc giao diện mặc định nếu không có)
                if (previousLookAndFeel != null) {
                    UIManager.setLookAndFeel(previousLookAndFeel);
                } else {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                }
            }

            // Cập nhật giao diện cho thành phần cha (nếu có)
            if (parentComponent != null) {
                SwingUtilities.updateComponentTreeUI(parentComponent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void lamDepTabbedPaneEdgeStyle(JTabbedPane tabbedPane) {
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setForeground(Color.BLACK);
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setOpaque(false);
        tabbedPane.setFocusable(false);

        tabbedPane.setUI(new BasicTabbedPaneUI() {
            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex,
                    int x, int y, int w, int h, boolean isSelected) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color bgColor = isSelected ? Color.ORANGE : new Color(245, 245, 245);
                g2.setColor(bgColor);

                Path2D path = new Path2D.Double();
                int arc = 12;

                // Vẽ hình tab cong sóng như Edge
                path.moveTo(x, y + h);
                path.quadTo(x, y, x + arc, y); // bo góc trái
                path.curveTo(x + w / 3, y, x + 2 * w / 3, y, x + w - arc, y); // cong sóng
                path.quadTo(x + w, y, x + w, y + h); // bo góc phải
                path.closePath();

                g2.fill(path);
            }

            @Override
            protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
                    int x, int y, int w, int h, boolean isSelected) {
                // Không vẽ viền
            }

            @Override
            protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects,
                    int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
                // Không hiển thị viền focus
            }
        });
    }

    public static void lamDepComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.BOLD, 14));
        comboBox.setForeground(Color.BLACK);
        comboBox.setBackground(Color.WHITE);
        comboBox.setFocusable(false);

        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                // Không vẽ nền khi focus
            }

            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setForeground(Color.DARK_GRAY);
                button.setBackground(new Color(240, 240, 240));
                button.setFocusPainted(false);
                return button;
            }
        });

        // Bo góc và viền đẹp hơn
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(160, 160, 160), 1),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));

        // Tùy chỉnh renderer để chỉnh font + padding đẹp hơn
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
                lbl.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
                if (isSelected) {
                    lbl.setBackground(new Color(255, 200, 100)); // Màu nền khi hover hoặc chọn
                    lbl.setForeground(Color.BLACK);
                }
                return lbl;
            }
        });
    }

    public static void lamDepTextField(JTextField textField) {
        // Cài đặt phông chữ và màu sắc cho JTextField
        textField.setFont(new Font("Segoe UI", Font.BOLD, 14));
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.WHITE);

        // Cài đặt viền cho JTextField
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(160, 160, 160), 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        textField.setCaretColor(Color.BLACK); // Màu con trỏ khi gõ

        // KeyListener để chỉ cho phép nhập số
        chiduocnhapsoNormal(textField);
    }

    public static void chiduocnhapsoNormal(JTextField txt) {
        Document doc = txt.getDocument();
        if (doc instanceof AbstractDocument) {
            ((AbstractDocument) doc).setDocumentFilter(new DocumentFilter() {
                @Override
                public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                        throws BadLocationException {
                    if (isValidInput(fb, offset, 0, string)) {
                        super.insertString(fb, offset, string, attr);
                    }
                }

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                        throws BadLocationException {
                    if (isValidInput(fb, offset, length, text)) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }

                private boolean isValidInput(FilterBypass fb, int offset, int length, String text)
                        throws BadLocationException {
                    if (!text.matches("\\d*")) {
                        showWarning("Chỉ được nhập số");
                        return false;
                    }

                    String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
                    String newText = oldText.substring(0, offset) + text + oldText.substring(offset + length);

                    if (newText.length() > MAX_LENGTH) {
                        showWarning("Tối đa " + MAX_LENGTH + " chữ số");
                        return false;
                    }

                    return true;
                }

                private void showWarning(String msg) {
                    CustomMessageNormal(msg);
                }
            });
        }
    }

    public static void lamDepLabel(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(50, 50, 50)); // Màu chữ đậm, dễ đọc
        label.setOpaque(true); // Cho phép nền hiển thị
        label.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
    }

    private static final int MAX_LENGTH_DATE = 10; // Độ dài tối đa cho định dạng dd/MM/yyyy
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{0,2}(/\\d{0,2}(/\\d{0,4})?)?"); // Kiểm tra định
                                                                                                     // dạng tạm thời

    public static void chiduocnhapDDMMYYYY(JDateChooser dateChooser) {
        // Lấy JTextField bên trong JDateChooser
        JTextField editor = (JTextField) dateChooser.getDateEditor().getUiComponent();
        Document doc = editor.getDocument();
        if (doc instanceof AbstractDocument) {
            ((AbstractDocument) doc).setDocumentFilter(new DocumentFilter() {
                @Override
                public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                        throws BadLocationException {
                    if (isValidInput(fb, offset, 0, string)) {
                        super.insertString(fb, offset, string, attr);
                    }
                }

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                        throws BadLocationException {
                    if (isValidInput(fb, offset, length, text)) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }

                private boolean isValidInput(FilterBypass fb, int offset, int length, String text)
                        throws BadLocationException {
                    // Kiểm tra ký tự đầu vào: chỉ cho phép số và dấu /
                    if (!text.matches("[0-9/]*")) {
                        CustomMessage("Chỉ được nhập số và dấu /!");
                        return false;
                    }

                    // Lấy văn bản hiện tại và xây dựng văn bản mới sau khi thêm
                    String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
                    String newText = oldText.substring(0, offset) + text + oldText.substring(offset + length);

                    // Kiểm tra độ dài tối đa
                    if (newText.length() > MAX_LENGTH_DATE) {
                        CustomMessage("Định dạng ngày tối đa " + MAX_LENGTH_DATE + " ký tự (dd/MM/yyyy)!");
                        return false;
                    }

                    // Kiểm tra định dạng tạm thời
                    if (!DATE_PATTERN.matcher(newText).matches()) {
                        CustomMessage("Định dạng ngày không hợp lệ! Vui lòng nhập theo dd/MM/yyyy.");
                        return false;
                    }

                    // Nếu văn bản đầy đủ (10 ký tự), kiểm tra định dạng ngày hợp lệ
                    if (newText.length() == MAX_LENGTH_DATE) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        sdf.setLenient(false); // Không cho phép ngày không hợp lệ (ví dụ: 32/13/2025)
                        try {
                            sdf.parse(newText);
                        } catch (ParseException e) {
                            CustomMessage("Ngày không hợp lệ! Vui lòng nhập lại theo định dạng dd/MM/yyyy.");
                            return false;
                        }
                    }

                    return true;
                }
            });
        }
    }

    public static void chiduocnhapDDMMYYYYNormal(JDateChooser dateChooser) {
        // Lấy JTextField bên trong JDateChooser
        JTextField editor = (JTextField) dateChooser.getDateEditor().getUiComponent();
        Document doc = editor.getDocument();
        if (doc instanceof AbstractDocument) {
            ((AbstractDocument) doc).setDocumentFilter(new DocumentFilter() {
                @Override
                public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                        throws BadLocationException {
                    if (isValidInput(fb, offset, 0, string)) {
                        super.insertString(fb, offset, string, attr);
                    }
                }

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                        throws BadLocationException {
                    if (isValidInput(fb, offset, length, text)) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }

                private boolean isValidInput(FilterBypass fb, int offset, int length, String text)
                        throws BadLocationException {
                    // Kiểm tra ký tự đầu vào: chỉ cho phép số và dấu /
                    if (!text.matches("[0-9/]*")) {
                        CustomMessageNormal("Chỉ được nhập số và dấu /!");
                        return false;
                    }

                    // Lấy văn bản hiện tại và xây dựng văn bản mới sau khi thêm
                    String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
                    String newText = oldText.substring(0, offset) + text + oldText.substring(offset + length);

                    // Kiểm tra độ dài tối đa
                    if (newText.length() > MAX_LENGTH_DATE) {
                        CustomMessageNormal("Định dạng ngày tối đa " + MAX_LENGTH_DATE + " ký tự (dd/MM/yyyy)!");
                        return false;
                    }

                    // Kiểm tra định dạng tạm thời
                    if (!DATE_PATTERN.matcher(newText).matches()) {
                        CustomMessageNormal("Định dạng ngày không hợp lệ! Vui lòng nhập theo dd/MM/yyyy.");
                        return false;
                    }

                    // Nếu văn bản đầy đủ (10 ký tự), kiểm tra định dạng ngày hợp lệ
                    if (newText.length() == MAX_LENGTH_DATE) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        sdf.setLenient(false); // Không cho phép ngày không hợp lệ (ví dụ: 32/13/2025)
                        try {
                            sdf.parse(newText);
                        } catch (ParseException e) {
                            CustomMessageNormal("Ngày không hợp lệ! Vui lòng nhập lại theo định dạng dd/MM/yyyy.");
                            return false;
                        }
                    }

                    return true;
                }
            });
        }
    }
}
