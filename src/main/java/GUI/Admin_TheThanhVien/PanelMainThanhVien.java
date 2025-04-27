package GUI.Admin_TheThanhVien;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import BLL.TheThanhVienBLL;
import DTO.TheThanhVienDTO;
import GUI.Admin_PanelThongKe.PanelExport;
import GUI.Admin_PanelThongKe.PanelTimKH;
import GUI.ComponentCommon.*;

public class PanelMainThanhVien extends JPanel implements ActionListener, MouseListener {
    JPanel pn1, pn2;
    JButton btnThem, btnKhoa, btnSua, btnTim, btnXemBlock, btnIn;
    StyledTable tb;
    DefaultTableModel model;
    private ArrayList<TheThanhVienDTO> TTV = TheThanhVienBLL.getAllMembersACTIVE();

    private void initPanel(JPanel pnl, String title) {
        pnl.setBorder(new CompoundBorder(new TitledBorder(title), new EmptyBorder(4, 4, 4, 4)));
    }

    private void initPanel1(JPanel pn1) {
        initPanel(pn1, "Hành động");
        pn1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        btnThem = new JButton("Thêm");
        btnKhoa = new JButton("Khóa");
        btnSua = new JButton("Sửa");
        btnTim = new JButton("Tìm kiếm");
        btnXemBlock = new JButton("<html><center>DS TTV Đã Khóa</center><html>");
        btnIn = new JButton("Xuất danh sách");

        // Thiết lập style cho từng nút
        TienIch.nutStyle(btnThem, "addIcon.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnKhoa, "lock.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnSua, "editIcon.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnTim, "search.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnXemBlock, "list.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);
        TienIch.nutStyle(btnIn, "printer.png", 14, 24, 12, new Color(238, 238, 238), Color.GRAY, Color.DARK_GRAY);

        // Đặt khoảng cách và vị trí cho từng nút
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
        btnKhoa.addActionListener(this);
        btnKhoa.addMouseListener(this);
        btnSua.addActionListener(this);
        btnTim.addActionListener(this);
        btnXemBlock.addActionListener(this);
        btnIn.addActionListener(this);
    }

    private void initPanel2(JPanel pn2) {
        initPanel(pn2, "Danh sách");
        pn2.setLayout(new BorderLayout());
        String[] tencot = { "Mã thành viên", "Họ tên", "Số điện thoại", "Điểm tích lũy" };
        Object[][] data = new Object[0][tencot.length];
        tb = new StyledTable(data, tencot);
        model = (DefaultTableModel) tb.getModel();
        loadThanhVien(TTV);
        StyledTable.hoverTable(tb, model);
        StyledTable.TableEvent(tb, model, "KH");
        JScrollPane scr = new JScrollPane(tb);
        pn2.add(scr, BorderLayout.CENTER);
    }

    private void loadThanhVien(ArrayList<TheThanhVienDTO> ttv) {
        model.setRowCount(0);
        ttv = TheThanhVienBLL.getAllMembersACTIVE();
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
        pn1.setPreferredSize(new Dimension(0, 120));
        add(pn1, BorderLayout.NORTH);

        pn2 = new JPanel();
        initPanel2(pn2);
        add(pn2, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TienIch.setDarkUI();
        if (e.getSource() == btnTim) {
            PanelTimKH panel = new PanelTimKH();
            int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin muốn tìm kiếm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == 0) {
                System.out.println("Bạn vừa nhập: ");
            }
        } else if (e.getSource() == btnIn) {
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
        } else if (e.getSource() == btnXemBlock) {
            PanelXemDSLock panel = new PanelXemDSLock();
            int result = JOptionPane.showConfirmDialog(null, panel, "Danh sách TTV đã khóa",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                TienIch.CustomMessage("Đã hủy xem danh sách thẻ thành viên bị khóa");
            }
        } else if (e.getSource() == btnThem) {
            PanelThemThanhVien panel = new PanelThemThanhVien();
            int result = JOptionPane.showConfirmDialog(null, panel, "Thêm",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (panel.ktraBieuThucChinhQuy()) {
                    String kq = TheThanhVienBLL.addMember(panel.create1TV());
                    TienIch.CustomMessage(kq);
                    loadThanhVien(TTV);
                } else {
                    TienIch.CustomMessage("Hãy nhập đầy đủ thông tin");
                }
            } else {
                TienIch.CustomMessage("Đã hủy thêm thành viên!");
            }
        } else if (e.getSource() == btnSua) {
            String maTV = JOptionPane.showInputDialog(null, "", "Nhập Mã Thành Viên Cần Sửa",
                    JOptionPane.PLAIN_MESSAGE);
            if (maTV != null && !maTV.trim().isEmpty()) {
                TheThanhVienDTO member = TheThanhVienBLL.getMemberById(Integer.parseInt(maTV));
                if (member != null) {
                    if (member.getTrangThai().equals("INACTIVE")) {
                        TienIch.CustomMessage("Thành viên này đã bị khóa, không thể sửa");
                    } else {
                        PanelSuaThanhVien panel = new PanelSuaThanhVien(member);
                        int result = JOptionPane.showConfirmDialog(null, panel, "Sửa thông tin thành viên",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                        if (result == JOptionPane.OK_OPTION) {
                            boolean success = TheThanhVienBLL.updateMember(panel.getDTOTheThanhVien());
                            if (success) {
                                TienIch.CustomMessage("Cập nhật thành viên thành công!");
                                loadThanhVien(TTV);
                            } else {
                                TienIch.CustomMessage("Cập nhật thất bại");
                            }
                        } else {
                            TienIch.CustomMessage("Đã hủy sửa thành viên");
                        }
                    }
                } else {
                    TienIch.CustomMessage("Mã thành viên không tồn tại");
                }
            }
        } else if (e.getSource() == btnKhoa) {
            String maTV = JOptionPane.showInputDialog(null, "", "Nhập Mã Thành Viên Cần Khóa",
                    JOptionPane.PLAIN_MESSAGE);
            if (maTV != null && !maTV.trim().isEmpty()) {
                if (TheThanhVienBLL.getMemberById(Integer.parseInt(maTV)).getTrangThai().equals("INACTIVE")) {
                    TienIch.CustomMessage("Thành viên này đã khóa sẵn rồi!");
                } else {
                    boolean success = TheThanhVienBLL.deleteMember(Integer.parseInt(maTV));
                    if (success) {
                        TienIch.CustomMessage("Khóa thành viên thành công!");
                        loadThanhVien(TTV);
                    } else {
                        TienIch.CustomMessage("Khóa thành viên thất bại");
                    }
                }
            }
        }
        TienIch.resetUI();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        TienIch.setDarkUI();
        if (SwingUtilities.isRightMouseButton(e)) {
            String maTV = JOptionPane.showInputDialog(null, "", "Nhập Mã Thành Viên Cần Mở Khóa",
                    JOptionPane.PLAIN_MESSAGE);
            if (maTV != null && !maTV.trim().isEmpty()) {
                if (TheThanhVienBLL.getMemberById(Integer.parseInt(maTV)).getTrangThai().equals("ACTIVE")) {
                    TienIch.CustomMessage("Thành viên này đã mở khóa sẵn rồi!");
                } else {
                    boolean success = TheThanhVienBLL.UndeleteMember(Integer.parseInt(maTV));
                    if (success) {
                        TienIch.CustomMessage("Mở khóa thành viên thành công!");
                        loadThanhVien(TTV);
                    } else {
                        TienIch.CustomMessage("Mở khóa thành viên thất bại");
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