package client;

import javax.swing.SwingUtilities;

import model.GameEngine;
import model.GameEngineImpl;
import view.ConsoleLoggerCallback;
import view.components.MainFrame;
import view.gui.callback.GuiCallback;

public class GuiCardGame {

	private static GameEngine engine;
	private static GuiCallback view;

	public static void main(String[] args) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				engine = new GameEngineImpl();
				engine.registerCallback(new ConsoleLoggerCallback(engine));

			}
		}).start();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				view = new GuiCallback(engine);
				engine.registerCallback(view);

				new MainFrame(engine, view);

			}
		});

	}

}
