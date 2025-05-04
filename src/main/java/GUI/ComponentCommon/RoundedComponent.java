package GUI.ComponentCommon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class RoundedComponent {

    private static int radius;

    public static void setRadius(int r) {
        radius = r;
    }

    // Bo góc cho JPanel
    public static JPanel createRoundedPanel(JPanel panel, Color color) {
        panel.setOpaque(false);
        return new JPanelWithRoundedCorners(panel, color, radius);
    }

    // Bo góc cho JButton
    public static JButton createRoundedButton(JButton button, Color color) {
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return new JButtonWithRoundedCorners(button, color, radius);
    }

    // Bo góc cho JTextField
    public static JTextField createRoundedTextField(JTextField textField, Color color) {
        textField.setOpaque(false);
        textField.setBorder(null);
        return new JTextFieldWithRoundedCorners(textField, color, radius);
    }

    // Bo góc cho JLabel
    public static JLabel createRoundedLabel(JLabel label, Color color) {
        label.setOpaque(false);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return new JLabelWithRoundedCorners(label, color, radius);
    }

    // Bo góc cho JComboBox
    public static JComboBox<String> createRoundedComboBox(JComboBox<String> comboBox, Color color) {
        comboBox.setOpaque(false);
        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, bounds.width, bounds.height, radius, radius);
                g2.dispose();
            }
        });
        comboBox.setFont(new Font("Arial", Font.BOLD, 14));

        comboBox.setBackground(new Color(0, 0, 0, 0));
        comboBox.setBorder(null);
        return comboBox;
    }
    

    // Bo góc cho JSpinner
    public static JSpinner createRoundedSpinner(JSpinner spinner, Color color) {
        return new JSpinnerWithRoundedCorners(spinner, color, radius);
    }

    // Lớp con cho JPanel
    private static class JPanelWithRoundedCorners extends JPanel {
        private final JPanel panel;
        private  Color color;

        public JPanelWithRoundedCorners(JPanel panel, Color color, int radius) {
            this.panel = panel;
            if(color != null){
                this.color = color;
            }
            setOpaque(false);
            setLayout(new BorderLayout());
            add(panel, BorderLayout.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
        }
    }

    // Lớp con cho JButton
    private static class JButtonWithRoundedCorners extends JButton {
        private  Color color;

        public JButtonWithRoundedCorners(JButton button, Color color, int radius) {
            super(button.getText());
            this.color = color;
            setOpaque(false);
            setFont(button.getFont());
            setForeground(button.getForeground());
            setPreferredSize(button.getPreferredSize());
            setBorder(null);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
        public void setColorBackground(Color color) {
            this.color = color;
            repaint(); // Vẽ lại nút với màu mới
        }

    }

    // Lớp con cho JTextField
    private static class JTextFieldWithRoundedCorners extends JTextField {
        private final Color color;

        public JTextFieldWithRoundedCorners(JTextField textField, Color color, int radius) {
            super(textField.getText());
            this.color = color;
            setOpaque(false);
            setBorder(null);
            setFont(textField.getFont());
            setForeground(textField.getForeground());
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // Lớp con cho JLabel
    private static class JLabelWithRoundedCorners extends JLabel {
        private final Color color;

        public JLabelWithRoundedCorners(JLabel label, Color color, int radius) {
            super(label.getText());
            this.color = color;
            setOpaque(false);
            setFont(label.getFont());
            setForeground(label.getForeground());
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // Lớp con cho JSpinner
    // Lớp con cho JSpinner
private static class JSpinnerWithRoundedCorners extends JSpinner {
    private final Color color;

    public JSpinnerWithRoundedCorners(JSpinner spinner, Color color, int radius) {
        super(spinner.getModel());
        this.color = color;
        setOpaque(false);
        setBorder(null);
        setFont(spinner.getFont());
        setForeground(spinner.getForeground());
        setPreferredSize(spinner.getPreferredSize());

        // Áp dụng bo góc và màu nền cho editor của JSpinner (JTextField)
        JComponent editor = (JComponent) getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
            textField.setOpaque(false);  // Đảm bảo rằng nền không bị ẩn
            textField.setBorder(null);   // Bỏ border mặc định
            textField.setFont(spinner.getFont());
            textField.setFont(new Font("Arial", Font.BOLD, 14));
            textField.setForeground(Color.BLACK);
            textField.setBackground(color);  // Áp dụng màu nền cho text field
            // Áp dụng bo góc cho text field
            textField.setOpaque(true);  // Đảm bảo rằng nền màu hiển thị
        }

        // Áp dụng bo góc và màu nền cho các nút tăng/giảm giá trị
        Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JComponent) {
                ((JComponent) component).setOpaque(false);
                ((JComponent) component).setBackground(color);
                // Vẽ bo góc cho các nút
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    button.setOpaque(false);
                    button.setBorderPainted(false);
                    button.setFocusPainted(false);
                    button.setBackground(color);  // Áp dụng màu nền cho nút
                    button.setPreferredSize(new Dimension(30, 30));  // Đảm bảo kích thước nút hợp lý
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.dispose();
    }
}


    // Hàm main test
    public static void main(String[] args) {
        setRadius(20);

        JFrame frame = new JFrame("Rounded Component Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setPreferredSize(new Dimension(200, 100));
        panel = createRoundedPanel(panel, Color.CYAN);

        JButton button = new JButton("Click me");
        button = createRoundedButton(button, Color.GREEN);

        JTextField textField = new JTextField("Enter Text", 15);
        textField = createRoundedTextField(textField, Color.PINK);

        JLabel label = new JLabel("This is a label");
        label = createRoundedLabel(label, Color.YELLOW);

        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Item 1", "Item 2"});
        comboBox = createRoundedComboBox(comboBox, Color.LIGHT_GRAY);

        JSpinner spinner = new JSpinner();
        spinner = createRoundedSpinner(spinner, Color.ORANGE);

        frame.add(panel);
        frame.add(button);
        frame.add(textField);
        frame.add(label);
        frame.add(comboBox);
        frame.add(spinner);

        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
