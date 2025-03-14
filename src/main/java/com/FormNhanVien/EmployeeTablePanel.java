package com.FormNhanVien;
import javax.swing.*;

import com.ComponentCommon.StyledTable;

import java.awt.*;

public class EmployeeTablePanel extends JPanel {
    public EmployeeTablePanel() {
        setLayout(new BorderLayout());

        String[] columnNames = {"Mã NV", "Họ Tên", "Ngày Sinh","Giới tính", "Địa Chỉ", "Chức Vụ", "Số ĐT"};
        Object[][] data = {
            {"1", "Nguyễn Văn A", "20","", "Hà Nội"},
            {"2", "Trần Thị B", "22", "","Hồ Chí Minh"},
            {"3", "Lê Văn C", "25", "","Đà Nẵng"}
        };

        StyledTable tablePanel = new StyledTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(tablePanel);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee Table Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        EmployeeTablePanel employeeTablePanel = new EmployeeTablePanel();
        
        frame.add(employeeTablePanel);
        frame.setVisible(true);
    }
}