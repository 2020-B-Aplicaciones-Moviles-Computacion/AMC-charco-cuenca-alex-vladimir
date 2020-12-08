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

        //boton de ciclo de vida
        val btCicloVida = findViewById<Button>(R.id.button_ir_ciclo_vida)
        btCicloVida.setOnClickListener{
            irLifeCycle()
        }

        //boton de listview
        val btListView = findViewById<Button>(R.id.btn_ir_list_view)
        btListView.setOnClickListener{
            irListView()
        }


    }

    fun irListView(){
        val intentExplicito2 = Intent(
                this,
                BListView::class.java
        )
        startActivity(intentExplicito2)
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