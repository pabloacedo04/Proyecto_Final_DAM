package com.example.proyectofinaldam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;

public class sudoku extends AppCompatActivity {

    Celda[][] tablero;

    String ej;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);
        ej = "? 5 4 3 ? 6 ? ? 2 "+
             "? 6 ? 7 5 4 3 8 ? "+
             "7 9 3 ? ? 8 6 4 5 "+
             "6 2 ? 4 ? ? 8 5 ? "+
             "? 3 ? ? 8 ? ? ? ? "+
             "? ? ? 5 ? ? ? ? 3 "+
             "9 8 6 2 ? 5 ? 3 4 "+
             "2 7 ? ? 4 3 5 ? ? "+
             "? ? ? ? 7 ? ? ? 6 ";

        String [] numeros = ej.split(" ");

        tablero = new Celda[9][9];
        TableLayout tl = new TableLayout(this);
        for (int i = 0; i<9; i++){
            TableRow tr = new TableRow(this);
            for(int j = 0; j<9; j++){
                String s = numeros[i*9 + j];
                Character c = s.charAt(0);
                tablero[i][j] = new Celda(c=='?'?0:c-'0',this);
                tr.addView(tablero[i][j].btn);
            }
            tl.setShrinkAllColumns(true);
            tl.setStretchAllColumns(true);
            tl.addView(tr);
        }
        setContentView(tl);
    }
}