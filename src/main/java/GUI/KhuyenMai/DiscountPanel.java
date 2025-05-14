package GUI.KhuyenMai;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import BLL.KhuyenMaiBLL;
import DTO.KhuyenMaiDTO;

import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.Component;

import DAL.KhuyenMaiDAL;

public class DiscountPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JTextField textField_tenOrIdKM = new JTextField();
	private static JTextField textField_IDorTenSP = new JTextField();
	
	private static String header[] = {"Mã khuyến mãi", "Tên khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"};
	private static DefaultTableModel tableModel = new DefaultTableModel(header, 0);
	private static JTable tableKhuyenMai = new JTable(tableModel);
	private JScrollPane scrollPane = new JScrollPane(tableKhuyenMai);
	private static JComboBox comboBox_trangThai = new JComboBox( new String[] {"ACTIVE", "INACTIVE", "tất cả"}) ;
	private static JDateChooser dateChooserKetThuc = new JDateChooser();
	private static JDateChooser dateChooserBatDau = new JDateChooser();
	private static	JComboBox comboBox_sapXep = new JComboBox(new String[]{"Tăng dần", "Giảm dần"});
	private static JComboBox comboBox_column = new JComboBox(new String[] {"Mã khuyến mãi", "Tên khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc"});
	private  JButton btnthem = new JButton("Thêm");
	private  JButton btnXem = new JButton("Xem");
	private  JButton btnSua = new JButton("Sửa");
	private  JButton btnXoa = new JButton("Xoá");





	/**
	 * Create the panel.
	 */
	public DiscountPanel() {
		dateChooserBatDau.setFont(new Font("Arial", Font.BOLD, 15));

	
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date ngayCuThe = sdf.parse("10/06/2024");
			Date ngayCuThe2 = sdf.parse("10/06/2026");
			dateChooserBatDau.setDate(ngayCuThe);
			dateChooserKetThuc.setDate(ngayCuThe2);
		} catch (ParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ!");
		}
		dateChooserKetThuc.getCalendarButton().setFont(new Font("Arial", Font.BOLD, 15));
		dateChooserKetThuc.setFont(new Font("Arial", Font.BOLD, 15));


		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(15, 15, 15, 15));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.white);
		panel_2.setPreferredSize(new Dimension(10, 120));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 4, 10, 10));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("ID/Tên khuyến mãi");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setPreferredSize(new Dimension(88, 25));
		panel_4.add(lblNewLabel);
		
		textField_tenOrIdKM = new JTextField();
		panel_4.add(textField_tenOrIdKM);
		textField_tenOrIdKM.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("Trạng thái");
		lblNewLabel_1.setMinimumSize(new Dimension(2246, 13));
		lblNewLabel_1.setMaximumSize(new Dimension(2546, 13));
		lblNewLabel_1.setPreferredSize(new Dimension(46, 25));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		panel_6.add(lblNewLabel_1);
		comboBox_trangThai.setFont(new Font("Arial", Font.BOLD, 14));
		
		panel_6.add(comboBox_trangThai);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_2 = new JLabel("ID/Tên sản phẩm");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_2.setPreferredSize(new Dimension(78, 25));
		panel_7.add(lblNewLabel_2);
		
		textField_IDorTenSP = new JTextField();
		panel_7.add(textField_IDorTenSP);
		textField_IDorTenSP.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(5, 5, 5, 5));
		// panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 5, 5));
		
		JButton btnExcel = new JButton("Excel");
		btnExcel.setBackground(new Color(0, 204, 0));
		btnExcel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		// panel_5.add(btnExcel);
		
		JButton btnPDF = new JButton("PDF");
		btnPDF.setBackground(new Color(255, 0, 51));
		btnPDF.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		// panel_5.add(btnPDF);
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_3 = new JLabel("Ngày bắt đầu");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3.setMaximumSize(new Dimension(2261, 25));
		lblNewLabel_3.setPreferredSize(new Dimension(61, 25));

		panel_8.add(lblNewLabel_3);
		panel_8.add(dateChooserBatDau);
		
		JPanel panel_9 = new JPanel();
		panel_2.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_4 = new JLabel("Ngày kết thúc");
		lblNewLabel_4.setMaximumSize(new Dimension(2264, 25));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_4.setPreferredSize(new Dimension(264, 25));

		panel_9.add(lblNewLabel_4);
		panel_9.add(dateChooserKetThuc);
		
		JPanel panel_18 = new JPanel();
		panel_2.add(panel_18);
		panel_18.setLayout(new BoxLayout(panel_18, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_5 = new JLabel("Sắp xếp");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_5.setMaximumSize(new Dimension(236, 13));
		lblNewLabel_5.setMinimumSize(new Dimension(2236, 13));
		lblNewLabel_5.setPreferredSize(new Dimension(2236, 25));
		panel_18.add(lblNewLabel_5);
		
		comboBox_sapXep.setFont(new Font("Arial", Font.BOLD, 14));
		panel_18.add(comboBox_sapXep);
		
		JPanel panel_19 = new JPanel();
		panel_2.add(panel_19);
		panel_19.setLayout(new BoxLayout(panel_19, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_6 = new JLabel("Xếp theo cột");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_6.setMaximumSize(new Dimension(257, 13));
		lblNewLabel_6.setMinimumSize(new Dimension(2257, 13));
		lblNewLabel_6.setPreferredSize(new Dimension(257, 25));
		panel_19.add(lblNewLabel_6);
		
		comboBox_column.setFont(new Font("Arial", Font.BOLD, 14));
		panel_19.add(comboBox_column);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.white);
		panel_3.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_3.setPreferredSize(new Dimension(180, 10));
		panel.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new GridLayout(0, 2, 10, 10));
		
		JPanel panel_14 = new JPanel();
		panel_3.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));
		btnthem.setBackground(new Color(0, 255, 102));
		btnthem.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		
		panel_14.add(btnthem);
		
		JPanel panel_13 = new JPanel();
		panel_3.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		btnXem.setBackground(new Color(0, 153, 153));
		btnXem.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		panel_13.add(btnXem);
		
		JPanel panel_15 = new JPanel();
		panel_3.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		btnXoa.setBackground(new Color(255, 102, 0));
		btnXoa.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		panel_15.add(btnXoa);
		
		JPanel panel_11 = new JPanel();
		panel_3.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		btnSua.setBackground(new Color(255, 204, 51));
		btnSua.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		panel_11.add(btnSua);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_17 = new JPanel();
		panel_17.setBackground(Color.white);
		panel_17.setBorder(new EmptyBorder(0, 15, 15, 15));
		panel_1.add(panel_17, BorderLayout.CENTER);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		panel_17.add(scrollPane);



		loadFilterKhuyenMai();
		addEvent();
		customizeTable1(tableKhuyenMai);
	}

	public static void loadFilterKhuyenMai(){
		tableModel.setRowCount(0);
		ArrayList<KhuyenMaiDTO> dsKM = KhuyenMaiBLL.getFilteredDiscounts(
			textField_tenOrIdKM.getText().trim(),
			comboBox_trangThai.getSelectedItem().toString().trim(),
			textField_IDorTenSP.getText().trim(),
			new java.sql.Date(dateChooserBatDau.getDate().getTime()),
			new java.sql.Date(dateChooserKetThuc.getDate().getTime()), 
			comboBox_sapXep.getSelectedItem().toString().trim(),
			comboBox_column.getSelectedItem().toString().trim()
			
		);
		
		for(KhuyenMaiDTO km : dsKM){
			tableModel.addRow(new Object[]{km.getMaKM(), km.getTenKM(), km.getNgayBD(), km.getNgayKT(),km.getTrangThai()});
		}

	}

	public  void addEvent(){
		textField_tenOrIdKM.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				loadFilterKhuyenMai(); 
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				loadFilterKhuyenMai();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				loadFilterKhuyenMai();
			}
		});

		textField_IDorTenSP.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				loadFilterKhuyenMai(); 
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				loadFilterKhuyenMai();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				loadFilterKhuyenMai();
			}
		});

		comboBox_trangThai.addActionListener(e ->{
			loadFilterKhuyenMai();
		});
		comboBox_sapXep.addActionListener(e ->{
			loadFilterKhuyenMai();
		});
		comboBox_column.addActionListener(e ->{
			loadFilterKhuyenMai();
		});

		dateChooserBatDau.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				loadFilterKhuyenMai(); 
			}
		});
		dateChooserKetThuc.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				loadFilterKhuyenMai(); 
			}
		});

		btnthem.addActionListener(e->{
			new ThemKhuyenMai_Dialog(null).setVisible(true);
		});
		btnXem.addActionListener(e->{
			xemChiTietKhuyenMai();
		});

		btnSua.addActionListener(e->{
			suaKhuyenMai();
		});
		btnXoa.addActionListener(e->{
			xoaKhuyenMai();
		});

	}

	
	private void customizeTable1(JTable talbe_) {
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
		talbe_.setDefaultEditor(Object.class, null); 
		
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


	public static void xemChiTietKhuyenMai(){
		int row = tableKhuyenMai.getSelectedRow();
		if(row < 0){
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 khuyến mãi để xem");
			return;
		}
		new XemKhuyenMai_Dialog(null, Integer.parseInt(tableKhuyenMai.getValueAt(row, 0).toString())).setVisible(true);;
	}

	public static void suaKhuyenMai(){
		int row = tableKhuyenMai.getSelectedRow();
		if(row < 0){
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 khuyến mãi để sửa");
			return;
		}
		new SuaKhuyenmai_Dialog(null, Integer.parseInt(tableKhuyenMai.getValueAt(row, 0).toString())).setVisible(true);;
	}

	public static void xoaKhuyenMai(){
		int row = tableKhuyenMai.getSelectedRow();
		if(row < 0){
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 khuyến mãi để xoá");
			return;
		}
		KhuyenMaiDTO km = KhuyenMaiBLL.getDiscountById(Integer.parseInt(tableKhuyenMai.getValueAt(row, 0).toString()));
		if(km.getTrangThai().equalsIgnoreCase("INACTIVE")){
			JOptionPane.showMessageDialog(null,"Khuyến mãi này đã được xoá từ trước.!!!");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(null,"Bạn có muốn xoá Khuyến mãi này không ??", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(confirm == JOptionPane.YES_OPTION){
			km.setTrangThai("INACTIVE");
			KhuyenMaiBLL.updateDiscount(km);
			JOptionPane.showMessageDialog(null,"Xoá khuyến mãi thành công !!!");
			loadFilterKhuyenMai();
		}
	}

	

	

	public static void main(String[] args) {
		JFrame frame = new JFrame("Danh sách sản phẩm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 700);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(new DiscountPanel(), BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
