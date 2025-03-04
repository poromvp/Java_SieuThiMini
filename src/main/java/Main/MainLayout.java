package Main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainLayout extends JFrame {
	

    public MainLayout(){
        setBounds(100, 100, 1390, 703);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        


    }

    public static void main(String[] args) {
        new MainLayout();
    }
}
