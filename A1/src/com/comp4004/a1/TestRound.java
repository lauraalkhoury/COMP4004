package com.comp4004.a1;

//TestRound.java---------------------------------
import junit.framework.*; 

public class TestRound extends TestCase {
	Round r;
	
	public TestRound(String name) {
	  super(name);
	}
	
	public void setUp(int num_players) {
		r = Round.initRound(num_players);
	}
	
	public void testSetNumPlayers() {
		int num_players = 3;
		setUp(num_players);
		assertEquals(num_players, r.getNumPlayers());
	}
	
	public void testLowerPlayerLimit() {
		int num_players = 1;
		setUp(num_players);
		// initRound should return null if num_players less than 2
		assertEquals(null, r);
	}
	
	public void testUpperPlayerLimit() {
		int num_players = 5;
		setUp(num_players);
		
		// initRound should return null if num_players greater than 4
		assertEquals(null, r);
	}
	
	public void testPlayerArrSize() {
		int num_players = 4;
		setUp(num_players);
		
		assertEquals(num_players, r.playerIDs.length);
	}

}

