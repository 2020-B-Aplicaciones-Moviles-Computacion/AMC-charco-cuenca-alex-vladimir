 package com.example.examplervh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val array1= arrayListOf<String>("a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b","a","b")
        val rv=findViewById<RecyclerView>(R.id.rv1)
        val rv2=findViewById<RecyclerView>(R.id.rv2)
        val lm=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        val lm2=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        val adap=AdapterRV(array1)
        rv.layoutManager=lm
        rv2.layoutManager=lm2
        rv.setHasFixedSize(true)
        rv2.setHasFixedSize(true)
        rv.adapter=adap
        rv2.adapter=adap


    }
}