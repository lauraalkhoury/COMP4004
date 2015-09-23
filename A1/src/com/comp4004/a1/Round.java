package com.comp4004.a1;

import java.util.Vector;

public class Round {
	int             numPlayers;
	Vector<Integer> playerIDs;
	Vector<String>  playerHands;
	Deck            d;
	
	private Round(int p) {
		// set number of players for this round
		numPlayers = p;
		
		// allocate array of player IDs
		playerIDs = new Vector<Integer>(numPlayers);
		
		// assign playerID for each player
		for(int i = 0; i < numPlayers; ++i) {
			playerIDs.add(i+1);
		}
		
		// allocate vector of player hands with max size numPlayers
		// actual size is 0 upon initialization
		playerHands = new Vector<String>(numPlayers);
		
		// initialize deck of cards
		d = new Deck();
	}
	
	public static Round initRound(int p) {
		if(p < 2 || p > 4) {
			return null;
		}
		else {
			Round r = new Round(p);
			return r;
		}
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
	public void addHand(String hand) {
		// only add hand to round if it is valid
		if(handIsValid(hand))
			playerHands.add(hand);
	}
	
	public boolean handIsValid(String hand) {
		if(hand.isEmpty())
			return false;

		// split hand into 6 parts (id, 5 cards)
		String[] splitHand = hand.split("\\s");
		
		// get player ID from hand array
		int playerID = Integer.parseInt(splitHand[0]);
		
		// check if this hand has valid player ID
		if(!playerIDs.contains(playerID)) {
			return false;
		}

		// check number of parameters after ID is valid (exactly five)
		if((splitHand.length - 1) != 5)
			return false;
		
		// check that each card is valid
		for(int i = 1; i < splitHand.length; ++i) {
			if(d.cards.contains(splitHand[i]))
				return false;
		}
		
		// if all tests pass
		return true;
	}
}
