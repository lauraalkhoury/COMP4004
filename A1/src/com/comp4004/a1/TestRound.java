package com.comp4004.a1;

//TestRound.java---------------------------------
import junit.framework.*; 

public class TestRound extends TestCase {
	public TestRound(String name) {
	  super(name);
	}
	
	public void testSetNumPlayers() {
		int num_players = 3;
		Round r = Round.initRound(num_players);
		assertEquals(num_players, r.getNumPlayers());
	}
	
	public void testLowerPlayerLimit() {
		int num_players = 1;
		Round r = Round.initRound(num_players);
		
		// initRound should return null if num_players less than 2
		assertEquals(null, r);
	}
	
	public void testUpperPlayerLimit() {
		int num_players = 5;
		Round r = Round.initRound(num_players);
		
		// initRound should return null if num_players greater than 4
		assertEquals(null, r);
	}

}

