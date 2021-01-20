package com.example.firebase_assistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BFirestore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_firestore)

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

    }
}