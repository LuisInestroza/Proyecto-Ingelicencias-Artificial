package com.example.usuario.project_ia.Adapters



import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.usuario.project_ia.R
import com.example.usuario.project_ia.interfaces.RecyclerUserListener
import com.example.usuario.project_ia.models.User
import kotlinx.android.synthetic.main.template_inmobiliario_items.view.*


class UserAdapter(private val users: List<User>, private val listener: RecyclerUserListener)
    :RecyclerView.Adapter<UserAdapter.ViewHolderUser>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolderUser {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_usuario_list, parent, false)

        return ViewHolderUser(view)

    }

    override fun getItemCount()= users.size

    override fun onBindViewHolder(holder: UserAdapter.ViewHolderUser, position: Int)  =holder.bind(users[position], listener)


    class ViewHolderUser(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(user: User, listener: RecyclerUserListener) = with(itemView){


            tvCategoria.text = user.toString()
            tvPrecio.text = user.toString()
            tvDescripcion.text = user.toString()


            // Implementar los eventos
            setOnClickListener { listener.onClick(user, adapterPosition) }
        }
    }


}