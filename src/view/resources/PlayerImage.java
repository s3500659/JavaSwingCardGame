package view.resources;

import javax.swing.Icon;
import javax.swing.ImageIcon;

// Image size must be exactly 80 px
public enum PlayerImage {
	
	PLAYER_1("images/Player 1.png"),
	PLAYER_2("images/Player 2.png"),
	PLAYER_3("images/Player 3.png"),
	PLAYER_4("images/Player 4.png"),
	PLAYER_5("images/Player 5.png"),
	PLAYER_6("images/Player 6.png"),
	DEFAULT("images/Default.png");
	
	private String imageLocation;

	PlayerImage(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	
	public String getImageLocation() {
		return imageLocation;
	}
	
	public Icon createPlayerAvatar() {
		return new ImageIcon(imageLocation);
	}
	

}
