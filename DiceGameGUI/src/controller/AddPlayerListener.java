package controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AbstractMenuListener;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.DiceAppGUI;
import view.StatusBar;
import view.ToolBar;

public class AddPlayerListener extends AbstractMenuListener {

	public AddPlayerListener(DiceAppGUI menu, GameEngine game, ToolBar tools,
			StatusBar status) {
		super(menu, game, tools, status);
	}
	
	@Override
    public void actionPerformed(ActionEvent event)
    {
      addPlayer();
    }
	
	/**
	 * Adds a player onto the game if the user presses OK and a valid number for points is entered
	 */
	public void addPlayer() 
	{
		JPanel pane = new JPanel();
		
		pane.setLayout(new GridLayout(2,2));
		
		pane.add(new JLabel("Name: "));
		JTextField nameInput = new JTextField("NAME");
		pane.add(nameInput);
		
		pane.add(new JLabel("Points: "));
		JTextField pointInput = new JTextField("500");
		pane.add(pointInput);
		
		int addPlayer = JOptionPane.showConfirmDialog(pane, pane, "Add Player", JOptionPane.OK_CANCEL_OPTION);

        String id = Integer.toString(menu.getNumID());
        
		switch (addPlayer) {
			case JOptionPane.CANCEL_OPTION:
				System.out.println("No player was added.");
				break;
			case JOptionPane.OK_OPTION:
				String name = nameInput.getText();
	            try {
	            	int point = Integer.parseInt(pointInput.getText());
	            	Player p = new SimplePlayer(id,name,point);
		            menu.addPlayerInfo(p, id, point);
		            System.out.println("Successfully added " + p.getPlayerId() + 
		            		" "+ p.getPlayerName()
		            		+ " to Players");
	            } catch (NumberFormatException ex) {
	            	System.out.println("Not valid number entered.");
	    			JOptionPane.showMessageDialog(pane, "Not a valid number.");
	            } catch (Exception e) {
	            	e.printStackTrace();
	            }
	            menu.updateSummary();
	            break;
		}
	}

}
