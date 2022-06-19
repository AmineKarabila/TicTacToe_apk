package com.example.tictactoe_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

    }

    public void go_back(View view){
        Intent intent = new Intent(HelpActivity.this, MainActivity.class);
        startActivity(intent);
    }
}