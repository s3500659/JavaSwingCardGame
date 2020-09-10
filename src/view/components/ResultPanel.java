package view.components;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class ResultPanel extends JPanel {

	private int roundsPlayed = 10;

	public ResultPanel() {
		setLayout(new GridLayout(0, 1));

		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

		for (int i = 0; i < roundsPlayed; i++) {

			JPanel roundResultPanel = new JPanel();
			roundResultPanel.setBorder(border);

			JLabel roundLabel = new JLabel("Round " + (i + 1) + ":");
			roundResultPanel.add(roundLabel);

			JLabel resultLabel = new JLabel("results...");
			roundResultPanel.add(resultLabel);

			add(roundResultPanel);

		}

	}

}
