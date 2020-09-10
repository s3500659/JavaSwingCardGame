package model.card;

/**
 * This enum represent the rank for each card
 * 
 * <p>
 * The natural order of rank should be Ace, 2, 3, ... 9, 10, Jack, Queen, King
 * 
 * <p>
 * <b>Note: </b>You must provide the method {@link Rank#getRankValue()} in the
 * enum and/or for each of it's values.
 * 
 * <p>
 * <b>Hint: </b>You may find it useful to override methods in the enum and/or
 * on each of the value.
 * 
 * <p>
 * <b>Hint: </b>Be sure to follow naming conventions for your enum values
 * 
 * <p>
 * <b>Note: </b> The {@link Rank#valueOf(String)} and {@link Rank#values()}
 * methods are provided by the API - you do not need to write or override them
 * yourself.
 * 
 * @author Ross Nye
 * 
 * @see model.card.Card
 * @see model.card.Suit
 *
 */
public enum Rank
{
	ACE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	QUEEN(10),
	KING(10);
	
	private final int rankValue;
	
	private Rank(int rank) {
		this.rankValue = rank;
	}
	
	public int getRankValue() {
		return rankValue;
	}
	
	@Override
	public String toString() {
		return Integer.toString(rankValue);
	}
	

		
}
