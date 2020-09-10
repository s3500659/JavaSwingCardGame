package controller.about;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.dialog.about.AboutDialog;

public class AboutDialogActionListener implements ActionListener {
	
	private JFrame frame;
	
	public AboutDialogActionListener(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new AboutDialog(frame, "About", true);
				
			}
		});
		
	}

}
