package com.example.helloworld;

import android.util.Log;

import java.util.ArrayList;
import android.widget.TextView;


public class Game {

    Deck deck = new Deck(); // creates new Deck
    ArrayList<Card> you = new ArrayList<Card>(); // creates ArrayList of cards for player
    ArrayList<Card> dealer = new ArrayList<Card>();
    int n = 0;
    boolean dealerDone = false;
    boolean playerDone = false;

    public Game() {
        deck.shuffle(); // shuffles deck
        start(deck, dealer, you); // deals cards and prints initial cards
    }



    public void hit() {
        Log.d("myTag", "Player drew card!");
        if (getScore(you) < 21) {
            you.add(deck.dealCard(n));
            n++;
            Log.d("myTag", "Your cards:");
            printCards(you);
            Log.d("myTag", "Your points: " + getScore(you));
            Log.d("myTag", "Dealer's cards:");
            for (int i = 0; i<dealer.size()-1; i++) {
                Log.d("myTag", ""+dealer.get(i));
            }
            Log.d("myTag", "UNKNOWN");
            if (getScore(you) > 21) {
                Log.d("myTag", "Player BUSTED!");
                playerDone = true;
                ending(dealer, you);
            }
        }
        DealerTurn(dealer);
    }

    public String printYHand() {
        String pHand = "";
        for (int i = 0; i < you.size(); i++) {
            pHand += you.get(i) + ", ";
        }
        return pHand;
    }

    public String printDHand() {
        String dHand = "";
        for (int i = 0; i < dealer.size(); i++) {
            dHand += dealer.get(i) + ", ";
        }
        return dHand;
    }

    public void DealerTurn(ArrayList<Card> d) {
        if (getScore(d) < 17 && playerDone == false) {
            Log.d("myTag", "Dealer drew card!");
            d.add(deck.dealCard(n));
            n++;
            Log.d("myTag", "Your cards:");
            printCards(you);
            Log.d("myTag", "Your points: " + getScore(you));
            Log.d("myTag", "Dealer's cards:");
            for (int i = 0; i<d.size()-1; i++) {
                Log.d("myTag", ""+d.get(i));
            }
            Log.d("myTag", "UNKNOWN");
            if (getScore(d) > 21) {
                Log.d("myTag", "Dealer BUSTED!");
                ending(dealer, you);
            }
        } else if (playerDone ==false) {
            Log.d("myTag", "Dealer stood!");
            dealerDone = true;
        }
    }

    public void stand() {
        Log.d("myTag", "Player stood!");
        playerDone = true;
        while(dealerDone==false) {
            DealerTurn(dealer);
        }
        ending(dealer, you); // calculates winner and prints winner
    }


    public static void start(Deck deck, ArrayList<Card> d, ArrayList<Card> y) {
        y.add(deck.dealCard(0)); // deals cards
        d.add(deck.dealCard(1));
        y.add(deck.dealCard(2));
        d.add(deck.dealCard(3));
        Log.d("myTag", "Your cards:");
        printCards(y);
        Log.d("myTag", "Your points: " + getScore(y));
        Log.d("myTag", "Dealer's cards:");
        Log.d("myTag", ""+d.get(0));
        Log.d("myTag", "UNKNOWN");
    }

    public static void ending(ArrayList<Card> d, ArrayList<Card> y) {
        Log.d("myTag", "\nYour cards:");
        printCards(y);
        Log.d("myTag", "Your points: " + getScore(y));
        Log.d("myTag", "\nDealer's cards:");
        printCards(d);
        Log.d("myTag", "Dealer's points: " + getScore(d));
        result(getScore(y), getScore(d));
    }

    public static void result(int y, int d) {
        if (d > 21 && y <= 21) {
            Log.d("myTag", "\nYou win! Dealer loses!");
        }
        if (y > 21 && d <= 21) {
            Log.d("myTag", "\nDealer wins! You lose!");
        }
        if (d > y) {
            Log.d("myTag", "\nDealer wins! You lose!");
        } else if (d == y) {
            Log.d("myTag", "\nTie! No winner!");
        } else if (d > 21 && y > 21) {
            Log.d("myTag", "\nTie! No winner!");
        } else if (d < y) {
            Log.d("myTag", "\nYou win! Dealer loses!");
        }
    }

    public static int getScore(ArrayList<Card> input) {
        int points = 0;
        for (int i = 0; i < input.size(); i++) {

            points += input.get(i).getPoints();
        }
        return points;
    }

    public static void printCards(ArrayList<Card> input) {
        for (int i = 0; i < input.size(); i++) {
            Log.d("myTag", "" + input.get(i));
        }
    }
}
