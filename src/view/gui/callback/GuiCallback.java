package view.gui.callback;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.SwingUtilities;

import model.GameEngine;
import model.Player;
import model.card.Card;
import model.card.Deck;
import model.card.Hand;
import model.card.Suit;
import view.GameCallback;
import view.resources.PlayerImage;
import view.resources.Properties;

public class GuiCallback implements GameCallback {

	private PlayerImage playerImage;
	private Player focusedPlayer = null;
	private Player theHouse;
	private int roundNumber = 1;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private HashMap<Player, Properties> betType = new HashMap<>();
	private HashMap<Player, Suit> bettingSuit = new HashMap<>();
	private int selectedDealSpeed = 500;

	public GuiCallback(GameEngine engine) {

		Collection<Player> players = engine.getAllPlayers();
		for (Player p : players) {
			if (p.getId().equals("House")) {
				theHouse = p;

			}
		}
	}

	public void disableGameItems() {
		pcs.firePropertyChange(Properties.DISABLE_GAME_ITEMS.getProperty(), null, null);
	}
	
	public void enableGameItems() {
		pcs.firePropertyChange(Properties.ENABLE_GAME_ITEMS.getProperty(), null, null);
	}

	public void newRound() {
		incrementRoundsPlayed();
		pcs.firePropertyChange(Properties.NEW_ROUND.getProperty(), null, null);
	}

	public void incrementRoundsPlayed() {
		roundNumber++;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public Player getTheHouse() {
		return theHouse;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {

		this.pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void addPlayer(Player player) {

		pcs.firePropertyChange(Properties.NEW_PLAYER.getProperty(), null, player);

	}

	@Override
	public void removePlayer(Player player) {

		pcs.firePropertyChange(Properties.REMOVE_PLAYER.getProperty(), null, player);

	}

	@Override
	public void betUpdated(Player player) {

		pcs.firePropertyChange(Properties.UPDATE_BET.getProperty(), null, player);

	}

	@Override
	public void newDeck(Deck deck) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerCard(Player player, Card card) {

		pcs.firePropertyChange(Properties.PLAYER_CARD.getProperty(), player, card);

	}

	@Override
	public void playerBust(Player player, Card card) {

		pcs.firePropertyChange(Properties.PLAYER_BUST.getProperty(), player, card);

	}

	@Override
	public void houseCard(Hand houseHand, Card card) {

		pcs.firePropertyChange(Properties.PLAYER_BUST.getProperty(), theHouse, card);

	}

	@Override
	public void houseBust(Hand houseHand, Card card) {

		pcs.firePropertyChange(Properties.PLAYER_BUST.getProperty(), theHouse, card);

	}

	public Player getFocusedPlayer() {

		return focusedPlayer;
	}

	public void setFocusedPlayer(Player player) {
		focusedPlayer = player;
	}

	public void setStatus1(Player player) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				if (focusedPlayer != null) {

					String newValue = "Selected Player: " + player.getId();
					pcs.firePropertyChange(Properties.STATUS_1.getProperty(), null, newValue);

				}
			}
		});
	}

	public PlayerImage getPlayerIcon() {
		return playerImage;
	}

	public void setPlayerIcon(PlayerImage image) {
		this.playerImage = image;
	}

	public void setPlayerBetType(Player player, Properties suitBet) {
		this.betType.put(player, suitBet);

	}

	public Properties getBetType(Player player) {
		return betType.get(player);
	}

	public void setBettingSuit(Player player, Suit suit) {
		this.bettingSuit.put(player, suit);
	}

	public Suit getBettingSuit(Player player) {
		return bettingSuit.get(player);
	}

	public void setDealSpeed(int speed) {

		selectedDealSpeed = speed;

	}

	public int getDealSpeed() {
		return selectedDealSpeed;
	}

	public void updatePlayerHand(Player player) {
		pcs.firePropertyChange(Properties.UPDATE_PLAYER_HAND.getProperty(), null, player);

	}

	public void clearPlayersHandPanel() {
		pcs.firePropertyChange(Properties.CLEAR_PLAYERS_HAND_PANEL.getProperty(), null, null);

	}

}
