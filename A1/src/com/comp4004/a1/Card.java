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
	
	public CardNum toCardNum(String s) {
		switch(s) {
		case "Two":
			return CardNum.TWO;
		case "Three":
			return CardNum.THREE;
		case "Four":
			return CardNum.FOUR;
		case "Five":
			return CardNum.FIVE;
		case "Six":
			return CardNum.SIX;
		case "Seven":
			return CardNum.SEVEN;
		case "Eight":
			return CardNum.EIGHT;
		case "Nine":
			return CardNum.NINE;
		case "Ten":
			return CardNum.TEN;
		case "Jack":
			return CardNum.JACK;
		case "Queen":
			return CardNum.QUEEN;
		case "King":
			return CardNum.KING;
		default:
			return null;
		}
	}
}
