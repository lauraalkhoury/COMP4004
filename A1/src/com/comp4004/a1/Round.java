package com.comp4004.a1;

import java.util.HashMap;

public class Round {
	int             numPlayers;
	HashMap<Integer, String> hands;
	Deck            d;
	
	private Round(int p) {
		// set number of players for this round
		numPlayers = p;
		
		// allocate hashmap for players' hands
		hands = new HashMap<Integer, String>();
		
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
	
	// TODO: addHand(int playerID, Card[] cards)
	public void addHand(String hand) {
		// only add hand to round if it is valid
		if(handIsValid(hand)) {
			hands.put(Integer.valueOf(hand.charAt(0)), hand);
		}
	}
	
	public boolean handIsValid(String hand) {
		if(hand.isEmpty())
			return false;

		// split hand into 6 parts (id, 5 cards)
		String[] splitHand = hand.split("\\s");
		
		// get player ID from hand array
		int playerID = Integer.parseInt(splitHand[0]);
		
		// check to see playerID is valid
		if(playerID < 0 || playerID > (numPlayers-1))
			return false;
		
		// check that player hasn't already played
		if(hands.containsKey(playerID))
			return false;

		// check number of parameters after ID is valid (exactly five)
		if((splitHand.length - 1) != 5)
			return false;
		
		for(int i = 1; i < splitHand.length; ++i) {
			// check that each card has valid name
			if(!d.isValidCard(splitHand[i]))
				return false;
			
			// check card is not already in use
			if(d.isCardInUse(splitHand[i]))
				return false;
			
			// if card passes tests, set it to "in use"
			d.setCardInUse(true, splitHand[i]);
		}
		
		// if all tests pass
		return true;
	}
}
