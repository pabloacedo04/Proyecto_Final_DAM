package com.example.proyectofinaldam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class FlappyBirdGame extends AppCompatActivity {

    bbddHelper helper;
    Bundle recibo;
    String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flappy_bird_game);

        helper = new bbddHelper(getApplicationContext());
        recibo = getIntent().getExtras();
        usuario = recibo.getString("usuario");
    }
}