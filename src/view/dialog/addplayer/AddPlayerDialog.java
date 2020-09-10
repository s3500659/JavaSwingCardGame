package view.dialog.addplayer;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import controller.addPlayer.CreatePlayerAction;
import controller.addPlayer.SelectedPlayerIconAction;
import model.GameEngine;
import view.gui.callback.GuiCallback;
import view.resources.PlayerImage;

@SuppressWarnings("serial")
public class AddPlayerDialog extends JDialog {

	public AddPlayerDialog(GameEngine engine, GuiCallback view, JFrame frame) {
		super(frame, "Add Player", true);

		setLayout(new GridLayout(0, 2));

		JLabel name = new JLabel("Enter player name: ");
		add(name);

		JTextField nameField = new JTextField();
		add(nameField);

		JLabel id = new JLabel("Enter player id: ");
		add(id);

		JTextField idField = new JTextField();
		add(idField);

		JLabel points = new JLabel("Enter player starting points: ");
		add(points);

		JTextField pointsField = new JTextField();
		add(pointsField);

		JLabel playerIcon = new JLabel("Select a player icon: ");
		add(playerIcon);

		JPanel playerIconPanel = new JPanel();
		add(playerIconPanel);

		ButtonGroup playerIconGroup = new ButtonGroup();

		
		for (PlayerImage i : PlayerImage.values()) {
			AbstractButton playerIconButton = new JToggleButton(i.createPlayerAvatar());
			playerIconPanel.add(playerIconButton);
			playerIconGroup.add(playerIconButton);

			playerIconButton.addActionListener(new SelectedPlayerIconAction(view, i));

		}

		JButton createPlayer = new JButton("Create Player");
		add(createPlayer);

		JButton cancel = new JButton("Cancel");
		add(cancel);

		createPlayer.addActionListener(new CreatePlayerAction(engine, nameField, idField, pointsField, this));

		cancel.addActionListener(e -> {
			this.dispose();
		});
		
		setMinimumSize(new Dimension(800,600));
		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}

}
