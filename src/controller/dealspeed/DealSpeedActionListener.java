package controller.dealspeed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import view.gui.callback.GuiCallback;

public class DealSpeedActionListener implements ActionListener {

	private String SLOW;
	private String DEF;
	private String FAST;
	private GuiCallback view;

	public DealSpeedActionListener(String SLOW, String DEF, String FAST, GuiCallback view) {
		this.SLOW = SLOW;
		this.DEF = DEF;
		this.FAST = FAST;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {

		JMenuItem source = (JMenuItem) evt.getSource();

		int slow = 1500;
		int def = 500;
		int fast = 150;

		if (source.getActionCommand() == SLOW) {
			view.setDealSpeed(slow);
		} else if (source.getActionCommand() == DEF) {
			view.setDealSpeed(def);
		} else if (source.getActionCommand() == FAST) {
			view.setDealSpeed(fast);
		} else {
			view.setDealSpeed(def);
		}

	}

}
