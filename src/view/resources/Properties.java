package view.resources;

public enum Properties {

	STATUS_1("Status 1 update"), 
	NEW_PLAYER("New player"), 
	REMOVE_PLAYER("Remove player"), 
	UPDATE_BET("Update bet"),
	SCORE_BET("Score bet"), 
	SUIT_BET("Suit bet"), 
	PLAYER_CARD("Player card"),
	UPDATE_PLAYER_HAND("Update player's hand"), 
	PLAYER_BUST("Player bust"),
	CLEAR_PLAYERS_HAND_PANEL("Clear players hand panel"), 
	NEW_ROUND("New round"),
	DISABLE_GAME_ITEMS("Disable game items"), 
	ENABLE_GAME_ITEMS("Enable game items");

	private String property;

	Properties(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

}
