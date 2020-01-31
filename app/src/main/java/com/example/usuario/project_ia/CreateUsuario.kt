package com.example.usuario.project_ia


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import java.util.HashMap


class CreateUsuario : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_usuario)



        val btnAddUser = findViewById<Button>(R.id.btnCrearCuenta)
        val tvUserName = findViewById<TextView>(R.id.etNombreUsuario)
        val tvCorreo = findViewById<TextView>(R.id.etCorreoUsuario)
        val tvPassword = findViewById<TextView>(R.id.etPassword)


    }






}
