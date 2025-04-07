package com.FormEmployee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;


public class InterfaceOrderManagement extends JPanel implements ActionListener{

    private  static JPanel pn_toolBar ;
    private  static JButton btn_show ;
     private  static JButton btn_edit ;
     private  static JButton btn_delete;
     private  static JButton btn_excel ;
     private  static JButton btn_pdf ;

     private  static JPanel pn_listOrder ;
     private  static DefaultTableModel dftmd_listOrder ;
        private  static JTable tb_listOrder ;

        private  static JScrollPane scp_listOrder;
        private  static JPanel pn_findOrder ;

        
   
 
        JLabel lbl_orderId ;
       
        JLabel lbl_startDate ;

     
        JLabel lbl_endDate ;
        JLabel lbl_minTotal ;
        JLabel lbl_maxTotal ;
        JLabel lbl_status ;
        JLabel lbl_memberId ;
        JLabel lbl_memberName ;
        JLabel lbl_phone ;
        JButton btn_findOrder ;
        JLabel lbl_sort ;
        JLabel lbl_column ;
        JTextField txt_idOrder ;
        JTextField txt_startDate ;
        JTextField txt_endDate ;
        JTextField txt_minTotal ;
        JTextField txt_maxTotal ;
        JTextField txt_status ;
        JTextField txt_idMember;
        JTextField txt_memberName ;
        JTextField txt_memberPhone;
        JTextField txt_sort ;
        JTextField txt_column;
        JButton btn_sort ;

        JDateChooser dateChooserStart = new JDateChooser();
        JDateChooser dateChooserEnd = new JDateChooser();
        String[] items = {"Tăng dần", "Giảm dần"};
        JComboBox<String> comboBoxSort = new JComboBox<>(items);
        JSpinner spinnerTotalMin = new JSpinner(new SpinnerNumberModel(100000, 0, 1000000, 1000));
        JSpinner spinnerTotalMax = new JSpinner(new SpinnerNumberModel(1000000, 1, 10000000, 1000));



    public InterfaceOrderManagement(){
        setBackground(Color.pink);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 15, 15, 15);

//  panel lamf thanh cong cuj---============================
        pn_toolBar = new JPanel(new FlowLayout());
        btn_show = new JButton("xem");
        btn_edit = new JButton("sửa");
        btn_delete = new JButton("xoá");
        btn_excel = new JButton("Excel");
        btn_pdf = new JButton("PDF");
        
        pn_toolBar.add(btn_show);
        pn_toolBar.add(btn_edit);
        pn_toolBar.add(btn_delete);
        pn_toolBar.add(btn_excel);
        pn_toolBar.add(btn_pdf);

        btn_show.addActionListener(this);
        btn_edit.addActionListener(this);
        btn_delete.addActionListener(this);
        btn_excel.addActionListener(this);
        btn_pdf.addActionListener(this);


// ======= panel table hiển thị list đơn hàng==============
        pn_listOrder = new JPanel(new BorderLayout(15, 15));
        String[] header = {"Mã", "Ngày mua","tổng tiền", "trạng thái", "giảm giá(%)"};
        Object[][] data = {
            {1, "2025-03-18", 100000, 3, "đã thanh toán", 5},
            {2, "2025-03-18", 100000, 3, "đã thanh toán", 5},
            {3, "2025-03-18", 100000, 3, "đã thanh toán", 5},
            {4, "2025-03-18", 100000, 3, "đã thanh toán", 5},
            {5, "2025-03-18", 100000, 3, "đã thanh toán", 5},
        };
        dftmd_listOrder = new DefaultTableModel(data, header);
        tb_listOrder = new JTable(dftmd_listOrder);
        // Đưa bảng vào JScrollPane để có thanh cuộn
        scp_listOrder = new JScrollPane(tb_listOrder);
        // Thêm JScrollPane vào panel
        pn_listOrder.add(scp_listOrder, BorderLayout.CENTER);
        
// ==============panel tìm kiếm =============================================
        pn_findOrder = new JPanel(new GridLayout(0, 6, 15, 10));

    
        lbl_orderId = new JLabel("mã đơn hàng");
        txt_idOrder = new JTextField();
        pn_findOrder.add(styledItemInput(lbl_orderId, txt_idOrder));

   
        lbl_startDate = new JLabel("ngày bắt đầu");
        txt_startDate = new JTextField();
        pn_findOrder.add(styledDateInput(lbl_startDate, dateChooserStart));
        
        
        lbl_endDate = new JLabel("ngày kết thúc");
        txt_endDate = new JTextField();
        pn_findOrder.add(styledDateInput(lbl_endDate, dateChooserEnd));


