package GUI.Admin_PanelThongKe;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import GUI.TienIch;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PanelBaoCaoNV extends JPanel implements ActionListener {
    JButton btnTim, btnDS;
    JTable tb;
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<NhanVienDTO> DsNV = (ArrayList<NhanVienDTO>) new NhanVienBLL().getAllNhanVien();
    JPanel pn1, pn2;
    JPopupMenu popupMenu;
    JMenuItem exportItem;

    public void showpupop(Object obj) {
        if (obj instanceof JTable tb) {
            tb.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                private void showPopup(MouseEvent e) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });
        } else if (obj instanceof JScrollPane scr) {
            scr.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                private void showPopup(MouseEvent e) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });
        }
    }

    public void initPanel1() {
        pn1.setBorder(new CompoundBorder(new TitledBorder("Chức năng"), new EmptyBorder(4, 4, 4, 4)));
        pn1.setLayout(new GridLayout(1, 2, 2, 5));

        btnTim = new JButton("Tìm");
        TienIch.nutStyle(btnTim, "search.png", 20, 50, 25);
        pn1.add(btnTim);

        btnDS = new JButton("Danh sách nhân viên có doanh số tốt nhất");
        TienIch.nutStyle(btnDS, "list.png", 20, 50, 25);
        pn1.add(btnDS);

        btnTim.addActionListener((ActionListener) this);
        btnDS.addActionListener((ActionListener) this);
    }

    public void initPanel2() {
        pn2.setBorder(new CompoundBorder(new TitledBorder("Danh sách"), new EmptyBorder(4, 4, 4, 4)));
        pn2.setLayout(new BorderLayout());
        String[] tencot = { "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại" };
        model = new DefaultTableModel(tencot, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        loadNhanVien(DsNV);
        tb = new JTable(model);
        TableControl.TableStyle(tb, model);
        TableControl.TableEvent(tb, model, "NV");
        scr = new JScrollPane(tb);
        pn2.add(scr, BorderLayout.CENTER);

        // Thêm sự kiện chuột phải cho bảng
        showpupop(tb);
        showpupop(scr);
    }

    public PanelBaoCaoNV() {
        setBorder(new CompoundBorder(new TitledBorder("Báo cáo nhân viên"), new EmptyBorder(4, 4, 4, 4)));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pn1 = new JPanel();
        initPanel1();
        add(pn1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pn2 = new JPanel();
        initPanel2();
        add(pn2, gbc);

        // Thêm popup menu
        popupMenu = new JPopupMenu();
        exportItem = new JMenuItem("In Báo Cáo");
        exportItem.addActionListener(this);
        popupMenu.add(exportItem);
    }

    private void loadNhanVien(ArrayList<NhanVienDTO> dsnv) {
        model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ
        for (NhanVienDTO nv : dsnv) {
            model.addRow(new Object[] {
                    nv.getMaNV(),
                    nv.getTenNV(),
                    TienIch.ddmmyyyy(nv.getNgaySinh()),
                    nv.getSDT()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTim) {
            PanelTimVN panel = new PanelTimVN();
            // Hiển thị JOptionPane
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            /*
             * giá trị của biến result sẽ là:
             * JOptionPane.OK_OPTION (0) nếu bạn nhấn OK.
             * JOptionPane.CANCEL_OPTION (2) nếu bạn nhấn Cancel.
             * JOptionPane.CLOSED_OPTION (-1) nếu bạn đóng hộp thoại bằng dấu X (góc trên
             * phải).
             */
            if (result == 0) {
                System.out.println("Bạn vừa nhập: " + panel.getTxtName());
            }
        }

        if (e.getSource() == btnDS) {
            PanelTotNhat panel = new PanelTotNhat();
            JOptionPane.showMessageDialog(null, panel, "Xem Danh Sách", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
