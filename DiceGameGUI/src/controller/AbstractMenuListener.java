package controller;

import view.DiceAppGUI;
import view.StatusBar;
import view.ToolBar;

import java.awt.event.ActionListener;

import model.interfaces.GameEngine;

public abstract class AbstractMenuListener implements ActionListener {
	
	protected DiceAppGUI menu;
	protected GameEngine game;
	protected ToolBar tools;
	protected StatusBar status;
	
	public AbstractMenuListener(DiceAppGUI menu, GameEngine game, ToolBar tools, StatusBar status) 
	{
		this.menu = menu;
		this.game = game;
		this.tools = tools;
		this.status = status;
	}

}
