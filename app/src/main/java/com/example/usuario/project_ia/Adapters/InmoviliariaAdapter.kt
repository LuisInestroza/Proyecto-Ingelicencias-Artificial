package com.example.usuario.project_ia.Adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.usuario.project_ia.AddInmoviliaria
import com.example.usuario.project_ia.Clases.Inmoviliaria
import com.example.usuario.project_ia.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_inmoviliaria.*
import java.io.IOException
import java.util.*


class InmoviliariaAdapter (val mCtx: Context, val layoutId: Int, val inmoviliariaList:List<Inmoviliaria>)
    :ArrayAdapter<Inmoviliaria>(mCtx, layoutId, inmoviliariaList){



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{


//        Declaracion de variables

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutId, null)


//        Declaracion de variables para mostrar en el adapter

        val categoria = view.findViewById<TextView>(R.id.tvCategoriaShow)
        val precio = view.findViewById<TextView>(R.id.tvPrecioShow)
        val descripcion = view.findViewById<TextView>(R.id.tvDescripcionShow)
        val imagen = view.findViewById<ImageView>(R.id.ivInmuebleShow)

        // Declaracion de variables para los botones de eliminar y actualizar
        val btnActualizar = view.findViewById<TextView>(R.id.btnUpdateInmueble)
        val btnEliminar = view.findViewById<TextView>(R.id.btnEliminarInmueble)



        val inmoviliaria = inmoviliariaList[position]

        categoria.text = inmoviliaria.categoria
        precio.text = inmoviliaria.precio.toString()
        descripcion.text = inmoviliaria.descripcion



        // Mostrar la imagen
        Picasso.with(mCtx).load(inmoviliaria.imagem).into(imagen)

        // Acciones de eliminar y actualizar
        btnEliminar.setOnClickListener {
            elimininarInmoviliario(inmoviliaria)
        }




        return view

    }

    private fun elimininarInmoviliario(inmoviliaria: Inmoviliaria){
        //        Crear un alert dialog para confirmar la eliminación
//        de un registro
        val builder = AlertDialog.Builder(mCtx)
        builder.setMessage("¿Desea eliminar este registro?")
        builder.setPositiveButton("Borrar", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                // Conexion a firebase
                val conexionFirebase = FirebaseDatabase.getInstance().getReference("Inmoviliarios")
//              Elinimar el registro a la base de datos.
                conexionFirebase.child(inmoviliaria.id).removeValue()
                Toast.makeText(mCtx, "inmoviliario Eliminado", Toast.LENGTH_SHORT).show()
            }


        })
        builder.setNegativeButton("Cancelar", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
//                Toast.makeText(mCtx, "Cancelado", Toast.LENGTH_SHORT).show()

            }
        })

//        Motrar el builder de eliminar para confirmar al usuario
        val alerta = builder.create()
        alerta.show()
    }









}