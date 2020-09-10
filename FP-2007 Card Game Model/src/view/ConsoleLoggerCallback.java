package view;

import java.util.Collection;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.GameEngine;
import model.Player;
import model.bet.BetResult;
import model.card.Card;
import model.card.Deck;
import model.card.Hand;
import tools.Tools;

/**
 * An implementation of GameCallback which uses a Logger to log game events to
 * the console.
 * 
 * <p>
 * <b>Important!</b> DO NOT EDIT THE STATIC BLOCK THAT SETS UP THE LOGGER OR
 * IT'S DECLARATION!
 * 
 * <p>
 * <b>Note:</b> Logging message format should be consistent with the output
 * trace.
 * 
 * @author Ross Nye
 * 
 * @see view.GameCallback
 * @see view.GameCallbackCollection
 *
 */
public class ConsoleLoggerCallback implements GameCallback {
	/**
	 * A static {@link java.util.logging.Logger} object used for logging
	 * information (in this case to the console)
	 * 
	 * DO NOT EDIT!
	 */
	GameEngine engine = null;
	public static final Logger LOGGER;

	static {
		// DO NOT EDIT THIS STATIC BLOCK!!

		// Creating consoleHandler, add it and set the log levels.
		LOGGER = Logger.getLogger(ConsoleLoggerCallback.class.getName());
		LOGGER.setLevel(Level.FINER);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.FINER);
		LOGGER.addHandler(handler);
		LOGGER.setUseParentHandlers(false);
	}

	public ConsoleLoggerCallback(GameEngine engine) {
		this.engine = engine;
	}

	@Override
	public void addPlayer(Player player) {
		// Added Player id=P1, name=Player One, points=1000, No Bet, Empty Hand
		LOGGER.log(Level.INFO,
				String.format("Added Player id=%s, name=%s, points=%d, %s, %s",
						player.getId(), player.getName(),
						player.getTotalPoints(), player.getBet().toString(),
						player.getHand().toString()));
	}

	@Override
	public void removePlayer(Player player) {
		// INFO: Removed Player id=X4, name=Player Four, points=4000, No Bet,
		// Empty Hand
		LOGGER.log(Level.INFO,
				String.format("Added Player id=%s, name=%s, points=%d, %s, %s",
						player.getId(), player.getName(),
						player.getTotalPoints(), player.getBet().toString(),
						player.getHand().toString()));
	}

	@Override
	public void betUpdated(Player player) {
		// INFO: Bet updated for P1 to Score Bet for 100
		LOGGER.log(Level.INFO, String.format("Bet updated for %s to %s",
				player.getId(), player.getBet().toString()));
	}

	@Override
	public void newDeck(Deck deck) {
		// INFO: A new deck of cards was created with 52 cards
		LOGGER.log(Level.INFO,
				String.format("A new deck of cards was created with %d cards",
						deck.cardsInDeck()));

	}

	@Override
	public void playerCard(Player player, Card card) {
		// FINE: Player P2 dealt Ace of Clubs
		LOGGER.log(Level.FINE,
				String.format("Player %s dealt %s of %s", player.getId(),
						card.getRank().getRankValue(),
						Tools.capitalise(card.getSuit())));
	}

	@Override
	public void playerBust(Player player, Card card) {
		// FINE: Player P2 bust on 5 of Clubs
		LOGGER.log(Level.FINE,
				String.format("Player %s bust on %s of %s", player.getId(),
						card.getValue(), Tools.capitalise(card.getSuit())));
		LOGGER.log(Level.INFO, String.format("Player %s score is %d",
				player.getId(), player.getHand().getScore()));

	}

	@Override
	public void houseCard(Hand houseHand, Card card) {
		// FINE: House dealt Queen of Clubs
		LOGGER.log(Level.FINE,
				String.format("House dealt %s", card.toString()));
	}

	@Override
	public void houseBust(Hand houseHand, Card card) {
		// FINE: House bust on 8 of Spades
		LOGGER.log(Level.FINE,
				String.format("House bust on %s", card.toString()));
		LOGGER.log(Level.INFO,
				String.format("House Hand: Hand of %d cards %s Score: %d",
						houseHand.getNumberOfCards(),
						houseHand.getCards().toString(), houseHand.getScore()));

		Collection<Player> playerList = engine.getAllPlayers();
		String playerString = "";
		String result = "";
		String betOutcome = "";
		for (Player i : playerList) {
			BetResult betResult = i.getBet().finaliseBet(houseHand);
			if (betResult == BetResult.UNDETERMINED) {
				result = "No Bet";
			} else if (betResult == BetResult.DRAW) {
				result = "Draw";
			} else if (betResult == BetResult.PLAYER_LOSS) {
				result = "Player Loss";
			} else {
				result = "Player Win";
			}
			if (i.getBet().getOutcome() == 0 && result.equals("No Bet")) {
				betOutcome = "";
			} else if (i.getBet().getOutcome() == 0) {
				betOutcome = "0";
			} else {
				betOutcome = Integer.toString(i.getBet().getOutcome());
			}
			playerString += String.format(
					"%s\n" + "Player: %s %20s %20s %10s\n", i.toString(),
					i.getId(), i.getName(), result, betOutcome);
		}
		LOGGER.log(Level.INFO,
				String.format("Final Results:\n%s", playerString));
	}
}
