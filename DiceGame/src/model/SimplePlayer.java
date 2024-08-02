package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	
	private String playerId;
	private String playerName;
	private int points;
	private int bet;
	private DicePair result = new DicePairImpl();
	
	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerId = playerId;
		this.playerName = playerName;
		points = initialPoints;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void setPoints(int points) {
		this.points += points;
	}

	@Override
	public String getPlayerId() {
		return playerId;
	}

	@Override
	public boolean setBet(int bet) {
		//set true if bet is more than 0 and players points are more than/equal to the set bet
		//false if above requirements arent valid
		boolean validBet = false;
		if(bet > 0 && bet <= points) {
			this.bet = bet;
			validBet = true;
		} 
		return validBet;
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public void resetBet() {
		bet = 0;
	}

	@Override
	public DicePair getResult() {
		return result;
	}

	@Override
	public void setResult(DicePair rollResult) {
		result = rollResult;
	}
	
	public String toString() {
		String info = "";
		
		if(result != null) {
			info = "PLAYER: \nID: " + playerId + 
					"\nName: " + playerName +
					"\nPoints: " + points +
					"\nRESULT: " + result.toString();
		} else {
			info = "PLAYER: \nID: " + playerId + 
					"\nName: " + playerName +
					"\nPoints: " + points +
					"\nRESULT: ";
		}
		return info;
	}
	
}
