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

    int[] GameFieldArray = {2,2,2,2,2,2,2,2,2}; //Feld Werte ->  2:Unbelegt, 1: Spieler2, 0: Spieler1
    int[][] WinningPos = { {0,1,2}  //Bei gleichen Werten an Positionen, ist das Spiel gewonnen
                          ,{3,4,5}
                          ,{6,7,8}
                          ,{0,3,6}
                          ,{2,5,8}
                          ,{1,4,7}
                          ,{0,4,8}
                          ,{2,4,6}

                            };
    boolean gameActive = true; //Bei Avtiven Spielverlauf aktiv
    String testPlayer1;
    String testPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent(); // Übergabe von Namen
        testPlayer1 = intent.getStringExtra(MainActivity.EXTRA_TEXT1); // Aus der MainActivity übergeben
        testPlayer2 = intent.getStringExtra(MainActivity.EXTRA_TEXT2);

        TextView text_player1 = findViewById(R.id.textView2);
        TextView text_player2 = findViewById(R.id.textView8); 

        text_player1.setText(testPlayer1);
        text_player2.setText(testPlayer2);

    }

    public void visiblity(View view){
        ImageView counter  = (ImageView) view;
        Log.i("Tag", "ImageView " + view.getTag().toString()); //Log-Eintrag des Tags

        int tappedField = Integer.parseInt(counter.getTag().toString()); //Tag in ein Int casten

        if(GameFieldArray[tappedField] == 2 && gameActive == true){ //Abfangen das gesetzte Felder nicht ein zweites mal belegt werden können
            GameFieldArray[tappedField] = player_counter; //Überschreiben der Zwei mit Spieler Nummer


           // counter.animate().alpha(0.0F).setDuration(2000);
           // counter.setImageResource(R.drawable.x);//Setzten der counter Variable auf anderen Image
           // counter.animate().alpha(1.0F).setDuration(2000); //Diesen sichbar machen

            if(player_counter == 1){
                counter.animate().alpha(0.0F).setDuration(2000);
                counter.setImageResource(R.drawable.x);//Setzten der counter Variable auf anderen Image
                counter.animate().alpha(1.0F).setDuration(2000);//Diesen sichbar machen
                player_counter = 0;
            }
            else if(player_counter == 0){
                counter.animate().alpha(0.0F).setDuration(2000);
                counter.setImageResource(R.drawable.kreis);
                counter.animate().alpha(1.0F).setDuration(2000);
                player_counter = 1;
            }

            for(int[] winningPos:WinningPos){ // Abfragen der gleichheit der Symbole an vorbestimmten Positionen
                if(GameFieldArray[winningPos[0]] == GameFieldArray[winningPos[1]] && GameFieldArray[winningPos[1]] == GameFieldArray[winningPos[2]] && GameFieldArray[winningPos[2]] != 2 ){
                    String winner = "";
                    if(player_counter == 1){ //Spieler2 gewinnt
                        winner = testPlayer2;
                    }
                    else if(player_counter == 0){ //Spieler1 gewinnt
                        winner = testPlayer1;
                    }
                    Toast.makeText(this, winner + " hat gewonnen", Toast.LENGTH_SHORT).show(); //Ausgeben eines Toast
                    gameActive = false; // Speile ist Zuende


                    TextView winnerText = findViewById(R.id.textView);

                    winnerText.setText(winner + " has won"); // Einfügen des Gewinnertextes

                    winnerText.setVisibility(View.VISIBLE); //WinnerText sichtbar machen
                }
            }

        }
        else{
            gameActive = false;
            for(int counterState: GameFieldArray){ //Bei belegen aller Felder
                if(counterState == 2){
                    gameActive = true;
                }
            }
            TextView winnerText = findViewById(R.id.textView);

            winnerText.setText("No winner"); // Einfügen des Gewinnertextes

        }


    }
    public void go_back(View view){
        Intent intent2 = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent2 );
    }


}