package com.comp4004.a1;

//TestDeck.java---------------------------------
import junit.framework.*; 

public class TestDeck extends TestCase {
	public void testNewDeck() {
		Deck d = new Deck();
		assertEquals(52, d.cards.size());
	}
	
	public void testInValidCard() {
		Deck d = new Deck();
		
		assertEquals(false, d.isValidCard("HelloWorld"));
	}
	
	public void testValidCard() {
		Deck d = new Deck();
		
		assertEquals(true, d.isValidCard("TenHearts"));
	}
	
	public void testUnusedCard() {
		Deck d = new Deck();
		
		assertEquals(false, d.isCardInUse("ThreeDiamonds"));
	}
	
	public void testUsedCard() {
		Deck d = new Deck();
		d.setCardInUse(true, "ThreeDiamonds");
		
		assertEquals(true, d.isCardInUse("ThreeDiamonds"));
	}
}
