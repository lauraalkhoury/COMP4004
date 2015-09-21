package com.comp4004.a1;

import java.util.Vector;

public class Round {
	int             numPlayers;
	Vector<Integer> playerIDs;
	Vector<String>  playerHands;
	
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

		String[] splitHand = hand.split("\\s");
		
		// get player ID from hand array
		int playerID = Integer.parseInt(splitHand[0]);
		
		if(!playerIDs.contains(playerID)) {
			return false;
		}

		// check number of cards is valid
		if((splitHand.length - 1) != 5)
			return false;
		
		return true;
	}
}
