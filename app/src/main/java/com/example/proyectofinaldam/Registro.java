package com.example.proyectofinaldam;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    Button btnRegistro;
    EditText usuario, contrasena, contrasena2;
    TextView titulo;
    String usr, cambioContra;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Bundle recibo = getIntent().getExtras();
        assert recibo != null;
        usr = recibo.getString("usuario");
        cambioContra = recibo.getString("cambioContrasena");

        final bbddHelper bbdd = new bbddHelper(getApplicationContext());

        btnRegistro = findViewById(R.id.btnRegistrar);
        titulo = findViewById(R.id.titulo);
        usuario = findViewById(R.id.tvUsuario);
        contrasena = findViewById(R.id.tvContrasena);
        contrasena2 = findViewById(R.id.tvContrasena2);

        if (cambioContra.equals("si")){
            titulo.setText("Cambio de Contraseña");
            usuario.setText(usr);
            usuario.setEnabled(false);
            btnRegistro.setText("Cambiar Contraseña");
        }

        btnRegistro.setOnClickListener(view ->{
            if(usuario.getText().toString().equals("")){
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_LONG).show();
            }
            else if (contrasena.getText().toString().length()<6){
                Toast.makeText(this, "La contraseña es demasiado corta, debe tener mínimo 6 caracteres", Toast.LENGTH_LONG).show();
            }
            else if (contrasena.getText().toString().equals(contrasena2.getText().toString())){
                if (cambioContra.equals("si")){
                    bbdd.cambiarContrasena(usr, contrasena.getText().toString());
                    Toast.makeText(this, "Contraseña actualizada correctamente", Toast.LENGTH_LONG).show();
                }
                else {
                    if (!bbdd.existeUsuario(usuario.getText().toString(), this)) {
                        bbdd.agregarUsuario(usuario.getText().toString(), contrasena.getText().toString());
                        Intent i = new Intent(Registro.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(this, "Ya hay otro usuario con ese nombre", Toast.LENGTH_LONG).show();
                    }
                }
            }
            else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            }
        } );
    }
}