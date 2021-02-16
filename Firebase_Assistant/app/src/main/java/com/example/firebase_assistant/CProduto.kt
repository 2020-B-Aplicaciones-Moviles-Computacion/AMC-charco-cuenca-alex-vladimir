package com.example.firebase_assistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CProduto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_produto)

        val btnCreateProduct=findViewById<Button>(R.id.btn_createproduct)
        btnCreateProduct.setOnClickListener {
            createProduct()
        }
    }

    fun createProduct(){
        val etProductName=findViewById<EditText>(R.id.et_productname)
        val etProductPrice=findViewById<EditText>(R.id.et_productprice)

        val newProduct= hashMapOf<String,Any>(
            "nombre" to etProductName.text.toString(),
            "precio" to etProductPrice.text.toString().toDouble()
        )
        Log.i("fb-firestore","${newProduct}")

        val db= Firebase.firestore

        val referencia = db.collection("producto")
            .document()
            .set(newProduct)
        referencia
            .addOnSuccessListener {
                Toast.makeText(this, "Created in Firestore", LENGTH_SHORT).show()
            }
            .addOnFailureListener {

            }
    }
}