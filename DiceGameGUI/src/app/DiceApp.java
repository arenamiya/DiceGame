package app;

import javax.swing.SwingUtilities;

import view.DiceAppGUI;

public class DiceApp {
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new DiceAppGUI();
			}
		});
	}
}

