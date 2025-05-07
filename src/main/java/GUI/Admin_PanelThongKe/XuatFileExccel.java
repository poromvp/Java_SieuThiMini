package GUI.Admin_PanelThongKe;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BLL.NhanVienBLL;

import org.apache.poi.ss.usermodel.*;
import java.util.List;
import java.sql.Date;

public class XuatFileExccel {
    public static void exportToExcel(ArrayList<List<Object>> data, String[] columnNames, String title, String MANV,
            ArrayList<String> search) throws IOException {
        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // Create styles
        // Title style: bold, size 16, centered
        CellStyle titleStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 16);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);

        // Bold style for date, printed by, search criteria, and headers
        CellStyle boldStyle = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        boldStyle.setFont(boldFont);

        // Bordered cell style for data rows
        CellStyle borderedCellStyle = workbook.createCellStyle();
        borderedCellStyle.setBorderTop(BorderStyle.THIN);
        borderedCellStyle.setBorderBottom(BorderStyle.THIN);
        borderedCellStyle.setBorderLeft(BorderStyle.THIN);
        borderedCellStyle.setBorderRight(BorderStyle.THIN);

        // Date cell style for data
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(workbook.createDataFormat().getFormat("dd/MM/yyyy"));

        // Bordered date cell style for date data
        CellStyle borderedDateCellStyle = workbook.createCellStyle();
        borderedDateCellStyle.setDataFormat(workbook.createDataFormat().getFormat("dd/MM/yyyy"));
        borderedDateCellStyle.setBorderTop(BorderStyle.THIN);
        borderedDateCellStyle.setBorderBottom(BorderStyle.THIN);
        borderedDateCellStyle.setBorderLeft(BorderStyle.THIN);
        borderedDateCellStyle.setBorderRight(BorderStyle.THIN);

        // Row index tracker
        int rowIndex = 0;

        // Add title row
        Row titleRow = sheet.createRow(rowIndex++);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(title);
        titleCell.setCellStyle(titleStyle);
        // Merge cells for title (across all columns)
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnNames.length - 1));

        // Add print date row
        Row dateRow = sheet.createRow(rowIndex++);
        Cell dateCell = dateRow.createCell(0);
        String printDate = "Ngày in: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateCell.setCellValue(printDate);
        dateCell.setCellStyle(boldStyle);

        // Add printed by row
        Row printedByRow = sheet.createRow(rowIndex++);
        Cell printedByCell = printedByRow.createCell(0);
        printedByCell.setCellValue("Người in: " + new NhanVienBLL().getNameNV(MANV));
        printedByCell.setCellStyle(boldStyle);

        // Add empty row for spacing
        sheet.createRow(rowIndex++);

        // Add search criteria rows
        if (search != null && !search.isEmpty()) {
            Row searchRow = sheet.createRow(rowIndex++);
            Cell searchCell = searchRow.createCell(0);
            searchCell.setCellValue("Tiêu chí tìm kiếm: ");
            searchCell.setCellStyle(boldStyle);
            for (String criterion : search) {
                searchRow = sheet.createRow(rowIndex++);
                searchCell = searchRow.createCell(0);
                searchCell.setCellValue(criterion);
                searchCell.setCellStyle(boldStyle);
            }
        }

        // Add empty row for spacing
        sheet.createRow(rowIndex++);

        // Create header row
        Row headerRow = sheet.createRow(rowIndex++);
        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(boldStyle);
        }

        // Create data rows
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(rowIndex++);
            List<Object> rowData = data.get(i);
            for (int j = 0; j < rowData.size(); j++) {
                Cell cell = row.createCell(j);
                Object value = rowData.get(j);
                if (value != null) {
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                        cell.setCellStyle(borderedCellStyle);
                    } else if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                        cell.setCellStyle(borderedCellStyle);
                    } else if (value instanceof Boolean) {
                        cell.setCellValue((Boolean) value);
                        cell.setCellStyle(borderedCellStyle);
                    } else if (value instanceof Date) {
                        cell.setCellValue((Date) value);
                        cell.setCellStyle(borderedDateCellStyle);
                    } else {
                        cell.setCellValue(value.toString());
                        cell.setCellStyle(borderedCellStyle);
                    }
                } else {
                    cell.setCellStyle(borderedCellStyle); // Apply border even for null values
                }
            }
        }

        // Auto-size columns
        for (int i = 0; i < columnNames.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Open file chooser
        JFileChooser fileChooser = new JFileChooser("src/main/resources/file/export/");
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx"));

        // Show save dialog
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            // Ensure the file has .xlsx extension
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }

        // Close workbook
        workbook.close();
    }
}