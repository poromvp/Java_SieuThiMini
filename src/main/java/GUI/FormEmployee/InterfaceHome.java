package GUI.FormEmployee;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterfaceHome extends JPanel{
      public InterfaceHome(){
        setBackground(Color.CYAN);
    }

     public static void main(String[] args) {
        JFrame frame = new JFrame("Đơn hàng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500); // Mở rộng chiều ngang để thấy rõ tỷ lệ
        frame.setLocationRelativeTo(null);
        JPanel panel = new  InterfaceHome();
    
        frame.add(panel);
        frame.setVisible(true);
    }
}
