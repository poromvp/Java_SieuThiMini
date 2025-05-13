
package GUI.Admin_TheThanhVien;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import BLL.TheThanhVienBLL;
import DTO.TheThanhVienDTO;
import GUI.ComponentCommon.StyledTextField;
import GUI.ComponentCommon.TienIch;

public class PanelSuaThanhVien extends JPanel {
    private StyledTextField txtTenTV;
    private StyledTextField txtDiaChi;
    private StyledTextField txtSDT;
    private StyledTextField txtDiemTL;
    private StyledTextField txtNgayBD;
    private JLabel lblImagePreview;
    private JDateChooser dateNgaySinh;
    private JDateChooser dateNgayKT;
    private String tenAnh;
    private int maTV;

    public PanelSuaThanhVien(TheThanhVienDTO member) {
        this.maTV = member.getMaTV();
        setLayout(new BorderLayout(10, 10));
        TienIch.taoTitleBorder(this, "Sửa thông tin thẻ thành viên");

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));

        // Các thành phần nhập liệu
        txtTenTV = new StyledTextField();
        TienIch.chiDuocNhapChu(txtTenTV);
        dateNgaySinh = new JDateChooser();
        txtDiaChi = new StyledTextField();
        txtSDT = new StyledTextField();
        JButton btnChonAnh = new JButton("Chọn ảnh");
        txtDiemTL = new StyledTextField();
        txtDiemTL.setEnabled(false);
        txtNgayBD = new StyledTextField();
        txtNgayBD.setEnabled(false);
        dateNgayKT = new JDateChooser();

        dateNgaySinh.setDateFormatString("dd/MM/yyyy");
        dateNgaySinh.setMaxSelectableDate(new java.util.Date());
        TienIch.checkngaynhaptutay(dateNgaySinh, member.getNgaySinh());
        TienIch.checkngaynhapdutuoi(dateNgaySinh, member.getNgaySinh());

        dateNgayKT.setDateFormatString("dd/MM/yyyy");
        dateNgayKT.setMinSelectableDate(TienIch.addTwoYearsToDate(member.getNgayBD()));
        TienIch.checkngayKT(dateNgayKT, member.getNgayBD(), member.getNgayKT());

        // Label hiển thị ảnh
        lblImagePreview = new JLabel("Chưa chọn ảnh", JLabel.CENTER);
        lblImagePreview.setPreferredSize(new Dimension(200, 350));
        lblImagePreview.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Load thông tin thành viên
        if (member != null) {
            txtTenTV.setText(member.getTenTV());
            dateNgaySinh.setDate(member.getNgaySinh());
            //System.out.println("Ngày sinh: "+TienIch.ddmmyyyy(dateNgaySinh.getDate()));
            txtDiaChi.setText(member.getDiaChi());
            txtSDT.setText(member.getSdt());
            tenAnh = member.getTenAnh();
            txtDiemTL.setText(member.getDiemTL() + "");
            txtNgayBD.setText(TienIch.ddmmyyyy(member.getNgayBD()));
            dateNgayKT.setDate(member.getNgayKT());
            if (tenAnh != null && !tenAnh.isEmpty()) {
                try {
                    TienIch.anhAVT(lblImagePreview, tenAnh, 200, 350, "KH");
                } catch (Exception e) {
                    lblImagePreview.setText("Lỗi tải ảnh");
                }
            }
        }

        // Sự kiện chọn ảnh
        btnChonAnh.addActionListener(_ -> {
            TienIch.resetUI();
            TienIch.setlookandfeel(true, null);
            JFileChooser chooser = new JFileChooser("src/main/resources/images/avtMember");
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                TienIch.setDarkUI();
                TienIch.setlookandfeel(false, null);
                File file = chooser.getSelectedFile();
                tenAnh = file.getName();
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(200, 350, Image.SCALE_SMOOTH);
                lblImagePreview.setIcon(new ImageIcon(img));
                lblImagePreview.setText("");
            }
            TienIch.setDarkUI();
            TienIch.setlookandfeel(false, null);
        });

        // Thêm vào panel
        formPanel.add(new JLabel("Tên thành viên:"));
        formPanel.add(txtTenTV);
        formPanel.add(new JLabel("Ngày sinh:"));
        formPanel.add(dateNgaySinh);
        formPanel.add(new JLabel("Địa chỉ:"));
        formPanel.add(txtDiaChi);
        formPanel.add(new JLabel("SĐT:"));
        formPanel.add(txtSDT);
        formPanel.add(new JLabel("Điểm tích lũy:"));
        formPanel.add(txtDiemTL);
        formPanel.add(new JLabel("Ngày bắt đầu:"));
        formPanel.add(txtNgayBD);
        formPanel.add(new JLabel("Ngày kết thúc:"));
        formPanel.add(dateNgayKT);
        formPanel.add(new JLabel("Ảnh đại diện:"));
        formPanel.add(btnChonAnh);

        add(formPanel, BorderLayout.CENTER);
        add(lblImagePreview, BorderLayout.EAST);

        TienIch.chiduocnhapso(txtSDT);

        TienIch.chiduocnhapDDMMYYYY(dateNgaySinh);
        TienIch.chiduocnhapDDMMYYYY(dateNgayKT);
    }

    public int ktranull(){
        if(txtTenTV.getText().isEmpty()){
            return 2;
        }
        if(dateNgaySinh.getDate() == null){
            return 3;
        }
        if(txtDiaChi.getText().isEmpty()){
            return 4;
        }
        if(txtSDT.getText().isEmpty()){
            return 5;
        }
        if(txtSDT.getText().length()!=10){
            return 6;
        }
        if(dateNgayKT.getDate() == null){
            return 7;
        }
        return 1;
    }

    public TheThanhVienDTO getDTOTheThanhVien() {
        TheThanhVienDTO dto = new TheThanhVienDTO();
        dto.setNgaySinh(new java.sql.Date(dateNgaySinh.getDate().getTime()));
        System.out.println("Ngày sinh: " + TienIch.ddmmyyyy(dateNgaySinh.getDate()));
        dto.setNgayKT(new java.sql.Date(dateNgayKT.getDate().getTime()));
        dto.setTenTV(txtTenTV.getText());
        dto.setDiaChi(txtDiaChi.getText());
        dto.setSdt(txtSDT.getText());
        dto.setTenAnh(tenAnh);
        // Giữ nguyên các giá trị khác
        TheThanhVienDTO existing = TheThanhVienBLL.getMemberById(maTV);
        if (existing != null) {
            dto.setMaTV(existing.getMaTV());
            dto.setDiemTL(existing.getDiemTL());
            dto.setNgayBD(existing.getNgayBD());
            dto.setTrangThai(existing.getTrangThai());
        }
        return dto;
    }
}
