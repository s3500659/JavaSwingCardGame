package client.test;

import javax.swing.SwingUtilities;

import model.GameEngine;
import model.GameEngineImpl;
import model.PlayerImpl;
import view.ConsoleLoggerCallback;
import view.components.MainFrame;
import view.gui.callback.GuiCallback;

public class GuiCardGameTestClient {

	public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				GameEngine engine = new GameEngineImpl();
				engine.registerCallback(new ConsoleLoggerCallback(engine));
				
				GuiCallback view = new GuiCallback(engine);
				engine.registerCallback(view);
				
				new MainFrame(engine, view);		
				
				engine.addPlayer(new PlayerImpl("p1", "ash", 1000));
				engine.addPlayer(new PlayerImpl("p2", "misty", 1000));


				
			}
		});

	}

}
