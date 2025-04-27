package GUI.FormEmployee;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



import BLL.ChiTietKhuyenMaiBLL;
import BLL.SanPhamBLL;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.SanPhamDTO;

public class PanelTimSanPham extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTenOrIdSP;
	private JTextField txtTenOrIdLoaiSP;
	private JTable table;
	private JSpinner spinnerGiaMin;
	private JSpinner spinnerGiaMax;
	private DefaultTableModel model;

	public PanelTimSanPham() {
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		// Item 1: ID/Tên SP
		JPanel item1 = new JPanel();
		item1.setLayout(new BoxLayout(item1, BoxLayout.Y_AXIS));
		panel.add(item1);

		JLabel lblTenOrId = new JLabel("Id/Tên SP");
		lblTenOrId.setAlignmentX(Component.LEFT_ALIGNMENT);
		item1.add(lblTenOrId);

		txtTenOrIdSP = new JTextField(10);
		txtTenOrIdSP.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtTenOrIdSP.getPreferredSize().height));
		txtTenOrIdSP.setAlignmentX(Component.LEFT_ALIGNMENT);
		item1.add(txtTenOrIdSP);

		// Item 2: Loại SP
		JPanel item2 = new JPanel();
		item2.setLayout(new BoxLayout(item2, BoxLayout.Y_AXIS));
		panel.add(item2);

		JLabel lblLoaiSP = new JLabel("Loại SP");
		lblLoaiSP.setAlignmentX(Component.LEFT_ALIGNMENT);
		item2.add(lblLoaiSP);

		txtTenOrIdLoaiSP = new JTextField(10);
		txtTenOrIdLoaiSP.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtTenOrIdLoaiSP.getPreferredSize().height));
		txtTenOrIdLoaiSP.setAlignmentX(Component.LEFT_ALIGNMENT);
		item2.add(txtTenOrIdLoaiSP);

		// Item 3: Giá Min
		JPanel item3 = new JPanel();
		item3.setLayout(new BoxLayout(item3, BoxLayout.Y_AXIS));
		panel.add(item3);

		JLabel lblGiaMin = new JLabel("Giá Min");
		lblGiaMin.setAlignmentX(Component.LEFT_ALIGNMENT);
		item3.add(lblGiaMin);

		spinnerGiaMin = new JSpinner(new SpinnerNumberModel(10000, 1, 1000000, 1000));
		spinnerGiaMin.setMaximumSize(new Dimension(Integer.MAX_VALUE, spinnerGiaMin.getPreferredSize().height));
		spinnerGiaMin.setAlignmentX(Component.LEFT_ALIGNMENT);
		item3.add(spinnerGiaMin);

		// Item 4: Giá Max
		JPanel item4 = new JPanel();
		item4.setLayout(new BoxLayout(item4, BoxLayout.Y_AXIS));
		panel.add(item4);

		JLabel lblGiaMax = new JLabel("Giá Max");
		lblGiaMax.setAlignmentX(Component.LEFT_ALIGNMENT);
		item4.add(lblGiaMax);

		spinnerGiaMax = new JSpinner(new SpinnerNumberModel(100000, 1, 1000000, 1000));
		spinnerGiaMax.setMaximumSize(new Dimension(Integer.MAX_VALUE, spinnerGiaMax.getPreferredSize().height));
		spinnerGiaMax.setAlignmentX(Component.LEFT_ALIGNMENT);
		item4.add(spinnerGiaMax);

		// Item 5: Nút tìm
		JPanel item5 = new JPanel();
		item5.setLayout(new BoxLayout(item5, BoxLayout.Y_AXIS));
		panel.add(item5);

		
		
		JPanel panelTable = new JPanel();
		add(panelTable, BorderLayout.CENTER);
		panelTable.setLayout(new BorderLayout(0, 0));
		String[] header = {"Mã", "Tên", "Giá", "Khuyến mãi"};
		model = new DefaultTableModel(null, header);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		panelTable.add(scrollPane, BorderLayout.CENTER);
		// model.addRow(new Object[]{1, "fgfdk", 342, 12});

		customizeTable();
		AddEvent();

	}

	public  void LoadSanPhamTimKiem(){
		String idOrTenSP = txtTenOrIdSP.getText();
		String idOrTenLoaiSP = txtTenOrIdLoaiSP.getText();
		Object giaMinObject = spinnerGiaMin.getValue();
		int giaMin = (Integer)giaMinObject;
		Object giaMaxObject = spinnerGiaMax.getValue();
		int giaMax = (Integer)giaMaxObject;

		ArrayList<SanPhamDTO> dsSP = SanPhamBLL.timKiemSanPham(idOrTenSP, idOrTenLoaiSP, giaMin, giaMax);
		model.setRowCount(0);
		for(SanPhamDTO sp : dsSP){
			double ctkm = ChiTietKhuyenMaiBLL.getProductOnSaleToday(sp.getMaSP());

			model.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getGia(), ctkm});	
		}
	}

	public void AddEvent(){
		txtTenOrIdLoaiSP.getDocument().addDocumentListener(new DocumentListener() {
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

		txtTenOrIdSP.getDocument().addDocumentListener(new DocumentListener() {
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

		spinnerGiaMax.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				LoadSanPhamTimKiem();
			}
		});

		spinnerGiaMin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				LoadSanPhamTimKiem();
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Kiểm tra nếu là double-click
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					if (row != -1) {
						int maSP = (int) table.getValueAt(row, 0);  // Giả sử "Mã" nằm ở cột 0
						String tenSP = (String) table.getValueAt(row, 1); // Giả sử "Tên" nằm ở cột 1
						double gia = (double) table.getValueAt(row, 2); // Giả sử "Giá" nằm ở cột 2
						double rate = ChiTietKhuyenMaiBLL.getProductOnSaleToday(maSP);
					 	FormOrderDetailList.addProductDetail(new Object[]{maSP, tenSP, gia,rate, 1, gia*(1 - ChiTietKhuyenMaiBLL.getProductOnSaleToday(maSP)/100)});
					}
				}
			}
		});
		
	};

	
	private void customizeTable() {
		// Thiết lập font cho bảng và các cài đặt khác
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.getTableHeader().setBackground(new Color(17, 32, 51));
		table.getTableHeader().setForeground(Color.WHITE);
		table.setSelectionBackground(new Color(200, 230, 255));
		table.setSelectionForeground(Color.BLACK);
		table.setGridColor(new Color(220, 220, 220));
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
	
		// Đảm bảo bảng không thể chỉnh sửa
		table.setDefaultEditor(Object.class, null); // Không cho phép chỉnh sửa bất kỳ cột nào
		
		// Custom TableCellRenderer
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
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
		}
	}
	
	
	
		
    


	public static void main(String[] args) {
		JFrame frame = new JFrame("Danh sách sản phẩm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 200);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(new PanelTimSanPham(), BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
