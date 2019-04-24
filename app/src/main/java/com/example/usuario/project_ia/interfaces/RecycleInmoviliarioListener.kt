package com.example.usuario.project_ia.interfaces

import com.example.usuario.project_ia.models.Inmobiliario

interface RecycleInmoviliarioListener {

    fun onClikc(inmobiliario: Inmobiliario, position: Int)
    fun onLongClick(inmobiliario: Inmobiliario, position: Int)
}