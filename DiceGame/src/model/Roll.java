package model;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class Roll extends Thread {
	
	private int finalDelay, delayIncrement, delay;
	private Player p;
	private GameEngine game;
	private GameEngineCallback record;
	private int dieNum;
	private Die die;

	public Roll(Player p, GameEngine game, GameEngineCallback cb, int die, 
			int initialDelay, int finalDelay, int delayIncrement) {
		
		this.p = p;
		dieNum = die;
		this.game = game;
		
		this.finalDelay = finalDelay;
		this.delayIncrement = delayIncrement;
		delay = initialDelay;
		record = cb;
		
	}

	@Override
	public void run() {
		
		while(delay < finalDelay) {
			
			DicePair dp = new DicePairImpl();
			
			if(dieNum == 1) die = dp.getDie1();
			if(dieNum == 2) die = dp.getDie2();
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(p == null) {
				record.houseDieUpdate(die, game);
			} else if (p != null){
				record.playerDieUpdate(p, die, game);
			}
			
			delay += delayIncrement;
			
		}
		
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}