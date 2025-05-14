package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.ComponentCommon.TienIch;
import GUI.FormWareHouse.*;

import java.awt.*;
import java.awt.event.*;

public class FrameQuanLyKho extends JFrame  implements ActionListener{
    private JPanel rightPn;
    private LeftMenuWareHouse pn_leftMenu;
    private String maNV;
     private JPanel Center = new JPanel(new BorderLayout());
    private JPanel header = new JPanel(new BorderLayout());
    private JLabel lbl_header = new JLabel("Nhập kho");

    public FrameQuanLyKho(String maNV){
        this.maNV=maNV;
        setTitle("Frame Quản Lý Kho");
        setSize(1300,750);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        header.setBackground(new Color(33, 58, 89));
        header.setPreferredSize(new Dimension(0, 50));
        lbl_header.setForeground(new Color(255, 255, 255));
        lbl_header.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_header.setHorizontalAlignment(SwingConstants.CENTER);
        header.add(lbl_header);
        Center.add(header, BorderLayout.NORTH);

        pn_leftMenu = new LeftMenuWareHouse(maNV);
        pn_leftMenu.setActionListener(this);
        pn_leftMenu.setPreferredSize(new Dimension(250, 0));

        add(pn_leftMenu,BorderLayout.WEST);
        rightPn = new JPanel();
        panelImport();
        Center.add(rightPn, BorderLayout.CENTER);
 rightPn.setBackground(new Color(204, 255, 255));
        rightPn.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(Center,BorderLayout.CENTER);
        setVisible(true);
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event.getID() == WindowEvent.WINDOW_OPENED) {
                Window window = (Window) event.getSource();
                ImageIcon icon = new ImageIcon(TienIch.class.getResource("/images/icon/supermarket.png"));
                Image img = icon.getImage();
                Image resizedImg = img.getScaledInstance(512, 512, Image.SCALE_SMOOTH);
                window.setIconImage(new ImageIcon(resizedImg).getImage());
            }
        }, AWTEvent.WINDOW_EVENT_MASK);
    }

    public void panelImport(){
        rightPn.removeAll();
        rightPn.setLayout(new BorderLayout());
        lbl_header.setText("Nhập kho");
        FormImport importPanel = new FormImport(maNV);
        rightPn.add(importPanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelProduct(){
        rightPn.removeAll();
        lbl_header.setText("Sản phẩm");

        rightPn.setLayout(new BorderLayout());

        FormProduct productPanel= new FormProduct();
        rightPn.add(productPanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelCategory(){
        rightPn.removeAll();
        lbl_header.setText("Loại sản phẩm");
        rightPn.setLayout(new BorderLayout());

        FormProductType productTypePanel= new FormProductType();
        rightPn.add(productTypePanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public void panelSupplier(){
        lbl_header.setText(" Nhà cung cấp sản phẩm");

        rightPn.removeAll();
        rightPn.setLayout(new BorderLayout());

        FormSupplier supplierPanel= new FormSupplier();
        rightPn.add(supplierPanel, BorderLayout.CENTER);

        rightPn.revalidate();
        rightPn.repaint();
    }

    public static void main(String[] args) {
         new FrameQuanLyKho("1");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== pn_leftMenu.getBtnImport()){
            panelImport();
        } else if (e.getSource()== pn_leftMenu.getBtnProduct()) {
            panelProduct();
        } else if (e.getSource()==pn_leftMenu.getBtnProductType()) {
            panelCategory();
        } else if (e.getSource()==pn_leftMenu.getBtnSupplier()) {
            panelSupplier();
        } else if(e.getSource()==pn_leftMenu.getBtnLogout()){
            JOptionPane.showMessageDialog(this, "Bạn đã đăng xuất.");
            this.dispose();
            new FrameLogin();
        }
    }
}

