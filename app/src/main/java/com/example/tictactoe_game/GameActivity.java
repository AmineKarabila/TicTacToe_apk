package com.example.tictactoe_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    int player_counter = 1; //Welcher spieler ist aktuell an der Reihe

    int[] GameFieldArray = {2,2,2,2,2,2,2,2,2}; //Feld Werte
    int[][] WinningPos = { {0,1,2}  //Bei gleichen Werten an Positionen, ist das Spiel gewonnen
                          ,{3,4,5}
                          ,{6,7,8}
                          ,{0,3,6}
                          ,{2,5,8}
                          ,{1,4,7}
                          ,{0,4,8}
                          ,{2,4,6}

                            };
    boolean gameActive = true; //Aktiv wenn Spiel noch läuft
    String testPlayer1;
    String testPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        MediaPlayer mediaPlayer =  MediaPlayer.create(this, R.raw.music_game); // Einbinden von Musik stoppen
        mediaPlayer.pause();

        Intent intent = getIntent(); // Übergabe von Namen
        testPlayer1 = intent.getStringExtra(MainActivity.EXTRA_TEXT1);
        testPlayer2 = intent.getStringExtra(MainActivity.EXTRA_TEXT2);

        TextView text_player1 = findViewById(R.id.textView2);
        TextView text_player2 = findViewById(R.id.textView8); 

        text_player1.setText(testPlayer1);
        text_player2.setText(testPlayer2);


    }

    public void visiblity(View view){
        ImageView counter  = (ImageView) view;
        Log.i("Tag", view.getTag().toString());

        int tappedField = Integer.parseInt(counter.getTag().toString());

        if(GameFieldArray[tappedField] == 2 && gameActive == true){
            GameFieldArray[tappedField] = player_counter;


            counter.animate().alpha(0.0F).setDuration(2000);
            counter.setImageResource(R.drawable.x);
            counter.animate().alpha(1.0F).setDuration(2000);

            if(player_counter == 1){
                counter.animate().alpha(0.0F).setDuration(2000);
                counter.setImageResource(R.drawable.x);
                counter.animate().alpha(1.0F).setDuration(2000);
                player_counter = 0;
            }
            else if(player_counter == 0){
                counter.animate().alpha(0.0F).setDuration(2000);
                counter.setImageResource(R.drawable.kreis);
                counter.animate().alpha(1.0F).setDuration(2000);
                player_counter = 1;
            }

            for(int[] winningPos:WinningPos){
                if(GameFieldArray[winningPos[0]] == GameFieldArray[winningPos[1]] && GameFieldArray[winningPos[1]] == GameFieldArray[winningPos[2]] && GameFieldArray[winningPos[2]] != 2 ){
                    String winner = "";
                    if(player_counter == 1){
                        winner = testPlayer2;
                    }
                    else if(player_counter == 0){
                        winner = testPlayer1;
                    }
                    Toast.makeText(this, winner + "hat gewonnen", Toast.LENGTH_SHORT).show();
                    gameActive = false;


                    TextView winnerText = findViewById(R.id.textView);

                    winnerText.setText(winner + " win");

                    winnerText.setVisibility(View.VISIBLE);
                }
            }

        }
        else{
            gameActive = false;
            for(int counterState: GameFieldArray){
                if(counterState == 2){
                    gameActive = true;
                }
            }
        }


    }
    public void go_back(View view){
        Intent intent2 = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent2 );
    }


}