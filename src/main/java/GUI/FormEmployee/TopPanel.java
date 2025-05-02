package GUI.FormEmployee;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Dimension;

public class TopPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TopPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createHorizontalGlue());
		JLabel lbl_anh = new JLabel("New label");
		lbl_anh.setMaximumSize(new Dimension(100, 100));
		lbl_anh.setPreferredSize(new Dimension(100, 100));
		add(lbl_anh);

	}

}
