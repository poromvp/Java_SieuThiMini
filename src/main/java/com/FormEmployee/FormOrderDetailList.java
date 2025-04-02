package com.FormEmployee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class FormOrderDetailList extends JPanel {
    private static JTable tableProduct;
    private static DefaultTableModel tableModel;
    private static JScrollPane scrollPane;
    private static JPopupMenu popupMenu;
    
    private static final String[] HEADER = {"Mã SP", "Tên SP", "Giá", "Giảm giá", "Số lượng", "thành tiền"};
    private static final double[] WIDTH_COL = {0.1, 0.4, 0.2, 0.1, 0.1, 0.1};

    public static double calCalculateTotalAmount(){
        double total = 0;
        for(int i = 0; i < tableProduct.getRowCount(); i++){
            double price = Double.parseDouble(tableProduct.getValueAt(i, 2).toString());
            double discount = Double.parseDouble(tableProduct.getValueAt(i, 3).toString());
            int quantity = Integer.parseInt(tableProduct.getValueAt(i, 4).toString());
            total += (price - (price * discount / 100)) * quantity;
        }
        return total;
    }

    public static double calCalculateTotal(){
        double total = 0;
        for(int i = 0; i < tableProduct.getRowCount(); i++){
            double price = Double.parseDouble(tableProduct.getValueAt(i, 2).toString());
            int quantity = Integer.parseInt(tableProduct.getValueAt(i, 4).toString());
            total += (price ) * quantity;
        }
        return total;
    }
    
    

    public  FormOrderDetailList() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tableModel = new DefaultTableModel(null, HEADER) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableProduct = new JTable(tableModel);
        customizeTable();

        scrollPane = new JScrollPane(tableProduct);
        updateColumnWidths();
        scrollPane.setPreferredSize(new Dimension(600, 300));
        add(scrollPane, BorderLayout.CENTER);

        tableProduct.setComponentPopupMenu(createPopupMenu());
        tableProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }
        });

        tableModel.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.INSERT) {
                OrderIformationForm.rederOrderInformation();
            } else if (e.getType() == TableModelEvent.DELETE) {
                OrderIformationForm.rederOrderInformation();
            } else if (e.getType() == TableModelEvent.UPDATE) {
            OrderIformationForm.rederOrderInformation();
        }
        });

    }

    private static void customizeTable() {
        tableProduct.setFont(new Font("Arial", Font.PLAIN, 14));
        tableProduct.setRowHeight(30);
        tableProduct.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableProduct.getTableHeader().setBackground(new Color(30, 144, 255));
        tableProduct.getTableHeader().setForeground(Color.WHITE);
        tableProduct.setSelectionBackground(new Color(200, 230, 255));
        tableProduct.setSelectionForeground(Color.BLACK);
        tableProduct.setGridColor(new Color(220, 220, 220));
        tableProduct.setShowVerticalLines(false);
        tableProduct.setShowHorizontalLines(false);
    
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
        for (int i = 0; i < tableProduct.getColumnCount(); i++) {
            tableProduct.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        }
    }
    
    private static void updateColumnWidths() {
        TableColumnModel columnModel = tableProduct.getColumnModel();
        int totalWidth = scrollPane.getViewport().getWidth();
        for (int i = 0; i < WIDTH_COL.length; i++) {
            columnModel.getColumn(i).setPreferredWidth((int) (totalWidth * WIDTH_COL[i]));
        }
    }

    private static JPopupMenu createPopupMenu() {
        popupMenu = new JPopupMenu();
        
         JMenuItem viewItem = new JMenuItem("Xem chi tiết", new ImageIcon("src/main/resources/images/menuu.png"));
        JMenuItem deleteItem = new JMenuItem("Xóa sản phẩm", new ImageIcon("src/main/resources/images/deletee.png"));
        JMenuItem editQtyItem = new JMenuItem("Sửa số lượng", new ImageIcon("src/main/resources/images/editt.png"));


        viewItem.addActionListener(e -> viewProduct());
        deleteItem.addActionListener(e -> deleteProduct());
        editQtyItem.addActionListener(e -> editQuantity());

        popupMenu.add(viewItem);
        popupMenu.add(editQtyItem);
        popupMenu.add(deleteItem);

        return popupMenu;
    }

    private static void showPopup(MouseEvent e) {
        int row = tableProduct.rowAtPoint(e.getPoint());
        if (row >= 0) {
            tableProduct.setRowSelectionInterval(row, row);
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    public static void addProductDetailById(int id){

    }

    

    public static void addProductDetail(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    public static void addRows(Object[][] rowsData) {
        for (Object[] row : rowsData) {
            tableModel.addRow(row);
        }
    }

    private static void viewProduct() {
        int row = tableProduct.getSelectedRow();
        if (row != -1) {
            String productName = tableProduct.getValueAt(row, 1).toString();
            JOptionPane.showMessageDialog(null, "Thông tin chi tiết sản phẩm: " + productName, "Chi tiết", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void deleteProduct() {
        int row = tableProduct.getSelectedRow();
        if (row != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(row);
            }
        }
    }

    private static void editQuantity() {
        int row = tableProduct.getSelectedRow();
        if (row != -1) {
            String currentQty = tableProduct.getValueAt(row, 4).toString();
            String newQty = JOptionPane.showInputDialog(null, "Nhập số lượng mới:", currentQty);
            if (newQty != null && !newQty.trim().isEmpty()) {
                double price = Double.parseDouble(tableProduct.getValueAt(row, 2).toString());
                double discount = Double.parseDouble(tableProduct.getValueAt(row, 3).toString());
                double total = (price - (price * discount / 100)) * Integer.parseInt(newQty);

                tableModel.setValueAt(newQty, row, 4);
                tableModel.setValueAt(total, row, 5);
            }
        }
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Danh sách sản phẩm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        FormOrderDetailList orderList = new FormOrderDetailList();
        frame.add(orderList, BorderLayout.CENTER);

        addProductDetail(new Object[]{1, "Sản phẩm A", 100000, 3, 10, 53});
        addRows(new Object[][]{
            {2, "Sản phẩm B", 200000, 2, 10, 4353},
            {3, "Sản phẩm C", 300000, 1, 5, 54335}
        });

        frame.setVisible(true);
    }
}
