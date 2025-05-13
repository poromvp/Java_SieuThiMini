package GUI.Admin_TheThanhVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BLL.TheThanhVienBLL;
import DTO.SearchTheThanhVienDTO;
import DTO.TheThanhVienDTO;
import GUI.Admin_PanelThongKe.PanelExport;
import GUI.Admin_PanelThongKe.PanelTimKH;
import GUI.Admin_PanelThongKe.XuatFileExccel;
import GUI.ComponentCommon.StyledTable;
import GUI.ComponentCommon.TienIch;

public class PanelMainThanhVien extends JPanel implements ActionListener, MouseListener {
    JPanel pn1, pn2;
    JButton btnThem, btnKhoa, btnSua, btnTim, btnXemBlock, btnIn;
    StyledTable tb;
    DefaultTableModel model;
    private ArrayList<TheThanhVienDTO> TTV;
    SearchTheThanhVienDTO SEARCH = new SearchTheThanhVienDTO();

    // Khởi tạo panel với tiêu đề và viền
    private void initPanel(JPanel pnl, String title) {
        pnl.setBorder(new CompoundBorder(new TitledBorder(title), new EmptyBorder(4, 4, 4, 4)));
    }

    // Khởi tạo panel chứa các nút hành động
    private void initPanel1(JPanel pn1) {
        initPanel(pn1, "Hành động");
        pn1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        btnThem = new JButton("Thêm");
        btnThem.setToolTipText("Click chuột phải để nhập bằng danh sách");
        btnKhoa = new JButton("Khóa");
        btnKhoa.setToolTipText("Click chuột phải để mở khóa");
        btnSua = new JButton("Sửa");
        btnTim = new JButton("Tìm kiếm");
        btnXemBlock = new JButton("<html><center>DS TTV Đã Khóa</center><html>");
        btnIn = new JButton("Xuất danh sách");

        // Thiết lập style cho các nút
        TienIch.nutStyle(btnThem, "addIcon.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnKhoa, "lock.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnSua, "editIcon.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnTim, "search.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnXemBlock, "list.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnIn, "printer.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);

        // Đặt vị trí và khoảng cách cho các nút
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;

        gbc.gridx = 0;
        pn1.add(btnThem, gbc);
        gbc.gridx = 1;
        pn1.add(btnKhoa, gbc);
        gbc.gridx = 2;
        pn1.add(btnSua, gbc);
        gbc.gridx = 3;
        pn1.add(btnTim, gbc);
        gbc.gridx = 4;
        pn1.add(btnXemBlock, gbc);
        gbc.gridx = 5;
        pn1.add(btnIn, gbc);

        btnThem.addActionListener(this);
        btnThem.addMouseListener(this);
        btnKhoa.addActionListener(this);
        btnKhoa.addMouseListener(this);
        btnSua.addActionListener(this);
        btnTim.addActionListener(this);
        btnXemBlock.addActionListener(this);
        btnIn.addActionListener(this);
    }

    // Khởi tạo panel chứa bảng danh sách thành viên
    private void initPanel2(JPanel pn2) {
        initPanel(pn2, "Danh sách");
        pn2.setLayout(new BorderLayout());
        String[] tencot = { "Mã thành viên", "Họ tên", "Số điện thoại", "Điểm tích lũy" };
        Object[][] data = new Object[0][tencot.length];
        tb = new StyledTable(data, tencot);
        model = (DefaultTableModel) tb.getModel();
        TTV = TheThanhVienBLL.getAllMembersACTIVE();
        loadThanhVien(TTV);
        StyledTable.hoverTable(tb, model);
        StyledTable.TableEvent(tb, model, "KH", MANV);
        JScrollPane scr = new JScrollPane(tb);
        pn2.add(scr, BorderLayout.CENTER);
    }

    // Tải dữ liệu thành viên vào bảng
    private void loadThanhVien(ArrayList<TheThanhVienDTO> ttv) {
        model.setRowCount(0);
        for (TheThanhVienDTO tv : ttv) {
            LocalDate today = LocalDate.now();
            if (tv.getNgayKT().before(Date.valueOf(today))) {
                if (TheThanhVienBLL.deleteMember(tv.getMaTV())) {
                    System.out.println("Đã khóa " + tv.getMaTV());
                    continue;
                }
            }
            model.addRow(new Object[] {
                    tv.getMaTV(),
                    tv.getTenTV(),
                    tv.getSdt(),
                    tv.getDiemTL()
            });
        }
    }

    public String MANV;

