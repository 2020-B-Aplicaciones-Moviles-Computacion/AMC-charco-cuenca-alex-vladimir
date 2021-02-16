package com.example.a02_android

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {
    var posItemSelected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        dbMemory.cargaInicialDatos()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_list_view)

//        dbMemory.cargaExterna(b1.name,b1.desc)

        val adaptador = ArrayAdapter(
                this,//contexto
                android.R.layout.simple_list_item_1,  //layaour  (xml visual que existe en adroid)
                dbMemory.arrayString//lista de numeros
        )

        //cualquier cosa que se mostrara visualemnte y se itere, como listras
        //VA a necesitar un componente visual similar a listview, hay otros componentes a parte de este
        //El adaptador ayudara a definir como se vera visualmente dentro de la lsita que se usara

        val listView = findViewById<ListView>(R.id.lv_entrenador)

        listView.adapter=adaptador

/*
        listView.setOnItemLongClickListener{
                    parent,
                    view,
                    position,
                    id->
            Log.i("Intent-Explicit-LView","Hello :${position},${id}")

            //alertas
            val builder=AlertDialog.Builder(this)

            val selecUser = booleanArrayOf(
                    true,false,false,true
            )

            val opciones = resources.getStringArray(R.array.string_array_opciones_dialogo)

            builder.setTitle("Titulo")

            builder.setMultiChoiceItems(
                   opciones,
                    selecUser,
                    DialogInterface.OnMultiChoiceClickListener{
                        dialog,which,isChecked->
                        Log.i("Intent-Explicit-LView","Selecciono: ${which},${isChecked}")
                    }
                    //arreglo de booleanos de lo que esta o noo seleccionado
            )

            

 //           builder.setMessage("Hola")

            builder.setPositiveButton(
                            "Si",
                            DialogInterface.OnClickListener { dialog,which->
                                Log.i("Intent-Explicit-LView","Si")
                            }

                    )
            builder.setNegativeButton(
                            "No",
                            null
                    )

            //creo el dialogo
            val dialogo = builder.create()
            dialogo.show()

            return@setOnItemLongClickListener true

        }

*/

        //para notificar los cambios en la lista, como aumentar algo dentro del arreglo,
        adaptador.notifyDataSetChanged()

        registerForContextMenu(listView)

        val btnAddLV = findViewById<Button>(R.id.btn_anadir_item_lv)

        btnAddLV.setOnClickListener{
            anadirListView(adaptador,BEntrenador("Entrenador X", "Descripcion X"),dbMemory.arrayString)
        }

        //dbMemory.arregloEnteros.add(5)
        //dbMemory.arregloEnteros.add(6)


    }

    fun anadirListView(
            adaptador: ArrayAdapter<BEntrenador>,
            item: BEntrenador,
            arreglo: ArrayList<BEntrenador>
    ){
        arreglo.add(item)
        adaptador.notifyDataSetChanged()
    }

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
    //Dependiendo del item selecconado, tendramos diferntes opciones de menu, y segun la opcion de menu
    //se hara algo diferente
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId){
            R.id.mi_editar->{
                Log.i("intent-explicito","Editar ${dbMemory.arrayString[posItemSelected]}")
                return true
            }
            R.id.mi_eliminar->{
                Log.i("intent-explicito","Eliminar ${dbMemory.arrayString[posItemSelected]}")
                return true
            }
            else-> super.onContextItemSelected(item)


        }

    }


}