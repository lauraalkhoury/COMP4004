package com.comp4004.a1;

import java.util.ArrayList;

public class Game {
	public static void main(String [ ] args) {
		// change this value to be whatever you'd like
		int numPlayers = 4;
		
		Round r = Round.initRound(numPlayers);
		
		// change these values to be whatever you'd like
		r.addHand("4 AceHearts KingHearts QueenHearts JackHearts TenHearts");
		r.addHand("3 SixClubs FiveClubs FourClubs ThreeClubs TwoClubs");
		r.addHand("2 NineHearts NineSpades NineDiamonds NineClubs KingSpades");
		r.addHand("1 AceClubs AceSpades AceDiamonds KingClubs KingDiamonds");
		
		r.rank();
		
		ArrayList<String> results = r.getResultsDescending();
		
		System.out.println(" =========== GAME RESULTS =========== ");
		System.out.println(results.toString());
		System.out.println(" ==================================== ");
	}
}
