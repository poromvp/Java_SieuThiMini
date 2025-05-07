package GUI.KhuyenMai;

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





import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import BLL.LoaiSanPhamBLL;
import BLL.SanPhamBLL;
import DTO.LoaiSanPhamDTO;
import DTO.SanPhamDTO;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class TimSanPham_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNhpIdOr = new JTextField();

	private static String header[] = {"Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Giá"};
	private static DefaultTableModel tableModel = new DefaultTableModel(header, 0);
	private static JTable tableSanPham = new JTable(tableModel);
	private JScrollPane scrollPane = new JScrollPane(tableSanPham);
	JComboBox comboBox = new JComboBox(new String[] {"tất cả"});
	JSpinner spinner_GiaMin = new JSpinner();
	JSpinner spinner_GiaMax = new JSpinner();
	JButton okButton = new JButton("THÊM");
	JButton cancelButton = new JButton("Cancel");
	JSpinner spinner_tiLe = new JSpinner();






	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TimSanPham_Dialog dialog = new TimSanPham_Dialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TimSanPham_Dialog(JFrame parent) {
		super(parent, "Tìm sản phẩm", true);

		setBounds(100, 100, 698, 704);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(10, 10));
		{
			{
				{
						LoaiSanPhamBLL loaiBLL = new LoaiSanPhamBLL();
					ArrayList<LoaiSanPhamDTO> dsLoaiSP = loaiBLL.getList();
					for(LoaiSanPhamDTO loai : dsLoaiSP){
						comboBox.addItem(loai.getTenLoaiSP());
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	
		{
			JPanel panel_Body = new JPanel();
			panel_Body.setBorder(new EmptyBorder(15, 15, 15, 15));
			panel_Body.setBackground(new Color(204, 255, 255));
			contentPanel.add(panel_Body, BorderLayout.SOUTH);
			panel_Body.setLayout(new BorderLayout(10,10));
			{
				JPanel panel_2 = new JPanel();
				panel_Body.add(panel_2);
				panel_2.setLayout(new BorderLayout(10, 10));
				{
					panel_2.add(scrollPane);
				}
				{
					JPanel panel_1_1 = new JPanel();
					panel_2.add(panel_1_1, BorderLayout.NORTH);
					{
						JLabel lblNewLabel_4 = new JLabel("Nhập tỉ lệ giảm");
						lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 14));
						lblNewLabel_4.setPreferredSize(new Dimension(150, 25));
						panel_1_1.add(lblNewLabel_4);
					}
					{
						spinner_tiLe.setFont(new Font("Arial", Font.BOLD, 14));
						spinner_tiLe.setPreferredSize(new Dimension(100, 30));
						spinner_tiLe.setModel(new SpinnerNumberModel(Float.valueOf(10), Float.valueOf(1), Float.valueOf(100), Float.valueOf(1)));
						panel_1_1.add(spinner_tiLe);
					}
				}
			}
		
			{
				JPanel panel = new JPanel();
				panel_Body.add(panel, BorderLayout.NORTH);
				panel.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel_5 = new JLabel("Thêm chi tiết khuyến mãi");
					lblNewLabel_5.setForeground(new Color(255, 255, 255));
					lblNewLabel_5.setOpaque(true);
					lblNewLabel_5.setBackground(new Color(0, 102, 153));
					panel.add(lblNewLabel_5, BorderLayout.NORTH);
					lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 20));
					lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel_5.setMaximumSize(new Dimension(286, 13));
					lblNewLabel_5.setPreferredSize(new Dimension(86, 50));
				}
				JPanel panel_input = new JPanel();
				panel.add(panel_input);
				panel_input.setPreferredSize(new Dimension(10, 60));
				panel_input.setLayout(new GridLayout(0, 4, 10, 10));
				{
					JPanel panel_1 = new JPanel();
					panel_input.add(panel_1);
					panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
					{
						JLabel lblNewLabel = new JLabel("ID/ Tên sản phẩm");
						lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
						lblNewLabel.setPreferredSize(new Dimension(81, 25));
						panel_1.add(lblNewLabel);
					}
					{
						txtNhpIdOr = new JTextField();
						txtNhpIdOr.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
						panel_1.add(txtNhpIdOr);
						txtNhpIdOr.setColumns(10);
					}
				}
				JPanel panel_12 = new JPanel();
				panel_input.add(panel_12);
				panel_12.setLayout(new BoxLayout(panel_12, BoxLayout.Y_AXIS));
				{
					JLabel lblNewLabel_1 = new JLabel("Loại sản phẩm");
					lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
					lblNewLabel_1.setMinimumSize(new Dimension(2243, 13));
					lblNewLabel_1.setMaximumSize(new Dimension(2243, 13));
					lblNewLabel_1.setPreferredSize(new Dimension(22222, 25));
					panel_12.add(lblNewLabel_1);
				}
				panel_12.add(comboBox);
				{
					JPanel panel_13 = new JPanel();
					panel_input.add(panel_13);
					panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.Y_AXIS));
					{
						JLabel lblNewLabel_2 = new JLabel("Giá MIN");
						lblNewLabel_2.setMinimumSize(new Dimension(2236, 13));
						lblNewLabel_2.setMaximumSize(new Dimension(236, 13));
						lblNewLabel_2.setPreferredSize(new Dimension(236, 25));
						lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
						panel_13.add(lblNewLabel_2);
					}
					{
						spinner_GiaMin.setModel(new SpinnerNumberModel(10000, 0, 1000000000, 1000));
						spinner_GiaMin.setFont(new Font("Arial", Font.BOLD, 14));
						panel_13.add(spinner_GiaMin);
					}
				}
				{
					JPanel panel_14 = new JPanel();
					panel_input.add(panel_14);
					panel_14.setLayout(new BoxLayout(panel_14, BoxLayout.Y_AXIS));
					{
						JLabel lblNewLabel_3 = new JLabel("Giá MAX");
						lblNewLabel_3.setMinimumSize(new Dimension(2238, 13));
						lblNewLabel_3.setMaximumSize(new Dimension(2238, 13));
						lblNewLabel_3.setPreferredSize(new Dimension(238, 25));
						panel_14.add(lblNewLabel_3);
					}
					{
						spinner_GiaMax.setModel(new SpinnerNumberModel(500000, 1000, 1000000000, 1000));
						spinner_GiaMax.setFont(new Font("Arial", Font.BOLD, 14));
						panel_14.add(spinner_GiaMax);
					}
				}
			}
		}
		LoadSanPhamTimKiem();
		addEvent();
		customizeTable1(tableSanPham);
	}

	public  void LoadSanPhamTimKiem(){

		String idOrTenSP = txtNhpIdOr.getText();
		String idOrTenLoaiSP = (String) comboBox.getSelectedItem();
		Object giaMinObject = spinner_GiaMin.getValue();
		int giaMin = (Integer)giaMinObject;
		Object giaMaxObject = spinner_GiaMax.getValue();
		int giaMax = (Integer)giaMaxObject;

		ArrayList<SanPhamDTO> dsSP = SanPhamBLL.timKiemSanPham(idOrTenSP, idOrTenLoaiSP, giaMin, giaMax);
		tableModel.setRowCount(0);
		for(SanPhamDTO sp : dsSP){
			LoaiSanPhamBLL lsp = new LoaiSanPhamBLL();
			tableModel.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), lsp.getLoaiSanPham(sp.getMaLSP()).getTenLoaiSP(), sp.getGia()});	
		}
	}

	public  void addEvent(){
		txtNhpIdOr.getDocument().addDocumentListener(new DocumentListener() {
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
		comboBox.addActionListener(e ->{
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
		okButton.addActionListener(e->{
			int rowSelected = tableSanPham.getSelectedRow();
			if(rowSelected < 0){
				JOptionPane.showMessageDialog(null, "Hãy chọn 1 dòng trong bảng sản phẩm để thêm.");
			}else{
				ThemKhuyenMai_Dialog.themChiTietKhuyenMaiVaoBang(new Object[]{tableSanPham.getValueAt(rowSelected, 0),tableSanPham.getValueAt(rowSelected, 1), tableSanPham.getValueAt(rowSelected, 2), tableSanPham.getValueAt(rowSelected, 3), Double.parseDouble( spinner_tiLe.getValue().toString()),   Double.parseDouble(tableSanPham.getValueAt(rowSelected, 3).toString()) * (1 - Double.parseDouble( spinner_tiLe.getValue().toString()) /100) });
				// SuaKhuyenmai_Dialog.themChiTietKhuyenMaiVaoBang(new Object[]{tableSanPham.getValueAt(rowSelected, 0),tableSanPham.getValueAt(rowSelected, 1), tableSanPham.getValueAt(rowSelected, 2), tableSanPham.getValueAt(rowSelected, 3), Double.parseDouble( spinner_tiLe.getValue().toString()),   Double.parseDouble(tableSanPham.getValueAt(rowSelected, 3).toString()) * (1 - Double.parseDouble( spinner_tiLe.getValue().toString()) /100) });
			}
		});
		cancelButton.addActionListener(e->{
			dispose();
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



}
