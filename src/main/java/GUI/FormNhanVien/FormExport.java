package GUI.FormNhanVien;

import javax.swing.*;
import javax.swing.border.*;

import org.jfree.chart.util.ExportUtils;

import GUI.Export;
import GUI.ComponentCommon.ButtonCustom;

import java.awt.*;

public class FormExport extends JPanel {

    private JRadioButton rbExcel;
    private JRadioButton rbPDF;
    private FormTableNhanVien table = new FormTableNhanVien();
    public FormExport() {
        TitledBorder border = new TitledBorder("Chọn định dạng");
        border.setTitleColor(Color.BLACK);
        setLayout(new BorderLayout(10, 10));
        setBorder(new CompoundBorder(border, new EmptyBorder(4, 4, 4, 4)));

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        rbExcel = new JRadioButton("Excel");
        rbPDF = new JRadioButton("PDF");
        
        ButtonGroup group = new ButtonGroup();
        group.add(rbExcel);
        group.add(rbPDF);

        rbExcel.setSelected(true);

        radioPanel.add(rbExcel);
        radioPanel.add(rbPDF);

        ButtonCustom btnExport = new ButtonCustom("XUẤT", 12, "blue");
        btnExport.addActionListener(e -> xuatFile());

        add(btnExport,BorderLayout.SOUTH);
        add(radioPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    public void xuatFile(){
        if (rbExcel.isSelected()) {
            Export.exportToExcel(table.getNhanVienTable(), "Danh sách nhân viên");
        } else if (rbPDF.isSelected()) {
            Export.exportToPDF(table.getNhanVienTable(), "Danh sách nhân viên");
        }

    }
    public static void main(String[] args) {
            JFrame frame = new JFrame("Test FormExport");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 150);
            frame.setLocationRelativeTo(null); 
            frame.setContentPane(new FormExport());
            frame.setVisible(true);
    }
}
