package GUI.ComponentCommon;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class Header extends JPanel{
	private Color COLOR = new Color(33,58,89);
	
    public Header() {
		setBackground(COLOR);
		setPreferredSize(new Dimension( getWidth(), 50));

	}
}
