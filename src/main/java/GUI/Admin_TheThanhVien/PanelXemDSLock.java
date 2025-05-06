package GUI.Admin_TheThanhVien;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import BLL.TheThanhVienBLL;
import DTO.SearchTheThanhVienDTO;
import DTO.TheThanhVienDTO;
import GUI.Admin_PanelThongKe.PanelExport;
import GUI.Admin_PanelThongKe.PanelTimKH;
import GUI.Admin_PanelThongKe.XuatFileExccel;
import GUI.ComponentCommon.*;
import java.util.List;

public class PanelXemDSLock extends JPanel implements ActionListener {
    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel model;
    JScrollPane scr;
    private ArrayList<TheThanhVienDTO> TTV = TheThanhVienBLL.getAllMembersINACTIVE();
    JPopupMenu popupMenu;
    JMenuItem searchItem, exportItem;
    public String MANV;
    public SearchTheThanhVienDTO SEARCH = new SearchTheThanhVienDTO();

    public PanelXemDSLock(String MANV) {
        this.MANV = MANV;
        TienIch.taoTitleBorder(this, "Danh sách Thẻ Thành Viên bị khóa");
        setLayout(new BorderLayout());

        String[] tencot = { "Mã thành viên", "Họ tên", "Số điện thoại", "Điểm tích lũy" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        loadThanhVien(TTV);
        StyledTable.hoverTable(tb, model);
        StyledTable.TableEvent(tb, model, "KH", MANV);
        scr = new JScrollPane(tb);
        add(scr, BorderLayout.CENTER);

        // Thêm popup menu
        popupMenu = new JPopupMenu();
        searchItem = new JMenuItem("Tìm Kiếm");
        exportItem = new JMenuItem("Xuất file");
        searchItem.addActionListener(this);
        exportItem.addActionListener(this);
        popupMenu.add(searchItem);
        popupMenu.add(exportItem);

        // Thêm sự kiện chuột phải cho bảng
        showpupop(tb);
        showpupop(scr);
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
    public ArrayList<String> SEARCH2 = new ArrayList<>();
    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == searchItem) {
            PanelTimKH panel = new PanelTimKH();
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == 0) {
                TTV = panel.ketquaLock();
                if(TTV.size()!=0){
                    SEARCH = panel.traSearch();
                    SEARCH2 = panel.stringSearch();
                } else {
                    TienIch.CustomMessage("Không tìm thấy");
                }
                loadThanhVien(TTV);
            }
        } else if (e.getSource() == exportItem) {
            PanelExport panel = new PanelExport();
            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (panel.getSelectedFormat().equals("excel")) {
                    if(TTV.size() == 0){
                        TienIch.CustomMessage("Không có gì để xuất");
                    } else {
                        try {
                            // Chuẩn bị dữ liệu cho exportToExcel
                            ArrayList<List<Object>> data = new ArrayList<>();
                            for (TheThanhVienDTO tv : TTV) {
                                List<Object> row = new ArrayList<>();
                                row.add(tv.getMaTV());
                                row.add(tv.getTenTV());
                                row.add(tv.getNgaySinh());
                                row.add(tv.getDiaChi());
                                row.add(tv.getDiemTL());
                                row.add(tv.getSdt());
                                row.add(tv.getNgayBD());
                                row.add(tv.getNgayKT());
                                data.add(row);
                            }
                            String[] columnNames = { "Mã thành viên", "Họ tên", "Ngày sinh", "Địa chỉ", "Điểm tích lũy", "Số điện thoại", "Ngày bắt đầu", "Ngày kết thúc"};
                            String title = "DANH SÁCH THẺ THÀNH VIÊN BỊ KHÓA";
                            String manv = this.MANV;
                            // Gọi hàm exportToExcel
                            XuatFileExccel.exportToExcel(data, columnNames, title, manv, SEARCH2);
                            TienIch.CustomMessage("Xuất file Excel thành công!");
                        } catch (Exception ex) {
                            TienIch.CustomMessage("Lỗi khi xuất file Excel: " + ex.getMessage());
                        }
                    }
                } else {
                    if(TTV.size()!=0){
                        PanelExport.InPDFTheThanhVienTheoSearch(TTV, SEARCH, MANV, " ĐÃ KHÓA");
                        panel.XuatPDF(model);
                    }else{
                        TienIch.CustomMessage("Không có gì để in!");
                    }
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