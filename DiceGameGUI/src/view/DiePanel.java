package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Canvas;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.Die;


@SuppressWarnings("serial")
public class DiePanel extends JPanel {
	
	private JLabel dieFace = new JLabel("");
	private Dimension d;
	private int dieValue;
	private Canvas canvas = new Canvas();
	private Graphics g;
	/**
	 * an image of a die
	 * @param die - the die image created
	 */
	public DiePanel(Die die) {
		
		dieValue = die.getValue();
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK,3, true));
		setBackground(Color.WHITE);
		d = new Dimension(200,200);
		canvas.setPreferredSize(d);
		dieFace.setText(Integer.toString(die.getValue()));
		dieFace.setFont(new Font("Sans Serif", Font.BOLD, 25));
		//add(canvas);
		//add(dieFace);
		
	}
	
	/**
	 * Changes the size of the die to the dimension
	 */
	public void setSize(Dimension d)
	{
		this.d = d; 
		setPreferredSize(d);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int dotSize = 15;
		
		switch(dieValue) {
			case 1:
				g.fillOval(getHeight()/2-7, getHeight()/2-7, dotSize, dotSize);
				break;
			case 2:
				g.fillOval(getHeight()/3-5, getWidth()/2-2-5, dotSize, dotSize);
				g.fillOval(getHeight()/3*2-5, getWidth()/2-2-5, dotSize, dotSize);
				break;
			case 3:
				g.fillOval(getHeight()/2-7, getHeight()/2-7, dotSize, dotSize);
				g.fillOval(getHeight()/3-5, getHeight()/3*2-5, dotSize, dotSize);
				g.fillOval(getHeight()/3*2-5, getHeight()/3-5, dotSize, dotSize);
				break;
			case 4:
				g.fillOval(getHeight()/3-5, getHeight()/3-5, dotSize, dotSize);
				g.fillOval(getHeight()/3-5, getHeight()/3*2-5, dotSize, dotSize);
				g.fillOval(getHeight()/3*2-5, getHeight()/3-5, dotSize, dotSize);
				g.fillOval(getHeight()/3*2-5, getHeight()/3*2-5, dotSize, dotSize);
				break;
			case 5:
				g.fillOval(getHeight()/2-7, getHeight()/2-7, dotSize, dotSize);
				g.fillOval(getHeight()/3-5, getHeight()/3-5, dotSize, dotSize);
				g.fillOval(getHeight()/3-5, getHeight()/3*2-5, dotSize, dotSize);
				g.fillOval(getHeight()/3*2-7, getHeight()/3-5, dotSize, dotSize);
				g.fillOval(getHeight()/3*2-5, getHeight()/3*2-7, dotSize, dotSize);
				break;
			case 6:
				g.fillOval(getHeight()/4-5, getHeight()/4-5, dotSize, dotSize);
				g.fillOval(getHeight()/4-5, getHeight()/4*2-5, dotSize, dotSize);
				g.fillOval(getHeight()/4-5, getHeight()/4*3-5, dotSize, dotSize);
				g.fillOval(getHeight()/4*3-5, getHeight()/4-5, dotSize, dotSize);
				g.fillOval(getHeight()/4*3-5, getHeight()/4*2-5, dotSize, dotSize);
				g.fillOval(getHeight()/4*3-5, getHeight()/4*3-5, dotSize, dotSize);
				break;
		}
	}

	
}
