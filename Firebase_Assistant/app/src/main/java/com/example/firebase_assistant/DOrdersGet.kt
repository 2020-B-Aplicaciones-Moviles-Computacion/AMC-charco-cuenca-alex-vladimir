
package com.example.firebase_assistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.firebase_assistant.DTO.FirestoreOrderDTO
import com.example.firebase_assistant.DTO.FirestoreRestaurantDTO
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DOrdersGet : AppCompatActivity() {

    var last: QueryDocumentSnapshot? =null
    var arrayOrders= arrayListOf<String>("a","b")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_orders_get)

        val lvOrders=findViewById<ListView>(R.id.tl_get_orders)

        //adaptador
        val adaptador = ArrayAdapter(
            this,//contexto
            android.R.layout.simple_list_item_1,
            arrayOrders
        )

        val btnGetOtders=findViewById<Button>(R.id.btn_get_orders)

        btnGetOtders.setOnClickListener {
            getOrders()
        }

        lvOrders.adapter=adaptador
        adaptador.notifyDataSetChanged()


    }




    fun getOrders(){
        val db= Firebase.firestore
        val reference=db.collection("orden")
        val adaptador = ArrayAdapter(
            this,//contexto
            android.R.layout.simple_list_item_1,  //layaour  (xml visual que existe en adroid)
            arrayOrders//lista de numeros
        )
        Log.i("4fb-firestore","Current last: ${last}")

        if(last==null){
            reference
                    .limit(1)
                    .get()
                    .addOnSuccessListener {

                        val Orden=it.documents[0].toObject(FirestoreOrderDTO::class.java)
                        Orden?.nombre=it.documents[0].id
                        Log.i("4fb-firestore","1: ${it}")
                        arrayOrders.add(Orden?.nombre.toString())
                        adaptador.notifyDataSetChanged()
                        last=it.last()

                    }
                    .addOnFailureListener {
                        Log.i("4fb-firestore","Nothing Recovered")
                    }
        }else{
            reference
                    .limit(1)
                    .startAfter(last)
                    .get()
                    .addOnSuccessListener {
                        val Orden=it.documents[0].toObject(FirestoreOrderDTO::class.java)
                        Orden?.nombre=it.documents[0].id
                        Log.i("4fb-firestore","2: ${it}")
                        arrayOrders.add(Orden?.nombre.toString())
                        adaptador.notifyDataSetChanged()
                        last=it.last()

                    }
                    .addOnFailureListener {
                        Log.i("4fb-firestore","Nothing Recovered")
                    }

        }

    }




}