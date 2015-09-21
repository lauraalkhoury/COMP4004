package com.comp4004.a1;

public class Round {
	int      numPlayers;
	int[]    playerIDs;
	String[] playerHands;
	
	private Round(int p) {
		// set number of players for this round
		numPlayers = p;
		
		// allocate array of player IDs
		playerIDs = new int[numPlayers];
		
		// assign playerID for each player
		for(int i = 0; i < numPlayers; ++i) {
			playerIDs[i] = i+1;
		}
		
		// allocate array of player hands
		playerHands = new String[numPlayers];
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
}
