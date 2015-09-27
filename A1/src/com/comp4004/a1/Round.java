package com.comp4004.a1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Round {
	int                      numPlayers;
	HashMap<Integer, Card[]> hands;
	Deck                     d;
	
	private Round(int p) {
		// set number of players for this round
		numPlayers = p;
		
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
			return false;
		}
		
		// get player ID
		int id =  Character.getNumericValue(hand.charAt(0));
		
		// if ID is invalid, do not add hand
		if(!idIsValid(id)) {
			return false;
		}
				
		// split hand string (ignoring player ID) into cards
		String[] cardStrings = hand.substring(2).split("\\s");
		
		// check number of cards is valid (exactly five)
		if((cardStrings.length) != 5) {
			return false;
		}
		
		// createCardArray checks internally that each cardString is valid
		Card[] cards = createCardArray(cardStrings);
		if(cards == null) {
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
		if(id < 0 || id > (numPlayers-1))
			return false;
		
		// check that player hasn't already played
		if(hands.containsKey(id))
			return false;
		
		return true;
	}
	
	public int[] rank() {	
		return null;
	}
	
	public boolean isRoyalFlush(Card[] cards) {
		// check that all suits are the same
		HashMap<Suit, Integer> suitCount = countSuits(cards);
		if(!suitCount.containsValue(5))
			return false;
		
		// sort highest card number first
		Arrays.sort(cards);

		// check for correct card numbers
		if((cards[0].cardNum == CardNum.ACE) &&
		   (cards[1].cardNum == CardNum.KING) &&
		   (cards[2].cardNum == CardNum.QUEEN) &&
		   (cards[3].cardNum == CardNum.JACK) &&
		   (cards[4].cardNum == CardNum.TEN))
		   return true;
		else
			return false;
	}
	
	public boolean isStraightFlush(Card[] cards) {
		// check that all suits are the same
		HashMap<Suit, Integer> suitCount = countSuits(cards);
		if(!suitCount.containsValue(5))
			return false;
		
		// sort highest card number first
		Arrays.sort(cards);
		
		// check for a consecutive streak of card numbers
		int initCardNum = cards[0].cardNum.getValue();
		for(int i = 1; i < cards.length; ++i) {
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
