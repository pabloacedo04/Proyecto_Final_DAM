package com.example.proyectofinaldam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usuario, contrasena;
    Button btnRegistrar, btnLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.textLogin);
        contrasena = findViewById(R.id.textContrasena);
        btnRegistrar = findViewById(R.id.btnRegistro);
        btnLogin = findViewById(R.id.btnLogin);

        final bbddHelper bbdd= new bbddHelper(getApplicationContext());

        btnRegistrar.setOnClickListener(view -> {
            Intent i = new Intent(LoginActivity.this, registro.class);
            startActivity(i);
            //bbdd.agregarUsuario(usuario.getText().toString(), contrasena.getText().toString());
            //Toast.makeText(getApplicationContext(), "Usuario dado de alta", Toast.LENGTH_LONG).show();
        });

        btnLogin.setOnClickListener(view -> {

        });
    }
}