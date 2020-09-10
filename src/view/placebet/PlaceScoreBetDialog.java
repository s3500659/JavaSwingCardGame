package view.placebet;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.placebet.PlaceTheBetAction;
import model.GameEngine;
import model.Player;
import view.components.MainFrame;
import view.gui.callback.GuiCallback;

@SuppressWarnings("serial")
public class PlaceScoreBetDialog extends PlaceBetDialog {

	private MainFrame frame;
	private GameEngine engine;
	private GuiCallback view;

	public PlaceScoreBetDialog(MainFrame frame, GameEngine engine, GuiCallback view, Player focusedPlayer) {
		super(engine, view, frame);
		
		this.frame = frame;
		this.engine = engine;
		this.view = view;

		createDialog();

	}

	@Override
	public void createDialog() {
		JPanel topPanel = new JPanel(new GridLayout(0, 2));
		add(topPanel);

		JLabel bettingAmountLabel = new JLabel("Enter betting amount");
		topPanel.add(bettingAmountLabel);

		JTextField bettingAmountField = new JTextField();
		topPanel.add(bettingAmountField);

		JButton ok = new JButton("OK");
		topPanel.add(ok);

		ok.addActionListener(
				new PlaceTheBetAction(frame, engine, view, bettingAmountField, this));

		JButton cancel = new JButton("Cancel");
		topPanel.add(cancel);

		cancel.addActionListener(e -> {
			dispose();
		});

		setLocationRelativeTo(frame);
		pack();
		setVisible(true);

	}

}
