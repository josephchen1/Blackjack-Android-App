package com.example.helloworld;

public class Deck {
    Card[] cards = new Card[52];

    public Deck() {
        int count = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 1; y < 14; y++) {
                cards[count] = new Card(y, x);
                count++;
            }
        }
    }

    public void shuffle() {
        for (int i = 0; i < 10000; i++) {
            int a = (int) (Math.random() * cards.length);
            int b = (int) (Math.random() * cards.length);
            Card temp = cards[a];
            cards[a] = cards[b];
            cards[b] = temp;

        }
    }

    public Card dealCard(int n) {
        return cards[n];
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < cards.length; i++) {
            str += cards[i].toString() + "\n";
        }
        return str;
    }

}