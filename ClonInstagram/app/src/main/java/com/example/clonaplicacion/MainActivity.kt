  package com.example.clonaplicacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_start=findViewById<Button>(R.id.btn_loogin)

        btn_start.setOnClickListener {
            irActividad(Instagram::class.java)
        }
    }

    fun irActividad(
            clase:Class<*>
    ){
        val intentEx= Intent(
                this,
                clase
        )
         startActivity(intentEx)
    }




}