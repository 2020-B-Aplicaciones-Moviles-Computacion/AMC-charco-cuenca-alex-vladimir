package com.example.a02_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.result.Result
import com.google.android.material.textfield.TextInputEditText

class Http_Delete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http__delete)

        val btn_delete=findViewById<Button>(R.id.bt_del)
        btn_delete.setOnClickListener {
            metodoDelete()
        }
    }
    //Metodo Delete
    fun metodoDelete() {
        val txtid=findViewById<TextInputEditText>(R.id.txdel_id)
        var urlapi="https://jsonplaceholder.typicode.com/posts/"
        urlapi="https://jsonplaceholder.typicode.com/posts/"+txtid.text

        Log.i("http-klaxon", "URL: ${urlapi}")

        urlapi
            .httpDelete()
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val postString = result.get()
                        Log.i("http-klaxon", "Message: ${postString}")
                        Log.i("http-klaxon", "Deleted")
                    }

                }

            }

    }

}