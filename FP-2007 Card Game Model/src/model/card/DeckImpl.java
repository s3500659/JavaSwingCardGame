package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckImpl implements Deck {

	private static int cardsInDeck;
	private static List<Card> cards;

	private DeckImpl() {
		cardsInDeck = 0;
		cards = new ArrayList<>();
	}

	@Override
	public Card removeNextCard() throws IllegalStateException {
		if (cardsInDeck() <= 0) {
			throw new IllegalStateException(
					"There's no more cards in this deck");
		}
		Card removedCard = cards.iterator().next();
		cards.remove(removedCard);
		cardsInDeck--;
		return removedCard;
	}

	@Override
	public int cardsInDeck() {
		return cardsInDeck;
	}

	public static Deck createSortedDeck() {
		DeckImpl localDeck = new DeckImpl();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = new CardImpl(suit, rank);
				cards.add(card);
				cardsInDeck++;
			}
		}
		return localDeck;
	}

	public static Deck createShuffledDeck() {
		Deck localDeck = createSortedDeck();
		localDeck.shuffleDeck();
		return localDeck;
	}

	@Override
	public String toString() {
		return String.format("This deck contains %d cards", cardsInDeck);
	}

	@Override
	public void shuffleDeck() {
		Collections.shuffle(cards);

	}



}
