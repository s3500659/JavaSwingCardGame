package controller.addPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import model.GameEngine;
import model.Player;
import model.PlayerImpl;
import view.dialog.warning.WarningDialog;

public class CreatePlayerAction implements ActionListener {

	private JTextField name;
	private JTextField id;
	private JTextField points;
	private JDialog addPlayerDialog;
	private GameEngine engine;

	public CreatePlayerAction(GameEngine engine, JTextField name, JTextField id, JTextField points,
			JDialog addPlayerDialog) {

		this.engine = engine;
		this.name = name;
		this.id = id;
		this.points = points;
		this.addPlayerDialog = addPlayerDialog;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String name = this.name.getText();
		String id = this.id.getText();
		String points = this.points.getText();

		if (name.equals("")) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new WarningDialog(addPlayerDialog, "Please enter a player name ");

				}
			});
		} else if (id.equals("")) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new WarningDialog(addPlayerDialog, "Please enter a player id ");

				}
			});
		} else if (points.equals("")) {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new WarningDialog(addPlayerDialog, "Please enter initial player points ");

				}
			});
		} else {

			Player player = null;
			for (Player p : engine.getAllPlayers()) {
				if (p.getId().equals(id)) {
					player = p;
				}

			}

			if (player != null) {
				new WarningDialog(addPlayerDialog, "A player with this id already exist, please select another");
			} else {
				Player newPlayer = new PlayerImpl(id, name, Integer.parseInt(points));

				new Thread() {
					@Override
					public void run() {
						engine.addPlayer(newPlayer);
					}
				}.start();

				addPlayerDialog.dispose();
			}

		}

	}

}
