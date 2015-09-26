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
	
	public void testInitialNumHands() {
		int numPlayers = 4;
		setUp(numPlayers);
		
		assertEquals(0, r.hands.size());
	}
	
	public void testAddEmptyHand() {
		int numPlayers = 4;
		setUp(numPlayers);
		
		String hand = "";
		boolean addSuccess = r.addHand(hand);
		
		assertEquals(addSuccess, false);
	}
	
	public void testAddHandWithInvalidPlayerID() {
		int numPlayers = 4;
		setUp(numPlayers);
		
		// 8 is an invalid player ID
		String hand = "8 AceSpades TwoHearts ThreeClubs NineSpades TenDiamonds";
		r.addHand(hand);
		
		assertEquals(false, r.hands.containsValue(hand));
	}
	
	public void testAddHandWithTooFewCards() {
		int numPlayers = 4;
		setUp(numPlayers);
		
		// not enough cards to be a valid hand (4)
		String hand = "1 AceSpades TwoHearts ThreeClubs NineSpades";
		boolean addSuccess = r.addHand(hand);
		
		assertEquals(addSuccess, false);
	}
	
	public void testAddHandWithTooManyCards() {
		int numPlayers = 4;
		setUp(numPlayers);
		
		// too many cards to be a valid hand (6)
		String hand = "1 AceSpades TwoHearts ThreeClubs NineSpades TenDiamonds AceDiamonds";
		boolean addSuccess = r.addHand(hand);
		
		assertEquals(addSuccess, false);
	}
	
	public void testAddHandWithInvalidCards() {
		int numPlayers = 4;
		setUp(numPlayers);
		
		// invalid cards except one
		String hand = "2 ThreeSnails TwentyClubs FiftyHearts PuppiesKittens TenSpades";
		boolean addSuccess = r.addHand(hand);
		
		assertEquals(addSuccess, false);
	}
	
	public void testAddHandWithDuplicateCards() {
		int numPlayers = 4;
		setUp(numPlayers);
		
		String hand = "3 TwoHearts TwoHearts FiveSpades QueenDiamonds NineClubs";
		boolean addSuccess = r.addHand(hand);
		
		assertEquals(addSuccess, false);
	}

}

