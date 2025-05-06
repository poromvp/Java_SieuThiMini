package GUI.Admin_PanelThongKe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
import javax.swing.table.TableModel;


import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Date;

import BLL.KhuyenMaiBLL;
import BLL.LoaiSanPhamBLL;
import BLL.NhaCungCapBLL;
import BLL.NhanVienBLL;
import BLL.TaiKhoanBLL;
import BLL.TheThanhVienBLL;
import DTO.DonHangDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapHangDTO;
import DTO.SanPhamDTO;
import DTO.SearchBanChayDTO;
import DTO.SearchKHDHDTO;
import DTO.SearchLoHangDTO;
import DTO.SearchNVDHDTO;
import DTO.SearchNhanVienDTO;
import DTO.SearchTheThanhVienDTO;
import DTO.SearchTonKhoDTO;
import DTO.TheThanhVienDTO;
import GUI.Export;
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
        setBackground(new Color(33, 58, 89));

        // Panel radio button
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        radioPanel.setBackground(new Color(33, 58, 89));
        rbExcel = new JRadioButton("Excel");
        rbPDF = new JRadioButton("PDF");

        rbExcel.setForeground(Color.WHITE);
        rbPDF.setForeground(Color.WHITE);
        rbExcel.setBackground(new Color(33, 58, 89));
        rbPDF.setBackground(new Color(33, 58, 89));

        ButtonGroup group = new ButtonGroup();
        group.add(rbExcel);
        group.add(rbPDF);

        // chọn mặc định
        rbExcel.setSelected(true);

        radioPanel.add(rbExcel);
        radioPanel.add(rbPDF);

        add(radioPanel, BorderLayout.CENTER);
    }

    public void XuatPDF(DefaultTableModel model) {
        this.tableModel = model;
        TienIch.CustomMessage("Xuất file ra file PDF...");
    }

    public void XuatExccel(JTable tb, String title){
        Export.exportToExcel(tb, title);
    }

    public void XuatExccel(DefaultTableModel model) {
        this.tableModel = model;
        TienIch.CustomMessage("Xuất file ra file Excel...");
    }

    // Trả về định dạng người dùng đã chọn
    public String getSelectedFormat() {
        if (rbExcel.isSelected())
            return "excel";
        else if (rbPDF.isSelected())
            return "pdf";
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
                BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED);
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

    public static void printTheThanhVienTable(JTable table) {
        JFileChooser chooser = new JFileChooser("src/main/resources/file/export/");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Chọn thư mục để lưu danh sách thẻ thành viên");

        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            String filePath = selectedFolder.getAbsolutePath() + File.separator + "DanhSachTheThanhVien_"
                    + System.currentTimeMillis() + ".pdf";
            try {
                // Tạo document mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Font hỗ trợ tiếng Việt
                String fontPath = "src/main/resources/fonts/arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontNormal = new Font(baseFont, 12);
                //Font fontBold = new Font(baseFont, 12, Font.BOLD);
                Font fontTitle = new Font(baseFont, 22, Font.BOLD);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD, com.itextpdf.text.BaseColor.WHITE);

                // Logo
                String imagePath = "src/main/resources/images/icon/Logo_Main.png";
                Image logo = Image.getInstance(imagePath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);

                // Ngày tháng năm và tên người in
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date(System.currentTimeMillis());
                String currentDate = "" + dateFormat.format(today);
                String tenNhanVien = "a";
                Paragraph dateAndUser = new Paragraph("Ngày in: " + currentDate + " | Người in: " + tenNhanVien,
                        fontNormal);
                dateAndUser.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                document.add(dateAndUser);

                // Tiêu đề
                Paragraph title = new Paragraph("DANH SÁCH THẺ THÀNH VIÊN\n\n", fontTitle);
                title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                document.add(title);

                // Tạo bảng dữ liệu
                TableModel model = table.getModel();
                int columnCount = model.getColumnCount();
                PdfPTable pdfTable = new PdfPTable(columnCount);
                pdfTable.setWidthPercentage(100);
                pdfTable.setWidths(new float[] { 1.5f, 3f, 2f, 1.5f }); // Tỷ lệ cột tương ứng với bảng thẻ thành viên
                pdfTable.setSpacingBefore(10f);

                // Header bảng
                String[] headers = { "Mã thành viên", "Họ tên", "Số điện thoại", "Điểm tích lũy" };
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor(new com.itextpdf.text.BaseColor(33, 58, 89));
                    pdfTable.addCell(cell);
                }

                // Dữ liệu bảng
                for (int row = 0; row < model.getRowCount(); row++) {
                    for (int col = 0; col < columnCount; col++) {
                        Object value = model.getValueAt(row, col);
                        PdfPCell cell = new PdfPCell(new Paragraph(value != null ? value.toString() : "", fontNormal));
                        cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        pdfTable.addCell(cell);
                    }
                }

                document.add(pdfTable);
                document.close();

                JOptionPane.showMessageDialog(null, "Xuất danh sách thẻ thành viên thành công!", "SUCCESS",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (java.io.FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Không thể xuất file vì file đang được mở.\nVui lòng đóng file và thử lại.", "Lỗi File",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xuất file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Phương thức mới để xuất ArrayList thành PDF
    public static void exportArrayListToPDF(ArrayList<?> list) {
        JFileChooser chooser = new JFileChooser("src/main/resources/file/export/");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Chọn thư mục để lưu danh sách thẻ thành viên");

        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            String filePath = selectedFolder.getAbsolutePath() + File.separator + "DanhSachTheThanhVien_"
                    + System.currentTimeMillis() + ".pdf";
            try {
                // Tạo document mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Font hỗ trợ tiếng Việt
                String fontPath = "src/main/resources/fonts/arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontNormal = new Font(baseFont, 12);
                //Font fontBold = new Font(baseFont, 12, Font.BOLD);
                Font fontTitle = new Font(baseFont, 22, Font.BOLD);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD, com.itextpdf.text.BaseColor.WHITE);

                // Ngày tháng năm và tên người in
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date(System.currentTimeMillis());
                String currentDate = dateFormat.format(today);
                String tenNhanVien = "Không xác định"; // Thay bằng tên nhân viên thực tế nếu có
                Paragraph dateAndUser = new Paragraph("Ngày in: " + currentDate + " | Người in: " + tenNhanVien,
                        fontNormal);
                dateAndUser.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                document.add(dateAndUser);

                // Logo
                String imagePath = "src/main/resources/images/icon/Logo_Main.png";
                Image logo = Image.getInstance(imagePath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);

                // Tiêu đề
                Paragraph title = new Paragraph("DANH SÁCH THẺ THÀNH VIÊN\n\n", fontTitle);
                title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                document.add(title);

                // Tạo bảng dữ liệu
                int columnCount = 8; // Dựa trên dữ liệu thành viên
                PdfPTable pdfTable = new PdfPTable(columnCount);
                pdfTable.setWidthPercentage(100);
                pdfTable.setWidths(new float[] { 1f, 2.5f, 2f, 1.5f, 2.5f, 1.5f, 2.5f, 2.5f });
                pdfTable.setSpacingBefore(10f);

                // Header bảng
                String[] headers = { "Mã", "Họ tên", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Điểm tích lũy",
                        "Ngày BD", "Ngày KT" };
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor(new com.itextpdf.text.BaseColor(33, 58, 89));
                    pdfTable.addCell(cell);
                }

                // Dữ liệu bảng
                for (Object obj : list) {
                    // Giả sử obj là một đối tượng có các phương thức getter
                    try {
                        java.lang.reflect.Method getMaThanhVien = obj.getClass().getMethod("getMaTV");
                        java.lang.reflect.Method getHoTen = obj.getClass().getMethod("getTenTV");
                        java.lang.reflect.Method getNgaySinh = obj.getClass().getMethod("getNgaySinh");
                        java.lang.reflect.Method getDiaChi = obj.getClass().getMethod("getDiaChi");
                        java.lang.reflect.Method getSoDienThoai = obj.getClass().getMethod("getSdt");
                        java.lang.reflect.Method getDiemTichLuy = obj.getClass().getMethod("getDiemTL");
                        java.lang.reflect.Method getNgayBD = obj.getClass().getMethod("getNgayBD");
                        java.lang.reflect.Method getNgayKT = obj.getClass().getMethod("getNgayKT");

                        String maThanhVien = String.valueOf(getMaThanhVien.invoke(obj));
                        String hoTen = String.valueOf(getHoTen.invoke(obj));
                        String soDienThoai = String.valueOf(getSoDienThoai.invoke(obj));
                        String diemTichLuy = String.valueOf(getDiemTichLuy.invoke(obj));
                        String ngaysinh = String.valueOf(getNgaySinh.invoke(obj));
                        String diachi = String.valueOf(getDiaChi.invoke(obj));
                        String ngayBD = String.valueOf(getNgayBD.invoke(obj));
                        String ngayKT = String.valueOf(getNgayKT.invoke(obj));

                        PdfPCell cell1 = new PdfPCell(new Paragraph(maThanhVien, fontNormal));
                        PdfPCell cell2 = new PdfPCell(new Paragraph(hoTen, fontNormal));
                        PdfPCell cell3 = new PdfPCell(new Paragraph(ngaysinh, fontNormal));
                        PdfPCell cell4 = new PdfPCell(new Paragraph(diachi, fontNormal));
                        PdfPCell cell5 = new PdfPCell(new Paragraph(soDienThoai, fontNormal));
                        PdfPCell cell6 = new PdfPCell(new Paragraph(diemTichLuy, fontNormal));
                        PdfPCell cell7 = new PdfPCell(new Paragraph(ngayBD, fontNormal));
                        PdfPCell cell8 = new PdfPCell(new Paragraph(ngayKT, fontNormal));

                        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell6.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell7.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell8.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

                        pdfTable.addCell(cell1);
                        pdfTable.addCell(cell2);
                        pdfTable.addCell(cell3);
                        pdfTable.addCell(cell4);
                        pdfTable.addCell(cell5);
                        pdfTable.addCell(cell6);
                        pdfTable.addCell(cell7);
                        pdfTable.addCell(cell8);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi truy cập dữ liệu: " + e.getMessage(), "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        document.close();
                        return;
                    }
                }

                document.add(pdfTable);
                document.close();

                TienIch.CustomMessage("Xuất danh sách thẻ thành viên thành công!");

            } catch (java.io.FileNotFoundException e) {
                TienIch.CustomMessage("Không thể xuất file vì file đang được mở, vui lòng đóng file và thử lại");
            } catch (Exception e) {
                e.printStackTrace();
                TienIch.CustomMessage("Đã xảy ra lỗi khi xuất file!");
            }
        }
    }

    public static void InPDFTheThanhVienTheoSearch(ArrayList<?> list, SearchTheThanhVienDTO search, String MANV,
            String tua) {
        JFileChooser chooser = new JFileChooser("src/main/resources/file/export/");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Chọn thư mục để lưu danh sách thẻ thành viên");

        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            String filePath = selectedFolder.getAbsolutePath() + File.separator + "DanhSachTheThanhVien_"
                    + System.currentTimeMillis() + ".pdf";
            try {
                // Tạo document mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Font hỗ trợ tiếng Việt
                String fontPath = "src/main/resources/fonts/arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontNormal = new Font(baseFont, 12);
                Font fontBold = new Font(baseFont, 12, Font.BOLD);
                Font fontTitle = new Font(baseFont, 22, Font.BOLD);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD, com.itextpdf.text.BaseColor.WHITE);

                // Ngày tháng năm và tên người in
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date(System.currentTimeMillis());
                String currentDate = dateFormat.format(today);
                String tenNhanVien = new NhanVienBLL().getNameNV(MANV); // Thay bằng tên nhân viên thực tế nếu có
                Paragraph dateAndUser = new Paragraph("Ngày in: " + currentDate + " | Người in: " + tenNhanVien,
                        fontNormal);
                dateAndUser.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                document.add(dateAndUser);

                // Logo
                String imagePath = "src/main/resources/images/icon/Logo_Main.png";
                Image logo = Image.getInstance(imagePath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);

                // Tiêu đề
                Paragraph title = new Paragraph("DANH SÁCH THẺ THÀNH VIÊN" + tua + "\n\n", fontTitle);
                title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                document.add(title);

                // Tiêu chí lọc
                if (search.getMaTV() != 0) {
                    Paragraph ma = new Paragraph("Mã thành viên: " + search.getMaTV(), fontNormal);
                    ma.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(ma);
                }
                if (search.getTenTV() != null) {
                    if (!search.getTenTV().trim().isEmpty()) {
                        Paragraph ten = new Paragraph("Tên thành viên: " + search.getTenTV(), fontNormal);
                        ten.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(ten);
                    }
                }
                if (search.getDiaChi() != null) {
                    if (!search.getDiaChi().isEmpty()) {
                        Paragraph diachi = new Paragraph("Địa chỉ: " + search.getDiaChi(), fontNormal);
                        diachi.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(diachi);
                    }
                }
                if (search.getSinhFrom() != null && search.getSinhTo() != null) {
                    Paragraph sinh = new Paragraph(
                            "Ngày sinh từ: " + search.getSinhFrom() + " đến: " + search.getSinhTo(), fontNormal);
                    sinh.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(sinh);
                }
                if (search.getSDT() != null) {
                    if (!search.getSDT().trim().isEmpty()) {
                        Paragraph sdt = new Paragraph("Số điện thoại: " + search.getSDT(), fontNormal);
                        sdt.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(sdt);
                    }
                }
                if (search.getMaDH() != 0) {
                    Paragraph madh = new Paragraph("Mã đơn hàng: " + search.getMaDH(), fontNormal);
                    madh.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(madh);
                }
                if (search.getDhMin() != 0 && search.getDhMax() != 0) {
                    Paragraph numdh = new Paragraph(
                            "Tổng đơn hàng từ: " + search.getDhMin() + " đến " + search.getDhMax(), fontNormal);
                    document.add(numdh);
                }
                if (search.getTongMin() != 0 && search.getTongMax() != 0) {
                    Paragraph numchitieu = new Paragraph(
                            "Tổng chi tiêu từ: " + search.getTongMin() + " đến " + search.getTongMax(), fontNormal);
                    document.add(numchitieu);
                }
                if (search.getDtlMin() != 0 && search.getDtlMax() != 0) {
                    Paragraph numdtl = new Paragraph(
                            "Điểm tích lũy từ: " + search.getDtlMin() + " đến " + search.getDtlMax(), fontNormal);
                    document.add(numdtl);
                }
                if (search.getNgayMin() != null && search.getNgayMax() != null) {
                    Paragraph ngay = new Paragraph(
                            "Hạn thẻ từ: " + search.getNgayMin() + " đến " + search.getNgayMax(), fontNormal);
                    ngay.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(ngay);
                }
                if (search.getOrder() != null && search.getBy() != null) {
                    Paragraph orderby = new Paragraph(
                            "Sắp xếp: " + search.getOrder() + " | Theo cột: " + search.getBy(),
                            fontNormal);
                    orderby.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(orderby);
                }

                // Tạo bảng dữ liệu
                int columnCount = 8; // Dựa trên dữ liệu thành viên
                PdfPTable pdfTable = new PdfPTable(columnCount);
                pdfTable.setWidthPercentage(100);
                pdfTable.setWidths(new float[] { 0.8f, 2.5f, 2.5f, 2f, 2.5f, 1f, 2.5f, 2.5f });
                pdfTable.setSpacingBefore(10f);

                // Header bảng
                String[] headers = { "Mã", "Họ tên", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Điểm TL",
                        "Ngày BD", "Ngày KT" };
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor(new com.itextpdf.text.BaseColor(33, 58, 89));
                    pdfTable.addCell(cell);
                }

                // Dữ liệu bảng
                for (Object obj : list) {
                    // Giả sử obj là một đối tượng có các phương thức getter
                    try {
                        java.lang.reflect.Method getMaThanhVien = obj.getClass().getMethod("getMaTV");
                        java.lang.reflect.Method getHoTen = obj.getClass().getMethod("getTenTV");
                        java.lang.reflect.Method getNgaySinh = obj.getClass().getMethod("getNgaySinh");
                        java.lang.reflect.Method getDiaChi = obj.getClass().getMethod("getDiaChi");
                        java.lang.reflect.Method getSoDienThoai = obj.getClass().getMethod("getSdt");
                        java.lang.reflect.Method getDiemTichLuy = obj.getClass().getMethod("getDiemTL");
                        java.lang.reflect.Method getNgayBD = obj.getClass().getMethod("getNgayBD");
                        java.lang.reflect.Method getNgayKT = obj.getClass().getMethod("getNgayKT");

                        String maThanhVien = String.valueOf(getMaThanhVien.invoke(obj));
                        String hoTen = String.valueOf(getHoTen.invoke(obj));
                        String soDienThoai = String.valueOf(getSoDienThoai.invoke(obj));
                        String diemTichLuy = String.valueOf(getDiemTichLuy.invoke(obj));
                        String ngaysinh = String.valueOf(getNgaySinh.invoke(obj));
                        String diachi = String.valueOf(getDiaChi.invoke(obj));
                        String ngayBD = String.valueOf(getNgayBD.invoke(obj));
                        String ngayKT = String.valueOf(getNgayKT.invoke(obj));

                        PdfPCell cell1 = new PdfPCell(new Paragraph(maThanhVien, fontNormal));
                        PdfPCell cell2 = new PdfPCell(new Paragraph(hoTen, fontNormal));
                        PdfPCell cell3 = new PdfPCell(new Paragraph(ngaysinh, fontNormal));
                        PdfPCell cell4 = new PdfPCell(new Paragraph(diachi, fontNormal));
                        PdfPCell cell5 = new PdfPCell(new Paragraph(soDienThoai, fontNormal));
                        PdfPCell cell6 = new PdfPCell(new Paragraph(diemTichLuy, fontNormal));
                        PdfPCell cell7 = new PdfPCell(new Paragraph(ngayBD, fontNormal));
                        PdfPCell cell8 = new PdfPCell(new Paragraph(ngayKT, fontNormal));

                        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell6.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell7.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell8.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

                        pdfTable.addCell(cell1);
                        pdfTable.addCell(cell2);
                        pdfTable.addCell(cell3);
                        pdfTable.addCell(cell4);
                        pdfTable.addCell(cell5);
                        pdfTable.addCell(cell6);
                        pdfTable.addCell(cell7);
                        pdfTable.addCell(cell8);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi truy cập dữ liệu: " + e.getMessage(), "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        document.close();
                        return;
                    }
                }

                document.add(pdfTable);

                Paragraph nguoilambaocao = new Paragraph("Người in\nKý và ghi rõ họ tên", fontBold);
                Paragraph banquanli = new Paragraph("Ban quản lý\nKý và ghi rõ họ tên", fontBold);

                PdfPTable table2 = new PdfPTable(2);
                table2.setWidthPercentage(100);
                table2.setWidths(new float[] { 1, 1 });

                PdfPCell nguoilam = new PdfPCell(nguoilambaocao);
                nguoilam.setBorder(PdfPCell.NO_BORDER);
                nguoilam.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoilam.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                PdfPCell nguoiduyet = new PdfPCell(banquanli);
                nguoiduyet.setBorder(PdfPCell.NO_BORDER);
                nguoiduyet.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoiduyet.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                table2.addCell(nguoilam);
                table2.addCell(nguoiduyet);
                document.add(table2);

                document.close();

                TienIch.CustomMessage("Xuất danh sách thẻ thành viên thành công!");

            } catch (java.io.FileNotFoundException e) {
                TienIch.CustomMessage("Không thể xuất file vì file đang được mở, vui lòng đóng file và thử lại");
            } catch (Exception e) {
                e.printStackTrace();
                TienIch.CustomMessage("Đã xảy ra lỗi khi xuất file!");
            }
        }
    }

    public static void InPDFDonHangCuaTTVTheoSearch(ArrayList<DonHangDTO> list, SearchKHDHDTO search,
            TheThanhVienDTO member, String TONGCHITIEU, int SODONHANG, String MANV) {
        JFileChooser chooser = new JFileChooser("src/main/resources/file/export/");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Chọn thư mục để lưu danh sách đơn hàng của thẻ thành viên");

        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            String filePath = selectedFolder.getAbsolutePath() + File.separator + "DanhSachDonHangCuaTV_"
                    + member.getMaTV() + "_" +
                    +System.currentTimeMillis() + ".pdf";
            try {
                // Tạo document mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Font hỗ trợ tiếng Việt
                String fontPath = "src/main/resources/fonts/arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontNormal = new Font(baseFont, 12);
                Font fontBold = new Font(baseFont, 12, Font.BOLD);
                Font fontTitle = new Font(baseFont, 22, Font.BOLD);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD, com.itextpdf.text.BaseColor.WHITE);

                // Ngày tháng năm và tên người in
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date(System.currentTimeMillis());
                String currentDate = dateFormat.format(today);
                String tenNhanVien = new NhanVienBLL().getNameNV(MANV); // Thay bằng tên nhân viên thực tế nếu có
                Paragraph dateAndUser = new Paragraph("Ngày in: " + currentDate + " | Người in: " + tenNhanVien,
                        fontNormal);
                dateAndUser.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                document.add(dateAndUser);

                // Logo
                String imagePath = "src/main/resources/images/icon/Logo_Main.png";
                Image logo = Image.getInstance(imagePath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);

                // Tiêu đề
                Paragraph title = new Paragraph("DANH SÁCH ĐƠN HÀNG CỦA THÀNH VIÊN\n\n", fontTitle);
                title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                document.add(title);

                // Thông tin chi tiết
                String fileAVT = "src/main/resources/images/avtMember/" + member.getTenAnh();
                Image avt = Image.getInstance(fileAVT);
                avt.scaleToFit(150, 350);

                Paragraph tenTV = new Paragraph("Họ và tên: " + member.getTenTV(), fontBold);
                Paragraph namSinh = new Paragraph("Ngày sinh: " + member.getNgaySinh() + "", fontBold);
                Paragraph diaChi = new Paragraph("Địa chỉ: " + member.getDiaChi(), fontBold);
                Paragraph sdt = new Paragraph("Số điện thoại: " + member.getSdt(), fontBold);
                Paragraph diem = new Paragraph("Điểm: " + member.getDiemTL() + "", fontBold);
                Paragraph maTV = new Paragraph("Mã: " + member.getMaTV() + "", fontBold);
                Paragraph ngayBD = new Paragraph("Hạn thẻ từ:  " + member.getNgayBD() + "", fontBold);
                Paragraph ngayKT = new Paragraph("Hạn thẻ đến: " + member.getNgayKT() + "", fontBold);
                Paragraph tt1 = new Paragraph("Tổng đơn hàng: " + SODONHANG + "", fontBold);
                Paragraph tt2 = new Paragraph("Tổng chi tiêu: " + TONGCHITIEU + "", fontBold);

                // Tạo bảng với 3 cột
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100); // Bảng chiếm toàn bộ chiều rộng
                table.setWidths(new float[] { 1, 1, 1 }); // Tỷ lệ cột (ảnh nhỏ hơn thông tin)

                // Tạo ô cho ảnh
                PdfPCell avtCell = new PdfPCell(avt);
                avtCell.setBorder(PdfPCell.NO_BORDER); // Không viền
                avtCell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT); // Căn trái ảnh
                avtCell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP); // Căn trên cùng

                // Tạo ô cho thông tin chi tiết
                PdfPCell infoCell = new PdfPCell();
                infoCell.setBorder(PdfPCell.NO_BORDER); // Không viền
                infoCell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP); // Căn trên cùng

                // Tạo ô cho tóm tắt
                PdfPCell infoCell2 = new PdfPCell();
                infoCell2.setBorder(PdfPCell.NO_BORDER);
                infoCell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                infoCell2.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                // Thêm các đoạn văn vào ô thông tin
                infoCell.addElement(tenTV);
                infoCell.addElement(namSinh);
                infoCell.addElement(diaChi);
                infoCell.addElement(sdt);
                infoCell.addElement(diem);
                infoCell.addElement(maTV);

                infoCell2.addElement(tt1);
                infoCell2.addElement(tt2);
                infoCell2.addElement(ngayBD);
                infoCell2.addElement(ngayKT);

                // Thêm các ô vào bảng
                table.addCell(avtCell);
                table.addCell(infoCell);
                table.addCell(infoCell2);

                // Thêm bảng vào tài liệu
                document.add(table);

                // Tiêu chí lọc
                if (search.getMaDH() != 0) {
                    Paragraph ma = new Paragraph("Mã đơn hàng: " + search.getMaDH(), fontNormal);
                    ma.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(ma);
                }
                if (search.getNgayTTfrom() != null && search.getNgayTTto() != null) {
                    Paragraph ngayTT = new Paragraph(
                            "Ngày thanh toán từ: " + search.getNgayTTfrom() + " đến " + search.getNgayTTto(),
                            fontNormal);
                    ngayTT.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(ngayTT);
                }
                if (search.getTienMin() != 0 && search.getTienMax() != 0) {
                    Paragraph thanhtien = new Paragraph(
                            "Thành tiền từ: " + search.getTienMin() + " đến " + search.getTienMax(), fontNormal);
                    document.add(thanhtien);
                }
                if (search.getPTTT() != null) {
                    Paragraph ptttoan = new Paragraph("Phương thức thanh toán: " + search.getPTTT(),
                            fontNormal);
                    ptttoan.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(ptttoan);
                }
                if (search.getMaNV() != 0) {
                    Paragraph maNV = new Paragraph("Mã nhân viên: " + search.getMaNV(), fontNormal);
                    maNV.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maNV);
                }
                if (search.getTenNV() != null) {
                    if (!search.getTenNV().trim().isEmpty()) {
                        Paragraph tenNV = new Paragraph("Tên nhân viên: " + search.getTenNV(), fontNormal);
                        tenNV.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenNV);
                    }
                }
                if (search.getMaKM() != 0) {
                    Paragraph maKM = new Paragraph("Mã khuyến mãi: " + search.getMaKM(), fontNormal);
                    maKM.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maKM);
                }
                if (search.getTenKM() != null) {
                    if (!search.getTenKM().trim().isEmpty()) {
                        Paragraph tenKM = new Paragraph("Tên khuyến mãi: " + search.getTenKM(), fontNormal);
                        tenKM.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenKM);
                    }
                }
                if (search.getMaSP() != 0) {
                    Paragraph maSP = new Paragraph("Mã sản phẩm: " + search.getMaSP(), fontNormal);
                    maSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maSP);
                }
                if (search.getTenSP() != null) {
                    if (!search.getTenSP().trim().isEmpty()) {
                        Paragraph tenSP = new Paragraph("Tên sản phẩm: " + search.getTenSP(), fontNormal);
                        tenSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenSP);
                    }
                }
                if (search.getMaLSP() != 0) {
                    Paragraph maLSP = new Paragraph("Mã loại sản phẩm: " + search.getMaLSP(), fontNormal);
                    maLSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maLSP);
                }
                if (search.getTenLSP() != null) {
                    if (!search.getTenLSP().trim().isEmpty()) {
                        Paragraph tenLSP = new Paragraph("Tên loại sản phẩm: " + search.getTenLSP(), fontNormal);
                        tenLSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenLSP);
                    }
                }
                if (search.getOrder() != null && search.getBy() != null) {
                    Paragraph orderby = new Paragraph(
                            "Sắp xếp: " + search.getOrder() + " | Theo cột: " + search.getBy(),
                            fontNormal);
                    orderby.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(orderby);
                }

                // Tạo bảng dữ liệu
                int columnCount = 8; // Dựa trên dữ liệu
                PdfPTable pdfTable = new PdfPTable(columnCount);
                pdfTable.setWidthPercentage(100);
                pdfTable.setWidths(new float[] { 1f, 1.5f, 2.2f, 1.5f, 2f, 1f, 1.8f, 2f });
                pdfTable.setSpacingBefore(10f);

                // Header bảng
                String[] headers = { "Mã ĐH", "KM", "Tên NV", "PTTToán", "Ngày TT", "Mã DTL",
                        "Tiền KD", "Tổng tiền" };
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor(new com.itextpdf.text.BaseColor(33, 58, 89));
                    pdfTable.addCell(cell);
                }

                // Dữ liệu bảng
                for (DonHangDTO obj : list) {
                    try {
                        String madonhang = obj.getMaDH() + "";
                        String tenkhuyenmai = obj.getMaKM() != 0
                                ? KhuyenMaiBLL.getDiscountById(obj.getMaKM()).getTenKM()
                                : "Không";
                        String tennhanvien = new NhanVienBLL().getNhanVienByMa(obj.getMaNV() + "").getTenNV();
                        String phuongthucthanhtoan = obj.getPtThanhToan();
                        String ngaythanhtoan = obj.getNgayTT();
                        String madiemtichluy = obj.getMaDTL() != 0 ? obj.getMaDTL() + "" : "Không";
                        String tienkhachdua = TienIch.formatVND(obj.getTienKD());
                        String tongtien = TienIch.formatVND(obj.getTongTien());

                        PdfPCell cell1 = new PdfPCell(new Paragraph(madonhang, fontNormal));
                        PdfPCell cell2 = new PdfPCell(new Paragraph(tenkhuyenmai, fontNormal));
                        PdfPCell cell3 = new PdfPCell(new Paragraph(tennhanvien, fontNormal));
                        PdfPCell cell4 = new PdfPCell(new Paragraph(phuongthucthanhtoan, fontNormal));
                        PdfPCell cell5 = new PdfPCell(new Paragraph(ngaythanhtoan, fontNormal));
                        PdfPCell cell6 = new PdfPCell(new Paragraph(madiemtichluy, fontNormal));
                        PdfPCell cell7 = new PdfPCell(new Paragraph(tienkhachdua, fontNormal));
                        PdfPCell cell8 = new PdfPCell(new Paragraph(tongtien, fontNormal));

                        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell6.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell7.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell8.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

                        pdfTable.addCell(cell1);
                        pdfTable.addCell(cell2);
                        pdfTable.addCell(cell3);
                        pdfTable.addCell(cell4);
                        pdfTable.addCell(cell5);
                        pdfTable.addCell(cell6);
                        pdfTable.addCell(cell7);
                        pdfTable.addCell(cell8);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi truy cập dữ liệu: " + e.getMessage(), "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        document.close();
                        return;
                    }
                }

                document.add(pdfTable);

                Paragraph nguoilambaocao = new Paragraph("Người in\nKý và ghi rõ họ tên", fontBold);
                Paragraph banquanli = new Paragraph("Ban quản lý\nKý và ghi rõ họ tên", fontBold);

                PdfPTable table2 = new PdfPTable(2);
                table2.setWidthPercentage(100);
                table2.setWidths(new float[] { 1, 1 });

                PdfPCell nguoilam = new PdfPCell(nguoilambaocao);
                nguoilam.setBorder(PdfPCell.NO_BORDER);
                nguoilam.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoilam.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                PdfPCell nguoiduyet = new PdfPCell(banquanli);
                nguoiduyet.setBorder(PdfPCell.NO_BORDER);
                nguoiduyet.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoiduyet.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                table2.addCell(nguoilam);
                table2.addCell(nguoiduyet);
                document.add(table2);

                document.close();

                TienIch.CustomMessage("Xuất danh sách đơn hàng của thành viên thành công!");

            } catch (java.io.FileNotFoundException e) {
                TienIch.CustomMessage("Không thể xuất file vì file đang được mở, vui lòng đóng file và thử lại");
            } catch (Exception e) {
                e.printStackTrace();
                TienIch.CustomMessage("Đã xảy ra lỗi khi xuất file!");
            }
        }
    }

    public static void InPDFNhanVienTheoSearch(ArrayList<NhanVienDTO> list, SearchNhanVienDTO search, String MANV) {
        JFileChooser chooser = new JFileChooser("src/main/resources/file/export/");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Chọn thư mục để lưu danh sách nhân viên");

        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            String filePath = selectedFolder.getAbsolutePath() + File.separator + "DanhSachNhanVien_"
                    + System.currentTimeMillis() + ".pdf";
            try {
                // Tạo document mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Font hỗ trợ tiếng Việt
                String fontPath = "src/main/resources/fonts/arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontNormal = new Font(baseFont, 12);
                Font fontBold = new Font(baseFont, 12, Font.BOLD);
                Font fontTitle = new Font(baseFont, 22, Font.BOLD);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD, com.itextpdf.text.BaseColor.WHITE);

                // Ngày tháng năm và tên người in
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date(System.currentTimeMillis());
                String currentDate = dateFormat.format(today);
                String tenNhanVien = new NhanVienBLL().getNameNV(MANV); // Thay bằng tên nhân viên thực tế nếu có
                Paragraph dateAndUser = new Paragraph("Ngày in: " + currentDate + " | Người in: " + tenNhanVien,
                        fontNormal);
                dateAndUser.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                document.add(dateAndUser);

                // Logo
                String imagePath = "src/main/resources/images/icon/Logo_Main.png";
                Image logo = Image.getInstance(imagePath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);

                // Tiêu đề
                Paragraph title = new Paragraph("DANH SÁCH NHÂN VIÊN\n\n", fontTitle);
                title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                document.add(title);

                // Tiêu chí lọc
                if (search.getMaNV() != 0) {
                    Paragraph maNV = new Paragraph("Mã nhân viên: " + search.getMaNV(), fontNormal);
                    maNV.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maNV);
                }
                if (search.getTenNV() != null) {
                    if (!search.getTenNV().trim().isEmpty()) {
                        Paragraph tenNV = new Paragraph("Tên nhân viên: " + search.getTenNV(), fontNormal);
                        tenNV.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenNV);
                    }
                }
                if (search.getDiaChi() != null) {
                    if (!search.getDiaChi().trim().isEmpty()) {
                        Paragraph diaChi = new Paragraph("Địa chỉ: " + search.getDiaChi(), fontNormal);
                        diaChi.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(diaChi);
                    }
                }
                if (search.getNgaySinhFrom() != null && search.getNgaySinhTo() != null) {
                    Paragraph ngaySinh = new Paragraph(
                            "Ngày sinh từ: " + search.getNgaySinhFrom() + " đến " + search.getNgaySinhTo(), fontNormal);
                    ngaySinh.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(ngaySinh);
                }
                if (search.getQuyen() != null) {
                    if (!search.getQuyen().trim().isEmpty()) {
                        Paragraph chucVu = new Paragraph("Chức vụ: " + search.getQuyen(), fontNormal);
                        chucVu.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(chucVu);
                    }
                }
                if (search.getSDT() != null) {
                    if (!search.getSDT().trim().isEmpty()) {
                        Paragraph SDT = new Paragraph("Số điện thoại: " + search.getSDT(), fontNormal);
                        SDT.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(SDT);
                    }
                }
                if (search.getMaDH() != 0) {
                    Paragraph maDH = new Paragraph("Mã đơn hàng: " + search.getMaDH(), fontNormal);
                    maDH.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maDH);
                }
                if (search.getTongDHMin() != 0 && search.getTongDHMax() != 0) {
                    Paragraph tongDonHang = new Paragraph(
                            "Tổng đơn hàng từ: " + search.getTongDHMin() + " đến " + search.getTongDHMax(), fontNormal);
                    document.add(tongDonHang);
                }
                if (search.getDoanhSoMin() != 0 && search.getDoanhSoMax() != 0) {
                    Paragraph doanhSo = new Paragraph(
                            "Doanh số từ: " + search.getDoanhSoMin() + " đến " + search.getDoanhSoMax(), fontNormal);
                    document.add(doanhSo);
                }
                if (search.getLuongMin() != 0 && search.getLuongMax() != 0) {
                    Paragraph Luong = new Paragraph(
                            "Lương từ: " + search.getLuongMin() + " đến " + search.getLuongMax(), fontNormal);
                    document.add(Luong);
                }
                if (search.getOrder() != null && search.getBy() != null) {
                    Paragraph orderby = new Paragraph(
                            "Sắp xếp: " + search.getOrder() + " | Theo cột: " + search.getBy(),
                            fontNormal);
                    orderby.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(orderby);
                }

                // Tạo bảng dữ liệu
                int columnCount = 8; // Dựa trên dữ liệu
                PdfPTable pdfTable = new PdfPTable(columnCount);
                pdfTable.setWidthPercentage(100);
                pdfTable.setWidths(new float[] { 0.5f, 1.5f, 0.8f, 1.8f, 2.2f, 1.5f, 2f, 2f });
                pdfTable.setSpacingBefore(10f);

                // Header bảng
                String[] headers = { "Mã NV", "Tên NV", "Giới tính", "Ngày Sinh", "CCCD",
                        "Địa chỉ", "SĐT", "Lương" };
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor(new com.itextpdf.text.BaseColor(33, 58, 89));
                    pdfTable.addCell(cell);
                }

                // Dữ liệu bảng
                for (NhanVienDTO obj : list) {
                    try {
                        String manhanvien = obj.getMaNV() + "";
                        String tennhanvien = obj.getTenNV();
                        String gioitinh = obj.getGioiTinh();
                        String ngaysinh = obj.getNgaySinh() + "";
                        String cancuoccongdan = obj.getCCCD();
                        String diachi = obj.getDiaChi();
                        String sodienthoai = obj.getSDT();
                        String luong = TienIch.formatVND(obj.getLuong());

                        PdfPCell cell1 = new PdfPCell(new Paragraph(manhanvien, fontNormal));
                        PdfPCell cell2 = new PdfPCell(new Paragraph(tennhanvien, fontNormal));
                        PdfPCell cell3 = new PdfPCell(new Paragraph(gioitinh, fontNormal));
                        PdfPCell cell4 = new PdfPCell(new Paragraph(ngaysinh, fontNormal));
                        PdfPCell cell5 = new PdfPCell(new Paragraph(cancuoccongdan, fontNormal));
                        PdfPCell cell6 = new PdfPCell(new Paragraph(diachi, fontNormal));
                        PdfPCell cell7 = new PdfPCell(new Paragraph(sodienthoai, fontNormal));
                        PdfPCell cell8 = new PdfPCell(new Paragraph(luong, fontNormal));

                        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell6.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell7.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell8.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

                        pdfTable.addCell(cell1);
                        pdfTable.addCell(cell2);
                        pdfTable.addCell(cell3);
                        pdfTable.addCell(cell4);
                        pdfTable.addCell(cell5);
                        pdfTable.addCell(cell6);
                        pdfTable.addCell(cell7);
                        pdfTable.addCell(cell8);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi truy cập dữ liệu: " + e.getMessage(), "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        document.close();
                        return;
                    }
                }

                document.add(pdfTable);

                Paragraph nguoilambaocao = new Paragraph("Người in\nKý và ghi rõ họ tên", fontBold);
                Paragraph banquanli = new Paragraph("Ban quản lý\nKý và ghi rõ họ tên", fontBold);

                PdfPTable table2 = new PdfPTable(2);
                table2.setWidthPercentage(100);
                table2.setWidths(new float[] { 1, 1 });

                PdfPCell nguoilam = new PdfPCell(nguoilambaocao);
                nguoilam.setBorder(PdfPCell.NO_BORDER);
                nguoilam.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoilam.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                PdfPCell nguoiduyet = new PdfPCell(banquanli);
                nguoiduyet.setBorder(PdfPCell.NO_BORDER);
                nguoiduyet.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoiduyet.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                table2.addCell(nguoilam);
                table2.addCell(nguoiduyet);
                document.add(table2);

                document.close();

                TienIch.CustomMessage("Xuất danh sách nhân viên thành công!");

            } catch (java.io.FileNotFoundException e) {
                TienIch.CustomMessage("Không thể xuất file vì file đang được mở, vui lòng đóng file và thử lại");
            } catch (Exception e) {
                e.printStackTrace();
                TienIch.CustomMessage("Đã xảy ra lỗi khi xuất file!");
            }
        }

    }

    public static void InPDFNhanVienTheoTotNhatSearch(ArrayList<NhanVienDTO> list, SearchNhanVienDTO search, Date from,
            Date to, String MANV) {
        JFileChooser chooser = new JFileChooser("src/main/resources/file/export/");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Chọn thư mục để lưu danh sách nhân viên");

        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            String filePath = selectedFolder.getAbsolutePath() + File.separator + "DanhSachNhanVienTot_"
                    + System.currentTimeMillis() + ".pdf";
            try {
                // Tạo document mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Font hỗ trợ tiếng Việt
                String fontPath = "src/main/resources/fonts/arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontNormal = new Font(baseFont, 12);
                Font fontBold = new Font(baseFont, 12, Font.BOLD);
                Font fontTitle = new Font(baseFont, 18, Font.BOLD);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD, com.itextpdf.text.BaseColor.WHITE);

                // Ngày tháng năm và tên người in
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date(System.currentTimeMillis());
                String currentDate = dateFormat.format(today);
                String tenNhanVien = new NhanVienBLL().getNameNV(MANV); // Thay bằng tên nhân viên thực tế nếu có
                Paragraph dateAndUser = new Paragraph("Ngày in: " + currentDate + " | Người in: " + tenNhanVien,
                        fontNormal);
                dateAndUser.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                document.add(dateAndUser);

                // Logo
                String imagePath = "src/main/resources/images/icon/Logo_Main.png";
                Image logo = Image.getInstance(imagePath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);

                // Tiêu đề
                Paragraph title = new Paragraph("DANH SÁCH NHÂN VIÊN CÓ DOANH SỐ TỐT NHẤT\n\n", fontTitle);
                title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                document.add(title);

                // Từ ngày
                Paragraph ngay = new Paragraph("Từ ngày: " + from + " đến: " + to + "\n", fontBold);
                ngay.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                document.add(ngay);

                // Tiêu chí lọc
                if (search.getMaNV() != 0) {
                    Paragraph maNV = new Paragraph("Mã nhân viên: " + search.getMaNV(), fontNormal);
                    maNV.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maNV);
                }
                if (search.getTenNV() != null) {
                    if (!search.getTenNV().trim().isEmpty()) {
                        Paragraph tenNV = new Paragraph("Tên nhân viên: " + search.getTenNV(), fontNormal);
                        tenNV.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenNV);
                    }
                }
                if (search.getDiaChi() != null) {
                    if (!search.getDiaChi().trim().isEmpty()) {
                        Paragraph diaChi = new Paragraph("Địa chỉ: " + search.getDiaChi(), fontNormal);
                        diaChi.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(diaChi);
                    }
                }
                if (search.getNgaySinhFrom() != null && search.getNgaySinhTo() != null) {
                    Paragraph ngaySinh = new Paragraph(
                            "Ngày sinh từ: " + search.getNgaySinhFrom() + " đến " + search.getNgaySinhTo(), fontNormal);
                    ngaySinh.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(ngaySinh);
                }
                if (search.getQuyen() != null) {
                    if (!search.getQuyen().trim().isEmpty()) {
                        Paragraph chucVu = new Paragraph("Chức vụ: " + search.getQuyen(), fontNormal);
                        chucVu.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(chucVu);
                    }
                }
                if (search.getSDT() != null) {
                    if (!search.getSDT().trim().isEmpty()) {
                        Paragraph SDT = new Paragraph("Số điện thoại: " + search.getSDT(), fontNormal);
                        SDT.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(SDT);
                    }
                }
                if (search.getMaDH() != 0) {
                    Paragraph maDH = new Paragraph("Mã đơn hàng: " + search.getMaDH(), fontNormal);
                    maDH.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maDH);
                }
                if (search.getTongDHMin() != 0 && search.getTongDHMax() != 0) {
                    Paragraph tongDonHang = new Paragraph(
                            "Tổng đơn hàng từ: " + search.getTongDHMin() + " đến " + search.getTongDHMax(), fontNormal);
                    document.add(tongDonHang);
                }
                if (search.getDoanhSoMin() != 0 && search.getDoanhSoMax() != 0) {
                    Paragraph doanhSo = new Paragraph(
                            "Doanh số từ: " + search.getDoanhSoMin() + " đến " + search.getDoanhSoMax(), fontNormal);
                    document.add(doanhSo);
                }
                if (search.getLuongMin() != 0 && search.getLuongMax() != 0) {
                    Paragraph Luong = new Paragraph(
                            "Lương từ: " + search.getLuongMin() + " đến " + search.getLuongMax(), fontNormal);
                    document.add(Luong);
                }
                if (search.getOrder() != null && search.getBy() != null) {
                    Paragraph orderby = new Paragraph(
                            "Sắp xếp: " + search.getOrder() + " | Theo cột: " + search.getBy(),
                            fontNormal);
                    orderby.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(orderby);
                }

                // Tạo bảng dữ liệu
                int columnCount = 8; // Dựa trên dữ liệu
                PdfPTable pdfTable = new PdfPTable(columnCount);
                pdfTable.setWidthPercentage(100);
                pdfTable.setWidths(new float[] { 0.5f, 1.5f, 0.8f, 1.8f, 2.2f, 1.5f, 2f, 2f });
                pdfTable.setSpacingBefore(10f);

                // Header bảng
                String[] headers = { "Mã NV", "Tên NV", "Giới tính", "Ngày Sinh", "CCCD",
                        "Địa chỉ", "SĐT", "Lương" };
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor(new com.itextpdf.text.BaseColor(33, 58, 89));
                    pdfTable.addCell(cell);
                }

                // Dữ liệu bảng
                for (NhanVienDTO obj : list) {
                    try {
                        String manhanvien = obj.getMaNV() + "";
                        String tennhanvien = obj.getTenNV();
                        String gioitinh = obj.getGioiTinh();
                        String ngaysinh = obj.getNgaySinh() + "";
                        String cancuoccongdan = obj.getCCCD();
                        String diachi = obj.getDiaChi();
                        String sodienthoai = obj.getSDT();
                        String luong = TienIch.formatVND(obj.getLuong());

                        PdfPCell cell1 = new PdfPCell(new Paragraph(manhanvien, fontNormal));
                        PdfPCell cell2 = new PdfPCell(new Paragraph(tennhanvien, fontNormal));
                        PdfPCell cell3 = new PdfPCell(new Paragraph(gioitinh, fontNormal));
                        PdfPCell cell4 = new PdfPCell(new Paragraph(ngaysinh, fontNormal));
                        PdfPCell cell5 = new PdfPCell(new Paragraph(cancuoccongdan, fontNormal));
                        PdfPCell cell6 = new PdfPCell(new Paragraph(diachi, fontNormal));
                        PdfPCell cell7 = new PdfPCell(new Paragraph(sodienthoai, fontNormal));
                        PdfPCell cell8 = new PdfPCell(new Paragraph(luong, fontNormal));

                        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell6.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell7.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell8.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

                        pdfTable.addCell(cell1);
                        pdfTable.addCell(cell2);
                        pdfTable.addCell(cell3);
                        pdfTable.addCell(cell4);
                        pdfTable.addCell(cell5);
                        pdfTable.addCell(cell6);
                        pdfTable.addCell(cell7);
                        pdfTable.addCell(cell8);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi truy cập dữ liệu: " + e.getMessage(), "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        document.close();
                        return;
                    }
                }

                document.add(pdfTable);

                Paragraph nguoilambaocao = new Paragraph("Người in\nKý và ghi rõ họ tên", fontBold);
                Paragraph banquanli = new Paragraph("Ban quản lý\nKý và ghi rõ họ tên", fontBold);

                PdfPTable table2 = new PdfPTable(2);
                table2.setWidthPercentage(100);
                table2.setWidths(new float[] { 1, 1 });

                PdfPCell nguoilam = new PdfPCell(nguoilambaocao);
                nguoilam.setBorder(PdfPCell.NO_BORDER);
                nguoilam.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoilam.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                PdfPCell nguoiduyet = new PdfPCell(banquanli);
                nguoiduyet.setBorder(PdfPCell.NO_BORDER);
                nguoiduyet.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoiduyet.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                table2.addCell(nguoilam);
                table2.addCell(nguoiduyet);
                document.add(table2);

                document.close();

                TienIch.CustomMessage("Xuất danh sách nhân viên thành công!");

            } catch (java.io.FileNotFoundException e) {
                TienIch.CustomMessage("Không thể xuất file vì file đang được mở, vui lòng đóng file và thử lại");
            } catch (Exception e) {
                e.printStackTrace();
                TienIch.CustomMessage("Đã xảy ra lỗi khi xuất file!");
            }
        }
    }

    public static void InPDFDonHangCuaNVTheoSearch(ArrayList<DonHangDTO> list, SearchNVDHDTO search,
            NhanVienDTO nhanvien, String MANV) {
        JFileChooser chooser = new JFileChooser("src/main/resources/file/export/");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Chọn thư mục để lưu danh sách đơn hàng do nhân viên làm");

        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            String filePath = selectedFolder.getAbsolutePath() + File.separator + "DanhSachDonHangCuaNV_"
                    + nhanvien.getMaNV() + "_" +
                    +System.currentTimeMillis() + ".pdf";
            try {
                // Tạo document mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Font hỗ trợ tiếng Việt
                String fontPath = "src/main/resources/fonts/arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontNormal = new Font(baseFont, 12);
                Font fontBold = new Font(baseFont, 12, Font.BOLD);
                Font fontTitle = new Font(baseFont, 20, Font.BOLD);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD, com.itextpdf.text.BaseColor.WHITE);

                // Ngày tháng năm và tên người in
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date(System.currentTimeMillis());
                String currentDate = dateFormat.format(today);
                String tenNhanVien = new NhanVienBLL().getNameNV(MANV); // Thay bằng tên nhân viên thực tế nếu có
                Paragraph dateAndUser = new Paragraph("Ngày in: " + currentDate + " | Người in: " + tenNhanVien,
                        fontNormal);
                dateAndUser.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                document.add(dateAndUser);

                // Logo
                String imagePath = "src/main/resources/images/icon/Logo_Main.png";
                Image logo = Image.getInstance(imagePath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);

                // Tiêu đề
                Paragraph title = new Paragraph("DANH SÁCH ĐƠN HÀNG DO NHÂN VIÊN LÀM\n\n", fontTitle);
                title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                document.add(title);

                // Thông tin chi tiết
                String fileAVT = "src/main/resources/images/avtEmployee/" + nhanvien.getImage();
                Image avt = Image.getInstance(fileAVT);
                avt.scaleToFit(150, 350);

                Paragraph tenTV = new Paragraph("Họ và tên: " + nhanvien.getTenNV(), fontBold);
                Paragraph namSinh = new Paragraph("Ngày sinh: " + nhanvien.getNgaySinh(), fontBold);
                Paragraph diaChi = new Paragraph("Địa chỉ: " + nhanvien.getDiaChi(), fontBold);
                Paragraph sdt = new Paragraph("Số điện thoại: " + nhanvien.getSDT(), fontBold);
                Paragraph diem = new Paragraph("Chức vụ: " + new TaiKhoanBLL().getQuyenNV(nhanvien.getMaNV() + "") + "",
                        fontBold);
                Paragraph maTV = new Paragraph("Mã: " + nhanvien.getMaNV(), fontBold);
                double sum = 0;
                for (DonHangDTO dh : list) {
                    sum += dh.getTongTien();
                }
                Paragraph tt1 = new Paragraph("Tổng đơn hàng đã thực hiện: " + list.size(), fontBold);
                Paragraph tt2 = new Paragraph("Doanh số bán hàng: " + TienIch.formatVND(sum), fontBold);

                // Tạo bảng với 3 cột
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100); // Bảng chiếm toàn bộ chiều rộng
                table.setWidths(new float[] { 1, 1, 1 }); // Tỷ lệ cột (ảnh nhỏ hơn thông tin)

                // Tạo ô cho ảnh
                PdfPCell avtCell = new PdfPCell(avt);
                avtCell.setBorder(PdfPCell.NO_BORDER); // Không viền
                avtCell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT); // Căn trái ảnh
                avtCell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP); // Căn trên cùng

                // Tạo ô cho thông tin chi tiết
                PdfPCell infoCell = new PdfPCell();
                infoCell.setBorder(PdfPCell.NO_BORDER); // Không viền
                infoCell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP); // Căn trên cùng

                // Tạo ô cho tóm tắt
                PdfPCell infoCell2 = new PdfPCell();
                infoCell2.setBorder(PdfPCell.NO_BORDER);
                infoCell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                infoCell2.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                // Thêm các đoạn văn vào ô thông tin
                infoCell.addElement(tenTV);
                infoCell.addElement(namSinh);
                infoCell.addElement(diaChi);
                infoCell.addElement(sdt);
                infoCell.addElement(diem);
                infoCell.addElement(maTV);

                infoCell2.addElement(tt1);
                infoCell2.addElement(tt2);

                // Thêm các ô vào bảng
                table.addCell(avtCell);
                table.addCell(infoCell);
                table.addCell(infoCell2);

                // Thêm bảng vào tài liệu
                document.add(table);

                // Tiêu chí lọc
                if (search.getMaDH() != 0) {
                    Paragraph ma = new Paragraph("Mã đơn hàng: " + search.getMaDH(), fontNormal);
                    ma.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(ma);
                }
                if (search.getNgayTTfrom() != null && search.getNgayTTto() != null) {
                    Paragraph ngayTT = new Paragraph(
                            "Ngày thanh toán từ: " + search.getNgayTTfrom() + " đến " + search.getNgayTTto(),
                            fontNormal);
                    ngayTT.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(ngayTT);
                }
                if (search.getTienMin() != 0 && search.getTienMax() != 0) {
                    Paragraph thanhtien = new Paragraph(
                            "Thành tiền từ: " + search.getTienMin() + " đến " + search.getTienMax(), fontNormal);
                    document.add(thanhtien);
                }
                if (search.getPTTT() != null) {
                    Paragraph ptttoan = new Paragraph("Phương thức thanh toán: " + search.getPTTT(),
                            fontNormal);
                    ptttoan.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(ptttoan);
                }
                if (search.getMaKH() != 0) {
                    Paragraph maKH = new Paragraph("Mã khách hàng: " + search.getMaKH(), fontNormal);
                    maKH.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maKH);
                }
                if (search.getTenKH() != null) {
                    if (!search.getTenKH().trim().isEmpty()) {
                        Paragraph tenKH = new Paragraph("Tên khách hàng: " + search.getTenKH(), fontNormal);
                        tenKH.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenKH);
                    }
                }
                if (search.getMaKM() != 0) {
                    Paragraph maKM = new Paragraph("Mã khuyến mãi: " + search.getMaKM(), fontNormal);
                    maKM.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maKM);
                }
                if (search.getTenKM() != null) {
                    if (!search.getTenKM().trim().isEmpty()) {
                        Paragraph tenKM = new Paragraph("Tên khuyến mãi: " + search.getTenKM(), fontNormal);
                        tenKM.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenKM);
                    }
                }
                if (search.getMaSP() != 0) {
                    Paragraph maSP = new Paragraph("Mã sản phẩm: " + search.getMaSP(), fontNormal);
                    maSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maSP);
                }
                if (search.getTenSP() != null) {
                    if (!search.getTenSP().trim().isEmpty()) {
                        Paragraph tenSP = new Paragraph("Tên sản phẩm: " + search.getTenSP(), fontNormal);
                        tenSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenSP);
                    }
                }
                if (search.getMaLSP() != 0) {
                    Paragraph maLSP = new Paragraph("Mã loại sản phẩm: " + search.getMaLSP(), fontNormal);
                    maLSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maLSP);
                }
                if (search.getTenLSP() != null) {
                    if (!search.getTenLSP().trim().isEmpty()) {
                        Paragraph tenLSP = new Paragraph("Tên loại sản phẩm: " + search.getTenLSP(), fontNormal);
                        tenLSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenLSP);
                    }
                }
                if (search.getOrder() != null && search.getBy() != null) {
                    Paragraph orderby = new Paragraph(
                            "Sắp xếp: " + search.getOrder() + " | Theo cột: " + search.getBy(),
                            fontNormal);
                    orderby.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(orderby);
                }

                // Tạo bảng dữ liệu
                int columnCount = 8; // Dựa trên dữ liệu
                PdfPTable pdfTable = new PdfPTable(columnCount);
                pdfTable.setWidthPercentage(100);
                pdfTable.setWidths(new float[] { 1f, 1.5f, 2.2f, 1.5f, 2f, 1f, 1.8f, 2f });
                pdfTable.setSpacingBefore(10f);

                // Header bảng
                String[] headers = { "Mã ĐH", "KM", "Tên NV", "PTTToán", "Ngày TT", "Mã DTL",
                        "Tiền KD", "Tổng tiền" };
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor(new com.itextpdf.text.BaseColor(33, 58, 89));
                    pdfTable.addCell(cell);
                }

                // Dữ liệu bảng
                for (DonHangDTO obj : list) {
                    try {
                        String madonhang = obj.getMaDH() + "";
                        String tenkhuyenmai = obj.getMaKM() != 0
                                ? KhuyenMaiBLL.getDiscountById(obj.getMaKM()).getTenKM()
                                : "Không";
                        String tenkhachhang = obj.getMaKH() != 0
                                ? TheThanhVienBLL.getMemberById(obj.getMaKH()).getTenTV()
                                : "Không";
                        String phuongthucthanhtoan = obj.getPtThanhToan();
                        String ngaythanhtoan = obj.getNgayTT();
                        String madiemtichluy = obj.getMaDTL() != 0 ? obj.getMaDTL() + "" : "Không";
                        String tienkhachdua = TienIch.formatVND(obj.getTienKD());
                        String tongtien = TienIch.formatVND(obj.getTongTien());

                        PdfPCell cell1 = new PdfPCell(new Paragraph(madonhang, fontNormal));
                        PdfPCell cell2 = new PdfPCell(new Paragraph(tenkhuyenmai, fontNormal));
                        PdfPCell cell3 = new PdfPCell(new Paragraph(tenkhachhang, fontNormal));
                        PdfPCell cell4 = new PdfPCell(new Paragraph(phuongthucthanhtoan, fontNormal));
                        PdfPCell cell5 = new PdfPCell(new Paragraph(ngaythanhtoan, fontNormal));
                        PdfPCell cell6 = new PdfPCell(new Paragraph(madiemtichluy, fontNormal));
                        PdfPCell cell7 = new PdfPCell(new Paragraph(tienkhachdua, fontNormal));
                        PdfPCell cell8 = new PdfPCell(new Paragraph(tongtien, fontNormal));

                        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell6.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell7.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell8.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

                        pdfTable.addCell(cell1);
                        pdfTable.addCell(cell2);
                        pdfTable.addCell(cell3);
                        pdfTable.addCell(cell4);
                        pdfTable.addCell(cell5);
                        pdfTable.addCell(cell6);
                        pdfTable.addCell(cell7);
                        pdfTable.addCell(cell8);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi truy cập dữ liệu: " + e.getMessage(), "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        document.close();
                        return;
                    }
                }

                document.add(pdfTable);

                Paragraph nguoilambaocao = new Paragraph("Người in\nKý và ghi rõ họ tên", fontBold);
                Paragraph banquanli = new Paragraph("Ban quản lý\nKý và ghi rõ họ tên", fontBold);

                PdfPTable table2 = new PdfPTable(2);
                table2.setWidthPercentage(100);
                table2.setWidths(new float[] { 1, 1 });

                PdfPCell nguoilam = new PdfPCell(nguoilambaocao);
                nguoilam.setBorder(PdfPCell.NO_BORDER);
                nguoilam.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoilam.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                PdfPCell nguoiduyet = new PdfPCell(banquanli);
                nguoiduyet.setBorder(PdfPCell.NO_BORDER);
                nguoiduyet.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoiduyet.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                table2.addCell(nguoilam);
                table2.addCell(nguoiduyet);
                document.add(table2);

                document.close();

                TienIch.CustomMessage("Xuất danh sách đơn hàng của nhân viên thành công!");

            } catch (java.io.FileNotFoundException e) {
                TienIch.CustomMessage("Không thể xuất file vì file đang được mở, vui lòng đóng file và thử lại");
            } catch (Exception e) {
                e.printStackTrace();
                TienIch.CustomMessage("Đã xảy ra lỗi khi xuất file!");
            }
        }
    }

    public static void InPDFSanPhamBanChaySearch(ArrayList<SearchBanChayDTO> list, SearchBanChayDTO search, Date from,
            Date to, String MANV) {
        JFileChooser chooser = new JFileChooser("src/main/resources/file/export/");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Chọn thư mục để lưu danh sách sản phẩm bán chạy");

        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            String filePath = selectedFolder.getAbsolutePath() + File.separator + "DanhSachSPBanChay_"
                    + System.currentTimeMillis() + ".pdf";
            try {
                // Tạo document mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Font hỗ trợ tiếng Việt
                String fontPath = "src/main/resources/fonts/arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontNormal = new Font(baseFont, 12);
                Font fontBold = new Font(baseFont, 12, Font.BOLD);
                Font fontTitle = new Font(baseFont, 20, Font.BOLD);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD, com.itextpdf.text.BaseColor.WHITE);

                // Ngày tháng năm và tên người in
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date(System.currentTimeMillis());
                String currentDate = dateFormat.format(today);
                String tenNhanVien = new NhanVienBLL().getNameNV(MANV); // Thay bằng tên nhân viên thực tế nếu có
                Paragraph dateAndUser = new Paragraph("Ngày in: " + currentDate + " | Người in: " + tenNhanVien,
                        fontNormal);
                dateAndUser.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                document.add(dateAndUser);

                // Logo
                String imagePath = "src/main/resources/images/icon/Logo_Main.png";
                Image logo = Image.getInstance(imagePath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);

                // Tiêu đề
                Paragraph title = new Paragraph("DANH SÁCH SẢN PHẨM BÁN CHẠY\n\n", fontTitle);
                title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                document.add(title);

                // Từ ngày
                Paragraph ngay = new Paragraph("Từ ngày: " + from + " đến: " + to + "\n", fontBold);
                ngay.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                document.add(ngay);

                // Tiêu chí lọc
                if (search.getMaSP() != 0) {
                    Paragraph maSP = new Paragraph("Mã sản phẩm: " + search.getMaSP(), fontNormal);
                    maSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maSP);
                }
                if (search.getTenSP() != null) {
                    if (!search.getTenSP().trim().isEmpty()) {
                        Paragraph tenSP = new Paragraph("Tên sản phẩm: " + search.getTenSP(), fontNormal);
                        tenSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenSP);
                    }
                }
                if (search.getMaLSP() != 0) {
                    Paragraph maLSP = new Paragraph("Mã loại sản phẩm: " + search.getMaLSP(), fontNormal);
                    maLSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maLSP);
                }
                if (search.getTenLSP() != null) {
                    if (!search.getTenLSP().trim().isEmpty()) {
                        Paragraph tenLSP = new Paragraph("Tên loại sản phẩm: " + search.getTenLSP(), fontNormal);
                        tenLSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenLSP);
                    }
                }
                if (search.getSLmin() !=0 && search.getSLmax()!=0){
                    Paragraph soLuong = new Paragraph("Số lượng bán ra từ: " + search.getSLmin() + " đến: "+search.getSLmax(), fontNormal);
                        soLuong.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(soLuong);
                }
                if (search.getSort() != null && search.getBy() != null) {
                    Paragraph orderby = new Paragraph(
                            "Sắp xếp: " + search.getSort() + " | Theo cột: " + search.getBy(),
                            fontNormal);
                    orderby.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(orderby);
                }

                // Tạo bảng dữ liệu
                int columnCount = 5; // Dựa trên dữ liệu
                PdfPTable pdfTable = new PdfPTable(columnCount);
                pdfTable.setWidthPercentage(100);
                pdfTable.setWidths(new float[] { 0.5f, 2f, 2f, 1.5f, 2f});
                pdfTable.setSpacingBefore(10f);

                // Header bảng
                String[] headers = { "Mã SP", "Loại", "Sản Phẩm", "Số lượng bán ra", "Mã các đơn hàng"};
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor(new com.itextpdf.text.BaseColor(33, 58, 89));
                    pdfTable.addCell(cell);
                }

                // Dữ liệu bảng
                for (SearchBanChayDTO obj : list) {
                    try {
                        String masanpham = obj.getMaSP() + "";
                        String tenloai = obj.getTenLSP();
                        String tensanpham = obj.getTenSP();
                        String soluongbanra = obj.getSLbanra() + "";
                        String macacdonhang = obj.getChuoiMaDH();

                        PdfPCell cell1 = new PdfPCell(new Paragraph(masanpham, fontNormal));
                        PdfPCell cell2 = new PdfPCell(new Paragraph(tenloai, fontNormal));
                        PdfPCell cell3 = new PdfPCell(new Paragraph(tensanpham, fontNormal));
                        PdfPCell cell4 = new PdfPCell(new Paragraph(soluongbanra, fontNormal));
                        PdfPCell cell5 = new PdfPCell(new Paragraph(macacdonhang, fontNormal));

                        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

                        pdfTable.addCell(cell1);
                        pdfTable.addCell(cell2);
                        pdfTable.addCell(cell3);
                        pdfTable.addCell(cell4);
                        pdfTable.addCell(cell5);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi truy cập dữ liệu: " + e.getMessage(), "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        document.close();
                        return;
                    }
                }

                document.add(pdfTable);

                Paragraph nguoilambaocao = new Paragraph("Người in\nKý và ghi rõ họ tên", fontBold);
                Paragraph banquanli = new Paragraph("Ban quản lý\nKý và ghi rõ họ tên", fontBold);

                PdfPTable table2 = new PdfPTable(2);
                table2.setWidthPercentage(100);
                table2.setWidths(new float[] { 1, 1 });

                PdfPCell nguoilam = new PdfPCell(nguoilambaocao);
                nguoilam.setBorder(PdfPCell.NO_BORDER);
                nguoilam.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoilam.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                PdfPCell nguoiduyet = new PdfPCell(banquanli);
                nguoiduyet.setBorder(PdfPCell.NO_BORDER);
                nguoiduyet.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoiduyet.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                table2.addCell(nguoilam);
                table2.addCell(nguoiduyet);
                document.add(table2);

                document.close();

                TienIch.CustomMessage("Xuất danh sách sản phẩm bán chạy thành công!");

            } catch (java.io.FileNotFoundException e) {
                TienIch.CustomMessage("Không thể xuất file vì file đang được mở, vui lòng đóng file và thử lại");
            } catch (Exception e) {
                e.printStackTrace();
                TienIch.CustomMessage("Đã xảy ra lỗi khi xuất file!");
            }
        }
    }

    public static void InPDFSanPhamTonKhoSearch(ArrayList<SanPhamDTO> list, SearchTonKhoDTO search, String MANV) {
        JFileChooser chooser = new JFileChooser("src/main/resources/file/export/");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Chọn thư mục để lưu danh sách sản phẩm tồn kho");

        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            String filePath = selectedFolder.getAbsolutePath() + File.separator + "DanhSachSPTonKho_"
                    + System.currentTimeMillis() + ".pdf";
            try {
                // Tạo document mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Font hỗ trợ tiếng Việt
                String fontPath = "src/main/resources/fonts/arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontNormal = new Font(baseFont, 12);
                Font fontBold = new Font(baseFont, 12, Font.BOLD);
                Font fontTitle = new Font(baseFont, 20, Font.BOLD);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD, com.itextpdf.text.BaseColor.WHITE);

                // Ngày tháng năm và tên người in
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date(System.currentTimeMillis());
                String currentDate = dateFormat.format(today);
                String tenNhanVien = new NhanVienBLL().getNameNV(MANV); // Thay bằng tên nhân viên thực tế nếu có
                Paragraph dateAndUser = new Paragraph("Ngày in: " + currentDate + " | Người in: " + tenNhanVien,
                        fontNormal);
                dateAndUser.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                document.add(dateAndUser);

                // Logo
                String imagePath = "src/main/resources/images/icon/Logo_Main.png";
                Image logo = Image.getInstance(imagePath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);

                // Tiêu đề
                Paragraph title = new Paragraph("DANH SÁCH SẢN PHẨM TỒN KHO\n\n", fontTitle);
                title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                document.add(title);

                // Tiêu chí lọc
                if (search.getMaSP() != 0) {
                    Paragraph maSP = new Paragraph("Mã sản phẩm: " + search.getMaSP(), fontNormal);
                    maSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maSP);
                }
                if (search.getTenSP() != null) {
                    if (!search.getTenSP().trim().isEmpty()) {
                        Paragraph tenSP = new Paragraph("Tên sản phẩm: " + search.getTenSP(), fontNormal);
                        tenSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenSP);
                    }
                }
                if (search.getMaLSP() != 0) {
                    Paragraph maLSP = new Paragraph("Mã loại sản phẩm: " + search.getMaLSP(), fontNormal);
                    maLSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maLSP);
                }
                if (search.getTenLSP() != null) {
                    if (!search.getTenLSP().trim().isEmpty()) {
                        Paragraph tenLSP = new Paragraph("Tên loại sản phẩm: " + search.getTenLSP(), fontNormal);
                        tenLSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenLSP);
                    }
                }
                if (search.getSLmin() !=0 && search.getSLmax()!=0){
                    Paragraph soLuong = new Paragraph("Số lượng tồn từ: " + search.getSLmin() + " đến: "+search.getSLmax(), fontNormal);
                        soLuong.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(soLuong);
                }
                if (search.getGiamin() !=0 && search.getGiamax()!=0){
                    Paragraph giaTien = new Paragraph("Đơn giá từ: " + search.getGiamin() + " đến: "+search.getGiamax(), fontNormal);
                        giaTien.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(giaTien);
                }
                if (search.getMaNCC() != 0) {
                    Paragraph maNCC = new Paragraph("Mã nhà cung cấp: " + search.getMaNCC(), fontNormal);
                    maNCC.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maNCC);
                }
                if (search.getTenNCC() != null) {
                    if (!search.getTenNCC().trim().isEmpty()) {
                        Paragraph tenNCC = new Paragraph("Tên nhà cung cấp: " + search.getTenNCC(), fontNormal);
                        tenNCC.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenNCC);
                    }
                }
                if (search.getSort() != null && search.getBy() != null) {
                    Paragraph orderby = new Paragraph(
                            "Sắp xếp: " + search.getSort() + " | Theo cột: " + search.getBy(),
                            fontNormal);
                    orderby.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(orderby);
                }

                // Tạo bảng dữ liệu
                int columnCount = 6; // Dựa trên dữ liệu
                PdfPTable pdfTable = new PdfPTable(columnCount);
                pdfTable.setWidthPercentage(100);
                pdfTable.setWidths(new float[] { 0.5f, 2f, 2f, 1.5f, 2f, 1.8f});
                pdfTable.setSpacingBefore(10f);

                // Header bảng
                String[] headers = { "Mã SP", "Loại", "Sản Phẩm", "Số lượng tồn", "Nhà cung cấp", "Đơn giá"};
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor(new com.itextpdf.text.BaseColor(33, 58, 89));
                    pdfTable.addCell(cell);
                }

                // Dữ liệu bảng
                for (SanPhamDTO obj : list) {
                    try {
                        String masanpham = obj.getMaSP() + "";
                        String tenloai = new LoaiSanPhamBLL().getLoaiSanPham(obj.getMaLSP()).getTenLoaiSP();
                        String tensanpham = obj.getTenSP();
                        String soluongton = obj.getSoLuongTon() + "";
                        String tennhacungcap = new NhaCungCapBLL().getNhaCungCap(obj.getMaNCC()).getTenNCC();
                        String dongia = TienIch.formatVND(obj.getGia());

                        PdfPCell cell1 = new PdfPCell(new Paragraph(masanpham, fontNormal));
                        PdfPCell cell2 = new PdfPCell(new Paragraph(tenloai, fontNormal));
                        PdfPCell cell3 = new PdfPCell(new Paragraph(tensanpham, fontNormal));
                        PdfPCell cell4 = new PdfPCell(new Paragraph(soluongton, fontNormal));
                        PdfPCell cell5 = new PdfPCell(new Paragraph(tennhacungcap, fontNormal));
                        PdfPCell cell6 = new PdfPCell(new Paragraph(dongia, fontNormal));

                        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell6.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

                        pdfTable.addCell(cell1);
                        pdfTable.addCell(cell2);
                        pdfTable.addCell(cell3);
                        pdfTable.addCell(cell4);
                        pdfTable.addCell(cell5);
                        pdfTable.addCell(cell6);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi truy cập dữ liệu: " + e.getMessage(), "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        document.close();
                        return;
                    }
                }

                document.add(pdfTable);

                Paragraph nguoilambaocao = new Paragraph("Người in\nKý và ghi rõ họ tên", fontBold);
                Paragraph banquanli = new Paragraph("Ban quản lý\nKý và ghi rõ họ tên", fontBold);

                PdfPTable table2 = new PdfPTable(2);
                table2.setWidthPercentage(100);
                table2.setWidths(new float[] { 1, 1 });

                PdfPCell nguoilam = new PdfPCell(nguoilambaocao);
                nguoilam.setBorder(PdfPCell.NO_BORDER);
                nguoilam.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoilam.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                PdfPCell nguoiduyet = new PdfPCell(banquanli);
                nguoiduyet.setBorder(PdfPCell.NO_BORDER);
                nguoiduyet.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoiduyet.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                table2.addCell(nguoilam);
                table2.addCell(nguoiduyet);
                document.add(table2);

                document.close();

                TienIch.CustomMessage("Xuất danh sách sản phẩm tồn kho thành công!");

            } catch (java.io.FileNotFoundException e) {
                TienIch.CustomMessage("Không thể xuất file vì file đang được mở, vui lòng đóng file và thử lại");
            } catch (Exception e) {
                e.printStackTrace();
                TienIch.CustomMessage("Đã xảy ra lỗi khi xuất file!");
            }
        }
    }

    public static void InPDFLoHangSearch(ArrayList<PhieuNhapHangDTO> list, SearchLoHangDTO search, String MANV) {
        JFileChooser chooser = new JFileChooser("src/main/resources/file/export/");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Chọn thư mục để lưu danh sách lô hàng");

        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = chooser.getSelectedFile();
            String filePath = selectedFolder.getAbsolutePath() + File.separator + "DanhSachLoHang_"
                    + System.currentTimeMillis() + ".pdf";
            try {
                // Tạo document mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Font hỗ trợ tiếng Việt
                String fontPath = "src/main/resources/fonts/arial.ttf";
                BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font fontNormal = new Font(baseFont, 12);
                Font fontBold = new Font(baseFont, 12, Font.BOLD);
                Font fontTitle = new Font(baseFont, 20, Font.BOLD);
                Font fontHeader = new Font(baseFont, 12, Font.BOLD, com.itextpdf.text.BaseColor.WHITE);

                // Ngày tháng năm và tên người in
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date(System.currentTimeMillis());
                String currentDate = dateFormat.format(today);
                String tenNhanVien = new NhanVienBLL().getNameNV(MANV); // Thay bằng tên nhân viên thực tế nếu có
                Paragraph dateAndUser = new Paragraph("Ngày in: " + currentDate + " | Người in: " + tenNhanVien,
                        fontNormal);
                dateAndUser.setAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                document.add(dateAndUser);

                // Logo
                String imagePath = "src/main/resources/images/icon/Logo_Main.png";
                Image logo = Image.getInstance(imagePath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);

                // Tiêu đề
                Paragraph title = new Paragraph("DANH SÁCH LÔ HÀNG ĐÃ NHẬP\n\n", fontTitle);
                title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                document.add(title);

                // Tiêu chí lọc
                if (search.getMaDonNH() !=0){
                    Paragraph maDonNH = new Paragraph("Mã đơn nhập hàng: " + search.getMaDonNH(), fontNormal);
                    maDonNH.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maDonNH);
                }
                if (search.getTenDonNH() != null) {
                    if (!search.getTenDonNH().trim().isEmpty()) {
                        Paragraph tenDonNH = new Paragraph("Tên đơn nhập hàng: " + search.getTenDonNH(), fontNormal);
                        tenDonNH.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenDonNH);
                    }
                }
                if (search.getNgayTu()!=null && search.getNgayDen()!=null){
                    Paragraph ngay = new Paragraph(
                            "Ngày nhập từ: " + search.getNgayTu() + " đến " + search.getNgayDen(),
                            fontNormal);
                    ngay.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(ngay);
                }
                if (search.getMaNV() != 0) {
                    Paragraph maNV = new Paragraph("Mã nhân viên: " + search.getMaNV(), fontNormal);
                    maNV.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maNV);
                }
                if (search.getTenNV() != null) {
                    if (!search.getTenNV().trim().isEmpty()) {
                        Paragraph tenNV = new Paragraph("Tên nhân viên: " + search.getTenNV(), fontNormal);
                        tenNV.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenNV);
                    }
                }
                if (search.getMaNCC() != 0) {
                    Paragraph maNCC = new Paragraph("Mã nhà cung cấp: " + search.getMaNCC(), fontNormal);
                    maNCC.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maNCC);
                }
                if (search.getTenNCC() != null) {
                    if (!search.getTenNCC().trim().isEmpty()) {
                        Paragraph tenNCC = new Paragraph("Tên nhà cung cấp: " + search.getTenNCC(), fontNormal);
                        tenNCC.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenNCC);
                    }
                }
                if (search.getGiaMin() !=0 && search.getGiaMax()!=0){
                    Paragraph giaNhap = new Paragraph("Giá nhập từ: " + search.getGiaMin() + " đến: "+search.getGiaMax(), fontNormal);
                        giaNhap.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(giaNhap);
                }
                if (search.getMaSP() != 0) {
                    Paragraph maSP = new Paragraph("Mã sản phẩm: " + search.getMaSP(), fontNormal);
                    maSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maSP);
                }
                if (search.getTenSP() != null) {
                    if (!search.getTenSP().trim().isEmpty()) {
                        Paragraph tenSP = new Paragraph("Tên sản phẩm: " + search.getTenSP(), fontNormal);
                        tenSP.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                        document.add(tenSP);
                    }
                }
                if (search.getMaLH() != 0) {
                    Paragraph maLH = new Paragraph("Mã lô hàng: " + search.getMaLH(), fontNormal);
                    maLH.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(maLH);
                }
                if (search.getOrder() != null && search.getBy() != null) {
                    Paragraph orderby = new Paragraph(
                            "Sắp xếp: " + search.getOrder() + " | Theo cột: " + search.getBy(),
                            fontNormal);
                    orderby.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
                    document.add(orderby);
                }

                // Tạo bảng dữ liệu
                int columnCount = 5; // Dựa trên dữ liệu
                PdfPTable pdfTable = new PdfPTable(columnCount);
                pdfTable.setWidthPercentage(100);
                pdfTable.setWidths(new float[] { 0.5f, 2f, 2f, 2f, 2f});
                pdfTable.setSpacingBefore(10f);

                // Header bảng
                String[] headers = { "Mã đơn", "Tên", "Ngày nhập", "Nhân viên", "Nhà cung cấp"};
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontHeader));
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                    cell.setBackgroundColor(new com.itextpdf.text.BaseColor(33, 58, 89));
                    pdfTable.addCell(cell);
                }

                // Dữ liệu bảng
                for (PhieuNhapHangDTO obj : list) {
                    try {
                        String maphieunhaphang = obj.getMaPNH() + "";
                        String tendonnhaphang = obj.getTenPNH();
                        String ngaynhap = obj.getNgayNhap() + "";
                        String nhanvien = new NhanVienBLL().getNhanVienByMa(obj.getMaNV()+"").getTenNV();
                        String tennhacungcap = new NhaCungCapBLL().getNhaCungCap(obj.getMaNCC()).getTenNCC();

                        PdfPCell cell1 = new PdfPCell(new Paragraph(maphieunhaphang, fontNormal));
                        PdfPCell cell2 = new PdfPCell(new Paragraph(tendonnhaphang, fontNormal));
                        PdfPCell cell3 = new PdfPCell(new Paragraph(ngaynhap, fontNormal));
                        PdfPCell cell4 = new PdfPCell(new Paragraph(nhanvien, fontNormal));
                        PdfPCell cell5 = new PdfPCell(new Paragraph(tennhacungcap, fontNormal));

                        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell3.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell4.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell5.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);

                        pdfTable.addCell(cell1);
                        pdfTable.addCell(cell2);
                        pdfTable.addCell(cell3);
                        pdfTable.addCell(cell4);
                        pdfTable.addCell(cell5);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi khi truy cập dữ liệu: " + e.getMessage(), "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        document.close();
                        return;
                    }
                }

                document.add(pdfTable);

                Paragraph nguoilambaocao = new Paragraph("Người in\nKý và ghi rõ họ tên", fontBold);
                Paragraph banquanli = new Paragraph("Ban quản lý\nKý và ghi rõ họ tên", fontBold);

                PdfPTable table2 = new PdfPTable(2);
                table2.setWidthPercentage(100);
                table2.setWidths(new float[] { 1, 1 });

                PdfPCell nguoilam = new PdfPCell(nguoilambaocao);
                nguoilam.setBorder(PdfPCell.NO_BORDER);
                nguoilam.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoilam.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                PdfPCell nguoiduyet = new PdfPCell(banquanli);
                nguoiduyet.setBorder(PdfPCell.NO_BORDER);
                nguoiduyet.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nguoiduyet.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_TOP);

                table2.addCell(nguoilam);
                table2.addCell(nguoiduyet);
                document.add(table2);

                document.close();

                TienIch.CustomMessage("Xuất danh sách phiếu nhập hàng thành công!");

            } catch (java.io.FileNotFoundException e) {
                TienIch.CustomMessage("Không thể xuất file vì file đang được mở, vui lòng đóng file và thử lại");
            } catch (Exception e) {
                e.printStackTrace();
                TienIch.CustomMessage("Đã xảy ra lỗi khi xuất file!");
            }
        }
    }
}
