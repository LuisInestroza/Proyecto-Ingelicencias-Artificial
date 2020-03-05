package com.example.usuario.project_ia


import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.usuario.project_ia.Clases.Usuario
import com.google.firebase.database.FirebaseDatabase


class CreateUsuario : AppCompatActivity() {


//    Iniciar variables

    lateinit var editTextNombre: EditText
    lateinit var editTextCorreo: EditText
    lateinit var editTextPassword: EditText
    lateinit var buttonCreate: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_usuario)

//        Llamar a los elementos del activity create_usuario

        editTextNombre = findViewById(R.id.editTextNombreUsuario)
        editTextCorreo = findViewById(R.id.editTextCorreoUsuario)
        editTextPassword = findViewById(R.id.editTextPasswordUsuario)
        buttonCreate = findViewById(R.id.buttonCrearCuenta)

        // Verificar que haya conexión a internet

        val cm = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val coneccionInfomacion = cm.activeNetworkInfo

        if (coneccionInfomacion != null && coneccionInfomacion.isConnected){

//            Toast.makeText(baseContext, "Conectado", Toast.LENGTH_SHORT).show()
        }

        else {
            Toast.makeText(baseContext, "NO HAY CONEXIÓN A INTERNET", Toast.LENGTH_SHORT).show()
        }



        buttonCreate.setOnClickListener {
            crearNuevoUsuario()
        }

    }


//    Funcion de crear un nuevo usuario
    private fun crearNuevoUsuario(){


//    Declaracion de variables oara trabajar

    val nombreUsuario = editTextNombre.text.toString().trim()
    val email = editTextCorreo.text.toString().trim()
    val password = editTextPassword.text.toString().trim()


//    Verificar que los campos no esten vacios y mostrar el error

    if (nombreUsuario.isEmpty() || email.isEmpty() || password.isEmpty()){

        editTextNombre.error = "Ingrese su nombre de usuario"
        editTextCorreo.error = "Ingrese su correo electronico"
        editTextPassword.error ="Igrese su contraseña"
        return
    }else{

//        Si los campos no estan vacios resgistrar el nuevo usuario en la base de datos

//        Declaracion de variables
        val conexionFirebase = FirebaseDatabase.getInstance().getReference("Usuarios")
        val usuarioID = conexionFirebase.push().key
        val nuevoUsuario = Usuario(usuarioID.toString(), nombreUsuario, email, password)

//        Conexion a firebase
        conexionFirebase.child(usuarioID.toString()).setValue(nuevoUsuario).addOnCompleteListener {
            Toast.makeText(this, "Datos Registrados", Toast.LENGTH_SHORT).show()
        }

    }


}

}
