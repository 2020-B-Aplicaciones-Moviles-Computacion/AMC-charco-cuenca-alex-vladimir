package com.example.a02_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecycleView : AppCompatActivity() {
    var totalLikes=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_g_recycle_view)

        val  listaEntrenador = arrayListOf<BEntrenador>()
        val ligaPokemon = liga("Kanto","Liga Kanto")
        listaEntrenador.add(
            BEntrenador("Alexander",
            "Level 1",
                ligaPokemon
            )
        )
        listaEntrenador.add(
            BEntrenador("Cosme",
                "Level 2",
                ligaPokemon
            )
        )

        val rv_ViewEntrenadores = findViewById<RecyclerView>(
            R.id.rv_entrenadores
        )

        this.iniciarRecycleView(
            listaEntrenador,
            this,
            rv_ViewEntrenadores
        )


    }

    fun iniciarRecycleView(
        lista:List<BEntrenador>,
        actividad:GRecycleView,
        recyclerView: androidx.recyclerview.widget.RecyclerView
    ){
        val adaptador=RecyclerViewAdaptadorNomCed(
            lista,actividad,recyclerView
        )

        recyclerView.adapter=adaptador
        //animaciones
        recyclerView.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager=androidx.recyclerview.widget.LinearLayoutManager(actividad)

    }

    fun increaseTotalLikes(){
        totalLikes=totalLikes+1
        val tv_likes=findViewById<TextView>(R.id.tv_totalLikes)
        tv_likes.text=totalLikes.toString()
    }
}