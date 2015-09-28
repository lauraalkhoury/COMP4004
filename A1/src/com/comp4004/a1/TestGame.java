package com.comp4004.a1;

import junit.framework.*; 

//TestGame.java---------------------------------
public class TestGame extends TestCase {
	public void testPlayRound() {
		Game g = new Game();
		
		g.playRound();
		int np = g.r.numPlayers;
	}
}
