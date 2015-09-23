package com.comp4004.a1;

import java.util.Vector;

public class Deck {
	String[] suits   = {"Clubs", "Diamonds", "Hearts", "Spades"};
	String[] numbers = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	
	Vector<String> cards = new Vector<String>();
	
	public Deck() {
		//cards = new String[52];
		
		// generate a deck of cards
//		int cardIndex = 0;
		for(int i = 0; i < suits.length; ++i) {
			for(int j = 0; j < numbers.length; ++j) {
				cards.addElement(numbers[j]+suits[i]);
//				System.out.println("New card number: " + " " + cards.elementAt(cardIndex));
//				cardIndex++;
			}
		}
	}
}
