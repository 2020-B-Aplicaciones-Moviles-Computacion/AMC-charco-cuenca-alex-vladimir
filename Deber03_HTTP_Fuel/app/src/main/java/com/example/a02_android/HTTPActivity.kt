package com.example.a02_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import com.google.android.material.textfield.TextInputEditText

class HTTPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h_t_t_p)

        //search
        val btng=findViewById<Button>(R.id.bt_get)
        btng.setOnClickListener {
            startMethod(Http_Get::class.java)
        }
        //update
        val btnu=findViewById<Button>(R.id.bt_update)
        btnu.setOnClickListener {
            startMethod(Http_Update::class.java)
        }
        //delete
        val btnd=findViewById<Button>(R.id.bt_delete)
        btnd.setOnClickListener {
            startMethod(Http_Delete::class.java)
        }
    }

    //Apertura Actividad
    fun  startMethod(
            clase:Class<*>
    ){
        val intentExplicito = Intent(
                this,
                clase
        )
        startActivity(intentExplicito)
    }

}