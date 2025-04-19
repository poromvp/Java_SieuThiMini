package GUI.FormTaiKhoan;

import javax.swing.*;
import GUI.ComponentCommon.StyledTextField;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormSearchAccount extends JPanel {
    private StyledTextField searchField;
    private FormTableAccount tablePanel;

    public FormSearchAccount(FormTableAccount tablePanel) {
        this.tablePanel = tablePanel;
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Tìm Kiếm"));

        searchField = new StyledTextField();
        searchField.setPreferredSize(new Dimension(200, 30)); 

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String keyword = searchField.getText().trim();
                tablePanel.searchAndUpdateTable(keyword);
            }
        });

        add(searchField);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test FormSearchAccount");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.add(new FormSearchAccount(new FormTableAccount()));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}