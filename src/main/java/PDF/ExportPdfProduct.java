package PDF;

import BLL.SanPhamBLL;
import BLL.LoaiSanPhamBLL;
import BLL.NhaCungCapBLL;
import DTO.SanPhamDTO;
import DTO.LoaiSanPhamDTO;
import DTO.NhaCungCapDTO;
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
import java.util.List;
import java.util.Locale;

public class ExportPdfProduct {

    private final SanPhamBLL sanPhamBLL = new SanPhamBLL();
    private final LoaiSanPhamBLL loaiSanPhamBLL = new LoaiSanPhamBLL();
    private final NhaCungCapBLL nhaCungCapBLL = new NhaCungCapBLL();
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    public void exportPDF() {
        try {
            // Đặt giao diện giống hệ điều hành
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn nơi lưu danh sách sản phẩm và đặt tên file");
        chooser.setSelectedFile(new File("DanhSachSanPham.pdf"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            // Thêm đuôi .pdf nếu người dùng không nhập
            if (!selectedFile.getName().toLowerCase().endsWith(".pdf")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".pdf");
            }

            try {
                // Tạo tài liệu PDF mới
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

                // Tiêu đề phụ
                Paragraph subheading = new Paragraph("DANH SÁCH SẢN PHẨM\n\n", fontBold);
                subheading.setAlignment(Element.ALIGN_CENTER);
                document.add(subheading);

                // Tạo bảng sản phẩm
                PdfPTable table = new PdfPTable(7);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{1f, 2.5f, 1.5f, 1.5f, 2f, 2f,2f});
                table.setSpacingBefore(10f);

                // Tiêu đề bảng
                String[] headers = {"Mã SP", "Tên sản phẩm", "Giá", "Số lượng tồn", "Loại sản phẩm", "Nhà cung cấp","Trạng thái"};
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor(new BaseColor(33, 58, 89));
                    table.addCell(cell);
                }

                // Dữ liệu sản phẩm
                List<SanPhamDTO> productList = sanPhamBLL.getAllProducts();
                for (int i = 0; i < productList.size(); i++) {
                    SanPhamDTO product = productList.get(i);
                    LoaiSanPhamDTO loaiSP = loaiSanPhamBLL.getLoaiSanPham(product.getMaLSP());
                    NhaCungCapDTO ncc = nhaCungCapBLL.getNhaCungCap(product.getMaNCC());

                    addTableCell(table, String.valueOf(product.getMaSP()), fontNormal);
                    addTableCell(table, product.getTenSP(), fontNormal);
                    addTableCell(table, currencyFormat.format(product.getGia()), fontNormal);
                    addTableCell(table, String.valueOf(product.getSoLuongTon()), fontNormal);
                    addTableCell(table, loaiSP != null ? loaiSP.getTenLoaiSP() : "Chưa cập nhật", fontNormal);
                    addTableCell(table, ncc != null ? ncc.getTenNCC() : "Chưa cập nhật", fontNormal);
                    addTableCell(table,product.getTrangThai(),fontNormal);
                }

                document.add(table);

                document.close();

                JOptionPane.showMessageDialog(null, "Xuất danh sách sản phẩm thành công!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Không thể xuất danh sách sản phẩm vì file đang được mở.\nVui lòng đóng file và thử lại.", "Lỗi File", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xuất danh sách sản phẩm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

        try {
            // Trả về giao diện mặc định của Java
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTableCell(PdfPTable table, String content, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
}
