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
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_crud_inmoviliario.*


class CrudInmoviliario : AppCompatActivity() {

    val CAMERA_REQUEST = 0



    // Conectar a firebase
    private val store: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var proyectoBD: CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_inmoviliario)



        // Establecer la coleccion a utilizar
        proyectoBD = store.collection("inmobiliario")



        val tvCategoria = findViewById<EditText>(R.id.etCategoria)
        /*
        val tvPrecio = findViewById<EditText>(R.id.etPrecio)
        val tvDescripcion = findViewById<EditText>(R.id.etDescripcion)
        val imImagen = findViewById<ImageView>(R.id.ivInmueble)
            */


        btnUpdateInmueble.setOnClickListener {
            val categoria = tvCategoria.text
            if(categoria.isNotEmpty()){
                val crudInmoviliario = CrudInmoviliario()
                updateInmoviliario(crudInmoviliario)
            }
            else{
                Toast.makeText(this, "Los datos son se actualizaron", Toast.LENGTH_SHORT).show()
            }
        }

        // llamar a la camara mediante el boton Imagin Button
        btnCamaraCRUD.setOnClickListener {
            val callCamaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (callCamaraIntent.resolveActivity(packageManager) != null){
                startActivityForResult(callCamaraIntent, CAMERA_REQUEST)
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

    private fun updateInmoviliario(crudInmoviliario: CrudInmoviliario){
        val actualizar = HashMap<String, Any>()
        actualizar["categoria"] = crudInmoviliario
        actualizar["precio" ] = crudInmoviliario
        actualizar["descripcion"] = crudInmoviliario
        actualizar["imagen"] = crudInmoviliario

        }

    }

