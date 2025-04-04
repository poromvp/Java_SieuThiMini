package FormTaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.ComponentCommon.StyledTable;

import java.awt.*;

public class FormTableAccount extends JPanel {
    private StyledTable accountTable;
    private DefaultTableModel tableModel;

    public FormTableAccount() {
        setLayout(new BorderLayout());
        String[] columnNames = {"Mã NV", "Tài khoản", "Mật khẩu","Trạng thái"};
        Object[][] data = {
            {"1", "userA", "1234","Đang hoạt động"},
            {"2", "userB", "5678","Ngưng hoạt động"},
            {"3", "userC", "abcd","Đang hoạt động"}
        };

        tableModel = new DefaultTableModel(data, columnNames);
        accountTable = new StyledTable(data,columnNames);
        JScrollPane scrollPane = new JScrollPane(accountTable);

        add(scrollPane, BorderLayout.CENTER);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getAccountTable() {
        return accountTable;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test FormTableAccount");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.add(new FormTableAccount());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
