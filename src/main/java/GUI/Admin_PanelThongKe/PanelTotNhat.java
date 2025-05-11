package GUI.Admin_PanelThongKe;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BLL.BaoCaoNhanVienBLL;
import DTO.NhanVienDTO;
import DTO.SearchNhanVienDTO;
import GUI.ComponentCommon.*;
import java.util.List;

public class PanelTotNhat extends JPanel implements ActionListener {
    StyledTable tb; // Thay JTable bằng StyledTable
    DefaultTableModel model;
    JScrollPane scr;
    public ArrayList<NhanVienDTO> DsNV;;
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
        sukien();
    }

    public void sukien() {
        from.addPropertyChangeListener("date", _ -> {
            if (from.getDate() != null && to.getDate() != null) {
                Date select1 = new java.sql.Date(from.getDate().getTime());
                Date select2 = new java.sql.Date(to.getDate().getTime());
                if (select1.after(select2)) {
                    TienIch.CustomMessage("Ngày bắt đầu không thể lớn hơn ngày kết thúc");
                    return;
                }
                System.out.println("Ngày được chọn (SQL) từ: " + select1);
                System.out.println("Ngày được chọn (SQL) tới: " + select2);
                DsNV = BaoCaoNhanVienBLL.getTopNhanVienByDoanhSo(select1, select2);
                loadNhanVien(DsNV);
            } else {
                TienIch.CustomMessage("Không thể để trống");
                from.setDate(new Date(System.currentTimeMillis()));
            }
        });

        to.addPropertyChangeListener("date", _ -> {
            if (from.getDate() != null && to.getDate() != null) {
                Date select1 = new java.sql.Date(from.getDate().getTime());
                Date select2 = new java.sql.Date(to.getDate().getTime());
                if (select1.after(select2)) {
                    TienIch.CustomMessage("Ngày bắt đầu không thể lớn hơn ngày kết thúc");
                    return;
                }
                System.out.println("Ngày được chọn (SQL) từ: " + select1);
                System.out.println("Ngày được chọn (SQL) tới: " + select2);
                DsNV = BaoCaoNhanVienBLL.getTopNhanVienByDoanhSo(select1, select2);
                loadNhanVien(DsNV);
            } else {
                TienIch.CustomMessage("Không thể để trống");
                to.setDate(new Date(System.currentTimeMillis()));
            }
        });
    }
    public String MANV;
    public PanelTotNhat(String MANV) {
        this.MANV = MANV;
        TienIch.taoTitleBorder(this, "Danh sách nhân viên có doanh số tốt nhất");
        setLayout(new BorderLayout());
        initPanelTool();
        add(pnTool, BorderLayout.NORTH);

        String[] tencot = { "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại" };
        Object[][] data = new Object[0][tencot.length]; // Dữ liệu rỗng
        tb = new StyledTable(data, tencot); // Khởi tạo StyledTable
        model = (DefaultTableModel) tb.getModel();
        DsNV = BaoCaoNhanVienBLL.getTopNhanVienByDoanhSo(new java.sql.Date(from.getDate().getTime()),
        new java.sql.Date(to.getDate().getTime()));
        loadNhanVien(DsNV);
        StyledTable.TableEvent(tb, model, "NV", MANV); // Giữ sự kiện double-click
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
    public SearchNhanVienDTO SEARCH= new SearchNhanVienDTO();
    public ArrayList<String> SEARCH2 = new ArrayList<>();
    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == searchItem) {
            PanelTimVN panel = new PanelTimVN();
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == 0) {
                DsNV = panel.ketqua(new Date(from.getDate().getTime()), new Date(to.getDate().getTime()));
                if(DsNV.size()!=0){
                    SEARCH = panel.trasearch();
                    SEARCH2 = panel.stringsearch();
                } else{
                    TienIch.CustomMessage("Không tìm thấy");
                }
                loadNhanVien(DsNV);
            }
        } else if (e.getSource() == exportItem) {
            PanelExport panel = new PanelExport();
            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (panel.getSelectedFormat().equals("excel")) {
                    if(DsNV.size() == 0){
                        TienIch.CustomMessage("Không có gì để xuất");
                    } else {
                        try {
                            // Chuẩn bị dữ liệu cho exportToExcel
                            ArrayList<List<Object>> data = new ArrayList<>();
                            for (NhanVienDTO nhanvien : DsNV) {
                                List<Object> row = new ArrayList<>();
                                row.add(nhanvien.getMaNV());
                                row.add(nhanvien.getTenNV());
                                row.add(nhanvien.getGioiTinh());
                                row.add(nhanvien.getNgaySinh());
                                row.add(nhanvien.getCCCD());
                                row.add(nhanvien.getDiaChi());
                                row.add(nhanvien.getSDT());
                                row.add(TienIch.formatVND(nhanvien.getLuong()));
                                data.add(row);
                            }
                            String[] columnNames = { "Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh", "CCCD", "Địa chỉ", "Số điện thoại", "Lương"};
                            String title = "DANH SÁCH NHÂN VIÊN CÓ DOANH SỐ BÁN HÀNG TỐT NHẤT";
                            String manv = this.MANV;
                            // Gọi hàm exportToExcel
                            XuatFileExccel.exportToExcel(data, columnNames, title, manv, SEARCH2);
                        } catch (Exception ex) {
                            TienIch.CustomMessage("Lỗi khi xuất file Excel: " + ex.getMessage());
                        }
                    }
                } else {
                    if(DsNV.size()!=0){
                        PanelExport.InPDFNhanVienTheoTotNhatSearch(DsNV, SEARCH, new Date(from.getDate().getTime()), new Date(to.getDate().getTime()), MANV);
                    } else {
                        TienIch.CustomMessage("Không có gì để in");
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