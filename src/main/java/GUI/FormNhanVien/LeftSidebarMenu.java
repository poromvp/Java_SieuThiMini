package GUI.FormNhanVien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class LeftSidebarMenu extends JPanel implements ActionListener {

    private JButton btnHome;
    private JButton btnEmployee;
    private JButton btnProduct;
    private JButton btnReport;
    private JButton btnAccount;
    private JButton btnLogout;
    private JButton btnTheTV;
    private JButton btnKM;
    private JButton selectedButton = null;

    private ActionListener listener;  

    private Color bgColor = new Color(33,58,89);

    public JButton getBtnHome() {
        return btnHome;
    }

    public JButton getBtnEmployee() {
        return btnEmployee;
    }

    public JButton getBtnProduct() {
        return btnProduct;
    }

    public JButton getBtnReport() {
        return btnReport;
    }

    public JButton getBtnAccount() {
        return btnAccount;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public JButton getBtnTheTV(){
        return btnTheTV;
    }

    public JButton getBtnKM(){
        return btnKM;
    }

    public LeftSidebarMenu() {
        setBackground(bgColor);
        setMaximumSize(new Dimension(250, getHeight()));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel employeePanel = new JPanel();
        employeePanel.setLayout(new BoxLayout(employeePanel, BoxLayout.Y_AXIS));
        employeePanel.setBackground(new Color(25,45,70));
        employeePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Avatar nhân viên (có thể thay bằng ảnh thật sau này)
        ImageIcon baseAvata = new ImageIcon("src/main/resources/images/icon/boy.png");
        Image resizeAvata = baseAvata.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        JLabel avtLabel = new JLabel(new ImageIcon(resizeAvata));
        avtLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        avtLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        employeePanel.add(avtLabel);

        // Tên nhân viên
        JLabel nameLabel = new JLabel("Nguyễn Văn A");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        employeePanel.add(nameLabel);

        // Chức vụ
        JLabel roleLabel = new JLabel("Quản lý");
        roleLabel.setForeground(Color.LIGHT_GRAY);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        employeePanel.add(roleLabel);

        add(employeePanel);
        add(Box.createVerticalGlue());
    
//        // Logo
//        JLabel logoLabel = new JLabel(new ImageIcon("src/main/resources/images/icon/Logo1.png"));
//        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa logo
//        add(logoLabel);
    
        // Tạo các nút
        btnHome = createButton("Trang Chủ", "src/main/resources/images/home.png");
        btnEmployee = createButton("Nhân Viên", "src/main/resources/images/employee.png");
        btnProduct = createButton("Sản Phẩm", "src/main/resources/images/product.png");
        btnReport = createButton("Báo Cáo", "src/main/resources/images/report.png");
        btnAccount = createButton("Tài Khoản", "src/main/resources/images/account.png");
        btnLogout = createButton("Đăng Xuất", "src/main/resources/images/exit.png");
        btnTheTV = createButton("Thành Viên", "src/main/resources/images/thethanhvien.png");
        btnKM = createButton("Khuyến Mãi", "src/main/resources/images/discount.png");
    
        // Thêm các nút vào panel
        add(btnHome);
        add(btnEmployee);
        add(btnProduct);
        add(btnAccount);
        add(btnTheTV);
        add(btnKM);
        add(btnReport);
        
        // Thêm khoảng trống linh hoạt để đẩy nút đăng xuất xuống dưới cùng
        add(Box.createVerticalGlue());
        
        // Thêm nút đăng xuất
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
                if (button != selectedButton) {
                    button.setBackground(new Color(145, 228, 243)); // Hover
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button != selectedButton) {
                    button.setBackground(bgColor); // Trở về bình thường
                }
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();
            highlightSelectedButton(clickedButton);
        }

        if (listener != null) {
            listener.actionPerformed(e);
        }
    }

    private void highlightSelectedButton(JButton button) {
        if (selectedButton != null) {
            selectedButton.setBackground(bgColor); // reset nút trước đó
        }
        button.setBackground(new Color(12, 192, 223)); // màu khi chọn
        selectedButton = button;
    }

    public void setActionListener(ActionListener listener) {
        this.listener = listener;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Detail Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        
        LeftSidebarMenu TEST = new LeftSidebarMenu();
        
        frame.add(TEST);
        frame.setVisible(true);
    }
}