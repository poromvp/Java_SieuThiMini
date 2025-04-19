package GUI.FormWareHouse;

import BLL.SanPhamBLL;
import DTO.SanPhamDTO;
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

    public FormProduct(){
        setLayout(new BorderLayout());
        setBackground(Color.white);

        // Tạo panel Tìm Kiếm
        JPanel timKiempnl = new JPanel();
        timKiempnl.setBorder(BorderFactory.createTitledBorder("Tìm Kiếm"));
        timKiempnl.setLayout(new GridLayout(1, 3, 5, 5));
        String timKiemCombo[]={"Tìm theo mã",
                "Tìm theo tên sản phẩm",
                "Tìm theo loại sản phẩm "};
        JComboBox timKiemCb= new JComboBox(timKiemCombo);
        JTextField timKiemtxt = new JTextField();// Nhap thong tin tim kiem
        JButton timBtn = new JButton("Tìm");

        timBtn.addActionListener(e->{
            String keyword = timKiemtxt.getText();
            String searchType = (String) timKiemCb.getSelectedItem();
            List<SanPhamDTO> results = SanPhamBLL.searchProducts(keyword, searchType);
            updateTableData(results);
        });
        timKiempnl.add(timKiemCb);
        timKiempnl.add(timKiemtxt);
        timKiempnl.add(timBtn);
        add(timKiempnl,BorderLayout.NORTH);


        ButtonCustom themBtn = new ButtonCustom("Thêm","add",16,20,20);
        themBtn.addActionListener(e -> {
            JDialog info_product = new JDialog((Frame) null,true);
            info_product.setSize(400,300);
            info_product.setLocationRelativeTo(null);
            info_product.setLayout(new BorderLayout());
            info_product.add(addProduct,BorderLayout.CENTER);
            info_product.setVisible(true);
            loadTableData();
        });

        ButtonCustom suaBtn = new ButtonCustom("Sửa","edit",16,20,20);
        suaBtn.addActionListener(e->{
            int selectedRow = table.getSelectedRow();
            if(selectedRow>=0){
                int maSP = (int) tableModel.getValueAt(selectedRow,0);
                SanPhamDTO product = SanPhamBLL.getProductById(maSP);
                JOptionPane.showMessageDialog(null,"Sửa chưa làm :)))");
            }else{
                JOptionPane.showMessageDialog(null,"Vui lòng chọn sản phẩm để sửa");
            }
        });


        ButtonCustom xoaBtn = new ButtonCustom("Xóa","del",16,20,20);
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

        // Bảng sản phẩm
        String[] headerCol = {"Mã SP", "Tên SP", "Giá", "Loai","Nhà cung cấp"};
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

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBtn.add(themBtn);
        panelBtn.add(suaBtn);
        panelBtn.add(xoaBtn);

        JPanel infoProduct = new JPanel();
        infoProduct.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));
        infoProduct.setLayout(new BorderLayout());
        infoProduct.add(scrollPane,BorderLayout.CENTER);
        infoProduct.add(panelBtn,BorderLayout.SOUTH);
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
            tableModel.addRow(new Object[]{
                product.getMaSP(),
                product.getTenSP(),
                product.getGia(),
                product.getMaLSP(),
                product.getMaNCC()
            });
        }
    }

    public static void main (String[] args) {
        FormProduct test = new FormProduct();
        JFrame frame = new JFrame("Thông tin sản phẩm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1000);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.add(test,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
