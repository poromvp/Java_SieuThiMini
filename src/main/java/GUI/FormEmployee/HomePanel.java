package GUI.FormEmployee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import GUI.ComponentCommon.RoundedComponent;

public class HomePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel panelCenter;
    private JPanel panelItem1;

    /**
     * Create the panel.
     */
    public HomePanel() {
        setLayout(new BorderLayout(0, 0));
        
        panelCenter = new JPanel();
        panelCenter.setBackground(new Color(204, 255, 255));
        add(panelCenter, BorderLayout.CENTER);
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.X_AXIS));
// ==================================================================================================================
		panelItem1 = new JPanel();
		RoundedComponent.setRadius(12);
		panelItem1 = RoundedComponent.createRoundedPanel(panelItem1, Color.white);
		panelItem1.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelItem1.setLayout(new BoxLayout(panelItem1, BoxLayout.Y_AXIS));
		
        // panelCenter.add(panel);
        
        ImageIcon icon = new ImageIcon("src/main/resources/images/icon/tinhchinhxac_128px.png");
        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
  
        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setIcon(new ImageIcon(scaledImage));
        lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        panelItem1.add(lblNewLabel);

        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText(
            "<html>" +
                "<body style='text-align: center; margin: 0; padding: 10px;'>" +
                    "<div style='width: 100%; font-size: 20px; font-family: Arial; font-weight: bold; color: black;'>" +
                        " QUẢN LÝ HÀNG HOÁ  <br>HIỆU QUẢ" +
                    "</div>" +
                "</body>" +
            "</html>"
        );
        editorPane.setEditable(false);
        editorPane.setOpaque(false); 
        panelItem1.add(editorPane);
		panelItem1.setAlignmentX(Component.CENTER_ALIGNMENT);

		// panelCenter.add(Box.createHorizontalStrut(10));

// ==========================================================================================================================
		// Thêm 2 panel trống vào
        JPanel panelItem2 = new JPanel();
		panelItem2 = RoundedComponent.createRoundedPanel(panelItem2, Color.white);
        panelItem2.setBorder(new EmptyBorder(10, 10, 10, 10));
		ImageIcon icon1 = new ImageIcon("src/main/resources/images/icon/tinhchinhxac_128px.png");
        Image scaledImage1 = icon1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        panelItem2.setLayout(new BoxLayout(panelItem2, BoxLayout.Y_AXIS));
		
        JLabel lblNewLabel1 = new JLabel();
        lblNewLabel1.setIcon(new ImageIcon(scaledImage1));
        lblNewLabel1.setAlignmentX(Component.CENTER_ALIGNMENT); 
        panelItem2.add(lblNewLabel1);

        // panel_1.setOpaque(false); // trong suốt
		JEditorPane editorPane2 = new JEditorPane();
        editorPane2.setContentType("text/html");
        editorPane2.setText(
            "<html>" +
                "<body style='text-align: center; margin: 0; padding: 10px;'>" +
                    "<div style='width: 100%; font-size: 20px; font-family: Arial; font-weight: bold; color: black;'>" +
                        "THỐNG KÊ PHÂN TÍCH<br>CHÍNH XÁC" +
                    "</div>" +
                "</body>" +
            "</html>"
        );
        editorPane2.setEditable(false);
        editorPane2.setOpaque(false);
		panelItem2.add(editorPane2);
        // panelCenter.add(panel_1);
		panelItem2.setAlignmentX(Component.CENTER_ALIGNMENT);

		// panelCenter.add(Box.createHorizontalStrut(10));

// ===========================================================================================================

		JPanel panelItem3 = new JPanel();
		panelItem3 = RoundedComponent.createRoundedPanel(panelItem3, Color.white);
		panelItem3.setBorder(new EmptyBorder(10, 10, 10, 10));
		ImageIcon icon3 = new ImageIcon("src/main/resources/images/icon/tinhchinhxac_128px.png");
		Image scaledImage3 = icon3.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		panelItem3.setLayout(new BoxLayout(panelItem3, BoxLayout.Y_AXIS));

		JLabel lblNewLabel3 = new JLabel();
		lblNewLabel3.setIcon(new ImageIcon(scaledImage3));
		lblNewLabel3.setAlignmentX(Component.CENTER_ALIGNMENT); 
		panelItem3.add(lblNewLabel3);

		// panel_3.setOpaque(false); // trong suốt
		JEditorPane editorPane3 = new JEditorPane();
		editorPane3.setContentType("text/html");
		editorPane3.setText(
			"<html>" +
				"<body style='text-align: center; margin: 0; padding: 10px;'>" +
					"<div style='width: 100%; font-size: 20px; font-family: Arial; font-weight: bold; color: black;'>" +
						"GIAO DIỆN THÂN THIỆN<br>DỂ DÙNG" +
					"</div>" +
				"</body>" +
			"</html>"
		);
		editorPane3.setEditable(false);
		editorPane3.setOpaque(false);
		panelItem3.add(editorPane3);
		panelItem3.setAlignmentX(Component.CENTER_ALIGNMENT);

		// panelCenter.add(panel_3);

