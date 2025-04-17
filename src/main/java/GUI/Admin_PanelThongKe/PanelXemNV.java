package GUI.Admin_PanelThongKe;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import GUI.TienIch;

import java.awt.*;
import java.util.ArrayList;

public class PanelXemNV extends JPanel {
    
    JPanel pn1, pn2, pn3;
    public PanelXemNV(DefaultTableModel model, int dong) {
        setBackground(new Color(226, 224, 221));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        pn1 = new JPanel();
        pn2 = new JPanel();
        pn3 = new JPanel();

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        initPanel1(model, dong);
        add(pn1,gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        initPanel2();
        add(pn2,gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=1;
        initPanel3();
        add(pn3,gbc);
    }

    public String LayChuoiTuBang(DefaultTableModel model, int dong, int cot) {
        Object txt = model.getValueAt(dong, cot);
        return txt.toString();
    }
    
    JTextArea GioiThieu;
    JLabel lbTenNV, lbNgSInh, lbDchi, lbSDT, lbChucVu, lbID;
    public void initPanel1(DefaultTableModel model, int dong){
        pn1.setBorder(new CompoundBorder(new TitledBorder("Thông tin chi tiết"), new EmptyBorder(4, 4, 4, 4)));
        pn1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx=1.0;
        gbc.weighty=1.0;
        gbc.fill=GridBagConstraints.BOTH;

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=6;
        JLabel avt = new JLabel();
        TienIch.labelStyle(avt, 0, 80, "boy.png");
        pn1.add(avt,gbc);
        gbc.gridheight=1;

        gbc.gridx=1;
        gbc.gridy=0;
        JLabel ten = new JLabel("Tên NV: ");
        TienIch.labelStyle(ten, 4, 15, null);
        pn1.add(ten,gbc);
        
        gbc.gridx=2;
        gbc.gridy=0;
        lbTenNV = new JLabel(LayChuoiTuBang(model, dong, 0));
        TienIch.labelStyle(lbTenNV, 2, 15, null);
        pn1.add(lbTenNV,gbc);
        
        gbc.gridx=1;
        gbc.gridy=1;
        JLabel namsinh = new JLabel("Năm sinh: ");
        TienIch.labelStyle(namsinh, 4, 15, null);
        pn1.add(namsinh,gbc);
        
        gbc.gridx=2;
        gbc.gridy=1;
        lbNgSInh = new JLabel(LayChuoiTuBang(model, dong, 0));
        TienIch.labelStyle(lbNgSInh, 2, 15, null);
        pn1.add(lbNgSInh,gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        JLabel dchi = new JLabel("Địa chỉ: ");
        TienIch.labelStyle(dchi, 4, 15, null);
        pn1.add(dchi,gbc);
        
        gbc.gridx=2;
        gbc.gridy=2;
        lbDchi = new JLabel(LayChuoiTuBang(model, dong, 0));
        TienIch.labelStyle(lbDchi, 2, 15, null);
        pn1.add(lbDchi,gbc);

        gbc.gridx=1;
        gbc.gridy=3;
        JLabel sdt = new JLabel("SĐT: ");
        TienIch.labelStyle(sdt, 4, 15, null);
        pn1.add(sdt,gbc);
        
        gbc.gridx=2;
        gbc.gridy=3;
        lbSDT = new JLabel(LayChuoiTuBang(model, dong, 0));
        TienIch.labelStyle(lbSDT, 2, 15, null);
        pn1.add(lbSDT,gbc);

        gbc.gridx=1;
        gbc.gridy=4;
        JLabel cvu = new JLabel("Chức vụ:");
        TienIch.labelStyle(cvu, 4, 15, null);
        pn1.add(cvu,gbc);
        
        gbc.gridx=2;
        gbc.gridy=4;
        lbChucVu = new JLabel(LayChuoiTuBang(model, dong, 0));
        TienIch.labelStyle(lbChucVu, 2, 15, null);
        pn1.add(lbChucVu,gbc);

        gbc.gridx=1;
        gbc.gridy=5;
        JLabel id = new JLabel("ID:");
        TienIch.labelStyle(id, 4, 15, null);
        pn1.add(id,gbc);
        
        gbc.gridx=2;
        gbc.gridy=5;
        lbID = new JLabel(LayChuoiTuBang(model, dong, 0));
        TienIch.labelStyle(lbID, 2, 15, null);
        pn1.add(lbID,gbc);

        gbc.gridx=3;
        gbc.gridy=0;
        JLabel gthieu = new JLabel("Giới thiệu: ");
        TienIch.labelStyle(gthieu, 1, 15, null);
        pn1.add(gthieu,gbc);
        
        gbc.gridx=4;
        gbc.gridy=0;
        gbc.gridwidth=6;
        gbc.gridheight=6;
        GioiThieu = new JTextArea(6,6);
        GioiThieu.setEditable(false);
        GioiThieu.setText("tôi thích công việc này, bla bla...");
        pn1.add(GioiThieu,gbc);
    }

    JLabel lbTongDonHang, lbDoanhSo, lbLuong;
    public void initPanel2(){
        pn2.setBorder(new CompoundBorder(new TitledBorder("Tóm tắt"), new EmptyBorder(4, 4, 4, 4)));
        pn2.setLayout(new GridLayout(3,2));
        
        JLabel tongdh = new JLabel("<html>Tổng đơn hàng <br>đã thực hiện: </html>");
        TienIch.labelStyle(tongdh, 4, 15, null);
        pn2.add(tongdh);
        lbTongDonHang = new JLabel("123");
        TienIch.labelStyle(lbTongDonHang, 2, 15, null);
        pn2.add(lbTongDonHang);

        JLabel doanhs = new JLabel("<html>Doanh số <br>bán hàng: </html>");
        TienIch.labelStyle(doanhs, 4, 15, null);
        pn2.add(doanhs);
        lbDoanhSo = new JLabel("1,000,000" + " VND");
        TienIch.labelStyle(lbDoanhSo, 2, 15, null);
        pn2.add(lbDoanhSo);

        JLabel luong = new JLabel("Lương: ");
        TienIch.labelStyle(luong, 4, 15, null);
        pn2.add(luong);
        lbLuong = new JLabel("7,000,000" + " VND");
        TienIch.labelStyle(lbLuong, 2, 15, null);
        pn2.add(lbLuong);
    }

    JTable tb;
    DefaultTableModel modelMini;
    JScrollPane scr;
    public ArrayList<hoadontemp> dsHoaDon= new ArrayList<>();
    public void initPanel3(){
        pn3.setLayout(new FlowLayout());
        pn3.setBorder(new CompoundBorder(new TitledBorder("Danh sách các đơn hàng đã thanh toán"), new EmptyBorder(4, 4, 4, 4)));
        String[] tencot = {"ID", "Tên", "Price", "Date"};
        hoadontemp a = new hoadontemp("1", "Cam", "10,000", "10/10/2025");
        hoadontemp b = new hoadontemp("3", "Cam", "10,000", "10/10/2025");
        hoadontemp c = new hoadontemp("4", "Cam", "10,000", "10/10/2025");
        hoadontemp d = new hoadontemp("2", "Cam", "10,000", "10/10/2025");
        dsHoaDon.add(a);
        dsHoaDon.add(b);
        dsHoaDon.add(c);
        dsHoaDon.add(d);
        modelMini = new DefaultTableModel(tencot, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        refreshTable();
        tb = new JTable(modelMini);
        TableControl.TableStyle(tb, modelMini);
        TableControl.TableEvent(tb, modelMini, "HD");
        scr = new JScrollPane(tb);
        scr.setPreferredSize(new Dimension(400, 120)); // Điều chỉnh chiều rộng và chiều cao của bảng
        pn3.add(scr);
    }
    private void refreshTable() {
        modelMini.setRowCount(0);  // Xóa toàn bộ dữ liệu cũ
        for (hoadontemp s : dsHoaDon) {
            modelMini.addRow(new Object[]{s.getId(), s.getName(), s.getPrice(), s.getDate()});
        }
    }
}
