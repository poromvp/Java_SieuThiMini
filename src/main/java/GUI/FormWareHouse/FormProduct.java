package GUI.FormWareHouse;

import BLL.SanPhamBLL;
import BLL.NhaCungCapBLL;
import BLL.LoaiSanPhamBLL;
import DTO.LoaiSanPhamDTO;
import DTO.NhaCungCapDTO;
import DTO.SanPhamDTO;
import EXCEL.ExportExcelProduct;
import EXCEL.ImportExcelProduct;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FormProduct extends JPanel {
    private FormAddProduct addProduct = new FormAddProduct();
    private JTable table;
    private DefaultTableModel tableModel;
    private NhaCungCapBLL ncc = new NhaCungCapBLL();
    private LoaiSanPhamBLL lsp = new LoaiSanPhamBLL();

    public FormProduct(){
        setLayout(new BorderLayout());
        setBackground(Color.white);

        // Tạo panel Tìm Kiếm
        JPanel timKiempnl = new JPanel();
        timKiempnl.setBorder(BorderFactory.createTitledBorder("Tìm Kiếm"));
        timKiempnl.setLayout(new GridLayout(2, 2, 5, 5));
        timKiempnl.setBackground(Color.white);

        JLabel mamSPLabel = new JLabel("Mã sản phẩm: ");
        JTextField maSPText = new JTextField();

        JLabel tenSPLabel = new JLabel("Tên sản phẩm:");
        JTextField tenSPText = new JTextField();

        JLabel loaiSPLabel = new JLabel("Loại sản phẩm:");
        JComboBox<String> loaiSPCb = new JComboBox<>();
        loaiSPCb.setBackground(Color.white);
        loaiSPCb.addItem("Tất cả");
        List<LoaiSanPhamDTO> loaiSPList = lsp.getList();
        for(LoaiSanPhamDTO loai : loaiSPList){
            loaiSPCb.addItem(loai.getTenLoaiSP()+" ("+loai.getMaLSP()+") ");
        }

        ButtonCustom timBtn = new ButtonCustom("Tìm kiếm","search",12,17,17);
        timBtn.addActionListener(e->{
            String maSP = maSPText.getText().trim();
            String tenSP = tenSPText.getText().trim();
            String loaiSP= loaiSPCb.getSelectedItem().toString();
            int maLSP = -1;
            if(!loaiSP.equals("Tất cả")){
                maLSP = Integer.parseInt(loaiSP.substring(loaiSP.indexOf("(") + 1, loaiSP.indexOf(")")));
            }
            List<SanPhamDTO> results = SanPhamBLL.searchProducts(maSP,tenSP,maLSP);
            updateTableData(results);
        });

        timKiempnl.add(mamSPLabel);
        timKiempnl.add(maSPText);
        timKiempnl.add(tenSPLabel);
        timKiempnl.add(tenSPText);
        timKiempnl.add(loaiSPLabel);
        timKiempnl.add(loaiSPCb);
        timKiempnl.add(new JLabel());
        timKiempnl.add(timBtn);

        add(timKiempnl,BorderLayout.NORTH);


        ButtonCustom themBtn = new ButtonCustom("Thêm","add",16,40,40);
        themBtn.addActionListener(e -> {
            JDialog info_product = new JDialog((Frame) null,true);
            info_product.setSize(400,300);
            info_product.setLocationRelativeTo(null);
            info_product.setLayout(new BorderLayout());
            info_product.add(addProduct,BorderLayout.CENTER);
            info_product.setVisible(true);
            loadTableData();
        });

        ButtonCustom suaBtn = new ButtonCustom("Sửa","edit",16,40,40);
        suaBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int maSP = (int) tableModel.getValueAt(selectedRow, 0);
                SanPhamDTO product = SanPhamBLL.getProductById(maSP);
                if (product != null) {
                    FormEditProduct editDialog = new FormEditProduct(null, product);
                    editDialog.setVisible(true);
                    loadTableData(); // Làm mới bảng sau khi sửa
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để sửa");
            }
        });


        ButtonCustom xoaBtn = new ButtonCustom("Xóa","del",16,40,40);
        xoaBtn.addActionListener(e ->{
            int selectedRow = table.getSelectedRow();
            if(selectedRow >= 0){
                int maSP = (int) tableModel.getValueAt(selectedRow,0);
                if(SanPhamBLL.deleteProduct(maSP)){
                    JOptionPane.showMessageDialog(null,"Xóa sản phẩm thành công");
                    loadTableData();
                }else {
                    JOptionPane.showMessageDialog(null,"Xóa sản phẩm thất bại !!!");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để xóa !!!");
            }
        });

        ButtonCustom importExcelBtn = new ButtonCustom("Nhập từ Excel","importExcel",16,40,40);
        ButtonCustom exportExcelBtn = new ButtonCustom("Xuất ra Excel","exportExcel",16,40,40);

        importExcelBtn.addActionListener(e -> {
            if (ImportExcelProduct.importProduct((JFrame) SwingUtilities.getWindowAncestor(this))) {
                loadTableData(); // Làm mới bảng
            }
        });

        exportExcelBtn.addActionListener(e -> ExportExcelProduct.exportProduct((JFrame) SwingUtilities.getWindowAncestor(this)));
        // Bảng sản phẩm
        String[] headerCol = {"Mã SP", "Tên SP", "Giá","Số lượng tồn", "Loai","Nhà cung cấp"};
        tableModel = new DefaultTableModel(headerCol,0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table = new StyledTable(new Object[][]{},headerCol);
        table.setModel(tableModel);
        loadTableData();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() >= 0) {
                    int maSP = (int) tableModel.getValueAt(table.getSelectedRow(), 0);
                    SanPhamDTO product = SanPhamBLL.getProductById(maSP);
                    if (product != null) {
                        FormProductDetail detailDialog = new FormProductDetail(null, product);
                        detailDialog.setVisible(true);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(Color.white);

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(Color.WHITE);
        panelBtn.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBtn.add(themBtn);
        panelBtn.add(suaBtn);
        panelBtn.add(xoaBtn);
        panelBtn.add(importExcelBtn);
        panelBtn.add(exportExcelBtn);

        JPanel infoProduct = new JPanel();
        infoProduct.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        infoProduct.setLayout(new BorderLayout());
        infoProduct.add(scrollPane,BorderLayout.CENTER);
        infoProduct.add(panelBtn,BorderLayout.SOUTH);
        infoProduct.setBackground(Color.white);
        infoProduct.setVisible(true);

        add(infoProduct,BorderLayout.CENTER);
    }

    private void loadTableData(){
        tableModel.setRowCount(0);
        List<SanPhamDTO> products = SanPhamBLL.getAllProducts();
        updateTableData(products);
    }

    private void updateTableData(List<SanPhamDTO> products){

        tableModel.setRowCount(0);
        for (SanPhamDTO product : products){
            NhaCungCapDTO ncc = this.ncc.getNhaCungCap(product.getMaNCC());
            LoaiSanPhamDTO lsp = this.lsp.getLoaiSanPham(product.getMaLSP());
            String tenNcc = (ncc != null) ? ncc.getTenNCC() : "Chưa cập nhật";
            String tenLoaiSP= (lsp!=null) ? lsp.getTenLoaiSP() :"Chưa cập nhật";
            tableModel.addRow(new Object[]{
                product.getMaSP(),
                product.getTenSP(),
                product.getGia(),
                product.getSoLuongTon(),
                tenLoaiSP,
                tenNcc,

            });
        }
    }

    public static void main (String[] args) {
        FormProduct test = new FormProduct();
        JFrame frame = new JFrame("Thông tin sản phẩm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.add(test,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
