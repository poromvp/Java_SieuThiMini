package com.NhanVien_BaoCaoBanHang;
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
public class PanelXemChiTiet extends JPanel{
    JTextArea txtMaHD,txtMANV;
    public PanelXemChiTiet(JTable tb, int dong){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(226, 224, 221));

        txtMaHD=new JTextArea(LayChuoiTuBang(tb, dong, 0));
        txtMaHD.setEditable(false);
        txtMANV = new JTextArea(LayChuoiTuBang(tb, dong, 5));
        txtMANV.setEditable(false);

        add(new JLabel("Mã Hóa Đơn:"));
        add(txtMaHD);
        add(new JLabel("Mã Nhân Viên:"));
        add(txtMANV);
    }

    public String LayChuoiTuBang(JTable tb, int dong, int cot){
        Object txt=tb.getValueAt(dong, cot);
        return txt.toString();
    }
}
