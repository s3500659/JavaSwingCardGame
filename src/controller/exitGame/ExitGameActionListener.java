package controller.exitGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.dialog.exitgame.ExitGameDialog;

public class ExitGameActionListener implements ActionListener {

	private JFrame frame;

	public ExitGameActionListener(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ExitGameDialog(frame);

			}
		});

	}

}
