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

    public LeftSidebarMenu() {
        setBackground(bgColor);
        setMaximumSize(new Dimension(250, getHeight()));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
    
        // Logo
        JLabel logoLabel = new JLabel(new ImageIcon("src/main/resources/images/icon/Logo1.png"));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa logo
        add(logoLabel);
    
        // Tạo các nút
        btnHome = createButton("Trang Chủ", "src/main/resources/images/home.png");
        btnEmployee = createButton("Nhân Viên", "src/main/resources/images/employee.png");
        btnProduct = createButton("Sản Phẩm", "src/main/resources/images/product.png");
        btnReport = createButton("Báo Cáo", "src/main/resources/images/report.png");
        btnAccount = createButton("Tài Khoản", "src/main/resources/images/account.png");
        btnLogout = createButton("Đăng Xuất", "src/main/resources/images/exit.png");
        btnTheTV = createButton("Thẻ Thành Viên", "src/main/resources/images/thethanhvien.png");
    
        // Thêm các nút vào panel
        add(btnHome);
        add(btnEmployee);
        add(btnProduct);
        add(btnAccount);
        add(btnTheTV);
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
        
        LeftSidebarMenu TEST = new LeftSidebarMenu();
        
        frame.add(TEST);
        frame.setVisible(true);
    }
}