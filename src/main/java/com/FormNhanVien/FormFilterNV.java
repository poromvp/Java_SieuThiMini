package com.FormNhanVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.ComponentCommon.ButtonCustom;
import com.ComponentCommon.StyledTextField;

public class FormFilterNV extends JPanel {
 public FormFilterNV() {
        setBorder(BorderFactory.createTitledBorder("Lọc"));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(5, 5)); 
        
        JPanel filterPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        String[] doTuoiStr = {"Không","18-25 tuổi", "25-30 tuổi", "trên 30 tuổi"};
        String[] gioiTinhStr = {"không","Nam", "Nữ"};
        JLabel doTuoiLabel = new JLabel("Độ tuổi:");
        JLabel gioiTinhLabel = new JLabel("Giới tính:");
        JLabel khuVucLabel = new JLabel("Khu vực:");
        JComboBox<String> doTuoiComb = new JComboBox<>(doTuoiStr);
        JComboBox<String> gioiTinhComb = new JComboBox<>(gioiTinhStr);
        StyledTextField khuVucField = new StyledTextField(); 

        filterPanel.add(doTuoiLabel);
        filterPanel.add(gioiTinhLabel);
        filterPanel.add(khuVucLabel);
        filterPanel.add(doTuoiComb);
        filterPanel.add(gioiTinhComb);
        filterPanel.add(khuVucField);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        ButtonCustom btnFilter = new ButtonCustom("Lọc",16,"blue");
        ButtonCustom btnCancel = new ButtonCustom("Hủy",16,"red");
        btnPanel.add(btnFilter);
        btnPanel.add(btnCancel);

        add(filterPanel, BorderLayout.CENTER); 
        add(btnPanel, BorderLayout.SOUTH);     

    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(500,200);
        jFrame.add(new FormFilterNV());
        jFrame.setVisible(true);
    }
}
