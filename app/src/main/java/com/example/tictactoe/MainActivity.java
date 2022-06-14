package com.example.tictactoe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    Dialog dialog;
    String str="Player 1's turn";
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    // put all win positions in a 2D array
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    int counter = 1;
    int win1=0;
    int win2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String s=Integer.toString(counter);
        TextView textview=findViewById(R.id.player);
        textview.setText(str);
        TextView Score1=findViewById(R.id.player1);
        TextView Score2=findViewById(R.id.player2);
        Score1.setText("Player1 \n"+Integer.toString(win1));
        Score2.setText("Player2 \n"+Integer.toString(win2));



    }

    public void startnew(){

    }

    public void gameReset() {
        counter=1;
        gameActive = true;

        HandlerThread handlerThread = new HandlerThread("background-thread");
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper());
        handler.postDelayed(new Runnable() {
            @Override public void run() {
                Log.e("ashes","error");
                // call some methods here

                // make sure to finish the thread to avoid leaking memory
                handlerThread.quitSafely();
            }
        }, 20000);
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        // remove all the images from the boxes inside the grid


        TextView status = findViewById(R.id.player);
        status.setText("Player 1's turn");
    }


    public void addimage(View view) {
        counter++;


        ImageView image=(ImageView) view;
       if(activePlayer==0){
           int i=Integer.parseInt(image.getTag().toString());
           if(gameState[i-1]==2){
           image.setImageResource(R.drawable.image0);
           gameState[i-1]=0;
           activePlayer=1;}
       }
       else{
           int i=Integer.parseInt(image.getTag().toString());
           if(gameState[i-1]==2){
           image.setImageResource(R.drawable.multiplyimag);
           gameState[i-1]=1;
           activePlayer=0;}
       }
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2){
                // Somebody has won! - Find out who!

                gameActive = false;
                example dialog= new example();
                if(gameState[winPosition[0]] == 0){

                    Bundle bundle = new Bundle();
                    win1++;
                    TextView Score1=findViewById(R.id.player1);

                    Score1.setText("Player1 \n"+Integer.toString(win1));
                    bundle.putString("TEXT","Player1 Wins's");

                    dialog.setArguments(bundle);
                    dialog.show((MainActivity.this).getSupportFragmentManager(),"Image Dialog");
                    gameReset();
                }
                else{
                    Bundle bundle = new Bundle();
                    win2++;
                    TextView Score2=findViewById(R.id.player2);

                    Score2.setText("Player2\n"+Integer.toString(win2));
                    bundle.putString("TEXT","Player2 Wins's");
                    dialog.setArguments(bundle);
                    dialog.show((MainActivity.this).getSupportFragmentManager(),"Image Dialog");
                    gameReset();
                }


            }
        }
        if(counter==10)
        {


            example dialog= new example();
            Bundle bundle = new Bundle();
            bundle.putString("TEXT","Draw");
            dialog.setArguments(bundle);
            dialog.show((MainActivity.this).getSupportFragmentManager(),"Image Dialog");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    // yourMethod();
                }
            }, 5000);

            gameReset();
            counter=1;

        }

        if(str=="Player 1's turn")
            str="Player 2's turn";
        else
            str="Player 1's turn";

        TextView textview=findViewById(R.id.player);
        textview.setText(str);

    }


    public void newgame(View view) {
        ((ImageView) findViewById(R.id.image1)).setImageResource(0);
        ((ImageView) findViewById(R.id.image2)).setImageResource(0);
        ((ImageView) findViewById(R.id.image3)).setImageResource(0);
        ((ImageView) findViewById(R.id.image4)).setImageResource(0);
        ((ImageView) findViewById(R.id.image5)).setImageResource(0);
        ((ImageView) findViewById(R.id.image6)).setImageResource(0);
        ((ImageView) findViewById(R.id.image7)).setImageResource(0);
        ((ImageView) findViewById(R.id.image8)).setImageResource(0);
        ((ImageView) findViewById(R.id.image9)).setImageResource(0);
    }
    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}