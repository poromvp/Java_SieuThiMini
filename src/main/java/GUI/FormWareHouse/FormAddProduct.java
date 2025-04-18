package GUI.FormWareHouse;

import BLL.SanPhamBLL;
import DTO.SanPhamDTO;
import GUI.ComponentCommon.StyledTextField;

import javax.swing.*;
import java.awt.*;

public class FormAddProduct extends JPanel {
    private JComboBox trangThai;
    private StyledTextField tenSP,giaSP,loaiSP,ncc,tenAnh,moTa;
    public FormAddProduct(){
        setLayout(new BorderLayout());

        JPanel nhapPanel = new JPanel();
        nhapPanel.setLayout(new GridLayout(7,2,5,5));

        nhapPanel.add(new JLabel("Tên sản phẩm:"));
        tenSP = new StyledTextField();
        nhapPanel.add(tenSP);

        nhapPanel.add(new JLabel("Giá sản phẩm:"));
        giaSP = new StyledTextField();
        nhapPanel.add(giaSP);

        nhapPanel.add(new JLabel("Loại sản phẩm:"));
        loaiSP = new StyledTextField();
        nhapPanel.add(loaiSP);

        nhapPanel.add(new JLabel("Nhà cung cấp:"));
        ncc = new StyledTextField();
        nhapPanel.add(ncc);

        nhapPanel.add(new JLabel("Tên ảnh:"));
        tenAnh = new StyledTextField();
        nhapPanel.add(tenAnh);

        nhapPanel.add(new JLabel("Mô tả:"));
        moTa = new StyledTextField();
        nhapPanel.add(moTa);

        nhapPanel.add(new JLabel("Trạng thái:"));
        String[] trangThaiOptions = {"ACTIVE", "INACTIVE"};
        trangThai = new JComboBox<>(trangThaiOptions);
        nhapPanel.add(trangThai);

        JButton btn1 = new JButton("Thêm sản phẩm");
        btn1.addActionListener(e->{
            try{
                SanPhamDTO product = new SanPhamDTO(
                        0,
                        Integer.parseInt(ncc.getText()),
                        Integer.parseInt(loaiSP.getText()),
                        tenAnh.getText(),
                        Double.parseDouble(giaSP.getText()),
                        tenSP.getText(),
                        moTa.getText(),
                        (String) trangThai.getSelectedItem()
                );
                if (SanPhamBLL.addProduct(product)){
                    JOptionPane.showMessageDialog(null,"Thêm sản phẩm thành công !");
                    clearFields();
                }else{
                    JOptionPane.showMessageDialog(null,"Thêm sản phẩm thất bại!");
                }
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập đúng định dạng số !");
            }
        });
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(btn1);

        add(nhapPanel,BorderLayout.CENTER);
        add(btnPanel,BorderLayout.SOUTH);
    }

    private void clearFields() {
        tenSP.setText("");
        giaSP.setText("");
        loaiSP.setText("");
        ncc.setText("");
        tenAnh.setText("");
        moTa.setText("");
    }
    public static void main(String[] args) {
        JFrame f = new JFrame("Thêm sản phẩm");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,500);
        f.setLayout(new BorderLayout());
        f.setLocationRelativeTo(null);


        FormAddProduct test = new FormAddProduct();
        f.add(test, BorderLayout.CENTER);
        f.setVisible(true);
    }
}
