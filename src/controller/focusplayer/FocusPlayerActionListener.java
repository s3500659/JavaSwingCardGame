package controller.focusplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import model.Player;
import view.gui.callback.GuiCallback;

public class FocusPlayerActionListener implements ActionListener {

	private GuiCallback view;
	private Player player;

	public FocusPlayerActionListener(GuiCallback view, Player player) {
		this.view = view;
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JToggleButton source = (JToggleButton) e.getSource();

		if (source.isSelected()) {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					view.setFocusedPlayer(player);
					view.setStatus1(player);
					view.updatePlayerHand(player);
				}
			});
		}

	}

}
