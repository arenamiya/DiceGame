package model;

import java.util.Objects;
import java.util.Random;

import model.interfaces.DicePair;
import model.interfaces.Die;

public class DicePairImpl implements DicePair {
	
    private Random rand = new Random();
	private static int NUM_FACES = 6;
	private Die die1;
	private Die die2;
	
	public DicePairImpl() { 
		
	die1 = new DieImpl(1,rand.nextInt(NUM_FACES) + 1,NUM_FACES);
	die2 = new DieImpl(2,rand.nextInt(NUM_FACES) + 1,NUM_FACES);
	   
	}


	@Override
	public Die getDie1() {
		return die1;
	}

	@Override
	public Die getDie2() {
		return die2;
	}

	@Override
	public int getTotal() {
		int value = die1.getValue() + die2.getValue();
		return value;
	}

	@Override
	public boolean equals(DicePair dicePair) {
		
		boolean isEqual = false;
		if(this.die1.equals(dicePair.getDie1()) && this.die2.equals(dicePair.getDie2())) isEqual = true;
		return isEqual;
		
	}
	
	@Override
	public boolean equals(Object dicePair) {
		
		return this.equals((DicePair) dicePair);
		
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(die1, die2, NUM_FACES) * Objects.hash(die2, die1, die2);
	}
	
	
	@Override
	public String toString() {
		String str = "None.";
		if(this != null) { str = "Dice 1: " + die1.toString() + 
				", Dice 2: " + die2.toString() +
				", Total: " + getTotal(); }
		return str;
	}

	@Override
	public int compareTo(DicePair dicePair) {
		if(this.getTotal() > dicePair.getTotal()) return 1;
		if(this.getTotal() < dicePair.getTotal()) return -1;
		else return 0;
	}
	

}
