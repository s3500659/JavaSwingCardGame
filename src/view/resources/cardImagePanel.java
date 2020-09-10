package view.resources;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class cardImagePanel extends JPanel {

	private Image image;

	public cardImagePanel(Image image) {
		
		this.image = image;
		setBackground(Color.BLACK);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public Dimension preferredSize() {
		return new Dimension(220, 200);
	}

}
