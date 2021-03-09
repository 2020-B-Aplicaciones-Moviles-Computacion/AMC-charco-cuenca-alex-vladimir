package com.example.proyecto_2bim

import android.content.Context
import android.content.Intent
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
    var times:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        times++;
        Log.i("travel","Times: ${times}")
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
                    finish()
                    irActividad(ComicDetail::class.java)
                }
                R.id.go_favorites -> {
                    finish()
                    irActividad(Favoritos::class.java)
                }
                R.id.go_settings -> {
                    finish()
                    irActividad(Settings::class.java)
                }
            }
            true
        }

        val RecViewSearchMangas:RecyclerView=findViewById(R.id.rv_search_manga)
        val listComics= arrayListOf<String>("a","b","c","d","e")

        iniciarRecycleView(
            listComics,
            this,
            RecViewSearchMangas
        )
    }


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

    //
    fun irActividad(
            clase:Class<*>,
            param:ArrayList<Pair<String,*>>?=null,
            codigo:Int? =null
    ){
        val intentEx= Intent(
                this,
                clase
        )

        if(param!=null){
            param.forEach {
                var nombreVar = it.first
                var valorVar=it.second

                var tipoDato=false

                tipoDato=it.second is String

                if(tipoDato==true){
                    intentEx.putExtra(nombreVar,valorVar as String)
                }
                tipoDato=it.second is Int

                if(tipoDato==true){
                    intentEx.putExtra(nombreVar,valorVar as Int)
                }
                /*
                tipoDato=it.second is Parcelable

                if(tipoDato==true){
                    intentEx.putExtra(nombreVar,valorVar as Parcelable)
                }
*/
            }
        }

        if(codigo!=null){
            startActivityForResult(intentEx,codigo)
        }else{
            startActivity(intentEx)
        }
    }

}