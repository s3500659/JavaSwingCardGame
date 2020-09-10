package controller.removeplayer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameEngine;
import model.Player;

@SuppressWarnings("serial")
public class RemovePlayerConfirmationDialog extends JDialog {

	public RemovePlayerConfirmationDialog(JFrame frame, GameEngine engine, Player focusedPlayer) {
		super(frame, true);

		JPanel panel = new JPanel();
		add(panel);

		JLabel label = new JLabel("Are you sure you want to remove this player from the game? ");
		panel.add(label);

		JButton ok = new JButton("OK");
		panel.add(ok);
		ok.addActionListener(new RemoveThePlayer(engine, focusedPlayer, this));

		JButton cancel = new JButton("Cancel");
		panel.add(cancel);
		cancel.addActionListener(e -> {
			this.dispose();
		});

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
	}
}
