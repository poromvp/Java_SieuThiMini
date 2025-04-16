package GUI.FormEmployee;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.ComponentCommon.StyledTextField;


public class FormOrderInformation extends JPanel {
    
    public FormOrderInformation(){
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tìm thông tin đơn hàng"));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần

        // Labels (Cột 1)
        gbc.gridx = 0;
        gbc.weightx = 0.2; // Cột 1 nhỏ hơn
        add(new JLabel("Hình thức mua:"), gbc);
        
        gbc.gridy = 1;
        add(new JLabel("SĐT:"), gbc);
        
        gbc.gridy = 2;
        add(new JLabel("Tên KH:"), gbc);

        gbc.gridy = 3;
        add(new JLabel("Mã HD:"), gbc);
        
        gbc.gridy = 4;
        add(new JLabel("Tên nhân viên:"), gbc);
        
        gbc.gridy = 5;
        add(new JLabel("Tổng tiền:"), gbc);
        
        gbc.gridy = 6;
        add(new JLabel("Giảm giá khuyến mãi:"), gbc);
        
        gbc.gridy = 7;
        add(new JLabel("Thanh toán (Số):"), gbc);

        gbc.gridy = 8;
        add(new JLabel("Voucher tùy động:"), gbc);
        
        gbc.gridy = 9;
        add(new JLabel("Hình thức thanh toán:"), gbc);
        
        gbc.gridy = 10;
        add(new JLabel("Tiền khách đưa:"), gbc);

        gbc.gridy = 11;
        add(new JLabel("Tiền khách CK:"), gbc);
        
        gbc.gridy = 12;
        add(new JLabel("Ghi chú:"), gbc);

        // Fields (Cột 2)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8; // Cột 2 lớn hơn
        add(new JComboBox<>(new String[]{"Tại quầy", "Online"}), gbc);
        
        gbc.gridy = 1;
        add(new StyledTextField(), gbc);
        
        gbc.gridy = 2;
        add(new StyledTextField(), gbc);

        gbc.gridy = 3;
        add(new StyledTextField(), gbc);
        
        gbc.gridy = 4;
        add(new StyledTextField(), gbc);
        
        gbc.gridy = 5;
        add(new StyledTextField(), gbc);
        
        gbc.gridy = 6;
        add(new StyledTextField(), gbc);
        
        gbc.gridy = 7;
        add(new StyledTextField(), gbc);
        
        gbc.gridy = 8;
        add(new StyledTextField(), gbc);
        
        gbc.gridy = 9;
        add(new JComboBox<>(new String[]{"Tiền mặt", "Chuyển khoản"}), gbc);
        
        gbc.gridy = 10;
        add(new StyledTextField(), gbc);
        
        gbc.gridy = 11;
        add(new StyledTextField(), gbc);
        
        gbc.gridy = 12;
        add(new StyledTextField(), gbc);

        // Nút Lưu Hóa Đơn
        gbc.gridx = 1;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JButton("Lưu Hóa Đơn"), gbc);
    }
     public static void main(String[] args) {
        JFrame frame = new JFrame("Đơn hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.add(new FormOrderInformation());
        frame.setVisible(true);
    }
}
