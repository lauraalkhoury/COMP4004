package com.comp4004.a1;

//TestDeck.java---------------------------------
import junit.framework.*; 

public class TestDeck extends TestCase {
	public void testNewDeck() {
		Deck d = new Deck();
		assertEquals(52, d.cards.size());
	}
}
