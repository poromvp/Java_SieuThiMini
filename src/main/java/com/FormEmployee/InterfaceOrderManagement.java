package com.FormEmployee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class InterfaceOrderManagement extends JPanel implements ActionListener{

    JPanel pn_toolBar ;
    JButton btn_show ;
    JButton btn_edit ;
    JButton btn_delete;
    JButton btn_excel ;
    JButton btn_pdf ;

    JPanel pn_listOrder ;
        DefaultTableModel dftmd_listOrder ;
        JTable tb_listOrder ;

        JScrollPane scp_listOrder;
        JPanel pn_findOrder ;

        
   
 
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
        JTextField txt_dateStart ;
        JTextField txt_dateEnd ;
        JTextField txt_totalMin ;
        JTextField txt_totalMax ;
        JTextField txt_status ;
        JTextField txt_idMember;
        JTextField txt_memberName ;
        JTextField txt_memberPhone;
        JTextField txt_sort ;
        JTextField txt_column;
        JButton btn_sort ;



    public InterfaceOrderManagement(){
        setBackground(Color.pink);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

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
        pn_listOrder = new JPanel(new BorderLayout());
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
        pn_findOrder = new JPanel(new GridBagLayout());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần

        // Labels (Cột 1)
        gbc.gridx = 0;
        gbc.weightx = 0.2; // Cột 1 nhỏ hơn
         lbl_orderId = new JLabel("mã đơn hàng");
        pn_findOrder.add(lbl_orderId, gbc);

        gbc.gridy = 1;
        lbl_startDate = new JLabel("ngày bắt đầu");
        pn_findOrder.add(lbl_startDate, gbc);

        gbc.gridy = 2;
        lbl_endDate = new JLabel("ngày kết thúc");
        pn_findOrder.add(lbl_endDate, gbc);

        gbc.gridy = 3;
        lbl_minTotal = new JLabel("tổng tiền min");
        pn_findOrder.add(lbl_minTotal, gbc);
        
        gbc.gridy = 4;
        lbl_maxTotal = new JLabel("tổng tiền max");
        pn_findOrder.add(lbl_maxTotal, gbc);

        gbc.gridy = 5;
        lbl_status = new JLabel("trạng thái");
        pn_findOrder.add(lbl_status, gbc);

        gbc.gridy = 6;
        lbl_memberId = new JLabel("mã thành viên");
        pn_findOrder.add(lbl_memberId, gbc);

        gbc.gridy = 7;
        lbl_memberName = new JLabel("tên thành viên");
        pn_findOrder.add(lbl_memberName, gbc);

        gbc.gridy = 8;
        lbl_phone = new JLabel("số điện thoại");
        pn_findOrder.add(lbl_phone, gbc);

        btn_findOrder = new JButton("Tìm kiếm");
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        pn_findOrder.add(btn_findOrder, gbc);
        gbc.gridwidth = 1;

        gbc.gridy = 10;
        lbl_sort = new JLabel("sắp xếp");
        pn_findOrder.add(lbl_sort, gbc);

        gbc.gridy = 11;
        lbl_column = new JLabel("theo cột");
        pn_findOrder.add(lbl_column, gbc);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.9;
        gbc.gridx = 1;
        gbc.gridy = 0;
        txt_idOrder = new JTextField();
        pn_findOrder.add(txt_idOrder, gbc);

        gbc.gridy = 1;
        txt_dateStart = new JTextField();
        pn_findOrder.add(txt_dateStart, gbc);
        
        gbc.gridy = 2;
        txt_dateEnd = new JTextField();
        pn_findOrder.add(txt_dateEnd, gbc);

        gbc.gridy = 3;
        txt_totalMin = new JTextField();
        pn_findOrder.add(txt_totalMin, gbc);
        
        gbc.gridy = 4;
        txt_totalMax = new JTextField();
        pn_findOrder.add(txt_totalMax, gbc);
        
        gbc.gridy = 5;
        txt_status = new JTextField();
        pn_findOrder.add(txt_status, gbc);
        
        gbc.gridy = 6;
        txt_idMember = new JTextField();
        pn_findOrder.add(txt_idMember, gbc);
        
        gbc.gridy = 7;
        txt_memberName = new JTextField();
        pn_findOrder.add(txt_memberName, gbc);
        
        gbc.gridy = 8;
        txt_memberPhone = new JTextField();
        pn_findOrder.add(txt_memberPhone, gbc);
        
        gbc.gridy = 10;
        txt_sort = new JTextField();
        pn_findOrder.add(txt_sort, gbc);
        
        gbc.gridy = 11;
        txt_column = new JTextField();
        pn_findOrder.add(txt_column, gbc);


        gbc.gridy = 12;
        btn_sort = new JButton("sắp xếp");
        pn_findOrder.add(btn_sort, gbc);
// ===========================================================================

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Mở rộng ngang
        gbc.weightx = 1; // Trọng số ngang để nó mở rộng theo chiều ngang
        add(pn_toolBar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH; // Cho phép mở rộng cả chiều ngang và dọc
        gbc.weighty = 1; // Trọng số dọc để bảng mở rộng đúng
        add(pn_listOrder, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.fill = GridBagConstraints.BOTH; // Cho phép mở rộng cả chiều ngang và dọc
        gbc.gridheight = 2;
        add(pn_findOrder, gbc);
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
                return; // Thoát khỏi phương thức nếu không có hàng nào được chọn
            }
    
            if (e.getSource() == btn_delete) {
                dftmd_listOrder.removeRow(selectedRow);
                System.out.println("Đã xoá hàng: " + selectedRow);
            }
    
            if (e.getSource() == btn_edit) {
                System.out.println("Chức năng chỉnh sửa chưa được triển khai!");
            }
    
            if (e.getSource() == btn_show) {
                System.out.println("Chức năng hiển thị chưa được triển khai!");
            }
    
            if (e.getSource() == btn_excel) {
                System.out.println("Xuất Excel chưa được triển khai!");
            }
    
            if (e.getSource() == btn_pdf) {
                System.out.println("Xuất PDF chưa được triển khai!");
            }
    
        } catch (Exception ex) {
            ex.printStackTrace(); // In lỗi ra console để dễ debug
        }
    }
    
    
}
