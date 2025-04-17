package GUI.FormTaiKhoan;

import javax.swing.*;

import GUI.ComponentCommon.StyledTextField;

import java.awt.*;

public class FormSearchAccount extends JPanel {
    public FormSearchAccount() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Tìm Kiếm"));

        StyledTextField searchField = new StyledTextField();

        add(searchField);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test FormSearchAccount");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.add(new FormSearchAccount());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
