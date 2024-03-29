package com.comp4004.a1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Round {
	int  numPlayers;
	Deck d;
	
	HashMap<Integer, Card[]>  hands;
	ArrayList<Integer>        playerRanking;
	
	
	private Round(int n) {
		numPlayers = n;
		
		// allocate hashmap for players' hands
		hands = new HashMap<Integer, Card[]>();
		
		// initialize deck of cards
		d = new Deck();
	}
	
	public static Round initRound(int numPlayers) {
		// check to make sure numPlayers is valid
		if(numPlayers < 2 || numPlayers > 4) {
			return null;
		}
		Round r = new Round(numPlayers);
		return r;
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
	public boolean addHand(String hand) {
		// check that hand is not empty
		if(hand.isEmpty()) {
			System.out.println("Error: Empty string.\n" + "hand: " + hand + "\n");
			return false;
		}
		
		// get player ID
		int id =  Character.getNumericValue(hand.charAt(0));
		
		// if ID is invalid, do not add hand
		if(!idIsValid(id)) {
			System.out.println("Error: Invalid player ID.\n" + "hand: " + hand + "\n");
			return false;
		}
				
		// split hand string (ignoring player ID) into cards
		String[] cardStrings = hand.substring(2).split("\\s");
		
		// check number of cards is valid (exactly five)
		if((cardStrings.length) != 5) {
			System.out.println("Error: Invalid number of cards provided.\n" + "hand: " + hand + "\n");
			return false;
		}
		
		// createCardArray checks internally that each cardString is valid
		Card[] cards = createCardArray(cardStrings);
		if(cards == null) {
			System.out.println("Error: One or more invalid cards provided.\n" + "hand: " + hand + "\n");
			return false; // one or more cardStrings was invalid
		}

		// if all checks pass, add cards array to hands HashMap
		hands.put(id, cards);
		return true;
	}
	
	public Card[] createCardArray(String[] cardStrings) {
		Card[] cards = new Card[5];
		for(int i = 0; i < cardStrings.length; ++i) {
			// check with deck to make sure card is not in use
			if(d.isCardInUse(cardStrings[i])) {
				System.out.println("Error: card is in use.\n" + "card: " + cardStrings[i] + "\n");
				return null;
			}
			 Card c = new Card();
					 
			 // createFromString ensures that cardString passed in is valid
			 if(c.createFromString(cardStrings[i]) == false) {
				 return null;
			 }
			 
			 cards[i] = c;
			 
			 d.setCardInUse(true, cardStrings[i]);
		}
		return cards;
	}
	
	public boolean idIsValid(int id) {
		// check to see playerID is a valid number
		if(id < 1 || id > numPlayers)
			return false;
		
		// check that player hasn't already played
		if(hands.containsKey(id))
			return false;
		
		return true;
	}
	
	public int rankHand(Card[] cards) {
		HashMap<Suit, Integer> suitCount = countSuits(cards);
		
		// if suitCount = [5]
		if(suitCount.containsValue(5)) {
			if(isAceToTen(cards))       // check royal flush (1)
				return 1;
			else if(checkStraight(cards)) // check straight flush (2)
				return 2;
			else                          // if not, must be flush (5)
				return 5;
		}
		
		HashMap<CardNum, Integer> cardNumCount = countCardNums(cards);
		
		// if cardNumCount = [4, 1], four of a kind (3)
		if(cardNumCount.containsValue(4))
			return 3;
		
		// if cardNumCount = [3, 2], full house (4)
		if(cardNumCount.containsValue(3) && cardNumCount.containsValue(2))
			return 4;
		
		// if Rank[i - (i-4)], straight (6)
		if(checkStraight(cards))
			return 6;
		
		// three of a kind (7)
		if(cardNumCount.containsValue(3)) 
			return 7;
		
		// count number of pairs in hand
		int pairCount = 0;
		for (Integer value : cardNumCount.values()) {
		    if(value == 2)
		    	pairCount++;
		}
		
		if(pairCount == 2)
			return 8; // two pair (8)
		else if(pairCount == 1)	
			return 9; // one pair (9)
		else {
			Arrays.sort(cards);
			Card highCard = cards[0];
			
			return 24 - highCard.cardNum.getValue(); // high card (24 - card value [see CardNum class])
		}
	}
	
	public void rank() {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		
		for(HashMap.Entry<Integer, Card[]> entry : hands.entrySet()) {
		    Card[] playerCards = entry.getValue();
		    
		    // rank each player's score and add to hashmap
		    int thisScore = rankHand(playerCards);
		    scores.add(thisScore);
		}
		playerRanking = scores;
	}
	
	// output results (sorted by rank in descending order) in the following format:
	// Player ID: __, Cards: [__, __, __, __, __], Rank: __
	public ArrayList<String> getResultsDescending() {
		
		ArrayList<String> results = new ArrayList<String>();
		// find the highest rank in hashmap, add to final results
		ArrayList<Integer> playerRankingCopy = new ArrayList<Integer>(playerRanking);

		
		for(int i = playerRankingCopy.size()-1; i >= 0; --i) {			
			int indexWithHighRank = 0;
			int highRank          = 0;
			
			for(int j = playerRankingCopy.size()-1; j >= 0; --j) {
			    int rank = playerRankingCopy.get(j);
			    
			    if(rank > highRank) {
			    	indexWithHighRank = j;
			    	highRank          = rank;
			    }
			}
			
			int playerID = playerRanking.indexOf(highRank)+1;
			results.add("Player ID: " + playerID + ", Cards: " + Arrays.toString(hands.get(playerID)) + ", Rank: " + highRank);
		    playerRankingCopy.remove(indexWithHighRank);
		}
		return results;
	}
	
	public boolean isAceToTen(Card[] cards) {
		// sort highest card number first
		Arrays.sort(cards);
		
		// check for exact card numbers
		if((cards[0].cardNum == CardNum.ACE) &&
		   (cards[1].cardNum == CardNum.KING) &&
		   (cards[2].cardNum == CardNum.QUEEN) &&
		   (cards[3].cardNum == CardNum.JACK) &&
		   (cards[4].cardNum == CardNum.TEN))
		   return true;
		else
			return false;
	}
	
	// checks if given cards have consecutive values (after sorting)
	public boolean checkStraight(Card[] cards) {
		// sort ace-high or ace-low?
		boolean containsAce  = false;
		boolean containsKing = false;
		boolean containsTwo  = false;
		
		String[] cardNames = new String[5];
		for(int i = 0; i < cards.length; i++) {
			cardNames[i] = cards[i].getName();
		}
		
		for(int i = 0; i < cards.length; ++i) {
			//check for ace in deck
			if(cards[i].cardNum == CardNum.ACE)
				containsAce = true;

			//check for king in deck
			if(cards[i].cardNum == CardNum.KING)
				containsKing = true;
			
			//check for two in deck
			if(cards[i].cardNum == CardNum.TWO)
				containsTwo = true;
		}
		
		if(!containsAce || (containsAce && containsKing))
			Arrays.sort(cards); // sort normally
		if(containsKing && containsTwo)
			return false; // not possibly a straight
		if(containsAce && containsTwo) {
			// sort array for low ace
			// ie. [A, 5, 4, 3, 2] becomes [5, 4, 3, 2, A]
			Arrays.sort(cards);

			// shift elements down one
			Card temp = cards[0];

			for (int i = 0; i < (cards.length-1); ++i) {
				cards[i] = cards[i+1];
			}
			
			// move ace (presumably first element) to the end
			cards[cards.length - 1] = temp;
		}
		
		int initCardNum = cards[0].cardNum.getValue();
		for(int i = 1; i < cards.length; ++i) {
			if(initCardNum == 5)
			// add case for low-ace (ie. [5, 4, 3, 2, ACE] is a valid straight)
			if(i == 4 && (cards[i-1].cardNum == CardNum.TWO && cards[i].cardNum == CardNum.ACE))
				return true;
			if(cards[i].cardNum.getValue() != (initCardNum - i)) {
				return false;
			}
		}
		return true;
	}
	
	// this function counts the occurrences of suits in a given hand of cards
	public HashMap<Suit, Integer> countSuits(Card[] cards) {
		HashMap<Suit, Integer> suits = new HashMap<Suit, Integer>();
		
		for(int i = 0; i < cards.length; ++i) {
			Suit thisSuit = cards[i].suit;
			
			// if this suit has already been entered into hashmap
			if(suits.containsKey(thisSuit)) {
				// find hashmap entry for that suit, increment value
				suits.put(thisSuit, suits.get(thisSuit) + 1);
			}
			else {
				// create new hashmap entry with value <Suit, 1>
				suits.put(thisSuit, 1);
			}
		}
		return suits;
	}
	
	// this function counts the occurrences of card numbers in a given hand of cards
	public HashMap<CardNum, Integer> countCardNums(Card[] cards) {
		HashMap<CardNum, Integer> cardNums = new HashMap<CardNum, Integer>();
		
		for(int i = 0; i < cards.length; ++i) {
			CardNum thisCardNum = cards[i].cardNum;
			
			// if this cardNum has already been entered into hashmap
			if(cardNums.containsKey(thisCardNum)) {
				// find hashmap entry for that cardNum, increment value
				cardNums.put(thisCardNum, cardNums.get(thisCardNum) + 1);
			}
			else {
				// create new hashmap entry with value <CardNum, 1>
				cardNums.put(thisCardNum, 1);
			}
		}
		return cardNums;
	}
}
