package GUI.ComponentCommon;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

public class ButtonCustom extends JButton {
    private boolean isRounded;
    private Color bgColor,originalColor;

    public ButtonCustom(String text, int fontsize, String color) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, fontsize));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        this.isRounded = true;

        // Xác định màu nền ban đầu
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

        // Hiệu ứng hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bgColor = bgColor.darker();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bgColor = originalColor;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setCursor(new Cursor(Cursor.HAND_CURSOR)); //hiệu ứng cursor
        if (isRounded) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            super.paintComponent(g);
        }
    }

    public ButtonCustom(String text, String type, int fontsize, int w, int h) {
        initComponent(text, type, fontsize, w, h);
    }


    private void initComponent(String text, String type, int fontsize, int w, int h) {
        ImageIcon addURL = new ImageIcon("src/main/resources/images/icon/addIcon.png");
        ImageIcon delURL = new ImageIcon("src/main/resources/images/icon/delIcon.png");
        ImageIcon editURL = new ImageIcon("src/main/resources/images/icon/editIcon.png");
        ImageIcon resetURL = new ImageIcon("src/main/resources/images/reset_button.png");
        ImageIcon searchURL = new ImageIcon("src/main/resources/images/icon/search.png");
        ImageIcon hisURL = new ImageIcon("src/main/resources/images/icon/hisIcon.png");
        ImageIcon exportPDF = new ImageIcon("src/main/resources/images/icon/printer.png");
        switch (type) {
            case "del":
                this.setIcon(resizeIcon(delURL, w, h));
                break;
            case "add":
                this.setIcon(resizeIcon(addURL, w, h));
                break;
            case "edit":
                this.setIcon(resizeIcon(editURL, w, h));
                break;
            case "reset":
                this.setIcon(resizeIcon(resetURL,w,h));
                break;
            case "search":
                this.setIcon(resizeIcon(searchURL,w,h));
                break;
            case "his":
                this.setIcon(resizeIcon(hisURL, w, h));
                break;
            case "printer":
                this.setIcon(resizeIcon(exportPDF, w, h));
                break;
            default:
                break;
        }

        this.setText(text);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
        this.setFont(new Font("Arial", Font.PLAIN, fontsize));

        // Xóa viền và background
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);

    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        if (img != null) {
            Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImg);
        }
        return icon;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test ButtonCustom");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new FlowLayout());

        ButtonCustom button = new ButtonCustom("lưu",16,"blue");
        button.addActionListener(_ -> JOptionPane.showMessageDialog(frame, "Button Clicked!"));

        frame.add(button);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
