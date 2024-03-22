package com.example.proyectofinaldam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Context context = this;
    EditText usuario, contrasena;
    Button btnRegistrar, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.textLogin);
        contrasena = findViewById(R.id.textContrasena);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnLogin = findViewById(R.id.btnLogin);

        final bbddHelper bbdd= new bbddHelper(getApplicationContext());

        btnLogin.setOnClickListener(view -> {
            if(usuario.getText().toString().equals("") || contrasena.getText().toString().equals("")){
                Toast.makeText(this, "Faltan datos por rellenar", Toast.LENGTH_LONG).show();
            }
            else{
                Usuario u = bbdd.devolverUsuario(usuario.getText().toString(), this);
                if (u.getContrasena().equals(contrasena.getText().toString())){
                    //Intent i = new Intent(LoginActivity.this, );
                    //startActivity(i);
                }
            }
        });

        btnRegistrar.setOnClickListener(view -> {
            /*
                bbdd.agregarUsuario(usuario.getText().toString(), contrasena.getText().toString());
                Toast.makeText(getApplicationContext(), "Usuario dado de alta", Toast.LENGTH_LONG).show();
             */
            Intent i = new Intent(LoginActivity.this, registro.class);
            startActivity(i);
        });


    }
}