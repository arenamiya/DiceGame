package view;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {
	
	private DicePanel dice;
	private SummaryPanel summary;
	
	public GameEngineCallbackGUI(DicePanel dice, SummaryPanel summary) {
		this.dice = dice;
		this.summary = summary;
	}
	
	@Override
	public void playerDieUpdate(Player player, Die die, GameEngine gameEngine) {
		dice.updateDie(die);
	}

	@Override
	public void houseDieUpdate(Die die, GameEngine gameEngine) {
		dice.updateDie(die);
	}

	@Override
	public void playerResult(Player player, DicePair result, GameEngine gameEngine) {
		dice.updateDice(result);
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		dice.updateDice(result);
		summary.setHouseResult(result);
	}
	
}
