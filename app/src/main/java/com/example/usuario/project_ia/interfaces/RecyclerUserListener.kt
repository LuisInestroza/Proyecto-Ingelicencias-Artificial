package com.example.usuario.project_ia.interfaces

import com.example.usuario.project_ia.models.User

interface RecyclerUserListener {
    fun onClick(user: User, position: Int)
    fun onLongClick(user: User, position: Int)
}