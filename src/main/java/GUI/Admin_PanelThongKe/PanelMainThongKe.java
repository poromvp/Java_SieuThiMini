package GUI.Admin_PanelThongKe;

import javax.swing.*;
import java.awt.*;

public class PanelMainThongKe extends JPanel{
    public String MANV;
    public PanelMainThongKe(String MANV) {
        this.MANV = MANV;
        setBackground(new Color(33,58,89));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTabbedPane tab = new JTabbedPane();
        
        PanelDoanhThu pn1 = new PanelDoanhThu(MANV);
        tab.addTab("Báo cáo doanh thu tổng hợp", pn1);
        
        PanelKhoTongHop pn2 = new PanelKhoTongHop(MANV);
        tab.addTab("Báo cáo kho tổng hợp", pn2);
        
        PanelBaoCaoNV pn3 = new PanelBaoCaoNV(MANV);
        tab.addTab("Báo cáo nhân viên", pn3);

        PanelBaoCaoKH pn4 = new PanelBaoCaoKH(MANV);
        tab.addTab("Báo cáo khách hàng", pn4);
        
        add(tab);

    }
}
