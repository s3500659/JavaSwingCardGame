package controller.dealcards;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameEngine;
import model.bet.Bet;
import view.components.MainFrame;
import view.dialog.warning.WarningDialog;
import view.gui.callback.GuiCallback;

public class DealCardsActionListener implements ActionListener {

	private GameEngine engine;
	private GuiCallback view;
	private MainFrame frame;

	public DealCardsActionListener(GameEngine engine, GuiCallback view, MainFrame frame) {
		this.engine = engine;
		this.view = view;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		new Thread() {

			@Override
			public void run() {
				
				// if focus player has not placed any bets
				if (view.getFocusedPlayer().getBet() == Bet.NO_BET) {
					new WarningDialog(frame, "This player has not placed any bets");
				} else {
					// disable game items
					view.disableGameItems();
					
					// deal to focused player
					engine.dealPlayer(view.getFocusedPlayer().getId(), view.getDealSpeed());
					
					// enable game item
					view.enableGameItems();
				}

			}
		}.start();

	}

}
