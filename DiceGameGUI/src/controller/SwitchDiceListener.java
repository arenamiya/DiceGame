package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.DiceAppGUI;

public class SwitchDiceListener implements ItemListener {
	
	private DiceAppGUI menu;
	
	public SwitchDiceListener(DiceAppGUI menu) {
		this.menu = menu;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		menu.updateDicePanel();
	}
	
}
