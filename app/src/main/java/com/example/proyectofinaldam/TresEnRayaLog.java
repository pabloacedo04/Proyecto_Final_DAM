package com.example.proyectofinaldam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class TresEnRayaLog extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres_en_raya);
        EditText jugador1 = findViewById(R.id.jugador1);
        EditText jugador2 = findViewById(R.id.jugador2);
        Button iniciar = findViewById(R.id.iniciobtn);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getjugador1 = jugador1.getText().toString();
                String getjugador2 = jugador2.getText().toString();
                if (getjugador1.isEmpty() || getjugador2.isEmpty()) {
                    Toast.makeText(TresEnRayaLog.this, "Porfavor, introduzca un nombre", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(TresEnRayaLog.this, TresEnRayaGame.class);
                    intent.putExtra("jugador1", getjugador1);
                    intent.putExtra("jugador2", getjugador2);
                    startActivity(intent);
                }
            }
        });
    }
}
