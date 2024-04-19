package com.example.proyectofinaldam;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

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
        });
    }
}
