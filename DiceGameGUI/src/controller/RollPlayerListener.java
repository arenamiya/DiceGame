package controller;

import java.awt.event.ActionEvent;

import model.interfaces.GameEngine;
import view.DiceAppGUI;
import view.StatusBar;
import view.ToolBar;

public class RollPlayerListener extends AbstractMenuListener {

	public RollPlayerListener(DiceAppGUI menu, GameEngine tools, ToolBar game, StatusBar status) {
		super(menu, tools, game, status);
	}
	
	@Override
    public void actionPerformed(ActionEvent event)
    {
      menu.rollPlayer();
    }
	
}
