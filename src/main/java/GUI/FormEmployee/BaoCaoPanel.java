package GUI.FormEmployee;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import BLL.DonHangBLL;
import BLL.NhanVienBLL;
import BLL.SearchFilterBLL;
import DTO.DonHangDTO;
import DTO.NhanVienDTO;
import EXCEL.ExportExcelReport;
import GUI.Admin_PanelThongKe.PanelTimThK;
import GUI.ComponentCommon.RoundedComponent;
import GUI.ComponentCommon.TienIch;
import PDF.ChiTietBC_Dialog;
import PDF.ChiTietDH_Dialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.Cursor;

public class BaoCaoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
 	private  final String[] HEADER = {"Mã đơn hàng", "Tên nhân viên", "PT thanh toán",  "thành tiền", "Ngày"};
    private  DefaultTableModel tableModel = new DefaultTableModel(HEADER, 0);
	private  JTable tableOrder = new JTable(tableModel);
    private  JScrollPane scrollPane = new JScrollPane(tableOrder);
	private static ArrayList<DonHangDTO> DSHoaDon = new ArrayList<DonHangDTO>();
	private JPanel panel_TimKiemBtn = new JPanel();
	private static  PanelTimThK panelTimKiem = new PanelTimThK();
	private JLabel lbl_TongTien = new JLabel("10000000 VNĐ");
	private JLabel lbl_DH = new JLabel("2000");
	private static int soDonHang = 0;
	private static double danhThu = 0;
	private JPanel panel_Excel = new JPanel();
	private JPanel panel_PDF = new JPanel();

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

	/**
	 * Create the panel.
	 */
	public BaoCaoPanel() {
		panelTimKiem = new PanelTimThK();
		NhanVienBLL nvBLL = new NhanVienBLL();
		NHANVIEN = nvBLL.getNhanVienByMa(ProfilePanel.getMaNhanVien() + "");
		setBackground(new Color(224, 255, 255));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_ThongKe = new JPanel();
		panel_ThongKe.setBackground(new Color(224, 255, 255));
		panel_ThongKe.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_ThongKe.setPreferredSize(new Dimension(10, 150));
		add(panel_ThongKe, BorderLayout.NORTH);
		panel_ThongKe.setLayout(new GridLayout(0, 1, 10, 10));
		

		ImageIcon imageIcon = new ImageIcon( "src/main/resources/images/icon/Shopping_bag.png");
		Image image = imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); 
		
		ImageIcon imageIcon1 = new ImageIcon( "src/main/resources/images/icon/dollar_.png");
		Image image1 = imageIcon1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); 
		
		JPanel panel_4 = new JPanel();
		panel_ThongKe.add(panel_4);
		panel_4.setLayout(new BorderLayout(10, 10));
		
		JPanel panel_1 = new JPanel();
		panel_4.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 10, 0));
		
		JPanel panel_DonHang = new JPanel();
		RoundedComponent.setRadius(13);
		// panel_DonHang = RoundedComponent.createRoundedPanel(panel_DonHang, new Color(0, 153, 153));
		panel_1.add(panel_DonHang);
		panel_DonHang.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_DonHang.setBackground(new Color(0, 153, 153));
		panel_DonHang.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_IconDH = new JPanel();
		panel_IconDH.setOpaque(false);
		panel_DonHang.add(panel_IconDH);
		panel_IconDH.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(image));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_IconDH.add(lblNewLabel);
		
		JPanel panel_SoLieuDH = new JPanel();
		panel_SoLieuDH.setOpaque(false);
		panel_DonHang.add(panel_SoLieuDH);
		panel_SoLieuDH.setLayout(new GridLayout(2, 0, 0, 0));
		
		
		lbl_DH.setFont(new Font("Arial", Font.BOLD, 20));
		panel_SoLieuDH.add(lbl_DH);
		
		JLabel lbl_donHang = new JLabel("Đơn Hàng");
		lbl_donHang.setFont(new Font("Arial", Font.BOLD, 20));
		panel_SoLieuDH.add(lbl_donHang);
		
		JPanel panel_DanhThu = new JPanel();
		panel_1.add(panel_DanhThu);
		panel_DanhThu.setBackground(new Color(250, 128, 114));
		panel_DanhThu.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_DanhThu.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_iconDT = new JPanel();
		panel_iconDT.setOpaque(false);
		panel_DanhThu.add(panel_iconDT);
		panel_iconDT.setLayout(new BorderLayout(0, 0));
		

		JLabel lbl_iconDT = new JLabel();
		lbl_iconDT.setIcon(new ImageIcon(image1));
		lbl_iconDT.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_iconDT.setHorizontalAlignment(SwingConstants.CENTER);
		panel_iconDT.add(lbl_iconDT, BorderLayout.CENTER);
		
		JPanel panel_ThongTinDT = new JPanel();
		panel_ThongTinDT.setOpaque(false);
		panel_DanhThu.add(panel_ThongTinDT);
		panel_ThongTinDT.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel lbl_danhthu = new JLabel("Danh thu");
		lbl_danhthu.setFont(new Font("Arial", Font.BOLD, 20));
		panel_ThongTinDT.add(lbl_danhthu);
		
		
		lbl_TongTien.setFont(new Font("Arial", Font.BOLD, 20));
		panel_ThongTinDT.add(lbl_TongTien);
		
		JPanel panel_Button = new JPanel();
		panel_4.add(panel_Button, BorderLayout.EAST);
		panel_Button.setLayout(new GridLayout(1, 0, 0, 0));
		panel_TimKiemBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		panel_TimKiemBtn.setBackground(new Color(189, 183, 107));
		panel_Button.add(panel_TimKiemBtn);
		panel_TimKiemBtn.setLayout(new BorderLayout(0, 0));

		ImageIcon imageIconTimKiem = new ImageIcon( "src/main/resources/images/icon/search_1.png");
		Image imageTimKiem = imageIconTimKiem.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH); 
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(imageTimKiem));
		lblNewLabel_1.setPreferredSize(new Dimension(43, 80));
		panel_TimKiemBtn.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel_4 = new JLabel("Tìm kiếm");
		lblNewLabel_4.setPreferredSize(new Dimension(80, 13));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_TimKiemBtn.add(lblNewLabel_4, BorderLayout.CENTER);
		
	
		panel_Excel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_Excel.setBackground(new Color(143, 188, 143));
		panel_Button.add(panel_Excel);
		panel_Excel.setLayout(new BorderLayout(0, 0));
		
		ImageIcon imageIconExcel = new ImageIcon( "src/main/resources/images/icon/excel.png");
		Image imageExcel = imageIconExcel.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH); 
		

		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setIcon(new ImageIcon(imageExcel));
		lblNewLabel_2.setPreferredSize(new Dimension(43, 80));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_Excel.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("Xuất");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		panel_Excel.add(lblNewLabel_5, BorderLayout.CENTER);
		
		panel_PDF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_PDF.setBackground(new Color(219, 112, 147));
		panel_Button.add(panel_PDF);
		panel_PDF.setLayout(new BorderLayout(0, 0));
		ImageIcon imageIconPDF = new ImageIcon( "src/main/resources/images/icon/pdf.png");
		Image imagePDF = imageIconPDF.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH); 
		
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setIcon(new ImageIcon(imagePDF));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setPreferredSize(new Dimension(43, 80));
		panel_PDF.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JLabel lblNewLabel_6 = new JLabel("Xuất\r\n\r\n");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_PDF.add(lblNewLabel_6, BorderLayout.CENTER);
		
		JPanel panel_DanhSachDH = new JPanel();
		panel_DanhSachDH.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_DanhSachDH.setBackground(new Color(224, 255, 255));
		panel_DanhSachDH.setOpaque(false);
		add(panel_DanhSachDH, BorderLayout.CENTER);
		panel_DanhSachDH.setLayout(new BorderLayout(0, 0));
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setBackground(new Color(224, 255, 255));
		
		panel_DanhSachDH.add(scrollPane, BorderLayout.CENTER);

		loadDonHang();
		customizeTable1(tableOrder);
		addEvent();
	}

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



	public void addEvent(){
		panel_TimKiemBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, panelTimKiem, "Export",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					loadDonHang();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_TimKiemBtn.setBackground(new Color(169, 163, 87));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_TimKiemBtn.setBackground(new Color(189, 183, 107));
			}
		});
		
		panel_PDF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ChiTietBC_Dialog(null).setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_PDF.setBackground(new Color(199, 92, 127));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_PDF.setBackground(new Color(219, 112, 147));
			}
		});

		panel_Excel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new ExportExcelReport().exportExcel();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_Excel.setBackground(new Color(123, 168, 123));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_Excel.setBackground(new Color(143, 188, 143));
			}
		});



		tableOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableOrder.rowAtPoint(e.getPoint());
				if(e.getClickCount() == 2){
					new ChiTietDH_Dialog(null, (int)tableOrder.getValueAt(row, 0)).setVisible(true);;
				}				
			}
		});
			}

	public static PanelTimThK getPanelTimkiem(){
		return panelTimKiem;
	}
	
	private void loadDonHang() {
		
		DSHoaDon = SearchFilterBLL.timKiem_SapXepDonHang(panelTimKiem.filter());
		tableModel.setRowCount(0);
		double tongTien_ = 0;
		for (DonHangDTO hd : DSHoaDon) {
			tongTien_ += hd.getTongTien();
			tableModel.addRow(new Object[] {
				hd.getMaDH(),
				hd.getMaNV(),
				hd.getPtThanhToan(),
				TienIch.formatVND(hd.getTongTien()),
				TienIch.ddmmyyyy(hd.getNgayTT())
			});
		}
		lbl_TongTien.setText(tongTien_ + " VND");		
		soDonHang = DSHoaDon.size();
		danhThu = tongTien_;
		lbl_DH.setText(DSHoaDon.size()+ "");
    }

	public static int getSoDonhang(){
		return soDonHang;
	}
	public static double getDanhThu(){
		return danhThu;
	}
	public static ArrayList<DonHangDTO> getDanhSachDonHang(){
		return DSHoaDon;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Danh sách sản phẩm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 500);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(new BaoCaoPanel(), BorderLayout.CENTER);
		frame.setVisible(true);
	}



}
