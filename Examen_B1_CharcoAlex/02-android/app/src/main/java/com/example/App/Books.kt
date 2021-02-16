package com.example.App

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class Books : AppCompatActivity() {
    var posItemSelected = 0
    var bookslv= arrayListOf<String>()
    var bookslvid= arrayListOf<Int>()
    val CODIGO_ACTUALIZAR_DATOS=102
    var idAuthor: Int? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        idAuthor=intent.getIntExtra("idAuthor",0)
        Log.i("inbook","fk: ${idAuthor}")

        //Boton crear libro
        val btn_CreateBook=findViewById<Button>(R.id.btn_create_book)
        btn_CreateBook.setOnClickListener {
            irActividad(Book_Create::class.java,idAuthor,CODIGO_ACTUALIZAR_DATOS)
        }

        //Database
        val dbLibrary=LibraryDB.getInstance(this).LibraryDAO

        lifecycleScope.launch {
            bookslv.clear()
            bookslvid.clear()
            dbLibrary.getbookbyfk(idAuthor!!).forEach {
                bookslv.add("${it.id.toString()} - ${it.name} - Paginas: ${it.pages.toString()} - Disponible: ${it.available.toString()} - Puntuacion: ${it.puntuation.toString()}" +
                        " - Fecha Lanzamiento: ${it.relsdate}")
                bookslvid.add(it.id)
                Log.i("book","${it.name}")
            }
            fillMenu()
        }

    }

    //FUNCIONES
    //CONTEXT MENU
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu2, menu)

        val info=menuInfo as AdapterView.AdapterContextMenuInfo
        val id=info.position
        posItemSelected=id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val contexto=this
        val adaptor=ArrayAdapter(
                contexto,
                android.R.layout.simple_list_item_1,
                bookslv
        )
        return when (item?.itemId){
            R.id.mb_delete->{
                Log.i("intent-explicito","Deleting")
                val delete=LibraryDB.getInstance(this).LibraryDAO
                lifecycleScope.launch {
                    delete.deletebook(bookslvid[posItemSelected])
                    adaptor.notifyDataSetChanged()
                    bookslv.clear()
                    bookslvid.clear()
                    delete.getbookbyfk(idAuthor!!).forEach {
                        bookslv.add("${it.id.toString()} - ${it.name} - Paginas: ${it.pages.toString()} - Disponible: ${it.available.toString()} - Puntuacion: ${it.puntuation.toString()} - Fecha Lanzamiento: ${it.relsdate}")
                        bookslvid.add(it.id)
                        Log.i("book","${it.name}")
                    }

                    fillMenu()
                }
                Toast.makeText(this,"Book Deleted", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.mb_edit->{
                Log.i("intent-explicito","Edit")
                irActividad(Book_Edit::class.java,idAuthor,bookslvid[posItemSelected],CODIGO_ACTUALIZAR_DATOS)
                return true
            }

            else-> super.onContextItemSelected(item)


        }

    }

    fun irActividad(
        clase:Class<*>,
        param1:Int?=null,
        param2:Int?=null,
        codigo:Int? =null
    ){
        val intentEx= Intent(this, clase)

        if(param1!=null){
            intentEx.putExtra("fkAuthor",param1)
        }
        if(param2!=null){
            intentEx.putExtra("idBook",param2)
        }


        if(codigo!=null){
            startActivityForResult(intentEx,codigo)
        }else{
            startActivity(intentEx)
        }
    }

    //llenado con adaptador
    fun fillMenu(){
        val listBooks=findViewById<ListView>(R.id.lv_books)

        val adapterBooks = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                bookslv
        )
        adapterBooks.notifyDataSetChanged()
        listBooks.adapter=adapterBooks
        registerForContextMenu(listBooks)
    }

    override fun onResume() {
        super.onResume()
        if(!bookslv.isEmpty()){
            val resume=LibraryDB.getInstance(this).LibraryDAO
            val idAuthorResume=intent.getIntExtra("idAuthor",0)
            bookslv.clear()
            bookslvid.clear()
            lifecycleScope.launch {
                resume.getbookbyfk(idAuthorResume).forEach {
                    bookslv.add("${it.id.toString()} - ${it.name} - Paginas: ${it.pages.toString()} - Disponible: ${it.available.toString()} - Puntuacion: ${it.puntuation.toString()} - Fecha Lanzamiento: ${it.relsdate}")
                    bookslvid.add(it.id)
                }
                fillMenu()
        }

        }
    }
}