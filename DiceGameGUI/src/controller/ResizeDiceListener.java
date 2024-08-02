package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import view.DicePanel;

public class ResizeDiceListener extends ComponentAdapter {
	
	private DicePanel dice;
	
	public ResizeDiceListener(DicePanel dice) 
	{
		this.dice = dice;
	}

	public void componentResized(ComponentEvent e) 
	{
		dice.resizeDice();
    }

}