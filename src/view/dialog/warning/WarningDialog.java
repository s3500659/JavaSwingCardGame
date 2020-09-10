package view.dialog.warning;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WarningDialog extends JDialog {

	public WarningDialog(JFrame frame, String message) {
		super(frame, "Warning", true);

		JPanel topPanel = new JPanel();
		add(topPanel);

		JLabel messageLabel = new JLabel(message);
		topPanel.add(messageLabel);

		JButton ok = new JButton("Ok");
		topPanel.add(ok);
		ok.addActionListener(e -> {
			this.dispose();
		});

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}

	public WarningDialog(JDialog dialog, String message) {
		super(dialog, "Warning", true);

		JPanel topPanel = new JPanel();
		add(topPanel);

		JLabel messageLabel = new JLabel(message);
		topPanel.add(messageLabel);

		JButton ok = new JButton("Ok");
		topPanel.add(ok);
		ok.addActionListener(e -> {
			this.dispose();
		});

		pack();
		setLocationRelativeTo(dialog);
		setVisible(true);

	}

}
