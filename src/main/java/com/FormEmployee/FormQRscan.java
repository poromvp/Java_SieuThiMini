package com.FormEmployee;

    

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.BorderFactory;
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

import com.ComponentCommon.StyledTextField;
import com.QR.ScanQR;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.layout.borders.Border;

import BLL.ChiTietKhuyenMaBLL;
import BLL.SanPhamBLL;
import DTO.SanPhamDTO;


public class FormQRscan extends JPanel {

    public static JPanel containerQR = new JPanel();
    public static ScanQR scanQR = new ScanQR();

    public static JLabel lbl_tenSP = new JLabel("Tên SP: ");
    public static JTextField txt_tenSP = new StyledTextField();

    public static JLabel lbl_giaSP = new JLabel("Gia SP: ");
    public static JTextField txt_giaSP = new StyledTextField();

    public static JLabel lbl_soLuong = new JLabel("Số lượng: ");
    public static JTextField txt_soLuong = new StyledTextField();

    public static JLabel lbl_loSX = new JLabel("Lô SX: ");
    public static JTextField txt_loSX = new StyledTextField();

    public static JLabel lbl_ngaySX = new JLabel("Ngày SX: ");
    public static JTextField txt_ngaySX = new StyledTextField();

    public static JLabel lbl_ngayHH = new JLabel("Ngày HH: ");
    public static JTextField txt_ngayHH = new StyledTextField();

    
    // =========

    private static JLabel label_qr;
    private static JLabel lbl_id = new JLabel("Id SP");
    private static JTextField txt_id;
    private static JButton toggleButton;
    private static OpenCVFrameGrabber grabber;
    private static boolean isScanning = false; 
    private static String txtOld = null;
    private static Thread scanThread;


    public FormQRscan() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 100));
        setBackground(new Color(30, 144, 255));

        label_qr = new JLabel();
        label_qr.setPreferredSize(new Dimension(150, 150));
        txt_id = new StyledTextField();
        txt_id.setEditable(false);

        toggleButton = new JButton("Bật quét QR");
        toggleButton.setMaximumSize(new Dimension(10, 30));   
        toggleButton.addActionListener(e -> toggleScanning());

        containerQR.setLayout(new GridBagLayout());

        // Giảm kích thước font của JLabel
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        lbl_tenSP.setFont(labelFont);
        lbl_giaSP.setFont(labelFont);
        lbl_soLuong.setFont(labelFont);
        lbl_loSX.setFont(labelFont);
        lbl_ngaySX.setFont(labelFont);
        lbl_ngayHH.setFont(labelFont);
        lbl_id.setFont(labelFont);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,0,0,0);

        // Hàng 1: Tên SP
        double[] columnWeights = {0.2, 0.4, 0.2, 0.4};
        
        addComponent( containerQR ,lbl_tenSP, 0, 0, 1, 0.1, gbc);
        addComponent( containerQR ,txt_tenSP, 1, 0, 3, 0.9, gbc);

        // Hàng 2: Giá SP - Số lượng - Lô SX
        addComponent( containerQR ,lbl_giaSP,  0, 1, 1, columnWeights[0], gbc);
        addComponent( containerQR ,txt_giaSP, 1, 1, 1, columnWeights[1], gbc);
        addComponent( containerQR ,lbl_soLuong, 2, 1, 1, columnWeights[2], gbc);
        addComponent( containerQR ,txt_soLuong, 3, 1, 1, columnWeights[3], gbc);

        // Hàng 3: ID & nút bật quét QR
        addComponent( containerQR ,lbl_id, 0, 2, 1, columnWeights[0], gbc);
        addComponent( containerQR ,txt_id, 1, 2, 1, columnWeights[1], gbc);
        addComponent( containerQR ,toggleButton, 3, 2, 1, columnWeights[3], gbc);
        // addCell(label, 6, 0, 1, 1, 3);

        JPanel pn_qr = new JPanel();
        pn_qr.setLayout(new BorderLayout());
        pn_qr.add(label_qr, BorderLayout.CENTER);

        add(containerQR, BorderLayout.CENTER);
        add(pn_qr, BorderLayout.EAST);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
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
                        label_qr.setIcon(new ImageIcon(scaledImg));
                        String qrText = decodeQRCode(img);

                        if (qrText != null) {
                            if (!qrText.equals(txtOld)) {
                                try {
                                    txt_id.setText(qrText);
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

    private void addComponent(JPanel panel, Component comp, int x, int y, int width, double weightx, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.weightx = weightx;
        panel.add(comp, gbc);
    }
    



	
	public static void insertProductInformation(int id) {
		SanPhamDTO sp = SanPhamBLL.getProductById(id);
		if(sp != null) {
            txt_tenSP.setText(sp.getTenSP());
			txt_giaSP.setText(sp.getGia() + "");
			txt_soLuong.setText(sp.getTenSP());
			txt_ngaySX.setText(sp.getTenAnh());
			txt_ngayHH.setText(sp.getTenAnh());
            double tiLeGiam = ChiTietKhuyenMaBLL.getProductOnSaleToday(id);
            FormOrderDetailList.addProductDetail(new Object[]{id, sp.getTenSP(), sp.getGia(), tiLeGiam, 1});
		}else{

        }
	}

	 public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.add(new FormQRscan());

       

        frame.setVisible(true);
    }
}
