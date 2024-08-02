package controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.interfaces.GameEngine;
import view.DiceAppGUI;
import view.StatusBar;
import view.ToolBar;

public class RemovePlayerListener extends AbstractMenuListener {
	
	public RemovePlayerListener(DiceAppGUI menu, GameEngine game, ToolBar tools, StatusBar status) {
		super(menu, game, tools, status);
	}
	
	@Override
    public void actionPerformed(ActionEvent event)
    {
      removePlayer();
    }
	
	public void removePlayer()
	{
		
		JPanel panel = new JPanel();
		
		int index = tools.returnCurrentIndex();
		String id = tools.returnPlayerID(index);
		String name = game.getPlayer(id).getPlayerName();
		
		boolean remPlayer = game.removePlayer(game.getPlayer(id));
		
		if(!remPlayer) {
			
			System.out.println("Could not remove player from " + index);
			JOptionPane.showMessageDialog(panel, "Could not remove player", "Error", JOptionPane.ERROR_MESSAGE);
			
		} else {
			
			menu.updateSummary();
			menu.removePlayerInfo(index);
			
			System.out.println("Successfully removed player.");
			status.updateStatus("Successfully removed Player " + name);
			JOptionPane.showMessageDialog(panel, "Successfully removed Player " + name);
			
		}
		
	}
	
	
}
