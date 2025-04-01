package com.FormEmployee;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ComponentCommon.StyledTextField;

public class OrderIformationForm extends JPanel {
    private StyledTextField txtPhone, txtCustomerName, txtOrderId, txtTotal, txtDiscount, txtCash, txtPoint;
    private JComboBox<String> cbPaymentMethod;
    private JRadioButton rbYesMember, rbNoMember,  rbYesUsePoint, rbNoUsePoint;
    private JButton btnSave, btnPrint;
    private JLabel lblEmployeeName, lblCash;

    public OrderIformationForm() {
        setSize(400, 400);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Nhân viên
        lblEmployeeName = new JLabel("Nhân viên: Nguyễn Văn A");
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblEmployeeName, gbc);
        
        // Mã hóa đơn
        gbc.gridy = 1; gbc.gridwidth = 1;
        add(new JLabel("Mã hóa đơn:"), gbc);
        txtOrderId = new StyledTextField(102, 30);
        gbc.gridx = 1;
        add(txtOrderId, gbc);
        
        // Tổng tiền
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Tổng tiền:"), gbc);
        txtTotal = new StyledTextField();
        gbc.gridx = 1;
        add(txtTotal, gbc);
        
        // % Khuyến mãi
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("% Khuyến mãi:"), gbc);
        txtDiscount = new StyledTextField();
        gbc.gridx = 1;
        add(txtDiscount, gbc);
        
        // Tích điểm?
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Có thẻ thành viên?"), gbc);
        rbYesMember = new JRadioButton("Có");
        rbNoMember= new JRadioButton("Không", true);
        ButtonGroup group = new ButtonGroup();
        group.add(rbYesMember);
        group.add(rbNoMember);
        JPanel panel = new JPanel();
        panel.add(rbYesMember);
        panel.add(rbNoMember);
        gbc.gridx = 1;
        add(panel, gbc);
        
        // Số điện thoại (ẩn nếu không tích điểm)
        gbc.gridx = 0; gbc.gridy = 5;
        add(new JLabel("SĐT KH:"), gbc);
        txtPhone = new StyledTextField();
        txtPhone.setText("khang");
        gbc.gridx = 1;
        add(txtPhone, gbc);
        
        // Tên khách hàng
        gbc.gridx = 0; gbc.gridy = 6;
        add(new JLabel("Tên KH:"), gbc);
        txtCustomerName = new StyledTextField();
        gbc.gridx = 1;
        add(txtCustomerName, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        add(new JLabel("Điểm TL:"), gbc);
        txtPoint = new StyledTextField();
        gbc.gridx = 1;
        add(txtPoint, gbc);


        gbc.gridx = 0; gbc.gridy = 8;
        add(new JLabel("Mua với điểm TL?"), gbc);
        rbYesUsePoint = new JRadioButton("Có");
        rbNoUsePoint = new JRadioButton("Không", true);
        ButtonGroup group1 = new ButtonGroup();
        group1.add(rbYesUsePoint);
        group1.add(rbNoUsePoint);
        JPanel panel1 = new JPanel();
        panel1.add(rbYesUsePoint);
        panel1.add(rbNoUsePoint);
        gbc.gridx = 1;
        add(panel1, gbc);
        
        // Hình thức thanh toán
        gbc.gridx = 0; gbc.gridy = 9;
        add(new JLabel("Hình thức TT:"), gbc);
        cbPaymentMethod = new JComboBox<>(new String[]{"Tiền mặt", "Chuyển khoản"});
        gbc.gridx = 1;
        add(cbPaymentMethod, gbc);
        
        // Tiền khách đưa (ẩn nếu không phải tiền mặt)
        lblCash = new JLabel("Tiền khách đưa:");
        gbc.gridx = 0; gbc.gridy = 10;
        add(lblCash, gbc);
        txtCash = new StyledTextField();
        gbc.gridx = 1;
        add(txtCash, gbc);

        // Nút lưu đơn hàng
        gbc.gridx = 0; gbc.gridy = 11;
        btnSave = new JButton("Lưu đơn hàng");
        add(btnSave, gbc);

        // Nút in hóa đơn
        gbc.gridx = 1;
        btnPrint = new JButton("In hóa đơn");
        add(btnPrint, gbc);

        // Xử lý sự kiện
        rbNoMember.addActionListener(e -> toggleCustomerFields(false));
        rbYesMember.addActionListener(e -> toggleCustomerFields(true));
        cbPaymentMethod.addActionListener(e -> toggleCashField());

        toggleCustomerFields(false); // Ẩn ban đầu
        toggleCashField(); // Ẩn tiền khách đưa nếu không phải tiền mặt
        
        setVisible(true);
        
        // khong chỉnh sửa 
        txtCustomerName.setEnabled(false);
        txtPoint.setEnabled(false);
        txtPoint.isAddColorBorder(false);
        txtCustomerName.isAddColorBorder(false);

    }

    private void toggleCustomerFields(boolean isEnabled) {
        txtPhone.setEnabled(isEnabled);
        txtPhone.isAddColorBorder(isEnabled);
        txtPhone.setText("");
    }

    private void toggleCashField() {
        boolean isCash = cbPaymentMethod.getSelectedItem().equals("Tiền mặt");
        lblCash.setVisible(isCash);
        txtCash.setVisible(isCash);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new OrderIformationForm());
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
