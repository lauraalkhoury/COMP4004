package com.comp4004.a1;

public class Card {
	private String  name;
	private boolean inUse;
	public  Suit    suit;
	
	public Card() {
		inUse = false;
	}
	
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
	
	public boolean createFromString(String s) {
		this.name = s;
		return true;
	}
	
	public Suit toSuit(String s) {
		switch(s) {
		case "Diamonds":
			return Suit.DIAMONDS;
		case "Hearts":
			return Suit.HEARTS;
		case "Clubs":
			return Suit.CLUBS;
		case "Spades":
			return Suit.SPADES;
		default:
			return null;
		}
	}
}
