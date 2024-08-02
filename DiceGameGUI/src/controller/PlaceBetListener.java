package controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.DiceAppGUI;
import view.StatusBar;
import view.ToolBar;

public class PlaceBetListener extends AbstractMenuListener {
	
	public PlaceBetListener(DiceAppGUI menu, GameEngine game, ToolBar tools, StatusBar status) {
		super(menu, game, tools, status);
	}
	
	@Override
    public void actionPerformed(ActionEvent event)
    {
      placeBet();
    }
	

	/**
	 * Places a bet for the user that's currently selected in the combobox
	 * after they press OK and enter a valid bet (an integer & sufficient points)
	 * Can not place a bet while the dice is rolling.
	 * If the bet is not an int or the player does not have a sufficient number of points
	 * no bet is made.
	 */
	public void placeBet()
	{
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
		
		if(!menu.getCurrentlyRolling()) {
			
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			
			int index = tools.returnCurrentIndex();
			Player p = tools.returnCurrentPlayer();
			
			JLabel name = new JLabel("Set a bet for: " + p.getPlayerName() + "\n");
			JLabel currentPoints = new JLabel("Current amount of points: " + p.getPoints() + "\n");
			JTextField betInput = new JTextField(Integer.toString(p.getPoints()));
			
			panel.add(name);
			panel.add(currentPoints);
			panel.add(betInput);
			
			int placeBet = JOptionPane.showConfirmDialog(panel, panel, "Place Bet", JOptionPane.OK_CANCEL_OPTION);
			
			switch (placeBet) {
			case JOptionPane.CANCEL_OPTION:
				System.out.println("No bet was made.");
				break;
			case JOptionPane.OK_OPTION:
	            try {
	            	int betAmount = Integer.parseInt(betInput.getText());
	            	if(betAmount <= p.getPoints()) {
		            	menu.placeBet(p, index, betAmount);
			            System.out.println("Successfully bet " + p.getBet() + 
			            		" to "+ p.getPlayerName());
			            status.updateStatus("Successfully bet " + p.getBet() + " to " + p.getPlayerName());
	            	} else {
	            		JOptionPane.showMessageDialog(panel, "Insufficient points.");
	            		System.out.println("Invalid bet");
	            	}
	            } catch (NumberFormatException ex) {
	            	System.out.println("Invalid number was entered.");
	    			JOptionPane.showMessageDialog(panel, "Not a valid number.");
	            }
	            menu.updateSummary();
	            break;
			}
		
		} else {
			
			JOptionPane.showMessageDialog(panel, "Can not place a bet while dice is rolling.");
		}
		
	}

}
