package com.example.proyectofinaldam;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bbddHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NOMBRE_BBDD = "proyecto.db";
    private static final String TABLA_USUARIOS = "CREATE TABLE T_USUARIOS(usr text primary key, pass text)";
    public bbddHelper(Context context) {
        super(context, NOMBRE_BBDD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP table if exists t_usuarios");
        sqLiteDatabase.execSQL(TABLA_USUARIOS);
    }

    public void agregarUsuario(String usr, String pass){
        SQLiteDatabase bbdd = getWritableDatabase();
        if (bbdd!=null){
            bbdd.execSQL("INSERT INTO t_usuarios VALUES ('"+usr+"','"+pass+"');");
            bbdd.close();
        }
    }

    public boolean existeUsuario(String usr, Context context){
        boolean vuelta;
        bbddHelper bbddHelper = new bbddHelper(context);
        SQLiteDatabase db = bbddHelper.getWritableDatabase();

        Cursor busqueda = db.rawQuery("SELECT * FROM t_usuarios where usr = '"+usr+"' LIMIT 1", null);
        if (busqueda.moveToFirst()){
            vuelta = true;
        }
        else{
            vuelta = false;
        }
        busqueda.close();
        return vuelta;
    }

    public Usuario devolverUsuario(String usr, Context context){
        Usuario usuario = new Usuario();

        bbddHelper bbddHelper = new bbddHelper(context);
        SQLiteDatabase db = bbddHelper.getWritableDatabase();

        Cursor busqueda = db.rawQuery("SELECT * FROM t_usuarios where usr = '"+usr+"' LIMIT 1", null);

        if (busqueda.moveToFirst()){
            usuario.setUsr(busqueda.getString(0));
            usuario.setContrasena(busqueda.getString(1));
        }

        return usuario;
    }

}
