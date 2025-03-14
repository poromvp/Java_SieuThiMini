
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NhanVienFrame extends JFrame{
    public NhanVienFrame(){
        init();
    }
    public void init(){
        //JFrame
        setTitle("Quản Lý Nhân Viên");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setPreferredSize(new Dimension(150, 500));
        sidebarPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.add(new JLabel("THÔNG TIN", SwingConstants.CENTER));
        sidebarPanel.add(Box.createVerticalGlue());
        sidebarPanel.add(new JLabel("ĐĂNG XUẤT", SwingConstants.CENTER));

        // Main Panel (Chứa bảng nhân viên) - Màu vàng
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

        // Tiêu đề
        JLabel titleLabel = new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Bảng Nhân Viên - Màu xanh dương
        String[] columnNames = {"Mã NV", "Tên NV", "Chức Vụ", "Lương"};
        Object[][] data = {
                {"NV001", "Nguyễn Văn A", "Nhân viên", "10,000,000"},
                {"NV002", "Trần Thị B", "Quản lý", "15,000,000"},
                {"NV003", "Lê Văn C", "Kế toán", "12,000,000"}
        };
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable employeeTable = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        
        

        // Panel Bảng Nhân Viên 
        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        tablePanel.add(new JLabel("BẢNG NHÂN VIÊN"));
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Panel Hành Động 
        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        actionPanel.setPreferredSize(new Dimension(100, 100));
        actionPanel.setLayout(new GridLayout(3, 1, 5, 5));
        actionPanel.add(new JButton("Thêm"));
        actionPanel.add(new JButton("Sửa"));
        actionPanel.add(new JButton("Xóa"));
        mainPanel.add(actionPanel, BorderLayout.EAST);

        // Panel Tìm Kiếm 
        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        searchPanel.add(new JLabel("Tìm kiếm"));
        searchPanel.add(new JTextField(15));
        searchPanel.add(new JButton("Tìm"));
        mainPanel.add(searchPanel, BorderLayout.SOUTH);

        // Thêm vào JFrame
        add(sidebarPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
        
    }
    public static void main(String[] args) {
        new NhanVienFrame();
    }
}
