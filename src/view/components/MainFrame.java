package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import model.GameEngine;
import view.gui.callback.GuiCallback;
import view.resources.Properties;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements PropertyChangeListener {
	
	private ToolBar toolBar;
	private StatusBar statusBar;
	private JMenuBar menuBar;

	public MainFrame(GameEngine engine, GuiCallback view) {
		super("Pokemon Black Jack");

		menuBar = new MyMenuBar(this, view, engine);
		toolBar = new ToolBar(engine, view, this);
		statusBar = new StatusBar(view);
		PlayerPanel playerPanel = new PlayerPanel(engine, view, this);
		PlayerHandPanel playerHandPanel = new PlayerHandPanel(this, view, engine);
		//ResultPanel resultPanel = new ResultPanel();

		setLayout(new BorderLayout());
		setJMenuBar(menuBar);
		
		add(toolBar, BorderLayout.NORTH);
		add(statusBar, BorderLayout.SOUTH);
		//add(resultPanel, BorderLayout.EAST);
		add(playerPanel, BorderLayout.WEST);
		add(playerHandPanel, BorderLayout.CENTER);



		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1000, 400));
		pack();
		setVisible(true);
		
		view.addPropertyChangeListener(this);
		

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == Properties.CLEAR_PLAYERS_HAND_PANEL.getProperty()) {
			revalidate();
			pack();
		}
	}
	
//	public ToolBar getToolBar() {
//		return toolBar;
//	}
//	
//	// Hi Ross - I cannot access the public methods in my menuBar for some reason. I'm unable to figure out why.
//	// I'm unable to disable and enable my menuItems while dealing to player/house because i'm
//	// unable to access the methods residing in this class.
//	public JMenuBar getMyMenuBar() {
//		return menuBar;
//	}

}
