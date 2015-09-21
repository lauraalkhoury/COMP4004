package com.comp4004.a1;

//TestRound.java---------------------------------
import junit.framework.*; 

public class TestRound extends TestCase {
	public TestRound(String name) {
	  super(name);
	}
	
	public void testSetNumPlayers() {
		int num_players = 5;
		Round r = new Round(num_players);
		assertEquals(num_players, r.getNumPlayers() );
	}

}

