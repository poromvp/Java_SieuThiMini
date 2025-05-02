package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BLL.NhanVienBLL;
import DTO.NhanVienDTO;
import GUI.ComponentCommon.*;

public class PanelTotNhat extends JPanel implements ActionListener {
    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<NhanVienDTO> DsNV = (ArrayList<NhanVienDTO>) new NhanVienBLL().getAllNhanVien();
    JPopupMenu popupMenu;
    JMenuItem searchItem, exportItem;
    JDateChooser from, to;
    JPanel pnTool = new JPanel();

    public void initPanelTool() {
        pnTool.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        // Ngày hôm nay
        Date today = new Date(System.currentTimeMillis());

        // Ngày đầu tiên của tháng
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = new Date(cal.getTimeInMillis());

        from = new JDateChooser();
        from.setMaxSelectableDate(today);
        from.setDateFormatString("dd/MM/yyyy");
        TienIch.checkngaynhaptutay(from, today);
        TienIch.timStyle(from);
        from.setDate(firstDayOfMonth);

        to = new JDateChooser();
        to.setMaxSelectableDate(today);
        to.setDateFormatString("dd/MM/yyyy");
        TienIch.checkngaynhaptutay(to, today);
        TienIch.timStyle(to);
        to.setDate(today);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel tu = new JLabel("Từ");
        tu.setHorizontalAlignment(SwingConstants.RIGHT);
        pnTool.add(tu, gbc);

        gbc.gridx = 1;
        pnTool.add(from);

        gbc.gridx = 2;
        JLabel toi = new JLabel("Tới");
        toi.setHorizontalAlignment(SwingConstants.RIGHT);
        pnTool.add(toi, gbc);

        gbc.gridx = 3;
        pnTool.add(to);
    }

    public PanelTotNhat() {
        TienIch.taoTitleBorder(this, "Danh sách nhân viên có doanh số tốt nhất");
        setLayout(new BorderLayout());

        String[] tencot = { "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        loadNhanVien(DsNV);
        TableControl.TableEvent(tb, model, "NV"); // Giữ sự kiện double-click
        scr = new JScrollPane(tb);
        add(scr, BorderLayout.CENTER);
        initPanelTool();
        add(pnTool, BorderLayout.NORTH);


        // Thêm popup menu
        popupMenu = new JPopupMenu();
        searchItem = new JMenuItem("Tìm Kiếm");
        exportItem = new JMenuItem("In Báo Cáo");
        searchItem.addActionListener(this);
        exportItem.addActionListener(this);
        popupMenu.add(searchItem);
        popupMenu.add(exportItem);

        // Thêm sự kiện chuột phải cho bảng
        showpupop(tb);
        showpupop(scr);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == searchItem) {
            PanelTimVN panel = new PanelTimVN();
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == 0) {
                System.out.println("Bạn vừa nhập: ");
            }
            loadNhanVien(DsNV);
        } else if (e.getSource() == exportItem) {
            PanelExport panel = new PanelExport();
            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (panel.getSelectedFormat().equals("excel")) {
                    panel.XuatExccel(model);
                } else {
                    panel.XuatPDF(model);
                }
            } else if (result == JOptionPane.CANCEL_OPTION) {
                TienIch.CustomMessage("Đã hủy xuất file");
            } else {
                TienIch.CustomMessage("Đã hủy xuất file");
            }
        }
        TienIch.resetUI();
    }
}