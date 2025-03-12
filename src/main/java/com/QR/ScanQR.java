package com.QR;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;

import com.FormEmployee.FormQRscan;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

// import component.ComponentOrder.FormQRscan;

public class ScanQR extends JPanel {
    private JLabel label;
    private JTextField textField;
    private JButton toggleButton;
    private OpenCVFrameGrabber grabber;
    private boolean isScanning = false; // Biến kiểm soát việc quét
    private String txtOld = null;
    private Thread scanThread;

    public ScanQR() {
        setLayout(new BorderLayout());
        
        label = new JLabel();
        label.setPreferredSize(new Dimension(150, 150));
        textField = new JTextField();
        textField.setEditable(false);

        toggleButton = new JButton("Bật quét QR");
        toggleButton.addActionListener(e -> toggleScanning());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(toggleButton, BorderLayout.NORTH);
        controlPanel.add(textField, BorderLayout.SOUTH);

        add(label, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void toggleScanning() {
        if (isScanning) {
            stopScanning();
        } else {
            startScanning();
        }
    }

    private void startScanning() {
        isScanning = true;
        toggleButton.setText("Tắt quét QR");
        scanThread = new Thread(() -> {
            try {
                grabber = new OpenCVFrameGrabber(0);
                grabber.setImageWidth(150);
                grabber.setImageHeight(150);
                grabber.start();

                Java2DFrameConverter converter = new Java2DFrameConverter();
                OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();

                while (isScanning) {
                    Frame frameGrabbed = grabber.grab();
                    if (frameGrabbed == null) continue;

                    Mat mat = converterToMat.convert(frameGrabbed);
                    Mat flippedMat = new Mat();
                    opencv_core.flip(mat, flippedMat, 1);
                    Frame flippedFrame = converterToMat.convert(flippedMat);
                    BufferedImage img = converter.getBufferedImage(flippedFrame);

                    if (img != null) {
                        Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        label.setIcon(new ImageIcon(scaledImg));
                        String qrText = decodeQRCode(img);

                        if (qrText != null) {
                            if (!qrText.equals(txtOld)) {
                                try {
                                    textField.setText(qrText);
                                    txtOld = qrText;
                                    int id = Integer.parseInt(qrText);
                                    System.out.println("Quét thành công: " + qrText);
                                    FormQRscan.insertProductInformation(id);
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (NumberFormatException ex) {
                                    System.out.println("QRcode không phải số");
                                }
                            } else {
                                System.out.println("QRcode cũ");
                            }
                        } else {
                            System.out.println("QRcode null");
                        }
                    }

                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                stopScanning();
            }
        });
        scanThread.start();
    }

    private void stopScanning() {
        isScanning = false;
        toggleButton.setText("Bật quét QR");

        try {
            if (grabber != null) {
                grabber.stop();
                grabber.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String decodeQRCode(BufferedImage img) {
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(img);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

            Result result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("QR Code Scanner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(340, 420);
        frame.add(new ScanQR());
        frame.setVisible(true);
    }
}
