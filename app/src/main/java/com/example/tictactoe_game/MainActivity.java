package com.example.tictactoe_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT1 = "com.example.tictactoe_game.EXTRA_TEXT1"; // Verweis auf die MainActivitty
    public static final String EXTRA_TEXT2 = "com.example.tictactoe_game.EXTRA_TEXT2";
    int playMusic = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView1 = findViewById(R.id.imageView); //TicTacToe Image
        imageView1.setX(-1000); //Zu beginn ausserhalb des Sichtfeldes
        imageView1.animate().translationXBy(1000).setDuration(500); //Verschieben auf der x-Achse ins Sichtfeld

        VideoView videoView = findViewById(R.id.videoView); //Einbinden einer Animationssequenz
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video_main); // Sein Pfad
        if(playMusic == 1){ //Einmaliges Abspielen der Music
            MediaPlayer mediaPlayer =  MediaPlayer.create(this, R.raw.music_game);
            //mediaPlayer.start();
            playMusic = 0;
        }
        videoView.start(); //Starten der Animation

    }

    public void clickokButton(View view){ //onClick auf ImageView
        Log.i("Info", "User öndert Image");
        ImageView imageView1 = findViewById(R.id.imageView); //B
        ImageView imageView2 = findViewById(R.id.imageView2);

        if(imageView1.getAlpha() == 1){ //Ändern des Image durch verblassen
            imageView1.animate().alpha(0).setDuration(500);
            imageView2.animate().alpha(1).setDuration(500);
        }
        if(imageView2.getAlpha() == 1){
            imageView2.animate().alpha(0).setDuration(500);
            imageView1.animate().alpha(1).setDuration(500);
        }
    }

    public void set_translation(View view) throws InterruptedException { //Bei klicken auf PLAY
        Intent intent = new Intent(MainActivity.this, GameActivity.class); //Zum verwenden von Aktionen auf andere App Komponenten

        Log.i("Info", "User möchte Spiel starten");
        ImageView imageView1 = findViewById(R.id.imageView); //Image rotiert auf vorderseite
        imageView1.animate().translationYBy(2000).rotation(1000).scaleX(10).scaleY(10).setDuration(2000);

        //Übergabe der Strings für Player Namen
        EditText editTextPlayer1 =  (EditText) findViewById(R.id.editTextPlayer1);
        String stringPlayer1 = editTextPlayer1.getText().toString();

        EditText editTextPlayer2 = (EditText) findViewById(R.id.editTextPlayer2);
        String stringPlayer2 = editTextPlayer2.getText().toString();

        //Einfügen in Intent übergabe in die GameActivity
        intent.putExtra(EXTRA_TEXT1, stringPlayer1);
        intent.putExtra(EXTRA_TEXT2, stringPlayer2);

        //Starten der Activity
        startActivity(intent);

    }

        public void clickokButtonforHelp(View view){ // Springen in die HelpActivity
        Log.i("Info", "User braucht Hilfe");
        Intent intent = new Intent(MainActivity.this, HelpActivity.class);
        startActivity(intent);


    }

}