package com.example.proyectofinaldam;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Memory extends Activity {
    ImageButton imb00, imb01, imb02, imb03, imb04, imb05, imb06, imb07, imb08, imb09, imb10, imb11, imb12, imb13, imb14, imb15;
    ImageButton[] tablero = new ImageButton[16];
    Button botonReiniciar, botonSalir;
    TextView textoPuntuacion;
    int puntuacion, aciertos, fondo, numeroPrimero, numeroSegundo;
    int[] imagenes;
    ArrayList<Integer> arrayDesordenado;
    ImageButton primero;
    boolean bloqueo = false;
    bbddHelper helper;
    final Handler handler = new Handler();
    Bundle recibo;
    String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        init();
        helper = new bbddHelper(getApplicationContext());
        recibo = getIntent().getExtras();
        if (recibo != null) {
            usuario = recibo.getString("usuario");
        }
    }

    private void cargarTablero(){
        imb00 = findViewById(R.id.carta1);
        imb01 = findViewById(R.id.carta2);
        imb02 = findViewById(R.id.carta3);
        imb03 = findViewById(R.id.carta4);
        imb04 = findViewById(R.id.carta5);
        imb05 = findViewById(R.id.carta6);
        imb06 = findViewById(R.id.carta7);
        imb07 = findViewById(R.id.carta8);
        imb08 = findViewById(R.id.carta9);
        imb09 = findViewById(R.id.carta10);
        imb10 = findViewById(R.id.carta11);
        imb11 = findViewById(R.id.carta12);
        imb12 = findViewById(R.id.carta13);
        imb13 = findViewById(R.id.carta14);
        imb14 = findViewById(R.id.carta15);
        imb15 = findViewById(R.id.carta16);

        tablero[0] = imb00;
        tablero[1] = imb01;
        tablero[2] = imb02;
        tablero[3] = imb03;
        tablero[4] = imb04;
        tablero[5] = imb05;
        tablero[6] = imb06;
        tablero[7] = imb07;
        tablero[8] = imb08;
        tablero[9] = imb09;
        tablero[10] = imb10;
        tablero[11] = imb11;
        tablero[12] = imb12;
        tablero[13] = imb13;
        tablero[14] = imb14;
        tablero[15] = imb15;
    }

    private void cargarBotones(){
        botonReiniciar = findViewById(R.id.botonJuegoReiniciar);
        botonSalir = findViewById(R.id.botonJuegoSalir);
        botonReiniciar.setOnClickListener(v -> init());

        botonSalir.setOnClickListener(v -> finish());
    }

    @SuppressLint("SetTextI18n")
    private void cargarTexto(){
        textoPuntuacion = findViewById(R.id.texto_puntuacion);
        puntuacion = 0;
        aciertos = 0;
        textoPuntuacion.setText("Puntuacion: " + puntuacion);
    }

    private void cargarImagenes(){
        imagenes = new int[]{
                R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3,
                R.drawable.img4,
                R.drawable.img5,
                R.drawable.img6,
                R.drawable.img7,
                R.drawable.img8
        };
        fondo = R.drawable.fondo1;
    }

    private ArrayList<Integer> barajar(int longitud){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0; i<longitud*2; i++){
            result.add(i % longitud);
        }
        Collections.shuffle(result);
        return result;
    }

    @SuppressLint("SetTextI18n")
    private void comprobar(int i, final ImageButton imgb){
        if(primero == null){
            primero = imgb;
            primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
            primero.setImageResource(imagenes[arrayDesordenado.get(i)]);
            primero.setEnabled(false);
            numeroPrimero = arrayDesordenado.get(i);
        } else {
            bloqueo = true;
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(imagenes[arrayDesordenado.get(i)]);
            imgb.setEnabled(false);
            numeroSegundo = arrayDesordenado.get(i);
            if(numeroPrimero == numeroSegundo){
                primero = null;
                bloqueo = false;
                aciertos++;
                puntuacion++;
                textoPuntuacion.setText("Puntuación: " + puntuacion);
                if(aciertos == imagenes.length){
                    Toast toast = Toast.makeText(getApplicationContext(), "Has ganado!!", Toast.LENGTH_LONG);
                    toast.show();
                    helper.agregarPuntuacion(usuario, 2, puntuacion);
                }
            } else {
                handler.postDelayed(() -> {
                    primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    primero.setImageResource(fondo);
                    primero.setEnabled(true);
                    imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imgb.setImageResource(fondo);
                    imgb.setEnabled(true);
                    bloqueo = false;
                    primero = null;
                    puntuacion--;
                    textoPuntuacion.setText("Puntuación: " + puntuacion);
                }, 1000);
            }
        }
    }

    private void init(){
        cargarTablero();
        cargarBotones();
        cargarTexto();
        cargarImagenes();
        arrayDesordenado = barajar(imagenes.length);
        for(int i=0; i<tablero.length; i++){
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
        }
        handler.postDelayed(() -> {
            for (ImageButton imageButton : tablero) {
                imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageButton.setImageResource(fondo);
            }
        }, 1500);
        for(int i=0; i<tablero.length; i++) {
            final int j = i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(v -> {
                if(!bloqueo)
                    comprobar(j, tablero[j]);
            });
        }

    }

}