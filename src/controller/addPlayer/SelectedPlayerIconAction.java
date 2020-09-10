package controller.addPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import view.gui.callback.GuiCallback;
import view.resources.PlayerImage;

public class SelectedPlayerIconAction implements ActionListener {

	private GuiCallback view;
	private PlayerImage image;

	public SelectedPlayerIconAction(GuiCallback view, PlayerImage image) {
		this.view = view;
		this.image = image;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				view.setPlayerIcon(image);
				
			}
		});

	}

}
