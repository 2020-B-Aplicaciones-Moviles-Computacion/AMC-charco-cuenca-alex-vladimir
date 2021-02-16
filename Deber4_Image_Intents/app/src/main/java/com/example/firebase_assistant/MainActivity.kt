package com.example.firebase_assistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    val COD_SESION_INICIO=102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnImages=findViewById<Button>(R.id.btn_intent_images)

        btnImages.setOnClickListener {
            irActividad(EIntents_Images::class.java)
        }


    }




    //Ir Actividad
    fun irActividad(

        clase:Class<*>,
        param:ArrayList<Pair<String,*>>?=null,
        codigo:Int? =null
    ){
        val intentEx=Intent(
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
            }
        }

        if(codigo!=null){
            startActivityForResult(intentEx,codigo)
        }else{
            startActivity(intentEx)
        }
    }



    }














