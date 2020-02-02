package com.example.usuario.project_ia


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.usuario.project_ia.Clases.Inmoviliaria
import com.google.firebase.database.FirebaseDatabase


import kotlinx.android.synthetic.main.activity_add_inmoviliaria.*

import kotlin.collections.HashMap

class AddInmoviliaria : AppCompatActivity() {



//    Iniciar variables de los elementos del activity
    lateinit var editTextPrecio: EditText
    lateinit var editTextCategoria: EditText
    lateinit var editTextDescripcion: EditText
    lateinit var imageViewImagen: ImageView
    lateinit var buttonGuardarInmoviliario: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inmoviliaria)

//        Llamar los elementos de los activy
        editTextPrecio = findViewById(R.id.etPrecio)
        editTextCategoria = findViewById(R.id.etCategoria)
        editTextDescripcion = findViewById(R.id.etDescripcion)
        imageViewImagen = findViewById(R.id.ivInmueble)
        buttonGuardarInmoviliario = findViewById(R.id.btnAddInmueble)


//        Agregar la funcion de agregar un inmoviliario a la base de datos
        buttonGuardarInmoviliario.setOnClickListener {
            agregarInmoviliario()
        }

    }

    private fun agregarInmoviliario(){

//        Declarar variables de para trabajar

        val precio = editTextPrecio.text.toString().toInt()
        val categoria = editTextCategoria.text.toString()
        val descripcion = editTextDescripcion.text.toString()
        val imagen = imageViewImagen.toString()

//        TODO() Implementar conxion a la camara y galeria del movil
//        TODO() Implementar registros de imagenes a la base de datos firebase


//        validar que los campos no esten vacios
        if (precio == null || categoria.isEmpty() || descripcion.isEmpty()){

            editTextPrecio.error = "Debes ingresar el precio"
            editTextCategoria.error = "Debes ingresar la categoria"
            editTextDescripcion.error = "Debes de ingresar la descripcion"
            return
        }else{

//            En el caso que el usuario haya llenado los campos
//            Insertar los nuevos datos a firebase

            val conexionFirebase = FirebaseDatabase.getInstance().getReference("Inmoviliarios")
            val inmoviliarioID = conexionFirebase.push().key
            val nuevoInmoviliario = Inmoviliaria(inmoviliarioID.toString(), categoria, precio, descripcion, imagen)

//            Hacer la insercion de a la base de datos
            conexionFirebase.child(inmoviliarioID.toString()).setValue(nuevoInmoviliario).addOnCompleteListener {
                Toast.makeText(this, "Inmoviliario Registrado", Toast.LENGTH_SHORT).show()
            }
        }





    }









}
