package com.QR;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;

public class GenerateQRCode {
    public static void main(String[] args) {
        // Tạo giao diện
        JFrame frame = new JFrame("QR Code Generator");
        JTextField textField = new JTextField();
        JButton generateButton = new JButton("Generate QR Code");
        JLabel qrLabel = new JLabel();

        // Thiết lập layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(textField, BorderLayout.NORTH);
        panel.add(generateButton, BorderLayout.CENTER);
        panel.add(qrLabel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Bắt sự kiện khi nhấn nút
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = textField.getText();
                if (!data.isEmpty()) {
                    BufferedImage qrImage = generateQRCode(data, 300, 300);
                    if (qrImage != null) {
                        qrLabel.setIcon(new ImageIcon(qrImage)); // Hiển thị ảnh QR Code
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Vui lòng nhập dữ liệu!");
                }
            }
        });
    }

    // Hàm tạo mã QR
    private static BufferedImage generateQRCode(String data, int width, int height) {
        try {
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1); // Viền nhỏ hơn

            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height, hints);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
