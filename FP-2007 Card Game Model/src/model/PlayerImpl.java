package model;

import model.bet.Bet;
import model.bet.BetResult;
import model.card.Hand;
import model.card.HandImpl;

public class PlayerImpl implements Player {
	
	private final String ID;
	private final String NAME;
	private int points;
	private Bet bet = Bet.NO_BET;
	private Hand hand = new HandImpl();
	
	public PlayerImpl(String id, String name, int points) throws java.lang.NullPointerException, 
								java.lang.IllegalArgumentException {
		if (id == null) {
			throw new NullPointerException("Player id cannot be null");
		}
		if (name == null) {
			throw new NullPointerException("Player name cannot be null");
		}
		if (id.equals("")) {
			throw new IllegalArgumentException("Id cannot be empty");
		}
		if (name.equals("")) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		if (points <= 0) {
			throw new IllegalArgumentException("Player starting point must be greater than 0");
		}

		this.ID = id;
		this.NAME = name;
		this.points = points;	
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public int getTotalPoints() {
		return points + bet.getAmount();
	}

	@Override
	public void assignBet(Bet bet) {
		this.bet = bet;
		points -= bet.getAmount();
	}

	@Override
	public Bet getBet() {
		return bet;
	}

	@Override
	public Hand getHand() {
		return hand;
	}

	@Override
	public void applyBetResult(Hand houseHand) {
		if (houseHand == null) {
			return;
		}
		BetResult result = bet.finaliseBet(houseHand);
		if (result == BetResult.PLAYER_WIN) {
			points = getTotalPoints() + (bet.getAmount() * bet.getMultiplier());
		} 
//		else if (result == BetResult.PLAYER_LOSS ) {
//			points = getTotalPoints() - (bet.getAmount() * bet.getMultiplier());
//		}		
	}

	@Override
	public void resetBet() {
		if (bet != Bet.NO_BET) {
			bet = Bet.NO_BET;
			points += bet.getAmount();
		}
	}

	@Override
	public String toString() {
		return String.format("Player id=%s, name=%s, points=%d, %s, %s", ID, NAME, points, bet.toString(), 
				hand.toString());
	}
}
