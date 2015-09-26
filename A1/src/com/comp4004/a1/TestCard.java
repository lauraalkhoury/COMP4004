package com.comp4004.a1;

//TestCard.java---------------------------------
import junit.framework.*; 

public class TestCard extends TestCase {
	public void testNewCardName() {
		Card c = new Card("AceSpades");
		
		assertEquals("AceSpades", c.getName());
	}
	
	public void testIsCardInUse() {
		Card c = new Card("TwoClubs");
		
		assertEquals(false, c.isInUse());
	}
	
	public void testEmptyToSuit() {
		Card c = new Card();
		Suit s = c.toSuit("");
		
		assertEquals(s, null);
	}
	
	public void testInvalidToSuit() {
		Card c = new Card();
		Suit s = c.toSuit("Kittens");
		
		assertEquals(s, null);
	}
	
	public void testValidToSuit() {
		Card c = new Card();
		Suit s = c.toSuit("Diamonds");
		
		assertEquals(s, Suit.DIAMONDS);
	}
}
