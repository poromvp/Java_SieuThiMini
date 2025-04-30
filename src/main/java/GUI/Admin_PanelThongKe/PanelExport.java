package GUI.Admin_PanelThongKe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.FileOutputStream;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import GUI.ComponentCommon.TienIch;

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
        TienIch.CustomMessage("In báo cáo ra file PDF...");
    }

    public void XuatExccel(DefaultTableModel model){
        this.tableModel = model;
        TienIch.CustomMessage("In báo cáo ra file Excel...");
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

    public void exportJTableToPDF(JTable table) {
        JFileChooser fileChooser = new JFileChooser("src/main/resources/images");
        fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files", "pdf");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int userChoice = fileChooser.showSaveDialog(null);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".pdf")) {
                filePath += ".pdf";
            }

            try {
                // Tạo đối tượng Document
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Tạo đối tượng BaseFont từ font hỗ trợ Unicode (ví dụ: Arial Unicode MS)
                BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font font = new Font(baseFont, 12);

                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

                // Thêm tiêu đề cột vào bảng PDF
                for (int i = 0; i < table.getColumnCount(); i++) {
                    pdfTable.addCell(new PdfPCell(new Phrase(table.getColumnName(i), font)));
                }

                // Thêm dữ liệu vào bảng PDF
                for (int rows = 0; rows < table.getRowCount(); rows++) {
                    for (int cols = 0; cols < table.getColumnCount(); cols++) {
                        Object value = table.getValueAt(rows, cols);
                        pdfTable.addCell(new PdfPCell(new Phrase(value != null ? value.toString() : "", font)));
                    }
                }

                document.add(pdfTable);
                document.close();

                TienIch.CustomMessage("Đã xuất file thành công!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi xuất PDF: " + ex.getMessage());
            }
        }
    }
}
