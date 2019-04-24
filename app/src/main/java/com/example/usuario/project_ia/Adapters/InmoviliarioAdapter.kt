package com.example.usuario.project_ia.Adapters

import android.support.v7.widget.RecyclerView
import android.view.*
import com.example.usuario.project_ia.R
import com.example.usuario.project_ia.interfaces.RecycleInmoviliarioListener
import com.example.usuario.project_ia.models.Inmobiliario
import kotlinx.android.synthetic.main.template_usuario_list.view.*

class InmoviliarioAdapter(private val inmoviliarios: List<Inmobiliario>, private val listener: RecycleInmoviliarioListener)
    : RecyclerView.Adapter<InmoviliarioAdapter.ViewHolderInmoviliario>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InmoviliarioAdapter.ViewHolderInmoviliario{

        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_inmobiliario_items, parent, false)

        return ViewHolderInmoviliario(view)
    }

   override fun getItemCount() = inmoviliarios.size

    override fun onBindViewHolder(holder: InmoviliarioAdapter.ViewHolderInmoviliario, position: Int) = holder.bind(inmoviliarios[position], listener)


    class ViewHolderInmoviliario(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(inmobiliario: Inmobiliario, listener: RecycleInmoviliarioListener) = with(itemView){
            tvUsuario.text = inmobiliario.toString()
            tvCorreo.text = inmobiliario.toString()
            tvPassword.text = inmobiliario.toString()

            // Implementar los eventos
            setOnClickListener { listener.onClikc(inmobiliario, adapterPosition) }
        }
    }
}