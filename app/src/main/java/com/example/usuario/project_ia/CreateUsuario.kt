package com.example.usuario.project_ia


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.usuario.project_ia.models.User
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap


class CreateUsuario : AppCompatActivity() {

    // Variable para firebase
    private val store: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var proyectoBD: CollectionReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_usuario)


        // Establecer la conexion

        proyectoBD = store.collection("usuarios")

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

        proyectoBD.add(newUser)
            .addOnCompleteListener {
                Toast.makeText(this, "Usuario Guardado", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Usuario No Guardado", Toast.LENGTH_SHORT).show()
            }
    }




}
