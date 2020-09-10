package view.components;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import view.gui.callback.GuiCallback;
import view.resources.Properties;

@SuppressWarnings("serial")
public class StatusBar extends JPanel implements PropertyChangeListener {

	private JLabel status1, status2;
	private GuiCallback view;

	public StatusBar(GuiCallback view) {
		this.view = view;

		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		setLayout(new GridLayout(1, 0));

		this.status1 = new JLabel("No player selected", SwingConstants.LEFT);
		status1.setBorder(border);

		this.status2 = new JLabel("Current round # " + view.getRoundNumber(), SwingConstants.RIGHT);
		status2.setBorder(border);

		add(status1);
		add(status2);

		view.addPropertyChangeListener(this);
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == Properties.STATUS_1.getProperty()) {
			status1.setText((String) evt.getNewValue());
		}
		
		if (evt.getPropertyName() == Properties.NEW_ROUND.getProperty()) {
			status2.setText("Current round # " + view.getRoundNumber());
		}

	}

}
