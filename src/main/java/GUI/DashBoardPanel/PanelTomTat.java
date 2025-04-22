package GUI.DashBoardPanel;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import BLL.DonHangBLL;
import BLL.SanPhamBLL;
import DTO.DonHangDTO;
import GUI.ComponentCommon.*;

public class PanelTomTat extends JPanel {
    JPanel pn1, pn2, pn3;
    JLabel sp, luotmua, doanhthu;
    public ArrayList<DonHangDTO> HoaDon = DonHangBLL.getAllOrders();

    public void initPanel1() {
        TitledBorder border = new TitledBorder("Sản Phẩm");
        border.setTitleColor(Color.WHITE);
        pn1.setBorder(new CompoundBorder(border, new EmptyBorder(4, 4, 4, 4)));
        pn1.setLayout(new GridLayout(1, 2, 5, 5));
        pn1.setBackground(new Color(33, 58, 89));

        JLabel lbName = new JLabel("Sản Phẩm:");
        TienIch.labelStyle(lbName, 1, 18, null);
        pn1.add(lbName);

        sp = new JLabel(SanPhamBLL.getAllProducts().size() + "");
        TienIch.labelStyle(sp, 2, 20, null);
        pn1.add(sp);
    }

    public void initPanel2() {
        TitledBorder border = new TitledBorder("Lượt Mua");
        border.setTitleColor(Color.WHITE);
        pn2.setBorder(new CompoundBorder(border, new EmptyBorder(4, 4, 4, 4)));
        pn2.setLayout(new GridLayout(1, 2, 5, 5));
        pn2.setBackground(new Color(33, 58, 89));

        JLabel lbName = new JLabel("Lượt Mua:");
        TienIch.labelStyle(lbName, 1, 18, null);
        pn2.add(lbName);

        luotmua = new JLabel(DonHangBLL.countOrder() + "");
        TienIch.labelStyle(luotmua, 2, 20, null);
        pn2.add(luotmua);
    }

    public void initPanel3() {
        TitledBorder border = new TitledBorder("Doanh Thu");
        border.setTitleColor(Color.WHITE);
        pn3.setBorder(new CompoundBorder(border, new EmptyBorder(4, 4, 4, 4)));
        pn3.setLayout(new GridLayout(1, 2, 5, 5));
        pn3.setBackground(new Color(33, 58, 89));

        JLabel lbName = new JLabel("Doanh Thu:");
        TienIch.labelStyle(lbName, 1, 18, null);
        pn3.add(lbName);

        double sumDoanhThu = 0;
        for (DonHangDTO hd : HoaDon) {
            sumDoanhThu += DonHangBLL.tinhTongTienByMaDonHang(hd.getMaDH());
        }
        doanhthu = new JLabel(TienIch.formatVND(sumDoanhThu));
        TienIch.labelStyle(doanhthu, 2, 20, null);
        pn3.add(doanhthu);
    }

    public PanelTomTat() {
        setLayout(new GridLayout(1, 3, 10, 10));

        pn1 = new JPanel();
        initPanel1();
        add(pn1);

        pn2 = new JPanel();
        initPanel2();
        add(pn2);

        pn3 = new JPanel();
        initPanel3();
        add(pn3);
    }
}
