package com.FormNhanVien;
import javax.swing.*;

import com.ComponentCommon.StyledTextField;

import java.awt.*;

public class FormSearchNV extends JPanel {
    public FormSearchNV() {
        setLayout(new BorderLayout());  // BorderLayout giúp kéo giãn searchField
        setBorder(BorderFactory.createTitledBorder("Tìm Kiếm"));
        setBackground(Color.WHITE);
        
        StyledTextField searchField = new StyledTextField();
        searchField.setColumns(50);

        add(searchField, BorderLayout.NORTH); // Căn giữa và kéo giãn searchField
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Search Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        
        FormSearchNV searchPanel = new FormSearchNV();
        
        frame.add(searchPanel);
        frame.setVisible(true);
    }
}