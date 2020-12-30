package com.example.a02_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import com.google.android.material.textfield.TextInputEditText

class Http_Update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http__update)

        val btn_update = findViewById<Button>(R.id.bt_put)
        btn_update.setOnClickListener {
            metodoPut()
        }
    }
    fun metodoPut() {
        val txtid=findViewById<TextInputEditText>(R.id.txupd_id)
        val txtuserid=findViewById<TextInputEditText>(R.id.txupd_userId)
        val txttitle=findViewById<TextInputEditText>(R.id.txupd_tittle)
        val txtbody=findViewById<TextInputEditText>(R.id.txupd_body)

        var urlapi="https://jsonplaceholder.typicode.com/posts/"+txtid.text


        val param:List<Pair<String,*>> = listOf(
            "userId" to txtuserid.text,
            "title" to txttitle.text,
            "body" to txtbody.text
        )
        txtbody.setText("")
        txtid.setText("")
        txtuserid.setText("")
        txttitle.setText("")


        urlapi
            .httpPut(param)
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

}}