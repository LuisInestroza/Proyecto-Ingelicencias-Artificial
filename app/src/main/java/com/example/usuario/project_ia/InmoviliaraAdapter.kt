package com.example.usuario.project_ia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.usuario.project_ia.Clases.Inmoviliaria


class InmoviliaraAdapter (val mCtx: Context, val layoutId: Int, val inmoviliariaList:List<Inmoviliaria>)
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


        val inmoviliaria = inmoviliariaList[position]

        categoria.text = inmoviliaria.categoria
        precio.text = inmoviliaria.precio.toString()
        descripcion.text = inmoviliaria.descripcion


        return view

    }


}