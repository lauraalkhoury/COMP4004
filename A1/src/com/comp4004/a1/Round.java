package com.comp4004.a1;

public class Round {
	int num_players;
	
	private Round(int p) {
		num_players = p; // set number of players for this round
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
