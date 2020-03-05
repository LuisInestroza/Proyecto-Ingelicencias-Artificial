package com.example.usuario.project_ia


import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.usuario.project_ia.Clases.Inmoviliaria
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask


import kotlinx.android.synthetic.main.activity_add_inmoviliaria.*
import java.io.IOException
import java.util.*

class AddInmoviliaria : AppCompatActivity() {



//    Iniciar variables de los elementos del activity
    lateinit var editTextPrecio: EditText
    lateinit var editTextCategoria: EditText
    lateinit var editTextDescripcion: EditText
    lateinit var imageViewImagen: ImageView
    lateinit var buttonGuardarInmoviliario: Button

//    Permisos para la camara
    private val PERMISSION_CODE = 1000
    var imagi: Uri? = null
    private val IMAGEN_CAPTURE_CODE = 1001


//    Guardar la imagen en la store
    private var imagePreview: ImageView? = null
    private val PICK_IMAGE_REQUEST = 1
//    private var filePath: Uri? = null
    private var firebaseStorage: FirebaseStorage? = null
    private var storageReference: StorageReference? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inmoviliaria)

//        Llamar los elementos de los activy
        editTextPrecio = findViewById(R.id.etPrecio)
        editTextCategoria = findViewById(R.id.etCategoria)
        editTextDescripcion = findViewById(R.id.etDescripcion)
        imageViewImagen = findViewById(R.id.ivInmueble)
        buttonGuardarInmoviliario = findViewById(R.id.btnAddInmueble)

        // Verificar que haya conexión a internet

        val cm = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val coneccionInfomacion = cm.activeNetworkInfo

        if (coneccionInfomacion != null && coneccionInfomacion.isConnected){

            Toast.makeText(baseContext, "Conectado", Toast.LENGTH_SHORT).show()
        }

        else {
            Toast.makeText(baseContext, "NO HAY CONEXIÓN A INTERNET", Toast.LENGTH_SHORT).show()
        }

//        Conectar a firebase store
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference



//        Agregar la funcion de agregar un inmoviliario a la base de datos
        buttonGuardarInmoviliario.setOnClickListener {
            agregarInmoviliario()
        }

//        Hacer la conexion con la camara
        btnCamara.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                    val permiso = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permiso, PERMISSION_CODE)

                }else{

                    abrirCamara()
                }
            }else{

                abrirCamara()
            }
        }

//        Hacer conexion  a la galeria
        btnGaleria.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ){

                    val permiso = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permiso, PERMISSION_CODE_GALERY)

                }else{

                    abrirGaleria()
                }
            }else{
                abrirGaleria()
            }

        }

    }

    private fun agregarInmoviliario(){

//        Declarar variables de para trabajar

        val precio = editTextPrecio.text.toString().toInt()
        val categoria = editTextCategoria.text.toString().trim()
        val descripcion = editTextDescripcion.text.toString().trim()


        if(imagi != null){
            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            ref?.putFile(imagi!!)?.addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot> {
                Toast.makeText(this, "Image Uploaded", Toast.LENGTH_SHORT).show()

                if (categoria.isEmpty() || descripcion.isEmpty()){


                    editTextCategoria.error = "Debes ingresar la categoria"
                    editTextDescripcion.error = "Debes de ingresar la descripcion"
                    return@OnSuccessListener

                }else{

//                   Obtener la URL

                     ref?.downloadUrl?.addOnSuccessListener {
                        Log.d("RegisterActivity", "imagen url  $it")

                         val url = it

//                       n el caso que el usuario haya llenado los campos
//                       Insertar los nuevos datos a firebase

                         val conexionFirebase = FirebaseDatabase.getInstance().getReference("Inmoviliarios")
                         val inmoviliarioID = conexionFirebase.push().key
                         val nuevoInmoviliario = Inmoviliaria(inmoviliarioID.toString(), categoria, precio, descripcion, url.toString())

//                          Hacer la insercion de a la base de datos
                         conexionFirebase.child(inmoviliarioID.toString()).setValue(nuevoInmoviliario).addOnCompleteListener {
                             Toast.makeText(this, "Inmoviliario Registrado", Toast.LENGTH_SHORT).show()
                         }
                     }



                }


            })?.addOnFailureListener(OnFailureListener { e ->
                Toast.makeText(this, "Image Uploading Failed " + e.message, Toast.LENGTH_SHORT).show()
            })

            //        validar que los campos no esten vacios

        }else{
            Toast.makeText(this, "Please Select an Image", Toast.LENGTH_SHORT).show()
        }








    }
//     Funcion para abrir la camara del dispositivo
    private fun abrirCamara(){

    val valor = ContentValues()
    valor.put(MediaStore.Images.Media.TITLE, "Nueva Imagen")
    valor.put(MediaStore.Images.Media.DESCRIPTION, "De la camara")
    imagi = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, valor)

    val camara= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    camara.putExtra(MediaStore.EXTRA_OUTPUT, imagi)

    startActivityForResult(camara, IMAGEN_CAPTURE_CODE)


}


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when(requestCode){
            PERMISSION_CODE ->{
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    abrirGaleria()

                }else{
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE_GALERY){
            ivInmueble.setImageURI(imagi)
            ivInmueble.setImageURI(data?.data)

            if(data == null || data.data == null){
                return
            }
            imagi = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imagi)
                imagePreview?.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }


        }

    }


//    Funcionalidad e acceder a la galeria


    private fun abrirGaleria(){

        val intento = Intent(Intent.ACTION_PICK)
        intento.type ="image/*"
        startActivityForResult(intento, IMAGE_PICK_CODE_GALERY)

    }
    companion object{

    private val IMAGE_PICK_CODE_GALERY = 1000
    private val PERMISSION_CODE_GALERY = 1001
}






}
