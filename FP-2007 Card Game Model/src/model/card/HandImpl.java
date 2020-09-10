package model.card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HandImpl implements Hand {
	private int currentScore = 0;
	private List<Card> myCards = new ArrayList<Card>();

	public HandImpl() {

	}

	@Override
	public boolean dealCard(Card card) {
		int newCardScore = card.getValue();
		if ((currentScore + newCardScore) > Hand.BUST_SCORE) {
			return false;
		} else {
			myCards.add(card);
			currentScore += newCardScore;
			return true;
		}
	}

	@Override
	public boolean isEmpty() {
		if (myCards.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public int getNumberOfCards() {
		return myCards.size();
	}

	@Override
	public int getScore() {
		return currentScore;
	}

	@Override
	public int getSuitCount(Suit suit) {
		int suitCount = 0;
		if (suit == null) {
			return 0;
		}
		for (Card card : myCards) {
			if (card.getSuit() == suit) {
				suitCount++;
			}
		}
		return suitCount;
	}

	@Override
	public Collection<Card> getCards() {
		List<Card> cloneCards = new ArrayList<Card>(myCards);
		Collection<Card> immutableList = Collections
				.unmodifiableCollection(cloneCards);
		return immutableList;
	}

	@Override
	public void reset() {
		currentScore = 0;
		myCards.removeAll(myCards);

	}

	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "Empty Hand";
		}

		String cardString = "";
		for (Card card : myCards) {
			cardString += card.toString() + ", ";
		}
		String newCardString = cardString.replaceAll(", $", "");
		return String.format("Hand of %s cards [%s] Score: %d",
				this.getNumberOfCards(), newCardString, this.currentScore);
	}

}
