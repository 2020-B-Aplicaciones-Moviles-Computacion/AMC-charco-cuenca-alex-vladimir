package com.example.proyecto_2bim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var times:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        times++;
        Log.i("travel","Times: ${times}")

        val navbar=findViewById<BottomNavigationView>(R.id.navbar)
        //navbar.isSelected=false
        navbar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.go_search -> {
                    irActividad(Search::class.java)
                    Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show()
                }
                R.id.go_back -> finish()
                R.id.go_comics -> {
                    irActividad(Library::class.java)
                }
                R.id.go_favorites -> {
                    irActividad(Favoritos::class.java)
                }
                R.id.go_settings -> {
                    irActividad(Settings::class.java)
                }
            }
            true
        }

        //MAPAS
        val btnMap=findViewById<Button>(R.id.btn_maps2)
        btnMap.setOnClickListener {
            irActividad(MapContainer::class.java)
        }

        val btnFrag=findViewById<Button>(R.id.btn_frag)
        btnMap.setOnClickListener {
            irActividad(MapContainer::class.java)
            
        }
    }


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

