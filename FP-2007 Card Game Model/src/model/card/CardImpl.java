package model.card;

import tools.Tools;

public class CardImpl implements Card, Tools {

	private Suit suit;
	private Rank rank;

	public CardImpl(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public Rank getRank() {
		return rank;
	}

	@Override
	public int getValue() {
		return rank.getRankValue();
	}

	@Override
	public int compareTo(Card card) {
		CardImpl otherCard = (CardImpl) card;
		if (this.getSuit() == otherCard.getSuit()) {
			if (this.getValue() == otherCard.getValue()) {
				return 0;
			} else if (this.getValue() > otherCard.getValue()) {
				return 1;
			} else {
				return -1;
			}
		} else {
			if (this.getSuit().ordinal() > otherCard.getSuit().ordinal()) {
				return 1;
			} else if (this.getSuit().ordinal() < otherCard.getSuit()
					.ordinal()) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardImpl other = (CardImpl) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public String toString() {
		Rank rank = this.getRank();
		if (rank == Rank.ACE || rank == Rank.JACK || rank == Rank.QUEEN
				|| rank == Rank.KING) {
			return String.format("%s of %s", Tools.capitalise(this.getRank()),
					Tools.capitalise(this.getSuit()));
		} else {
			return String.format("%d of %s", this.getValue(),
					Tools.capitalise(this.getSuit()));
		}
	}

}
