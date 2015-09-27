package com.comp4004.a1;

public class Card implements Comparable<Card>{
	private String  name;
	private boolean inUse;
	public  CardNum cardNum;
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
	
	public boolean createFromString(String cardName) {
		String[] splitCardString = cardName.split("(?=\\p{Upper})");
		if(splitCardString.length != 2) {
			return false;
		}
		
		CardNum cn = toCardNum(splitCardString[0]);
		Suit    s  = toSuit(splitCardString[1]);
		
		// if either CardNum or Suit is invalid
		if(cn == null || s == null) {
			return false;
		}
		
		this.name    = cardName;
		this.cardNum = cn;
		this.suit    = s;
		
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
		case "Ace":
			return CardNum.ACE;
		default:
			return null;
		}
	}

	@Override
	public int compareTo(Card compareCard) {
		CardNum compareCardNum = ((Card) compareCard).cardNum; 
		
		//descending order
		return compareCardNum.getValue() - this.cardNum.getValue();
	}
}
