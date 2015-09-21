package com.comp4004.a1;

//TestRound.java---------------------------------
import junit.framework.*; 

public class TestRound extends TestCase {
	Round r;
	
	public TestRound(String name) {
	  super(name);
	}
	
	public void setUp(int numPlayers) {
		r = Round.initRound(numPlayers);
	}
	
	public void testSetNumPlayers() {
		int numPlayers = 3;
		setUp(numPlayers);
		assertEquals(numPlayers, r.getNumPlayers());
	}
	
	public void testLowerPlayerLimit() {
		int numPlayers = 1;
		setUp(numPlayers);
		
		// initRound should return null if numPlayers less than 2
		assertEquals(null, r);
	}
	
	public void testUpperPlayerLimit() {
		int numPlayers = 5;
		setUp(numPlayers);
		
		// initRound should return null if numPlayers greater than 4
		assertEquals(null, r);
	}
	
	public void testPlayerIDArrSize() {
		int numPlayers = 4;
		setUp(numPlayers);
		
		assertEquals(numPlayers, r.playerIDs.length);
	}
	
	public void testInitialPlayerHandArrSize() {
		int numPlayers = 4;
		setUp(numPlayers);
		
		assertEquals(0, r.playerHands.size());
	}
	
	public void testAddHand() {
		int numPlayers = 4;
		setUp(numPlayers);
		
		String hand = "";
		r.addHand(hand);
		
		assertEquals(true, r.playerHands.contains(hand));
	}

}

