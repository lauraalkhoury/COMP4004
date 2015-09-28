package com.comp4004.a1;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	Round r;

	public void playRound() {
		// change this value to be whatever you'd like
		int numPlayers = 4;
		
		r = Round.initRound(numPlayers);
		
		// change these values to be whatever you'd like
		r.addHand("4 AceHearts KingHearts QueenHearts JackHearts TenHearts");
		r.addHand("3 SixClubs FiveClubs FourClubs ThreeClubs TwoClubs");
		r.addHand("2 NineHearts NineSpades NineDiamonds NineClubs KingSpades");
		r.addHand("1 AceClubs AceSpades AceDiamonds KingClubs KingDiamonds");
		
		r.rank();
		
		ArrayList<String> results = r.getResultsDescending();
		
		System.out.println(results.toString());
	}
}
