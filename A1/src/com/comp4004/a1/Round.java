package com.comp4004.a1;

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
	
	public boolean isRoyalFlush(Card[] c) {
		if(c.length == 0)
			return false;
		
		// check that all suits are the same
		Suit initialSuit = c[0].suit;

		for(int i = 1; i < c.length; ++i) {
			if(c[i].suit != initialSuit) {
				return false;
			}
		}
		
		// sort highest card number first
		Arrays.sort(c);

		if((c[0].cardNum == CardNum.ACE) &&
		   (c[1].cardNum == CardNum.KING) &&
		   (c[2].cardNum == CardNum.QUEEN) &&
		   (c[3].cardNum == CardNum.JACK) &&
		   (c[4].cardNum == CardNum.TEN))
		   return true;
		else
			return false;
	}
}
