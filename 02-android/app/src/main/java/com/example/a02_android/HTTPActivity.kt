package com.example.a02_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
class HTTPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h_t_t_p)
        metodoPost()

    }

    //Metodo GET
    fun metodoGet() {
        "https://jsonplaceholder.typicode.com/posts/1"
            .httpGet()
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val userString = result.get()
                        Log.i("http-klaxon", "Message: ${userString}")
                    }
                }
            }
    }

    //Metodo POST
    fun metodoPost() {
        val param: List<Pair<String, *>>
        param = listOf(
            "title" to "Alexander",
            "body" to "Yadda Yadda Yadda",
            "iserId" to 1
        )

        "https://jsonplaceholder.typicode.com/posts"
            .httpPost(param)
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val userString = result.get()
                        Log.i("http-klaxon", "Message: ${userString}")
                    }
                }
            }
    }
}