package com.example.proyectofinaldam;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TresEnRayaGame extends AppCompatActivity {

    private final List<int[]> listaCombinaciones = new ArrayList<>();
    private int[] posiciones = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int turno = 1;
    private int totalCuadradosSeleccionados = 1;
    private boolean juegoTerminado = false;

    private TextView jugador1Nombre, jugador2Nombre;
    private ImageView cuadrado1, cuadrado2, cuadrado3, cuadrado4, cuadrado5, cuadrado6, cuadrado7, cuadrado8, cuadrado9;
    private Button btnreiniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres_en_raya_game);

        listaCombinaciones.add(new int[]{0, 1, 2});
        listaCombinaciones.add(new int[]{3, 4, 5});
        listaCombinaciones.add(new int[]{6, 7, 8});
        listaCombinaciones.add(new int[]{0, 3, 6});
        listaCombinaciones.add(new int[]{1, 4, 7});
        listaCombinaciones.add(new int[]{2, 5, 8});
        listaCombinaciones.add(new int[]{2, 4, 6});
        listaCombinaciones.add(new int[]{0, 4, 8});

        btnreiniciar = findViewById(R.id.btnReinicio);

        // Inicializar vistas
        jugador1Nombre = findViewById(R.id.jugador1nombre);
        jugador2Nombre = findViewById(R.id.jugador2nombre);
        cuadrado1 = findViewById(R.id.cuadrado1);
        cuadrado2 = findViewById(R.id.cuadrado2);
        cuadrado3 = findViewById(R.id.cuadrado3);
        cuadrado4 = findViewById(R.id.cuadrado4);
        cuadrado5 = findViewById(R.id.cuadrado5);
        cuadrado6 = findViewById(R.id.cuadrado6);
        cuadrado7 = findViewById(R.id.cuadrado7);
        cuadrado8 = findViewById(R.id.cuadrado8);
        cuadrado9 = findViewById(R.id.cuadrado9);

        String getjugador1 = getIntent().getStringExtra("jugador1");
        String getjugador2 = getIntent().getStringExtra("jugador2");

        jugador1Nombre.setText(getjugador1);
        jugador2Nombre.setText(getjugador2);

        prepararClickListeners();
    }

    private void prepararClickListeners() {
        btnreiniciar.setOnClickListener(view -> reiniciar());

        cuadrado1.setOnClickListener(view -> {
            if (estaUsada(0)) {
                realizarCambio(cuadrado1, 0);
            }
        });

        cuadrado2.setOnClickListener(view -> {
            if (estaUsada(1)) {
                realizarCambio(cuadrado2, 1);
            }
        });

        cuadrado3.setOnClickListener(view -> {
            if (estaUsada(2)) {
                realizarCambio(cuadrado3, 2);
            }
        });

        cuadrado4.setOnClickListener(view -> {
            if (estaUsada(3)) {
                realizarCambio(cuadrado4, 3);
            }
        });

        cuadrado5.setOnClickListener(view -> {
            if (estaUsada(4)) {
                realizarCambio(cuadrado5, 4);
            }
        });

        cuadrado6.setOnClickListener(view -> {
            if (estaUsada(5)) {
                realizarCambio(cuadrado6, 5);
            }
        });

        cuadrado7.setOnClickListener(view -> {
            if (estaUsada(6)) {
                realizarCambio(cuadrado7, 6);
            }
        });

        cuadrado8.setOnClickListener(view -> {
            if (estaUsada(7)) {
                realizarCambio(cuadrado8, 7);
            }
        });

        cuadrado9.setOnClickListener(view -> {
            if (estaUsada(8)) {
                realizarCambio(cuadrado9, 8);
            }
        });
    }

    private void realizarCambio(ImageView cuadrado, int cuadradoSeleccionado) {
        if (juegoTerminado) {
            return;
        }

        posiciones[cuadradoSeleccionado] = turno;

        if (turno == 1) {
            cuadrado.setImageResource(R.drawable.cruz);
            if (comprobar()) {
                Toast toast = Toast.makeText(getApplicationContext(), jugador1Nombre.getText().toString()+" ha ganado!!", Toast.LENGTH_LONG);
                toast.show();
                juegoTerminado = true;
            } else if (totalCuadradosSeleccionados == 9) {
                Toast toast = Toast.makeText(getApplicationContext(), "Empate!!", Toast.LENGTH_LONG);
                toast.show();
                juegoTerminado = true;
            } else {
                cambiarTurno(2);
                totalCuadradosSeleccionados++;
            }
        } else {
            cuadrado.setImageResource(R.drawable.circulo);
            if (comprobar()) {
                Toast toast = Toast.makeText(getApplicationContext(), jugador2Nombre.getText().toString()+" ha ganado!!", Toast.LENGTH_LONG);
                toast.show();
                juegoTerminado = true;
            } else if (totalCuadradosSeleccionados == 9) {
                Toast toast = Toast.makeText(getApplicationContext(), "Empate!!", Toast.LENGTH_LONG);
                toast.show();
                juegoTerminado = true;
            } else {
                cambiarTurno(1);
                totalCuadradosSeleccionados++;
            }
        }
    }

    private void cambiarTurno(int turnoActual) {
        turno = turnoActual;
    }

    private boolean comprobar() {
        boolean respuesta = false;
        for (int i = 0; i < listaCombinaciones.size(); i++) {
            final int[] combinacion = listaCombinaciones.get(i);

            if (posiciones[combinacion[0]] == turno &&
                    posiciones[combinacion[1]] == turno &&
                    posiciones[combinacion[2]] == turno) {
                respuesta = true;
            }
        }
        return respuesta;
    }

    private boolean estaUsada(int posicion) {
        return posiciones[posicion] == 0;
    }

    public void reiniciar() {
        posiciones = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        turno = 1;
        totalCuadradosSeleccionados = 1;
        juegoTerminado = false;

        // Restablecer imágenes a su estado inicial
        cuadrado1.setImageResource(R.drawable.blanco);
        cuadrado2.setImageResource(R.drawable.blanco);
        cuadrado3.setImageResource(R.drawable.blanco);
        cuadrado4.setImageResource(R.drawable.blanco);
        cuadrado5.setImageResource(R.drawable.blanco);
        cuadrado6.setImageResource(R.drawable.blanco);
        cuadrado7.setImageResource(R.drawable.blanco);
        cuadrado8.setImageResource(R.drawable.blanco);
        cuadrado9.setImageResource(R.drawable.blanco);
    }
}