        lbl_minTotal = new JLabel("tổng tiền min");
        txt_minTotal = new JTextField();
        pn_findOrder.add(styledSpinnerInput(lbl_minTotal, spinnerTotalMin));
        
    
        lbl_maxTotal = new JLabel("tổng tiền max");
        txt_maxTotal = new JTextField();
        pn_findOrder.add(styledSpinnerInput(lbl_maxTotal, spinnerTotalMax));
        
        
        
        lbl_status = new JLabel("trạng thái");
        txt_status = new JTextField();
        pn_findOrder.add(styledItemInput(lbl_status, txt_status));
        
        
        lbl_memberId = new JLabel("mã thành viên");
        txt_idMember = new JTextField();
        pn_findOrder.add(styledItemInput(lbl_memberId, txt_idMember));
        
        
        lbl_memberName = new JLabel("tên thành viên");
        txt_memberName = new JTextField();
        pn_findOrder.add(styledItemInput(lbl_memberName, txt_memberName));

        
        lbl_phone = new JLabel("số điện thoại");
        txt_memberPhone = new JTextField();
        pn_findOrder.add(styledItemInput(lbl_phone, txt_memberPhone));

        
        
        
        lbl_sort = new JLabel("sắp xếp");
        txt_sort = new JTextField();
        pn_findOrder.add(styledComboboxInput(lbl_sort, comboBoxSort));
        
        
        lbl_column = new JLabel("theo cột");
        txt_column = new JTextField();
        pn_findOrder.add(styledItemInput(lbl_column, txt_column));
        
        
        btn_findOrder = new JButton("Tìm kiếm");
        pn_findOrder.add(btn_findOrder);



     
  

 
 
        
    
        


   
        
       
        
// ===========================================================================

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Mở rộng ngang
        gbc.weightx = 1; // Trọng số ngang để nó mở rộng theo chiều ngang
        pn_findOrder.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Padding ngoài
        add(pn_findOrder, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH; // Cho phép mở rộng cả chiều ngang và dọc
        gbc.weighty = 1; // Trọng số dọc để bảng mở rộng đúng
        pn_listOrder.add(pn_toolBar,BorderLayout.NORTH);
        pn_listOrder.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(pn_listOrder, gbc);

        customizeTable();
    }

