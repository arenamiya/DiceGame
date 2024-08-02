package controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.DiceAppGUI;
import view.StatusBar;
import view.ToolBar;

public class RemoveBetListener extends AbstractMenuListener {
	
	public RemoveBetListener(DiceAppGUI menu,GameEngine game, ToolBar tools, StatusBar status) {
		super(menu, game, tools, status);
	}
	
	@Override
    public void actionPerformed(ActionEvent event)
    {
      cancelBet();
    }
	
	/**
	 * Cancels a bet for the player that is currently selected in the combobox
	 * Dialog panel appears if user tries to cancel a bet while the dice is rolling
	 */
	public void cancelBet()
	{
		JPanel panel = new JPanel();
		
		if(!menu.getCurrentlyRolling()) {
			
			int index = tools.returnCurrentIndex();
			Player p = tools.returnCurrentPlayer();
			menu.resetBet(index);
			
			System.out.println("Bet was reset for Player " + p.getPlayerName() + " to " + p.getBet());
			status.updateStatus("Bet was reset for Player " + p.getPlayerName());
			JOptionPane.showMessageDialog(panel, "Bet was reset for Player " + p.getPlayerName());
			menu.updateSummary();
		
		} else  {
			
			JOptionPane.showMessageDialog(panel, "Can not cancel bet while dice is rolling.");
			
		}
	}

}
