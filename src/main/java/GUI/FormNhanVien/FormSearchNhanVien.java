package GUI.FormNhanVien;

import javax.swing.*;
import GUI.ComponentCommon.StyledTextField;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormSearchNhanVien extends JPanel {
    private StyledTextField searchField;
    private FormTableNhanVien tablePanel;

    public FormSearchNhanVien(FormTableNhanVien tablePanel) {
        this.tablePanel = tablePanel;
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Tìm Kiếm"));
        setBackground(Color.white);
        searchField = new StyledTextField();
        searchField.setPreferredSize(new Dimension(300, 30));
        searchField.setPlaceholder("Nhập tên, sdt, cccd,...");
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
        JFrame frame = new JFrame("Test FormSearchNhanVien");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.add(new FormSearchNhanVien(new FormTableNhanVien()));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}