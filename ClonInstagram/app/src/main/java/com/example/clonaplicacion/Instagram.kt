package com.example.clonaplicacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Instagram : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instagram)

        val btn_iv_go=findViewById<ImageView>(R.id.iv_btn_go)
        val btn_iv_heart=findViewById<ImageView>(R.id.iv_btn_heart)
        val btn_iv_add=findViewById<ImageView>(R.id.iv_btn_add)

        btn_iv_go.isClickable
        btn_iv_go.setOnClickListener {
            Toast.makeText(this,"Go Clicked",LENGTH_SHORT).show()
        }

        btn_iv_heart.setOnClickListener {
            Toast.makeText(this,"Heart Clicked",LENGTH_SHORT).show()
        }

        btn_iv_add.setOnClickListener {
            Toast.makeText(this,"Add Clicked",LENGTH_SHORT).show()
        }

        //Framgnets Logic
/*
        val navMenu=findViewById<BottomNavigationView>(R.id.bnv_navbar)
        val navController=findNavController(R.id.mainFragment)

        navMenu.setupWithNavController(navController);
*/

        val rvPost=findViewById<RecyclerView>(R.id.rv_posts)

        val listaPost= arrayListOf<String>()
        listaPost.add("250");
        listaPost.add("345");
        listaPost.add("478");


        this.iniciarRecycleView(
                listaPost,
                this,
                rvPost
        )

    }



    //FUNCIONES
    fun irActividad(
        clase:Class<*>
    ){
        val intentEx= Intent(
            this,
            clase
        )
        startActivity(intentEx)
    }

    fun iniciarRecycleView(
            lista:List<String>,
            actividad:Instagram,
            recyclerView: androidx.recyclerview.widget.RecyclerView
    ){
        val adaptador=RecViewAdapter(
                lista,actividad,recyclerView
        )

        recyclerView.adapter=adaptador
        //animaciones
        recyclerView.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager=androidx.recyclerview.widget.LinearLayoutManager(actividad)

    }

}