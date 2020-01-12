package com.example.helloworld;

import android.util.Log;

import java.util.ArrayList;
import android.widget.TextView;
import android.widget.Toast;


public class Game {

    Deck deck = new Deck(); // creates new Deck
    ArrayList<Card> you = new ArrayList<Card>(); // creates ArrayList of cards for player
    ArrayList<Card> dealer = new ArrayList<Card>();
    int n = 0;
    boolean dealerDone = false;
    boolean playerDone = false;
    boolean endingg = false;

    public Game() {
        deck.shuffle(); // shuffles deck
        start(); // deals cards and prints initial cards
    }



    public void hit() {
        Log.d("myTag", "Player drew card!");
        if (getScore(you) < 21 && endingg == false) {
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
                endingg = true;
            }
        }
        DealerTurn();
    }

    public String printYHand() {
        String pHand = "";
        for (int i = 0; i < you.size(); i++) {
            pHand += you.get(i) + ", ";
        }
        return pHand;
    }

    public String printDHand() {
        String dHand = "Unknown, ";
        for (int i = 0; i < dealer.size() - 1; i++) {
            dHand += dealer.get(i) + ", ";
        }
        return dHand;
    }

    public String printDFinalHand() {
        String dHand = "";
        for (int i = 0; i < dealer.size(); i++) {
            dHand += dealer.get(i) + ", ";
        }
        return dHand;
    }

    public int printYScore(){
        return getScore(you);
    }

    public int printDScore(){
        return getScore(dealer);
    }


    public void DealerTurn() {
        if (getScore(dealer) < 17 && playerDone == false) {
            Log.d("myTag", "Dealer drew card!");
            dealer.add(deck.dealCard(n));
            n++;
            Log.d("myTag", "Your cards:");
            printCards(you);
            Log.d("myTag", "Your points: " + getScore(you));
            Log.d("myTag", "Dealer's cards:");
            for (int i = 0; i<dealer.size()-2; i++) {
                Log.d("myTag", ""+dealer.get(i));
            }
            Log.d("myTag", "UNKNOWN");
            if (getScore(dealer) >= 21) {
                dealerDone = true;
                ending(dealer, you);
                endingg = true;
            }
        } else if (getScore(dealer) > 17 && getScore(dealer) < 21) {
            Log.d("myTag", "Dealer stood!");
            dealerDone = true;
        } else if (getScore(dealer)==21) {
            dealerDone = true;
            ending(dealer, you);
            endingg = true;
        } else if (getScore(dealer) < 17 && playerDone == true) {
            while (getScore(dealer) < 17) {
                Log.d("myTag", "Dealer drew card!");
                dealer.add(deck.dealCard(n));
                n++;
            }
            ending(dealer, you);
            endingg = true;
        }
    }

    public void stand() {
        Log.d("myTag", "Player stood!");
        playerDone = true;
        while(dealerDone==false) {
            DealerTurn();
        }
        ending(dealer, you);
        endingg = true; // calculates winner and prints winner
    }


    public void start() {
        n = 0;
        dealerDone = false;
        playerDone = false;
        endingg = false;
        you.add(deck.dealCard(0)); // deals cards
        dealer.add(deck.dealCard(1));
        you.add(deck.dealCard(2));
        dealer.add(deck.dealCard(3));
        Log.d("myTag", "Your cards:");
        printCards(you);
        Log.d("myTag", "Your points: " + getScore(you));
        Log.d("myTag", "Dealer's cards:");
        Log.d("myTag", ""+dealer.get(0));
        Log.d("myTag", "UNKNOWN");
    }

    public void ending(ArrayList<Card> d, ArrayList<Card> y) {

        Log.d("myTag", "\nYour cards:");
        printCards(y);
        Log.d("myTag", "Your points: " + getScore(y));
        Log.d("myTag", "\nDealer's cards:");
        printCards(d);
        Log.d("myTag", "Dealer's points: " + getScore(d));
        result(getScore(y), getScore(d));
        dealerDone = true;
        playerDone = true;
    }


    public static void result(int y, int d) {
        if (d > 21 && y <= 21) {
            Log.d("myTag", "\nYou win! Dealer loses!");
        }
        else if (d <= 21 && y > 21) {
            Log.d("myTag", "\nDealer wins! You lose!");
        }
        else if (d > 21 && y > 21) {
            Log.d("myTag", "\nTie! No winner!");
        }
        else if (d > y) {
            Log.d("myTag", "\nDealer wins! You lose!");
        }
        else if (d == y) {
            Log.d("myTag", "\nTie! No winner!");
        }
        else if (d < y) {
            Log.d("myTag", "\nYou win! Dealer loses!");
        }
    }

    public String printResult() {
        String r = "";
        if (getScore(dealer) > 21 && getScore(you) <= 21) {
           r += "You win! Dealer loses!";
        } else if (getScore(you) > 21 && getScore(dealer) <= 21) {
            r += "Dealer wins! You lose!";
        } else if (getScore(dealer) > 21 && getScore(you) > 21) {
            r += "Tie! No winner!";
        } else if (getScore(dealer) > getScore(you)) {
            r += "Dealer wins! You lose!";
        } else if (getScore(dealer) == getScore(you)) {
            r += "Tie! No winner!";
        } else if (getScore(dealer) < getScore(you)) {
            r += "You win! Dealer loses!";
        }
        return r;
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
