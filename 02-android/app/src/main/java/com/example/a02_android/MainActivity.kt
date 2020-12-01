package com.example.a02_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("main1","oncreate")

        val btCicloVida = findViewById<Button>(R.id.button_ir_ciclo_vida)
        btCicloVida.setOnClickListener{
            irLifeCycle()
        }

    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i("main1","ondestroy")
    }


    fun irLifeCycle(){
        val intentExplicito = Intent(
            this,
            A_lifecycle::class.java
        )
        startActivity(intentExplicito)
    }

}