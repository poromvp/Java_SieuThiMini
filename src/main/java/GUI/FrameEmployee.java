package GUI;


import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import GUI.ComponentCommon.Header;
import GUI.ComponentCommon.TienIch;
import GUI.FormEmployee.BaoCaoPanel;
import GUI.FormEmployee.HomePanel;
import GUI.FormEmployee.LeftMenu;
import GUI.FormEmployee.OrderPanel;
import GUI.FormEmployee.ProfilePanel;
import GUI.NhanVien_BaoCaoBanHang.PanelMainBaoCao;

public class FrameEmployee extends JFrame implements ActionListener {
 

    private  LeftMenu pn_leftMenu;
    private  Header pn_header;

    private  OrderPanel pn_formOrder;
    private  HomePanel pn_formHome;
    private  BaoCaoPanel panelMainBaoCao;
    private  ProfilePanel panelProfile;


    private static  JLabel lbl_title = new JLabel("Trang Chủ");
    private static JPanel pn_body;
    private  static  JPanel pn_cardLayout;
    private static CardLayout cardLayout;

 


    public FrameEmployee(String maNV){
        setLayout(new BorderLayout());
        setSize(1300, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event.getID() == WindowEvent.WINDOW_OPENED) {
                Window window = (Window) event.getSource();
                ImageIcon icon = new ImageIcon(TienIch.class.getResource("/images/icon/supermarket.png"));
                Image img = icon.getImage();
                Image resizedImg = img.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
                window.setIconImage(new ImageIcon(resizedImg).getImage());
            }
        }, AWTEvent.WINDOW_EVENT_MASK);
        

       

        pn_header = new Header();
        pn_leftMenu = new LeftMenu(maNV);
        panelProfile = new ProfilePanel( Integer.parseInt( maNV));
        
        
        cardLayout = new CardLayout(); 
        pn_cardLayout = new JPanel(cardLayout);  

        pn_formOrder = new OrderPanel();
        pn_formHome = new HomePanel();
        panelMainBaoCao = new BaoCaoPanel();

        pn_cardLayout.add(pn_formHome, "formHome");
        pn_cardLayout.add(panelProfile, "formUser");
        pn_cardLayout.add(pn_formOrder, "formOrder");
        pn_cardLayout.add(panelMainBaoCao, "formReport");

        
        pn_body = new JPanel(new BorderLayout());
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        lbl_title.setPreferredSize(new Dimension(100, 50));
        lbl_title.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_title.setBackground(LeftMenu.getColor());
        lbl_title.setOpaque(true);
        lbl_title.setForeground(Color.white);
        pn_body.add(lbl_title, BorderLayout.NORTH);
        pn_body.add(pn_cardLayout, BorderLayout.CENTER);
        
        
        // add(pn_header, BorderLayout.NORTH);
        add(pn_leftMenu, BorderLayout.WEST);
        add(pn_body, BorderLayout.CENTER);
        
        setVisible(true);
    }


    public static void main(String[] args) {
        new FrameEmployee(1 +"");
    }
    
    public static void setPage(String pagename, String title){
        lbl_title.setText(title);
        cardLayout.show(pn_cardLayout, pagename);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
   