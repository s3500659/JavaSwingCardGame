package model.bet;

import model.Player;
import model.card.Hand;

public abstract class AbstractBet implements Bet {

	private Player player;
	private int amount;
	protected BetResult result;
	
	public AbstractBet(Player player, int amount) throws java.lang.NullPointerException,
    java.lang.IllegalArgumentException {
		
		if (player == null) {
			throw new NullPointerException("Player cannot be null");
		}
		if (amount <= 0) {
			throw new IllegalArgumentException("Betting amount must greater than 0");
		}
		if (player.getPoints() < amount) {
			throw new IllegalArgumentException("Player does not have enough points to place this bet");
		}		
		
		result = BetResult.UNDETERMINED;
		this.player = player;
		this.amount = amount;
	}


	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public int getAmount() {
		return amount;
	}

	@Override
	public abstract int getMultiplier();

	@Override
	public abstract BetResult finaliseBet(Hand houseHand);

	@Override
	public BetResult getResult() {
		return result;
	}

	@Override
	public int getOutcome() {
		if (result == BetResult.UNDETERMINED || result == BetResult.DRAW) {
			return 0;
		} else if (result == BetResult.PLAYER_WIN) {
			return amount * getMultiplier();
		} else {
			return -amount;
		}
	}

	@Override
	public int getOutcome(BetResult result) {
		if (result == BetResult.UNDETERMINED || result == BetResult.DRAW) {
			return 0;
		} else if (result == BetResult.PLAYER_WIN) {
			return amount * getMultiplier();
		} else {
			return -amount;
		}
	}

	@Override
	public int compareTo(Bet bet) {
		if (this.amount == bet.getAmount()) {
			return 0;
		} else if (this.amount > bet.getAmount()) {
			return 1;
		} else {
			return -1;
		}
	}


	@Override
	public abstract String toString();
	
	

}
