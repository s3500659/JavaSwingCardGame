package view.components;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.addPlayer.AddPlayerActionListener;
import controller.dealcards.DealCardsActionListener;
import controller.dealhouse.DealHouseActionListener;
import controller.placebet.PlaceScoreBetActionListener;
import controller.placebet.PlaceSuitBetActionListener;
import controller.removeplayer.RemovePlayerActionListener;
import model.GameEngine;
import view.gui.callback.GuiCallback;
import view.resources.Properties;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar implements PropertyChangeListener {
	
	private AbstractButton addPlayerButton;
	private AbstractButton removePlayerButton;
	private AbstractButton placeScoreBetButton;
	private AbstractButton placeSuitBetButton;
	private AbstractButton dealPlayerButton;
	private AbstractButton dealHouseButton;
	
	
	
	public ToolBar(GameEngine engine, GuiCallback view, MainFrame frame) {

		addPlayerButton = new JButton("Add Player",
				new ImageIcon("images/Add Player.png"));
		removePlayerButton = new JButton("Remove Player",
				new ImageIcon("images/Remove Player.png"));
		placeScoreBetButton = new JButton("Place Score Bet",
				new ImageIcon("images/Place Bet.png"));
		placeSuitBetButton = new JButton("Place Suit Bet",
				new ImageIcon("images/Place Bet.png"));
		dealPlayerButton = new JButton("Deal Player",
				new ImageIcon("images/Deal Cards.png"));
		dealHouseButton = new JButton("Deal House",
				new ImageIcon("images/Deal Cards.png"));
//		AbstractButton betOutcomeButton = new JButton("Bet Outcome",
//				new ImageIcon("images/Bet Outcome.png"));

		add(addPlayerButton);
		add(removePlayerButton);
		add(placeScoreBetButton);
		add(placeSuitBetButton);
		add(dealPlayerButton);
		add(dealHouseButton);
//		add(betOutcomeButton);
		
		addPlayerButton.addActionListener(new AddPlayerActionListener(engine, view, frame));
		removePlayerButton.addActionListener(new RemovePlayerActionListener(engine, view, frame));
		placeScoreBetButton.addActionListener(new PlaceScoreBetActionListener(engine, view, frame));
		placeSuitBetButton.addActionListener(new PlaceSuitBetActionListener(engine, view, frame));
		dealPlayerButton.addActionListener(new DealCardsActionListener(engine, view, frame));
		dealHouseButton.addActionListener(new DealHouseActionListener(engine, view, frame));

		
		view.addPropertyChangeListener(this);
		
		
	}
	
	private void disableToolbarButtons() {
		addPlayerButton.setEnabled(false);
		removePlayerButton.setEnabled(false);
		placeScoreBetButton.setEnabled(false);
		placeSuitBetButton.setEnabled(false);
		placeScoreBetButton.setEnabled(false);
		dealPlayerButton.setEnabled(false);
		dealHouseButton.setEnabled(false);
	}
	
	private void enableToolbarButtons() {
		addPlayerButton.setEnabled(true);
		removePlayerButton.setEnabled(true);
		placeScoreBetButton.setEnabled(true);
		placeSuitBetButton.setEnabled(true);
		placeScoreBetButton.setEnabled(true);
		dealPlayerButton.setEnabled(true);
		dealHouseButton.setEnabled(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if (evt.getPropertyName() == Properties.DISABLE_GAME_ITEMS.getProperty()) {
			disableToolbarButtons();
		}
		
		if (evt.getPropertyName() == Properties.ENABLE_GAME_ITEMS.getProperty()) {
			enableToolbarButtons();
		}
		
	}

}
