package com.example.firebase_assistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BFirestore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_firestore)
/*
        FIRESTORE TES
        val db=Firebase.firestore
        //COnsula de base de datos
        db.collection("usuario")
            .get()
            .addOnSuccessListener { result->
                for(document in result){
                    Log.i("fb-firestore","${document.id}->${document.data}")
                }
            }
            .addOnFailureListener { exception->
                Log.w("fb-firestore","Error",exception)
            }
        */

        //Botones
        val btnProduct=findViewById<Button>(R.id.btn_product)
        val btnRestaurant=findViewById<Button>(R.id.btn_restarurant)
        val btnOrden=findViewById<Button>(R.id.btn_ordenes)
        val btnGetOrder=findViewById<Button>(R.id.btn_getOrder)
        val btnImages=findViewById<Button>(R.id.btn_images)

        btnProduct.setOnClickListener {
            irActividad(CProduto::class.java)
        }
        btnRestaurant.setOnClickListener {
            irActividad(CRestaurante::class.java)
        }

        btnOrden.setOnClickListener {
            irActividad(COrdenes::class.java)
        }

        btnGetOrder.setOnClickListener {
            irActividad(DOrdersGet::class.java)
        }

        btnImages.setOnClickListener {
            irActividad(EIntents_Images::class.java)
        }

    }

    //FUNCIONES
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
            }
        }

        if(codigo!=null){
            startActivityForResult(intentEx,codigo)
        }else{
            startActivity(intentEx)
        }
    }
}