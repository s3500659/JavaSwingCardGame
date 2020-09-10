package view.dialog.exitgame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ExitGameDialog extends JDialog {

	public ExitGameDialog(JFrame frame) {
		super(frame, "Exit Game", true);

		setLayout(new FlowLayout());

		JLabel label = new JLabel("Are you sure you want to exit the game?");
		add(label);

		JButton yesButton = new JButton("Yes");
		JButton noButton = new JButton("No");
		add(yesButton);
		add(noButton);

		yesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		noButton.addActionListener(e -> {dispose();});

		setLocationRelativeTo(frame);
		pack();
		setVisible(true);

	}

}
