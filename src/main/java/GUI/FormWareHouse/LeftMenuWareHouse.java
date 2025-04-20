package GUI.FormWareHouse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

import GUI.FormNhanVien.LeftSidebarMenu;

import static javax.swing.text.StyleConstants.setBackground;

public class LeftMenuWareHouse extends JPanel implements  ActionListener{
    private JButton btnImport;
    private JButton btnProduct;
    private JButton btnLogout;
    private JButton btnProductType;
    private JButton btnSupplier;

    private ActionListener listener;

    private Color bgColor = new Color(33,58,89);

    public JButton getBtnImport() {
        return btnImport;
    }

    public JButton getBtnProduct() {
        return btnProduct;
    }

//    public ActionListener getListener() {
//        return listener;
//    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public JButton getBtnProductType(){
        return btnProductType;
    }

    public JButton getBtnSupplier(){
        return  btnSupplier;
    }


    public LeftMenuWareHouse(){
        setBackground(bgColor);
        setMaximumSize(new Dimension(250, getHeight()));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
//        JPanel topPanel = new JPanel();
//        topPanel.setLayout(new GridLayout(5, 1, 2, 2));
//        topPanel.setBackground(Color.WHITE);
//        JPanel botPanel = new JPanel();
//        botPanel.setBackground(Color.WHITE);


//        //Thay doi kich thuoc anh
//        ImageIcon originalIcon = new ImageIcon("src/main/resources/images/icon/Logo_1.png");
//        Image originalImage = originalIcon.getImage();
//        Image resizedImage = originalImage.getScaledInstance(200,200,Image.SCALE_SMOOTH);
//        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        //Logo
        JLabel logoLabel = new JLabel( new ImageIcon("src/main/resources/images/icon/Logo1.png"));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(logoLabel);

        btnImport = createButton("Nhập hàng", "src/main/resources/images/ImportLogo.png");
        btnProduct = createButton("Sản phẩm", "src/main/resources/images/product.png");
        btnLogout = createButton("Đăng Xuất", "src/main/resources/images/exit.png");
        btnProductType= createButton("Loại sản phẩm","src/main/resources/images/category.png");
        btnSupplier= createButton("Nhà cung cấp","src/main/resources/images/supplier.png");

        setButtonStyle(btnImport);
        setButtonStyle(btnProduct);
        setButtonStyle(btnLogout);
        setButtonStyle(btnProductType);
        setButtonStyle(btnSupplier);

//        btnImport.addActionListener(this);
//        btnProduct.addActionListener(this);
//        btnLogout.addActionListener(this);

        add(btnImport);
        add(btnProduct);
        add(btnProductType);
        add(btnSupplier);

        add(Box.createVerticalGlue());

        add(btnLogout);

    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(iconPath));
        setButtonStyle(button);
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa nút
        return button;
    }

    public void setButtonStyle(JButton button) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setMaximumSize(new Dimension(350, 60));
        button.setPreferredSize(new Dimension(350, 60));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(12, 192, 223)); // Màu nền khi hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor); // Màu nền khi không hover
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (listener != null) {
            listener.actionPerformed(e);
        }
    }

    public void setActionListener(ActionListener listener) {
        this.listener = listener;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Detail Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);

        LeftMenuWareHouse TEST = new LeftMenuWareHouse();

        frame.add(TEST);
        frame.setVisible(true);
    }
}