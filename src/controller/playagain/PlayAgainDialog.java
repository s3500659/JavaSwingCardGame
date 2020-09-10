package controller.playagain;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.GameEngine;
import model.Player;
import model.bet.Bet;
import view.gui.callback.GuiCallback;

@SuppressWarnings("serial")
public class PlayAgainDialog extends JDialog {

	private GameEngine engine;
	private GuiCallback view;
	JPanel basePanel = new JPanel(new BorderLayout());
	JPanel resultPanel = new JPanel(new GridLayout(0, 1));

	public PlayAgainDialog(GameEngine engine, GuiCallback view, JFrame frame) {
		super(frame, "Play Again?", true);
		this.engine = engine;
		this.view = view;

		add(basePanel);

		basePanel.add(resultPanel, BorderLayout.CENTER);

		displayResults();
		playAgainConfirmation();

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}

	private void playAgainConfirmation() {

		JPanel mainPanel = new JPanel();
		basePanel.add(mainPanel, BorderLayout.SOUTH);

		JLabel label = new JLabel("Would you play another round?");
		mainPanel.add(label);

		JButton playAgain = new JButton("Play again");
		mainPanel.add(playAgain);

		JButton cancel = new JButton("Quit Game");
		mainPanel.add(cancel);

		cancel.addActionListener(e -> {
			System.exit(0);
		});

		playAgain.addActionListener(new PlayAgainActionListener(engine, view, this));

	}

	private void displayResults() {
		
		HashMap<Player, Integer> outcomes = new HashMap<>();
		for (Player p : engine.getAllPlayers()) {
			outcomes.put(p, p.getBet().getOutcome());
		}
		
		outcomes = sortByValue(outcomes);
		
		for (Map.Entry<Player, Integer> entry : outcomes.entrySet()) {
			if (entry.getKey().getBet() != Bet.NO_BET) {
				JPanel panel = new JPanel(new GridLayout(0, 1));
				resultPanel.add(panel);

				JLabel nameLabel = new JLabel("Player Name: " + entry.getKey().getName());
				panel.add(nameLabel);

				int pointsWon = entry.getKey().getBet().getOutcome();
				String result = null;
				if (pointsWon == 0) {
					result = "Draw";
				} else if (pointsWon > 0) {
					result = "Win";
				} else {
					result = "Loss";
				}
				JLabel resultLabel = new JLabel("Result: " + result);
				panel.add(resultLabel);

				JLabel amountWonLabel = new JLabel("Amount won: " + entry.getKey().getBet().getOutcome());
				panel.add(amountWonLabel);
				
				JLabel emptyLabel = new JLabel("--------------------------------------");
				panel.add(emptyLabel);
			}
		}
		

//		for (Player p : engine.getAllPlayers()) {
//			if (p.getBet() != Bet.NO_BET) {
//				JPanel panel = new JPanel(new GridLayout(0, 1));
//				resultPanel.add(panel);
//
//				JLabel nameLabel = new JLabel("Player Name: " + p.getName());
//				panel.add(nameLabel);
//
//				int pointsWon = p.getBet().getOutcome();
//				String result = null;
//				if (pointsWon == 0) {
//					result = "Draw";
//				} else if (pointsWon > 0) {
//					result = "Win";
//				} else {
//					result = "Loss";
//				}
//				JLabel resultLabel = new JLabel("Result: " + result);
//				panel.add(resultLabel);
//
//				JLabel amountWonLabel = new JLabel("Amount won: " + p.getBet().getOutcome());
//				panel.add(amountWonLabel);
//				
//				JLabel emptyLabel = new JLabel("--------------------------------------");
//				panel.add(emptyLabel);
//			}
//		}
	}
	
	// function to sort hashmap by values 
    private HashMap<Player, Integer> sortByValue(HashMap<Player, Integer> hm) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<Player, Integer> > list = 
               new LinkedList<Map.Entry<Player, Integer> >(hm.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new CompareValues());
          
        // put data from sorted list to hashmap  
        HashMap<Player, Integer> temp = new LinkedHashMap<Player, Integer>(); 
        for (Map.Entry<Player, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 

}
