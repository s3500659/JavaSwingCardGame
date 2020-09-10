package controller.removeplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.GameEngine;
import model.Player;
import view.dialog.warning.WarningDialog;
import view.gui.callback.GuiCallback;

public class RemovePlayerActionListener implements ActionListener {

	private GameEngine engine;
	private GuiCallback view;
	private JFrame frame;

	public RemovePlayerActionListener(GameEngine engine, GuiCallback view, JFrame frame) {
		this.engine = engine;
		this.view = view;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Player focusedPlayer = view.getFocusedPlayer();

		if (focusedPlayer == null) {
			
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new WarningDialog(frame, "To remove a player you must first focus the player");
					
				}
			});
		} else {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new RemovePlayerConfirmationDialog(frame, engine, focusedPlayer);

				}
			});
		}

	}

}
