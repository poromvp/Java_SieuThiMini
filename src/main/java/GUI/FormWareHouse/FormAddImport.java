package GUI.FormWareHouse;

import BLL.ChiTietNhapHangBLL;
import BLL.LoaiSanPhamBLL;
import BLL.NhapHangBLL;
import BLL.SanPhamBLL;
import DTO.ChiTietPNHangDTO;
import DTO.PhieuNhapHangDTO;
import DTO.SanPhamDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTable;
import GUI.ComponentCommon.StyledTextField;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.function.Consumer;

public class FormAddImport extends JPanel {
    private StyledTable table;
    private StyledTable productTable;
    private StyledTextField maSP;
    private NhapHangBLL nhapHangBLL = new NhapHangBLL();
    private LoaiSanPhamBLL loaiSanPhamBLL = new LoaiSanPhamBLL();
    private ChiTietNhapHangBLL chiTietNhapBLL = new ChiTietNhapHangBLL();
    private ArrayList<ChiTietPNHangDTO> chiTietList = new ArrayList<>();
    private PhieuNhapHangDTO phieuNhap;
    private Runnable refreshCallback;
    private SanPhamBLL sanPhamBLL = new SanPhamBLL();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public FormAddImport(PhieuNhapHangDTO phieuNhap,Runnable refreshCallback) {
        this.phieuNhap = phieuNhap;
        this.refreshCallback= refreshCallback;

        setLayout(new BorderLayout());
        setBackground(Color.white);

        // Panel hiển thị danh sách sản phẩm của nhà cung cấp
        JPanel supplierProductPanel = new JPanel();
        supplierProductPanel.setBackground(Color.white);
        supplierProductPanel.setLayout(new BorderLayout());
        supplierProductPanel.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm của nhà cung cấp"));

        String[] productHeader = {"Mã SP", "Tên SP", "Loại sản phẩm", "Số lượng tồn"};
        ArrayList<SanPhamDTO> supplierProducts = sanPhamBLL.getProductsByNhaCungCap(phieuNhap.getMaNCC());
        Object[][] productData = convertProductsToArray(supplierProducts);
        productTable = new StyledTable(productData, productHeader);
        productTable.setEditable(false);

        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = productTable.getSelectedRow();
                    if (row >= 0) {
                        maSP.setText(String.valueOf(productTable.getValueAt(row, 0)));
                    }
                }
            }
        });

        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        productScrollPane.setBackground(Color.white);
        supplierProductPanel.add(productScrollPane, BorderLayout.CENTER);


        add(supplierProductPanel, BorderLayout.WEST);

        // Panel nhập thông tin sản phẩm
        JPanel import_info = new JPanel();
        import_info.setBackground(Color.white);
        import_info.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        import_info.setLayout(new GridLayout(8, 2, 5, 5));

        import_info.add(new JLabel("Mã sản phẩm:"));
         maSP = new StyledTextField();
         maSP.setEnabled(false);
         maSP.setForeground(Color.GRAY);
         maSP.setText("Mã sản phẩm phải được chọn từ bảng !");
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

        import_info.add(new JLabel("Ngày sản xuất:"));
        JDateChooser nsxChooser = new JDateChooser();
        nsxChooser.setDateFormatString("dd/MM/yyyy");
        import_info.add(nsxChooser);

        import_info.add(new JLabel("Hạn sử dụng:"));
        JDateChooser hsdChooser = new JDateChooser();
        hsdChooser.setDateFormatString("dd/MM/yyyy");
        import_info.add(hsdChooser);

        ButtonCustom btnThemSanPham = new ButtonCustom("Thêm sản phẩm",12,"blue");

        btnThemSanPham.addActionListener(e -> {
            try {
                int maSPValue = Integer.parseInt(maSP.getText().trim());
                int maLHValue = Integer.parseInt(maLH.getText().trim());
                int soLuongValue = Integer.parseInt(soLuong.getText().trim());
                double giaNhapValue = Double.parseDouble(gia.getText().trim());
                Date nsxValue = nsxChooser.getDate();
                Date hsdValue = hsdChooser.getDate();

                if (!chiTietNhapBLL.validateSoLuong(soLuongValue) || !chiTietNhapBLL.validateGiaNhap(giaNhapValue)) {
                    JOptionPane.showMessageDialog(this, "Số lượng và giá nhập phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (nsxValue == null || hsdValue == null) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sản xuất và hạn sử dụng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Date currentDate = new Date();
                if (nsxValue.after(currentDate)) {
                    JOptionPane.showMessageDialog(this, "Ngày sản xuất không được trong tương lai!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (hsdValue.before(currentDate)) {
                    JOptionPane.showMessageDialog(this, "Hạn sử dụng không được trong quá khứ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (nsxValue.after(hsdValue)) {
                    JOptionPane.showMessageDialog(this, "Ngày sản xuất phải trước hạn sử dụng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ChiTietPNHangDTO chiTiet = new ChiTietPNHangDTO();
                chiTiet.setMaLH(maLHValue);
                chiTiet.setMaSP(maSPValue);
                chiTiet.setSoLuong(soLuongValue);
                chiTiet.setGiaNhap(giaNhapValue);
                chiTiet.setNsx(nsxValue);
                chiTiet.setHsd(hsdValue);
                chiTiet.setTrangThai("ACTIVE");

                chiTietList.add(chiTiet);
                refreshTable();

                maSP.setText("");
                maLH.setText("");
                soLuong.setText("");
                gia.setText("");
                nsxChooser.cleanup();
                hsdChooser.cleanup();

                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        ButtonCustom btnHoanTat = new ButtonCustom("Hoàn tất đơn nhập hàng",12,"green");
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
                    SanPhamDTO sanPham = sanPhamBLL.getProductById(chiTiet.getMaSP());
                    if (sanPham != null) {
                        sanPham.setSoLuongTon(sanPham.getSoLuongTon() + chiTiet.getSoLuong());
                        if (!sanPhamBLL.updateProduct(sanPham)) {
                            allChiTietSaved = false;
                            JOptionPane.showMessageDialog(this, "Cập nhật số lượng tồn cho sản phẩm " + chiTiet.getMaSP() + " thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    } else {
                        allChiTietSaved = false;
                        JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm với mã " + chiTiet.getMaSP(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
                if(allChiTietSaved){
                    JOptionPane.showMessageDialog(this, "Hoàn tất đơn nhập hàng thành công!");
                    refreshCallback.run();
                    SwingUtilities.getWindowAncestor(this).dispose();
                }else {
                    JOptionPane.showMessageDialog(this, "Lưu chi tiết đơn nhập hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(this, "Lưu đơn nhập hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        });

        ButtonCustom btnHuy = new ButtonCustom("Hủy",12,"red");
        btnHuy.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(btnThemSanPham);
        buttonPanel.add(btnHoanTat);
        buttonPanel.add(btnHuy);

        import_info.add(new JLabel());
        import_info.add(buttonPanel);

        add(import_info, BorderLayout.NORTH);

        // Panel hiển thị danh sách sản phẩm
        JPanel product_info = new JPanel();
        product_info.setBackground(Color.white);
        product_info.setLayout(new BorderLayout());
        product_info.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm đã thêm"));

        String[] headerCol = {"STT", "Mã SP", "Mã lô hàng", "Số lượng", "Giá nhập","Ngày sản xuất","Hạn sử dụng", "Thành tiền"};
        table = new StyledTable(new Object[0][8], headerCol);
        table.setEditable(false);
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        product_info.add(scrollPane, BorderLayout.CENTER);

        add(product_info, BorderLayout.CENTER);

    }

    private void refreshTable() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        Object[][] data = new Object[chiTietList.size()][8];
        for (int i = 0; i < chiTietList.size(); i++) {
            ChiTietPNHangDTO ct = chiTietList.get(i);
            double thanhTien = ct.getSoLuong() * ct.getGiaNhap();
            data[i][0] = i + 1;
            data[i][1] = ct.getMaSP();
            data[i][2] = ct.getMaLH();
            data[i][3] = ct.getSoLuong();
            data[i][4] = currencyFormat.format(ct.getGiaNhap());
            data[i][5] = ct.getNsx() != null ? dateFormat.format(ct.getNsx()) : "";
            data[i][6] = ct.getHsd() != null ? dateFormat.format(ct.getHsd()) : "";
            data[i][7] = currencyFormat.format(thanhTien);
        }
        table.setModel(new DefaultTableModel(data, new String[]{"STT", "Mã SP", "Mã lô hàng", "Số lượng", "Giá nhập","Ngày sản xuất","Hạn sử dụng","Thành tiền"}));
    }

    private Object[][] convertProductsToArray(ArrayList<SanPhamDTO> products) {
        Object[][] data = new Object[products.size()][4];
        for (int i = 0; i < products.size(); i++) {
            SanPhamDTO sp = products.get(i);
            data[i][0] = sp.getMaSP();
            data[i][1] = sp.getTenSP();
            data[i][2] = loaiSanPhamBLL.getLoaiSanPham(sp.getMaLSP()).getTenLoaiSP();
            data[i][3] = sp.getSoLuongTon();
        }
        return data;
    }
}