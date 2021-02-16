package com.example.a02_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.android.material.textfield.TextInputEditText

class Http_Get : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http__get)

        val btn_search1 = findViewById<Button>(R.id.bt_search1)
        btn_search1.setOnClickListener{
            metodoGet()
        }
    }


    //Metodo GET
    fun metodoGet() {
        val txtid=findViewById<TextInputEditText>(R.id.txget_id)
        var urlapi="https://jsonplaceholder.typicode.com/posts/"
        urlapi="https://jsonplaceholder.typicode.com/posts/"+txtid.text

        Log.i("http-klaxon", "URL: ${urlapi}")

        urlapi
            .httpGet()
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: ${error}")
                    }
                    is Result.Success -> {
                        val postString = result.get()
                        Log.i("http-klaxon", "Message: ${postString}")
                        val post= Klaxon()
                            .parse<IPostHttp>(postString)

                        Log.i("http-klaxon", "id: ${post?.id}")
                        Log.i("http-klaxon", "userId: ${post?.userId}")
                        Log.i("http-klaxon", "tittle: ${post?.title}")
                        Log.i("http-klaxon", "body: ${post?.body}")

                    }
                }
            }

        //ARRAY POST
/*
        "https://jsonplaceholder.typicode.com/posts"
                .httpGet()
                .responseString { req, res, result ->
                    when (result) {
                        is Result.Failure -> {
                            val error = result.getException()
                            Log.i("http-klaxon", "Error: ${error}")
                        }
                        is Result.Success -> {
                            val postString = result.get()
                            Log.i("http-klaxon", "${postString}")
                            val arrayPost = Klaxon()
                                    .parseArray<IPostHttp>(postString)
                            if (arrayPost != null) {
                                arrayPost.forEach {
                                    Log.i("http-klaxon", "${it.title}")
                                }
                            }

                        }
                    }
                }
    */}
    /*
    //Metodo POST
    fun metodoPost(){
        val param:List<Pair<String,*>> = listOf(
                "title" to "Cosme",
                "body" to "Yada yada yada",
                "userId" to 25
        )
    "https://jsonplaceholder.typicode.com/posts"
            .httpPost(param)
            .responseString{req,res,result->
                when(result){
                    is Result.Failure->{
                        val error=result.getException()
                        Log.i("http-klaxon","${error}")
                    }
                    is Result.Success->{
                        val postString=result.get()
                        Log.i("http-klaxon","${postString}")
                        val post=Klaxon()
                                .parse<IPostHttp>(postString)
                        Log.i("http-klaxon","${post?.title}")
                    }
                }
            }
    }*/



}