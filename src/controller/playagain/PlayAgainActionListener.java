package controller.playagain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import model.GameEngine;
import model.Player;
import view.gui.callback.GuiCallback;

public class PlayAgainActionListener implements ActionListener {

	private GameEngine engine;
	private GuiCallback view;
	private PlayAgainDialog playAgainDialog;

	public PlayAgainActionListener(GameEngine engine, GuiCallback view, PlayAgainDialog playAgainDialog) {
		this.engine = engine;
		this.view = view;
		this.playAgainDialog = playAgainDialog;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		view.clearPlayersHandPanel();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				for (Player p : engine.getAllPlayers()) {
					view.updatePlayerHand(p);
				}
				
			}
		});
		
		engine.resetAllBetsAndHands();		
		playAgainDialog.dispose();
		view.newRound();

	}

}
