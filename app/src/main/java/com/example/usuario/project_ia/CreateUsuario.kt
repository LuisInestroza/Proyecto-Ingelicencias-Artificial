package com.example.usuario.project_ia


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.usuario.project_ia.models.User

import java.util.HashMap


class CreateUsuario : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_usuario)



        val btnAddUser = findViewById<Button>(R.id.btnCrearCuenta)
        val tvUserName = findViewById<TextView>(R.id.etNombreUsuario)
        val tvCorreo = findViewById<TextView>(R.id.etCorreoUsuario)
        val tvPassword = findViewById<TextView>(R.id.etPassword)


        btnAddUser.setOnClickListener {
            val users = tvUserName.text
            if (users.isNotEmpty()){

                val user = User(users.toString(), tvCorreo.text.toString(), tvPassword.text.toString())
                saveUser(user)
            }else{
                Toast.makeText(this, "Debes de llenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUser(user: User){
        val newUser = HashMap<String, Any>()
        newUser["userName"] = user.usuario
        newUser["correo"] = user.correo
        newUser["password"] = user.contraseña


    }




}
