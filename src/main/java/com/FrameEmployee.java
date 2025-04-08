package com;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ComponentCommon.Header;
import com.FormEmployee.InterfaceHome;
import com.FormEmployee.InterfaceOrder;
import com.FormEmployee.InterfaceOrderManagement;
import com.FormEmployee.LeftMenu;
import com.NhanVien_BaoCaoBanHang.PanelMainBaoCao;

public class FrameEmployee extends JFrame implements ActionListener {
 

    private  LeftMenu pn_leftMenu;
    private  Header pn_header;

    private  InterfaceOrder pn_formOrder;
    private  InterfaceOrderManagement pn_formOrderManagement;
    private  InterfaceHome pn_formHome;
    private  PanelMainBaoCao panelMainBaoCao;


    private static  JLabel lbl_title = new JLabel("Trang Chủ");
    private static JPanel pn_body;
    private  static  JPanel pn_cardLayout;
    private static CardLayout cardLayout;

 


    public FrameEmployee(){
        setLayout(new BorderLayout());
        setSize(1300, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/images/icon/Logo_market.png");  
        this.setIconImage(icon);  
        

       

        pn_header = new Header();
        pn_leftMenu = new LeftMenu();
        
        
        cardLayout = new CardLayout(); 
        pn_cardLayout = new JPanel(cardLayout);  

        pn_formOrder = new InterfaceOrder();
        pn_formOrderManagement = new InterfaceOrderManagement();
        pn_formHome = new InterfaceHome();
        panelMainBaoCao = new PanelMainBaoCao();

        pn_cardLayout.add(pn_formHome, "formHome");
        pn_cardLayout.add(pn_formOrder, "formOrder");
        pn_cardLayout.add(pn_formOrderManagement, "formOrderManagement");
        pn_cardLayout.add(panelMainBaoCao, "formReport");

        
        pn_body = new JPanel(new BorderLayout());
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        pn_body.add(lbl_title, BorderLayout.NORTH);
        pn_body.add(pn_cardLayout, BorderLayout.CENTER);
        
        
        add(pn_header, BorderLayout.NORTH);
        add(pn_leftMenu, BorderLayout.WEST);
        add(pn_body, BorderLayout.CENTER);
        setVisible(true);
    }


    public static void main(String[] args) {
        new FrameEmployee();
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
   