package com.example.usuario.project_ia

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.ListView
import android.widget.Toast
import com.example.usuario.project_ia.Clases.Inmoviliaria
import com.google.firebase.database.*


import kotlinx.android.synthetic.main.activity_usuario_list.*

class UsuarioList : AppCompatActivity() {

//    Declaracion de variables
    lateinit var ref: DatabaseReference
    lateinit var inmoviliariaList: MutableList<Inmoviliaria>
    lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario_list)


//        Listar los datos en los view

        inmoviliariaList = mutableListOf()
        listView = findViewById(R.id.listInmueblesShow)
        ref = FirebaseDatabase.getInstance().getReference("Inmoviliarios")

//         Verificar que haya conexion a intenet
        val cm = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val coneccionInfomacion = cm.activeNetworkInfo

        if (coneccionInfomacion != null && coneccionInfomacion.isConnected){
        }
        else {
            Toast.makeText(baseContext, "NO HAY CONECCIÓN A INTERNET", Toast.LENGTH_SHORT).show()
        }


//        Listar los datos

        ref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    inmoviliariaList.clear()
                    for (e in p0.children){
                        val inmoviliarias = e.getValue(Inmoviliaria::class.java)
                        inmoviliariaList.add(inmoviliarias!!)
                    }

             val adapter = InmoviliaraAdapter(this@UsuarioList, R.layout.list_inmoviliario, inmoviliariaList)
                listView.adapter = adapter

                }
            }
        })





    }





}
