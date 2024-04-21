package com.example.proyectofinaldam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SeleccionJuego extends AppCompatActivity {

    TextView prueba;
    ImageButton juego1, juego2, juego3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_juego);

        juego1 = findViewById(R.id.juego1);
        juego2 = findViewById(R.id.juego2);
        juego3 = findViewById(R.id.juego3);

        prueba = findViewById(R.id.textView);
        Bundle recibo = getIntent().getExtras();
        String usuario = recibo.getString("usuario");

        juego1.setOnClickListener(view -> {
            /*
            Intent i = new Intent(this, Memory.class);
            i.putExtra("usuario", usuario);
            startActivity(i);
            */
        });

        juego2.setOnClickListener(view -> {
            Intent i = new Intent(SeleccionJuego.this, Memory.class);
            i.putExtra("usuario", usuario);
            startActivity(i);
        });

        juego3.setOnClickListener(view -> {
            Intent i = new Intent(SeleccionJuego.this, Sudoku.class);
            i.putExtra("usuario", usuario);
            startActivity(i);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}