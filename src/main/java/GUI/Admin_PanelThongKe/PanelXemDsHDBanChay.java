package GUI.Admin_PanelThongKe;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


import BLL.DonHangBLL;
import DTO.DonHangDTO;
import DTO.SearchBanChayDTO;
import GUI.ComponentCommon.StyledTable;
import GUI.ComponentCommon.TienIch;

public class PanelXemDsHDBanChay extends JPanel {
    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<DonHangDTO> HoaDon = new ArrayList<>();

    public PanelXemDsHDBanChay(SearchBanChayDTO search) {
        String[] parts = search.getChuoiMaDH().split("\\s*,\\s*");
        for (String part : parts) {
            System.out.println(Integer.parseInt(part));
            HoaDon.add(DonHangBLL.getOrderById(Integer.parseInt(part)));
        }

        TienIch.taoTitleBorder(this, "Danh sách các hóa đơn");
        setLayout(new BorderLayout());
        String[] tencot = { "Mã đơn hàng", "Mã nhân viên", "PTTT", "Thành tiền", "Ngày" };
        for (DonHangDTO hd : HoaDon) {
            System.out.println(hd.getMaDH() + " " + hd.getMaNV() + " " + hd.getPtThanhToan() + " " + hd.getNgayTT());
        }
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        loadDonHang(HoaDon);
        StyledTable.hoverTable(tb, model);
        StyledTable.TableEvent(tb, model, "HD"); // Giữ sự kiện double-click
        scr = new JScrollPane(tb);
        TienIch.setPreferredSizeTuDong(scr, tb);
        add(scr, BorderLayout.CENTER);
    }

    private void loadDonHang(ArrayList<DonHangDTO> danhsach) {
        model.setRowCount(0);
        for (DonHangDTO hd : danhsach) {
            model.addRow(new Object[] {
                    hd.getMaDH(),
                    hd.getMaNV(),
                    hd.getPtThanhToan(),
                    TienIch.formatVND(DonHangBLL.tinhTongTienByMaDonHang(hd.getMaDH())),
                    TienIch.ddmmyyyy(hd.getNgayTT()) });
        }
    }
}
