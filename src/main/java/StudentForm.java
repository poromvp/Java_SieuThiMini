import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import JDBC.DBConnection;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentForm extends JFrame {
    private JTextField txtId, txtName, txtDiem, txtMaKhoa;
    private DefaultTableModel tableModel;
    private JTable table;

    private Connection conn;
    private JComboBox<String> cbKhoa;


    public StudentForm() {
        setTitle("Quản lý sinh viên");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        connectToDatabase();
        loadStudents();
        loadTenKhoaToComboBox();
    }

    private void initUI() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sinh viên"));
    
        inputPanel.add(new JLabel("Mã SV:"));
        txtId = new JTextField();
        inputPanel.add(txtId);
    
        inputPanel.add(new JLabel("Họ tên:"));
        txtName = new JTextField();
        inputPanel.add(txtName);
    
        inputPanel.add(new JLabel("Điểm:"));
        txtDiem = new JTextField();
        inputPanel.add(txtDiem);
    
        inputPanel.add(new JLabel("Tên Khoa:"));
        cbKhoa = new JComboBox<>();
        inputPanel.add(cbKhoa); // ComboBox hiển thị TenKhoa
    
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Thêm");
        JButton btnUpdate = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm mới");
    
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
    
        String[] columns = {"Mã SV", "Họ tên", "Điểm", "Tên Khoa"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
    
        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());
        btnDelete.addActionListener(e -> deleteStudent());
        btnClear.addActionListener(e -> clearFields());
    
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtId.setText(tableModel.getValueAt(row, 0).toString());
                    txtName.setText(tableModel.getValueAt(row, 1).toString());
                    txtDiem.setText(tableModel.getValueAt(row, 2).toString());
                    cbKhoa.setSelectedItem(tableModel.getValueAt(row, 3).toString()); // set combobox theo tên khoa
                }
            }
        });
    
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }
    

    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối thành công!");
        } catch (Exception e) {
            System.out.println("Kết nối thất bại:");
            e.printStackTrace();
        }
    }
    private void loadTenKhoaToComboBox() {
        try {
            String sql = "SELECT s.id, s.name, s.diem, k.TenKhoa FROM students s JOIN khoa k ON s.MaKhoa = k.MaKhoa";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String diem = rs.getString("diem");
                String tenKhoa = rs.getString("TenKhoa");
                cbKhoa.addItem(tenKhoa);
                txtId.setText(id);
                txtDiem.setText(diem);
                txtName.setText(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadStudents() {
        if (conn == null) {
            System.out.println("❌ Không thể tải sinh viên vì chưa kết nối CSDL.");
            return;
        }
        try {
            tableModel.setRowCount(0);
            String sql = "SELECT s.id, s.name, s.diem, k.TenKhoa FROM students s JOIN khoa k ON s.MaKhoa = k.MaKhoa";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String diem = rs.getString("diem");
                String tenKhoa = rs.getString("TenKhoa");
    
                tableModel.addRow(new Object[]{id, name, diem, tenKhoa});
            }
    

    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

    private void addStudent() {
        String id = txtId.getText();
        String name = txtName.getText();
        String diem = txtDiem.getText();
        String maKhoa = txtMaKhoa.getText();

        try {
            Statement stmt = conn.createStatement();
            String sql = String.format("INSERT INTO students VALUES ('%s', '%s', '%s', '%s')",
                    id, name, diem, maKhoa);
            stmt.executeUpdate(sql);
            loadStudents();
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this, "Mã sinh viên đã tồn tại!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateStudent() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String id = txtId.getText();
            String name = txtName.getText();
            String diem = txtDiem.getText();
            String maKhoa = txtMaKhoa.getText();

            try {
                Statement stmt = conn.createStatement();
                String sql = String.format(
                        "UPDATE students SET name='%s', diem='%s', MaKhoa='%s' WHERE id='%s'",
                        name, diem, maKhoa, id
                );
                stmt.executeUpdate(sql);
                loadStudents();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn sinh viên để sửa.");
        }
    }

    private void deleteStudent() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String id = txtId.getText();
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sinh viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Statement stmt = conn.createStatement();
                    String sql = String.format("DELETE FROM students WHERE id='%s'", id);
                    stmt.executeUpdate(sql);
                    loadStudents();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn sinh viên để xóa.");
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtDiem.setText("");
        txtMaKhoa.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {
        new StudentForm().setVisible(true);
    }
}
