package com.FormEmployee;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.ComponentCommon.StyledTextField;

import BLL.DiemTichLuyBLL;
import BLL.DonHangBLL;
import BLL.KhuyenMaiBLL;
import BLL.TheThanhVienBLL;
import DTO.DiemTichLuyDTO;
import DTO.DonHangDTO;
import DTO.KhuyenMaiDTO;
import DTO.TheThanhVienDTO;

public class OrderIformationForm extends JPanel {
    private static StyledTextField txtemployeeName , txtPhone, txtCustomerName, txtOrderId, txtTotal, txtTotalFinaly, txtDiscount, txtCash, txtPoint, txtPointRate, txtDiscountMax;
    private static JComboBox<String> cbPaymentMethod;
    private static JRadioButton rbYesMember, rbNoMember,  rbYesUsePoint, rbNoUsePoint;
    private static JButton btnSave, btnPrint;
    private static JLabel lblEmployeeName, lblCash, lblDiscountMax, lblPointRate;
    private static ButtonGroup group1;
    private static int maNV = 1;



    private static int countOrder;
    private static KhuyenMaiDTO discount;
    private static double totalAmount, total;
    // private static int  checkMember = -1;
    private static TheThanhVienDTO  Member = null;
    private static DiemTichLuyDTO  loyalyPoint = null;
    

