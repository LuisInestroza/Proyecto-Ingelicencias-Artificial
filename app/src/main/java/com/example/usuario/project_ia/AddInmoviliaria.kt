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
import com.example.usuario.project_ia.models.Inmobiliario

import kotlinx.android.synthetic.main.activity_add_inmoviliaria.*

import kotlin.collections.HashMap

class AddInmoviliaria : AppCompatActivity() {


    val CAMERA_REQUEST = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inmoviliaria)

        // llamar a la camara mediante el boton Imagin Button
        btnCamara.setOnClickListener {
            val callCamaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (callCamaraIntent.resolveActivity(packageManager) != null){
                startActivityForResult(callCamaraIntent, CAMERA_REQUEST)
            }
        }






        // Establecer las variables

        val btnAddInmoviliaria = findViewById<Button>(R.id.btnAgregarInmueble)
        val tvCategoria = findViewById<EditText>(R.id.etCategoria)
        val tvPrecio = findViewById<EditText>(R.id.etPrecio)
        val tvDescripcion = findViewById<EditText>(R.id.etDescripcion)
        val imImagen = findViewById<ImageView>(R.id.ivInmueble)


        btnAddInmoviliaria.setOnClickListener {
            val categoria = tvCategoria.text

            if (categoria.isNotEmpty()){
                // Almacenar los datos a firebase
                val inmobiliario = Inmobiliario(categoria.toString(), tvPrecio.toString().toDouble(),  tvDescripcion.toString(), imImagen.toString())
                saveInmoviliario(inmobiliario)
            }else{
                Toast.makeText(this, "Debes de llenar los campos", Toast.LENGTH_SHORT).show()
            }

        }


    }


    // funcion override que se encarga de cargar la camara
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            CAMERA_REQUEST -> {
                if (resultCode == Activity.RESULT_OK && data != null){
                    ivInmueble.setImageBitmap(data.extras.get("data") as Bitmap)

                }
            }
            else ->{
                Toast.makeText(this, "Foto Capturada", Toast.LENGTH_SHORT).show()
            }
        }
    }





    // Funcion de guardar los datos
    private fun saveInmoviliario(inmobiliario: Inmobiliario){
        val newInmobiliario = HashMap<String, Any>()
        newInmobiliario["categoria"] = inmobiliario.categoria
        newInmobiliario["precio"] = inmobiliario.precio
        newInmobiliario["descripcion"] = inmobiliario.descripcion
        newInmobiliario["Imagen"] = inmobiliario.imagen


    }


}
