package view.placebet;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import controller.placebet.PlaceTheBetAction;
import controller.placebet.SelectedSuitToggleListener;
import model.GameEngine;
import model.card.Suit;
import view.components.MainFrame;
import view.gui.callback.GuiCallback;

@SuppressWarnings("serial")
public class PlaceSuitBetDialog extends PlaceBetDialog {
	
	private MainFrame frame;
	private GameEngine engine;
	private GuiCallback view;


	public PlaceSuitBetDialog(GameEngine engine, GuiCallback view, MainFrame frame) {
		
		super(engine, view, frame);		
		this.frame = frame;
		this.engine = engine;
		this.view = view;
		
		super.setSuitBet(true);
		createDialog();
		
	}
	
	public void createDialog() {
		JPanel topPanel = new JPanel(new GridLayout(0,2));
		add(topPanel);
		
		JLabel label = new JLabel("Select suit: ", SwingConstants.CENTER);
		topPanel.add(label);
		
		JPanel suitPanel = new JPanel();
		topPanel.add(suitPanel);
		
		JLabel betAmountLabel = new JLabel("Enter bet amount", SwingConstants.CENTER);
		topPanel.add(betAmountLabel);
		
		JTextField betAmountField = new JTextField();
		topPanel.add(betAmountField);
		
		ActionListener selectedSuitAl = new SelectedSuitToggleListener(view);
		ButtonGroup group = new ButtonGroup();
		for (Suit i : Suit.values()) {
			JToggleButton suitToggle = new JToggleButton(i.toString());
			group.add(suitToggle);
			
			suitToggle.addActionListener(selectedSuitAl);
			
			suitPanel.add(suitToggle);
		}
		
		JLabel emptyLabel = new JLabel();
		topPanel.add(emptyLabel);
		
		JPanel confirmationPanel = new JPanel();
		topPanel.add(confirmationPanel);
		
		JButton ok = new JButton("OK");
		confirmationPanel.add(ok);
		ok.addActionListener(new PlaceTheBetAction(frame, engine, view, super.isSuitBet(), betAmountField, this));

		
		JButton cancel = new JButton("Cancel");
		confirmationPanel.add(cancel);
		cancel.addActionListener(e -> {dispose();});
		
		pack();
		setLocationRelativeTo(frame);
		setVisible(true);			
	}
	
	
}
