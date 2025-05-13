package PDF;

import BLL.ChiTietNhapHangBLL;
import BLL.NhaCungCapBLL;
import BLL.NhanVienBLL;
import BLL.SanPhamBLL;
import DTO.ChiTietPNHangDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapHangDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ExportPdfImport {

    private final ChiTietNhapHangBLL chiTietBLL = new ChiTietNhapHangBLL();
    private final NhanVienBLL nhanVienBLL = new NhanVienBLL();
    private final NhaCungCapBLL nhaCungCapBLL = new NhaCungCapBLL();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    public void exportPDF(PhieuNhapHangDTO phieuNhap) {
        try {
            // Đổi Look & Feel sang hệ điều hành
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn nơi lưu phiếu nhập hàng và đặt tên file");
        chooser.setSelectedFile(new File("PhieuNhapHang_" + phieuNhap.getMaPNH() + ".pdf"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            // Nếu người dùng không nhập đuôi .pdf, thêm vào
            if (!selectedFile.getName().toLowerCase().endsWith(".pdf")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".pdf");
            }

            try {
                // Tạo document mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                // Font hỗ trợ tiếng Việt
                String fontPath = "src/main/resources/fonts/arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontNormal = new Font(baseFont, 12);
                Font fontBold = new Font(baseFont, 12, Font.BOLD);
                Font fontTitle = new Font(baseFont, 22, Font.BOLD);
                Font fontSubtitle = new Font(baseFont, 14, Font.ITALIC);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD, BaseColor.WHITE);

                // Logo
                String imagePath = "src/main/resources/images/icon/Logo_Main.png";
                Image logo = Image.getInstance(imagePath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);

                // Tiêu đề
                Paragraph title = new Paragraph("SIÊU THỊ MINI SGU\n", fontTitle);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                Paragraph subtitle = new Paragraph("Chất lượng trong từng lựa chọn!\n\n", fontSubtitle);
                subtitle.setAlignment(Element.ALIGN_CENTER);
                document.add(subtitle);

                // Thêm tiêu đề phần chi tiết phiếu nhập hàng
                Paragraph subheading = new Paragraph("CHI TIẾT PHIẾU NHẬP HÀNG\n\n", fontBold);
                subheading.setAlignment(Element.ALIGN_CENTER);
                document.add(subheading);

                // Bảng thông tin phiếu nhập hàng 2 cột
                PdfPTable infoTable = new PdfPTable(1);
                infoTable.setWidthPercentage(100);
                infoTable.setSpacingBefore(10f);
                infoTable.setSpacingAfter(10f);

                // Các thông tin phiếu nhập hàng
                addInfoCell(infoTable, "Mã phiếu nhập hàng: " + phieuNhap.getMaPNH(), fontNormal);
                addInfoCell(infoTable, "Ngày nhập: " + dateFormat.format(phieuNhap.getNgayNhap()), fontNormal);

                NhanVienDTO nhanVien = nhanVienBLL.getNhanVienById(phieuNhap.getMaNV());
                addInfoCell(infoTable, "Tên nhân viên: " + (nhanVien != null ? nhanVien.getTenNV() : "Không xác định"), fontNormal);
                addInfoCell(infoTable, "Mã nhân viên: " + phieuNhap.getMaNV(), fontNormal);

                NhaCungCapDTO nhaCungCap = nhaCungCapBLL.getNhaCungCap(phieuNhap.getMaNCC());
                addInfoCell(infoTable, "Nhà cung cấp: " + (nhaCungCap != null ? nhaCungCap.getTenNCC() : "Không xác định"), fontNormal);
                addInfoCell(infoTable, "Mã nhà cung cấp: " + phieuNhap.getMaNCC(), fontNormal);

                double tongTien = chiTietBLL.calculateTongTien(phieuNhap.getMaPNH());
                addInfoCell(infoTable, "Tổng tiền: " + currencyFormat.format(tongTien), fontNormal);
//                addInfoCell(infoTable, "Trạng thái: " + phieuNhap.getTrangThai(), fontNormal);

                document.add(infoTable);

                // Tạo bảng chi tiết sản phẩm
                PdfPTable table = new PdfPTable(8);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{1f, 1.5f, 1.5f, 1f, 1.5f, 1.5f, 1.5f, 2f});
                table.setSpacingBefore(10f);

                // Header bảng
                String[] headers = {"STT", "Mã SP", "Mã lô hàng", "Số lượng", "Giá nhập", "Ngày SX", "Hạn SD", "Thành tiền"};
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor(new BaseColor(33, 58, 89));
                    table.addCell(cell);
                }

                // Dữ liệu chi tiết sản phẩm
                ArrayList<ChiTietPNHangDTO> chiTietList = chiTietBLL.getChiTietByMaPNH(phieuNhap.getMaPNH());
                for (int i = 0; i < chiTietList.size(); i++) {
                    ChiTietPNHangDTO chiTiet = chiTietList.get(i);
                    double thanhTien = chiTiet.getSoLuong() * chiTiet.getGiaNhap();

                    addTableCell(table, String.valueOf(i + 1), fontNormal);
                    addTableCell(table, String.valueOf(chiTiet.getMaSP()), fontNormal);
                    addTableCell(table, String.valueOf(chiTiet.getMaLH()), fontNormal);
                    addTableCell(table, String.valueOf(chiTiet.getSoLuong()), fontNormal);
                    addTableCell(table, currencyFormat.format(chiTiet.getGiaNhap()), fontNormal);
                    addTableCell(table, chiTiet.getNsx() != null ? dateFormat.format(chiTiet.getNsx()) : "", fontNormal);
                    addTableCell(table, chiTiet.getHsd() != null ? dateFormat.format(chiTiet.getHsd()) : "", fontNormal);
                    addTableCell(table, currencyFormat.format(thanhTien), fontNormal);
                }

                document.add(table);

                // Thêm phần ký tên
                PdfPTable signatureTable = new PdfPTable(2);
                signatureTable.setWidthPercentage(100);
                signatureTable.setSpacingBefore(30f);
                signatureTable.setWidths(new float[]{1f, 1f});

                // Cột Người lập phiếu
                PdfPCell creatorCell = new PdfPCell();
                creatorCell.setBorder(0);
                creatorCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                creatorCell.addElement(new Paragraph("Người lập phiếu", fontBold));
                creatorCell.addElement(new Paragraph("(Ký, ghi rõ họ tên)", fontNormal));
                creatorCell.addElement(new Paragraph("\n\n\n", fontNormal)); // Khoảng trống cho chữ ký
                creatorCell.addElement(new Paragraph(nhanVien != null ? nhanVien.getTenNV() : "", fontNormal));
                signatureTable.addCell(creatorCell);

                // Cột Đại diện nhà cung cấp
                PdfPCell supplierCell = new PdfPCell();
                supplierCell.setBorder(0);
                supplierCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                supplierCell.addElement(new Paragraph("Đại diện nhà cung cấp", fontBold));
                supplierCell.addElement(new Paragraph("(Ký, ghi rõ họ tên)", fontNormal));
                supplierCell.addElement(new Paragraph("\n\n\n", fontNormal)); // Khoảng trống cho chữ ký
//                supplierCell.addElement(new Paragraph(nhaCungCap != null ? nhaCungCap.getTenNCC() : "", fontNormal));
                signatureTable.addCell(supplierCell);

                document.add(signatureTable);

                document.close();

                JOptionPane.showMessageDialog(null, "Xuất phiếu nhập hàng thành công!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Không thể xuất phiếu nhập hàng vì file đang được mở.\nVui lòng đóng file và thử lại.", "Lỗi File", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xuất phiếu nhập hàng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

        try {
            // Trả về Look & Feel mặc định của Java
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addInfoCell(PdfPTable table, String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBorder(0);
        table.addCell(cell);
    }

    private void addTableCell(PdfPTable table, String content, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
}