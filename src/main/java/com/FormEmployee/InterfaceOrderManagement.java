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
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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

import BLL.DonHangBLL;


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

        
   
 
        private  static    JLabel lbl_orderId ;
       
        private  static    JLabel lbl_startDate ;

     
        private  static    JLabel lbl_endDate ;
        private  static    JLabel lbl_minTotal ;
        private  static    JLabel lbl_maxTotal ;
        private  static JLabel lbl_status ;
        private  static JLabel lbl_employeeId ;
        private  static JLabel lbl_employeeName ;
        private  static JLabel lbl_phone ;
        private  static JButton btn_findOrder ;
        private  static JLabel lbl_sort ;
        private  static JLabel lbl_column ;
        private  static JTextField txt_idOrder ;
        private  static JTextField txt_startDate ;
        private  static JTextField txt_endDate ;
        private  static JTextField txt_minTotal ;
        private  static JTextField txt_maxTotal ;
        private  static JTextField txt_status ;
        private  static JTextField txt_employeeId;
        private  static JTextField txt_employeeName ;
        private  static JTextField txt_memberPhone;
        private  static JTextField txt_sort ;
        private  static JTextField txt_column;
        private  static JButton btn_sort ;

        private  static JDateChooser dateChooserStart = new JDateChooser();
        private  static JDateChooser dateChooserEnd = new JDateChooser();
        private  static String[] items = {"Tăng dần", "Giảm dần"};
        private  static JComboBox<String> comboBoxSort = new JComboBox<>(items);
        private  static String[] column = {"Mã đơn hàng", "Tên nhân viên", "Ngày mua", "Tổng tiền"};
        private  static JComboBox<String> comboBoxColumnSort = new JComboBox<>(column);
        private  static JSpinner spinnerTotalMin = new JSpinner(new SpinnerNumberModel(100000, 0, 1000000, 1000));
        private  static JSpinner spinnerTotalMax = new JSpinner(new SpinnerNumberModel(1000000, 1, 10000000, 1000));

        private  static JLabel lbl_show = new JLabel("Hiển thị");
        private  static JSpinner spinnerShow = new JSpinner(new SpinnerNumberModel(10, 1, 100000000, 1));

        private  static String[] status = {"FINISHED", "UNFINISHED", "DELETED"};
        private  static JComboBox<String> comboBoxStatus = new JComboBox<>(status);

        private static String[] header = {"Mã DH", "Mã NV", "tên NV", "Ngày mua",  "Trạng thái","Tên KH", "Tổng tiền", "Giảm giá(%)"};
        private static ArrayList<ArrayList<Object>> data = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(101, 1, 1, "2025-04-01", 2500000, "Chưa thanh toán", 0)),
            new ArrayList<>(Arrays.asList(102, 2, 2, "2025-04-02", 3200000, "Đã thanh toán", 10)),
            new ArrayList<>(Arrays.asList(103, 3, 3, "2025-04-03", 1500000, "Đã thanh toán", 5)),
            new ArrayList<>(Arrays.asList(104, 1, 4, "2025-04-04", 4200000, "Chưa thanh toán", 15)),
            new ArrayList<>(Arrays.asList(105, 4, 5, "2025-04-05", 2800000, "Đã thanh toán", 0)),
            new ArrayList<>(Arrays.asList(106, 2, 6, "2025-04-06", 3500000, "Đã thanh toán", 20)),
            new ArrayList<>(Arrays.asList(107, 3, 7, "2025-04-07", 1900000, "Chưa thanh toán", 10)),
            new ArrayList<>(Arrays.asList(108, 1, 8, "2025-04-08", 4000000, "Đã thanh toán", 5)),
            new ArrayList<>(Arrays.asList(109, 4, 9, "2025-04-09", 2200000, "Đã thanh toán", 0)),
            new ArrayList<>(Arrays.asList(110, 2, 10, "2025-04-10", 3100000, "Chưa thanh toán", 8))
        ));


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
        // String[] header = {"Mã", "Ngày mua","tổng tiền", "trạng thái", "giảm giá(%)"};
        // String[] header2 = {"Mã DH", "Mã NV", "Mã KH", "Ngày mua","tổng tiền",  "trạng thái", "giảm giá(%)"};
        // dftmd_listOrder = new DefaultTableModel(data, header);
        // tb_listOrder = new JTable(dftmd_listOrder);
        // // Đưa bảng vào JScrollPane để có thanh cuộn
        // scp_listOrder = new JScrollPane(tb_listOrder);
        // // Thêm JScrollPane vào panel
        // pn_listOrder.add(scp_listOrder, BorderLayout.CENTER);
        
        // ==============panel tìm kiếm =============================================
        pn_findOrder = new JPanel(new GridLayout(0, 7, 15, 10));
        
        
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
        pn_findOrder.add(styledComboboxInput(lbl_status, comboBoxStatus));
        
        
        btn_findOrder = new JButton("Tìm kiếm");
        pn_findOrder.add(btn_findOrder);
        
        lbl_employeeId = new JLabel("mã nhân viên");
        txt_employeeId = new JTextField();
        pn_findOrder.add(styledItemInput(lbl_employeeId, txt_employeeId));
        
        
        lbl_employeeName = new JLabel("tên nhân viên");
        txt_employeeName = new JTextField();
        pn_findOrder.add(styledItemInput(lbl_employeeName, txt_employeeName));
        
        
        lbl_phone = new JLabel("số DT thành viên");
        txt_memberPhone = new JTextField();
        pn_findOrder.add(styledItemInput(lbl_phone, txt_memberPhone));
        
        
        
        
        lbl_sort = new JLabel("sắp xếp");
        txt_sort = new JTextField();
        pn_findOrder.add(styledComboboxInput(lbl_sort, comboBoxSort));
        
        
        lbl_column = new JLabel("theo cột");
        txt_column = new JTextField();
        pn_findOrder.add(styledComboboxInput(lbl_column, comboBoxColumnSort));
        
        pn_findOrder.add(styledSpinnerInput( lbl_show, spinnerShow));
        

        dateChooserStart.getDateEditor().setEnabled(false);
        dateChooserEnd.getDateEditor().setEnabled(false);
        
        
        dftmd_listOrder = new DefaultTableModel(header, 0); // 0 là số dòng ban đầu
        tb_listOrder = new JTable(dftmd_listOrder);
        scp_listOrder = new JScrollPane(tb_listOrder);
        pn_listOrder.add(scp_listOrder, BorderLayout.CENTER);
        // =====================================================EVENT

        JTextField dateField = ((JTextField) dateChooserStart.getDateEditor().getUiComponent());
        // Bắt sự kiện click vào text field để xóa ngày
        dateField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dateChooserStart.setDate(null);
            }
        });
        
        dateField = ((JTextField) dateChooserEnd.getDateEditor().getUiComponent());
        // Bắt sự kiện click vào text field để xóa ngày
        dateField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dateChooserEnd.setDate(null);
            }
        });

        btn_findOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                renderOrderToTable();
            }
        });;


     
  

 
 
        
    
        


   
        
       
        
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

        renderOrderToTable();
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

    public static void renderOrderToTable(){
        ArrayList<String> whereConditions = new ArrayList<>();
        ArrayList<String> having = new ArrayList<>();
        ArrayList<Object> param = new ArrayList<>();
        String orderBy = null;
        String orderType = null;
        Integer limit = null;
        
        String id = txt_idOrder.getText().trim();
        if(!id.isEmpty()){
            if (id.matches("\\d+")) {
                whereConditions.add("donhang.maDH = ?"); // thiếu khoảng trắng ở cuối → sửa bên dưới luôn
                param.add(Integer.parseInt(id));
            } else {
                data = new ArrayList<>(Arrays.asList()); 
                JOptionPane.showMessageDialog(null, "Mã đơn hàng phải là số nguyên!", "Lỗi mã đơn hàng", JOptionPane.WARNING_MESSAGE);
                return;
            } 
        }
        
        Date date = dateChooserStart.getDate(); // Lấy ngày từ JDateChooser
        if (date != null) {
            whereConditions.add("donhang.NgayTT >= ?");
            param.add(new java.sql.Date(date.getTime())); // Convert sang java.sql.Date
        }
        date = dateChooserEnd.getDate(); // Lấy ngày từ JDateChooser
        if (date != null) {
            whereConditions.add("donhang.NgayTT <= ?");
            param.add(new java.sql.Date(date.getTime())); // Convert sang java.sql.Date
        }
        
        Object status = comboBoxStatus.getSelectedItem();
        if (status != null && !status.toString().isEmpty()) {
            whereConditions.add("donhang.trangThai = ?");
            param.add(status.toString());
        }

        id = txt_employeeId.getText().trim();
        if(!id.isEmpty()){
            if (id.matches("\\d+")) {
                whereConditions.add("donhang.maNV = ?"); // thiếu khoảng trắng ở cuối → sửa bên dưới luôn
                param.add(Integer.parseInt(id));
            } else {
                data = new ArrayList<>(Arrays.asList()); 
                JOptionPane.showMessageDialog(null, "Mã nhân viên phải là số nguyên!", "Lỗi mã nhân viên", JOptionPane.WARNING_MESSAGE);
                return;
            } 
        }

        String name = txt_employeeName.getText().trim();
        if (!name.isEmpty()) {
            whereConditions.add("nhanvien.tenNV LIKE ?");
            param.add("%" + name + "%"); // dùng LIKE để tìm gần đúng theo tên
        }

        String phone = txt_memberPhone.getText().trim();
        if (!phone.isEmpty()) {
            if (phone.matches("\\d+")) {
                whereConditions.add("theThanhVien.SDT = ?");
                param.add(phone);
            } else {
                data = new ArrayList<>(Arrays.asList()); 
                JOptionPane.showMessageDialog(null, "Số điện thoại phải là số!", "Lỗi số điện thoại", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Lấy giá trị cột sắp xếp
        Object columnSort = comboBoxColumnSort.getSelectedItem();
        System.out.println(columnSort.toString().trim().toLowerCase());
        if (columnSort != null) {
            switch (columnSort.toString().trim().toLowerCase()) {
                case "mã đơn hàng": orderBy = "donhang.maDH"; break;
                case "tên nhân viên": orderBy = "nhanvien.tenNV"; break;
                case "ngày mua": orderBy = "donhang.NgayTT"; break;
                case "tổng tiền": orderBy = "tongTien"; break;
                default:
                    throw new IllegalArgumentException("Trường orderBy không hợp lệeeeee");
            }
        } else {
            orderBy = "donhang.maDH"; // fallback mặc định
        }
        System.out.println(orderBy);

                // Lấy thứ tự sắp xếp
        Object sort = comboBoxSort.getSelectedItem();
        if (sort != null && !sort.toString().isEmpty()) {
            if (sort.toString().equals("Tăng dần")) {
                orderType = "ASC";
            } else {
                orderType = "DESC";
            }
        } else {
            // Mặc định tăng dần
            orderType = "ASC";
        }
        System.out.println(orderType);


        
        
        
        // ==== Tổng tiền tối thiểu ====
        Object minVal = spinnerTotalMin.getValue();
        if (minVal instanceof Number) {
            int min = ((Number) minVal).intValue();
            having.add("SUM(chitietdh.SoLuong * sanpham.Gia) >= ?");
            param.add(min);
        }

        // ==== Tổng tiền tối đa ====
        Object maxVal = spinnerTotalMax.getValue();
        if (maxVal instanceof Number) {
            int max = ((Number) maxVal).intValue();
            having.add("SUM(chitietdh.SoLuong * sanpham.Gia) <= ?");
            param.add(max);
        }

        // ==== Tổng tiền tối thiểu ====
      Object limitShow = spinnerShow.getValue();
      if (limitShow instanceof Number) {
          limit =  ((Number) limitShow).intValue();
          param.add(limit);
      }else{
          JOptionPane.showMessageDialog(null, "Số dòng phải là số nguyên!", "Lỗi số dòng", JOptionPane.WARNING_MESSAGE);

      }
        
        data = DonHangBLL.getFindSortOrder(whereConditions, having, param, orderBy, orderType, limit);
        addDataToTable();
    }

    public static void addDataToTable(){
        dftmd_listOrder.setRowCount(0); // Xóa hết dữ liệu cũ
        for (ArrayList<Object> rowData : data) {
            dftmd_listOrder.addRow(rowData.toArray());
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
        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/images/icon/Logo_market.png");  
        frame.setIconImage(icon);  
        
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
