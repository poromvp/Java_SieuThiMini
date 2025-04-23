package GUI.ComponentCommon;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

import java.awt.*;

public class CustomComboBox extends JComboBox<String> {

    private final Color bgColor = new Color(17, 32, 51);

    public CustomComboBox(String[] items) {
        super(items);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(bgColor, 1));

    }
}
