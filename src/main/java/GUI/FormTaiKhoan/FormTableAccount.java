package GUI.FormTaiKhoan;

import BLL.TaiKhoanBLL;
import DTO.TaiKhoanDTO;
import GUI.ComponentCommon.StyledTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

public class FormTableAccount extends JPanel {
    private StyledTable accountTable;
    private DefaultTableModel tableModel;

    public FormTableAccount() {
        setLayout(new BorderLayout());
        String[] columnNames = {"Mã NV", "Tài khoản", "Mật khẩu", "Quyền", "Gmail", "Trạng thái"};
        initTable(columnNames);
    }

    private void initTable(String[] columnNames) {
        TaiKhoanBLL bll = new TaiKhoanBLL();
        List<TaiKhoanDTO> taiKhoanList = bll.getAllTaiKhoan();
        Object[][] data = new Object[taiKhoanList.size()][6];
        for (int i = 0; i < taiKhoanList.size(); i++) {
            TaiKhoanDTO tk = taiKhoanList.get(i);
            data[i] = new Object[]{
                tk.getMaNV(),
                tk.getTenTK(),
                tk.getMatKhau(),
                tk.getQuyen(),
                tk.getGmail(),
                tk.getTrangThai().equals("ACTIVE") ? "Đang hoạt động" : "Ngưng hoạt động"
            };
        }

        accountTable = new StyledTable(data, columnNames);
        tableModel = (DefaultTableModel) accountTable.getModel();
        JScrollPane scrollPane = new JScrollPane(accountTable);

        removeAll();
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    public void searchAndUpdateTable(String keyword) {
        TaiKhoanBLL bll = new TaiKhoanBLL();
        List<TaiKhoanDTO> taiKhoanList = bll.searchTaiKhoan(keyword);
        String[] columnNames = {"Mã NV", "Tài khoản", "Mật khẩu", "Quyền", "Gmail", "Trạng thái"};
        updateTableData(taiKhoanList, columnNames);
    }
    private void updateTableData(List<TaiKhoanDTO> taiKhoanList, String[] columnNames) {
        Object[][] data = new Object[taiKhoanList.size()][6];
        for (int i = 0; i < taiKhoanList.size(); i++) {
            TaiKhoanDTO tk = taiKhoanList.get(i);
            data[i] = new Object[]{
                tk.getMaNV(),
                tk.getTenTK(),
                tk.getMatKhau(),
                tk.getQuyen(),
                tk.getGmail(),
                tk.getTrangThai().equals("ACTIVE") ? "Đang hoạt động" : "Ngưng hoạt động"
            };
        }

        tableModel = new DefaultTableModel(data, columnNames);
        if (accountTable == null) {
            accountTable = new StyledTable(data, columnNames);
        } else {
            accountTable.setModel(tableModel);
        }

        JScrollPane scrollPane = new JScrollPane(accountTable);
        removeAll();
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    public void refreshTable() {
        String[] columnNames = {"Mã NV", "Tài khoản", "Mật khẩu", "Quyền", "Gmail", "Trạng thái"};
        initTable(columnNames);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getAccountTable() {
        return accountTable;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test FormTableAccount");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.add(new FormTableAccount());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}