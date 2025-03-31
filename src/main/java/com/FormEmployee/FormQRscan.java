package com.FormEmployee;

    


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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

import BLL.SanPhamBLL;
import DAL.SanPhamDAL;
import DTO.SanPhamDTO;


public class FormQRscan extends JPanel {

    public static JPanel containerQR = new JPanel();
    public static ScanQR scanQR = new ScanQR();

    public static JLabel lbl_tenSP = new JLabel("Tên SP: ");
    public static JTextField txt_tenSP = new StyledTextField();

    public static JLabel lbl_giaSP = new JLabel("Gia SP: ");
    public static JTextField txt_giaSP = new StyledTextField();

    public static JLabel lbl_soLuong = new JLabel("Số lượng: ");
    public static JTextField txt_soLuong = new StyledTextField();

    public static JLabel lbl_loSX = new JLabel("Lô SX: ");
    public static JTextField txt_loSX = new StyledTextField();

    public static JLabel lbl_ngaySX = new JLabel("Ngày SX: ");
    public static JTextField txt_ngaySX = new StyledTextField();

    public static JLabel lbl_ngayHH = new JLabel("Ngày HH: ");
    public static JTextField txt_ngayHH = new StyledTextField();

    public FormQRscan() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400));
        setBackground(new Color(30, 144, 255));

        containerQR.setLayout(new GridBagLayout());

        // Giảm kích thước font của JLabel
        Font labelFont = new Font("Arial", Font.PLAIN, 10);
        lbl_tenSP.setFont(labelFont);
        lbl_giaSP.setFont(labelFont);
        lbl_soLuong.setFont(labelFont);
        lbl_loSX.setFont(labelFont);
        lbl_ngaySX.setFont(labelFont);
        lbl_ngayHH.setFont(labelFont);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Hàng 1: Tên SP
        addCell(lbl_tenSP, 0, 0, 1, 0.05);
        addCell(txt_tenSP, 1, 0, 5, 0.95);

        // Hàng 2: Giá SP - Số lượng - Lô SX
        addCell(lbl_giaSP, 0, 1, 1, 0.05);
        addCell(txt_giaSP, 1, 1, 1, 0.3);
        addCell(lbl_soLuong, 2, 1, 1, 0.05);
        addCell(txt_soLuong, 3, 1, 1, 0.3);
        addCell(lbl_loSX, 4, 1, 1, 0.05);
        addCell(txt_loSX, 5, 1, 1, 0.3);

        // Hàng 3: Ngày SX - Ngày HH
        addCell(lbl_ngaySX, 0, 2, 1, 0.05);
        addCell(txt_ngaySX, 1, 2, 2, 0.95);
        addCell(lbl_ngayHH, 3, 2, 1, 0.05);
        addCell(txt_ngayHH, 4, 2, 2, 0.95);

        add(containerQR, BorderLayout.CENTER);
        add(scanQR, BorderLayout.EAST);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }

    private void addCell(Component component, int x, int y, int width, double weightx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.weightx = weightx;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        containerQR.add(component, gbc);
    }



	
	public static void insertProductInformation(int id) {
		SanPhamDTO sp = SanPhamBLL.getProductById(id);
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