    public OrderIformationForm() {
        setSize(400, 400);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Nhân viên
        lblEmployeeName = new JLabel("Mã nhân viên:");
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        add(lblEmployeeName, gbc);
        txtemployeeName = new StyledTextField();
        txtemployeeName.setText("1");
        txtemployeeName.SetEnabled(false);
        gbc.gridx = 1;
        add(txtemployeeName, gbc);

        
        // Mã hóa đơn
        gbc.gridx = 0;
        gbc.gridy = 1; gbc.gridwidth = 1; 
        add(new JLabel("Mã hóa đơn:"), gbc);
        txtOrderId = new StyledTextField(102, 30);
        txtOrderId.SetEnabled(false);
        gbc.gridx = 1;
        add(txtOrderId, gbc);
        
        // Tổng tiền
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Tổng tiền:"), gbc);
        txtTotal = new StyledTextField();
        txtTotal.SetEnabled(false);
        gbc.gridx = 1;
        add(txtTotal, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Thành tiền:"), gbc);
        txtTotalFinaly = new StyledTextField();
        txtTotalFinaly.SetEnabled(false);
        gbc.gridx = 1;
        add(txtTotalFinaly, gbc);
        
        // % Khuyến mãi
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("% Khuyến mãi:"), gbc);
        txtDiscount = new StyledTextField();
        txtDiscount.SetEnabled(false);
        gbc.gridx = 1;
        add(txtDiscount, gbc);
        
        // Tích điểm?
        gbc.gridx = 0; gbc.gridy = 5;
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
        gbc.gridx = 0; gbc.gridy = 6;
        add(new JLabel("SĐT KH:"), gbc);
        txtPhone = new StyledTextField();
        txtPhone.setText("khang");
        gbc.gridx = 1;
        add(txtPhone, gbc);
        
        // Tên khách hàng
        gbc.gridx = 0; gbc.gridy = 7;
        add(new JLabel("Tên KH:"), gbc);
        txtCustomerName = new StyledTextField();
        gbc.gridx = 1;
        add(txtCustomerName, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        add(new JLabel("Điểm TL:"), gbc);
        txtPoint = new StyledTextField();
        gbc.gridx = 1;
        add(txtPoint, gbc);


        gbc.gridx = 0; gbc.gridy = 9;
        add(new JLabel("Mua với điểm TL?"), gbc);
        rbYesUsePoint = new JRadioButton("Có");
        rbNoUsePoint = new JRadioButton("Không", true);
        group1 = new ButtonGroup();
        group1.add(rbYesUsePoint);
        group1.add(rbNoUsePoint);
        JPanel panel1 = new JPanel();
        panel1.add(rbYesUsePoint);
        panel1.add(rbNoUsePoint);
        gbc.gridx = 1;
        add(panel1, gbc);


        gbc.gridx = 0; gbc.gridy = 10;
        lblPointRate = new JLabel("đổi điểm giảm %:");
        add(lblPointRate, gbc);
        txtPointRate = new StyledTextField();
        txtPointRate.SetEnabled(false);
        gbc.gridx = 1;
        add(txtPointRate, gbc);

        gbc.gridx = 0; gbc.gridy = 11;
        lblDiscountMax = new JLabel("Giảm tối đa VND:");
        add(lblDiscountMax, gbc);
        txtDiscountMax = new StyledTextField();
        txtDiscountMax.SetEnabled(false);
        gbc.gridx = 1;
        add(txtDiscountMax, gbc);

        // Hình thức thanh toán
        gbc.gridx = 0; gbc.gridy = 12;
        add(new JLabel("Hình thức TT:"), gbc);
        cbPaymentMethod = new JComboBox<>(new String[]{"Tiền mặt", "Chuyển khoản"});
        gbc.gridx = 1;
        add(cbPaymentMethod, gbc);
        
        // Tiền khách đưa (ẩn nếu không phải tiền mặt)
        lblCash = new JLabel("Tiền khách đưa:");
        gbc.gridx = 0; gbc.gridy = 13;
        add(lblCash, gbc);
        txtCash = new StyledTextField();
        gbc.gridx = 1;
        add(txtCash, gbc);

        // Nút lưu đơn hàng
        gbc.gridx = 0; gbc.gridy = 14;
        btnSave = new JButton("Lưu đơn hàng");
        add(btnSave, gbc);

        // Nút in hóa đơn
        gbc.gridx = 1;
        btnPrint = new JButton("In hóa đơn");
        add(btnPrint, gbc);

        btnSave.addActionListener(e-> SaveOrder());
        // Xử lý sự kiện
        rbNoMember.addActionListener(e -> toggleCustomerFields(false));
        rbYesMember.addActionListener(e -> toggleCustomerFields(true));
        cbPaymentMethod.addActionListener(e -> toggleCashField());
        rbNoUsePoint.addActionListener(e -> toggleDiemtichLuy(false));
        rbYesUsePoint.addActionListener(e -> toggleDiemtichLuy(true));

        toggleCustomerFields(false); // Ẩn ban đầu
        toggleDiemtichLuy(false);
        toggleCashField(); // Ẩn tiền khách đưa nếu không phải tiền mặt
        
        setVisible(true);
        
        // khong chỉnh sửa 
        txtCustomerName.SetEnabled(false);
        txtPoint.SetEnabled(false);
        
   
    // render teen và điẻmTL
        txtPhone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String phone = txtPhone.getText().trim();
                if (!phone.isEmpty()) {
                    Member = TheThanhVienBLL.getMemberByPhone(phone);
                    if (Member != null) {
                        txtCustomerName.setText(Member.getTenTV());
                        txtPoint.setText(Member.getDiemTL() + "");
                        loyalyPoint = DiemTichLuyBLL.getDiemTichLuyByDiemTichLuy(Member.getDiemTL());
                        if(loyalyPoint != null){
                            txtPointRate.setText(loyalyPoint.getTiLeGiam() + "");
                            txtDiscountMax.setText(loyalyPoint.getGiamMax() + "");
                        }else{
                            txtPointRate.setText("không đủ điểm");
                            txtDiscountMax.setText("0.0");
                        }
                    } else {
                        
                        txtCustomerName.setText("lỗi thành viên!");
                        txtPoint.setText("lỗi thành viên!");
                        txtPointRate.setText("lỗi");
                    }
                }
            }
        });     
        rederOrderInformation();
    }

    // toggle khi nhấn có /không có thẻ thành viên
    private static void toggleCustomerFields(boolean isEnabled) {
        if(isEnabled == false){
            rbNoUsePoint.setSelected(true);
            txtPointRate.setVisible(false);
            txtDiscountMax.setVisible(false);
            lblPointRate.setVisible(false);
            lblDiscountMax.setVisible(false);


            txtPhone.setText("");
            txtCustomerName.setText("");
            txtPoint.setText("");
        }
        txtPhone.SetEnabled(isEnabled);
        rbNoUsePoint.setEnabled(isEnabled);
        rbYesUsePoint.setEnabled(isEnabled);
        
    }

    private static void toggleCashField() {
        boolean isCash = cbPaymentMethod.getSelectedItem().equals("Tiền mặt");
        lblCash.setVisible(isCash);
        txtCash.setVisible(isCash);
    }


    private static void toggleDiemtichLuy(boolean setlected) {
        txtPointRate.setVisible(setlected);
        txtDiscountMax.setVisible(setlected);
        lblPointRate.setVisible(setlected);
        lblDiscountMax.setVisible(setlected);
    }

    public static void rederOrderInformation(){
        countOrder = DonHangBLL.countOrder() + 1;
        txtOrderId.setText(countOrder + "");

        discount = KhuyenMaiBLL.getDiscountToday();
        if(discount != null){
            txtDiscount.setText(discount.getTileGiam() + "");
        }
        total = FormOrderDetailList.calCalculateTotal();
        totalAmount = FormOrderDetailList.calCalculateTotalAmount() - total*discount.getTileGiam()/100;
        txtTotal.setText(total + "");
        txtTotalFinaly.setText(totalAmount + "");
    }

    public static void SaveOrder(){
        LocalDateTime now = LocalDateTime.now();

        // Định dạng ngày giờ thành "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        DonHangDTO order = new DonHangDTO();
        order.setMaNV(maNV);
        order.setNgayTT(formattedDateTime);
        order.setPtThanhToan(cbPaymentMethod.getSelectedItem().equals("Tiền mặt") ? "CASH" : "BANK");
        order.setTienKD( 213);
        order.setTrangThai("FINISHED");
        if(discount != null){
            order.setMaKM(discount.getMaKM());
        }else{
            order.setMaKM(null);
        }
        if(rbYesMember.isSelected() == true){
            order.setMaKH(Member.getMaTV());
            if(rbYesUsePoint.isSelected() == true && loyalyPoint != null){
                order.setMaDTL(loyalyPoint.getMaDTL());
            }else{
                order.setMaDTL(null);
            }
        }else{
            order.setMaKH(null);
            order.setMaDTL(null);
        }
        boolean checkInsert =  DonHangBLL.insertOrder(order);
        if(checkInsert) {
            FormOrderDetailList.SaveOrderDetailList(Integer.parseInt( txtOrderId.getText()));
            System.out.println("them thanh cong dơn hang");
            if(Member != null){
                int totalTemp = (int) Math.floor( Double.parseDouble(txtTotal.getText().trim())); 
                int points = totalTemp / 1000; // Tính điểm tích lũy
                Member.setDiemTL(Member.getDiemTL() + points); // Cộng dồn điểm
                TheThanhVienBLL.updateMember(Member);
            }
        }else{
            System.out.println("them that bai");
        }
    }


    // public static void main(String[] args) {

        // String aaa = "23352.34";
        // int totalTemp = (int) Math.floor(Double.parseDouble(aaa.trim()));
        // int points = totalTemp / 1000;
        // System.out.println(points);
        
        // JFrame frame = new JFrame();
        // frame.add(new OrderIformationForm());
        // frame.setSize(500, 400);
        // frame.setLocationRelativeTo(null);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setVisible(true);
    // }

}
