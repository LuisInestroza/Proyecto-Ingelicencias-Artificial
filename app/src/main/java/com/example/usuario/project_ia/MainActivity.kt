package com.example.usuario.project_ia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Llamar el activity para crear una cuenta para el usuario
        btnCuenta.setOnClickListener {
            if (etUsuario.text.toString() != "" && etPassword.text.toString() != ""){
                Toast.makeText(this, "Esta cuenta no existe", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Crear Tu Cuenta", Toast.LENGTH_SHORT).show()
            }else{
            startActivity(Intent(this, CreateUsuario::class.java))
            }
        }

        // Llamar el activity Menu
        // Llamar el activity para crear una cuenta para el usuario
        btnIniciar.setOnClickListener {
            startActivity(Intent(this, Menu::class.java))
        }

    }
}
