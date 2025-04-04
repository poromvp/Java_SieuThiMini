package com.ComponentCommon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class StyledTextField extends JTextField {
    private Color bgColor = new Color(17, 32, 51);
    public StyledTextField() {
        // super(columns);
        setFont(new Font("SansSerif", Font.BOLD, 14));
        setForeground(Color.BLACK);
        setBackground(Color.WHITE);
        setCaretColor(Color.RED);
        setOpaque(true); 
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor, 2, true), 
                BorderFactory.createEmptyBorder(5, 10, 5, 10) 
        ));
        setPreferredSize(new Dimension(200, 30)); 
    }

    public StyledTextField(int w, int h) {
        // super(columns);
        setFont(new Font("SansSerif", Font.BOLD, 14));
        setForeground(Color.BLACK);
        setBackground(Color.WHITE);
        setCaretColor(Color.RED);
        setOpaque(true); 
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor, 2, true), 
                BorderFactory.createEmptyBorder(5, 10, 5, 10) 
        ));
        setPreferredSize(new Dimension(w, h)); 
    }

    public  void SetEnabled(boolean isAdd){
        setEnabled(isAdd);
        setEditable(isAdd);
        int thickness = isAdd == true? 2: 0;
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor, thickness, true), 
            BorderFactory.createEmptyBorder(5, 10, 5, 10) 
        ));
    }

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test StyledTextField");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 250);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            
            JPanel panel = new JPanel();
            panel.setLayout(null); 

            //  Tạo các thành phần
            JLabel nameLabel = new JLabel("Họ và Tên:");
            StyledTextField nameField = new StyledTextField();
            JLabel emailLabel = new JLabel("Email:");
            StyledTextField emailField = new StyledTextField();
            JButton btnSubmit = new JButton("Gửi");
            nameField.SetEnabled(false);
            //  Đặt vị trí từng thành phần
            nameLabel.setBounds(30, 30, 80, 25);
            nameField.setBounds(120, 30, 200, 35);
            emailLabel.setBounds(30, 80, 80, 25);
            emailField.setBounds(120, 80, 200, 35);
            btnSubmit.setBounds(150, 140, 100, 35);

            //  Thêm vào panel
            panel.add(nameLabel);
            panel.add(nameField);
            panel.add(emailLabel);
            panel.add(emailField);
            panel.add(btnSubmit);

            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }
}
