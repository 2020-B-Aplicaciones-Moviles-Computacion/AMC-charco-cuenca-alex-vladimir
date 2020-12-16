package com.example.a02_android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class RecyclerViewAdaptadorNomCed(
    private val listaEntrenador :List<BEntrenador>,
    private val contexto: Class<*>,
    private val recycleView:androidx.recyclerview.widget.RecyclerView
    ):androidx.recyclerview.widget.
    RecyclerView.Adapter<RecyclerViewAdaptadorNomCed.MyViewHolder>()
{
    inner class MyViewHolder(view: View):
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
            val nombreTextView:TextView
            val cedulaTextView:TextView
            val likesTextView:TextView
            val accionButon:Button
            var numlikes=0

        init{
            nombreTextView=view.findViewById(R.id.tv_nombre)
            cedulaTextView=view.findViewById(R.id.tv_cedula)
            likesTextView=view.findViewById(R.id.tv_likes)
            accionButon=view.findViewById(R.id.bt_likes)
            accionButon.setOnClickListener{
                this.anadirLike()
            }
        }

        fun anadirLike(){
            this.numlikes++
            likesTextView.text=this.numlikes.toString()
        }
    }

    override fun onCreateViewHolder(
        //Aqui definiremos que interfaz vamos a utilizar, dependiendeo de esto la pasaremos
        parent: ViewGroup,
        viewType: Int)
    : RecyclerViewAdaptadorNomCed.MyViewHolder
    {
        val itemView=LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_layout,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaEntrenador.size
        //Para retornar el numero de entrenadores dentro de la lista

    }

    override fun onBindViewHolder(
        holder: RecyclerViewAdaptadorNomCed.MyViewHolder,
        position: Int
    ) {
        //Cuando se cargue la interfaz, podremos empezar a llenar los datos
        val user=listaEntrenador[position]
        holder.nombreTextView.text=user.name
        holder.cedulaTextView.text=user.desc
        holder.accionButon.text="Like ${user.name}"
        holder.likesTextView.text="0"
    }
}