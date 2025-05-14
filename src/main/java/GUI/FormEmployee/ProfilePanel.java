package GUI.FormEmployee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import BLL.NhanVienBLL;
import BLL.TaiKhoanBLL;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.ComponentCommon.ButtonCustom;



public class ProfilePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField text_TenTK;
	private JTextField text_Ten;
	private JTextField text_DiaChi;
	private  JDateChooser dateChooserNgaySinh;
	private JTextField text_CCCD;
	private JTextField text_SDT;
	private JTextField textLuong;
	private JPasswordField passwordField;
	private ButtonCustom btn_DoiTenOrMatKhau;
	JRadioButton rdbtnNu = new JRadioButton("Nữ");
	JRadioButton rdbtnNam = new JRadioButton("Nam");
	ButtonCustom btnSuaThongTin = new ButtonCustom("Sửa thông tin",12,"orange");
	ButtonCustom btnLuuThongTin = new ButtonCustom("Lưu thông tin",12,"green");
	ButtonCustom btnHuyLuu = new ButtonCustom("Huỷ Lưu",12,"red");
	JPanel panel_GioiTinh2 = new JPanel();
	JLabel lbl_anh = new JLabel();
	ButtonCustom btnDoiAnh = new ButtonCustom("Đổi ảnh",12,"black");
	ButtonCustom btnLuuAnh = new ButtonCustom("Lưu ảnh",12,"blue");
	String imgPath = "";
	JPanel panel_Anh = new JPanel();
	String imgFolder = "src/main/resources/images/avtEmployee/";









	private static NhanVienDTO NHANVIEN = new NhanVienDTO();
	private static TaiKhoanDTO TAIKHOAN  = new TaiKhoanDTO(1, "TRINHKHANG", "12345", "98654398765", "5678", "email", "76", "");
	private JTextField text_Email;
	private JTextField text_Quyen;

	public static int getMaNhanVien(){
		return TAIKHOAN.getMaNV();
	}
	

	/**
	 * Create the panel.
	 */
	public ProfilePanel(int maNV) {
		NhanVienBLL nvBLL = new NhanVienBLL();
		NHANVIEN = nvBLL.getNhanVienByMa(maNV + "");
		TaiKhoanBLL tkBLL = new TaiKhoanBLL();
		TAIKHOAN = tkBLL.getTaiKhoanById(maNV);


		setBorder(new EmptyBorder(20, 20, 20, 20));
		setBackground(new Color(214, 235, 241));
		setLayout(new BorderLayout(20, 20));
		
		JPanel panel_AnhContainer = new JPanel();
		panel_AnhContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(panel_AnhContainer, BorderLayout.NORTH);
		panel_AnhContainer.setLayout(new BoxLayout(panel_AnhContainer, BoxLayout.X_AXIS));
		
		JPanel panel_TaiKhoan = new JPanel();
		panel_TaiKhoan.setMaximumSize(new Dimension(32767, 200));
		panel_AnhContainer.add(panel_TaiKhoan);
		panel_TaiKhoan.setLayout(new BoxLayout(panel_TaiKhoan, BoxLayout.Y_AXIS));
		
		JPanel panel_tenTK = new JPanel();
		panel_tenTK.setMaximumSize(new Dimension(32767, 65));
		panel_tenTK.setPreferredSize(new Dimension(10, 65));
		panel_TaiKhoan.add(panel_tenTK);
		panel_tenTK.setLayout(new BoxLayout(panel_tenTK, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setPreferredSize(new Dimension(74, 30));
		panel_tenTK.add(lblNewLabel_1);
		
		text_TenTK = new JTextField();
		text_TenTK.setFocusable(false);
		text_TenTK.setBackground(new Color(255, 255, 255));
		text_TenTK.setEditable(false);
		text_TenTK.setFont(new Font("Arial", Font.BOLD, 14));
		panel_tenTK.add(text_TenTK);
		text_TenTK.setText(TAIKHOAN.getTenTK());
		text_TenTK.setColumns(10);
		
		JPanel panel_MatKhau = new JPanel();
		panel_MatKhau.setPreferredSize(new Dimension(10, 65));
		panel_MatKhau.setMaximumSize(new Dimension(32767, 65));
		panel_TaiKhoan.add(panel_MatKhau);
		panel_MatKhau.setLayout(new BoxLayout(panel_MatKhau, BoxLayout.Y_AXIS));
		
		JLabel lbl_matKhau = new JLabel("Mật khẩu");
		lbl_matKhau.setFont(new Font("Arial", Font.BOLD, 14));
		lbl_matKhau.setPreferredSize(new Dimension(42, 30));
		panel_MatKhau.add(lbl_matKhau);
		
		passwordField = new JPasswordField();
		passwordField.setFocusable(false);
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setEditable(false);
		passwordField.setText(TAIKHOAN.getMatKhau());
		panel_MatKhau.add(passwordField);
		
		Panel panel = new Panel();
		panel.setMinimumSize(new Dimension(2210, 45));
		panel.setPreferredSize(new Dimension(10, 45));
		panel_TaiKhoan.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JLabel lblQuyen = new JLabel("Quyền");
		lblQuyen.setFont(new Font("Arial", Font.BOLD, 14));
		lblQuyen.setMinimumSize(new Dimension(1243, 13));
		lblQuyen.setPreferredSize(new Dimension(43, 30));
		panel_3.add(lblQuyen);
		
		text_Quyen = new JTextField();
		text_Quyen.setFont(new Font("Arial", Font.BOLD, 14));
		text_Quyen.setBackground(new Color(255, 255, 255));
		text_Quyen.setFocusable(false);
		panel_3.add(text_Quyen);
		text_Quyen.setColumns(10);
		panel.add(Box.createHorizontalStrut(10));
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(180, 10));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 10, 5));
		
		btn_DoiTenOrMatKhau = new ButtonCustom("Đổi Tên/Mật khẩu",12,"orange");
		btn_DoiTenOrMatKhau.setBackground(new Color(153, 204, 255));
		panel_1.add(btn_DoiTenOrMatKhau);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 10, 10));
		btnDoiAnh.setBackground(new Color(153, 204, 255));
		panel_5.add(btnDoiAnh);
		btnDoiAnh.setMinimumSize(new Dimension(100, 21));
		btnLuuAnh.setBackground(new Color(153, 204, 255));
		panel_5.add(btnLuuAnh);
		btnLuuAnh.setEnabled(false);
		
		
		
		panel_AnhContainer.add(Box.createHorizontalStrut(10));

		panel_Anh.setPreferredSize(new Dimension(200, 200));
		panel_Anh.setMaximumSize(new Dimension(200, 32767));
		panel_AnhContainer.add(panel_Anh);
		panel_Anh.setLayout(new BorderLayout(0, 0));
		lbl_anh.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon imageIcon = new ImageIcon(imgFolder  + NHANVIEN.getImage());
		Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); 
		lbl_anh.setIcon(new ImageIcon(image));
		panel_Anh.add(lbl_anh, BorderLayout.CENTER);
		
		JPanel panel_thongTinContainer = new JPanel();
		panel_thongTinContainer.setBackground(new Color(240, 240, 240));
		add(panel_thongTinContainer);
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
		
		JLabel lbl_TenNV = new JLabel("Tên nhân viên");
		lbl_TenNV.setPreferredSize(new Dimension(65, 25));
		lbl_TenNV.setFont(new Font("Arial", Font.BOLD, 14));
		panel_item1.add(lbl_TenNV);
		
		text_Ten = new JTextField();
		text_Ten.setFocusable(false);
		text_Ten.setBackground(new Color(255, 255, 255));
		text_Ten.setFont(new Font("Arial", Font.BOLD, 14));
		text_Ten.setText(NHANVIEN.getTenNV());
		panel_item1.add(text_Ten);
		text_Ten.setColumns(10);
		
		JPanel panel_item2 = new JPanel();
		panel_item2.setMinimumSize(new Dimension(10, 65));
		panel_item2.setPreferredSize(new Dimension(10, 65));
		panel_item2.setMaximumSize(new Dimension(32767, 65));
		panel_thongTin1.add(panel_item2);
		panel_item2.setLayout(new BoxLayout(panel_item2, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_2 = new JLabel("Địa chỉ\r\n");
		lblNewLabel_2.setMinimumSize(new Dimension(330, 13));
		lblNewLabel_2.setMaximumSize(new Dimension(3330, 13));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_2.setPreferredSize(new Dimension(433, 30));
		panel_item2.add(lblNewLabel_2);
		
		text_DiaChi = new JTextField();
		text_DiaChi.setFocusable(false);
		text_DiaChi.setBackground(Color.white);
		text_DiaChi.setDisabledTextColor(Color.WHITE);
		text_DiaChi.setText(NHANVIEN.getDiaChi());
		text_DiaChi.setFont(new Font("Arial", Font.BOLD, 14));
		panel_item2.add(text_DiaChi);
		text_DiaChi.setColumns(10);
		
		JPanel panel_SDT = new JPanel();
		panel_SDT.setMaximumSize(new Dimension(32767, 65));
		panel_thongTin1.add(panel_SDT);
		panel_SDT.setLayout(new BoxLayout(panel_SDT, BoxLayout.Y_AXIS));
		
		JLabel lbl_SDT = new JLabel("Số điện thoại");
		lbl_SDT.setPreferredSize(new Dimension(59, 30));
		lbl_SDT.setMinimumSize(new Dimension(2259, 13));
		lbl_SDT.setFont(new Font("Arial", Font.BOLD, 14));
		panel_SDT.add(lbl_SDT);
		
		text_SDT = new JTextField();
		text_SDT.setFocusable(false);
		text_SDT.setFont(new Font("Arial", Font.BOLD, 14));
		text_SDT.setBackground(Color.WHITE);
		panel_SDT.add(text_SDT);
		text_SDT.setColumns(10);
		
		JPanel panel_Email = new JPanel();
		panel_Email.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		panel_Email.setMaximumSize(new Dimension(32767, 65));
		panel_thongTin1.add(panel_Email);
		panel_Email.setLayout(new BoxLayout(panel_Email, BoxLayout.Y_AXIS));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
		lblEmail.setMinimumSize(new Dimension(2222, 13));
		lblEmail.setPreferredSize(new Dimension(223, 30));
		panel_Email.add(lblEmail);
		
		text_Email = new JTextField();
		text_Email.setFocusable(false);
		text_Email.setBackground(Color.WHITE);
		text_Email.setFont(new Font("Arial", Font.BOLD, 14));
		panel_Email.add(text_Email);
		text_Email.setColumns(10);
		
		JPanel panel_ThongTin2 = new JPanel();
		panel_thongTinContainer.add(panel_ThongTin2);
		
		panel_ThongTin2.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_ThongTin2.setLayout(new BoxLayout(panel_ThongTin2, BoxLayout.Y_AXIS));
		
		JPanel panel_GioiTinh = new JPanel();
		panel_GioiTinh.setMaximumSize(new Dimension(32767, 65));
		panel_GioiTinh.setPreferredSize(new Dimension(10, 65));
		panel_ThongTin2.add(panel_GioiTinh);
		panel_GioiTinh.setLayout(new BoxLayout(panel_GioiTinh, BoxLayout.Y_AXIS));
		
		JLabel lbl_gioiTinh = new JLabel("giới tính");
		lbl_gioiTinh.setMinimumSize(new Dimension(3336, 13));
		lbl_gioiTinh.setPreferredSize(new Dimension(236, 30));
		lbl_gioiTinh.setFont(new Font("Arial", Font.BOLD, 14));
		panel_GioiTinh.add(lbl_gioiTinh);
		
		panel_GioiTinh2.setBackground(new Color(255, 255, 255));
		panel_GioiTinh.add(panel_GioiTinh2);
		rdbtnNam.setEnabled(false);
		
		rdbtnNam.setFont(new Font("Arial", Font.BOLD, 14));
		panel_GioiTinh2.add(rdbtnNam);
		rdbtnNu.setEnabled(false);
		
		rdbtnNu.setFont(new Font("Arial", Font.BOLD, 14));
		panel_GioiTinh2.add(rdbtnNu);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnNam);
		btnGroup.add(rdbtnNu);
		JPanel panel_NgaySinh = new JPanel();
		panel_NgaySinh.setMaximumSize(new Dimension(32767, 65));
		panel_NgaySinh.setFont(new Font("Arial", Font.BOLD, 14));
		panel_ThongTin2.add(panel_NgaySinh);
		panel_NgaySinh.setLayout(new BoxLayout(panel_NgaySinh, BoxLayout.Y_AXIS));
		
		JLabel lbl_NgaySinh = new JLabel("Ngày sinh");
		lbl_NgaySinh.setMinimumSize(new Dimension(2345, 13));
		lbl_NgaySinh.setFont(new Font("Arial", Font.BOLD, 14));
		lbl_NgaySinh.setPreferredSize(new Dimension(343, 30));
		panel_NgaySinh.add(lbl_NgaySinh);
		dateChooserNgaySinh = new JDateChooser();
		dateChooserNgaySinh.setFont(new Font("Arial", Font.BOLD, 15));
		dateChooserNgaySinh.getCalendarButton().setBackground(new Color(255, 255, 255));
		dateChooserNgaySinh.setBackground(new Color(152, 251, 152));
		dateChooserNgaySinh.setEnabled(false);
		dateChooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(51, 17));
		dateChooserNgaySinh.setPreferredSize(new Dimension(273, 35));
		panel_NgaySinh.add(dateChooserNgaySinh);
		
		JPanel panel_CCCD = new JPanel();
		panel_CCCD.setMaximumSize(new Dimension(32767, 65));
		panel_ThongTin2.add(panel_CCCD);
		panel_CCCD.setLayout(new BoxLayout(panel_CCCD, BoxLayout.Y_AXIS));
		
		JLabel lbl_CCCD = new JLabel("Căn cước công dân");
		lbl_CCCD.setPreferredSize(new Dimension(90, 30));
		lbl_CCCD.setFont(new Font("Arial", Font.BOLD, 14));
		panel_CCCD.add(lbl_CCCD);
		
		text_CCCD = new JTextField();
		text_CCCD.setFocusable(false);
		text_CCCD.setBackground(Color.WHITE);
		text_CCCD.setText("051205002022");
		text_CCCD.setFont(new Font("Arial", Font.BOLD, 14));
		panel_CCCD.add(text_CCCD);
		text_CCCD.setColumns(10);
		
		JPanel panel_Luong = new JPanel();
		panel_Luong.setMaximumSize(new Dimension(32767, 65));
		panel_ThongTin2.add(panel_Luong);
		panel_Luong.setLayout(new BoxLayout(panel_Luong, BoxLayout.Y_AXIS));
		
		JLabel lblLuong = new JLabel("Lương");
		lblLuong.setMinimumSize(new Dimension(2230, 13));
		lblLuong.setFont(new Font("Arial", Font.BOLD, 14));
		lblLuong.setMaximumSize(new Dimension(2230, 30));
		lblLuong.setPreferredSize(new Dimension(3330, 30));
		panel_Luong.add(lblLuong);
		
		Panel panel_4 = new Panel();
		panel_Luong.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		textLuong = new JTextField();
		textLuong.setFocusable(false);
		panel_4.add(textLuong);
		textLuong.setFont(new Font("Arial", Font.BOLD, 14));
		textLuong.setBackground(Color.WHITE);
		textLuong.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("VND\r\n");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		panel_4.add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		panel_ThongTin2.add(panel_2);
		panel_2.setMaximumSize(new Dimension(32767, 65));
		btnSuaThongTin.setBackground(new Color(51, 153, 204));
		
		panel_2.add(btnSuaThongTin);
		btnLuuThongTin.setBackground(new Color(102, 153, 204));
		
		panel_2.add(btnLuuThongTin);
		btnHuyLuu.setBackground(new Color(102, 153, 204));
		
		panel_2.add(btnHuyLuu);

		btnLuuThongTin.setVisible(false);
		btnHuyLuu.setVisible(false);

		addEvent();
		loadThongTin();

	}


	public void addEvent(){
		btn_DoiTenOrMatKhau.addActionListener(e->{
			doiTenMatKhau();
		});
		btnSuaThongTin.addActionListener(e->{
			text_Ten.setFocusable(true);
			text_DiaChi.setFocusable(true);
			text_SDT.setFocusable(true);
			text_Email.setFocusable(true);
			text_CCCD.setFocusable(true);

			text_Ten.setBackground(new Color(250, 235, 215));
			text_DiaChi.setBackground(new Color(250, 235, 215));
			text_SDT.setBackground(new Color(250, 235, 215));
			text_Email.setBackground(new Color(250, 235, 215));
			text_CCCD.setBackground(new Color(250, 235, 215));
			panel_GioiTinh2.setBackground(new Color(250, 235, 215));

			rdbtnNam.setEnabled(true);
			rdbtnNu.setEnabled(true);
			dateChooserNgaySinh.setEnabled(true);

			
			btnSuaThongTin.setVisible(false);
			btnHuyLuu.setVisible(true);
			btnLuuThongTin.setVisible(true);
		});
		btnLuuThongTin.addActionListener(e->{
			text_Ten.setFocusable(false);
			text_DiaChi.setFocusable(false);
			text_SDT.setFocusable(false);
			text_Email.setFocusable(false);
			text_CCCD.setFocusable(false);

			dateChooserNgaySinh.setEnabled(false);
			
			thayDoiThongTinCaNhan();
			text_Ten.setBackground(Color.white);
			text_DiaChi.setBackground(Color.white);
			text_SDT.setBackground(Color.white);
			text_Email.setBackground(Color.white);
			text_CCCD.setBackground(Color.white);
			panel_GioiTinh2.setBackground(Color.white);

			rdbtnNam.setEnabled(false);
			rdbtnNu.setEnabled(false);

			btnHuyLuu.setVisible(false);
			btnLuuThongTin.setVisible(false);
			btnSuaThongTin.setVisible(true);

		});
		btnHuyLuu.addActionListener(e->{
			int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn HUỶ không ???", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(confirm == JOptionPane.YES_OPTION){
				text_Ten.setFocusable(false);
				text_DiaChi.setFocusable(false);
				text_SDT.setFocusable(false);
				text_Email.setFocusable(false);
				text_CCCD.setFocusable(false);

				
				text_Ten.setBackground(Color.white);
				text_DiaChi.setBackground(Color.white);
				text_SDT.setBackground(Color.white);
				text_Email.setBackground(Color.white);
				text_CCCD.setBackground(Color.white);

				panel_GioiTinh2.setBackground(Color.white);

				rdbtnNam.setEnabled(false);
				rdbtnNu.setEnabled(false);
				dateChooserNgaySinh.setEnabled(false);

				btnHuyLuu.setVisible(false);
				btnLuuThongTin.setVisible(false);
				btnSuaThongTin.setVisible(true);
			}
			loadThongTin();
		});
		btnDoiAnh.addActionListener(e->{
			imgPath = chonAnhTuLocal();
		});
		btnLuuAnh.addActionListener(e->{
			luuAnhVaoProject();
		});


	}


	public void doiTenMatKhau() {
		JPasswordField passwordField = new JPasswordField();
		int option = JOptionPane.showConfirmDialog(null, passwordField, "Nhập mật khẩu", JOptionPane.OK_CANCEL_OPTION);
	
		if (option == JOptionPane.OK_OPTION) {
			String password = new String(passwordField.getPassword());
			if (TAIKHOAN.getMatKhau().equals(password)) {
				// Tạo dialog
				JFrame parentFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);

				JDialog dialog = new JDialog( parentFrame, "Đổi tài khoản");
				dialog.setSize(300, 250);
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setLayout(new BorderLayout());
				

				JLabel lblTen = new JLabel("Tên đăng nhập:");
				JTextField txtTen = new JTextField();
	
				JLabel lblMatKhauMoi = new JLabel("Mật khẩu mới:");
				JPasswordField txtMatKhauMoi = new JPasswordField();
	
				JLabel lblXacNhan = new JLabel("Xác nhận mật khẩu:");
				JPasswordField txtXacNhan = new JPasswordField();
	
				JButton btnLuu = new JButton("Lưu");
				JButton btnHuy = new JButton("Huỷ");
				JPanel content = new JPanel(new GridLayout(5, 2, 5, 5));
				content.setBorder(BorderFactory.createEmptyBorder(10, 10  , 10, 10));
				content.add(lblTen);
				content.add(txtTen);
				content.add(lblMatKhauMoi);
				content.add(txtMatKhauMoi);
				content.add(lblXacNhan);
				content.add(txtXacNhan);
				content.add(new JLabel()); 
				content.add(new JLabel()); 
				content.add(btnLuu);
				content.add(btnHuy);
				dialog.setContentPane(content);
	
				// Sự kiện nút Lưu
				btnLuu.addActionListener(e -> {
					String ten = txtTen.getText().trim();
					String mkMoi = new String(txtMatKhauMoi.getPassword());
					String xacNhan = new String(txtXacNhan.getPassword());
	
					if (ten.isEmpty() || mkMoi.isEmpty() || xacNhan.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
						return;
					}
	
					if (!mkMoi.equals(xacNhan)) {
						JOptionPane.showMessageDialog(null, "Mật khẩu xác nhận không khớp.");
						return;
					}
	
					TAIKHOAN.setTenTK(ten);
					TAIKHOAN.setMatKhau(mkMoi);
					TaiKhoanBLL tkBLL = new TaiKhoanBLL();
					tkBLL.updateTaiKhoan(TAIKHOAN);
					loadThongTin();
					JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
					dialog.dispose();
				});
	
				// Sự kiện nút Huỷ
				btnHuy.addActionListener(e -> dialog.dispose());
	
				dialog.setVisible(true);
	
			} else {
				JOptionPane.showMessageDialog(passwordField, "Mật khẩu không đúng. Vui lòng thử lại.");
			}
		}
	}


	public void loadThongTin(){
		NhanVienBLL nvBLL = new NhanVienBLL();
		NHANVIEN = nvBLL.getNhanVienByMa(NHANVIEN.getMaNV() + "");
		TaiKhoanBLL tkBLL = new TaiKhoanBLL();
		TAIKHOAN = tkBLL.getTaiKhoanById(TAIKHOAN.getMaNV());

		text_TenTK.setText(TAIKHOAN.getTenTK());
		passwordField.setText(TAIKHOAN.getMatKhau());
		text_Ten.setText(NHANVIEN.getTenNV());
		text_DiaChi.setText(NHANVIEN.getDiaChi());
		text_SDT.setText(NHANVIEN.getSDT());
		text_Email.setText(TAIKHOAN.getGmail());
		text_Quyen.setText(TAIKHOAN.getQuyen());
		ImageIcon imageIcon = new ImageIcon(imgFolder  + NHANVIEN.getImage());
		Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); 
		lbl_anh.setIcon(new ImageIcon(image));
		if(NHANVIEN.getGioiTinh().equalsIgnoreCase("nam")){
			rdbtnNam.setSelected(true);
		}else{
			rdbtnNu.setSelected(true);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(NHANVIEN.getNgaySinh().toString());
			dateChooserNgaySinh.setDate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		text_CCCD.setText(NHANVIEN.getCCCD());

		DecimalFormat df = new DecimalFormat("#,###");  
		textLuong.setText(df.format(NHANVIEN.getLuong()) + "");	}

	public void thayDoiThongTinCaNhan(){
		

		
		if(text_Ten.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Tên nhân viên không được để trống!!!");
			return;
		}
		if(text_DiaChi.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống!!!");
			return;
		}
		if(text_SDT.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống!!!");
			return;
		}
		
		if(!text_SDT.getText().matches("\\d+")){
			JOptionPane.showMessageDialog(null, "Số điện thoại chỉ gồm các kí tự số!!!");
			return;	
		}
		if(text_SDT.getText().length() != 10){
			JOptionPane.showMessageDialog(null, "Số điện thoại chỉ gồm 10 kí tự số!!!");
			return;	
		}
		
		String email = text_Email.getText().trim();

		if (email.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Email không được để trống!!!");
			return;
		}

		// Regex kiểm tra email hợp lệ
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

		if (!email.matches(emailRegex)) {
			JOptionPane.showMessageDialog(null, "Email không hợp lệ!!!");
			return;
		}

		if(text_CCCD.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "CCCD không được để trống!!!");
			return;
		}
		if(!text_CCCD.getText().matches("\\d+")){
			JOptionPane.showMessageDialog(null, "Số CCCD chỉ gồm các kí tự số!!!");
			return;	
		}
		if(dateChooserNgaySinh.getDate() == null){
			JOptionPane.showMessageDialog(null, "Ngày sinh không được để trống!!!");
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn lưu không ???", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(confirm == JOptionPane.YES_OPTION){
			NHANVIEN.setCCCD(text_CCCD.getText());
			NHANVIEN.setDiaChi(text_DiaChi.getText());
			NHANVIEN.setGioiTinh(rdbtnNam.isSelected()? "Nam" : "Nữ");
			NHANVIEN.setNgaySinh(dateChooserNgaySinh.getDate());
			NHANVIEN.setSDT(text_SDT.getText());
			NHANVIEN.setTenNV(text_Ten.getText());
			TAIKHOAN.setGmail(text_Email.getText());
			
			NhanVienBLL nvBLL = new NhanVienBLL();
			nvBLL.updateNhanVien(NHANVIEN);
			TaiKhoanBLL tkBLL = new TaiKhoanBLL();
			tkBLL.updateTaiKhoan(TAIKHOAN);
			loadThongTin();

		}

	}


	public  String chonAnhTuLocal() {
		
try {
            // Đổi Look & Feel sang hệ điều hành
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn ảnh");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Ảnh", "jpg", "jpeg", "png", "gif"));
try {
		    // Trả về Look & Feel mặc định của Java (thường là Metal)
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    // Cập nhật lại UI trên frame (nếu cần)
		    SwingUtilities.updateComponentTreeUI(this); // yourFrame là JFrame hoặc JDialog
		} catch (Exception e) {
		    e.printStackTrace();
		}

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();		
			ImageIcon imageIcon = new ImageIcon(path);
			Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); 
			lbl_anh.setIcon(new ImageIcon(image));
		
			btnDoiAnh.setEnabled(false);
			btnLuuAnh.setEnabled(true);
            return path;

        }
        return null;
    }



	public void luuAnhVaoProject() {

		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thay đổi ảnh không ???", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(confirm == JOptionPane.YES_OPTION){
			try {
		
				File sourceFile = new File(imgPath);
				String fileName = sourceFile.getName(); 
				File destFile = new File(imgFolder + fileName);
		
				// Tạo thư mục nếu chưa tồn tại
				new File(imgFolder).mkdirs();
				Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
				System.out.println("Đã lưu ảnh vào: " + destFile.getAbsolutePath());
				JOptionPane.showMessageDialog(null, "Lưu ảnh thành công");
				NHANVIEN.setImage(fileName);
				NhanVienBLL nvBLL = new NhanVienBLL();
				nvBLL.updateNhanVien(NHANVIEN);
				btnDoiAnh.setEnabled(true);
				btnLuuAnh.setEnabled(false);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Lỗi khi lưu ảnh vào project.");
			}

		}
		if(confirm == JOptionPane.NO_OPTION){
			ImageIcon imageIcon = new ImageIcon(imgFolder  + NHANVIEN.getImage());
			Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); 
			lbl_anh.setIcon(new ImageIcon(image));
			panel_Anh.add(lbl_anh, BorderLayout.CENTER);
			btnDoiAnh.setEnabled(true);
			btnLuuAnh.setEnabled(false);

		}
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Danh sách sản phẩm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 500);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(new ProfilePanel(1), BorderLayout.CENTER);
		frame.setVisible(true);
	}



}
