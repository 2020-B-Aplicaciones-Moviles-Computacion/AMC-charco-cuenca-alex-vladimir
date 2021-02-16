package com.example.firebase_assistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CRestaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_restaurante)

        val btnCreateRestaurant=findViewById<Button>(R.id.btn_createrestaurant)
        btnCreateRestaurant.setOnClickListener {
            createRestaurant()
        }
    }

    fun createRestaurant(){
        val etRestaurantName=findViewById<EditText>(R.id.et_restaurantname)

        val newProduct= hashMapOf<String,Any>(
            "nombre" to etRestaurantName.text.toString()
        )
        Log.i("fb-firestore","${newProduct}")

        val db= Firebase.firestore

        val referencia = db.collection("restaurantes")
            .document()
            .set(newProduct)
        referencia
            .addOnSuccessListener {
                etRestaurantName.setText("")
                Toast.makeText(this, "Created in Firestore", Toast.LENGTH_SHORT).show()
                this.finish()
            }
            .addOnFailureListener {

            }
    }
}