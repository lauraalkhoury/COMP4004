package com.comp4004.a1;

public enum CardNum {
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(11),
	QUEEN(12),
	KING(13),
	ACE(14);
	
	private int value;

    private CardNum(int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
};
