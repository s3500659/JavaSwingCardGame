package view.resources;

import javax.swing.ImageIcon;

import model.card.Suit;
import model.card.Rank;

public enum CardImage {
	
	TWO_CLUBS("images/cards/2C.png", Suit.CLUBS, Rank.TWO),
	TWO_DIAMOND("images/cards/2D.png", Suit.DIAMONDS, Rank.TWO),
	TWO_HEART("images/cards/2H.png", Suit.HEARTS, Rank.TWO),
	TWO_SPADE("images/cards/2S.png", Suit.SPADES, Rank.TWO),
	
	THREE_CLUBS("images/cards/3C.png", Suit.CLUBS, Rank.THREE),
	THREE_DIAMOND("images/cards/3D.png", Suit.DIAMONDS, Rank.THREE),
	THREE_HEART("images/cards/3H.png", Suit.HEARTS, Rank.THREE),
	THREE_SPADE("images/cards/3S.png", Suit.SPADES, Rank.THREE),
	
	FOUR_CLUBS("images/cards/4C.png", Suit.CLUBS, Rank.FOUR),
	FOUR_DIAMOND("images/cards/4D.png", Suit.DIAMONDS, Rank.FOUR),
	FOUR_HEART("images/cards/4H.png", Suit.HEARTS, Rank.FOUR),
	FOUR_SPADE("images/cards/4S.png", Suit.SPADES, Rank.FOUR),
	
	FIVE_CLUBS("images/cards/5C.png", Suit.CLUBS, Rank.FIVE),
	FIVE_DIAMOND("images/cards/5D.png", Suit.DIAMONDS, Rank.FIVE),
	FIVE_HEART("images/cards/5H.png", Suit.HEARTS, Rank.FIVE),
	FIVE_SPADE("images/cards/5S.png", Suit.SPADES, Rank.FIVE),
	
	SIX_CLUBS("images/cards/6C.png", Suit.CLUBS, Rank.SIX),
	SIX_DIAMOND("images/cards/6D.png", Suit.DIAMONDS, Rank.SIX),
	SIX_HEART("images/cards/6H.png", Suit.HEARTS, Rank.SIX),
	SIX_SPADE("images/cards/6S.png", Suit.SPADES, Rank.SIX),
	
	SEVEN_CLUBS("images/cards/7C.png", Suit.CLUBS, Rank.SEVEN),
	SEVEN_DIAMOND("images/cards/7D.png", Suit.DIAMONDS, Rank.SEVEN),
	SEVEN_HEART("images/cards/7H.png", Suit.HEARTS, Rank.SEVEN),
	SEVEN_SPADE("images/cards/7S.png", Suit.SPADES, Rank.SEVEN),
	
	EIGHT_CLUBS("images/cards/8C.png", Suit.CLUBS, Rank.EIGHT),
	EIGHT_DIAMOND("images/cards/8D.png", Suit.DIAMONDS, Rank.EIGHT),
	EIGHT_HEART("images/cards/8H.png", Suit.HEARTS, Rank.EIGHT),
	EIGHT_SPADE("images/cards/8S.png", Suit.SPADES, Rank.EIGHT),
	
	NINE_CLUBS("images/cards/9C.png", Suit.CLUBS, Rank.NINE),
	NINE_DIAMOND("images/cards/9D.png", Suit.DIAMONDS, Rank.NINE),
	NINE_HEART("images/cards/9H.png", Suit.HEARTS, Rank.NINE),
	NINE_SPADE("images/cards/9S.png", Suit.SPADES, Rank.NINE),
	
	TEN_CLUBS("images/cards/10C.png", Suit.CLUBS, Rank.TEN),
	TEN_DIAMOND("images/cards/10D.png", Suit.DIAMONDS, Rank.TEN),
	TEN_HEART("images/cards/10H.png", Suit.HEARTS, Rank.TEN),
	TEN_SPADE("images/cards/10S.png", Suit.SPADES, Rank.TEN),
	
	JACK_CLUBS("images/cards/JC.png", Suit.CLUBS, Rank.JACK),
	JACK_DIAMOND("images/cards/JD.png", Suit.DIAMONDS, Rank.JACK),
	JACK_HEART("images/cards/JH.png", Suit.HEARTS, Rank.JACK),
	JACK_SPADE("images/cards/JS.png", Suit.SPADES, Rank.JACK),
	
	QUEEN_CLUBS("images/cards/QC.png", Suit.CLUBS, Rank.QUEEN),
	QUEEN_DIAMOND("images/cards/QD.png", Suit.DIAMONDS, Rank.QUEEN),
	QUEEN_HEART("images/cards/QH.png", Suit.HEARTS, Rank.QUEEN),
	QUEEN_SPADE("images/cards/QS.png", Suit.SPADES, Rank.QUEEN),
	
	KING_CLUBS("images/cards/KC.png", Suit.CLUBS, Rank.KING),
	KING_DIAMOND("images/cards/KD.png", Suit.DIAMONDS, Rank.KING),
	KING_HEART("images/cards/KH.png", Suit.HEARTS, Rank.KING),
	KING_SPADE("images/cards/KS.png", Suit.SPADES, Rank.KING),
	
	ACE_CLUBS("images/cards/AC.png", Suit.CLUBS, Rank.ACE),
	ACE_DIAMOND("images/cards/AD.png", Suit.DIAMONDS, Rank.ACE),
	ACE_HEART("images/cards/AH.png", Suit.HEARTS, Rank.ACE),
	ACE_SPADE("images/cards/AS.png", Suit.SPADES, Rank.ACE);
	
	

	private String imagePath;
	private Suit suit;
	private Rank rank;

	CardImage(String imagePath, Suit suit, Rank rank) {
		this.imagePath = imagePath;
		this.suit = suit;
		this.rank = rank;
		
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public Rank getRank() {
		return rank;
	}
	

	public ImageIcon getCardImage() {
		return new ImageIcon(imagePath);
	}
	
	public String getPath() {
		return imagePath;
	}
	

	
	


	
	
	
	
	
	

}
