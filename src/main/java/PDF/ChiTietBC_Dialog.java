package PDF;


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
import java.time.LocalDate;
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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BLL.DonHangBLL;
import BLL.NhanVienBLL;
import BLL.TheThanhVienBLL;
import DTO.DonHangDTO;
import DTO.NhanVienDTO;
import DTO.TheThanhVienDTO;
import GUI.FormEmployee.BaoCaoPanel;
import GUI.FormEmployee.ProfilePanel;



public class ChiTietBC_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
    private String[] HEADER = {"Mã DH", "Mã KM", "Mã KH", "PT thanh toán", "Ngày mua", "thành tiền"};
	private DefaultTableModel tableModel_SP = new DefaultTableModel(HEADER, 0);
	private JTable tableProduct = new JTable(tableModel_SP);
    // private JScrollPane scrollPane_SP = new JScrollPane(tableProduct);
  	JTableHeader tableHeader = tableProduct.getTableHeader();
	private ArrayList<DonHangDTO> DSHoaDon = DonHangBLL.getAllOrders();
	JTextArea txtrNhapNoiDung = new JTextArea();

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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			ChiTietBC_Dialog dialog = new ChiTietBC_Dialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChiTietBC_Dialog(JFrame parent) {
		super(parent, "Xem chi tiết báo cáo !", true); 

		NhanVienBLL nvBLL = new NhanVienBLL();
		NHANVIEN = nvBLL.getNhanVienByMa(ProfilePanel.getMaNhanVien() + "");
	
		setBounds(100, 100, 976, 706);
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
						JLabel lbl_TieuDe = new JLabel("BÁO CÁO BÁN HÀNG");
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
						JLabel lbl_MaDH = new JLabel("");
						panel_Item1.add(lbl_MaDH);
						lbl_MaDH.setFont(new Font("Arial", Font.BOLD, 14));
						lbl_MaDH.setPreferredSize(new Dimension(270, 25));
						lbl_MaDH.setMinimumSize(new Dimension(270, 25));
					}
					{
						LocalDate today = LocalDate.now();
						JLabel lblNewLabel = new JLabel("Ngày báo cáo : " + today);
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
						JLabel lbl_tenNV = new JLabel("Tên nhân viên : " + NHANVIEN.getTenNV());
						lbl_tenNV.setFont(new Font("Arial", Font.BOLD, 14));
						lbl_tenNV.setMaximumSize(new Dimension(75, 25));
						lbl_tenNV.setPreferredSize(new Dimension(75, 25));
						panel_item2.add(lbl_tenNV);
					}
					{
						JLabel lblNewLabel_1 = new JLabel("Mã nhân viên : " + NHANVIEN.getMaNV());
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
					{
						JLabel lbl_NgayBT = new JLabel("Thống kê từ ngày  : " + (BaoCaoPanel.getPanelTimkiem().getNgayBatDau() != null? BaoCaoPanel.getPanelTimkiem().getNgayBatDau(): " ngày thành lập"));
						lbl_NgayBT.setFont(new Font("Arial", Font.BOLD, 14));
						panel_item3.add(lbl_NgayBT);
					}
					{
						LocalDate today = LocalDate.now();
						JLabel lbl_NgayKT = new JLabel("Thống kê đến ngày : " +  (BaoCaoPanel.getPanelTimkiem().getNgayKetThuc() != null?BaoCaoPanel.getPanelTimkiem().getNgayKetThuc()  : today.toString()));
						lbl_NgayKT.setFont(new Font("Arial", Font.BOLD, 14));
						panel_item3.add(lbl_NgayKT);
					}
				}
				
				{
					JPanel panel = new JPanel();
					panel.setPreferredSize(new Dimension(10, 25));
					panel_Infor.add(panel);
					panel.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lblNewLabel_9 = new JLabel("Tổng đơn hàng : " + BaoCaoPanel.getSoDonhang() );
						lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 14));
						panel.add(lblNewLabel_9);
					}
					{
						JLabel lblNewLabel_10 = new JLabel("Tổng danh thu : " + BaoCaoPanel.getDanhThu() + " VND"); 
						lblNewLabel_10.setFont(new Font("Arial", Font.BOLD, 14));
						panel.add(lblNewLabel_10);
					}
				}
				{
					JPanel panel = new JPanel();
					panel.setPreferredSize(new Dimension(600, 150));
					panel_Infor.add(panel);
					panel.setLayout(new BorderLayout(0, 0));
					{
						JLabel lblNewLabel_7 = new JLabel("Nội dung thống kê : ");
						lblNewLabel_7.setMinimumSize(new Dimension(2222, 13));
						lblNewLabel_7.setMaximumSize(new Dimension(2296, 13));
						lblNewLabel_7.setPreferredSize(new Dimension(2296, 13));
						lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 14));
						panel.add(lblNewLabel_7);
					}
					{
						txtrNhapNoiDung.setLineWrap(true);
						txtrNhapNoiDung.setFont(new Font("Arial", Font.PLAIN, 14));
						txtrNhapNoiDung.setText("Nhập nội dung ...");
						txtrNhapNoiDung.setWrapStyleWord(true);
						JScrollPane ScrollPaneNoiDung = new JScrollPane(txtrNhapNoiDung);
						panel.add(ScrollPaneNoiDung);
					}
					{
						JLabel lblNewLabel_2 = new JLabel("Nội dung báo cáo :");
						lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
						panel.add(lblNewLabel_2, BorderLayout.NORTH);
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

					for(DonHangDTO hd : BaoCaoPanel.getDanhSachDonHang()){
						tableModel_SP.addRow(new Object[]{
							hd.getMaDH(), 
							hd.getMaKM(),
							hd.getMaKH(),
							hd.getPtThanhToan(),
							hd.getNgayTT(), 
							hd.getTongTien()
						});
					}
					customizeTable1(tableProduct);
				}
			}
			{
				JPanel panel_chuKy = new JPanel();
				panel_chuKy.setPreferredSize(new Dimension(10, 100));
				panel_body.add(panel_chuKy, BorderLayout.SOUTH);
				panel_chuKy.setLayout(new GridLayout(1, 0, 0, 0));
				{
					JPanel panel = new JPanel();
					panel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
					panel_chuKy.add(panel);
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
					{
						JLabel lblNewLabel_3 = new JLabel("Người làm báo cáo");
						lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
						lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_3.setMaximumSize(new Dimension(2285, 25));
						lblNewLabel_3.setPreferredSize(new Dimension(2285, 25));
						panel.add(lblNewLabel_3);
					}
					{
						JLabel lblNewLabel_4 = new JLabel("Ký và ghi rõ họ tên");
						lblNewLabel_4.setMaximumSize(new Dimension(2288, 13));
						lblNewLabel_4.setPreferredSize(new Dimension(2288, 13));
						lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 12));
						panel.add(lblNewLabel_4);
					}
				}
				{
					JPanel panel = new JPanel();
					panel_chuKy.add(panel);
				}
				{
					JPanel panel = new JPanel();
					panel_chuKy.add(panel);
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
					{
						JLabel lblNewLabel_5 = new JLabel("Ban quản lý siêu thị");
						lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 14));
						lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_5.setMaximumSize(new Dimension(2288, 25));
						lblNewLabel_5.setPreferredSize(new Dimension(2288, 25));
						panel.add(lblNewLabel_5);
					}
					{
						JLabel lblNewLabel_6 = new JLabel("Ký và ghi rõ họ tên");
						lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 12));
						lblNewLabel_6.setMaximumSize(new Dimension(2288, 13));
						lblNewLabel_6.setPreferredSize(new Dimension(2288, 13));
						panel.add(lblNewLabel_6);
					}
				}
			}
		}
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("IN BÁO CÁO");
				okButton.setBackground(new Color(51, 255, 102));
				okButton.setActionCommand("In báo cáo");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(e->{
					int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn IN BÁO CÁO  không ???", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
		chooser.setSelectedFile(new File("BaoCao.pdf"));
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
				com.itextpdf.text.Font fontNormalSub = new com.itextpdf.text.Font(baseFont, 10);
	
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
				com.itextpdf.text.Paragraph subheading = new com.itextpdf.text.Paragraph("BÁO CÁO BÁN HÀNG\n\n", fontBold);
				subheading.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
				document.add(subheading);
		
				// Bảng thông tin đơn hàng 2 cột
				com.itextpdf.text.pdf.PdfPTable infoTable = new com.itextpdf.text.pdf.PdfPTable(2);
				infoTable.setWidthPercentage(100);
				infoTable.setSpacingBefore(10f);
				infoTable.setSpacingAfter(10f);
		
				// Các thông tin đơn hàng
				com.itextpdf.text.Phrase phrase_MaDH = new com.itextpdf.text.Phrase(" " , fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_MaDH = new com.itextpdf.text.pdf.PdfPCell(phrase_MaDH);
				cell_MaDH.setBorder(0);  
				infoTable.addCell(cell_MaDH);
	
				LocalDate today = LocalDate.now();
				com.itextpdf.text.Phrase phrase_NgayTT = new com.itextpdf.text.Phrase("Ngày báo cáo : " + today, fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_NgayTT = new com.itextpdf.text.pdf.PdfPCell(phrase_NgayTT);
				cell_NgayTT.setBorder(0);  
				infoTable.addCell(cell_NgayTT);
	
				NhanVienBLL nvBLL = new NhanVienBLL();
				com.itextpdf.text.Phrase phrase_TenNV = new com.itextpdf.text.Phrase("Tên nhân viên : "  + NHANVIEN.getTenNV(), fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_TenNV = new com.itextpdf.text.pdf.PdfPCell(phrase_TenNV);
				cell_TenNV.setBorder(0);  
				infoTable.addCell(cell_TenNV);
	
				com.itextpdf.text.Phrase phrase_MaNV = new com.itextpdf.text.Phrase("Mã nhân viên : " + NHANVIEN.getMaNV(), fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_MaNV = new com.itextpdf.text.pdf.PdfPCell(phrase_MaNV);
				cell_MaNV.setBorder(0);  
				infoTable.addCell(cell_MaNV);
				
				
				com.itextpdf.text.Phrase phrase_ngayBD = new com.itextpdf.text.Phrase("Thống kê từ ngày : " +(BaoCaoPanel.getPanelTimkiem().getNgayBatDau() != null? "khang": " ngày thành lập"), fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_ngaBD = new com.itextpdf.text.pdf.PdfPCell(phrase_ngayBD);
				cell_ngaBD.setBorder(0);  
				infoTable.addCell(cell_ngaBD);
	
				com.itextpdf.text.Phrase phrase_ngayKT = new com.itextpdf.text.Phrase("thống kê đến ngày : " +(BaoCaoPanel.getPanelTimkiem().getNgayKetThuc() != null?"khản" : today.toString()), fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_ngayKT = new com.itextpdf.text.pdf.PdfPCell(phrase_ngayKT);
				cell_ngayKT.setBorder(0);  
				infoTable.addCell(cell_ngayKT);
	
	
				com.itextpdf.text.Phrase phrase_TenKH = new com.itextpdf.text.Phrase("Tổng số đơn hàng : " + BaoCaoPanel.getSoDonhang(), fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_TenKH = new com.itextpdf.text.pdf.PdfPCell(phrase_TenKH);
				cell_TenKH.setBorder(0);  
				infoTable.addCell(cell_TenKH);
	
				com.itextpdf.text.Phrase phrase_MaKH = new com.itextpdf.text.Phrase("Tổng danh thu : " + BaoCaoPanel.getDanhThu(), fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_MaKH = new com.itextpdf.text.pdf.PdfPCell(phrase_MaKH);
				cell_MaKH.setBorder(0);  
				infoTable.addCell(cell_MaKH);
	
				com.itextpdf.text.Phrase phrase_TenKM = new com.itextpdf.text.Phrase("Nội dung báo cáo :\n " + txtrNhapNoiDung.getText() , fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_TenKM = new com.itextpdf.text.pdf.PdfPCell(phrase_TenKM);
				cell_TenKM.setBorder(0);  
				
				
				document.add(infoTable);
				document.add(phrase_TenKM);
		
				// Tạo bảng chi tiết sản phẩm
				com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(6);
				table.setWidthPercentage(100);
				table.setWidths(new float[]{1f, 1f, 1f, 2f, 3f, 2.5f});
				table.setSpacingBefore(10f);
		
				// Header bảng
				String[] headers = {"Mã DH", "Mã KM", "Mã KH", "PT thanh toán", "Ngày mua", "thành tiền"};
				for (String header : headers) {
					com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(header, fontHeader));
					cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
					cell.setBackgroundColor(new com.itextpdf.text.BaseColor(33,58,89)); // Màu xanh nhạt
					table.addCell(cell);
				}
	
		
				for (DonHangDTO dh : BaoCaoPanel.getDanhSachDonHang()) {
					com.itextpdf.text.pdf.PdfPCell cell1 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(String.valueOf(dh.getMaDH()), fontNormal));
					cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					table.addCell(cell1);
	
					com.itextpdf.text.pdf.PdfPCell cell2 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(String.valueOf(dh.getMaKM()), fontNormal));
					cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					table.addCell(cell2);
					
					com.itextpdf.text.pdf.PdfPCell cell3 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(String.valueOf(dh.getMaKH()), fontNormal));
					cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					table.addCell(cell3);
	
					com.itextpdf.text.pdf.PdfPCell cell4 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(dh.getPtThanhToan(), fontNormal));
					cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					table.addCell(cell4);
	
		
					com.itextpdf.text.pdf.PdfPCell cell5 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(String.valueOf(dh.getNgayTT()), fontNormal));
					cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					table.addCell(cell5);
	
					com.itextpdf.text.pdf.PdfPCell cell6 = new com.itextpdf.text.pdf.PdfPCell(new com.itextpdf.text.Paragraph(String.format("%,.0f", (double)dh.getTongTien()), fontNormal));
					cell6.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
					table.addCell(cell6);
	
				}
		
				document.add(table);
				
				com.itextpdf.text.pdf.PdfPTable table_chuKy = new com.itextpdf.text.pdf.PdfPTable(3);
				infoTable.setWidthPercentage(100);
				infoTable.setSpacingBefore(10f);
				infoTable.setSpacingAfter(10f);
				
				com.itextpdf.text.Phrase phrase_TenNguoiLam = new com.itextpdf.text.Phrase("Người làm báo cáo", fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_TenNguoilam = new com.itextpdf.text.pdf.PdfPCell(phrase_TenNguoiLam);
				cell_TenNguoilam.setBorder(0);  
				cell_TenNguoilam.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
				table_chuKy.addCell(cell_TenNguoilam);
				
				com.itextpdf.text.Phrase phrase_rong = new com.itextpdf.text.Phrase("", fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_rong = new com.itextpdf.text.pdf.PdfPCell(phrase_rong);
				cell_rong.setBorder(0);  
				cell_rong.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
				table_chuKy.addCell(cell_rong);
				
				
				com.itextpdf.text.Phrase phrase_quanLy = new com.itextpdf.text.Phrase("Ban quản lý siêu thị", fontNormal);
				com.itextpdf.text.pdf.PdfPCell cell_quanLy = new com.itextpdf.text.pdf.PdfPCell(phrase_quanLy);
				cell_quanLy.setBorder(0);  
				cell_quanLy.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
				table_chuKy.addCell(cell_quanLy);
				
				
				com.itextpdf.text.Phrase phrase_TenNguoiLam2 = new com.itextpdf.text.Phrase("Ký và ghi rõ họ tên", fontNormalSub);
				com.itextpdf.text.pdf.PdfPCell cell_TenNguoilam2 = new com.itextpdf.text.pdf.PdfPCell(phrase_TenNguoiLam2);
				cell_TenNguoilam2.setBorder(0);  
				cell_TenNguoilam2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
				table_chuKy.addCell(cell_TenNguoilam2);
				
				
				com.itextpdf.text.pdf.PdfPCell cell_rong2 = new com.itextpdf.text.pdf.PdfPCell(phrase_rong);
				cell_rong2.setBorder(0);  
				cell_rong2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
				table_chuKy.addCell(cell_rong2);
				
				
				com.itextpdf.text.Phrase phrase_quanLy2 = new com.itextpdf.text.Phrase("Ký và ghi rõ họ tên", fontNormalSub);
				com.itextpdf.text.pdf.PdfPCell cell_quanLy2 = new com.itextpdf.text.pdf.PdfPCell(phrase_quanLy2);
				cell_quanLy2.setBorder(0);  
				cell_quanLy2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
				table_chuKy.addCell(cell_quanLy2);
				
				
				
				document.add(table_chuKy);
				
				document.close();
		
				System.out.println("Xuất PDF thành công!");
				JOptionPane.showMessageDialog(null, "XUẤT BÁO CÁO thành công !!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				
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
