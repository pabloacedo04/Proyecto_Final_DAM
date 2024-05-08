package com.example.proyectofinaldam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FlappyBird extends AppCompatActivity {

    ImageView btnInicio;

    bbddHelper helper;
    Bundle recibo;
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flappy_bird);

        helper = new bbddHelper(getApplicationContext());
        recibo = getIntent().getExtras();
        usuario = recibo.getString("usuario");

        btnInicio = findViewById(R.id.btnIncio);

        btnInicio.setOnClickListener(view -> {
            Intent i = new Intent(this, FlappyBirdGame.class);
            i.putExtra("usuario", usuario);
            startActivity(i);
        });
    }
}