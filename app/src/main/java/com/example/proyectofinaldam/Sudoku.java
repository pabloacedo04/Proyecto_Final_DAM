package com.example.proyectofinaldam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Random;

public class Sudoku extends AppCompatActivity {

    public class Celda {
        int numero;
        boolean numInicial;
        Button btn;

        public Celda(int valorInicial, Context contexto){
            numero = valorInicial;
            numInicial = numero != 0;
            btn = new Button(contexto);
            if (numInicial) btn.setText(String.valueOf(numero));
            else btn.setTextColor(Color.BLUE);
            btn.setOnClickListener(view -> {
                if (numInicial) return;
                numero++;
                if (numero>9) numero=1;
                btn.setText(String.valueOf(numero));
                if (terminado()){
                    if(victoria()){
                        isRunning = false;
                        Toast.makeText(Sudoku.this, "Has ganado!", Toast.LENGTH_SHORT).show();
                        bbddHelper.agregarPuntuacion(usuario, 3, seconds);
                    }
                    else{
                        Toast.makeText(Sudoku.this, "Hay algún error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    bbddHelper bbddHelper = new bbddHelper(this);
    Celda[][] tablero;
    TableLayout tl;
    LinearLayout linearLayout;
    String [] ej;
    TextView cuentaSegundos;
    int seconds = 0;
    boolean isRunning = false;
    Button btnPausar;
    Button btnSeguir;

    Bundle recibo;
    String usuario;

    int num;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startTimer();

        recibo = getIntent().getExtras();
        usuario = Objects.requireNonNull(recibo).getString("usuario");

        ej = new String[10];
        ej[0] = "? 5 4 3 ? 6 ? ? 2 "+
                "? 6 ? 7 5 4 3 8 ? "+
                "7 9 3 ? ? 8 6 4 5 "+
                "6 2 ? 4 ? ? 8 5 ? "+
                "? 3 ? ? 8 ? ? ? ? "+
                "? ? ? 5 ? ? ? ? 3 "+
                "9 8 6 2 ? 5 ? 3 4 "+
                "2 7 ? ? 4 3 5 ? ? "+
                "? ? ? ? 7 ? ? ? 6 ";

        ej[1] = "? 7 6 9 ? 2 3 ? ? "+
                "? 2 4 3 ? 7 ? ? 5 "+
                "8 ? ? 6 4 ? 7 ? ? "+
                "? ? ? 5 ? ? ? 4 6 "+
                "? 9 ? ? ? 6 ? 2 8 "+
                "? 4 5 8 2 ? ? ? 7 "+
                "? 5 7 ? ? ? ? ? ? "+
                "? ? 3 ? 5 4 8 ? ? "+
                "2 ? ? ? ? ? ? ? 3 ";

        ej[2] = "? 8 3 5 9 ? 2 6 1 "+
                "5 2 4 ? 7 ? ? ? 8 "+
                "6 ? 1 3 2 ? 5 ? 7 "+
                "? 5 ? 7 3 ? ? ? 4 "+
                "3 ? 7 ? 6 ? 8 5 2 "+
                "2 ? 9 ? 8 ? ? ? ? "+
                "4 1 ? 6 5 ? 3 ? ? "+
                "8 ? 6 ? 4 ? ? 7 5 "+
                "? 7 ? ? 1 ? 4 ? ? ";

        ej[3] = "? 7 ? ? 1 4 8 ? 9 "+
                "? ? ? ? ? 7 6 ? 1 "+
                "? ? ? 6 8 ? 7 5 3 "+
                "? 9 ? ? ? 8 2 ? 7 "+
                "3 ? 5 7 4 6 9 1 8 "+
                "7 ? ? 2 9 3 ? ? 5 "+
                "2 ? ? ? ? 5 ? 8 6 "+
                "5 ? 7 8 6 ? 3 ? 4 "+
                "? 6 8 4 ? ? ? 7 2 ";

        ej[4] = "? 8 ? ? ? 7 4 9 3 "+
                "4 ? ? 9 6 ? ? ? 7 "+
                "? 9 ? 8 ? ? ? ? 5 "+
                "5 ? ? ? 3 4 2 ? 6 "+
                "? ? ? 6 8 ? 3 7 ? "+
                "3 ? 8 2 ? ? 5 ? ? "+
                "? ? ? 4 ? ? 7 3 8 "+
                "2 ? 5 ? ? ? ? 4 ? "+
                "8 ? 7 ? ? 6 9 5 2 ";

        ej[5] = "? ? ? 3 ? ? ? 7 8 "+
                "? ? 6 ? ? ? 3 4 ? "+
                "? ? ? 6 5 4 2 ? ? "+
                "6 2 ? 5 8 ? 7 ? 4 "+
                "4 ? 8 ? ? ? ? 3 6 "+
                "? ? ? 7 ? ? ? 8 2 "+
                "? ? ? ? ? ? ? 5 3 "+
                "2 ? 9 8 3 5 4 ? ? "+
                "? 5 7 ? ? 9 ? ? ? ";

        ej[6] = "? 7 ? ? ? 8 2 3 6 "+
                "4 ? ? 6 ? 3 ? ? ? "+
                "? ? ? ? ? ? ? ? ? "+
                "? ? ? 5 ? 2 3 7 8 "+
                "? 6 ? ? ? 7 5 4 ? "+
                "? 8 ? ? ? ? ? ? 2 "+
                "? ? 7 ? ? 6 9 ? 4 "+
                "? 5 6 3 2 ? 7 ? ? "+
                "8 ? 2 ? ? ? ? ? ? ";

        ej[7] = "? 4 6 7 ? 2 3 ? ? "+
                "3 1 ? ? ? 8 2 9 ? "+
                "? ? ? 3 4 9 ? ? ? "+
                "2 7 9 ? ? 5 ? ? ? "+
                "? ? ? ? 9 3 ? 1 2 "+
                "? ? 4 2 8 7 6 5 ? "+
                "7 6 ? 5 ? ? 9 3 ? "+
                "? 9 ? ? 3 6 5 2 7 "+
                "? 2 3 9 7 1 4 6 ? ";

        ej[8] = "? ? 2 5 ? ? ? ? 8 "+
                "3 ? ? ? ? 2 ? 5 ? "+
                "? 9 ? ? 6 4 7 3 ? "+
                "4 ? ? ? 7 5 ? 8 6 "+
                "6 2 ? 4 ? ? ? 7 5 "+
                "? ? 7 ? ? ? 4 ? 3 "+
                "? 7 8 1 ? 6 ? ? ? "+
                "? 3 ? 2 8 ? ? ? ? "+
                "2 ? ? 3 ? ? ? 4 7 ";

        ej[9] = "? 4 8 6 ? ? 2 ? ? "+
                "? ? 2 ? ? ? ? ? ? "+
                "? 9 ? 5 ? 2 6 ? ? "+
                "? 7 6 3 5 ? ? ? 2 "+
                "2 ? 4 ? ? ? 5 3 8 "+
                "? 3 ? 4 ? ? ? 7 ? "+
                "? 2 5 ? 3 ? 8 6 ? "+
                "? 8 3 7 4 ? ? ? 5 "+
                "? ? 7 ? ? ? ? ? 1 ";

        Random random = new Random();
        num = random.nextInt(10);
        String [] numeros = ej[num].split(" ");

        tablero = new Celda[9][9];
        tl = new TableLayout(this);

        for (int i = 0; i < 9; i++) {
            TableRow tr = new TableRow(this);
            for (int j = 0; j < 9; j++) {
                String s = numeros[i * 9 + j];
                char c = s.charAt(0);
                tablero[i][j] = new Celda(c == '?' ? 0 : c - '0', this);
                TableRow.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
                if ((j + 1) % 3 == 0 && j < 8) {
                    params.rightMargin = 30;
                }

                if ((i + 1) % 3 == 0 && i < 8) {
                    params.bottomMargin = 30;
                }
                tablero[i][j].btn.setLayoutParams(params);
                tr.addView(tablero[i][j].btn);
            }
            tl.addView(tr);
        }



        tl.setShrinkAllColumns(true);
        tl.setStretchAllColumns(true);
        tl.setPadding(0,40,0,30);

        cuentaSegundos = new TextView(this);
        cuentaSegundos.setTextColor(Color.RED);
        cuentaSegundos.setTextSize(30);

        btnPausar = new Button(this);
        btnPausar.setText("Pausar");
        btnPausar.setTextSize(16);
        btnPausar.setTextColor(Color.BLACK);
        btnPausar.setBackgroundColor(Color.RED);
        btnPausar.setHeight(10);
        btnPausar.setOnClickListener(view -> {
            isRunning = false;
            for(int i = 0; i<9; i++){
                for (int j=0; j<9; j++){
                    tablero[i][j].btn.setEnabled(false);
                }
            }
            btnSeguir.setEnabled(true);
            btnPausar.setEnabled(false);
        });

        btnSeguir = new Button(this);
        btnSeguir.setEnabled(false);
        btnSeguir.setText("Continuar");
        btnSeguir.setTextColor(Color.BLACK);
        btnSeguir.setBackgroundColor(Color.RED);
        btnSeguir.setTextSize(16);
        btnSeguir.setHeight(10);
        btnSeguir.setOnClickListener(view -> {
            startTimer();
            for(int i = 0; i<9; i++){
                for (int j=0; j<9; j++){
                    tablero[i][j].btn.setEnabled(true);
                }
            }
            btnSeguir.setEnabled(false);
            btnPausar.setEnabled(true);
        });

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        layoutParams.topMargin = 30;

        btnPausar.setLayoutParams(layoutParams);
        btnSeguir.setLayoutParams(layoutParams);
        cuentaSegundos.setLayoutParams(layoutParams);

        linearLayout = new LinearLayout(this);
        linearLayout.addView(cuentaSegundos);
        linearLayout.addView(tl);
        linearLayout.addView(btnPausar);
        linearLayout.addView(btnSeguir);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(0, 100, 0, 0);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.morado));
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
                    if (comprobado[valor]) return true;
                    comprobado[valor] = true;
                }
            }
        }
        return false;
    }

    public boolean victoria(){
        for(int i=0; i <9; i++){
            if (correcto(i, 0, i + 1, 9)) return false;
        }
        for(int j=0; j<9; j++){
            if (correcto(0, j, 9, j + 1)) return false;
        }
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (correcto(3 * i, 3 * j, 3 * i + 3, 3 * j + 3)) return false;
            }
        }
        return true;
    }

    private void startTimer() {
        isRunning = true;
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                if (isRunning) {
                    // Actualiza el TextView con los segundos transcurridos
                    cuentaSegundos.setText("Segundos: "+seconds);
                    seconds++;
                    // Ejecuta este Runnable cada segundo (1000 milisegundos)
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }
}

