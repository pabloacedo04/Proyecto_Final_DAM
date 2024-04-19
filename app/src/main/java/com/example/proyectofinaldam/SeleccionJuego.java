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

        prueba.setText("Usuario: "+usuario);

        juego1.setOnClickListener(view -> {
            Toast.makeText(this, "Activity Juego 1", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, memory.class);
            startActivity(intent);
        });

        juego2.setOnClickListener(view -> {
            Toast.makeText(this, "Activity Juego 2", Toast.LENGTH_SHORT).show();
        });

        juego3.setOnClickListener(view -> {
            Intent i = new Intent(SeleccionJuego.this, sudoku.class);
            startActivity(i);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}