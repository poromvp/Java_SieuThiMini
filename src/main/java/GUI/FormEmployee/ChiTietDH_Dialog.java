package GUI.FormEmployee;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BLL.ChiTietDonHangBLL;
import BLL.ChiTietKhuyenMaiBLL;
import BLL.DiemTichLuyBLL;
import BLL.DonHangBLL;
import BLL.KhuyenMaiBLL;
import BLL.NhanVienBLL;
import BLL.SanPhamBLL;
import BLL.TheThanhVienBLL;
import DTO.ChiTietDonHangDTO;
import DTO.ChiTietKhuyenMaiDTO;
import DTO.DiemTichLuyDTO;
import DTO.DonHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.SanPhamDTO;
import DTO.TheThanhVienDTO;



public class ChiTietDH_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DonHangDTO DONHANG = new DonHangDTO(1, null, 1, 1, "BANK", "2005-04-27", 1,1, 1, "FINISHED");
    private String[] HEADER = {"Mã SP", "Tên SP", "Giá", "Giảm giá", "Số lượng", "thành tiền"};
	private DefaultTableModel tableModel_SP = new DefaultTableModel(HEADER, 0);
	private JTable tableProduct = new JTable(tableModel_SP);
    // private JScrollPane scrollPane_SP = new JScrollPane(tableProduct);
  	JTableHeader tableHeader = tableProduct.getTableHeader();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			ChiTietDH_Dialog dialog = new ChiTietDH_Dialog(null, 5);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChiTietDH_Dialog(JFrame parent, int maDH) {
		super(parent, "Xem chi tiết đơn hàng !", true); 

		DONHANG = DonHangBLL.getOrderById(maDH);

		setBounds(100, 100, 976, 652);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel_LogoTitle = new JPanel();
			panel_LogoTitle.setBorder(new EmptyBorder(20, 20, 20, 20));
			contentPanel.add(panel_LogoTitle, BorderLayout.NORTH);
			panel_LogoTitle.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_logo = new JPanel();
				panel_logo.setPreferredSize(new Dimension(150, 150));
				panel_logo.setMaximumSize(new Dimension(200, 200));
				panel_LogoTitle.add(panel_logo, BorderLayout.WEST);
				panel_logo.setLayout(new BorderLayout(0, 0));
				{
					JLabel lbl_logo = new JLabel("New label");
					ImageIcon imageIcon = new ImageIcon( "src/main/resources/images/icon/Logo_Main.png");
					Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH); 
					lbl_logo.setIcon(new ImageIcon(image));

					panel_logo.add(lbl_logo);
				}
			}
			{
				JPanel panel_title = new JPanel();
				panel_LogoTitle.add(panel_title, BorderLayout.CENTER);
				panel_title.setLayout(new BoxLayout(panel_title, BoxLayout.Y_AXIS));
				{
					panel_title.add(Box.createVerticalStrut(50));

					JLabel lbl_title = new JLabel("SIEU THI MINI SGU");
					lbl_title.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 30));
					panel_title.add(lbl_title);
				}
				{
					JLabel lbl_slogan = new JLabel("Chất lượng trong từng lựa chọn!");
					lbl_slogan.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
					panel_title.add(lbl_slogan);
				}
			}
		}
		{
			JPanel panel_body = new JPanel();
			panel_body.setBorder(new EmptyBorder(20, 20, 20, 20));
			contentPanel.add(panel_body, BorderLayout.CENTER);
			panel_body.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_Infor = new JPanel();
				panel_body.add(panel_Infor, BorderLayout.NORTH);
				panel_Infor.setLayout(new BoxLayout(panel_Infor, BoxLayout.Y_AXIS));
				{
					JPanel panel_tieuDe = new JPanel();
					panel_Infor.add(panel_tieuDe);
					panel_tieuDe.setLayout(new BorderLayout(0, 0));
					{
						JLabel lbl_TieuDe = new JLabel("CHI TIẾT ĐƠN HÀNG");
						panel_tieuDe.add(lbl_TieuDe);
						lbl_TieuDe.setMinimumSize(new Dimension(2299, 13));
						lbl_TieuDe.setMaximumSize(new Dimension(2299, 213));
						lbl_TieuDe.setHorizontalAlignment(SwingConstants.CENTER);
						lbl_TieuDe.setPreferredSize(new Dimension(99, 70));
						lbl_TieuDe.setFont(new Font("Arial", Font.BOLD, 25));
					}
				}
				{
					JPanel panel_Item1 = new JPanel();
					panel_Item1.setMaximumSize(new Dimension(32767, 25));
					panel_Infor.add(panel_Item1);
					panel_Item1.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lbl_MaDH = new JLabel("Mã đơn hàng : " + DONHANG.getMaDH());
						panel_Item1.add(lbl_MaDH);
						lbl_MaDH.setFont(new Font("Arial", Font.BOLD, 14));
						lbl_MaDH.setPreferredSize(new Dimension(270, 25));
						lbl_MaDH.setMinimumSize(new Dimension(270, 25));
					}
					{
						JLabel lblNewLabel = new JLabel("Ngày thanh toán : " + DONHANG.getNgayTT());
						lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
						lblNewLabel.setMaximumSize(new Dimension(76, 25));
						lblNewLabel.setPreferredSize(new Dimension(76, 25));
						panel_Item1.add(lblNewLabel);
					}
				}
				{
					JPanel panel_item2 = new JPanel();
					panel_item2.setPreferredSize(new Dimension(10, 25));
					panel_Infor.add(panel_item2);
					panel_item2.setLayout(new GridLayout(0, 2, 0, 0));
					{
						NhanVienBLL nvBLL = new NhanVienBLL();
						JLabel lbl_tenNV = new JLabel("Tên nhân viên : " + nvBLL.getNhanVienByMa(DONHANG.getMaNV() + "").getTenNV());
						lbl_tenNV.setFont(new Font("Arial", Font.BOLD, 14));
						lbl_tenNV.setMaximumSize(new Dimension(75, 25));
						lbl_tenNV.setPreferredSize(new Dimension(75, 25));
						panel_item2.add(lbl_tenNV);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("Mã nhân viên : " + DONHANG.getMaNV());
						lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
						lblNewLabel_1.setMaximumSize(new Dimension(71, 25));
						lblNewLabel_1.setPreferredSize(new Dimension(271, 25));
						panel_item2.add(lblNewLabel_1);
					}
				}
				{
					JPanel panel_item3 = new JPanel();
					panel_item3.setPreferredSize(new Dimension(10, 25));
					panel_Infor.add(panel_item3);
					panel_item3.setLayout(new GridLayout(0, 2, 0, 0));
					TheThanhVienDTO tv = TheThanhVienBLL.getMemberById(DONHANG.getMaKH());
					{
						JLabel lbl_tenKH = new JLabel("Tên khách hàng : " + (tv == null ? "null" : tv.getTenTV()));
						lbl_tenKH.setFont(new Font("Arial", Font.BOLD, 14));
						panel_item3.add(lbl_tenKH);
					}
					{
						JLabel lblNewLabel_3 = new JLabel("Mã khách hàng : " + (tv == null ? "null" : tv.getMaTV()));
						lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
						panel_item3.add(lblNewLabel_3);
					}
				}
				{
					JPanel panel = new JPanel();
					panel.setPreferredSize(new Dimension(10, 25));
					panel_Infor.add(panel);
					panel.setLayout(new GridLayout(0, 2, 0, 0));
					KhuyenMaiDTO km = KhuyenMaiBLL.getDiscountById(DONHANG.getMaKM());
					{
						JLabel lblNewLabel_2 = new JLabel("Tên khuyến mãi : \r\n" + (km == null ? "null" : km.getTenKM()));
						lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
						panel.add(lblNewLabel_2);
					}
					{
						JLabel lblNewLabel_4 = new JLabel("Mã khuyến mãi : " + (km == null ? "null" : km.getMaKM()));
						lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 14));
						panel.add(lblNewLabel_4);
					}
				}
				{
					JPanel panel = new JPanel();
					panel.setPreferredSize(new Dimension(10, 25));
					panel_Infor.add(panel);
					panel.setLayout(new GridLayout(0, 2, 0, 0));
					DiemTichLuyDTO dtl = DiemTichLuyBLL.getDiemTichLuyById(DONHANG.getMaDTL());
					{
						JLabel lblNewLabel_5 = new JLabel("Số điểm tích luỹ : " + (dtl == null ? "null" : dtl.getDiemTL()));
						lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 14));
						panel.add(lblNewLabel_5);
					}
					{
						JLabel lblNewLabel_6 = new JLabel("Tỉ lệ giảm (của ĐTL) : " +  (dtl == null ? "null" : dtl.getTiLeGiam()) + " %");
						lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 14));
						panel.add(lblNewLabel_6);
					}
				}
				{
					JPanel panel = new JPanel();
					panel.setPreferredSize(new Dimension(10, 25));
					panel_Infor.add(panel);
					panel.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lblNewLabel_7 = new JLabel("Phương thức thanh toán : " + DONHANG.getPtThanhToan());
						lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 14));
						panel.add(lblNewLabel_7);
					}
					{
						JLabel lblNewLabel_8 = new JLabel("Tiền khách đưa/ chuyển : " + (DONHANG.getTienKD() == -1 ? 0 : DONHANG.getTienKD()) + " VNĐ");
						lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 14));
						panel.add(lblNewLabel_8);
					}
				}
				{
					JPanel panel = new JPanel();
					panel.setPreferredSize(new Dimension(10, 25));
					panel_Infor.add(panel);
					panel.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lblNewLabel_9 = new JLabel("Tổng tiền : " + DONHANG.getTongTien() + " VNĐ");
						lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 14));
						panel.add(lblNewLabel_9);
					}
					{
						JLabel lblNewLabel_10 = new JLabel("Trạng thái thanh toán : Đã thanh toán");
						lblNewLabel_10.setFont(new Font("Arial", Font.BOLD, 14));
						panel.add(lblNewLabel_10);
					}
				}
			}
			{

				JPanel panel_DanhSachSP = new JPanel();
				panel_DanhSachSP.setBorder(new EmptyBorder(20, 0, 0, 0));
				panel_body.add(panel_DanhSachSP, BorderLayout.CENTER);
				panel_DanhSachSP.setLayout(new BorderLayout(0, 0));
				{
					panel_DanhSachSP.add(tableProduct.getTableHeader(), BorderLayout.NORTH);
					panel_DanhSachSP.add(tableProduct, BorderLayout.CENTER);

					ArrayList<ChiTietDonHangDTO> dsCTDH = ChiTietDonHangBLL.getChiTietByMaDH(DONHANG.getMaDH());
					for(ChiTietDonHangDTO ctdh : dsCTDH){
						SanPhamDTO sanPham = SanPhamBLL.getProductById(ctdh.getMaSP());
						ChiTietKhuyenMaiDTO ctkm = ChiTietKhuyenMaiBLL.getDiscountDetail(ctdh.getMaDH(), ctdh.getMaSP());
						double tiLe = ctkm == null ? 0.0 : ctkm.getTiLeGiam();
						tableModel_SP.addRow(new Object[]{
							ctdh.getMaSP(), 
							sanPham.getTenSP(),
							sanPham.getGia(),
							tiLe,
							ctdh.getSoLuong(), 
							sanPham.getGia()*( 1 - tiLe/100)*ctdh.getSoLuong()
						});
					}
					customizeTable1(tableProduct);
				}
			}
		}
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("IN HOÁ ĐƠN");
				okButton.setBackground(new Color(51, 255, 102));
				okButton.setActionCommand("In Hoá Dơn");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(e->{
					int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn IN HOÁ ĐƠN  không ???", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(confirm == JOptionPane.YES_OPTION){
						exportPDF();
					}
				});
			}
			
		}
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

	private void exportPDF() {
		try {
            // Đổi Look & Feel sang hệ điều hành
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Chọn nơi lưu báo cáo và đặt tên file");
		chooser.setSelectedFile(new File("DonHang.pdf"));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int result = chooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();

			// Nếu người dùng không nhập đuôi .pdf, thêm vào
			if (!selectedFile.getName().toLowerCase().endsWith(".pdf")) {
				selectedFile = new File(selectedFile.getAbsolutePath() + ".pdf");
			}

			System.out.println("File sẽ lưu: " + selectedFile.getAbsolutePath());
			try {
				// Tạo document mới
				com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        		com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
        		document.open();
		
				// Font hỗ trợ tiếng Việt
				String fontPath = "src/main/resources/fonts/arial.ttf";
				com.itextpdf.text.pdf.BaseFont baseFont = com.itextpdf.text.pdf.BaseFont.createFont(
						fontPath, com.itextpdf.text.pdf.BaseFont.IDENTITY_H, com.itextpdf.text.pdf.BaseFont.EMBEDDED);
				com.itextpdf.text.Font fontNormal = new com.itextpdf.text.Font(baseFont, 12);
				com.itextpdf.text.Font fontBold = new com.itextpdf.text.Font(baseFont, 12, com.itextpdf.text.Font.BOLD);
				com.itextpdf.text.Font fontTitle = new com.itextpdf.text.Font(baseFont, 22, com.itextpdf.text.Font.BOLD);
				com.itextpdf.text.Font fontSubtitle = new com.itextpdf.text.Font(baseFont, 14, com.itextpdf.text.Font.ITALIC);
				com.itextpdf.text.Font fontHeader = new com.itextpdf.text.Font(baseFont, 12, com.itextpdf.text.Font.BOLD, com.itextpdf.text.BaseColor.WHITE);
	
				// Logo
				String imagePath = "src/main/resources/images/icon/Logo_Main.png";
				com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(imagePath);
				logo.scaleToFit(100, 100);
				logo.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
				document.add(logo);
		
				// Tiêu đề
				com.itextpdf.text.Paragraph title = new com.itextpdf.text.Paragraph("SIÊU THỊ MINI SGU \n", fontTitle);
				title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
				document.add(title);
		
				com.itextpdf.text.Paragraph subtitle = new com.itextpdf.text.Paragraph("Chất lượng trong từng lựa chọn!\n\n", fontSubtitle);
				subtitle.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
				document.add(subtitle);
		
				// Thêm tiêu đề phần chi tiết đơn hàng
				com.itextpdf.text.Paragraph subheading = new com.itextpdf.text.Paragraph("CHI TIẾT ĐƠN HÀNG\n\n", fontBold);
				subheading.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
				document.add(subheading);
		
				// Bảng thông tin đơn hàng 2 cột
				com.itextpdf.text.pdf.PdfPTable infoTable = new com.itextpdf.text.pdf.PdfPTable(2);
				infoTable.setWidthPercentage(100);
				infoTable.setSpacingBefore(10f);
				infoTable.setSpacingAfter(10f);
		
				// Các thông tin đơn hàng
				com.itextpdf.text.Phrase phrase_MaDH = new com.itextpdf.text.Phrase("Mã đơn hàng : " + DONHANG.getMaDH(), fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_MaDH = new com.itextpdf.text.pdf.PdfPCell(phrase_MaDH);
				cell_MaDH.setBorder(0);  
				infoTable.addCell(cell_MaDH);
	
				com.itextpdf.text.Phrase phrase_NgayTT = new com.itextpdf.text.Phrase("Ngày thanh toán : " + DONHANG.getNgayTT(), fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_NgayTT = new com.itextpdf.text.pdf.PdfPCell(phrase_NgayTT);
				cell_NgayTT.setBorder(0);  
				infoTable.addCell(cell_NgayTT);
	
				NhanVienBLL nvBLL = new NhanVienBLL();
				com.itextpdf.text.Phrase phrase_TenNV = new com.itextpdf.text.Phrase("Tên nhân viên : "  + nvBLL.getNhanVienByMa(DONHANG.getMaNV() + "").getTenNV(), fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_TenNV = new com.itextpdf.text.pdf.PdfPCell(phrase_TenNV);
				cell_TenNV.setBorder(0);  
				infoTable.addCell(cell_TenNV);
	
				com.itextpdf.text.Phrase phrase_MaNV = new com.itextpdf.text.Phrase("Mã nhân viên : " + DONHANG.getMaNV(), fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_MaNV = new com.itextpdf.text.pdf.PdfPCell(phrase_MaNV);
				cell_MaNV.setBorder(0);  
				infoTable.addCell(cell_MaNV);
	
				TheThanhVienDTO tv = TheThanhVienBLL.getMemberById(DONHANG.getMaKH());
				com.itextpdf.text.Phrase phrase_TenKH = new com.itextpdf.text.Phrase("Tên khách hàng : " + (tv == null ? "null" : tv.getTenTV()), fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_TenKH = new com.itextpdf.text.pdf.PdfPCell(phrase_TenKH);
				cell_TenKH.setBorder(0);  
				infoTable.addCell(cell_TenKH);
	
				com.itextpdf.text.Phrase phrase_MaKH = new com.itextpdf.text.Phrase("Mã khách hàng : " + (tv == null ? "null" : tv.getMaTV()), fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_MaKH = new com.itextpdf.text.pdf.PdfPCell(phrase_MaKH);
				cell_MaKH.setBorder(0);  
				infoTable.addCell(cell_MaKH);
	
				KhuyenMaiDTO km = KhuyenMaiBLL.getDiscountById(DONHANG.getMaKM());
				com.itextpdf.text.Phrase phrase_TenKM = new com.itextpdf.text.Phrase("Tên khuyến mãi : " + (km == null ? "null" : km.getTenKM()) , fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_TenKM = new com.itextpdf.text.pdf.PdfPCell(phrase_TenKM);
				cell_TenKM.setBorder(0);  
				infoTable.addCell(cell_TenKM);
	
				com.itextpdf.text.Phrase phrase_MaKM = new com.itextpdf.text.Phrase("Mã khuyến mãi : " + (km == null ? "null" : km.getMaKM())  , fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_MaKM = new com.itextpdf.text.pdf.PdfPCell(phrase_MaKM);
				cell_MaKM.setBorder(0);  
				infoTable.addCell(cell_MaKM);
	
				DiemTichLuyDTO dtl = DiemTichLuyBLL.getDiemTichLuyById(DONHANG.getMaDTL());
				com.itextpdf.text.Phrase phrase_DTL = new com.itextpdf.text.Phrase("Số điểm tích lũy : " + (dtl == null ? "null" : dtl.getDiemTL()) , fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_DTL = new com.itextpdf.text.pdf.PdfPCell(phrase_DTL);
				cell_DTL.setBorder(0);  
				infoTable.addCell(cell_DTL);
	
				com.itextpdf.text.Phrase phrase_TLGiamDTL = new com.itextpdf.text.Phrase("Tỉ lệ giảm (ĐTL): " + (dtl == null ? "null" : dtl.getTiLeGiam()) + " %" , fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_TLGiamDTL = new com.itextpdf.text.pdf.PdfPCell(phrase_TLGiamDTL);
				cell_TLGiamDTL.setBorder(0);  
				infoTable.addCell(cell_TLGiamDTL);
	
				com.itextpdf.text.Phrase phrase_PTTT = new com.itextpdf.text.Phrase("Phương thức thanh toán : "  + DONHANG.getPtThanhToan() , fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_PTTT = new com.itextpdf.text.pdf.PdfPCell(phrase_PTTT);
				cell_PTTT.setBorder(0);  
				infoTable.addCell(cell_PTTT);
	
				com.itextpdf.text.Phrase phrase_TienKD = new com.itextpdf.text.Phrase("Tiền khách đưa/chuyển : " + (DONHANG.getTienKD() == -1 ? 0 : DONHANG.getTienKD()) + " VNĐ" , fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_TienKD = new com.itextpdf.text.pdf.PdfPCell(phrase_TienKD);
				cell_TienKD.setBorder(0);  
				infoTable.addCell(cell_TienKD);
	
				com.itextpdf.text.Phrase phrase_TongTien = new com.itextpdf.text.Phrase("Tổng tiền : " + String.format("%,.0f", (double)DONHANG.getTongTien()) + " VNĐ" , fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_TongTien = new com.itextpdf.text.pdf.PdfPCell(phrase_TongTien);
				cell_TongTien.setBorder(0);  
				infoTable.addCell(cell_TongTien);
	
				com.itextpdf.text.Phrase phrase_TrangThaiTT = new com.itextpdf.text.Phrase("Trạng thái thanh toán :  Đã thanh toán" , fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_TrangThaiTT = new com.itextpdf.text.pdf.PdfPCell(phrase_TrangThaiTT);
				cell_TrangThaiTT.setBorder(0);  
				infoTable.addCell(cell_TrangThaiTT);
	
				document.add(infoTable);
		
				// Tạo bảng chi tiết sản phẩm
				com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(6);
				table.setWidthPercentage(100);
				table.setWidths(new float[]{1.2f, 3f, 2f, 2f, 2f, 2.5f});
				table.setSpacingBefore(10f);
		
				// Header bảng
				String[] headers = {"Mã SP", "Tên SP", "Giá", "Giảm giá", "Số lượng", "Thành tiền"};
				for (String header : headers) {
					com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(header, fontHeader));
					cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
					cell.setBackgroundColor(new com.itextpdf.text.BaseColor(33,58,89)); // Màu xanh nhạt
					table.addCell(cell);
				}
	
		
				// Dữ liệu chi tiết đơn hàng
				java.util.ArrayList<ChiTietDonHangDTO> dsCTDH = ChiTietDonHangBLL.getChiTietByMaDH(DONHANG.getMaDH());
				for (ChiTietDonHangDTO ctdh : dsCTDH) {
					SanPhamDTO sp = SanPhamBLL.getProductById(ctdh.getMaSP());
					ChiTietKhuyenMaiDTO ctkm = ChiTietKhuyenMaiBLL.getDiscountDetail(ctdh.getMaDH(), ctdh.getMaSP());
		
					double tiLe = (ctkm != null) ? ctkm.getTiLeGiam() : 0.0;
					double thanhTien = sp.getGia() * (1 - tiLe / 100.0) * ctdh.getSoLuong();
		
					com.itextpdf.text.pdf.PdfPCell cell1 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(String.valueOf(ctdh.getMaSP()), fontNormal));
					cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					table.addCell(cell1);
	
					com.itextpdf.text.pdf.PdfPCell cell2 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(sp.getTenSP(), fontNormal));
					cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					table.addCell(cell2);
	
					com.itextpdf.text.pdf.PdfPCell cell3 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(String.format("%,.0f", sp.getGia()), fontNormal));
					cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					table.addCell(cell3);
	
					com.itextpdf.text.pdf.PdfPCell cell4 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(tiLe + " %", fontNormal));
					cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					table.addCell(cell4);
	
					com.itextpdf.text.pdf.PdfPCell cell5 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(String.valueOf(ctdh.getSoLuong()), fontNormal));
					cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					table.addCell(cell5);
	
					com.itextpdf.text.pdf.PdfPCell cell6 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(String.format("%,.0f", thanhTien), fontNormal));
					cell6.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					table.addCell(cell6);
	
				}
		
				document.add(table);
				document.close();
		
				System.out.println("Xuất PDF thành công!");
				JOptionPane.showMessageDialog(null, "XUẤT ĐƠN HÀNG thành công !!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (java.io.FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Không thể XUẤT ĐƠN HÀNG vì file đang được mở.\nVui lòng đóng file và thử lại.", "Lỗi File", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
		    // Trả về Look & Feel mặc định của Java (thường là Metal)
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    // Cập nhật lại UI trên frame (nếu cần)
		    SwingUtilities.updateComponentTreeUI(this); // yourFrame là JFrame hoặc JDialog
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
}
