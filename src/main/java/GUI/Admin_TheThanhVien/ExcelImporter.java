package GUI.Admin_TheThanhVien;

import DTO.TheThanhVienDTO;
import GUI.ComponentCommon.TienIch;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.sql.Date;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImporter {

    public static ArrayList<TheThanhVienDTO> importFromExcel(File file) throws Exception {
        ArrayList<TheThanhVienDTO> members = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
                Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            int i = 1;
            for (Row row : sheet) {
                if (row.getRowNum() == 0)
                    continue; // Bỏ qua dòng tiêu đề

                String tenTV = getCellValueAsString(row.getCell(0));
                String ngaySinhStr = getCellValueAsString(row.getCell(1));
                String diaChi = getCellValueAsString(row.getCell(2));
                String sdt = getCellValueAsString(row.getCell(3));
                String tenAnh = getCellValueAsString(row.getCell(4));

                if (tenTV.isEmpty() || ngaySinhStr.isEmpty() || diaChi.isEmpty() || sdt.isEmpty() || tenAnh.isEmpty()) {
                    TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do không có dữ liệu");
                    continue; 
                }

                // Chuyển đổi ngày sinh
                Date ngaySinh;
                try {
                    
                    if (!TienIch.isValidName(tenTV)) {
                        TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do tên chứa số hoặc ký tự đặc biệt");
                        continue;
                    }

                    java.util.Date parsedDate = dateFormat.parse(ngaySinhStr);
                    ngaySinh = new Date(parsedDate.getTime());

                    LocalDate birthDate = ngaySinh.toLocalDate();
                    LocalDate currentDate = LocalDate.now();

                    if (birthDate.isAfter(currentDate)) {
                        TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do ngày sinh trong tương lai");
                        continue;
                    }


                    Period period = Period.between(birthDate, currentDate);

                    if (period.getYears() < 18) {
                        TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do chưa đủ 18 tuổi");
                        continue;
                    }

                    if (!sdt.matches("\\d+")){
                        TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do số điện thoại chứa ký tự khác số");
                        continue;
                    }

                    if (sdt.trim().length() != 10){
                        TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do số điện thoại phải có 10 chữ số");
                        continue;
                    }
                } catch (Exception ex) {
                    TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do ngày sinh không hợp lệ");
                    continue; // Bỏ qua dòng có ngày sinh không hợp lệ
                }

                // Kiểm tra file ảnh đại diện tồn tại
                File avatarFile = new File("src/main/resources/images/avtMember/" + tenAnh);
                if (!avatarFile.exists()) {
                    TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do không tìm thấy ảnh");
                    continue; // Bỏ qua nếu file ảnh không tồn tại
                }

                // Tạo đối tượng TheThanhVienDTO
                TheThanhVienDTO member = new TheThanhVienDTO(tenTV, ngaySinh, diaChi, sdt, tenAnh);
                members.add(member);
            }
        }
        return members;
    }

    /*public static ArrayList<TheThanhVienDTO> importFromExcelNew(File file) throws Exception {
        ArrayList<TheThanhVienDTO> members = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
                Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            int i = 1;
            for (Row row : sheet) {
                if (row.getRowNum() == 0)
                    continue; // Bỏ qua dòng tiêu đề

                String tenTV = getCellValueAsString(row.getCell(0));
                String ngaySinhStr = getCellValueAsString(row.getCell(1));
                String diaChi = getCellValueAsString(row.getCell(2));
                String sdt = getCellValueAsString(row.getCell(3));
                String tenAnh = getCellValueAsString(row.getCell(4));

                // Kiểm tra dữ liệu hợp lệ
                if (tenTV.isEmpty() || ngaySinhStr.isEmpty() || diaChi.isEmpty() || sdt.isEmpty() || tenAnh.isEmpty()
                        || sdt.trim().length() != 10 || !sdt.matches("\\d+")) {
                    TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ");
                    continue; // Bỏ qua dòng không hợp lệ
                }

                java.sql.Date ngaySinh;
                try {
                    java.util.Date parsedDate = dateFormat.parse(ngaySinhStr);
                    ngaySinh = new java.sql.Date(parsedDate.getTime());

                    LocalDate birthDate = ngaySinh.toLocalDate();
                    LocalDate currentDate = LocalDate.now();

                    if (birthDate.isAfter(currentDate)) {
                        TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do ngày sinh trong tương lai");
                        continue;
                    }

                    // Kiểm tra tên hợp lệ
                    if (!TienIch.isValidName(tenTV)) {
                        TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do tên chứa số hoặc ký tự đặc biệt");
                        continue;
                    }

                    Period period = Period.between(birthDate, currentDate);

                    if (period.getYears() < 18) {
                        TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do chưa đủ 18 tuổi");
                        continue;
                    }
                } catch (Exception ex) {
                    TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do ngày sinh không hợp lệ");
                    continue; // Bỏ qua dòng có ngày sinh không hợp lệ
                }

                // Kiểm tra file ảnh đại diện tồn tại
                File avatarFile = new File("src/main/resources/images/avtMember/" + tenAnh);
                if (!avatarFile.exists()) {
                    TienIch.CustomMessage("Dòng thứ " + (i++) + " không hợp lệ do không tìm thấy ảnh");
                    continue; // Bỏ qua nếu file ảnh không tồn tại
                }

                // Tạo đối tượng TheThanhVienDTO
                TheThanhVienDTO member = new TheThanhVienDTO(tenTV, ngaySinh, diaChi, sdt, tenAnh);
                members.add(member);
            }
        }
        return members;
    }
*/
    // Lấy giá trị ô dưới dạng chuỗi, sử dụng DataFormatter để giữ định dạng gốc
    private static String getCellValueAsString(Cell cell) {
        if (cell == null)
            return "";
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }
}