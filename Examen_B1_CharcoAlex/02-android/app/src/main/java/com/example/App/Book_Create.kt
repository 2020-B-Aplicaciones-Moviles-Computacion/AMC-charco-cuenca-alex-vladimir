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

class Book_Create : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book__create)
        //Id Author
        val fkAuthor=intent.getIntExtra("fkAuthor",0)

        //Get
        val in_name=findViewById<TextInputEditText>(R.id.in_cb_name)
        val in_pages=findViewById<EditText>(R.id.in_cb_pages)
        val in_punt=findViewById<EditText>(R.id.in_cb_punt)
        val in_avail=findViewById<Switch>(R.id.in_cb_avail)
        val in_year=findViewById<EditText>(R.id.in_cb_year)
        val in_month=findViewById<EditText>(R.id.in_cb_month)
        val in_day=findViewById<EditText>(R.id.in_cb_day)

        //Button
        val btn_create_book=findViewById<Button>(R.id.btn_createb)
        btn_create_book.setOnClickListener {
            val date:String=in_year.text.toString()+"-"+in_month.text.toString()+"-"+in_day.text.toString()
            val newBook=Book(0,in_name.text.toString(),in_pages.text.toString().toInt(),in_punt.text.toString().toFloat(),in_avail.isChecked,date,fkAuthor)

            Log.i("inbook","${in_name.text.toString()},${in_pages.text.toString().toInt()},${in_punt.text.toString().toFloat()},${in_avail.isChecked},${fkAuthor}")

            val Library=LibraryDB.getInstance(this).LibraryDAO

            lifecycleScope.launch {
                Library.insertBook(newBook)
            }
            Toast.makeText(this,"Book Created", Toast.LENGTH_SHORT).show()
            this.finish()

        }

    }
}