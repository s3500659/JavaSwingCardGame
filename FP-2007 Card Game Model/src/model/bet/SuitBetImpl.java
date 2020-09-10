package model.bet;

import model.Player;
import model.card.Hand;
import model.card.Suit;
import tools.Tools;

public class SuitBetImpl extends AbstractBet implements SuitBet, Tools {
	
	private Suit suit;
	
	public SuitBetImpl(Player player, int amount, Suit suit) throws java.lang.NullPointerException,
    java.lang.IllegalArgumentException {
		super(player, amount);
		this.suit = suit;
	}

	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public int getMultiplier() {
		return 4;
	}

	@Override
	public BetResult finaliseBet(Hand houseHand) {
		int houseSuitCount = houseHand.getSuitCount(suit);
		int playerSuitCount = getPlayer().getHand().getSuitCount(suit);
		if (playerSuitCount <= houseSuitCount) {
			return result = BetResult.PLAYER_LOSS;
		} else {
			return result = BetResult.PLAYER_WIN;
		}
	}
	
	@Override
	public String toString() {
		return String.format("Suit bet for %d on %s", getAmount(), Tools.capitalise(suit));
	}

	
	
	
	
	
	
	

//	public SuitBetImpl(Player player, int amount, Suit suit) throws java.lang.NullPointerException, 
//	java.lang.IllegalArgumentException {
//		if (player == null) {
//			throw new NullPointerException("Player cannot be null");
//		}
//		if (amount <= 0) {
//			throw new IllegalArgumentException("Betting amount must be greater than 0");
//		}
//		if (player.getPoints() < amount) {
//			throw new IllegalArgumentException("Player does not have enough points to place this bet");
//		}
//		
//		this.player = player;
//		this.amount = amount;
//		this.suit = suit;
//	}
//
//	@Override
//	public Player getPlayer() {
//		return player;
//	}
//
//	@Override
//	public int getAmount() {
//		return amount;
//	}
//
//	@Override
//	public int getMultiplier() {
//		return 4;
//	}
//
//	@Override
//	public BetResult finaliseBet(Hand houseHand) {
//		int houseSuitCount = houseHand.getSuitCount(suit);
//		int playerSuitCount = this.player.getHand().getSuitCount(suit);
//		if (playerSuitCount <= houseSuitCount) {
//			return result = BetResult.PLAYER_LOSS;
//		} else {
//			return result = BetResult.PLAYER_WIN;
//		}
//	}
//
//	@Override
//	public BetResult getResult() {
//		return result;
//	}
//
//	@Override
//	public int getOutcome() {
//		if (result == BetResult.UNDETERMINED) {
//			return 0;
//		} else if (result == BetResult.PLAYER_WIN) {
//			return amount * getMultiplier();
//		} else {
//			return -(amount * getMultiplier());
//		}
//	}
//
//	@Override
//	public int getOutcome(BetResult result) {
//		if (result == BetResult.UNDETERMINED || result == BetResult.DRAW) {
//			return 0;
//		} else if (result == BetResult.PLAYER_WIN) {
//			return amount * getMultiplier();
//		} else {
//			return -(amount * getMultiplier());
//		}
//	}
//
//	@Override
//	public int compareTo(Bet bet) {
//		int otherBet = bet.getAmount();
//		if (amount == otherBet) {
//			return 0;
//		} else if (amount > otherBet) {
//			return 1;
//		} else {
//			return -1;
//		}
//	}
//
//	@Override
//	public Suit getSuit() {
//		return suit;
//	}
//

	
	
	
	
	
	

}
