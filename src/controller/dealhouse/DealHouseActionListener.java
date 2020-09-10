package controller.dealhouse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.playagain.PlayAgainDialog;
import model.GameEngine;
import model.Player;
import model.bet.Bet;
import view.components.MainFrame;
import view.dialog.warning.WarningDialog;
import view.gui.callback.GuiCallback;

public class DealHouseActionListener implements ActionListener {

	private GameEngine engine;
	private GuiCallback view;
	private MainFrame frame;

	public DealHouseActionListener(GameEngine engine, GuiCallback view, MainFrame frame) {
		this.engine = engine;
		this.view = view;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		new Thread() {
			@Override
			public void run() {

				boolean flag = true;
				for (Player p : engine.getAllPlayers()) {
					if (p.getBet() != Bet.NO_BET) {
						if (p.getHand().isEmpty()) {
							new WarningDialog(frame,
									"Players that has placed a bet my be dealt to first before dealing to the house");
							flag = false;
						}
					}
				}
				
				if (flag == true) {
					// disable game items
					view.disableGameItems();
					
					// deal house
					engine.dealHouse(view.getDealSpeed());
					
					//enable game items
					view.enableGameItems();
					
					// apply bet result
					for (Player p : engine.getAllPlayers()) {
						p.applyBetResult(view.getTheHouse().getHand());
					}
					
					// ask if player wants to play another round
					new PlayAgainDialog(engine, view, frame);
				}

			}
		}.start();

	}

}
