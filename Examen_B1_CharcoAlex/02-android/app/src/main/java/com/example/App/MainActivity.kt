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
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.lifecycleScope
import androidx.room.*
import com.example.App.Book
import com.example.App.LibraryDB
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var posItemSelected = 0
    var authorslv= arrayListOf<String>()
    var authorslvid= arrayListOf<Int>()
    val CODIGO_ACTUALIZAR_DATOS=102


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_CreateAuthor= findViewById<Button>(R.id.btn_create_author)
        btn_CreateAuthor.setOnClickListener {
            irActividad(Author_Create::class.java)
        }

    //Database
        val dbLibrary=LibraryDB.getInstance(this).LibraryDAO
        val authors= listOf(
                Author(0,"Victor Hugo",23,5.4f,true,"1741-01-02"),
                Author(0,"Humberto Eco",64,9.4f,false,"1942-03-05")
        )

        val books= listOf(
                Book(0,"Los miserables",451,9.9f,true,"1777-02-02",1),
                Book(0,"El nombre de la rosa",46,5.9f,false,"1841-02-02",1),
                Book(0,"Los pilares de la tierra",251,7.9f,true,"1985-02-02",2),
                Book(0,"Nuestra senora de paris",196,4.9f,false,"1999-02-02",2)
        )

        lifecycleScope.launch {
            if(dbLibrary.getauthors().size==0){
                authors.forEach{
                    dbLibrary.insertAuthor(it)
                }
                books.forEach {
                    dbLibrary.insertBook(it)
                }
            }
            authorslv.clear()
            authorslvid.clear()
            dbLibrary.getauthors().forEach {

                authorslv.add("${it.id.toString()} - ${it.name} - Edad: ${it.age.toString()} - Vivo: ${it.alive.toString()} - Peso: ${it.weigth} - Fecha Nacimiento: ${it.borndate}")
                authorslvid.add(it.id)
            }
            fillMenu()
        }



    }

    //CONTEXT MENU
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val info=menuInfo as AdapterView.AdapterContextMenuInfo
        val id=info.position
        posItemSelected=id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item?.itemId){
            R.id.ma_delete->{
                Log.i("intent-explicito","Deleting")
                val delete=LibraryDB.getInstance(this).LibraryDAO
                lifecycleScope.launch {
                    delete.deleteauthor(authorslvid[posItemSelected])
                    authorslv.clear()
                    authorslvid.clear()
                    delete.getauthors().forEach {
                        authorslv.add("${it.id.toString()} - ${it.name} - Edad: ${it.age.toString()} - Vivo: ${it.alive.toString()} - Peso: ${it.weigth} - Fecha Nacimiento: ${it.borndate}")
                        authorslvid.add(it.id)
                    }
                    fillMenu()
                }
                fillMenu()
                Toast.makeText(this,"Author Deleted", LENGTH_SHORT).show()

                return true
            }
            R.id.ma_edit->{
                Log.i("intent-explicito","Edit")
                irActividad(Author_Edit::class.java,authorslvid[posItemSelected],CODIGO_ACTUALIZAR_DATOS)
                return true
            }

            R.id.ma_books->{
                Log.i("intent-explicito","Books")
                irActividad(Books::class.java,authorslvid[posItemSelected],CODIGO_ACTUALIZAR_DATOS)
                return true
            }
            else-> super.onContextItemSelected(item)


        }
        fillMenu()
    }



    //Para actualizar el adaptador con cada cambio
    fun fillMenu(){
        var listAuthors=findViewById<ListView>(R.id.lv_authors)
        val adapterAuthors = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                authorslv
        )
        adapterAuthors.notifyDataSetChanged()
        listAuthors.adapter=adapterAuthors
        registerForContextMenu(listAuthors)
    }


    //FUNCIONES PARA ACTIVIDADES
    fun irActividad(
            clase:Class<*>,
            param:Int?=null,
            codigo:Int? =null
    ){
        val intentEx=Intent(this, clase)

        if(param!=null){
            intentEx.putExtra("idAuthor",param)
        }

        if(codigo!=null){
            startActivityForResult(intentEx,codigo)
        }else{
            startActivity(intentEx)
        }
    }

    override fun onActivityResult(
            requestCode: Int, //Codigo de peticion - por el usuario
            resultCode: Int, //Codigo reslado - OK, CNACELAD
            data: Intent? //OPcional, com.example.a02_android.data
    ) {
        super.onActivityResult(requestCode, resultCode, data)
       // fillMenu()
        when(requestCode){
            102->{
                if(resultCode== RESULT_OK){
                    Log.i("Intent-Explicit","Returned")
                    if(data!=null){
                        Log.i("Intent-Explicit","${data.getStringExtra("status")}")
                    }
                }else{
                    Log.i("Intent-Explicit","Not returned")
                }
            }

        }
    }


    override fun onResume() {
        super.onResume()
        if(!authorslv.isEmpty()){
            val resume=LibraryDB.getInstance(this).LibraryDAO
            authorslv.clear()
            authorslvid.clear()
            lifecycleScope.launch {
                resume.getauthors().forEach {
                    authorslv.add("${it.id.toString()} - ${it.name} - Edad: ${it.age.toString()} - Vivo: ${it.alive.toString()} - Peso: ${it.weigth} - Fecha Nacimiento: ${it.borndate}")
                    authorslvid.add(it.id)
                }
                fillMenu()
            }
        }

    }



}