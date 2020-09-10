package controller.removeplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import model.GameEngine;
import model.Player;

public class RemoveThePlayer implements ActionListener {
	
	private GameEngine engine;
	private Player focusedPlayer;
	private JDialog dialog;

	public RemoveThePlayer(GameEngine engine, Player focusedPlayer, JDialog dialog) {
		this.engine = engine;
		this.focusedPlayer = focusedPlayer;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		new Thread() {
			@Override
			public void run() {
				engine.removePlayer(focusedPlayer.getId());
			}
		}.start();
		
		dialog.dispose();

	}

}
