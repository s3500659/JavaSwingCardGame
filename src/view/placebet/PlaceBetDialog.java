package view.placebet;

import javax.swing.JDialog;
import model.GameEngine;
import view.components.MainFrame;
import view.gui.callback.GuiCallback;

@SuppressWarnings("serial")
public abstract class PlaceBetDialog extends JDialog {

	private boolean isSuitBet = false;


	public PlaceBetDialog(GameEngine engine, GuiCallback view, MainFrame frame) {
		super(frame, "Place Bet", true);


	}

	public abstract void createDialog();

	public boolean isSuitBet() {
		return isSuitBet;
	}

	public void setSuitBet(boolean isSuitBet) {
		this.isSuitBet = isSuitBet;
	}



}
