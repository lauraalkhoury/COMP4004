package com.comp4004.a1;

public class Round {
	int      num_players;
	int[]    playerIDs;
	String[] playerHands;
	
	private Round(int p) {
		// set number of players for this round
		num_players = p;
		
		// allocate array of player IDs
		playerIDs = new int[num_players];
		
		// assign playerID for each player
		for(int i = 0; i < num_players; ++i) {
			playerIDs[i] = i+1;
		}
		
		// allocate array of player hands
		playerHands = new String[num_players];
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
		return num_players;
	}
}
