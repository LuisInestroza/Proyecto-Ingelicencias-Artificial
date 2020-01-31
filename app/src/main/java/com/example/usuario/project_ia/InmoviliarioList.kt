package com.example.usuario.project_ia

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.usuario.project_ia.Adapters.InmoviliarioAdapter
import com.example.usuario.project_ia.interfaces.RecycleInmoviliarioListener
import com.example.usuario.project_ia.models.Inmobiliario

import kotlinx.android.synthetic.main.activity_inmoviliario_list.*

class InmoviliarioList : AppCompatActivity() {


    private val inmuebleList: ArrayList<Inmobiliario> = ArrayList()
    private lateinit var adapter: InmoviliarioAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inmoviliario_list)




        val layoutManager = LinearLayoutManager(this)
        adapter = InmoviliarioAdapter(inmuebleList, object: RecycleInmoviliarioListener{
            override fun onClikc(inmobiliario: Inmobiliario, position: Int) {
                Toast.makeText(applicationContext, "${inmobiliario.categoria}", Toast.LENGTH_SHORT).show()

            }

            override fun onLongClick(inmobiliario: Inmobiliario, position: Int) {
                TODO("No Implementado")
            }
        })

        rvInmobiliario.setHasFixedSize(true)
        rvInmobiliario.layoutManager = layoutManager
        rvInmobiliario.itemAnimator = DefaultItemAnimator()
        rvInmobiliario.adapter = adapter


    }


}
