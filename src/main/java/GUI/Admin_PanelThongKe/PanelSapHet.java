package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import BLL.LoaiSanPhamBLL;
import BLL.SanPhamBLL;
import DTO.SanPhamDTO;

public class PanelSapHet extends JPanel {
    JTable tb;
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<SanPhamDTO> DsSP = (ArrayList<SanPhamDTO>) SanPhamBLL.getAllProducts();

    public PanelSapHet() {
        setBorder(new CompoundBorder(new TitledBorder("Danh Sách Nhập Hàng"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new BorderLayout());

        String[] tencot = { "Mã sản phẩm", "Tên loại", "Tên sản phẩm" };
        model = new DefaultTableModel(tencot, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        loadSanPham(DsSP);
        tb = new JTable(model);
        TableControl.TableStyle(tb, model);
        scr = new JScrollPane(tb);

        add(scr, BorderLayout.CENTER);
    }

    private void loadSanPham(ArrayList<SanPhamDTO> DsSP) {
        model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (SanPhamDTO sp : DsSP) {
            model.addRow(new Object[] {
                    sp.getMaSP(),
                    new LoaiSanPhamBLL().getLoaiSanPham(sp.getMaLSP()).getTenLoaiSP(),
                    sp.getTenSP()
            });
        }
    }
}
