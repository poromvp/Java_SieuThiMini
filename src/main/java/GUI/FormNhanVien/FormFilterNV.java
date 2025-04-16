package GUI.FormNhanVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import GUI.ComponentCommon.ButtonCustom;
import GUI.ComponentCommon.StyledTextField;

public class FormFilterNV extends JPanel implements ActionListener {
        private JComboBox<String> doTuoiComb;
        private JComboBox<String> gioiTinhComb;
        private StyledTextField khuVucField;
        private ButtonCustom btnFilter = new ButtonCustom("Lọc",16,"blue");
        private ButtonCustom btnCancel = new ButtonCustom("Hủy",16,"red");
    
        public FormFilterNV() {
            setBorder(BorderFactory.createTitledBorder("Lọc"));
            setBackground(Color.WHITE);
            setLayout(new BorderLayout(5, 5)); 
            
            JPanel filterPanel = new JPanel(new GridLayout(2, 3, 5, 5));
            String[] doTuoiStr = {"Không", "18-25 tuổi", "25-30 tuổi", "trên 30 tuổi"};
            String[] gioiTinhStr = {"Không", "Nam", "Nữ"};
            JLabel doTuoiLabel = new JLabel("Độ tuổi:");
            JLabel gioiTinhLabel = new JLabel("Giới tính:");
            JLabel khuVucLabel = new JLabel("Khu vực:");
            
            doTuoiComb = new JComboBox<>(doTuoiStr);
            gioiTinhComb = new JComboBox<>(gioiTinhStr);
            khuVucField = new StyledTextField(); 
    
            filterPanel.add(doTuoiLabel);
            filterPanel.add(gioiTinhLabel);
            filterPanel.add(khuVucLabel);
            filterPanel.add(doTuoiComb);
            filterPanel.add(gioiTinhComb);
            filterPanel.add(khuVucField);
    
            JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            btnPanel.add(btnFilter);
            btnPanel.add(btnCancel);
    
            btnCancel.addActionListener(this);
    
            add(filterPanel, BorderLayout.CENTER); 
            add(btnPanel, BorderLayout.SOUTH);     
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCancel){
                CancelFilter();
            }
        }
    
        public void CancelFilter() {
            doTuoiComb.setSelectedIndex(0);
            gioiTinhComb.setSelectedIndex(0);
            khuVucField.setText("");
        }
    
        public static void main(String[] args) {
            JFrame jFrame = new JFrame("Form Lọc Nhân Viên");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setSize(500, 200);
            jFrame.add(new FormFilterNV());
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);
        }
}
