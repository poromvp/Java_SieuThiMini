import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentForm extends JFrame {
    private JTextField txtId, txtName, txtMajor;
    private JComboBox<String> cbGender;
    private DefaultTableModel tableModel;
    private JTable table;

    private Connection conn;

    public StudentForm() {
        setTitle("Quản lý sinh viên (MySQL + Statement)");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        connectToDatabase();
        loadStudents();
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

        inputPanel.add(new JLabel("Giới tính:"));
        cbGender = new JComboBox<>(new String[]{"Nam", "Nữ"});
        inputPanel.add(cbGender);

        inputPanel.add(new JLabel("Ngành học:"));
        txtMajor = new JTextField();
        inputPanel.add(txtMajor);

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Thêm");
        JButton btnUpdate = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnClear = new JButton("Làm mới");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        String[] columns = {"Mã SV", "Họ tên", "Giới tính", "Ngành học"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        btnAdd.addActionListener(e -> addStudent());
        btnUpdate.addActionListener(e -> updateStudent());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtId.setText(tableModel.getValueAt(row, 0).toString());
                    txtName.setText(tableModel.getValueAt(row, 1).toString());
                    cbGender.setSelectedItem(tableModel.getValueAt(row, 2).toString());
                    txtMajor.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentdb", "root", ""
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối CSDL: " + ex.getMessage());
        }
    }

    private void loadStudents() {
        try {
            tableModel.setRowCount(0);
            String sql = "SELECT * FROM students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getString("major")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addStudent() {
        String id = txtId.getText();
        String name = txtName.getText();
        String gender = cbGender.getSelectedItem().toString();
        String major = txtMajor.getText();

        try {
            Statement stmt = conn.createStatement();
            String sql = String.format("INSERT INTO students VALUES ('%s', '%s', '%s', '%s')",
                    id, name, gender, major);
            stmt.executeUpdate(sql);
            loadStudents();
            clearForm();
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
            String gender = cbGender.getSelectedItem().toString();
            String major = txtMajor.getText();

            try {
                Statement stmt = conn.createStatement();
                String sql = String.format(
                    "UPDATE students SET name='%s', gender='%s', major='%s' WHERE id='%s'",
                    name, gender, major, id
                );
                stmt.executeUpdate(sql);
                loadStudents();
                clearForm();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn sinh viên cần sửa.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentForm().setVisible(true));
    }
}
