package model.bet;

import model.Player;
import model.card.Hand;

public class ScoreBetImpl extends AbstractBet implements ScoreBet {
	

	public ScoreBetImpl(Player player, int amount) throws java.lang.NullPointerException,
    java.lang.IllegalArgumentException {
		super(player, amount);
	}

	@Override
	public int getMultiplier() {
		return 2;
	}

	@Override
	public BetResult finaliseBet(Hand houseHand) {
		
		int houseScore = houseHand.getScore();
		int playerScore = getPlayer().getHand().getScore();
		

		if (houseScore > Hand.BUST_SCORE) {
			result = BetResult.PLAYER_WIN;	
		} else if (playerScore > Hand.BUST_SCORE) {
			result = BetResult.PLAYER_LOSS;
		} else if (playerScore == houseScore) {
			result = BetResult.DRAW;
		} else if (playerScore > houseScore) {
			result = BetResult.PLAYER_WIN;
		} else {
			result = BetResult.PLAYER_LOSS;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("Score Bet for %d", getAmount());
	}
	
	
	
	

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
//		return 2;
//	}
//
//	@Override
//	public BetResult finaliseBet(Hand houseHand) {
//		int houseScore = houseHand.getScore();
//		int playerScore = this.getPlayer().getHand().getScore();
//		if (houseScore > Hand.BUST_SCORE) {
//			return result = BetResult.PLAYER_WIN;	
//		} else if (playerScore > Hand.BUST_SCORE) {
//			return result = BetResult.PLAYER_LOSS;
//		} else if (playerScore == houseScore) {
//			return result = BetResult.DRAW;
//		} else if (playerScore > houseScore) {
//			return result = BetResult.PLAYER_WIN;
//		} else {
//			return result = BetResult.PLAYER_LOSS;
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
//		if (this.amount == bet.getAmount()) {
//			return 0;
//		} else if (this.amount > bet.getAmount()) {
//			return 1;
//		} else {
//			return -1;
//		}
//	}
//
//

}
