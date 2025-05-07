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
import java.util.ArrayList;
import java.util.Date;


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import BLL.ChiTietKhuyenMaiBLL;
import BLL.KhuyenMaiBLL;
import BLL.LoaiSanPhamBLL;
import BLL.SanPhamBLL;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.KhuyenMaiDTO;
import DTO.LoaiSanPhamDTO;
import DTO.SanPhamDTO;

public class XemKhuyenMai_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNhpTnKhuyn;
	private  JDateChooser dateChooserKetThuc = new JDateChooser();
	private  JDateChooser dateChooserBatDau = new JDateChooser();
	private static String header[] = {"Mã sản phẩm","Tên sản phẩm", "Tên loại sản phẩm", "Giá", "Tỉ lệ giảm", "Thành tiền"};
	private static DefaultTableModel tableModel = new DefaultTableModel(header, 0);
	private static JTable tableKhuyenMai = new JTable(tableModel);
	private JScrollPane scrollPane = new JScrollPane(tableKhuyenMai);
	JButton cancelButton = new JButton("Cancel");
	private KhuyenMaiDTO KHUYENMAI = new KhuyenMaiDTO();
	private JTextField textField_trạngThai;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			XemKhuyenMai_Dialog dialog = new XemKhuyenMai_Dialog(null, 6);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public XemKhuyenMai_Dialog(JFrame parent , int MaKM) {
		super(parent, "Thêm khuyến mãi", true);
		KHUYENMAI = KhuyenMaiBLL.getDiscountById(MaKM);
		setBounds(100, 100, 907, 552);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(204, 255, 255));
			panel.setBorder(new EmptyBorder(15, 15, 15, 15));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
				panel_1.add(Box.createHorizontalGlue());
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					panel_1.add(scrollPane);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton.setBackground(new Color(255, 153, 153));
				cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(e ->{
					dispose();
				});
			}
		}
	
		customizeTable1(tableKhuyenMai);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblNewLabel_4 = new JLabel("Chi tiết khuyến mãi");
				lblNewLabel_4.setForeground(new Color(255, 255, 255));
				lblNewLabel_4.setBackground(new Color(0, 102, 153));
				lblNewLabel_4.setOpaque(true);
				lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
				lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_4.setPreferredSize(new Dimension(81, 50));
				panel.add(lblNewLabel_4, BorderLayout.NORTH);
			}
			{
				JPanel panel_2 = new JPanel();
				panel_2.setBackground(new Color(204, 255, 255));
				panel_2.setBorder(new EmptyBorder(15, 15, 0, 15));
				panel.add(panel_2);
				panel_2.setPreferredSize(new Dimension(10, 75));
				panel_2.setLayout(new GridLayout(0, 4, 10, 0));
				{
					JPanel panel_1 = new JPanel();
					panel_2.add(panel_1);
					panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
					{
						JLabel lblNewLabel = new JLabel("Nhập tên khuyến mãi");
						lblNewLabel.setPreferredSize(new Dimension(97, 25));
						lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
						panel_1.add(lblNewLabel);
					}
					{
						txtNhpTnKhuyn = new JTextField();
						txtNhpTnKhuyn.setFocusable(false);
						txtNhpTnKhuyn.setText(KHUYENMAI.getTenKM());
						txtNhpTnKhuyn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
						panel_1.add(txtNhpTnKhuyn);
						txtNhpTnKhuyn.setColumns(10);
					}
				}
				{
					JPanel panel_1 = new JPanel();
					panel_2.add(panel_1);
					panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
					{
						JLabel lblNewLabel_1 = new JLabel("Ngày bắt đầu");
						lblNewLabel_1.setMinimumSize(new Dimension(2261, 13));
						lblNewLabel_1.setPreferredSize(new Dimension(61, 25));
						lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
						panel_1.add(lblNewLabel_1);
					}
					{
						dateChooserBatDau.getCalendarButton().setPreferredSize(new Dimension(30, 17));
						dateChooserBatDau.setFocusable(false);
						dateChooserBatDau.setFont(new Font("Arial", Font.BOLD, 15));
						panel_1.add(dateChooserBatDau);
						dateChooserKetThuc.getCalendarButton().setPreferredSize(new Dimension(30, 17));
						dateChooserKetThuc.setFocusable(false);
						dateChooserKetThuc.setDate(KHUYENMAI.getNgayBD());
						JTextField editor = ((JTextField) dateChooserBatDau.getDateEditor().getUiComponent());
						editor.setEditable(false);

					}
				}
				{
					JPanel panel_1 = new JPanel();
					panel_2.add(panel_1);
					panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
					{
						JLabel lblNewLabel_2 = new JLabel("Ngày kết thúc");
						lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
						lblNewLabel_2.setMinimumSize(new Dimension(2264, 13));
						lblNewLabel_2.setPreferredSize(new Dimension(2264, 25));
						panel_1.add(lblNewLabel_2);
					}
					{
						dateChooserKetThuc.setFont(new Font("Arial", Font.BOLD, 15));
						panel_1.add(dateChooserKetThuc);
						dateChooserBatDau.setDate(KHUYENMAI.getNgayKT());
						JTextField editor = ((JTextField) dateChooserKetThuc.getDateEditor().getUiComponent());
						editor.setEditable(false);
					}
				}
				{
					JPanel panel_1 = new JPanel();
					panel_2.add(panel_1);
					panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
					{
						JLabel lblNewLabel_3 = new JLabel("Trạng thái");
						lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
						lblNewLabel_3.setMaximumSize(new Dimension(2246, 25));
						lblNewLabel_3.setMinimumSize(new Dimension(2246, 13));
						lblNewLabel_3.setPreferredSize(new Dimension(2246, 25));
						panel_1.add(lblNewLabel_3);
					}
					{
						textField_trạngThai = new JTextField();
						textField_trạngThai.setHorizontalAlignment(SwingConstants.CENTER);
						textField_trạngThai.setText(KHUYENMAI.getTrangThai());
						textField_trạngThai.setFocusable(false);
						textField_trạngThai.setFont(new Font("Arial", Font.BOLD, 14));
						panel_1.add(textField_trạngThai);
						textField_trạngThai.setColumns(10);
					}
				}
			}
		}
	// private static String header[] = {"Mã sản phẩm","Tên sản phẩm", "Tên loại sản phẩm", "Giá", "Tỉ lệ giảm", "Thành tiền"};

		// load ds sản phảm khuýen mãi
		tableModel.setRowCount(0);
		ArrayList<ChiTietKhuyenMaiDTO> dsCTKM =  ChiTietKhuyenMaiBLL.getDiscountDetailsByDiscountId(MaKM);
		for(ChiTietKhuyenMaiDTO ctkm :  dsCTKM){
			LoaiSanPhamBLL lspBLL = new LoaiSanPhamBLL();
			SanPhamBLL spBLL = new SanPhamBLL();
			SanPhamDTO sp = spBLL.getProductById(ctkm.getMaSP());
			LoaiSanPhamDTO lsp = lspBLL.getLoaiSanPham(sp.getMaLSP());
			tableModel.addRow(new Object[] {ctkm.getMaSP(), sp.getTenSP(), lsp.getTenLoaiSP(), sp.getGia(),ctkm.getTiLeGiam(), sp.getGia() *(1 - (double)ctkm.getTiLeGiam()/100)});
		}
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


}


