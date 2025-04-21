package GUI.Admin_PanelThongKe;

import javax.swing.*;
import java.awt.*;

public class PanelMainThongKe extends JPanel{

    public PanelMainThongKe() {
        setBackground(new Color(30, 144, 255));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTabbedPane tab = new JTabbedPane();
        
        PanelDoanhThu pn1 = new PanelDoanhThu();
        tab.addTab("Báo cáo doanh thu tổng hợp", pn1);
        
        PanelKhoTongHop pn2 = new PanelKhoTongHop();
        tab.addTab("Báo cáo kho tổng hợp", pn2);
        
        PanelBaoCaoNV pn3 = new PanelBaoCaoNV();
        tab.addTab("Báo cáo nhân viên", pn3);

        PanelBaoCaoKH pn4 = new PanelBaoCaoKH();
        tab.addTab("Báo cáo khách hàng", pn4);
        
        add(tab);

    }
}
