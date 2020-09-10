package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import model.bet.Bet;
import model.bet.ScoreBetImpl;
import model.bet.SuitBetImpl;
import model.card.Card;
import model.card.Deck;
import model.card.DeckImpl;
import model.card.Hand;
import model.card.Suit;
import view.GameCallback;
import view.GameCallbackCollection;

public class GameEngineImpl implements GameEngine, GameCallbackCollection {

	private Player theHouse = new PlayerImpl("House", "The House", 10000);
	private Collection<Player> players = new ArrayList<>();
	private Collection<GameCallback> callbacks = new ArrayList<>();
	private Collection<Player> playersHasBeenDealtCards = new ArrayList<>();
	private Deck deck = null;

	public GameEngineImpl() {
		players.add(theHouse);
	}

	@Override
	public void registerCallback(GameCallback callback) {
		callbacks.add(callback);
	}

	@Override
	public void removeCallback(GameCallback callback) {
		callbacks.remove(callback);
	}

	@Override
	public void addPlayer(Player player)
			throws NullPointerException, IllegalArgumentException {
		if (player == null) {
			throw new NullPointerException("Player object cannot be null");
		}
		String otherPlayerId = player.getId();
		for (Player i : players) {
			if (i.getId().equals(otherPlayerId)) {
				throw new IllegalArgumentException("This player already exist");
			}
		}
		players.add(player);
		for (GameCallback currCallback : callbacks) {
			currCallback.addPlayer(player);
		}
	}

	@Override
	public void removePlayer(String playerId)
			throws NullPointerException, IllegalArgumentException {
		Player player = null;
		for (Player i : players) {
			if (i.getId().equals(playerId)) {
				player = i;
			}
		}
		if (playerId == null) {
			throw new NullPointerException("The supplied player id is null");
		}
		if (player != null) {
			players.remove(player);
		} else {
			throw new IllegalArgumentException(playerId + " not found");
		}
		for (GameCallback currCallback : callbacks) {
			currCallback.removePlayer(player);
		}
	}

	@Override
	public Collection<Player> getAllPlayers() {
		Collection<Player> shallowCopy = new ArrayList<>(players);
		return Collections.unmodifiableCollection(shallowCopy);
	}

	@Override
	public void placeBet(String playerId, int amount)
			throws NullPointerException, IllegalArgumentException {

		Player player = null;
		for (Player i : players) {
			if (i.getId().equals(playerId)) {
				player = i;
			}
		}
		throwPlaceBetExceptions(playerId, amount, player);

		Bet currentBet = player.getBet();
		if (amount > currentBet.getAmount()) {
			ScoreBetImpl newBet = new ScoreBetImpl(player, amount);
			player.assignBet(newBet);
			for (GameCallback currCallback : callbacks) {
				currCallback.betUpdated(player);
			}
		} else {
			throw new IllegalArgumentException(
					"The new bet amount is less than the current bet amount");
		}
	}

	@Override
	public void placeBet(String playerId, int amount, Suit suit)
			throws NullPointerException, IllegalArgumentException {
		Player player = null;
		for (Player i : players) {
			if (i.getId().equals(playerId)) {
				player = i;
			}
		}
		throwPlaceBetExceptions(playerId, amount, player);
		if (suit == null) {
			throw new NullPointerException("supplied suit cannot be null");
		}
		SuitBetImpl newBet = new SuitBetImpl(player, amount, suit);
		player.assignBet(newBet);
		for (GameCallback currCallback : callbacks) {
			currCallback.betUpdated(player);
		}
	}

	private void throwPlaceBetExceptions(String playerId, int amount,
			Player player)
			throws NullPointerException, IllegalArgumentException {
		if (playerId == null) {
			throw new NullPointerException("Player id cannot be null");
		}
		if (player == null) {
			throw new IllegalArgumentException(
					"There is no current player with the supplied playerId");
		}
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"The supplied amount is not a positive number");
		}
		if (player.getTotalPoints() < amount) {
			throw new IllegalArgumentException(
					"The supplied player is does not have enough points to place the bet");
		}
		if (player.getBet().getAmount() > amount) {
			throw new IllegalArgumentException(
					"The existing bet is greater than propose bet");
		}
	}

	@Override
	public void dealPlayer(String playerId, int delay)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {

		Player player = null;
		for (Player i : players) {
			if (i.getId().equals(playerId)) {
				player = i;
			}
		}

		if (playerId == null) {
			throw new NullPointerException("The supplied player id is null");
		}
		if (delay < 0) {
			throw new IllegalArgumentException(
					"The supplied delay cannot be a negative number");
		}
		if (player == null) {
			throw new IllegalArgumentException(
					"There is no current player with the supplied playerId");
		}
		if (player.getBet() == Bet.NO_BET) {
			throw new IllegalStateException(
					"The player has not placed any bet");
		}
		if (playersHasBeenDealtCards.contains(player)) {
			throw new IllegalStateException("Player has already been dealt to");
		}

		if (deck == null) {
			deck = DeckImpl.createShuffledDeck();
			for (GameCallback currCallback : callbacks) {
				currCallback.newDeck(deck);
			}
		}

		int playerScore = player.getHand().getScore();
		Card removedCard = null;
		while (playerScore <= Hand.BUST_SCORE) {
			removedCard = deck.removeNextCard();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			player.getHand().dealCard(removedCard);
			playerScore += removedCard.getValue();
			for (GameCallback i : callbacks) {
				i.playerCard(player, removedCard);
			}
		}
		if ((playerScore + removedCard.getValue()) > Hand.BUST_SCORE) {
			for (GameCallback i : callbacks) {
				i.playerBust(player, removedCard);
			}
		}
		playersHasBeenDealtCards.add(player);
	}

	@Override
	public void dealHouse(int delay) throws IllegalArgumentException {
		if (delay < 0) {
			throw new IllegalArgumentException(
					"The supplied delay cannot be a negative number");
		}

		if (deck == null) {
			deck = DeckImpl.createShuffledDeck();
			for (GameCallback currCallback : callbacks) {
				currCallback.newDeck(deck);
			}
		}

		int houseScore = theHouse.getHand().getScore();
		Card removedCard = null;
		while (houseScore <= Hand.BUST_SCORE) {
			removedCard = deck.removeNextCard();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			theHouse.getHand().dealCard(removedCard);
			houseScore += removedCard.getValue();
			for (GameCallback i : callbacks) {
				i.houseCard(theHouse.getHand(), removedCard);
			}
		}
		if ((houseScore + removedCard.getValue()) > Hand.BUST_SCORE) {
			for (GameCallback i : callbacks) {
				i.houseBust(theHouse.getHand(), removedCard);
			}
		}
		
		for (Player p : players) {
			p.applyBetResult(theHouse.getHand());
		}
	}

	@Override
	public void resetAllBetsAndHands() {
		for (Player currPlayer : players) {
			for (GameCallback currCallback : callbacks) {
				currPlayer.assignBet(Bet.NO_BET);
				currCallback.betUpdated(currPlayer);
				currPlayer.getHand().reset();
			}
		}
		playersHasBeenDealtCards.clear();
	}
}
