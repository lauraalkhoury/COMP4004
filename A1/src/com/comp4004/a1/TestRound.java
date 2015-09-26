package com.comp4004.a1;

import java.util.Arrays;

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
	
	public void testRankHand() {
		int numPlayers = 4;
		setUp(numPlayers);
		
		r.addHand("4 AceHearts KingHearts QueenHearts JackHearts TenHearts"); // royal flush
		r.addHand("3 NineClubs EightClubs SevenClubs SixClubs FiveClubs");    // straight flush
		r.addHand("2 AceHearts AceSpades AceDiamonds AceClubs KingHearts");   // four of a kind
		r.addHand("1 AceHearts AceSpaces AceDiamonds KingHearts KingSpades"); // full house
		
		int[] actualRanking = r.rank();
		
		int[] correctRanking = {4, 3, 2, 1};
		
		assertEquals(correctRanking, actualRanking);
	}
	
	public void testIsRoyalFlush() {
		System.out.println("Entering testIsRoyalFlush");
		int numPlayers = 4;
		setUp(numPlayers);
		
		String[] cardStrArr = {"AceHearts", "KingHearts", "QueenHearts", "JackHearts", "TenHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		System.out.println(Arrays.toString(cardStrArr));
		System.out.println(Arrays.toString(cardArr));

		boolean results = r.isRoyalFlush(cardArr);
		
		System.out.println("Exiting testIsRoyalFlush");
		assertEquals(results, true);
	}

}

