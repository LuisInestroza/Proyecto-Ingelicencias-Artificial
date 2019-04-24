package com.example.usuario.project_ia

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.usuario.project_ia.Adapters.UserAdapter
import com.example.usuario.project_ia.interfaces.RecyclerUserListener
import com.example.usuario.project_ia.models.User
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_usuario_list.*

class UsuarioList : AppCompatActivity() {


    // Variables de FIrebase
    private val store: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var protectoBD: CollectionReference
    private val userLits: ArrayList<User> = ArrayList()
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario_list)


        // Hacer referencia a la coleccion
        protectoBD = store.collection("usuarios")


        val layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(userLits, object : RecyclerUserListener {
            override fun onClick(user: User, position: Int) {
                Toast.makeText(applicationContext, "${user.usuario}", Toast.LENGTH_SHORT).show()

            }

            override fun onLongClick(user: User, position: Int) {
                TODO("No Implementado")

            }
        })
        rvUsuarios.setHasFixedSize(true)
        rvUsuarios.layoutManager = layoutManager
        rvUsuarios.itemAnimator = DefaultItemAnimator()
        rvUsuarios.adapter = adapter


    }





}
