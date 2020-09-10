package controller.placebet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JTextField;

import controller.playagain.PlayAgainDialog;
import model.GameEngine;
import model.Player;
import model.bet.Bet;
import view.components.MainFrame;
import view.dialog.warning.WarningDialog;
import view.gui.callback.GuiCallback;
import view.placebet.PlaceBetDialog;
import view.placebet.PlaceScoreBetDialog;
import view.resources.Properties;

public class PlaceTheBetAction implements ActionListener {

	private GuiCallback view;
	private MainFrame frame;
	private GameEngine engine;
	private JTextField betAmountField;
	private boolean isSuitBet;
	private PlaceBetDialog betDialog;

	public PlaceTheBetAction(MainFrame frame, GameEngine engine, GuiCallback view, boolean suitBet,
			JTextField betAmountField, PlaceBetDialog betDialog) {
		this.frame = frame;
		this.engine = engine;
		this.view = view;
		this.isSuitBet = suitBet;
		this.betAmountField = betAmountField;
		this.betDialog = betDialog;
	}

	public PlaceTheBetAction(MainFrame frame, GameEngine engine, GuiCallback view, JTextField betAmountField,
			PlaceScoreBetDialog betDialog) {
		this.view = view;
		this.frame = frame;
		this.engine = engine;
		this.betAmountField = betAmountField;
		this.betDialog = betDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Player focusedPlayer = view.getFocusedPlayer();

		if (betAmountField.getText().equals("") || betAmountField.getText() == null) {
			new WarningDialog(frame, "Please enter a valid bet amount");
		}

		int betAmount = Integer.parseInt(betAmountField.getText());

		if (betAmount <= 0) {
			new WarningDialog(frame, "Bet Amount cannot be 0 or less");
		} else if (betAmount > view.getFocusedPlayer().getPoints()) {
			new WarningDialog(frame, "You don't have enough points to place this bet");
		}

		if (isSuitBet) {

			if (view.getBettingSuit(focusedPlayer) == null) {
				new WarningDialog(betDialog, "Please select a betting suit");
			} else {
				engine.placeBet(focusedPlayer.getId(), betAmount, view.getBettingSuit(focusedPlayer));
				view.setPlayerBetType(focusedPlayer, Properties.SUIT_BET);
				betDialog.dispose();

			}

		} else {
			engine.placeBet(focusedPlayer.getId(), betAmount);
			view.setPlayerBetType(focusedPlayer, Properties.SCORE_BET);
			betDialog.dispose();

		}

		// start auto deal
		new Thread(new Runnable() {

			@Override
			public void run() {
				automaticDeal();

			}
		}).start();

	}

	private void automaticDeal() {

		boolean flag = true;
		Collection<Player> hasBeenDealt = new ArrayList<>();

		for (Player p : engine.getAllPlayers()) {
			if (p != view.getTheHouse()) {
				if (p.getBet() == Bet.NO_BET) {
					flag = false;
				}
				if (!p.getHand().isEmpty()) {
					hasBeenDealt.add(p);
				}
			}
		}

		if (flag == true) {
			// disable game items while dealing
			view.disableGameItems();

			// deal to each player
			for (Player p : engine.getAllPlayers()) {
				if (hasBeenDealt.contains(p) || p == view.getTheHouse()) {
					continue;
				} else {
					engine.dealPlayer(p.getId(), view.getDealSpeed());
				}

			}
			
			// deal to house
			engine.dealHouse(view.getDealSpeed());

			// enable game items
			view.enableGameItems();
			

			// apply bet result
			for (Player p : engine.getAllPlayers()) {
				p.applyBetResult(view.getTheHouse().getHand());
			}
			new PlayAgainDialog(engine, view, frame);

		}

	}

}
