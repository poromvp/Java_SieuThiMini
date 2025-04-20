package GUI.FormWareHouse;

import BLL.ChiTietNhapHangBLL;
import BLL.NhapHangBLL;
import DTO.ChiTietPNHangDTO;
import DTO.PhieuNhapHangDTO;
import GUI.ComponentCommon.StyledTable;
import GUI.ComponentCommon.StyledTextField;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.Consumer;

public class FormAddImport extends JPanel {
    private StyledTable table;
    private NhapHangBLL nhapHangBLL = new NhapHangBLL();
    private ChiTietNhapHangBLL chiTietNhapBLL = new ChiTietNhapHangBLL();
    private ArrayList<ChiTietPNHangDTO> chiTietList = new ArrayList<>();
    private PhieuNhapHangDTO phieuNhap;
    private Runnable refreshCallback;

    public FormAddImport(PhieuNhapHangDTO phieuNhap,Runnable refreshCallback) {
        this.phieuNhap = phieuNhap;
        this.refreshCallback= refreshCallback;

        setLayout(new BorderLayout());
        setBackground(Color.white);

        // Panel nhập thông tin sản phẩm
        JPanel import_info = new JPanel();
        import_info.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        import_info.setLayout(new GridLayout(6, 2, 5, 5));

        import_info.add(new JLabel("Mã sản phẩm:"));
        StyledTextField maSP = new StyledTextField();
        import_info.add(maSP);

        import_info.add(new JLabel("Mã lô hàng:"));
        StyledTextField maLH = new StyledTextField();
        import_info.add(maLH);

        import_info.add(new JLabel("Số lượng:"));
        StyledTextField soLuong = new StyledTextField();
        import_info.add(soLuong);

        import_info.add(new JLabel("Giá nhập:"));
        StyledTextField gia = new StyledTextField();
        import_info.add(gia);

        JButton btnThemSanPham = new JButton("Thêm sản phẩm");

        btnThemSanPham.addActionListener(e->{
            try{
                int maSPValue = Integer.parseInt(maSP.getText().trim());
                int maLHValue = Integer.parseInt(maLH.getText().trim());
                int soLuongValue = Integer.parseInt(soLuong.getText().trim());
                double giaNhapValue = Double.parseDouble(gia.getText().trim());

                // Validate số lượng và giá nhập
                if (!chiTietNhapBLL.validateSoLuong(soLuongValue) || !chiTietNhapBLL.validateGiaNhap(giaNhapValue)) {
                    JOptionPane.showMessageDialog(this, "Số lượng và giá nhập phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ChiTietPNHangDTO chiTiet = new ChiTietPNHangDTO();
                chiTiet.setMaLH(maLHValue);
                chiTiet.setMaSP(maSPValue);
                chiTiet.setSoLuong(soLuongValue);
                chiTiet.setGiaNhap(giaNhapValue);
                chiTiet.setTrangThai("ACTIVE");

                chiTietList.add(chiTiet);
                refreshTable();

                maSP.setText("");
                maLH.setText("");
                soLuong.setText("");
                gia.setText("");

                JOptionPane.showMessageDialog(this,"Thêm sản  phẩm thành công !");

            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnHoanTat = new JButton("Hoàn tất đơn nhập hàng");

        btnHoanTat.addActionListener(e ->{
            if (chiTietList.isEmpty()){
                JOptionPane.showMessageDialog(this,"Vui lòng nhập ít nhất một sản phẩm !","Lỗi",JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = nhapHangBLL.insertPhieuNhapHang(phieuNhap);
            if(success){
                int maPNH = phieuNhap.getMaPNH();

                boolean allChiTietSaved = true;
                for(ChiTietPNHangDTO chiTiet: chiTietList){
                    chiTiet.setMaPNH(maPNH);
                    if(!chiTietNhapBLL.insertChiTietNhapHang(chiTiet)){
                        allChiTietSaved = false;
                        break;
                    }
                }
                if(allChiTietSaved){
                    JOptionPane.showMessageDialog(this, "Hoàn tất đơn nhập hàng thành công!");
                    refreshCallback.run(); // Gọi callback để làm mới bảng trong FormImport
                    SwingUtilities.getWindowAncestor(this).dispose(); // Đóng dialog
                }else {
                    JOptionPane.showMessageDialog(this, "Lưu chi tiết đơn nhập hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(this, "Lưu đơn nhập hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        });

        JButton btnHuy = new JButton("Hủy");
        btnHuy.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnThemSanPham);
        buttonPanel.add(btnHoanTat);
        buttonPanel.add(btnHuy);

        import_info.add(new JLabel());
        import_info.add(buttonPanel);

        add(import_info, BorderLayout.NORTH);

        // Panel hiển thị danh sách sản phẩm
        JPanel product_info = new JPanel();
        product_info.setLayout(new BorderLayout());
        product_info.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm đã thêm"));

        String[] headerCol = {"STT", "Mã SP", "Mã lô hàng", "Số lượng", "Giá nhập", "Thành tiền"};
        table = new StyledTable(new Object[0][6], headerCol);
        table.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        product_info.add(scrollPane, BorderLayout.CENTER);

        add(product_info, BorderLayout.CENTER);

    }

    private void refreshTable() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        Object[][] data = new Object[chiTietList.size()][6];
        for (int i = 0; i < chiTietList.size(); i++) {
            ChiTietPNHangDTO ct = chiTietList.get(i);
            double thanhTien = ct.getSoLuong() * ct.getGiaNhap();
            data[i][0] = i + 1;
            data[i][1] = ct.getMaSP();
            data[i][2] = ct.getMaLH();
            data[i][3] = ct.getSoLuong();
            data[i][4] = currencyFormat.format(ct.getGiaNhap());
            data[i][5] = currencyFormat.format(thanhTien);
        }
        table.setModel(new DefaultTableModel(data, new String[]{"STT", "Mã SP", "Mã lô hàng", "Số lượng", "Giá nhập", "Thành tiền"}));
    }

//    public static void main(String[] args) {
//        FormAddImport test = new FormAddImport();
//        JFrame frame = new JFrame("Thêm đơn nhập hàng");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 600);
//        frame.setLayout(new BorderLayout());
//        frame.setLocationRelativeTo(null);
//        frame.add(test, BorderLayout.CENTER);
//        frame.setVisible(true);
//    }
}