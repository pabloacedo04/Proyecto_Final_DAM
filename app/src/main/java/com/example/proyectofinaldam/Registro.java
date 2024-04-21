package com.example.proyectofinaldam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    Button btnRegistro;
    EditText usuario, contrasena, contrasena2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final bbddHelper bbdd = new bbddHelper(getApplicationContext());

        btnRegistro = findViewById(R.id.btnRegistrar);
        usuario = findViewById(R.id.tvUsuario);
        contrasena = findViewById(R.id.tvContrasena);
        contrasena2 = findViewById(R.id.tvContrasena2);

        btnRegistro.setOnClickListener(view ->{
            if(usuario.getText().toString().equals("")){
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_LONG).show();
            }
            else if (contrasena.getText().toString().length()<6){
                Toast.makeText(this, "La contraseña es demasiado corta, debe tener mínimo 6 caracteres", Toast.LENGTH_LONG).show();
            }
            else if (contrasena.getText().toString().equals(contrasena2.getText().toString())){
                if(!bbdd.existeUsuario(usuario.getText().toString(), this)){
                    bbdd.agregarUsuario(usuario.getText().toString(), contrasena.getText().toString());
                    Intent i = new Intent(Registro.this, LoginActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(this, "Ya hay otro usuario con ese nombre", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            }
        } );
    }
}