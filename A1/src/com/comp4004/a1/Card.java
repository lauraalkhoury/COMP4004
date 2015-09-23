package com.comp4004.a1;

public class Card {
	private String  name;
	private boolean inUse;
	
	public Card(String n) {
		name  = n;
		inUse = false;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isInUse() {
		return inUse;
	}
	
	public void setInUse(boolean b) {
		inUse = b;
	}
}