package tools;

import model.card.Rank;
import model.card.Suit;

public interface Tools {
	
	public static String capitalise(Rank rank) {
		String lowerCaseString = rank.toString().toLowerCase();
		String capitalisedString = lowerCaseString.substring(0,1).toUpperCase() + lowerCaseString.substring(1);
		return capitalisedString;
	}
	
	public static String capitalise(Suit suit) {
		String lowerCaseString = suit.toString().toLowerCase();
		String capitalisedString = lowerCaseString.substring(0,1).toUpperCase() + lowerCaseString.substring(1);
		return capitalisedString;
	}

}
