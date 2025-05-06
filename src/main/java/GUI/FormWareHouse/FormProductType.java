package GUI.FormWareHouse;

import BLL.LoaiSanPhamBLL;
import DTO.LoaiSanPhamDTO;
import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTable;
import GUI.ComponentCommon.StyledTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormProductType extends JPanel implements ActionListener {
    private LoaiSanPhamBLL loaiSanPhamBLL = new LoaiSanPhamBLL();
    private StyledTable table;
    private StyledTextField txtMaLSP, txtTenLSP;
    private JComboBox<String> cbTrangThai;
    private ButtonCustom btnAdd, btnEdit, btnDelete, btnReset;

    public FormProductType() {
        setLayout(new BorderLayout());
        setBackground(Color.white);

        // Panel chứa bảng
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.white);
        tablePanel.setBorder(BorderFactory.createTitledBorder("Loại sản phẩm"));

        // Tạo bảng
        String[] columns = {"Mã LSP", "Tên LSP", "Trạng thái"};
        table = new StyledTable(new Object[][]{}, columns);
        refreshTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        txtMaLSP.setText(table.getValueAt(selectedRow, 0).toString());
                        txtMaLSP.setEnabled(false);
                        txtTenLSP.setText(table.getValueAt(selectedRow, 1).toString());
                        cbTrangThai.setSelectedItem(table.getValueAt(selectedRow, 2).toString());
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.white);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        // Panel chứa form nhập liệu và nút
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(Color.white);
        northPanel.setBorder(BorderFactory.createTitledBorder("Thông tin"));

        // Form nhập liệu
        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.setBackground(Color.white);
        JPanel labelsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        labelsPanel.setBackground(Color.white);
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        fieldsPanel.setBackground(Color.white);

        txtMaLSP = new StyledTextField(150, 30);
        txtMaLSP.setText("Mã loại sản phẩm sẽ đuơc tạo tự động");
        txtMaLSP.setEnabled(false);
        txtTenLSP = new StyledTextField(150, 30);
        cbTrangThai = new JComboBox<>(new String[]{"Hoạt động", "Ngừng hoạt động"});
        cbTrangThai.setBackground(Color.white);

        labelsPanel.add(new JLabel("Mã LSP:"));
        labelsPanel.add(new JLabel("Tên LSP:"));
        labelsPanel.add(new JLabel("Trạng thái:"));
        fieldsPanel.add(txtMaLSP);
        fieldsPanel.add(txtTenLSP);
        fieldsPanel.add(cbTrangThai);

        formPanel.add(labelsPanel, BorderLayout.WEST);
        formPanel.add(fieldsPanel, BorderLayout.CENTER);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.white);
        btnAdd = new ButtonCustom("Thêm", "add", 14, 20, 20);
        btnEdit = new ButtonCustom("Sửa", "edit", 14, 20, 20);
        btnDelete = new ButtonCustom("Xóa", "del", 14, 20, 20);
        btnReset = new ButtonCustom("Làm mới","reset",14,20,20);
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnReset.addActionListener(this);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnReset);

        northPanel.add(formPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);
    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (LoaiSanPhamDTO lsp : loaiSanPhamBLL.getList()) {
            model.addRow(new Object[]{
                    lsp.getMaLSP(),
                    lsp.getTenLoaiSP(),
                    lsp.getTrangThai().equals("ACTIVE") ? "Hoạt động" : "Ngừng hoạt động"
            });
        }
    }

    private void clearForm() {
        txtMaLSP.setText("Mã loại sản phẩm sẽ đuơc tạo tự động");
        txtMaLSP.setEnabled(false);
        txtTenLSP.setText("");
        cbTrangThai.setSelectedIndex(0);
        table.clearSelection();
    }

    public boolean validateInput(){
        if(txtTenLSP.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Tên loại sản phẩm không được để trống !","Lỗi",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnAdd){
            if(!validateInput()){
                return;
            }
            String tenLSP = txtTenLSP.getText().trim();
            String trangThai = cbTrangThai.getSelectedItem().toString().equals("Hoạt động") ? "ACTIVE" : "INACTIVE";

            LoaiSanPhamDTO lsp = new LoaiSanPhamDTO(0,tenLSP,trangThai);
            if(loaiSanPhamBLL.add(lsp)){
                refreshTable();
                clearForm();
                JOptionPane.showMessageDialog(this,"Thêm loại sản phẩm thành công");
            }else{
                JOptionPane.showMessageDialog(this,"Vui lòng kiểm tra lại dữ liệu","Lỗi",JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource()==btnEdit) {
            int selectedRow = table.getSelectedRow();
            if(selectedRow>=0){
                if(!validateInput()){
                    return;
                }
                try{
                    int maLSP = Integer.parseInt(txtMaLSP.getText().trim());
                    String tenLSP=txtTenLSP.getText().trim();
                    String trangThai = cbTrangThai.getSelectedItem().toString().equals("Hoạt động") ? "ACTIVE" : "INACTIVE";
                    LoaiSanPhamDTO lsp = new LoaiSanPhamDTO(maLSP,tenLSP,trangThai);
                    if(loaiSanPhamBLL.update(lsp)){
                        refreshTable();
                        clearForm();
                        JOptionPane.showMessageDialog(this,"Sửa loại sản phẩm thành công !");
                    }else{
                        JOptionPane.showMessageDialog(this,"Sửa loại sản phẩm thất bại ! Vui lòng kiểm tra lại dữ liệu !","Lỗi",JOptionPane.ERROR_MESSAGE);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }else {
                JOptionPane.showMessageDialog(this,"Vui lòng chọn loại sản phẩm để chỉnh sửa","Thông báo",JOptionPane.WARNING_MESSAGE);
            }
        }else if (e.getSource()==btnDelete) {
            int selectedRow = table.getSelectedRow();
            if(selectedRow>=0){
                int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn xóa loại sản phẩm này","Xác nhận xóa loại sản phẩm",JOptionPane.YES_NO_OPTION);
                if(confirm==JOptionPane.YES_OPTION){
                    int maLSP = Integer.parseInt(txtMaLSP.getText().trim());
                    if(loaiSanPhamBLL.delete(maLSP)){
                        refreshTable();;
                        clearForm();
                        JOptionPane.showMessageDialog(this,"Đã xóa loại sản phẩm thành công !");
                    }
                    else {
                        JOptionPane.showMessageDialog(this,"Xóa loại sản phẩm thất bại !","Lỗi",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else {
                JOptionPane.showMessageDialog(this,"Vui lòng chọn sản phẩm để xóa","Thông báo",JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource()==btnReset) {
            clearForm();
            JOptionPane.showMessageDialog(this,"Form đã được làm mới","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void main(String[] args) {
        JFrame f = new JFrame("Thêm sản phẩm");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,500);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);


        FormProductType test = new FormProductType();
        f.add(test, BorderLayout.CENTER);
        f.setVisible(true);
    }
}