package controller.placebet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import model.GameEngine;
import model.Player;
import view.components.MainFrame;
import view.dialog.warning.WarningDialog;
import view.gui.callback.GuiCallback;
import view.placebet.PlaceScoreBetDialog;

public class PlaceScoreBetActionListener implements ActionListener {

	private GameEngine engine;
	private GuiCallback view;
	private MainFrame frame;

	public PlaceScoreBetActionListener(GameEngine engine, GuiCallback view, MainFrame frame) {

		this.engine = engine;
		this.view = view;
		this.frame = frame;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Player focusedPlayer = view.getFocusedPlayer();
		if (focusedPlayer == null) {
			new WarningDialog(frame, "Please focus a player to perform this action");
		} else if (focusedPlayer == view.getTheHouse()) {
			new WarningDialog(frame, "The house may not place any bet");
		} else {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new PlaceScoreBetDialog(frame, engine, view, focusedPlayer);				

				}
			});
		}

	}

}
