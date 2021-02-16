package com.example.App

import android.content.Intent
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

class Book_Edit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book__edit)
        //Id Author
        val fkAuthor=intent.getIntExtra("fkAuthor",0)
        val idBook=intent.getIntExtra("idBook",0)

        //Get
        val in_name=findViewById<TextInputEditText>(R.id.in_ub_name)
        val in_pages=findViewById<EditText>(R.id.in_ub_pages)
        val in_punt=findViewById<EditText>(R.id.in_ub_punt)
        val in_avail=findViewById<Switch>(R.id.in_ub_avail)
        val in_year=findViewById<EditText>(R.id.in_ub_year)
        val in_month=findViewById<EditText>(R.id.in_ub_month)
        val in_day=findViewById<EditText>(R.id.in_ub_day)

        val Library=LibraryDB.getInstance(this).LibraryDAO

        //Fill text
        lifecycleScope.launch {
            val bookget=Library.getbookbyid(idBook)
            in_name.setText(bookget.name)
            in_pages.setText(bookget.pages.toString())
            in_punt.setText(bookget.puntuation.toString())
            in_avail.setChecked(bookget.available)
            in_year.setText(bookget.relsdate.substring(0,4))
            in_month.setText(bookget.relsdate.substring(5,7))
            in_day.setText(bookget.relsdate.substring(8,10))
        }

        //Button
        val btn_update_book=findViewById<Button>(R.id.btn_ubook)
        btn_update_book.setOnClickListener {
            val date:String=in_year.text.toString()+"-"+in_month.text.toString()+"-"+in_day.text.toString()
            val editBook=Book(idBook,in_name.text.toString(),in_pages.text.toString().toInt(),in_punt.text.toString().toFloat(),in_avail.isChecked,date,fkAuthor)


            lifecycleScope.launch {
                Library.updatebook(editBook)

            }
            Toast.makeText(this,"Book  Updated", Toast.LENGTH_SHORT).show()
            val intentResponse = Intent()
            intentResponse.putExtra("status","ok")
            this.setResult(
                RESULT_OK,
                intentResponse
            )
            this.finish()
            //
        }

    }
}