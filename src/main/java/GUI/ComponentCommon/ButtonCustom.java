package GUI.ComponentCommon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonCustom extends JButton {
    private boolean isRounded;
    private Color bgColor, originalColor;

    public ButtonCustom(String text, int fontsize, String color) {
        super(text);
        this.isRounded = true;
        setFont(new Font("Arial", Font.BOLD, fontsize));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);

        // Thiết lập màu nền gốc
        switch (color.toLowerCase()) {
            case "blue":
                originalColor = new Color(51, 204, 255);
                break;
            case "red":
                originalColor = new Color(204, 51, 0);
                break;
            case "green":
                originalColor = new Color(102, 255, 102);
                break;
            case "black":
                originalColor = new Color(17, 32, 51);
                break;
            default:
                originalColor = Color.GRAY;
                break;
        }
        bgColor = originalColor;

        applyMouseEffects();
    }

    public ButtonCustom(String text, String type, int fontsize, int w, int h) {
        super(text);
        this.isRounded = false;
        setFont(new Font("Arial", Font.PLAIN, fontsize));
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
        setForeground(Color.BLACK);
    
        setBorderPainted(false);
        setContentAreaFilled(true);             // Cho phép tô nền
        setFocusPainted(false);
        setOpaque(true);                        // Bắt buộc vẽ nền
        setBackground(Color.WHITE);            // Màu nền trắng
    
        ImageIcon icon = switch (type) {
            case "add" -> new ImageIcon("src/main/resources/images/icon/addIcon.png");
            case "del" -> new ImageIcon("src/main/resources/images/icon/delIcon.png");
            case "edit" -> new ImageIcon("src/main/resources/images/icon/editIcon.png");
            case "reset" -> new ImageIcon("src/main/resources/images/reset_button.png");
            case "search" -> new ImageIcon("src/main/resources/images/icon/search.png");
            case "his" -> new ImageIcon("src/main/resources/images/icon/hisIcon.png");
            case "printer" -> new ImageIcon("src/main/resources/images/icon/printer.png");
            case "importExcel" -> new ImageIcon("src/main/resources/images/icon/importExcel.png");
            case "exportExcel" -> new ImageIcon("src/main/resources/images/icon/exportExcel.png");
            default -> null;
        };
    
        if (icon != null) {
            setIcon(resizeIcon(icon, w, h));
        }
    
        // Thiết lập màu nền gốc
        originalColor = Color.WHITE;
        bgColor = originalColor;
    
        applyMouseEffects();  // Nếu có hiệu ứng di chuột, giữ lại
    }
    

    private void applyMouseEffects() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bgColor = originalColor.darker();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bgColor = originalColor;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                bgColor = originalColor.darker().darker();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (contains(e.getPoint())) {
                    bgColor = originalColor.darker();
                } else {
                    bgColor = originalColor;
                }
                repaint();
            }
        });
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        if (img != null) {
            Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImg);
        }
        return icon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền tùy theo bo tròn hay không
        if (isRounded) {
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        } else {
            g2.setColor(bgColor);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        g2.dispose();
        super.paintComponent(g);
    }
    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test ButtonCustom");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new FlowLayout());

        ButtonCustom button = new ButtonCustom("Lưu", 16, "blue");
        button.addActionListener(_ -> JOptionPane.showMessageDialog(frame, "Button Clicked!"));

        ButtonCustom iconButton = new ButtonCustom("Thêm", "add", 12, 30, 30);

        frame.add(button);
        frame.add(iconButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
