package com.sieuthi.Admin_PanelThongKe;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class PanelBieuDo extends JPanel{
    public PanelBieuDo(){
        setBorder(new CompoundBorder(new TitledBorder("Biểu Đồ Doanh Thu"), new EmptyBorder(4, 4, 4, 4)));
        
        setLayout(new BorderLayout());
        
        // Tạo biểu đồ
        JFreeChart chart = createChart(createDataset());
        
        // Đặt biểu đồ vào ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    // Tạo dữ liệu cho biểu đồ
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100, "Doanh thu", "T 1");
        dataset.addValue(200, "Doanh thu", "T 2");
        dataset.addValue(150, "Doanh thu", "T 3");
        dataset.addValue(50, "Doanh thu", "T 4");
        dataset.addValue(70, "Doanh thu", "T 5");
        dataset.addValue(30, "Doanh thu", "T 6");
        dataset.addValue(10, "Doanh thu", "T 7");
        dataset.addValue(150, "Doanh thu", "T 8");
        dataset.addValue(161, "Doanh thu", "T 9");
        dataset.addValue(150, "Doanh thu", "T 10");
        dataset.addValue(165, "Doanh thu", "T 11");
        dataset.addValue(100, "Doanh thu", "T 12");
        return dataset;
    }

    // Tạo biểu đồ từ dataset
    private JFreeChart createChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Biểu đồ doanh thu",  // Tiêu đề
                "Tháng",              // Nhãn trục X
                "Doanh thu (triệu VND)", // Nhãn trục Y
                dataset);
    }

}
