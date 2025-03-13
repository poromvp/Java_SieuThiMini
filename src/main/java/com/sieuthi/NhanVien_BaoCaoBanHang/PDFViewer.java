/*package com.sieuthi.NhanVien_BaoCaoBanHang;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.ImageType;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFViewer extends JFrame {
    public PDFViewer(String filePath) {
        setTitle("PDF Preview");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            // Đọc file PDF
            PDDocument document = PDDocument.load(new File(filePath));
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Hiển thị từng trang PDF
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 100, ImageType.RGB);
                JLabel label = new JLabel(new ImageIcon(image));
                panel.add(label);
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            getContentPane().add(scrollPane);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể mở PDF!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PDFViewer("doanh_thu.pdf").setVisible(true));
    }
}*/

