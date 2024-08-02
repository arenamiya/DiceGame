package model;

import java.util.Objects;

import model.interfaces.Die;

public class DieImpl implements Die {
	
	private int number;
	private int value;
	private int numFaces;
	
	public DieImpl(int number, int value, int numFaces) throws IllegalArgumentException {
		
		//throw exception if number is less than 1 or more than 2
		if (number < 1 || number > 2) {
			throw new IllegalArgumentException("invalid dice number");
		} else {
			this.number = number;
		}
		
		//throw exception of number is less than 1 or more than numFaces
		if(value < 1 || value > numFaces) {
			throw new IllegalArgumentException("this value is invalid");
		} else {
			this.value = value;
		}
		
		//throw exception of number is only or less than 1 diceFace
		if(numFaces <= 1) {
			throw new IllegalArgumentException("can not have only/less than 1 dice face");
		} else {
			this.numFaces = numFaces;
		}
	
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public int getNumFaces() {
		return numFaces;
	}

	@Override
	public boolean equals(Die die) {
		boolean isEqual = false;
		if(this.value == die.getValue() && this.numFaces == die.getNumFaces()) {
			isEqual = true;
		}
		return isEqual;
	}
	
	@Override
	public boolean equals(Object die) {
		return this.equals((DieImpl) die);
		
	}
	
	public int hashCode() {
		return Objects.hash(numFaces,value,numFaces) * Objects.hash(value,numFaces,value);
	} 
	
	public String toString() {
		//converts value into string
		String strValue = "";
		if(value <= 9) {
		switch(value) {
		case 1:
			strValue = "One";
			break;
		case 2:
			strValue = "Two";
			break;
		case 3:
			strValue = "Three";
			break;
		case 4:
			strValue = "Four";
			break;
		case 5:
			strValue = "Five";
			break;
		case 6:
			strValue = "Six";
			break;
		case 7:
			strValue = "Seven";
			break;
		case 8:
			strValue = "Eight";
			break;
		case 9:
			strValue = "Nine";
			break;
		}
		} else if (value > 9) {
			strValue = "> Nine";
		}
		return strValue;
	}
	

}
