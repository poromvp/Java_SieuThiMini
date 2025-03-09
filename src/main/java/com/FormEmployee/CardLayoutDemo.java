package com.FormEmployee;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardLayoutDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CardLayout Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Tạo CardLayout với khoảng cách giữa các thẻ
        CardLayout cardLayout = new CardLayout(10, 10);
        JPanel cardPanel = new JPanel(cardLayout);

        // Tạo các thẻ (JPanel)
        JPanel card1 = new JPanel();
        card1.setBackground(Color.RED);
        card1.add(new JLabel("Card 1"));

        JPanel card2 = new JPanel();
        card2.setBackground(Color.GREEN);
        card2.add(new JLabel("Card 2"));

        JPanel card3 = new JPanel();
        card3.setBackground(Color.BLUE);
        card3.add(new JLabel("Card 3"));

        // Thêm các thẻ vào CardLayout
        cardPanel.add(card1, "Card1");
        cardPanel.add(card2, "Card2");
        cardPanel.add(card3, "Card3");

        // Tạo panel chứa nút điều khiển
        JPanel buttonPanel = new JPanel();
        JButton btnNext = new JButton("Next");
        JButton btnPrevious = new JButton("Previous");
        JButton btnFirst = new JButton("First");
        JButton btnLast = new JButton("Last");
        JButton btnShowCard2 = new JButton("Show Card 2");

        // Gán sự kiện cho các nút
        btnNext.addActionListener(e -> cardLayout.next(cardPanel));
        btnPrevious.addActionListener(e -> cardLayout.previous(cardPanel));
        btnFirst.addActionListener(e -> cardLayout.first(cardPanel));
        btnLast.addActionListener(e -> cardLayout.last(cardPanel));
        btnShowCard2.addActionListener(e -> cardLayout.show(cardPanel, "Card2"));

        // Thêm nút vào panel
        buttonPanel.add(btnPrevious);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnFirst);
        buttonPanel.add(btnLast);
        buttonPanel.add(btnShowCard2);

        // Thêm vào Frame
        frame.setLayout(new BorderLayout());
        frame.add(cardPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
