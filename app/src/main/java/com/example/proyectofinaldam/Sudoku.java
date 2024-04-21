package com.example.proyectofinaldam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Sudoku extends AppCompatActivity {

    public class Celda {
        int numero;
        boolean numInicial;
        Button btn;

        public Celda(int valorInicial, Context contexto){
            numero = valorInicial;
            if (numero!=0) numInicial = true;
            else numInicial = false;
            btn = new Button(contexto);
            if (numInicial) btn.setText(String.valueOf(numero));
            else btn.setTextColor(Color.BLUE);
            btn.setOnClickListener(view -> {
                if (numInicial) return;
                numero++;
                if (numero>9) numero=1;
                btn.setText(String.valueOf(numero));
                if (terminado()){
                    if (victoria()){
                        tv.setText("Ganaste");
                    }
                    else{
                        tv.setText("Hay algún error");
                    }
                }
            });
        }
    }
    Celda[][] tablero;
    TableLayout tl;
    TextView tv;
    LinearLayout linearLayout;
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
        tl = new TableLayout(this);
        for (int i = 0; i<9; i++){
            TableRow tr = new TableRow(this);
            for(int j = 0; j<9; j++){
                String s = numeros[i*9 + j];
                Character c = s.charAt(0);
                tablero[i][j] = new Celda(c=='?'?0:c-'0',this);
                tr.addView(tablero[i][j].btn);
            }
            tl.addView(tr);
        }
        tl.setShrinkAllColumns(true);
        tl.setStretchAllColumns(true);
        tv = new TextView(this);
        linearLayout = new LinearLayout(this);
        linearLayout.addView(tl);
        linearLayout.addView(tv);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);
    }

    public boolean terminado(){
        for (int i=0; i<9; i++)
            for (int j=0; j<9; j++)
                if (tablero[i][j].numero==0)
                    return false;
        return true;
    }

    public boolean correcto(int i1, int j1, int i2, int j2){
        boolean [] comprobado = new boolean[10];
        for (int i=1; i<=9; i++) comprobado[i] = false;
        for(int i=i1; i<i2; i++){
            for (int j = j1; j<j2; j++){
                int valor = tablero[i][j].numero;
                if (valor!=0){
                    if (comprobado[valor]) return false;
                    comprobado[valor] = true;
                }
            }
        }
        return true;
    }

    public boolean victoria(){
        for(int i=0; i <9; i++){
            if (!correcto(i, 0, i+1, 9)) return false;
        }
        for(int j=0; j<9; j++){
            if (!correcto(0, j, 9, j+1)) return false;
        }
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (!correcto(3*i, 3*j, 3*i+3, 3*j+3)) return false;
            }
        }
        return true;
    }
}