package GUI.FormEmployee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import BLL.NhanVienBLL;
import BLL.TaiKhoanBLL;
import GUI.ComponentCommon.StyledLeftMenubutton;
import GUI.FrameEmployee;

public class LeftMenu extends JPanel implements ActionListener {

    private JButton btn_home;
    private JButton btn_sell;
//    private JButton btn_orderManagement;
    private JButton btn_report;
    private NhanVienBLL bllnv = new NhanVienBLL();
    private TaiKhoanBLL blltk = new TaiKhoanBLL();
    // private FrameNhanVien frame; // Tham chiếu đến FrameNhanVien
    private Color bgColor = new Color(33,58,89);
    public LeftMenu(String maNV) { 
        
        
        setBackground(bgColor);
        setPreferredSize(new Dimension(230, getHeight()));
        setLayout(new FlowLayout());
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 10, 0, 0);
        setBorder(emptyBorder);

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
        JLabel nameLabel = new JLabel(bllnv.getNameNV(maNV));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        employeePanel.add(nameLabel);

        // Chức vụ
        JLabel roleLabel = new JLabel(blltk.getQuyenNV(maNV));
        roleLabel.setForeground(Color.LIGHT_GRAY);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        employeePanel.add(roleLabel);

        add(employeePanel);
        add(Box.createVerticalGlue());

        btn_home =  new JButton("Trang Chủ", new ImageIcon("src/main/resources/images/home.png"));
        btn_sell = new JButton("bán hàng", new ImageIcon("src/main/resources/images/selling.png"));
//        btn_orderManagement = new JButton("Quản lí bán hàng", new ImageIcon("src/main/resources/images/product.png"));
        btn_report =  new StyledLeftMenubutton("src/main/resources/images/report.png", "Báo cáo");
        
        btn_home = new StyledLeftMenubutton("src/main/resources/images/home.png", "Trang Chủ");
        btn_sell = new StyledLeftMenubutton("src/main/resources/images/selling.png", "Bán Hàng");
//        btn_orderManagement = new StyledLeftMenubutton("src/main/resources/images/selling.png", "Quản Lí đơn hàng");


        setButtonStyle(btn_home);
        setButtonStyle(btn_sell);
//        setButtonStyle(btn_orderManagement);
        setButtonStyle(btn_report);


        btn_home.addActionListener(this);
        btn_sell.addActionListener(this);
//        btn_orderManagement.addActionListener(this);
        btn_report.addActionListener(this);

        add(btn_home);
        add(btn_sell);
//        add(btn_orderManagement);
        add(btn_report);
    }



    public void setButtonStyle(JButton button) {
        button.setBackground(Color.WHITE); 
        button.setForeground(Color.BLACK); 
        button.setFocusPainted(false);  
        button.setBorderPainted(false);  
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Kiểu chữ
        button.setPreferredSize(new Dimension(200, 60)); // Kích thước nút
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Con trỏ khi hover
        Border emptyBorder = BorderFactory.createEmptyBorder(20,10,20,10);
        setBorder(emptyBorder);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(240, 240, 240)); // Màu nền khi hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE); // Màu nền khi không hover
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_sell) {
            System.out.println("click btn_sell");
            FrameEmployee.setPage("formOrder", "Bán Hàng");
        }
        if (e.getSource() == btn_home) {
            System.out.println("click btn_home");
            FrameEmployee.setPage("formHome", "Trang Chủ");
        } 
//        if (e.getSource() == btn_orderManagement) {
//            System.out.println("click btn_orderManagement");
//            FrameEmployee.setPage("formOrderManagement", "Quản Lí Đơn Hàng");
//        }

        if (e.getSource() == btn_report) {
            System.out.println("click btn_report");
            FrameEmployee.setPage("formReport", "Báo cáo đơn hàng");
        }
    }
}
