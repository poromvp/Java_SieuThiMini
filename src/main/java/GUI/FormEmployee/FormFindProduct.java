package GUI.FormEmployee;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import GUI.ComponentCommon.StyledTable;
import GUI.ComponentCommon.StyledTextField;


public class FormFindProduct extends JPanel {
    private static JButton btn_find = new JButton("Tìm");

    private static JLabel lbl_maSP = new JLabel("Mã SP");
    private static JTextField txt_maSP = new StyledTextField();
    private static JPanel pn_maSP = new JPanel(new GridBagLayout());

    private static JLabel lbl_loaiSP = new JLabel("Loại SP");
    private static JTextField txt_loaiSP = new StyledTextField();
    private static JPanel pn_loaiSP = new JPanel(new GridBagLayout());

    private static JLabel lbl_tenSP = new JLabel("Tên SP");
    private static JTextField txt_tenSP = new StyledTextField();
    private static JPanel pn_tenSP = new JPanel(new GridBagLayout());

    private static JLabel lbl_giaSP = new JLabel("Giá SP");
    private static JTextField txt_giaSP = new StyledTextField();
    private static JPanel pn_giaSP = new JPanel(new GridBagLayout());

    private static JPanel pn_containerInput = new JPanel(new GridBagLayout());

    private static String[] headerCol = {"Mã SP", "Tên SP", "Giá", "Giảm giá", "Tồn kho"};
    private static double[] widthCol = {0.1, 0.4, 0.2, 0.1, 0.2}; // Tỷ lệ phần trăm độ rộng

    Object[][] data = {
            {1, "Sản phẩm A", 100000, 3, 10},
            {2, "Sản phẩm B", 200000, 2, 5},
            {3, "Sản phẩm C", 200000, 2, 5},
            {4, "Sản phẩm D", 200000, 2, 5},
            {5, "Sản phẩm E", 200000, 2, 5},
            {5, "Sản phẩm E", 200000, 2, 5},
            {5, "Sản phẩm E", 200000, 2, 5},
            {5, "Sản phẩm E", 200000, 2, 5},
            {5, "Sản phẩm E", 200000, 2, 5},
            {5, "Sản phẩm E", 200000, 2, 5},
            {5, "Sản phẩm E", 200000, 2, 5},
            {5, "Sản phẩm E", 200000, 2, 5},
            
    };

    public FormFindProduct() {
        this.setLayout(new BorderLayout());

        // Panel chứa các ô nhập liệu
        addCell(pn_maSP, lbl_maSP, 0, 0, 1, 1);
        addCell(pn_maSP, txt_maSP, 0, 1, 1, 1);
        addCell(pn_containerInput, pn_maSP, 0, 0, 1, 1);

        addCell(pn_loaiSP, lbl_loaiSP, 0, 0, 1, 1);
        addCell(pn_loaiSP, txt_loaiSP, 0, 1, 1, 1);
        addCell(pn_containerInput, pn_loaiSP, 1, 0, 1, 1);

        addCell(pn_tenSP, lbl_tenSP, 0, 0, 1, 1);
        addCell(pn_tenSP, txt_tenSP, 0, 1, 1, 1);
        addCell(pn_containerInput, pn_tenSP, 2, 0, 1, 1);

        addCell(pn_giaSP, lbl_giaSP, 0, 0, 1, 1);
        addCell(pn_giaSP, txt_giaSP, 0, 1, 1, 1);
        addCell(pn_containerInput, pn_giaSP, 3, 0, 1, 1);

        addCell(pn_containerInput, btn_find, 4, 0, 1, 1);

        
        
        JTable table = new StyledTable(data, headerCol);
      
        // Giới hạn số hàng hiển thị (5 hàng)
        int rowCountToShow = 4;
        int tableHeight = table.getRowHeight() * rowCountToShow;

        // Bọc bảng trong JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(450, tableHeight)); // Giới hạn chiều cao
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Luôn hiển thị thanh cuộn



        scrollPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setColumnWidths(table, scrollPane);
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(pn_containerInput, BorderLayout.NORTH);
    }

    private void addCell(JPanel panel, Component component, int x, int y, int width, double weightx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = weightx;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(component, gbc);
    }

    // Hàm cập nhật độ rộng của từng cột khi cửa sổ thay đổi kích thước
    private void setColumnWidths(JTable table, JScrollPane scrollPane) {
        TableColumnModel columnModel = table.getColumnModel();
        int totalWidth = scrollPane.getViewport().getWidth(); // Lấy kích thước thực tế của bảng

        for (int i = 0; i < headerCol.length; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth((int) (totalWidth * widthCol[i]));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.add(new FormFindProduct());

       

        frame.setVisible(true);
    }
}
