package com.comp4004.a1;

import java.util.HashMap;

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
	
	public void setUp() {
		r = Round.initRound(4);
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
		setUp();
		
		assertEquals(0, r.hands.size());
	}
	
	public void testAddEmptyHand() {
		setUp();
		
		String hand = "";
		boolean addSuccess = r.addHand(hand);
		
		assertEquals(addSuccess, false);
	}
	
	public void testAddHandWithInvalidPlayerID() {
		setUp();
		
		// 8 is an invalid player ID
		String hand = "8 AceSpades TwoHearts ThreeClubs NineSpades TenDiamonds";
		r.addHand(hand);
		
		assertEquals(false, r.hands.containsValue(hand));
	}
	
	public void testAddHandWithTooFewCards() {
		setUp();
		
		// not enough cards to be a valid hand (4)
		String hand = "1 AceSpades TwoHearts ThreeClubs NineSpades";
		boolean addSuccess = r.addHand(hand);
		
		assertEquals(addSuccess, false);
	}
	
	public void testAddHandWithTooManyCards() {
		setUp();
		
		// too many cards to be a valid hand (6)
		String hand = "1 AceSpades TwoHearts ThreeClubs NineSpades TenDiamonds AceDiamonds";
		boolean addSuccess = r.addHand(hand);
		
		assertEquals(addSuccess, false);
	}
	
	public void testAddHandWithInvalidCards() {
		setUp();
		
		// invalid cards except one
		String hand = "2 ThreeSnails TwentyClubs FiftyHearts PuppiesKittens TenSpades";
		boolean addSuccess = r.addHand(hand);
		
		assertEquals(addSuccess, false);
	}
	
	public void testAddHandWithDuplicateCards() {
		setUp();
		
		String hand = "3 TwoHearts TwoHearts FiveSpades QueenDiamonds NineClubs";
		boolean addSuccess = r.addHand(hand);
		
		assertEquals(addSuccess, false);
	}
	
	public void testRankHand() {
		setUp();
		
		r.addHand("4 AceHearts KingHearts QueenHearts JackHearts TenHearts"); // royal flush
		r.addHand("3 NineClubs EightClubs SevenClubs SixClubs FiveClubs");    // straight flush
		r.addHand("2 AceHearts AceSpades AceDiamonds AceClubs KingHearts");   // four of a kind
		r.addHand("1 AceHearts AceSpaces AceDiamonds KingHearts KingSpades"); // full house
		
		int[] actualRanking = r.rank();
		
		int[] correctRanking = {4, 3, 2, 1};
		
		assertEquals(correctRanking, actualRanking);
	}
	
	public void testIsRoyalFlush() {
		setUp();
		
		String[] cardStrArr = {"AceHearts", "KingHearts", "QueenHearts", "JackHearts", "TenHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);

		boolean results = r.isRoyalFlush(cardArr);
		
		assertEquals(results, true);
	}
	
	public void testIsNotRoyalFlush() {
		setUp();
		
		String[] cardStrArr = {"AceSpades", "KingHearts", "QueenHearts", "JackHearts", "TenHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);

		boolean results = r.isRoyalFlush(cardArr);
		
		assertEquals(results, false);
	}
	
	public void testIsStraightFlush() {
		setUp();
		
		String[] cardStrArr = {"TwoSpades", "ThreeSpades", "FourSpades", "SixSpades", "FiveSpades"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);

		boolean results = r.isStraightFlush(cardArr);
		
		assertEquals(results, true);
	}
	
	public void testIsNotStraightFlush() {
		setUp();
		
		String[] cardStrArr = {"AceSpades", "KingSpades", "QueenHearts", "JackClubs", "TenHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);

		boolean results = r.isStraightFlush(cardArr);
		
		assertEquals(results, false);
	}
	
	public void testCountSuits221() {
		String[] cardStrArr = {"AceSpades", "KingSpades", "QueenHearts", "JackClubs", "TenHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		HashMap<Suit, Integer> actualSuitCount   = r.countSuits(cardArr);
		
		HashMap<Suit, Integer> expectedSuitCount = new HashMap<Suit, Integer>();
		expectedSuitCount.put(Suit.SPADES, 2);
		expectedSuitCount.put(Suit.HEARTS, 2);
		expectedSuitCount.put(Suit.CLUBS, 1);
		
		assertEquals(expectedSuitCount, actualSuitCount);
	}
	
	public void testCountSuits5() {
		String[] cardStrArr = {"AceDiamonds", "KingDiamonds", "QueenDiamonds", "JackDiamonds", "TenDiamonds"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		HashMap<Suit, Integer> actualSuitCount   = r.countSuits(cardArr);
		
		HashMap<Suit, Integer> expectedSuitCount = new HashMap<Suit, Integer>();
		expectedSuitCount.put(Suit.DIAMONDS, 5);
		
		assertEquals(expectedSuitCount, actualSuitCount);
	}
	
	public void testCountSuits2111() {
		String[] cardStrArr = {"AceSpades", "KingSpades", "QueenDiamonds", "JackClubs", "TenHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		HashMap<Suit, Integer> actualSuitCount   = r.countSuits(cardArr);
		
		HashMap<Suit, Integer> expectedSuitCount = new HashMap<Suit, Integer>();
		expectedSuitCount.put(Suit.SPADES, 2);
		expectedSuitCount.put(Suit.HEARTS, 1);
		expectedSuitCount.put(Suit.DIAMONDS, 1);
		expectedSuitCount.put(Suit.CLUBS, 1);
		
		assertEquals(expectedSuitCount, actualSuitCount);
	}
	
	public void testCountSuits32() {
		String[] cardStrArr = {"KingSpades", "QueenSpades", "JackClubs", "AceSpades", "TenClubs"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		HashMap<Suit, Integer> actualSuitCount   = r.countSuits(cardArr);
		
		HashMap<Suit, Integer> expectedSuitCount = new HashMap<Suit, Integer>();
		expectedSuitCount.put(Suit.CLUBS, 2);
		expectedSuitCount.put(Suit.SPADES, 3);
		
		assertEquals(expectedSuitCount, actualSuitCount);
	}
}

