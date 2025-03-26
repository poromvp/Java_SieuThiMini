package com.FormEmployee;

    


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ComponentCommon.StyledTextField;
import com.QR.ScanQR;

import DAL.SanPhamDAL;
import DTO.SanPham;



public class FormQRscan extends JPanel {
	
	public static JPanel containerQR = new JPanel();
	public static ScanQR scanQR = new ScanQR();
	
	public static JLabel lbl_tenSP = new JLabel("Tên SP: ");
	public static JTextField txt_tenSP = new StyledTextField();
	
	public static JLabel lbl_giaSP = new JLabel("Gia SP: ");
	public static JTextField txt_giaSP = new StyledTextField();
	
	public static JLabel lbl_soLuong = new JLabel("Số lượng: ");
	public static JTextField txt_soLuong = new StyledTextField();
	
	public static JLabel lbl_ngaySX = new JLabel("Ngày SX: ");
	public static JTextField txt_ngaySX = new StyledTextField();
	
	public static JLabel lbl_ngayHH = new JLabel("Ngày HH: ");
	public static JTextField txt_ngayHH = new StyledTextField();
	
	
	public FormQRscan() {		
		this.setLayout(new BorderLayout());
		setSize(500, 400);
		setBackground(new Color(30, 144, 255));
        setLayout(new BorderLayout());

        
        containerQR.setLayout(new GridBagLayout());
//        containerQR.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Co giãn cả ngang và dọc
//        gbc.insets = new Insets(5, 5, 5, 5);

        // Hàng 1: "Tên SP" chiếm 1 cột, ô nhập chiếm 3 cột
        addCell(containerQR, lbl_tenSP, 0, 0, 1, 0);
        addCell(containerQR, txt_tenSP, 1, 0, 5, 1.0);

        // Hàng 2: "Giá" chiếm 1 ô, ô nhập giá chiếm 2 ô, "Số lượng" chiếm 1 ô, ô nhập số lượng chiếm 2 ô
        addCell(containerQR, lbl_giaSP, 0, 1, 1, 0);
        addCell(containerQR, txt_giaSP, 1, 1, 2, 0.5); // weightx = 0.5 để cân bằng
        addCell(containerQR, lbl_soLuong, 3, 1, 1, 0);
        addCell(containerQR, txt_soLuong, 4, 1, 2, 0.5);

        // Hàng 3: "NSX" chiếm 1 ô, ô nhập NSX chiếm 2 ô, "HSD" chiếm 1 ô, ô nhập HSD chiếm 2 ô
        addCell(containerQR, lbl_ngaySX, 0, 2, 1, 0);
        addCell(containerQR, txt_ngaySX, 1, 2, 2, 0.5);
        addCell(containerQR, lbl_ngayHH, 3, 2, 1, 0);
        addCell(containerQR, txt_ngayHH, 4, 2, 2, 0.5);

     
        
       
     
        
   
        add(containerQR, BorderLayout.CENTER);
        add(scanQR, BorderLayout.EAST);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	}
	
	 private void addCell(JPanel panel, Component component, int x, int y, int width, double weightx) {
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridx = x;
	        gbc.gridy = y;
	        gbc.gridwidth = width;
	        gbc.fill = GridBagConstraints.BOTH;
	        gbc.weightx = weightx; 
	        gbc.insets = new Insets(5, 5, 5, 5);
	        panel.add(component, gbc);
	    }
	
	
	public static void insertProductInformation(int id) {
		SanPham sp = SanPhamDAL.getSanPhamByMaSanPham(id);
		if(sp != null) {
			txt_tenSP.setText(sp.getTenSP());
			txt_giaSP.setText(sp.getGia() + "");
			txt_soLuong.setText(sp.getTenSP());
			txt_ngaySX.setText(sp.getTenAnh());
			txt_ngayHH.setText(sp.getTenAnh());
		}
	}

	 public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.add(new FormQRscan());

       

        frame.setVisible(true);
    }
}
