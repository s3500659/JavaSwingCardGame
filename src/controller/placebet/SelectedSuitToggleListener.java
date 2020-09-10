package controller.placebet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import model.card.Suit;
import view.gui.callback.GuiCallback;

public class SelectedSuitToggleListener implements ActionListener {

	private GuiCallback view;

	public SelectedSuitToggleListener(GuiCallback view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {

		JToggleButton button = (JToggleButton) evt.getSource();
		String bettingSuit = button.getText();
		if (bettingSuit.equals(Suit.CLUBS.toString())) {
			view.setBettingSuit(view.getFocusedPlayer(), Suit.CLUBS);
		} else if (bettingSuit.equals(Suit.DIAMONDS.toString())) {
			view.setBettingSuit(view.getFocusedPlayer(), Suit.DIAMONDS);
		} else if (bettingSuit.equals(Suit.HEARTS.toString())) {
			view.setBettingSuit(view.getFocusedPlayer(), Suit.HEARTS);
		} else {
			view.setBettingSuit(view.getFocusedPlayer(), Suit.SPADES);
		}
	}

}
