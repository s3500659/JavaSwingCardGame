package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import controller.focusplayer.FocusPlayerActionListener;
import model.GameEngine;
import model.Player;
import model.bet.Bet;
import view.gui.callback.GuiCallback;
import view.resources.PlayerImage;
import view.resources.Properties;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel implements PropertyChangeListener {

	
	private ButtonGroup playersGroup = new ButtonGroup();
	private GuiCallback view;
	private JFrame frame;
	private GameEngine engine;
	
	private HashMap<Player, JPanel> playersPanel = new HashMap<>();
	private HashMap<Player, JLabel> playerPointLabel = new HashMap<>();
	private HashMap<Player, JLabel> playerBetTypeLabel = new HashMap<>();
	private HashMap<Player, JLabel> playerBetSuitLabel = new HashMap<>();
	


	public PlayerPanel(GameEngine engine, GuiCallback view, JFrame frame) {

		this.view = view;
		this.frame = frame;
		this.engine = engine;

		setLayout(new GridLayout(0, 1));

		view.addPropertyChangeListener(this);
		
		// add House player
		addTheHousePlayer();

	}
	
	private void addTheHousePlayer() {
		Collection<Player> players = engine.getAllPlayers();
		for (Player p : players) {
			if (p.getId().equals("House")) {
				addNewPlayer(p, null);
				break;
			}
		}
	}

	private void addNewPlayer(Player newPlayer, PlayerImage playerIcon) {

		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

		JPanel topContainer = new JPanel(new GridLayout(0,1));
		
		JPanel outerContainer = new JPanel(new BorderLayout());
		JPanel innerContainer = new JPanel(new GridLayout(0, 2));

		outerContainer.add(innerContainer, BorderLayout.CENTER);

		innerContainer.setBorder(border);
		
		JLabel playerImage = null;
		if (playerIcon == null) {
			playerImage = new JLabel(PlayerImage.DEFAULT.createPlayerAvatar());
		} else {
			playerImage = new JLabel(playerIcon.createPlayerAvatar());
		}
		innerContainer.add(playerImage);

		JLabel playerName = new JLabel("Name: " + newPlayer.getName());
		JLabel playerId = new JLabel("ID: " + newPlayer.getId());
		JLabel playerPoints = new JLabel("Points: " + newPlayer.getPoints());
		JLabel betType = new JLabel("Bet type: " + newPlayer.getBet().toString());
		JLabel betSuit = new JLabel();

		JPanel playerInfoPanel = new JPanel(new GridLayout(0, 1));

		playerInfoPanel.add(playerName);
		playerInfoPanel.add(playerId);
		playerInfoPanel.add(playerPoints);
		playerInfoPanel.add(betType);
		playerInfoPanel.add(betSuit);

		innerContainer.add(playerInfoPanel);

		AbstractButton focusPlayer = new JToggleButton("Focus Player");
		playersGroup.add(focusPlayer);
		focusPlayer.addActionListener(new FocusPlayerActionListener(view, newPlayer));

		outerContainer.add(focusPlayer, BorderLayout.SOUTH);

		topContainer.add(outerContainer);
		add(topContainer);

		playersPanel.put(newPlayer, topContainer);
		playerPointLabel.put(newPlayer, playerPoints);
		playerBetTypeLabel.put(newPlayer, betType);
		playerBetSuitLabel.put(newPlayer, betSuit);

		frame.revalidate();
		frame.pack();
	}

	private void removePlayer(Player player) {
		JPanel panel = playersPanel.get(player);
		remove(panel);
		playersPanel.remove(player);
		frame.revalidate();
		frame.pack();
	}
	
	private void updatePlayerInfo(Player player) {
		
		SwingUtilities.invokeLater(new Runnable() {
			JLabel pointLabel;
			@Override
			public void run() {
				if (player.getBet() != Bet.NO_BET) {
					pointLabel = playerPointLabel.get(player);
					pointLabel.setText("Points: " + Integer.toString(player.getPoints()));
					
					
					JLabel betTypeLabel = playerBetTypeLabel.get(player);
					betTypeLabel.setText("Bet Type: " + view.getBetType(player).getProperty());
					
					if (view.getBetType(player) == Properties.SUIT_BET) {
						JLabel betSuitLabel = playerBetSuitLabel.get(player);
						betSuitLabel.setText("Betting Suit: " + view.getBettingSuit(player).toString());
					}
				}
				
				
				revalidate();
				
				
			}
		});
		
		
		
		
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == Properties.NEW_PLAYER.getProperty()) {

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					addNewPlayer((Player) evt.getNewValue(), view.getPlayerIcon());

				}
			});
		}

		if (evt.getPropertyName() == Properties.REMOVE_PLAYER.getProperty()) {

			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					removePlayer((Player) evt.getNewValue());

				}
			});
		}
		
		if (evt.getPropertyName() == Properties.UPDATE_BET.getProperty()) {
			
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					updatePlayerInfo((Player) evt.getNewValue());
					
				}
			});
		}
		
		if (evt.getPropertyName() == Properties.CLEAR_PLAYERS_HAND_PANEL.getProperty()) {
			for (Player p : engine.getAllPlayers()) {
				updatePlayerInfo(p);
			}
		}

	}

}
