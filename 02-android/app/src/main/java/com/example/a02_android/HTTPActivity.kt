package com.example.a02_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
class HTTPActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h_t_t_p)
        //metodoGet()
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
                        val postString = result.get()
                        Log.i("http-klaxon", "Message: ${postString}")
                        val post=Klaxon()
                                .parse<IPostHttp>(postString)
                        Log.i("http-klaxon","Title es: ${post?.title}")

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


    }



}