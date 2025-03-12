package com.FormEmployee;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.ComponentCommon.StyledTable;

public class FormOrderDetailList extends JPanel {

    private StyledTable tableProduct;
    private JScrollPane scrollPane;
    private String[] header = {"Mã SP", "Tên SP", "Giá", "Giảm giá"};
    private static final double[] widthCol = {0.1, 0.5, 0.2, 0.2}; // Tỷ lệ phần trăm độ rộng

    Object[][] data = {
        {1, "Sản phẩm A", 100000, 3},
        {2, "Sản phẩm B", 200000, 2},
        {3, "Sản phẩm C", 200000, 2},
        {4, "Sản phẩm D", 200000, 2},
        {5, "Sản phẩm E", 200000, 2},
        {6, "Sản phẩm F", 300000, 1},
    };

    public FormOrderDetailList() {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Danh sách sản phẩm"));

        this.setLayout(new BorderLayout()); // 🔥 Đảm bảo JPanel có layout

        
        tableProduct = new StyledTable(data, header );
        tableProduct.setEditable(false);
      

        scrollPane = new JScrollPane(tableProduct);
        scrollPane.setPreferredSize(new Dimension(450, 300));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setColumnWidths();
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        setColumnWidths();
    }

    private void setColumnWidths() {
        if (tableProduct.getColumnModel() == null || scrollPane.getViewport().getWidth() == 0) return;

        TableColumnModel columnModel = tableProduct.getColumnModel();
        int totalWidth = scrollPane.getViewport().getWidth(); //  Đúng cách lấy kích thước

        for (int i = 0; i < header.length; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth((int) (totalWidth * widthCol[i]));
        }

        tableProduct.revalidate(); 
        tableProduct.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Order Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        FormOrderDetailList orderDetailList = new FormOrderDetailList();
        frame.add(orderDetailList, BorderLayout.CENTER);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                orderDetailList.setColumnWidths();
            }
        });

        frame.setVisible(true);
    }
}
