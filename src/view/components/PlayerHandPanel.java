package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.GameEngine;
import model.Player;
import model.card.Card;
import view.gui.callback.GuiCallback;
import view.resources.CardImage;
import view.resources.Properties;
import view.resources.cardImagePanel;

@SuppressWarnings("serial")
public class PlayerHandPanel extends JPanel implements PropertyChangeListener {

	private JFrame frame;
	private Collection<JPanel> cardPanels = new ArrayList<>();
	private HashMap<Player, Card> bustedCard = new HashMap<>();
	private GameEngine engine;

	public PlayerHandPanel(JFrame frame, GuiCallback view, GameEngine engine) {

		this.frame = frame;
		this.engine = engine;

		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		setBorder(border);
		setLayout(new GridLayout(0, 5));
		setBackground(Color.BLACK);

		view.addPropertyChangeListener(this);
		setVisible(true);

	}

	private void addCard(CardImage card) {

		ImageIcon icon = new ImageIcon(card.getPath());
		Image image = icon.getImage();
		JPanel cardImagePanel = new cardImagePanel(image);
		add(cardImagePanel);
		cardPanels.add(cardImagePanel);

//		frame.pack();
		revalidate();

	}

	private void addCard(CardImage card, String label) {

		JPanel topPanel = new JPanel(new BorderLayout());
		add(topPanel);

		ImageIcon icon = new ImageIcon(card.getPath());
		Image image = icon.getImage();
		JPanel cardImagePanel = new cardImagePanel(image);
		topPanel.add(cardImagePanel, BorderLayout.CENTER);

		JLabel textLabel = new JLabel(label, SwingConstants.CENTER);
		textLabel.setOpaque(true);
		textLabel.setBackground(Color.RED);
		topPanel.add(textLabel, BorderLayout.SOUTH);

		cardPanels.add(topPanel);

		frame.pack();
		revalidate();

	}

	private void updateHand(Player player) {
		for (JPanel panel : cardPanels) {
			remove(panel);
		}
		cardPanels.clear();
		frame.pack();
		revalidate();

		Collection<Card> cards = player.getHand().getCards();
		if (cards != null) {
			for (Card card : cards) {
				for (CardImage image : CardImage.values()) {
					if (card.getSuit() == image.getSuit()) {
						if (card.getRank() == image.getRank()) {
							addCard(image);
							//frame.pack();
							revalidate();

						}
					}
				}
			}
		}

		Card bustedCard = this.bustedCard.get(player);
		if (bustedCard != null) {
			for (CardImage image : CardImage.values()) {
				if (bustedCard.getSuit() == image.getSuit()) {
					if (bustedCard.getRank() == image.getRank()) {
						addCard(image, String.format("Busted Card - Total Score: %d", player.getHand().getScore()));
						frame.pack();
						revalidate();

					}

				}
			}
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName() == Properties.PLAYER_CARD.getProperty()) {

			Card card = (Card) evt.getNewValue();

			for (CardImage i : CardImage.values()) {

				if (card.getSuit() == i.getSuit()) {
					if (card.getRank() == i.getRank()) {
						addCard(i);

					}
				}
			}
		}

		if (evt.getPropertyName() == Properties.PLAYER_BUST.getProperty()) {
			bustedCard.put((Player) evt.getOldValue(), (Card) evt.getNewValue());
			updateHand((Player) evt.getOldValue());
		}

		if (evt.getPropertyName() == Properties.UPDATE_PLAYER_HAND.getProperty()) {
			Player player = (Player) evt.getNewValue();
			updateHand(player);
		}

		if (evt.getPropertyName() == Properties.CLEAR_PLAYERS_HAND_PANEL.getProperty()) {
			for (Player p : engine.getAllPlayers()) {
				bustedCard.clear();
				updateHand(p);
			}
		}

	}

}
