package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

import model.interfaces.DicePair;
import model.interfaces.Die;

@SuppressWarnings("serial")
public class DicePanel extends JPanel {
	
	private DiePanel d1;
	private DiePanel d2;
	FlowLayout flow = new FlowLayout(FlowLayout.CENTER,5,20);
	
	public DicePanel(Dimension d)
	{
		
		setLayout(flow);
		setBackground(Color.LIGHT_GRAY);
		
	}
	
	/**
	 * Updates both die on the die panel with the players last result
	 * @param player - the die being shown
	 */
	public void updateDice(DicePair dp)
	{
		removeAll();

		d1 = new DiePanel(dp.getDie1());
		add(d1);

		d2 = new DiePanel(dp.getDie2());
		add(d2);
		
		resizeDice();
		
	}
	
	/**
	 * Resizes both die into the a little less than half the width
	 */
	public void resizeDice()
	{
		
		int width = getWidth()-30;
		Dimension dieSize = new Dimension(width/2,width/2);
		
		d1.setSize(dieSize);
		d2.setSize(dieSize);
		
		flow.setVgap(getHeight()/5);
		
		revalidate();
		
	}
	
	/**
	 * Updates a die on the dice panel based on the die's number
	 * @param die - the die being updated
	 */
	public void updateDie(Die die) {
		
		int dieNum = die.getNumber();
		
		switch(dieNum) {
			case 1:
				remove(d1);
				d1 = new DiePanel(die);
				add(d1);
				break;
			case 2:
				remove(d2);
				d2 = new DiePanel(die);
				add(d2);
				break;
		}
		resizeDice();
		revalidate();
	}
}
