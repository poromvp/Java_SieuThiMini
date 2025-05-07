package GUI.FormEmployee;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.opencv_core.Mat;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;



import BLL.ChiTietDonHangBLL;
import BLL.ChiTietKhuyenMaiBLL;
import BLL.DiemTichLuyBLL;
import BLL.DonHangBLL;
import BLL.KhuyenMaiBLL;
import BLL.LoaiSanPhamBLL;
import BLL.NhanVienBLL;
import BLL.SanPhamBLL;
import BLL.TheThanhVienBLL;
import DTO.ChiTietDonHangDTO;
import DTO.DiemTichLuyDTO;
import DTO.DonHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.LoaiSanPhamDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import DTO.TheThanhVienDTO;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;


import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import BLL.ChiTietKhuyenMaiBLL;
import BLL.SanPhamBLL;
import DTO.SanPhamDTO;
import GUI.ComponentCommon.RoundedComponent;
import GUI.ComponentCommon.StyledTextField;
import GUI.QR.ScanQR;
import PDF.ChiTietDH_Dialog;



public class OrderPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldTongTien;
	private JTextField text_ThanhTien;
	private JTextField textKhuyenMai;
	private JTextField textTheThanhVien;
	private JTextField textSDT;
	private JTextField textTenKH;
	private JTextField textDiem;
	private JTextField textMuaDTL;
	private JRadioButton rdbtnKhongThe = new JRadioButton("Không thẻ\r\n");
	private JRadioButton rdbtnCoThe = new JRadioButton("Có thẻ\r\n");
	JRadioButton rdbbtnKhongMuaDTL = new JRadioButton("Không");
	JRadioButton rdbtnMuaDTL = new JRadioButton("Có\r\n");

	JComboBox comboBoxPTTT = new JComboBox(new String[]{ "Tiền mặt", "Chuyển khoản"});
	JComboBox comboBoxDTL = new JComboBox();
	JSpinner spinner_tienKD = new JSpinner();

	JPanel panel_ButtonLuuInDH = new JPanel();


	

	JPanel panel_SDT = new JPanel();
	JPanel panel_TenKH = new JPanel();
	JPanel panel_DTL = new JPanel();
	JPanel panel_MuaDTL = new JPanel();
	JPanel panel_ChonDTL = new JPanel();
	JPanel panel_QRbank = new JPanel();
	JPanel panel_TienKD = new JPanel();


	JButton btnLuuDonHang = new JButton("Lưu đơn hàng\r\n");
	JButton btnInDH = new JButton("In hoá đơn");


	private static int JUST_MADONHANG = -1;



	private String[] header = {"Mã", "Tên", "Giá", "Khuyến mãi"};
	private DefaultTableModel	model_timKiem = new DefaultTableModel( header, 0);
	private JTable	tableTimKiem = new JTable(model_timKiem);
	private JScrollPane scrollPaneTimKiem = new JScrollPane(tableTimKiem);
	private JComboBox<String> combobox_LoaiSP = new JComboBox<>(new String[]{"Tất cả"});

	private JButton toggleButton = new JButton("Bật Scan");

    private static final String[] HEADER = {"Mã SP", "Tên SP", "Giá", "Giảm giá", "Số lượng", "thành tiền"};
    private static DefaultTableModel tableModel_SP = new DefaultTableModel(HEADER, 0);
	private static JTable tableProduct = new JTable(tableModel_SP);
    private static JScrollPane scrollPane_SP = new JScrollPane(tableProduct);
    private static JPopupMenu popupMenu;
    private Color bgColor = new Color(17, 32, 51);
	private static final double[] WIDTH_COL = {0.1, 0.4, 0.2, 0.1, 0.1, 0.1};
	private JTextField textTenOrID;
	private JSpinner spinner_GiaMin;
	private JSpinner spinner_GiaMax;
	JButton btnTaoMoi = new JButton("Tạo mới ĐH");



	private static JLabel label_qr;
    private static JLabel lbl_id = new JLabel();
    private static OpenCVFrameGrabber grabber;
    private static boolean isScanning = false; 
    private static Thread scanThread;

	private NhanVienDTO  NHANVIEN = new NhanVienDTO(
            1,
            "Nguyễn Văn A",
            null,
            "Nam",
            "123 Đường ABC, Quận 1",
            "0123456789",
            "123456789012",
            8000000.0,
            1
        ); 

	private TheThanhVienDTO khachHang = null;

	/**
	 * Create the panel.
	 */
	public OrderPanel() {

		NhanVienBLL nvBLL = new NhanVienBLL();
		NHANVIEN = nvBLL.getNhanVienByMa(ProfilePanel.getMaNhanVien() + "");

		khachHang = null;
		setBackground(new Color(207, 235, 243));
		setBorder(new EmptyBorder(15, 15, 15, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{874, 0};
		// gridBagLayout.columnWidths = new int[]{478, 320};
		// gridBagLayout.rowHeights = new int[]{234, 437};
		gridBagLayout.columnWeights = new double[]{1.0, 0.05};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0};

		// gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		// gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.columnWeights = new double[]{1, 0.05};
		gridBagLayout.rowWeights = new double[]{0.5, 1.0};

		setLayout(gridBagLayout);
		
		JPanel panelTimKiem = new JPanel();
		panelTimKiem.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints gbc_panelTimKiem = new GridBagConstraints();
		gbc_panelTimKiem.fill = GridBagConstraints.BOTH;
		gbc_panelTimKiem.insets = new Insets(0, 0, 10, 5);
		gbc_panelTimKiem.gridx = 0;
		gbc_panelTimKiem.gridy = 0;
		add(panelTimKiem, gbc_panelTimKiem);
		panelTimKiem.setLayout(new BoxLayout(panelTimKiem, BoxLayout.X_AXIS));
		
		JPanel panel_ThongTinSPqr = new JPanel();
		panelTimKiem.add(panel_ThongTinSPqr);
		panel_ThongTinSPqr.setLayout(new BoxLayout(panel_ThongTinSPqr, BoxLayout.X_AXIS));
		
		JPanel panel_TimKiem = new JPanel();
		panel_ThongTinSPqr.add(panel_TimKiem);
		panel_TimKiem.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_ThuocTinhTimKiem = new JPanel();
		panel_TimKiem.add(panel_ThuocTinhTimKiem, BorderLayout.NORTH);
		panel_ThuocTinhTimKiem.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(0, 10));
		panel_4.setMinimumSize(new Dimension(0, 10));
		panel_ThuocTinhTimKiem.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JLabel lbL_IdOrTenSP = new JLabel("ID / Tên sản phẩm");
		lbL_IdOrTenSP.setFont(new Font("Arial", Font.BOLD, 14));
		panel_4.add(lbL_IdOrTenSP);
		
		textTenOrID = new JTextField();
		textTenOrID.setPreferredSize(new Dimension(7, 25));
		panel_4.add(textTenOrID);
		textTenOrID.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(0, 10));
		panel_5.setMinimumSize(new Dimension(0, 10));
		panel_ThuocTinhTimKiem.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Loại sản phẩm");
		lblNewLabel.setMinimumSize(new Dimension(2264, 13));
		lblNewLabel.setMaximumSize(new Dimension(2283, 13));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		panel_5.add(lblNewLabel);
		
		LoaiSanPhamBLL loaiBLL = new LoaiSanPhamBLL();
		ArrayList<LoaiSanPhamDTO> dsLoaiSP = loaiBLL.getList();
		for(LoaiSanPhamDTO loai : dsLoaiSP){
			combobox_LoaiSP.addItem(loai.getTenLoaiSP());
		}
		panel_5.add(combobox_LoaiSP);
		
		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(0, 10));
		panel_6.setMinimumSize(new Dimension(0, 10));
		panel_ThuocTinhTimKiem.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("Giá MIN\r\n");
		lblNewLabel_1.setMinimumSize(new Dimension(2236, 13));
		lblNewLabel_1.setMaximumSize(new Dimension(2236, 13));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		panel_6.add(lblNewLabel_1);
		
		spinner_GiaMin = new JSpinner();
		spinner_GiaMin.setModel(new SpinnerNumberModel(10000, 1000, 1000000, 1000));
		panel_6.add(spinner_GiaMin);
		
		JPanel panel_7 = new JPanel();
		panel_7.setMinimumSize(new Dimension(0, 10));
		panel_ThuocTinhTimKiem.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_2 = new JLabel("Giá MAX");
		lblNewLabel_2.setMinimumSize(new Dimension(2238, 13));
		lblNewLabel_2.setMaximumSize(new Dimension(2238, 13));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		panel_7.add(lblNewLabel_2);
		
		spinner_GiaMax = new JSpinner();
		spinner_GiaMax.setModel(new SpinnerNumberModel(100000, 1000, 100000000, 1000));
		panel_7.add(spinner_GiaMax);
		
		panel_TimKiem.add(scrollPaneTimKiem, BorderLayout.CENTER);
		
		panel_ThongTinSPqr.add(Box.createHorizontalStrut(10));
		
		JPanel panel_QR = new JPanel();
		panel_QR.setPreferredSize(new Dimension(200, 10));
		panel_QR.setMinimumSize(new Dimension(200, 10));
		panel_QR.setMaximumSize(new Dimension(400, 32767));
		panel_ThongTinSPqr.add(panel_QR);
		panel_QR.setLayout(new BorderLayout(0, 0));
		
		label_qr = new JLabel();
		label_qr.setOpaque(true);
		label_qr.setBackground(Color.LIGHT_GRAY);
		label_qr.setHorizontalAlignment(SwingConstants.CENTER);
		panel_QR.add(label_qr, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		panel_8.setPreferredSize(new Dimension(10, 25));
		panel_QR.add(panel_8, BorderLayout.SOUTH);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		panel_8.add(toggleButton);
		lbl_id.setText("_________");
		lbl_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id.setOpaque(true);
		lbl_id.setBackground(Color.WHITE);
		panel_8.add(lbl_id);
		
		JPanel panelDanhSachSP = new JPanel();
		panelDanhSachSP.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints gbc_panelDanhSachSP = new GridBagConstraints();
		gbc_panelDanhSachSP.fill = GridBagConstraints.BOTH;
		gbc_panelDanhSachSP.insets = new Insets(0, 0, 0, 5);
		gbc_panelDanhSachSP.gridx = 0;
		gbc_panelDanhSachSP.gridy = 1;
		add(panelDanhSachSP, gbc_panelDanhSachSP);
        panelDanhSachSP.setLayout(new BorderLayout(0, 0));
        scrollPane_SP.setBackground(Color.LIGHT_GRAY);
		
        scrollPane_SP.setPreferredSize(new Dimension(600, 300));
		panelDanhSachSP.add(scrollPane_SP);
		
		JPanel panelThongTin = new JPanel();
		panelThongTin.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints gbc_panelThongTin = new GridBagConstraints();
		gbc_panelThongTin.insets = new Insets(10, 10, 10, 10);
		gbc_panelThongTin.gridheight = 2;
		gbc_panelThongTin.fill = GridBagConstraints.BOTH;
		gbc_panelThongTin.gridx = 1;
		gbc_panelThongTin.gridy = 0;
		add(panelThongTin, gbc_panelThongTin);
		panelThongTin.setLayout(new BorderLayout(10, 10));
		
		JPanel panelRightInput = new JPanel();
		panelThongTin.add(panelRightInput, BorderLayout.CENTER);
		panelRightInput.setLayout(new BoxLayout(panelRightInput, BoxLayout.Y_AXIS));
		ButtonGroup btnFroupTheThanhvien = new ButtonGroup();
		
		JPanel panel_TheThanhVien = new JPanel();
		panelRightInput.add(panel_TheThanhVien);
		panel_TheThanhVien.setLayout(new BoxLayout(panel_TheThanhVien, BoxLayout.X_AXIS));
		
		JPanel panelTongTien = new JPanel();
		panelTongTien.setMaximumSize(new Dimension(32767, 25));
		panelRightInput.add(panelTongTien);
		panelTongTien.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTongTien = new JLabel("Tổng tiền");
		panelTongTien.add(lblTongTien);
		lblTongTien.setFont(new Font("Arial", Font.BOLD, 14));
		lblTongTien.setPreferredSize(new Dimension(100, 25));
		lblTongTien.setMinimumSize(new Dimension(100, 25));
		lblTongTien.setMaximumSize(new Dimension(100, 25));
		
		textFieldTongTien = new JTextField();
		textFieldTongTien.setHorizontalAlignment(SwingConstants.CENTER);
		panelTongTien.add(textFieldTongTien);
		textFieldTongTien.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldTongTien.setEditable(false);
		textFieldTongTien.setText("0  VND");
		textFieldTongTien.setMinimumSize(new Dimension(7, 25));
		textFieldTongTien.setMaximumSize(new Dimension(2147483647, 25));
		textFieldTongTien.setPreferredSize(new Dimension(7, 25));
		textFieldTongTien.setColumns(20);
		
		JPanel panelThanhTien = new JPanel();
		panelThanhTien.setMaximumSize(new Dimension(32767, 25));
		panelRightInput.add(panelThanhTien);
		panelThanhTien.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblThanhTien = new JLabel("Thành tiền");
		panelThanhTien.add(lblThanhTien);
		lblThanhTien.setFont(new Font("Arial", Font.BOLD, 14));
		lblThanhTien.setPreferredSize(new Dimension(100, 25));
		lblThanhTien.setMinimumSize(new Dimension(100, 25));
		lblThanhTien.setMaximumSize(new Dimension(100, 25));
		
		text_ThanhTien = new JTextField();
		text_ThanhTien.setHorizontalAlignment(SwingConstants.CENTER);
		panelThanhTien.add(text_ThanhTien);
		text_ThanhTien.setFont(new Font("Arial", Font.BOLD, 14));
		text_ThanhTien.setEditable(false);
		text_ThanhTien.setText("0 VND");
		text_ThanhTien.setPreferredSize(new Dimension(7, 25));
		text_ThanhTien.setMinimumSize(new Dimension(7, 25));
		text_ThanhTien.setMaximumSize(new Dimension(2147483647, 25));
		text_ThanhTien.setColumns(10);
		
		JPanel panelKM = new JPanel();
		panelKM.setMaximumSize(new Dimension(32767, 25));
		panelRightInput.add(panelKM);
		panelKM.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblKhuyenMai = new JLabel("% Khuyến mãi");
		panelKM.add(lblKhuyenMai);
		lblKhuyenMai.setFont(new Font("Arial", Font.BOLD, 14));
		lblKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
		lblKhuyenMai.setPreferredSize(new Dimension(100, 25));
		lblKhuyenMai.setMinimumSize(new Dimension(80, 25));
		lblKhuyenMai.setMaximumSize(new Dimension(100, 25));
		
		textKhuyenMai = new JTextField();
		textKhuyenMai.setHorizontalAlignment(SwingConstants.CENTER);
		panelKM.add(textKhuyenMai);
		textKhuyenMai.setFont(new Font("Arial", Font.BOLD, 14));
		textKhuyenMai.setEditable(false);
		textKhuyenMai.setPreferredSize(new Dimension(7, 25));
		textKhuyenMai.setMinimumSize(new Dimension(7, 25));
		textKhuyenMai.setMaximumSize(new Dimension(2147483647, 25));
		textKhuyenMai.setText("0");
		textKhuyenMai.setColumns(20);
		
		JPanel panel_LaThanhVien = new JPanel();
		panel_LaThanhVien.setMaximumSize(new Dimension(32767, 60));
		panelRightInput.add(panel_LaThanhVien);
		panel_LaThanhVien.setLayout(new GridLayout(0, 1, 0, 0));
		
		textTheThanhVien = new JTextField();
		textTheThanhVien.setForeground(Color.RED);
		panel_LaThanhVien.add(textTheThanhVien);
		textTheThanhVien.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		textTheThanhVien.setEditable(false);
		textTheThanhVien.setText("Có thẻ thành viên ?");
		textTheThanhVien.setPreferredSize(new Dimension(7, 25));
		textTheThanhVien.setMaximumSize(new Dimension(2147483647, 25));
		textTheThanhVien.setColumns(10);
		
				
				JPanel panel = new JPanel();
				panel_LaThanhVien.add(panel);
				panel.setMaximumSize(new Dimension(32767, 25));
				rdbtnCoThe.setSelected(true);
				
				rdbtnCoThe.setFont(new Font("Arial", Font.BOLD, 14));
				panel.add(rdbtnCoThe);
				
				rdbtnKhongThe.setFont(new Font("Arial", Font.BOLD, 14));
				panel.add(rdbtnKhongThe);
				btnFroupTheThanhvien.add(rdbtnKhongThe);
				btnFroupTheThanhvien.add(rdbtnCoThe);
				
				panel_SDT.setMaximumSize(new Dimension(32767, 25));
				panelRightInput.add(panel_SDT);
				panel_SDT.setLayout(new GridLayout(0, 2, 0, 0));
				
				JLabel lblSDTKH = new JLabel("SDT\r\n");
				panel_SDT.add(lblSDTKH);
				lblSDTKH.setFont(new Font("Arial", Font.BOLD, 14));
				lblSDTKH.setMaximumSize(new Dimension(300, 25));
				lblSDTKH.setPreferredSize(new Dimension(19, 25));
				
				textSDT = new JTextField();
				textSDT.setHorizontalAlignment(SwingConstants.CENTER);
				panel_SDT.add(textSDT);
				textSDT.setFont(new Font("Arial", Font.BOLD, 14));
				textSDT.setText("0000000000");
				textSDT.setPreferredSize(new Dimension(7, 25));
				textSDT.setMaximumSize(new Dimension(300, 25));
				textSDT.setColumns(10);
				
				panel_TenKH.setMaximumSize(new Dimension(32767, 25));
				panelRightInput.add(panel_TenKH);
				panel_TenKH.setLayout(new GridLayout(0, 2, 0, 0));
				
				JLabel lblTenKH = new JLabel("Tên Khách hàng\r\n");
				lblTenKH.setFont(new Font("Arial", Font.BOLD, 14));
				panel_TenKH.add(lblTenKH);
				lblTenKH.setMaximumSize(new Dimension(300, 25));
				
				textTenKH = new JTextField();
				textTenKH.setToolTipText("hãy nhập SDT thể hiển thị tên khách hàng\r\n");
				textTenKH.setHorizontalAlignment(SwingConstants.CENTER);
				textTenKH.setFont(new Font("Arial", Font.PLAIN, 14));
				panel_TenKH.add(textTenKH);
				textTenKH.setEditable(false);
				textTenKH.setText("???");
				textTenKH.setPreferredSize(new Dimension(7, 25));
				textTenKH.setMaximumSize(new Dimension(2147483647, 25));
				textTenKH.setColumns(110);
				
				panel_DTL.setMaximumSize(new Dimension(32767, 25));
				panelRightInput.add(panel_DTL);
				panel_DTL.setLayout(new GridLayout(0, 2, 0, 0));
				
				JLabel lblDiemTL = new JLabel("Điểm tích luỹ");
				lblDiemTL.setFont(new Font("Arial", Font.BOLD, 14));
				panel_DTL.add(lblDiemTL);
				lblDiemTL.setMaximumSize(new Dimension(518, 25));
				lblDiemTL.setPreferredSize(new Dimension(100, 25));
				
				textDiem = new JTextField();
				textDiem.setToolTipText("Hãy nhập SDT để hiển thị điểm của khách hàng\r\n");
				textDiem.setHorizontalAlignment(SwingConstants.CENTER);
				textDiem.setFont(new Font("Arial", Font.BOLD, 14));
				panel_DTL.add(textDiem);
				textDiem.setEditable(false);
				textDiem.setText("???");
				textDiem.setPreferredSize(new Dimension(7, 25));
				textDiem.setMaximumSize(new Dimension(2147483647, 25));
				textDiem.setColumns(10);
				
				panel_MuaDTL.setMaximumSize(new Dimension(32767, 60));
				panelRightInput.add(panel_MuaDTL);
				panel_MuaDTL.setLayout(new GridLayout(0, 1, 0, 0));
				
				textMuaDTL = new JTextField();
				textMuaDTL.setForeground(Color.RED);
				textMuaDTL.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
				panel_MuaDTL.add(textMuaDTL);
				textMuaDTL.setEditable(false);
				textMuaDTL.setPreferredSize(new Dimension(7, 25));
				textMuaDTL.setMaximumSize(new Dimension(300, 25));
				textMuaDTL.setText("Mua với điểm tích luỷ không ?");
				textMuaDTL.setColumns(10);
				
				JPanel panel_1 = new JPanel();
				panel_MuaDTL.add(panel_1);
				panel_1.setPreferredSize(new Dimension(10, 25));
				panel_1.setMaximumSize(new Dimension(32767, 25));
				rdbtnMuaDTL.setSelected(true);
				
				rdbtnMuaDTL.setFont(new Font("Arial", Font.BOLD, 14));
				panel_1.add(rdbtnMuaDTL);
				
				rdbbtnKhongMuaDTL.setFont(new Font("Arial", Font.BOLD, 14));
				panel_1.add(rdbbtnKhongMuaDTL);

				ButtonGroup buttonGroup1 = new ButtonGroup();
				buttonGroup1.add(rdbtnMuaDTL);
				buttonGroup1.add(rdbbtnKhongMuaDTL);
				
				panel_ChonDTL.setMaximumSize(new Dimension(32767, 25));
				panelRightInput.add(panel_ChonDTL);
				panel_ChonDTL.setLayout(new GridLayout(0, 2, 0, 0));
				
				JLabel lblChonDiem = new JLabel("Chọn điểm tích luỹ");
				lblChonDiem.setFont(new Font("Arial", Font.BOLD, 14));
				lblChonDiem.setPreferredSize(new Dimension(150, 25));
				panel_ChonDTL.add(lblChonDiem);
				lblChonDiem.setMaximumSize(new Dimension(285, 25));
				

				ArrayList<DiemTichLuyDTO> dsDTL =  DiemTichLuyBLL.getAllDiemTichLuy();
				for(DiemTichLuyDTO dtl : dsDTL){
					comboBoxDTL.addItem(dtl.getDiemTL()  +" Điểm    (-" + (double)dtl.getTiLeGiam()+"%)");
				}
				panel_ChonDTL.add(comboBoxDTL);
				comboBoxDTL.setMinimumSize(new Dimension(29, 25));
				comboBoxDTL.setPreferredSize(new Dimension(29, 25));
				comboBoxDTL.setMaximumSize(new Dimension(32767, 25));
				
				JPanel panel_PTTT = new JPanel();
				panel_PTTT.setMaximumSize(new Dimension(32767, 25));
				panelRightInput.add(panel_PTTT);
				panel_PTTT.setLayout(new GridLayout(0, 2, 0, 0));
				
				JLabel lblPhuongThucTT = new JLabel("PT thanh toán\r\n\r\n");
				lblPhuongThucTT.setPreferredSize(new Dimension(100, 13));
				lblPhuongThucTT.setFont(new Font("Arial", Font.BOLD, 14));
				panel_PTTT.add(lblPhuongThucTT);
				lblPhuongThucTT.setMaximumSize(new Dimension(164, 25));
				
				panel_PTTT.add(comboBoxPTTT);
				comboBoxPTTT.setMaximumSize(new Dimension(32767, 25));
				comboBoxPTTT.setPreferredSize(new Dimension(29, 25));
				
				panel_TienKD.setMaximumSize(new Dimension(32767, 25));
				panelRightInput.add(panel_TienKD);
				panel_TienKD.setLayout(new GridLayout(0, 2, 0, 0));
				
				JLabel lblTienKhachDua = new JLabel("Tiền khách đưa\r\n");
				lblTienKhachDua.setFont(new Font("Arial", Font.BOLD, 14));
				panel_TienKD.add(lblTienKhachDua);
				lblTienKhachDua.setMaximumSize(new Dimension(154, 25));
				lblTienKhachDua.setPreferredSize(new Dimension(71, 25));
				
				spinner_tienKD.setModel(new SpinnerNumberModel(0, 0, 1000000000, 1000));
				panel_TienKD.add(spinner_tienKD);
				spinner_tienKD.setPreferredSize(new Dimension(29, 25));
				spinner_tienKD.setMaximumSize(new Dimension(32767, 25));
				panel_QRbank.setVisible(false);
				
				panel_QRbank.setMaximumSize(new Dimension(200, 200));
				panelRightInput.add(panel_QRbank);
				panel_QRbank.setLayout(new BorderLayout(0, 0));
				
	
				ImageIcon icon = new ImageIcon("src/main/resources/images/QR.jpeg");
		        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		        ImageIcon scaledIcon = new ImageIcon(img);
		       
		        JLabel lblNewLabel_3 = new JLabel(scaledIcon);
				panel_QRbank.add(lblNewLabel_3);
				
				JPanel panel_2 = new JPanel();
				panel_2.setPreferredSize(new Dimension(310, 10));
				panel_2.setMaximumSize(new Dimension(32767, 60));
				panelRightInput.add(panel_2);
				panel_2.setLayout(new GridLayout(2, 1, 10, 10));
				
				panel_ButtonLuuInDH.setMaximumSize(new Dimension(32767, 25));
				panel_2.add(panel_ButtonLuuInDH);
				panel_ButtonLuuInDH.setLayout(new BorderLayout(0, 0));
				
				btnInDH.setBackground(new Color(0, 102, 153));
				btnInDH.setFont(new Font("Arial", Font.BOLD, 14));
				panel_ButtonLuuInDH.add(btnInDH);
				btnLuuDonHang.setBackground(new Color(51, 153, 0));
				
				btnLuuDonHang.setFont(new Font("Arial", Font.BOLD, 14));
				panel_ButtonLuuInDH.add(btnLuuDonHang);
				
				btnTaoMoi.setBackground(new Color(255, 0, 51));
				// btnHuy.setBackground(new Color(255, 51, 0));
				// RoundedComponent.setRadius(12);
				// btnHuy = RoundedComponent.createRoundedButton(btnHuy, new Color(255, 51, 0));
				btnTaoMoi.setFont(new Font("Arial", Font.BOLD, 14));
				panel_2.add(btnTaoMoi);


				// //////////////////////////////////////////////////

				
		
				// =================================================
		LoadSanPhamTimKiem();
		AddEventTimKiem();
		customizeTable1(tableProduct);
		updateColumnWidths();
		customizeTable1(tableTimKiem);
	}

	public  double calCalculateTotalAmount(){
        double total = 0;
        for(int i = 0; i < tableProduct.getRowCount(); i++){
            double price = Double.parseDouble(tableProduct.getValueAt(i, 2).toString());
            double discount = Double.parseDouble(tableProduct.getValueAt(i, 3).toString());
            double quantity = Double.parseDouble(tableProduct.getValueAt(i, 4).toString());
            total += (price - (price * discount / 100)) * quantity;
        }
		if(khachHang != null && rdbtnMuaDTL.isSelected()){
			DiemTichLuyDTO DTL_ = DiemTichLuyBLL.getAllDiemTichLuy().get(comboBoxDTL.getSelectedIndex()); 
			total = total*(1 - (double)DTL_.getTiLeGiam()/100);
		}
        return total;
    }

    public static double calCalculateTotal(){
        double total = 0;
        for(int i = 0; i < tableProduct.getRowCount(); i++){
            double price = Double.parseDouble(tableProduct.getValueAt(i, 2).toString());
            double quantity = Double.parseDouble(tableProduct.getValueAt(i, 4).toString());
            total += (price ) * quantity;
        }
        return total;
    }
    
	


    
    private static void updateColumnWidths() {
        TableColumnModel columnModel = tableProduct.getColumnModel();
        int totalWidth = scrollPane_SP.getViewport().getWidth();
        for (int i = 0; i < WIDTH_COL.length; i++) {
            columnModel.getColumn(i).setPreferredWidth((int) (totalWidth * WIDTH_COL[i]));
        }
    }

    private static JPopupMenu createPopupMenu() {
        popupMenu = new JPopupMenu();
        
         JMenuItem viewItem = new JMenuItem("Xem chi tiết", new ImageIcon("src/main/resources/images/menuu.png"));
        JMenuItem deleteItem = new JMenuItem("Xóa sản phẩm", new ImageIcon("src/main/resources/images/deletee.png"));
        JMenuItem editQtyItem = new JMenuItem("Sửa số lượng", new ImageIcon("src/main/resources/images/editt.png"));


        viewItem.addActionListener(e -> viewProduct());
        deleteItem.addActionListener(e -> deleteProduct());
        editQtyItem.addActionListener(e -> editQuantity());

        popupMenu.add(viewItem);
        popupMenu.add(editQtyItem);
        popupMenu.add(deleteItem);

        return popupMenu;
    }

    private static void showPopup(MouseEvent e) {
        int row = tableProduct.rowAtPoint(e.getPoint());
        if (row >= 0) {
            tableProduct.setRowSelectionInterval(row, row);
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

 
    
	public void addProductDetail(Object[] rowData) {
		SanPhamDTO  sp = SanPhamBLL.getProductById(Integer.parseInt(rowData[0].toString()));
		for (int i = 0; i < tableProduct.getRowCount(); i++) {
			if (rowData[0].toString().equals(tableProduct.getValueAt(i, 0).toString())) {
				int oldQty = Integer.parseInt(tableProduct.getValueAt(i, 4).toString());
				int newQty = Integer.parseInt(rowData[4].toString());
				if(sp .getSoLuongTon() < oldQty + newQty){
					JOptionPane.showMessageDialog(null, " Đơn hàng sẽ có số lượng của sản phẩm " +  sp .getTenSP() + " vượt quá số lượng tồn kho sau khi thêm (" + sp .getSoLuongTon() +  " < "  + (oldQty+ newQty) +") . KHÔNG thể thêm!!!" );
					return;
				}
				tableProduct.setValueAt(oldQty + newQty, i, 4);
				tableProduct.setValueAt(Double.parseDouble(rowData[2].toString())*(1 - (double) Double.parseDouble(rowData[3].toString())/100)*(oldQty + newQty), i, 5);
				rederOrderInformation();
				JOptionPane.showMessageDialog(null, "Thêm thành công " + rowData[1].toString());
				return;
			}
		}
		tableModel_SP.addRow(rowData);
		JOptionPane.showMessageDialog(null, "Thêm thành công " + rowData[1].toString());
		rederOrderInformation();
	}
	

    private static void viewProduct() {
        int row = tableProduct.getSelectedRow();
        if (row != -1) {
            String productName = tableProduct.getValueAt(row, 1).toString();
            // JOptionPane.showMessageDialog(null, "Thông tin chi tiết sản phẩm: " + productName, "Chi tiết", JOptionPane.INFORMATION_MESSAGE);
			new ChiTietSP_Dialog(null, Integer.parseInt( tableProduct.getValueAt(row, 0).toString())).setVisible(true);;
        }
    }

    private static void deleteProduct() {
        int row = tableProduct.getSelectedRow();
        if (row != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel_SP.removeRow(row);
            }
        }
    }

    private static void editQuantity() {
        int row = tableProduct.getSelectedRow();
        if (row != -1) {
            String currentQty = tableProduct.getValueAt(row, 4).toString();
            String newQty = JOptionPane.showInputDialog(null, "Nhập số lượng mới:", currentQty);
			SanPhamDTO sp = SanPhamBLL.getProductById(Integer.parseInt(tableProduct.getValueAt(row, 0).toString()));
			if(sp.getSoLuongTon() <  Integer.parseInt(newQty)){
				JOptionPane.showMessageDialog(null, " Đơn hàng sẽ có số lượng của sản phẩm " +  sp .getTenSP() + " vượt quá số lượng tồn kho sau khi thêm (" + sp .getSoLuongTon() +  " < "  + ( newQty) +") . KHÔNG thể thêm!!!" );
				return;
			}
            if (newQty != null && !newQty.trim().isEmpty()) {
                double price = Double.parseDouble(tableProduct.getValueAt(row, 2).toString());
                double discount = Double.parseDouble(tableProduct.getValueAt(row, 3).toString());
                double total = (price - (price * discount / 100)) * Integer.parseInt(newQty);

                tableModel_SP.setValueAt(newQty, row, 4);
                tableModel_SP.setValueAt(total, row, 5);
				JOptionPane.showMessageDialog(null, "Sửa số lượng thành công!!!" );

            }
        }
    }

    public static void SaveOrderDetailList(int orderId){
        for( int i = 0; i < tableModel_SP.getRowCount(); i++){
            ChiTietDonHangDTO orderDetail = new ChiTietDonHangDTO();
            orderDetail.setMaDH(orderId);
            orderDetail.setMaSP(Integer.parseInt( (tableModel_SP.getValueAt(i, 0).toString())));
            orderDetail.setSoLuong(Integer.parseInt( (tableModel_SP.getValueAt(i, 4).toString())));
            orderDetail.setTrangThai("ACTIVE");
            ChiTietDonHangBLL.insertOrderDetail(orderDetail);
        }
    }

	// =======================================================================
	public  void LoadSanPhamTimKiem(){
		String idOrTenSP = textTenOrID.getText();
		String idOrTenLoaiSP = (String) combobox_LoaiSP.getSelectedItem();
		Object giaMinObject = spinner_GiaMin.getValue();
		int giaMin = (Integer)giaMinObject;
		Object giaMaxObject = spinner_GiaMax.getValue();
		int giaMax = (Integer)giaMaxObject;

		ArrayList<SanPhamDTO> dsSP = SanPhamBLL.timKiemSanPham(idOrTenSP, idOrTenLoaiSP, giaMin, giaMax);
		model_timKiem.setRowCount(0);
		for(SanPhamDTO sp : dsSP){
			double ctkm = ChiTietKhuyenMaiBLL.getProductOnSaleToday(sp.getMaSP());

			model_timKiem.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getGia(), ctkm});	
		}
	}

	public void AddEventTimKiem(){
		// ============================================ tiềm kiếm
		textTenOrID.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				LoadSanPhamTimKiem();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				LoadSanPhamTimKiem();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				LoadSanPhamTimKiem(); 
			}
		});

		combobox_LoaiSP.addActionListener(e ->{
			LoadSanPhamTimKiem();

		});
			

		spinner_GiaMin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				LoadSanPhamTimKiem();
			}
		});

		spinner_GiaMax.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				LoadSanPhamTimKiem();
			}
		});
		tableTimKiem.setPreferredScrollableViewportSize(new Dimension(40, 400));

		tableTimKiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Kiểm tra nếu là double-click
				if (e.getClickCount() == 2) {
					int row = tableTimKiem.getSelectedRow();
					if (row != -1) {
						int maSP = (int) tableTimKiem.getValueAt(row, 0);  // Giả sử "Mã" nằm ở cột 0
						String tenSP = (String) tableTimKiem.getValueAt(row, 1); // Giả sử "Tên" nằm ở cột 1
						double gia = (double) tableTimKiem.getValueAt(row, 2); // Giả sử "Giá" nằm ở cột 2
						double rate = ChiTietKhuyenMaiBLL.getProductOnSaleToday(maSP);
					 	addProductDetail(new Object[]{maSP, tenSP, gia,rate, 1, gia*(1 - (double)ChiTietKhuyenMaiBLL.getProductOnSaleToday(maSP)/100)});
						 rederOrderInformation();
					}
				}
			}
		});



		// =====================================thông tin
		textSDT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String phone = textSDT.getText().trim();
				khachHang = TheThanhVienBLL.getMemberByPhone(phone);
                if (!phone.isEmpty()) {
                    if (khachHang != null) {
                        textTenKH.setText(khachHang.getTenTV());
                        textDiem.setText(khachHang.getDiemTL() + "");
                       
					}else{
                        textTenKH.setText("lỗi thành viên!");
                        textDiem.setText("lỗi thành viên!");
                    }
                }
            }
        });     
    

		toggleButton.addActionListener(e -> toggleScanning());
		tableProduct.setComponentPopupMenu(createPopupMenu());
		tableProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showPopup(e);
				}
			}
		});


		tableModel_SP.addTableModelListener(e -> {
			if (e.getType() == TableModelEvent.INSERT) {
				rederOrderInformation();
			} else if (e.getType() == TableModelEvent.DELETE) {
				rederOrderInformation();
			} else if (e.getType() == TableModelEvent.UPDATE) {
			rederOrderInformation();
		}
		});
		

		rdbtnKhongThe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnKhongThe.isSelected()) {
					panel_SDT.setVisible(false);
					panel_TenKH.setVisible(false);
					panel_DTL.setVisible(false);
					panel_MuaDTL.setVisible(false);
					panel_ChonDTL.setVisible(false);
					rdbbtnKhongMuaDTL.setSelected(true);

				}
			}
		});
		rdbtnCoThe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnCoThe.isSelected()) {
					panel_SDT.setVisible(true);
					panel_TenKH.setVisible(true);
					panel_DTL.setVisible(true);
					panel_MuaDTL.setVisible(true);
				}
			}
		});

		rdbbtnKhongMuaDTL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbbtnKhongMuaDTL.isSelected()) {
					panel_ChonDTL.setVisible(false);
				}
			}
		});
		rdbtnMuaDTL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnMuaDTL.isSelected()) {
					panel_ChonDTL.setVisible(true);
				}
			}
		});
	
		comboBoxPTTT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) comboBoxPTTT.getSelectedItem();
				System.out.println("Lựa chọn hiện tại: " + selected); // Debug
		
				if (selected != null && selected.equalsIgnoreCase("tiền mặt")) {
					panel_QRbank.setVisible(false);
					panel_TienKD.setVisible(true);
				} else {
					panel_QRbank.setVisible(true);
					panel_TienKD.setVisible(false);
				}
		
				panel_QRbank.getParent().revalidate();
				panel_QRbank.getParent().repaint();
			}
		});


		btnLuuDonHang.addActionListener(e->{
			SaveOrder();
		});

		btnInDH.addActionListener(e->{
			JOptionPane.showMessageDialog(null, " id " + JUST_MADONHANG);
			new ChiTietDH_Dialog(null, JUST_MADONHANG).setVisible(true);;
		});

		btnTaoMoi.addActionListener(e->{

			int confirm = JOptionPane.showConfirmDialog(
			null,
			"Bạn có chắc chắn xoá đơn hiện tại để thêm mới không?",
			"Xác nhận",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		
		if (confirm == JOptionPane.NO_OPTION) {
			panel_ButtonLuuInDH.removeAll();
			panel_ButtonLuuInDH.add(btnLuuDonHang);
			panel_ButtonLuuInDH.repaint();
			panel_ButtonLuuInDH.revalidate();			
			tableModel_SP.setRowCount(0);
			textFieldTongTien.setText("0 VND");
			text_ThanhTien .setText("0 VND");
			textKhuyenMai .setText("???");
			// textTheThanhVien .setText("");
			textSDT .setText("???");
			textTenKH .setText("???");
			textDiem .setText("???");
			// textMuaDTL .setText("");
			textTenOrID.setText("");
			JUST_MADONHANG = -1;
			
		}

		});
		

	};

	
	private void customizeTable1(JTable talbe_) {
		// Thiết lập font cho bảng và các cài đặt khác
		talbe_.setFont(new Font("Arial", Font.PLAIN, 14));
		talbe_.setRowHeight(30);
		talbe_.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		talbe_.getTableHeader().setBackground(new Color(17, 32, 51));
		talbe_.getTableHeader().setForeground(Color.WHITE);
		talbe_.setSelectionBackground(new Color(200, 230, 255));
		talbe_.setSelectionForeground(Color.BLACK);
		talbe_.setGridColor(new Color(220, 220, 220));
		talbe_.setShowVerticalLines(false);
		talbe_.setShowHorizontalLines(false);
	
		// Đảm bảo bảng không thể chỉnh sửa
		talbe_.setDefaultEditor(Object.class, null); // Không cho phép chỉnh sửa bất kỳ cột nào
		
		// Custom talbe_CellRenderer
		DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setHorizontalAlignment(SwingConstants.CENTER);
				if (!isSelected) {
					cell.setBackground(row % 2 == 0 ? Color.WHITE : new Color(235, 235, 235)); 
				}
				return cell;
			}
		};
	
		// Đặt custom renderer cho tất cả các cột
		for (int i = 0; i < talbe_.getColumnCount(); i++) {
			talbe_.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
		}
	}
	
	// ===============================================================================


	
    private void toggleScanning() {
        if (isScanning) {
            stopScanning();
        } else {
            startScanning();
        }
    }

	 private void startScanning() {
        isScanning = true;
        toggleButton.setText("Tắt Scan");
        scanThread = new Thread(() -> {
            try {
                grabber = new OpenCVFrameGrabber(0);
                grabber.setImageWidth(150);
                grabber.setImageHeight(150);
                grabber.start();

                Java2DFrameConverter converter = new Java2DFrameConverter();
                OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();
                String lastQRText = "";
                long lastScanTime = 0;
                                

                while (isScanning) {
                    Frame frameGrabbed = grabber.grab();
                    if (frameGrabbed == null) continue;

                    Mat mat = converterToMat.convert(frameGrabbed);
                    Mat flippedMat = new Mat();
                    opencv_core.flip(mat, flippedMat, 1);
                    Frame flippedFrame = converterToMat.convert(flippedMat);
                    BufferedImage img = converter.getBufferedImage(flippedFrame);

                    if (img != null) {
                        Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        label_qr.setIcon(new ImageIcon(scaledImg));
                        String qrText = decodeQRCode(img);


                        long now = System.currentTimeMillis();

                        // Nếu phát hiện mã QR mới, hoặc cùng mã cũ nhưng đã qua 3 giây
                        if (qrText != null && (!qrText.equals(lastQRText) || now - lastScanTime >= 3000)) {
                            try {
                                lbl_id.setText(qrText);
                                int id = Integer.parseInt(qrText);
                                System.out.println("Quét thành công: " + qrText);
                                insertProductInformation(id);

                                // Ghi nhận mã hiện tại và thời gian
                                lastQRText = qrText;
                                lastScanTime = now;

                            } catch (NumberFormatException ex) {
                                System.out.println("QRcode không phải số");
                            }
                        }

                        else {
                            System.out.println("QRcode null");
                        }
                    }

                    // Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                stopScanning();
            }
        });
        scanThread.start();
    }

    private void stopScanning() {
        isScanning = false;
        toggleButton.setText("Bật Scan");

        try {
            if (grabber != null) {
                grabber.stop();
                grabber.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String decodeQRCode(BufferedImage img) {
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(img);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

            Result result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {
            return null;
        }
    }

 



	
	public  void insertProductInformation(int id) {
		SanPhamDTO sp = SanPhamBLL.getProductById(id);
		if(sp != null) {
            double tiLeGiam = ChiTietKhuyenMaiBLL.getProductOnSaleToday(id);
            double price = sp.getGia()*(1 - (double)tiLeGiam/100);
            addProductDetail(new Object[]{id, sp.getTenSP(), sp.getGia(), tiLeGiam, 1 , price});
			rederOrderInformation();
		}else{
			JOptionPane.showMessageDialog(null,"Thêm không thành công sản phẩm mã " + id + "" );
        }
	}



	  public  void rederOrderInformation(){
      	int  total = (int) calCalculateTotal();
       	int  totalAmount = (int) calCalculateTotalAmount() ;
		textFieldTongTien.setText(total + "  VND");
        text_ThanhTien.setText(totalAmount + "  VND");
    }


	public void SaveOrder(){
		
		if(tableProduct.getRowCount() == 0){
			JOptionPane.showMessageDialog(null,"Không thê thêm vì không có sản phẩm, Vui lòng thêm sản phẩm");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(
			null,
			"Bạn có chắc chắn muốn lưu đơn hàng này không?",
			"Xác nhận",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		
		if (confirm == JOptionPane.NO_OPTION) {
			return;
		}
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
		KhuyenMaiDTO km =  KhuyenMaiBLL.getDiscountToday();
		Integer maKM = (km != null)? km.getMaKM() : null;
		int tongTien = (int)calCalculateTotalAmount();
		System.out.println(tongTien);
		String pttt = ((String) comboBoxPTTT.getSelectedItem()).equalsIgnoreCase("tiền mặt") ? "CASH" : "BANK";
		int tienKD = 0;
		if(pttt.equals("CASH")){
			if((int)spinner_tienKD.getValue() < tongTien){
				JOptionPane.showMessageDialog(null, "tiền khách đưa không đủ để thanh toán!!!");
				return;
			}else{
				tienKD = (int)spinner_tienKD.getValue() ;
			}
		}else{
			tienKD = -1;
		}

		int maDH = -1;
		System.out.print(maDH);

		if(rdbtnCoThe.isSelected()){
			if(khachHang != null){
				if(rdbtnMuaDTL.isSelected()){
					String str = (String ) comboBoxDTL.getSelectedItem();
					String numberStr = str.split(" ")[0]; 
					int dieuKienDTL = Integer.parseInt(numberStr);
					if(Integer.parseInt(textDiem.getText()) < dieuKienDTL){
						JOptionPane.showMessageDialog(null, "Điểm của khách hàng khôn đủ để giảm giá !!!");
						return ;
					}else{
						DiemTichLuyDTO DTL_ = DiemTichLuyBLL.getAllDiemTichLuy().get(comboBoxDTL.getSelectedIndex()); 
						maDH = DonHangBLL.insertOrder(new DonHangDTO(1, khachHang.getMaTV(), maKM, NHANVIEN.getMaNV(), pttt, formattedDateTime,DTL_.getMaDTL(), tienKD,tongTien, "FINISHED"));						
						khachHang.setDiemTL(khachHang.getDiemTL() - dieuKienDTL + (int)(calCalculateTotalAmount()/1000));
						TheThanhVienBLL.updateMember(khachHang);
					}
				}else{
					maDH = DonHangBLL.insertOrder(new DonHangDTO(1, khachHang.getMaTV(), maKM, NHANVIEN.getMaNV(), pttt, formattedDateTime,null, tienKD,tongTien, "FINISHED"));	
					khachHang.setDiemTL(khachHang.getDiemTL()  + (int)(calCalculateTotalAmount()/1000));
					TheThanhVienBLL.updateMember(khachHang);					
				}
			}else{
				JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại của khách hàng để tiếp tục !!!");
				return ;
			}
		}else{
			JUST_MADONHANG = DonHangBLL.insertOrder(new DonHangDTO(1, null, maKM, NHANVIEN.getMaNV(), pttt, formattedDateTime,null, tienKD,tongTien, "FINISHED"));	
			// JUST_MADONHANG 
		}

		for( int i = 0; i < tableProduct.getRowCount(); i++){
			ChiTietDonHangBLL.insertOrderDetail(new ChiTietDonHangDTO(JUST_MADONHANG, (int) tableProduct.getValueAt(i, 0), (int)tableProduct.getValueAt(i, 4), "ACTIVE"));
		}
		JOptionPane.showMessageDialog(null, "Lưu đơn hàng thành công !!!");
		panel_ButtonLuuInDH.removeAll();
		panel_ButtonLuuInDH.add(btnInDH);
		panel_ButtonLuuInDH.repaint();
		panel_ButtonLuuInDH.revalidate();		
	}

	public void taoMoiDonHang(){
		
	
	
	rdbbtnKhongMuaDTL = new JRadioButton("Không");
	rdbtnMuaDTL = new JRadioButton("Có\r\n");

	comboBoxPTTT = new JComboBox(new String[]{ "Tiền mặt", "Chuyển khoản"});
	comboBoxDTL = new JComboBox();
	spinner_tienKD = new JSpinner();


	JButton btnLuuDonHang = new JButton("Lưu đơn hàng\r\n");
	JButton btnInDH = new JButton("In hoá đơn");





	// private JTable	tableTimKiem = new JTable(model_timKiem);
	// private JScrollPane scrollPaneTimKiem = new JScrollPane(tableTimKiem);
	// private JComboBox<String> combobox_LoaiSP = new JComboBox<>(new String[]{"Tất cả"});

	// private JButton toggleButton = new JButton("Bật Scan");

  	 tableModel_SP = new DefaultTableModel(HEADER, 0);
	tableProduct = new JTable(tableModel_SP);
	// spinner_GiaMin;
	// spinner_GiaMax;




	 NHANVIEN = new NhanVienDTO(
            1,
            "Nguyễn Văn A",
            null,
            "Nam",
            "123 Đường ABC, Quận 1",
            "0123456789",
            "123456789012",
            8000000.0,
            1
        ); 

	khachHang = null;

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Danh sách sản phẩm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 700);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(new OrderPanel(), BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
