package com.example.proyectofinaldam;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class FlappyBird extends AppCompatActivity {
    //ImageView btnInicio;
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

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        Constantes.ANCHO_PANTALLA = dm.widthPixels;
        Constantes.ALTO_PANTALLA = dm.heightPixels;

        //btnInicio = findViewById(R.id.btnIncio);

        /*
        btnInicio.setOnClickListener(view -> {
            Intent i = new Intent(this, FlappyBirdGame.class);
            i.putExtra("usuario", usuario);
            startActivity(i);
        });
        */
    }
}