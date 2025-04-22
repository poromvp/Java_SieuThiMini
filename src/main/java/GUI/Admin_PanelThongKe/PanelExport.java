package GUI.Admin_PanelThongKe;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class PanelExport extends JPanel {

    private DefaultTableModel tableModel;
    private JRadioButton rbExcel;
    private JRadioButton rbPDF;

    public PanelExport() {
        TitledBorder border = new TitledBorder("Chọn định dạng");
        border.setTitleColor(Color.WHITE);
        setLayout(new BorderLayout(10, 10));
        setBorder(new CompoundBorder(border, new EmptyBorder(4, 4, 4, 4)));
        setBackground(new Color(33,58,89));

        // Panel radio button
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        radioPanel.setBackground(new Color(33,58,89));
        rbExcel = new JRadioButton("Excel");
        rbPDF = new JRadioButton("PDF");

        rbExcel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        rbPDF.setFont(new Font("Segoe UI", Font.BOLD, 24));
        rbExcel.setForeground(Color.WHITE);
        rbPDF.setForeground(Color.WHITE);
        rbExcel.setBackground(new Color(33,58,89));
        rbPDF.setBackground(new Color(33,58,89));

        ButtonGroup group = new ButtonGroup();
        group.add(rbExcel);
        group.add(rbPDF);
        
        // chọn mặc định
        rbExcel.setSelected(true);
        
        radioPanel.add(rbExcel);
        radioPanel.add(rbPDF);
        
        add(radioPanel, BorderLayout.CENTER);
    }
    
    public void XuatPDF(DefaultTableModel model){
        this.tableModel = model;
        JOptionPane.showMessageDialog(null, "In báo cáo ra file PDF...");
    }

    public void XuatExccel(DefaultTableModel model){
        this.tableModel = model;
        JOptionPane.showMessageDialog(null, "In báo cáo ra file Exccel...");
    }

    // Trả về định dạng người dùng đã chọn
    public String getSelectedFormat() {
        if (rbExcel.isSelected()) return "excel";
        else if (rbPDF.isSelected()) return "pdf";
        return "";
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
