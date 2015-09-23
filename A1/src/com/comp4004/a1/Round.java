package com.comp4004.a1;

import java.util.HashMap;

public class Round {
	int                      numPlayers;
	HashMap<Integer, Card[]> hands;
	Deck                     d;
	
	private Round(int p) {
		// set number of players for this round
		numPlayers = p;
		
		// allocate hashmap for players' hands
		hands = new HashMap<Integer, Card[]>();
		
		// initialize deck of cards
		d = new Deck();
	}
	
	public static Round initRound(int numPlayers) {
		if(numPlayers < 2 || numPlayers > 4) {
			return null;
		}
		else {
			Round r = new Round(numPlayers);
			return r;
		}
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
	public void addHand(String hand) {
		// check that hand is not empty
		if(hand.isEmpty())
			return;
		
		// get player ID
		int id = Integer.valueOf(hand.charAt(0));
		
		// if ID is invalid, do not add hand
		if(!idIsValid(id))
			return;
				
		// split hand string (ignoring player ID) into cards
		String[] cardStrings = hand.substring(1).split("\\s");
		
		// if cards in hand are invalid, do not add hand
		if(!handIsValid(cardStrings))
			return;
		
		Card[] cards = new Card[cardStrings.length];
		for(int i = 0; i < cards.length; ++i) {
			// convert string to card object
			cards[i] = new Card(cardStrings[i]);
		}
		hands.put(id, cards);
	}
	
	public boolean handIsValid(String[] cards) {
		// check number of cards is valid (exactly five)
		if((cards.length) != 5)
			return false;
		
		for(int i = 0; i < cards.length; ++i) {
			// check that each card has valid name
			if(!d.isValidCard(cards[i]))
				return false;
			
			// check card is not already in use
			if(d.isCardInUse(cards[i]))
				return false;
			
			// if card passes tests, set it to "in use"
			d.setCardInUse(true, cards[i]);
		}
		
		// if all tests pass
		return true;
	}
	
	public boolean idIsValid(int id) {
		// check to see playerID is a valid number
		if(id < 0 || id > (numPlayers-1))
			return false;
		
		// check that player hasn't already played
		if(hands.containsKey(id))
			return false;
		
		return true;
	}
}