// ===============================================================================================
		panelItem2.setMaximumSize(new Dimension(290, 500));
		panelItem2.setPreferredSize(new Dimension(290, 400));

		panelItem3.setMaximumSize(new Dimension(290, 500));
		panelItem3.setPreferredSize(new Dimension(290, 400));

		panelItem1.setMaximumSize(new Dimension(290, 500)); 
		panelItem1.setPreferredSize(new Dimension(290, 400));

		Box box = Box.createHorizontalBox();

		box.add(Box.createHorizontalGlue());
		box.add(panelItem1);
		
		JTextArea txtrGkfd = new JTextArea();
		txtrGkfd.setText("- Giúp theo dõi chi tiết số lượng hàng tồn kho, nhập – xuất chính xác theo thời gian thực.\r\n- Tự động cập nhật và cảnh báo khi hàng sắp hết, tránh thiếu hụt hoặc dư thừa.\r\n- Tiết kiệm thời gian, giảm thiểu thất thoát và nâng cao hiệu suất quản lý kho.\r\n\r\n"  //
						);
		txtrGkfd.setLineWrap(true); 
		txtrGkfd.setWrapStyleWord(true);
		txtrGkfd.setFont(new Font("Arial", Font.PLAIN, 18));
		txtrGkfd.setPreferredSize(new Dimension(200, 300));
		panelItem1.add(txtrGkfd);
		box.add(Box.createHorizontalStrut(30));
		box.add(panelItem2);
		
		JTextArea textArea2 = new JTextArea();
		textArea2.setText("- Cung cấp báo cáo doanh thu, chi phí, lợi nhuận theo ngày, tuần, tháng.\r\n- Giúp chủ cửa hàng nắm rõ tình hình kinh doanh và đưa ra quyết định kịp thời.\r\n- Dữ liệu trực quan, dễ hiểu, hỗ trợ phân tích xu hướng và lập kế hoạch hiệu quả.\r\r\n"  //
						);
		textArea2.setLineWrap(true); 
		textArea2.setWrapStyleWord(true);
		textArea2.setFont(new Font("Arial", Font.PLAIN, 18));
		textArea2.setPreferredSize(new Dimension(200, 300));
		panelItem2.add(textArea2);
		box.add(Box.createHorizontalStrut(30));
		box.add(panelItem3);
		
		JTextArea textArea3 = new JTextArea();
		textArea3.setText("- Thiết kế trực quan, đơn giản hóa thao tác giúp ai cũng dễ sử dụng – kể cả người không rành công nghệ.\r\n- Tối ưu cho cả máy tính và thiết bị di động, thao tác nhanh chóng mọi lúc, mọi nơi.\r\n-Tiết kiệm thời gian đào tạo, phù hợp với nhiều mô hình cửa hàng.\r\n\r\n"  //
						);
		textArea3.setLineWrap(true); 
		textArea3.setWrapStyleWord(true);
		textArea3.setFont(new Font("Arial", Font.PLAIN, 18));
		textArea3.setPreferredSize(new Dimension(200, 300));
		panelItem3.add(textArea3);
		box.add(Box.createHorizontalGlue());

		panelCenter.add(box);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_2.setBackground(new Color(204, 255, 255));
		add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		// C:\Users\KHANG\GIT-SGU\Java_SieuThiMini\src\main\resources\images\anhcho1.png
		ImageIcon icon4 = new ImageIcon("src/main/resources/images/anhcho1.png");
		Image scaledImage4 = icon4.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		panelItem3.setLayout(new BoxLayout(panelItem3, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setIcon(new ImageIcon(scaledImage4));
		// panel_2.add(lblNewLabel_1, BorderLayout.WEST);


		JEditorPane editorPaneTieuDe = new JEditorPane();
		editorPaneTieuDe.setForeground(new Color(0, 51, 102));
        editorPaneTieuDe.setContentType("text/html");
        editorPaneTieuDe.setText(
            "<html>" +
                "<body style='text-align: center; margin: 0; padding: 10px;'>" +
                    "<div style='width: 100%; font-size: 20px; font-family: Arial; font-weight: bold; color: rgb(0, 51, 102);'>" +
                        "ỨNG DỤNG QUẢN LÝ CỬA HÀNG SIÊU THỊ MINI <br>Bán hàng nhanh - Quản lý chuẩn - Tối ưu lợi nhuận" +
                    "</div>" +
                "</body>" +
            "</html>"
        );
        editorPaneTieuDe.setEditable(false);
        editorPaneTieuDe.setOpaque(false);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(new Color(0, 51, 102));
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.add(editorPaneTieuDe);
		panel_2.add(panel_4, BorderLayout.CENTER);


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Đơn hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new HomePanel();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
