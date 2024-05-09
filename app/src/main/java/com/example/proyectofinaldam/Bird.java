package com.example.proyectofinaldam;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Bird extends Objetos{
    int count, vFlap, bitmapActual;
    ArrayList<Bitmap> arrBit = new ArrayList<>();
    public Bird() {
        this.count = 0;
        this.vFlap = 5;
        this.bitmapActual = 0;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(this.getBm(), this.x, this.y, null);
    }

    public ArrayList<Bitmap> getArrBit() {
        return arrBit;
    }

    public void setArrBit(ArrayList<Bitmap> arrBit) {
        this.arrBit = arrBit;
        for (int i = 0; i < arrBit.size(); i++){
            this.arrBit.set(i, Bitmap.createScaledBitmap(this.arrBit.get(i), (int) this.ancho, (int) this.alto, true));
        }
    }

    @Override
    public Bitmap getBm(){
        count++;
        if (this.count == this.vFlap){
            for (int i = 0; i < arrBit.size(); i++){
                if (i == arrBit.size()-1){
                    this.bitmapActual = 0;
                    break;
                }
                else if(this.bitmapActual == i){
                    bitmapActual = i+1;
                    break;
                }
            }
        }
        return this.arrBit.get(bitmapActual);
    }
}
