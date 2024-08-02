package model;

import java.util.Collection;
import java.util.Vector;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	
	private Collection<Player> players;
	private Collection<GameEngineCallback> callbacks;
	
	public GameEngineImpl() {
		//constructor made to construct collections for players and game callbacks
		players = new Vector<>();
		callbacks = new Vector<>();
	}
	

	@Override
	public void rollPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) {
		
		//addGameEngineCallback(new GameEngineCallbackImpl());
		
		//throw exception if delay parameters arent valid
		if (checkDelayParameters(initialDelay1, finalDelay1, delayIncrement1,
				initialDelay2, finalDelay2, delayIncrement2)) {

			for(GameEngineCallback cb : callbacks) {
				
				roll(player, cb, initialDelay1, finalDelay1, delayIncrement1,
						initialDelay2, finalDelay2, delayIncrement2);
				
				DicePair result = new DicePairImpl();
				
				player.setResult(result);
				cb.playerResult(player, result, this);
			
			}
			
		} else {
			System.out.println("Invalid parameters.");
			throw new IllegalArgumentException("invalid parameters");
		}
		
		
	}

	@Override
	public void rollHouse(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) {
		
		//addGameEngineCallback(new GameEngineCallbackImpl());
		
		if (checkDelayParameters(initialDelay1, finalDelay1, delayIncrement1,
				initialDelay2, finalDelay2, delayIncrement2)) {
			
			for(GameEngineCallback cb : callbacks) {
				
				roll(null, cb, initialDelay1, finalDelay1, delayIncrement1,
						initialDelay2, finalDelay2, delayIncrement2);
						
				DicePair result = new DicePairImpl();
				
				for(Player p : players) {
					applyWinLoss(p,result);
					p.resetBet();
				}
			
				cb.houseResult(result, this);
				
			}
				
		} else {
			System.out.println("Invalid parameters.");
			throw new IllegalArgumentException("invalid parameters");
		}
		

	}

	@Override
	public void applyWinLoss(Player player, DicePair houseResult) {
		
		int winLoss = 0;
		if (player.getResult().compareTo(houseResult) == 1) winLoss = player.getBet();
		else if (player.getResult().compareTo(houseResult) == -1) winLoss = -(player.getBet());
		
		player.setPoints(winLoss);
		
	}

	@Override
	public void addPlayer(Player player) { 
		
		boolean unique = true;
		String id = "";
		for(Player p : players) {
			if(p.getPlayerId().equals(player.getPlayerId())) {
				unique = false;
				id = p.getPlayerId();
			}
		}
		
		if(unique) players.add(player);
		if(!unique) {
			getPlayer(id).setPlayerName(player.getPlayerName());
			getPlayer(id).setPoints(player.getPoints());
			getPlayer(id).setResult(player.getResult());
		}
		
	}

	@Override
	public Player getPlayer(String id) {
		
		Player player = null;
		
		for(Player p : players) {
			if(p.getPlayerId().equals(id)) player = p;
		}
		
		return player;
	}

	@Override
	public boolean removePlayer(Player player) {
		
		return players.remove(player);
		
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		
		return player.setBet(bet);
		
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		
		callbacks.add(gameEngineCallback);

	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		
		return callbacks.remove(gameEngineCallback);
		
	}

	@Override
	public Collection<Player> getAllPlayers() {
		
		Collection<Player> copy = players;
		return copy;
		
	}
	
	private boolean checkDelayParameters(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) {
		
		boolean validDelay = true;
		
		if (initialDelay1 < 0 || finalDelay1 < 0 || delayIncrement1 < 0 || 
				initialDelay2 < 0 || finalDelay2 < 0 || delayIncrement2 < 0 ||
				finalDelay1 < initialDelay1 || finalDelay2 < initialDelay2 ||
				delayIncrement1 > (finalDelay1 - initialDelay1) ||
				delayIncrement2 > (finalDelay2 - initialDelay2)) {
			
			validDelay = false;
			
		} 
		
		return validDelay;
		
	}
	
	private void roll(Player player, GameEngineCallback gbc,
			int initialDelay1, int finalDelay1, int delayIncrement1,
			int initialDelay2, int finalDelay2, int delayIncrement2) {
		
		Thread t1 = new Roll(player, this, gbc, 1,
						initialDelay1, finalDelay1, delayIncrement1);
		Thread t2 = new Roll(player, this, gbc, 2,
						initialDelay2, finalDelay2, delayIncrement2);
				
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
