package GUI.FormEmployee;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import DTO.NhanVienDTO;
import DTO.SanPhamDTO;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.JRadioButton;

import java.awt.Color;

import javax.swing.border.EmptyBorder;

import java.awt.Button;

import javax.swing.JPasswordField;

import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import BLL.NhanVienBLL;
import BLL.TaiKhoanBLL;
import DTO.TaiKhoanDTO;

import javax.swing.JScrollPane;

import DAL.NhanVienDAL;

import javax.swing.SwingConstants;

import java.io.*;
import java.nio.file.*;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import BLL.LoaiSanPhamBLL;
import BLL.SanPhamBLL;
import DTO.LoaiSanPhamDTO;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import BLL.LoaiSanPhamBLL;
import BLL.SanPhamBLL;
import DTO.LoaiSanPhamDTO;
import DTO.SanPhamDTO;

public class ChiTietSP_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField text_MaSP;
	private JTextField text_MaLoai;
	private JTextField text_TenSP;
	private JTextField text_Gia;
	JLabel lbl_anh = new JLabel();
	String imgPath = "";
	String imgFolder = "src/main/resources/images/ImageProduct/";









	private  SanPhamDTO SANPHAM  = new   SanPhamDTO(1, 1, 1, "", 1.1,  "BÁNH /", "",  "ACTIVE",  1) ;
	private LoaiSanPhamDTO loaiSP ;
	private JTextField text_SoLuong;
	private JTextField text_LoaiSP;
	private JTextField text_TrangThai;
	JPanel panel_thongTinContainer = new JPanel();

	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChiTietSP_Dialog dialog = new ChiTietSP_Dialog(null, 1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChiTietSP_Dialog(JFrame parent, int maSP) {
		super(parent, "Chi tiết sản phẩm", true); 
	
		setMaximumSize(new Dimension(83647, 2147483647));
		getContentPane().setMaximumSize(new Dimension(3647, 2147483647));
		setSize(800, 400);
		setResizable(false);
		setLocationRelativeTo(null);


		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_thongTinContainer.setMaximumSize(new Dimension(2767, 32767));
		getContentPane().add(panel_thongTinContainer, BorderLayout.CENTER);


		SANPHAM = SanPhamBLL.getProductById(maSP);
		LoaiSanPhamBLL loaiBLL = new LoaiSanPhamBLL();
		loaiSP  = loaiBLL.getLoaiSanPham(SANPHAM.getMaLSP());

		// setBorder(new EmptyBorder(20, 20, 20, 20));
		setBackground(new Color(214, 235, 241));
		getContentPane().setLayout(new BorderLayout());
		ImageIcon imageIcon = new ImageIcon(imgFolder  + SANPHAM.getTenAnh());
		// ImageIcon imageIcon = new ImageIcon(imgFolder  + "product-default.jpg");
		Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); 
		
		panel_thongTinContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_thongTinContainer.setBackground(new Color(204, 255, 255));
		getContentPane().add(panel_thongTinContainer);
		panel_thongTinContainer.setLayout(new GridLayout(1, 0, 10, 0));
		
		JPanel panel_thongTin1 = new JPanel();
		panel_thongTin1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_thongTinContainer.add(panel_thongTin1);
		panel_thongTin1.setLayout(new BoxLayout(panel_thongTin1, BoxLayout.Y_AXIS));
		
		JPanel panel_item1 = new JPanel();
		panel_item1.setMaximumSize(new Dimension(32767, 65));
		panel_item1.setMinimumSize(new Dimension(10, 65));
		panel_item1.setPreferredSize(new Dimension(10, 65));
		panel_thongTin1.add(panel_item1);
		panel_item1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lbl_MaSP = new JLabel("Mã sản phẩm");
		lbl_MaSP.setPreferredSize(new Dimension(65, 25));
		lbl_MaSP.setFont(new Font("Arial", Font.BOLD, 14));
		panel_item1.add(lbl_MaSP);
		
		text_MaSP = new JTextField();
		text_MaSP.setFocusable(false);
		text_MaSP.setBackground(new Color(255, 255, 255));
		text_MaSP.setFont(new Font("Arial", Font.BOLD, 14));
		text_MaSP.setText(SANPHAM.getMaSP() + "");
		panel_item1.add(text_MaSP);
		text_MaSP.setColumns(10);
		
		JPanel panel_item2 = new JPanel();
		panel_item2.setMinimumSize(new Dimension(10, 65));
		panel_item2.setPreferredSize(new Dimension(10, 65));
		panel_item2.setMaximumSize(new Dimension(32767, 65));
		panel_thongTin1.add(panel_item2);
		panel_item2.setLayout(new BoxLayout(panel_item2, BoxLayout.Y_AXIS));
		
		JLabel lbl_MaLoaiSP = new JLabel("Mã loại sản phẩm");
		lbl_MaLoaiSP.setMinimumSize(new Dimension(330, 13));
		lbl_MaLoaiSP.setMaximumSize(new Dimension(3330, 13));
		lbl_MaLoaiSP.setFont(new Font("Arial", Font.BOLD, 14));
		lbl_MaLoaiSP.setPreferredSize(new Dimension(433, 30));
		panel_item2.add(lbl_MaLoaiSP);
		
		text_MaLoai = new JTextField();
		text_MaLoai.setFocusable(false);
		text_MaLoai.setBackground(Color.white);
		text_MaLoai.setDisabledTextColor(Color.WHITE);
		text_MaLoai.setText(SANPHAM.getMaLSP() + "");
		text_MaLoai.setFont(new Font("Arial", Font.BOLD, 14));
		panel_item2.add(text_MaLoai);
		text_MaLoai.setColumns(10);
		
		JPanel panel_trangThai = new JPanel();
		panel_trangThai.setMaximumSize(new Dimension(32767, 65));
		panel_thongTin1.add(panel_trangThai);
		panel_trangThai.setLayout(new BoxLayout(panel_trangThai, BoxLayout.Y_AXIS));
		
		JLabel lbl_TrangThai = new JLabel("Trạng thái");
		lbl_TrangThai.setMaximumSize(new Dimension(2246, 13));
		lbl_TrangThai.setMinimumSize(new Dimension(2246, 13));
		lbl_TrangThai.setPreferredSize(new Dimension(2246, 30));
		lbl_TrangThai.setFont(new Font("Arial", Font.BOLD, 14));
		panel_trangThai.add(lbl_TrangThai);
		
		text_TrangThai = new JTextField();
		text_TrangThai.setFont(new Font("Arial", Font.BOLD, 14));
		panel_trangThai.add(text_TrangThai);
		text_TrangThai.setText(SANPHAM.getTrangThai());
		text_TrangThai.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_6.setMaximumSize(new Dimension(32767, 65));
		panel_thongTin1.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JLabel lblSoLuong = new JLabel("Số lượng tồn");
		lblSoLuong.setFont(new Font("Arial", Font.BOLD, 14));
		lblSoLuong.setMinimumSize(new Dimension(2260, 13));
		lblSoLuong.setMaximumSize(new Dimension(2260, 30));
		lblSoLuong.setPreferredSize(new Dimension(2260, 30));
		panel_6.add(lblSoLuong);
		
		text_SoLuong = new JTextField();
		text_SoLuong.setFont(new Font("Arial", Font.BOLD, 14));
		panel_6.add(text_SoLuong);
		text_SoLuong.setText(SANPHAM.getSoLuongTon() + "");
		text_SoLuong.setColumns(10);
		
		JPanel panel_ThongTin2 = new JPanel();
		panel_thongTinContainer.add(panel_ThongTin2);
		
		panel_ThongTin2.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_ThongTin2.setLayout(new BoxLayout(panel_ThongTin2, BoxLayout.Y_AXIS));
		
		JPanel panel_SDT = new JPanel();
		panel_ThongTin2.add(panel_SDT);
		panel_SDT.setMaximumSize(new Dimension(32767, 65));
		panel_SDT.setLayout(new BoxLayout(panel_SDT, BoxLayout.Y_AXIS));
		
		JLabel lbl_TenSP = new JLabel("Tên sản phẩm");
		lbl_TenSP.setPreferredSize(new Dimension(59, 30));
		lbl_TenSP.setMinimumSize(new Dimension(2259, 13));
		lbl_TenSP.setFont(new Font("Arial", Font.BOLD, 14));
		panel_SDT.add(lbl_TenSP);
		
		text_TenSP = new JTextField();
		text_TenSP.setFocusable(false);
		text_TenSP.setFont(new Font("Arial", Font.BOLD, 14));
		text_TenSP.setBackground(Color.WHITE);
		panel_SDT.add(text_TenSP);
		text_TenSP.setText(SANPHAM.getTenSP());
		text_TenSP.setColumns(10);
		
		JPanel panel_Email = new JPanel();
		panel_ThongTin2.add(panel_Email);
		panel_Email.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		panel_Email.setMaximumSize(new Dimension(32767, 65));
		panel_Email.setLayout(new BoxLayout(panel_Email, BoxLayout.Y_AXIS));
		
		JLabel lblTenLoaiSP = new JLabel("Tên loại sản phẩm");
		lblTenLoaiSP.setFont(new Font("Arial", Font.BOLD, 14));
		lblTenLoaiSP.setMinimumSize(new Dimension(2222, 13));
		lblTenLoaiSP.setPreferredSize(new Dimension(223, 30));
		panel_Email.add(lblTenLoaiSP);
		
		text_LoaiSP = new JTextField();
		text_LoaiSP.setFont(new Font("Arial", Font.BOLD, 14));
		text_LoaiSP.setText(loaiSP.getTenLoaiSP());
		panel_Email.add(text_LoaiSP);
		text_LoaiSP.setColumns(10);
		
		JPanel panel_Luong = new JPanel();
		panel_ThongTin2.add(panel_Luong);
		panel_Luong.setMaximumSize(new Dimension(32767, 65));
		panel_Luong.setLayout(new BoxLayout(panel_Luong, BoxLayout.Y_AXIS));
		
		JLabel lbl_Gia = new JLabel("Giá sản phẩm");
		lbl_Gia.setMinimumSize(new Dimension(2230, 13));
		lbl_Gia.setFont(new Font("Arial", Font.BOLD, 14));
		lbl_Gia.setMaximumSize(new Dimension(2230, 30));
		lbl_Gia.setPreferredSize(new Dimension(3330, 30));
		panel_Luong.add(lbl_Gia);
		
		Panel panel_4 = new Panel();
		panel_Luong.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		text_Gia = new JTextField();
		text_Gia.setFocusable(false);
		panel_4.add(text_Gia);
		text_Gia.setText(SANPHAM.getGia() + "");
		text_Gia.setFont(new Font("Arial", Font.BOLD, 14));
		text_Gia.setBackground(Color.WHITE);
		text_Gia.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("VND\r\n");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		panel_4.add(lblNewLabel_3);
		
		JPanel panel_7 = new JPanel();
		panel_ThongTin2.add(panel_7);
		panel_7.setMaximumSize(new Dimension(32767, 100));
		panel_7.setPreferredSize(new Dimension(10, 30));
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		JLabel lbl_moTa = new JLabel("Mô tả sản phẩm");
		lbl_moTa.setMinimumSize(new Dimension(2271, 13));
		lbl_moTa.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_moTa.setMaximumSize(new Dimension(2271, 44));
		lbl_moTa.setPreferredSize(new Dimension(71, 30));
		lbl_moTa.setFont(new Font("Arial", Font.BOLD, 14));
		panel_7.add(lbl_moTa);
		
		JTextArea txtrSnPhm = new JTextArea();
		txtrSnPhm.setText(SANPHAM.getMoTa());
		txtrSnPhm.setFont(new Font("Arial", Font.PLAIN, 13));
		panel_7.add(txtrSnPhm);

		
		JPanel panel_5 = new JPanel();
		panel_thongTinContainer.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
				JPanel panel_Anh = new JPanel();
				panel_Anh.setBorder(new EmptyBorder(20, 0, 0, 0));
				panel_Anh.setMinimumSize(new Dimension(200, 200));
				panel_5.add(panel_Anh);
				panel_Anh.setPreferredSize(new Dimension(200, 200));
				panel_Anh.setMaximumSize(new Dimension(2200, 200));
				panel_Anh.setLayout(new BorderLayout(0, 0));
				lbl_anh.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_anh.setIcon(new ImageIcon(image));
				panel_Anh.add(lbl_anh, BorderLayout.CENTER);


		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Thoát");
				cancelButton.setActionCommand("Thoát");
				buttonPane.add(cancelButton);
				
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(10, 50));
				panel.setBackground(new Color(0, 51, 102));
				getContentPane().add(panel, BorderLayout.NORTH);
				panel.setLayout(new BorderLayout(0, 0));
				
				JLabel lblNewLabel = new JLabel("Thông tin sản phẩm");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
				lblNewLabel.setForeground(new Color(255, 255, 255));
				panel.add(lblNewLabel);

				cancelButton.addActionListener(e->{
					dispose();
				});
			}
		}
	}

}