    // Panel cho JTextField
    public static JPanel styledItemInput(JLabel lbl, JTextField txt) {
        // Sử dụng GridLayout với 2 hàng, 1 cột, khoảng cách giữa các phần tử là 10px
        JPanel pn = new JPanel(new GridLayout(2, 1, 0, 0)); // Tăng khoảng cách giữa các phần tử
        // pn.setBackground(Color.GREEN);
        // Label styling
        lbl.setFont(new Font("Roboto", Font.BOLD, 14));

        // TextField styling
        txt.setFont(new Font("Roboto", Font.PLAIN, 14));
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 1, true), // Viền xanh
                BorderFactory.createEmptyBorder(1, 10, 1, 10) // Padding trong
        ));
        txt.setPreferredSize(new Dimension(200, 25)); // Điều chỉnh chiều cao của JTextField xuống 25px

        // Thêm vào panel
        pn.add(lbl);
        pn.add(txt);

        return pn;
    }

    // Panel cho JComboBox
    public static JPanel styledComboboxInput(JLabel lbl, JComboBox<String> comboBox) {
        // Sử dụng GridLayout thay vì BoxLayout để có thể kiểm soát kích thước các phần tử dễ dàng hơn
        JPanel pn = new JPanel(new GridLayout(2, 1, 0, 0)); // Tăng khoảng cách giữa các phần tử

        // Label styling
        lbl.setFont(new Font("Roboto", Font.BOLD, 14));

        // ComboBox styling
        comboBox.setFont(new Font("Roboto", Font.PLAIN, 14));
        comboBox.setPreferredSize(new Dimension(200, 25)); // Điều chỉnh chiều cao ComboBox xuống 25px
        comboBox.setBackground(Color.WHITE); // Màu nền của ComboBox
        comboBox.setForeground(Color.BLACK); // Màu chữ của ComboBox
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 1, true), // Viền xanh
                BorderFactory.createEmptyBorder(0, 0, 0, 0) // Padding trong
        ));

        // Thêm vào panel
        pn.add(lbl);
        pn.add(comboBox);

        return pn;
    }

    // Panel cho DateChooser
    public static JPanel styledDateInput(JLabel lbl, JDateChooser dateChooser) {
        JPanel pn = new JPanel(new GridLayout(2, 1, 0, 0)); // Sử dụng GridLayout để căn chỉnh các phần tử đều

        // Label styling
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        lbl.setHorizontalAlignment(JLabel.LEFT);

        // Date chooser styling
        dateChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        dateChooser.setDateFormatString("yyyy-MM-dd");

        // Lấy JTextField từ JDateChooser để style
        JTextField textField = (JTextField) dateChooser.getDateEditor().getUiComponent();

        // Styling cho JTextField
        textField.setFont(new Font("Arial", Font.PLAIN, 14)); // Font chữ cho textfield
        textField.setPreferredSize(new Dimension(200, 25)); // Điều chỉnh chiều cao cho textfield xuống 25px
        textField.setBackground(Color.WHITE); // Màu nền
        textField.setForeground(Color.BLACK); // Màu chữ
        textField.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2)); // Viền màu xanh
        textField.setHorizontalAlignment(JTextField.LEFT); // Căn giữa nội dung textfield

        // DateChooser kích thước
        dateChooser.setPreferredSize(new Dimension(200, 25));
        dateChooser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25)); // Giữ chiều cao cố định là 25px

        // Add to panel
        pn.add(lbl);
        pn.add(dateChooser);

        return pn;
    }

    public static JPanel styledSpinnerInput(JLabel lbl, JSpinner spinner) {
    // Tạo panel với layout 2 dòng, 1 cột
        JPanel pn = new JPanel(new GridLayout(2, 1, 0, 0));

        // Label styling
        lbl.setFont(new Font("Roboto", Font.BOLD, 14));

        // Spinner styling
        spinner.setFont(new Font("Roboto", Font.PLAIN, 14));
        spinner.setPreferredSize(new Dimension(200, 25));
        spinner.setBackground(Color.WHITE);
        spinner.setForeground(Color.BLACK);

        // Lấy editor của spinner để style phần nhập số
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField spinnerTextField = ((JSpinner.DefaultEditor) editor).getTextField();
            spinnerTextField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(100, 149, 237), 1, true),
                    BorderFactory.createEmptyBorder(0, 0 , 0,0)
            ));
            spinnerTextField.setBackground(Color.WHITE);
            spinnerTextField.setForeground(Color.BLACK);
            spinnerTextField.setFont(new Font("Roboto", Font.PLAIN, 14));
            spinnerTextField.setPreferredSize(new Dimension(200, 25));
        }

        // Thêm label và spinner vào panel
        pn.add(lbl);
        pn.add(spinner);

        return pn;  
    }

     private static void customizeTable() {
        tb_listOrder.setFont(new Font("Arial", Font.PLAIN, 14));
        tb_listOrder.setRowHeight(30);
        tb_listOrder.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tb_listOrder.getTableHeader().setBackground(new Color(30, 144, 255));
        tb_listOrder.getTableHeader().setForeground(Color.WHITE);
        tb_listOrder.setSelectionBackground(new Color(200, 230, 255));
        tb_listOrder.setSelectionForeground(Color.BLACK);
        tb_listOrder.setGridColor(new Color(220, 220, 220));
        tb_listOrder.setShowVerticalLines(false);
        tb_listOrder.setShowHorizontalLines(false);
    
        DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                if (!isSelected) {
                    cell.setBackground(row % 2 == 0 ? Color.WHITE : new Color(235, 235, 235)); 
                }
                return cell;
            }
        };
        for (int i = 0; i < tb_listOrder.getColumnCount(); i++) {
            tb_listOrder.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        }
    }




     public static void main(String[] args) {
        JFrame frame = new JFrame("Đơn hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500); // Mở rộng chiều ngang để thấy rõ tỷ lệ
        frame.setLocationRelativeTo(null);
        JPanel panel = new  InterfaceOrderManagement();
        // panel.setBackground(Color.CYAN);
        frame.add(panel);
        frame.setVisible(true);
    }

        @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int selectedRow = tb_listOrder.getSelectedRow();

            // Kiểm tra nếu không có hàng nào được chọn
            if (selectedRow == -1) {
                System.out.println("Vui lòng chọn một hàng trước khi thao tác!");
                return;
            }

            if (e.getSource() == btn_delete) {
                dftmd_listOrder.removeRow(selectedRow);
                System.out.println("Đã xoá hàng: " + selectedRow);
            }

            if (e.getSource() == btn_edit) {
                editOrder(selectedRow);
            }

            if (e.getSource() == btn_show) {
                showOrderDetails(selectedRow);
            }

            if (e.getSource() == btn_excel) {
                exportToExcel();
            }

            if (e.getSource() == btn_pdf) {
                exportToPDF();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void editOrder(int rowIndex) {
        String newDate = JOptionPane.showInputDialog("Nhập ngày mới:", dftmd_listOrder.getValueAt(rowIndex, 1));
        String newTotal = JOptionPane.showInputDialog("Nhập tổng tiền mới:", dftmd_listOrder.getValueAt(rowIndex, 2));

        if (newDate != null && newTotal != null) {
            dftmd_listOrder.setValueAt(newDate, rowIndex, 1);
            dftmd_listOrder.setValueAt(newTotal, rowIndex, 2);
            System.out.println("Cập nhật đơn hàng thành công!");
        }
    }

    private void showOrderDetails(int rowIndex) {
        String details = "Mã: " + dftmd_listOrder.getValueAt(rowIndex, 0) + "\n"
                + "Ngày mua: " + dftmd_listOrder.getValueAt(rowIndex, 1) + "\n"
                + "Tổng tiền: " + dftmd_listOrder.getValueAt(rowIndex, 2) + "\n"
                + "Trạng thái: " + dftmd_listOrder.getValueAt(rowIndex, 3) + "\n"
                + "Giảm giá: " + dftmd_listOrder.getValueAt(rowIndex, 4) + "%";
        
        JOptionPane.showMessageDialog(this, details, "Chi tiết đơn hàng", JOptionPane.INFORMATION_MESSAGE);
    }

    private void exportToExcel() {
    try {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Danh sách đơn hàng");

        // Ghi tiêu đề
        XSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < dftmd_listOrder.getColumnCount(); i++) {
            headerRow.createCell(i).setCellValue(dftmd_listOrder.getColumnName(i));
        }

        // Ghi dữ liệu
        for (int row = 0; row < dftmd_listOrder.getRowCount(); row++) {
            XSSFRow excelRow = sheet.createRow(row + 1);
            for (int col = 0; col < dftmd_listOrder.getColumnCount(); col++) {
                excelRow.createCell(col).setCellValue(dftmd_listOrder.getValueAt(row, col).toString());
            }
        }

        // Xuất file
        FileOutputStream fileOut = new FileOutputStream("orders.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

        System.out.println("Xuất Excel thành công!");
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }

    private void exportToPDF() {
    // try {
    //     Document document = new Document();
    //     PdfWriter.getInstance(document, new FileOutputStream("orders.pdf"));
    //     document.open();
    //     PdfPTable table = new PdfPTable(dftmd_listOrder.getColumnCount());

    //     // Thêm tiêu đề
    //     for (int i = 0; i < dftmd_listOrder.getColumnCount(); i++) {
    //         table.addCell(dftmd_listOrder.getColumnName(i));
    //     }

    //     // Thêm dữ liệu
    //     for (int row = 0; row < dftmd_listOrder.getRowCount(); row++) {
    //         for (int col = 0; col < dftmd_listOrder.getColumnCount(); col++) {
    //             table.addCell(dftmd_listOrder.getValueAt(row, col).toString());
    //         }
    //     }

    //     document.add(table);
    //     document.close();
    //     System.out.println("Xuất PDF thành công!");
    // } catch (Exception ex) {
    //     ex.printStackTrace();
    // }
}


    

    
    
}
