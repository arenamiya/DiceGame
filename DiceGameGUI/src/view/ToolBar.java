package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;


import controller.AddPlayerListener;
import controller.PlaceBetListener;
import controller.RemoveBetListener;
import controller.RemovePlayerListener;
import controller.RollPlayerListener;
import controller.SwitchDiceListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
	
	private JButton addPlayer, removePlayer, placeBet, cancelBet, roll;
	
	private DiceAppGUI app;
	private GameEngine game;
	private StatusBar status;
	
	private ArrayList<String> playerIDs = new ArrayList<>();
	
	private JComboBox<String> playerDropDown = new JComboBox(playerIDs.toArray());
	
	public ToolBar(DiceAppGUI app, GameEngine game, StatusBar status) {
		
		this.app = app;
		this.game = game;
		this.status = status;
		
		addPlayer = new JButton("Add Player");
		removePlayer = new JButton("Remove Player");
		placeBet = new JButton("Place Bet");
		cancelBet = new JButton("Cancel Bet");
		roll = new JButton("Roll");
		
		addListeners();
		addToBar();
		
	}
	
	public void addListeners() {
		addPlayer.addActionListener(new AddPlayerListener(app, game, this, status));
		removePlayer.addActionListener(new RemovePlayerListener(app, game, this, status));
		placeBet.addActionListener(new PlaceBetListener(app, game, this, status));
		cancelBet.addActionListener(new RemoveBetListener(app, game, this, status));
		roll.addActionListener(new RollPlayerListener(app, game, this, status));
		playerDropDown.addItemListener(new SwitchDiceListener(app));
	}
	
	public void addToBar() {
		add(addPlayer);
		add(removePlayer);
		add(placeBet);
		add(cancelBet);
		add(roll);
		add(playerDropDown);
	}
	
	public void addPlayerToDropDown(String name,String id) {
		playerIDs.add(id);
		playerDropDown.addItem(id + ". " + name);
	}
	
	public void removePlayerFromDropdown(int index) {
		playerIDs.remove(index);
		playerDropDown.removeItemAt(index);
	}
	
	public Player returnCurrentPlayer() {
		int index = playerDropDown.getSelectedIndex();
		String id = playerIDs.get(index);
		Player p = game.getPlayer(id);
		return p;
	}
	
	public int returnCurrentIndex() {
		return playerDropDown.getSelectedIndex();
	}
	
	public String returnPlayerID(int index) {
		return playerIDs.get(index);
	}
	
	public ArrayList<String> returnAllIDs() {
		ArrayList<String> tmp = playerIDs;
		return tmp;
	}
	
}
