package com.example.proyectofinaldam;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FlappyBirdGame extends View {
    Bird bird;
    Handler handler;
    Runnable r;

    public FlappyBirdGame(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bird = new Bird();

        bird.setAncho(100 * Constantes.ANCHO_PANTALLA / 1080);
        bird.setAlto(100 * Constantes.ALTO_PANTALLA / 1920);
        bird.setX(100 * Constantes.ANCHO_PANTALLA / 1080);
        bird.setY((int) (Constantes.ALTO_PANTALLA / 2 - bird.getAlto() / 2));

        ArrayList<Bitmap> arrBit = new ArrayList<>();
        arrBit.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.flappybird));
        bird.setArrBit(arrBit);

        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        bird.draw(canvas);
        handler.postDelayed(r, 10);
    }
}
