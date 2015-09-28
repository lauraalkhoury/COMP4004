package com.comp4004.a1;

import java.util.Random;

public class Game {
	Round r;
	
	
	
	public void playRound() {
		Random rn = new Random();
		int min = 2;
		int max = 4;
		int numPlayers = rn.nextInt(max - min + 1) + min;
		
		r = Round.initRound(numPlayers);
	}
}
