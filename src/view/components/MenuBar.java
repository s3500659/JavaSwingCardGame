package view.components;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.about.AboutDialogActionListener;
import controller.addPlayer.AddPlayerActionListener;
import controller.dealcards.DealCardsActionListener;
import controller.dealspeed.DealSpeedActionListener;
import controller.exitGame.ExitGameActionListener;
import controller.placebet.PlaceScoreBetActionListener;
import controller.placebet.PlaceSuitBetActionListener;
import controller.removeplayer.RemovePlayerActionListener;
import model.GameEngine;
import view.gui.callback.GuiCallback;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	private GuiCallback view;

	public MenuBar(MainFrame frame, GuiCallback view, GameEngine engine) {
		
		this.view = view;
		
		// file menu
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

//		// file - new game
//		JMenuItem newGame = new JMenuItem("New Game", KeyEvent.VK_N);
//		file.add(newGame);
//
//		// file - save game
//		JMenuItem saveGame = new JMenuItem("Save Game", KeyEvent.VK_S);
//		file.add(saveGame);
//
//		// file - load saved game
//		JMenuItem loadGame = new JMenuItem("Load Game", KeyEvent.VK_L);
//		file.add(loadGame);

		// file menu items
		JMenuItem exitGame = new JMenuItem("Exit Game", KeyEvent.VK_X);
		exitGame.addActionListener(new ExitGameActionListener(frame));
		file.add(exitGame);

		// game menu
		JMenu game = new JMenu("Game");
		game.setMnemonic(KeyEvent.VK_G);

		// game menu item
		JMenuItem addPlayer = new JMenuItem("Add Player", KeyEvent.VK_A);
		addPlayer.addActionListener(new AddPlayerActionListener(engine, view, frame));
		
		JMenuItem removePlayer = new JMenuItem("Remove Player", KeyEvent.VK_R);
		removePlayer.addActionListener(new RemovePlayerActionListener(engine, view, frame));
		
		JMenuItem scoreBet = new JMenuItem("Place Score Bet", KeyEvent.VK_P);
		scoreBet.addActionListener(new PlaceScoreBetActionListener(engine, view, frame));
		
		JMenuItem suitBet = new JMenuItem("Place Suit Bet", KeyEvent.VK_S);
		suitBet.addActionListener(new PlaceSuitBetActionListener(engine, view, frame));

		
		JMenuItem dealCards = new JMenuItem("Deal Cards", KeyEvent.VK_D);
		dealCards.addActionListener(new DealCardsActionListener(engine, view, frame));
		
		JMenu dealSpeed = new JMenu("Set Deal Speed");
		
		// JMenuItem betOutcome = new JMenuItem("Bet Outcome", KeyEvent.VK_B);

		game.add(addPlayer);
		game.add(removePlayer);
		game.add(scoreBet);
		game.add(suitBet);
		game.add(dealCards);
		// game.add(betOutcome);
		game.add(dealSpeed);
		
		// deal speed items
		setDealSpeedItems(dealSpeed);
		

		// help menu
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);

		// Game rules
//		JMenuItem gameRules = new JMenuItem("Game Rules", KeyEvent.VK_R);
//		help.add(gameRules);

		// feedback item
//		JMenuItem feedback = new JMenuItem("Send Feedback", KeyEvent.VK_F);
//		help.add(feedback);

		// help menu items
		JMenuItem about = new JMenuItem("About", KeyEvent.VK_A);
		help.add(about);

		// action listener for about button
		about.addActionListener(new AboutDialogActionListener(frame));

		// add to tool bar
		add(file);
		add(game);
		add(help);
	}
	
	private void setDealSpeedItems(JMenu menu) {
		
		final String SLOW = "Slow", DEF = "Default", FAST = "Fast";
		
		JMenuItem slow = new JMenuItem(SLOW);
		JMenuItem def = new JMenuItem(DEF);
		JMenuItem fast = new JMenuItem(FAST);
		
		menu.add(slow);
		menu.add(def);
		menu.add(fast);
		
		slow.setActionCommand(SLOW);
		def.setActionCommand(DEF);
		fast.setActionCommand(FAST);
		
		ActionListener al = new DealSpeedActionListener(SLOW, DEF, FAST, view);
		
		slow.addActionListener(al);
		def.addActionListener(al);
		fast.addActionListener(al);
		
		
		
	}

}
