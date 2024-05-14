package com.example.proyectofinaldam;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;

public class Bird extends Objetos{
    int count, vFlap, bitmapActual;
    private float caida;
    ArrayList<Bitmap> arrBit = new ArrayList<>();
    public Bird() {
        this.count = 0;
        this.vFlap = 5;
        this.bitmapActual = 0;
        this.caida = 0;
    }

    public void draw(Canvas canvas){
        caida();
        canvas.drawBitmap(this.getBm(), this.x, this.y, null);
    }

    private void caida() {
        this.caida+=0.6;
        this.y += this.caida;
    }

    public float getCaida() {
        return caida;
    }

    public void setCaida(float caida) {
        this.caida = caida;
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
            count =0;
        }
        if (this.caida<0){
            Matrix matrix = new Matrix();
            matrix.postRotate(-25);
            return Bitmap.createBitmap(arrBit.get(bitmapActual), 0, 0, arrBit.get(bitmapActual).getWidth(), arrBit.get(bitmapActual).getHeight(), matrix, true);
        }
        else if(this.caida>=0){
            Matrix matrix = new Matrix();
            if (this.caida<70){
                matrix.postRotate(-25+(caida*2));
            }
            else{
                matrix.postRotate(45);
            }
            return Bitmap.createBitmap(arrBit.get(bitmapActual), 0, 0, arrBit.get(bitmapActual).getWidth(), arrBit.get(bitmapActual).getHeight(), matrix, true);
        }
        return this.arrBit.get(bitmapActual);
    }
}
