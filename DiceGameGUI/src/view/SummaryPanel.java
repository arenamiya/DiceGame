package view;

import model.interfaces.DicePair;
import model.interfaces.Player;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel {
	
	private JLabel label = new JLabel("~~~~~~ SUMMARY PANEL ~~~~~~");
	private String resultStr;
	private ArrayList<Integer> lastPoints = new ArrayList<>();
	private ArrayList<Integer> lastBets = new ArrayList<>();
	
	public SummaryPanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		add(label);
		add(new JSeparator());
		add(new JLabel("HOUSE:"));
		add(new JLabel("Last Result: "));
		resultStr = "None.";
		add(new JSeparator());
		
	}
	
	/**
	 * get the bets from the last round
	 * @param lastBets an array list of all the previous rounds bets
	 */
	public void copyLastRound(ArrayList<Integer> lastPoints, ArrayList<Integer> lastBet) {
		this.lastPoints = lastPoints;
		this.lastBets = lastBet;
	}
	
	/**
	 * sets the house result
	 * @param result
	 */
	public void setHouseResult(DicePair result) {
		resultStr = result.toString();
	}
	
	/**
	 * adds a players summary onto the summary bar
	 * @param p - the players summary being added
	 */
	public void playerSummary(Player p) {
		
		int winLoss = 0;
		if(lastPoints.size() > 0 && lastBets.size() > 0) {
			int lastPoint = lastPoints.get(Integer.parseInt(p.getPlayerId()));
			int lastBet = lastBets.get(Integer.parseInt(p.getPlayerId()));
			if(lastPoint > p.getPoints()) winLoss = -(lastBet);
			if(lastPoint < p.getPoints()) winLoss = lastBet;
		}
		
			
		add(new JLabel("PLAYER:"));
		add(new JLabel("ID: " + p.getPlayerId()));
		add(new JLabel("Name: " + p.getPlayerName()));
		add(new JLabel("Points: " + p.getPoints()));
		add(new JLabel("Bet: " + p.getBet()));
		add(new JLabel("Last Result: "));
		add(new JLabel(p.getResult().toString()));
		add(new JLabel("Last Win/Loss: " + winLoss));
		
		add(new JSeparator());
		
	}
	
	public void cleanSummary() {
		
		removeAll();
		
		add(label);
		add(new JSeparator());

		add(new JLabel("HOUSE:"));
		add(new JLabel("Last Result: "));
		add(new JLabel(resultStr));
		
		add(new JSeparator());
	}
	

}
