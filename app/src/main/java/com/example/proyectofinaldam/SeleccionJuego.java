package com.example.proyectofinaldam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SeleccionJuego extends AppCompatActivity {
    ImageButton juego1, juego2, juego3;
    String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_juego);

        juego1 = findViewById(R.id.juego1);
        juego2 = findViewById(R.id.juego2);
        juego3 = findViewById(R.id.juego3);

        Bundle recibo = getIntent().getExtras();
        if (recibo != null) {
            usuario = recibo.getString("usuario");
        }

        juego1.setOnClickListener(view -> {
            Intent i = new Intent(this, TresEnRayaLog.class);
            i.putExtra("usuario", usuario);
            startActivity(i);
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_puntuaciones:
                Intent i = new Intent(SeleccionJuego.this, Puntuaciones.class);
                i.putExtra("usuario", usuario);
                startActivity(i);
                break;
            case R.id.action_cambiarPass:
                    Intent i2 = new Intent(SeleccionJuego.this, Registro.class);
                    i2.putExtra("usuario", usuario);
                    i2.putExtra("cambioContrasena", "si");
                    startActivity(i2);
                break;
            case R.id.action_cerrarSesion:
                    Intent i3 = new Intent(SeleccionJuego.this, LoginActivity.class);
                    startActivity(i3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}