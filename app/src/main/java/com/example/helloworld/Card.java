package com.example.helloworld;

public class Card {
    // declaring constants
    public static final String[] FACES = { "ZERO", "ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT",
            "NINE", "TEN", "JACK", "QUEEN", "KING" };
    private static final String[] SUITS = { "HEARTS", "DIAMONDS", "SPADES", "CLUBS" };

    // instance variables
    private String suit;
    private String face;
    private int value;

    // constructors
    public Card() {
        // random index between 1 and 13 (then 0 and 3) inclusive
        // set instance variables
        value = (int) (Math.random() * (FACES.length - 1) + 1);
        face = FACES[value];
        suit = SUITS[(int) (Math.random() * SUITS.length)];
    }

    public Card(int f, int s) {
        face = FACES[f];
        suit = SUITS[s];
        value = f;
    }

    // methods
    // setters
    public void setSuit(String s) {
        for (int i = 0; i < SUITS.length; i++) {
            if (s.equalsIgnoreCase(SUITS[i])) {
                suit = SUITS[i];
            }
        }
    }

    public void setFace(String f) {
        for (int i = 1; i < FACES.length; i++) {
            if (f.equalsIgnoreCase(FACES[i])) {
                face = FACES[i];
                value = i;
            }
        }
    }

    // getters
    public String getFace() {
        return face;
    }

    public String getSuit() {
        return suit;
    }

    public int getPoints() {
        if (value > 10) {
            return 10;
        } else if (value == 1) {
            return 11;
        }
        return value;
    }

    public String toString() {
        return face + " of " + suit;
    }
}
