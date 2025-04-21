package GUI.Admin_TheThanhVien;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import BLL.TheThanhVienBLL;
import DTO.TheThanhVienDTO;

import java.awt.*;
import java.util.ArrayList;

import GUI.TienIch;
import GUI.Admin_PanelThongKe.TableControl;

public class PanelMainThanhVien extends JPanel {
    JPanel pn1, pn2;
    JButton btnThem, btnXoa, btnSua, btnTim, btnXemBlock;
    JTable tb;
    DefaultTableModel model;
    private ArrayList<TheThanhVienDTO> TTV = TheThanhVienBLL.getAllMembers();

    private void initPanel(JPanel pnl, String title) {
        pnl.setBorder(new CompoundBorder(new TitledBorder(title), new EmptyBorder(4, 4, 4, 4)));
    }

    private void initPanel1(JPanel pn1) {
        initPanel(pn1, "Hành động");
        pn1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xóa");
        btnSua = new JButton("Sửa");
        btnTim = new JButton("Tìm kiếm");
        btnXemBlock = new JButton("<html><center>DS TTV Đã Khóa</center><html>");

        // Thiết lập style cho từng nút
        TienIch.nutStyle(btnThem, "addIcon.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnXoa, "delIcon.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnSua, "editIcon.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnTim, "search.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnXemBlock, "list.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);

        // Đặt khoảng cách và vị trí cho từng nút
        gbc.insets = new Insets(10, 10, 10, 10); // padding: top, left, bottom, right
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;

        gbc.gridx = 0;
        pn1.add(btnThem, gbc);
        gbc.gridx = 1;
        pn1.add(btnXoa, gbc);
        gbc.gridx = 2;
        pn1.add(btnSua, gbc);
        gbc.gridx = 3;
        pn1.add(btnTim, gbc);
        gbc.gridx = 4;
        pn1.add(btnXemBlock, gbc);
    }

    private void initPanel2(JPanel pn2) {
        initPanel(pn2, "Danh sách");
        pn2.setLayout(new BorderLayout());
        String[] tencot = { "Mã thành viên", "Họ tên", "Số điện thoại", "Điểm tích lũy" };
        model = new DefaultTableModel(tencot, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        loadThanhVien(TTV);
        tb = new JTable(model);
        TableControl.TableStyle(tb, model);
        TableControl.TableEvent(tb, model, "HD");
        JScrollPane scr = new JScrollPane(tb);
        pn2.add(scr, BorderLayout.CENTER);
    }

    private void loadThanhVien(ArrayList<TheThanhVienDTO> ttv) {
        model.setRowCount(0);
        for (TheThanhVienDTO tv : ttv) {
            model.addRow(new Object[] {
                    tv.getMaTV(),
                    tv.getTenTV(),
                    tv.getSdt(),
                    tv.getDiemTL()
            });
        }
    }

    public PanelMainThanhVien() {
        setLayout(new BorderLayout());
    
        pn1 = new JPanel();
        initPanel1(pn1);
        pn1.setPreferredSize(new Dimension(0, 120)); // Giảm chiều cao panel1 ở đây
        add(pn1, BorderLayout.NORTH);
    
        pn2 = new JPanel();
        initPanel2(pn2);
        add(pn2, BorderLayout.CENTER);
    }    
}
