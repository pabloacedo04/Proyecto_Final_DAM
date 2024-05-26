package com.example.proyectofinaldam;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Puntuaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);

        final bbddHelper bbdd = new bbddHelper(getApplicationContext());

        TextView memory1 = findViewById(R.id.memoryview1);
        TextView memory2 = findViewById(R.id.memoryview2);
        TextView memory3 = findViewById(R.id.memoryview3);
        TextView sudoku1 = findViewById(R.id.sudokuview1);
        TextView sudoku2 = findViewById(R.id.sudokuview2);
        TextView sudoku3 = findViewById(R.id.sudokuview3);
        
        Bundle recibo = getIntent().getExtras();
        String usuario = recibo.getString("usuario");
        
        List<Integer> ptsMemory = bbdd.devolverPuntuaciones(usuario, 2);
        List<Integer> ptsSudoku = bbdd.devolverPuntuaciones(usuario, 3);

        if (ptsMemory.size()==3){
            memory1.setText("Record: "+ptsMemory.get(0)+" puntos");
            memory2.setText("2º: "+ptsMemory.get(1)+" puntos");
            memory3.setText("3º: "+ptsMemory.get(2)+" puntos");
        }
        else{
            memory1.setText("No has jugado las partidas suficientes");
        }
        if (ptsSudoku.size()==3){
            sudoku1.setText("Record: "+ptsSudoku.get(2)+" segundos");
            sudoku2.setText("2º: "+ptsSudoku.get(1)+" segundos");
            sudoku3.setText("3º: "+ptsSudoku.get(0)+" segundos");
        }
        else{
            sudoku1.setText("No has jugado las partidas suficientes");
        }
    }

}