package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class StatusBar extends JPanel {
	
	private JLabel bar = new JLabel("Status: ");
	private JLabel status = new JLabel("");
	
	public StatusBar(JFrame frame) 
	{
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		add(bar);
		add(status);
	}
	
	/**
	 * updates the status bar
	 * @param message - the message being updated on the status bar
	 */
	public void updateStatus(String message)
	{
		status.setText(message);
	}

}
