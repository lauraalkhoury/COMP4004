package com.comp4004.a1;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	public void testIsAceToTenInOrder() {
		setUp();
		
		String[] cardStrArr = {"AceHearts", "KingHearts", "QueenHearts", "JackHearts", "TenHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);

		boolean results = r.isAceToTen(cardArr);
		
		assertEquals(results, true);
	}
	
	public void testIsAceToTenOutOfOrder() {
		setUp();
		
		String[] cardStrArr = {"KingHearts", "AceHearts", "JackHearts", "TenHearts", "QueenHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);

		boolean results = r.isAceToTen(cardArr);
		
		assertEquals(results, true);
	}
	
	public void testIsNotAceToTen() {
		setUp();
		
		String[] cardStrArr = {"AceSpades", "NineHearts", "QueenHearts", "JackHearts", "TenHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);

		boolean results = r.isAceToTen(cardArr);
		
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
	
	public void testCountCardNums41() {
		String[] cardStrArr = {"AceDiamonds", "AceClubs", "AceHearts", "AceSpades", "TenDiamonds"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		HashMap<CardNum, Integer> actualCardNumCount   = r.countCardNums(cardArr);
		
		HashMap<CardNum, Integer> expectedCardNumCount = new HashMap<CardNum, Integer>();
		expectedCardNumCount.put(CardNum.ACE, 4);
		expectedCardNumCount.put(CardNum.TEN, 1);
		
		assertEquals(expectedCardNumCount, actualCardNumCount);
	}
	
	public void testCountCardNums32() {
		String[] cardStrArr = {"AceDiamonds", "AceClubs", "AceHearts", "TenSpades", "TenDiamonds"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		HashMap<CardNum, Integer> actualCardNumCount   = r.countCardNums(cardArr);
		
		HashMap<CardNum, Integer> expectedCardNumCount = new HashMap<CardNum, Integer>();
		expectedCardNumCount.put(CardNum.ACE, 3);
		expectedCardNumCount.put(CardNum.TEN, 2);
		
		assertEquals(expectedCardNumCount, actualCardNumCount);
	}
	
	public void testCountCardNums11111() {
		String[] cardStrArr = {"AceDiamonds", "ThreeClubs", "SixHearts", "TenSpades", "KingDiamonds"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		HashMap<CardNum, Integer> actualCardNumCount   = r.countCardNums(cardArr);
		
		HashMap<CardNum, Integer> expectedCardNumCount = new HashMap<CardNum, Integer>();
		expectedCardNumCount.put(CardNum.ACE, 1);
		expectedCardNumCount.put(CardNum.THREE, 1);
		expectedCardNumCount.put(CardNum.SIX, 1);
		expectedCardNumCount.put(CardNum.TEN, 1);
		expectedCardNumCount.put(CardNum.KING, 1);
		
		assertEquals(expectedCardNumCount, actualCardNumCount);
	}
	
	public void testCheckSortedStraight() {
		String[] cardStrArr = {"AceDiamonds", "KingClubs", "QueenHearts", "JackSpades", "TenDiamonds"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		boolean result = r.checkStraight(cardArr);
		
		assertEquals(result, true);
	}
	
	public void testCheckUnsortedStraight() {
		String[] cardStrArr = {"TenDiamonds", "KingClubs", "QueenHearts", "JackSpades", "AceDiamonds"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		boolean result = r.checkStraight(cardArr);
		
		assertEquals(result, true);
	}
	
	public void testCheckInvalidStraight() {
		String[] cardStrArr = {"TwoDiamonds", "KingClubs", "QueenHearts", "JackSpades", "AceDiamonds"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		boolean result = r.checkStraight(cardArr);
		
		assertEquals(result, false);
	}
	
	public void testRankHandRoyalFlush() {
		String[] cardStrArr = {"AceHearts", "KingHearts", "QueenHearts", "JackHearts", "TenHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		int actualRank   = r.rankHand(cardArr);
		
		assertEquals(actualRank, 1);
	}
	
	public void testRankHandStraightFlush() {
		String[] cardStrArr = {"NineClubs", "EightClubs", "SevenClubs", "SixClubs", "FiveClubs"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		int actualRank   = r.rankHand(cardArr);
		
		assertEquals(actualRank, 2);
	}
	
	public void testRankHandFourOfAKind() {
		String[] cardStrArr = {"AceHearts","AceSpades", "AceDiamonds", "AceClubs", "KingHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		int actualRank   = r.rankHand(cardArr);
		
		assertEquals(actualRank, 3);
	}
	
	public void testRankHandFullHouse() {
		String[] cardStrArr = {"AceHearts","AceSpades", "AceDiamonds", "KingHearts", "KingSpades"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		int actualRank   = r.rankHand(cardArr);
		
		assertEquals(actualRank, 4);
	}
	
	public void testRankHandFlush() {
		String[] cardStrArr = {"AceSpades", "TenSpades", "SevenSpades", "SixSpades", "TwoSpades"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		int actualRank   = r.rankHand(cardArr);
		
		assertEquals(actualRank, 5);
	}
	
	public void testRankHandStraightAceLow() {
		String[] cardStrArr = {"FiveClubs", "FourDiamonds", "ThreeSpades", "TwoHearts", "AceHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		int actualRank   = r.rankHand(cardArr);
		
		assertEquals(actualRank, 6);
	}
	
	public void testRankHandStraightAceHigh() {
		String[] cardStrArr = {"AceDiamonds", "KingClubs", "QueenSpades", "JackDiamonds", "TenHearts"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		int actualRank   = r.rankHand(cardArr);
		
		assertEquals(actualRank, 6);
	}
	
	public void testRankHandThreeOfAKind() {
		String[] cardStrArr = {"AceHearts","AceSpades", "AceDiamonds", "KingSpades", "QueenClubs"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		int actualRank   = r.rankHand(cardArr);
		
		assertEquals(actualRank, 7);
	}
	
	public void testRankHandTwoPair() {
		String[] cardStrArr = {"AceHearts","AceSpades", "KingClubs", "KingDiamonds", "QueenSpades"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		int actualRank   = r.rankHand(cardArr);
		
		assertEquals(actualRank, 8);
	}
	
	public void testRankHandOnePair() {
		String[] cardStrArr = {"AceHearts","AceSpades", "KingClubs", "QueenSpades", "JackDiamonds"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		int actualRank   = r.rankHand(cardArr);
		
		assertEquals(actualRank, 9);
	}
	
	public void testRankHandHighCard() {
		String[] cardStrArr = {"AceHearts","KingClubs", "QueenDiamonds", "JackClubs", "NineSpades"};
		Card[]   cardArr    = r.createCardArray(cardStrArr);
		
		int actualRank   = r.rankHand(cardArr);
		
		assertEquals(actualRank, 10);
	}
	
	public void testRank() {
		setUp();
		
		r.addHand("4 AceHearts KingHearts QueenHearts JackHearts TenHearts"); // royal flush (1)
		r.addHand("3 SixClubs FiveClubs FourClubs ThreeClubs TwoClubs");    // straight flush (2)
		r.addHand("2 NineHearts NineSpades NineDiamonds NineClubs KingSpades");   // four of a kind (3)
		r.addHand("1 AceClubs AceSpades AceDiamonds KingClubs KingDiamonds"); // full house (4)
				
		r.rank();
		
		// ranks will be in reverse order as they appear above because ranking is evaluated in the order of playerID
		// ie. id=1, rank=4; id=2,rank=3; id=3,rank=2; id=4,rank=1;
		ArrayList<Integer> correctRanking = new ArrayList<Integer>(Arrays.asList(4, 3, 2, 1));	
		System.out.println("player ranking: " + r.playerRanking);
		
		assertEquals(correctRanking, r.playerRanking);
	}
	
	public void testTieRoyalFlushRank() {
		setUp();
		
		r.addHand("4 AceHearts KingHearts QueenHearts JackHearts TenHearts"); // royal flush
		r.addHand("3 AceClubs KingClubs QueenClubs JackClubs TenClubs"); // royal flush
		r.addHand("2 NineHearts NineSpades NineDiamonds NineClubs KingSpades");   // four of a kind
		r.addHand("1 TwoHearts TwoSpades TwoDiamonds ThreeClubs ThreeDiamonds"); // full house
				
		r.rank();
		
		ArrayList<Integer> correctRanking = new ArrayList<Integer>(Arrays.asList(4, 3, 1, 1));	
		
		assertEquals(correctRanking, r.playerRanking);
	}
	
	public void testTieHighCardRank() {
		setUp();
				
		r.addHand("4 AceHearts KingHearts QueenHearts JackHearts TenHearts"); // royal flush
		r.addHand("3 SixClubs FiveClubs FourClubs ThreeClubs TwoClubs");    // straight flush
		r.addHand("2 FiveHearts ThreeHearts NineDiamonds AceClubs KingSpades");   // high card
		r.addHand("1 ThreeSpades FourDiamonds TwoDiamonds SevenSpades SixHearts"); // high card
				
		r.rank();
		
		ArrayList<Integer> correctRanking = new ArrayList<Integer>(Arrays.asList(17, 10, 2, 1));
		
		assertEquals(correctRanking, r.playerRanking);
	}
	
	public void testGetResultsDescending() {
		setUp();
		
		r.addHand("4 AceHearts KingHearts QueenHearts JackHearts TenHearts");
		r.addHand("3 SixClubs FiveClubs FourClubs ThreeClubs TwoClubs");
		r.addHand("2 NineHearts NineSpades NineDiamonds NineClubs KingSpades");
		r.addHand("1 AceClubs AceSpades AceDiamonds KingClubs KingDiamonds");
		
		r.rank();
		
		ArrayList<String> actualResults   = r.getResultsDescending();
		
		ArrayList<String> expectedResults = new ArrayList<String>();
		expectedResults.add("Player ID: 1, Cards: [AceClubs, AceSpades, AceDiamonds, KingClubs, KingDiamonds], Rank: 4");
		expectedResults.add("Player ID: 2, Cards: [NineHearts, NineSpades, NineDiamonds, NineClubs, KingSpades], Rank: 3");
		expectedResults.add("Player ID: 3, Cards: [SixClubs, FiveClubs, FourClubs, ThreeClubs, TwoClubs], Rank: 2");
		expectedResults.add("Player ID: 4, Cards: [AceHearts, KingHearts, QueenHearts, JackHearts, TenHearts], Rank: 1");
		
		assertEquals(actualResults, expectedResults);
	}
}

