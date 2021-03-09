package com.example.proyecto_2bim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList

class Favoritos : AppCompatActivity() {
    var times:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        times++;
        Log.i("travel","Times: ${times}")

        val bnv_navbar=findViewById<BottomNavigationView>(R.id.navbar_favorites)
        bnv_navbar.selectedItemId=R.id.go_favorites
        bnv_navbar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.go_search -> {
                    irActividad(Search::class.java)
                }
                R.id.go_back -> {
                    finish()
                }
                R.id.go_comics -> {
                    irActividad(Library::class.java)
                }
                R.id.go_favorites -> {
                }
                R.id.go_settings -> {
                    irActividad(Settings::class.java)
                }
            }
            true
        }
    }
    fun irActividad(
            clase:Class<*>,
            param: ArrayList<Pair<String, *>>?=null,
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