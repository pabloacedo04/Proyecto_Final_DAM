package com.example.proyectofinaldam;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Objetos {
    int x, y;
    float alto, ancho;
    Rect rect;
    Bitmap bm;

    public Objetos() {
    }

    public Objetos(int x, int y, float alto, float ancho) {
        this.x = x;
        this.y = y;
        this.alto = alto;
        this.ancho = ancho;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public Rect getRect() {
        return new Rect(this.x, this.y, (int) (this.x+this.ancho), (int) (this.y+this.alto));
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }
}
