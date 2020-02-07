package com.example.usuario.project_ia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        // Llamar el activity para crear una cuenta para el usuario
        btnAgregarInmueble.setOnClickListener {
            startActivity(Intent(this, AddInmoviliaria::class.java))
        }


        btnListarInmueble.setOnClickListener {
            startActivity(Intent(this, InmoviliarioList::class.java))
        }



        // Llamar el activity para crear una cuenta para el usuario
        btnUsuarios.setOnClickListener {
            startActivity(Intent(this, UsuarioList::class.java))
        }
    }
}
