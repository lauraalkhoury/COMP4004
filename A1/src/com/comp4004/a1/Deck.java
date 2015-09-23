package com.comp4004.a1;

import java.util.Vector;

public class Deck {
	String[] suits   = {"Clubs", "Diamonds", "Hearts", "Spades"};
	String[] numbers = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	
	Vector<Card> cards = new Vector<Card>();
	
	public Deck() {
		// generate a deck of cards
		for(int i = 0; i < suits.length; ++i) {
			for(int j = 0; j < numbers.length; ++j) {
				Card c = new Card(numbers[j]+suits[i]);
				cards.addElement(c);
			}
		}
	}

	public boolean isValidCard(String card) {
		// check if given card name is in deck
		for(int i = 0; i < cards.size(); ++i) {
			if(cards.elementAt(i).getName().equals(card))
				return true;
		}
		return false;
	}

	public boolean isCardInUse(String card) {
		for(int i = 0; i < cards.size(); ++i) {
			if(cards.elementAt(i).getName().equals(card))
				return cards.elementAt(i).isInUse();
		}
		return false;
	}
	
	public void setCardInUse(boolean b, String card) {
		for(int i = 0; i < cards.size(); ++i) {
			if(cards.elementAt(i).getName().equals(card))
				cards.elementAt(i).setInUse(b);
		}
	}
}
