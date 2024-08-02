package view;

import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import controller.ResizeDiceListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.DicePairImpl;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class DiceAppGUI extends JFrame
{
	
	private int numID = 0;
	private GameEngine game = new GameEngineImpl();
	
	private JMenuBar menu = new JMenuBar();
	
	private ArrayList<Boolean> playerRolled = new ArrayList<>();
	private ArrayList<Integer> playerBet = new ArrayList<>();
	private ArrayList<Integer> playerPoints = new ArrayList<>();
	
	private SummaryPanel summary = new SummaryPanel();
	private StatusBar status = new StatusBar(this);
	private ToolBar tools = new ToolBar(this, game, status);
	
	private DicePanel dice;
	
	private boolean currentlyRolling = false;
	
	public DiceAppGUI()
	{
		super("Dice App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		setLayout(new BorderLayout());
		tools.setFloatable(false);
		
		//TOOL/MENU BAR
		menu.add(tools);
	    setJMenuBar(menu);
	    
	    //DICE PANEL
	    dice = new DicePanel(getSize());
	    dice.updateDice(new DicePairImpl());
	    dice.addComponentListener(new ResizeDiceListener(dice));
	    add(dice,BorderLayout.CENTER);
	    
	    //SUMMARY PANEL
	    JScrollPane scroll = new JScrollPane(summary);
	    add(scroll,BorderLayout.WEST);
	    
	    //STATUS BAR
	    add(status,BorderLayout.SOUTH);
	    status.updateStatus("Game started!");
	    
		setVisible(true);
		
		game.addGameEngineCallback(new GameEngineCallbackGUI(dice,summary));
		//game.addGameEngineCallback(new GameEngineCallbackImpl());
		
	};
	
	/**
	 * Rolls the current Player selected in the combobox.
	 * Then moves to the rollHouse() function after the player has finished rolling.
	 * Updates the status of the game and the summary
	 */
	public void rollPlayer() 
	{
		
		JPanel panel = new JPanel();
		Player p = tools.returnCurrentPlayer();
		int index = tools.returnCurrentIndex();
		
		if(p.getBet() > 0 && !currentlyRolling) {
			
		    status.updateStatus("Currently Rolling: " + p.getPlayerName());
			
			new Thread() {
				@Override
				public void run()
				{
					currentlyRolling = true;
					game.rollPlayer(p, 100, 1000, 100, 50, 500, 50);

					try {
						SwingUtilities.invokeAndWait(new Runnable() {
							@Override
							public void run() {
								currentlyRolling = false;
								playerRolled.set(index, true);
								status.updateStatus("Last Rolled: Player " + p.getPlayerName());
								dice.updateDice(p.getResult());
								updateSummary();
								rollHouse();
							}
						});
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				}
			}.start();
					
			
		} else if (p.getBet() == 0) {
			JOptionPane.showMessageDialog(panel, "Can not roll because the selected Player hasn't bet.");
		} else if (currentlyRolling) {
			JOptionPane.showMessageDialog(panel, "Can not roll because the dice is already rolling.");
		}
		
	}
	
	/**
	 * House  checks if all players have rolled.
	 * 
	 * If all players have rolled, then the house will roll.
	 * All players well be reset to not having rolled anymore.
	 */
	private void rollHouse()
	{
		
		JPanel panel = new JPanel();
		
		boolean allRolled = true;
		
		for(Boolean rolled : playerRolled) {
			if(!rolled) {
				allRolled = false;
			}
		}
		
		if(!currentlyRolling && allRolled) {
			
			status.updateStatus("Currently Rolling: House");
			
			new Thread() {
				@Override
				public void run()
				{
					currentlyRolling = true;
					game.rollHouse(100, 1000, 100, 50, 500, 50);

					try {
						SwingUtilities.invokeAndWait(new Runnable() {
							@Override
							public void run() {
								
								currentlyRolling = false;

								summary.copyLastRound(playerPoints, playerBet);

								for(int index = 0; index < playerRolled.size() ; index++) {
									playerRolled.set(index, false);
									playerBet.set(index, 0);
								}
								
								ArrayList<String> ids = tools.returnAllIDs();
								
								try {
									for(String id : ids) {
										int i = 0;
										Player p = game.getPlayer(id);
										if(p.getPoints() == 0) {
											game.removePlayer(p);
											removePlayerInfo(i);
										}
										i++;
									}
								}catch (Exception e) {
									e.printStackTrace();
								}
			
								status.updateStatus("Last Rolled: House");
								updateSummary();
							}
						});
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			
				}
			}.start();
			
		
		} else if (currentlyRolling) {
			JOptionPane.showMessageDialog(panel, "Can not roll because the dice is already rolling.");
		}
		
	}
	
	/**
	 * Updates the dice panel and the summary panel
	 * with information of the player currently selected in the combobox.
	 */
	public void updateDicePanel()
	{
		try {
			Player p = tools.returnCurrentPlayer();
			dice.updateDice(p.getResult());
		} catch (Exception e) {
			dice.updateDice(new DicePairImpl());
		}
	}
	
	public void updateSummary()
	{
		summary.cleanSummary();
		
		for(Player p : game.getAllPlayers()) {
			summary.playerSummary(p);
		}
	}
	
	/**
	 * 
	 * @return the next number ID that hasnt been used
	 */
	public int getNumID() {
		return numID;
	}
	
	/**
	 * adds a players information onto the GUI and game
	 * @param player - the player being added
	 * @param id - the id of the player
	 */
	public void addPlayerInfo(Player player, String id, int points) {
		 game.addPlayer(player);
		 tools.addPlayerToDropDown(player.getPlayerName(), id);
		 playerBet.add(0);
		 playerRolled.add(false);
		 playerPoints.add(points);
		 numID++;
	}
	
	/**
	 * removes a player from the game
	 * @param index - the players information being removed from the GUI
	 */
	public void removePlayerInfo(int index) {
		
		playerBet.remove(index);
		playerRolled.remove(index);
		tools.removePlayerFromDropdown(index);
		
	}
	
	/**
	 * returns a boolean that states if the dice is currently rolling
	 * @return true if the dice is rolling, false if the dice is not rolling
	 */
	public boolean getCurrentlyRolling() {
		return currentlyRolling;
	}
	
	/**
	 * resets the bet for the player selected in index
	 * @param index - selecting the player to remove
	 */
	public void resetBet(int index) {
		Player player = game.getPlayer(tools.returnPlayerID(index));
		player.resetBet();
		playerBet.set(index, player.getBet());
	}
	
	public void placeBet(Player p, int index, int bet) {

    	playerBet.set(index, bet);
    	System.out.println("bet amount:"+playerBet.get(index));
		game.placeBet(p, bet);
	}

}