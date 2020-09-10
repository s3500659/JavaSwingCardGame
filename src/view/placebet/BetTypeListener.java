//package view.placebet;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import view.gui.callback.GuiCallback;
//
//public class BetTypeListener implements ActionListener {
//
//	private String SCORE_BET;
//	private String SUIT_BET;
//	private GuiCallback view;
//	
//	public BetTypeListener(String SCORE_BET, String SUIT_BET, GuiCallback view, PlaceScoreBetDialog placeBetDialog) {
//		this.SCORE_BET = SCORE_BET;
//		this.SUIT_BET = SUIT_BET;
//		this.view = view;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getActionCommand() == SCORE_BET) {
//			view.putPlayerBetType(SCORE_BET);
//		} else if (e.getActionCommand() == SUIT_BET) {
//			view.putPlayerBetType(SUIT_BET);
//
//		}
//	}
//
//}
