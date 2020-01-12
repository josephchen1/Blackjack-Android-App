package com.example.helloworld;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Scanner;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        game = new Game();
        show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }

    public void show() {
        TextView pCards = (TextView)findViewById(R.id.playerCards);
        pCards.setText("Your hand: " + game.printYHand());
        TextView pScore = (TextView)findViewById(R.id.playerScore);
        pScore.setText("Your score: " + game.printYScore());
        TextView dCards = (TextView)findViewById(R.id.dealerCards);
        dCards.setText("Dealer's hand: " + game.printDHand());
        TextView dScore = (TextView)findViewById(R.id.dealerScore);
        dScore.setText("Dealer's score: ");
    }

    public void finalShow() {
        TextView pCards = (TextView)findViewById(R.id.playerCards);
        pCards.setText("Your hand: " + game.printYHand());
        TextView pScore = (TextView)findViewById(R.id.playerScore);
        pScore.setText("Your score: " + game.printYScore());
        TextView dCards = (TextView)findViewById(R.id.dealerCards);
        dCards.setText("Dealer's hand: " + game.printDFinalHand());
        TextView dScore = (TextView)findViewById(R.id.dealerScore);
        dScore.setText("Dealer's score: " + game.printDScore());
    }

    public void result() {
        TextView result = (TextView)findViewById(R.id.result);
        result.setText(game.printResult());
    }

    public void restart(View v) {
        game.deck.shuffle();
        game.dealer.clear();
        game.you.clear();
        game.start();
        show();
        TextView result = (TextView)findViewById(R.id.result);
        result.setText("Result :");
    }

    public void heet(View v) {
        Toast.makeText(this, "You took another card",
                Toast.LENGTH_SHORT).show();
        Log.i("info", "hello");
        game.hit();
        show();
        if (game.endingg) {
            result();
            finalShow();
        }
    }

    public void stand(View v) {
        Toast.makeText(this, "You stood",
                Toast.LENGTH_SHORT).show();
        Log.i("info", "hello");
        game.stand();
        show();
        if (game.endingg) {
            result();
            finalShow();
        }
        Log.d("myTag", "" + game.endingg);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
