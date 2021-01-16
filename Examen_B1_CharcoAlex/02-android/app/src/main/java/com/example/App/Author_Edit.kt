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

class Author_Edit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author__edit)

        //Gets
        val in_name=findViewById<TextInputEditText>(R.id.in_e_name)
        val in_age=findViewById<EditText>(R.id.in_e_age)
        val in_weigth=findViewById<EditText>(R.id.in_e_weigth)
        val in_alive=findViewById<Switch>(R.id.in_e_alive)
        val in_year=findViewById<EditText>(R.id.in_e_year)
        val in_month=findViewById<EditText>(R.id.in_e_month)
        val in_day=findViewById<EditText>(R.id.in_e_day)


        val idAuthor=intent.getIntExtra("idAuthor",0)

        val Library=LibraryDB.getInstance(this).LibraryDAO

        //Fill text
        lifecycleScope.launch {
            val authorupdate=Library.getauthorbyid(idAuthor)
            in_name.setText(authorupdate.name)
            in_age.setText(authorupdate.age.toString())
            in_weigth.setText(authorupdate.weigth.toString())
            in_alive.setChecked(authorupdate.alive);
            in_year.setText(authorupdate.borndate.substring(0,4))
            in_month.setText(authorupdate.borndate.substring(5,7))
            in_day.setText(authorupdate.borndate.substring(8,10))
        }

        //Boton
        val btn_editA=findViewById<Button>(R.id.btn_e_author)
        btn_editA.setOnClickListener {
            val date:String=in_year.text.toString()+"-"+in_month.text.toString()+"-"+in_day.text.toString()
            var editAuthor=Author(idAuthor,in_name.text.toString(),in_age.text.toString().toInt(),in_weigth.text.toString().toFloat(),in_alive.isChecked,date)

            lifecycleScope.launch {
                Library.updateauthor(editAuthor)
            }
            Toast.makeText(this,"Author Updated", Toast.LENGTH_SHORT).show()
            val intentResponse = Intent()
            intentResponse.putExtra("status","ok")
            this.setResult(
                RESULT_OK,
                intentResponse
            )
            this.finish()
        }

    }

}