    public PanelMainThanhVien(String MANV) {
        this.MANV = MANV;
        setLayout(new BorderLayout());

        pn1 = new JPanel();
        initPanel1(pn1);
        pn1.setPreferredSize(new Dimension(0, 120));
        add(pn1, BorderLayout.NORTH);

        pn2 = new JPanel();
        initPanel2(pn2);
        add(pn2, BorderLayout.CENTER);
    }

    public ArrayList<String> SEARCH2 = new ArrayList<>();

    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == btnTim) {
            PanelTimKH panel = new PanelTimKH();
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == 0) {
                TTV = panel.ketqua();
                if (TTV.size() == 0) {
                    TienIch.CustomMessage("Không tìm thấy thẻ thành viên với tiêu chí như vậy!");
                } else {
                    SEARCH = panel.traSearch();
                    SEARCH2 = panel.stringSearch();
                }
                loadThanhVien(TTV);
            }
        } else if (e.getSource() == btnIn) {
            PanelExport panel = new PanelExport();
            int result = JOptionPane.showConfirmDialog(null, panel, "Export",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (panel.getSelectedFormat().equals("excel")) {
                    if (TTV.size() == 0) {
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
                            String[] columnNames = { "Mã thành viên", "Họ tên", "Ngày sinh", "Địa chỉ", "Điểm tích lũy",
                                    "Số điện thoại", "Ngày bắt đầu", "Ngày kết thúc" };
                            String title = "DANH SÁCH THẺ THÀNH VIÊN";
                            String manv = this.MANV;
                            // Gọi hàm exportToExcel
                            XuatFileExccel.exportToExcel(data, columnNames, title, manv, SEARCH2);
                        } catch (Exception ex) {
                            TienIch.CustomMessage("Lỗi khi xuất file Excel: " + ex.getMessage());
                        }
                    }
                } else {
                    if (TTV.size() != 0) {
                        PanelExport.InPDFTheThanhVienTheoSearch(TTV, SEARCH, MANV, "");
                    } else {
                        TienIch.CustomMessage("Không có gì để in");
                    }
                }
            } else if (result == JOptionPane.CANCEL_OPTION) {
                TienIch.CustomMessage("Đã hủy xuất file");
            } else {
                TienIch.CustomMessage("Đã hủy xuất file");
            }
        } else if (e.getSource() == btnXemBlock) {
            PanelXemDSLock panel = new PanelXemDSLock(MANV);
            JOptionPane.showMessageDialog(null, panel, "Danh sách TTV đã khóa", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == btnThem) {
            while (true) {
                PanelThemThanhVien panel = new PanelThemThanhVien();
                int result = JOptionPane.showConfirmDialog(null, panel, "Thêm",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    int check = panel.ktranull();
                    if (check == 1) {
                        String kq = TheThanhVienBLL.addMember(panel.create1TV());
                        TTV = TheThanhVienBLL.getAllMembersACTIVE();
                        TienIch.CustomMessage(kq);
                        loadThanhVien(TTV);
                        break;
                    } else if (check == 6) {
                        TienIch.CustomMessage("Số điện thoại phải gồm 10 số");
                    } else {
                        TienIch.CustomMessage("Phải nhập đầy đủ thông tin");
                    }
                } else {
                    TienIch.CustomMessage("Đã hủy thêm thành viên!");
                    break;
                }
            }
        } else if (e.getSource() == btnSua) {
            while (true) {
                Integer maTV = TienIch.getValidMemberId("Nhập Mã Thành Viên Cần Sửa", "");
                if (maTV != null) {
                    TheThanhVienDTO member = TheThanhVienBLL.getMemberById(maTV);
                    if (member != null) {
                        if (member.getTrangThai().equals("INACTIVE")) {
                            TienIch.CustomMessage("Thành viên này đã bị khóa, không thể sửa");
                        } else {
                            while (true) {
                                PanelSuaThanhVien panel = new PanelSuaThanhVien(member);
                                int result = JOptionPane.showConfirmDialog(null, panel, "Sửa thông tin thành viên",
                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                                if (result == JOptionPane.OK_OPTION) {
                                    int check = panel.ktranull();
                                    if (check == 1) {
                                        boolean success = TheThanhVienBLL.updateMember(panel.getDTOTheThanhVien());
                                        if (success) {
                                            TienIch.CustomMessage("Cập nhật thành viên thành công!");
                                            TTV = TheThanhVienBLL.getAllMembersACTIVE();
                                            loadThanhVien(TTV);
                                            break;
                                        } else {
                                            if (TheThanhVienBLL
                                                    .getMemberByPhone(panel.getDTOTheThanhVien().getSdt()) != null) {
                                                TienIch.CustomMessage(
                                                        "Cập nhật thất bại do số điện thoại này đã có người dùng rồi");
                                            } else {
                                                TienIch.CustomMessage("Cập nhật thất bại");
                                                break;
                                            }
                                        }
                                    } else if (check == 6) {
                                        TienIch.CustomMessage("Số điện thoại phải có 10 số");
                                    } else {
                                        TienIch.CustomMessage("Không được để trống");
                                    }
                                } else {
                                    TienIch.CustomMessage("Đã hủy sửa thành viên này");
                                    break;
                                }
                            }
                        }
                    } else {
                        TienIch.CustomMessage("Mã thành viên không tồn tại");
                    }
                } else {
                    break;
                }
            }
        } else if (e.getSource() == btnKhoa) {
            while (true) {
                Integer maTV = TienIch.getValidMemberId("Nhập Mã Thành Viên Cần Khóa", "");
                if (maTV != null) {
                    TheThanhVienDTO member = TheThanhVienBLL.getMemberById(maTV);
                    if (member != null) {
                        if (TheThanhVienBLL.getMemberById(maTV).getTrangThai().equals("INACTIVE")) {
                            TienIch.CustomMessage("Thành viên này đã khóa sẵn rồi!");
                        } else {
                            boolean success = TheThanhVienBLL.deleteMember(maTV);
                            if (success) {
                                TienIch.CustomMessage("Khóa thành viên thành công!");
                                TTV = TheThanhVienBLL.getAllMembersACTIVE();
                                loadThanhVien(TTV);
                                break;
                            } else {
                                TienIch.CustomMessage("Khóa thành viên thất bại");
                                break;
                            }
                        }
                    } else {
                        TienIch.CustomMessage("Mã thành viên không tồn tại");
                    }
                } else {
                    break;
                }
            }
        }
        TienIch.resetUI();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        TienIch.setDarkUI();
        if (SwingUtilities.isRightMouseButton(e)) {
            if (e.getSource() == btnThem) {
                // Nhấp chuột phải vào btnThem: Nhập từ file Excel
                TienIch.resetUI();
                TienIch.setlookandfeel(true, null);
                JFileChooser chooser = new JFileChooser("src/main/resources/file/import");
                chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("File Excel", "xlsx", "xls"));
                int result = chooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    TienIch.setlookandfeel(false, null);
                    TienIch.setDarkUI();
                    File file = chooser.getSelectedFile();
                    try {
                        ArrayList<TheThanhVienDTO> members = ExcelImporter.importFromExcel(file);
                        for (TheThanhVienDTO member : members) {
                            String kq = TheThanhVienBLL.addMember(member);
                            if (!kq.contains("Thêm thành viên thành công!")) {
                                TienIch.CustomMessage("Lỗi khi thêm thành viên: " + kq);
                            }
                        }
                        TTV = TheThanhVienBLL.getAllMembersACTIVE();
                        loadThanhVien(TTV);
                    } catch (Exception ex) {
                        TienIch.CustomMessage("Lỗi khi nhập từ Excel: " + ex.getMessage());
                    }
                } else {
                    TienIch.setlookandfeel(false, null);
                    TienIch.setDarkUI();
                    TienIch.CustomMessage("Hủy import");
                }
                TienIch.setlookandfeel(false, null);
            } else if (e.getSource() == btnKhoa) {
                while (true) {
                    // Nhấp chuột phải vào btnKhoa: Mở khóa thành viên
                    Integer maTV = TienIch.getValidMemberId("Nhập Mã Thành Viên Cần Mở Khóa", "");
                    if (maTV != null) {
                        TheThanhVienDTO member = TheThanhVienBLL.getMemberById(maTV);
                        if (member != null) {
                            if (member.getTrangThai().equals("ACTIVE")) {
                                TienIch.CustomMessage("Thành viên này đã mở khóa sẵn rồi!");
                            } else {
                                boolean success = TheThanhVienBLL.UndeleteMember(maTV, member.getNgayKT());
                                if (success) {
                                    TienIch.CustomMessage("Mở khóa thành viên thành công!");
                                    TTV = TheThanhVienBLL.getAllMembersACTIVE();
                                    loadThanhVien(TTV);
                                    break;
                                } else {
                                    TienIch.CustomMessage("Mở khóa thành viên thất bại");
                                    break;
                                }
                            }
                        } else {
                            TienIch.CustomMessage("Mã thành viên không tồn tại");
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        TienIch.resetUI();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
}