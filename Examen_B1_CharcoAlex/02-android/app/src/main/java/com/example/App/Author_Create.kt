package com.example.App

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class Author_Create : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author__create)

        val in_name=findViewById<TextInputEditText>(R.id.in_a_name)
        val in_age=findViewById<EditText>(R.id.in_a_age)
        val in_weigth=findViewById<EditText>(R.id.in_a_weigth)
        val in_alive=findViewById<Switch>(R.id.in_a_alive)
        val in_year=findViewById<EditText>(R.id.in_a_year)
        val in_month=findViewById<EditText>(R.id.in_a_month)
        val in_day=findViewById<EditText>(R.id.in_a_day)

        //button Create
        val btn_createA=findViewById<Button>(R.id.btn_create1)
        btn_createA.setOnClickListener {
            val date:String=in_year.text.toString()+"-"+in_month.text.toString()+"-"+in_day.text.toString()
            val newAuthor=Author(0,in_name.text.toString(),in_age.text.toString().toInt(),in_weigth.text.toString().toFloat(),in_alive.isChecked,date)

            val Library=LibraryDB.getInstance(this).LibraryDAO
            lifecycleScope.launch {
                Library.insertAuthor(newAuthor)
            }
            Toast.makeText(this,"Author Created", Toast.LENGTH_SHORT).show()
            this.finish()
        }

    }
}