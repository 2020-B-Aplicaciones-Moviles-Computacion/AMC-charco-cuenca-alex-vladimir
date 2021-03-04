package com.example.proyecto_2bim

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import com.example.proyecto_2bim.AdaptadoresRecycleViews.AdapterMangaSearch as AdapterSearchManga

class Search : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val bnv_navbar=findViewById<BottomNavigationView>(R.id.navbar_search)
        bnv_navbar.selectedItemId=R.id.go_search
        bnv_navbar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.go_search -> {
                }
                R.id.go_back -> {
                    finish()
                }
                R.id.go_comics -> {
                    Toast.makeText(this, "COMICS", Toast.LENGTH_SHORT).show()
                }
                R.id.go_favorites -> {
                    Toast.makeText(this, "FAVORITES", Toast.LENGTH_SHORT).show()
                }
                R.id.go_settings -> {
                    Toast.makeText(this, "SETTINGS", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

/*
        iniciarRecycleView(
            listComics,
            this,
            RecViewSearchMangas
        )
    */}


    fun iniciarRecycleView(
        lista:List<String>,
        actividad:Search,
        recyclerView: androidx.recyclerview.widget.RecyclerView
    ){
        val adaptador= AdapterSearchManga(
            lista,actividad,recyclerView
        )
        recyclerView.adapter=adaptador
        //Animations
        recyclerView.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager=androidx.recyclerview.widget.LinearLayoutManager(actividad)
    }

    fun getFirebase(){

        //Prueba Firebase
        val db= Firebase.firestore
        db.collection("comics")
                .get()
                .addOnSuccessListener {
                    for(doc in it){
                        Log.i("fb-firestore-search","Result: ${doc.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("fb-firestore-search","Result: ${it}")
                }

        val etSearch=findViewById<EditText>(R.id.et_search)

        etSearch.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                //Perform Code
                Toast.makeText(this, "CLICKED", Toast.LENGTH_SHORT).show()
                return@OnKeyListener true
            }
            false
        })
        val RecViewSearchMangas=findViewById<RecyclerView>(R.id.rv_search_manga_result)

        val listComics= arrayListOf<String>("a","b","c")
    }


}
