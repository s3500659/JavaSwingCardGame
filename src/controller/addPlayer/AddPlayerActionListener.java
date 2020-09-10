package controller.addPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.GameEngine;
import view.dialog.addplayer.AddPlayerDialog;
import view.gui.callback.GuiCallback;

public class AddPlayerActionListener implements ActionListener {

	private GameEngine engine;
	private GuiCallback view;
	private JFrame frame;

	public AddPlayerActionListener(GameEngine engine, GuiCallback view, JFrame frame) {
		this.engine = engine;
		this.view = view;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new AddPlayerDialog(engine, view, frame);
			}
		});

	}

}
